package uoc.tdp.pac4.st.client.e;

import java.util.List;

import javax.swing.SwingConstants;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;

/***
 * Classe que mostra el resultat de
 * l'informe de demanda de recanvis.
 * 
 * @author Swing Team - 2014
 *
 * @param <T>
 */
public class SalesReportResult<T> extends ReportResult<SalesReportLine> {
	
	private static final long serialVersionUID = -8384621080755817338L;
	//Alineació de les columnes
	private static final int[] columnAlign = {SwingConstants.RIGHT, 
												SwingConstants.RIGHT,
												SwingConstants.LEFT,
												SwingConstants.LEFT,
												SwingConstants.LEFT,
												SwingConstants.CENTER,
												SwingConstants.LEFT,
												SwingConstants.LEFT};
	//Camps que formen l'informe
	private static final String[] columnField = {Constants.REPORT_COUNT,
												Constants.REPORT_TOTAL_AMOUNT,
												Constants.REPORT_ESTAB_NAME,
												Constants.REPORT_GROUP_NAME,
												Constants.REPORT_SUBGROUP_NAME,
												Constants.REPORT_PRODUCT_ID,
												Constants.REPORT_PRODUCT_NAME,
												Constants.REPORT_PROVIDER_NAME};
	//Nom de les columnes
	private static final String[] columnName = {TokenKeys.REPORT_COLUMN_EVENT,
												TokenKeys.REPORT_COLUMN_TOTAL_AMOUNT,
												TokenKeys.REPORT_COLUMN_ESTABLISHMENT_NAME,
												TokenKeys.REPORT_COLUMN_GROUP,
												TokenKeys.REPORT_COLUMN_SUBGROUP,
												TokenKeys.REPORT_COLUMN_PRODUCT_ID,
												TokenKeys.REPORT_COLUMN_PRODUCT_NAME,
												TokenKeys.REPORT_COLUMN_PROVIDER_NAME};
	//Mida de les columnes (percentatge)
	private static final int[] columnWidth = {5,5,15,15,15,10,25,10};

	/***
	 * Constructor
	 * 
	 * @param reportLines
	 * @param reportSelectorData
	 */
	public SalesReportResult (List<SalesReportLine> reportLines, ReportSelectorData reportSelectorData){
		super(reportLines, reportSelectorData);
		setTitle("TITLE_SALES_REPORT");
		
		//Mostrem l'informe
		this.showReport(columnName, columnField, columnWidth, columnAlign);
	}
}
