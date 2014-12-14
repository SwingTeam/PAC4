package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;


public abstract class Base10 extends Base2 implements Serializable {

	 private String telephone;
	 private String address;
	 private String city;
	 private String country;
	 private String cp;
	 private String province;
	 private Date data_alta;
	 
	 public Base10()
	 {
		 
	 }
	 public Base10(String name, int id,String telephone, String address,String city, String country, String cp,String province, Date data_alta)
	 {
		 super(name,id);
		 this.telephone = telephone;
		 this.address = address;
		 this.city = city;
		 this.country = country;
		 this.cp = cp;
		 this.data_alta = data_alta;
		 this.province = province;
	 }
	 
	 
	/**
	 * @return the data_alta
	 */
	public Date getData_alta() {
		return data_alta;
	}
	/**
	 * @param data_alta the data_alta to set
	 */
	public void setData_alta(Date data_alta) {
		this.data_alta = data_alta;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	 

	public boolean checkCP()
	{
		return checkCP(this.getCp());
	}
	
	public boolean checkCP (String cp)
	{
		if (cp.length() != 5) 
			return false;
	
	    for (int i = 0; i < cp.length(); i++) {
	         if (!Character.isDigit(cp.charAt(i))) { 
	             return false;
	         }
	    }
	    return true;
	}
	
	/**
	 * checkCP - M�tode per valida el Tel�fon d'un usuari
	 * 
	 * @param tel�fon - El tel�fon que cal validar
	 * @return boolean - Torna cert si el tel�fon �s valid, en altre cas fals
	 */
	public boolean checkTelefon (String telefon)
	{
		if (telefon.length() != 9 || telefon.charAt(0)=='0') 
			return false;
		
	    for (int i = 0; i < telefon.length(); i++) {
	         if (!Character.isDigit(telefon.charAt(i))) { 
	             return false;
	         }
	    }
	    return true;
	}
	 
}
