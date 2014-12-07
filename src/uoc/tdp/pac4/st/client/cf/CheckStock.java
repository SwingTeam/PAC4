package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.ProducteManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.common.ui.ComboBoxHelper;
import uoc.tdp.pac4.st.common.ui.LabelSubTitle;
import uoc.tdp.pac4.st.common.ui.LabelTitle;
import uoc.tdp.pac4.st.common.ui.STTable;
import uoc.tdp.pac4.st.common.ui.SelectProductControl;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class CheckStock extends JFrame {
	//RN: Temp
	private String codiLocal="L1"; 
	
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private SelectProductControl selectProductControl = null;
	private JComboBox<ComboBoxItem>  cmbLocal= null;
	private JTextField  txtStockInicial= null;
	private JTextField  txtStockFinal= null;
	
	
	private JButton btnClean;
	private JButton btnSearch;
	
	private STTable table = null;


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
			
				
					CheckStock frame = new CheckStock();
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
	public CheckStock() {
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
	    
    	LabelTitle lblTitle = new LabelTitle("LABEL_CHECK_STOCK");
    	lblTitle.setBounds(10, 10, 400, 20);
		getContentPane().add(lblTitle);

    	LabelSubTitle lblFiltreProducte = new LabelSubTitle("LABEL_FILTRE_PRODUCTE");
    	lblFiltreProducte.setBounds(40, 50, 200, 20);
		getContentPane().add(lblFiltreProducte);
		
		
	    //Select product control
	    selectProductControl= new SelectProductControl(this._clientManager, null); 
	    selectProductControl.setBounds(50, 20, 500, 100);    
	    getContentPane().add(selectProductControl);
	    	

    	LabelSubTitle lblFiltreWorkshop = new LabelSubTitle("LABEL_FILTRE_TALLER");
    	lblFiltreWorkshop.setBounds(40, 130, 200, 20);
		getContentPane().add(lblFiltreWorkshop);
		
		cmbLocal = new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbLocal(this._clientManager, cmbLocal);	  
	    cmbLocal.setBounds(50, 150, 200, 20);
	    getContentPane().add(cmbLocal);		
		
		
    	LabelSubTitle lblFiltreStock = new LabelSubTitle("LABEL_FILTRE_ESTOC");
    	lblFiltreStock.setBounds(300, 130, 200, 20);
		getContentPane().add(lblFiltreStock);

		JLabel lbDesde= new JLabel("LABEL_DESDE");
		lbDesde.setBounds(300, 150, 50, 20);
		getContentPane().add(lbDesde);
		
		txtStockInicial = new JTextField();   
		txtStockInicial .setBounds(350, 150, 50, 20);
	    getContentPane().add(txtStockInicial);
	    
		JLabel lbFins= new JLabel("LABEL_FINS");
		lbFins.setBounds(410, 150, 50, 20);
		getContentPane().add(lbFins);
	    
	    
		txtStockFinal = new JTextField();   
		txtStockFinal .setBounds(460, 150, 50, 20);
	    getContentPane().add(txtStockFinal);		

		
	    btnClean= new JButton("LABEL_CLEAN");
	    btnClean.setBounds(560, 140, 100, 40);

	    getContentPane().add(btnClean);	    
	    btnClean.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		resetForm();
    	}
	    });

	    btnSearch= new JButton("LABEL_SEARCH");
	    btnSearch.setBounds(700, 140, 100, 40);

	    getContentPane().add(btnSearch);	    
	    btnSearch.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		search();
    	}
	    });
	    
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
		setTitle(Managers.i18n.getTranslation("LABEL_CHECK_STOCK"));
	}
	
	

	private void drawTable() 
	{
		
		table= new STTable();
		table.showDeleteButton= false;
		table.setBounds(25, 100, 780, 300);
		table.setVisible(true);
		
		table.addColumn("productId", 0, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_TALLER"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_ESTOC"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_ESTOC_MINIM"), null, false, false);

		table.drawTable();
	    getContentPane().add(table);	

		    	    
	}
	
	private void search() {
		
		if (isValidSearch()) 
		{
			this.table.removeRows();		
			
			Integer grupId= this.selectProductControl.grupId;
			Integer subGrupId= this.selectProductControl.subGrupId;
			String producteId= this.selectProductControl.producteId;			
			String localId= (String) ((ComboBoxItem)  cmbLocal.getSelectedItem()).getId();
			Integer stockInicial= null;
			Integer stockFinal=null;
				
			if (this.txtStockInicial.getText().length() > 0 )
				stockInicial= Integer.parseInt(this.txtStockInicial.getText());
			if (this.txtStockFinal.getText().length() > 0 )
				stockFinal= Integer.parseInt(this.txtStockFinal.getText());
				
			try {
				
				//_clientManager.getRMIInterface().AddAlbara(albara);
				ProducteManager producteManager = new ProducteManager (new DatabaseManager());	
				List<Producte> list= producteManager.StockSearch(grupId, subGrupId, producteId, localId, stockInicial, stockFinal);
				
				AdRowsToTable(list);
			} 
			catch (Exception e) {
				Managers.exception.showException(new STException(e, e.getMessage()));
			}
		}
	}	
	
	private boolean isValidSearch(){	
		if (this.txtStockInicial.getText().trim().length() > 0 && ! Methods.isPositiveInt(this.txtStockInicial.getText()))
		{
		   try {
			    this.txtStockInicial.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INVALID_QUANTITY"), Enums.MessageType.Warning);
			   } catch (Exception e1) {
		   }			  
		   return false;
		}
		if (this.txtStockFinal.getText().trim().length() > 0 && ! Methods.isPositiveInt(this.txtStockFinal.getText()))
		{
		   try {
			   this.txtStockFinal.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INVALID_QUANTITY"), Enums.MessageType.Warning);
			   } catch (Exception e1) {
		   }			  
		   return false;
		}
		return true;		
	}
	
	private void AdRowsToTable(List<Producte> list) 
	{			
		DefaultTableModel model= (DefaultTableModel) this.table.getModel();
		
		if (list.size() > 0)
		{
			//Afegeix procuctes a taula
			for (Producte producte: list)
			{		
				model.addRow(new Object[] { producte.getIdProducte(),
											producte.getLocal().getNomLocal(), 
											producte.getNomProducte(), 
											producte.getExistencies().getEstoc(), 
											producte.getExistencies().getEstocMinim()
									       });
			}
		}
		else 
		{
			model.addRow(new Object[] { 0,
					Managers.i18n.getTranslation("LABEL_NO_TROVAT"),
					"", 
					"", 
			       });		
		}
				
	}
	

	private void resetForm() {		
		selectProductControl.resetFields();
		cmbLocal.setSelectedIndex(0);
		this.txtStockFinal.setText("");
		this.txtStockInicial.setText("");
		this.table.removeRows();					
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
