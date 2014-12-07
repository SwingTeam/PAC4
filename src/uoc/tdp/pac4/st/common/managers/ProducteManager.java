package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.Producte;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class ProducteManager  {
	private DatabaseManager	db;
	
	public ProducteManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els productes per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte> StockSearch(Integer grupId, Integer subGrupId, String producteId, String localId, Integer stockInicial, Integer stockFinal) throws STException 
	{							
		List<Producte> listProducte= new ArrayList<Producte>();
				
		//Obtenim albara de la BBDD
		StringBuilder sb= new StringBuilder();
		
		sb.append("SELECT producte.*, existencies.*, local.* FROM producte ");
		sb.append("INNER JOIN existencies ON existencies.producte_id = producte.id_producte ");
		sb.append("INNER JOIN local ON existencies.local_id = local.id_local ");
		
		if (grupId != null || subGrupId != null || producteId != null || localId != null || stockInicial != null || stockFinal != null)
		{		
			sb.append(" WHERE ");
			
			if (grupId != null)
				sb.append("producte.productegrup_id = " + grupId + " AND ");
			
			if (subGrupId != null)
				sb.append("producte.productesubgrup_id = " + subGrupId + " AND ");

			if (producteId != null)
				sb.append("producte.id_producte= '" + producteId+ "' AND ");
						
			if (localId != null )
				sb.append("local.id_local = '" + localId + "' AND ");
			
			if (stockInicial != null)
				sb.append("existencies.estoc >= " + stockInicial + " AND ");
			
			if (stockFinal != null) 
				sb.append("existencies.estoc <= " + stockFinal + " AND ");
		
			
			
			sb= new StringBuilder(sb.substring(0, sb.length() -4)); //borra ultim AND
		}
		sb.append(" ORDER BY local.nomlocal, producte.nomproducte");
		
		ResultSet resultSet= db.selectData(sb.toString());

		try 
		{		
			ExistenciesManager existenciesManager = new ExistenciesManager (db); 
			LocalManager localManager = new LocalManager (db);
			//Llegim resultat
			while (resultSet.next()) 
			{
				Producte producte= GetFromResultSet(resultSet);
				Existencies existencies = existenciesManager.GetFromResultSet(resultSet);
				LocalST local  = localManager.GetFromResultSet(resultSet); 

				producte.setExistencies(existencies);
				producte.setLocal(local);
				listProducte.add(producte);
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
		return listProducte;
	}	
	 
	 /***
	  * 
	  * Torna tots els productes per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte> Search(String proveidorId, Integer grupId, Integer subGrupId) throws STException 
	{							
		List<Producte> listProducte= new ArrayList<Producte>();
				
		//Obtenim albara de la BBDD
		StringBuilder sb= new StringBuilder();
		sb.append("SELECT producte.* FROM producte ");
		
		if (proveidorId != null || grupId != null || subGrupId != null)
		{
			if (proveidorId != null)
			{
				sb.append("INNER JOIN producteproveidor ON producte.id_producte = producteproveidor.producte_id");
			}
			
			sb.append(" WHERE ");
			
			if (proveidorId != null)
			{
				sb.append(" producteproveidor.proveidor_id= '" + proveidorId + "' AND ");
			}
			
			if (grupId != null)
				sb.append("productegrup_id = " + grupId + " AND ");
			
			if (subGrupId != null)
				sb.append("productesubgrup_id = " + subGrupId + " AND ");
			
			sb= new StringBuilder(sb.substring(0, sb.length() -4)); //borra ultim AND
		}
		sb.append(" ORDER BY nomproducte");
		
		ResultSet resultSet= db.selectData(sb.toString());

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				Producte producte= GetFromResultSet(resultSet);
				listProducte.add(producte);
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
		return listProducte;
	}
	
	private Producte GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim producte
		Producte producte= new Producte();
		
		producte.setIdProducte(resultSet.getString(Constants.FIELD_ID_PRODUCTE));
		producte.setNomProducte(resultSet.getString(Constants.FIELD_NOMPRODUCTE));
		producte.setProducteGrupId(resultSet.getInt(Constants.FIELD_PRODUCTEGRUP_ID));
		producte.setProducteSubGrupId(resultSet.getInt(Constants.FIELD_PRODUCTEGRUP_ID));
		
		return producte;
	}	
}
