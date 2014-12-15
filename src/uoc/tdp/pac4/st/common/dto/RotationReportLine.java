package uoc.tdp.pac4.st.common.dto;

import java.io.Serializable;

import uoc.tdp.pac4.st.common.*;

/***
 * Classe que representa a una
 * línia bàsica de l'informe
 * de rotacions d'estoc
 *  
 * @author Swing Team - 2014
 *
 */
public class RotationReportLine extends StockOutReportLine implements Serializable{

	private static final long serialVersionUID = 4919226500743810551L;

	private long _days = 0;

	/***
	 * Constructor
	 */
	public RotationReportLine(){}
	
	/***
	 * Constructor 
	 * 
	 * @param local
	 */
	public RotationReportLine(LocalST local){
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
	public RotationReportLine(Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
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
	public RotationReportLine(int count,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		super(count, group, subgroup, product, provider, establishment);
	}

	/***
	 * Constructor
	 * 
	 * @param count
	 * @param days
	 * @param group
	 * @param subgroup
	 * @param product
	 * @param provider
	 * @param establishment
	 */
	public RotationReportLine(int count,
							int days,
							Grup group,
							SubGrup subgroup,
							Producte product,
							Proveidor provider,
							LocalST establishment){
		super(count, group, subgroup, product, provider, establishment);
		this._days = days;
	}
	
	//********************************* Getters ********************************************
	public long getDays(){return this._days;}
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	public void setDays(long days){this._days = days;}
	//********************************* ******* ********************************************
	
	/***
	 * Recupera el valor corresponent a
	 * un determinat camp.
	 */
	public Object getValue(String fieldName){
		Object result = null;
		switch (fieldName){
			case Constants.REPORT_DAYS:
				result = this._days;
				break;
		default:
			result = super.getValue(fieldName);
			break;
		}
		return result;
	}
	
}
