package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Proveidor;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class ProveidorManager  {
	private DatabaseManager	db;
	
	public ProveidorManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els proveidors
	  * 
	  * @return LLista de proveidors 
	  * @throws STException 
	  */	
	public List<Proveidor> List() throws STException 
	{							
		List<Proveidor> listProveidor = new ArrayList<Proveidor>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM Proveidor ORDER BY nomproveidor");

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				Proveidor proveidor= GetFromResultSet(resultSet);
				listProveidor.add(proveidor);
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
		return listProveidor;
	}
	
	private Proveidor GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim proveidor
		Proveidor proveidor = new Proveidor();
		
		proveidor.setIdProveidor(resultSet.getString(Constants.FIELD_ID_PROVEIDOR));
		proveidor.setIdsProveidor(resultSet.getInt(Constants.FIELD_IDS_PROVEIDOR));
		proveidor.setNomProveidor(resultSet.getString(Constants.FIELD_NOMPROVEIDOR));
		return proveidor;
	}	
}
