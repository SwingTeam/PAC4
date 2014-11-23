package uoc.tdp.pac4.st.common.managers;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe encarregada de la gestió del servidor
 * de l'aplicació.
 * 
 * @author Swing Team - 2014
 *
 * @param <T> Tipus corresponent a la interface RMI
 * @param <P> Tipus corresponent a la implementació
 * de la interfacte RMI
 */
@SuppressWarnings("unused")
public class ServerManager<T,P> {

	private int _connectionPort = 0;
	private String _connectionName = null;;
	private T _rmiInterface = null;
	private Registry _registry = null;
	private Enums.ServerStatus _serverStatus = Enums.ServerStatus.Stopped;
	private Class<P> _rmiImplementation = null;

	/***
	 * Constructor
	 * 
	 * @param inputPort Port que s'utilitzarà a la connexió RMI
	 * @param connectionName Nom de la connexió RMI
	 * @param rmiImplementation Classe corresponent a la
	 * implementació de la interface de RMI
	 */
	public ServerManager(int inputPort,
							String connectionName,
							Class<P> rmiImplementation){
			this._connectionPort = inputPort;
			this._connectionName = connectionName;
			this._rmiImplementation = rmiImplementation;
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
	 * @throws RemoteException 
	 */
	@SuppressWarnings("unchecked")
	public void startRMIConnection() throws STException {
		try {
			if (this._serverStatus == Enums.ServerStatus.Stopped){
				if (this._registry == null){
					this._registry = LocateRegistry.createRegistry(this._connectionPort);
					this._rmiInterface =  (T) this._rmiImplementation.newInstance();
					this._registry.rebind(this._connectionName, (Remote) this._rmiInterface);
				} else {
					this._registry.rebind(this._connectionName, (Remote) this._rmiInterface);
				}
			} else {
				throw new STException(TokenKeys.ERROR_SERVER_RUNNING);
			}
			this._serverStatus = Enums.ServerStatus.Running;
		} catch (AccessException e) {
			throw new STException(e, TokenKeys.ERROR_ACCESS_EXCEPTION);
		
		} catch (RemoteException e) {
			throw new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION);
		
		} catch (NullPointerException e) {
			throw new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE);
		
		} catch (Exception e){
			throw new STException(e);
			
		} finally {
			if (this._registry != null && this._serverStatus == Enums.ServerStatus.Stopped){
				this.stopRMIConnection(true);
			}
		}
	}

	/***
	 * Atura la connexió remota.
	 * 
	 */
	public void stopRMIConnection() throws STException {
		this.stopRMIConnection(false);
	}
	
	/***
	 * Atura la connexió remota.
	 * 
	 * @param forceStopping Indica que s'ha de
	 * procedir amb la parada del servidor amb
	 * independència del seu estat. 
	 */
	public void stopRMIConnection(boolean forceStopping) throws STException {
		try {
			if (this._serverStatus == Enums.ServerStatus.Running
					|| forceStopping){
				if (this._registry != null){
					this._registry.unbind(this._connectionName);
					UnicastRemoteObject.unexportObject(this._registry, true);
				}
			} else {
				throw new STException(TokenKeys.ERROR_SERVER_STOPPED);
			}
			
			this._serverStatus = Enums.ServerStatus.Stopped;
		
		} catch (AccessException e) {
			throw new STException(e, TokenKeys.ERROR_ACCESS_EXCEPTION);
		
		} catch (RemoteException e) {
			throw new STException(e, TokenKeys.ERROR_REMOTE_EXCEPTION);

		} catch (NotBoundException e) {
			throw new STException(e, TokenKeys.ERROR_NOT_BOUND_EXCEPTION_ON_SERVER);
		}
	}
	
	/***
	 * Indica l'estat de la connexió RMI
	 * 
	 * @return Un element de la enum ServerStatus,
	 * que indica l'estat actual 
	 */
	public Enums.ServerStatus getRMIConnectionStatus(){
		return this._serverStatus;
	}
}
