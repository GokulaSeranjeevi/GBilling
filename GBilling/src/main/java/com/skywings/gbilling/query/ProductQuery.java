package com.skywings.gbilling.query;

import org.springframework.stereotype.Component;

@Component
public class ProductQuery {

	private StringBuilder sb;

	public String getProCode() {

		sb = new StringBuilder("");
		sb.append(" Select Max(Procode+1) from Product Where 1=1 ");

		return sb.toString();
	}

	public String getTaxMaster() {

		sb = new StringBuilder("");
		sb.append("Select * from TaxMaster Where 1=1 And Active='Y'  ");

		return sb.toString();
	}

	public String getUnit() {

		sb = new StringBuilder("");

		sb.append("Select * from Fn_Unit()");

		return sb.toString();
	}

	public String getHsn() {

		sb = new StringBuilder("");
		sb.append("Select * from HSNMaster Where Active='Y'");

		return sb.toString();
	}

	public String saveProduct() {

		sb = new StringBuilder("");
		sb.append(
				" Insert into Product (Procode,ProName,ShortName,Catcode,Taxcode,IncGst,Unit,HsnCode,PurUnit,Active) Values (?,?,?,?,?,?,?,?,?,?)");

		return sb.toString();
	}

	public String getProduct() {

		sb = new StringBuilder("");
		sb.append("Select * from product Where 1=1");

		return sb.toString();
	}

}
