package edu.uoc.tdp.pac4.et.rmi;

import java.sql.SQLException;
import java.util.List;

import edu.uoc.tdp.pac4.et.common.*;
import edu.uoc.tdp.pac4.et.database.*;

/***
 * Interface per a la connexió RMI
 * 
 * @author Ester Marsal i Toni Casas - 2014
 *
 */
@SuppressWarnings("unused")
public interface PeticionsInterface extends java.rmi.Remote {

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
	 public List<Taller> getShopList(int shop) throws java.rmi.RemoteException;
	 
	 /***
	  * Retorna un boolean que indica si
	  * funciona la connexió a la base de dades
	  * de PostgreSQL
	  * 
	  * @return boolean amb el resultat del test
	  * @throws java.rmi.RemoteException
	  */
	 public String testPostgreSQLConnection() throws java.rmi.RemoteException;
	
	 /***
	  * Retorna una cadena que verifica la connexió
	  * remota al servidor.
	  * @return String Cadena que indica que la connexió
	  * funciona correctament.
	  * @throws RemoteException
	  * 
	  */ 
	 public String testRMIConnection() throws java.rmi.RemoteException;
	
}
