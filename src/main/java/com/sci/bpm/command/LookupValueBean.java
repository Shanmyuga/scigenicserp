package com.sci.bpm.command;

import java.io.Serializable;

public class LookupValueBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String lovName;
	private String lovType;
	private String lovDescription;
	private Long seqCustId;
	private String customerName;
	private String customerAddress;
	private String customerAddress1;
	private String customerPhone;
	private String cusomterCity;
	private String customerEmail;
	private String customerContact;
	private String customerCityCode;
	private String customerCode;
	private Long customerState;
	private Long seqClientOrgId;
	private String orgName;
	private String orgAddress;
	private String orgCode;
	private String orgHqStateCode;
	private String customerCountry;
	private String stateCode;
	private Long seqVendorId;
	private String vendorName;
	private String vendorAddress1;
	private String phoneNumber;
	private String emailId;
	private String vendorContact;
	private String vendorCity;
	private String vendorAddress;
	private String customerStatus;


	
	
	private String seqReportID;
	private String reportQuery;
	private String reportSubject;
	private String reportEmail;



	private String reportSchedule;
	private String reportStatus;

	private String reportFilter;
	private String matcatCode;
     private String matSpecDesc;
     private String materialSpec;
     private String specCode;

     private String matDept;

	public LookupValueBean() {
	}
	public LookupValueBean(String lovName,String lovType) {

		this.lovName = lovName;
		this.lovType = lovType;
	}

	public String getLovName() {
		return lovName;
	}
	public void setLovName(String lovName) {
		this.lovName = lovName;
	}
	public String getLovType() {
		return lovType;
	}
	public void setLovType(String lovType) {
		this.lovType = lovType;
	}
	public String getLovDescription() {
		return lovDescription;
	}
	public void setLovDescription(String lovDescription) {
		this.lovDescription = lovDescription;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerAddress1() {
		return customerAddress1;
	}
	public void setCustomerAddress1(String customerAddress1) {
		this.customerAddress1 = customerAddress1;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCusomterCity() {
		return cusomterCity;
	}
	public void setCusomterCity(String cusomterCity) {
		this.cusomterCity = cusomterCity;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAddress1() {
		return vendorAddress1;
	}
	public void setVendorAddress1(String vendorAddress1) {
		this.vendorAddress1 = vendorAddress1;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getVendorContact() {
		return vendorContact;
	}
	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}
	public String getVendorCity() {
		return vendorCity;
	}
	public void setVendorCity(String vendorCity) {
		this.vendorCity = vendorCity;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	public String getMatcatCode() {
		return matcatCode;
	}
	public void setMatcatCode(String matcatCode) {
		this.matcatCode = matcatCode;
	}
	public String getMatSpecDesc() {
		return matSpecDesc;
	}
	public void setMatSpecDesc(String matSpecDesc) {
		this.matSpecDesc = matSpecDesc;
	}
	public String getMaterialSpec() {
		return materialSpec;
	}
	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}
	public String getSpecCode() {
		return specCode;
	}
	public void setSpecCode(String specCode) {
		this.specCode = specCode;
	}
	public String getMatDept() {
		return matDept;
	}
	public void setMatDept(String matDept) {
		this.matDept = matDept;
	}
	
	public String getReportQuery() {
		return reportQuery;
	}
	public void setReportQuery(String reportQuery) {
		this.reportQuery = reportQuery;
	}
	public String getReportSubject() {
		return reportSubject;
	}
	public void setReportSubject(String reportSubject) {
		this.reportSubject = reportSubject;
	}
	public String getReportEmail() {
		return reportEmail;
	}
	public void setReportEmail(String reportEmail) {
		this.reportEmail = reportEmail;
	}
	public String getReportSchedule() {
		return reportSchedule;
	}
	public void setReportSchedule(String reportSchedule) {
		this.reportSchedule = reportSchedule;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getSeqReportID() {
		return seqReportID;
	}
	public void setSeqReportID(String seqReportID) {
		this.seqReportID = seqReportID;
	}
	public Long getCustomerState() {
		return customerState;
	}
	public void setCustomerState(Long customerState) {
		this.customerState = customerState;
	}
	public String getCustomerCountry() {
		return customerCountry;
	}
	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public String getReportFilter() {
		return reportFilter;
	}

	public void setReportFilter(String reportFilter) {
		this.reportFilter = reportFilter;
	}

	public Long getSeqVendorId() {
		return seqVendorId;
	}

	public void setSeqVendorId(Long seqVendorId) {
		this.seqVendorId = seqVendorId;
	}

	public Long getSeqCustId() {
		return seqCustId;
	}

	public void setSeqCustId(Long seqCustId) {
		this.seqCustId = seqCustId;
	}

	public Long getSeqClientOrgId() {
		return seqClientOrgId;
	}

	public void setSeqClientOrgId(Long seqClientOrgId) {
		this.seqClientOrgId = seqClientOrgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public String getCustomerCityCode() {
		return customerCityCode;
	}

	public void setCustomerCityCode(String customerCityCode) {
		this.customerCityCode = customerCityCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}
}
