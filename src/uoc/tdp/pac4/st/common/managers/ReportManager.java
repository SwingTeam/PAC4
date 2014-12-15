package uoc.tdp.pac4.st.common.managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;

/***
 * Classe encarregada de gestionar
 * tots els informes
 *  
 * @author Swing Team - 2014
 *
 */
public class ReportManager  {
	private DatabaseManager	_databaseManager = null;
	private final String fFirstDate = "firstDate";
	private final String fGName = "g_" + Constants.FIELD_NOM;
	private final String fQuantity = "fQuantity";
	private final String fRowCount = "rowCount";
	private final String fSgName = "sg_" + Constants.FIELD_NOM;
	private final String fAmount = "fAmount";
	private final String fTotalInput = "fInput";
	private final String fTotalOutput = "fOutput";
	private final String fUnitPrice = "unitPrice";
	
	public ReportManager(DatabaseManager databaseManager) throws STException {
		this._databaseManager = databaseManager;
	}
	
	private void fillinCommonData(ResultSet resultSet, ReportLine reportLine) throws SQLException{
		//Group data
		Grup group = new Grup();
		group.setIdGrup(resultSet.getInt(Constants.FIELD_PRODUCTEGRUP_ID));
		group.setNom(resultSet.getString(fGName));
		
		//Subgroup data
		SubGrup subgroup = new SubGrup();
		subgroup.setIdSubGrup(resultSet.getInt(Constants.FIELD_PRODUCTESUBGRUP_ID));
		subgroup.setNom(resultSet.getString(fSgName));
		
		//Establishment data
		LocalST estab = new LocalST();
		estab.setCoordX(resultSet.getInt(Constants.FIELD_COORDX));
		estab.setCoordY(resultSet.getInt(Constants.FIELD_COORDY));
		estab.setNomLocal(resultSet.getString(Constants.FIELD_NOMLOCAL));
		estab.setTelefon(resultSet.getString(Constants.FIELD_TELEFON));
		estab.setProvincia_id(resultSet.getString(Constants.FIELD_PROVINCIA_ID));
		estab.setCif(resultSet.getString(Constants.FIELD_CIF));
		
		Producte product = new Producte();
		product.setIdProducte(resultSet.getString(Constants.FIELD_PRODUCTE_ID));
		product.setNomProducte(resultSet.getString(Constants.FIELD_NOMPRODUCTE));
		
		//Provider data
		Proveidor provider = new Proveidor();
		provider.setIdProveidor(resultSet.getString(Constants.FIELD_PROVEIDOR_ID));
		provider.setNomProveidor(resultSet.getString(Constants.FIELD_NOMPROVEIDOR));
		
		reportLine.setGroup(group);
		reportLine.setEstablishment(estab);
		reportLine.setProduct(product);
		reportLine.setProvider(provider);
		reportLine.setSubgroup(subgroup);
	}
	
