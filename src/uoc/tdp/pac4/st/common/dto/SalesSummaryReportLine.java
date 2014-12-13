package uoc.tdp.pac4.st.common.dto;

import uoc.tdp.pac4.st.common.*;

/***
 * Classe que representa a una
 * línia bàsica de l'informe
 * global de vendes.
 *  
 * @author Swing Team - 2014
 *
 */

public class SalesSummaryReportLine extends ReportLine {

	private static final long serialVersionUID = 4919226500743810551L;
	private float _totalAmount = 0;
	private long _totalSales = 0;
	private float _unitPrice = 0; 
	
	/***
	 * Constructor
	 */
	public SalesSummaryReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public SalesSummaryReportLine(LocalST local){
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
	public SalesSummaryReportLine(Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param salesQuantity
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public SalesSummaryReportLine(long salesQuantity,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(salesQuantity, 0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param salesQuantity
	 * @param salesAmount
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public SalesSummaryReportLine(long salesQuantity,
							long salesAmount,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		this(salesQuantity, salesAmount, 0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param salesQuantity
	 * @param salesQuantity
	 * @param unitPrice
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public SalesSummaryReportLine(long salesQuantity,
							float salesAmount,
							float unitPrice,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		super(group, subgroup, product, provider, establishment);
		this._totalAmount = salesAmount;
		this._totalSales = salesQuantity;
		this._unitPrice = unitPrice;
	}
	
	//********************************* Getters ********************************************
	public float getSalesAmount(){return this._totalAmount;}
	public long getSalesQuantity(){return this._totalSales;}
	public float getUnitPrice(){return this._unitPrice;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setSalesAmount(float salesAmount){this._totalAmount = salesAmount;}
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
			case Constants.REPORT_TOTAL_AMOUNT:
				result = this._totalAmount;
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
