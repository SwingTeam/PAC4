package uoc.tdp.pac4.st.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Classe que implementa la interface
 * PeticionsImpl para la connexió RMI.
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public class ETallerStocksImpl extends UnicastRemoteObject implements ETallerStocksInterface, Serializable {

	private static final long serialVersionUID = -2041907125638859914L;
	private DatabaseManager _databaseManagement = null;
	
	/***
	 * Constructor
	 * @throws STException
	 * @throws RemoteException
	 */
	public ETallerStocksImpl() throws RemoteException, STException {
		super();
		try{
			//Llegim els paràmetres d'accés
			//Driver de java per a la connexió a postgreSQL
			String driver = Managers.settings.getSetting(Constants.DB_DRIVER);
			//Url d'accés a la base de dades
			String url = Managers.settings.getSetting(Constants.DB_URL);
			//Usuari
			String userName = Managers.settings.getSetting(Constants.DB_USERNAME);
			//Contrasenya
			String password = Managers.settings.getSetting(Constants.DB_PASSWORD);
			
			//Creem una instància de DatabaseManagmeent
			this._databaseManagement = new DatabaseManager(driver, url, userName, password);
			
		} catch (IOException e){
			throw new STException(e, TokenKeys.ERROR_CONFIGURATION_FILE);
			
		} catch (Exception e){
			throw new STException(e, TokenKeys.ERROR_UNEXPECTED);
		}
	}

	/**
	* Retorna una cadena que verifica la connexió
	* remota al servidor.
	* @return String
	* @throws STException
	* 
	*/ 
	@Override
	public String testRMIConnection() throws STException {
		return TokenKeys.TEST_CONNECTION;
	}

	/***
	 * Retorna una cadena que indica si
	 * funciona la connexió a la base de dades
	 * de PostgreSQL
	 * 
	 * @return boolean amb el resultat del test
	 * @throws STException
	 */
	@Override
	public String testPostgreSQLConnection() throws STException {
		return this._databaseManagement.testConnection();
	}

	/***
	 * Mètode que retorna una llista d'un
	 * taller específic o, si s'informa un 0,
	 * de tots els tallers.
	 * 
	 * @param shop Taller específic o 0 si
	 * es vol recuperar tots els tallers. 
	 * @return Una instància de List<Taller> amb
	 * el resultat de la consulta.
	 * @throws STException
	 */
	@Override
	public List<Taller> getShopList(int shop) throws STException {
		List<Taller> result = null;
		
		try {
			this._databaseManagement.openConnection();
			result = this._databaseManagement.getShopList(shop);
			this._databaseManagement.closeConnection();

		} catch (STException e){
			throw e;
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new STException(e);
		}
		return result;
	}
}
