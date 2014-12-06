package uoc.tdp.pac4.st.client.cf;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

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
	private JTextField txtQuantitat = null;
	
	private String proveidorId;
	
	public String producteId= null;	
	public String nomProducte= null;
	public Integer grupId= null;
	public Integer subGrupId= null;
	public Integer quantitat= null;
	
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
		
		JLabel lblQuantitat= new JLabel("LABEL_QUANTITAT");
		lblQuantitat.setBounds(520, 50, 70, 20);
		add(lblQuantitat);
	    
		
	    cmbGrup = new JComboBox<ComboBoxItem>();
	    fillCmbGrup(cmbGrup );
	    cmbGrup.setBounds(0, 75, 125, 20);
	    cmbGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();										
					fillCmbSubGrup(cmbSubGrup, grupId);
					fillCmbProducte(cmbProducte, grupId ,subGrupId);
					
				}
		    }
		});			    
	    add(cmbGrup);

	    
		cmbSubGrup = new JComboBox<ComboBoxItem>();
		fillCmbSubGrup(cmbSubGrup , null);
		cmbSubGrup.setBounds(150, 75, 125, 20);
		cmbSubGrup.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();
					
					fillCmbProducte(cmbProducte, grupId ,subGrupId);
					
				}
		    }
		});			    
		
	   add(cmbSubGrup);
		

		cmbProducte = new JComboBox<ComboBoxItem>();
		fillCmbProducte(cmbProducte, null,null);		
		cmbProducte.setBounds(300, 75, 200, 20);	
		cmbProducte.addItemListener(new ItemListener () {
		    public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					UpdateSelected();							
				}
		    }
		});					
	    add(cmbProducte);
	    	    
	    txtQuantitat= new JTextField();
	    txtQuantitat.setBounds(520, 75, 70, 20);			    	
	    add(txtQuantitat);
	    		
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
		txtQuantitat.setText("");
	}
	

	public void setEnabled(boolean enabled)
	{
		cmbGrup.setEnabled(enabled);
		cmbSubGrup.setEnabled(enabled);
		cmbProducte.setEnabled(enabled);
		txtQuantitat.setEnabled(enabled);	
	}
	/***
	 * Omple un ComboBox amb la llista de grups.
	 * 
	 */
	private void fillCmbGrup(JComboBox<ComboBoxItem> cmbBoxItem) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Grup> list = clientManager.getRMIInterface().listGrups();
			
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
			
			List<SubGrup> list = clientManager.getRMIInterface().getSubGrupsByGrup(grupId);
			
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
	private void fillCmbProducte(JComboBox<ComboBoxItem> cmbBoxItem, Integer grupId, Integer subGrupId) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Producte> list = clientManager.getRMIInterface().SearchProdutes(proveidorId, grupId, subGrupId);
			
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


	public boolean isValidLine() {		
		
		try 
		{
			if (producteId == null)
			{
				this.cmbProducte.requestFocus();
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_CHOOSE_PRODUCT"), Enums.MessageType.Warning);
				return false;
			}
		
			try 
			{ 
			  quantitat= Integer.parseInt(this.txtQuantitat.getText()); 
			} 
			catch(NumberFormatException e) {
				Methods.showMessage( Managers.i18n.getTranslation("VALIDATION_INVALID_QUANTITY"), Enums.MessageType.Warning);
			   this.txtQuantitat.requestFocus();
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
