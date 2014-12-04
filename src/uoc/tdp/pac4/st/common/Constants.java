package uoc.tdp.pac4.st.common;

/***
 * Aquesta classe conté totes les constants
 * d'ús comú a l'aplicació, a excepció de les
 * constants que fan referència a tokens, que
 * es troben a la classe Tokens.
 * 
 * @author Swing Team - 2014
 *
 */
public class Constants {
	
	//Imatge de fons
	public final static String BACKGROUND_PICTURE = "resources/background.png";

	//Dades del driver de connexió a la base de dades
	public final static String DB_DRIVER = "driver";
	//Clau de la ruta d'accés a la base de dades amb el driver jdbc
	public final static String DB_URL = "url";
	//Clau de la contrasenya de l'usuari d'accés a la base de dades
	public final static String DB_PASSWORD = "password";
	//Clau de l'esquema de la base de dades
	public final static String DB_SQUEMA = "schema";
	//Clau de l'usuari de la base de dades
	public final static String DB_USERNAME = "username";
	
	//Extensió dels fitxer d'excepcions
	public final static String EXCEPTION_FILE_EXTENSION = ".log";
	//Ruta del fitxers d'excepcions
	public final static String EXCEPTION_FILE_PATH = "logs";
	//Prefixe dels fitxer d'excepcions
	public final static String EXCEPTION_FILE_PREFIX = "Exceptions_";
	
