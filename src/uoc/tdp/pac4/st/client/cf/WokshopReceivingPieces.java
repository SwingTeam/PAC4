package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uoc.tdp.pac4.st.common.ComboBoxItem;
import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.Enums;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.Methods;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.MovimentManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.common.ui.ComboBoxHelper;
import uoc.tdp.pac4.st.common.ui.LabelSubTitle;
import uoc.tdp.pac4.st.common.ui.LabelTitle;
import uoc.tdp.pac4.st.common.ui.STTable;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class WokshopReceivingPieces extends JFrame {
	//RN: Temp
	private String codiLocal_central="L1"; 
	private String codiLocal="L2";
	
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private JComboBox<ComboBoxItem>  cmbAlbara = null;
	private JTextField txtDataAlbara = null;
	
	private JButton btnSave;
	private JButton btnCancel;
	
	private STTable table= null;
	
	private Albara albara= null;


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
			
				
					WokshopReceivingPieces frame = new WokshopReceivingPieces();
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
	public WokshopReceivingPieces() {
		startConnection();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Situem la finestra
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    getContentPane().setLayout(null);
	    
    	LabelTitle lblTitle = new LabelTitle("LABEL_RECEPCIO_PECES");
    	lblTitle.setBounds(10, 10, 400, 20);
		getContentPane().add(lblTitle);

		LabelSubTitle lblEscollirAlbara = new LabelSubTitle("LABEL_CHOOSE_ALBARA");  
		lblEscollirAlbara.setBounds(50, 50, 400, 20);
		getContentPane().add(lblEscollirAlbara);
		
		
		JLabel lblProveidor = new JLabel("LABEL_ALBARA");
		lblProveidor.setBounds(50, 80, 80, 20);
		getContentPane().add(lblProveidor);

	    cmbAlbara = new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbAlbara(this._clientManager, cmbAlbara, codiLocal);
	    cmbAlbara.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					if (((ComboBoxItem) cmbAlbara.getSelectedItem()).getId() != null)
					{
						updateTable();
					}
				}
		    }
		});			    
	    cmbAlbara.setBounds(120, 80, 200, 20);
	    getContentPane().add(cmbAlbara);
	    
	    
		JLabel lblDataAlbara= new JLabel("LABEL_DATA_ALBARA");
		lblDataAlbara.setBounds(340, 80, 100, 20);
		getContentPane().add(lblDataAlbara);

	    this.txtDataAlbara = new JTextField();
	    txtDataAlbara.setBounds(440, 80, 100, 20);
	    this.txtDataAlbara.setText(Methods.formatDate(new Date()));
	    getContentPane().add(txtDataAlbara);
	    	    

	    LabelSubTitle lblInstruccions= new LabelSubTitle("LABEL_UPDATE_ALBARA");  
	    lblInstruccions.setBounds(50, 120, 400, 20);
		getContentPane().add(lblInstruccions);
			    
    
		drawTable();
		
		
	    btnCancel = new JButton("LABEL_CANCEL");
	    btnCancel.setBounds(100, 400, 100, 40);

	    getContentPane().add(btnCancel);	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		resetForm();
    	}
	    });

		btnSave = new JButton("LABEL_SAVE");
		btnSave.setBounds(600, 400, 100, 40);	    	    
	    getContentPane().add(btnSave);		    
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (isValidForm())
	        	{	        		
	        		save();        	
	        	}
	    	}
	    });	    

		
	    
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		//titol pantalla
		setTitle(Managers.i18n.getTranslation("LABEL_RECEPCIO_PECES"));
		

	}

	private void updateTable() 
	{

		try {

			table.removeRows();
			
			int albaraId= (int) ((ComboBoxItem) cmbAlbara.getSelectedItem()).getId();
			 
			albara= _clientManager.getRMIInterface().getAlbaraById(albaraId);
			
			for (LinAlbara linAlbara: albara.getLiniesAlbara())
			{
				addRowToTable(linAlbara);			
			}
			
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, "ERROR_GETTING_DATA"));
		}		
	}

	private void drawTable() 
	{		
		table= new STTable();
		table.showDeleteButton= false;
		table.setBounds(25, 80, 780, 300);
		table.setVisible(true);
		
		table.addColumn("producteId", 0, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT_DEMANADA"), 100, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT_SERVIDA"), 100, true, true);
		
		
		table.drawTable();
	    getContentPane().add(table);		
	}
	
	private void addRowToTable(LinAlbara linia) 
	{		
		DefaultTableModel model= (DefaultTableModel) this.table.getModel();	
		table.addRow(new Object[] { linia.getProducte().getIdProducte(), linia.getProducte().getNomProducte(), linia.getMoviment().getNumUnitatsOrdre(), linia.getMoviment().getNumUnitSortides()});
		
		this.btnSave.setEnabled(true);			
	}
	

	private void resetForm() {
		this.cmbAlbara.setSelectedIndex(0);
		
		table.removeRows();
	}
	
	private void save() {
		if (isValidForm()) 
		{			
			try {
								
				Albara albara= new Albara();
				albara.setCodialbaraextern("");
				albara.setComAlbara("");
				albara.setDataAlbara(new Date());
				albara.setOrigenId(codiLocal_central);
				albara.setDestiId(codiLocal);
				albara.setLiniesAlbara(getLinAlbara());
				albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_ENTRADA);
				
					
				_clientManager.getRMIInterface().addAlbara(albara);
				
				resetForm();
				
				Methods.showMessage( Managers.i18n.getTranslation("INFO_ALBARA_SAVED"), Enums.MessageType.Info);
			} catch (Exception e) {
				Managers.exception.showException(new STException(e, "ERROR_SAVING_ALBARA"));
			}
		}
	}
	
	private ArrayList<LinAlbara> getLinAlbara() 
	{
		ArrayList<LinAlbara> linees= new ArrayList<LinAlbara>();
		
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		int rowCount = defaultTableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			LinAlbara linAlbara= new LinAlbara(); 
			linAlbara.setProducteId((String) table.getValueAt(i, 0));
			linAlbara.setUnitats(Integer.parseInt(table.getValueAt(i, 3).toString()));
			
			linees.add(linAlbara);
		}
		
		
		return linees;
	}

	
	private boolean isValidForm() {
		try 
		{
			
			if (((ComboBoxItem) cmbAlbara.getSelectedItem()).getId() == null)
			{
				this.cmbAlbara.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_CHOOSE_ALBARA"), Enums.MessageType.Warning);
				return false;
			}			

			if (! Methods.isValidDate(this.txtDataAlbara.getText()))
			{
				this.txtDataAlbara.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INVALID_DELIVERY_NOTE_DATE"), Enums.MessageType.Warning);
				return false;
			}			
		}
		catch(Exception e){};
		
		return true;
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
	
}
