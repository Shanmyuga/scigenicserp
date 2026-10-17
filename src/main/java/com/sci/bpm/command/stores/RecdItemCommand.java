package com.sci.bpm.command.stores;

import java.io.Serializable;

public class RecdItemCommand implements Serializable {

	private String matindex;
	private String recdCount;
	private String recdDimension;
	private String remarks;
	private String invoiceValue;

	public String getMatindex() {
		return matindex;
	}

	public void setMatindex(String matindex) {
		this.matindex = matindex;
	}

	public String getRecdCount() {
		return recdCount;
	}

	public void setRecdCount(String recdCount) {
		this.recdCount = recdCount;
	}

	public String getRecdDimension() {
		return recdDimension;
	}

	public void setRecdDimension(String recdDimension) {
		this.recdDimension = recdDimension;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getInvoiceValue() {
		return invoiceValue;
	}

	public void setInvoiceValue(String invoiceValue) {
		this.invoiceValue = invoiceValue;
	}
}
