package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinAlbara implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;

	/*
	 * Propietats privades
	 */
	private int idLiniaAlbara;
	private int albaraId;
	private int movimentId;
	private int unitats;
	private String producteId;

	/*
	 * Constructor
	 */
	public LinAlbara() {

	}

	/*
	 * Getters & setters
	 */
	public int getIdLiniaAlbara() {
		return idLiniaAlbara;
	}

	public void setIdLiniaAlbara(int idLiniaAlbara) {
		this.idLiniaAlbara = idLiniaAlbara;
	}

	public int getAlbaraId() {
		return albaraId;
	}

	public void setAlbaraId(int albaraId) {
		this.albaraId = albaraId;
	}

	public int getMovimentId() {
		return movimentId;
	}

	public void setMovimentId(int movimentId) {
		this.movimentId = movimentId;
	}

	public int getUnitats() {
		return unitats;
	}

	public void setUnitats(int unitats) {
		this.unitats = unitats;
	}

	public String getProducteId() {
		return producteId;
	}

	public void setProducteId(String producteId) {
		this.producteId = producteId;
	}

}
