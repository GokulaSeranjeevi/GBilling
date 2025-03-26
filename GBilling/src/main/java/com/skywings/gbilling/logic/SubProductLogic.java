package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.SubproductDao;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.SubProduct;

@Component
public class SubProductLogic {

	@Autowired
	private SubproductDao subProductdao;

	public String getSubProductCode() throws Exception {
		return subProductdao.getSuproductCode();
	}

	public List<SubProduct> getSubProduct() throws Exception {

		return subProductdao.getSubProduct();
	}

	public void saveSubProduct(SubProduct subProduct) throws Exception {

		subProductdao.saveProduct(subProduct);

	}

	public List<Product> getProduct() throws Exception {
		 return subProductdao.getProduct();
	}

}
