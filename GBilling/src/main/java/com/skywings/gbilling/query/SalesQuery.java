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

	public String getSalesView() {

		sb = new StringBuilder("");
		sb.append(
				"Select TranNo,TranDate,AcctName,P.ProName, Pieces,Rate,NetAmount,TaxAmount,GrossAmount,DiscAmount,C.Description Route,VechileNo,EwayBillNo,IRNNumber from Sales S\r\n"
						+ "Left Join MKSGMaster.dbo.CityMaster C On C.CityCode = S.RouteId\r\n"
						+ "Left Join MKSGMaster.dbo.Operator O On O.OperCode = S.SalMan\r\n"
						+ "Left Join MKSGMaster.dbo.Product P On P.ProCode = S.Procode\r\n" + "");

		return sb.toString();
	}

}
