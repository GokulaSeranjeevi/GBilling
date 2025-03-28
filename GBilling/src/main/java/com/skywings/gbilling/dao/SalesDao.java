package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.MasterCommon;
import com.skywings.gbilling.model.Operator;
import com.skywings.gbilling.model.Product;
import com.skywings.gbilling.model.Sales;
import com.skywings.gbilling.model.SubProduct;
import com.skywings.gbilling.model.VendorMaster;

public interface SalesDao {

	public List<VendorMaster> getVendor();

	public List<CityMaster> getCity();

	public List<Operator> getSaleMan();

	public List<Product> getProduct();

	public List<SubProduct> getSubProduct();

	public List<MasterCommon> getUnit();

	public String getTranNo();

	public void transactionSave(List<Sales> lstSales);

	public List<Sales> getSalesView();

}
