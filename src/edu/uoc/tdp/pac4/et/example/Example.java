package edu.uoc.tdp.pac4.et.example;

import java.io.IOException;

import edu.uoc.tdp.pac4.et.common.*;

public class Example {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try{
			//Inicialitza una instància de I18N que
			//s'utilitzarà a tota l'aplicació
			I18N i18n = new I18N(Constants.IDIOMA_CATALA);
			//Llegeix les configuracions de l'aplicació
			String language = Constants.IDIOMA_CATALA;
			Settings settings = new Settings();
			try{
				language = (String) settings.getSetting(Constants.SETTING_LANGUAGE);
			} catch (IOException | NullPointerException e) {
				System.out.println(i18n.getTranslation(TokenKeys.ERROR_CONFIGURATION_FILE));
			} catch (Exception e){
				System.out.println(i18n.getTranslation(TokenKeys.ERROR_UNEXPECTED));
			}
			settings = null;
			
			
			//Assigna l'idioma configurat
			i18n.setLanguage(language);
			System.out.println(i18n.getTranslation(TokenKeys.LANGUAGE_DATA) + " '" + language + "'");
			System.out.println(i18n.getTranslation(TokenKeys.ABOUT_US));
			i18n = null;

		} catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
