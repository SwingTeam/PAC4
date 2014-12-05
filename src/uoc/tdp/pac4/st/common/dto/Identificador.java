package uoc.tdp.pac4.st.common.dto;

/**
 * Superclase pensada per a ser l'arrel de la jerarquia 
 * de documents d'identificació. Es pot emprar en 
 * situacions en les quals calgui usar indistintament 
 * NIF, CIF o altres. 
 * Fa que l'aplicació sigui més resistent a possibles 
 * canvis en la legislació sobre identificació. 
 * @author 
 */
public abstract class Identificador 
        implements Cloneable, Comparable<Identificador>,
                        java.io.Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5142891116501604379L;
	/** Identificador general */
    private String id;
    

    /* Constructor amb un paràmetre
     * @param id : Identificador.
     */
    public Identificador(String id) {
        this.id = id;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }


    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obliga a implementar un mètode de validació.
     */
    public abstract boolean validar();


    /**
     * @return the id
     */
    public String toString() {
        return id;
    }
    
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Identificador))
            return false;
        final Identificador other = (Identificador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /**
     *  Implementa Comparable
     *  @param obj un Identificador no nul
     *  @throws NullPointerException si obj és nul
     */
    public int compareTo(Identificador obj) throws ClassCastException {
        return this.getId().compareTo(obj.getId());
    }

    
}
