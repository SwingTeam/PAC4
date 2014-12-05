package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

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
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.Methods;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class ReceivingPieces extends JFrame {
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;
	
	private JComboBox<ComboBoxItem>  cmbProveidor = null;	
	private JComboBox<ComboBoxItem>  cmbGrup = null;
	private JComboBox<ComboBoxItem>  cmbSubGrup = null;
	private JComboBox<ComboBoxItem>  cmbProducte = null;
	private JTextField txtQuantitat = null;
	
	private JPanel pnlLinAlbara = null;
	
	private JTable tblLinAlbara= null;
	
	private JButton btnSave = new JButton("SAVE");
    private JButton btnCancel = new JButton("CANCEL");

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
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Situem la finestra
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    getContentPane().setLayout(null);
			    
		JLabel lblProveidor = new JLabel("LABEL_PROVEIDOR");
		lblProveidor.setBounds(75, 50, 100, 20);
		getContentPane().add(lblProveidor);
				
	    cmbProveidor = new JComboBox<ComboBoxItem>();
	    fillCmbProveidor(cmbProveidor);
	    cmbProveidor.setBounds(175, 50, 150, 20);
	    cmbProveidor.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					String proveidorId= (String) ((ComboBoxItem) cmbProveidor.getSelectedItem()).getId();					
					Integer grupId = (Integer) ((ComboBoxItem) cmbGrup.getSelectedItem()).getId();
					Integer subGrupId = (Integer) ((ComboBoxItem) cmbSubGrup.getSelectedItem()).getId();
					
					fillCmbProducte(cmbProducte, proveidorId, grupId ,subGrupId);
					
				}
		    }
		});				
	    
	    getContentPane().add(cmbProveidor);
	    

		JLabel lblGrup = new JLabel("LABEL_GRUP");
		lblGrup.setBounds(75, 100, 100, 20);
		getContentPane().add(lblGrup);
		
	    cmbGrup = new JComboBox<ComboBoxItem>();
	    fillCmbGrup(cmbGrup );
	    cmbGrup.setBounds(175, 100, 150, 20);
	    cmbGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					String proveidorId= (String) ((ComboBoxItem) cmbProveidor.getSelectedItem()).getId();					
					Integer grupId = (Integer) ((ComboBoxItem) cmbGrup.getSelectedItem()).getId();
					Integer subGrupId = (Integer) ((ComboBoxItem) cmbSubGrup.getSelectedItem()).getId();
					
					fillCmbSubGrup(cmbSubGrup, grupId);
					fillCmbProducte(cmbProducte, proveidorId, grupId ,subGrupId);
					
				}
		    }
		});			    
	    getContentPane().add(cmbGrup);

	    
		JLabel lblSubGrup = new JLabel("LABEL_SUBGRUP");
		lblSubGrup.setBounds(75, 150, 100, 20);
		getContentPane().add(lblSubGrup);
	    
		cmbSubGrup = new JComboBox<ComboBoxItem>();
		fillCmbSubGrup(cmbSubGrup , null);
		cmbSubGrup.setBounds(175, 150, 150, 20);
		cmbSubGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					String proveidorId= (String) ((ComboBoxItem) cmbProveidor.getSelectedItem()).getId();					
					Integer grupId = (Integer) ((ComboBoxItem) cmbGrup.getSelectedItem()).getId();
					Integer subGrupId = (Integer) ((ComboBoxItem) cmbSubGrup.getSelectedItem()).getId();
					
					fillCmbProducte(cmbProducte, proveidorId, grupId ,subGrupId);
					
				}
		    }
		});			    
		
	    getContentPane().add(cmbSubGrup);
		

		JLabel lblProducte = new JLabel("LABEL_PRODUCTE");
		lblProducte.setBounds(75, 200, 100, 20);
		getContentPane().add(lblProducte);
	    
		cmbProducte = new JComboBox<ComboBoxItem>();
		fillCmbProducte(cmbProducte, null,null,null);
		cmbProducte.setBounds(175, 200, 350, 20);			    	
	    getContentPane().add(cmbProducte);
	    	    
	    txtQuantitat= new JTextField();
	    txtQuantitat.setBounds(175, 200, 350, 20);			    	
	    getContentPane().add(txtQuantitat);
	    
	    
	    
	    JButton btnAdd = new JButton("LABEL_AFEGIR");
	    btnAdd.setBounds(550, 200, 100, 25);
		this.add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
	        	if (IsValidLiniaAlbara())
	        	{	        		
	        		AddProducteToAlbara();		        	
	        	}
	        }
	      });
	    
	    
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    			
	    	}
	    });
	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    			
    	}
	    });
	    
	    
	    pnlLinAlbara= new JPanel();
	    pnlLinAlbara.setLayout(null);
	    pnlLinAlbara.setBounds(75, 250, 500, 200);
		getContentPane().add(pnlLinAlbara);
		
		DrawTableAlbara(pnlLinAlbara);

	    
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
	}

	/***
	 * Omple un ComboBox amb la llista de proveidors.
	 * 
	 */
	private void fillCmbProveidor(JComboBox<ComboBoxItem> cmbBoxItem ) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Proveidor> list = this._clientManager.getRMIInterface().listProveidors();
			
			Iterator<Proveidor> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				Proveidor proveidor= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(proveidor.getIdProveidor(), proveidor.getNomProveidor()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}		
	}
	
	/***
	 * Omple un ComboBox amb la llista de grups.
	 * 
	 */
	private void fillCmbGrup(JComboBox<ComboBoxItem> cmbBoxItem) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Grup> list = this._clientManager.getRMIInterface().listGrups();
			
			Iterator<Grup> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				Grup grup= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(grup.getIdGrup(), grup.getNom()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}		
	}
	
	/***
	 * Omple un ComboBox amb la llista de subgrups.
	 * 
	 */
	private void fillCmbSubGrup(JComboBox<ComboBoxItem> cmbBoxItem , Integer grupId) {
		
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<SubGrup> list = this._clientManager.getRMIInterface().getSubGrupsByGrup(grupId);
			
			Iterator<SubGrup> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				SubGrup subGrup= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(subGrup.getIdSubGrup(), subGrup.getNom()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}
	}
	
	/***
	 * Omple un ComboBox amb la llista de productes
	 * 
	 */
	private void fillCmbProducte(JComboBox<ComboBoxItem> cmbBoxItem, String proveidorId, Integer grupId, Integer subGrupId) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Producte> list = this._clientManager.getRMIInterface().SearchProdutes(proveidorId, grupId, subGrupId);
			
			Iterator<Producte> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				Producte producte= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(producte.getIdProducte(), producte.getNomProducte()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
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
	

	private void DrawTableAlbara(JPanel panel) 
	{
		DefaultTableModel model = new DefaultTableModel(); 		
		tblLinAlbara= new JTable(model);
		// Create a couple of columns 
		model.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE")); 
		model.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT")); 
				
		JScrollPane scrollPane = new JScrollPane(tblLinAlbara);
  
		tblLinAlbara.setFillsViewportHeight(true);
		scrollPane.setBounds(0, 0, 500, 200);
	  
		panel.removeAll();
		panel.add(scrollPane);
	    	    
	}

	public boolean IsValidLiniaAlbara() {
		return true;
	}
	
	private void AddProducteToAlbara() 
	{
		String producte= ((ComboBoxItem) cmbProducte.getSelectedItem()).getDescription();
			
		DefaultTableModel model = (DefaultTableModel) tblLinAlbara.getModel();
		
		model.addRow(new Object[]{producte, 1});		
	}
}
