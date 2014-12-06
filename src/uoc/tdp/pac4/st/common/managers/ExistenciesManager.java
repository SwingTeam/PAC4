package uoc.tdp.pac4.st.common.managers;

import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.Grup;




/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class ExistenciesManager  {
	private DatabaseManager	db;
	
	public ExistenciesManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	public void AddOrUpdate(Existencies existencies) throws STException 
	{	
		//comprovem que no existeixin existencies per aquest producte i local
		Existencies currentExistencies= getByProducteAndLocal(existencies.getProducteId(), existencies.getLocalId());
		
		if (currentExistencies != null) 
		{
			UpdateStockToDb(existencies);
		}
		else 
		{
			AddToDb(existencies);
		}		
	}
	
	 /***
	  * 
	  * Torna existencia per producte i local
	  * 
	  * @return Existencia
	  * @throws STException 
	  */	
	public Existencies getByProducteAndLocal(String producteId, String localId) throws STException
	{							
		Existencies existencies= null ;
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM existencies WHERE producte_id='" + producteId + "' AND local_id='" + localId + "'");

		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				existencies = GetFromResultSet(resultSet);
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
		return existencies;
	}
	
	private Existencies GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim Existencies
		Existencies existencies = new Existencies();
		
		existencies.setEstoc(resultSet.getInt(Constants.FIELD_ESTOC));
		existencies.setEstocMinim(resultSet.getInt(Constants.FIELD_ESTOCMINIM));
		existencies.setLocalId(resultSet.getString(Constants.FIELD_LOCAL_ID));
		existencies.setProducteId(resultSet.getString(Constants.FIELD_PRODUCTE_ID));
		
		return existencies;
	}	
	
	private int AddToDb(Existencies existencies) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_ESTOC, existencies.getEstoc());
		hashMap.put(Constants.FIELD_ESTOCMINIM, existencies.getEstocMinim());
		hashMap.put(Constants.FIELD_LOCAL_ID, existencies.getLocalId());
		hashMap.put(Constants.FIELD_PRODUCTE_ID, existencies.getProducteId());
		
		return db.insertDataAndReturnId(Constants.TABLE_EXISTENCIES, hashMap); 						
	}
	
	private void UpdateStockToDb(Existencies existencies) throws STException 
	{
		StringBuilder sql = new StringBuilder("UPDATE existencies SET estoc=estoc");
		if (existencies.getEstoc() >= 0) 
		{
			sql.append("+" + existencies.getEstoc());
		}
		else 
		{
			sql.append("-" + existencies.getEstoc());
		}
		
		
		sql.append(" WHERE producte_id='" +  existencies.getProducteId() + "' AND local_id='" + existencies.getLocalId() + "'");
		
		db.updateData(sql.toString()); 						
	}
}
