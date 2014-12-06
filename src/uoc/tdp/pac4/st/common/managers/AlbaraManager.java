package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;



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
	public int Add(Albara albara) throws STException 
	{						
		//Afegeix capçalera de l'albarà 
		int albaraId=  AddToDb(db, albara);
		
		LinAlbaraManager linAlbaraManager = new LinAlbaraManager(db);  
		
		//Afegeix linies de l'albarà
		for (LinAlbara linAlbara: albara.getLiniesAlbara())
		{
		    linAlbara.setAlbaraId(albaraId);		    
		    linAlbaraManager.Add(linAlbara);
		}
							
		return albaraId;
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
	public Albara GetById(int albaraId) throws STException 
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
				albara = GetFromResultSet(resultSet);
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
	

	private Albara GetFromResultSet(ResultSet resultSet) throws SQLException 
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
	
	private int AddToDb(DatabaseManager db, Albara albara) throws STException 
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
