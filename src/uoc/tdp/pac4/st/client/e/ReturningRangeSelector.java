package uoc.tdp.pac4.st.client.e;

import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;

/***
 * Classe corresponent a la finestra de selecció
 * de l'informe de devolucions de recanvis
 *  
 * @author Swing Team - 2014
 *
 */
public class ReturningRangeSelector extends ReportRangeSelector {

	private static final long serialVersionUID = 6223169968673953591L;

	/***
	 * Constructor
	 */
	public ReturningRangeSelector(){
		this(null, null);
	}

	/***
	 * Constructor
	 * 
	 * @param user Instància d'Usuari amb les dades
	 * de l'usuari actiu.
	 */
	public ReturningRangeSelector(Usuari user) {
		this(user, null);
	}
	
	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public ReturningRangeSelector(ClientManager<ETallerStocksInterface> clientManager) {
		this(null, clientManager);
	}	
	/***
	 * Constructor
	 * 
	 * @param user Instància d'Usuari amb les dades
	 * de l'usuari actiu.
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public ReturningRangeSelector(Usuari user, ClientManager<ETallerStocksInterface> clientManager) {
		super(user, clientManager);
		setTitle("TITLE_RETURNING_REPORT");
	}
	
	@Override
	protected void launchReport(ReportSelectorData reportSelectorData){
		try{
			List<ReturningReportLine> reportLines = this._clientManager.getRMIInterface().getReturningReport(reportSelectorData);
			
			ReturningReportResult<ReturningReportLine> frame 
				= new ReturningReportResult<ReturningReportLine>(reportLines, reportSelectorData);
			frame.setVisible(true);
			
		} catch (STException e){
			Managers.exception.showException(e);
		} catch (RemoteException e){
			Managers.exception.showException(new STException(e));
		}
	}
}
