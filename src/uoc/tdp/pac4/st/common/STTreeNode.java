package uoc.tdp.pac4.st.common;

import java.io.Serializable;

/***
 * Classe que s'utilitza per a crear
 * nodes als objectes JTree
 *  
 * @author Swing Team - 2014
 *
 */
public class STTreeNode implements Serializable {

	private static final long serialVersionUID = -7545493604407644544L;
	private String _description = null;
	private Object _id = null;
	private Enums.NodeType _nodeType = null;
	
	/***
	 * Constructor
	 * 
	 * @param id Identificador del node.
	 * @param description Decripció del node.
	 * @param nodeType Tipus de node.
	 */
	public STTreeNode(Object id, String description, Enums.NodeType nodeType){
		this._id = id;
		this._description = description;
		this._nodeType = nodeType;
	}
	
	//******************************** Getters ****************************************
	public Object getId(){return this._id;}
	public String getIdAsString(){return this._id.toString();}
	public String getDescription(){return this._description;}
	public Enums.NodeType getNodeType(){return this._nodeType;}
	//******************************** ******* ****************************************
	
	/***
	 * Retorna la cadena que representa el node.
	 * Pels nodes de tipus producte, retornarà
	 * la descripció més el codi del producte,
	 * mentre que per a la resta, només retornarà
	 * la descripció.
	 */
	public String toString(){
		if (this._nodeType == Enums.NodeType.Product)
			return this._description + (this._id != null ? " - " + this._id.toString() + "" : "" );
		else
			return this._description;
	}
}
