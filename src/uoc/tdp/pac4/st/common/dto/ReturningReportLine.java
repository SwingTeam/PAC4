package uoc.tdp.pac4.st.common.dto;

import uoc.tdp.pac4.st.common.*;

/***
 * Classe que representa a una
 * línia bàsica de l'informe
 * de devolució de recanvis.
 *  
 * @author Swing Team - 2014
 *
 */

public class ReturningReportLine extends ReportLine {

	private static final long serialVersionUID = 4919226500743810551L;
	private long _totalOutput = 0;
	private long _totalSales = 0;
	private float _unitPrice = 0; 
	
	/***
	 * Constructor
	 */
	public ReturningReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public ReturningReportLine(LocalST local){
		super(local);
	}
	
	/***
	 * Constructor
	 * 
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public ReturningReportLine(Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param returnedQuantity
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public ReturningReportLine(long returnedQuantity,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(returnedQuantity, 0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param returnedQuantity
	 * @param salesQuantity
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public ReturningReportLine(long returnedQuantity,
							long salesQuantity,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(returnedQuantity, salesQuantity, 0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param returnedQuantity
	 * @param salesQuantity
	 * @param unitPrice
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public ReturningReportLine(long returnedQuantity,
							long salesQuantity,
							float unitPrice,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		super(group, subgroup, product, provider, establishment);
		this._totalOutput = returnedQuantity;
		this._totalSales = salesQuantity;
		this._unitPrice = unitPrice;
	}
	
	//********************************* Getters ********************************************
	public float getPercentage(){return ((int) this._totalOutput * 10000 / this._totalSales) / 100;};
	public long getReturnedQuantity(){return this._totalOutput;}
	public long getSalesQuantity(){return this._totalSales;}
	public float getUnitPrice(){return this._unitPrice;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setReturnedQuantity(long returnedQuantity){this._totalOutput = returnedQuantity;}
	public void setSalesQuantity(long salesQuantity){this._totalSales = salesQuantity;}
	public void setUnitPrice(float unitPrice){this._unitPrice = unitPrice;}
	//********************************* ******* ********************************************
	
	/***
	 * Recupera el valor corresponent a
	 * un determinat camp.
	 */
	public Object getValue(String fieldName){
		Object result = null;
		switch (fieldName){
			case Constants.REPORT_PERCENTAGE:
				result = this.getPercentage();
				break;
			case Constants.REPORT_RETURNED_QUANTITY:
				result = this._totalOutput;
				break;
			case Constants.REPORT_SALES_QUANTITY:
				result = this._totalSales;
				break;
			case Constants.REPORT_UNIT_PRICE:
				result = this._unitPrice;
				break;
		default:
			result = super.getValue(fieldName);
			break;
		}
		return result;
	}
	
}
