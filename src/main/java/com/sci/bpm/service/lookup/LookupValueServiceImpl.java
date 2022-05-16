package com.sci.bpm.service.lookup;

import java.util.List;
import java.util.Map;

import com.sci.bpm.db.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sci.bpm.dao.lookup.LookupValueDAO;

@Service
@Transactional
public class LookupValueServiceImpl implements LookUpValueService {

	@Autowired
	private LookupValueDAO daoimpl;
	
	
	public List getDropDownValues(String queryKey) {
		
		
		return daoimpl.getDropDownList(queryKey);
	}
	
	public boolean addNewValue(SciLookupMaster master) {
		
		return daoimpl.addNewValue(master);
	}

	
	public Long loadIDData(String lovName) {
		return daoimpl.lookupId(lovName);
	}

	public List<SciCustomerMaster> loadCustomerforOrg(Long seqClientOrgId) {
		return daoimpl.loadCustomerforOrg(seqClientOrgId);
	}

	@Override
	public List<SciCustomerMaster> loadCustomerforOrgandState(Long seqClientOrgId, Long stateCode) {
		return daoimpl.loadCustomerforOrgandState(seqClientOrgId,stateCode);
	}

	public List<SciClientOrgMaster> loadOrgNames() {
		return daoimpl.loadOrgNames();
	}


	public Long loadIDDestc(String lovName) {
		return daoimpl.lookupId(lovName);
	}
	
	
	public Map loadIDDescription() {
		
		return daoimpl.loadDesc();
	}
	
	
	public boolean addNewItemValue(SciMasterItem master) {
		
		return daoimpl.addNewItemValue(master);
	}
	public void addNewCustomer(SciCustomerMaster custmaster) {
		daoimpl.addNewCustomer(custmaster);
		
	}

	public void addNewClientOrg(SciClientOrgMaster orgMaster) {
		daoimpl.addNewClientOrg(orgMaster);
	}

	public void addNewMatSpec(SciMatspecMaster specmaster) {
		daoimpl.addNewMatSpec(specmaster);
		
	}
	public void addNewVendor(SciVendorMaster vendorMaster) {
		
		daoimpl.addNewVendor(vendorMaster);
	}

	@Override
	public void updateVendor(SciVendorMaster vendorMaster) {
		daoimpl.updateVendor(vendorMaster);
	}

	@Override
	public void updateCustomer(SciCustomerMaster customerMaster) {
		daoimpl.updateCustomer(customerMaster);
	}

	public void updateCLientOrg(SciClientOrgMaster clientOrg) {
		daoimpl.updateCLientOrg(clientOrg);
	}

	@Override
	public SciVendorMaster loadVendor(Long seqVendorId) {

		return daoimpl.loadVendor(seqVendorId);
	}

	public void addNewReport(SciReportConfiguration reportConfig) {
		// TODO Auto-generated method stub
		daoimpl.addNewReport(reportConfig);
	}

	public List<SciReportConfiguration> loadReports() {
		// TODO Auto-generated method stub
		return daoimpl.loadReports();
	}

	public void updateStatus(SciReportConfiguration config) {
		// TODO Auto-generated method stub
		daoimpl.updateStatus(config);
	}

	public List<SciStateCityMasterEntity> loadCities(String stateCode) {
		return daoimpl.loadCities(stateCode);
	}

	@Override
	public String findOrgByOrgCode(String orgCode) {
		return daoimpl.findOrgByOrgCode(orgCode);
	}

	@Override
	public SciCustomerMaster findCustomer(Long seqClientId) {
		return daoimpl.findCustomer(seqClientId);
	}

	public String selectOrgCode() {
		return daoimpl.selectOrgCode();
	}

	public String selectCustomerCode(String seq_client_org_id) {
		return daoimpl.selectCustomerCode(seq_client_org_id);
	}


}
