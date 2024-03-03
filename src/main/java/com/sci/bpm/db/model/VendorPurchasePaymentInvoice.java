package com.sci.bpm.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Date;

@Table(name = "VENDOR_PURCHASE_PAYMENT_INVOICE_VIEW" ,schema = "scigenics")
@Immutable
@Entity
public class VendorPurchasePaymentInvoice implements Serializable {



    @Column(name = "VENDOR_NAME", length = 100)
    private String vendorName;

    @Id
    @Column(name = "VENDOR_ID")
    private Long vendorId;
    @Id
    @Column(name = "PAYMENT_DATE")
    private Date paymentDate;
    @Id
    @Column(name = "PAYMENT_AMT", precision = 10, scale = 2)
    private BigDecimal paymentAmt;
    @Id
    @Column(name = "PAYMENT_REMARKS", length = 40)
    private String paymentRemarks;
    @Id
    @Column(name = "INVOICE_DATE")
    private Date invoiceDate;
    @Id
    @Column(name = "INVOICE_AMT", precision = 10, scale = 2)
    private BigDecimal invoiceAmt;
    @Id
    @Column(name = "INVOICE_BILL_ID")
    private String invoicePurchaseOrder;

    @Convert(disableConversion = true)
    @Column(name = "PURCHASE_DATE")
    private Date purchaseDate;
    @Id
    @Column(name = "PO_ID")
    private Integer poId;

    @Column(name = "PURCHASE_COST", precision = 10, scale = 2)
    private BigDecimal purchaseCost;

    public BigDecimal getPurchaseCost() {
        return purchaseCost;
    }

    public void setPurchaseCost(BigDecimal purchaseCost) {
        this.purchaseCost = purchaseCost;
    }

    public Integer getPoId() {
        return poId;
    }

    public void setPoId(Integer poId) {
        this.poId = poId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getInvoicePurchaseOrder() {
        return invoicePurchaseOrder;
    }

    public void setInvoicePurchaseOrder(String invoicePurchaseOrder) {
        this.invoicePurchaseOrder = invoicePurchaseOrder;
    }

    public BigDecimal getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(BigDecimal invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentRemarks() {
        return paymentRemarks;
    }

    public void setPaymentRemarks(String paymentRemarks) {
        this.paymentRemarks = paymentRemarks;
    }

    public BigDecimal getPaymentAmt() {
        return paymentAmt;
    }

    public void setPaymentAmt(BigDecimal paymentAmt) {
        this.paymentAmt = paymentAmt;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
}