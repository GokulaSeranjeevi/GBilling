package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.OperatorDetailsDao;
import com.skywings.gbilling.model.Operator;

@Component
public class OperatorLogic {

	@Autowired
	private OperatorDetailsDao operatorDetailsDao;

	public String getOperId() throws Exception {

		return operatorDetailsDao.getOperId();

	}

	public void saveOperator(Operator operator) {

		try {
			operatorDetailsDao.saveOperator(operator);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Operator> getOperator() throws Exception {

		List<Operator> lstOperator = operatorDetailsDao.getOperator();

		return lstOperator;

	}

}
