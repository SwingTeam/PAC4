package edu.uoc.tdp.pac4.et.common;

/***
 * Aquesta classe conté totes les constants
 * d'ús comú a l'aplicació, a excepció de les
 * constants que fan referència a tokens, que
 * es troben a la classe Tokens.
 * 
 * @author Ester Marsal i Toni Casas - 2014
 *
 */
public class Constants {
	
	//Imatge de fons
	public final static String BACKGROUND_PICTURE = "resources/background.png";

	//Fitxer de configuració de l'accés a la base de dades
	public final static String DB_CONFIGURATION_FILE = "configuration.properties";
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
	
	//Clau per a l'idioma anglès
	public final static String IDIOMA_ANGLES = "en-GB";
	//Clau per a l'idioma castellà
	public final static String IDIOMA_CASTELLA = "es-ES";
	//Clau per a l'idioma català
	public final static String IDIOMA_CATALA = "ca-ES";

	//Nombre màxim de campanyes per taller i any
	public final static int MAX_CAMPAIGN_PER_YEAR = 3;
	//Claus del fitxer de configuració
	public final static String SETTING_LANGUAGE = "Language";
	//Clau de la URL el servidor RMI
	public final static String SETTING_RMI_URL = "RMI_Server_Url";
	//Clau del port de connexió RMI
	public final static String SETTING_RMI_PORT = "RMI_Port";
	//Clau de l'identificador de la connexió RMI
	public final static String SETTING_RMI_NAME = "RMI_Name";
	//Fitxer de configuració
	public final static String SETTINGS_FILE = "settings.properties";
}
