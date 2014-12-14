package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Usuari;
/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class UserManager  {
	private DatabaseManager	db;
	
	public UserManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	 /***
	  * 
	  * Afegeix un User 
	  * @param User L'usuari� a afegir
	  * 
	  * @return Integer amb l'ID de l'usuari� creat
	  * @throws STException 
	  */
	public int Add(Usuari User) throws STException 
	{				
		int UserId=  AddToDb(db, User);
		return UserId;
	}		
	
	/**
	 * @author emarsal2
	 * @since dissabte 6
	 * M�tode per posar una usuari que est� dins un resultSet en un objecte de tipus Usuari
	 * @param resultSet
	 * @return User usuari
	 * @throws SQLException
	 */

	private Usuari GetUserFromResultSet(ResultSet resultSet) throws SQLException 
	{
		Usuari User = new Usuari();
		String tmpField;
		User.setidUsuari(resultSet.getString(Constants.FIELD_ID_USUARI));
		User.setLogin(resultSet.getString(Constants.FIELD_LOGIN));
		User.setData_alta(resultSet.getDate(Constants.FIELD_DATAALTA));
		User.setCorreue(resultSet.getString(Constants.FIELD_CORREUEM));
		User.setCp(resultSet.getString(Constants.FIELD_CODPOST));
		User.setIdLocal(resultSet.getString(Constants.FIELD_LOCAL_ID));
		User.setMobil(resultSet.getString(Constants.FIELD_TELEFONMOBIL));
		tmpField = resultSet.getString(6); // get Cognoms
		if (!resultSet.wasNull())
			User.setcognoms(tmpField);
		else
			User.setcognoms(null);
		tmpField = resultSet.getString(Constants.FIELD_NOMUSUARI);
		if (!resultSet.wasNull())
			User.setnom(tmpField);
		else		
			User.setnom(null);
		tmpField = resultSet.getString(Constants.FIELD_PASSWORD);
		if (!resultSet.wasNull())
			User.setPassword(tmpField);
		else
			User.setPassword(null);
		tmpField = resultSet.getString(Constants.FIELD_POBLACIO);
		if (!resultSet.wasNull())
			User.setPoblacio(tmpField);
		else
			User.setPoblacio(null);
		User.setAdresa(resultSet.getString(17)); // get Address
		User.setPais(resultSet.getString(Constants.FIELD_PAIS));
		User.setProvince(resultSet.getString(Constants.FIELD_PROVINCIA_ID));
		User.setRol(resultSet.getString(Constants.FIELD_ROL));
		User.setNIF(resultSet.getString(Constants.FIELD_NIF));
		User.setTelefon(resultSet.getString(Constants.FIELD_TELEFONFIX));
		return User;
	}	

	/**
	 * @author emarsal2
	 * @since divendres 5
	 * M�tode per transformar una cadena que est� emmagatzemat dins un resultSet en un String
	 * @param resultSet
	 * @return String 
	 * @throws SQLException
	 */

	private String GetStringFromResultSet(ResultSet rs) throws SQLException
	{
		String str = rs.getString(Constants.FIELD_ID_PROVINCIA);
		return str;
	}
	/**
	 * @author emarsal2
	 * @since dijous 4
	 * M�tode per afegir un usuari dins la BD
	 * @param db
	 * @param User
	 * @return el id de l'usuari inserit
	 * @throws STException
	 */
	private int AddToDb(DatabaseManager db, Usuari User) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (User.getcognoms() != null)
		    hashMap.put(Constants.FIELD_COGNOMUSUARI, User.getcognoms());
		if (User.getLogin() != null)
			hashMap.put(Constants.FIELD_LOGIN,User.getLogin());
		if (User.getAdresa() != null)
			hashMap.put(Constants.FIELD_ADRECA,User.getAdresa());
			hashMap.put(Constants.FIELD_CODPOST,User.getCp());
			hashMap.put(Constants.FIELD_CORREUEM,User.getCorreue());
			hashMap.put(Constants.FIELD_DATAALTA,User.getData_alta());
		hashMap.put(Constants.FIELD_IDIOMA_ID,User.getIdIdioma());
		if (User.getIdLocal() != null)
			hashMap.put(Constants.FIELD_LOCAL_ID,User.getIdLocal());
		if (User.getProvince() != null)
			hashMap.put(Constants.FIELD_PROVINCIA_ID,User.getProvince());
		hashMap.put(Constants.FIELD_ID_USUARI,User.getidUsuari());
		hashMap.put(Constants.FIELD_NIF,User.getNIF());
		if (User.getnom() != null)
			hashMap.put(Constants.FIELD_NOMUSUARI,User.getnom());
		if (User.getPais() != null)
			hashMap.put(Constants.FIELD_PAIS,User.getPais());
		if (User.getPassword() != null)
			hashMap.put(Constants.FIELD_PASSWORD,User.getPassword());
		if (User.getPoblacio()  != null)
			hashMap.put(Constants.FIELD_POBLACIO,User.getPoblacio());
		if (User.getRol() != null)
			hashMap.put(Constants.FIELD_ROL,User.getRol());
		hashMap.put(Constants.FIELD_TELEFONFIX,User.getTelefon());
		hashMap.put(Constants.FIELD_TELEFONMOBIL,User.getMobil());
		hashMap.put(Constants.FIELD_VIGENTSN,User.getVigentSN());
		return db.insertDataAndReturnId(Constants.TABLE_USUARI, hashMap); 						
	}
	
	
	/**
	 * @author emarsal2
	 * @since dilluns 8
	 * M�tode que serveix per construir la cadena sql a partir de les dades d'un usuari per realitzar un UPDATE
	 * @param user with the fields full of new values
	 * @return String with part of sql statement to do update
	 */
	 private String prepareStringUpdate (Usuari user)
	{
		 String sql = null;
		 sql = "UPDATE "+Constants.TABLE_USUARI+" SET ";
		 sql += Constants.FIELD_NIF+"='"+user.getNIF()+"'";
		 if (user.getPassword() != null)
			 sql += ", "+Constants.FIELD_PASSWORD+"='"+user.getPassword()+"'";
		 else
			 sql += ", "+Constants.FIELD_PASSWORD+"=null";
		 if (user.getLogin() != null)
			sql += ", "+Constants.FIELD_LOGIN+"='"+user.getLogin()+"'";
		 else
			sql += ", "+Constants.FIELD_LOGIN+"=null";
		 if (user.getnom() != null)
		 	sql += ", "+Constants.FIELD_NOMUSUARI+"='"+user.getnom()+"'";
		 else
			 sql += ", "+Constants.FIELD_NOMUSUARI+"=null";
		 if (user.getcognoms() !=null)
		 	sql += ", "+Constants.FIELD_COGNOMUSUARI+"='"+user.getcognoms()+"'";
		 else
			 sql += ", "+Constants.FIELD_COGNOMUSUARI+"=null";
		 
		 if (user.getAdresa() != null)
		 	sql += ", "+Constants.FIELD_ADRECA+"='"+user.getAdresa()+"'";
		 else
			sql += ", "+Constants.FIELD_ADRECA+"=null";
		 if (user.getPoblacio() != null)
		 	sql += ", "+Constants.FIELD_POBLACIO+"='"+user.getPoblacio()+"'";
		 else
			 sql += ", "+Constants.FIELD_POBLACIO+"=null";
		 if (user.getCp() != null)
		 	sql += ", "+Constants.FIELD_CODPOST+"='"+user.getCp()+"'";
		 else
			 sql += ", "+Constants.FIELD_CODPOST+"=null";
		 if (user.getData_alta()!=null)
		 	sql += ", "+Constants.FIELD_DATAALTA+"='"+user.getData_alta()+"'";
		 else
			 sql += ", "+Constants.FIELD_DATAALTA+"=null";
		 if (user.getCorreue()!=null)
		 	sql += ", "+Constants.FIELD_CORREUEM+"='"+user.getCorreue()+"'";
		 else
			 sql += ", "+Constants.FIELD_CORREUEM+"=null";
		 if (user.getTelefon()!=null)
		 	sql += ", "+Constants.FIELD_TELEFONFIX+"='"+user.getTelefon()+"'";
		 else
			sql += ", "+Constants.FIELD_TELEFONFIX+"=null";
		 if (user.getProvince()!=null)
			 sql += ", "+Constants.FIELD_PROVINCIA_ID+"='"+user.getProvince()+"'";
		 else
			 sql += ", "+Constants.FIELD_PROVINCIA_ID+"=null";
		 if (user.getMobil()!=null)
		 	sql += ", "+Constants.FIELD_TELEFONMOBIL+"='"+user.getMobil()+"'";
		 else
			 sql += ", "+Constants.FIELD_TELEFONMOBIL+"=null";
		 if (user.getRol() != null)
			 sql += ", "+Constants.FIELD_ROL+"='"+user.getRol()+"'";
		 else
			 sql += ", "+Constants.FIELD_ROL+"=null";
		 if (user.getPais() != null)
		 	sql += ", "+Constants.FIELD_PAIS+"='"+user.getPais()+"'";
		 else
			 sql += ", "+Constants.FIELD_PAIS+"=null";
		 if (user.getIdLocal() != null)
			 sql += ", "+Constants.FIELD_LOCAL_ID+"='"+user.getIdLocal()+"'";
		 else
			 sql += ", "+Constants.FIELD_LOCAL_ID+"=null";
		 sql += " WHERE id_usuari like '"+user.getidUsuari()+"';";
		 return sql;
	}
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * M�tode que serveix per modificar les dades d'un usuari
	 * @param user
	 * @return String amb l'id de l'usuari modificat
	 * @throws STException 
	 * 
	 */
	public String update(Usuari user) throws STException 
	{
		String sql = prepareStringUpdate(user);
		try {
			db.updateData(sql);
		} catch (STException e) {
			throw e;
		}finally
		{
			db.finishTransaction(true);
		}
		return user.getidUsuari();
	}
	
	
	/**
	 *  @author emarsal2
	 *  @since divendres 5
	 *  M�tode que donat un nom de local retorna el seu ID del local
	 *  @param localName
	 *  @throws STException
	 *  @return String
	 * 
	 */
	public String getId_LocalwithName(String localName) throws STException
	{
		String idLocal = null;
		String sql = "SELECT ID_LOCAL FROM LOCAL WHERE NOMLOCAL LIKE '";
		sql +=localName;
		sql +="';";
		try 
		{		
			Object ob = db.getScalar(sql);
			idLocal = ob.toString();			
		}
		catch(STException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		} 
		return idLocal;
	}
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * M�tode que donat un id d'usuari , el d�na de baixa de la BBDD
	 * @param userId
	 * @throws STException
	 */
	public void delete (String userId) throws STException
	{
		try 
		{
           db.deleteData("DELETE FROM USUARI WHERE ID_USUARI LIKE '"+userId+"';");
		} catch (STException e) {
			throw e;
		}
		finally 
		{
			db.finishTransaction(true);
		}
	}

	/**
	 *  @author emarsal2
	 *  @sinse dimart 9
	 *  Mètode que serveix per calcular quin id li toca al següent usuari que es vol donar d'alta
	 *  selecciona el primer valor numèric lliure, tot i que després es desa com un String
	 *  @return String amb el nou id
	 *  @throws STException
	 */
	public String getNextId () throws STException
	{
		String newId = null;
		int beforeId = 0;
		int actualId = 0;
		int qtat = 0;
		int trobat = 0;
		ResultSet rs= db.selectData("SELECT to_number(id_usuari,'999') from usuari order by 1;");
		try 
		{	
			if (rs.next()) 
			{
				rs.last();
				qtat = rs.getRow(); 
				rs.beforeFirst();
				while (rs.next() && trobat==0 && qtat>0)
				{
					actualId = rs.getInt(1);
					if ((actualId-beforeId)!=1)
					{
						trobat = beforeId+1;
					} else {
						beforeId = actualId;
					}
					qtat--;
				}
				if (qtat==0) trobat=actualId+1;
				newId = Integer.toString(trobat);
			} else
			{
				newId = "1";
			}
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			db.closeResultSet(rs);
		}
		return newId;
	}
	
	/**
	 * @author emarsal2
	 * @since divendres 5
	 * M�tode per aconseguir un llistat amb les provincies de la BD
	 * @return List with the name of province
	 * @throws STException
	 */
	public List<String> getProvinceList() throws STException 
	{							
		List<String> pr = new ArrayList<String>();
		ResultSet rs= db.selectData("SELECT id_provincia FROM provincia");
		try 
		{	
			while (rs.next())
			{
				pr.add(GetStringFromResultSet(rs));
			}
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			db.closeResultSet(rs);
		}
		return pr;
	}	

	/**
	 * @author emarsal2
	 * @since dissabte 6
	 * M�tode que donat un NIF el cerca dins la BD si el troba retorna cert
	 * en cas contrari retorna fals
	 * @param nif
	 * @return
	 * @throws STException
	 */
	public boolean findNIF (String nif) throws STException
	{
		ResultSet rs= db.selectData("SELECT count(*) FROM USUARI where nif like '"+nif+"'");
		boolean trobat =false;
		try 
		{	
			if (rs.next())
			{
				if (rs.getInt(1)>0)
					trobat=true;
			}
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			db.closeResultSet(rs);
		}
		return trobat;
	}
	
	public Usuari searchUser (String id,String field) throws STException
	{
		Usuari user = new Usuari();
		boolean trobat = false;
		String sql = "select id_usuari,password,login,nif,nomusuari,cognomusuari,poblacio,codpost,local_id,dataalta,correuem,telefonfix,telefonmobil,provincia_id,pais,rol,"+Constants.FIELD_ADRECA+" from usuari where "+field+" like '"+id+"';";
		ResultSet rs= db.selectData(sql);
		try 
		{	
			if (rs.next())
			{
				user = this.GetUserFromResultSet(rs);
				trobat = true;
			} else
			{
				user.setNIF("000000000");
			}
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			db.closeResultSet(rs);
		}
		if (trobat)
		{
			sql = "select nomLocal from local where id_local like '"+user.getIdLocal()+"';";
			rs = db.selectData(sql);
			try
			{
				if (rs.next())
					user.setIdLocal(rs.getString(Constants.FIELD_NOMLOCAL));
			} catch (SQLException e)
			{
				throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
			} finally
			{
				db.closeResultSet(rs);
			}
		}	
		return user;
	}
	
	 /***
	  * 
	  * Torna un Usuari pel nom i passowrd
	  * 
	  * @param nom nom de l'usuari
	  * password password de l'usuari
	  * 
	  * @return User: L'albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public Usuari Login(String nom,String password) throws STException 
	{							
		
				
		//Obtenim User de la BBDD
		String sql = ("SELECT * " + 
				     " FROM " + Constants.TABLE_USUARI + 
				     " WHERE " + Constants.FIELD_LOGIN  + " = '" + nom + 
				     "' AND " + Constants.FIELD_PASSWORD   + " = '" + password + "'" ).toString(); 
		ResultSet resultSet = db.selectData(sql);
		Usuari user = new Usuari();
		
		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				user.setnom(resultSet.getString(Constants.FIELD_NOMUSUARI));
				user.setRol(resultSet.getString(Constants.FIELD_ROL));
				user.setcognoms(resultSet.getString(Constants.FIELD_COGNOMUSUARI));
				user.setidUsuari(resultSet.getString(Constants.FIELD_ID_USUARI));
				user.setPassword(resultSet.getString(Constants.FIELD_PASSWORD));
				user.setLogin(resultSet.getString(Constants.FIELD_LOGIN));
				return user;
			}else{
				return null;
			}			
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			//Molt important: Tanquem connexió, statement i resulset.
			db.closeResultSet(resultSet);
		}
		
	}
	
	/***
	  * 
	  * Torna un int pel idusuari i passowrd
	  * 
	  * @param idusuari id de l'usuari
	  * password password de l'usuari
	  * 
	  * @return int: 1-ok i 0 -ko
	  * @throws STException 
	  */	
	
	public int canviPassword(String idusuari,String password) throws STException
	{							
		//Actualitzem password a la BBDD
		String sql = ("UPDATE " + Constants.TABLE_USUARI + 
				     " SET PASSWORD = '" + password + "'" + 
				     " WHERE id_usuari = '" + idusuari + "'").toString(); 
		
		try{
			int result = db.updateData(sql);
			//Llegim resultat
			if (result!=0)  
			{
				return result;
			}else{
				return result;
			}
		}
		finally 
		{
			}
	}
	
}
