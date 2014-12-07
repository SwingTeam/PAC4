package uoc.tdp.pac4.st.common.ui;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;

import uoc.tdp.pac4.st.common.ComboBoxItem;
import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.MotiuDevolucio;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.managers.ClientManager;
import uoc.tdp.pac4.st.rmi.ETallerStocksInterface;

public class ComboBoxHelper  {
	
	/***
	 * Omple un ComboBox amb la llista de grups.
	 * 
	 */
	public static void fillCmbGrup(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem) {
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
	public static void  fillCmbSubGrup(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem , Integer grupId) {
		
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
	public static void fillCmbProducte(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem, String proveidorId, Integer grupId, Integer subGrupId) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Producte> list = clientManager.getRMIInterface().searchProdutes(proveidorId, grupId, subGrupId);
			
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
	 * Omple un ComboBox amb la llista de proveidors.
	 * 
	 */
	public static void fillCmbProveidor(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem ) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Proveidor> list = clientManager.getRMIInterface().listProveidors();
			
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
	 * Omple un ComboBox amb la llista de proveidors.
	 * 
	 */
	public static void fillCmbMotiuDevolucio(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem ) {
		try {
			cmbBoxItem.removeAllItems();
			
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<MotiuDevolucio> list = clientManager.getRMIInterface().listMotiuDevolucio();
			
			Iterator<MotiuDevolucio> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				MotiuDevolucio motiuDevolucio= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(motiuDevolucio.getIdMotiuDevolucio(), motiuDevolucio.getMotiu()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}		
	}
	
	/***
	 * Omple un ComboBox amb la llista de locals.
	 * 
	 */
	public static void fillCmbLocal(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem ) {
		try {
			cmbBoxItem.removeAllItems();
			 
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<LocalST> list = clientManager.getRMIInterface().listLocals();  
			
			Iterator<LocalST> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				LocalST local= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(local.getIdLocal(), local.getNomLocal()));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}		
	}
	
	/***
	 * Omple un ComboBox amb la llista d'albarans d'un local 
	 * 
	 */
	public static void fillCmbAlbara(ClientManager<ETallerStocksInterface> clientManager, JComboBox<ComboBoxItem> cmbBoxItem, String localId) {
		try {
			cmbBoxItem.removeAllItems();
			 
			cmbBoxItem.addItem(new ComboBoxItem(null, Managers.i18n.getTranslation("LABEL_ESCOLLIR")));
			
			List<Albara> list = clientManager.getRMIInterface().listAlbaransByLocal(localId);  
			
			Iterator<Albara> iterator= list.iterator();
			while (iterator.hasNext()) 
			{
				Albara albara= iterator.next();
				cmbBoxItem.addItem(new ComboBoxItem(albara.getIdAlbara(), Integer.toString(albara.getIdAlbara())));
			}			
		}
		catch (STException e) {
			Managers.exception.showException(e);
			
		} catch (RemoteException | NullPointerException e) {
			Managers.exception.showException(new STException(e));
		}		
	}	

}
