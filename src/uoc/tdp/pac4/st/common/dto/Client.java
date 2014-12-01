package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */		
    private int idsClient;
    private String idClient;
    private String nomClient;
    
    /*
     * Constructor
     * 
     */
    public Client() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */    
	public int getIdsClient() {
		return idsClient;
	}
	public void setIdsClient(int idsClient) {
		this.idsClient = idsClient;
	}
	public String getIdClient() {
		return idClient;
	}
	public void setIdClient(String idClient) {
		this.idClient = idClient;
	}
	public String getNomClient() {
		return nomClient;
	}
	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}
    
    
}
