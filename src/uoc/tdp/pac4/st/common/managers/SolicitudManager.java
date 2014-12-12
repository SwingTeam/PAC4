package uoc.tdp.pac4.st.common.managers;

import java.util.Date;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;




/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class SolicitudManager  {
	
	
	private DatabaseManager	db;
	
	public SolicitudManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
		

	public int demanarPeces(Albara albara) throws STException 
	{			
	
		try 
		{			
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA);
			
			//db.startTransaction();
			AlbaraManager manager= new AlbaraManager(db);
			return manager.add(albara);
					
	
			//db.finishTransaction(true);
		}
		catch (STException st) 
		{
			//db.finishTransaction(false);
			throw st;
		}
	}
	
	public int recepcionarPeces(Albara albara) throws STException 
	{			
	
		try 
		{			
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_COMPRA);
			
			//db.startTransaction();
			AlbaraManager manager= new AlbaraManager(db);
			return manager.add(albara);
					
	
			//db.finishTransaction(true);
		}
		catch (STException st) 
		{
			//db.finishTransaction(false);
			throw st;
		}
	}
	
	
	
	public void recepcionarPecesTaller(Albara albara) throws STException 
	{			
	
		try 
		{			
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA);
						
			//db.startTransaction();
			
			//Actualitza moviments de les linies recepcionades
			MovimentManager movimentManager= new MovimentManager(db);
			movimentManager.update(true, albara.getLiniesAlbara());
					
			//Afegeix existencies al local desti de les linies recepcionades
			ExistenciesManager existenciesManager= new ExistenciesManager(db);
			 existenciesManager.update(true, albara.getDestiId(), albara.getLiniesAlbara());
		
	
			//db.finishTransaction(true);
		}
		catch (STException st) 
		{
			//db.finishTransaction(false);
			throw st;
		}
	}

	public int retornarPeces(Albara albara) throws STException 
	{			
	
		try 
		{			
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_SORTIDA);
			
			//db.startTransaction();
			AlbaraManager manager= new AlbaraManager(db);
			return manager.add(albara);
					
	
			//db.finishTransaction(true);
		}
		catch (STException st) 
		{
			//db.finishTransaction(false);
			throw st;
		}
	}

	
	
	
}
