package com.sci.bpm.command.mi;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.web.multipart.MultipartFile;

public class MatCollectionCommand implements Serializable {
	
    private String  productCat ;
    private String  productType ;
	private Long seqprodID ;
	
	private BigDecimal matQty = null;
	private String matDuedatestr;
	private Date matDuedate;
	private String matDimesion = "NA";
	private String recommend;
	private String preparedBy;
	private String drawingRef;
	private String miForType;
	private String rawMis;
	private Float rawMIQty;

	private String rawMatDimension;

	private Long rawSeqVendorId;
	private String miPhase;
	private String matDesc;
	private String stageDesc;
	private String productSpecid;
	private String matDept;
	private String matindex;
	private String matSpec;
	private String matEstUnitCost;
	private String miForIssue;


	private String poMatAssign;

	private String rawMaterialDesc;

	private String unitOfMeasure;

	private Float unitPrice;

	private String moc;

	private String remarks;

	private String retDim;

	private Float retQty;
	private List<AdditionalInfoCommand> additionalInfoCommandList = new ArrayList();
	
	public String getMatEstUnitCost() {
		return matEstUnitCost;
	}
	public void setMatEstUnitCost(String matEstUnitCost) {
		this.matEstUnitCost = matEstUnitCost;
	}
	public String getProductCat() {
		return productCat;
	}
	public void setProductCat(String productCat) {
		this.productCat = productCat;
	}
	public Long getSeqprodID() {
		return seqprodID;
	}
	public void setSeqprodID(Long seqprodID) {
		this.seqprodID = seqprodID;
	}
	public BigDecimal getMatQty() {
		return matQty;
	}
	public void setMatQty(BigDecimal matQty) {
		this.matQty = matQty;
	}
	
	public String getMatDuedatestr() {
		return matDuedatestr;
	}
	public void setMatDuedatestr(String matDuedatestr) {
		this.matDuedatestr = matDuedatestr;
	}
	public String getMatDimesion() {
		return matDimesion;
	}
	public void setMatDimesion(String matDimesion) {
		this.matDimesion = matDimesion;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public String getPreparedBy() {
		return preparedBy;
	}
	public void setPreparedBy(String preparedBy) {
		this.preparedBy = preparedBy;
	}
	public String getDrawingRef() {
		return drawingRef;
	}
	public void setDrawingRef(String drawingRef) {
		this.drawingRef = drawingRef;
	}
	public String getMatDesc() {
		return matDesc;
	}
	public void setMatDesc(String matDesc) {
		this.matDesc = matDesc;
	}

	public Date getMatDuedate() throws ParseException {
	   SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	   if(!"".equals(matDuedatestr)) {
		   return format.parse(this.matDuedatestr);   
	   }
		return null;
	}
	public void setMatDuedate(Date matDuedate) {

		this.matDuedate = matDuedate;
	}
	
	
	public void reset() {
		this.seqprodID = null;
		this.matDesc = null;
		this.drawingRef = null;
		this.preparedBy = null;
		this.recommend = null;
		this.matDimesion = null;
		this.matQty = null;
		this.matDuedate = null;
		this.seqprodID = null;
		this.productCat = null;
		this.matDuedatestr = null;
		this.productType = null;
		this.matSpec = null;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getProductSpecid() {
		return productSpecid;
	}
	public void setProductSpecid(String productSpecid) {
		this.productSpecid = productSpecid;
	}
	public String getMatDept() {
		return matDept;
	}
	public void setMatDept(String matDept) {
		this.matDept = matDept;
	}
	public String getMatindex() {
		return matindex;
	}
	public void setMatindex(String matindex) {
		this.matindex = matindex;
	}
	public String getMatSpec() {
		return matSpec;
	}
	public void setMatSpec(String matSpec) {
		this.matSpec = matSpec;
	}
	public String getMiForType() {
		return miForType;
	}
	public void setMiForType(String miForType) {
		this.miForType = miForType;
	}

	public List<AdditionalInfoCommand> getAdditionalInfoCommandList() {
		return additionalInfoCommandList;
	}

	public void setAdditionalInfoCommandList(List<AdditionalInfoCommand> additionalInfoCommandList) {
		this.additionalInfoCommandList = additionalInfoCommandList;
	}

	public String getMiForIssue() {
		return miForIssue;
	}

	public void setMiForIssue(String miForIssue) {
		this.miForIssue = miForIssue;
	}

	public String getMiPhase() {
		return miPhase;
	}

	public void setMiPhase(String miPhase) {
		this.miPhase = miPhase;
	}


	public String getRawMis() {
		return rawMis;
	}

	public void setRawMis(String rawMis) {
		this.rawMis = rawMis;
	}

	public Float getRawMIQty() {
		return rawMIQty;
	}

	public void setRawMIQty(Float rawMIQty) {
		this.rawMIQty = rawMIQty;
	}

	public String getRawMatDimension() {
		return rawMatDimension;
	}

	public void setRawMatDimension(String rawMatDimension) {
		this.rawMatDimension = rawMatDimension;
	}

	public Long getRawSeqVendorId() {
		return rawSeqVendorId;
	}

	public void setRawSeqVendorId(Long rawSeqVendorId) {
		this.rawSeqVendorId = rawSeqVendorId;
	}

	public String getPoMatAssign() {
		return poMatAssign;
	}

	public void setPoMatAssign(String poMatAssign) {
		this.poMatAssign = poMatAssign;
	}

	public String getRawMaterialDesc() {
		return rawMaterialDesc;
	}

	public void setRawMaterialDesc(String rawMaterialDesc) {
		this.rawMaterialDesc = rawMaterialDesc;
	}

	public String getUnitOfMeasure() {
		return unitOfMeasure;
	}

	public void setUnitOfMeasure(String unitOfMeasure) {
		this.unitOfMeasure = unitOfMeasure;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getMoc() {
		return moc;
	}

	public void setMoc(String moc) {
		this.moc = moc;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRetDim() {
		return retDim;
	}

	public void setRetDim(String retDim) {
		this.retDim = retDim;
	}

	public Float getRetQty() {
		return retQty;
	}

	public void setRetQty(Float retQty) {
		this.retQty = retQty;
	}

	public String getStageDesc() {
		return stageDesc;
	}

	public void setStageDesc(String stageDesc) {
		this.stageDesc = stageDesc;
	}
}
