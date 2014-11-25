package uoc.tdp.pac4.st;

import java.io.IOException;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Class d'inici de l'aplicació per
 * a la part del servidor.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class InitializeServer {
	/**
	 * Posa en marxa el servidor de
	 * l'aplicació
	 */
	public static void main(String[] args) {
		try {

			//Inicialitza els gestors
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
			//la part servidor de l'aplicació
			ServerWindow serverFrame = new ServerWindow();
			serverFrame.setVisible(true);
			
		} catch (Exception e) {
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
		Managers.settings = new SettingManager(Constants.SETTINGS_FILE_SERVER);
		//Incialitza una instància del gestor d'excepcions
		Managers.exception = new ExceptionManager();
	}
}
