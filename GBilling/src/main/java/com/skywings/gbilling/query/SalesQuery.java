package com.skywings.gbilling.query;

import org.springframework.stereotype.Component;

@Component
public class SalesQuery {

	private StringBuilder sb;

	public String getVendor() {

		sb = new StringBuilder("");
		sb.append("Select * from AcctMaster Where 1=1 And Groupcode=105");

		return sb.toString();
	}

	public String getCity() {
		sb = new StringBuilder("");
		sb.append("Select * from CityMaster Where 1=1");

		return sb.toString();
	}

	public String getSalesman() {

		sb = new StringBuilder("");
		sb.append("Select * from Operator Where 1=1");
		return sb.toString();
	}

	public String getProduct() {

		sb = new StringBuilder("");
		sb.append("Select * from Product Where 1=1");

		return sb.toString();
	}

	public String getsubProduct() {
		sb = new StringBuilder("");
		sb.append("Select * from SubProduct Where 1=1");

		return sb.toString();
	}

	public String getUnit() {
		sb = new StringBuilder("");
		sb.append("Select * from Fn_Unit() Where 1=1");

		return sb.toString();
	}

	public String getTranNo() {
		sb = new StringBuilder("");
		sb.append("Select  Isnull(MAX(TranNo) ,0) +1 TranNo from Sales Where 1=1 ");

		return sb.toString();
	}

}
