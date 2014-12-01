package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinCompra implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	  		
	private int idLincompra;
	private int  movimentId;
	private int compraId;
	private Double preuCompra;
	private String comLinCompra;
    private String destiLocalId;
    private int numUnitRebudes;
    
    /*
     * Constructor
     * 
     */
    public LinCompra() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */     
	public int getIdLincompra() {
		return idLincompra;
	}
	public void setIdLincompra(int idLincompra) {
		this.idLincompra = idLincompra;
	}
	public int getMovimentId() {
		return movimentId;
	}
	public void setMovimentId(int movimentId) {
		this.movimentId = movimentId;
	}
	public int getCompraId() {
		return compraId;
	}
	public void setCompraId(int compraId) {
		this.compraId = compraId;
	}
	public Double getPreuCompra() {
		return preuCompra;
	}
	public void setPreuCompra(Double preuCompra) {
		this.preuCompra = preuCompra;
	}
	public String getComLinCompra() {
		return comLinCompra;
	}
	public void setComLinCompra(String comLinCompra) {
		this.comLinCompra = comLinCompra;
	}
	public String getDestiLocalId() {
		return destiLocalId;
	}
	public void setDestiLocalId(String destiLocalId) {
		this.destiLocalId = destiLocalId;
	}
	public int getNumUnitRebudes() {
		return numUnitRebudes;
	}
	public void setNumUnitRebudes(int numUnitRebudes) {
		this.numUnitRebudes = numUnitRebudes;
	}

    
    
}
