package uoc.tdp.pac4.st.common.managers;

import java.io.IOException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe encarregada de la gestió
 * de la connexió del client.
 * 
 * @author Swing Team - 2014
 *
 * @param <T> Tipus de la interface de
 * connexió RMI
 */
@SuppressWarnings("unused")
public class ClientManager<T> {

	private String _connectionName = null;
	private int _connectionPort = 0;
	private String _connectionUrl = null;
	private T _rmiInterface = null;
	private Registry _registry = null;
	private Enums.ConnectionStatus _connectionStatus 
		= Enums.ConnectionStatus.Closed;

	/***
	 * Constructor
	 * 
	 * @throws STException 
	 */
	public ClientManager() throws STException{
		this.getConnectionSettings();
	}
	
	/***
	 * Constructor
	 * 
	 * @param serverUrl Url del servidor RMI
	 * @param connectionPort Port que s'utilitzarà a la connexió RMI
	 * @param connectionName Nom de la connexió RMI
	 */
	public ClientManager(String serverUrl,
							int connectionPort,
							String connectionName){
		this._connectionUrl = serverUrl;
		this._connectionPort = connectionPort;
		this._connectionName = connectionName;
	}
	
	/***
	 * Métode que encarregat de fer la connexió
	 * RMI amb el servidor remot
	 */
	private void getConnectionSettings() throws STException{
		try{
			this._connectionUrl = Managers.settings.getSetting(Constants.SETTING_RMI_URL);
			this._connectionPort = Integer.parseInt(Managers.settings.getSetting(Constants.SETTING_RMI_PORT));
			this._connectionName = Managers.settings.getSetting(Constants.SETTING_RMI_NAME);
		} catch (IOException e){
			throw new STException(e);
		}
	}
	
	/***
	 * Mètode que retorna la interface de la
	 * connexió RMI.
	 * 
	 * @return La interface de treball de la connexió RMI
	 */
	public T getRMIInterface(){
		return this._rmiInterface;
	}
	
	/**
	 * Inicia la connexió remota.
	 * @throws STException 
	 */
	@SuppressWarnings("unchecked")
	public void startConnection() throws STException {
		try {
			if (this._connectionStatus == Enums.ConnectionStatus.Closed){
				 this._registry = LocateRegistry.getRegistry(this._connectionUrl, this._connectionPort);
				 this._rmiInterface =(T)this._registry.lookup(this._connectionName);
				 this._connectionStatus = Enums.ConnectionStatus.Open;

			} else if (this._connectionStatus == Enums.ConnectionStatus.Open){
				if (this._registry == null
						|| this._rmiInterface == null){
					this.stopConnection();
					this.startConnection();
				}
			}
		} catch (AccessException e) {
			throw new STException(e, TokenKeys.ERROR_ACCESS_EXCEPTION);
		
		} catch (RemoteException e) {
			throw new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION);
		
		} catch (NotBoundException e) {
			throw new STException(e, TokenKeys.ERROR_NOT_BOUND_EXCEPTION);
		
		} catch (Exception e){
			throw new STException(e);
		}
	}

	/***
	 * Atura la connexió remota.
	 */
	public void stopConnection() {
		this._registry = null;
		this._rmiInterface = null;
		this._connectionStatus = Enums.ConnectionStatus.Closed;
	}
	
	/***
	 * Indica l'estat de la connexió RMI
	 * 
	 * @return Un element de la enum ConnectionStatus,
	 * que indica l'estat actual 
	 */
	public Enums.ConnectionStatus connectionStatus(){
		return this._connectionStatus;
	}
}
