package com.sci.bpm.dao.lookup;

import java.util.List;
import java.util.Map;

import com.sci.bpm.db.model.*;

public interface LookupValueDAO {

	public List<SciStateCityMasterEntity> loadCities(String stateCode);

	public List getDropDownList(String query);
	public List<SciCustomerMaster> loadCustomerforOrg(Long seqClientOrgId);

	public boolean addNewValue(SciLookupMaster master);
	public List<SciClientOrgMaster> loadOrgNames();
	public Long lookupId(String lovname);
	public boolean addNewItemValue(SciMasterItem master);
	public Map loadDesc();
	public void addNewReport(SciReportConfiguration reportConfig);
	public void addNewMatSpec(SciMatspecMaster specmaster);
	public void addNewCustomer(SciCustomerMaster custmaster);
	public void addNewClientOrg(SciClientOrgMaster orgMaster);

	public void addNewVendor(SciVendorMaster vendorMaster);
	public List<SciReportConfiguration> loadReports();
	public void updateStatus(SciReportConfiguration config);
	public void updateVendor(SciVendorMaster vendorMaster);
	public  SciVendorMaster loadVendor(Long seqVendorId);
	public void updateCustomer(SciCustomerMaster customerMaster);
	public void updateCLientOrg(SciClientOrgMaster clientOrg);
	public String selectOrgCode();


}
