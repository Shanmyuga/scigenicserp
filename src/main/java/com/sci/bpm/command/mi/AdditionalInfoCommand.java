package com.sci.bpm.command.mi;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class AdditionalInfoCommand implements Serializable {

    private String additionalDetailText;
    private String additionalInfoLabel;
    private String additionalInfoType;
    private String additionalInfoMandatory;
    private transient MultipartFile additionalFile;

    private String originalDocName;
    private String docType;
    private byte[] fileData;
    private String[] additionalDropValues;

    public String getAdditionalDetailText() {
        return additionalDetailText;
    }

    public void setAdditionalDetailText(String additionalDetailText) {
        this.additionalDetailText = additionalDetailText;
    }

    public MultipartFile getAdditionalFile() {
        return additionalFile;
    }

    public void setAdditionalFile(MultipartFile additionalFile) {
        this.additionalFile = additionalFile;
    }

    public String[] getAdditionalDropValues() {
        return additionalDropValues;
    }

    public void setAdditionalDropValues(String[] additionalDropValues) {
        this.additionalDropValues = additionalDropValues;
    }

    public String getAdditionalInfoLabel() {
        return additionalInfoLabel;
    }

    public void setAdditionalInfoLabel(String additionalInfoLabel) {
        this.additionalInfoLabel = additionalInfoLabel;
    }

    public String getAdditionalInfoType() {
        return additionalInfoType;
    }

    public void setAdditionalInfoType(String additionalInfoType) {
        this.additionalInfoType = additionalInfoType;
    }

    public String getAdditionalInfoMandatory() {
        return additionalInfoMandatory;
    }

    public void setAdditionalInfoMandatory(String additionalInfoMandatory) {
        this.additionalInfoMandatory = additionalInfoMandatory;
    }

    public String getOriginalDocName() {
        return originalDocName;
    }

    public void setOriginalDocName(String originalDocName) {
        this.originalDocName = originalDocName;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
