package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.Enums;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.Methods;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.common.ui.LabelSubTitle;
import uoc.tdp.pac4.st.common.ui.LabelTitle;
import uoc.tdp.pac4.st.common.ui.STTable;
import uoc.tdp.pac4.st.common.ui.SelectProductControl;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class WorkshopRequestPieces extends JFrame {
	//RN: Temp
	private String codiLocal_desti="L1"; 
	private String codiLocal_origen="L2";
	
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private SelectProductControl selectProductControl = null;
	private JTextField txtQuantitat = null;
	private JButton btnAfegirAutomaticament;
	private JLabel lblEstoc;
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
			
				
					WorkshopRequestPieces frame = new WorkshopRequestPieces();
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
	public WorkshopRequestPieces() {
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
	    
    	LabelTitle lblTitle = new LabelTitle("LABEL_DEMANAR_PECES");
    	lblTitle.setBounds(10, 10, 400, 20);
		getContentPane().add(lblTitle);

    	LabelSubTitle lbAfegirAutomaticament = new LabelSubTitle("LABEL_AGEGIR_PETICIONS_AUTOMATICAMENT");
    	lbAfegirAutomaticament.setBounds(20, 50, 400, 20);
		getContentPane().add(lbAfegirAutomaticament);

		
		btnAfegirAutomaticament=  new JButton("LABEL_CARREGAR_PRODUCTES_ESTOC");
		btnAfegirAutomaticament.setBounds(20, 80, 350, 25);	
	    btnAfegirAutomaticament.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
	        	loadProducts();
	        }
	      });
	    getContentPane().add(btnAfegirAutomaticament);
	    

    	LabelSubTitle lbAfegirManualment = new LabelSubTitle("LABEL_AFEGIR_PETICIONS_MANUALMENT");
    	lbAfegirManualment.setBounds(20, 130, 400, 20);
		getContentPane().add(lbAfegirManualment);
			
	    
	    //Select product control
	    selectProductControl= new SelectProductControl(this._clientManager, null); 
	    selectProductControl.setBounds(20, 100, 500, 100);
	    selectProductControl.setVisible(true);	       	  
  		Action _canviarProducteAction = new AbstractAction()
  		{
  		    public void actionPerformed(ActionEvent e)
  		    {
  		    	UpdateEstock();
  		    }
  		};
  		selectProductControl.canviarProducteAction = _canviarProducteAction;		 
  		 
	    getContentPane().add(selectProductControl);
	    	    
		//estoc
		JLabel lblStock= new JLabel("LABEL_ESTOC");
		lblStock.setBounds(535, 150, 50, 20);
		add(lblStock);

	    lblEstoc= new JLabel();
	    lblEstoc.setBounds(535, 175, 50, 20);
	    add(lblEstoc);			
	    
	    //quantitat
		JLabel lblQuantitat= new JLabel("LABEL_QUANTITAT");
		lblQuantitat.setBounds(580, 150, 70, 20);
		add(lblQuantitat);
	    	    
		txtQuantitat= new JTextField();
		txtQuantitat.setBounds(580, 175, 50, 20);			    	
		add(txtQuantitat);

		
	    btnAdd = new JButton("LABEL_AFEGIR");
	    btnAdd.setBounds(660, 170, 120, 25);
	    getContentPane().add(btnAdd);		
		btnAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
	        	if (isValidLine())
	        	{	        		
	        		addRowToTable();        	
	        	}
	        }
	      });

		
		
		
	    btnCancel = new JButton("LABEL_CANCEL");
	    btnCancel.setBounds(100, 460, 100, 40);

	    getContentPane().add(btnCancel);	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		resetForm();
    	}
	    });

		btnSave = new JButton("LABEL_DEMANAR_PECES");
		btnSave.setBounds(600, 460, 150, 40);	    	    
	    getContentPane().add(btnSave);		    
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (isValidForm())
	        	{	        		
	        		save();        	
	        	}
	    	}
	    });	    
	    btnSave.setEnabled(false);
		
	    drawTable();
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		//titol pantalla
		setTitle(Managers.i18n.getTranslation("LABEL_DEMANAR_PECES"));		

	}
	
	private void UpdateEstock() 
	{
		try {
			
			String producteId= this.selectProductControl.producteId;
			String localId= codiLocal_origen;
			
			Existencies existencies = _clientManager.getRMIInterface().getExistenciesByProducteAndLocal(producteId, localId);
			if (existencies != null)
			{			
				lblEstoc.setText(String.valueOf(existencies.getEstoc()));
			}
			else 
			{
				lblEstoc.setText("0");
			}
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, "ERROR_UNEXPECTED"));
		}
	}
	
	
	private void loadProducts()
	{
		this.table.removeRows();
		
		try {
			
			
			List<Producte> list = _clientManager.getRMIInterface().getStockMinim(codiLocal_origen);
			
			if (list.size() > 0 )
			{
				AddRowsToTable(list);
			}
			else 
			{
				Methods.showMessage( Managers.i18n.getTranslation("LABEL_NO_PRODUCTES_ESTOC_MINIM"), Enums.MessageType.Info);
				
			}
		} 
		catch (Exception e) {
			Managers.exception.showException(new STException(e, e.getMessage()));
		}
		
	}
	
	private void AddRowsToTable(List<Producte> list) 
	{
		table.removeRows();
		
		for (Producte producte: list )
		{
			table.addRow(new Object[] { producte.getIdProducte(), producte.getNomProducte(), producte.getExistencies().getEstoc(), producte.getExistencies().getEstocMinim(), "X"});
		}			
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
	
	private void drawTable() 
	{		
		table= new STTable();
		table.showDeleteButton= true;
	
		table.addColumn("productId", 0, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_ESTOC"), 100, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT"), 100, true, true);
		
		table.setBounds(10, 110, 810, 350);
		
		
		table.drawTable();
	    getContentPane().add(table);		
	}
	
	private void addRowToTable() 
	{
	
		String producteId= selectProductControl.producteId;
		String nomProducte= selectProductControl.nomProducte;
		String estoc= this.lblEstoc.getText();
		Integer quantitat= Integer.parseInt(this.txtQuantitat.getText());
		
		DefaultTableModel model= (DefaultTableModel) this.table.getModel();
		
		//comprovar producte no s'ha afegit abans
		for(int row = 0;row < table.getRowCount();row++) {
	        String linProducteId= (String) table.getValueAt(row, 0);
	        if (linProducteId == producteId)
	        {
	        	int currentValue=  (int) table.getValueAt(row, 3);
	        	table.setValueAt(quantitat + currentValue, row, 3);
	        	return ;
	        }
	        
		}
		
		table.addRow(new Object[] { producteId, nomProducte, estoc, quantitat, "X"});
		this.btnSave.setEnabled(true);			
	}
	

	private void resetForm() {
		this.txtQuantitat.setText("");		
		selectProductControl.resetFields();					
		table.removeRows();
		this.btnSave.setEnabled(false);
	}
	
	private void save() {
		if (isValidForm()) 
		{
			Albara albara= new Albara();
			albara.setCodialbaraextern("");
			albara.setComAlbara("APROVISIONA");
			albara.setDataAlbara(new Date());
			albara.setOrigenId(codiLocal_origen);
			albara.setDestiId(codiLocal_desti);
			albara.setLiniesAlbara(getLinAlbara());
		

			try {
			
				_clientManager.getRMIInterface().demanarPeces(albara);
			
				resetForm();
				
				Methods.showMessage( Managers.i18n.getTranslation("LABEL_PECES_DEMANADES"), Enums.MessageType.Info);
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
			linAlbara.setUnitats((Integer) table.getValueAt(i, 3));
			
			linees.add(linAlbara);
		}
		
		
		return linees;
	}

	
	private boolean isValidForm() {
		try 
		{
			
		
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
