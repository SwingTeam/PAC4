package uoc.tdp.pac4.st.test;

import java.util.ArrayList;
import java.util.Date;

import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.managers.AlbaraManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;

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
			
			DatabaseManager db = new DatabaseManager();
			db.startTransaction();			
						
			try 
			{
				AlbaraManager am = new AlbaraManager(db);
				am.Add(albara);
				
				db.finishTransaction(true);
			}
			catch(STException e) 
			{
				db.finishTransaction(false);
				throw e;
			}		

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
}
