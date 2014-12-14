package uoc.tdp.pac4.st.client.cx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
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

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

@SuppressWarnings("unused")
public class CanviPassword extends JFrame {

	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;
	private JPasswordField TXT_OLD_PASSWORD; 

	private JPasswordField TXT_NEW_PASSWORD; 
	private JPasswordField TXT_MEW_PASSWORD2;
	private Usuari userPassword;
	
	/**
	 * Create the frame.
	 */
	public CanviPassword(Usuari user) {
		this.setName(null);
		setTitle("TITLE_CLIENT_WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		userPassword = user;
		JLabel lblNewLabel = new JLabel("LABEL_OLD_PASSWORD");
		lblNewLabel.setBounds(33, 26, 142, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnLogin = new JButton("BUTTON_EXECUTE");
		btnLogin.setBounds(186, 124, 114, 25);
		btnLogin.setName("LOGIN");
		contentPane.add(btnLogin);
		
		JLabel lblLabelexamplerecoverdata = new JLabel("LABEL_NEW_PASSWORD");
		
		lblLabelexamplerecoverdata.setBounds(33, 62, 142, 15);
		contentPane.add(lblLabelexamplerecoverdata);
		
		TXT_OLD_PASSWORD = new JPasswordField(10);
		TXT_OLD_PASSWORD.setActionCommand("OK");
		TXT_OLD_PASSWORD.setBounds(186, 24, 114, 19);
		contentPane.add(TXT_OLD_PASSWORD);
		
		TXT_NEW_PASSWORD = new JPasswordField(10);
		TXT_NEW_PASSWORD.setActionCommand("OK");
		TXT_NEW_PASSWORD.setBounds(186, 57, 114, 19);
		contentPane.add(TXT_NEW_PASSWORD);
		
		TXT_MEW_PASSWORD2 = new JPasswordField(10);
		TXT_MEW_PASSWORD2.setActionCommand("OK");
		TXT_MEW_PASSWORD2.setBounds(186, 93, 114, 19);
		contentPane.add(TXT_MEW_PASSWORD2);
		
		JLabel label = new JLabel("LABEL_NEW_PASSWORD");
		label.setBounds(33, 95, 142, 15);
		contentPane.add(label);
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		/* EVENTS - BEGIN *************************************************************************************/
		//BOTÓ INICIAR SERVIDOR
		
		
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		/* EVENTS - BEGIN *************************************************************************************/
		//BOTÓ INICIAR SERVIDOR
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					canviPassword();
				} catch (STException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
	
	private void canviPassword() throws STException, Exception {
		try {
			if (Arrays.equals(this.TXT_NEW_PASSWORD.getPassword(), this.TXT_MEW_PASSWORD2.getPassword())==false){
				this.TXT_MEW_PASSWORD2.setText("");
				this.TXT_MEW_PASSWORD2.setFocusable(true);
				Methods.showMessage("ERROR_NEW_PASSWORD" , Enums.MessageType.Warning);
				return;
			}
			if (Arrays.equals(this.TXT_OLD_PASSWORD.getPassword(), userPassword.getPassword().toCharArray())==false){
				Methods.showMessage("ERROR_OLD_PASSWORD" , Enums.MessageType.Warning);
				this.TXT_OLD_PASSWORD.setText("");
				this.TXT_OLD_PASSWORD.setFocusable(true);
				return;
			}
			
			startConnection();
			//DatabaseManager db = new DatabaseManager();
			//UserManager um = new UserManager(db);
			
			//Usuari user = um.Login(userPassword.getLogin(), userPassword.getPassword());
			Usuari user  = this._clientManager.getRMIInterface().Login(userPassword.getLogin(),userPassword.getPassword());
			
			if (user!=null){
				if (userPassword.getPassword().equals(user.getPassword())){
					stopConnection();
					startConnection();
					//int result=um.canviPassword(userPassword.getidUsuari(), this.TXT_NEW_PASSWORD.getText());
					int result = this._clientManager.getRMIInterface().canviPassword(userPassword.getidUsuari(), this.TXT_NEW_PASSWORD.getText());
					if (result!=0){
						Methods.showMessage("CHANGE_PASSWORD" , Enums.MessageType.Info);
					}else{
						
					}
				}else{
					Methods.showMessage("ERROR_NEW_PASSWORD" , Enums.MessageType.Warning);
				}
				//ClientWindow cw= new ClientWindow(nomcomplert,user.getRol());
				//cw.setVisible(true);
				this.dispose();
			}else{
				Methods.showMessage(TokenKeys.ERROR_ACCESS_EXCEPTION , Enums.MessageType.Warning);
			}
		} catch (STException e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION));

		} catch (Exception e){
			Managers.exception.showException(new STException(e));
		}finally{
			stopConnection();
		}
		
		
	}
}
