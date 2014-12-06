package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

import uoc.tdp.pac4.st.common.Taller;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Producte implements Serializable {
 
	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */		
    private String idProducte;
    private String nomProducte;
    private int producteGrupId;
    private int producteSubGrupId;
        
    private Taller taller;
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

	public Taller getTaller() {
		return taller;
	}

	public void setTaller(Taller taller) {
		this.taller = taller;
	}

	public Existencies getExistencies() {
		return existencies;
	}

	public void setExistencies(Existencies existencies) {
		this.existencies = existencies;
	}

}
