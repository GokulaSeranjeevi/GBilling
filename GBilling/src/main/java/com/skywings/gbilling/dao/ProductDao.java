package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.TaxMaster;

public interface ProductDao {

	public String getProcode() throws Exception;

	public List<TaxMaster> getTaxMaster() throws Exception;

	public List<MasterCommon> getUnit() throws Exception;

	public List<MasterCommon> getHsn() throws Exception;

	public void saveProduct(Product product) throws Exception;

	public List<Product> getProduct() throws Exception;

}
