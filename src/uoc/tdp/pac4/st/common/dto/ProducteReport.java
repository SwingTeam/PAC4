package uoc.tdp.pac4.st.common.dto;

/***
 * Classe amb la informació afegida a la
 * classe Producte, necessària pels informes.
 * 
 * @author Swing Team - 2014
 *
 */
public class ProducteReport extends Producte {
	
	private static final long serialVersionUID = -2577321659943661298L;
	private int _groupId = 0;
	private String _groupName = null;
	private int _subgrupId = 0;
	private String _subgroupName = null;
	
	public ProducteReport(){
		super();
	}

	//******************************* Getters ********************************************
	public int getGroupId(){return this._groupId;}
	public String getGroupName(){return this._groupName;}
	public int getSubgroupId(){return this._subgrupId;}
	public String getSubgroupName(){return this._subgroupName;}
	//******************************* ******* ********************************************
	
	//******************************* Setters ********************************************
	public void setGroupId(int value){this._groupId = value;}
	public void setGroupName(String value){this._groupName = value;}
	public void setSubgroupId(int value){this._subgrupId = value;}
	public void setSubgroupName (String value){this._subgroupName = value;}
	//******************************* ******* ********************************************
}
