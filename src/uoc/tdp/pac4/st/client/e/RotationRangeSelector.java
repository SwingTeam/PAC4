package uoc.tdp.pac4.st.client.e;

import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;

/***
 * Classe corresponent a la finestra de selecció
 * de l'informe de rotació d'estoc
 *  
 * @author Swing Team - 2014
 *
 */
public class RotationRangeSelector extends ReportRangeSelector {

	private static final long serialVersionUID = 6223169968673953591L;

	/***
	 * Constructor
	 */
	public RotationRangeSelector(){
		this(null);
	}

	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public RotationRangeSelector(ClientManager<ETallerStocksInterface> clientManager) {
		super(clientManager);
		setTitle("TITLE_ROTATION_REPORT");
	}
	
	@Override
	protected void launchReport(ReportSelectorData reportSelectorData){
		try{
			
			List<RotationReportLine> reportLines = this._clientManager.getRMIInterface().getRotationReport(reportSelectorData);
			
			RotationReportResult<RotationReportLine> frame 
				= new RotationReportResult<RotationReportLine>(reportLines, reportSelectorData);
			frame.setVisible(true);
			
		} catch (STException e){
			Managers.exception.showException(e);
		} catch (RemoteException e){
			Managers.exception.showException(new STException(e));
		}
	}
}
