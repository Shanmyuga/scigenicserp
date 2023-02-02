package com.sci.bpm.command.mi;

import java.io.Serializable;

public class ComponentDescription implements Serializable {

    private String serialNo;

    private String finishMI;

    private String description;

    private String dimension;

    private String quantity;

    private String unitPrice;

    private String totalPrice;

    private String miDrawingReference;

    private String miDeliveryDate;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getFinishMI() {
        return finishMI;
    }

    public void setFinishMI(String finishMI) {
        this.finishMI = finishMI;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMiDrawingReference() {
        return miDrawingReference;
    }

    public void setMiDrawingReference(String miDrawingReference) {
        this.miDrawingReference = miDrawingReference;
    }

    public String getMiDeliveryDate() {
        return miDeliveryDate;
    }

    public void setMiDeliveryDate(String miDeliveryDate) {
        this.miDeliveryDate = miDeliveryDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
