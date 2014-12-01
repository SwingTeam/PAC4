package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinVenda implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */		
	private int idLinVenda;
	private int movimentId;
	private int vendaId;
	private Double preuVenda;
	private String comLinVenda;
	private String origLocalId;
	
    
    /*
     * Constructor
     * 
     */
    public LinVenda() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */   	
	public int getIdLinVenda() {
		return idLinVenda;
	}
	public void setIdLinVenda(int idLinVenda) {
		this.idLinVenda = idLinVenda;
	}
	public int getMovimentId() {
		return movimentId;
	}
	public void setMovimentId(int movimentId) {
		this.movimentId = movimentId;
	}
	public int getVendaId() {
		return vendaId;
	}
	public void setVendaId(int vendaId) {
		this.vendaId = vendaId;
	}
	public Double getPreuVenda() {
		return preuVenda;
	}
	public void setPreuVenda(Double preuVenda) {
		this.preuVenda = preuVenda;
	}
	public String getComLinVenda() {
		return comLinVenda;
	}
	public void setComLinVenda(String comLinVenda) {
		this.comLinVenda = comLinVenda;
	}
	public String getOrigLocalId() {
		return origLocalId;
	}
	public void setOrigLocalId(String origLocalId) {
		this.origLocalId = origLocalId;
	}
    
}
