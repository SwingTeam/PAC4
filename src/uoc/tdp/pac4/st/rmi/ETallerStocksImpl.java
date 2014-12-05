package uoc.tdp.pac4.st.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.AlbaraManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.GrupManager;
import uoc.tdp.pac4.st.common.managers.ProducteManager;
import uoc.tdp.pac4.st.common.managers.ProveidorManager;
import uoc.tdp.pac4.st.common.managers.SubGrupManager;
import uoc.tdp.pac4.st.common.managers.UserManager;

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
	
			
	 public String getId_LocalwithName(String localName) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 UserManager um = new UserManager(db);
		 return um.getId_LocalwithName(localName);
	 }
	 
	 /***
	  * Afegeix un usuari
	  * 
	  * @return  id del nou usuari� create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addUser(Usuari user) throws RemoteException, STException
	 {
		    DatabaseManager databaseManager = new DatabaseManager();
			UserManager um = new UserManager(databaseManager); 
			return um.Add(user);
	 }	 
	 
	 /*** BEGIN: Subsistema Connexió ****/
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
	 
	 /***
	  * LLista tots els proveidors
	  * 
	  * @return  List<Proveidor>  Llistat de proveidors
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<Proveidor> listProveidors() throws  RemoteException,STException {
		DatabaseManager databaseManager = new DatabaseManager();
		ProveidorManager manager = new ProveidorManager(databaseManager );
		return manager.List();
  	 }
  	 
  	 
	 
	 /***
	  * LLista tots els grups
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<Grup> listGrups() throws  RemoteException,STException {
		DatabaseManager databaseManager = new DatabaseManager();
		GrupManager manager= new GrupManager(databaseManager );
		return manager.List();
  	 }
  	 
	 /***
	  * LLista els subgrups d'un grup
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<SubGrup> getSubGrupsByGrup(Integer grupId) throws  RemoteException,STException {
		DatabaseManager databaseManager = new DatabaseManager();
		SubGrupManager manager= new SubGrupManager(databaseManager );
		return manager.GetByGrupId(grupId);
  	 }
  	 
  	 /***
	  * 
	  * Torna tots els productes per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte> SearchProdutes(String proveidorId, Integer grupId, Integer subGrupId) throws RemoteException,STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		ProducteManager manager= new ProducteManager(databaseManager );
		return manager.Search(proveidorId, grupId, grupId);		
	}
	
  	 
	/*** END: Subsistema Connexió ****/		
}
