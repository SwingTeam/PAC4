package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Venda implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */			
    private Integer idVenda;
    private Date dataVenda;
    private String clientId;
    
    /*
     * Constructor
     * 
     */
    public Venda() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */       
	public Integer getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}
	public Date getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

}
