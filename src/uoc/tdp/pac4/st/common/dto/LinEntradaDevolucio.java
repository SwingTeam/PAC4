package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;


/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinEntradaDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/*
	 * Propietats privades
	 * 
	 */	
	private int idLinEntradaDevolucio;
	private int  movimentId;
	private int  entradaDevolucioId;
	private int  lineaAlbaraDevId;
	private String motiuEntrDevolId;
	private String comEntrDevol;
	private String clientId;
	private String destiLocalId;
	
    /*
     * Constructor
     * 
     */
    public LinEntradaDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */ 	
	public int getIdLinEntradaDevolucio() {
		return idLinEntradaDevolucio;
	}
	public void setIdLinEntradaDevolucio(int idLinEntradaDevolucio) {
		this.idLinEntradaDevolucio = idLinEntradaDevolucio;
	}
	public int getMovimentId() {
		return movimentId;
	}
	public void setMovimentId(int movimentId) {
		this.movimentId = movimentId;
	}
	public int getEntradaDevolucioId() {
		return entradaDevolucioId;
	}
	public void setEntradaDevolucioId(int entradaDevolucioId) {
		this.entradaDevolucioId = entradaDevolucioId;
	}
	public int getLineaAlbaraDevId() {
		return lineaAlbaraDevId;
	}
	public void setLineaAlbaraDevId(int lineaAlbaraDevId) {
		this.lineaAlbaraDevId = lineaAlbaraDevId;
	}
	public String getMotiuEntrDevolId() {
		return motiuEntrDevolId;
	}
	public void setMotiuEntrDevolId(String motiuEntrDevolId) {
		this.motiuEntrDevolId = motiuEntrDevolId;
	}
	public String getComEntrDevol() {
		return comEntrDevol;
	}
	public void setComEntrDevol(String comEntrDevol) {
		this.comEntrDevol = comEntrDevol;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getDestiLocalId() {
		return destiLocalId;
	}
	public void setDestiLocalId(String destiLocalId) {
		this.destiLocalId = destiLocalId;
	}
    
}
