package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uoc.tdp.pac4.st.common.Constants;
import uoc.tdp.pac4.st.common.STException;
import uoc.tdp.pac4.st.common.TokenKeys;
import uoc.tdp.pac4.st.common.dto.LinAlbara;
import uoc.tdp.pac4.st.common.dto.LinSortidaDevolucio;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LinSortidaDevolucioManager  {
	private DatabaseManager	db;
	
	public LinSortidaDevolucioManager(DatabaseManager _db) throws STException {
		this.db= _db;
	}
	
	public int Add(LinSortidaDevolucio linSortidaDevolucio) throws STException 
	{						
				
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put(Constants.FIELD_COMSORTDEVOL, linSortidaDevolucio.getComSortDevol());
		hashMap.put(Constants.FIELD_ID_LINSORTDEVOLUCIO, linSortidaDevolucio.getIdLinsortDevolucio());
		hashMap.put(Constants.FIELD_MOTIUSORTDEVOL_ID, linSortidaDevolucio.getMotiuSortDevolId());
		hashMap.put(Constants.FIELD_MOVIMENT_ID, linSortidaDevolucio.getMovimentId());
		hashMap.put(Constants.FIELD_ORIGLOCAL_ID, linSortidaDevolucio.getOrigLocalId());
		hashMap.put(Constants.FIELD_SORTIDADEVOLUCIO_ID, linSortidaDevolucio.getSortidaDevolucioId());
		
		return db.insertDataAndReturnId(Constants.TABLE_LINSORTIDADEVOLUCIO, hashMap); 						
	}
	
	 /***
	  * 
	  * Torna una linia d'albarà pel seu ID
	  * 
	  * @param albaraId Identificador de linia d'albarà
	  * 
	  * @return LinAlbara: Linia albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public LinAlbara GetById(int linAlbaraId) throws STException 
	{							
		LinAlbara linAlbara = null;
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.getByColumnValue(Constants.TABLE_LINALBARA, Constants.FIELD_ID_LINIAALBARA, linAlbaraId);

		try 
		{		
			//Llegim resultat
			if (resultSet.next()) 
			{
				linAlbara= getFromResultSet(resultSet);		
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
		return linAlbara;
	}	
	
	 /***
	  * 
	  * Torna totes les linies d'un albara
	  * 
	  * @param albaraId Identificador de l'albarà
	  * 
	  * @return LinAlbara: Linia albarà si ha estat trovat, null en cas contrari 
	  * @throws STException 
	  */	
	public List<LinAlbara> GetByAlbaraId(int albaraId) throws STException 
	{							
		List<LinAlbara> listLinAlbara = new ArrayList<LinAlbara>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.getByColumnValue(Constants.TABLE_LINALBARA, Constants.FIELD_ALBARA_ID, albaraId);

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				LinAlbara linAlbara= getFromResultSet(resultSet);
				listLinAlbara.add(linAlbara);
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
		return listLinAlbara;
	}	
	
	public LinAlbara getFromResultSet(ResultSet resultSet) throws SQLException 
	{
		//Omplim albara 
		LinAlbara linAlbara = new LinAlbara();
		linAlbara.setAlbaraId(resultSet.getInt(Constants.FIELD_ALBARA_ID));
		linAlbara.setIdLiniaAlbara(resultSet.getInt(Constants.FIELD_ID_LINIAALBARA));
		linAlbara.setMovimentId(resultSet.getInt(Constants.FIELD_MOVIMENT_ID));
		linAlbara.setUnitats(resultSet.getInt(Constants.FIELD_UNITATS));
		return linAlbara;
	}	
}
