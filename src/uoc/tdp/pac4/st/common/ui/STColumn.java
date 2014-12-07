package uoc.tdp.pac4.st.common.ui;

import javax.swing.JPanel;

public class STColumn extends  JPanel {
	private static final long serialVersionUID = -4456026866229375746L;
	
	private String nom;
	private Integer amplada;
	private boolean editable;
	private boolean validarEnterPositiu;	
	
	public STColumn(String _nom, Integer _amplada, boolean _editable, boolean _validarEnterPositiu) {
		nom = _nom;
		amplada= _amplada;		
		editable = _editable;
		validarEnterPositiu= _validarEnterPositiu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getAmplada() {
		return amplada;
	}

	public void setAmplada(Integer amplada) {
		this.amplada = amplada;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public boolean isValidarEnterPositiu() {
		return validarEnterPositiu;
	}

	public void setValidarEnterPositiu(boolean validarEnterPositiu) {
		this.validarEnterPositiu = validarEnterPositiu;
	}	

	
}
