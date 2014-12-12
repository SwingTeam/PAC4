package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Moviment;



/***
 * 
 *  
 * @author Swing Team - 2014
 *
 */
public class MovimentManager  {
	public static final String TIPUS_MOVIMENT_TRANSFERENCIA= "Transferencia"; //Transferència: Moviment intern. D’un local a un altre.
	public static final String TIPUS_MOVIMENT_VENDA= "Venda"; // Venda: D’un local cap a un client
	public static final String TIPUS_MOVIMENT_COMPRA= "Compra"; //Compra: D’un proveïdor cal a un local. No s’imprimeix document.
	public static final String TIPUS_MOVIMENT_SORTIDA="Sortida"; // Sortida Devolució: D’un taller al proveïdor.
	public static final String TIPUS_MOVIMENT_ENTRADA="Entrada"; //Entrada Devolució: D’un client a un taller.
	
	private DatabaseManager	db;
	
	public MovimentManager(DatabaseManager _db) throws STException {
		this.db = _db;
	}
	
	 /***
	  * 
	  * Afegeix un moviment
	  * 
	  * @param moviment Moviment a afegir
	  * 
	  * @return Integer amb l'ID del moviment creat
	  * @throws STException 
	  */
	public int add(Moviment moviment) throws STException 
	{						
		//Afegeix moviment 
		int movimentId=  addToDb(moviment);		
							
		return movimentId;
	}		

	
	 /***
	  * 
	  * Modifica moviment
	  * 
	  * @param list llista de moviments
	  * 
	  * @throws STException 
	  */
	public void update(Moviment moviment) throws STException 
	{					
		moviment.setCompletatSn(moviment.getNumUnitSortides()  >= moviment.getNumUnitatsOrdre() );
		
		updateToDb(moviment);
	}		
	
	
	private void updateToDb(Moviment moviment) throws STException 
	{
		StringBuilder sql= new StringBuilder("UPDATE moviment ");
		sql.append("SET numunitsortides=" + moviment.getNumUnitSortides() + ", ");
		sql.append("completatsn=" + moviment.getCompletatSn() + " ");
		sql.append("WHERE id_moviment=" + moviment.getIdMoviment());
				
		db.updateData(sql.toString()); 	
	}
	
	private int addToDb(Moviment moviment) throws STException 
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
	
	public Moviment getFromResultSet(ResultSet resultSet) throws SQLException 
	{
		Moviment moviment= new Moviment ();
		moviment.setCompletatSn(resultSet.getBoolean(Constants.FIELD_COMPLETATSN));
		moviment.setDataOrdre(resultSet.getDate(Constants.FIELD_DATAORDRE));
		moviment.setDataPrevLliurament(resultSet.getDate(Constants.FIELD_DATAPREVLLIURAMENT));
		moviment.setIdMoviment(resultSet.getInt(Constants.FIELD_ID_MOVIMENT));
		moviment.setNumUnitatsOrdre(resultSet.getInt(Constants.FIELD_NUMUNITATSORDRE));
		moviment.setNumUnitSortides(resultSet.getInt(Constants.FIELD_NUMUNITSORTIDES));
		moviment.setProducteId(resultSet.getString(Constants.FIELD_PRODUCTE_ID));
		moviment.setTipusMovimentId(resultSet.getString(Constants.FIELD_TIPUSMOVIMENT_ID));
		return moviment;
	}	
	
	public void update(boolean completatSn, ArrayList<LinAlbara> listLinAlbara) throws STException 
	{
		LinAlbaraManager libAlbaraManager = new LinAlbaraManager(db); 
		for (LinAlbara linea: listLinAlbara)
		{	
			libAlbaraManager.updateMoviment(completatSn	, linea);
		}
	}
	

}
