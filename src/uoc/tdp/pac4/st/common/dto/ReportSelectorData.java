package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import uoc.tdp.pac4.st.common.*;

/***
 * Classe que representa el rang de dades
 * seleccionades per a generar un informe.
 *  
 * @author Swing Team - 2014
 *
 */
public class ReportSelectorData implements Serializable {

	private static final long serialVersionUID = 4672909624546043455L;

	private Date _endDate = null;
	private String _establishmentId = null;
	private String _establishmentName = null;
	private String _order = null;
	private List<STTreeNode> _products = null;
	private Date _startDate = null;
	
	/***
	 * Constructor
	 * 
	 * @param startDate
	 * @param endDate
	 * @param establishmentId
	 * @param order
	 * @param products
	 */
	public ReportSelectorData(Date startDate,
								Date endDate,
								String establishmentId,
								String establishmentName,
								String order,
								List<STTreeNode> products){
		this._startDate = startDate;
		this._endDate = endDate;
		this._establishmentId = establishmentId;
		this._establishmentName = establishmentName;
		this._order = order;
		this._products = products;
	}
	
	//********************************* Getters ********************************************
	public Date getStartDate(){return this._startDate;}
	public Date getEndDate(){return this._endDate;}
	public String getEstablishmentId(){return this._establishmentId;}
	public String getEstablishmentName(){return this._establishmentName;}
	public String getOrder(){return this._order;}
	public List<STTreeNode> getProducts(){return this._products;}
	//********************************* ******* ********************************************
}
