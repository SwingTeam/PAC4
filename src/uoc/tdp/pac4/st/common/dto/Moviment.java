package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 * 
 */
public class Moviment implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	
    private Integer idMoviment;
    private Date dataOrdre;
    private String producteId;
    private Integer numUnitatsOrdre;
    private Integer numUnitSortides;
    private Boolean completatSn;
    private String tipusMovimentId;
    private Date dataPrevLliurament;
    
    
    /*
     * Constructor
     * 
     */
    public Moviment() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */     
	public Integer getIdMoviment() {
		return idMoviment;
	}
	public void setIdMoviment(Integer idMoviment) {
		this.idMoviment = idMoviment;
	}
	public Date getDataOrdre() {
		return dataOrdre;
	}
	public void setDataOrdre(Date dataOrdre) {
		this.dataOrdre = dataOrdre;
	}
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public Integer getNumUnitatsOrdre() {
		return numUnitatsOrdre;
	}
	public void setNumUnitatsOrdre(Integer numUnitatsOrdre) {
		this.numUnitatsOrdre = numUnitatsOrdre;
	}
	public Integer getNumUnitSortides() {
		return numUnitSortides;
	}
	public void setNumUnitSortides(Integer numUnitSortides) {
		this.numUnitSortides = numUnitSortides;
	}
	public Boolean getCompletatSn() {
		return completatSn;
	}
	public void setCompletatSn(Boolean completatSn) {
		this.completatSn = completatSn;
	}
	public String getTipusMovimentId() {
		return tipusMovimentId;
	}
	public void setTipusMovimentId(String tipusMovimentId) {
		this.tipusMovimentId = tipusMovimentId;
	}
	public Date getDataPrevLliurament() {
		return dataPrevLliurament;
	}
	public void setDataPrevLliurament(Date dataPrevLliurament) {
		this.dataPrevLliurament = dataPrevLliurament;
	}    
}
