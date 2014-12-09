package uoc.tdp.pac4.st.common;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.common.dto.*;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.SwingConstants;

/*** 
 * Classe basada en la classe JFrame i que
 * incorpora l'administració de la connexió
 * RMI i el comportament estàndard de totes
 * les finestres.
 *    
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class STFrame extends JFrame implements WindowListener {

	private static final long serialVersionUID = -8480829518162960937L;
	protected JPanel contentPane;
	protected ClientManager<ETallerStocksInterface> _clientManager = null;
	protected JFrame frame = null;
	private boolean _managerWasOpened = false; 
	private boolean _managerWasCreated = false;
	private boolean _useRMI = true;

	/***
	 * Constructor
	 */
	public STFrame(){
		this(null, true);
	}
	
	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public STFrame(ClientManager<ETallerStocksInterface> clientManager){
		this(clientManager, true);
	}
	
	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 * @param useRMI Indica si la finestra utilitzarà
	 * la connexió RMI. Si s'informa negativament, no
	 * obrirà ni tancarà la connexió.
	 */
	public STFrame(ClientManager<ETallerStocksInterface> clientManager, boolean useRMI) {
		this.frame = this;
		this._clientManager = clientManager;
		this.setName(null);
		
		//Inicialitzem el panell contenidor dels controls
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		this.contentPane.setLayout(null);
		setContentPane(this.contentPane);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		//Inicia la captura d'events de la finestra
		addWindowListener(this);		
	}
	
	/* WINDOW EVENTS - BEGIN *************************************************************************************/
	/***
	 * Obrirem sempre la connexió RMI quan
	 * s'obri la finestra
	 */
	@Override
	public void windowOpened(WindowEvent e) {
		//Traducció dels tokens
		try {
			Methods.setFrameLanguage(this);
		} catch (Exception ex) {
			Managers.exception.showException(new STException(ex, TokenKeys.ERROR_TRANSLATING_WINDOW));
		}
		//Centra el formulari
		Methods.centerWindow(this);
		this.startConnection();
		this.initializeFrame();
	}
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
	 * Aquest mètode s'ha d'implementar 
	 * a les classes que derivin d'aquesta classe
	 */
	protected void initializeFrame(){};
	
	/***
	 * Obre la connexió RMI i anota
	 * l'estat actual de la connexió
	 */
	private void startConnection(){
		try{
			if (!this._useRMI)
				return;
			
			if (this._clientManager == null)
				this._clientManager = new ClientManager<ETallerStocksInterface>();
			else{
				this._managerWasCreated = true;
				this._managerWasOpened = (this._clientManager.connectionStatus() == Enums.ConnectionStatus.Open);
			}
			
			this._clientManager.startConnection();

		} catch (STException e){
			Managers.exception.showException(e);
		}
	}
	
	/***
	 * Tanca la connexió RMI si ens
	 * l'havien donada tancada.
	 * Destrueix la instància de
	 * ClientManager si no l'havíem
	 * rebuda creada.
	 */
	private void stopConnection(){
		if (!this._useRMI)
			return;
		
		if (this._clientManager != null
				&& !this._managerWasOpened){
			this._clientManager.stopConnection();
			if (!this._managerWasCreated)
				this._clientManager = null;
		}
	}
}
