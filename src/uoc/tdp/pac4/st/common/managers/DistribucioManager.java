package uoc.tdp.pac4.st.common.managers;

import java.util.ArrayList;
import java.util.Date;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.LinAlbara;




/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class DistribucioManager  {
	
	public static final String DISTRIBUCIO_DEMANDA_ACTUAL= "D"; 
	public static final String DISTRIBUCIO_RUPTURA_ESTOC= "R";
	
	private DatabaseManager	db;
	
	public DistribucioManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
		

	public void Distribuir(String localOrigenId, ArrayList<LinAlbara> listLinAlbara) throws STException 
	{			
	
		try 
		{	
			ArrayList<Albara> listDistribucio= getListAlbaransDistribucio(localOrigenId, listLinAlbara);
			
			//db.startTransaction();
			
			//Actualitza moviments de les linies recepcionades
			MovimentManager movimentManager= new MovimentManager(db);
			movimentManager.update(false, listLinAlbara);
					
			//Rest existencies al local origen de les linies recepcionades
			ExistenciesManager existenciesManager= new ExistenciesManager(db);
			existenciesManager.update(false, localOrigenId,listLinAlbara);
									
			//Crea albarans de sortida (transferencia)
			AlbaraManager albaraManager = new AlbaraManager(db);
			for (Albara albara: listDistribucio)
			{	
				albaraManager.add(albara);				
			}
			
			
			//db.finishTransaction(true);
		}
		catch (STException st) 
		{
			//db.finishTransaction(false);
			throw st;
		}
	}
	

	private ArrayList<Albara> getListAlbaransDistribucio(String localOrigenId, ArrayList<LinAlbara> listLinAlbara)  
	{			
		
		ArrayList<Albara> listDistribucio= new ArrayList<Albara>();
		//Agafa liniees d'albara i les distribueix als locals
		for (LinAlbara linea: listLinAlbara)
		{		
			Albara albara = getAlbaraFromList(linea.getAlbaraId(), listDistribucio);
			
			if (albara == null)
			{
				//Creem nou albara
				albara= new Albara();
				albara.setCodialbaraextern("");
				albara.setComAlbara(String.valueOf(linea.getAlbaraId()));
				albara.setDestiId(linea.getLocal().getIdLocal());
				albara.setDataAlbara(new Date());
				albara.setOrigenId(localOrigenId);
				albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA);
				
				listDistribucio.add(albara);
			}							
			linea.setAlbaraId(albara.getIdAlbara());
			linea.setUnitats(linea.getMoviment().getNumUnitSortides());					
			
			albara.getLiniesAlbara().add(linea);
		}		
		
		return listDistribucio;
	}
	
	
	private Albara getAlbaraFromList(int albaraId, ArrayList<Albara> list) 
	{
		//Agafa liniees d'albara i les distribueix als locals
		for (Albara albara: list)
		{		
			if (albara.getIdAlbara() == albaraId) 
			{
				return albara;			
			}
		}
		return null;
	}

	

}
