package uoc.tdp.pac4.st.client.e;

import java.util.List;

import javax.swing.SwingConstants;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;

/***
 * Classe que mostra el resultat de
 * l'informe de devolució de recanvis.
 * 
 * @author Swing Team - 2014
 *
 * @param <T>
 */
public class ReturningReportResult<T> extends ReportResult<ReturningReportLine> {
	
	private static final long serialVersionUID = -8384621080755817338L;
	//Alineació de les columnes
	private static final int[] columnAlign = {SwingConstants.RIGHT, 
												SwingConstants.RIGHT,
												SwingConstants.RIGHT,
												SwingConstants.RIGHT,
												SwingConstants.LEFT,
												SwingConstants.LEFT,
												SwingConstants.LEFT,
												SwingConstants.CENTER,
												SwingConstants.LEFT,
												SwingConstants.LEFT};
	//Camps que formen l'informe
	private static final String[] columnField = {Constants.REPORT_PERCENTAGE,
												Constants.REPORT_RETURNED_QUANTITY,
												Constants.REPORT_UNIT_PRICE,
												Constants.REPORT_RETURNED_AMOUNT,
												Constants.REPORT_ESTAB_NAME,
												Constants.REPORT_GROUP_NAME,
												Constants.REPORT_SUBGROUP_NAME,
												Constants.REPORT_PRODUCT_ID,
												Constants.REPORT_PRODUCT_NAME,
												Constants.REPORT_PROVIDER_NAME};
	//Nom de les columnes
	private static final String[] columnName = {TokenKeys.REPORT_COLUMN_PERCENTAGE,
												TokenKeys.REPORT_COLUMN_RETURNED_QUANTITY,
												TokenKeys.REPORT_COLUMN_UNIT_PRICE,
												TokenKeys.REPORT_COLUMN_TOTAL_AMOUNT,
												TokenKeys.REPORT_COLUMN_ESTABLISHMENT_NAME,
												TokenKeys.REPORT_COLUMN_GROUP,
												TokenKeys.REPORT_COLUMN_SUBGROUP,
												TokenKeys.REPORT_COLUMN_PRODUCT_ID,
												TokenKeys.REPORT_COLUMN_PRODUCT_NAME,
												TokenKeys.REPORT_COLUMN_PROVIDER_NAME};
	//Mida de les columnes (percentatge)
	private static final int[] columnWidth = {6,8,8,8,10,5,5,15,25,10};

	/***
	 * Constructor
	 * 
	 * @param reportLines
	 * @param reportSelectorData
	 */
	public ReturningReportResult (List<ReturningReportLine> reportLines, ReportSelectorData reportSelectorData){
		super(reportLines, reportSelectorData);
		setTitle("TITLE_RETURNING_REPORT");
		
		//Mostrem l'informe
		this.showReport(columnName, columnField, columnWidth, columnAlign);
	}
}
