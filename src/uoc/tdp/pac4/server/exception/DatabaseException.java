package uoc.tdp.pac4.server.exception;

/***
 * Database manager, wraps connection access and provides common SQL execution methods.
 * 
 * @author Roi Neira
 *
 */
public class DatabaseException  extends Exception {

	private static final long serialVersionUID = 1L;
	public static final String DRIVER_NOT_FOUND = "DRIVER_NOT_FOUND";
    public static final String ERROR_CONECTING_DB = "ERROR_CONECTING_DB";    
    public static final String ERROR_QUERYNG_DB = "ERROR_QUERYNG_DB";
    public static final String ERROR_SAVING_DB = "ERROR_SAVING_DB";
    public static final String UNEXPECTED_EXCEPTION= "UNEXPECTED_EXCEPTION";
    
    
	public DatabaseException()
	{
		super();
	}
	
	public DatabaseException(String exceptionMessage)
	{
		super(exceptionMessage);
	}
}
