package uoc.tdp.pac4.i18n;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;


/**
 * Provides i18n access to bundle resources
 * @author Roi Neira
 *
 */
public class LanguageProvider {

	private static final String ResourcePath= "i18n.resources/messages";
	private static ResourceBundle resourceBundle;
	private static Locale locale;
	

	/**
	* Sets i18n culture
	* @param localeString Locale culture 
	* 
	*/
	
	public static void setLanguage(String localeString) {
				
		if (localeString != "")
		{
			locale= Locale.getDefault(); 				
		}
		else 
		{
			locale= new Locale(localeString);
		}	
		
		resourceBundle = ResourceBundle.getBundle(ResourcePath, locale);
	}
	
	/**
	* Gets a key from resource files
	* @param key Key in property file 
	* @return Translated resource
	*/	
	public static String getMessage(String key)  {		
		try 
		{
			return resourceBundle.getString(key);
		}
		catch(MissingResourceException e) {
			return key;
		}		
	}
}
