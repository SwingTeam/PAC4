package uoc.tdp.pac4.st;

import java.io.IOException;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;
import uoc.tdp.pac4.st.client.e.*;

/***
 * Classe d'inici de l'aplicació per
 * a la part del client.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class InitializeClient {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{

			//Inicialitzem els gestors que s'utilitzaran
			//a l'aplicació
			initializeManagers();
			//Llegeix la configuració d'idioma de l'aplicació,
			//que, per defecte, serà català
			String language = Constants.LANGUAGE_CATALAN;
			try{
				language = (String) Managers.settings.getSetting(Constants.SETTING_LANGUAGE);
			
			} catch (IOException | NullPointerException e) {
				//Errors d'accés al fitxer de configuració
				Managers.exception.showException(new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE));
			
			} catch (Exception e){
				//Altres tipus d'error
				Managers.exception.showException(new STException(e));
			}
			//Assigna l'idioma configurat
			Managers.i18n.setLanguage(language);
			//Inicialitza el formulari principal de 
			//la part client de l'aplicació
			//ExampleWindow clientFrame = new ExampleWindow();
//			StockOutRangeSelector clientFrame = new StockOutRangeSelector();
//			RotationRangeSelector clientFrame = new RotationRangeSelector(); 
			SalesRangeSelector clientFrame = new SalesRangeSelector();
			clientFrame.setVisible(true);

		} catch (Exception e){
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_STARTING));
		}
	}
	
	/***
	 * Inicialitza tots els gestors que utilitzarà
	 * l'aplicació
	 */
	private static void initializeManagers(){
		//Inicialitza una instància del gestor
		//d'internacionalització que
		//s'utilitzarà a tota l'aplicació
		Managers.i18n = new I18nManager(Constants.LANGUAGE_CATALAN);
		//Inicialitza una instància del gestor de
		//configuració que s'utilitzarà a tota l'aplicació
		Managers.settings = new SettingManager();
		//Incialitza una instància del gestor d'excepcions
		Managers.exception = new ExceptionManager();
	}
	
}
