package com.skywings.gbilling.model;

public class TaxMaster {

	private int TaxCode;
	private String TaxName;
	private double TaxPer;
	private double CGSTPer;
	private double SGSTPer;
	private String Active;

	public int getTaxCode() {
		return TaxCode;
	}

	public void setTaxCode(int taxCode) {
		TaxCode = taxCode;
	}

	public String getTaxName() {
		return TaxName;
	}

	public void setTaxName(String taxName) {
		TaxName = taxName;
	}

	public double getTaxPer() {
		return TaxPer;
	}

	public void setTaxPer(double taxPer) {
		TaxPer = taxPer;
	}

	public double getCGSTPer() {
		return CGSTPer;
	}

	public void setCGSTPer(double cGSTPer) {
		CGSTPer = cGSTPer;
	}

	public double getSGSTPer() {
		return SGSTPer;
	}

	public void setSGSTPer(double sGSTPer) {
		SGSTPer = sGSTPer;
	}

	public String getActive() {
		return Active;
	}

	public void setActive(String active) {
		Active = active;
	}

}
