package uoc.tdp.pac4.st.client.m;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.Enums;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.Methods;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.NIF;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;
public class UserManagementWindow extends JFrame{


	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;
	
	
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;
		java.util.Date dataActual = null;
		java.util.Date dataBaixa = null;
		Usuari user = new Usuari();
		private boolean afterFind = false;
		//Fields
		private JTextField txidUsuari;
		private JTextField txPassword;
		private JTextField txLogin;
		private JTextField txNIF;
		private JTextField txName;
		private JTextField txCognoms;
		private JTextField txAddress;
		private JTextField txCity;
		private JTextField txCP;
		private JTextField txTaller;
		private JComboBox<String> cmbTaller = new JComboBox<String>();
	    private JTextField txDataAlta;
		private JTextField txe_mail;
		private JTextField txTel;
		private JTextField txMobil;
		private JTextField txProvince;
		private JComboBox<String> cmbProvince = new JComboBox<String>();
		private JTextField txCountry;
		private JTextField txRol;
		private JComboBox<String> cmbRol = new JComboBox<String>();
		
		/**
		 * Els dos botons per cercar segon IdUsuari o NIF
		 * nom�s s'afegeixen al formulari en cas que l'operaci� a realitzar
		 * sigui qualsevol menys la de dona d'alta.
		 */
		private JButton btnXIdUsuari = new JButton("LABEL_SEARCH");
	    private JButton btnXNIF = new JButton("LABEL_SEARCH");
		
