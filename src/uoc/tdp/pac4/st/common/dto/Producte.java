package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Producte implements Serializable {
 
	private static final long serialVersionUID = 6390771549199822474L;

	/*
	 * Propietats privades
	 * 
	 */		
    private String idProducte;
    private String nomProducte;
    private int producteGrupId;
    private int producteSubGrupId;
        
    private LocalST local;
    private Existencies existencies;
    
    
    /*
     * Constructor
     * 
     */
    public Producte() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */      
	public String getIdProducte() {
		return idProducte;
	}
	public void setIdProducte(String idProducte) {
		this.idProducte = idProducte;
	}
	public String getNomProducte() {
		return nomProducte;
	}
	public void setNomProducte(String nomProducte) {
		this.nomProducte = nomProducte;
	}
	public int getProducteGrupId() {
		return producteGrupId;
	}
	public void setProducteGrupId(int producteGrupId) {
		this.producteGrupId = producteGrupId;
	}
	
	public int getProducteSubGrupId() {
		return producteSubGrupId;
	}
	public void setProducteSubGrupId(int producteSubGrupId) {
		this.producteSubGrupId = producteSubGrupId;
	}

	public LocalST getLocal() {
		return local;
	}

	public void setLocal(LocalST local) {
		this.local= local;
	}

	public Existencies getExistencies() {
		return existencies;
	}

	public void setExistencies(Existencies existencies) {
		this.existencies = existencies;
	}

}
