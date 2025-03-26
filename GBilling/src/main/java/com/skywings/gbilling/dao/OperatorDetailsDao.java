package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.Operator;

public interface OperatorDetailsDao {

	public String getOperId() throws Exception;

	public void saveOperator(Operator operator) throws Exception;

	public List<Operator> getOperator() throws Exception;

}
