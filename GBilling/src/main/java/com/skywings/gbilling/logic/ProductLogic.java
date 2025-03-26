package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.ProductDao;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.TaxMaster;

@Component
public class ProductLogic {

	@Autowired
	private ProductDao productDao;

	public String getProCode() throws Exception {

		return productDao.getProcode();

	}

	public List<TaxMaster> getGst() throws Exception {

		return productDao.getTaxMaster();

	}

	public List<MasterCommon> getUnit() throws Exception {

		return productDao.getUnit();
	}

	public List<MasterCommon> getHsn() throws Exception {
		return productDao.getHsn();
	}

	public void saveProduct(Product product) throws Exception {
		productDao.saveProduct(product);

	}

	public List<Product> getProduct() throws Exception {
		return productDao.getProduct();
	}

}
