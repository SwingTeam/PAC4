package uoc.tdp.pac4.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import uoc.tdp.pac4.server.exception.DatabaseException;

/***
 * Database manager, wraps connection access and provides common SQL execution methods.
 * 
 * @author Roi Neira
 *
 */
public class DatabaseManager {

	private Connection connection;
	
	public DatabaseManager(String driver, String url, String userName, String password ) throws DatabaseException{		
		connection = GetConnection(driver, url, userName, password);
	}
	
	
	public PreparedStatement GetPreparedStatement(String sql) throws SQLException 
	{
		return this.connection.prepareStatement( sql,
				 ResultSet.TYPE_SCROLL_INSENSITIVE,
				 ResultSet.CONCUR_UPDATABLE);
		
	}
	
	public void RunPreparedStatement(PreparedStatement preparedStatement) throws SQLException 
	{		
		preparedStatement.execute();	
		preparedStatement.close();			
	}
	
	public ResultSet GetResultSet(String sql) throws SQLException 
	{
		Statement statement = null;
		ResultSet resultSet = null;
		
		statement = connection.createStatement(
			ResultSet.TYPE_SCROLL_INSENSITIVE,
			ResultSet.CONCUR_UPDATABLE);			
					
		resultSet= statement.executeQuery(sql);
	
		
		return resultSet;		
	}

	private Connection GetConnection(String driver, String url, String userName, String password ) throws DatabaseException{
		
		try {
						
			//Check if driver can be instantiated
			Class.forName(driver);		
			
			//Get connection to DB server using provided credentials
			return DriverManager.getConnection(url,
					userName, 
					password);
			
		} catch (ClassNotFoundException exc) {
			//	Driver JDBC not found
			throw new DatabaseException(DatabaseException.DRIVER_NOT_FOUND);
		} catch (SQLException exc) {
			//	can't connect to database, 
			throw new DatabaseException(DatabaseException.ERROR_CONECTING_DB);
		} catch (Exception exc){
			//	Unexpected
			throw new DatabaseException(DatabaseException.UNEXPECTED_EXCEPTION);
		}	
	}
}