	//*********************** Camps de la base de dades **************************
	public final static String FIELD_ADRECA = "adreca";
	public final static String FIELD_ALBARA_ID = "albara_id";
	public final static String FIELD_CIF = "cif";
	public final static String FIELD_CLIENT_ID = "client_id";
	public final static String FIELD_CODIALBARAEXTERN = "codialbaraextern";
	public final static String FIELD_CODPOST = "codpost";
	public final static String FIELD_COGNOMUSUARI = "cognomusuari";
	public final static String FIELD_COMALBARA = "comalbara";
	public final static String FIELD_COMENTRDEVOL = "comentrdevol";
	public final static String FIELD_COMLINCOMPRA = "comlincompra";
	public final static String FIELD_COMLINVENDA = "comlinvenda";
	public final static String FIELD_COMPLETATSN = "completatsn";
	public final static String FIELD_COMPRA_ID = "compra_id";
	public final static String FIELD_COMSORTDEVOL = "comsortdevol";
	public final static String FIELD_COORDX = "coordx";
	public final static String FIELD_COORDY = "coordy";
	public final static String FIELD_CORREUEM = "correuem";
	public final static String FIELD_DATAALBARA = "dataalbara";
	public final static String FIELD_DATAALERTA = "dataalerta";
	public final static String FIELD_DATAALTA = "dataalta";
	public final static String FIELD_DATABAIXA = "databaixa";
	public final static String FIELD_DATACOMPRA = "datacompra";
	public final static String FIELD_DATAENTRDEVOLUCIO = "dataentrdevolucio";
	public final static String FIELD_DATAORDRE = "dataordre";
	public final static String FIELD_DATAPREVLLIURAMENT = "dataprevlliurament";
	public final static String FIELD_DATASORTDEVOLUCIO = "datasortdevolucio";
	public final static String FIELD_DATATRANSFERENCIA = "datatransferencia";
	public final static String FIELD_DATAVENDA = "datavenda";
	public final static String FIELD_DESTI_ID = "desti_id";
	public final static String FIELD_DESTILOCAL_ID = "destilocal_id";
	public final static String FIELD_ENTRADADEVOLUCIO_ID = "entradadevolucio_id";
	public final static String FIELD_ESTOC = "estoc";
	public final static String FIELD_ESTOCMINIM = "estocminim";
	public final static String FIELD_ID_ALBARA = "id_albara";
	public final static String FIELD_ID_ALERTA = "id_alerta";
	public final static String FIELD_ID_CLIENT = "id_client";
	public final static String FIELD_ID_COMPRA = "id_compra";
	public final static String FIELD_ID_ENTRADADEVOLUCIO = "id_entradadevolucio";
	public final static String FIELD_ID_GRUP = "id_grup";
	public final static String FIELD_ID_IDIOMA = "id_idioma";
	public final static String FIELD_ID_LINCOMPRA = "id_lincompra";
	public final static String FIELD_ID_LINENTRADADEVOLUCIO = "id_linentradadevolucio";
	public final static String FIELD_ID_LINIAALBARA = "id_liniaalbara";
	public final static String FIELD_ID_LINSORTDEVOLUCIO = "id_linsortdevolucio";
	public final static String FIELD_ID_LINTRANSFERENCIA = "id_lintransferencia";
	public final static String FIELD_ID_LINVENDA = "id_linvenda";
	public final static String FIELD_ID_LOCAL = "id_local";
	public final static String FIELD_ID_MOTIUDEVOLUCIO = "id_motiudevolucio";
	public final static String FIELD_ID_MOVIMENT = "id_moviment";
	public final static String FIELD_ID_PRODUCTE = "id_producte";
	public final static String FIELD_ID_PRODUCTEPROVEIDOR = "id_producteproveidor";
	public final static String FIELD_ID_PROVEIDOR = "id_proveidor";
	public final static String FIELD_ID_PROVINCIA = "id_provincia";
	public final static String FIELD_ID_SORTIDADEVOL = "id_sortidadevol";
	public final static String FIELD_ID_SUBGRUP = "id_subgrup";
	public final static String FIELD_ID_TIPUSALERTA = "id_tipusalerta";
	public final static String FIELD_ID_TIPUSMOVIMENT = "id_tipusmoviment";
	public final static String FIELD_ID_TRANSFERENCIA = "id_transferencia";
	public final static String FIELD_ID_USUARI = "id_usuari";
	public final static String FIELD_ID_VENDA = "id_venda";
	public final static String FIELD_IDIOMA_ID = "idioma_id";
	public final static String FIELD_IDS_CLIENT = "ids_client";
	public final static String FIELD_IDS_LOCAL = "ids_local";
	public final static String FIELD_IDS_PROVEIDOR = "ids_proveidor";
	public final static String FIELD_LINEAALBARADEV_ID = "lineaalbaradev_id";
	public final static String FIELD_LOCAL_ID = "local_id";
	public final static String FIELD_LOGIN = "login";
	public final static String FIELD_MOTIUENTRDEVOL_ID = "motiuentrdevol_id";
	public final static String FIELD_MOTIUSORTDEVOL_ID = "motiusortdevol_id";
	public final static String FIELD_MOVIMENT_ID = "moviment_id";
	public final static String FIELD_NIF = "nif";
	public final static String FIELD_NOM = "nom";
	public final static String FIELD_NOMCLIENT = "nomclient";
	public final static String FIELD_NOMLOCAL = "nomlocal";
	public final static String FIELD_NOMPRODUCTE = "nomproducte";
	public final static String FIELD_NOMPROVEIDOR = "nomproveidor";
	public final static String FIELD_NOMUSUARI = "nomusuari";
	public final static String FIELD_NUMUNITATSORDRE = "numunitatsordre";
	public final static String FIELD_NUMUNITREBUDES = "numunitrebudes";
	public final static String FIELD_NUMUNITSORTIDES = "numunitsortides";
	public final static String FIELD_ORIGEN_ID = "origen_id";
	public final static String FIELD_ORIGLOCAL_ID = "origlocal_id";
	public final static String FIELD_PAIS = "pais";
	public final static String FIELD_PASSWORD = "password";
	public final static String FIELD_POBLACIO = "poblacio";
	public final static String FIELD_PREUCOMPRA = "preucompra";
	public final static String FIELD_PREUVENDA = "preuvenda";
	public final static String FIELD_PRODUCTE_ID = "producte_id";
	public final static String FIELD_PRODUCTEGRUP_ID = "productegrup_id";
	public final static String FIELD_PRODUCTESUBGRUP_ID = "productesubgrup_id";
	public final static String FIELD_PROVINCIA_ID = "provincia_id";
	public final static String FIELD_ROL = "rol";
	public final static String FIELD_SORTIDADEVOLUCIO_ID = "sortidadevolucio_id";
	public final static String FIELD_TELEFON = "telefon";
	public final static String FIELD_TELEFONFIX = "telefonfix";
	public final static String FIELD_TELEFONMOBIL = "telefonmobil";
	public final static String FIELD_TIPUSALERTA_ID = "tipusalerta_id";
	public final static String FIELD_TIPUSLOCAL = "tipuslocal";
	public final static String FIELD_TIPUSMOVIMENT_ID = "tipusmoviment_id";
	public final static String FIELD_TRANSFERENCIA_ID = "transferencia_id";
	public final static String FIELD_UNITATS = "unitats";
	public final static String FIELD_VENDA_ID = "venda_id";
	public final static String FIELD_VIGENTSN = "vigentsn";
	//****************************************************************************
	
