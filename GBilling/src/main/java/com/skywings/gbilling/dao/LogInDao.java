package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.Operator;

public interface LogInDao {

	public List<Operator> getOperator();

	public List<Operator> getOperDetails(Integer staffId);

}
