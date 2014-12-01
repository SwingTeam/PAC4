package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Provincia implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
	private String idProvincia;

    /*
     * Constructor
     * 
     */
    public Provincia() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */  	
	public String getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(String idProvincia) {
		this.idProvincia = idProvincia;
	}
}
