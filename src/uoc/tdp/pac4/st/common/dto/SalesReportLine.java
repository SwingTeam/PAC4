package uoc.tdp.pac4.st.common.dto;

import uoc.tdp.pac4.st.common.*;

/***
 * Classe que representa a una
 * línia bàsica de l'informe
 * de demanda de recanvis.
 *  
 * @author Swing Team - 2014
 *
 */

public class SalesReportLine extends StockOutReportLine {

	private static final long serialVersionUID = 4919226500743810551L;

	private float _unitPrice = 0;

	/***
	 * Constructor
	 */
	public SalesReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public SalesReportLine(Local local){
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
	public SalesReportLine(Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							Local establishment){
		this(0, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param count
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public SalesReportLine(int count,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							Local establishment){
		super(count, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param count
	 * @param unitPrice
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public SalesReportLine(int count,
							float unitPrice,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							Local establishment){
		super(count, group, subgroup, product, provider, establishment);
		this._unitPrice = unitPrice;
	}
	
	//********************************* Getters ********************************************
	public float getTotalAmount(){return this._unitPrice * this.getCount();};
	public float getUnitPrice(){return this._unitPrice;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setUnitPrice(float unitPrice){this._unitPrice = unitPrice;}
	//********************************* ******* ********************************************
	
	/***
	 * Recupera el valor corresponent a
	 * un determinat camp.
	 */
	public Object getValue(String fieldName){
		Object result = null;
		switch (fieldName){
			case Constants.REPORT_UNIT_PRICE:
				result = this._unitPrice;
				break;
			case Constants.REPORT_TOTAL_AMOUNT:
				result = this.getTotalAmount();
				break;
		default:
			result = super.getValue(fieldName);
			break;
		}
		return result;
	}
	
}
