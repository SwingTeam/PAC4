package uoc.tdp.pac4.st.client.cf;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.managers.AlbaraManager;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.ExceptionManager;
import uoc.tdp.pac4.st.common.managers.I18nManager;
import uoc.tdp.pac4.st.common.managers.MovimentManager;
import uoc.tdp.pac4.st.common.managers.SettingManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class ReceivingPieces extends JFrame {
	private static final long serialVersionUID = -3598083467773963566L;

	private ClientManager<ETallerStocksInterface> _clientManager = null;

	private JPanel contentPane;
	static final int xOffset = 30, yOffset = 30;		
	static final int openFrameCount = 0;

	private JComboBox<ComboBoxItem>  cmbProveidor = null;
	private JTextField txtAlbaraProveidor = null;
	private JTextField txtDataAlbara = null;
	
	private SelectProductControl selectProductControl = null;
	private JButton btnAdd; 
	private JButton btnSave;
	private JButton btnCancel;
	
	private JPanel pnlLinAlbara = null;
	
	private JTable tblLinAlbara= null;


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
		lblProveidor.setBounds(50, 20, 80, 20);
		add(lblProveidor);

	    cmbProveidor = new JComboBox<ComboBoxItem>();
	    fillCmbProveidor(cmbProveidor);
	    cmbProveidor.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					selectProductControl.setEnabled(true);
					btnAdd.setEnabled(true); 
				}
		    }
		});			    
	    cmbProveidor.setBounds(120, 20, 200, 20);
	    add(cmbProveidor);

		JLabel lblAlbaraProveidor = new JLabel("LABEL_ALBARA_PROVEIDOR");
		lblAlbaraProveidor.setBounds(50, 60, 150, 20);
		add(lblAlbaraProveidor);

	    txtAlbaraProveidor = new JTextField();
	    txtAlbaraProveidor.setBounds(200, 60, 150, 20);
	    add(txtAlbaraProveidor);

		JLabel lblDataAlbara= new JLabel("LABEL_DATA_ALBARA");
		lblDataAlbara.setBounds(370, 60, 100, 20);
		add(lblDataAlbara);

	    this.txtDataAlbara = new JTextField();
	    txtDataAlbara.setBounds(470, 60, 100, 20);
	    this.txtDataAlbara.setText(Methods.formatDate(new Date()));
	    add(txtDataAlbara);
	    
	    
	    //Select product control
	    selectProductControl= new SelectProductControl(this._clientManager, (String) ((ComboBoxItem) this.cmbProveidor.getSelectedItem()).getId()); 
	    selectProductControl.setBounds(50, 50, 600, 100);
	    selectProductControl.setVisible(true);	   
	    selectProductControl.setEnabled(false);
	    getContentPane().add(selectProductControl);
	      	    
	    btnAdd = new JButton("LABEL_AFEGIR");
	    btnAdd.setBounds(660, 120, 100, 25);
	    getContentPane().add(btnAdd);		
		btnAdd.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent paramAnonymousActionEvent) {
	        	if (selectProductControl.isValidLine())
	        	{	        		
	        		AdRowToTable();        	
	        	}
	        }
	      });

		
	    btnCancel = new JButton("LABEL_CANCEL");
	    btnCancel.setBounds(100, 450, 100, 40);

	    getContentPane().add(btnCancel);	    
	    btnCancel.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		Cancel();
    	}
	    });

		btnSave = new JButton("LABEL_SAVE");
		btnSave .setBounds(600, 450, 100, 40);	    	    
	    getContentPane().add(btnSave);		    
	    btnSave.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if (isValidForm())
	        	{	        		
	        		Save();        	
	        	}
	    	}
	    });

	    
	    
	    pnlLinAlbara= new JPanel();
	    pnlLinAlbara.setLayout(null);
	    pnlLinAlbara.setBounds(50, 200, 600, 200);
		getContentPane().add(pnlLinAlbara);
		
		DrawTable(pnlLinAlbara);
	    
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
			
			List<Proveidor> list = _clientManager.getRMIInterface().listProveidors();
			
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
	

	private void DrawTable(JPanel panel) 
	{
		
		//Crea un table model no editable 
		DefaultTableModel model = new DefaultTableModel()  {
			@Override
		    public boolean isCellEditable(int row, int column) {
				if(column != 3)
					return false;
				else 
					return true;
		    }
		};
		
		
		tblLinAlbara= new JTable(model); 
					
		model.addColumn("productId"); //0
		model.addColumn(Managers.i18n.getTranslation("LABEL_PRODUCTE")); //1 
		model.addColumn(Managers.i18n.getTranslation("LABEL_QUANTITAT")); //2
		model.addColumn("");
		
		JScrollPane scrollPane = new JScrollPane(tblLinAlbara);
  
		tblLinAlbara.setFillsViewportHeight(true);
		scrollPane.setBounds(0, 0, 600, 200);
	  
		panel.removeAll();
		panel.add(scrollPane);
		
		//delete button
		Action delete = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(tblLinAlbara, delete, 3);
		tblLinAlbara.getColumnModel().getColumn(0).setMinWidth(0);
		tblLinAlbara.getColumnModel().getColumn(0).setMaxWidth(0);	
		
		tblLinAlbara.getColumnModel().getColumn(2).setMaxWidth(150);
		tblLinAlbara.getColumnModel().getColumn(3).setMaxWidth(50);
		    	    
	}
	
	private void AdRowToTable() 
	{
	
		String producteId= selectProductControl.producteId;
		String nomProducte= selectProductControl.nomProducte;
		Integer quantitat= selectProductControl.quantitat;
		
		DefaultTableModel model= (DefaultTableModel) this.tblLinAlbara.getModel();
		
		//comprovar producte no s'ha afegit abans
		for(int row = 0;row < tblLinAlbara.getRowCount();row++) {
	        String linProducteId= (String) tblLinAlbara.getValueAt(row, 0);
	        if (linProducteId == producteId)
	        {
	        	int currentValue=  (int) tblLinAlbara.getValueAt(row, 2);
	        	tblLinAlbara.setValueAt(quantitat + currentValue, row, 2);
	        	return ;
	        }
	        
		}
		
		model.addRow(new Object[] { producteId, nomProducte, quantitat, "X"});
		this.btnSave.setEnabled(true);			
	}
	

	private void Cancel() {
		if (isValidForm()) 
		{
			selectProductControl.resetFields();
						
			
			DefaultTableModel defaultTableModel = (DefaultTableModel) tblLinAlbara.getModel();
			int rowCount = defaultTableModel .getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				defaultTableModel .removeRow(i);
			}
		}
	}
	
	private void Save() {
		if (isValidForm()) 
		{
			Albara albara= new Albara();
			albara.setCodialbaraextern(this.txtAlbaraProveidor.getText());
			albara.setComAlbara("");
			albara.setDataAlbara(Methods.getDate(this.txtDataAlbara.getText()));
			albara.setOrigenId( (String) ((ComboBoxItem)  this.cmbProveidor.getSelectedItem()).getId());
			albara.setDestiId("");
			albara.setLiniesAlbara(getLinAlbara());
			albara.setTipusMovimentId(MovimentManager.TIPUS_MOVIMENT_ENTRADA);

			try {
				//_clientManager.getRMIInterface().AddAlbara(albara);
				DatabaseManager d = new DatabaseManager();
				AlbaraManager a = new AlbaraManager(d);
				a.Add(albara);
				
			} catch (STException e) {
				Managers.exception.showException(new STException(e, "ERROR_SAVING_ALBARA"));
			}
		}
	}
	
	private ArrayList<LinAlbara> getLinAlbara() 
	{
		ArrayList<LinAlbara> linees= new ArrayList<LinAlbara>();
		
		DefaultTableModel defaultTableModel = (DefaultTableModel) tblLinAlbara.getModel();
		int rowCount = defaultTableModel.getRowCount();
		for (int i = rowCount - 1; i >= 0; i--) {
			LinAlbara linAlbara= new LinAlbara(); 
			linAlbara.setProducteId((String) tblLinAlbara.getValueAt(i, 0));
			linAlbara.setUnitats((Integer) tblLinAlbara.getValueAt(i, 2));
			
			linees.add(linAlbara);
		}
		
		
		return linees;
	}

	
	private boolean isValidForm() {
		if (this.txtAlbaraProveidor.getText().trim().length() == 0 )
		{
			return false;
		}
		if (! Methods.isValidDate(this.txtDataAlbara.getText()))
		{
			return false;
		}
		
		
		return true;
	}

}
