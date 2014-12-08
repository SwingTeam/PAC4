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
import uoc.tdp.pac4.st.common.dto.*;
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
	 public int addUser(Usuari user) throws STException
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
	 public int addAlbara(Albara albara) throws STException
	 {
		 //Creem el database manager per conectar amb la BD 
		DatabaseManager databaseManager = new DatabaseManager();
		
		//Creem AlbaraManager i li passem el databaseManager
		AlbaraManager albaraManager = new AlbaraManager(databaseManager); 
		return albaraManager.add(albara);
	 }
	 
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
public List<Albara> listAlbaransByLocal(String localId) throws  STException
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
/*** END: Subsistema Connexió ****/			 
	 
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
	 
}
