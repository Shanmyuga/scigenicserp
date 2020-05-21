package com.sci.bpm.command.marketing;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

public class EnqBean implements Serializable {

    private int windex;
    private Long seqCustomerId;
    private String enqDetails;
    private Date enqDate;
    private Long enqType;
    private Long seqClientOrgId;

    private String customerName;

    private String customerContact;

    private String enqSource;
    private String enqStatus;
    private String actionTaken;
    private String enqAttendee;
    private Date actionDate;
    private Long seqenqmasterid;
    private Long enqPriority;
    private Long enqmasterType;
    private Long enqCategory;
    private Date enqFollowUpDate;
    private Long seqEnqMasterId;
    private Long customerDept;
    private String enqDocDesc;

    private String orgCode;

    private String orgName;

    private String enqFullCode;

    private String customerCityCode;

    private String stateCode;

    private String rawOrgCode;

    private String rawstateCode;

    private String rawcustomerCityCode;

    private String orgStateCityCode;

    private Date enqCreateFromDate;

    private Date enqCreateToDate;

    private MultipartFile fileDoc;
    private String reportFilter;

    public Long getSeqEnqMasterId() {
        return seqEnqMasterId;
    }

    public void setSeqEnqMasterId(Long seqEnqMasterId) {
        this.seqEnqMasterId = seqEnqMasterId;
    }

    private Date bringForwardDate;

    private String purpose;

    public Long getSeqCustomerId() {
        return seqCustomerId;
    }

    public void setSeqCustomerId(Long seqCustomerId) {
        this.seqCustomerId = seqCustomerId;
    }

    public String getEnqDetails() {
        return enqDetails;
    }

    public void setEnqDetails(String enqDetails) {
        this.enqDetails = enqDetails;
    }

    public Date getEnqDate() {
        return enqDate;
    }

    public void setEnqDate(Date enqDate) {
        this.enqDate = enqDate;
    }

    public Long getEnqType() {
        return enqType;
    }

    public void setEnqType(Long enqType) {
        this.enqType = enqType;
    }

    public String getEnqSource() {
        return enqSource;
    }

    public void setEnqSource(String enqSource) {
        this.enqSource = enqSource;
    }

    public String getEnqStatus() {
        return enqStatus;
    }

    public void setEnqStatus(String enqStatus) {
        this.enqStatus = enqStatus;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public String getEnqAttendee() {
        return enqAttendee;
    }

    public void setEnqAttendee(String enqAttendee) {
        this.enqAttendee = enqAttendee;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public int getWindex() {
        return windex;
    }

    public void setWindex(int windex) {
        this.windex = windex;
    }

    public Long getSeqenqmasterid() {
        return seqenqmasterid;
    }

    public void setSeqenqmasterid(Long seqenqmasterid) {
        this.seqenqmasterid = seqenqmasterid;
    }

    public Date getBringForwardDate() {
        return bringForwardDate;
    }

    public void setBringForwardDate(Date bringForwardDate) {
        this.bringForwardDate = bringForwardDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getEnqCategory() {
        return enqCategory;
    }

    public void setEnqCategory(Long enqCategory) {
        this.enqCategory = enqCategory;
    }

    public Long getCustomerDept() {
        return customerDept;
    }

    public void setCustomerDept(Long customerDept) {
        this.customerDept = customerDept;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public Long getEnqmasterType() {
        return enqmasterType;
    }

    public void setEnqmasterType(Long enqmasterType) {
        this.enqmasterType = enqmasterType;
    }

    public Long getEnqPriority() {
        return enqPriority;
    }

    public void setEnqPriority(Long enqPriority) {
        this.enqPriority = enqPriority;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getEnqFollowUpDate() {
        return enqFollowUpDate;
    }

    public void setEnqFollowUpDate(Date enqFollowUpDate) {
        this.enqFollowUpDate = enqFollowUpDate;
    }

    public MultipartFile getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(MultipartFile fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getEnqDocDesc() {
        return enqDocDesc;
    }

    public void setEnqDocDesc(String enqDocDesc) {
        this.enqDocDesc = enqDocDesc;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCustomerCityCode() {
        return customerCityCode;
    }

    public void setCustomerCityCode(String customerCityCode) {
        this.customerCityCode = customerCityCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getRawOrgCode() {
        return rawOrgCode;
    }

    public void setRawOrgCode(String rawOrgCode) {
        this.rawOrgCode = rawOrgCode;
    }

    public String getRawstateCode() {
        return rawstateCode;
    }

    public void setRawstateCode(String rawstateCode) {
        this.rawstateCode = rawstateCode;
    }

    public String getRawcustomerCityCode() {
        return rawcustomerCityCode;
    }

    public void setRawcustomerCityCode(String rawcustomerCityCode) {
        this.rawcustomerCityCode = rawcustomerCityCode;
    }

    public String getOrgStateCityCode() {
        return orgStateCityCode;
    }

    public void setOrgStateCityCode(String orgStateCityCode) {
        this.orgStateCityCode = orgStateCityCode;
    }

    public Long getSeqClientOrgId() {
        return seqClientOrgId;
    }

    public void setSeqClientOrgId(Long seqClientOrgId) {
        this.seqClientOrgId = seqClientOrgId;
    }

    public String getEnqFullCode() {
        return enqFullCode;
    }

    public void setEnqFullCode(String enqFullCode) {
        this.enqFullCode = enqFullCode;
    }

    public String getReportFilter() {
        return reportFilter;
    }

    public void setReportFilter(String reportFilter) {
        this.reportFilter = reportFilter;
    }

    public Date getEnqCreateFromDate() {
        return enqCreateFromDate;
    }

    public void setEnqCreateFromDate(Date enqCreateFromDate) {
        this.enqCreateFromDate = enqCreateFromDate;
    }

    public Date getEnqCreateToDate() {
        return enqCreateToDate;
    }

    public void setEnqCreateToDate(Date enqCreateToDate) {
        this.enqCreateToDate = enqCreateToDate;
    }
}