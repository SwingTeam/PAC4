package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class EntradaDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	    
    private int idEntradaDevolucio;
    private Date dataEntrDevolucio;
    
    /*
     * Constructor
     * 
     */
    public EntradaDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */      
	public int getIdEntradaDevolucio() {
		return idEntradaDevolucio;
	}
	public void setIdEntradaDevolucio(int idEntradaDevolucio) {
		this.idEntradaDevolucio = idEntradaDevolucio;
	}
	public Date getDataEntrDevolucio() {
		return dataEntrDevolucio;
	}
	public void setDataEntrDevolucio(Date dataEntrDevolucio) {
		this.dataEntrDevolucio = dataEntrDevolucio;
	}
    
    
    
}
