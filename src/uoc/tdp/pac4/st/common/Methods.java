package uoc.tdp.pac4.st.common;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe que conté tots els mètodes d'ús comú
 * a l'aplicació i que no estan inclosos a
 * altres classes especialitzades.
 * 
 * @author Swing Team - 2014
 */
@SuppressWarnings("unused")
public class Methods {
	
	/***
	 * Mètode que posiciona una finestra en
	 * mig de la pantalla.
	 * 
	 * @param container Objecte que es centrarà a la pantalla
	 */
	public static void centerWindow (Container container){
		//Centrem la finestra a la pantalla
		container.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - container.getWidth()/2, 
							(Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - container.getHeight()/2);
	}

	/***
	 * Converteix una data en una cadena amb el format
	 * que utilitza postgreSQL
	 * 
	 * @param date Data a convertir
	 * @return
	 */
	public static String convertToPostgreSQLDateFormat(java.util.Date date){
		return new SimpleDateFormat(Constants.POSTGRESSQL_DATE_FORMAT).format(date);	
	}

	/***
	 * Converteix una data en una cadena amb el format
	 * que utilitza postgreSQL
	 * 
	 * @param date Data a convertir
	 * @return
	 */
	public static String convertToPostgreSQLDateFormat(java.sql.Date date){
		return new SimpleDateFormat(Constants.POSTGRESSQL_DATE_FORMAT).format(date);	
	}
	
	/***
	 * Crear una data a partir
	 * del dia, mes i any indicats.
	 * 
	 * @param day Dia de la data
	 * @param month Mes de la data
	 * @param year Any de la data
	 * @return
	 */
	public static java.sql.Date createSqlDate(int day, int month, int year){
		java.sql.Date result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		java.util.Date temp = calendar.getTime();
		result = new java.sql.Date(temp.getTime());
		return result;
	}
	
	/***
	 * Crear una data de tipus SQL,
	 * a partir del dia, mes i any indicats.
	 * 
	 * @param day Dia de la data
	 * @param month Mes de la data
	 * @param year Any de la data
	 * @return
	 */
	public static java.util.Date createDate(int day, int month, int year){
		java.util.Date result = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		result = calendar.getTime();
		return result;
	}

	/***
	 * Retorna la ruta i nom d'un fitxer per
	 * a guardar una excepció que s'hagi produit.
	 * 
	 * @return String amb la ruta i nom del fitxer
	 * que s'utilitzarà per a guardar la informació
	 * d'una excepció.
	 * 
	 * @throws SecurityException
	 * @throws IOException 
	 */
	public static String getExceptionFile() throws SecurityException, IOException {
		//String currentFolder = System.getProperty(Constants.PROPERTY_USER_DIR);
		String currentFolder = new File(".").getCanonicalPath();
		String fileName = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		
		//Ens assegurem de que la ruta existeix
		File path = new File(currentFolder + File.separator + Constants.EXCEPTION_FILE_PATH);
		if (!path.exists())
			path.mkdir();
		
		return currentFolder + 
				File.separator + 
				Constants.EXCEPTION_FILE_PATH +
				File.separator +
				Constants.EXCEPTION_FILE_PREFIX + 
				fileName + 
				Constants.EXCEPTION_FILE_EXTENSION;
	}
	
	/***
	 * Posa les traduccions als tokens de tots els
	 * elements continguts a un objecte Container
	*/
	public static void setContainerLanguage(Container component) throws Exception{
		if (component != null){
			
			Component[] components = null;
			
			if (component instanceof JMenu){
				components = ((JMenu)component).getPopupMenu().getComponents();
			} else {
				components = component.getComponents(); 
			}
			
		    for (Component c : components)
		    {
		    	if (c instanceof JMenu 
		    			|| c instanceof JMenuItem
		    			|| c instanceof AbstractButton){
			    	if (c.getName() == null)
						c.setName(((AbstractButton) c).getText());
		    	
		        	//Posa el text corresponent
		    		((AbstractButton) c).setText(Managers.i18n.getTranslation(c.getName()));
		    	}

		    	if (c instanceof JLabel){
			    	if (c.getName() == null)
						c.setName(((JLabel) c).getText());
		    	
		        	//Posa el text corresponent
		    		((JLabel) c).setText(Managers.i18n.getTranslation(c.getName()));
		    	}
		    	
		    	if (c instanceof Container){
		        	setContainerLanguage((Container) c);
		        }
		    }
		}
	}

	/***
	 * Posa les traduccions als tokens de tots els
	 * elements continguts a un objecte JDialog
	 * 
	 * @param dialog Objecte al que s'aplicarà la traducció
	 */
	public static void setDialogLanguage(JDialog dialog) throws Exception{
		setContainerLanguage(dialog.getContentPane());
		setContainerLanguage(dialog.getLayeredPane());
		setContainerLanguage(dialog.getRootPane());
		
		if (dialog.getName() == null)
			dialog.setName(dialog.getTitle());
		dialog.setTitle(Managers.i18n.getTranslation(dialog.getName()));
	}
	
	/***
	 * Posa les traduccions als tokens de tots els
	 * elements continguts a un objecte JFrame
	 * 
	 * @param frame Objecte al que s'aplicarà la traducció
	 */
	public static void setFrameLanguage(JFrame frame) throws Exception{
		setContainerLanguage(frame.getContentPane());
		setContainerLanguage(frame.getJMenuBar());
		setContainerLanguage(frame.getLayeredPane());
		setContainerLanguage(frame.getRootPane());
		
		if (frame.getName() == null)
			frame.setName(frame.getTitle());
		frame.setTitle(Managers.i18n.getTranslation(frame.getName()));
	}

	/***
	 * Posa les traduccions als tokens de tots els
	 * elements continguts a un objecte JFrame
	 * 
	 * @param frame Objecte al que s'aplicarà la traducció
	 */
	public static void setFrameLanguage(JInternalFrame frame) throws Exception{
		setContainerLanguage(frame.getContentPane());
		setContainerLanguage(frame.getJMenuBar());
		setContainerLanguage(frame.getLayeredPane());
		setContainerLanguage(frame.getRootPane());
		
		if (frame.getName() == null)
			frame.setName(frame.getTitle());
		frame.setTitle(Managers.i18n.getTranslation(frame.getName()));
	}
	
	/***
	 * Mètode que mostra un formulari modal
	 * amb el text i tipus d'icona indicades.
	 * 
	 * @param message Cadena que es mostrarà
	 * @param type Tipus de missatge a mostrar
	 */
	public static void showMessage (String message, Enums.MessageType type) throws Exception{
		String translatedMessage = Managers.i18n.getTranslation(message);
		MessageWindow dialog = new MessageWindow(translatedMessage, type);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//Centrem la finestra a la pantalla
		centerWindow(dialog);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	/***
	 * Retorna la cadena d'entrada sense
	 * espais per davant i per darrere.
	 * 
	 * @param value cadena a la que es trauran
	 * els espais.
	 * 
	 * @return La mateixa cadena d'entrada però
	 * sense espais al començament i al final.
	 */
	public static String trim(String value){
		String result = value;
		if (value != null){
			result = value.trim();
		}
		return result;
	}
}
