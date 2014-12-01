package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Proveidor implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */		
	private Integer idProducteProveidor;    
    private String producteId;    
    private String proveidorId;
    
    /*
     * Constructor
     * 
     */
    public Proveidor() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */     
	public Integer getIdProducteProveidor() {
		return idProducteProveidor;
	}
	public void setIdProducteProveidor(Integer idProducteProveidor) {
		this.idProducteProveidor = idProducteProveidor;
	}
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public String getProveidorId() {
		return proveidorId;
	}
	public void setProveidorId(String proveidorId) {
		this.proveidorId = proveidorId;
	}

}
