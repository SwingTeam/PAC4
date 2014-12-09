package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.MotiuDevolucio;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class MotiuDevolucioManager  {
	
	public static final String MOTIU_DEVOLUCIO_DEFECTE = "D";
	public static final String MOTIU_DEVOLUCIO_EQUIVOCADA= "E";
	public static final String MOTIU_DEVOLUCIO_NO_DEMANADA= "N";

	
	private DatabaseManager	db;
	
	public MotiuDevolucioManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	
	 /***
	  * 
	  * Torna tots els motius de devolucio
	  * 
	  * @return LLista de motius de devolucio 
	  * @throws STException 
	  */	
	public List<MotiuDevolucio> list() throws STException 
	{							
		List<MotiuDevolucio> list = new ArrayList<MotiuDevolucio>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM MotiuDevolucio ORDER BY id_motiudevolucio");

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				MotiuDevolucio motiuDevolucio= getFromResultSet(resultSet);
				list.add(motiuDevolucio);
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
	
	public MotiuDevolucio getFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim MotiuDevolucio
		MotiuDevolucio motiuDevolucio = new MotiuDevolucio();
		
		motiuDevolucio.setIdMotiuDevolucio(resultSet.getString(Constants.FIELD_ID_MOTIUDEVOLUCIO));
		return motiuDevolucio;
	}	
}
