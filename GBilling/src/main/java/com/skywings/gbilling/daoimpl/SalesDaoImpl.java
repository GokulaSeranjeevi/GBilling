package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.SalesDao;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.Sales;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.model.VendorMaster;
import com.skywings.gbilling.query.SalesQuery;

@Component
public class SalesDaoImpl implements SalesDao {

	@Autowired
	@Qualifier("MasterDb")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("tranJdbcTemplate")
	private JdbcTemplate secJdbcTemplate;
	@Autowired
	private SalesQuery salesQuery;

	@Override
	public List<VendorMaster> getVendor() {

		List<VendorMaster> lstVendorMasters;

		lstVendorMasters = jdbcTemplate.query(salesQuery.getVendor(), new VendorRowMapper());

		return lstVendorMasters;

	}

	class VendorRowMapper implements RowMapper<VendorMaster> {
		@Override
		public VendorMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			VendorMaster vendorMaster = new VendorMaster();

			vendorMaster.setAcctCode(rs.getString("AcctId"));
			vendorMaster.setAcctName(rs.getString("AcctName"));

			return vendorMaster;
		}
	}

	@Override
	public List<CityMaster> getCity() {

		List<CityMaster> lstCityMaster;

		lstCityMaster = jdbcTemplate.query(salesQuery.getCity(), new CityRowMapper());

		return lstCityMaster;
	}

	class CityRowMapper implements RowMapper<CityMaster> {
		@Override
		public CityMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			CityMaster cityMaster = new CityMaster();

			cityMaster.setCitycode(rs.getString("CityCode"));
			cityMaster.setDesc(rs.getString("Description"));

			return cityMaster;
		}
	}

	@Override
	public List<Operator> getSaleMan() {
		List<Operator> lstOperator;

		lstOperator = jdbcTemplate.query(salesQuery.getSalesman(), new SalesManRowMapper());

		return lstOperator;
	}

	class SalesManRowMapper implements RowMapper<Operator> {
		@Override
		public Operator mapRow(ResultSet rs, int rowNum) throws SQLException {

			Operator salesMan = new Operator();

			salesMan.setOperatorId(rs.getInt("Opercode"));
			salesMan.setOperNameS(rs.getString("OperName"));

			return salesMan;
		}
	}

	@Override
	public List<Product> getProduct() {
		List<Product> lstProduct;

		lstProduct = jdbcTemplate.query(salesQuery.getProduct(), new ProductManRowMapper());

		return lstProduct;
	}

	class ProductManRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

			Product product = new Product();

			product.setProCode(rs.getString("Procode"));
			product.setProName(rs.getString("ProName"));

			return product;
		}

	}

	@Override
	public List<SubProduct> getSubProduct() {
		List<SubProduct> lstsubProduct;

		lstsubProduct = jdbcTemplate.query(salesQuery.getsubProduct(), new SubProductManRowMapper());

		return lstsubProduct;
	}

	class SubProductManRowMapper implements RowMapper<SubProduct> {
		@Override
		public SubProduct mapRow(ResultSet rs, int rowNum) throws SQLException {

			SubProduct product = new SubProduct();

			product.setSubcode(rs.getString("subcode"));
			product.setSubProName(rs.getString("SubProName"));

			return product;
		}

	}

	@Override
	public List<MasterCommon> getUnit() {
		List<MasterCommon> lstUnit;

		lstUnit = jdbcTemplate.query(salesQuery.getUnit(), new UnitManRowMapper());

		return lstUnit;
	}

	class UnitManRowMapper implements RowMapper<MasterCommon> {
		@Override
		public MasterCommon mapRow(ResultSet rs, int rowNum) throws SQLException {

			MasterCommon unit = new MasterCommon();

			unit.setUnit(rs.getString("unit"));
			unit.setDescription(rs.getString("Description"));

			return unit;
		}

	}

	@Override
	public String getTranNo() {

		String tranNo = secJdbcTemplate.queryForObject(salesQuery.getTranNo(), String.class, new Object[] {});

		return tranNo;

	}

	@Override
	public void transactionSave(List<Sales> lstSales) {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(secJdbcTemplate);
		simpleJdbcCall.setProcedureName("Sp_SaveTransaction");

		try {

			Map<String, Object> mapTransactionSave = new HashMap<String, Object>();

			for (Sales sales : lstSales) {

				mapTransactionSave.put("TranDate", sales.getTranDate());
				mapTransactionSave.put("TranNo", sales.getTranNo());
				mapTransactionSave.put("AcctId", sales.getAcctId());
				mapTransactionSave.put("AcctName", sales.getAcctName());
				mapTransactionSave.put("GrossAmount", sales.getGrossAmount());
				mapTransactionSave.put("NetAmount", sales.getNetAmount());
				mapTransactionSave.put("TaxAmount", sales.getTaxAmount());
				mapTransactionSave.put("DiscPercentage", sales.getDiscPercentage());
				mapTransactionSave.put("DiscAmount", sales.getDiscAmount());
				mapTransactionSave.put("BillDate", sales.getBillDate());
				mapTransactionSave.put("BillTime", sales.getBillTime());
				mapTransactionSave.put("TaxCode", sales.getTaxCode());
				mapTransactionSave.put("Narration1", sales.getNarration1());
				mapTransactionSave.put("Narration2", "");
				mapTransactionSave.put("VechileNo", sales.getVechileNo());
				mapTransactionSave.put("RoundOff", 0.00);
				mapTransactionSave.put("EwayBillNo", "");
				mapTransactionSave.put("IRNNumber", "");
				mapTransactionSave.put("CompanyCode", "MKS");
				mapTransactionSave.put("SalesType", sales.getSalesType());
				mapTransactionSave.put("SalMan", sales.getSalMan());
				mapTransactionSave.put("RouteId", sales.getRouteId());
				mapTransactionSave.put("Createdby", 0);
				mapTransactionSave.put("Createdtime", "");
				mapTransactionSave.put("Rate", sales.getRate());
				mapTransactionSave.put("Pieces", sales.getPieces());
				mapTransactionSave.put("Procode", sales.getProcode());
				mapTransactionSave.put("Unit", sales.getUnit());

				try {
					simpleJdbcCall.execute(mapTransactionSave);

					JOptionPane.showMessageDialog(null,
							"Transaction successfully saved for TranNo: " + sales.getTranNo());
				} catch (Exception e) {
					System.err.println("Error executing transaction for TranNo: " + sales.getTranNo());
					throw new RuntimeException("Failed to save transaction for TranNo: " + sales.getTranNo(), e);
				}
			}

		} catch (EmptyStackException e) {
			System.err.println("EmptyStackException occurred: " + e.getMessage());
			throw e;
		} catch (Exception e) {
			System.err.println("An unexpected error occurred: " + e.getMessage());
			throw new RuntimeException("An unexpected error occurred while processing the transactions.", e);
		}
	}

}
