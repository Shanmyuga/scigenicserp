package com.sci.bpm.db.model;

import java.io.Serializable;

public class SciMIAdditionalInfoDTO implements Serializable {

    private String label;

    private String datatype;

    private String mandatory;

    private String[] dropValues;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getMandatory() {
        return mandatory;
    }

    public void setMandatory(String mandatory) {
        this.mandatory = mandatory;
    }

    public String[] getDropValues() {
        return dropValues;
    }

    public void setDropValues(String[] dropValues) {
        this.dropValues = dropValues;
    }
}
