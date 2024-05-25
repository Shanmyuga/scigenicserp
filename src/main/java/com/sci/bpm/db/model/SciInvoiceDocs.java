package com.sci.bpm.db.model;


import javax.persistence.*;
import java.util.Date;

/**
 * SciJdrDocs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCI_INVOICE_DOCS", schema = "SCIGENICS")
public class SciInvoiceDocs implements java.io.Serializable {

	// Fields

	private Long seqInvoiceDocId;
	private SciVendorInvoiceMaster vendorInvoiceMaster;
	private String invoiceDesc;
	private byte[] invoiceDoc;
	private String invoiceDocName;
	private String updatedBy;
	private Date updatedDate;
	private String docVersion;
	private String originalDoc;
	private Long docType;



	private String docCnttype;
	// Constructors

	/** default constructor */
	public SciInvoiceDocs() {
	}



	// Property accessors
	@Id
	@Column(name = "SEQ_INVOICE_DOC_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_INVOICE_DOC_SEQ")
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_INVOICE_DOC_SEQ" ,name="SCI_INVOICE_DOC_SEQ" )
	public Long getSeqInvoiceDocId() {
		return seqInvoiceDocId;
	}

	public void setSeqInvoiceDocId(Long seqInvoiceDocId) {
		this.seqInvoiceDocId = seqInvoiceDocId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQ_INOVICE_ID", nullable = false)
	public SciVendorInvoiceMaster getVendorInvoiceMaster() {
		return vendorInvoiceMaster;
	}

	public void setVendorInvoiceMaster(SciVendorInvoiceMaster vendorInvoiceMaster) {
		this.vendorInvoiceMaster = vendorInvoiceMaster;
	}

	@Column(name = "INVOICE_DESC", length = 100)

	public String getInvoiceDesc() {
		return invoiceDesc;
	}

	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}




	@Column(name = "INVOICE_DOC_NAME", nullable = false, length = 100)
	public String getInvoiceDocName() {
		return invoiceDocName;
	}

	public void setInvoiceDocName(String invoiceDocName) {
		this.invoiceDocName = invoiceDocName;
	}



	@Lob
	@Column(name = "INVOICE_DOC", nullable = false)
	public byte[] getInvoiceDoc() {
		return invoiceDoc;
	}

	public void setInvoiceDoc(byte[] invoiceDoc) {
		this.invoiceDoc = invoiceDoc;
	}
	
	@Column(name = "UPDATED_BY", length = 20)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE", length = 7)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "DOC_VERSION", length = 3)
	public String getDocVersion() {
		return this.docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}
	@Column(name = "ORIGINAL_DOC", nullable = true, length = 100)
	public String getOriginalDoc() {
		return originalDoc;
	}

	public void setOriginalDoc(String originalDoc) {
		this.originalDoc = originalDoc;
	}
	@Column(name = "DOC_CNTTYPE", nullable = true, length = 200)
	public String getDocCnttype() {
		return docCnttype;
	}

	public void setDocCnttype(String docCnttype) {
		this.docCnttype = docCnttype;
	}
	@Column(name = "DOC_TYPE", nullable = true, length = 20)
	public Long getDocType() {
		return docType;
	}

	public void setDocType(Long docType) {
		this.docType = docType;
	}

}