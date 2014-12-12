package uoc.tdp.pac4.st.client.cx;

import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.Enums.MessageType;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

@SuppressWarnings("unused")
public class ClientWindow extends JFrame {

	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;
	private JTabbedPane tabbedPane;
	private String rolUser; 
	private String rolAdministrador = "Administrador";
	private Usuari userClient;
	
	/**
	 * Create the frame.
	 */
	public ClientWindow(Usuari user) {
		this.setName(null);
		setTitle("TITLE_CLIENT_WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userClient = user;
		
		JLabel lblusuari = new JLabel("usuari");
		lblusuari.setBounds(33, 46, 75, 15);
		
		contentPane.add(lblusuari);
		
		JLabel lblLabelexamplerecoverresultset = new JLabel("PANELL DE GESTIÓ");
		lblLabelexamplerecoverresultset.setBounds(33, 19, 614, 15);
		contentPane.add(lblLabelexamplerecoverresultset);
		
		JButton btnExample8 = new JButton("BUTTON_EXECUTE");
		btnExample8.setBounds(604, 384, 142, 25);
		contentPane.add(btnExample8);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(33, 89, 696, 277);
		/*Mètode de comprova el rol de l'usuari i crea el TabbedPane*/
		if (user.getRol().equals(rolAdministrador)){
			
			JPanel panel1= new JPanel();
			panel1.setLayout(null);
			
			JButton btnAltaUsuari = new JButton("BUTTON_NEW_USER");
			btnAltaUsuari.setBounds(new Rectangle(150,10,300,50));
			panel1.add(btnAltaUsuari);
			
			JButton btnModificarUsuari = new JButton("BUTTON_MODIFY_USER");
			btnModificarUsuari.setBounds(new Rectangle(150,70,300,50));
			panel1.add(btnModificarUsuari);
			
			JButton btnBaixaUsuari = new JButton("BUTTON_DROP_USER");
			btnBaixaUsuari.setBounds(new Rectangle(150,130,300,50));
			panel1.add(btnBaixaUsuari);
			
			JButton btnConsultaUsuari = new JButton("BUTTON_SELECT_USER");
			btnConsultaUsuari.setBounds(new Rectangle(150,190,300,50));
			panel1.add(btnConsultaUsuari);
			
			tabbedPane.addTab("Usuaris", panel1);
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			
			JPanel panel2=new JPanel();
			panel2.setLayout(null);
			JButton btnAltaLocal = new JButton("BUTTON_NEW_ESTABLISHMENT");
			btnAltaLocal.setBounds(new Rectangle(150,10,300,50));
			panel2.add(btnAltaLocal);
			
			JButton btnModificarLocal = new JButton("BUTTON_MODIFY_ESTABLISHMENT");
			btnModificarLocal.setBounds(new Rectangle(150,70,300,50));
			panel2.add(btnModificarLocal);
			
			JButton btnBaixaLocal = new JButton("BUTTON_DROP_ESTABLISHMENT");
			btnBaixaLocal.setBounds(new Rectangle(150,130,300,50));
			panel2.add(btnBaixaLocal);
			
			JButton btnConsultaLocal = new JButton("BUTTON_SELECT_ESTABLISHMENT");
			btnConsultaLocal.setBounds(new Rectangle(150,190,300,50));
			panel2.add(btnConsultaLocal);
			
			//tabbedPane.addTab("Locals", panel2);
			tabbedPane.addTab("TAB_ESTABLISHMENT", panel2);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			
			JPanel panel3=new JPanel();
			panel3.setLayout(null);
			JButton btnAltaProveidor = new JButton("BUTTON_NEW_PROVIDER");
			btnAltaProveidor.setBounds(new Rectangle(150,10,300,50));
			panel3.add(btnAltaProveidor);
			
			JButton btnModificarProveidor = new JButton("BUTTON_MODIFY_PROVIDER");
			btnModificarProveidor.setBounds(new Rectangle(150,70,300,50));
			panel3.add(btnModificarProveidor);
			
			JButton btnBaixaProveidor = new JButton("BUTTON_DROP_PROVIDER");
			btnBaixaProveidor.setBounds(new Rectangle(150,130,300,50));
			panel3.add(btnBaixaProveidor);
			
			JButton btnConsultaProveidor = new JButton("BUTTON_SELECT_PROVIDER");
			btnConsultaProveidor.setBounds(new Rectangle(150,190,300,50));
			panel3.add(btnConsultaProveidor);
			
			tabbedPane.addTab("Proveidors", panel3);
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
			
			JPanel panel4=new JPanel();
			panel4.setLayout(null);
			JButton btnAltaProduct = new JButton("BUTTON_NEW_PRODUCT");
			btnAltaProduct.setBounds(new Rectangle(150,10,300,50));
			panel4.add(btnAltaProduct);
			
			JButton btnModificarProducte = new JButton("BUTTON_MODIFY_PRODUCT");
			btnModificarProducte.setBounds(new Rectangle(150,70,300,50));
			panel4.add(btnModificarProducte);
			
			JButton btnBaixaProducte = new JButton("BUTTON_DROP_PRODUCT");
			btnBaixaProducte.setBounds(new Rectangle(150,130,300,50));
			panel4.add(btnBaixaProducte);
			
			JButton btnConsultaProducte = new JButton("BUTTON_SELECT_PRODUCT");
			btnConsultaProducte.setBounds(new Rectangle(150,190,300,50));
			panel4.add(btnConsultaProducte);
			
			tabbedPane.addTab("Productes", panel4);
			tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
			
			JPanel panel5=new JPanel();
			panel5.setLayout(null);
			JButton btnAltaAlbara = new JButton("BUTTON_NEW_DELIVERYNOTE");
			btnAltaAlbara.setBounds(new Rectangle(150,10,300,50));
			panel5.add(btnAltaAlbara);
			
			JButton btnModificarAlbara = new JButton("BUTTON_MODIFY_DELIVERYNOTE");
			btnModificarAlbara.setBounds(new Rectangle(150,70,300,50));
			panel5.add(btnModificarAlbara);
			
			JButton btnBaixaAlbara = new JButton("BUTTON_DROP_DELIVERYNOTE");
			btnBaixaAlbara.setBounds(new Rectangle(150,130,300,50));
			panel5.add(btnBaixaAlbara);
			
			JButton btnConsultaAlbara = new JButton("BUTTON_SELECT_DELIVERYNOTE");
			btnConsultaAlbara.setBounds(new Rectangle(150,190,300,50));
			panel5.add(btnConsultaAlbara);
			
			tabbedPane.addTab("Albarans", panel5);
			tabbedPane.setMnemonicAt(4, KeyEvent.VK_4);
			
			JPanel panel6=new JPanel();
			panel6.setLayout(null);
			JButton btnCanviPassword = new JButton("BUTTON_CHANGE_PASSWORD");
			btnCanviPassword.setBounds(new Rectangle(150,10,300,50));
			panel6.add(btnCanviPassword);
		
			btnCanviPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CanviPassword cv = new CanviPassword(userClient);
					cv.setVisible(true);
				}
			});
			
