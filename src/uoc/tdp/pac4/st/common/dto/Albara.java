package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class Albara implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;

	/*
	 * Propietats privades
	 * 
	 */
	private int idAlbara;
    private Date dataAlbara;
    private String tipusMovimentId;
    private String origenId;
    private String destiId;    
    private String comAlbara;
    private String codialbaraextern;
    
    /*
     * Constructor
     * 
     */
    public Albara() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */
    public int getIdAlbara() {
		return idAlbara;
	}
	public void setIdAlbara(int idAlbara) {
		this.idAlbara = idAlbara;
	}
	public Date getDataAlbara() {
		return dataAlbara;
	}
	public void setDataAlbara(Date dataAlbara) {
		this.dataAlbara = dataAlbara;
	}
	public String getTipusMovimentId() {
		return tipusMovimentId;
	}
	public void setTipusMovimentId(String tipusMovimentId) {
		this.tipusMovimentId = tipusMovimentId;
	}
	public String getOrigenId() {
		return origenId;
	}
	public void setOrigenId(String origenId) {
		this.origenId = origenId;
	}
	public String getDestiId() {
		return destiId;
	}
	public void setDestiId(String destiId) {
		this.destiId = destiId;
	}
	public String getComAlbara() {
		return comAlbara;
	}
	public void setComAlbara(String comAlbara) {
		this.comAlbara = comAlbara;
	}
	public String getCodialbaraextern() {
		return codialbaraextern;
	}
	public void setCodialbaraextern(String codialbaraextern) {
		this.codialbaraextern = codialbaraextern;
	}

    
    
}