	    private JButton btnCleanFields = new JButton("LABEL_CLEAN");
	    private JButton btnClose = new JButton("LABEL_CLOSE");
	    /**
	     * El botó acció de canviar el seu nom depenen dels següents casos:
	     * Alta --> Crear
	     * Baixa --> Esborrar
	     * Update --> Modificar
	     * Select --> Consultar --> no cal aquest botó, ja tenim el de buscar
	     */
	    private JButton btnAccio = new JButton();

	
	/**
	 * Create the frame.
	 * @author emarsal2
	 * @since desembre 2014
	 * Mètode constructor de la UserManagementWindows, és encarregada de fer el manteniment dels usuaris dins la BBDD
	 * és a dir, les operacions CRUB
     * El paràmetre operation pot pendre els valor C, R, U o D
     * C per donar d'alta un nou Usuari
     * R per consultar les dades d'un Usuari
     * U per modificar les dades d'un Usuari
     * D per esborrar un Usuari

	 * @param operation és un Caràcter que indiqui quina acció és vol duu a terme
	 *  
	 */
	public UserManagementWindow(Usuari client, final String operation) {
		if (client.getRol().compareTo("Administrador")==0)
		{
		this.setName(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	    JLabel lblIdUsuari = new JLabel("TX_IDUSUARI");
	    lblIdUsuari.setBounds(10, 19, 60, 15);
	    getContentPane().add(lblIdUsuari);
	    
	    txidUsuari = new JTextField();
	    txidUsuari.setBounds(75, 15, 70, 19);
	    getContentPane().add(txidUsuari);
	    
		{
			JLabel lblDataAlta = new JLabel("TX_DATA_ALTA");
		    lblDataAlta.setBounds(265, 19, 60, 15);
		    getContentPane().add(lblDataAlta);
		    
		    txDataAlta = new JTextField();
		    txDataAlta.setEditable(false); 
		    txDataAlta.setBounds(325, 15, 90, 19);
		    getContentPane().add(txDataAlta);
		    
		}
		
			JLabel lblRol = new JLabel("TX_ROL");
		    lblRol.setBounds(265, 46, 25, 15);
		    getContentPane().add(lblRol);
		
		{
	    JLabel lblNIF = new JLabel("TX_NIF");
	    lblNIF.setBounds(10, 46, 30, 15);
	    getContentPane().add(lblNIF);
	    
	    txNIF = new JTextField();
	    txNIF.setBounds(75, 42, 70, 19);
	    getContentPane().add(txNIF);
	    }
		{
	    JLabel lblName = new JLabel("TX_NOM");
	    lblName.setBounds(10,82, 32, 15);
	    getContentPane().add(lblName);
	    
	    txName = new JTextField();
	    txName.setBounds(75, 78, 70, 19);
	    getContentPane().add(txName);
		}
		{
	    JLabel lblCognoms = new JLabel("TX_COGNOMS");
	    lblCognoms.setBounds(180,82, 60, 15);
	    getContentPane().add(lblCognoms);
	    
	    txCognoms = new JTextField();
	    txCognoms.setBounds(240,78,175,19);
	    getContentPane().add(txCognoms);
		}
		{
			JLabel lblMail = new JLabel("TX_MAIL");
			lblMail.setBounds(10, 113, 50, 15);
			getContentPane().add(lblMail);
			
			txe_mail = new JTextField();
		    txe_mail.setBounds(75,109,90,19);
		    getContentPane().add(txe_mail);
		}
		{
			JLabel lblTel = new JLabel("TX_TELEFON");
			lblTel.setBounds(10, 140, 50, 15);
			getContentPane().add(lblTel);
			
			txTel = new JTextField();
		    txTel.setBounds(75,136,70,19);
		    getContentPane().add(txTel);
	    
		}
		{
			JLabel lblMob = new JLabel("TX_MOBIL");
	    	lblMob.setBounds(10, 167, 50, 15);
	    	getContentPane().add(lblMob);
	    
			txMobil = new JTextField();
			txMobil.setBounds(75,163,70,19);
	    	getContentPane().add(txMobil);
   		}
		{
			JLabel lblLog = new JLabel("TX_LOGIN");
	    	lblLog.setBounds(10, 194, 50, 15);
	    	getContentPane().add(lblLog);
	    
			txLogin = new JTextField();
			txLogin.setBounds(75,190,70,19);
			getContentPane().add(txLogin);
		}
		
		{
			JLabel lblPw = new JLabel("TX_PASSWORD");
	    	lblPw.setBounds(10, 221, 70, 15);
	    	getContentPane().add(lblPw);
	    
			txPassword = new JTextField();
			txPassword.setBounds(75,217,70,19);
	    	getContentPane().add(txPassword);
		}
		{
		    btnCleanFields.setBounds(345, 220, 85,19);
		    getContentPane().add(btnCleanFields);
		    
		    btnClose.setBounds(345, 240, 85,19);
		    getContentPane().add(btnClose);
		    if (operation != "R")
		    {
		    	 
		    	  btnAccio.setBounds(345,200,85,19);
		    	  if (operation == "U")
		    		  btnAccio.setBounds(340, 200,90,19);
				  getContentPane().add(btnAccio);
		    }
		}
		{
			JLabel lbladr = new JLabel("TX_ADRECA");
			lbladr.setBounds(180, 113, 60, 15);
			getContentPane().add(lbladr);
			//
			txAddress = new JTextField();
			txAddress.setBounds(240,109,175,19);
		    getContentPane().add(txAddress);
		}
		
		{
			JLabel lblCity = new JLabel("TX_POBLACIO");
			lblCity.setBounds(180, 140, 60, 15);
			getContentPane().add(lblCity);
			//
			txCity = new JTextField();
		    txCity.setBounds(240,136,90,19);
		    getContentPane().add(txCity);
		    
			JLabel lblCP = new JLabel("TX_CP");
			lblCP.setBounds(340, 140, 25, 15);
			getContentPane().add(lblCP);
			//
			txCP = new JTextField();
		    txCP.setBounds(360,136,54,19);
		    getContentPane().add(txCP);
		}

			JLabel lblProv = new JLabel("TX_PROVINCIA");
			lblProv.setBounds(180, 167, 60, 15);
			getContentPane().add(lblProv);
			
		{
			JLabel lblPais = new JLabel("TX_PAIS");
			lblPais.setBounds(180, 194, 60, 15);
			getContentPane().add(lblPais);
			//
			txCountry = new JTextField();
		    txCountry.setBounds(240,190,70,19);
		    getContentPane().add(txCountry);
		}
		
			JLabel lblTaller = new JLabel("TX_TALLER");
		    lblTaller.setBounds(180, 221, 60, 15);
		    getContentPane().add(lblTaller);
		
		    
/*  BEGIN Customize window depend on type of action, if it is Create, Update, Read or Delete*/
		    if (operation == "C") // INSERT
		    {
		    	fillinComboBox(cmbTaller);
		    	fillinComboBoxProvince(cmbProvince);
		    	fillinComboBoxRol(cmbRol);
		    	txidUsuari.setText(idUserNew());
				dataActual = new Date();
	  		    SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy");
	            txDataAlta.setText(formatr.format(dataActual));
	    	    txidUsuari.setEditable(false); 
	    		btnAccio.setText("LABEL_CREATE");
	            setTitle("TITLE_USERADD");
		    }
		    if (operation == "U") // UPDATE
		    {
		    	cmbRol.removeAllItems();
		    	btnAccio.setText("LABEL_MODIFICAR");
				setTitle("TITLE_USERUPD");
				cmbRol.addItem(null);
				cmbRol.setSelectedItem(null);
			}
		    if (operation == "C" || operation == "U")// INSERT or UPDATE
			{
				cmbRol.setBounds(300,42, 115, 19);
			    cmbTaller.setBounds(240,217, 100, 19);
			    cmbProvince.setBounds(240,163,90,19);
			    getContentPane().add(cmbRol);
		    	getContentPane().add(cmbTaller);
			    getContentPane().add(cmbProvince);
				cmbProvince.setEditable(true);
			    cmbTaller.setEditable(true);
			    cmbRol.setEditable(true);
			    txName.setEditable(true);
			    txCognoms.setEditable(true);
			    txe_mail.setEditable(true);
			    txTel.setEditable(true);
			    txMobil.setEditable(true);
			    txLogin.setEditable(true);
			    txPassword.setEditable(true);
			    txAddress.setEditable(true);
			    txCity.setEditable(true);
			    txCP.setEditable(true);
			    txCountry.setEditable(true);
			    txNIF.setEditable(true); 
			}
		if (operation == "R") // SELECT
		{
			setTitle("TITLE_USERSEL");
		}
		if (operation == "R" || operation == "U" || operation == "D") // SELECT or UPDATE or DELETE
		{
			btnXIdUsuari.setBounds(145, 15, 80,19);
	    	getContentPane().add(btnXIdUsuari);
	    	btnXNIF.setBounds(145, 42, 80,19);
	    	getContentPane().add(btnXNIF);
	    	txidUsuari.setEditable(true);
		}
		if (operation ==  "R" || operation == "D") // SELECT or DELETE
			{
			txProvince = new JTextField();
	    	getContentPane().add(txProvince);	
	    	txRol = new JTextField();
	    	getContentPane().add(txRol);
	    	txTaller = new JTextField();
	    	getContentPane().add(txTaller);
	    	txRol.setBounds(300,42, 115, 19);
	    	txTaller.setBounds(240,217, 100, 19);
	    	txProvince.setBounds(240,163,90,19);
	    	getContentPane().add(txRol);
	    	getContentPane().add(txTaller);
	    	getContentPane().add(txProvince);
	    		txRol.setEditable(false);
	    		txProvince.setEditable(false);
	    		txTaller.setEditable(false);
		    	txName.setEditable(false);
			    txCognoms.setEditable(false);
			    txe_mail.setEditable(false);
			    txTel.setEditable(false);
			    txMobil.setEditable(false);
			    txLogin.setEditable(false);
			    txPassword.setEditable(false);
			    txAddress.setEditable(false);
			    txCity.setEditable(false);
			    txCP.setEditable(false);
			    txCountry.setEditable(false);
			    txNIF.setEditable(true); 
			}
		if (operation == "D")
		{
				btnAccio.setText("LABEL_ESBORRAR");
				setTitle("TITLE_USERDEL");
		}
// END COSTUMIZE THE WINDOW----------------------------------------------------------------------------
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		

		/* EVENTS - BEGIN *************************************************************************************/

		//BOTO QUE SEGONS LA OPERACIO HA DE PERMETRE INSERT, UPDATE OR DELETE
		btnAccio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				switch (operation)
			    {
			    case "C": 
			    		  insertUser(operation);
			    		  break;
			    case "U": 
			    	      updateUser(operation);
			    		  break;
			    case "D": 
			    	deleteUser(operation);
			    		  break;
			    }
			
			}
		});
		// BOTO QUE PERMET FER UNA CERCA D'UN USUARI PEL CAMP IDUSUARI
	    btnXIdUsuari.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    			searchUser(Constants.FIELD_ID_USUARI,operation);
	    	}
	    });
	    // BOTO QUE PERMET FER UNA CERCA D'UN USUARI PEL CAMP NIF
	    btnXNIF.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			searchUser(Constants.FIELD_NIF,operation);
    	}
	    });
	    // BOTO QUE PERMET NETEJAR ELS CAMPS
	    btnCleanFields.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cleanFields(operation);
	    	}
	    });
	    // BOTO QUE PERMET TANCAR LA FINESTRA
	    btnClose.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();    			
	    	}
	    });
	    	
		/* EVENTS - END *************************************************************************************/
		} else
		{
			try {
				Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.A_ROL) + " " +
						client.getRol() + " " +
						Managers.i18n.getTranslation(TokenKeys.A_PERMIS),
						Enums.MessageType.Info);
			} catch (Exception e) {
				Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
				}
		}
	}


	   /**
	    * @author emarsal2
	    * @since diumenge 7
     * Mètode per deixar tots els camps en blanc
    **/
 public void   cleanFields(String operation){
 	txNIF.setText(null);
 	txName.setText(null);
 	if (operation == "U")
 	{
 		txidUsuari.setText(null);
 		txDataAlta.setText(null);
 	}
 	if (operation == "U" || operation == "C")
 	{
 		cmbRol.removeAllItems();
 		cmbProvince.removeAllItems();
 		cmbTaller.removeAllItems();
 		cmbRol.setSelectedItem(null);
			cmbTaller.setSelectedItem(null);
			cmbProvince.setSelectedItem(null);
 	}
 	if (operation == "C")
 	{
 		fillinComboBox(cmbTaller);
 		fillinComboBoxProvince(cmbProvince);
 		fillinComboBoxRol(cmbRol);
     }
		if (operation == "R" || operation=="D") // SELECT or DELETE
		{
			txDataAlta.setText(null);
			txidUsuari.setText(null);
			txProvince.setText(null);
			txRol.setText(null);
			txTaller.setText(null);
     }
		txCognoms.setText(null);
		txe_mail.setText(null);
		txTel.setText(null);
		txMobil.setText(null);
		txLogin.setText(null);
		txPassword.setText(null);
		txAddress.setText(null);
		txCity.setText(null);
		txCP.setText(null);
		txCountry.setText(null);
		
		if (operation != "C")
		{
			cmbRol.removeAllItems();
		}
		afterFind = false;
}
 /**
  * @author emarsal2
  * @since dissabte 6
  * Mètode per comprovar que les noves dades de l'usuari siguin vàlides, si és el cas retorna 0
  * en cas que no siguin vàlides retorna un numero per indicar on s'ha detectat un possible error
  * 
  * @return enter 0 si tot és correcta, altrament si hi ha alguna dada no vàlida
  */
 public int validateUser(boolean nifOk,String operation)
 {
 	int v=0;
 	boolean repetit= false;
 	NIF nif = new NIF("");
 	nif.setId(user.getNIF());
 	
 	if (!user.getCp().isEmpty() && !user.checkCP(user.getCp()))
 		return 1;
 	if (!user.getCorreue().isEmpty() && !user.checkCorreuE(user.getCorreue()))
 		return 2;
 	if (!user.getMobil().isEmpty() && !user.checkMobil(user.getMobil()))
 		return 3;
 	if (!user.getTelefon().isEmpty() && !user.checkTelefon(user.getTelefon()))
 		return 4;
 	if (nif.getId().isEmpty())
 		return 6;
 	if (!nif.validar())
 		return 5;
 	if (user.getRol().compareTo("no escollit")==0)
 		return 8;
 	if (operation!="U" || nifOk==true) // si no es tracta d'un UPDATE, és a dir, és un INSERT i en aquest cas cal comprovar que el NIF no existeixi ja
 	{									// o bé és un UPDATE i el nou NIF és diferent del nou, llavors cal comprovar que no existeixi ja
 		try {
 			startConnection();
 			repetit = this._clientManager.getRMIInterface().findNIF(nif.getId());
 		}
 		catch (STException e) {
				Managers.exception.showException(e);
			} catch (RemoteException | NullPointerException e) {
				Managers.exception.showException(new STException(e));
			}finally{
				stopConnection();
			}
 		if (repetit) return 7;
 	}
 	return v;
 }
 
 /**
  * @author emarsal2
  * @since dimecres 3
  * Mètode que a partir del nom del local retorna el seu id
  * 
  * @param localName
  * @return el id del local
  */
 
 public String getId_LocalwithName (String localName)
 {
 	String id = null;
 	try {
 		startConnection();
 		id = this._clientManager.getRMIInterface().getId_LocalwithName(localName);
 			
 	}
 	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
 	return id;
 }

 /**
  * @author emarsal2
  * @since diumenge 7
  * Mètode privat per mostrar un avis  respecte un codi d'error numèric sobre dades d'un usuari
  * 
  * @param idUser codi d'error per decidir quin avís cal mostrar
 * @throws Exception 
  */
 private void showAdvise(int idUser) throws Exception
 {
 	String value = null;
 	switch (idUser)
		{
			case 1: value = TokenKeys.AD_CP;
					break;
			case 2: value = TokenKeys.AD_MAIL;
					break;
			case 3: value = TokenKeys.AD_MOBIL;
					break;
			case 4: value = TokenKeys.AD_FIX;
					break;
			case 5: value = TokenKeys.AD_CK_NIF;
					break;
			case 6: value = TokenKeys.AD_NIF;
					break;
			case 7: value = TokenKeys.AD_RP_NIF;
					break;
			case 8: value = TokenKeys.AD_ROL;
					break;
		}
		Methods.showMessage(Managers.i18n.getTranslation(value), Enums.MessageType.Warning);
 }
 /**
  * @author emarsal2
  * @since dimarts 9
  * Mètode per llegir dades dels camps de la Pantalla amb valors nous
  *  
  */
 private void readUser() throws Exception
 {
 	// reading no Mandatory fields
 	if (txLogin.getText().length() > 20)
 		{
 		Methods.showMessage(Constants.FIELD_LOGIN+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txLogin.getText().substring(0,19)+".",
				Enums.MessageType.Info);
		user.setLogin(txLogin.getText().substring(0, 19));
 		}
 	else
 		{
 		user.setLogin(txLogin.getText());
 		}
 	if (txName.getText().length() > 20)
			{
 		Methods.showMessage(Constants.FIELD_NOMUSUARI+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txName.getText().substring(0,19)+".",
				Enums.MessageType.Info);
		user.setLogin(txLogin.getText().substring(0, 19));
 			}
 	else
			{
		user.setnom(txName.getText());
			}
 	if (txCountry.getText().length() > 20)
		{
 		Methods.showMessage(Constants.FIELD_PAIS+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txCountry.getText().substring(0,19)+".",
				Enums.MessageType.Info);
		user.setPais(txCountry.getText().substring(0, 19));
		}
 	else
		{
 		user.setPais(txCountry.getText());
		}
 	if (txPassword.getText().length() > 20)
		{
 		Methods.showMessage(Constants.FIELD_PASSWORD+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txPassword.getText().substring(0,19)+".",
				Enums.MessageType.Info);
 		user.setPassword(txPassword.getText().substring(0, 19));
		}
 	else
		{
 		user.setPassword(txPassword.getText());
		}
 	if (txCity.getText().length() > 20)
		{
 		Methods.showMessage(Constants.FIELD_POBLACIO+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txCity.getText().substring(0,19)+".",
				Enums.MessageType.Info);
 		user.setPoblacio(txCity.getText().substring(0, 19));
		}
 	else
		{
 		user.setPoblacio(txCity.getText());
		}
 	if (txAddress.getText().length() > 20)
		{
 		Methods.showMessage(Constants.FIELD_ADRECA+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txAddress.getText().substring(0,19)+".",
				Enums.MessageType.Info);
 		user.setAdresa(txAddress.getText().substring(0, 19));
		}
 	else
		{
 		user.setAdresa(txAddress.getText());
		}
 	if (txCognoms.getText().length() > 30)
		{
 		Methods.showMessage(Constants.FIELD_COGNOMUSUARI+" "+Managers.i18n.getTranslation(TokenKeys.AD_TOO_LONG) +
				"\n"+txCognoms.getText().substring(0,29)+".",
				Enums.MessageType.Info);
		user.setcognoms(txCognoms.getText().substring(0, 29));
		}
 	else
		{
 		user.setcognoms(txCognoms.getText());
		}
 	
 	// reading fields from comboBox
 	if (cmbTaller.getSelectedItem() != null)
 		user.setIdLocal(getId_LocalwithName(cmbTaller.getSelectedItem().toString()));
 	else
 		user.setIdLocal(null);
 	if (cmbProvince.getSelectedItem() != null)
 		user.setProvince(cmbProvince.getSelectedItem().toString());
 	else
 		user.setProvince(null);
 	if (cmbRol.getSelectedItem() != null)
 		user.setRol(cmbRol.getSelectedItem().toString());
 	else
 		user.setRol("no escollit");
 	// reading mandatory fields
 	user.setMobil(txMobil.getText());
 	user.setNIF(txNIF.getText().toUpperCase());
 	user.setTelefon(txTel.getText());
 	user.setCorreue(txe_mail.getText());
 	user.setCp(txCP.getText());
  }
 
 /**
  * @author emarsal2
  * @since divendres 5
  * Mètode per modificar les dades d' un Usuari a la BD
  * Inclou la validació de les dades
  * 
  */
 public void updateUser(String operation)
 {
 	String value = null;
 	int idUser = 0;
 	String oldNIF,newNIF;
 	boolean nifOk = false;
 	if (afterFind) { // si realment s'ha fet searchUser amb resultats satisfactoris
 		oldNIF = user.getNIF();
 		try {
			readUser();
		} catch (Exception e1) {
			Managers.exception.showException(new STException(e1, TokenKeys.ERROR_UNEXPECTED));
		}
 		newNIF = user.getNIF();
 		if (oldNIF.compareTo(newNIF)!=0) 
 			nifOk=true; // si els dni són diferents
 			idUser = validateUser(nifOk,operation);
 			if (idUser==0) // dades noves valides
 			{
 				try {
 					startConnection();
 					value = this._clientManager.getRMIInterface().updateUser(user);
 					}
 				catch (STException e) {
 					Managers.exception.showException(e);
 				} catch (RemoteException | NullPointerException e) {
 					Managers.exception.showException(new STException(e));
 				}finally{
 					stopConnection();
 				}
 				try {
					Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.I_USER) + " "+
							value + " "+
							Managers.i18n.getTranslation(TokenKeys.I_MODIFICAR),
							Enums.MessageType.Info);
				} catch (Exception e) {
					Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
				}
 				cleanFields(operation);
 			} else // dades noves no valides, però potser si les modifica poden ser vàlides per aixó el afterFind el continuem deixant a true
 			{
 				try {
					showAdvise(idUser);
				} catch (Exception e) {
					Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
				}
 			}
 	}else { 
 		try {
			Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.AD_MODIFICAR),
					Enums.MessageType.Error);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
		}
 		afterFind=false; // reiniciem el valor
 	} 
 	
 }

 /**
  * @author emarsal2
  * @since divendres 5
  * Mètode per inserir un Usuari a la BD
  * Inclou la validació de les dades
  * 
  */
 
 public void insertUser(String operation)
 {
 	int idUser = 0;
 	try {
		readUser();
	} catch (Exception e1) {
		Managers.exception.showException(new STException(e1, TokenKeys.ERROR_UNEXPECTED));
	}
 	// reading fields only necessary when INSERT
 	user.setData_alta(dataActual);
 	user.setData_baixa(dataBaixa);
 	user.setIdIdioma("1");
 	user.setidUsuari(txidUsuari.getText());
 	user.setVigentSN(true);
 	idUser = validateUser(true,operation);
     if (idUser==0)
 	{
 	try {
 		startConnection();
 		idUser = this._clientManager.getRMIInterface().addUser(user);	
 	}
 	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}

	try {
		Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.I_USER) + " "+
				idUser + " " +
				Managers.i18n.getTranslation(TokenKeys.I_ALTA),
				Enums.MessageType.Info);
	} catch (Exception e) {
		Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
	}
		cleanFields(operation);
 	txidUsuari.setText(idUserNew());
 	} else
 	{
 		try {
			showAdvise(idUser);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
		}
 	}
 	
 }
 
 /**
  * @author emarsal2
  * @since dilluns 8
  * Mètode per aconseguir el següent id per l'usuari que es vol donar d'alta
  * 
  * @return String amb l'id possible pel següent usuari
  */
 public String idUserNew()
 {
 	String value = null;
 	try {
 		startConnection();
 		value = this._clientManager.getRMIInterface().lastIdUser();
 	}
 	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
 	return value;
 }
    /**
     * @author emarsal2
     * @since dimecres 10
     * Mètode per omplir un comboBox amb els valors possibles pel camps ROL
     * @param cmb
     */
    
    private void fillinComboBoxRol(JComboBox<String> cmb) {
    	cmb.addItem("Administrador"); 
    	cmb.addItem("Operador Taller");
    	cmb.addItem("Operador Oficina"); 
    	cmb.addItem(null);
    	cmbRol.setSelectedIndex(3);
    }
	
	/***
	 * Omple un ComboBox amb la llista de locals.
	 * 
	 * @param comboBox Objecte que serà omplert.
	 */
	private void fillinComboBoxProvince(JComboBox<String> cmb){
		try {
			
			startConnection();
			List<String> prov = this._clientManager.getRMIInterface().getProvinceList();
			
			if (prov != null){
				cmb.addItem(null);
				for (String item : prov)
					cmb.addItem(item);
				cmb.setSelectedIndex(0);
			}
		} catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		
		}finally{
			stopConnection();
		}
	}
	/***
	 * @author emarsal2
	 * @since dimecres 3
	 * Omple un ComboBox amb la llista de locals.
	 * 
	 * @param comboBox Objecte que serà omplert.
	 */
	
	private void fillinComboBox(JComboBox<String> cmbTaller){
		try {
			startConnection();
			List<Local> locals = this._clientManager.getRMIInterface().getEstablishmentList(null);
			if (locals != null){
				cmbTaller.addItem(null); // per deixar el camp buit
				for (Local item : locals){
					cmbTaller.addItem(item.getName());
					cmbTaller.setSelectedIndex(0);
			    }
			 }
		  }
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
	}

	/**
	 * @author emarsal2
	 * @since dilluns 8
	 * Mètode que serveix per cercar un usuari a partir del seu identificador proporcionat al camp de text de la finestra.
	 */
	private void searchUser (String nameColumn, String operation)
	{
		String column;
		if (nameColumn.equals(Constants.FIELD_NIF)) // establish with which column do the research
		{
			column = txNIF.getText();
			column = column.toUpperCase();
		} else
		{
			column = txidUsuari.getText(); 
		}
		if (column==null || column.isEmpty() )
		{
			cleanFields(operation);	
			try {
				Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.AD_INDICAR) +
						nameColumn.toUpperCase() +
						Managers.i18n.getTranslation(TokenKeys.AD_CERCAR),
						Enums.MessageType.Info);
			} catch (Exception e) {
				Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
			}
		} else {
		try {
    		startConnection();
    		user = _clientManager.getRMIInterface().userQuery(column,nameColumn);
    		}
    	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
		// ja tenim a dins user l'usuari que cercavem
		if (user.getNIF().compareTo("000000000")!=0) // user found
		{
			cleanFields(operation);
			if (nameColumn.equals(Constants.FIELD_NIF))
			{
				txNIF.setText(column);
			}else
			{
				txidUsuari.setText(column);
			}
			if (operation == "U")
			{
				fillinComboBox(cmbTaller);
				fillinComboBoxProvince(cmbProvince);
				fillinComboBoxRol(cmbRol);
			}
			fillFields(operation);
			afterFind = true;
		} else {// the user wasn't found
			cleanFields(operation); 
			if (nameColumn.equals(Constants.FIELD_NIF))
			{
				txNIF.setText(column);
			} else
			{
				txidUsuari.setText(column); 
			}
			try {
				Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.AD_USER)
						,					Enums.MessageType.Warning);
			} catch (Exception e) {
				Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
			}
		}
		}
	}
	
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * 
	 * Mètode que donat un usuari com a paràmetre posa els seus atributs dins els camps de text de la finestra actual
	 * @param user
	 */
	private void fillFields(String operation)
	{
			txidUsuari.setText(user.getidUsuari());
			if (user.getLogin()!=null)
				txLogin.setText(user.getLogin());
			txTel.setText(user.getTelefon());
			txNIF.setText(user.getNIF());
			Date tmpData = user.getData_alta();
			SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy");
	        txDataAlta.setText(formatr.format(tmpData));
	        txe_mail.setText(user.getCorreue());
	        txMobil.setText(user.getMobil());
	    	txCP.setText(user.getCp());
	 		if (user.getPoblacio()!=null)
	 		    txCity.setText(user.getPoblacio());
	 		if (user.getPassword()!=null) 
				txPassword.setText(user.getPassword());
	 		if (user.getPais()!=null) 
				txCountry.setText(user.getPais());
	 		if (user.getnom()!=null)
	 		    txName.setText(user.getnom());
	 		txCognoms.setText(user.getcognoms());
	 		if (user.getAdresa()!=null)
				txAddress.setText(user.getAdresa());
	 		if (operation == "D" || operation == "R") // DELETE or SELECT
	 		{
	 				txTaller.setText((user.getIdLocal()));
	 				txRol.setText(user.getRol());
	 				txProvince.setText(user.getProvince());
	 		}
			if (operation == "U")
			{
				cmbProvince.setSelectedItem(user.getProvince());
				cmbRol.setSelectedItem(user.getRol());
				cmbTaller.setSelectedItem(user.getIdLocal());
			}
		}
	
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * Mètode per donar de baixa un usuari de la BB
	 */
	private void deleteUser(String operation)
	{
		String value = user.getidUsuari();
		if (afterFind) // si realment s'ha cercat abans un usuari i la cerca ha anat bé
			{
				try {
					startConnection();
					_clientManager.getRMIInterface().deleteUser(value);
				}
				catch (STException e) {
					Managers.exception.showException(e);
				} catch (RemoteException | NullPointerException e) {
					Managers.exception.showException(new STException(e));
				}finally{
					stopConnection();
				}
				try {
					Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.I_USER) +
							" " + value + " "+ 
							Managers.i18n.getTranslation(TokenKeys.I_BAIXA),
							Enums.MessageType.Info);
				} catch (Exception e) {
					Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
				}
			
				cleanFields(operation);
			} else {
				try {
					Methods.showMessage(Managers.i18n.getTranslation(TokenKeys.I_SEL_USER) ,
							Enums.MessageType.Warning);
				} catch (Exception e) {
					Managers.exception.showException(new STException(e, TokenKeys.ERROR_UNEXPECTED));
				}
			} // end else NIF = empty or not found
		afterFind=false; // reiniciem el valor
	 } // end deleteUser method
	
	
	/***
	 * Métode que encarregat de fer la connexió
	 * RMI amb el servidor remot
	 */
	private void startConnection(){
		try{
			//Només carregarem les dades configurades la
			//primera vegada que es posi faci la connexió
			if (this._clientManager == null){
				try{
					String rmiUrl = Managers.settings.getSetting(Constants.SETTING_RMI_URL);
					int rmiPort = Integer.parseInt(Managers.settings.getSetting(Constants.SETTING_RMI_PORT));
					String rmiName = Managers.settings.getSetting(Constants.SETTING_RMI_NAME);
					this._clientManager = new ClientManager<ETallerStocksInterface>(rmiUrl, rmiPort, rmiName);
	
				} catch (IOException | NullPointerException e){
					Managers.exception.showException(new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE));
					
				}
			}
			if (this._clientManager != null)
				this._clientManager.startConnection();
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_NOT_BOUND_EXCEPTION));
			
		}
	}
	
	/***
	 * Métode que encarregat de tancar la connexió
	 * RMI amb el servidor remot
	 */
	private void stopConnection(){
		try{
			if (this._clientManager != null){
				this._clientManager.stopConnection();
				this._clientManager = null;
			}
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		
		}
	}
	
}
