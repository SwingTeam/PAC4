package uoc.tdp.pac4.st.client.m;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.STFrame;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.CIF;
import uoc.tdp.pac4.st.common.dto.LocalSTer;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class LocalManagementWindow extends STFrame {


	private static ClientManager<ETallerStocksInterface> _clientManager = null;

	private static final long serialVersionUID = -9197238227523784333L;
    static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

    /**
     * L'atribut operation pot pendre els valor C, R, U o D
     * C per donar d'alta un nou Taller
     * R per consultar les dades d'un Taller
     * U per modificar les dades d'un Taller
     * D per esborrar un Taller
	*/
	LocalSTer local= new LocalSTer();
	java.util.Date dataActual = null;
	private boolean afterFind = false;
	//Fields
	private JTextField txCIF;
	private JTextField txName;
	private JTextField txAddress;
	private JTextField txCity;
	private JTextField txCP;
	private JTextField txDataAlta;
	private JTextField txTel;
	private JTextField txProvince;
	private JComboBox<String> cmbProvince = new JComboBox<String>();
	private JTextField txCountry;
	private JTextField txCoordX;
	private JTextField txCoordY;
//	private JTextField txTipusLocal;
//	tipus local :  C --> per magatzem central i L --> per local taller
	
	/*
ids_local PK numeric --> ids_local es genera automàtic, només indicar-lo una vegada creat
id_local unique 20 --> aquest no el farem servir es genera automàtic a partir del ids_local
nomlocal unique 40 
cif 9
telefon 20
adre├ºa 40
codpost 7
provincia_id 20 
pais 20
tipuslocal 20 !!!!!
dataalta  Date
coordx integer
coordy*/
	
	private JButton btnXNomTaller = new JButton("LABEL_SEARCH");
    private JButton btnXCIF = new JButton("LABEL_SEARCH");
	
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

				
				
					LocalManagementWindow frame = new LocalManagementWindow("U");
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
	public LocalManagementWindow(final String operation) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Situem la finestra
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    getContentPane().setLayout(null);
	    
	    
	    JLabel lblCIF = new JLabel("TX_CIF");
	    lblCIF.setBounds(10, 19, 60, 15);
	    getContentPane().add(lblCIF);
	    
	    txCIF = new JTextField();
	    txCIF.setBounds(75, 15, 70, 19);
	    getContentPane().add(txCIF);
	    {
		    JLabel lblName = new JLabel("TX_NOM");
		    lblName.setBounds(10, 46, 50, 15);
		    getContentPane().add(lblName);
		    
		    txName = new JTextField();
		    txName.setBounds(75, 42,100, 19);
		    getContentPane().add(txName);
		}
		{
		    JLabel lblTel = new JLabel("TX_TELEFON");
		    lblTel.setBounds(10,73, 50, 15);
		    getContentPane().add(lblTel);
		    
		    txTel = new JTextField();
		    txTel.setBounds(75, 69, 70, 19);
		    getContentPane().add(txTel);
		}
			{
				JLabel lblAdr = new JLabel("TX_ADRECA");
				lblAdr.setBounds(10, 100, 50, 15);
				getContentPane().add(lblAdr);
				
				txAddress = new JTextField();
			    txAddress.setBounds(75,96,150,19);
			    getContentPane().add(txAddress);
			}
			{
				JLabel lblCity = new JLabel("TX_POBLACIO");
				lblCity.setBounds(10, 127, 60, 15);
				getContentPane().add(lblCity);
				
				txCity = new JTextField();
			    txCity.setBounds(75,123,150,19);
			    getContentPane().add(txCity);
		    
			}
			JLabel lblProv = new JLabel("TX_PROVINCIA");
			lblProv.setBounds(10, 154, 60, 15);
			getContentPane().add(lblProv);
			
			txProvince = new JTextField();
			txProvince.setBounds(75,150, 100,19);
			getContentPane().add(txProvince);
			
			
		{
			JLabel lblPais = new JLabel("TX_PAIS");
			lblPais.setBounds(10, 181, 60, 15);
			getContentPane().add(lblPais);
			//
			txCountry = new JTextField();
		    txCountry.setBounds(75,177,100,19);
		    getContentPane().add(txCountry);
		}

			JLabel lblCP = new JLabel("TX_CP");
			lblCP.setBounds(10, 208, 25, 15);
			getContentPane().add(lblCP);
			//
			txCP = new JTextField();
		    txCP.setBounds(75,204,50,19);
		    getContentPane().add(txCP);

		{
			JLabel lblDataAlta = new JLabel("TX_DATA_ALTA");
		    lblDataAlta.setBounds(265, 19, 60, 15);
		    getContentPane().add(lblDataAlta);
		    
		    txDataAlta = new JTextField();
		    txDataAlta.setEditable(false); 
		    txDataAlta.setBounds(325, 15, 90, 19);
		    getContentPane().add(txDataAlta);

			JLabel lblcoord = new JLabel("TX_MS_COO");
			lblcoord.setForeground(Color.BLUE);
			lblcoord.setBounds(235,67,200,15);
			getContentPane().add(lblcoord);
				JLabel lblx = new JLabel("TX_MS_COOX");
				lblx.setBounds(240,87, 90, 15);
				getContentPane().add(lblx);
				txCoordX = new JTextField();
				txCoordX.setBounds(335,83,80,19);
			    getContentPane().add(txCoordX);
				JLabel lbly = new JLabel("TX_MS_COOY");
				lbly.setBounds(240, 107, 90, 15);
				getContentPane().add(lbly);
				txCoordY = new JTextField();
			    txCoordY.setBounds(335,103,80,19);
			    getContentPane().add(txCoordY);
		   putRadioButtons("C");
			     
		}
		
		    btnCleanFields.setBounds(345, 220, 85,19);
		    getContentPane().add(btnCleanFields);
		    
		    /*  BEGIN Customize window depend on type of action, if it is Create, Update, Read or Delete*/
		    
		    btnClose.setBounds(345, 240, 85,19);
		    getContentPane().add(btnClose);
		    if (operation != "R")
		    {
		    	  btnAccio.setBounds(345,200,85,19);
		    	  if (operation == "U")
		    		  btnAccio.setBounds(340, 200,90,19);
				  getContentPane().add(btnAccio);
		    }
		    
		    if (operation == "C") // INSERT
		    {
		    	fillinComboBoxProvince(cmbProvince);
				dataActual = new Date();
	  		    SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy");
	            txDataAlta.setText(formatr.format(dataActual));
	    		btnAccio.setText("LABEL_CREATE");
	            setTitle("TITLE_LOCAADD");
		    }
		    if (operation == "U") // UPDATE
		    {
		    	btnAccio.setText("LABEL_MODIFICAR"); // FALTA CANVIAR-HO A LocalManagementWindowS
				setTitle("TITLE_LOCAUPD");
			}
		    if (operation == "C" || operation == "U")// INSERT or UPDATE
			{
				cmbProvince.setBounds(75,150,100,19);
			    getContentPane().add(cmbProvince);
				cmbProvince.setEditable(true);
			    txName.setEditable(true);
			    txTel.setEditable(true);
			    txAddress.setEditable(true);
			    txCity.setEditable(true);
			    txCP.setEditable(true);
			    txCountry.setEditable(true);
			    txCIF.setEditable(true); 
			}
		if (operation == "R") // SELECT
		{
			setTitle("TITLE_LOCASEL");
		}
		if (operation == "R" || operation == "U" || operation == "D") // SELECT or UPDATE or DELETE
		{
			btnXCIF.setBounds(145, 15, 80,19);
	    	getContentPane().add(btnXCIF);
	    	btnXNomTaller.setBounds(175, 42, 80,19);
	    	getContentPane().add(btnXNomTaller);
		}
		if (operation ==  "R" || operation == "D") // SELECT or DELETE
			{
				txProvince.setEditable(false);
	    	    txTel.setEditable(false);
			    txAddress.setEditable(false);
			    txCity.setEditable(false);
			    txCP.setEditable(false);
			    txCountry.setEditable(false);
			    txCIF.setEditable(true);
			    txCoordX.setEditable(false);
			    txCoordY.setEditable(false);
				txName.setEditable(true);
				
			}
		if (operation == "D")
		{
				btnAccio.setText("LABEL_ESBORRAR");// FALTA CANVIAR-HO A LocalManagementWindowS
				setTitle("TITLE_LOCADEL");
		}

		

		/* EVENTS - BEGIN *************************************************************************************/

		//BOTO QUE SEGONS LA OPERACIO HA DE PERMETRE INSERT, UPDATE OR DELETE
		btnAccio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch (operation)
			    {
			    case "C": 
			    		  insertLocal(operation);
			    		  break;
			    case "U": 
			    	      updateLocal(operation);
			    		  break;
			    case "D": deleteLocal(operation);
			    		  break;
			    }
			}
		});
				
		// BOTO QUE PERMET FER UNA CERCA D'UN taller PEL CAMP IDtaller
	    btnXCIF.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    			searchLocal(Constants.FIELD_CIF,operation);
	    	}
	    });
	    // BOTO QUE PERMET FER UNA CERCA D'UN taller PEL CAMP CIF
	    btnXNomTaller.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			searchLocal(Constants.FIELD_NOMLOCAL,operation);
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

		    
	}// END CONSTRUCTOR


	private void putRadioButtons(String local)
	{
    JLabel lbltT = new JLabel("TX_MS_TALLER");
	lbltT.setForeground(Color.BLUE);
	lbltT.setBounds(240,130,180,15);
	getContentPane().add(lbltT);
	JRadioButton rbtC = new JRadioButton("TX_B_1");
     JRadioButton rbtL = new JRadioButton("TX_B_2");
     if (local.compareTo("L")==0)
     {
     rbtL.setSelected(true);
     rbtC.setSelected(false);
     }else
     {
         rbtL.setSelected(true);
         rbtC.setSelected(false);
     }
     rbtC.setBounds(250, 150,150, 15);
     rbtL.setBounds(250, 165,150, 15);
     ButtonGroup group = new ButtonGroup();
      group.add(rbtC);
      group.add(rbtL);
      getContentPane().add(rbtC);
     getContentPane().add(rbtL);
}
	/***
	 * @author emarsal2
	 * @since divendres 5
	 * Omple un ComboBox amb la llista de Provincies.
	 * 
	 * @param comboBox Objecte que serà omplert.
	 */
	private void fillinComboBoxProvince(JComboBox<String> cmb){
		try {

			List<String> province = _clientManager.getRMIInterface().getProvinceList();
			if (province != null){
				cmb.addItem(null);
				for (String item : province)
					cmb.addItem(item);
				cmb.setSelectedIndex(0);
			}
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{

		}
	}

	   /**
	    * @author emarsal2
	    * @since diumenge 7
     * Mètode per deixar tots els camps en blanc
    **/
 public void   cleanFields(String operation){
 	txCIF.setText(null);
 	txName.setText(null);
 	if (operation == "U")
 	{
 		txDataAlta.setText(null);
 	}
 	if (operation == "U" || operation == "C")
 	{
 		cmbProvince.removeAllItems();
 		cmbProvince.setSelectedItem(null);
 	}
 	if (operation == "C")
 	{
 		fillinComboBoxProvince(cmbProvince);
 	}
		if (operation == "R" || operation=="D") // SELECT or DELETE
		{
			txDataAlta.setText(null);
			txProvince.setText(null);
		}
		txTel.setText(null);
		txAddress.setText(null);
		txCity.setText(null);
		txCP.setText(null);
		txCountry.setText(null);
		afterFind = false;
}
 /**
  * @author emarsal2
  * @since dissabte 6
  * Mètode per comprovar que les noves dades del taller siguin vàlides, si és el cas retorna 0
  * en cas que no siguin vàlides retorna un numero per indicar on s'ha detectat un possible error
  * 
  * @return enter 0 si tot és correcta, altrament si hi ha alguna dada no vàlida
  */
 public int validateLocal(boolean cifOk,String operation)
 {
 	int v=0;
 	boolean repetit= false;
 	CIF cif = new CIF("");
 	cif.setId(local.getCIF());
 	
 	if (!local.getCp().isEmpty() && !local.checkCP(local.getCp()))
 		return 1;
 	if (!local.getTelephone().isEmpty() && !local.checkTelefon(local.getTelephone()))
 		return 4;
 	if (cif.getId().isEmpty())
 		return 6;
 	if (!cif.validar())
 		return 5;
 	if (operation!="U" || cifOk==true) // si no es tracta d'un UPDATE, és a dir, és un INSERT i en aquest cas cal comprovar que el CIF no existeixi ja
 	{									// o bé és un UPDATE i el nou CIF és diferent del nou, llavors cal comprovar que no existeixi ja
 		try {

 			repetit = _clientManager.getRMIInterface().findCIF(cif.getId());
 		}
 		catch (STException e) {
				Managers.exception.showException(e);
			} catch (RemoteException | NullPointerException e) {
				Managers.exception.showException(new STException(e));
			}finally{
			}
 		if (repetit) return 7;
 	}
 	return v;
 }
 
 /**
  * @author emarsal2
  * @since diumenge 7
  * Mètode privat per mostrar un avis  respecte un codi d'error numèric sobre dades d'un taller
  * 
  * @param idUser codi d'error per decidir quin avís cal mostrar
  */
 private void showAdvise(int idUser)
 {
 	String value = null;
 	switch (idUser)
		{
			case 1: value = "AD_CP";
					break;
			case 4: value = "AD_FIX";
					break;
			case 5: value = "AD_CK_CIF";
					break;
			case 6: value = "AD_CIF";
					break;
			case 7: value = "I_CIF";
		}
		JOptionPane.showMessageDialog(null, value);
 }
 /**
  * @author emarsal2
  * @since dimarts 9
  * Mètode per llegir dades dels camps de la Pantalla amb valors nous
cif 9
telefon 20
codpost 7
provincia_id 20 
tipuslocal 20 !!!!!
dataalta  Date
coordx integer

  *  
  */
 private void readLocal()
 {
 	// reading no Mandatory fields
 	if (txName.getText().length() > 40)
			{
 		JOptionPane.showMessageDialog(null,Constants.FIELD_NOMLOCAL+" AD_TOO_LONG"+txName.getText().substring(0, 39)+"'");
 		local.setName(txName.getText().substring(0, 19));
			}
 	else
			{
		local.setName(txName.getText());
			}
 	if (txCountry.getText().length() > 20)
		{
 		JOptionPane.showMessageDialog(null,Constants.FIELD_PAIS+" AD_TOO_LONG"+txCountry.getText().substring(0, 19)+"'");
 		local.setCountry(txCountry.getText().substring(0, 19));
		}
 	else
		{
 		local.setCountry(txCountry.getText());
		}
 	if (txCity.getText().length() > 20)
		{
 		JOptionPane.showMessageDialog(null,Constants.FIELD_POBLACIO+" AD_TOO_LONG"+txCity.getText().substring(0, 19)+"'");
 		local.setCity(txCity.getText().substring(0, 19));
		}
 	else
		{
 		local.setCity(txCity.getText());
		}
 	if (txAddress.getText().length() > 40)
		{
 		JOptionPane.showMessageDialog(null,Constants.FIELD_ADRECA+" AD_TOO_LONG"+txAddress.getText().substring(0, 39)+"'");
 		local.setAddress(txAddress.getText().substring(0, 39));
		}
 	else
		{
 		local.setAddress(txAddress.getText());
		}
 	
 	// reading fields from comboBox
 	if (cmbProvince.getSelectedItem() != null)
 		local.setProvince(cmbProvince.getSelectedItem().toString());
 	else
 		local.setProvince(null);
 	// reading mandatory fields
 	local.setCIF(txCIF.getText().toUpperCase());
 	local.setTelephone(txTel.getText());
 	local.setCp(txCP.getText());
 }
 
 /**
  * @author emarsal2
  * @since divendres 5
  * Mètode per modificar les dades d' un taller a la BD
  * Inclou la validació de les dades
  * 
  */
 public void updateLocal(String operation)
 {
 	String value = null;
 	int idLocal = 0;
 	String oldCIF,newCIF;
 	String msg = null;
 	boolean cifOk = false;
 	if (afterFind) { // si realment s'ha fet searchLocal amb resultats satisfactoris
 		oldCIF = local.getCIF();
 		readLocal();
 		newCIF = local.getCIF();
 		if (oldCIF.compareTo(newCIF)!=0) 
 			cifOk=true; // si els CIF són diferents
 		idLocal = validateLocal(cifOk,operation);
 			if (idLocal == 0 || idLocal == 7) // dades noves valides
 			{
 				try {

 					value = _clientManager.getRMIInterface().updateLocal(local);
 					}
 				catch (STException e) {
 					Managers.exception.showException(e);
 				} catch (RemoteException | NullPointerException e) {
 					Managers.exception.showException(new STException(e));
 				}finally{

 				}
 				if (idLocal == 7)
 					msg = ". I_CIF";
 				JOptionPane.showMessageDialog(null,"I_TALLER "+value+" I_MODIFICAR"+msg);
 				cleanFields(operation);
 			} else // dades noves no valides, però potser si les modifica poden ser vàlides per aixó el afterFind el continuem deixant a true
 			{
 				showAdvise(idLocal);
 			}
 	}else { 
 		JOptionPane.showMessageDialog(null,"AD_MODI_LOCA");
 		afterFind=false; // reiniciem el valor
 	} 
 	
 }

 /**
  * @author emarsal2
  * @since divendres 5
  * Mètode per inserir un taller a la BD
  * Inclou la validació de les dades
  * 
  */
 
 public void insertLocal(String operation)
 {
 	String value = null;
 	int idLocal = 0;
 	readLocal();
 	// reading fields only necessary when INSERT
 	local.setData_alta(dataActual);
 	idLocal = validateLocal(true,operation);
     if (idLocal==0 || idLocal == 7)
 	{
 	try {

 		idLocal = _clientManager.getRMIInterface().addLocal(local);
 		value = Integer.toString(idLocal);
 		}
 	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{

		}
 		if (idLocal == 7)
 			JOptionPane.showMessageDialog(null,"I_TALLER "+value+" I_ALTA. I_CIF");
 		else
 			JOptionPane.showMessageDialog(null,"I_TALLER "+value+" I_ALTA.");
		cleanFields(operation);
 	} else
 	{
 		showAdvise(idLocal);
 	}
 	
 }
	/**
	 * @author emarsal2
	 * @since dilluns 8
	 * Mètode que serveix per cercar un taller a partir del seu identificador proporcionat al camp de text de la finestra.
	 */
	private void searchLocal (String nameColumn, String operation)
	{
		String column;
		if (nameColumn.equals(Constants.FIELD_CIF)) // establish with which column do the research
		{
			column = txCIF.getText();
			column = column.toUpperCase();
		} else
		{
			column = this.txName.getText(); 
		}
		if (column==null || column.isEmpty() )
		{
			cleanFields(operation);
			JOptionPane.showMessageDialog(null, "AD_INDICAR"+nameColumn.toUpperCase()+"AD_CERCAR");
		} else {
		try {

 		local = _clientManager.getRMIInterface().localQuery(column,nameColumn);
 		}
 	catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}finally{

		}
		// ja tenim a dins local el taller que cercavem
		if (local.getCIF().compareTo("000000000")!=0) // Taller found
		{
			cleanFields(operation);
			if (nameColumn.equals(Constants.FIELD_CIF))
			{
				txCIF.setText(column);
			}else
			{
				txName.setText(column);
			}
			if (operation == "U")
			{
				fillinComboBoxProvince(cmbProvince);
			}
			fillFields(operation);
			afterFind = true;
		} else {// the Local wasn't found
			cleanFields(operation); 
			if (nameColumn.equals(Constants.FIELD_CIF))
			{
				txCIF.setText(column);
			} else
			{
				txName.setText(column); 
			}
			JOptionPane.showMessageDialog(null,"AD_TALLER");
		}
		}
	}
	
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * Mètode que donat un taller com a paràmetre posa els seus atributs dins els camps de text de la finestra actual
	 * 
	 */
	private void fillFields(String operation)
	{
		putRadioButtons(local.getLocalTipus());
			txTel.setText(local.getTelephone());
			txCIF.setText(local.getCIF());
			txCoordX.setText(Integer.toString(local.getCoordX()));
			txCoordY.setText(Integer.toString(local.getCoordY()));
			Date tmpData = local.getData_alta();
			SimpleDateFormat formatr = new SimpleDateFormat("dd/MM/yyyy");
	        txDataAlta.setText(formatr.format(tmpData));
	        txCP.setText(local.getCp());
	 		if (local.getCity()!=null)
	 		    txCity.setText(local.getCity());
	 		if (local.getCountry()!=null) 
				txCountry.setText(local.getCountry());
	 		if (local.getName()!=null)
	 		    txName.setText(local.getName());
	 		if (local.getAddress()!=null)
				txAddress.setText(local.getAddress());
	 		if (operation == "D" || operation == "R") // DELETE or SELECT
	 		{
	 				txProvince.setText(local.getProvince());
	 		}
			if (operation == "U")
			{
				cmbProvince.setSelectedItem(local.getProvince());
			}
		}
	
	/**
	 * @author emarsal2
	 * @since diumenge 7
	 * Mètode per donar de baixa un taller de la BB
	 */
	private void deleteLocal(String operation)
	{
		int replay;
		int value = local.getId();
		if (afterFind) // si realment s'ha cercat abans un taller i la cerca ha anat bé
			{
			replay = JOptionPane.showConfirmDialog(null,"I_ERA_LOCA "+value+"I_SURE" );
			if (replay == JOptionPane.YES_OPTION)
			{
				try {

					_clientManager.getRMIInterface().deleteLocal(value);
				}
				catch (STException e) {
					Managers.exception.showException(e);
				} catch (RemoteException | NullPointerException e) {
					Managers.exception.showException(new STException(e));
				}finally{

				}
				JOptionPane.showMessageDialog(null,"I_TALLER "+value+" I_BAIXA");
				cleanFields(operation);
			}
			} else {
				JOptionPane.showMessageDialog(null,"AD_BAI_LOCA");
			} // end else CIF = empty or not found
		afterFind=false; // reiniciem el valor
	 } // end deleteUser method
	
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
