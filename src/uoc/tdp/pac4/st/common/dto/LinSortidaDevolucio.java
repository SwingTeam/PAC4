package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinSortidaDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	
    private int idLinsortDevolucio;
    private int movimentId;
    private Integer sortidaDevolucioId;
    private String motiuSortDevolId;
    private String comSortDevol;
    private String origLocalId;
    
    /*
     * Constructor
     * 
     */
    public LinSortidaDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */     
	public int getIdLinsortDevolucio() {
		return idLinsortDevolucio;
	}
	public void setIdLinsortDevolucio(int idLinsortDevolucio) {
		this.idLinsortDevolucio = idLinsortDevolucio;
	}
	public int getMovimentId() {
		return movimentId;
	}
	public void setMovimentId(int movimentId) {
		this.movimentId = movimentId;
	}
	public Integer getSortidaDevolucioId() {
		return sortidaDevolucioId;
	}
	public void setSortidaDevolucioId(Integer sortidaDevolucioId) {
		this.sortidaDevolucioId = sortidaDevolucioId;
	}
	public String getMotiuSortDevolId() {
		return motiuSortDevolId;
	}
	public void setMotiuSortDevolId(String motiuSortDevolId) {
		this.motiuSortDevolId = motiuSortDevolId;
	}
	public String getComSortDevol() {
		return comSortDevol;
	}
	public void setComSortDevol(String comSortDevol) {
		this.comSortDevol = comSortDevol;
	}
	public String getOrigLocalId() {
		return origLocalId;
	}
	public void setOrigLocalId(String origLocalId) {
		this.origLocalId = origLocalId;
	}
    
}
