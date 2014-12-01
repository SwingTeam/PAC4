package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

/***
 * Classe que representa a un local.
 * 
 * @author Swing Team - 2014
 *
 */
public class Local implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
	private Float _latitude = 0F;
	private Float _longitude = 0F;
    private String _name = null;
    private String _province = null;
    private String _phone = null;
	private String _taxId = null;

	/***
	 * Constructor
	 */
    public Local() {}

    /***
     * Constructor
     * 
     * @param taxId Cif del local
     * @param name Nom del local
     */
    public Local(String taxId, String name)
    {
    	this._taxId = taxId;
    	this._name = name;
    }

    /**************** getters *****************/
    public Float getLatitude(){
    	return this._latitude;
    }
    
    public Float getLongitude(){
    	return this._longitude;
    }
    
    public String getName(){
    	return this._name;
    }
	
    public String getProvince(){
    	return this._province;
    }
    
    public String getPhone(){
    	return this._phone;
    }
    
    public String getTaxId(){
    	return this._taxId;
    }
    
    /**************** setters *****************/
    public void setLatitude(Float value){
    	this._latitude = value;
    }
    
    public void setLongitude(Float value){
    	this._longitude = value;
    }
    
    public void setName(String value){
    	this._name = value;
    }
	
    public void setProvince(String value){
    	this._province = value;
    }
    
    public void setPhone(String value){
    	this._phone = value;
    }
    
    public void setTaxId(String value){
    	this._taxId = value;
    }
    
}
