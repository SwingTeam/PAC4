package uoc.tdp.pac4.st.client.cx;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

@SuppressWarnings("unused")
public class ClientWindow extends JFrame {

	private static final long serialVersionUID = -4301396368624900151L;
	private JPanel contentPane;
	private ClientManager<ETallerStocksInterface> _clientManager = null;
	/**
	 * Create the frame.
	 */
	public ClientWindow() {
		this.setName(null);
		setTitle("TITLE_CLIENT_WINDOW");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 849, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LABEL_EXAMPLE_CONNEXIO_RMI");
		lblNewLabel.setBounds(33, 26, 614, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnExample1 = new JButton("BUTTON_EXECUTE");
		btnExample1.setBounds(676, 21, 142, 25);
		contentPane.add(btnExample1);
		
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
		btnExample1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startConnection();
				testConnection();
				stopConnection();
			}
		});
		/* EVENTS - END *************************************************************************************/
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
	
}
