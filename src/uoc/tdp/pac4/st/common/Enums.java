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
	
	//Ordre dels informes
	public enum ReportOrder{
		Asc,
		Desc,
	}

	/***
	 * Tipus de node pels selectors
	 * de range d'informes.
	 */
	public enum NodeType{
		None,
		Group,
		Product,
		Root,
		Subgroup,
	}
	
	/***
	 * Tipus de moviment de material.
	 * Aquesta enum reflecteix els
	 * registres de la taula TipusMoviment.
	 */
	public enum MovementType{
		Transferencia,
		Venda,
		Compra,
		SortidaDevolucio,
		EntradaDevolucio,
	}
}
