package com.skywings.gbilling.query;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LogInQuery {

	private StringBuilder sb;

	public String getOperator() {

		sb = new StringBuilder();

		sb.append("Select * from Operator Where Active='Y' ");

		return sb.toString();
	}

	public String getOperDetails() {
		sb = new StringBuilder();

		sb.append("Select * from Operator Where Opercode= ?");

		return sb.toString();
	}

}
