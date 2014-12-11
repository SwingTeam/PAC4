package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


public class LocalSTer extends Base10 implements Serializable { 
		 /**
	 * 
	 */
	private static final long serialVersionUID = -6209253084751649683L;
		/**
			 * Camps de la classe Usuari
			 */
		 private Identificador cif = new CIF("");
		 private int coordX;
		 private int coordY;
		 private String LocalTipus;
		 
		 public LocalSTer()
		 {
			 // constructor sense par�metres
		 }
		 
		 public LocalSTer(String name, int id,String telephone, String address,String city, String country, String cp,String province, Date data_alta,String LocalTipus, int coordx, int coordy, String cif)
		 {
			  super(name,id,telephone,address,city,country,cp,province,data_alta);
			  this.setCoordX(coordx);
			  this.setCoordY(coordy);
			  this.cif.setId(cif);
		 }
		 
		 public String getCIF() {
			 return cif.getId();
		 }
		 
		 
		 public void setCIF(String cif) {
			 this.cif.setId(cif);
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

			public String getLocalTipus() {
				return LocalTipus;
			}

			public void setLocalTipus(String localTipus) {
				LocalTipus = localTipus;
			}

}
