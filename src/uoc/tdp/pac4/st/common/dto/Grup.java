package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Grup implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	
    private int idGrup;
    private String nom;
    
    /*
     * Constructor
     * 
     */
    public Grup() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */    
	public int getIdGrup() {
		return idGrup;
	}

	public void setIdGrup(int idGrup) {
		this.idGrup = idGrup;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

    
}
