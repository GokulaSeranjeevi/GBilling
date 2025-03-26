package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.SubProduct;

public interface SubproductDao {

	public String getSuproductCode() throws Exception;

	public List<SubProduct> getSubProduct() throws Exception;

	public void saveProduct(SubProduct subProduct) throws Exception;

	public List<Product> getProduct() throws Exception;

}
