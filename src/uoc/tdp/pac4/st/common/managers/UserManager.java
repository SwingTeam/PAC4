package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
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
	
	 /***
	  * 
	  * Torna un albarà pel seu ID
	  * 
	  * @param UserId Identificador d'albarà
	  * 
	  * @return User: L'albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public Usuari GetById(int UserId) throws STException 
	{							
		Usuari User = null;
				
		//Obtenim User de la BBDD
		ResultSet resultSet= db.getByColumnValue(Constants.TABLE_USUARI, Constants.FIELD_ID_USUARI, UserId);

		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				//Omplim User 
				User = GetFromResultSet(resultSet);
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
		return User;
	}	
	

	private Usuari GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim User 
		Usuari User = new Usuari();
		User.setLogin(resultSet.getString(Constants.FIELD_LOGIN));
		User.setcognoms(resultSet.getString(Constants.FIELD_COGNOMUSUARI));
		// falten camps

		return User;
	}	
	
	private int AddToDb(DatabaseManager db, Usuari User) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		if (!User.getcognoms().isEmpty() && User.getcognoms().compareTo("")!=0)
		    hashMap.put(Constants.FIELD_COGNOMUSUARI, User.getcognoms());
		if (!User.getLogin().isEmpty() && User.getLogin().compareTo("")!=0)
			hashMap.put(Constants.FIELD_LOGIN,User.getLogin());
		if (!User.getAdresa().isEmpty() && User.getAdresa().compareTo("")!=0)
			hashMap.put(Constants.FIELD_ADRECA,User.getAdresa());
		    hashMap.put(Constants.FIELD_CODPOST,User.getCp());
			hashMap.put(Constants.FIELD_CORREUEM,User.getCorreue());
			hashMap.put(Constants.FIELD_DATAALTA,User.getData_alta());
		hashMap.put(Constants.FIELD_IDIOMA_ID,User.getIdIdioma());
		hashMap.put(Constants.FIELD_LOCAL_ID,User.getIdLocal());
		hashMap.put(Constants.FIELD_PROVINCIA_ID,User.getProvince());
		hashMap.put(Constants.FIELD_ID_USUARI,User.getidUsuari());
		hashMap.put(Constants.FIELD_NIF,User.getNIF());
		if (!User.getnom().isEmpty() && User.getnom().compareTo("")!=0)
			hashMap.put(Constants.FIELD_NOMUSUARI,User.getnom());
		if (!User.getPais().isEmpty() && User.getPais().compareTo("")!=0)
			hashMap.put(Constants.FIELD_PAIS,User.getPais());
		if (!User.getPassword().isEmpty() && User.getPassword().compareTo("")!=0)
			hashMap.put(Constants.FIELD_PASSWORD,User.getPassword());
		if (!User.getPoblacio().isEmpty() && User.getPoblacio().compareTo("")!=0)
			hashMap.put(Constants.FIELD_POBLACIO,User.getPoblacio());
		hashMap.put(Constants.FIELD_ROL,User.getRol());
		hashMap.put(Constants.FIELD_TELEFONFIX,User.getTelefon());
		hashMap.put(Constants.FIELD_TELEFONMOBIL,User.getMobil());
		hashMap.put(Constants.FIELD_VIGENTSN,User.getVigentSN());
		return db.insertDataAndReturnId(Constants.TABLE_USUARI, hashMap); 						
	}
	
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
	
}
