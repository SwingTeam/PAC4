package uoc.tdp.pac4.st.common;

/***
 * Classe que representa un element
 * d'un ComboBox.
 * 
 * @author Swing Team - 2014
 *
 */
public class ComboBoxItem
{
	Object _id = 0;
	String _description = "";

	/***
	 * Constructor
	 * 
	 * @param id Object amb l'identificador de l'element.
	 */
	public ComboBoxItem(Object id){
		this._id = id;
	}
	
	/***
	 * Constructor
	 * @param id Object amb l'identificador de l'element.
	 * @param description Cadena amb la descripció de l'element
	 */
	public ComboBoxItem(Object id, String description){
		this._id = id;
		this._description = description;
	}
  
	/************************** Getters *********************************/
	public String getDescription(){
		return this._description;
	}
	
	public Object getId(){
		return this._id;
	}
	/************************** End Getters *********************************/
  
	/************************** Setters *********************************/
	public void setDescription(String value){
		this._description = value;
	}

	public void setId(Object value){
		this._id = value;  
	}
	/************************** End Setters *********************************/
  
	/***
	 * Mètode que retorna una cadena
	 * amb la representació de la instància.
	 */
	public String toString()
	{
		return this._description;
	}
} 
