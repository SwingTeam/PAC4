package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
		hashMap.put(Constants.FIELD_COGNOMUSUARI, User.getcognoms());
		hashMap.put(Constants.FIELD_LOGIN,User.getLogin());
		// falten camps
		return db.insertDataAndReturnId(Constants.TABLE_USUARI, hashMap); 						
	}
}
