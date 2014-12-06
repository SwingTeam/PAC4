package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.LocalST;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LocalManager  {
	
	
	private DatabaseManager	db;
	
	public LocalManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els motius de devolucio
	  * 
	  * @return LLista de motius de devolucio 
	  * @throws STException 
	  */	
	public List<LocalST> List() throws STException 
	{							
		List<LocalST> list = new ArrayList<LocalST>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM local ORDER BY nomlocal");

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				LocalST local= GetFromResultSet(resultSet);
				list.add(local);
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
		return list;
	}
	
	private LocalST GetFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim local
		LocalST local = new LocalST();
		
		local.setAdreca(resultSet.getString(Constants.FIELD_ADRECA));
		local.setCif(resultSet.getString(Constants.FIELD_CIF));
		local.setCoordX(resultSet.getInt(Constants.FIELD_COORDX));
		local.setCoordY(resultSet.getInt(Constants.FIELD_COORDY));
		local.setDataAlta(resultSet.getDate(Constants.FIELD_DATAALTA));
		local.setIdLocal(resultSet.getString(Constants.FIELD_ID_LOCAL));
		local.setNomLocal(resultSet.getString(Constants.FIELD_NOMLOCAL));
		local.setPais(resultSet.getString(Constants.FIELD_PAIS));
		local.setProvincia_id(resultSet.getString(Constants.FIELD_PROVINCIA_ID));
		local.setTelefon(resultSet.getString(Constants.FIELD_TELEFON));
		local.setTipusLocal(resultSet.getString(Constants.FIELD_TIPUSLOCAL));
		
		
		return local;
	}	
}
