package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class SortidaDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
    private Integer idSortidaDevol;
    private Date dataSortDevolucio;
    private ArrayList<LinSortidaDevolucio> LiniesSortidaDevolucio= new ArrayList<LinSortidaDevolucio>();
    
    /*
     * Constructor
     * 
     */
    public SortidaDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */      
	public Integer getIdSortidaDevol() {
		return idSortidaDevol;
	}
	public void setIdSortidaDevol(Integer idSortidaDevol) {
		this.idSortidaDevol = idSortidaDevol;
	}
	public Date getDataSortDevolucio() {
		return dataSortDevolucio;
	}
	public void setDataSortDevolucio(Date dataSortDevolucio) {
		this.dataSortDevolucio = dataSortDevolucio;
	}

	public ArrayList<LinSortidaDevolucio> getLiniesSortidaDevolucio() {
		return LiniesSortidaDevolucio;
	}

	public void setLiniesSortidaDevolucio(
			ArrayList<LinSortidaDevolucio> liniesSortidaDevolucio) {
		LiniesSortidaDevolucio = liniesSortidaDevolucio;
	}
}
