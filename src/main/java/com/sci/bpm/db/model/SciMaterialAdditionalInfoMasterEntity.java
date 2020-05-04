package com.sci.bpm.db.model;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity(name = "SCI_MATERIAL_ADDINFO_MASTER")
@Table(name = "SCI_MATERIAL_ADDINFO_MASTER")
@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class SciMaterialAdditionalInfoMasterEntity {


    private SciMIAdditionalInfoDTO[] additionalInfo;

    private Long seqMaterialInfoId;

    private String materialCatType;

    @Id
    @Column(name = "SEQ_MAT_ADDINFO_MASTER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCI_ADD_MATERIAL_INFO_SEQ")
    @SequenceGenerator(sequenceName = "SCI_ADD_MATERIAL_INFO_SEQ", allocationSize = 1, name = "SCI_ADD_MATERIAL_INFO_SEQ")
    public Long getSeqMaterialInfoId() {
        return seqMaterialInfoId;
    }

    public void setSeqMaterialInfoId(Long seqMaterialInfoId) {
        this.seqMaterialInfoId = seqMaterialInfoId;
    }

    @Column(name = "MATCAT_TYPE")
    public String getMaterialCatType() {
        return materialCatType;
    }

    public void setMaterialCatType(String materialCatType) {
        this.materialCatType = materialCatType;
    }

    @Column(name = "MAT_DEPT")
    public String getMaterialCatDept() {
        return materialCatDept;
    }

    public void setMaterialCatDept(String materialCatDept) {
        this.materialCatDept = materialCatDept;
    }

    @Column(name = "MATCAT_CODE")
    public String getMaterialCatId() {
        return materialCatId;
    }

    public void setMaterialCatId(String materialCatId) {
        this.materialCatId = materialCatId;
    }

    @Column(name = "NUMBER_OF_SPECS")
    public Long getNumberOfSpecs() {
        return numberOfSpecs;
    }

    public void setNumberOfSpecs(Long numberOfSpecs) {
        this.numberOfSpecs = numberOfSpecs;
    }


    private String materialCatDept;
    private String materialCatId;
    private Long numberOfSpecs;

    @Type(type = "json")
    @Column(name = "ADDITIONAL_INFO")
    public SciMIAdditionalInfoDTO[] getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(SciMIAdditionalInfoDTO[] additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
