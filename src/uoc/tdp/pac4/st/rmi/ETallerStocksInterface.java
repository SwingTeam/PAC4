package uoc.tdp.pac4.st.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Local;
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

	 /***
	  * Afegeix un albara i les seves linies a la base de dades
	  * 
	  * @return int id del nou albarà create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int AddAlbara(Albara albara) throws RemoteException, STException;

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

}
