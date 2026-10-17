package com.sci.bpm.db.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SciPurchaseDocs entity - documents attached to a purchase order.
 */
@Entity
@Table(name = "SCI_PURCHASE_DOCS", schema = "SCIGENICS")
public class SciPurchaseDocs implements java.io.Serializable {

	// Fields

	private Long seqPurchDocId;
	private Long seqPurchId;
	private String purchDocDesc;
	private byte[] purchDoc;
	private String purchDocName;
	private String updatedBy;
	private Date updatedDate;
	private String docVersion;
	private String originalDoc;
	private Long docType;
	private String docCnttype;

	// Constructors

	/** default constructor */
	public SciPurchaseDocs() {
	}

	// Property accessors
	@Id
	@Column(name = "SEQ_PURCH_DOC_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_PURCH_DOC_SEQ")
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_PURCH_DOC_SEQ" ,name="SCI_PURCH_DOC_SEQ" )
	public Long getSeqPurchDocId() {
		return this.seqPurchDocId;
	}

	public void setSeqPurchDocId(Long seqPurchDocId) {
		this.seqPurchDocId = seqPurchDocId;
	}

	@Column(name = "SEQ_PURCH_ID", nullable = false, precision = 22, scale = 0)
	public Long getSeqPurchId() {
		return this.seqPurchId;
	}

	public void setSeqPurchId(Long seqPurchId) {
		this.seqPurchId = seqPurchId;
	}

	@Column(name = "PURCH_DOC_DESC", length = 100)
	public String getPurchDocDesc() {
		return this.purchDocDesc;
	}

	public void setPurchDocDesc(String purchDocDesc) {
		this.purchDocDesc = purchDocDesc;
	}

	@Column(name = "PURCH_DOC_NAME", nullable = false, length = 100)
	public String getPurchDocName() {
		return this.purchDocName;
	}

	public void setPurchDocName(String purchDocName) {
		this.purchDocName = purchDocName;
	}

	@Lob
	@Column(name = "PURCH_DOC")
	public byte[] getPurchDoc() {
		return purchDoc;
	}

	public void setPurchDoc(byte[] purchDoc) {
		this.purchDoc = purchDoc;
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

	@Column(name = "DOC_TYPE", nullable = true, precision = 22, scale = 0)
	public Long getDocType() {
		return docType;
	}

	public void setDocType(Long docType) {
		this.docType = docType;
	}

}
