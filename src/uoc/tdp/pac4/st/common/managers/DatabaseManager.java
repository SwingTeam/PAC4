package uoc.tdp.pac4.st.common.managers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;


/***
 * Classe encarregada de gestionar
 * les comunicacions amb la base
 * de dades
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class DatabaseManager {

	//CONSTANTS
	private static final String BEGIN_GROUP = " (";
	private static final String COMMA = ", ";
	private static final String END_GROUP = ") ";
	private static final String FROM = "FROM ";
	private static final String INSERT_INTO = "INSERT INTO ";
	private static final String ORDER_ASCENDING = " ASC";
	private static final String ORDER_BY = "ORDER BY ";
	private static final String SELECT_ALL = "SELECT * ";
	private static final String SELECT_COUNT = "SELECT COUNT(*) ";
	private static final String SPACE = " ";
	private static final String VALUES = ") VALUES (";
	private static final String WHERE = "WHERE ";

	//FIELDS
	private Connection _dbConnection = null;
	private String _dbDriver = null;
	private String _dbPassword = null;
	private String _dbURL = null;
	private String _dbUserName = null;
	private Savepoint _savepoint = null;
	private boolean _transactionInProgress = false;

	/***
	 * Constructor
	 * 
	 * @throws STException 
	 */
	public DatabaseManager() throws STException{
		this(Constants.SETTINGS_FILE_SERVER);
	}

	/***
	 * Constructor
	 * 
	 * @param settingFile Nom del fitxer de configuració
	 * que utilitzarà per a llegir els valors emmagatzemats.
	 * 
	 * @throws STException 
	 */
	public DatabaseManager(String settingFile) throws STException{
		this.getConnectionSettings(settingFile);
	}

	/***
	 * Tanca la connexió amb la base de dades
	 * @throws SQLException 
	 */
	private void closeConnection() throws STException{
		this.closeConnection(false);
	}
	
	/***
	 * Tanca la connexió amb la base de dades
	 * 
	 * @param forceClosing Indica que es farà el
	 * tancament amb independència de que hi hagi
	 * una transacció en curs.
	 * @throws SQLException 
	 */
	private void closeConnection(boolean forceClosing) throws STException{
		try{
		if (this._dbConnection != null
				&& this.isOpen()
				&& (!this._transactionInProgress || forceClosing))
				this._dbConnection.close();
		} catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_DATABASE_CLOSING);
		}
	}
	
	/***
	 * Tanca un objecte Statement si
	 * aquest es troba obert
	 * 
	 * @param statement Objecte Statement que
	 * serà tancat.
	 */
	private void closeStatement(Statement statement) throws STException{
		try{
		if (statement != null
				&& !statement.isClosed())
			statement.close();
			statement = null;
		} catch (SQLException e){
			throw new STException (e, TokenKeys.ERROR_DATABASE_CLOSING_STATEMENT);
		}
	}

	 /***
	  * Realitza un commit de la
	  * última sentència executada,
	  * 
	  * @throws SQLException
	  */
	 private void commit() throws STException{
		 this.commit(false);
	 }
	
	 /***
	  * Realitza un commit de la
	  * última sentència executada,
	  * sempre que no s'hagi obert
	  * una transaction, o de totes
	  * les actualitzacions pendents,
	  * si s'indica forceCommit.
	  * 
	  * @param forceCommit Indica si s'ha de fer
	  * el commit amb independència de si s'ha
	  * iniciat una transacció.
	  * 
	  * @throws SQLException
	  */
	 private void commit(boolean forceCommit) throws STException{
		 try{
		 if ((!this._transactionInProgress || forceCommit)
				 && this.isOpen())
			 this._dbConnection.commit();
		 } catch (SQLException e){
			 throw new STException(e);
			 
		 } catch (STException e){
			 throw e;
		 }
	 }

	 /***
	  * Converteix un objecte a una cadena vàlida
	  * per a ser utilitzada en sentències amb postgreSQL
	  * 
	  * @param value Valor a convertir
	  * @return Una cadena vàlida per ser utilitzada
	  * a postgreSQL.
	  */
	 private String convertToPostfgreSQLString(Object value){
		 String result = null;
		 if ((value instanceof String)
				 || (value instanceof Character))
			 result = "'" + value.toString() + "'";
		 
		 else if (value instanceof java.util.Date)
			 result = "'" + Methods.convertToPostgreSQLDateFormat((java.util.Date)value) + "'";

		 else if (value instanceof java.sql.Date)
			 result = "'" + Methods.convertToPostgreSQLDateFormat((java.sql.Date)value) + "'";
		 
		 else if (value instanceof Boolean)
			 result = value.toString().toLowerCase();
		 
		 else 
			 result = value.toString();
		 
		 return result;
	 }
	 
	 /***
	  * Mètode que retorna el nombre de
	  * registres de una taula.
	  *
	  * @param table Nom de la tabla de la
	  * que es vol conéixer el nombre de files.
	  * @return Integer amb el nombre de registres
	  * de la taula.
	  * @throws STException
	  */
	 public long countTableRows(String table) throws STException{
		 long result = 0;
		 Statement statement = null;
		 ResultSet resultSet = null;
		 
		 try{
			this.openConnection();
			statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.FETCH_FORWARD);
			String sqlSentence =  SELECT_COUNT +
							         FROM + table;
			
			resultSet = statement.executeQuery(sqlSentence);
			if (resultSet != null){
				resultSet.next();
				result = (long) resultSet.getObject(1);
				resultSet.close();
			}
			this.closeStatement(statement);
			this.closeConnection();
		 
		 } catch (SQLException e){
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 throw e;
		 
		 } catch (Exception e){
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } finally{
			 this.closeConnection();
			 this.closeStatement(statement);
		 }
		return result;
	 }
	 
	 /***
	  * 
	  * Executa una sentència SQL d'eliminació
	  * de dades, retornant un integer amb el nombre
	  * de registres afectats per l'eliminació.
	  * 
	  * @param sql Cadena amb la sentència SQL
	  * que s'executarà.
	  * 
	  * @return Integer amb el nombre de registres
	  * afectats per l'eliminació.
	  * @throws STException 
	  */
	 public int deleteData(String sql) throws STException{
		 return this.updateData(sql);
	 }
	 
	 /***
	  * Finalitza una transacció, realitzant
	  * un commit o rollback segons s'indiqui
	  * al paràmetre d'entrada
	  * 
	 * @throws STException 
	  */
	 public void finishTransaction(boolean commit) throws STException{
		 try{
			 if (this._dbConnection != null
					 && this.isOpen()){
				 if (commit)
					 this.commit(true);
				 else
					 this.rollback();
			 }
			 this.closeConnection(true);
			 
		 } catch (STException e){
			 throw new STException(e, TokenKeys.ERROR_DATABASE_ENDING_TRANSACTION);

		 } finally {
			 this._savepoint = null;
			 this._transactionInProgress = false;
		 }
	 }
	 
	/***
	 * Llegeix els paràmetres de connexió
	 * a la base de dades i emmagatzemats
	 * a l'arxiu de configuració
	 * 
	 * @param settingFile Nom del fitxer de configuració que
	 * utilitzarà per a llegir els valors emmagatzemats.
	 * 
	 * @throws STException 
	 */
	private void getConnectionSettings(String settingFile) throws STException{
		try{
			//Llegim els paràmetres d'accés
			SettingManager settingManager = new SettingManager(settingFile);
			
			//Driver de java per a la connexió a postgreSQL
			this._dbDriver = Methods.trim(settingManager.getSetting(Constants.DB_DRIVER));
			//Url d'accés a la base de dades
			this._dbURL = Methods.trim(settingManager.getSetting(Constants.DB_URL));
			//Usuari
			this._dbUserName = Methods.trim(settingManager.getSetting(Constants.DB_USERNAME));
			//Contrasenya
			this._dbPassword = Methods.trim(settingManager.getSetting(Constants.DB_PASSWORD));
			
			settingManager = null;
			
		} catch (IOException e){
			throw new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE);
			
		} catch (Exception e){
			throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
		}
	}
	
	//TODO -> Implement other specific methods for general classes (Users, Products, etc).
	 /***
	  * Mètode que retorna una llista d'un
	  * local específic o, si s'informa un null,
	  * de tots els locals.
	  * 
	  * @param taxId Local específic o null si
	  * es vol recuperar tots els locals. 
	  * @return Una instància de List<Local> amb
	  * el resultat de la consulta.
	  * @throws SQLException
	  * @throws STException
	  */
	 public List<Local> getEstablishmentList(String taxId) throws STException{
		 List<Local> result = new ArrayList<Local>();
		 Statement statement = null;
		 ResultSet resultSet = null;
		 
		 try{
			if (this.openConnection()){
				statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																ResultSet.CONCUR_READ_ONLY);
				String sqlSentence = SELECT_ALL +
								        FROM + Constants.TABLE_LOCAL + " ";
				if (taxId != null){
					sqlSentence += WHERE + Constants.FIELD_CIF +  " = '" + taxId + "' ";
				}
				sqlSentence +=  ORDER_BY + Constants.FIELD_NOMLOCAL + ORDER_ASCENDING;
				
				resultSet = statement.executeQuery(sqlSentence);
				this.closeConnection();

				while (resultSet.next()) {
					Local item = new Local();
					item.setTaxId(resultSet.getString(Constants.FIELD_CIF));
					item.setName(resultSet.getString(Constants.FIELD_NOMLOCAL));
					item.setProvince(resultSet.getString(Constants.FIELD_PROVINCIA_ID));
					item.setPhone(resultSet.getString(Constants.FIELD_TELEFON));
					item.setLatitude(resultSet.getFloat(Constants.FIELD_COORDX));
					item.setLongitude(resultSet.getFloat(Constants.FIELD_COORDY));
					result.add(item);
				}
			} else {
				throw new STException(TokenKeys.ERROR_DATABASE_CONNECTION); 
			}
		 
		 } catch (SQLException e){
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 throw e;
		 
		 } catch (Exception e){
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } finally{
			 this.closeConnection();
			 this.closeStatement(statement);
		 }
		return result;
	 }

	/***
	 * Retorna el valor de la primera columna
	 * de la primera fila del resultat d'una
	 * sentència SELECT.
	 * 
	 * @param sql cadena amb la sentència
	 * de recuperació de dades a executar.
	 * 
	 * @return Object amb el valor de la
	 * primera columna de la primera fila
	 * del resultat de la sentència.
	 * @throws STException 
	 */
	public Object getScalar(String sql) throws STException{
		Object result = null;
		try{
			ResultSet resultSet = this.selectData(sql);
			if (resultSet != null){
				resultSet.next();
				result = resultSet.getObject(1);
			}
			resultSet.close();
			
		} catch (SQLException e){
			throw new STException(e);
		
		} catch (STException e){
			throw e;
		}
		
		return result;
	}

	 /***
	  * 
	  * Executa una sentència SQL d'inserció
	  * de dades, retornant un integer amb el nombre
	  * de registres afectats per l'eliminació.
	  * 
	  * @param table Nom de la tabla a on es
	  * farà la inserció.
	  * @param hashMap Instància de HashMap<String, Object>
	  * amb la col.lecció
	  * de camps i valors que s'inseriran.
	  * 
	  * @return Integer amb el nombre de registres
	  * afectats per la inserció.
	  * @throws STException 
	  */
	 public int insertData(String table, Map<String, Object> hashMap) throws STException{
		 StringBuilder sql = new StringBuilder();
		 
		 sql.append(INSERT_INTO);
		 sql.append(table);
		 sql.append(BEGIN_GROUP);
		 
		 Set<String> entries = hashMap.keySet();
		 
		 StringBuilder values = new StringBuilder();
		 for(String entry : entries){
			 if (values.length() > 0){
				 sql.append(COMMA);
				 values.append(COMMA);
			 }
			 sql.append(entry);
			 Object value = hashMap.get(entry);
			 values.append(this.convertToPostfgreSQLString(value));
		 }
		 sql.append(VALUES);
		 sql.append(values.toString());
		 sql.append(END_GROUP);
		 
		 return this.insertData(sql.toString());
	 }

	 /***
	  * 
	  * Executa una sentència SQL d'inserció
	  * de dades, retornant el valor de la sequencia insertada  
	  * 
	  * @param sql Cadena amb la sentència SQL
	  * que s'executarà.
	  * 
	  * @return Integer amb el nombre de registres
	  * afectats per la inserció.
	  * @throws STException 
	  */
	 public int insertDataAndReturnId(String table, Map<String, Object> hashMap) throws STException{
		 StringBuilder sql = new StringBuilder();
		 
		 sql.append(INSERT_INTO);
		 sql.append(table);
		 sql.append(BEGIN_GROUP);
		 
		 Set<String> entries = hashMap.keySet();
		 
		 StringBuilder values = new StringBuilder();
		 for(String entry : entries){
			 if (values.length() > 0){
				 sql.append(COMMA);
				 values.append(COMMA);
			 }
			 sql.append(entry);
			 Object value = hashMap.get(entry);
			 values.append(this.convertToPostfgreSQLString(value));
		 }
		 sql.append(VALUES);
		 sql.append(values.toString());
		 sql.append(END_GROUP);
		 
		 int id= -1;
		 Statement statement = null;
		 try{
			if (this.openConnection()){
				this.setAutoCommit(false);
				statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																ResultSet.CONCUR_UPDATABLE);
				statement.execute(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				
				ResultSet resultSet = statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					id= resultSet.getInt(1);
				}
				resultSet.close();
				this.commit();
			} else {
				throw new STException(TokenKeys.ERROR_DATABASE_CONNECTION); 
			}
		 
		 } catch (SQLException e){
			 this.rollback();
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 this.rollback();
			 throw e;
		 
		 } catch (Exception e){
			 this.rollback();
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } finally{
			 this.closeConnection();
			 this.closeStatement(statement);
		 }
		return id;
	 }
	 
	 /***
	  * 
	  * Executa una sentència SQL d'inserció
	  * de dades, retornant un integer amb el nombre
	  * de registres afectats per l'eliminació.
	  * 
	  * @param sql Cadena amb la sentència SQL
	  * que s'executarà.
	  * 
	  * @return Integer amb el nombre de registres
	  * afectats per la inserció.
	  * @throws STException 
	  */
	 public int insertData(String sql) throws STException{
		 return this.updateData(sql);
	 }
	
	/***
	 * Indica si la connexió
	 * amb la base de dades està oberta.
	 * 
	 * @return boolean indicant si está o no
	 * oberta la connexió
	 * @throws STException
	 */
	public boolean isOpen() throws STException{
		boolean result = false;
		try{
			if (this._dbConnection != null){
				result = !this._dbConnection.isClosed();
			}
		} catch(SQLException e){
			throw new STException(e);
		}
		return result;
	}
	
	/***
	 * Inicia la connexió amb la base de dades
	 * 
	 * @return boolean amb l'estat de la connexió
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private boolean openConnection() throws STException{
		boolean result = false; 
		try{
			if (this._dbConnection != null){
				//Com a màxim esperarem 10sg. per a comprovar la connexió
				if (!this._dbConnection.isClosed()){
					result = true;
				} else {
					this._dbConnection.close();
					this._dbConnection = null;
				}
			}
			if (this._dbConnection == null){
				//Recupera la classe del driver de java per a la connexió a postgreSQL
				Class.forName(this._dbDriver);
				//Obrim la connexió a la base de dades
				this._dbConnection = DriverManager.getConnection(this._dbURL, this._dbUserName, this._dbPassword);
				//Com a màxim esperarem 10sg. per a comprovar la connexió
				result = this._dbConnection.isValid(10);
				//Reset de les variables internes
				this._savepoint = null;
				this._transactionInProgress = false;
			}

		} catch (SQLException | ClassNotFoundException e){
			throw new STException(e, TokenKeys.ERROR_DATABASE_OPENING);
		}
		return result;
	}
	
	 /***
	  * Realitza un rollback de la
	  * última transacció executada o
	  * des de l'últim punt marcat.
	  * 
	  * @throws STException 
	  */
	 private void rollback() throws STException{
		 try{
			 if(this._dbConnection != null
				 && this.isOpen()){
				 if (this._savepoint != null)
					 this._dbConnection.rollback(this._savepoint);
				 else
					 this._dbConnection.rollback();
			 }
			 this._transactionInProgress = false;
			 this._savepoint = null;
		 } catch (SQLException e){
			 throw new STException(e, TokenKeys.ERROR_DATABASE_ROLLBACK);
		 }
	 }
	 
	 /***
	  * Assigna la propietat autocommit segons el
	  * valor indicat.
	  * 
	  * @param autoCommit valor de la propietat autocommit
	  * que s'assignarà.
	  * @throws SQLException
	  */
	 private void setAutoCommit(boolean autoCommit) throws STException{
		 try{
		 if (this.isOpen()
				 && this._dbConnection.getAutoCommit() != autoCommit)
			 this._dbConnection.setAutoCommit(autoCommit);
		 } catch (SQLException e){
			 throw new STException(e);
			 
		 } catch (STException e){
			 throw e;
		 }
	 }
	 
	 /***
	  * 
	  * Executa una sentència SQL d'extracció
	  * de resultats, retornant un objecte ResultSet
	  * amb la informació trobada.
	  * 
	  * @param sql Cadena amb la sentència SQL
	  * que s'executarà.
	  * 
	  * @return Objecte ResultSet amb el resultat de
	  * la sentència.
	  * @throws STException 
	  */
	 public ResultSet selectData(String sql) throws STException{
		 ResultSet result = null;
		 
		 try{
			if (this.openConnection()){
				Statement statement = null;
				statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																ResultSet.CONCUR_READ_ONLY);
				result = statement.executeQuery(sql);
			} else {
				throw new STException(TokenKeys.ERROR_DATABASE_CONNECTION); 
			}
		 
		 } catch (SQLException e){
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 throw e;
		 
		 } catch (Exception e){
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } finally{
			 this.closeConnection();
		 }
		return result;
	 }
	 
	 /***
	  * Inicia una transacció, emmagatzemant
	  * la situació actual de la base de dades
	  * per a poder fer un rollback si fos
	  * necessari
	  * @throws STException 
	  */
	 public void startTransaction() throws STException{
		 try{
			 if (this._savepoint == null){
				 this.openConnection();
				 this._dbConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				 this.setAutoCommit(false);
				 this._savepoint = this._dbConnection.setSavepoint();
				 this._transactionInProgress = true;
			 }
		 } catch (SQLException e){
			 this._savepoint = null;
			 this._transactionInProgress = false;
			 throw new STException(e, TokenKeys.ERROR_DATABASE_STARTING_TRANSACTION);
		 }
	 }
	 
	 /***
	 * Realitza un test de connexió a la
	 * base de dades i retorna un string
	 * amb el resultat del test
	 * 
	 * @return String amb el resultat del
	 * test.
	 * @throws STException 
	 */
	 public String testConnection() throws STException{
		String result = TokenKeys.DATABASE_TEST_KO;
		try{
			this.openConnection();
			this.closeConnection();
			result = TokenKeys.DATABASE_TEST_OK;
		} catch(Exception e){
			throw new STException(e, TokenKeys.ERROR_DATABASE_CONNECTION);
		}
		return result;
	 }

	 /***
	  * 
	  * Executa una sentència SQL d'actualització
	  * de dades, retornant un integer amb el nombre
	  * de registres afectats per l'actualització.
	  * 
	  * @param sql Cadena amb la sentència SQL
	  * que s'executarà.
	  * 
	  * @return Integer amb el nombre de registres
	  * afectats per l'actualització.
	  * @throws STException 
	  */
	 public int updateData(String sql) throws STException{
		 int result = 0;
		 Statement statement = null;
		 try{
			if (this.openConnection()){
				this.setAutoCommit(false);
				statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																ResultSet.CONCUR_UPDATABLE);
				result = statement.executeUpdate(sql);
				this.commit();
			} else {
				throw new STException(TokenKeys.ERROR_DATABASE_CONNECTION); 
			}
		 
		 } catch (SQLException e){
			 this.rollback();
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 this.rollback();
			 throw e;
		 
		 } catch (Exception e){
			 this.rollback();
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } finally{
			 this.closeConnection();
			 this.closeStatement(statement);
		 }
		return result;
	 }
	 
	 
	 /***
	  * Mètode que retorna una totes les files d'una taula donadales quals tenen una columna
	  * amb el valor passat per paràmetre 
	  * MOLT IMPORTANT: Aquest mètode no tanca ni la connexió ni el Statement que es fa servir.
	  * Un cop s'ha llegit el ResultSet, s'ha de tancar cridant al métode d'aquesta mateixa classe 'closeResultSet'
	  * 
	  * @param table La taula de la qual volem recuperar
	  * @param columnName El nom de la columna per la qual volem filtrar
	  * @param columnValue El valor que ha de tindre la columna
	  * @return Un resulSet.
	  * 
	  * @throws STException
	  */
	 public ResultSet getByColumnValue(String table, String columnName, int columnValue) throws STException{
		 Statement statement = null;
		 ResultSet resultSet = null;
		 
		 try{
			if (this.openConnection()){
				statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																ResultSet.CONCUR_READ_ONLY);
				String sqlSentence = SELECT_ALL +
								     FROM + table + " " +
								     WHERE + columnName +  " = " + columnValue;
				
				resultSet = statement.executeQuery(sqlSentence);
				
				return resultSet;
			} else {
				throw new STException(TokenKeys.ERROR_DATABASE_CONNECTION); 
			}
		 
		 } catch (SQLException e){
			 throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		 
		 } catch (STException e){
			 throw e;
		 
		 } catch (Exception e){
			 throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
			 
		 } 	
	 }
	 
	 public void closeResultSet(ResultSet resultSet) throws STException 
	 {		 
		 try {
			if (resultSet != null) 
			{
				resultSet.getStatement().getConnection().close();
				resultSet.getStatement().close();
				resultSet.close();
			}
		} catch (SQLException e) {		
			 throw new STException(e, TokenKeys.ERROR_DATABASE_CLOSING_STATEMENT);
		}		 	
	 }
}	 