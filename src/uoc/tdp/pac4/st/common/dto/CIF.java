package uoc.tdp.pac4.st.common.dto;
/**
 * <b>Descripció : </b><br><br>
 * Classe CIF. Gestiona documents d'identificació fiscal. <br>
 * <br><br>
 * <b>Funcionalitats :</b><br><br>
 * - D'un CIF donat. Pot tornar el CIF amb el DC en format lletra o nombre.<br>
 * - D'un CIF donat pot validar si es correcte o no.<br>
 * - D'un CIF donat pot tornar una cadena amb la descripció del organisme al que pertany.<br>
 * - Funcions de clone(), equals() i toString().<br>
 * <br><br>
 * <b>Descripció de la composició d'un CIF :</b><br><br>
 * Patró d'un CIF : L XXXXXXX D on :<br><br>
 * - L : Lletra que identifica el tipus d'organització al que pertany el CIF.<br>
 * - X : Nombres, (7), de que es composa el CIF.<br>
 * - D : Dí­git control.<br>
 * Heretda d'Identificador i passar a ella part dels 
 * seus métodes. S'internacionalitzen els missatges.<br> 
 * 
 */
public class CIF extends Identificador {

    /**
     * 
     */
    private static final long serialVersionUID = 6372032327748335325L;
    // Identificadors vàlids de tipus d'organismes 
    private static String validLletres = "ABCDEFGHJPQRSUV";
    // Caracters de control
    private static String caractersControl = "JABCDEFGHI";
    // Tipus que el seu digit de control ha de ser una lletre.
    private static String tipusDClletre = "PQS";
    // Tipus que el seu digit de control ha de ser un nombre 
    private static String tipusDCnombre = "ABEH";
    // Literals de tipus d'organismes 

    
    /**
     * Constructor amb CIF.
     * @param id: CIF
     */
    public CIF(String id) {
        super(id);
    }

    
    /** 
     * Funció que determina si un CIF de la instÃ ncia actual es o no vàlid.
     * @return Booleà  amb VERTADER si el CIF es correcte, i FALS si no ho es.
     */
    @Override
    public boolean validar() {
        return validar(this.getId());
    }

    
    /**
     * Funció estàtica que determina si un CIF donat es o no vàlid.         
     * Per comprovar la validessa d'un CIF :<br><br>
     * Els CIF de tipus "PQS" el caràcter de control ha de ser lletra.<br>
     * Els CIF de tipus "ABEH" el caràcter de control ha de ser nombre.<br>
     * Per a la resta és indistint.<br>
     * 1 - Sumar els dígits de les possicions parelles. AixÃ² dona <b>A</b><br>
     * 2 - Per cada una de les possicions senars, multiplicarles per 2 i fer la suma 
     * dels dígits del resultat. Sumar l'acumulat. Aixó dona <b>B</b><br>
     * 3 - Sumar C = A + B.<br>
     * 4 - Prendre únicament l'últim dí­git de C, i restar 10. Aixó dona <b>D</b><br>
     * <br>
     * Si D = dí­git control, llavors el CIF es correcte. En cas contrari, el CIF no serà  correcte.<br>    
     * <br><br>
     * <b>Exemple : </b><br><br>
     * Tenim el <b>CIF A08123456</b>. Llavors :<br>
     * 1) A = 8 + 2 + 4 = 14<br>
     * 2) B = (0*2=0 + 1*2=2 + 3*2=6 + 5*2=10=1+0=1) = 0+2+6+1 = 8<br>
     * 3) C = 14 + 8 = 22<br>
     * 4) Si C = 22 --> Agafem nomes l'últim dí­git = 2. I li restem 10 --> 10 - 2 = 8<br>
     * <br>
     * Com que 8 <> 6. El CIF donat no es correcte.<br>    
     * <br><br>
     * <b>Equivaléncies entre DC amb lletra i el DC amb nombre :</b><br><br>
     * J=0  -  A=1  -  B=2  -  C=3  -  D=4  -  E=5  -  F=6  -  G=7  -  H=8  -  I=9<br>
     * @param id : String que emmagatzema el CIF a validar.
     * @return Booleá  amb VERTADER si el CIF es correcte, i FALS si no ho es.
     */
    public static boolean validar(String id) {
        boolean ret = false;
        int digitControl;

        try {
            if ( id.length()== 9 ) { // Un CIF ha de tenir 9 carácters.                
                char lletraCIF = id.charAt(0); // Troba la 1a lletra del CIF.                
                // Comprova si la primera lletra del CIF es vÃ lida.                
                if ( validLletres.indexOf(lletraCIF) >= 0 ) {                    
                    if ( Character.isDigit( id.charAt(8) ) ) {                                    
                        digitControl = Character.getNumericValue(id.charAt(8));
                        if (tipusDClletre.indexOf(lletraCIF) >= 0) digitControl = 100;
                    } else {
                        digitControl = caractersControl.indexOf(id.charAt(8));
                        if (tipusDCnombre.indexOf(lletraCIF) >= 0) digitControl = 100;
                    }
                    int a=0, b=0, c=0;
                    byte[] senars = {0,2,4,6,8,1,3,5,7,9};
                    // Calcula A y B.
                    for(int t = 1; t <= 6; t = t + 2) {
                        a = a + Character.getNumericValue(id.charAt(t + 1)); // Suma parells.
                        b = b + senars[Character.getNumericValue(id.charAt(t))];
                    }
                    b = b + senars[Character.getNumericValue(id.charAt(7))];
                    // Calcula C
                    c = 10 - ((a + b) % 10);
                    // Compara C amb "digitControl".
                    // Si son iguals --> El CIF es correcte.
                    ret = ( c == digitControl ); 
                }
            }
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }


    /** 
     * Funció que retorna la descripció corresponent al tipus d'organisme 
     * del CIF de la instància actual.
     * @return String amb la descripció corresponent.
     */
    public String tipusOrganismeCIF() {
        return tipusOrganismeCIF(this.getId());
    }

    /** 
    * Funció estàtica que retorna la descripció corresponent al tipus d'organisme d'un CIF donat.<br>
    * @param id : CIF del que es vol saber el tipus d'organisme.
    * @return String amb la descripció corresponent.
    * <b>Descripcions dels identificadors de tipus d'organisme : </b>
    * A - Societat anónima<br>
    * B - Societat de responsabilitat limitada<br>
    * C - Societat colectiva<br>
    * D - Societat comanditaria<br>
    * E - Comunitat de bens<br>
    * F - Societat cooperativa<br>
    * G - Asociació<br>
    * H - Comunitat de propietaris <br>
    * J - Societats civils, amb o sense personalitat jurí­dica<br>
    * P - Corporació local<br>
    * Q - Organisme públic<br>
    * R - Congregacions i institucions religioses<br>
    * S - Organ de l'administració de l'Estat i de les Comunitats Autónomes<br>
    * U - Unions temporals d'Empreses<br>
    * V - Altres tipus no definits en la resta de claus <br>
    */
    public static String tipusOrganismeCIF(String id) {
        String result;    // Resultat de la operaciÃ³.
        String descTipusOrganisme[] = {"CIFtipusA", "CIFtipusB", "CIFtipusC", 
                "CIFtipusD", "CIFtipusE", "CIFtipusF", "CIFtipusG", "CIFtipusH",
                "CIFsenseUs", "CIFtipusJ", "CIFsenseUs", "CIFsenseUs", "CIFsenseUs",
                "CIFsenseUs", "CIFsenseUs","CIFtipusP", "CIFtipusQ", "CIFtipusR", 
                "CIFtipusS", "CIFtipusU", "CIFtipusV", "CIFsenseUs", "CIFsenseUs",
                "CIFsenseUs", "CIFsenseUs"};

        if ( validar(id) != true ) { // El CIF no es válid.
            result = "CIFnovalid";
        } else { // El CIF es válid.
            char lletraCIF = id.charAt(0); // Troba la lletra del CIF.
            if ( validLletres.indexOf(lletraCIF) < 0 ) {
                // No es una lletra corresponent a la llista.
                result = "CIFtipusNoTrobat";
            } else { // Recull la descripció corresponent a la llista.
                result = descTipusOrganisme[(byte)lletraCIF - (byte)'A'];
            }
        }
        return result;
    }


    /**
     * Canvia el DC del CIF a lletra. 
     */
    public void setDCLletra() {
        if (this.getId().length() == 9) {
            if (tipusDCnombre.indexOf(this.getId().charAt(0)) < 0) {
                if (Character.isDigit(this.getId().charAt(8))) {
                    int digitControl = Character.getNumericValue(this.getId().charAt(8));
                    this.setId(this.getId().substring(0, 8) + caractersControl.charAt(digitControl));
                }
            }
        }
    }


    /**
     * Canvia el DC del CIF a nombre. 
     */
    public void setDCNombre() {
        if (this.getId().length() == 9) {
            if (tipusDClletre.indexOf(this.getId().charAt(0)) < 0) {
                if (Character.isLetter(this.getId().charAt(8))) {
                    int digitControl = this.getId().charAt(8);
                    this.setId(this.getId().substring(0, 8) + caractersControl.indexOf(digitControl));
                }
            }
        }
    }

}
