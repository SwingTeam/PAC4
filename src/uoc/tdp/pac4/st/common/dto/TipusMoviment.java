package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class TipusMoviment implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
    private String idTipusMoviment;

    /*
     * Constructor
     * 
     */
    public TipusMoviment() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */   	    
	public String getIdTipusMoviment() {
		return idTipusMoviment;
	}
	public void setIdTipusMoviment(String idTipusMoviment) {
		this.idTipusMoviment = idTipusMoviment;
	}
    
    
}
