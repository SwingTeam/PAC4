package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.Moviment;
import uoc.tdp.pac4.st.common.dto.Producte;

/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinAlbaraManager  {
	private DatabaseManager	db;
	
	public LinAlbaraManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	public int add(LinAlbara linAlbara) throws STException 
	{						
				
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_ALBARA_ID, linAlbara.getAlbaraId());
		hashMap.put(Constants.FIELD_MOVIMENT_ID, linAlbara.getMovimentId() );
		hashMap.put(Constants.FIELD_UNITATS, linAlbara.getUnitats());
		
		return db.insertDataAndReturnId(Constants.TABLE_LINALBARA, hashMap); 						
	}
	

	 /***
	  * 
	  * Torna una linia d'albarà pel seu ID
	  * 
	  * @param albaraId Identificador de linia d'albarà
	  * 
	  * @return LinAlbara: Linia albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public LinAlbara getById(int linAlbaraId) throws STException 
	{							
		LinAlbara linAlbara = null;
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.getByColumnValue(Constants.TABLE_LINALBARA, Constants.FIELD_ID_LINIAALBARA, linAlbaraId);

		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				linAlbara= getFromResultSet(resultSet);		
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
		return linAlbara;
	}	
	
	 /***
	  * 
	  * Torna totes les linies d'un albara
	  * 
	  * @param albaraId Identificador de l'albarà
	  * 
	  * @return LinAlbara: Linia albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public ArrayList<LinAlbara> getByAlbaraId(int albaraId) throws STException 
	{							
		ArrayList<LinAlbara> listLinAlbara = new ArrayList<LinAlbara>();
				
		StringBuilder sql = new StringBuilder("SELECT * FROM linalbara ");
		sql.append("INNER JOIN moviment ON linalbara.moviment_id = moviment.id_moviment ");
		sql.append("INNER JOIN producte ON moviment.producte_id = producte.id_producte ");
		sql.append("WHERE linalbara.albara_id="+ albaraId);
		
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData(sql.toString());

		try 
		{
			MovimentManager movimentManager = new MovimentManager (db);
			ProducteManager producteManager = new ProducteManager (db);
			 
			//Llegim resultat
			while (resultSet.next()) 
			{
				LinAlbara linAlbara= getFromResultSet(resultSet);
				Moviment moviment = movimentManager.getFromResultSet(resultSet);
				Producte producte = producteManager.getFromResultSet(resultSet);
				
				linAlbara.setMoviment(moviment);
				linAlbara.setProducte(producte);
				
				listLinAlbara.add(linAlbara);
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
		return listLinAlbara;
	}	
	
	 /***
	  * 
	  * Torna linies d'albara per demanda actual
	  * 
	  * @throws STException 
	  */	
	public ArrayList<LinAlbara> getByDemandaActual(String localDestiId, String localOrigenId) throws STException 
	{							
		ArrayList<LinAlbara> listLinAlbara = new ArrayList<LinAlbara>();
				
		StringBuilder sql = new StringBuilder("SELECT * FROM linalbara ");
		sql.append("INNER JOIN albara ON albara.id_albara = linalbara.albara_id  ");
		sql.append("INNER JOIN moviment ON linalbara.moviment_id = moviment.id_moviment ");
		sql.append("INNER JOIN producte ON moviment.producte_id = producte.id_producte ");
		sql.append("INNER JOIN local ON local.id_local = albara.origen_id ");		
		sql.append("LEFT JOIN existencies ON existencies.producte_id = producte.id_producte AND existencies.local_id= albara.desti_id  ");
		sql.append("WHERE ");
		sql.append(" moviment.completatsn = false AND  ");
		sql.append(" moviment.tipusmoviment_id='" + MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA + "' AND");
		sql.append(" albara.desti_id='" + localDestiId + "'"); 
		
		if (localOrigenId != null)
		{
			sql.append(" AND albara.origen_id='" + localOrigenId + "'");
		}
		
		sql.append(" ORDER BY albara.dataalbara");
			
		ResultSet resultSet= db.selectData(sql.toString());

		try 
		{
			MovimentManager movimentManager = new MovimentManager (db);
			ProducteManager producteManager = new ProducteManager (db);
			ExistenciesManager existenciesManager = new ExistenciesManager (db);
			LocalManager localManager = new LocalManager (db);
			
			//Llegim resultat
			while (resultSet.next()) 
			{
				LinAlbara linAlbara= getFromResultSet(resultSet);
				Moviment moviment = movimentManager.getFromResultSet(resultSet);
				Producte producte = producteManager.getFromResultSet(resultSet);
				Existencies existencies= existenciesManager.getFromResultSet(resultSet);
				LocalST local= localManager.getFromResultSet(resultSet);
				
				linAlbara.setMoviment(moviment);
				linAlbara.setProducte(producte);
				linAlbara.setExistencies(existencies);
				linAlbara.setLocal(local);
				
				listLinAlbara.add(linAlbara);
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
		return listLinAlbara;
	}	
	
	
	 /***
	  * 
	  * Torna linies d'albara per ruptura d'estoc
	  * 
	  * @throws STException 
	  */	
	public ArrayList<LinAlbara> getByRupturaStock(String localDestiId, String localOrigenId) throws STException 
	{							
		ArrayList<LinAlbara> listLinAlbara = new ArrayList<LinAlbara>();
				
		StringBuilder sql = new StringBuilder("SELECT * FROM linalbara ");
		sql.append("INNER JOIN albara ON albara.id_albara = linalbara.albara_id  ");
		sql.append("INNER JOIN moviment ON linalbara.moviment_id = moviment.id_moviment ");
		sql.append("INNER JOIN producte ON moviment.producte_id = producte.id_producte ");
		sql.append("INNER JOIN local ON local.id_local = albara.origen_id ");		
		sql.append("INNER JOIN existencies ON existencies.producte_id = producte.id_producte AND existencies.local_id= albara.desti_id  ");
		sql.append("WHERE ");
		sql.append(" existencies.estocminim >= existencies.estoc AND  ");
		sql.append(" moviment.tipusmoviment_id='" + MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA + "' AND");
		sql.append(" albara.desti_id='" + localDestiId + "'"); 
		
		if (localOrigenId != null)
		{
			sql.append(" AND albara.origen_id='" + localOrigenId + "'");
		}
		
		sql.append(" ORDER BY albara.dataalbara");
			
		ResultSet resultSet= db.selectData(sql.toString());

		try 
		{
			MovimentManager movimentManager = new MovimentManager (db);
			ProducteManager producteManager = new ProducteManager (db);
			ExistenciesManager existenciesManager = new ExistenciesManager (db);
			LocalManager localManager = new LocalManager (db);
			
			//Llegim resultat
			while (resultSet.next()) 
			{
				LinAlbara linAlbara= getFromResultSet(resultSet);
				Moviment moviment = movimentManager.getFromResultSet(resultSet);
				Producte producte = producteManager.getFromResultSet(resultSet);
				Existencies existencies= existenciesManager.getFromResultSet(resultSet);
				LocalST local= localManager.getFromResultSet(resultSet);
				
				linAlbara.setMoviment(moviment);
				linAlbara.setProducte(producte);
				linAlbara.setExistencies(existencies);
				linAlbara.setLocal(local);
				
				listLinAlbara.add(linAlbara);
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
		return listLinAlbara;
	}	
	
	public LinAlbara getFromResultSet(ResultSet resultSet) throws SQLException 
	{

		LinAlbara linAlbara = new LinAlbara();
		linAlbara.setAlbaraId(resultSet.getInt(Constants.FIELD_ALBARA_ID));
		linAlbara.setIdLiniaAlbara(resultSet.getInt(Constants.FIELD_ID_LINIAALBARA));
		linAlbara.setMovimentId(resultSet.getInt(Constants.FIELD_MOVIMENT_ID));
		linAlbara.setUnitats(resultSet.getInt(Constants.FIELD_UNITATS));
		return linAlbara;
	}	
	
		
	public int updateMoviment(boolean completatSn, LinAlbara linAlbara) throws STException 
	{						
		StringBuilder sql = new StringBuilder("UPDATE moviment SET ");
		sql.append("numunitsortides= numunitsortides + " + linAlbara.getUnitats() + ", ");
		sql.append("completatsn= " + completatSn + " " );
		sql.append("from linalbara ");
		sql.append("where linalbara.moviment_id = moviment.id_moviment and ");
		sql.append("linalbara.id_liniaalbara=" + linAlbara.getIdLiniaAlbara());
		
		return db.updateData(sql.toString()); 						
	}
}
