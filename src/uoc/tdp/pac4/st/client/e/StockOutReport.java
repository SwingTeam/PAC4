package uoc.tdp.pac4.st.client.e;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JTree;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.common.dto.*;

public class StockOutReport extends JFrame {

	private static final long serialVersionUID = 8652477805992029593L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;

	/**
	 * Create the frame.
	 */
	public StockOutReport(ClientManager<ETallerStocksInterface> clientManager) {
		this._clientManager = clientManager;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 624);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblLabelselectlocal = new JLabel("LABEL_SELECT_LOCAL");
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
		
		JComboBox<ComboBoxItem> cmbSDay = new JComboBox<ComboBoxItem>();
		cmbSDay.setBounds(256, 55, 81, 24);
		contentPane.add(cmbSDay);
		
		JComboBox<ComboBoxItem> cmbEDay = new JComboBox<ComboBoxItem>();
		cmbEDay.setBounds(256, 87, 81, 24);
		contentPane.add(cmbEDay);
		
		JComboBox<ComboBoxItem> cmbSMonth = new JComboBox<ComboBoxItem>();
		cmbSMonth.setBounds(359, 55, 125, 24);
		contentPane.add(cmbSMonth);
		
		JComboBox<ComboBoxItem> cmbEMonth = new JComboBox<ComboBoxItem>();
		cmbEMonth.setBounds(359, 87, 125, 24);
		contentPane.add(cmbEMonth);
		
		JComboBox<ComboBoxItem> cmbSYear = new JComboBox<ComboBoxItem>();
		cmbSYear.setBounds(503, 55, 81, 24);
		contentPane.add(cmbSYear);
		
		JComboBox<ComboBoxItem> cmbEYear = new JComboBox<ComboBoxItem>();
		cmbEYear.setBounds(503, 87, 81, 24);
		contentPane.add(cmbEYear);
		
		JComboBox<ComboBoxItem> cmbOffice = new JComboBox<ComboBoxItem>();
		cmbOffice.setBounds(256, 132, 328, 24);
		contentPane.add(cmbOffice);
		
		JComboBox<ComboBoxItem> cmbOrder = new JComboBox<ComboBoxItem>();
		cmbOrder.setBounds(256, 168, 328, 24);
		contentPane.add(cmbOrder);
		
		JTree treeProduct = new JTree();
		treeProduct.setFont(new Font("Dialog", Font.PLAIN, 14));
		treeProduct.setBounds(22, 233, 562, 294);
		contentPane.add(treeProduct);
		
		JButton btnBack = new JButton("BUTTON_BACK");
		btnBack.setBounds(42, 539, 147, 25);
		contentPane.add(btnBack);
		
		JButton btnAccept = new JButton("BUTTON_ACCEPT_REPORT");
		btnAccept.setBounds(302, 539, 269, 25);
		contentPane.add(btnAccept);
		
		//**************************** Càrrega del contingut del controls *************************************
		this.loadDays(cmbSDay);
		this.loadDays(cmbEDay);
		this.loadMonths(cmbSMonth);
		this.loadMonths(cmbEMonth);
		this.loadYears(cmbSYear);
		this.loadYears(cmbEYear);
		this.loadOffices(cmbOffice);
		this.loadOrders(cmbOrder);
		this.loadTree(treeProduct);
		
	}
	
	private void loadDays(JComboBox<ComboBoxItem> comboBox){
		for(int i = 1; i <= 31; i++){
			ComboBoxItem item = new ComboBoxItem(i, new Integer(i).toString());
			comboBox.addItem(item);
		}
	}
	
	private void loadMonths(JComboBox<ComboBoxItem> comboBox){
		for(int i = 1; i <= 12; i++){
			ComboBoxItem item 
				= new ComboBoxItem(i, Managers.i18n.getTranslation(TokenKeys.MONTH + (new Integer(i).toString())));
			comboBox.addItem(item);
		}
	}

	private void loadOffices(JComboBox<ComboBoxItem> comboBox){
		try {
			if (this._clientManager == null)
				this._clientManager = new ClientManager<ETallerStocksInterface>();
			
			this._clientManager.startConnection();
			List<Local> locals = this._clientManager.getRMIInterface().getEstablishmentList(null);
			
			if (locals != null){
				for (Local item : locals){
					comboBox.addItem(new ComboBoxItem(item.getTaxId(), item.getName()));
				}
			}
		} catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		
		}finally{
			this._clientManager.stopConnection();
		}
	}
	
	private void loadOrders(JComboBox<ComboBoxItem> comboBox){
		ComboBoxItem ascendent = new ComboBoxItem("ASC", Managers.i18n.getTranslation(TokenKeys.ORDER_ASCENDANT));
		ComboBoxItem descendent = new ComboBoxItem("DESC", Managers.i18n.getTranslation(TokenKeys.ORDER_DESCENDANT));
		comboBox.addItem(ascendent);
		comboBox.addItem(descendent);
	}
	
	private void loadTree(JTree tree){
		//TODO -> Finish this method
	}
	
	private void loadYears(JComboBox<ComboBoxItem> comboBox){
		for(int i = 15; i >= 0; i--){
			ComboBoxItem item = new ComboBoxItem(i, new Integer(Calendar.getInstance().get(Calendar.YEAR) - i).toString());
			comboBox.addItem(item);
		}
	}

}
