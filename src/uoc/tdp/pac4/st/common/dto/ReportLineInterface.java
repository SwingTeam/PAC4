package uoc.tdp.pac4.st.common.dto;

/***
 * Interface que representa a una
 * línia bàsica d'informe.
 *  
 * @author Swing Team - 2014
 *
 */

abstract interface ReportLineInterface {
 
	//********************************* Getters ********************************************
	abstract Grup getGroup();
	abstract Local getEstablishment();
	abstract Producte getProduct();
	abstract Proveidor getProvider();
	abstract SubGrup getSubgroup();
	//********************************* ******* ********************************************

	//********************************* Setters ********************************************
	abstract void setGroup(Grup group);
	abstract void setEstablishment(Local establishment);
	abstract void setProduct(Producte product);
	abstract void setProvider(Proveidor provider);
	abstract void setSubgroup(SubGrup subgroup);
	//********************************* ******* ********************************************

	abstract Object getValue(String fieldName);
}