	/***
	 * Retorna una llista d'un producte
	 * o de tots els producte si s'indica
	 * null al paràmetre d'entrada.
	 * 
	 * @param productId Identificador d'un
	 * producte determinat o null si es vol
	 * obtenir tots els productes.
	 * @return List<ProducteReport> una llista
	 * de ProducteReport amb el resultat de
	 * la consulta.
	 * @throws STException
	 */
	public List<ProducteReport> getProductList(String productId) throws STException{
		List<ProducteReport> result = new ArrayList<ProducteReport>();
		String sql 
			= "SELECT p." + Constants.FIELD_ID_PRODUCTE + ", " + 
						"p." + Constants.FIELD_NOMPRODUCTE + ", " + 
						"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
						"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
						"g." + Constants.FIELD_NOM + " AS g" + Constants.FIELD_NOM + ", " + 
						"sg." + Constants.FIELD_NOM + " AS sg" + Constants.FIELD_NOM + " " +
					"FROM " + Constants.TABLE_PRODUCTE + " p " +
						"INNER JOIN " + Constants.TABLE_GRUP + " g " +
							"ON p." + Constants.FIELD_PRODUCTEGRUP_ID + " = g." + Constants.FIELD_ID_GRUP + " " +
						"INNER JOIN " + Constants.TABLE_SUBGRUP + " sg " +
							"ON p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = sg." + Constants.FIELD_ID_SUBGRUP + " ";
		
		if (productId != null)
			sql += "WHERE p." + Constants.FIELD_PRODUCTE_ID + " = '" + productId + "' ";
		
		sql += "ORDER BY g." + Constants.FIELD_NOM + " ASC, " +
						"sg." + Constants.FIELD_NOM + " ASC, " +
						"p." + Constants.FIELD_NOMPRODUCTE + " ASC;";

		try{
			ResultSet resultSet = this._databaseManager.selectData(sql);
			
			if (resultSet != null){
					while(resultSet.next()){
						ProducteReport item = new ProducteReport();
						item.setIdProducte(resultSet.getString(Constants.FIELD_ID_PRODUCTE));
						item.setNomProducte(resultSet.getString(Constants.FIELD_NOMPRODUCTE));
						item.setGroupId(resultSet.getInt(Constants.FIELD_PRODUCTEGRUP_ID));
						item.setGroupName(resultSet.getString("g" + Constants.FIELD_NOM));
						item.setSubgroupId(resultSet.getInt(Constants.FIELD_PRODUCTESUBGRUP_ID));
						item.setSubgroupName(resultSet.getString("sg" + Constants.FIELD_NOM));
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		
		return result;
	}
	
	/***
	 * Retorna una llista d'instàncies de
	 * ReturningReportLine, amb el resultat
	 * de l'informe de devolucions de recanvis.
	 * 
	 * @param reportSelectorData Instància de ReportSelectorData
	 * amb els valors seleccionats per l'usuari.
	 * 
	 * @return
	 * @throws STException
	 */
	public List<ReturningReportLine> getReturningReport(ReportSelectorData reportSelectorData) throws STException{
		List<ReturningReportLine> result = new ArrayList<ReturningReportLine>();
		StringBuilder sql = new StringBuilder();
		
		//SELECT
		sql.append("SELECT " +
					"SUM(" + Constants.FIELD_NUMUNITSORTIDES + ") AS " + fTotalOutput + ", " +
					"m." + Constants.FIELD_PRODUCTE_ID + ", " +
					"ld." + Constants.FIELD_ORIGLOCAL_ID + ", " +
					"l." + Constants.FIELD_ID_LOCAL + ", " +
					"l." + Constants.FIELD_NOMLOCAL + ", " +
					"l." + Constants.FIELD_CIF + ", " +
					"l." + Constants.FIELD_TELEFON + ", " +
					"l." + Constants.FIELD_ADRECA + ", " +
					"l." + Constants.FIELD_CODPOST + ", " +
					"l." + Constants.FIELD_PROVINCIA_ID + ", " +
					"l." + Constants.FIELD_PAIS + ", " +
					"l." + Constants.FIELD_TIPUSLOCAL + ", " +
					"l." + Constants.FIELD_DATAALTA + ", " +
					"l." + Constants.FIELD_COORDX + ", " +
					"l." + Constants.FIELD_COORDY + ", " +
					"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
					"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
					"p." + Constants.FIELD_NOMPRODUCTE + ", " +
					"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
					"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
					"g." + Constants.FIELD_NOM + " AS " + fGName + ", " +
					"sg." + Constants.FIELD_NOM + " AS " + fSgName + " ");
		
		//Quantitat total venuda
		sql.append(", (" +
						"SELECT " +
							"SUM(lc." + Constants.FIELD_NUMUNITREBUDES + ") " +
						"FROM " +
							Constants.TABLE_LINCOMPRA + " lc " +
							"INNER JOIN " + Constants.TABLE_MOVIMENT + " m " +
							"ON lc." + Constants.FIELD_MOVIMENT_ID + " = m." + Constants.FIELD_ID_MOVIMENT + " " +
						"WHERE " +
							"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Compra + "' " +
							"AND " +
							"m." + Constants.FIELD_DATAORDRE + 
									" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
							"AND " +
							"m." + Constants.FIELD_DATAORDRE + 
									" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' " +
						") AS " + fQuantity);
		//Preu mig
		sql.append(", (" +
				"SELECT " +
					"AVG(lc." + Constants.FIELD_PREUCOMPRA + "::numeric::float8) " +
				"FROM " +
					Constants.TABLE_LINCOMPRA + " lc " +
					"INNER JOIN " + Constants.TABLE_MOVIMENT + " m " +
					"ON lc." + Constants.FIELD_MOVIMENT_ID + " = m." + Constants.FIELD_ID_MOVIMENT + " " +
				"WHERE " +
					"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Compra + "' " +
					"AND " +
					"m." + Constants.FIELD_DATAORDRE + 
							" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
					"AND " +
					"m." + Constants.FIELD_DATAORDRE + 
							" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' " +
				") AS " + fUnitPrice + " ");
		
		//FROM
		sql.append("FROM " +
				Constants.TABLE_MOVIMENT + " m INNER JOIN " + Constants.TABLE_LINSORTIDADEVOLUCIO + " ld " +
				"ON m." + Constants.FIELD_ID_MOVIMENT + " = ld." + Constants.FIELD_MOVIMENT_ID + " " +
				"INNER JOIN " + Constants.TABLE_LOCAL + " l " +
				"ON ld." + Constants.FIELD_ORIGLOCAL_ID + " = l." + Constants.FIELD_ID_LOCAL + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTEPROVEIDOR + " pp " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = pp." + Constants.FIELD_PRODUCTE_ID + " " +
				"INNER JOIN " + Constants.TABLE_PROVEIDOR + " pv " +
				"ON pp." + Constants.FIELD_PROVEIDOR_ID + " = pv." + Constants.FIELD_ID_PROVEIDOR + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTE + " p " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = p." + Constants.FIELD_ID_PRODUCTE + " " +
				"INNER JOIN " + Constants.TABLE_GRUP + " g " +
				"ON p." + Constants.FIELD_PRODUCTEGRUP_ID + " = g." + Constants.FIELD_ID_GRUP + " " +
				"INNER JOIN " + Constants.TABLE_SUBGRUP + " sg " +
				"ON p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = sg." + Constants.FIELD_ID_SUBGRUP + " ");
		
		//WHERE
		sql.append("WHERE " +
				"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Compra + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' ");

		if (!reportSelectorData.getEstablishmentId().equalsIgnoreCase(Constants.ALL))
			sql.append(" AND ld." + Constants.FIELD_ORIGLOCAL_ID + " = '" + reportSelectorData.getEstablishmentId() + "' ");
	
		sql.append("AND " +
					"(");
						boolean first = true;
						for(STTreeNode treeNode : reportSelectorData.getProducts()){
							if (!first)
								sql.append(" OR ");
							
							if (treeNode.getNodeType() == Enums.NodeType.Root){
								sql.append("NOT p." + Constants.FIELD_ID_PRODUCTE + " IS NULL ");
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Group){
								sql.append("p." + Constants.FIELD_PRODUCTEGRUP_ID + " = " + treeNode.getId().toString());
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Subgroup)
								sql.append("p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = " + treeNode.getId().toString());
							
							else
								sql.append("p." + Constants.FIELD_ID_PRODUCTE + " = '" + treeNode.getId().toString() + "'");
							
							first = false;
						}
		sql.append(") ");

		//GROUP BY
		sql.append("GROUP BY " +
				"m." + Constants.FIELD_PRODUCTE_ID + ", " +
				"ld." + Constants.FIELD_ORIGLOCAL_ID + ", " +
				"l." + Constants.FIELD_ID_LOCAL + ", " +
				"l." + Constants.FIELD_NOMLOCAL + ", " +
				"l." + Constants.FIELD_CIF + ", " +
				"l." + Constants.FIELD_TELEFON + ", " +
				"l." + Constants.FIELD_ADRECA + ", " +
				"l." + Constants.FIELD_CODPOST + ", " +
				"l." + Constants.FIELD_PROVINCIA_ID + ", " +
				"l." + Constants.FIELD_PAIS + ", " +
				"l." + Constants.FIELD_TIPUSLOCAL + ", " +
				"l." + Constants.FIELD_DATAALTA + ", " +
				"l." + Constants.FIELD_COORDX + ", " + 
				"l." + Constants.FIELD_COORDY + ", " +
				"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
				"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
				"p." + Constants.FIELD_NOMPRODUCTE + ", " +
				"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
				"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
				"g." + Constants.FIELD_NOM + ", " +
				"sg." + Constants.FIELD_NOM + " ");
		
		//ORDER BY
		sql.append("ORDER BY " +
				fTotalOutput + " " + reportSelectorData.getOrder());
		
		try{
			ResultSet resultSet = this._databaseManager.selectData(sql.toString());
			
			if (resultSet != null){
					while(resultSet.next()){
						ReturningReportLine item = new ReturningReportLine();
						item.setReturnedQuantity(resultSet.getLong(fTotalOutput));
						item.setSalesQuantity(resultSet.getLong(fTotalInput));
						item.setUnitPrice(resultSet.getFloat(fUnitPrice));
						
						this.fillinCommonData(resultSet, item);
						
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		return result;
	}
	
	/***
	 * Retorna una llista d'instàncies de
	 * RotationReportLine, amb el resultat
	 * de l'informe de rotació demanat.
	 * 
	 * @param reportSelectorData Instància de ReportSelectorData
	 * amb els valors seleccionats per l'usuari.
	 * 
	 * @return
	 * @throws STException
	 */
	public List<RotationReportLine> getRotationReport (ReportSelectorData reportSelectorData) throws STException{
		List<RotationReportLine> result = new ArrayList<RotationReportLine>();
		
		StringBuilder sql = new StringBuilder();
		
		//SELECT
		sql.append("SELECT COUNT(*) AS " + fRowCount + ", " +
					//Dins del període, comptarem els dies des de
					//la data del primer moviment
					"MIN(m." + Constants.FIELD_DATAORDRE + ") AS " + fFirstDate + ", " +
					"m." + Constants.FIELD_PRODUCTE_ID + ", " +
					"a." + Constants.FIELD_ORIGEN_ID + ", " +
					"l." + Constants.FIELD_ID_LOCAL + ", " +
					"l." + Constants.FIELD_NOMLOCAL + ", " +
					"l." + Constants.FIELD_CIF + ", " +
					"l." + Constants.FIELD_TELEFON + ", " +
					"l." + Constants.FIELD_ADRECA + ", " +
					"l." + Constants.FIELD_CODPOST + ", " +
					"l." + Constants.FIELD_PROVINCIA_ID + ", " +
					"l." + Constants.FIELD_PAIS + ", " +
					"l." + Constants.FIELD_TIPUSLOCAL + ", " +
					"l." + Constants.FIELD_DATAALTA + ", " +
					"l." + Constants.FIELD_COORDX + ", " +
					"l." + Constants.FIELD_COORDY + ", " +
					"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
					"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
					"p." + Constants.FIELD_NOMPRODUCTE + ", " +
					"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
					"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
					"g." + Constants.FIELD_NOM + " AS " + fGName + ", " +
					"sg." + Constants.FIELD_NOM + " AS " + fSgName + " ");

		//FROM
		sql.append("FROM " +
				Constants.TABLE_MOVIMENT + " m INNER JOIN " + Constants.TABLE_LINALBARA + " la " +
				"ON m." + Constants.FIELD_ID_MOVIMENT + " = la." + Constants.FIELD_MOVIMENT_ID + " " +
				"INNER JOIN " + Constants.TABLE_ALBARA + " a " +
				"ON la." + Constants.FIELD_ALBARA_ID + " = a." + Constants.FIELD_ID_ALBARA + " " +
				"INNER JOIN " + Constants.TABLE_LOCAL + " l " +
				"ON a." + Constants.FIELD_ORIGEN_ID + " = l." + Constants.FIELD_ID_LOCAL + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTEPROVEIDOR + " pp " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = pp." + Constants.FIELD_PRODUCTE_ID + " " +
				"INNER JOIN " + Constants.TABLE_PROVEIDOR + " pv " +
				"ON pp." + Constants.FIELD_PROVEIDOR_ID + " = pv." + Constants.FIELD_ID_PROVEIDOR + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTE + " p " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = p." + Constants.FIELD_ID_PRODUCTE + " " +
				"INNER JOIN " + Constants.TABLE_GRUP + " g " +
				"ON p." + Constants.FIELD_PRODUCTEGRUP_ID + " = g." + Constants.FIELD_ID_GRUP + " " +
				"INNER JOIN " + Constants.TABLE_SUBGRUP + " sg " +
				"ON p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = sg." + Constants.FIELD_ID_SUBGRUP + " ");
		
		//WHERE
		sql.append("WHERE " +
				"(" +
					"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Venda + "' " +
					"OR " +
					"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Transferencia + "' " +
				") " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' ");
				
		if (!reportSelectorData.getEstablishmentId().equalsIgnoreCase(Constants.ALL))
				sql.append(" AND a." + Constants.FIELD_ORIGEN_ID + " = '" + reportSelectorData.getEstablishmentId() + "' ");
		
		sql.append("AND " +
					"(");
						boolean first = true;
						for(STTreeNode treeNode : reportSelectorData.getProducts()){
							if (!first)
								sql.append(" OR ");
							
							if (treeNode.getNodeType() == Enums.NodeType.Root){
								sql.append("NOT p." + Constants.FIELD_ID_PRODUCTE + " IS NULL ");
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Group){
								sql.append("p." + Constants.FIELD_PRODUCTEGRUP_ID + " = " + treeNode.getId().toString());
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Subgroup)
								sql.append("p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = " + treeNode.getId().toString());
							
							else
								sql.append("p." + Constants.FIELD_ID_PRODUCTE + " = '" + treeNode.getId().toString() + "'");
							
							first = false;
						}
		sql.append(") ");

		//GROUP BY
		sql.append("GROUP BY " +
				"m." + Constants.FIELD_PRODUCTE_ID + ", " +
				"a." + Constants.FIELD_ORIGEN_ID + ", " +
				"l." + Constants.FIELD_ID_LOCAL + ", " +
				"l." + Constants.FIELD_NOMLOCAL + ", " +
				"l." + Constants.FIELD_CIF + ", " +
				"l." + Constants.FIELD_TELEFON + ", " +
				"l." + Constants.FIELD_ADRECA + ", " +
				"l." + Constants.FIELD_CODPOST + ", " +
				"l." + Constants.FIELD_PROVINCIA_ID + ", " +
				"l." + Constants.FIELD_PAIS + ", " +
				"l." + Constants.FIELD_TIPUSLOCAL + ", " +
				"l." + Constants.FIELD_DATAALTA + ", " +
				"l." + Constants.FIELD_COORDX + ", " + 
				"l." + Constants.FIELD_COORDY + ", " +
				"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
				"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
				"p." + Constants.FIELD_NOMPRODUCTE + ", " +
				"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
				"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
				"g." + Constants.FIELD_NOM + ", " +
				"sg." + Constants.FIELD_NOM + " ");
		
		//ORDER BY
		sql.append("ORDER BY " +
				fRowCount + " " + reportSelectorData.getOrder());
		
		try{
			ResultSet resultSet = this._databaseManager.selectData(sql.toString());
			
			if (resultSet != null){
					while(resultSet.next()){
						RotationReportLine item = new RotationReportLine();
						item.setCount(resultSet.getInt(fRowCount));
						item.setDays(Methods.calculateDays(resultSet.getDate(fFirstDate), reportSelectorData.getEndDate()));
						
						this.fillinCommonData(resultSet, item);
						
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		return result;
		
	}

	/***
	 * Retorna una llista d'instàncies de
	 * SalesReportLine, amb el resultat
	 * de l'informe de demandes de recanvis.
	 * 
	 * @param reportSelectorData Instància de ReportSelectorData
	 * amb els valors seleccionats per l'usuari.
	 * 
	 * @return
	 * @throws STException
	 */
	public List<SalesReportLine> getSalesReport(ReportSelectorData reportSelectorData) throws STException{
		List<SalesReportLine> result = new ArrayList<SalesReportLine>();
		StringBuilder sql = new StringBuilder();
		
		//SELECT
		sql.append("SELECT COUNT(*) AS " + fRowCount + ", " +
					"m." + Constants.FIELD_PRODUCTE_ID + ", " +
					"lv." + Constants.FIELD_PREUVENDA + "::money::numeric::float8 AS " + fUnitPrice + ", " +
					"lv." + Constants.FIELD_ORIGLOCAL_ID + ", " +
					"l." + Constants.FIELD_ID_LOCAL + ", " +
					"l." + Constants.FIELD_NOMLOCAL + ", " +
					"l." + Constants.FIELD_CIF + ", " +
					"l." + Constants.FIELD_TELEFON + ", " +
					"l." + Constants.FIELD_ADRECA + ", " +
					"l." + Constants.FIELD_CODPOST + ", " +
					"l." + Constants.FIELD_PROVINCIA_ID + ", " +
					"l." + Constants.FIELD_PAIS + ", " +
					"l." + Constants.FIELD_TIPUSLOCAL + ", " +
					"l." + Constants.FIELD_DATAALTA + ", " +
					"l." + Constants.FIELD_COORDX + ", " +
					"l." + Constants.FIELD_COORDY + ", " +
					"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
					"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
					"p." + Constants.FIELD_NOMPRODUCTE + ", " +
					"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
					"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
					"g." + Constants.FIELD_NOM + " AS " + fGName + ", " +
					"sg." + Constants.FIELD_NOM + " AS " + fSgName + " ");

		//FROM
		sql.append("FROM " +
				Constants.TABLE_MOVIMENT + " m INNER JOIN " + Constants.TABLE_LINVENDA + " lv " +
				"ON m." + Constants.FIELD_ID_MOVIMENT + " = lv." + Constants.FIELD_MOVIMENT_ID + " " +
				"INNER JOIN " + Constants.TABLE_LOCAL + " l " +
				"ON lv." + Constants.FIELD_ORIGLOCAL_ID + " = l." + Constants.FIELD_ID_LOCAL + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTEPROVEIDOR + " pp " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = pp." + Constants.FIELD_PRODUCTE_ID + " " +
				"INNER JOIN " + Constants.TABLE_PROVEIDOR + " pv " +
				"ON pp." + Constants.FIELD_PROVEIDOR_ID + " = pv." + Constants.FIELD_ID_PROVEIDOR + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTE + " p " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = p." + Constants.FIELD_ID_PRODUCTE + " " +
				"INNER JOIN " + Constants.TABLE_GRUP + " g " +
				"ON p." + Constants.FIELD_PRODUCTEGRUP_ID + " = g." + Constants.FIELD_ID_GRUP + " " +
				"INNER JOIN " + Constants.TABLE_SUBGRUP + " sg " +
				"ON p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = sg." + Constants.FIELD_ID_SUBGRUP + " ");
		
		//WHERE
		sql.append("WHERE " +
				"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Venda + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' ");

		if (!reportSelectorData.getEstablishmentId().equalsIgnoreCase(Constants.ALL))
			sql.append(" AND lv." + Constants.FIELD_ORIGLOCAL_ID + " = '" + reportSelectorData.getEstablishmentId() + "' ");
	
		sql.append("AND " +
					"(");
						boolean first = true;
						for(STTreeNode treeNode : reportSelectorData.getProducts()){
							if (!first)
								sql.append(" OR ");
							
							if (treeNode.getNodeType() == Enums.NodeType.Root){
								sql.append("NOT p." + Constants.FIELD_ID_PRODUCTE + " IS NULL ");
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Group){
								sql.append("p." + Constants.FIELD_PRODUCTEGRUP_ID + " = " + treeNode.getId().toString());
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Subgroup)
								sql.append("p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = " + treeNode.getId().toString());
							
							else
								sql.append("p." + Constants.FIELD_ID_PRODUCTE + " = '" + treeNode.getId().toString() + "'");
							
							first = false;
						}
		sql.append(") ");

		//GROUP BY
		sql.append("GROUP BY " +
				"m." + Constants.FIELD_PRODUCTE_ID + ", " +
				"lv." + Constants.FIELD_PREUVENDA + ", " +
				"lv." + Constants.FIELD_ORIGLOCAL_ID + ", " +
				"l." + Constants.FIELD_ID_LOCAL + ", " +
				"l." + Constants.FIELD_NOMLOCAL + ", " +
				"l." + Constants.FIELD_CIF + ", " +
				"l." + Constants.FIELD_TELEFON + ", " +
				"l." + Constants.FIELD_ADRECA + ", " +
				"l." + Constants.FIELD_CODPOST + ", " +
				"l." + Constants.FIELD_PROVINCIA_ID + ", " +
				"l." + Constants.FIELD_PAIS + ", " +
				"l." + Constants.FIELD_TIPUSLOCAL + ", " +
				"l." + Constants.FIELD_DATAALTA + ", " +
				"l." + Constants.FIELD_COORDX + ", " + 
				"l." + Constants.FIELD_COORDY + ", " +
				"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
				"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
				"p." + Constants.FIELD_NOMPRODUCTE + ", " +
				"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
				"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
				"g." + Constants.FIELD_NOM + ", " +
				"sg." + Constants.FIELD_NOM + " ");
		
		//ORDER BY
		sql.append("ORDER BY " +
				fRowCount + " " + reportSelectorData.getOrder());
		
		try{
			ResultSet resultSet = this._databaseManager.selectData(sql.toString());
			
			if (resultSet != null){
					while(resultSet.next()){
						SalesReportLine item = new SalesReportLine();
						item.setCount(resultSet.getInt(fRowCount));
						item.setUnitPrice(resultSet.getFloat(fUnitPrice));
						
						this.fillinCommonData(resultSet, item);
						
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		return result;
	}
	
	/***
	 * Retorna una llista d'instàncies de
	 * SalesSummaryReportLine, amb el resultat
	 * de l'informe global de vendes de recanvis.
	 * 
	 * @param reportSelectorData Instància de ReportSelectorData
	 * amb els valors seleccionats per l'usuari.
	 * 
	 * @return
	 * @throws STException
	 */
	public List<SalesSummaryReportLine> getSalesSummaryReport(ReportSelectorData reportSelectorData) throws STException{
		List<SalesSummaryReportLine> result = new ArrayList<SalesSummaryReportLine>();
		StringBuilder sql = new StringBuilder();
		
		//SELECT
		sql.append("SELECT " +
						"l." + Constants.FIELD_NOMLOCAL + ", " +
						"SUM(m." + Constants.FIELD_NUMUNITSORTIDES + ") AS " + fTotalOutput + ", " +
						"SUM(m." + Constants.FIELD_NUMUNITSORTIDES + " * lv." + Constants.FIELD_PREUVENDA  + ") AS " + fAmount + ", " +
						"AVG(lv." + Constants.FIELD_PREUVENDA + "::numeric::float8) AS " + fUnitPrice + " ");
		
		//FROM
		sql.append("FROM " +
						Constants.TABLE_LINVENDA + " lv " +
						"INNER JOIN " + Constants.TABLE_MOVIMENT + " m " +
						"ON lv." + Constants.FIELD_MOVIMENT_ID + " = m." + Constants.FIELD_ID_MOVIMENT + " " +
						"INNER JOIN " + Constants.TABLE_LOCAL + " l " +
						"ON lv." + Constants.FIELD_ORIGLOCAL_ID + " = l." + Constants.FIELD_ID_LOCAL + " " +
						"INNER JOIN " + Constants.TABLE_PRODUCTE + " p " +
						"ON m." + Constants.FIELD_PRODUCTE_ID + " = p." + Constants.FIELD_ID_PRODUCTE + " ");
		
		//WHERE
		sql.append("WHERE " +
						"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Venda + "' " +
						"AND " +
						"m." + Constants.FIELD_DATAORDRE + 
								" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
						"AND " +
						"m." + Constants.FIELD_DATAORDRE + 
								" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' ");

		if (!reportSelectorData.getEstablishmentId().equalsIgnoreCase(Constants.ALL))
			sql.append(" AND lv." + Constants.FIELD_ORIGLOCAL_ID + " = '" + reportSelectorData.getEstablishmentId() + "' ");
	
		sql.append("AND " +
					"(");
						boolean first = true;
						for(STTreeNode treeNode : reportSelectorData.getProducts()){
							if (!first)
								sql.append(" OR ");
							
							if (treeNode.getNodeType() == Enums.NodeType.Root){
								sql.append("NOT p." + Constants.FIELD_ID_PRODUCTE + " IS NULL ");
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Group){
								sql.append("p." + Constants.FIELD_PRODUCTEGRUP_ID + " = " + treeNode.getId().toString());
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Subgroup)
								sql.append("p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = " + treeNode.getId().toString());
							
							else
								sql.append("p." + Constants.FIELD_ID_PRODUCTE + " = '" + treeNode.getId().toString() + "'");
							
							first = false;
						}
		sql.append(") ");

		//GROUP BY
		sql.append("GROUP BY " +
				"l." + Constants.FIELD_NOMLOCAL + " ");

		//ORDER BY
		sql.append("ORDER BY " +
				fTotalOutput + " " + reportSelectorData.getOrder());
		
		try{
			ResultSet resultSet = this._databaseManager.selectData(sql.toString());
			
			if (resultSet != null){
					while(resultSet.next()){
						SalesSummaryReportLine item = new SalesSummaryReportLine();
						item.setSalesAmount(resultSet.getFloat(fAmount));
						item.setSalesQuantity(resultSet.getLong(fTotalOutput));
						item.setUnitPrice(resultSet.getFloat(fUnitPrice));
						
						//Establishment data
						LocalST estab = new LocalST();
						estab.setNomLocal(resultSet.getString(Constants.FIELD_NOMLOCAL));
						
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		return result;
	}
	
	/***
	 * Retorna una llista d'instàncies de
	 * StockOutReportLine, amb el resultat
	 * de l'informe de ruptures d'estoc.
	 * 
	 * @param reportSelectorData Instància de ReportSelectorData
	 * amb els valors seleccionats per l'usuari.
	 * 
	 * @return
	 * @throws STException
	 */
	public List<StockOutReportLine> getStockOutReport(ReportSelectorData reportSelectorData) throws STException{
		List<StockOutReportLine> result = new ArrayList<StockOutReportLine>();
		StringBuilder sql = new StringBuilder();
		
		//SELECT
		sql.append("SELECT COUNT(*) AS " + fRowCount + ", " +
					"m." + Constants.FIELD_PRODUCTE_ID + ", " +
					"a." + Constants.FIELD_ORIGEN_ID + ", " +
					"l." + Constants.FIELD_ID_LOCAL + ", " +
					"l." + Constants.FIELD_NOMLOCAL + ", " +
					"l." + Constants.FIELD_CIF + ", " +
					"l." + Constants.FIELD_TELEFON + ", " +
					"l." + Constants.FIELD_ADRECA + ", " +
					"l." + Constants.FIELD_CODPOST + ", " +
					"l." + Constants.FIELD_PROVINCIA_ID + ", " +
					"l." + Constants.FIELD_PAIS + ", " +
					"l." + Constants.FIELD_TIPUSLOCAL + ", " +
					"l." + Constants.FIELD_DATAALTA + ", " +
					"l." + Constants.FIELD_COORDX + ", " +
					"l." + Constants.FIELD_COORDY + ", " +
					"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
					"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
					"p." + Constants.FIELD_NOMPRODUCTE + ", " +
					"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
					"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
					"g." + Constants.FIELD_NOM + " AS " + fGName + ", " +
					"sg." + Constants.FIELD_NOM + " AS " + fSgName + " ");

		//FROM
		sql.append("FROM " +
				Constants.TABLE_MOVIMENT + " m INNER JOIN " + Constants.TABLE_LINALBARA + " la " +
				"ON m." + Constants.FIELD_ID_MOVIMENT + " = la." + Constants.FIELD_MOVIMENT_ID + " " +
				"INNER JOIN " + Constants.TABLE_ALBARA + " a " +
				"ON la." + Constants.FIELD_ALBARA_ID + " = a." + Constants.FIELD_ID_ALBARA + " " +
				"INNER JOIN " + Constants.TABLE_LOCAL + " l " +
				"ON a." + Constants.FIELD_ORIGEN_ID + " = l." + Constants.FIELD_ID_LOCAL + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTEPROVEIDOR + " pp " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = pp." + Constants.FIELD_PRODUCTE_ID + " " +
				"INNER JOIN " + Constants.TABLE_PROVEIDOR + " pv " +
				"ON pp." + Constants.FIELD_PROVEIDOR_ID + " = pv." + Constants.FIELD_ID_PROVEIDOR + " " +
				"INNER JOIN " + Constants.TABLE_PRODUCTE + " p " +
				"ON m." + Constants.FIELD_PRODUCTE_ID + " = p." + Constants.FIELD_ID_PRODUCTE + " " +
				"INNER JOIN " + Constants.TABLE_GRUP + " g " +
				"ON p." + Constants.FIELD_PRODUCTEGRUP_ID + " = g." + Constants.FIELD_ID_GRUP + " " +
				"INNER JOIN " + Constants.TABLE_SUBGRUP + " sg " +
				"ON p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = sg." + Constants.FIELD_ID_SUBGRUP + " ");
		
		//WHERE
		sql.append("WHERE " +
				"(" +
					"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Venda + "' " +
					"OR " +
					"m." + Constants.FIELD_TIPUSMOVIMENT_ID + " = '" + Enums.MovementType.Transferencia + "' " +
				") " +
				"AND " +
			/*
			 	Ruptura d'estoc vindrà donada perquè
				no s'ha pogut servir el material...
				...s'entenc que per mancança de material
			*/
				"m." + Constants.FIELD_NUMUNITATSORDRE + " > m." + Constants.FIELD_NUMUNITSORTIDES + " " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" >= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getStartDate()) + "' " +
				"AND " +
				"m." + Constants.FIELD_DATAORDRE + 
						" <= '" + Methods.convertToPostgreSQLDateFormat(reportSelectorData.getEndDate()) + "' ");

		if (!reportSelectorData.getEstablishmentId().equalsIgnoreCase(Constants.ALL))
			sql.append(" AND a." + Constants.FIELD_ORIGEN_ID + " = '" + reportSelectorData.getEstablishmentId() + "' ");
	
		sql.append("AND " +
					"(");
						boolean first = true;
						for(STTreeNode treeNode : reportSelectorData.getProducts()){
							if (!first)
								sql.append(" OR ");
							
							if (treeNode.getNodeType() == Enums.NodeType.Root){
								sql.append("NOT p." + Constants.FIELD_ID_PRODUCTE + " IS NULL ");
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Group){
								sql.append("p." + Constants.FIELD_PRODUCTEGRUP_ID + " = " + treeNode.getId().toString());
							
							} else if (treeNode.getNodeType() == Enums.NodeType.Subgroup)
								sql.append("p." + Constants.FIELD_PRODUCTESUBGRUP_ID + " = " + treeNode.getId().toString());
							
							else
								sql.append("p." + Constants.FIELD_ID_PRODUCTE + " = '" + treeNode.getId().toString() + "'");
							
							first = false;
						}
		sql.append(") ");

		//GROUP BY
		sql.append("GROUP BY " +
				"m." + Constants.FIELD_PRODUCTE_ID + ", " +
				"a." + Constants.FIELD_ORIGEN_ID + ", " +
				"l." + Constants.FIELD_ID_LOCAL + ", " +
				"l." + Constants.FIELD_NOMLOCAL + ", " +
				"l." + Constants.FIELD_CIF + ", " +
				"l." + Constants.FIELD_TELEFON + ", " +
				"l." + Constants.FIELD_ADRECA + ", " +
				"l." + Constants.FIELD_CODPOST + ", " +
				"l." + Constants.FIELD_PROVINCIA_ID + ", " +
				"l." + Constants.FIELD_PAIS + ", " +
				"l." + Constants.FIELD_TIPUSLOCAL + ", " +
				"l." + Constants.FIELD_DATAALTA + ", " +
				"l." + Constants.FIELD_COORDX + ", " + 
				"l." + Constants.FIELD_COORDY + ", " +
				"pp." + Constants.FIELD_PROVEIDOR_ID + ", " +
				"pv." + Constants.FIELD_NOMPROVEIDOR + ", " +
				"p." + Constants.FIELD_NOMPRODUCTE + ", " +
				"p." + Constants.FIELD_PRODUCTEGRUP_ID + ", " +
				"p." + Constants.FIELD_PRODUCTESUBGRUP_ID + ", " +
				"g." + Constants.FIELD_NOM + ", " +
				"sg." + Constants.FIELD_NOM + " ");
		
		//ORDER BY
		sql.append("ORDER BY " +
				fRowCount + " " + reportSelectorData.getOrder());
		
		try{
			ResultSet resultSet = this._databaseManager.selectData(sql.toString());
			
			if (resultSet != null){
					while(resultSet.next()){
						StockOutReportLine item = new StockOutReportLine();
						item.setCount(resultSet.getInt(fRowCount));
						
						this.fillinCommonData(resultSet, item);
						
						result.add(item);
					}
			}
			resultSet.close();

		}catch (SQLException e){
			throw new STException(e, TokenKeys.ERROR_GETTING_DATA);
		}
		return result;
	}
	
}
