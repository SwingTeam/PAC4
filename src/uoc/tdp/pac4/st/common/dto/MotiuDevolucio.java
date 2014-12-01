package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class MotiuDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
    protected String idMotiuDevolucio;

    /*
     * Constructor
     * 
     */
    public MotiuDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */       
	public String getIdMotiuDevolucio() {
		return idMotiuDevolucio;
	}

	public void setIdMotiuDevolucio(String idMotiuDevolucio) {
		this.idMotiuDevolucio = idMotiuDevolucio;
	}    
}
