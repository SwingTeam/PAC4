package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class TipusAlerta implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
	private String idTipusAlerta;

	  /*
     * Constructor
     * 
     */
    public TipusAlerta() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */   	
	public String getIdTipusAlerta() {
		return idTipusAlerta;
	}

	public void setIdTipusAlerta(String idTipusAlerta) {
		this.idTipusAlerta = idTipusAlerta;
	}
	
	
}
