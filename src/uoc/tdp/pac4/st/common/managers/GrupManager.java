package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.Grup;




/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class GrupManager  {
	private DatabaseManager	db;
	
	public GrupManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els grups
	  * 
	  * @return LLista de grups 
	  * @throws STException 
	  */	
	public List<Grup> List() throws STException 
	{							
		List<Grup> listGrup = new ArrayList<Grup>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM Grup ORDER BY nom");

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				Grup grup= GetFromResultSet(resultSet);
				listGrup.add(grup);
			}			
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			//Molt important: Tanquem connexió, statement i resulset.
			db.closeResultSet(resultSet);
		}
		return listGrup;
	}
	
	private Grup GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim grup
		Grup grup = new Grup();
		
		grup.setIdGrup(resultSet.getInt(Constants.FIELD_ID_GRUP));
		grup.setNom(resultSet.getString(Constants.FIELD_NOM));
		return grup;
	}	
}
