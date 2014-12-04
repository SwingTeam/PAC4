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
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Local;
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

	/***
	 * Constructor
	 * @throws STException
	 * @throws RemoteException
	 */
	public ETallerStocksImpl() throws RemoteException, STException {
		super();
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
		String result = null;
		DatabaseManager databaseManager = new DatabaseManager();
		result = databaseManager.testConnection();
		databaseManager = null;
		return result;
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
	public List<Local> getEstablishmentList(String taxId) throws STException {
		List<Local> result = null;
		DatabaseManager databaseManager = new DatabaseManager();
		result = databaseManager.getEstablishmentList(taxId);
		databaseManager = null;
		return result;
	}
	
	 /***
	  * Afegeix un albara i les seves linies a la base de dades
	  * 
	  * @return int id del nou albarà create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int AddAlbara(Albara albara) throws STException
	 {
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		AlbaraManager albaraManager = new AlbaraManager(databaseManager); 
		return albaraManager.Add(albara);
	 }

	 /**
	  * Retorna String amb l'idUsuari m�s gran que de moment hi ha a la BD
	  * si no hi ha usuaris retorna String a null
	  * @return String id_usuari m�s gran
	  * @throws RemoteException
	  * @throws STException
	  */

	 public String lastIdUser() throws RemoteException, STException
	 {
		 String idUser=null;
		 DatabaseManager databaseManager = new DatabaseManager();
			long l = databaseManager.countTableRows("Usuari"); // prova per� no �s el definitiu , cal fer un m�tode nou a DatabaseManager
			idUser = Long.toString(l);
			databaseManager = null;
			return idUser;
	 }
}
