package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

import uoc.tdp.pac4.st.common.Constants;

/***
 * Classe que representa una línia de
 * l'informe de ruptures d'estoc
 * 
 * @author Swing Team - 2014
 *
 */
public class StockOutReportLine extends ReportLine implements Serializable {

	private static final long serialVersionUID = -7267979145042182485L;
	private int _count = 0;

	/***
	 * Constructor
	 */
	public StockOutReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public StockOutReportLine(Local local){
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
	public StockOutReportLine(Grup group,
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
	public StockOutReportLine(int count,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							Local establishment){
		super(group, subgroup, product, provider, establishment);
		this._count = count;
	}

	//********************************* Getters ********************************************
	public int getCount(){return this._count;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setCount(int count){this._count = count;}
	//********************************* ******* ********************************************
	
	/***
	 * Recupera el valor corresponent a
	 * un determinat camp.
	 */
	public Object getValue(String fieldName){
		Object result = null;
		switch (fieldName){
			case Constants.REPORT_COUNT:
				result = this._count;
				break;
		default:
			result = super.getValue(fieldName);
			break;
		}
		return result;
	}
}
