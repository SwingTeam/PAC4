package uoc.tdp.pac4.st.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Existencies;
import uoc.tdp.pac4.st.common.dto.Grup;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.Local;
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.MotiuDevolucio;
import uoc.tdp.pac4.st.common.dto.Producte;
import uoc.tdp.pac4.st.common.dto.Proveidor;
import uoc.tdp.pac4.st.common.dto.SubGrup;
import uoc.tdp.pac4.st.common.dto.Usuari;

/***
 * Interface per a la connexió RMI
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
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


	 /**
	  * Retorna String amb l'idUsuari m�s gran que de moment hi ha a la BD
	  * si no hi ha usuaris retorna String a null
	  * @return String id_usuari m�s gran
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String lastIdUser() throws RemoteException, STException;
	 /***
	  * Afegeix un usuari
	  * 
	  * @return  id del nou usuari� create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addUser(Usuari user) throws RemoteException, STException;
	 
	 public String getId_LocalwithName(String localName) throws RemoteException, STException;
	 
	 
	 /*** BEGIN: Subsistema Connexió ****/
	 
	 /***
	  * Afegeix un albara i les seves linies a la base de dades
	  * 
	  * @return int id del nou albarà create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int addAlbara(Albara albara) throws RemoteException, STException;
	 
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
	public List<Albara> listAlbaransByLocal(String localId) throws RemoteException, STException;
	
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
		
	/*** END: Subsistema Connexió ****/

}
