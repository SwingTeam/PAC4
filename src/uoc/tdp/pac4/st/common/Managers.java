package uoc.tdp.pac4.st.common;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe que conté les variables que
 * fan referència a tots els gestors
 * d'ús comú.
 * 
 * @author Swing Team - 2014
 *
 */
public class Managers {

	public static ExceptionManager exception = null;
	public static I18nManager i18n = null;
	public static SettingManager settings = null;
}
