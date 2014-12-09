package uoc.tdp.pac4.st.common.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uoc.tdp.pac4.st.common.ComboBoxItem;
import uoc.tdp.pac4.st.common.Enums;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.Methods;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class SelectProductControl extends JPanel {
	
	private ClientManager<ETallerStocksInterface>  clientManager = null;
	
	private JComboBox<ComboBoxItem>  cmbGrup = null;
	private JComboBox<ComboBoxItem>  cmbSubGrup = null;
	private JComboBox<ComboBoxItem>  cmbProducte = null;
	
	private String proveidorId;
	
	public Action canviarProducteAction;
	public String producteId= null;	
	public String nomProducte= null;
	public Integer grupId= null;
	public Integer subGrupId= null;
	
	/**
	 * Create the frame.
	 */
	public SelectProductControl(ClientManager<ETallerStocksInterface> _clientManager, String _proveidorId) {

		this.proveidorId= _proveidorId;
		this.clientManager= _clientManager;
	    setLayout(null);
			    
		JLabel lblGrup = new JLabel("LABEL_GRUP");
		lblGrup.setBounds(0, 50, 200, 20);
		add(lblGrup);
		
		JLabel lblSubGrup = new JLabel("LABEL_SUBGRUP");
		lblSubGrup.setBounds(150, 50, 200, 20);
		add(lblSubGrup);
	    		
		JLabel lblProducte = new JLabel("LABEL_PRODUCTE");
		lblProducte.setBounds(300, 50, 400, 20);
		add(lblProducte);
		

		
	    cmbGrup = new JComboBox<ComboBoxItem>();
	    ComboBoxHelper.fillCmbGrup(this.clientManager, cmbGrup );
	    cmbGrup.setBounds(0, 75, 125, 20);
	    cmbGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();										
					ComboBoxHelper.fillCmbSubGrup(clientManager, cmbSubGrup, grupId);
					ComboBoxHelper.fillCmbProducte(clientManager, cmbProducte, proveidorId, grupId ,subGrupId);
					
				}
		    }
		});			    
	    add(cmbGrup);

	    
		cmbSubGrup = new JComboBox<ComboBoxItem>();
		ComboBoxHelper.fillCmbSubGrup(this.clientManager, cmbSubGrup , null);
		cmbSubGrup.setBounds(150, 75, 125, 20);
		cmbSubGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();
					
					ComboBoxHelper.fillCmbProducte(clientManager, cmbProducte, proveidorId, grupId ,subGrupId);
					
				}
		    }
		});			    
		
	   add(cmbSubGrup);
		

		cmbProducte = new JComboBox<ComboBoxItem>();
		ComboBoxHelper.fillCmbProducte(this.clientManager, cmbProducte, null, null,null);		
		cmbProducte.setBounds(300, 75, 200, 20);	
		cmbProducte.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();
					if (canviarProducteAction != null)
						canviarProducteAction.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, producteId));
				}
		    }
		});					
	    add(cmbProducte);

	}
	
	private void UpdateSelected() 
	{				
		grupId = (Integer) ((ComboBoxItem) cmbGrup.getSelectedItem()).getId();
		subGrupId = (Integer) ((ComboBoxItem) cmbSubGrup.getSelectedItem()).getId();
		producteId= (String) ((ComboBoxItem) cmbProducte.getSelectedItem()).getId();
		nomProducte= ((ComboBoxItem) cmbProducte.getSelectedItem()).getDescription();
	}

	public void resetFields() 
	{				
		cmbGrup.setSelectedIndex(0);;
		cmbSubGrup.setSelectedIndex(0);
		cmbProducte.setSelectedIndex(0);
	}
	

	public void setEnabled(boolean enabled)
	{
		cmbGrup.setEnabled(enabled);
		cmbSubGrup.setEnabled(enabled);
		cmbProducte.setEnabled(enabled);
	}
	

	public boolean isValidLine() {		
		
		try 
		{
			if (producteId == null)
			{
				this.cmbProducte.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_CHOOSE_PRODUCT"), Enums.MessageType.Warning);
				return false;
			}
		
		}
		catch (Exception e )
		{
			return false;
		}
		
		return true;
	}	

}
