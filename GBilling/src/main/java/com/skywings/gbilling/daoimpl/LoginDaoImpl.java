package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.query.LogInQuery;

@Component
@Scope("prototype")
public class LoginDaoImpl implements com.skywings.gbilling.dao.LogInDao {

	@Autowired
	private LogInQuery operatorQuery;

	@Autowired
	@Qualifier("MasterDb")
	private JdbcTemplate tranJdbcTemplate;

	@Override
	public List<Operator> getOperator() {

		List<Operator> lstOperator;

		try {
			lstOperator = tranJdbcTemplate.query(operatorQuery.getOperator(), new OperatorRowMapper());

			return lstOperator;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	class OperatorRowMapper implements RowMapper<Operator> {

		@Override
		public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {

			Operator operator = new Operator();

			operator.setOperatorId(rs.getInt("operCode"));
			operator.setOperNameS(rs.getString("OperName"));
			operator.setOperPass(rs.getString("OperPass"));
			operator.setActive(rs.getString("Active"));

			return operator;
		}
	}

	@Override
	public List<Operator> getOperDetails(Integer staffId) {

		try {

			List<Operator> lstOperDetails;

			lstOperDetails = tranJdbcTemplate.query(operatorQuery.getOperDetails(), new OperatorRowMapper(),
					new Object[] { staffId });

			return lstOperDetails;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	// class OperDetailsRowMapper implements RowMapper<Operator> {
	//
	// @Override
	// public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {
	//
	// Operator operator = new Operator();
	//
	// operator.setStaffid(rs.getInt("Staffid"));
	// operator.setStaffname(rs.getString("StaffName"));
	// operator.setPwd(rs.getString("pwd"));
	// operator.setActive(rs.getString("Active"));
	//
	// return operator;
	// }
	// }

}