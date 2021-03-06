package uoc.tdp.pac4.st.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.*;

/***
 * Interface per a la connexió RMI
 * 
 * @author Swing Team - 2014
 * 
 */
public interface ETallerStocksInterface extends Remote {

	/***
	 * Mètode que retorna una llista d'un
	 * local específic o, si s'informa un null,
	 * de tots els locals.
	 * 
	 * @param taxId Local específic o null si
	 * es vol recuperar tots els locals. 
	 * @return Una instància de List<Local> amb
	 * el resultat de la consulta.
	 * @throws java.rmi.RemoteException
	 * @throws STException
	 */
	 public List<Local> getEstablishmentList(String taxId) throws RemoteException, STException;
	 
	 /***
	  * Retorna un boolean que indica si
	  * funciona la connexió a la base de dades
	  * de PostgreSQL
	  * 
	  * @return boolean amb el resultat del test
	  * @throws java.rmi.RemoteException
	  * @throws STException
	  */
	 public String testPostgreSQLConnection() throws RemoteException, STException;
	
	 /***
	  * Retorna una cadena que verifica la connexió
	  * remota al servidor.
	  * @return String Cadena que indica que la connexió
	  * funciona correctament.
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public String testRMIConnection() throws RemoteException, STException;



	 /********** BEGIN: Subsistema de Manteniment ************/
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
	 public String lastIdUser() throws RemoteException, STException;	 
	 
	 /***
	  * Afegeix un usuari
	  * 
	  * @return  id del nou usuari? create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addUser(Usuari user) throws RemoteException, STException;

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un id_usuari, dóna de baixa de la BBDD aquest usuari
	  * @param userId
	  * @throws RemoteException
	  * @throws STException
	  */
	 public void deleteUser (String userId) throws RemoteException, STException;

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un usuari, modifica les dades d'aquest usuari dins la BBDD
	  * @param user un usuari amb els camps modificats 
	  * @return String amb l'id de l'usuari que s'ha modificat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String updateUser (Usuari user) throws RemoteException, STException;
	 
	 public String getId_LocalwithName(String localName) throws RemoteException, STException;
	 
	 /***
	  * @author emarsal2
	  * @since divendres 5
	  * Mètode que cerca les provincies de la BD i les posa en una llista.
	  * 
	  * @return List with province of DataBase 
	  * @throws RemoteException
	  * @throws STException
	  */
	 public List<String> getProvinceList() throws RemoteException, STException;

	 /***
	  * @author emarsal2
	  * @since divendres 5
	  * Mètode que comprova si ja existeix el NIF d'un Usuari dins la BD
	  * @param nif String to look for inside BBDD
	  * @return true si el NIF s'ha trobat, false en cas contrari
	  * @throws RemoteException
	  * @throws STException
	  * 
	  */
	 public boolean findNIF(String nif) throws RemoteException, STException;
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
	 public boolean findCIF(String cif) throws RemoteException, STException;

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
	 public boolean findName(String name) throws RemoteException, STException;
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
	 public Usuari userQuery (String id,String field) throws RemoteException, STException;


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
	 public LocalSTer localQuery (String id,String field) throws RemoteException, STException;

	 
	 /***
	  * @author emarsal2
	  * @since dissabte 6
	  * Afegeix un Local
	  * 
	  * @return  id del nou Local creat
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addLocal(LocalSTer taller) throws RemoteException, STException;

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un id de Taller, dóna de baixa de la BBDD aquest Taller
	  * @param id del taller
	  * @throws RemoteException
	  * @throws STException
	  */
	 public void deleteLocal (int idTaller) throws RemoteException, STException;

	 /**
	  * @author emarsal2
	  * @since diumenge 7
	  * Mètode que donat un Taller, modifica les dades d'aquest Taller dins la BBDD
	  * @param taller un Local amb els camps modificats 
	  * @return String amb el nom del Taller que s'ha modificat
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String updateLocal (LocalSTer taller) throws RemoteException, STException;
	 
	 
	 /***  END: Subsistema de Manteniment ****/	 
	 
	 /*** BEGIN: Subsistema control de fluxe****/
	 	 
	 /***
	  * LLista tots els proveidors
	  * 
	  * @return  List<Proveidor>  Llistat de proveidors
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<Proveidor> listProveidors() throws RemoteException, STException;
	 
	 /***
	  * LLista tots els grups
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<Grup> listGrups() throws  RemoteException,STException;
  	 
	 /***
	  * LLista els subgrups d'un grup
	  * 
	  * @return  List<Grup>  Llistat de grups
	  * @throws RemoteException
	  * @throws STException
	  */	 
  	 public List<SubGrup> getSubGrupsByGrup(Integer grupId) throws  RemoteException,STException;
	 
  	 
  	 /***
	  * 
	  * Torna tots els productes per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte> searchProdutes(String proveidorId, Integer grupId, Integer subGrupId) throws RemoteException,STException; 

	 /***
	  * LLista tots els motius devolucions
	  * 
	  * @return  List<Proveidor>  Llistat de motius devolucions
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<MotiuDevolucio> listMotiuDevolucio() throws RemoteException, STException;
	 

	 /***
	  * Torna existencies per local i producte
	  * 
	  * @return Existencies Existencia
	  * @throws RemoteException
	  * @throws STException
	  */	  
	 public Existencies getExistenciesByProducteAndLocal(String producteId, String localId)throws RemoteException, STException;
	 
	 
	 /***
	  * LLista tots els locals
	  * 
	  * @return  List<Local>  Llistat de locals
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<LocalST> listLocals() throws RemoteException, STException;		

	 /***
	  * 
	  * Torna tots els productes amb estoc minim per un local 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */	
	public List<Producte>getStockMinim(String localId) throws RemoteException, STException;

