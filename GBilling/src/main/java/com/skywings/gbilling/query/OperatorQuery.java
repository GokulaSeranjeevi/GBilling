package com.skywings.gbilling.query;

import org.springframework.stereotype.Component;

@Component
public class OperatorQuery {

	private StringBuilder sb;

	public String getOperId() {

		sb = new StringBuilder("");

		sb.append(" Select Max(Opercode+1) from Operator Where 1=1");

		return sb.toString();
	}

	public String saveOperator() {

		sb = new StringBuilder("");
		sb.append("Insert into Operator (Opercode,OperName,OperPass,Active) Values (?,?,?,?) ");

		return sb.toString();
	}

	public String getOperator() {

		sb = new StringBuilder("");

		sb.append("Select Cast(opercode as Varchar(30))Opercode,OperName,Active,OperPass from Operator  Where 1=1 ");

		return sb.toString();
	}

}
