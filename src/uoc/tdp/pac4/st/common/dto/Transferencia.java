package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
	private Integer idTransferencia;
    private Date dataTransferencia;
    

    /*
     * Constructor
     * 
     */
    public Transferencia() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */   	    
	public Integer getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(Integer idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
	public Date getDataTransferencia() {
		return dataTransferencia;
	}
	public void setDataTransferencia(Date dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}
}