	 /***
	  * 
	  * Torna tots els albarans de tipus transferencia d'un local 
	  * 
	  * @return List<Albara> LLista d'albarans 
	  * @throws STException 
	  */	
	public List<Albara> listAlbaransRecepcioByLocal(String localId) throws RemoteException, STException;
	
	 /***
	  * 
	  * Torna un albara pel seu id 
	  * 
	  * @return Albara albara 
	  * @throws STException 
	  */	
	public Albara getAlbaraById(int albaraId) throws RemoteException, STException;
	
	
	 /***
	  * 
	  * Torna linies d'un albarà 
	  * 
	  * @return Linies albara 
	  * @throws STException 
	  */	
	public List<LinAlbara> getLinAlbaraByAlbaraId(int albaraId) throws RemoteException, STException;
		
	 /***
	  * 
	  * Torna tots els productes amb el seu estoc per proveidor, grup i subgrup 
	  * 
	  * @return List<Producte> LLista de productes 
	  * @throws STException 
	  */		
	public List<Producte> stockSearch(Integer grupId, Integer subGrupId, String producteId, String localId, Integer stockInicial, Integer stockFinal) throws RemoteException, STException;
	
	
	/***
	 * 
	 * Torna linies d'albara per demanda actual
	 * 
	 * @throws STException 
	 */	
	public ArrayList<LinAlbara> getByDemandaActual(String localDestiId, String localOrigenId) throws RemoteException, STException;
	

	/***
	 * 
	 * Torna linies d'albara per ruptura estoc
	 * 
	 * @throws STException 
	 */	
	
	public ArrayList<LinAlbara> getByRupturaStock(String localDestiId, String localOrigenId) throws RemoteException, STException;
	
	 /***
	  * Afegeix un albara i les seves linies a la base de dades
	  * 
	  * @return int id del nou albarà create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	public int demanarPeces(Albara albara) throws RemoteException, STException;
	
	
	/***
	 * recepciona peces 
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public int recepcionarPeces(Albara albara) throws RemoteException, STException;	

	/***
	 * recepciona peces 
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public void recepcionarPecesTaller(Albara albara) throws RemoteException, STException;
	
	/***
	 * Retorna peces 
	 * 
	 * @return int id del nou albarà creat
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public int retornarPeces(Albara albara) throws RemoteException, STException;	
	
	
	/***
	 * Distribueix peces als tallers
	 * 
	 * @throws RemoteException
	 * @throws STException
	 */ 
	public void Distribuir(String localOrigenId, ArrayList<LinAlbara> listLinAlbara) throws RemoteException, STException;
	
	
	/*** END: Subsistema control fluxe****/
	
	

	
	
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
	 public List<ProducteReport> getProductList(String productId) throws RemoteException, STException;
	 
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
	 public List<StockOutReportLine> getStockOutReport(ReportSelectorData reportSelectorData) throws RemoteException, STException;
	 
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
	 public List<RotationReportLine> getRotationReport(ReportSelectorData reportSelectorData) throws RemoteException, STException;

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
	 public List<SalesReportLine> getSalesReport(ReportSelectorData reportSelectorData) throws RemoteException, STException;
	 
	 /***
	  * Retorna un usuari
	  * 
	  * 
	  * @param nom nom de l'usuari
	  * password password de lusuari
	  * 
	  * @return Usuari
	  * 
	  * @throws RemoteException
	  * @throws STException
	  */
	 public Usuari Login(String nom, String password) throws RemoteException, STException;
	 
	 /***
	  * Retorna un int
	  * 
	  * 
	  * @param idusuari id de l'usuari
	  * password password de lusuari 
	  * 
	  * @return Int 1-ok 0-ko
	  * 
	  * @throws RemoteException
	  * @throws STException
	  */
	 public int canviPassword(String idusuari,String password) throws RemoteException, STException;

	 /***
	  * LLista tots els locals
	  * 
	  * @param usuari Instància d'Usuari amb les dades
	  * de l'usuari que està fent la crida.
	  * @return  List<Local>  Llistat de locals
	  * @throws RemoteException
	  * @throws STException
	  */	 
	 public List<LocalST> listLocals(Usuari usuari) throws RemoteException, STException;		

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
	 public List<ReturningReportLine> getReturningReport(ReportSelectorData reportSelectorData) throws RemoteException, STException;

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
	 public List<SalesSummaryReportLine> getSalesSummaryReport(ReportSelectorData reportSelectorData) throws RemoteException, STException;

	 
}