	//Clau per a l'idioma català
	public final static String LANGUAGE_CATALAN = "ca-ES";
	//Clau per a l'idioma anglès
	public final static String LANGUAGE_ENGLISH = "en-GB";
	//Inici del nom dels fitxers d'idiomes
	public final static String LANGUAGE_FILE_NAME = "resources";
	//Ruta dels fitxers d'idioma
	public final static String LANGUAGE_PATH = "tokens";
	//Clau per a l'idioma castellà
	public final static String LANGUAGE_SPANISH = "es-ES";

	//Format de dates de postgreSQL
	public static final String POSTGRESSQL_DATE_FORMAT = "yyyy-MM-dd";
	
	//Propietat que indica el directory de l'usuari
	public final static String PROPERTY_USER_DIR = "user.dir";
	
	//Claus del fitxer de configuració
	public final static String SETTING_LANGUAGE = "Language";
	//Clau de la URL el servidor RMI
	public final static String SETTING_RMI_URL = "RMI_Server_Url";
	//Clau del port de connexió RMI
	public final static String SETTING_RMI_PORT = "RMI_Port";
	//Clau de l'identificador de la connexió RMI
	public final static String SETTING_RMI_NAME = "RMI_Name";
	//Fitxer de configuració pel client
	public final static String SETTINGS_FILE_CLIENT = "clientSettings.properties";
	//Fitxer de configuració pel servidor
	public final static String SETTINGS_FILE_SERVER = "serverSettings.properties";
	
	//*********************** Taules de la base de dades **************************
	public final static String TABLE_ALBARA = "albara";
	public final static String TABLE_ALERTA = "alerta";
	public final static String TABLE_CLIENT = "client";
	public final static String TABLE_COMPRA = "compra";
	public final static String TABLE_ENTRADADEVOLUCIO = "entradadevolucio";
	public final static String TABLE_EXISTENCIES = "existencies";
	public final static String TABLE_GRUP = "grup";
	public final static String TABLE_IDIOMA = "idioma";
	public final static String TABLE_LINALBARA = "linalbara";
	public final static String TABLE_LINCOMPRA = "lincompra";
	public final static String TABLE_LINENTRADADEVOLUCIO = "linentradadevolucio";
	public final static String TABLE_LINSORTIDADEVOLUCIO = "linsortidadevolucio";
	public final static String TABLE_LINTRANSFERENCIA = "lintransferencia";
	public final static String TABLE_LINVENDA = "linvenda";
	public final static String TABLE_LOCAL = "local";
	public final static String TABLE_MOTIUDEVOLUCIO = "motiudevolucio";
	public final static String TABLE_MOVIMENT = "moviment";
	public final static String TABLE_PRODUCTE = "producte";
	public final static String TABLE_PRODUCTEPROVEIDOR = "producteproveidor";
	public final static String TABLE_PROVEIDOR = "proveidor";
	public final static String TABLE_PROVINCIA = "provincia";
	public final static String TABLE_SORTIDADEVOLUCIO = "sortidadevolucio";
	public final static String TABLE_SUBGRUP = "subgrup";
	public final static String TABLE_TIPUSALERTA = "tipusalerta";
	public final static String TABLE_TIPUSMOVIMENT = "tipusmoviment";
	public final static String TABLE_TRANSFERENCIA = "transferencia";
	public final static String TABLE_USUARI = "usuari";
	public final static String TABLE_VENDA = "venda";
	//****************************************************************************
	
	
}
