package com.sci.bpm.db.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * SciEnquiryMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCI_ENQUIRY_MASTER", schema = "SCIGENICS")
public class SciEnquiryMaster implements java.io.Serializable {

	// Fields

	private Long seqEnqryId;
	private SciCustomerMaster sciCustomerMaster;
	
	private String enqDetails;
	private Date enqDate;
	private Long enqType;
	private Date insertedDate;
	private String insertedBy;
	private String udpatedBy;
	private Date updatedDate;
	private String enqAttendee;
	private Date closeDate;
	private String enqSource;
	private String enqStatus;
	private Long enqPriority;
	private String enquiryTimeline;
	private Long enqCategory;
	
	private Long customerDept;
	private Date enqFollowUpDate;
	
	private String customerContact;
	private Long enquiryCode;
	private String enqCustomerCode;
	private String enqStateCityCode;

	private String enqOrgCode;

	private String enqFullCode;

	private String actionTaken;
	private Date actionDate;

	private String lastUpdatedBy;
	private Date bringForwardDate;

	private String enqCommerStatus;

	private String enqOrVisit;

	private String visitEnqRefCode;


	private Set<SciEnquiryDetails> sciEnquiryDetailses = new HashSet<SciEnquiryDetails>(
			0);

	// Constructors

	/** default constructor */
	public SciEnquiryMaster() {
	}

	/** minimal constructor */
	public SciEnquiryMaster(Long seqEnqryId) {
		this.seqEnqryId = seqEnqryId;
	}

	/** full constructor */
	public SciEnquiryMaster(Long seqEnqryId,
			String enqDetails, Date enqDate, String enqType,
			Date insertedDate, String insertedBy, String udpatedBy,
			Date updatedDate, String enqAttendee, Date closeDate,
			String enqSource, String enqStatus,
			Set<SciEnquiryDetails> sciEnquiryDetailses) {
		this.seqEnqryId = seqEnqryId;

		this.enqDetails = enqDetails;
		this.enqDate = enqDate;
		
		this.insertedDate = insertedDate;
		this.insertedBy = insertedBy;
		this.udpatedBy = udpatedBy;
		this.updatedDate = updatedDate;
		this.enqAttendee = enqAttendee;
		this.closeDate = closeDate;
		this.enqSource = enqSource;
		this.enqStatus = enqStatus;
		this.sciEnquiryDetailses = sciEnquiryDetailses;
	}

	// Property accessors

