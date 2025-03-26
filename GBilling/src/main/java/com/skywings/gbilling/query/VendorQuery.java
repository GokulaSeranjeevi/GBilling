package com.skywings.gbilling.query;

import org.springframework.stereotype.Component;

@Component
public class VendorQuery {

	private StringBuilder sb;

	public String getAcctCode() {

		sb = new StringBuilder("");
		sb.append("Select Max(AcctId+1) from AcctMaster Where 1=1");

		return sb.toString();
	}

	public String getState() {

		sb = new StringBuilder("");
		sb.append(" Select * from StateMaster Where 1=1");

		return sb.toString();

	}

	public String getGroup() {

		sb = new StringBuilder("");
		sb.append(" Select * from BalGroup Where 1=1");

		return sb.toString();

	}

	public String getCity() {
		sb = new StringBuilder("");
		sb.append(" Select * from CityMaster Where 1=1");

		return sb.toString();
	}

	public String saveVendor() {

		sb = new StringBuilder("");
		sb.append(
				"Insert Into AcctMaster (AcctId,AcctName,Groupcode,Address1,Address2,Address3,Pincode,Phone,MobileNo,EmailId,GstNo,PanNo,ShortName,DateOfJoin,CityCode,StateCode) Values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");

		return sb.toString();
	}

	public String getVendor() {
		sb = new StringBuilder("");
		sb.append(
				" Select AcctId,AcctName,A.ShortName, B.GroupName,Address1,Address2,Address3,Pincode,Phone,MobileNo,EmailId,GstNo,PanNo,C.Description City,S.Description State from AcctMaster A\r\n"
						+ "Left Join BalGroup B On B.GroupCode= A.Groupcode\r\n"
						+ "Left Join CityMaster C On C.CityCode = A.CityCode\r\n"
						+ "Left Join StateMaster S On S.StateCode = A.StateCode\r\n" + "Where 1=1");

		return sb.toString();
	}

}
