package uoc.tdp.pac4.st.common.managers;

import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Moviment;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class MovimentManager  {
	public static String TIPUS_MOVIMENT_TRANSFERENCIA= "T"; //Transferència: Moviment intern. D’un local a un altre.
	public static String TIPUS_MOVIMENT_VENDA= "V"; // Venda: D’un local cap a un client
	public static String TIPUS_MOVIMENT_COMPRA= "C"; //Compra: D’un proveïdor cal a un local. No s’imprimeix document.
	public static String TIPUS_MOVIMENT_SORTIDA="S"; // Sortida Devolució: D’un taller al proveïdor.
	public static String TIPUS_MOVIMENT_ENTRADA="E"; //Entrada Devolució: D’un client a un taller.
	
	private DatabaseManager	db;
	
	public MovimentManager(DatabaseManager _db) throws STException {
		this.db = _db;
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
	public int Add(Moviment moviment) throws STException 
	{						
		//Afegeix capçalera de l'albarà 
		int movimentId=  AddToDb(db, moviment);		
							
		return movimentId;
	}		
	
	
	private int AddToDb(DatabaseManager db, Moviment moviment) throws STException 
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_COMPLETATSN, moviment.getCompletatSn());
		hashMap.put(Constants.FIELD_DATAORDRE, moviment.getDataOrdre());
		hashMap.put(Constants.FIELD_DATAPREVLLIURAMENT, moviment.getDataPrevLliurament());
		hashMap.put(Constants.FIELD_NUMUNITATSORDRE, moviment.getNumUnitatsOrdre());
		hashMap.put(Constants.FIELD_NUMUNITSORTIDES, moviment.getNumUnitSortides());
		hashMap.put(Constants.FIELD_PRODUCTE_ID, moviment.getProducteId());
		hashMap.put(Constants.FIELD_TIPUSMOVIMENT_ID, moviment.getTipusMovimentId());
		
		return db.insertDataAndReturnId(Constants.TABLE_MOVIMENT, hashMap); 						
	}
}
