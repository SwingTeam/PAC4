package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

import uoc.tdp.pac4.st.common.Managers;
import uoc.tdp.pac4.st.common.managers.MotiuDevolucioManager;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class MotiuDevolucio implements Serializable {

	private static final long serialVersionUID = 6017623832256676834L;
    
	/* 
	 * Propietats privades
	 * 
	 */			
    private String idMotiuDevolucio;
    private String motiu;
    
    
    /*
     * Constructor
     * 
     */
    public MotiuDevolucio() 
    {
    
    }

    /*
     * Getters & setters
     * 
     */       
	public String getIdMotiuDevolucio() {
		return idMotiuDevolucio;
	}

	public void setIdMotiuDevolucio(String idMotiuDevolucio) {
		this.idMotiuDevolucio = idMotiuDevolucio;
	}    
	
	
	public String getMotiu() {
		String token= "";
		switch (idMotiuDevolucio)
		{
			case MotiuDevolucioManager.MOTIU_DEVOLUCIO_DEFECTE:
				token= "MOTIU_DEVOLUCIO_DEFECTE";
				break;
			case MotiuDevolucioManager.MOTIU_DEVOLUCIO_EQUIVOCADA:
				token= "MOTIU_DEVOLUCIO_EQUIVOCADA";
				break;
			case MotiuDevolucioManager.MOTIU_DEVOLUCIO_NO_DEMANADA:
				token= "MOTIU_DEVOLUCIO_NO_DEMANADA";
				break;				
		}
				
		return Managers.i18n.getTranslation(token);
	}
}
