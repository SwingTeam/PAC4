package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.SubGrup;




/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class SubGrupManager  {
	private DatabaseManager	db;
	
	public SubGrupManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els subGrups
	  * 
	  * @return LLista de subGrups 
	  * @throws STException 
	  */	
	public List<SubGrup> GetByGrupId(Integer grupId) throws STException 
	{							
		List<SubGrup> listSubGrup = new ArrayList<SubGrup>();
				
		//Obtenim albara de la BBDD
		String sql= "SELECT * FROM SubGrup ";
		if (grupId != null) 
		{
			sql+= " WHERE id_grup=" + grupId ;
		}
		sql+= " ORDER BY nom";
		ResultSet resultSet= db.selectData(sql);

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				SubGrup subGrup= GetFromResultSet(resultSet);
				listSubGrup.add(subGrup);
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
		return listSubGrup;
	}
	
	private SubGrup GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim subGrup
		SubGrup subGrup = new SubGrup();
		
		subGrup.setIdSubGrup(resultSet.getInt(Constants.FIELD_ID_SUBGRUP));
		subGrup.setIdGrup(resultSet.getInt(Constants.FIELD_ID_GRUP));
		subGrup.setNom(resultSet.getString(Constants.FIELD_NOM));
		return subGrup;
	}	
}
