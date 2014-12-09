package uoc.tdp.pac4.st.common.dto;
import java.util.regex.*;
import java.io.Serializable;
import java.util.Date;
/**
  * @author Grup Swing Dream
  * @version "1.00, 18/11/14" 
  * Classe que representa a un usuari de l'apliaci�
  *
  * @param titulo El nuevo t�tulo de la descripci�n.
  * @throws IllegalArgumentException Si titulo es null, est� vac�o o contiene s�lo espacios.
  * @return
  */
 public class Usuari implements Serializable { 
 /**
	 * Camps de la classe Usuari
	 */
	private static final long serialVersionUID = 4700317219324523776L;
	private String idUsuari = "";
	private String password = "";
    private String login = "";
    //private String NIF = "";
    private Identificador NIF = new NIF("");
    private String nom = "";
    private String cognoms ="";
    private String idIdioma = "";
    private String adresa = "";
    private String poblacio = "";
    private String cp = "";
    private String idLocal = "";
    private Date data_alta;
    private Boolean vigentSN;
    private Date data_baixa;
    private String correue ="";
    private String telefon = "";
    private String mobil = "";
    private String province = "";
    private String pais = "";
    private String rol = "";
  /** 
    * Class constructor.
    */
    public Usuari()
    {
    }

    /***
     * Constructor
     * 
     * @param pid_taller
     * @param pid_empleat
     * @param pnom
     * @param pcognoms
     * @param pidIdioma
     * @param padre�a
     * @param ppoblacio
     * @param pidLocal
     * @param ppais
     *
     */
	public Usuari(String pidUsuari, String pPassword, String pnom, String pcognoms, String pidIdioma, String padresa, String ppoblacio, String pidLocal, String ppais)
	{
	  idUsuari = pidUsuari;
	  password = pPassword;
	  nom = pnom;
	  cognoms = pcognoms;
	  idIdioma = pidIdioma;
	  adresa = padresa;
	  poblacio = ppoblacio;
	  idLocal = pidLocal;
	  pais = ppais;
    }

	/**
	 * @param usuari the user id to set
	 */

	public void setidUsuari (String usuari)
	{
		this.idUsuari = usuari;
	}
	  /**
	  * M�tode getidUsuari, serveix per aconseguir el idUsuari que serveix per identificar un usuari
	 * @param i 
	  * 
	  * @return int    el identificador de l'usuari en l'aplicaci�
	  */

	public String getidUsuari ()
	{
		return idUsuari;
	}
	/**
	 * @param nom the nom to set
	 */

	public void setnom (String nom)
	{
		this.nom = nom;
	}
	/**
	 * @return the nom
	 */
	
	public String getnom ()
	{
		return (new String (nom));
	}

	/**
	 * @param cognoms the cognoms to set
	 */

	public void setcognoms (String cognoms)
	{
		this.cognoms = cognoms;
	}
	/**
	 * @return the cognoms
	 */
	
	public String getcognoms ()
	{
		return (new String (cognoms));
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the idIdioma
	 */
	public String getIdIdioma() {
		return idIdioma;
	}

	/**
	 * @param idIdioma the idIdioma to set
	 */
	public void setIdIdioma(String idIdioma) {
		this.idIdioma = idIdioma;
	}

	/**
	 * @return the adre�a
	 */
	public String getAdresa() {
		return adresa;
	}

	/**
	 * @param adre�a the adre�a to set
	 */
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	/**
	 * @return the poblacio
	 */
	public String getPoblacio() {
		return poblacio;
	}

	/**
	 * @param poblacio the poblacio to set
	 */
	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}

	/**
	 * @return the idLocal
	 */
	public String getIdLocal() {
		return idLocal;
	}

	/**
	 * @param idLocal the idLocal to set
	 */
	public void setIdLocal(String idLocal) {
		this.idLocal = idLocal;
	}

	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * @return the nIF
	 */
	public String getNIF() {
		return NIF.getId();
	}

	/**
	 * @param nIF the nIF to set
	 */
	public void setNIF(String nIF) {
		NIF.setId(nIF);
	}


	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
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
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the correue
	 */
	public String getCorreue() {
		return correue;
	}

	/**
	 * @param correue the correue to set
	 */
	public void setCorreue(String correue) {
		this.correue = correue;
	}

	private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
    /**
     * checkCorreuE valida el correu electr�nic donat per l'usauri
     *
     * @param email que cal validar si �s correcte
     *            
     * @return cert si el correu electr�nic �s correcte, fals en altre cas
     */
    public boolean checkCorreuE(String email) {
 
        // Compiles the given regular expression into a pattern.
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
 
        // Match the given input against this pattern
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
     }
	
	
	/**
	 * @return the telefon
	 */
	public String getTelefon() {
		return telefon;
	}

	/**
	 * @param telefon the telefon to set
	 */
	public void setTelefon(String telefon) {
		this.telefon = telefon;
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

	/**
	 * @return the mobil
	 */
	public String getMobil() {
		return mobil;
	}

	/**
	 * @param mobil the mobil to set
	 */
	public void setMobil(String mobil) {
		this.mobil = mobil;
	}
	
	/**
	 * checkCP - M�tode per valida el M�bil d'un usuari
	 * 
	 * @param telefon - El m�bil que cal validar
	 * @return boolean - Torna cert si el m�bil �s valid, en altre cas fals
	 */
	public boolean checkMobil (String telefon)
	{
		if (checkTelefon(telefon)==false || telefon.charAt(0)!='6')
			return false;
	    return true;
	}

	public Boolean getVigentSN() {
		return vigentSN;
	}

	public void setVigentSN(Boolean vigentSN) {
		this.vigentSN = vigentSN;
	}

	public Date getData_baixa() {
		return data_baixa;
	}

	public void setData_baixa(Date data_baixa) {
		this.data_baixa = data_baixa;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
 }