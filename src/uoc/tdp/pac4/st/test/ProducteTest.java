package uoc.tdp.pac4.st.test;

import java.util.List;

import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.ProducteManager;

/***
 * Testejador per classe Albara
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class ProducteTest {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			//Tests get grup 
			DatabaseManager db = new DatabaseManager();
			ProducteManager manager = new ProducteManager(db);
			
			List<Producte> list;
			list = manager.search(null, null, null);
			list = manager.search("1", null, null);
			list = manager.search("1", 1, null);
			list = manager.search("1", 1, 1);

		} catch (Exception e){
			e.printStackTrace();
		}
	}

	
}
