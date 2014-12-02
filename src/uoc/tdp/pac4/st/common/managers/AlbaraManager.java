package uoc.tdp.pac4.st.common.managers;

import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
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
