package com.skywings.gbilling.model;

public class Product {

	private String ProCode;
	private String ProName;
	private String ShortName;
	private int Catcode;
	private int Taxcode;
	private String IncGst;
	private String Unit;
	private String HsnCode;
	private String PurUnit;
	private String Active;

	public String getProCode() {
		return ProCode;
	}

	public void setProCode(String proCode) {
		ProCode = proCode;
	}

	public String getProName() {
		return ProName;
	}

	public void setProName(String proName) {
		ProName = proName;
	}

	public String getShortName() {
		return ShortName;
	}

	public void setShortName(String shortName) {
		ShortName = shortName;
	}

	public int getCatcode() {
		return Catcode;
	}

	public void setCatcode(int catcode) {
		Catcode = catcode;
	}

	public int getTaxcode() {
		return Taxcode;
	}

	public void setTaxcode(int taxcode) {
		Taxcode = taxcode;
	}

	public String getIncGst() {
		return IncGst;
	}

	public void setIncGst(String incGst) {
		IncGst = incGst;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getHsnCode() {
		return HsnCode;
	}

	public void setHsnCode(String hsnCode) {
		HsnCode = hsnCode;
	}

	public String getPurUnit() {
		return PurUnit;
	}

	public void setPurUnit(String purUnit) {
		PurUnit = purUnit;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

}
