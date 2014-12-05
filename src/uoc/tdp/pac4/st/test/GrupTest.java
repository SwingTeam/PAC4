package uoc.tdp.pac4.st.test;

import java.util.List;

import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.GrupManager;

/***
 * Testejador per classe Albara
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class GrupTest {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			//Tests get grup 
			DatabaseManager db = new DatabaseManager();
			GrupManager manager = new GrupManager(db);
			
			List<Grup> list = manager.List();

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
}
