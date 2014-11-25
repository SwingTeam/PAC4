package uoc.tdp.pac4.st.server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe que correspon a la pantalla
 * principal de la part del servidor.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class ServerWindow extends JFrame implements WindowListener {

	private static final long serialVersionUID = 2808854076521762091L;
	private JPanel contentPane;
	private JFrame frame;
	private ServerManager<ETallerStocksInterface, ETallerStocksImpl> _serverManagement = null;
	
	/***
	 * Classe que es correspon amb la finestra
	 * principal de l'aplicació servidor,
	 * 
	 * @author Swing Team - 2014
	 *
	 */
	public ServerWindow() {
		frame = this;
		setType(Type.UTILITY);
		this.setName(null);
		setResizable(false);
		setTitle("TITLE_WINDOW_SERVER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 240);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlServerStatus = new JPanel();
		pnlServerStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlServerStatus.setBounds(12, 168, 424, 35);
		contentPane.add(pnlServerStatus);
		
		final JLabel lblServerStatus = new JLabel("STATUS_STOPPED");
		lblServerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerStatus.setFont(new Font("Dialog", Font.BOLD, 16));
		pnlServerStatus.add(lblServerStatus);
		
		final JButton btnStart = new JButton("BUTTON_START_SERVER");
		btnStart.setBounds(25, 54, 177, 35);
		contentPane.add(btnStart);
		
		final JButton btnStop = new JButton("BUTTON_STOP_SERVER");
		btnStop.setBounds(239, 54, 177, 35);
		btnStop.setEnabled(false);
		contentPane.add(btnStop);
		
		final JButton btnClose = new JButton("BUTTON_CLOSE");
		btnClose.setBounds(161, 120, 120, 35);
		contentPane.add(btnClose);
		
		//Traducció dels tokens de la pantalla
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception e) {
			Managers.exception.showException(new STException(e, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		
		//Centrem la finestra
		Methods.centerWindow(this);
		
		//Inicia la captura d'events de la finestra
		addWindowListener(this);		
		
		/* EVENTS - BEGIN *************************************************************************************/
		//BOTÓ INICIAR SERVIDOR
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					startConnection();
					if (_serverManagement != null){
						if (_serverManagement.getRMIConnectionStatus() == Enums.ServerStatus.Running){
							btnStart.setEnabled(false);
							btnStop.setEnabled(true);
							lblServerStatus.setText(Managers.i18n.getTranslation(TokenKeys.STATUS_RUNNING));
						}
					}
				}catch(Exception ex){
					Managers.exception.showException(new STException(ex, TokenKeys.ERROR_RMI_STARTING));
				}
			}
		});
		
		//BOTÓ ATURAR SERVIDOR
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					stopConnection();
					btnStart.setEnabled(true);
					btnStop.setEnabled(false);
					lblServerStatus.setText(Managers.i18n.getTranslation(TokenKeys.STATUS_STOPPED));
				}catch(Exception ex){
					Managers.exception.showException(new STException(ex, TokenKeys.ERROR_RMI_CLOSING));
				}
			}
		});

		//BOTÓ TANCAR
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					lblServerStatus.setText(Managers.i18n.getTranslation(TokenKeys.STATUS_STOPPED));
					frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				}catch(Exception ex){
					Managers.exception.showException(new STException(ex));
				}
			}
		});
		/* EVENTS - END *************************************************************************************/
	}

	/* WINDOW EVENTS - BEGIN *************************************************************************************/
	@Override
	public void windowOpened(WindowEvent e) {}
	/***
	 * Capturem el tancament de la finestra per
	 * a alliberar els ports abans de sortir.
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		this.stopConnection();
	}
	@Override
	public void windowClosed(WindowEvent e) {}
	@Override
	public void windowIconified(WindowEvent e) {}
	@Override
	public void windowDeiconified(WindowEvent e) {}
	@Override
	public void windowActivated(WindowEvent e) {}
	@Override
	public void windowDeactivated(WindowEvent e) {}
	/* WINDOW EVENTS - END *************************************************************************************/

	/***
	 * Tancar la connexió RMI
	 */
	private void stopConnection(){
		try{
			if (this._serverManagement != null){
				this._serverManagement.stopRMIConnection();
				this._serverManagement = null;
			}
		}catch(STException e){
			Managers.exception.showException(e);
		}
	}
	
	/***
	 * Inicia la connexió RMI
	 */
	private void startConnection(){
		try{
			//Només carregarem les dades configurades la
			//primera vegada que es posi en marxa
			if (this._serverManagement == null){
				SettingManager settings = new SettingManager();
				int rmiPort = Integer.parseInt(settings.getSetting(Constants.SETTING_RMI_PORT));
				String rmiName = settings.getSetting(Constants.SETTING_RMI_NAME);
				settings = null;
				this._serverManagement 
					= new ServerManager<ETallerStocksInterface, ETallerStocksImpl>(rmiPort, rmiName, ETallerStocksImpl.class);
			}
			if (this._serverManagement != null)
				_serverManagement.startRMIConnection();

		} catch (STException e){
			Managers.exception.showException(e);
			
		}catch(Exception e){
			Managers.exception.showException(new STException(e));
		}
	}
}
