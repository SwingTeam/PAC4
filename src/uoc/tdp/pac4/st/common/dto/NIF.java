package uoc.tdp.pac4.st.common.dto;
import java.text.DecimalFormat;

/**
 * <b>Descripció :</b><br>
 * Classe NIF. Gestiona documents d'identificaci´o fiscal. <br>
 * Amb utilitats per el pas de DNIs a NIFs.<br>
 * <br>
 *  
 * Per a heretar d'Identificador i pasar a ella part dels
 * seus métodes.<br>
 * @author 
 */
public class NIF extends Identificador {

    /**
     * 
     */
    private static final long serialVersionUID = -3088555014204127712L;


    /**
     * Constructor amb DNI.
     * @param nom : DNI a partir del qual es forma el NIF.
     */
    public NIF(int nom) {
        super( ((DecimalFormat)(new DecimalFormat("00000000"))).format(nom)
                + NIF.trobarLletra(nom));
    }
    
    /**
     * Constructor amb NIF.
     * @param id : NIF.
     */
    public NIF(String id) {
        super(id);
    }

    /**
     * Funció que determina si un NIF de la instància actual es o no vàlid.
     * @return Booleà  amb VERTADER si el NIF es correcte, i FALS si no ho es.
     */
    @Override
    public boolean validar() {
        return validar(this.getId());
    }

    /** 
     * Funció estàtica que determina si un NIF donat es o no vàlid 
     * @param id : String que emmagatzema el NIF a validar.
     * @return Booleà  amb VERTADER si el NIF es correcte, i FALS si no ho es.
     */
    public static boolean validar(String id) {
        boolean validar = false;
        try {
            if (id.length() < 10) { // comprovar longitud
                char control = id.charAt(id.length() - 1); // quedar-se amb la lletra
                int nombre = Integer.parseInt(id.substring(0, id.length() - 1));    // quedar-se amb els dígits numèrics
                validar = ( control == trobarLletra(nombre)); // comparar la lletra amb la que hauria de ser
            }
        } 
        catch(Exception e) {
            validar = false;
        }
        return validar;
    }

    /** 
     * Funció estàtica que retorna la lletra de NIF corresponent a un DNI donat.
     * @param nom : DNI del que es vol trobar la lletra del NIF.
     * @return String amb la lletra corresponent.
    */
    public static char trobarLletra(int nom) {
        return "TRWAGMYFPDXBNJZSQVHLCKET".charAt(nom % 23);
    }

}
