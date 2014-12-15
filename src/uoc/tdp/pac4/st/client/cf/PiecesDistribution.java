package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.Moviment;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.DistribucioManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.common.ui.ComboBoxHelper;
import uoc.tdp.pac4.st.common.ui.LabelSubTitle;
import uoc.tdp.pac4.st.common.ui.LabelTitle;
import uoc.tdp.pac4.st.common.ui.STTable;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class PiecesDistribution extends JFrame {

	private static final long serialVersionUID = -3598083467773963566L;
	
	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private JComboBox<ComboBoxItem>  cmbCriteriBase = null;
	private JComboBox<ComboBoxItem>  cmbLocal = null;

	private JButton btnSave;
	private JButton btnCancel;
	
	private STTable table= null;
	
	private static final int COLUMN_LINALBARA_ID= 0;
	private static final int COLUMN_PRODUCTE_ID= 1;
	private static final int COLUMN_ALBARA_ID= 2;
	private static final int COLUMN_ORIGEN_ID= 3;
	private static final int COLUMN_TALLER= 4;
	private static final int COLUMN_PRODUCTE= 5;
	private static final int COLUMN_QUANTITAT= 6;
	private static final int COLUMN_ESTOC= 7;
	private static final int COLUMN_ENVIAR= 8;
		
	private Usuari usuari; 

	/**
	 * Create the frame.
	 */
	public PiecesDistribution(Usuari _usuari) {
		usuari= _usuari;
		startConnection();
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 850, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Situem la finestra
	    setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	    getContentPane().setLayout(null);
	    
    	LabelTitle lblTitle = new LabelTitle("LABEL_DISTRIBUCIO_PECES");
    	lblTitle.setBounds(10, 10, 400, 20);
		getContentPane().add(lblTitle);

		LabelSubTitle lbCriteriBase = new LabelSubTitle("LABEL_CRITERI_DISTRUCIO");
    	lbCriteriBase.setBounds(50, 40, 400, 20);
		getContentPane().add(lbCriteriBase);
		
		
		JLabel lbEnfuncioDe = new JLabel("LABEL_EN_FUNCIO_DADES");
		lbEnfuncioDe .setBounds(50, 70, 150, 20);
		getContentPane().add(lbEnfuncioDe );
		

		cmbCriteriBase = new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbCriteriDistribucio(this._clientManager, cmbCriteriBase);
	    cmbCriteriBase.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					if (((ComboBoxItem)  cmbCriteriBase.getSelectedItem()).getId() != null) 
					{
						loadTable();
					}
					else 
					{
						resetForm();
					}
				}
		    }
		});			    
	    cmbCriteriBase.setBounds(210, 70, 200, 20);
	    getContentPane().add(cmbCriteriBase);

		JLabel lblAlbaraProveidor = new JLabel("LABEL_TALLER");
		lblAlbaraProveidor.setBounds(420, 70, 150, 20);
		getContentPane().add(lblAlbaraProveidor);


		cmbLocal= new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbLocal(this._clientManager, cmbLocal, usuari.getIdLocal());
		cmbLocal.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					if (((ComboBoxItem)  cmbLocal.getSelectedItem()).getId() != null) 
					{
						loadTable();
					}
					else 
					{
						resetForm();
					}					
				}
		    }
		});			    
		cmbLocal.setBounds(500, 70, 200, 20);
		getContentPane().add(cmbLocal);
		

		LabelSubTitle lbLlista = new LabelSubTitle("LABEL_LLISTA_DISTRIBUCIO");
		lbLlista.setBounds(50, 120, 400, 20);
		getContentPane().add(lbLlista);

		
		
		drawTable();
		
		
	    btnCancel = new JButton("LABEL_CANCEL");
	    btnCancel.setBounds(100, 380, 100, 40);

	    getContentPane().add(btnCancel);	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		resetForm();
    	}
	    });

		btnSave = new JButton("LABEL_DISTRIBUIR_PECES");
		btnSave.setBounds(600, 380, 150, 40);	    	    
	    getContentPane().add(btnSave);		    
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {	    		   	
        		save();        	
	    	}
	    });	   
	    
	    btnSave.setEnabled(false);

		
	    
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


	private void drawTable() 
	{		
		table= new STTable();
		table.showDeleteButton= true;
		table.setBounds(25, 80, 780, 300);
		table.setVisible(true);
		
		table.addColumn("linAlbaraId", 0, false, false);
		table.addColumn("producteId", 0, false, false);
		table.addColumn("albaraId", 0, false, false);		
		table.addColumn("origenId", 0, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_TALLER"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE"), null, false, false);
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT"), 100, true, true);
		table.addColumn(Managers.i18n.getTranslation("LABEL_ESTOC"), 100, false, false);		
		table.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT_ENVIAR"), null, true, true);
		
		table.deleteRow= new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        if (table.getRowCount() == 0) 
		        {
		        	btnSave.setEnabled(false);
		        }
		    }
		};	
		table.validateCell= new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		    	validateStock(e.getActionCommand());
		    }
		};	
			
		
		table.drawTable();
	    getContentPane().add(table);		
	}
	
	private void loadTable() 
	{
		String localOrigenId= null;
		
		try {
			if (((ComboBoxItem)  cmbLocal.getSelectedItem()).getId() != null)
				localOrigenId= ((ComboBoxItem)  cmbLocal.getSelectedItem()).getId().toString();
					
			table.removeRows();
			
			ArrayList<LinAlbara> list=null;
			if (((ComboBoxItem)  cmbCriteriBase.getSelectedItem()).getId() == DistribucioManager.DISTRIBUCIO_DEMANDA_ACTUAL) 
			{
				list= _clientManager.getRMIInterface().getByDemandaActual(usuari.getIdLocal(), localOrigenId);
			}
			else 
			{
				list= _clientManager.getRMIInterface().getByRupturaStock(usuari.getIdLocal(), localOrigenId);
			}
			
							
			for (LinAlbara linAlbara: list)
			{
				addRowToTable(linAlbara);
				
			}		
			
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, "ERROR_GETTING_DATA"));
		}				
	}
	
	private void validateStock(String input_quantitat) 
	{
		int editingRow= table.getEditingRow();
		Integer stock= Integer.parseInt(table.getValueAt(editingRow, PiecesDistribution.COLUMN_ESTOC).toString());
		Integer quantitat= Integer.parseInt(input_quantitat);
		
		if (quantitat > stock)
		{
			 try {
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_NO_ESTOC"), Enums.MessageType.Warning);
			} catch (Exception e) {
			}
			 throw new NumberFormatException(); //cancel cell editing
		}
	}
	
	private void addRowToTable(LinAlbara linAlbara) 
	{
		
		table.addRow(new Object[] { 
				linAlbara.getIdLiniaAlbara(),
				linAlbara.getProducte().getIdProducte(),
				linAlbara.getAlbaraId(),
				linAlbara.getLocal().getIdLocal(),
				linAlbara.getLocal().getNomLocal() ,				
				linAlbara.getProducte().getNomProducte(),
				linAlbara.getMoviment().getNumUnitatsOrdre() - linAlbara.getMoviment().getNumUnitSortides() , 
				linAlbara.getExistencies().getEstoc(),				
				"",
				"X"});	
		this.btnSave.setEnabled(true);			
	}
	

	private void resetForm() {
					
		this.cmbCriteriBase.setSelectedIndex(0);
		this.cmbLocal.setSelectedIndex(0);
		table.removeRows();
	}
	
	private void save() {
		try {
			
			ArrayList<LinAlbara> linees= getLinAlbara(); 
			
			_clientManager.getRMIInterface().Distribuir(usuari.getIdLocal(), linees);
					
			resetForm();
			
			Methods.showMessage( Managers.i18n.getTranslation("LABEL_PECES_DISTRIBUIDES"), Enums.MessageType.Info);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, "ERROR_SAVING_ALBARA"));
		}
	}
	
	private ArrayList<LinAlbara> getLinAlbara() 
	{
		ArrayList<LinAlbara> linees= new ArrayList<LinAlbara>();
		
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		int rowCount = defaultTableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			if (table.getValueAt(i, PiecesDistribution.COLUMN_ENVIAR).toString().trim().length() > 0)
			{
				LinAlbara linAlbara= new LinAlbara();
				
				linAlbara.setAlbaraId(Integer.parseInt(table.getValueAt(i, PiecesDistribution.COLUMN_ALBARA_ID).toString()));
				linAlbara.setIdLiniaAlbara( Integer.parseInt(table.getValueAt(i, PiecesDistribution.COLUMN_LINALBARA_ID).toString()));
				linAlbara.setProducteId(table.getValueAt(i, PiecesDistribution.COLUMN_PRODUCTE_ID).toString());
				
				Moviment moviment = new Moviment();
				moviment.setNumUnitatsOrdre(Integer.parseInt(table.getValueAt(i, PiecesDistribution.COLUMN_QUANTITAT).toString()));
				moviment.setNumUnitSortides(Integer.parseInt(table.getValueAt(i, PiecesDistribution.COLUMN_ENVIAR).toString()));
				
				LocalST local = new LocalST();
				local.setIdLocal(table.getValueAt(i, PiecesDistribution.COLUMN_ORIGEN_ID).toString());
							
				linAlbara.setLocal(local);
				linAlbara.setMoviment(moviment);
				
				linees.add(linAlbara);
			}
		}
		
		
		return linees;
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
