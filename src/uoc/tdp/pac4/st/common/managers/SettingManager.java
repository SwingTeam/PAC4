package uoc.tdp.pac4.st.common.managers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.*;
import uoc.tdp.pac4.st.client.cf.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.client.e.*;
import uoc.tdp.pac4.st.client.m.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe encarregada de llegir i guardar
 * informació en un fitxer de configuració.
 *
 * @author Swing Team - 2014 
 */
@SuppressWarnings("unused")
public class SettingManager {

	private String _configFile = null;
	private static final String _defaultConfigFile = Constants.SETTINGS_FILE_CLIENT;
	
	/***
	 * Constructor
	 */
	public SettingManager(){}
	
	/***
	 * Constructor
	 * 
	 * @param file Ruta i nom del fitxer de configuració
	 */
	public SettingManager(String file){
		this._configFile = file;
	}
	
	/***
	 * Retorna la ruta del fitxer de configuració
	 * de la instància actual.
	 *  
	 * @return String amb la ruta del fitxer de
	 * configuració de la instància actual.
	 */
	public String getConfigFile(){
		return this._configFile == null ? _defaultConfigFile : this._configFile;
	}
	
	/***
	 * Recupera un valor de l'arxiu de configuracions
	 * 
	 * @param key Clau identificativa del valor a recuperar.
	 * @throws IOException 
	 */
	public String getSetting(String key) throws IOException{
		return getSetting(this.getConfigFile(), key);
	}
	
	/***
	 * Recupera un valor de l'arxiu de configuracions
	 * 
	 * @param file Fitxer a on es guarda el valor a recuperar.
	 * @param key Clau identificativa del valor a recuperar.
	 * @throws IOException 
	 */
	public String getSetting(String file, String key) throws IOException{
		String result = null;
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(file));
		result = properties.getProperty(key);
		properties = null;
		return result;
	}
	
	/***
	 * Guarda un valor a l'arxiu de configuracions
	 * 
	 * @param key Clau identificativa del valor a guardar.
	 * @param value Valor que es guardarà
	 * @throws IOException 
	 */
	public void setSetting(String key, Object value) throws IOException{
		setSetting(this.getConfigFile(), key, value);
	}
	
	/***
	 * Guarda un valor a l'arxiu de configuracions
	 * 
	 * @param file Fitxer a on es guardarà el valor.
	 * @param key Clau identificativa del valor a guardar.
	 * @param value Valor que es guardarà.
	 * @throws IOException 
	 */
	public void setSetting(String file, String key, Object value) throws IOException{
		FileOutputStream outputFile = null;
		Properties properties = new Properties();
		properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream(file));
		properties.put(key, value);
		URL url = ClassLoader.getSystemClassLoader().getResource(file);
		outputFile = new FileOutputStream(url.getPath());
		properties.store(outputFile, null);
		properties = null;
		if (outputFile != null)
			outputFile.close();
				
		outputFile = null;
	}
}
