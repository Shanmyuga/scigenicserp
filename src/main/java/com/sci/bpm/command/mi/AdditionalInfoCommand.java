package com.sci.bpm.command.mi;

import org.springframework.web.multipart.MultipartFile;

public class AdditionalInfoCommand {

    private String additionalDetailText;


    private MultipartFile additionalFile;

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
}
