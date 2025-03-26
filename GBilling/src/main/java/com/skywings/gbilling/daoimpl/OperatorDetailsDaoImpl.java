package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.OperatorDetailsDao;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.query.LogInQuery;
import com.skywings.gbilling.query.OperatorQuery;

@Component
public class OperatorDetailsDaoImpl implements OperatorDetailsDao {

	@Autowired
	private OperatorQuery operatorQuery;

	@Autowired
	@Qualifier("MasterDb")
	private JdbcTemplate jdbcTemplate;

	@Override
	public String getOperId() throws Exception {

		try {

			String operId = jdbcTemplate.queryForObject(operatorQuery.getOperId(), String.class, new Object[] {});

			return operId;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

	}

	@Override
	public void saveOperator(Operator operator) throws Exception {

		try {

			jdbcTemplate.update(operatorQuery.saveOperator(), new Object[] { operator.getOperatorId(),
					operator.getOperNameS(), operator.getOperPass(), operator.getActive() });

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<Operator> getOperator() throws Exception {

		try {

			List<Operator> lstOperators = jdbcTemplate.query(operatorQuery.getOperator(), new OperatorRowMapper());

			return lstOperators;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	class OperatorRowMapper implements RowMapper<Operator> {
		@Override
		public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {

			Operator operator = new Operator();

			operator.setOperatorId(rs.getInt("Opercode"));
			operator.setOperNameS(rs.getString("OperName"));
			operator.setOperPass(rs.getString("Operpass"));
			operator.setActive(rs.getString("Active"));

			return operator;
		}
	}

}
