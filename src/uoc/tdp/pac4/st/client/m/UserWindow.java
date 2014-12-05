//adre├ºa
package uoc.tdp.pac4.st.client.m;

import java.awt.BorderLayout;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.NIF;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class UserWindow extends JFrame {
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;
    private String operation = "C"; 
    /**
     * L'atribut operation pot pendre els valor C, R, U o D
     * C per donar d'alta un nou Usuari
     * R per consultar les dades d'un Usuari
     * U per modificar les dades d'un Usuari
     * D per esborrar un Usuari
	*/
		java.util.Date dataActual = new Date();
		java.util.Date dataBaixa = null;
		Usuari user = new Usuari();
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
		private JComboBox<String> cmbTaller = null;
	    private JTextField txDataAlta;
		private JTextField txe_mail;
		private JTextField txTel;
		private JTextField txMobil;
		private JTextField txProvince;
		private JTextField txCountry;
		private JComboBox<String> cmbRol = null;
		/**
		 * Els dos botons per cercar segon IdUsuari o NIF
		 * nom�s s'afegeixen al formulari en cas que l'operaci� a realitzar
		 * sigui qualsevol menys la de dona d'alta.
		 */
		private JButton btnXIdUsuari = new JButton("Buscar");
	    private JButton btnXNIF = new JButton("Buscar");
		
	    private JButton btnCleanFields = new JButton("Netejar");
	    private JButton btnClose = new JButton("Tancar");
	    /**
	     * El bot� btnAccio ha de canviar el seu nom depenen dels seg�ents casos:
	     * Alta --> Crear
	     * Baixa --> Esborrar
	     * Update --> Modificar
	     * Select --> Consultar --> no cal aquest bot�, ja tenim el de buscar
	     */
	    private JButton btnAccio = new JButton();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try{

					//Inicialitzem els gestors que s'utilitzaran
					//a l'aplicació
					initializeManagers();
					//Llegeix la configuració d'idioma de l'aplicació,
					//que, per defecte, serà català
					String language = Constants.LANGUAGE_CATALAN;
					try{
						language = (String) Managers.settings.getSetting(Constants.SETTING_LANGUAGE);
					
					} catch (IOException | NullPointerException e) {
						//Errors d'accés al fitxer de configuració
						Managers.exception.showException(new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE));
					
					} catch (Exception e){
						//Altres tipus d'error
						Managers.exception.showException(new STException(e));
					}
					//Assigna l'idioma configurat
					Managers.i18n.setLanguage(language);

				
				
					UserWindow frame = new UserWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		switch (operation)
	    {
	    case "C": btnAccio.setText("Crear");
	              setTitle("Gestio d'Usuaris - Alta");break;
	    case "U": btnAccio.setText("Modificar");
	    		  setTitle("Gestio d'Usuaris - Modificaci�");break;
	    case "R": setTitle("Gestio d'Usuaris - Consulta");break;
	    case "D": btnAccio.setText("Esborrar");
                  setTitle("Gestio d'Usuaris - Baixa");break;
	    }
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Situem la finestra
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    getContentPane().setLayout(null);
		
	    {
	    JLabel lblIdUsuari = new JLabel("Id Usuari: ");
	    lblIdUsuari.setBounds(10, 19, 60, 15);
	    getContentPane().add(lblIdUsuari);
	    
	    txidUsuari = new JTextField();
	    txidUsuari.setEditable(false); 
	    /* no es pot editar perqu� �s un valor que es genera autom�ticament
	     * i no es pot deixar modificar per ning�
	     */
	    
	    txidUsuari.setColumns(10);
	    txidUsuari.setBounds(75, 15, 70, 19);
	    getContentPane().add(txidUsuari);
	    
		{
			JLabel lblDataAlta = new JLabel("Data alta: ");
		    lblDataAlta.setBounds(265, 19, 60, 15);
		    getContentPane().add(lblDataAlta);
		    
		    txDataAlta = new JTextField();
		    txDataAlta.setColumns(10);
		    txDataAlta.setEditable(false); // no es pot editar perqu� �s la DATA ACTUAL
		    txDataAlta.setBounds(325, 15, 90, 19);
		    
	        SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy");
	        txDataAlta.setText(formatr.format(dataActual));
		    getContentPane().add(txDataAlta);
		}
		
		{
			JLabel lblRol = new JLabel("Rol: ");
		    lblRol.setBounds(265, 46, 25, 15);
		    getContentPane().add(lblRol);
		    
		    this.cmbRol = new JComboBox<String>();
		    this.cmbRol.addItem("Administrador"); //0
		    this.cmbRol.addItem("Operador Taller");// 1
		    this.cmbRol.addItem("Operador Oficina"); //2
		    this.cmbRol.setBounds(300,42, 115, 19);
		    cmbRol.setSelectedIndex(1);
		    getContentPane().add(this.cmbRol);
		}
		
		{
	    JLabel lblNIF = new JLabel("NIF: ");
	    lblNIF.setBounds(10, 46, 30, 15);
	    getContentPane().add(lblNIF);
	    
	    txNIF = new JTextField();
	    txNIF.setColumns(10);
	    txNIF.setEditable(true); // el NIF es sempre un camp editable per cercar o b� per inserir o modificar
	    txNIF.setBounds(75, 42, 70, 19);
	    getContentPane().add(txNIF);
	    }
		{
	    JLabel lblName = new JLabel("Nom: ");
	    lblName.setBounds(10,82, 32, 15);
	    getContentPane().add(lblName);
	    
	    txName = new JTextField();
	    txName.setColumns(10);
	    txName.setBounds(75, 78, 70, 19);
	    getContentPane().add(txName);
		}
		{
	    JLabel lblCognoms = new JLabel("Cognoms: ");
	    lblCognoms.setBounds(180,82, 60, 15);
	    getContentPane().add(lblCognoms);
	    
	    txCognoms = new JTextField();
	    txCognoms.setColumns(30);
	    txCognoms.setBounds(240,78,175,19);
	    getContentPane().add(txCognoms);
		}
		{
			JLabel lblMail = new JLabel("e-mail: ");
			lblMail.setBounds(10, 113, 50, 15);
			getContentPane().add(lblMail);
			
			txe_mail = new JTextField();
		    txe_mail.setColumns(20);
		    txe_mail.setBounds(75,109,90,19);
		    getContentPane().add(txe_mail);
		}
		{
			JLabel lblTel = new JLabel("Telèfon: ");
			lblTel.setBounds(10, 140, 50, 15);
			getContentPane().add(lblTel);
			
			txTel = new JTextField();
		    txTel.setColumns(10);
		    txTel.setBounds(75,136,70,19);
		    getContentPane().add(txTel);
	    
		}
		{
			JLabel lblMob = new JLabel("Mòbil: ");
	    	lblMob.setBounds(10, 167, 50, 15);
	    	getContentPane().add(lblMob);
	    
			txMobil = new JTextField();
			txMobil.setColumns(10);
			txMobil.setBounds(75,163,70,19);
	    	getContentPane().add(txMobil);
   		}
		{
			JLabel lblLog = new JLabel("Login: ");
	    	lblLog.setBounds(10, 194, 50, 15);
	    	getContentPane().add(lblLog);
	    
			txLogin = new JTextField();
			txLogin.setColumns(10);
			txLogin.setBounds(75,190,70,19);
			getContentPane().add(txLogin);
		}
		
		{
			JLabel lblPw = new JLabel("Password: ");
	    	lblPw.setBounds(10, 221, 70, 15);
	    	getContentPane().add(lblPw);
	    
			txPassword = new JTextField();
			txPassword.setColumns(10);
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
		    	/**
		    	 * El boto accio no s'ha de mostrar si es vol realitzar una consulta, perque
		    	 * per consultar ja tenim els botons de buscar per NIF i IdUsuari
		    	 */
		    	  btnAccio.setBounds(345,200,85,19);
				  getContentPane().add(btnAccio);
		    }
		}
		{
			JLabel lbladr = new JLabel("Adreça: ");
			lbladr.setBounds(180, 113, 60, 15);
			getContentPane().add(lbladr);
			//
			txAddress = new JTextField();
		    txAddress.setColumns(10);
			txAddress.setBounds(240,109,175,19);
		    getContentPane().add(txAddress);
		}
		
		{
			JLabel lblCity = new JLabel("Població: ");
			lblCity.setBounds(180, 140, 60, 15);
			getContentPane().add(lblCity);
			//
			txCity = new JTextField();
		    txCity.setColumns(10);
		    txCity.setBounds(240,136,90,19);
		    getContentPane().add(txCity);
		    
			JLabel lblCP = new JLabel("CP: ");
			lblCP.setBounds(340, 140, 25, 15);
			getContentPane().add(lblCP);
			//
			txCP = new JTextField();
		    txCP.setColumns(5);
		    txCP.setBounds(360,136,54,19);
		    getContentPane().add(txCP);
		}

		{
			JLabel lblProv = new JLabel("Província: ");
			lblProv.setBounds(180, 167, 60, 15);
			getContentPane().add(lblProv);
			//
			txProvince = new JTextField();
		    txProvince.setColumns(10);
		    txProvince.setBounds(240,163,70,19);
		    getContentPane().add(txProvince);
		}

		{
			JLabel lblPais = new JLabel("País: ");
			lblPais.setBounds(180, 194, 60, 15);
			getContentPane().add(lblPais);
			//
			txCountry = new JTextField();
		    txCountry.setColumns(10);
		    txCountry.setBounds(240,190,70,19);
		    getContentPane().add(txCountry);
		}
		
		{
			JLabel lblTaller = new JLabel("Taller: ");
		    lblTaller.setBounds(180, 221, 60, 15);
		    getContentPane().add(lblTaller);
		    
			this.cmbTaller = new JComboBox<String>();
		    this.cmbTaller.setBounds(240,217, 100, 19);
		}    
		    if (operation =="C" || operation == "U")
		    {
		    	/**
		    	 * Si l'operaci� �s per INSERT o UPDATE alguns camps han de ser editables
		    	 */
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
		    txProvince.setEditable(true);
		    txCountry.setEditable(true);
		    cmbTaller.setEditable(true);
		    cmbRol.setEditable(true);
		    } else
		    {
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
			    txProvince.setEditable(false);
			    txCountry.setEditable(false);
			    cmbTaller.setEditable(false);
			    cmbRol.setEditable(false);	
		    }
		    
		}
		/* EVENTS - BEGIN *************************************************************************************/
	    if (operation == "C")
	    {
	    	/**
	    	 * Si l'operaci� es per fer una alta d'usuari
	    	 * el id, s'ha de generar autom�ticament, per aix� hi ha el m�tode idUserNew
	    	 */
	        txidUsuari.setText(idUserNew());
	    	/**
	    	 * Tamb� cal deixar a l'usuari triar un taller entre els que hi ha a la BD
	    	 * en altre cas, el combo s'omplir� amb el taller que correspongui segons la cerca
	    	 */
	    	fillinComboBox(cmbTaller);
		    getContentPane().add(this.cmbTaller);
	    }else
	    {
	    	/**
	    	 * Si l'operaci� �s qualsevol (menys INSERT)
	    	 * cal deixar cercar l'usuari per IdUsuari
	    	 */
	    btnXIdUsuari.setBounds(145, 15, 80,19);
	    getContentPane().add(btnXIdUsuari);
	    btnXNIF.setBounds(145, 42, 80,19);
	    getContentPane().add(btnXNIF);
	    }

		//BOTO QUE SEGON LA OPERACIO HA DE PERMETRE INSERT, UPDATE, SELECT OR DELETE
		btnAccio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (operation)
			    {
			    case "C": // abans cal validar totes les dades
			    		  insertUser();
			    		  break;
			    case "U": // abans cal validar totes les dades
			    	//    updateUser();
			    		  break;
			    case "D": // deleteUser();
			    		  break;
			    }
			}
		});
		// BOTO QUE PERMET FER UNA CERCA D'UN USUARI PEL CAMP IDUSUARI
	    btnXIdUsuari.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    			//searchUserId();
	    	}
	    });
	    // BOTO QUE PERMET FER UNA CERCA D'UN USUARI PEL CAMP NIF
	    btnXNIF.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			//searchUserNIF();
    	}
	    });
	    // BOTO QUE PERMET NETEJAR ELS CAMPS
	    btnCleanFields.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		cleanFields();
	    	}
	    });
	    // BOTO QUE PERMET TANCAR LA FINESTRA
	    btnClose.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		dispose();    			
	    	}
	    });
	    	
		/* EVENTS - END *************************************************************************************/
		
		
	}

	   /**
     * M�tode per deixar tots els camps en blanc
     */
    /**
     * 
     */
    public void   cleanFields(){
    	txNIF.setText("");
    	//txidUsuari.setText("");
    	txName.setText("");
 		//txDataAlta.setText("");
 		txCognoms.setText("");
 		txe_mail.setText("");
 		txTel.setText("");
 		txMobil.setText("");
 		txLogin.setText("");
 		txPassword.setText("");
 		txAddress.setText("");
 		txCity.setText("");
 		txCP.setText("");
 		txCountry.setText("");
 		txProvince.setText("");
 		if (operation != "C")
 		{
 			cmbRol.removeAllItems();
 		}
 }
    /**
     * Mètode per comprovar que les noves dades de l'usuari siguin vàlides, si és el cas retorna 0
     * en cas que no siguin vàlides retorna un numero per indicar on s'ha detectat un possible error
     * 
     * @param user
     * @return enter 0 si tot és correcta, altrament si hi ha alguna dada no vàlida
     */
    public int validateUser(Usuari user)
    {
    	int v=0;
    	NIF nif = new NIF("");
    	nif.setId(user.getNIF());
    	
    	if (user.getCp().isEmpty() || !user.checkCP(user.getCp()))
    		return 1;
    	if (user.getCorreue().isEmpty() || !user.checkCorreuE(user.getCorreue()))
    		return 2;
    	if (user.getMobil().isEmpty() || !user.checkMobil(user.getMobil()))
    		return 3;
    	if (user.getTelefon().isEmpty() || !user.checkTelefon(user.getTelefon()))
    		return 4;
    	if (nif.getId().isEmpty() || !nif.validar())
    		return 5;
    	if (nif.getId().isEmpty())
    		return 6;
    	return v;
    }
    
    /**
     * M�tode que a partir del nom del local retorna el seu id
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
     * M�tode per poder inserir un usuari una vegada les dades han estat validades
     * 
     */
    
    public String insertUser()
    {
    	String value = null;
    	int idUser = 0;
    	Usuari user = new Usuari();
    	user.setAdresa(txAddress.getText());
    	user.setcognoms(txCognoms.getText());
    	user.setCorreue(txe_mail.getText());
    	user.setCp(txCP.getText());
    	user.setData_alta(dataActual);
    	user.setData_baixa(dataBaixa);
    	user.setIdIdioma("1");
    	user.setIdLocal(cmbTaller.getSelectedItem().toString());
    	user.setIdLocal(getId_LocalwithName(user.getIdLocal()));
    	user.setidUsuari(txidUsuari.getText());
    	user.setLogin(txLogin.getText());
    	user.setMobil(txMobil.getText());
    	user.setNIF(txNIF.getText());
    	user.setnom(txName.getText());
    	user.setPais(txCountry.getText());
    	user.setPassword(txPassword.getText());
    	user.setPoblacio(txCity.getText());
    	user.setProvince(txProvince.getText());
    	user.setRol(cmbRol.getSelectedItem().toString());
    	user.setTelefon(txTel.getText());
    	user.setVigentSN(true);
    	idUser = validateUser(user);
        if (idUser==0)
    	{
    	try {
    		startConnection();
    		idUser = this._clientManager.getRMIInterface().addUser(user);
    		value = Integer.toString(idUser);	
    	}
    	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
    	txidUsuari.setText(idUserNew());
		cleanFields();
        
    	} else
    	{
    		switch (idUser)
    		{
    			case 1: value = "El Codi Postal indicat no és vàlid!!!!";
    					break;
    			case 2: value = "El e-mail indicat no és vàlid!!!!";
    					break;
    			case 3: value = "El telèfon mòbil indicat no és vàlid!!!!";
    					break;
    			case 4: value = "El telèfon fix indicat no és vàlid!!!!";
						break;
    			case 5: value = "El NIF indicat no és vàlid, recorda que cal indicar la lletra!!!!";
    					break;
    			case 6: value = "És obligatori indicar el NIF!!!!";
    		}
    		JOptionPane.showMessageDialog(null, "Hi ha alguna dada no valida!!!!!"+value);
			
    		// mostrar motiu pel que no es valid l'usuari
    	}
    	return value;
    }
    
    /**
     * M�tode per aconseguir el seg�ent id per l'usuari que es vol crear
     * 
     */
    public String idUserNew()
    {
    	String value = null;
    	try {
    		startConnection();
    		
    		value = this._clientManager.getRMIInterface().lastIdUser();
    		long l = Long.valueOf(value)+1;
        	value = Long.toString(l);	
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
	/***
	 * Omple un ComboBox amb la llista de locals.
	 * 
	 * @param comboBox Objecte que serà omplert.
	 */
	/**
	 * @param cmbTaller
	 */
	private void fillinComboBox(JComboBox<String> cmbTaller){
		try {
			startConnection();
			List<Local> locals = this._clientManager.getRMIInterface().getEstablishmentList(null);
			if (locals != null){
				for (Local item : locals){
					cmbTaller.addItem(item.getName());
				
					cmbTaller.setSelectedIndex(cmbTaller.getItemCount()-1);
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
				//System.out.println("Intentem comen�ar connexio");
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
	

	/***
	 * Inicialitza tots els gestors que utilitzarà
	 * l'aplicació
	 */
	private static void initializeManagers(){
		//Inicialitza una instància del gestor
		//d'internacionalització que
		//s'utilitzarà a tota l'aplicació
		Managers.i18n = new I18nManager(Constants.LANGUAGE_CATALAN);
		//Inicialitza una instància del gestor de
		//configuració que s'utilitzarà a tota l'aplicació
		Managers.settings = new SettingManager();
		//Incialitza una instància del gestor d'excepcions
		Managers.exception = new ExceptionManager();
	}
	
	
}
