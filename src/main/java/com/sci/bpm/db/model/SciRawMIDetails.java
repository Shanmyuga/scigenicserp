package com.sci.bpm.db.model;

import javax.persistence.*;


@Entity
@Table(name = "SCI_RAW_MI_DETAILS", schema = "SCIGENICS", uniqueConstraints = {})
public class SciRawMIDetails implements java.io.Serializable {

	private Long seqRawMIid;
	
	private SciMatindMaster rawMIMaster;
	
	private SciMatindMaster subcontractMIMaster;

	private Float matQty;

	private String matDimension;



	private String vendorName;

	private SciVendorMaster sciVendorMaster;

	private String rawMaterialDesc;

	private String unitOfMeasure;

	private Float unitPrice;

	private String moc;

	private String remarks;

	private String retDim;
	private String stageDesc;

	private Float retQty;
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

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_ORIG_MI_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public SciMatindMaster getRawMIMaster() {
		return rawMIMaster;
	}

	public void setRawMIMaster(SciMatindMaster rawMIMaster) {
		this.rawMIMaster = rawMIMaster;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_SUB_CONT_MI_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public SciMatindMaster getSubcontractMIMaster() {
		return subcontractMIMaster;
	}

	public void setSubcontractMIMaster(SciMatindMaster subcontractMIMaster) {
		this.subcontractMIMaster = subcontractMIMaster;
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



	@Column(name="RAW_MATERIAL_DESC", length=500)
	public String getRawMaterialDesc() {
		return rawMaterialDesc;
	}

	public void setRawMaterialDesc(String rawMaterialDesc) {
		this.rawMaterialDesc = rawMaterialDesc;
	}

	@Column(name="UNIT_OF_MEASURE", length=20)
	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}
	@Column(name = "DC_UNIT_COST", unique = false, nullable = true, insertable = true, updatable = true, precision = 9, scale = 2)

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name="MOC", length=20)
	public String getMoc() {
		return moc;
	}

	public void setMoc(String moc) {
		this.moc = moc;
	}

	@Column(name="REMARKS", length=20)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Transient
	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	@Column(name="RET_DIM", length=20)
	public String getRetDim() {
		return retDim;
	}

	public void setRetDim(String retDim) {
		this.retDim = retDim;
	}
	@Column(name = "RET_QTY", unique = false, nullable = true, insertable = true, updatable = true, precision = 9, scale = 2)
	public Float getRetQty() {
		return retQty;
	}

	public void setRetQty(Float retQty) {
		this.retQty = retQty;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEQ_VENDOR_ID", unique = false, nullable = false, insertable = true, updatable = true)
	public SciVendorMaster getSciVendorMaster() {
		return sciVendorMaster;
	}

	public void setSciVendorMaster(SciVendorMaster sciVendorMaster) {
		this.sciVendorMaster = sciVendorMaster;
	}

	@Column(name="STAGE_DESC", length=20)
	public String getStageDesc() {
		return stageDesc;
	}

	public void setStageDesc(String stageDesc) {
		this.stageDesc = stageDesc;
	}
}
