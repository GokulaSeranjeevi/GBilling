package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.VendorDao;
import com.skywings.gbilling.daoimpl.ProductDaoImpl.ProductRowMapper;
import com.skywings.gbilling.model.BalGroup;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.StateMaster;
import com.skywings.gbilling.model.VendorMaster;
import com.skywings.gbilling.query.VendorQuery;

@Component
public class VendorDetailsDaoImpl implements VendorDao {
	@Autowired
	@Qualifier("MasterDb")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private VendorQuery vendorQuery;

	@Override
	public String getAcctCode() throws Exception {
		try {

			String acctcode = jdbcTemplate.queryForObject(vendorQuery.getAcctCode(), String.class, new Object[] {});

			return acctcode;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
	}

	@Override
	public List<StateMaster> getState() throws Exception {

		try {

			List<StateMaster> lstStateMasters = jdbcTemplate.query(vendorQuery.getState(), new StateRowMapper());

			return lstStateMasters;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class StateRowMapper implements RowMapper<StateMaster> {
		@Override
		public StateMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			StateMaster stateMaster = new StateMaster();

			stateMaster.setStateCode(rs.getString("Statecode"));
			stateMaster.setDesc(rs.getString("Description"));
			stateMaster.setStateGSTCode(rs.getString("StateGSTCode"));

			return stateMaster;
		}
	}

	@Override
	public List<BalGroup> getGroup() throws Exception {
		try {

			List<BalGroup> lstBalGroup = jdbcTemplate.query(vendorQuery.getGroup(), new GroupRowMapper());

			return lstBalGroup;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class GroupRowMapper implements RowMapper<BalGroup> {
		@Override
		public BalGroup mapRow(ResultSet rs, int rowNum) throws SQLException {

			BalGroup balGroup = new BalGroup();

			balGroup.setGroupCode(rs.getString("GroupCode"));
			balGroup.setGroupName(rs.getString("GroupName"));

			return balGroup;
		}
	}

	@Override
	public List<CityMaster> getCity() throws Exception {
		try {

			List<CityMaster> lstBalGroup = jdbcTemplate.query(vendorQuery.getCity(), new CityRowMapper());

			return lstBalGroup;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class CityRowMapper implements RowMapper<CityMaster> {
		@Override
		public CityMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			CityMaster cityMaster = new CityMaster();

			cityMaster.setCitycode(rs.getString("cityCode"));
			cityMaster.setDesc(rs.getString("Description"));
			cityMaster.setStatecode(rs.getString("Statecode"));

			return cityMaster;
		}
	}

	@Override
	public void saveVendor(VendorMaster vendor) throws Exception {

		try {

			jdbcTemplate.update(vendorQuery.saveVendor(),
					new Object[] { vendor.getAcctCode(), vendor.getAcctName(), vendor.getGroupCode(),
							vendor.getAddress1(), vendor.getAddress2(), vendor.getAddress3(), vendor.getPincode(),
							vendor.getPhone(), vendor.getMobileNo(), vendor.getEmailId(), vendor.getGSTNo(),
							vendor.getPANNo(), vendor.getShortName(), "", vendor.getCityCode(),
							vendor.getStateCode() });

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	@Override
	public List<VendorMaster> getVendor() throws Exception {
		try {

			List<VendorMaster> lstvendormaster = jdbcTemplate.query(vendorQuery.getVendor(), new VendorRowMapper());

			return lstvendormaster;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class VendorRowMapper implements RowMapper<VendorMaster> {
		@Override
		public VendorMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			VendorMaster vendor = new VendorMaster();

			vendor.setAcctCode(rs.getString("AcctId"));
			vendor.setAcctName(rs.getString("AcctName"));
			vendor.setShortName(rs.getString("ShortName"));
			vendor.setGroupName(rs.getString("GroupName"));
			vendor.setAddress1(rs.getString("Address1"));
			vendor.setAddress2(rs.getString("Address2"));
			vendor.setAddress3(rs.getString("Address3"));
			vendor.setPincode(rs.getString("Pincode"));
			vendor.setPhone(rs.getString("phone"));
			vendor.setMobileNo(rs.getString("MobileNo"));
			vendor.setEmailId(rs.getString("EmailId"));
			vendor.setGSTNo(rs.getString("GSTNo"));
			vendor.setPANNo(rs.getString("PANNo"));
			vendor.setCity(rs.getString("City"));
			vendor.setState(rs.getString("State"));

			return vendor;
		}
	}

}
