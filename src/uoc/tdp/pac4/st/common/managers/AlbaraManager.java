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
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Moviment;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class AlbaraManager  {
	private DatabaseManager	db;
	
	public AlbaraManager(DatabaseManager _db) throws STException {
		this.db= _db;
	} 
	
	 /***
	  * 
	  * Afegeix un albara i les seves linies
	  * 
	  * @param albara L'albarà a afegir
	  * 
	  * @return Integer amb l'ID de l'albarà creat
	  * @throws STException 
	  */
	public int add(Albara albara) throws STException 
	{			
		int albaraId;
		
		//this.db.startTransaction();
		
		try 
		{
			//Afegeix capçalera de l'albarà 
			albaraId=  addToDb(db, albara);
	
			MovimentManager movimentManager = new MovimentManager(db);
			LinAlbaraManager linAlbaraManager = new LinAlbaraManager(db);
			ExistenciesManager existenciesManager = new ExistenciesManager(db);
			
			//Afegeix linies de l'albarà
			for (LinAlbara linAlbara: albara.getLiniesAlbara())
			{
				//Crea liniea de moviment
				Moviment moviment = new Moviment();
				moviment.setCompletatSn(true);
				moviment.setDataOrdre(albara.getDataAlbara());
				moviment.setDataPrevLliurament(albara.getDataAlbara());
				moviment.setNumUnitatsOrdre(linAlbara.getUnitats());
				moviment.setNumUnitSortides(linAlbara.getUnitats());
				moviment.setProducteId(linAlbara.getProducteId());
				moviment.setTipusMovimentId(albara.getTipusMovimentId());
						
				int movimentId= movimentManager.add(moviment);
				
			    linAlbara.setAlbaraId(albaraId);
			    linAlbara.setMovimentId(movimentId);		
			    linAlbaraManager.add(linAlbara);
			    
			    addOrUpdateExistencies(existenciesManager, albara, linAlbara);
			}		
			
			
			//this.db.finishTransaction(true);
		}
		catch(Exception e)
		{
			//this.db.finishTransaction(false);
			throw e;
		}				
		
							
		return albaraId;
	}		

	
	private void addOrUpdateExistencies(ExistenciesManager existenciesManager, Albara albara, LinAlbara linAlbara) throws STException 
	{		
		  //Actualitzem existencies depenent de tipus moviment
		Existencies existencies = new Existencies();
		existencies.setEstoc(linAlbara.getUnitats());
		existencies.setEstocMinim(linAlbara.getUnitats());
		existencies.setProducteId(linAlbara.getProducteId());
	    
		
		switch (albara.getTipusMovimentId()) 
	    {
	    	case MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA:
				existencies.setLocalId(albara.getOrigenId());
				existencies.setEstoc(-1 * linAlbara.getUnitats()); //Restem a l'origen 
	    		break;
	    	case MovimentManager.TIPUS_MOVIMENT_VENDA:
				existencies.setLocalId(albara.getOrigenId());
				existencies.setEstoc(linAlbara.getUnitats());  //Restem a l'origen 
	    		break;
	    	case MovimentManager.TIPUS_MOVIMENT_COMPRA:
				existencies.setLocalId(albara.getDestiId()); //Afegim a desti
				existencies.setEstoc(linAlbara.getUnitats());
	    		break;
	    	case MovimentManager.TIPUS_MOVIMENT_SORTIDA:
				existencies.setLocalId(albara.getOrigenId());
				existencies.setEstoc(-1 * linAlbara.getUnitats());  //Restem a l'origen
	    		break;
	    	case MovimentManager.TIPUS_MOVIMENT_ENTRADA:
	    		existencies.setLocalId(albara.getDestiId()); 
	    		existencies.setEstoc(linAlbara.getUnitats()); //Afegim a desti
	    		break;		    		
	    }
		
		existenciesManager.addOrUpdate(existencies);		
	}
	 /***
	  * 
	  * Torna un albarà pel seu ID
	  * 
	  * @param albaraId Identificador d'albarà
	  * 
	  * @return Albara: L'albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public Albara getById(int albaraId) throws STException 
	{							
		Albara albara = null;
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.getByColumnValue(Constants.TABLE_ALBARA, Constants.FIELD_ID_ALBARA, albaraId);

		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				//Omplim albara 
				albara = getFromResultSet(resultSet);
				
				//Omplim linies albara
				LinAlbaraManager linAlbaraManager= new LinAlbaraManager(db);  
				ArrayList<LinAlbara> liniesAlbara= (ArrayList<LinAlbara>) linAlbaraManager.getByAlbaraId(albaraId);
				albara.setLiniesAlbara(liniesAlbara);
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
		return albara;
	}	
	

	 /***
	  * 
	  * Torna albarans per desti i tipus de moviment
	  * 
	  * 
	  * @return llista d'albarans 
	  * @throws STException 
	  */	
	public List<Albara> listByDestiAndTipusMoviment(String destiId, String tipusMoviment) throws STException 
	{			
	
		List<Albara> list = new ArrayList<Albara>();
		
		StringBuilder sql = new StringBuilder("select * from albara inner join local on albara.desti_id = local.id_local ");					
		if (destiId != null || tipusMoviment != null)
		{
			sql.append("where ");
			
			if (destiId != null) 
				sql.append("albara.desti_Id='" + destiId + "' AND ");	
			if (tipusMoviment != null) 
				sql.append("albara.tipusmoviment_Id='" + tipusMoviment + "' AND ");	
			
			sql= new StringBuilder(sql.substring(0, sql.length() -4)); //borra ultim AND
		}
		
		ResultSet resultSet= db.selectData(sql.toString());
		
		try 
		{				
			//Llegim resultat
			while (resultSet.next()) 
			{
				Albara albara= getFromResultSet(resultSet);											
				list.add(albara);
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
		return list;
	}		
	

	private Albara getFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim albara 
		Albara albara = new Albara();
		albara.setCodialbaraextern(resultSet.getString(Constants.FIELD_CODIALBARAEXTERN));
		albara.setComAlbara(resultSet.getString(Constants.FIELD_CODIALBARAEXTERN));
		albara.setDataAlbara(resultSet.getDate(Constants.FIELD_DATAALBARA));
		albara.setDestiId(resultSet.getString(Constants.FIELD_DESTI_ID));
		albara.setIdAlbara(resultSet.getInt(Constants.FIELD_ID_ALBARA));
		albara.setOrigenId(resultSet.getString(Constants.FIELD_ORIGEN_ID));
		albara.setTipusMovimentId(resultSet.getString(Constants.FIELD_TIPUSMOVIMENT_ID));
		
		return albara;
	}	
	
	private int addToDb(DatabaseManager db, Albara albara) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_CODIALBARAEXTERN, albara.getCodialbaraextern());
		hashMap.put(Constants.FIELD_COMALBARA, albara.getComAlbara());
		hashMap.put(Constants.FIELD_DATAALBARA, albara.getDataAlbara());
		hashMap.put(Constants.FIELD_DESTI_ID, albara.getDestiId());
		hashMap.put(Constants.FIELD_ORIGEN_ID, albara.getOrigenId());
		hashMap.put(Constants.FIELD_TIPUSMOVIMENT_ID, albara.getTipusMovimentId());
		
		return db.insertDataAndReturnId(Constants.TABLE_ALBARA, hashMap); 						
	}
}
