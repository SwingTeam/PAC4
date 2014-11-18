package edu.uoc.tdp.pac4.et.rmi;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import edu.uoc.tdp.pac4.et.common.*;
import edu.uoc.tdp.pac4.et.database.*;

/***
 * Classe que implementa la interface
 * PeticionsImpl para la connexió RMI.
 * 
 * @author Ester Marsal i Toni Casas - 2014
 *
 */
@SuppressWarnings("unused")
public class PeticionsImpl extends UnicastRemoteObject implements PeticionsInterface, Serializable {

	private static final long serialVersionUID = -2041907125638859914L;
	private DatabaseManagement _databaseManagement = null;
	
	/***
	 * Constructor
	 * @throws IOException 
	 */
	public PeticionsImpl() throws RemoteException {
		super();
		try{
			//Creem una instància de settings per a
			//llegir els valors de configuració de l'accés
			//a la base de dades
			Settings settings = new Settings(Constants.DB_CONFIGURATION_FILE);
			
			//Llegim els paràmetres d'accés
			//Driver de java per a la connexió a postgreSQL
			String driver = settings.getSetting(Constants.DB_DRIVER);
			//Url d'accés a la base de dades
			String url = settings.getSetting(Constants.DB_URL);
			//Usuari
			String userName = settings.getSetting(Constants.DB_USERNAME);
			//Contrasenya
			String password = settings.getSetting(Constants.DB_PASSWORD);
			
			//Alliberem memòria
			settings = null;
					
			//Creem una instància de DatabaseManagmeent
			this._databaseManagement = new DatabaseManagement(driver, url, userName, password);
			
		} catch (IOException e){
			throw new RemoteException(TokenKeys.ERROR_CONFIGURATION_FILE);
		}
	}

	/**
	* Retorna una cadena que verifica la connexió
	* remota al servidor.
	* @return String
	* @throws RemoteException
	* 
	*/ 
	@Override
	public String testRMIConnection() throws RemoteException {
		return TokenKeys.TEST_CONNECTION;
	}

	/***
	 * Retorna una cadena que indica si
	 * funciona la connexió a la base de dades
	 * de PostgreSQL
	 * 
	 * @return boolean amb el resultat del test
	 * @throws java.rmi.RemoteException
	 */
	@Override
	public String testPostgreSQLConnection() throws RemoteException {
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
	 * @throws java.rmi.RemoteException
	 */
	@Override
	public List<Taller> getShopList(int shop) throws RemoteException {
		List<Taller> result = null;
		
		try {
			this._databaseManagement.openConnection();
			result = this._databaseManagement.getShopList(shop);
			this._databaseManagement.closeConnection();

		} catch (SQLException | ClassNotFoundException e) {
			throw new RemoteException(e.getMessage());
		}
		return result;
	}
}
