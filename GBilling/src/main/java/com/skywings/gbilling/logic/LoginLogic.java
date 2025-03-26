package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.common.CommonMethods;
import com.skywings.gbilling.dao.LogInDao;
import com.skywings.gbilling.model.Operator;

@Component
@Scope("prototype")
public class LoginLogic {

	@Autowired
	private LogInDao operatorDao;

	@SuppressWarnings("unchecked")
	public List<Operator> getOperators() {

		return operatorDao.getOperator();

	}

	public List<Operator> getOperatorDetails(Integer staffId) {

		return operatorDao.getOperDetails(staffId);

	}

}
