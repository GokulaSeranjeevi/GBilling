package com.skywings.gbilling.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.skywings.gbilling.dao.VendorDao;
import com.skywings.gbilling.model.BalGroup;
import com.skywings.gbilling.model.CityMaster;
import com.skywings.gbilling.model.StateMaster;
import com.skywings.gbilling.model.VendorMaster;

@Component
public class VendorLogic {

	@Autowired
	private VendorDao vendorDao;

	public String getAcctcode() throws Exception {

		return vendorDao.getAcctCode();
	}

	public List<StateMaster> getState() throws Exception {
		return vendorDao.getState();
	}

	public List<BalGroup> getGroup() throws Exception {
		return vendorDao.getGroup();
	}

	public List<CityMaster> getCity() throws Exception {
		return vendorDao.getCity();
	}

	public void saveVendor(VendorMaster vendor) throws Exception {

		vendorDao.saveVendor(vendor);

	}

	public List<VendorMaster> getVendor() throws Exception {
		return vendorDao.getVendor();
	}

}
