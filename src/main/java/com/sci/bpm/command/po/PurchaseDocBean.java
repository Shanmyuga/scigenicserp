package com.sci.bpm.command.po;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class PurchaseDocBean implements Serializable {

	private String purchDocDesc;
	private transient MultipartFile filedoc;
	private String purchDocName;
	private String docVersion;
	private Long docType;

	public String getPurchDocDesc() {
		return purchDocDesc;
	}

	public void setPurchDocDesc(String purchDocDesc) {
		this.purchDocDesc = purchDocDesc;
	}

	public MultipartFile getFiledoc() {
		return filedoc;
	}

	public void setFiledoc(MultipartFile filedoc) {
		this.filedoc = filedoc;
	}

	public String getPurchDocName() {
		return purchDocName;
	}

	public void setPurchDocName(String purchDocName) {
		this.purchDocName = purchDocName;
	}

	public String getDocVersion() {
		return docVersion;
	}

	public void setDocVersion(String docVersion) {
		this.docVersion = docVersion;
	}

	public Long getDocType() {
		return docType;
	}

	public void setDocType(Long docType) {
		this.docType = docType;
	}

}
