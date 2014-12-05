package uoc.tdp.pac4.st.test;

import java.util.List;

import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.SubGrupManager;

/***
 * Testejador per classe Albara
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class SubGrupTest {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			//Tests get grup 
			DatabaseManager db = new DatabaseManager();
			SubGrupManager manager = new SubGrupManager(db);
			
			List<SubGrup> list = manager.GetByGrupId(null);
			list = manager.GetByGrupId(1);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
}