			JButton btnCanviIdioma = new JButton("BUTTON_CHANGE_LANGUAGE");
			btnCanviIdioma.setBounds(new Rectangle(150,70,300,50));
			panel6.add(btnCanviIdioma);
			
			tabbedPane.addTab("Contrassenya i Idioma", panel6);
			tabbedPane.setMnemonicAt(5, KeyEvent.VK_5);
			
			
			
		}else{
			JPanel panel1= new JPanel();
			panel1.setLayout(null);
			
			JButton btnAltaUsuari = new JButton("Boto1");
			btnAltaUsuari.setBounds(new Rectangle(150,10,300,50));
			panel1.add(btnAltaUsuari);
			
			JButton btnModificarUsuari = new JButton("Boto2");
			btnModificarUsuari.setBounds(new Rectangle(150,70,300,50));
			panel1.add(btnModificarUsuari);
			
			JButton btnBaixaUsuari = new JButton("Boto3");
			btnBaixaUsuari.setBounds(new Rectangle(150,130,300,50));
			panel1.add(btnBaixaUsuari);
			
			JButton btnConsultaUsuari = new JButton("Boto4");
			btnConsultaUsuari.setBounds(new Rectangle(150,190,300,50));
			panel1.add(btnConsultaUsuari);
			
			tabbedPane.addTab("Gestió Magatzem", panel1);
			tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
			
			JPanel panel2=new JPanel();
			panel2.setLayout(null);
			JButton btnAltaLocal = new JButton("Boto1");
			btnAltaLocal.setBounds(new Rectangle(150,10,300,50));
			panel2.add(btnAltaLocal);
			
			JButton btnModificarLocal = new JButton("Boto2");
			btnModificarLocal.setBounds(new Rectangle(150,70,300,50));
			panel2.add(btnModificarLocal);
			
			JButton btnBaixaLocal = new JButton("Boto3");
			btnBaixaLocal.setBounds(new Rectangle(150,130,300,50));
			panel2.add(btnBaixaLocal);
			
			JButton btnConsultaLocal = new JButton("Boto4");
			btnConsultaLocal.setBounds(new Rectangle(150,190,300,50));
			panel2.add(btnConsultaLocal);
			
			tabbedPane.addTab("Distribució material", panel2);
			tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
			
			JPanel panel3=new JPanel();
			panel3.setLayout(null);
			JButton btnAltaProveidor = new JButton("Boto1");
			btnAltaProveidor.setBounds(new Rectangle(150,10,300,50));
			panel3.add(btnAltaProveidor);
			
			JButton btnModificarProveidor = new JButton("Boto2");
			btnModificarProveidor.setBounds(new Rectangle(150,70,300,50));
			panel3.add(btnModificarProveidor);
			
			JButton btnBaixaProveidor = new JButton("Boto3");
			btnBaixaProveidor.setBounds(new Rectangle(150,130,300,50));
			panel3.add(btnBaixaProveidor);
			
			JButton btnConsultaProveidor = new JButton("Boto4");
			btnConsultaProveidor.setBounds(new Rectangle(150,190,300,50));
			panel3.add(btnConsultaProveidor);
			
			tabbedPane.addTab("Avisos", panel3);
			tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
			
			JPanel panel4=new JPanel();
			panel4.setLayout(null);
			JButton btnAltaProduct = new JButton("Boto1");
			btnAltaProduct.setBounds(new Rectangle(150,10,300,50));
			panel4.add(btnAltaProduct);
			
			JButton btnModificarProducte = new JButton("Boto2");
			btnModificarProducte.setBounds(new Rectangle(150,70,300,50));
			panel4.add(btnModificarProducte);
			
			JButton btnBaixaProducte = new JButton("Boto3");
			btnBaixaProducte.setBounds(new Rectangle(150,130,300,50));
			panel4.add(btnBaixaProducte);
			
			JButton btnConsultaProducte = new JButton("Boto4");
			btnConsultaProducte.setBounds(new Rectangle(150,190,300,50));
			panel4.add(btnConsultaProducte);
			
			tabbedPane.addTab("Estadístiques", panel4);
			tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
			
			JPanel panel5=new JPanel();
			panel5.setLayout(null);
			
			JButton btnCanviPassword = new JButton("BUTTON_CHANGE_PASSWORD");
			btnCanviPassword.setBounds(new Rectangle(150,10,300,50));
			panel5.add(btnCanviPassword);
			
			btnCanviPassword.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					CanviPassword cv = new CanviPassword(userClient);
					cv.setVisible(true);
				}
			});
			
			JButton btnCanviIdioma = new JButton("BUTTON_CHANGE_LANGUAGE");
			btnCanviIdioma.setBounds(new Rectangle(150,70,300,50));
			panel5.add(btnCanviIdioma);
			
			tabbedPane.addTab("Contrassenya i Idioma", panel5);
			tabbedPane.setMnemonicAt(4, KeyEvent.VK_5);
		}
		contentPane.add(tabbedPane);
		
	
		
		
		
		JLabel lbluser = new JLabel();
		lbluser.setBounds(100, 46, 346, 15);
		lbluser.setText(userClient.getnom());
		contentPane.add(lbluser);
		
		
	
		
		
		
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		//Omplim el label
		

		//BOTÓ D'ELIMINACIÓ DE DADES
		btnExample8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testDeleteData();
			}
		});
		

		
		
		
		
	
		
		
		/* EVENTS - END *************************************************************************************/
	}

	

	/***
	 * Mostra el contingut de la tabla indicada
	 * 
	 * @param db Objecte DatabaseManager obert
	 * @throws STException
	 * @throws Exception
	 */
	private void showTableLocal(DatabaseManager db) throws STException, Exception{
		ResultSet resultSet = null;
		resultSet = db.selectData("Select * from " + Constants.TABLE_LOCAL);
	
		resultSet.beforeFirst();
		int i = 0;
		while (resultSet.next()){
			i++;
			Methods.showMessage(resultSet.getString(Constants.FIELD_NOMLOCAL),Enums.MessageType.Info);
		}
		resultSet.close();
		resultSet = null;
		Methods.showMessage(String.format(Managers.i18n.getTranslation(TokenKeys.AVAILABLE_ESTABLIMENTS), i), Enums.MessageType.Info);
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
	 * Métode que prova la connexió establerta
	 * amb el servidor remot RMI
	 */
	private void testConnection(){
		try {
			if (this._clientManager != null){
				if (this._clientManager.connectionStatus() == Enums.ConnectionStatus.Open){
					String result = this._clientManager.getRMIInterface().testRMIConnection();
					Methods.showMessage(result, Enums.MessageType.Info);
				} else {
					Methods.showMessage(TokenKeys.ERROR_CLOSED_CONNECTION, Enums.MessageType.Warning);
				}
			} else {
				Methods.showMessage(TokenKeys.ERROR_CLOSED_CONNECTION, Enums.MessageType.Error);
			}
		} catch (STException e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION));

		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
	
	/***
	 * Executa una sentència SQL d'eliminació
	 * de dades a la base de dades. 
	 * Fixeu-vos que no cal obrir la
	 * connexió a la base de dades ni tancar-la.
	 * També he afegit com s'utilitza el mètode
	 * getScalar, que recupera el valor de la
	 * primera columna de la primera fila
	 * del resultat d'una sentència de selecció.
	 *  
	 * ATENCIÓ: 
	 * ========
	 * 
	 * El que mostra aquest exemple no es
	 * pot reproduir a mètodes del costat del client.
	 * Només s'ha de reproduir a mètodes
	 * que es trobin a LA BANDA DEL SERVIDOR
	 */
	private void testDeleteData(){
		DatabaseManager db = null;
		try {
			db = new DatabaseManager();
			db.startTransaction();
			
			//Recuperem el cif d'un taller
			Object cif = db.getScalar("SELECT " + 
										Constants.FIELD_CIF + 
										" FROM " + Constants.TABLE_LOCAL + 
										" ORDER BY " + Constants.FIELD_CIF + " ASC" +
										" LIMIT 1");

			//Eliminem el registre
			int result = db.deleteData("DELETE FROM " + Constants.TABLE_LOCAL + " WHERE cif = '" + cif + "'");
			
			//Mostrem els registres actuals
			this.showTableLocal(db);

			//Desfem els canvis
			db.finishTransaction(false);
			
		} catch (STException e){
			Managers.exception.showException(e);
		
		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
}
