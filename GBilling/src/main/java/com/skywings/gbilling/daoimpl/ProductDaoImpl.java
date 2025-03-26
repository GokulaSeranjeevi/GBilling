package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.ProductDao;
import com.skywings.gbilling.daoimpl.OperatorDetailsDaoImpl.OperatorRowMapper;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.TaxMaster;
import com.skywings.gbilling.query.ProductQuery;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	@Qualifier("MasterDb") 
	private JdbcTemplate jdbcTemplate;

	@Autowired
	
	private ProductQuery productQuery;

	@Override
	public String getProcode() throws Exception {

		try {

			String proCode = jdbcTemplate.queryForObject(productQuery.getProCode(), String.class, new Object[] {});

			return proCode;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	@Override
	public List<TaxMaster> getTaxMaster() throws Exception {

		List<TaxMaster> lstTaxMaster;

		try {

			lstTaxMaster = jdbcTemplate.query(productQuery.getTaxMaster(), new TaxMasterRowMapper());

			return lstTaxMaster;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	class TaxMasterRowMapper implements RowMapper<TaxMaster> {
		@Override
		public TaxMaster mapRow(ResultSet rs, int rowNum) throws SQLException {

			TaxMaster taxMaster = new TaxMaster();

			taxMaster.setTaxCode(rs.getInt("Taxcode"));
			taxMaster.setTaxName(rs.getString("TaxName"));
			taxMaster.setTaxPer(rs.getDouble("TaxPer"));
			taxMaster.setCGSTPer(rs.getDouble("CGSTPer"));
			taxMaster.setSGSTPer(rs.getDouble("SGSTPer"));
			taxMaster.setActive(rs.getString("Active"));

			return taxMaster;
		}
	}

	@Override
	public List<MasterCommon> getUnit() throws Exception {

		List<MasterCommon> lstMasterCommon;
		try {

			lstMasterCommon = jdbcTemplate.query(productQuery.getUnit(), new UnitRowMapper());

			return lstMasterCommon;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	class UnitRowMapper implements RowMapper<MasterCommon> {

		@Override
		public MasterCommon mapRow(ResultSet rs, int rowNum) throws SQLException {

			MasterCommon masterCommon = new MasterCommon();

			masterCommon.setDescription(rs.getString("Description"));
			masterCommon.setUnit(rs.getString("Unit"));

			return masterCommon;

		}
	}

	@Override
	public List<MasterCommon> getHsn() throws Exception {
		List<MasterCommon> lstMasterCommon;
		try {

			lstMasterCommon = jdbcTemplate.query(productQuery.getHsn(), new HSNRowMapper());

			return lstMasterCommon;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}

	}

	class HSNRowMapper implements RowMapper<MasterCommon> {

		@Override
		public MasterCommon mapRow(ResultSet rs, int rowNum) throws SQLException {

			MasterCommon Hsn = new MasterCommon();

			Hsn.setHSNCode(rs.getString("HSNCode"));
			Hsn.setHSNName(rs.getString("HSNName"));
			Hsn.setHSNActive(rs.getString("Active"));

			return Hsn;

		}

	}

	@Override
	public void saveProduct(Product product) throws Exception {

		try {

			jdbcTemplate.update(productQuery.saveProduct(),
					new Object[] { product.getProCode(), product.getProName(), product.getShortName(),
							product.getCatcode(), product.getTaxcode(), product.getIncGst(), product.getUnit(),
							product.getHsnCode(), product.getPurUnit(), product.getActive() });

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	@Override
	public List<Product> getProduct() throws Exception {
		try {

			List<Product> lstProduct = jdbcTemplate.query(productQuery.getProduct(), new ProductRowMapper());

			return lstProduct;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

			Product product = new Product();

			product.setProCode(rs.getString("Procode"));
			product.setProName(rs.getString("ProName"));
			product.setShortName(rs.getString("ShortName"));
			product.setTaxcode(rs.getInt("taxcode"));
			product.setIncGst(rs.getString("Incgst"));
			product.setUnit(rs.getString("unit"));
			product.setHsnCode(rs.getString("HsnCode"));
			product.setPurUnit(rs.getString("Purunit"));
			product.setActive(rs.getString("ACtive"));

			return product;
		}
	}
}
