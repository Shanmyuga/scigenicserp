package com.sci.bpm.service.lookup;

import java.util.List;
import java.util.Map;

import com.sci.bpm.db.model.*;


public interface LookUpValueService {
	
	public List getDropDownValues(String queryKey);
	
	public boolean addNewValue(SciLookupMaster master);
	public Long loadIDData(String lovName);
    public List<SciCustomerMaster> loadCustomerforOrg(Long seqClientOrgId);
	public List<SciClientOrgMaster> loadOrgNames();
	public boolean addNewItemValue(SciMasterItem master);
	public Map loadIDDescription();
	public void addNewMatSpec(SciMatspecMaster specmaster);
	public void addNewCustomer(SciCustomerMaster custmaster);
	public void addNewClientOrg(SciClientOrgMaster orgMaster);
	public void addNewVendor(SciVendorMaster vendorMaster);
	public void updateVendor(SciVendorMaster vendorMaster);
	public void updateCustomer(SciCustomerMaster customerMaster);
	public void updateCLientOrg(SciClientOrgMaster clientOrg);
	public  SciVendorMaster loadVendor(Long seqVendorId);
	public void addNewReport(SciReportConfiguration reportConfig);
	public List<SciReportConfiguration> loadReports();
	public void updateStatus(SciReportConfiguration config);
	public List<SciStateCityMasterEntity> loadCities(String stateCode);
	public String selectOrgCode();
	public String selectCustomerCode(String seq_client_org_id);
}
