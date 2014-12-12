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
import uoc.tdp.pac4.st.common.dto.LocalST;
import uoc.tdp.pac4.st.common.dto.LocalSTer;
import uoc.tdp.pac4.st.common.dto.Usuari;



/***
 * 
 * 
 * @author Swing Team - 2014
 *
 */
public class LocalManager  {
	
	public static final String TIPUS_MOVIMENT_MAGATZEM_CENTRAL= "C"; //magatzem central
	public static final String TIPUS_MOVIMENT_TALLER= "L"; // taller.local

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
	public List<LocalST> list() throws STException 
	{							
		List<LocalST> list = new ArrayList<LocalST>();
				
		//Obtenim albara de la BBDD
		ResultSet resultSet= db.selectData("SELECT * FROM local ORDER BY nomlocal");

		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				LocalST local= getFromResultSet(resultSet);
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
	
	public LocalST getFromResultSet(ResultSet resultSet) throws SQLException 
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
	
	/***** BEGIN    MÈTODES PEL SUBSISTEMA DE MANTENIMENT ********/
	/**
	 * @author emarsal2
	 * @since dijous 10
	 * Mètode per cercar un local dins la taula, la cerca està parametritzada 
	 * @param id valor pel qual es fa la cerca
	 * @param field nom del camp/atribut per qual es fa la cerca
	 * @return Local amb les dades dins si l'ha trobat a la BD
	 * @throws STException
	 */
	public LocalSTer searchLocal(String id, String field)		throws STException
	{
		LocalSTer local = new LocalSTer(); 
			String sql = "select ids_local,nomlocal,cif,telefon,"+Constants.FIELD_ADRECA+",codpost,provincia_id,pais,tipuslocal,dataalta,coordx,coordy from local where "+field+" like '"+id+"';";
			ResultSet rs= db.selectData(sql);
			try 
			{	
				if (rs.next())
				{
					local = this.GetLocalFromResultSet(rs);
				} else
				{
					local.setCIF("000000000");
				}
			}
			catch(SQLException e)
			{
				throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
			}
			finally 
			{
				db.closeResultSet(rs);
			}
			return local;
	}
	/**
	 * @author emarsal2
	 * @since dijous 10
	 * Mètode que recupera les dades d'un local a partir d'un resulset
	 * @param resultSet
	 * @return Local amb les dades recuperades dins
	 * @throws SQLException
	 */

	private LocalSTer GetLocalFromResultSet(ResultSet resultSet) throws SQLException 
	{
		LocalSTer local = new LocalSTer();
		String tmpField;
		local.setData_alta(resultSet.getDate(Constants.FIELD_DATAALTA));
		local.setCp(resultSet.getString(Constants.FIELD_CODPOST));
		local.setId(resultSet.getInt(Constants.FIELD_IDS_LOCAL));
		tmpField = resultSet.getString(Constants.FIELD_NOMLOCAL);
		if (!resultSet.wasNull())
			local.setName(tmpField);
		else		
			local.setName(null);
		tmpField = resultSet.getString(Constants.FIELD_POBLACIO);
		if (!resultSet.wasNull())
			local.setCity(tmpField);
		else
			local.setCity(null);
		local.setAddress(resultSet.getString(5)); // get Address
		local.setCountry(resultSet.getString(Constants.FIELD_PAIS));
		local.setProvince(resultSet.getString(Constants.FIELD_PROVINCIA_ID));
		local.setCIF(resultSet.getString(Constants.FIELD_CIF));
		local.setTelephone(resultSet.getString(Constants.FIELD_TELEFON));
		local.setLocalTipus(resultSet.getString(Constants.FIELD_TIPUSLOCAL));
		local.setCoordX(resultSet.getInt(Constants.FIELD_COORDX));
		local.setCoordY(resultSet.getInt(Constants.FIELD_COORDY));
		return local;
	}	

	
	/**
	 * @autor emarsal2
	 * @since dijous 10
	 * Mètode per esborrar un local
	 * @param localName
	 * @throws STException
	 */
	public void deleteLocal (int idLocal) throws STException
	{
		try 
		{
           db.deleteData("DELETE FROM local WHERE nomLocal="+idLocal+";");
		} catch (STException e) {
			throw e;
		}
		finally 
		{
			db.finishTransaction(true);
		}	
	}
	
	public String updateLocal (LocalSTer local) throws STException 
	{
		String sql = prepareStringUpdate(local);
		try {
			System.out.println("Before do the update");
			System.out.println(sql);
			db.updateData(sql);
			System.out.println(sql);
		} catch (STException e) {
			throw e;
		}finally
		{
			db.finishTransaction(true);
		}
		return local.getName();
	}
	
	/**
	 * @author emarsal2
	 * @since dilluns 8
	 * M�tode que serveix per construir la cadena sql a partir de les dades d'un TALLER per realitzar un UPDATE
	 * @param Local with the fields full of new values
	 * @return String with part of sql statement to do update
	 */
	 private String prepareStringUpdate (LocalSTer local)
	 {
		 String sql = null;
		 sql = "UPDATE "+Constants.TABLE_LOCAL+" SET ";
		 sql += Constants.FIELD_CIF+"='"+local.getCIF()+"'";
		 if (local.getName() != null)
		 	sql += ", "+Constants.FIELD_NOMLOCAL+"='"+local.getName()+"'";
		 else
			 sql += ", "+Constants.FIELD_NOMLOCAL+"=null";
		 if (local.getAddress() != null)
		 	sql += ", "+Constants.FIELD_ADRECA+"='"+local.getAddress()+"'";
		 else
			sql += ", "+Constants.FIELD_ADRECA+"=null";
		 if (local.getCity() != null)
		 	sql += ", "+Constants.FIELD_POBLACIO+"='"+local.getCity()+"'";
		 else
			 sql += ", "+Constants.FIELD_POBLACIO+"=null";
		 if (local.getCp() != null)
		 	sql += ", "+Constants.FIELD_CODPOST+"='"+local.getCp()+"'";
		 else
			 sql += ", "+Constants.FIELD_CODPOST+"=null";
		  if (local.getTelephone()!=null)
		 	sql += ", "+Constants.FIELD_TELEFON+"='"+local.getTelephone()+"'";
		 else
			sql += ", "+Constants.FIELD_TELEFON+"=null";
		 if (local.getProvince()!=null)
			 sql += ", "+Constants.FIELD_PROVINCIA_ID+"='"+local.getProvince()+"'";
		 else
			 sql += ", "+Constants.FIELD_PROVINCIA_ID+"=null";
		 if (local.getCountry() != null)
		 	sql += ", "+Constants.FIELD_PAIS+"='"+local.getCountry()+"'";
		 else
			 sql += ", "+Constants.FIELD_PAIS+"=null";
		 if (local.getCoordX() != 0)
			 sql +=", "+Constants.FIELD_COORDX+"="+local.getCoordX();
		 else
			 sql += ", "+Constants.FIELD_COORDX+"=null";
		 if (local.getCoordY() != 0)
			 sql +=", "+Constants.FIELD_COORDY+"="+local.getCoordY();
		 else
			 sql += ", "+Constants.FIELD_COORDY+"=null";
		 if (local.getLocalTipus() != null)
			 sql +=", "+Constants.FIELD_TIPUSLOCAL+"='"+local.getLocalTipus()+"'";
		 else
			 sql +=", "+Constants.FIELD_TIPUSLOCAL+"=null";
		 
		 sql += " WHERE ids_local ="+local.getId()+";";
		 return sql;
	}
	
	
	/**
	 * @author emarsal2
	 * @since dijous 10
	 * Mètode per afegir un local
	 * @param local
	 * @return int amb el id del local que s'ha creat
	 * @throws STException
	 */
	public int addLocal (LocalSTer local) throws STException
	{
		Map<String, Object> hashMap = new HashMap<String, Object>();

		if (local.getAddress() != null)
			hashMap.put(Constants.FIELD_ADRECA,local.getAddress());
			hashMap.put(Constants.FIELD_CODPOST,local.getCp());
			hashMap.put(Constants.FIELD_DATAALTA,local.getData_alta());
		if (local.getProvince() != null)
			hashMap.put(Constants.FIELD_PROVINCIA_ID,local.getProvince());
		hashMap.put(Constants.FIELD_CIF,local.getCIF());
		if (local.getName() != null)
			hashMap.put(Constants.FIELD_NOMLOCAL,local.getName());
		if (local.getCountry() != null)
			hashMap.put(Constants.FIELD_PAIS,local.getCountry());
		if (local.getCity()  != null)
			hashMap.put(Constants.FIELD_POBLACIO,local.getCity());
		hashMap.put(Constants.FIELD_TELEFON,local.getTelephone());
		if (local.getCoordX() != 0)
			hashMap.put(Constants.FIELD_COORDX,local.getCoordX());
		if (local.getCoordY() != 0)
			hashMap.put(Constants.FIELD_COORDY,local.getCoordY());
		if (local.getLocalTipus() != null)
			hashMap.put(Constants.FIELD_TIPUSLOCAL,local.getLocalTipus());
		return db.insertDataAndReturnId(Constants.TABLE_LOCAL, hashMap);
	}

	/**
	 * @author emarsal2
	 * @since dissabte 6
	 * M�tode que donat un CIF el cerca dins la BD si el troba retorna cert
	 * en cas contrari retorna fals
	 * @param cif
	 * @return true if find the cif inside Database false otherwise
	 * @throws STException
	 */
	public boolean findCIF (String cif) throws STException
	{
		ResultSet rs= db.selectData("SELECT count(*) FROM local where cif like '"+cif+"'");
		boolean trobat =false;
		try 
		{	
			if (rs.next())
			{
				if (rs.getInt(1)>0)
					trobat=true;
			}
		}
		catch(SQLException e)
		{
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		finally 
		{
			db.closeResultSet(rs);
		}
		return trobat;
	}
	
	
	/***** END    MÈTODES PEL SUBSISTEMA DE MANTENIMENT ********/

	/******************** Mètodes per a tots els subsistemes ************************/
	 /***
	  * 
	  * Torna tots els motius de devolucio
	  * 
	  * @param usuari Instància d'Usuari amb
	  * les dades de l'usuari que fa la crida.
	  * @return LLista de motius de devolucio 
	  * @throws STException 
	  */	
	public List<LocalST> list(Usuari usuari) throws STException 
	{							
		List<LocalST> list = new ArrayList<LocalST>();
				
		//Obtenim la llista de locals en funció
		//del rol de l'usuari
		String sql = "SELECT * FROM " + Constants.TABLE_LOCAL + " %s ORDER BY " + Constants.FIELD_NOMLOCAL;
		if (usuari.getRol() != Constants.ROLE_ADMIN)
			sql = String.format(sql, "WHERE " + Constants.FIELD_LOCAL_ID + " = " + usuari.getIdLocal());
		
		ResultSet resultSet= db.selectData(sql);
		try 
		{		
			//Llegim resultat
			while (resultSet.next()) 
			{
				LocalST local= getFromResultSet(resultSet);
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
	/******************** End Mètodes per a tots els subsistemes ********************/
	 
	 
	
}
