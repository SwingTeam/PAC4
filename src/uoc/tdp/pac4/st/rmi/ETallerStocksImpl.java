package uoc.tdp.pac4.st.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.LocalSTer;
import uoc.tdp.pac4.st.common.dto.MotiuDevolucio;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.ProducteReport;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.dto.ReportSelectorData;
import uoc.tdp.pac4.st.common.dto.ReturningReportLine;
import uoc.tdp.pac4.st.common.dto.RotationReportLine;
import uoc.tdp.pac4.st.common.dto.SalesReportLine;
import uoc.tdp.pac4.st.common.dto.SalesSummaryReportLine;
import uoc.tdp.pac4.st.common.dto.StockOutReportLine;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.dto.Usuari;
import uoc.tdp.pac4.st.common.managers.AlbaraManager;
import uoc.tdp.pac4.st.common.managers.DatabaseManager;
import uoc.tdp.pac4.st.common.managers.DistribucioManager;
import uoc.tdp.pac4.st.common.managers.ExistenciesManager;
import uoc.tdp.pac4.st.common.managers.GrupManager;
import uoc.tdp.pac4.st.common.managers.LinAlbaraManager;
import uoc.tdp.pac4.st.common.managers.LocalManager;
import uoc.tdp.pac4.st.common.managers.MotiuDevolucioManager;
import uoc.tdp.pac4.st.common.managers.MovimentManager;
import uoc.tdp.pac4.st.common.managers.ProducteManager;
import uoc.tdp.pac4.st.common.managers.ProveidorManager;
import uoc.tdp.pac4.st.common.managers.ReportManager;
import uoc.tdp.pac4.st.common.managers.SolicitudManager;
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
	
	
	
	
/*** BEGIN: Subsistema Manteniment ****/
	/***
	  * @author emarsal2
	  * @since divendres 5
	  * Mètode que cerca les provincies de la BD i les posa en una llista.
	  * 
	  * @return List with province of DataBase 
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<String> getProvinceList() throws RemoteException, STException {
	 	List<String> pr = null;
		DatabaseManager db = new DatabaseManager();
		UserManager um = new UserManager (db);
		pr= um.getProvinceList();
		return pr;
	}
	 /**
    * @author emarsal2
	  * @since divendres 5
	  * Mètode que comprova si ja existeix el NIF d'un Usuari dins la BD
	  * @param nif String to look for inside BBDD
	  * @return true si el NIF s'ha trobat, false en cas contrari
	  * @throws RemoteException
	  * @throws STException

	  */
	 public boolean findNIF(String nif) throws RemoteException, STException {
		 DatabaseManager db = new DatabaseManager();
		 UserManager um = new UserManager(db);
		return um.findNIF(nif); 
	 }
	 
	 /***
	  * @author emarsal2
	  * @since divendres 5
	  * Mètode que comprova si ja existeix el CIF d'un Local dins la BD
	  * @param cif String to look for inside BBDD
	  * @return true si el CIF s'ha trobat, false en cas contrari
	  * @throws RemoteException
	  * @throws STException
	  * 
	  */
	 public boolean findCIF(String cif) throws RemoteException, STException {
		 DatabaseManager db = new DatabaseManager();
		 LocalManager lm = new LocalManager(db);
		return lm.findCIF(cif); 

	 }


	 /***
	  * @author emarsal2
	  * @since divendres 5
	  * Mètode que comprova si ja existeix el NOM d'un Local dins la BD
	  * @param name String to look for inside BBDD
	  * @return true si el NAME s'ha trobat, false en cas contrari
	  * @throws RemoteException
	  * @throws STException
	  * 
	  */
	 public boolean findName(String name) throws RemoteException, STException {
		 DatabaseManager db = new DatabaseManager();
		 LocalManager lm = new LocalManager(db);
		return lm.findName(name); 

	 }

	 
	 /**
	  * 
	  * @author emarsal2
	  * @since dilluns 8
	  * Retorna un enter amb el possible id per un nou usuari
	  * 
	  * @return id pel nou usuari que es vol crear
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String lastIdUser() throws RemoteException, STException {
	 	DatabaseManager db = new DatabaseManager();
		UserManager um = new UserManager (db);
		return um.getNextId();
	}
	
/**
	  * @author emarsal2
	  * @since divendres 5
	  * @param localName
	  * @return String with id_local
	  * 
	  */
	 public String getId_LocalwithName(String localName) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 UserManager um = new UserManager(db);
		 return um.getId_LocalwithName(localName);
	 }
	 
	 /***
	  * Afegeix un usuari
	  * @author emarsal2
	  * @since divendres 5
	  * @param user to add
	  * 
	  * @return  id del nou usuari? create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addUser(Usuari user) throws RemoteException, STException
	 {
		    DatabaseManager databaseManager = new DatabaseManager();
			UserManager um = new UserManager(databaseManager); 
			return um.Add(user);
	 }	 

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un id_usuari, dóna de baixa de la BBDD aquest usuari
	  * @param userId
	  * @throws RemoteException
	  * @throws STException
	  */
	 public void deleteUser (String userId) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 UserManager um = new UserManager(db);
		 um.delete(userId);
	 }
	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un usuari, modifica les dades d'aquest usuari dins la BBDD
	  * @param user un usuari amb els camps modificats 
	  * @return String amb l'id de l'usuari que s'ha modificat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String updateUser (Usuari user) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 UserManager um = new UserManager(db);
		 return um.update(user);
	 }
	 
	 /**
	  * @author emarsal2
	  * @since dissabte 6
	  * Mètode que serveix per cercar un usuari per un camp dins la BD
	  * @param id valor que cal cercar dins la BD
	  * @param field camp pel qual s'ha de fer la cerca del valor id
	  * @return Usuari que s'ha trobat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public Usuari userQuery (String id,String field) throws RemoteException, STException
	 {
		    DatabaseManager db = new DatabaseManager();
			UserManager um = new UserManager(db); 
			return um.searchUser(id,field);
	 }
	 /**
	  * @author emarsal2
	  * @since dissabte 6
	  * Mètode que serveix per cercar un Local per un camp dins la BD
	  * @param id valor que cal cercar dins la BD
	  * @param field camp pel qual s'ha de fer la cerca del valor id
	  * @return Local que s'ha trobat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public LocalSTer localQuery (String id,String field) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 LocalManager lm = new LocalManager(db);
		 return lm.searchLocal(id,field);
	 }
	 /***
	  * @author emarsal2
	  * @since dissabte 6
	  * Afegeix un Local
	  * 
	  * @return  id del nou Local creat
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addLocal(LocalSTer taller) throws RemoteException, STException
	 {
		    DatabaseManager databaseManager = new DatabaseManager();
			LocalManager lm = new LocalManager(databaseManager); 
			return lm.add(taller);
	 }

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un Nom de Taller, dóna de baixa de la BBDD aquest Taller
	  * @param Nom del taller
	  * @throws RemoteException
	  * @throws STException
	  */
	 public void deleteLocal (int idTaller) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 LocalManager lm = new LocalManager(db);
		 lm.deleteLocal(idTaller);
	 }

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un Taller, modifica les dades d'aquest Taller dins la BBDD
	  * @param taller un Local amb els camps modificats 
	  * @return String amb el nom del Taller que s'ha modificat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String updateLocal (LocalSTer taller) throws RemoteException, STException
	 {
		 DatabaseManager db = new DatabaseManager();
		 LocalManager ul = new LocalManager(db);
		 return ul.updateLocal(taller);
	 }
	 	 
	/*** END: Subsistema Manteniment*******/	
	
	 /*** BEGIN: Subsistema control de fluxe ****/

	 
	 /***
	  * LLista tots els proveidors
	  * 
	  * @return  List<Proveidor>  Llistat de proveidors
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<Proveidor> listProveidors() throws STException {
		DatabaseManager databaseManager = new DatabaseManager();
		ProveidorManager manager = new ProveidorManager(databaseManager );
		return manager.list();
  	 }
  	 
  	 
	 
	 /***
	  * LLista tots els grups
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<Grup> listGrups() throws STException {
		DatabaseManager databaseManager = new DatabaseManager();
		GrupManager manager= new GrupManager(databaseManager );
		return manager.list();
  	 }
  	 
	 /***
	  * LLista els subgrups d'un grup
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<SubGrup> getSubGrupsByGrup(Integer grupId) throws  STException {
		DatabaseManager databaseManager = new DatabaseManager();
		SubGrupManager manager= new SubGrupManager(databaseManager );
		return manager.getByGrupId(grupId);
  	 }
  	 
  	 /***
	  * 
	  * Torna tots els productes per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte> searchProdutes(String proveidorId, Integer grupId, Integer subGrupId) throws STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		ProducteManager manager= new ProducteManager(databaseManager );
		return manager.search(proveidorId, grupId, subGrupId);		
	}
	
	 /***
	  * LLista tots els motius devolucions
	  * 
	  * @return  List<Proveidor>  Llistat de motius devolucions
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<MotiuDevolucio> listMotiuDevolucio() throws STException	
	 {
		DatabaseManager databaseManager = new DatabaseManager();
		MotiuDevolucioManager manager= new MotiuDevolucioManager(databaseManager );
		return manager.list();	 	 
	 }
	 
	 /***
	  * Torna existencies per local i producte
	  * 
	  * @return Existencies Existencia
	  * @throws RemoteException
	  * @throws STException
	  */	  
	 public Existencies getExistenciesByProducteAndLocal(String producteId, String localId)throws STException
	 {
		DatabaseManager databaseManager = new DatabaseManager();
		ExistenciesManager manager= new ExistenciesManager(databaseManager );
		return manager.getByProducteAndLocal(producteId, localId);	 	 		 
	 }	 
	 
	 /***
	  * LLista tots els locals
	  * 
	  * @return  List<Local>  Llistat de locals
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<LocalST> listLocals() throws  STException	
	 {
		DatabaseManager databaseManager = new DatabaseManager();
		LocalManager manager= new LocalManager(databaseManager );
		return manager.list();	 	 
	 }
 	 
		
	
	 /***
	  * 
	  * Torna tots els productes amb estoc minim per un local 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte>getStockMinim(String localId) throws  STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		ProducteManager manager= new ProducteManager(databaseManager);
		return manager.getStockMinim(localId);	
	}
	
	 /***
	  * 
	  * Torna tots els albarans de tipus transferencia d'un local 
	  * 
	  * @return List<Albara> LLista d'albarans 
	  * @throws STException 
	  */	
	public List<Albara> listAlbaransRecepcioByLocal(String localId) throws  STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		AlbaraManager manager= new AlbaraManager(databaseManager);
		return manager.listByDestiAndTipusMoviment(localId, MovimentManager.TIPUS_MOVIMENT_TRANSFERENCIA);	
	}
	
	
	 /***
	  * 
	  * Torna un albara pel seu id 
	  * 
	  * @return Albara albara 
	  * @throws STException 
	  */	
	public Albara getAlbaraById(int albaraId) throws STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		AlbaraManager manager= new AlbaraManager(databaseManager);
		return manager.getById(albaraId);	
	}
		
	 /***
	  * 
	  * Torna linies d'un albarà 
	  * 
	  * @return Linies albara 
	  * @throws STException 
	  */	
	public List<LinAlbara> getLinAlbaraByAlbaraId(int albaraId) throws  STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		LinAlbaraManager manager= new LinAlbaraManager(databaseManager);
		return manager.getByAlbaraId(albaraId);	
	}
	
	/***
	 * 
	 * Torna tots els productes amb el seu estoc per proveidor, grup i subgrup 
	 * 
	 * @return List<Producte> LLista de productes 
	 * @throws STException 
	 */	
	public List<Producte> stockSearch(Integer grupId, Integer subGrupId, String producteId, String localId, Integer stockInicial, Integer stockFinal) throws STException 
	{
		DatabaseManager databaseManager = new DatabaseManager();
		ProducteManager producteManager = new ProducteManager (new DatabaseManager());	
		return producteManager.stockSearch(grupId, subGrupId, producteId, localId, stockInicial, stockFinal);
		
	}
	
	/***
	 * 
	 * Torna linies d'albara per demanda actual
	 * 
	 * @throws STException 
	 */	
	public ArrayList<LinAlbara> getByDemandaActual(String localDestiId, String localOrigenId) throws STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		LinAlbaraManager linAlbaraManager = new LinAlbaraManager(databaseManager); 
		return linAlbaraManager.getByDemandaActual(localDestiId, localOrigenId);
	}
							
	/***
	 * 
	 * Torna linies d'albara per ruptura stock
	 * 
	 * @throws STException 
	 */	
	public ArrayList<LinAlbara> getByRupturaStock(String localDestiId, String localOrigenId) throws STException
	{
		DatabaseManager databaseManager = new DatabaseManager();
		LinAlbaraManager linAlbaraManager = new LinAlbaraManager(databaseManager); 
		return linAlbaraManager.getByRupturaStock(localDestiId, localOrigenId);
	}
	
	/***
	 * Demana les peces de l'albara
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public int demanarPeces(Albara albara) throws STException
	{
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		SolicitudManager manager = new SolicitudManager(databaseManager); 
		return manager.demanarPeces(albara);
	}
	
	
	/***
	 * Distribueix peces als tallers
	 * 
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public void Distribuir(String localOrigenId, ArrayList<LinAlbara> listLinAlbara) throws STException
	{
		DistribucioManager distribucioManager = new DistribucioManager(new DatabaseManager());
		distribucioManager.Distribuir(localOrigenId, listLinAlbara);
	}
	
	/***
	 * recepciona peces 
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public int recepcionarPeces(Albara albara) throws STException
	{
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		SolicitudManager manager = new SolicitudManager(databaseManager); 
		return manager.recepcionarPeces(albara);
	}	
	
	
	
	/***
	 * Recepciona peces taller
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException 
	 */ 
	public void recepcionarPecesTaller(Albara albara) throws STException
	{
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		SolicitudManager manager = new SolicitudManager(databaseManager); 
		manager.recepcionarPecesTaller(albara);
	}		
	
	/***
	 * Retorna peces 
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public int retornarPeces(Albara albara) throws STException
	{
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		SolicitudManager manager = new SolicitudManager(databaseManager); 
		return manager.retornarPeces(albara);
	}	
	

	
	/*** END: Subsistema control de fluxe ****/			 
	 
	 /***
	  * Retorna una llista d'un producte específic o de
	  * tots els productes, si s'indica null al 
	  * paràmetre productId.
	  * 
	  * @param productId Codi d'un producte o null si
	  * es vol recuperar tots els productes.
	  * @return List<ProducteReport> Una instància de 
	  * List<ProducteReport> amb el resultat de la consulta.
	  * @throws STException
	  */
	 public List<ProducteReport> getProductList(String productId) throws STException{
		 //Creem el database manager per a conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem ReportManager i li passem el databaseManager
		ReportManager reportManager = new ReportManager(databaseManager); 
		return reportManager.getProductList(productId);
	 }
	 
	 /***
	  * Retorna una llista de línies de l'informe
	  * de ruptures d'estoc.
	  * 
	  * @param reportSelectorData Instància de ReportSelectorData
	  * que conté la selecció del rang de resultats que ha
	  * fet l'usuari.
	  * @return List<StockOutReportLine> Retorna una llista d'objectes
	  * StockOutReportLine, amb el resultat de l'informe demanat.
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<StockOutReportLine> getStockOutReport(ReportSelectorData reportSelectorData) throws STException{
		 //Creem el database manager per a connectar amb la base de dades
		 DatabaseManager databaseManager = new DatabaseManager();
		 
		 //Creem una instància de ReportManager i
		 //li passem el databaseManager
		 ReportManager reportManager = new ReportManager(databaseManager);
		 return reportManager.getStockOutReport(reportSelectorData);
	 }
	 
	 /***
	  * Retorna una llista de línies de l'informe
	  * de rotació d'estoc.
	  * 
	  * @param reportSelectorData Instància de ReportSelectorData
	  * que conté la selecció del rang de resultats que ha
	  * fet l'usuari.
	  * @return List<RotationReportLine> Retorna una llista d'objectes
	  * RotationReportLine, amb el resultat de l'informe demanat.
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<RotationReportLine> getRotationReport(ReportSelectorData reportSelectorData) throws STException{
		 //Creem el database manager per a connectar amb la base de dades
		 DatabaseManager databaseManager = new DatabaseManager();
		 
		 //Creem una instància de ReportManager i
		 //li passem el databaseManager
		 ReportManager reportManager = new ReportManager(databaseManager);
		 return reportManager.getRotationReport(reportSelectorData);
	 }
	 
	 /***
	  * Retorna una llista de línies de l'informe
	  * de demanda de recanvis.
	  * 
	  * @param reportSelectorData Instància de ReportSelectorData
	  * que conté la selecció del rang de resultats que ha
	  * fet l'usuari.
	  * @return List<SalesReportLine> Retorna una llista d'objectes
	  * SalesReportLine, amb el resultat de l'informe demanat.
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<SalesReportLine> getSalesReport(ReportSelectorData reportSelectorData) throws STException{
		 //Creem el database manager per a connectar amb la base de dades
		 DatabaseManager databaseManager = new DatabaseManager();
		 
		 //Creem una instància de ReportManager i
		 //li passem el databaseManager
		 ReportManager reportManager = new ReportManager(databaseManager);
		 return reportManager.getSalesReport(reportSelectorData);
	 }
	 
	 /***
	  * Retorna un Usuari
	  * 
	  * 
	  * @param nom nom de l'usuari
	  * password password de lusuari
	  * 
	  * @return Usuari
	  * 
	  * @throws STException
	  */
	 
	 public Usuari Login(String nom, String password) throws STException
	 {
		 //Creem el database manager per conectar amb la BD 
			DatabaseManager databaseManager = new DatabaseManager();
			UserManager usuarimanager = new UserManager(databaseManager);
			return usuarimanager.Login(nom,password);
	 }
	 
	 /***
	  * Retorna un int
	  * 
	  * 
	  * @param idusuari id de l'usuari
	  * password password de lusuari
	  * 
	  * @return Int 1-ok 0-ko
	  * 
	  * 
	  * @throws STException 
	  */
	 
	 public int canviPassword(String idusuari,String password) throws STException
	 {
		DatabaseManager db = new DatabaseManager();
		UserManager usuarimanager = new UserManager(db);
		
			return usuarimanager.canviPassword(idusuari, password);
		
		 
	 }
	 
	 /***
	  * LLista tots els locals
	  * 
	  * @param usuari Instància d'Usuari amb
	  * les dades de l'usuari que fa la crida.
	  * @return  List<Local>  Llistat de locals
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<LocalST> listLocals(Usuari usuari) throws  STException	
	 {
		DatabaseManager databaseManager = new DatabaseManager();
		LocalManager manager= new LocalManager(databaseManager );
		return manager.list(usuari);	 	 
	 }
	 
	 /***
	  * Retorna una llista de línies de l'informe
	  * de devolució de recanvis.
	  * 
	  * @param reportSelectorData Instància de ReportSelectorData
	  * que conté la selecció del rang de resultats que ha
	  * fet l'usuari.
	  * @return List<ReturningReportLine> Retorna una llista d'objectes
	  * ReturningReportLine, amb el resultat de l'informe demanat.
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<ReturningReportLine> getReturningReport(ReportSelectorData reportSelectorData) throws RemoteException, STException{
		 //Creem el database manager per a connectar amb la base de dades
		 DatabaseManager databaseManager = new DatabaseManager();
		 
		 //Creem una instància de ReportManager i
		 //li passem el databaseManager
		 ReportManager reportManager = new ReportManager(databaseManager);
		 return reportManager.getReturningReport(reportSelectorData);
	 }

	 /***
	  * Retorna una llista de línies de l'informe
	  * global de vendes de recanvis.
	  * 
	  * @param reportSelectorData Instància de ReportSelectorData
	  * que conté la selecció del rang de resultats que ha
	  * fet l'usuari.
	  * @return List<SalesSummaryReportLine> Retorna una llista d'objectes
	  * SalesSummaryReportLine, amb el resultat de l'informe demanat.
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<SalesSummaryReportLine> getSalesSummaryReport(ReportSelectorData reportSelectorData) throws RemoteException, STException{
		 //Creem el database manager per a connectar amb la base de dades
		 DatabaseManager databaseManager = new DatabaseManager();
		 
		 //Creem una instància de ReportManager i
		 //li passem el databaseManager
		 ReportManager reportManager = new ReportManager(databaseManager);
		 return reportManager.getSalesSummaryReport(reportSelectorData);
		 
	 }


	 
}
