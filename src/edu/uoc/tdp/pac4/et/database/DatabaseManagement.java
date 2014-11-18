package edu.uoc.tdp.pac4.et.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.uoc.tdp.pac4.et.common.*;


/***
 * Classe encarregada de gestionar
 * les comunicacions amb la base
 * de dades
 * 
 * @author Ester Marsal i Toni Casas - 2014
 *
 */
@SuppressWarnings("unused")
public class DatabaseManagement {

	private Connection _dbConnection = null;
	private String _dbDriver = null;
	private String _dbPassword = null;
	private String _dbURL = null;
	private String _dbUserName = null;

	/***
	 * Constructor
	 * 
	 * @param driver Driver d'accés a la base de dades
	 * @param url Url d'accés a la base de dades
	 * @param userName Nom d'usuari a la base de dades
	 * @param password Contrasenya de l'usuari d'accés a la base de dades
	 */
	public DatabaseManagement(String driver, String url, String userName, String password){
		this._dbDriver = driver != null ? driver.trim() : null;
		this._dbURL = url != null ? url.trim() : null;
		this._dbUserName = userName != null ? userName.trim() : null;
		this._dbPassword = password != null ? password.trim() : null;
	}

	/***
	 * Tanca la connexió amb la base de dades
	 * @throws SQLException 
	 */
	public void closeConnection() throws SQLException{
		if (this._dbConnection != null)
				this._dbConnection.close();
	}
	
	/***
	 * Indica si la connexió está oberta
	 * amb la base de dades.
	 * 
	 * @return boolean indicant si está o no
	 * oberta la connexió
	 * @throws SQLException
	 */
	public boolean isOpen() throws SQLException{
		boolean result = false;
		if (this._dbConnection != null){
			result = !this._dbConnection.isClosed();
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
	public boolean openConnection() throws SQLException, ClassNotFoundException{
		boolean result = false; 
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
		}
		return result;
	}
	
	/***
	 * Realitza un test de connexió a la
	 * base de dades i retorna un string
	 * amb el resultat del test
	 * 
	 * @return String amb el resultat del
	 * test.
	 */
	public String testConnection(){
		String result = TokenKeys.DATABASE_TEST_KO;
		try{
			this.openConnection();
			this.closeConnection();
			result = TokenKeys.DATABASE_TEST_OK;
		} catch(Exception e){
			//En aquest cas no fem res
		}
		return result;
	}

	 /***
	  * Mètode que retorna una llista d'un
	  * taller específic o, si s'informa un 0,
	  * de tots els tallers.
	  * 
	  * @param shop Taller específic o 0 si
	  * es vol recuperar tots els tallers. 
	  * @return Una instància de List<Taller> amb
	  * el resultat de la consulta.
	  * @throws SQLException
	  */
	 public List<Taller> getShopList(int shop) throws SQLException{
		 List<Taller> result = new ArrayList<Taller>();
		 Statement statement = null;
		 ResultSet resultSet = null;

		if (this.isOpen()){
			statement = this._dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
															ResultSet.CONCUR_UPDATABLE);
			String sqlSentence = "SELECT * " +
							        "FROM taller ";
			if (shop > 0){
				sqlSentence += "WHERE id_taller = " + shop + " ";
			}
			sqlSentence += "ORDER BY nombre ASC;";
			
			resultSet = statement.executeQuery(sqlSentence);
			while (resultSet.next()) {
				Taller item = new Taller();
				item.setId_taller(resultSet.getInt("id_taller"));
				item.setnombre(resultSet.getString("nombre"));
				item.setdireccion(resultSet.getString("direccion"));
				result.add(item);
			}
		} else {
			throw new SQLException(TokenKeys.ERROR_DATABASE_CONNECTION); 
		}
		return result;
	 }
	 
	 /***
	  * Realitza un rollback de la
	  * última transacció executada.
	  */
	 public void rollback(){
		 try{
			 this._dbConnection.rollback();
		 } catch (SQLException e){
			 //No farem res
		 }
	 }
}	 