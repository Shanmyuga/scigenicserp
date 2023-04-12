package com.sci.bpm.db.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * SciPurchaseMast entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCI_PURCHASE_MAST", schema = "SCIGENICS", uniqueConstraints = {})
public class SciPurchaseMast implements java.io.Serializable {

	// Fields

	private Long seqPurchId;
	private String vendorOrder;
	private String vendorAddress;
	private String updatedBy;
	private Date updatedDate;
	private Date purchaseCreatedDt;
	private Date purchaseDueDate;
	private Long purchaseStatus;
	private SciVendorMaster sciVendorMaster;
	private Set<SciPurchaseItemdetails> sciPurchaseItemdetailses = new HashSet<SciPurchaseItemdetails>(
			0);
	private String specialCondition;
	private Float totalCost;
	private String modeOfDispatch;
	private String insurance;
	private String paymentTerms;
	private String deliverySchedule;
	private String supportDocuments;
	private String purchaseType;
	private Double freigntCharges;
	private Double vatCharges;
	private Double exciseCharges;
	private Double excisePercent;
	private Double vatPercentage;
	private Double itemTotalCost;
	private Double poTotalcost;
	private Double packingFrwdCharges;
	
	private String remarksPO;
	private String refundStatement;
	private Date refDate;
	private String qutRefNo;
	private Date piDate;
	private String subContVendor;
	private Double gst;
	private Double gstCharges;
	private Double creditTimeline;
	private String poPaidFully;
	private Date poPaidDate;
	private Date vendorAgreedDate;
	public Float workCost;
	public Integer paymentPercentage;
	private String certRequired;

	private String certStatus;

	private String workOrders;
	private String vendorStatus;
	private Long dcVendorId;
	// Constructors

	@Column(name = "GST_CHARGES", precision = 11, scale = 2)
	public Double getGstCharges() {
		return gstCharges;
	}

	public void setGstCharges(Double gstCharges) {
		this.gstCharges = gstCharges;
	}

	@Column(name = "GST", precision = 5, scale = 2)
	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	/** default constructor */
	public SciPurchaseMast() {
	}

	/** minimal constructor */
	public SciPurchaseMast(Long seqPurchId, String vendorOrder,
			String vendorAddress, Date updatedDate, Date purchaseCreatedDt,
			Date purchaseDueDate, Long purchaseStatus) {
		this.seqPurchId = seqPurchId;
		this.vendorOrder = vendorOrder;
		this.vendorAddress = vendorAddress;
		this.updatedDate = updatedDate;
		this.purchaseCreatedDt = purchaseCreatedDt;
		this.purchaseDueDate = purchaseDueDate;
		this.purchaseStatus = purchaseStatus;
	}

	/** full constructor */
	public SciPurchaseMast(Long seqPurchId, String vendorOrder,
			String vendorAddress, String updatedBy, Date updatedDate,
			Date purchaseCreatedDt, Date purchaseDueDate, Long purchaseStatus,
			Set<SciPurchaseItemdetails> sciPurchaseItemdetailses) {
		this.seqPurchId = seqPurchId;
		this.vendorOrder = vendorOrder;
		this.vendorAddress = vendorAddress;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.purchaseCreatedDt = purchaseCreatedDt;
		this.purchaseDueDate = purchaseDueDate;
		this.purchaseStatus = purchaseStatus;
		this.sciPurchaseItemdetailses = sciPurchaseItemdetailses;
	}

	// Property accessors
	@Id
	@Column(name = "SEQ_PURCH_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_PURCHASE_MAST_SEQ")
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_PURCHASE_MAST_SEQ" ,name="SCI_PURCHASE_MAST_SEQ" )
	public Long getSeqPurchId() {
		return this.seqPurchId;
	}

	public void setSeqPurchId(Long seqPurchId) {
		this.seqPurchId = seqPurchId;
	}

