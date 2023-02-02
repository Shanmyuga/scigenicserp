package com.sci.bpm.command.mi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeliveryChallanDTO implements Serializable {

    private  String deliveryChellanNo;

    private String dcDate;

    private String purchaseNo;

    private String workOrderNo;

    private String vendorAddress;

    private String vendorName;

    private List<RawComponentDesc> rawComponentDescList = new ArrayList<RawComponentDesc>();
    private List<ComponentDescription> componentDescriptionList = new ArrayList<ComponentDescription>();

    public List<ComponentDescription> getComponentDescriptionList() {
        return componentDescriptionList;
    }

    public void setComponentDescriptionList(List<ComponentDescription> componentDescriptionList) {
        this.componentDescriptionList = componentDescriptionList;
    }

    public void addComponentDesc(ComponentDescription componentDescription) {
        this.componentDescriptionList.add(componentDescription);
    }
    public String getDeliveryChellanNo() {
        return deliveryChellanNo;
    }

    public void setDeliveryChellanNo(String deliveryChellanNo) {
        this.deliveryChellanNo = deliveryChellanNo;
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

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public List<RawComponentDesc> getRawComponentDescList() {
        return rawComponentDescList;
    }

    public void setRawComponentDescList(List<RawComponentDesc> rawComponentDescList) {
        this.rawComponentDescList = rawComponentDescList;
    }

    public void addRawComponentDesc(RawComponentDesc rawComponentDesc) {
        this.rawComponentDescList.add(rawComponentDesc);
    }
}
