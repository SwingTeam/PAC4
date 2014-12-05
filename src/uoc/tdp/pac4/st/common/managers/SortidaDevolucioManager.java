package uoc.tdp.pac4.st.common.managers;

import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.LinSortidaDevolucio;
import uoc.tdp.pac4.st.common.dto.SortidaDevolucio;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class SortidaDevolucioManager  {
	private DatabaseManager	db;
	
	public SortidaDevolucioManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	 /***
	  * 
	  * Afegeix una devolucio
	  * 
	  * @param devolucio devolucio
	  * 
	  * @return Integer amb l'ID devolucio
	  * @throws STException 
	  */
	public int Add(SortidaDevolucio sortidaDevolucio) throws STException 
	{						
		//Afegeix capçalera de l'albarà 
		int sortidaDevolucioId=  AddToDb(db, sortidaDevolucio);
		
		LinSortidaDevolucioManager linSortidaDevolucioManager= new LinSortidaDevolucioManager(db);  
		
		//Afegeix linies de l'albarà
		for (LinSortidaDevolucio linSortidaDevolucio: sortidaDevolucio.getLiniesSortidaDevolucio())
		{
			linSortidaDevolucio.setSortidaDevolucioId(sortidaDevolucioId);		    
		    linSortidaDevolucioManager.Add(linSortidaDevolucio);
		}
							
		return sortidaDevolucioId;
	}		
	
	
	private int AddToDb(DatabaseManager db, SortidaDevolucio sortidaDevolucio) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_DATASORTDEVOLUCIO, sortidaDevolucio.getDataSortDevolucio());
		hashMap.put(Constants.FIELD_ID_SORTIDADEVOL, sortidaDevolucio.getIdSortidaDevol());
		
		return db.insertDataAndReturnId(Constants.TABLE_SORTIDADEVOLUCIO, hashMap); 						
	}
}
