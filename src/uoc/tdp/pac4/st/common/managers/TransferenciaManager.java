package uoc.tdp.pac4.st.common.managers;

import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Transferencia;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class TransferenciaManager  {
	private DatabaseManager	db;
	
	public TransferenciaManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	 /***
	  * 
	  * Afegeix un transferencia i les seves linies
	  * 
	  * @param transferencia L'albarà a afegir
	  * 
	  * @return Integer amb l'ID de l'albarà creat
	  * @throws STException 
	  */
	public int Add(Transferencia transferencia) throws STException 
	{						
		//Afegeix capçalera de l'albarà 
		int transferenciaId=  AddToDb(db, transferencia);
				
		return transferenciaId;
	}		
	

	private int AddToDb(DatabaseManager db, Transferencia transferencia) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_DATATRANSFERENCIA, transferencia.getDataTransferencia());
		hashMap.put(Constants.FIELD_ID_TRANSFERENCIA, transferencia.getIdTransferencia());
		
		return db.insertDataAndReturnId(Constants.TABLE_TRANSFERENCIA, hashMap); 						
	}
}
