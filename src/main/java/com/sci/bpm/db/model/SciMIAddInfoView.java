package com.sci.bpm.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SCI_MI_ADDINFO_VIEWS", schema = "SCIGENICS", uniqueConstraints = {})
@Immutable
public class SciMIAddInfoView {

    private Long seqMIId;

    private String addInfo;


    @Id
    @Column(name = "SEQ_MI_ID")
    public Long getSeqMIId() {
        return seqMIId;
    }

    public void setSeqMIId(Long seqMIId) {
        this.seqMIId = seqMIId;
    }
    @Column(name="ADD_INFO")
    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
