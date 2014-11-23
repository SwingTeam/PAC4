package uoc.tdp.pac4.st.common;

import java.io.Serializable;

/***
 * Classe que representa a un taller
 * 
 * @author Swing Team - 2014
 *
 */
public class Taller implements Serializable {

	private static final long serialVersionUID = 6892063471203609384L;
	
	private int id_taller = 0;
    private String nombre = "";
    private String direccion="";

    public Taller() {}

    public Taller(int pid_taller, String pnombre, String pdireccion)
    {
      id_taller = pid_taller;
      nombre = pnombre;
      direccion = pdireccion;
    }
    
    public void setId_taller (int taller)
    {
     id_taller = taller;
    }
    
    public int getId_taller ()
    {
    return id_taller;
    }
    
    public void setnombre (String nom)
    {
     this.nombre = nom;
    }
    
    public String getnombre ()
    {
    return (new String (nombre));
    }
    
    public void setdireccion (String adreca)
    {
     this.direccion = adreca;
    }
    
    public String getdireccion ()
    {
    return (new String (direccion));
    }
}
