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

import javax.swing.AbstractAction;
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
import uoc.tdp.pac4.st.common.ui.LabelTitle;
import uoc.tdp.pac4.st.common.ui.STTable;
import uoc.tdp.pac4.st.common.ui.SelectProductControl;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class ReceivingPieces extends JFrame {
	//RN: Temp
	private String codiLocal="L1"; 
	
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private JComboBox<ComboBoxItem>  cmbProveidor = null;
	private JTextField txtAlbaraProveidor = null;
	private JTextField txtDataAlbara = null;
	
	private SelectProductControl selectProductControl = null;
	private JTextField txtQuantitat = null;
	private JButton btnAdd; 
	private JButton btnSave;
	private JButton btnCancel;
	
	private STTable table= null;


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
			
				
					ReceivingPieces frame = new ReceivingPieces();
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
	public ReceivingPieces() {
		startConnection();
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 550);
		
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


		JLabel lblProveidor = new JLabel("LABEL_PROVEIDOR");
		lblProveidor.setBounds(50, 50, 80, 20);
		getContentPane().add(lblProveidor);

	    cmbProveidor = new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbProveidor(this._clientManager, cmbProveidor);
	    cmbProveidor.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					if (((ComboBoxItem)  cmbProveidor.getSelectedItem()).getId() != null) 
					{
						lineInputSetEnabled(true);
					}
					else 
					{
						lineInputSetEnabled(false);
					}

				}
		    }
		});			    
	    cmbProveidor.setBounds(120, 50, 200, 20);
	    getContentPane().add(cmbProveidor);

		JLabel lblAlbaraProveidor = new JLabel("LABEL_ALBARA_PROVEIDOR");
		lblAlbaraProveidor.setBounds(50, 80, 150, 20);
		getContentPane().add(lblAlbaraProveidor);

	    txtAlbaraProveidor = new JTextField();
	    txtAlbaraProveidor.setBounds(200, 80, 150, 20);
	    getContentPane().add(txtAlbaraProveidor);

		JLabel lblDataAlbara= new JLabel("LABEL_DATA_ALBARA");
		lblDataAlbara.setBounds(370, 80, 100, 20);
		getContentPane().add(lblDataAlbara);

	    this.txtDataAlbara = new JTextField();
	    txtDataAlbara.setBounds(470, 80, 100, 20);
	    this.txtDataAlbara.setText(Methods.formatDate(new Date()));
	    getContentPane().add(txtDataAlbara);
	    
	    
	    //Select product control
	    selectProductControl= new SelectProductControl(this._clientManager, (String) ((ComboBoxItem) this.cmbProveidor.getSelectedItem()).getId()); 
	    selectProductControl.setBounds(50, 80, 500, 100);
	    selectProductControl.setVisible(true);	       	   		   
	    getContentPane().add(selectProductControl);

		JLabel lblQuantitat= new JLabel("LABEL_QUANTITAT");
		lblQuantitat.setBounds(580, 135, 70, 20);
		add(lblQuantitat);
	    
	    
		txtQuantitat= new JTextField();
		txtQuantitat.setBounds(580, 155, 50, 20);			    	
		add(txtQuantitat);

		
	    btnAdd = new JButton("LABEL_AFEGIR");
	    btnAdd.setBounds(660, 150, 120, 25);
	    getContentPane().add(btnAdd);		
		btnAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
	        	if (isValidLine())
	        	{	        		
	        		addRowToTable();        	
	        	}
	        }
	      });

		drawTable();
		
		
	    btnCancel = new JButton("LABEL_CANCEL");
	    btnCancel.setBounds(100, 420, 100, 40);

	    getContentPane().add(btnCancel);	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		resetForm();
    	}
	    });

		btnSave = new JButton("LABEL_SAVE");
		btnSave.setBounds(600, 420, 100, 40);	    	    
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
		
		lineInputSetEnabled(false);

	}

	private boolean isValidLine(){
		if (selectProductControl.isValidLine()) 
		{
			if (! Methods.isPositiveInt(this.txtQuantitat.getText()))
			{
			   try {
					Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INVALID_QUANTITY"), Enums.MessageType.Warning);
				   } catch (Exception e1) {
			   }			  
			   return false;
			}
			
			return true;			
		}
		return false;
	}

	private void lineInputSetEnabled(boolean enabled) 
	{
		selectProductControl.setEnabled(enabled);
		btnAdd.setEnabled(enabled);
		txtQuantitat.setEnabled(enabled);
	}


	private void drawTable() 
	{		
		table= new STTable();
		table.showDeleteButton= true;
		table.setBounds(25, 100, 780, 300);
		table.setVisible(true);
		
		table.addColumn("productId", 0, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT"), 100, true, true);
		
		table.deleteRow= new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        if (table.getRowCount() == 0) 
		        {
		        	cmbProveidor.setEnabled(true);
		        }
		    }
		};	
		table.drawTable();
	    getContentPane().add(table);		
	}
	
	private void addRowToTable() 
	{
	
		String producteId= selectProductControl.producteId;
		String nomProducte= selectProductControl.nomProducte;
		Integer quantitat= Integer.parseInt(this.txtQuantitat.getText());
		
		DefaultTableModel model= (DefaultTableModel) this.table.getModel();
		
		//comprovar producte no s'ha afegit abans
		for(int row = 0;row < table.getRowCount();row++) {
	        String linProducteId= (String) table.getValueAt(row, 0);
	        if (linProducteId == producteId)
	        {
	        	int currentValue=  (int) table.getValueAt(row, 2);
	        	table.setValueAt(quantitat + currentValue, row, 2);
	        	return ;
	        }
	        
		}
		
		table.addRow(new Object[] { producteId, nomProducte, quantitat, "X"});
		this.cmbProveidor.setEnabled(false);
		this.btnSave.setEnabled(true);			
	}
	

	private void resetForm() {
		this.txtAlbaraProveidor.setText("");
		this.txtDataAlbara.setText(Methods.formatDate(new Date()));
		this.cmbProveidor.setEnabled(true);		
		this.cmbProveidor.setSelectedIndex(0);
		this.txtQuantitat.setText("");
		selectProductControl.setEnabled(false);
		btnAdd.setEnabled(false); 	
		
		selectProductControl.resetFields();
					
		table.removeRows();
	}
	
	private void save() {
		if (isValidForm()) 
		{
			Albara albara= new Albara();
			albara.setCodialbaraextern(this.txtAlbaraProveidor.getText());
			albara.setComAlbara("");
			albara.setDataAlbara(Methods.getDate(this.txtDataAlbara.getText()));
			albara.setOrigenId( (String) ((ComboBoxItem)  this.cmbProveidor.getSelectedItem()).getId());
			albara.setDestiId(codiLocal);
			albara.setLiniesAlbara(getLinAlbara());
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_ENTRADA);

			try {
				
				_clientManager.getRMIInterface().AddAlbara(albara);
				/*
				AlbaraManager m = new AlbaraManager(new DatabaseManager());
				m.Add(albara);
				*/
				
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
			linAlbara.setUnitats((Integer) table.getValueAt(i, 2));
			
			linees.add(linAlbara);
		}
		
		
		return linees;
	}

	
	private boolean isValidForm() {
		try 
		{
			
			if (((ComboBoxItem) cmbProveidor.getSelectedItem()).getId() == null)
			{
				this.cmbProveidor.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_CHOOSE_PROVIDER"), Enums.MessageType.Warning);
				return false;
			}			
			if (this.txtAlbaraProveidor.getText().trim().length() == 0 )
			{
				this.txtAlbaraProveidor.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INPUT_DELIVERY_NOTE_CODE"), Enums.MessageType.Warning);
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
