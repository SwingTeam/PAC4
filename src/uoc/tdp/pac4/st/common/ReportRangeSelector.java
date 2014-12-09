package uoc.tdp.pac4.st.common;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.JButton;

import java.awt.Font;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.common.dto.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/***
 * Classe base per a tots els formularis de
 * selecció de rang de valors pels informes.
 * 
 * @author Swing Team - 2014
 *
 */
public class ReportRangeSelector extends STFrame {

	private static final long serialVersionUID = 8652477805992029593L;
	private JComboBox<ComboBoxItem> cmbSDay = null;
	private JComboBox<ComboBoxItem> cmbSMonth = null;
	private JComboBox<ComboBoxItem> cmbSYear = null;
	private JComboBox<ComboBoxItem> cmbEDay = null;
	private JComboBox<ComboBoxItem> cmbEMonth = null;
	private JComboBox<ComboBoxItem> cmbEYear = null;
	private JComboBox<ComboBoxItem> cmbOffice = null;
	private JComboBox<ComboBoxItem> cmbOrder = null;
	private JTree treeProduct = null;

	/***
	 * Constructor
	 */
	public ReportRangeSelector(){
		this(null);
	}
	
	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public ReportRangeSelector(ClientManager<ETallerStocksInterface> clientManager) {
		super(clientManager);
		setContentPane(contentPane);
		setResizable(false);
		setBounds(100, 100, 616, 624);
		
		JLabel lblNewLabel = new JLabel("LABEL_START_DATE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(12, 60, 214, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblLabelday = new JLabel("LABEL_DAY");
		lblLabelday.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelday.setBounds(256, 31, 91, 15);
		contentPane.add(lblLabelday);
		
		JLabel lblLabelenddate = new JLabel("LABEL_END_DATE");
		lblLabelenddate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLabelenddate.setBounds(12, 92, 214, 15);
		contentPane.add(lblLabelenddate);
		
		JLabel lblLabelmonth = new JLabel("LABEL_MONTH");
		lblLabelmonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelmonth.setBounds(359, 31, 132, 15);
		contentPane.add(lblLabelmonth);
		
		JLabel lblLabelyear = new JLabel("LABEL_YEAR");
		lblLabelyear.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabelyear.setBounds(503, 31, 91, 15);
		contentPane.add(lblLabelyear);
		
		JLabel lblLabelselectlocal = new JLabel("LABEL_SELECT_ESTAB");
		lblLabelselectlocal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLabelselectlocal.setBounds(12, 137, 214, 15);
		contentPane.add(lblLabelselectlocal);
		
		JLabel lblLabelreportorder = new JLabel("LABEL_REPORT_ORDER");
		lblLabelreportorder.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLabelreportorder.setBounds(12, 173, 214, 15);
		contentPane.add(lblLabelreportorder);
		
		JLabel lblLabelselectproducts = new JLabel("LABEL_SELECT_PRODUCTS");
		lblLabelselectproducts.setBounds(22, 212, 562, 15);
		contentPane.add(lblLabelselectproducts);
		
		cmbSDay = new JComboBox<ComboBoxItem>();
		cmbSDay.setBackground(Color.WHITE);
		cmbSDay.setBounds(256, 55, 81, 24);
		contentPane.add(cmbSDay);
		
		cmbEDay = new JComboBox<ComboBoxItem>();
		cmbEDay.setBackground(Color.WHITE);
		cmbEDay.setBounds(256, 87, 81, 24);
		contentPane.add(cmbEDay);
		
		cmbSMonth = new JComboBox<ComboBoxItem>();
		cmbSMonth.setBackground(Color.WHITE);
		cmbSMonth.setBounds(359, 55, 125, 24);
		contentPane.add(cmbSMonth);
		
		cmbEMonth = new JComboBox<ComboBoxItem>();
		cmbEMonth.setBackground(Color.WHITE);
		cmbEMonth.setBounds(359, 87, 125, 24);
		contentPane.add(cmbEMonth);
		
		cmbSYear = new JComboBox<ComboBoxItem>();
		cmbSYear.setBackground(Color.WHITE);
		cmbSYear.setBounds(503, 55, 81, 24);
		contentPane.add(cmbSYear);
		
		cmbEYear = new JComboBox<ComboBoxItem>();
		cmbEYear.setBackground(Color.WHITE);
		cmbEYear.setBounds(503, 87, 81, 24);
		contentPane.add(cmbEYear);
		
		cmbOffice = new JComboBox<ComboBoxItem>();
		cmbOffice.setBackground(Color.WHITE);
		cmbOffice.setBounds(256, 132, 328, 24);
		contentPane.add(cmbOffice);
		
		cmbOrder = new JComboBox<ComboBoxItem>();
		cmbOrder.setBackground(Color.WHITE);
		cmbOrder.setBounds(256, 168, 328, 24);
		contentPane.add(cmbOrder);
		
		JButton btnBack = new JButton("BUTTON_BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnBack.setBounds(42, 539, 147, 25);
		contentPane.add(btnBack);
		
		JButton btnAccept = new JButton("BUTTON_ACCEPT_REPORT");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createReport();
			}
		});
		btnAccept.setBounds(302, 539, 269, 25);
		contentPane.add(btnAccept);
	}
	
	private void createReport(){
		//Recuperem els valor que passarem al ReportManager
		//Data d'inici
		Date inici = Methods.createDate((int) ((ComboBoxItem)this.cmbSDay.getSelectedItem()).getId(), 
										(int) ((ComboBoxItem) this.cmbSMonth.getSelectedItem()).getId(), 
										(int) ((ComboBoxItem) this.cmbSYear.getSelectedItem()).getId());
		//Data de finalització
		Date fi = Methods.createDate((int) ((ComboBoxItem)this.cmbEDay.getSelectedItem()).getId(), 
				(int) ((ComboBoxItem) this.cmbEMonth.getSelectedItem()).getId(), 
				(int) ((ComboBoxItem) this.cmbEYear.getSelectedItem()).getId());
		//Local
		String estabId = ((ComboBoxItem)this.cmbOffice.getSelectedItem()).getId().toString();
		String estabName = ((ComboBoxItem)this.cmbOffice.getSelectedItem()).getDescription();
		//Ordre
		String order = ((ComboBoxItem) this.cmbOrder.getSelectedItem()).getId().toString();
		//Grups, subgrups i productes seleccionats
		List<STTreeNode> products = new ArrayList<STTreeNode>();
	    TreePath[] treePaths = treeProduct.getSelectionPaths();
	    if (treePaths != null){
		    for(TreePath item : treePaths){
		    	STTreeNode node = (STTreeNode) ((DefaultMutableTreeNode)item.getLastPathComponent()).getUserObject();
		    	products.add(node);
		    }
	    }
	    try{
		    //Comprovem dades
		    String errors = "";
		    if (inici == null){
		    	errors += Managers.i18n.getTranslation(TokenKeys.ERROR_START_DATE);
		    }
		    if (fi == null){
		    	errors += Managers.i18n.getTranslation(TokenKeys.ERROR_END_DATE);
		    }
		    if (estabId == null || estabId.isEmpty()){
		    	errors += Managers.i18n.getTranslation(TokenKeys.ERROR_NO_ESTABLISHMENT);
		    }
		    if (order == null || order.isEmpty()){
		    	errors += Managers.i18n.getTranslation(TokenKeys.ERROR_NO_ORDER);
		    }
		    if (products.size() == 0){
		    	errors += Managers.i18n.getTranslation(TokenKeys.ERROR_NO_PRODUCTS);
		    }
		    if (!errors.isEmpty()){
		    	Methods.showMessage(errors, Enums.MessageType.Warning);
		    } else{
		    	this.launchReport(new ReportSelectorData(inici, fi, estabId, estabName, order, products));
		    }
	    } catch(Exception e){
	    	Managers.exception.showException(new STException(e));
	    }
	}
	
	@Override
	protected void initializeFrame(){
		//**************************** Càrrega del contingut del controls *************************************
		this.loadDays(this.cmbSDay);
		this.loadDays(this.cmbEDay);
		this.loadMonths(this.cmbSMonth);
		this.loadMonths(this.cmbEMonth);
		this.loadYears(this.cmbSYear);
		this.loadYears(this.cmbEYear);
		this.loadOffices(this.cmbOffice);
		this.loadOrders(this.cmbOrder);
		
		//Crea l'arbre
		DefaultMutableTreeNode rootTree = loadTree();
		treeProduct = new JTree(rootTree);
		treeProduct.setFont(new Font("Dialog", Font.PLAIN, 14));
		treeProduct.setBounds(22, 233, 572, 294);
		contentPane.add(treeProduct);
		//Tipus de selecció de l'arbre
	    treeProduct.getSelectionModel().setSelectionMode
	            (TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		//*****************************************************************************************************
	}
	
	protected void launchReport(ReportSelectorData reportSelectorData){}
	
	private void loadDays(JComboBox<ComboBoxItem> comboBox){
		for(int i = 1; i <= 31; i++){
			ComboBoxItem item = new ComboBoxItem(i, new Integer(i).toString());
			comboBox.addItem(item);
			if (i == Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
				comboBox.setSelectedItem(item);
		}
	}
	
	private void loadMonths(JComboBox<ComboBoxItem> comboBox){
		for(int i = 1; i <= 12; i++){
			ComboBoxItem item 
				= new ComboBoxItem(i, Managers.i18n.getTranslation(TokenKeys.MONTH + (new Integer(i).toString())));
			comboBox.addItem(item);
			if (i - 1 == Calendar.getInstance().get(Calendar.MONTH))
				comboBox.setSelectedItem(item);
		}
	}

	private void loadOffices(JComboBox<ComboBoxItem> comboBox){
		try {
			List<Local> locals = this._clientManager.getRMIInterface().getEstablishmentList(null);
			
			if (locals != null){
				for (Local item : locals){
					comboBox.addItem(new ComboBoxItem(item.getTaxId(), item.getName()));
				}
			}
		} catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_GETTING_DATA));
		}
	}
	
	private void loadOrders(JComboBox<ComboBoxItem> comboBox){
		for(Enums.ReportOrder enumItem: Enums.ReportOrder.values()){
			ComboBoxItem item = new ComboBoxItem(enumItem.name(), 
					Managers.i18n.getTranslation(Constants.ENUM_PREFIX + enumItem.name().toUpperCase()));
			comboBox.addItem(item);
		}
	}
	
	private DefaultMutableTreeNode loadTree(){
		//Root (Recanvis)
		DefaultMutableTreeNode root
	        = new DefaultMutableTreeNode(new STTreeNode(0, Managers.i18n.getTranslation(TokenKeys.REPORT_TREE_ROOT), Enums.NodeType.Root));
		try{
		    //Recuperem la llista de grupos, subgrupos i productes
			List<ProducteReport> products = this._clientManager.getRMIInterface().getProductList(null);
			
			String groupName = null;
			String subgroupName = null;
			DefaultMutableTreeNode group = null;
			DefaultMutableTreeNode subgroup = null;
			for(ProducteReport item : products){
				if (item.getGroupName() != groupName){
					group = new DefaultMutableTreeNode(new STTreeNode(item.getGroupId(), item.getGroupName(), Enums.NodeType.Group));
					root.add(group);
				}
				if (item.getSubgroupName() != subgroupName){
					subgroup = new DefaultMutableTreeNode(new STTreeNode(item.getSubgroupId(), item.getSubgroupName(), Enums.NodeType.Subgroup));
					group.add(subgroup);
				}
				groupName = item.getGroupName();
				subgroupName = item.getSubgroupName();
				subgroup.add(new DefaultMutableTreeNode(new STTreeNode(item.getIdProducte(), item.getNomProducte(), Enums.NodeType.Product)));
			}
		} catch (STException e) {
			Managers.exception.showException(e);
		} catch(RemoteException | NullPointerException e){
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_GETTING_DATA));
		} catch (Exception e){
			Managers.exception.showException(new STException(e));;
		}
		return root;
	}
	
	private void loadYears(JComboBox<ComboBoxItem> comboBox){
		for(int i = Constants.REPORT_YEAR_PERIOD; i >= 0; i--){
			Integer year = new Integer(Calendar.getInstance().get(Calendar.YEAR) - i);
			ComboBoxItem item = new ComboBoxItem(year, year.toString());
			comboBox.addItem(item);
			if (i == 0)
				comboBox.setSelectedItem(item);
		}
	}
}
