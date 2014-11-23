package uoc.tdp.pac4.st.common.managers;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe encarregada de realitzar la
 * gestió de totes les excepcions de
 * la aplicació
 * 
 * @author Swing Team - 2014
 */
@SuppressWarnings("unused")
public class ExceptionManager {
	
	/***
	 * Mètode que processa una excepció.
	 * Aquest mètode serà l'encarregat de processar
	 * centralitzadament totes les excepcions no
	 * controlades que es produeixin a l'aplicació.

	 * @param e Intància d'Exception que serà processada.
	 */
	public void showException (STException exception){
		//Mostrarem el missatge d'error per pantalla
		try {
			String message = exception.getMessage();
			if (message == null)
				message = Managers.i18n.getTranslation(TokenKeys.ERROR_UNEXPECTED);
			Methods.showMessage(message, Enums.MessageType.Error);
		} catch (Exception e) {
			//Si es produeix un error en aquest mètode
			//només l'escriurem a la sortida estandard
			e.printStackTrace();
		}
	}
}
