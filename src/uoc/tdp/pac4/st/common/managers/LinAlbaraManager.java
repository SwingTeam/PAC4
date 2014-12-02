package uoc.tdp.pac4.st.common.managers;

import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.LinAlbara;



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
	
	public int Add(LinAlbara linAlbara) throws STException 
	{						
				
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_ALBARA_ID, linAlbara.getAlbaraId());
		hashMap.put(Constants.FIELD_MOVIMENT_ID, linAlbara.getMovimentId() );
		hashMap.put(Constants.FIELD_UNITATS, linAlbara.getUnitats());
		
		return db.insertDataAndReturnId(Constants.TABLE_LINALBARA, hashMap); 						
	}
}
