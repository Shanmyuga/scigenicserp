package com.sci.bpm.command.po;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * An MI of a purchase order that is not received, or only partly received.
 */
public class POPendingItemCommand implements Serializable {

	private String seqMiId;
	private String matCode;
	private String matSpec;
	private String matType;
	private BigDecimal ordQty;
	private BigDecimal recdQty;
	private BigDecimal pendingQty;
	private String ordDim;
	private String workorder;

	public String getSeqMiId() {
		return seqMiId;
	}

	public void setSeqMiId(String seqMiId) {
		this.seqMiId = seqMiId;
	}

	public String getMatCode() {
		return matCode;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	public String getMatSpec() {
		return matSpec;
	}

	public void setMatSpec(String matSpec) {
		this.matSpec = matSpec;
	}

	public String getMatType() {
		return matType;
	}

	public void setMatType(String matType) {
		this.matType = matType;
	}

	public BigDecimal getOrdQty() {
		return ordQty;
	}

	public void setOrdQty(BigDecimal ordQty) {
		this.ordQty = ordQty;
	}

	public BigDecimal getRecdQty() {
		return recdQty;
	}

	public void setRecdQty(BigDecimal recdQty) {
		this.recdQty = recdQty;
	}

	/**
	 * Pending count = MI quantity - received quantity.
	 */
	public BigDecimal getPendingQty() {
		if (ordQty != null) {
			BigDecimal recd = recdQty == null ? BigDecimal.ZERO : recdQty;
			return clean(ordQty.subtract(recd));
		}
		return clean(pendingQty);
	}

	public BigDecimal getOrdQtyDisplay() {
		return clean(ordQty);
	}

	public BigDecimal getRecdQtyDisplay() {
		return clean(recdQty == null ? BigDecimal.ZERO : recdQty);
	}

	// 10.0000 -> 10, 2.5000 -> 2.5
	private BigDecimal clean(BigDecimal value) {
		if (value == null) {
			return null;
		}
		BigDecimal stripped = value.stripTrailingZeros();
		return stripped.scale() < 0 ? stripped.setScale(0) : stripped;
	}

	public void setPendingQty(BigDecimal pendingQty) {
		this.pendingQty = pendingQty;
	}

	public String getOrdDim() {
		return ordDim;
	}

	public void setOrdDim(String ordDim) {
		this.ordDim = ordDim;
	}

	public String getWorkorder() {
		return workorder;
	}

	public void setWorkorder(String workorder) {
		this.workorder = workorder;
	}

	public String getStatus() {
		if (recdQty == null || recdQty.signum() == 0) {
			return "Not Received";
		}
		return "Partially Received";
	}

}
