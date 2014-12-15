package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Proveidor implements Serializable {
	
	private static final long serialVersionUID = -9105147634565946892L;

	/*
	 * Propietats privades
	 * 
	 */			
    private Integer idsProveidor;
    private String idProveidor;
    private String nomProveidor;
    

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
	public Integer getIdsProveidor() {
		return idsProveidor;
	}
	public void setIdsProveidor(Integer idsProveidor) {
		this.idsProveidor = idsProveidor;
	}
	public String getIdProveidor() {
		return idProveidor;
	}
	public void setIdProveidor(String idProveidor) {
		this.idProveidor = idProveidor;
	}
	public String getNomProveidor() {
		return nomProveidor;
	}
	public void setNomProveidor(String nomProveidor) {
		this.nomProveidor = nomProveidor;
	}

}
