package uoc.tdp.pac4.st.test;

import java.util.Date;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.managers.AlbaraManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.LinAlbaraManager;

/***
 * Testejador per classe Albara
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class AlbaraTest {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			//Tests add albara
			int albaraId;
			DatabaseManager db = new DatabaseManager();
			AlbaraManager am = new AlbaraManager(db);
			Albara albara= new Albara();
			
			albara.setCodialbaraextern("Codialbaraextern");
			albara.setComAlbara("ComAlbara");
			albara.setDataAlbara(new Date());
			albara.setDestiId("destiId");
			albara.setOrigenId("origenId");
			albara.setTipusMovimentId("e");			
			
			LinAlbara linAlbara = new LinAlbara();
			linAlbara.setMovimentId(3);
			linAlbara.setUnitats(99);
		
			albara.getLiniesAlbara().add(linAlbara);
			
			db.startTransaction();			
						
			try 
			{				
				albaraId= am.Add(albara);
				
				db.finishTransaction(true);
			}
			catch(STException e) 
			{
				db.finishTransaction(false);
				throw e;
			}		
			
			//Test get albara by Id
			albara = am.GetById(albaraId);
			
			//Test get linAlbara by albaraId
			LinAlbaraManager linAlbaraManager = new LinAlbaraManager(db); 				
			List<LinAlbara> listLinAlbara = linAlbaraManager.GetByAlbaraId(albaraId);
			
			//Test get linAlbara by ID
			linAlbara = linAlbaraManager.GetById(listLinAlbara.get(0).getIdLiniaAlbara());

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
}
