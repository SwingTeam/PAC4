package uoc.tdp.pac4.st.common;

/***
 * Classe que conté totes les enums d'ús comú
 * a l'aplicació
 * 
 * @author Swing Team - 2014
 *
 */
public class Enums {
	
	//Tipus de missatge
	public enum MessageType{
		None,
		Error,
		Info,
		Warning,
	}
	
	//Estat del Server 
	public enum ServerStatus{
		Stopped,
		Running,
	}
	
	//Estat de la connexió remota
	public enum ConnectionStatus{
		Open,
		Closed,
	}
}
