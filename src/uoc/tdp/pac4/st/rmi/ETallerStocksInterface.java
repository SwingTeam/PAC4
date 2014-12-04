package uoc.tdp.pac4.st.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.dto.Albara;
import uoc.tdp.pac4.st.common.dto.Local;

/***
 * Interface per a la connexi贸 RMI
 * 
 * @author Swing Team - 2014
 *
 */
@SuppressWarnings("unused")
public interface ETallerStocksInterface extends Remote {

	/***
	 * M猫tode que retorna una llista d'un
	 * local espec铆fic o, si s'informa un null,
	 * de tots els locals.
	 * 
	 * @param taxId Local espec铆fic o null si
	 * es vol recuperar tots els locals. 
	 * @return Una inst脿ncia de List<Local> amb
	 * el resultat de la consulta.
	 * @throws java.rmi.RemoteException
	 * @throws STException
	 */
	 public List<Local> getEstablishmentList(String taxId) throws RemoteException, STException;
	 
	 /***
	  * Retorna un boolean que indica si
	  * funciona la connexi贸 a la base de dades
	  * de PostgreSQL
	  * 
	  * @return boolean amb el resultat del test
	  * @throws java.rmi.RemoteException
	  * @throws STException
	  */
	 public String testPostgreSQLConnection() throws RemoteException, STException;
	
	 /***
	  * Retorna una cadena que verifica la connexi贸
	  * remota al servidor.
	  * @return String Cadena que indica que la connexi贸
	  * funciona correctament.
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public String testRMIConnection() throws RemoteException, STException;

	 /***
	  * Afegeix un albara i les seves linies a la base de dades
	  * 
	  * @return int id del nou albar脿 create
	  * @throws RemoteException
	  * @throws STException
	  */ 
	 public int AddAlbara(Albara albara) throws RemoteException, STException;

	 /**
	  * Retorna String amb l'idUsuari m閟 gran que de moment hi ha a la BD
	  * si no hi ha usuaris retorna String a null
	  * @return String id_usuari m閟 gran
	  * @throws RemoteException
	  * @throws STException
	  */
	 public String lastIdUser() throws RemoteException, STException;
}
