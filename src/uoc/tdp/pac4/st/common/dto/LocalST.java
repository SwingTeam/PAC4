package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;

/***
 * Classe que representa a un local.
 * 
 * @author Swing Team - 2014
 * 
 */
public class LocalST implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
	
    
	/*
	 * Propietats privades
	 * 
	 */		
    private String idLocal;
    private String nomLocal;
    private String cif;
    private String telefon;
    private String adreca;
    private String provincia_id;
    private String pais;
    private String tipusLocal;
    private Date dataAlta;
    private int coordX;
    private int coordY;
    
	public String getIdLocal() {
		return idLocal;
	}
	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}
	public String getNomLocal() {
		return nomLocal;
	}
	public void setNomLocal(String nomLocal) {
		this.nomLocal = nomLocal;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public String getProvincia_id() {
		return provincia_id;
	}
	public void setProvincia_id(String provincia_id) {
		this.provincia_id = provincia_id;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getTipusLocal() {
		return tipusLocal;
	}
	public void setTipusLocal(String tipusLocal) {
		this.tipusLocal = tipusLocal;
	}
	public Date getDataAlta() {
		return dataAlta;
	}
	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
    
}
