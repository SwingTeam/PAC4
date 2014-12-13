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
public class SalesSummaryReportResult<T> extends ReportResult<SalesSummaryReportLine> {
	
	private static final long serialVersionUID = -8384621080755817338L;
	//Alineació de les columnes
	private static final int[] columnAlign = {SwingConstants.RIGHT, 
												SwingConstants.RIGHT,
												SwingConstants.RIGHT,
												SwingConstants.LEFT};
	//Camps que formen l'informe
	private static final String[] columnField = {Constants.REPORT_SALES_QUANTITY,
												Constants.REPORT_UNIT_PRICE,
												Constants.REPORT_TOTAL_AMOUNT,
												Constants.REPORT_ESTAB_NAME};
	//Nom de les columnes
	private static final String[] columnName = {TokenKeys.REPORT_COLUMN_QUANTITY,
												TokenKeys.REPORT_COLUMN_UNIT_PRICE,
												TokenKeys.REPORT_COLUMN_TOTAL_AMOUNT,
												TokenKeys.REPORT_COLUMN_ESTABLISHMENT_NAME};
	//Mida de les columnes (percentatge)
	private static final int[] columnWidth = {20,20,20,40};

	/***
	 * Constructor
	 * 
	 * @param reportLines
	 * @param reportSelectorData
	 */
	public SalesSummaryReportResult (List<SalesSummaryReportLine> reportLines, ReportSelectorData reportSelectorData){
		super(reportLines, reportSelectorData);
		setTitle("TITLE_SALES_SUMMARY_REPORT");
		
		//Mostrem l'informe
		this.showReport(columnName, columnField, columnWidth, columnAlign);
	}
}
