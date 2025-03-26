package com.skywings.gbilling.dao;

import java.util.List;

import com.skywings.gbilling.model.BalGroup;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.StateMaster;
import com.skywings.gbilling.model.VendorMaster;

public interface VendorDao {

	public String getAcctCode() throws Exception;

	public List<StateMaster> getState() throws Exception;

	public List<BalGroup> getGroup() throws Exception;

	public List<CityMaster> getCity() throws Exception;

	public void saveVendor(VendorMaster vendor) throws Exception;

	public List<VendorMaster> getVendor() throws Exception;

}
