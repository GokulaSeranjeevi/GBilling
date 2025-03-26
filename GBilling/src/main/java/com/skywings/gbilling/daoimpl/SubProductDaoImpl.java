package com.skywings.gbilling.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.SubproductDao;
import com.skywings.gbilling.daoimpl.ProductDaoImpl.ProductRowMapper;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.query.SubProductQuery;

@Component
public class SubProductDaoImpl implements SubproductDao {

	@Autowired
	@Qualifier("MasterDb")
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private SubProductQuery subproductQuery;

	@Override
	public String getSuproductCode() throws Exception {
		try {

			String subCode = jdbcTemplate.queryForObject(subproductQuery.getSubCode(), String.class, new Object[] {});

			return subCode;

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}
	}

	@Override
	public List<SubProduct> getSubProduct() throws Exception {
		try {

			List<SubProduct> lstSubProduct = jdbcTemplate.query(subproductQuery.getSubProduct(),
					new SubProductRowMapper());

			return lstSubProduct;

		} catch (Exception e) {

			throw new Exception(e.getMessage());
		}
	}

	class SubProductRowMapper implements RowMapper<SubProduct> {
		@Override
		public SubProduct mapRow(ResultSet rs, int rowNum) throws SQLException {

			SubProduct product = new SubProduct();

			product.setSubcode(rs.getString("SUbcode"));
			product.setSubProName(rs.getString("SubProname"));
			product.setProCode(rs.getString("Procode"));
			product.setProName(rs.getString("ProName"));
			product.setActive(rs.getString("ACtive"));

			return product;
		}
	}

	@Override
	public void saveProduct(SubProduct subProduct) throws Exception {
		try {

			jdbcTemplate.update(subproductQuery.saveSubProduct(), new Object[] { subProduct.getSubcode(),
					subProduct.getSubProName(), subProduct.getProCode(), subProduct.getActive() });

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}

	}

	@Override
	public List<Product> getProduct() throws Exception {
		try {

			List<Product> lstProduct = jdbcTemplate.query(subproductQuery.getProduct(), new ProductRowMapper());

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
