package uoc.tdp.pac4.st.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.*;
import uoc.tdp.pac4.st.client.cx.*;
import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;
import uoc.tdp.pac4.st.server.*;

/***
 * Interface per a la connexió RMI
 * 
 * @author Swing Team - 2014
 *
 */
public interface ETallerStocksInterface extends Remote {

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
	 public List<Taller> getShopList(int shop) throws RemoteException, STException;
	 
	 /***
	  * Retorna un boolean que indica si
	  * funciona la connexió a la base de dades
	  * de PostgreSQL
	  * 
	  * @return boolean amb el resultat del test
	  * @throws java.rmi.RemoteException
	  */
	 public String testPostgreSQLConnection() throws RemoteException, STException;
	
	 /***
	  * Retorna una cadena que verifica la connexió
	  * remota al servidor.
	  * @return String Cadena que indica que la connexió
	  * funciona correctament.
	  * @throws RemoteException
	  * 
	  */ 
	 public String testRMIConnection() throws RemoteException, STException;
	
}
