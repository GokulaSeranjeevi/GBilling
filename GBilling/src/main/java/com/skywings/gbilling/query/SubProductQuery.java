package com.skywings.gbilling.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubProductQuery {

	private StringBuilder sb;

	public String getSubCode() {

		sb = new StringBuilder("");
		sb.append("Select Max(Subcode+1) from SubProduct Where 1=1 ");

		return sb.toString();
	}

	public String getSubProduct() {

		sb = new StringBuilder("");
		sb.append("select P.ProName,S.* from SubProduct S\r\n" + "Left Join Product P On P.ProCode = S.ProCode\r\n"
				+ "Where 1=1");

		return sb.toString();
	}

	public String saveSubProduct() {

		sb = new StringBuilder("");
		sb.append("Insert Into SubProduct (SubCode,SubProName,ProCode,Active) Values (?,?,?,?)");

		return sb.toString();
	}

	public String getProduct() {
		sb = new StringBuilder("");
		sb.append("Select * from product Where 1=1");

		return sb.toString();
	}

}
