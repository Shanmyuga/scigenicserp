package com.sci.bpm.command.po;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="PurchaseOrder")
@XmlSeeAlso(RawMIDetails.class)
public class PurchaseOrder {
    public PurchaseOrder () {
    }

    private List<RawMIDetails> rawMIList = new ArrayList();


    private String vendorDetails;

    private String dcDate;

    private String purchaseNo;

    private String workOrderNo;

    private String fmtNo;

    private String sno;

    private String miId;

    private String miDesc;

    private String miDimen;

    private String miQty;

    private String miUom;

    private String unitCost;

    private String totalCost;

    private String drawingRef;

    private String delDate;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getMiId() {
        return miId;
    }

    public void setMiId(String miId) {
        this.miId = miId;
    }

    public String getMiDesc() {
        return miDesc;
    }

    public void setMiDesc(String miDesc) {
        this.miDesc = miDesc;
    }

    public String getMiDimen() {
        return miDimen;
    }

    public void setMiDimen(String miDimen) {
        this.miDimen = miDimen;
    }

    public String getMiQty() {
        return miQty;
    }

    public void setMiQty(String miQty) {
        this.miQty = miQty;
    }

    public String getMiUom() {
        return miUom;
    }

    public void setMiUom(String miUom) {
        this.miUom = miUom;
    }

    public String getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(String unitCost) {
        this.unitCost = unitCost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getDrawingRef() {
        return drawingRef;
    }

    public void setDrawingRef(String drawingRef) {
        this.drawingRef = drawingRef;
    }

    public String getDelDate() {
        return delDate;
    }

    public void setDelDate(String delDate) {
        this.delDate = delDate;
    }

    public String getVendorDetails() {
        return vendorDetails;
    }

    public void setVendorDetails(String vendorDetails) {
        this.vendorDetails = vendorDetails;
    }

    public String getDcDate() {
        return dcDate;
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    public String getPurchaseNo() {
        return purchaseNo;
    }

    public void setPurchaseNo(String purchaseNo) {
        this.purchaseNo = purchaseNo;
    }

    public String getWorkOrderNo() {
        return workOrderNo;
    }

    public void setWorkOrderNo(String workOrderNo) {
        this.workOrderNo = workOrderNo;
    }

    public String getFmtNo() {
        return fmtNo;
    }

    public void setFmtNo(String fmtNo) {
        this.fmtNo = fmtNo;
    }

    @XmlElementWrapper(name = "rawMIList")
    @XmlElement(name = "rawMI")
    public List getRawMIList() {
        return rawMIList;
    }

    public void setRawMIList(List rawMIList) {
        this.rawMIList = rawMIList;
    }

    public void addRawMi(RawMIDetails rawMIDetails) {
        this.rawMIList.add(rawMIDetails);
    }
}