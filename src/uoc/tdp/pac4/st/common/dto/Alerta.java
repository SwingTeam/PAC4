package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Alerta implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;

	/*
	 * Propietats privades
	 * 
	 */	
    private int idAlerta;
    private Date dataAlerta;
    private String tipusAlertaId;
    private String producteId;
    private String localId;
    private Integer estoc;
    private Integer estocMinim;
    
    /*
     * Constructor
     * 
     */
    public Alerta() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */
    
	public int getIdAlerta() {
		return idAlerta;
	}
	public void setIdAlerta(int idAlerta) {
		this.idAlerta = idAlerta;
	}
	public Date getDataAlerta() {
		return dataAlerta;
	}
	public void setDataAlerta(Date dataAlerta) {
		this.dataAlerta = dataAlerta;
	}
	public String getTipusAlertaId() {
		return tipusAlertaId;
	}
	public void setTipusAlertaId(String tipusAlertaId) {
		this.tipusAlertaId = tipusAlertaId;
	}
	public String getProducteId() {
		return producteId;
	}
	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}
	public String getLocalId() {
		return localId;
	}
	public void setLocalId(String localId) {
		this.localId = localId;
	}
	public Integer getEstoc() {
		return estoc;
	}
	public void setEstoc(Integer estoc) {
		this.estoc = estoc;
	}
	public Integer getEstocMinim() {
		return estocMinim;
	}
	public void setEstocMinim(Integer estocMinim) {
		this.estocMinim = estocMinim;
	}

    
    
}
