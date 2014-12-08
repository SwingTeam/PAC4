package uoc.tdp.pac4.st.client.e;

import java.util.List;

import javax.swing.SwingConstants;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;

public class RotationReportResult<T> extends ReportResult<RotationReportLine> {
	
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
												Constants.REPORT_DAYS,
												Constants.REPORT_ESTAB_NAME,
												Constants.REPORT_GROUP_NAME,
												Constants.REPORT_SUBGROUP_NAME,
												Constants.REPORT_PRODUCT_ID,
												Constants.REPORT_PRODUCT_NAME,
												Constants.REPORT_PROVIDER_NAME};
	//Nom de les columnes
	private static final String[] columnName = {TokenKeys.REPORT_COLUMN_EVENT,
												TokenKeys.REPORT_COLUMN_DAYS,
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
	public RotationReportResult (List<RotationReportLine> reportLines, ReportSelectorData reportSelectorData){
		super(reportLines, reportSelectorData);
		setTitle("TITLE_ROTATION_REPORT");
		
		//Mostrem l'informe
		this.showReport(columnName, columnField, columnWidth, columnAlign);
	}
}
