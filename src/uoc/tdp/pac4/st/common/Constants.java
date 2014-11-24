package uoc.tdp.pac4.st.common;

/***
 * Aquesta classe conté totes les constants
 * d'ús comú a l'aplicació, a excepció de les
 * constants que fan referència a tokens, que
 * es troben a la classe Tokens.
 * 
 * @author Swing Team - 2014
 *
 */
public class Constants {
	
	//Imatge de fons
	public final static String BACKGROUND_PICTURE = "resources/background.png";

	//Dades del driver de connexió a la base de dades
	public final static String DB_DRIVER = "driver";
	//Clau de la ruta d'accés a la base de dades amb el driver jdbc
	public final static String DB_URL = "url";
	//Clau de la contrasenya de l'usuari d'accés a la base de dades
	public final static String DB_PASSWORD = "password";
	//Clau de l'esquema de la base de dades
	public final static String DB_SQUEMA = "schema";
	//Clau de l'usuari de la base de dades
	public final static String DB_USERNAME = "username";
	
	//Camps de la base de dades
	public final static String FIELD_TAXID = "cif";
	public final static String FIELD_NAME = "nombre";
	public final static String FIELD_PROVINCE = "provincia";
	public final static String FIELD_PHONE = "telefono";
	public final static String FIELD_LONGITUDE = "longitud";
	public final static String FIELD_LATITUDE = "latitud";
	
	//Clau per a l'idioma català
	public final static String LANGUAGE_CATALAN = "ca-ES";
	//Clau per a l'idioma anglès
	public final static String LANGUAGE_ENGLISH = "en-GB";
	//Inici del nom dels fitxers d'idiomes
	public final static String LANGUAGE_FILE_NAME = "resources";
	//Ruta dels fitxers d'idioma
	public final static String LANGUAGE_PATH = "tokens";
	//Clau per a l'idioma castellà
	public final static String LANGUAGE_SPANISH = "es-ES";

	//Claus del fitxer de configuració
	public final static String SETTING_LANGUAGE = "Language";
	//Clau de la URL el servidor RMI
	public final static String SETTING_RMI_URL = "RMI_Server_Url";
	//Clau del port de connexió RMI
	public final static String SETTING_RMI_PORT = "RMI_Port";
	//Clau de l'identificador de la connexió RMI
	public final static String SETTING_RMI_NAME = "RMI_Name";
	//Fitxer de configuració pel client
	public final static String SETTINGS_FILE_CLIENT = "clientSettings.properties";
	//Fitxer de configuració pel servidor
	public final static String SETTINGS_FILE_SERVER = "serverSettings.properties";
	
	//Tabla local
	public final static String TABLE_LOCAL = "\"Local\"";
	
}
