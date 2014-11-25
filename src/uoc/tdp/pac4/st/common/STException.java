package uoc.tdp.pac4.st.common;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;


/***
 * Classe d'ús comú a tota l'aplicació
 * i que representa la classe base d'excepcions.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class STException extends Exception{
	
	private static final long serialVersionUID = -437923893493056843L;
	private String _tokenKey = null; 

	/***
	 * Constructor
	 * 
	 * @param exception Excepció amb la que
	 * es crearà la instància.
	 */
	public STException (Exception exception){
		super(exception);
	}

	/***
	 * Constructor
	 * 
	 * @param tokenKey Clau dels arxius de recursos
	 * corresponent a la descripció personalitzada
	 * de l'excepció.
	 */
	public STException (String tokenKey){
		super(tokenKey);
		this._tokenKey = tokenKey;
	}
	
	/***
	 * Constructor
	 * 
	 * @param exception Excepció amb la que
	 * es crearà la instància.
	 * @param tokenKey Clau dels arxius de recursos
	 * corresponent a la descripció personalitzada
	 * de l'excepció.
	 */
	public STException (Exception exception, String tokenKey){
		super(exception);
		this._tokenKey = tokenKey;
	}
	
	/***
	 * Retorna la descripció de l'excepció.
	 * Si no s'ha indicat un token, retornarà
	 * la descripció de l'excepció original,
	 * i si s'ha indicat un token, retornarà
	 * el text corresponent al token.
	 */
	@Override
	public String getMessage(){
		String message = this._tokenKey == null
				? super.getMessage()
				: this._tokenKey;
		return Managers.i18n.getTranslation(message);
	}
}
