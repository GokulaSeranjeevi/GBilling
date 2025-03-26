package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.SalesDao;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.Sales;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.model.VendorMaster;

@Component
public class SalesLogic {

	@Autowired
	private SalesDao salesDao;

	public List<VendorMaster> getVendor() {

		return salesDao.getVendor();

	}

	public List<CityMaster> getCity() {
		return salesDao.getCity();
	}

	public List<Operator> getSalesMan() {
		return salesDao.getSaleMan();
	}

	public List<Product> getProduct() {
		return salesDao.getProduct();
	}

	public List<SubProduct> getSubProduct() {
		return salesDao.getSubProduct();
	}

	public List<MasterCommon> getUnit() {
		return salesDao.getUnit();
	}

	public String getTranNo() {
		return salesDao.getTranNo();
	}

	public void transactionSave(List<Sales> lstSales) {

		salesDao.transactionSave(lstSales);

	}

}
