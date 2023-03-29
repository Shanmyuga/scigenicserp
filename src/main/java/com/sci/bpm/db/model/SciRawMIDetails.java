package com.sci.bpm.db.model;

import javax.persistence.*;


@Entity
@Table(name = "SCI_RAW_MI_DETAILS", schema = "SCIGENICS", uniqueConstraints = {})
public class SciRawMIDetails implements java.io.Serializable {

	private Long seqRawMIid;
	
	private Long seqOrigMIID;
	
	private Long seqSubContMIID;

	private Float matQty;

	private String matDimension;

	private Long seqVendorId;

	private String vendorName;
	
	@Id
	@Column(name = "SEQ_RAW_MI_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 9, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_RAW_MI_SEQ")
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_RAW_MI_SEQ" ,name="SCI_RAW_MI_SEQ" )
	public Long getSeqRawMIid() {
		return seqRawMIid;
	}

	public void setSeqRawMIid(Long seqRawMIid) {
		this.seqRawMIid = seqRawMIid;
	}
	
	@Column(name = "SEQ_ORIG_MI_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 9, scale = 0)

	public Long getSeqOrigMIID() {
		return seqOrigMIID;
	}

	public void setSeqOrigMIID(Long seqOrigMIID) {
		this.seqOrigMIID = seqOrigMIID;
	}

	@Column(name = "SEQ_SUB_CONT_MI_ID", unique = false, nullable = false, insertable = true, updatable = true, precision = 9, scale = 0)
	public Long getSeqSubContMIID() {
		return seqSubContMIID;
	}

	public void setSeqSubContMIID(Long seqSubContMIID) {
		this.seqSubContMIID = seqSubContMIID;
	}
	@Column(name = "MAT_QTY", unique = false, nullable = true, insertable = true, updatable = true, precision = 9, scale = 2)
	public Float getMatQty() {
		return matQty;
	}

	public void setMatQty(Float matQty) {
		this.matQty = matQty;
	}
	@Column(name="MAT_DIMENSION", length=20)
	public String getMatDimension() {
		return matDimension;
	}

	public void setMatDimension(String matDimension) {
		this.matDimension = matDimension;
	}
	@Column(name = "SEQ_VENDOR_ID", unique = false, nullable = true, insertable = true, updatable = true, precision = 9, scale = 0)
	public Long getSeqVendorId() {
		return seqVendorId;
	}

	public void setSeqVendorId(Long seqVendorId) {
		this.seqVendorId = seqVendorId;
	}

	@Transient
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