	@Column(name = "VENDOR_ORDER", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getVendorOrder() {
		return this.vendorOrder;
	}

	public void setVendorOrder(String vendorOrder) {
		this.vendorOrder = vendorOrder;
	}

	@Column(name = "VENDOR_ADDRESS", unique = false, nullable = true, insertable = true, updatable = true, length = 1000)
	public String getVendorAddress() {
		return this.vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	@Column(name = "UPDATED_BY", unique = false, nullable = true, insertable = true, updatable = true, length = 20)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PURCHASE_CREATED_DT", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPurchaseCreatedDt() {
		return this.purchaseCreatedDt;
	}

	public void setPurchaseCreatedDt(Date purchaseCreatedDt) {
		this.purchaseCreatedDt = purchaseCreatedDt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PURCHASE_DUE_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getPurchaseDueDate() {
		return this.purchaseDueDate;
	}

	public void setPurchaseDueDate(Date purchaseDueDate) {
		this.purchaseDueDate = purchaseDueDate;
	}

	@Column(name = "PURCHASE_STATUS", unique = false, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	public Long getPurchaseStatus() {
		return this.purchaseStatus;
	}

	public void setPurchaseStatus(Long purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "sciPurchaseMast")
	public Set<SciPurchaseItemdetails> getSciPurchaseItemdetailses() {
		return this.sciPurchaseItemdetailses;
	}
	

	public void setSciPurchaseItemdetailses(
			Set<SciPurchaseItemdetails> sciPurchaseItemdetailses) {
		this.sciPurchaseItemdetailses = sciPurchaseItemdetailses;
	}

	
	public void addItemDetail(SciPurchaseItemdetails details)
			 {
		this.sciPurchaseItemdetailses.add(details);
	}
	
	
	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_VENDOR_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public SciVendorMaster getSciVendorMaster() {
		return sciVendorMaster;
	}

	public void setSciVendorMaster(SciVendorMaster sciVendorMaster) {
		this.sciVendorMaster = sciVendorMaster;
	}

	@Column(name = "SPECIAL_CONDITION", length = 1000)
	public String getSpecialCondition() {
		return this.specialCondition;
	}

	public void setSpecialCondition(String specialCondition) {
		this.specialCondition = specialCondition;
	}

	@Column(name = "TOTAL_COST", precision = 22, scale = 0)
	public Float getTotalCost() {
		return this.totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	@Column(name = "MODE_OF_DISPATCH", length = 50)
	public String getModeOfDispatch() {
		return this.modeOfDispatch;
	}

	public void setModeOfDispatch(String modeOfDispatch) {
		this.modeOfDispatch = modeOfDispatch;
	}

	@Column(name = "INSURANCE", length = 50)
	public String getInsurance() {
		return this.insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	@Column(name = "PAYMENT_TERMS", length = 100)
	public String getPaymentTerms() {
		return this.paymentTerms;
	}

	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}

	@Column(name = "DELIVERY_SCHEDULE", length = 100)
	public String getDeliverySchedule() {
		return this.deliverySchedule;
	}

	public void setDeliverySchedule(String deliverySchedule) {
		this.deliverySchedule = deliverySchedule;
	}

	@Column(name = "SUPPORT_DOCUMENTS", length = 500)
	public String getSupportDocuments() {
		return this.supportDocuments;
	}

	public void setSupportDocuments(String supportDocuments) {
		this.supportDocuments = supportDocuments;
	}
	@Column(name = "PURCHASE_TYPE", length = 100)
	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}
	
private String insertedBy;
	
	private Date insertedDate;
	@Column(name = "INSERTED_BY", length = 20)
	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INSERTED_DATE", nullable = true, length = 7)
	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	@Column(name = "FRIEGHT_CHARGES", precision = 11, scale = 2)
	public Double getFreigntCharges() {
		return freigntCharges;
	}

	public void setFreigntCharges(Double freigntCharges) {
		this.freigntCharges = freigntCharges;
	}

	@Column(name = "VAT_CHARGES", precision = 11, scale = 2)
	public Double getVatCharges() {
		return vatCharges;
	}

	public void setVatCharges(Double vatCharges) {
		this.vatCharges = vatCharges;
	}

	@Column(name = "EXCISE_CHARGES", precision = 11, scale = 2)
	public Double getExciseCharges() {
		return exciseCharges;
	}

	public void setExciseCharges(Double exciseCharges) {
		this.exciseCharges = exciseCharges;
	}
	@Column(name = "EXCISE_PERCENTAGE", precision = 5, scale = 2)
	public Double getExcisePercent() {
		return excisePercent;
	}

	public void setExcisePercent(Double excisePercent) {
		this.excisePercent = excisePercent;
	}
	@Column(name = "VAT_PERCENTAGE", precision = 5, scale = 2)
	public Double getVatPercentage() {
		return vatPercentage;
	}

	public void setVatPercentage(Double vatPercentage) {
		this.vatPercentage = vatPercentage;
	}
	@Column(name = "ITEM_TOTAL_COST", precision = 11, scale = 2)
	public Double getItemTotalCost() {
		return itemTotalCost;
	}

	public void setItemTotalCost(Double itemTotalCost) {
		this.itemTotalCost = itemTotalCost;
	}
	@Column(name = "PO_TOTAL_COST", precision = 11, scale = 2)
	public Double getPoTotalcost() {
		return poTotalcost;
	}

	public void setPoTotalcost(Double poTotalcost) {
		this.poTotalcost = poTotalcost;
	}
	@Column(name = "PACKING_FRWD_CHARGES", precision = 11, scale = 2)
	public Double getPackingFrwdCharges() {
		return packingFrwdCharges;
	}

	public void setPackingFrwdCharges(Double packingFrwdCharges) {
		this.packingFrwdCharges = packingFrwdCharges;
	}
	@Column(name = "REMARKS_PO", length = 1000)
	public String getRemarksPO() {
		return remarksPO;
	}
	
	public void setRemarksPO(String remarksPO) {
		this.remarksPO = remarksPO;
	}
	@Column(name = "REFUND_STATEMENT", length = 100)
	public String getRefundStatement() {
		return refundStatement;
	}

	public void setRefundStatement(String refundStatement) {
		this.refundStatement = refundStatement;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "REF_DATE", nullable = true, length = 7)
	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}
	@Column(name = "QUT_REF_NO", length = 50)
	public String getQutRefNo() {
		return qutRefNo;
	}

	public void setQutRefNo(String qutRefNo) {
		this.qutRefNo = qutRefNo;
	}

	@Column(name = "SUBCONT_VENDOR", length = 200)
	public String getSubContVendor() {
		return subContVendor;
	}

	public void setSubContVendor(String subContVendor) {
		this.subContVendor = subContVendor;
	}

	@Column(name = "CREDIT_TIMELINE", precision = 5, scale = 2)
	public Double getCreditTimeline() {
		return creditTimeline;
	}

	public void setCreditTimeline(Double creditTimeline) {
		this.creditTimeline = creditTimeline;
	}

	@Column(name = "PURCHASE_PAID_FULLY", length = 20)
	public String getPoPaidFully() {
		return poPaidFully;
	}

	public void setPoPaidFully(String poPaidFully) {
		this.poPaidFully = poPaidFully;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PURCHASE_PAID_DATE", nullable = true, length = 7)
	public Date getPoPaidDate() {
		return poPaidDate;
	}

	public void setPoPaidDate(Date poPaidDate) {
		this.poPaidDate = poPaidDate;
	}

	@Column(name = "PURCHASE_PARTPAYMENT", precision =3, scale = 0)
	public Integer getPaymentPercentage() {
		return paymentPercentage;
	}

	public void setPaymentPercentage(Integer paymentPercentage) {
		this.paymentPercentage = paymentPercentage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PI_DATE", nullable = true, length = 7)
	public Date getPiDate() {
		return piDate;
	}

	public void setPiDate(Date piDate) {
		this.piDate = piDate;
	}

	@Transient
	public Float getWorkCost() {
		return workCost;
	}

	public void setWorkCost(Float workCost) {
		this.workCost = workCost;
	}

	@Column(name ="CERT_REQUIRED" ,length = 20)
	public String getCertRequired() {
		return certRequired;
	}

	public void setCertRequired(String certRequired) {
		this.certRequired = certRequired;
	}

	@Column(name = "CERTIFICATE_STATUS")
	public String getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(String certStatus) {
		this.certStatus = certStatus;
	}

	@Transient
	public String getWorkOrders() {
		return workOrders;
	}

	public void setWorkOrders(String workOrders) {
		this.workOrders = workOrders;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "VENDOR_AGREED_DATE", nullable = true, length = 7)
	public Date getVendorAgreedDate() {
		return vendorAgreedDate;
	}

	public void setVendorAgreedDate(Date vendorAgreedDate) {
		this.vendorAgreedDate = vendorAgreedDate;
	}

	@Column(name = "VENDOR_STATUS",length = 50)
	public String getVendorStatus() {
		return vendorStatus;
	}

	public void setVendorStatus(String vendorStatus) {
		this.vendorStatus = vendorStatus;
	}

	@Column(name = "DC_VENDOR_ID", precision = 22, scale = 0)
	public Long getDcVendorId() {
		return dcVendorId;
	}

	public void setDcVendorId(Long dcVendorId) {
		this.dcVendorId = dcVendorId;
	}
}