package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Existencies implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 *  
	 */	  	
	private String producteId;
	private String localId;
	private Integer estoc;
	private Integer estocMinim;
	
    
    /*
     * Constructor
     * 
     */
    public Existencies() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */  	
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public Integer getEstoc() {
		return estoc;
	}
	public void setEstoc(Integer estoc) {
		this.estoc = estoc;
	}
	public Integer getEstocMinim() {
		return estocMinim;
	}
	public void setEstocMinim(Integer estocMinim) {
		this.estocMinim = estocMinim;
	}
    
}
