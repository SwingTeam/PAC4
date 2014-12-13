package uoc.tdp.pac4.st.client.e;

import java.rmi.RemoteException;
import java.util.List;

import uoc.tdp.pac4.st.common.*;
import uoc.tdp.pac4.st.common.dto.*;
import uoc.tdp.pac4.st.common.managers.*;
import uoc.tdp.pac4.st.rmi.*;

/***
 * Classe corresponent a la finestra de selecció
 * de l'informe de demanda de recanvis
 *  
 * @author Swing Team - 2014
 *
 */
public class SalesRangeSelector extends ReportRangeSelector {

	private static final long serialVersionUID = 6223169968673953591L;

	/***
	 * Constructor
	 */
	public SalesRangeSelector(){
		this(null, null);
	}

	/***
	 * Constructor
	 * 
	 * @param user Instància d'Usuari amb les dades
	 * de l'usuari actiu.
	 */
	public SalesRangeSelector(Usuari user) {
		this(user, null);
	}
	
	/***
	 * Constructor
	 * 
	 * @param clientManager Instància de ClientManager
	 * que utilitzarà per a fer les connexions RMI.
	 */
	public SalesRangeSelector(ClientManager<ETallerStocksInterface> clientManager) {
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
	public SalesRangeSelector(Usuari user, ClientManager<ETallerStocksInterface> clientManager) {
		super(user, clientManager);
		setTitle("TITLE_SALES_REPORT");
	}
	
	@Override
	protected void launchReport(ReportSelectorData reportSelectorData){
		try{
			
			List<SalesReportLine> reportLines = this._clientManager.getRMIInterface().getSalesReport(reportSelectorData);
			
			SalesReportResult<SalesReportLine> frame 
				= new SalesReportResult<SalesReportLine>(reportLines, reportSelectorData);
			frame.setVisible(true);
			
		} catch (STException e){
			Managers.exception.showException(e);
		} catch (RemoteException e){
			Managers.exception.showException(new STException(e));
		}
	}
}
