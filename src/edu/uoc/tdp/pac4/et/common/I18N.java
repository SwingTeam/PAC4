package edu.uoc.tdp.pac4.et.common;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/***
 * Classe encarregada de la gestió de l'idioma
 * de l'aplicació.
 * 
 * @author Ester Marsal i Toni Casas - 2014
 *
 */
public class I18N {
	//Nom d'arxiu d'accés als fitxers de recursos.
	//Per defecte és "resources"
	private final String _baseNameFile = "resources";
	//Ruta d'accés als fitxers de recursos.
	//Per defecte és "tokens"
	private final String _baseNamePath = "tokens";
	//Ruta completa per defecte, d'accès als arxius de recursos.
	private String _baseName = _baseNamePath + File.separator + _baseNameFile;
	//Idioma per defecte
	private String _default = Constants.IDIOMA_CATALA;
	//Idioma seleccionat
	private String _language = null;
	
	//Idiomes suportats per l'aplicació
	private final static List<String> _languageList 
		= Arrays.asList(Constants.IDIOMA_CATALA //Català 
						, Constants.IDIOMA_CASTELLA //Castellà
						, Constants.IDIOMA_ANGLES //Anglès
						);

	/***
	 * Constructor
	 */
	public I18N(){

	}
	
	/***
	 * Constructor
	 * @param Idioma per defecte que utilitzarà la
	 * classe.
	 */
	public I18N(String language){
		this._default = language;
	}

	/***
	 * Constructor
	 * @param language Idioma per defecte que utilitzarà la
	 * classe.
	 * @param baseName Ruta sencera a on estan els fitxers
	 * de recursos.
	 */
	public I18N(String language, String baseName){
		this._default = language;
		this._baseName = baseName;
	}
	
	
	/***
	 * Retorna la ruta sencera d'accés als fitxers
	 * de recursos.
	 * @return Cadena amb la ruta sencera d'accés als
	 * fitxers de recursos.
	 */
	public String getBaseName(){
		return this._baseName;
	}
	
	/***
	 * Retorna l'idioma actual de la classe.
	 */
	public String getLanguage(){
		return (this._language == null || !_languageList.contains(this._language)) 
				? this._default 
				: this._language;
	}

	/***
	 * Retorna una instància de Locale d'acord
	 * amb l'idioma seleccionat
	 * @return
	 */
	private Locale getLocale(){
		Locale result = null;
		String[] items = this.getLanguage().split("-");
		if (items.length == 1)
			result = new Locale.Builder().setLanguage(items[0]).build();
		else if (items.length == 2)
			result = new Locale.Builder().setLanguage(items[0]).setRegion(items[1]).build();
		
		return result;
	}
	
	/***
	 * Recupera la traducció de un token
	 * corresponent a l'idioma actual.
	 * 
	 * @param Token a traduïr.
	 */
	public String getTranslation(String token){
		String result = token;
		if (token != null){
			ResourceBundle resourceBundle = null;
			resourceBundle = ResourceBundle.getBundle(this._baseName, this.getLocale());
			if (resourceBundle.containsKey(token))
				result = resourceBundle.getString(token.toUpperCase());
		}
		return result;
	}

	/***
	 * Assigna l'idioma actual de la classe.
	 */
	public void setLanguage(String value){
		this._language = value;
	}
}
