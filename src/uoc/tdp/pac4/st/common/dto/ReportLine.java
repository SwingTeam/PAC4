package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

import uoc.tdp.pac4.st.common.Constants;

/***
 * Classe que representa a una
 * línia bàsica d'informe.
 *  
 * @author Swing Team - 2014
 *
 */
public class ReportLine implements Serializable, ReportLineInterface {

	private static final long serialVersionUID = 4672909624546043455L;

	private Grup _group = null;
	private Local _establishment = null;
	private Producte _product = null;
	private Proveidor _provider = null;
	private SubGrup _subgroup = null;
	
	/***
	 * Constructor
	 */
	public ReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public ReportLine(Local local){
		this._establishment = local;
	}
	
	/***
	 * Constructor
	 * 
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public ReportLine(Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							Local establishment){
		this._group = group;
		this._establishment = establishment;
		this._product = product;
		this._provider = provider;
		this._subgroup = subgroup;
	}
	
	//********************************* Getters ********************************************
	public Grup getGroup(){return this._group;}
	public Local getEstablishment(){return this._establishment;}
	public Producte getProduct(){return this._product;}
	public Proveidor getProvider(){return this._provider;}
	public SubGrup getSubgroup(){return this._subgroup;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setGroup(Grup group){this._group = group;}
	public void setEstablishment(Local establishment){this._establishment = establishment;}
	public void setProduct(Producte product){this._product = product;}
	public void setProvider(Proveidor provider){this._provider = provider;}
	public void setSubgroup(SubGrup subgroup){this._subgroup = subgroup;}
	//********************************* ******* ********************************************

	/***
	 * Recupera el valor corresponent a
	 * un determinat camp.
	 */
	public Object getValue (String fieldName){
		Object result = null;
		switch (fieldName){
			case Constants.REPORT_GROUP_ID:
				result = this._group.getIdGrup();
				break;
			case Constants.REPORT_GROUP_NAME:
				result = this._group.getNom();
				break;
			case Constants.REPORT_ESTAB_LATITUDE:
				result = this._establishment.getLatitude();
				break;
			case Constants.REPORT_ESTAB_LONGITUDE:
				result = this._establishment.getLongitude();
				break;
			case  Constants.REPORT_ESTAB_NAME:
				result = this._establishment.getName();
				break;
			case Constants.REPORT_ESTAB_PHONE:
				result = this._establishment.getPhone();
				break;
			case Constants.REPORT_ESTAB_PROVINCE:
				result = this._establishment.getProvince();
				break;
			case Constants.REPORT_ESTAB_TAX_ID:
				result = this._establishment.getTaxId();
				break;
			case Constants.REPORT_PRODUCT_ID:
				result = this._product.getIdProducte();
				break;
			case Constants.REPORT_PRODUCT_NAME:
				result = this._product.getNomProducte();
				break;
			case Constants.REPORT_PRODUCT_GROUP_ID:
				result = this._product.getProducteGrupId();
				break;
			case Constants.REPORT_PRODUCT_SUBGROUP_ID:
				result = this._product.getProducteSubGrupId();
				break;
			case Constants.REPORT_PROVIDER_ID:
				result = this._provider.getIdProveidor();
				break;
			case Constants.REPORT_PROVIDER_NAME:
				result = this._provider.getNomProveidor();
				break;
			case Constants.REPORT_SUBGROUP_ID:
				result = this._subgroup.getIdGrup();
				break;
			case Constants.REPORT_SUBGROUP_NAME:
				result = this._subgroup.getNom();
				break;
		}
		return result;
	}
}
