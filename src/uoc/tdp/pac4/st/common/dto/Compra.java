package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Compra implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	
    private int idCompra;
    private Date dataCompra;
    private String proveidorId;
    
    /*
     * Constructor
     * 
     */
    public Compra() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */    
	public int getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public String getProveidorId() {
		return proveidorId;
	}
	public void setProveidorId(String proveidorId) {
		this.proveidorId = proveidorId;
	}   
    
    
}