	@Id
	@Column(name = "SEQ_ENQRY_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 10, scale = 0)
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_ENQRY_ID_SEQ", name="SCI_ENQRY_ID_SEQ" )
	@GeneratedValue(generator = "SCI_ENQRY_ID_SEQ")
	public Long getSeqEnqryId() {
		return this.seqEnqryId;
	}

	public void setSeqEnqryId(Long seqEnqryId) {
		this.seqEnqryId = seqEnqryId;
	}

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_CUSTOMER_ID")
	public SciCustomerMaster getSciCustomerMaster() {
		return sciCustomerMaster;
	}

	public void setSciCustomerMaster(SciCustomerMaster sciCustomerMaster) {
		this.sciCustomerMaster = sciCustomerMaster;
	}

	@Column(name = "ENQ_DETAILS", length = 2000)
	public String getEnqDetails() {
		return this.enqDetails;
	}

	public void setEnqDetails(String enqDetails) {
		this.enqDetails = enqDetails;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "ENQ_DATE", length = 7)
	public Date getEnqDate() {
		return this.enqDate;
	}

	public void setEnqDate(Date enqDate) {
		this.enqDate = enqDate;
	}
	@Column(name = "ENQ_TYPE",  precision = 9, scale = 0)
	public Long getEnqType() {
		return this.enqType;
	}

	public void setEnqType(Long enqType) {
		this.enqType = enqType;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INSERTED_DATE", length = 7)
	public Date getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	@Column(name = "INSERTED_BY", length = 20)
	public String getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}

	@Column(name = "UDPATED_BY", length = 20)
	public String getUdpatedBy() {
		return this.udpatedBy;
	}

	public void setUdpatedBy(String udpatedBy) {
		this.udpatedBy = udpatedBy;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE", length = 7)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "ENQ_ATTENDEE", length = 20)
	public String getEnqAttendee() {
		return this.enqAttendee;
	}

	public void setEnqAttendee(String enqAttendee) {
		this.enqAttendee = enqAttendee;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "CLOSE_DATE", length = 7)
	public Date getCloseDate() {
		return this.closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Column(name = "ENQ_SOURCE", length = 100)
	public String getEnqSource() {
		return this.enqSource;
	}

	public void setEnqSource(String enqSource) {
		this.enqSource = enqSource;
	}

	@Column(name = "ENQ_STATUS", length = 1)
	public String getEnqStatus() {
		return this.enqStatus;
	}

	public void setEnqStatus(String enqStatus) {
		this.enqStatus = enqStatus;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sciEnquiryMaster")
	public Set<SciEnquiryDetails> getSciEnquiryDetailses() {
		return this.sciEnquiryDetailses;
	}

	public void setSciEnquiryDetailses(
			Set<SciEnquiryDetails> sciEnquiryDetailses) {
		this.sciEnquiryDetailses = sciEnquiryDetailses;
	}
	
	@Column(name = "CUSTOMER_CONTACT", length = 50)
	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}
	@Column(name = "ENQ_CATEGORY",  precision = 9, scale = 0)
	public Long getEnqCategory() {
		return enqCategory;
	}

	public void setEnqCategory(Long enqCategory) {
		this.enqCategory = enqCategory;
	}
	@Column(name = "CUSTOMER_DEPT",  precision = 9, scale = 0)
	public Long getCustomerDept() {
		return customerDept;
	}

	public void setCustomerDept(Long customerDept) {
		this.customerDept = customerDept;
	}


	@Column(name = "ENQUIRY_PRIORITY",  precision = 9, scale = 0)
	public Long getEnqPriority() {
		return enqPriority;
	}

	public void setEnqPriority(Long enqPriority) {
		this.enqPriority = enqPriority;
	}


	@Temporal(TemporalType.DATE)
	@Column(name = "ENQUIRY_FOLLOWUP_DATE", length = 7)
	public Date getEnqFollowUpDate() {
		return enqFollowUpDate;
	}

	public void setEnqFollowUpDate(Date enqFollowUpDate) {
		this.enqFollowUpDate = enqFollowUpDate;
	}

    @Column(name = "ENQ_CODE", precision = 9, scale = 0)
    public Long getEnquiryCode() {
        return enquiryCode;
    }

    public void setEnquiryCode(Long enquiryCode) {
        this.enquiryCode = enquiryCode;
    }

    @Column(name = "ENQ_CUSTOMER_CODE", length = 50)
    public String getEnqCustomerCode() {
        return enqCustomerCode;
    }

    public void setEnqCustomerCode(String enqCustomerCode) {
        this.enqCustomerCode = enqCustomerCode;
    }
	@Column(name = "ENQ_STATECITY_CODE", length = 20)
	public String getEnqStateCityCode() {
		return enqStateCityCode;
	}

	public void setEnqStateCityCode(String enqStateCityCode) {
		this.enqStateCityCode = enqStateCityCode;
	}
	@Column(name = "ENQ_ORG_CODE", length = 20)
	public String getEnqOrgCode() {
		return enqOrgCode;
	}

	public void setEnqOrgCode(String enqOrgCode) {
		this.enqOrgCode = enqOrgCode;
	}

	@Column(name = "ENQ_FULL_CODE", length = 20)
	public String getEnqFullCode() {
		return enqFullCode;
	}

	public void setEnqFullCode(String enqFullCode ) {
		this.enqFullCode = enqFullCode;
	}

		@Transient
	public String getActionTaken() {
		return actionTaken;
	}

	public void setActionTaken(String actionTaken) {
		this.actionTaken = actionTaken;
	}
	@Transient

	public Date getActionDate() {
		return actionDate;
	}

	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	@Transient
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	@Transient

	public Date getBringForwardDate() {
		return bringForwardDate;
	}

	public void setBringForwardDate(Date bringForwardDate) {
		this.bringForwardDate = bringForwardDate;
	}
	@Column(name = "ENQ_COMMER_STATUS", length = 50)
	public String getEnqCommerStatus() {
		return enqCommerStatus;
	}

	public void setEnqCommerStatus(String enqCommerStatus) {
		this.enqCommerStatus = enqCommerStatus;
	}
	@Column(name = "ENQ_OR_VISIT", length = 20)
	public String getEnqOrVisit() {
		return enqOrVisit;
	}

	public void setEnqOrVisit(String enqOrVisit) {
		this.enqOrVisit = enqOrVisit;
	}
	@Column(name = "ENQ_REF_VISIT", length = 50)
	public String getVisitEnqRefCode() {
		return visitEnqRefCode;
	}

	public void setVisitEnqRefCode(String visitEnqRefCode) {
		this.visitEnqRefCode = visitEnqRefCode;
	}
	@Column(name = "ENQUIRY_TIMELINE", length = 20)
	public String getEnquiryTimeline() {
		return enquiryTimeline;
	}

	public void setEnquiryTimeline(String enquiryTimeline) {
		this.enquiryTimeline = enquiryTimeline;
	}
}