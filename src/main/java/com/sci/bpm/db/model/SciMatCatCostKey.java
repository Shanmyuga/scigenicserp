package com.sci.bpm.db.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SciMatCatCostKey implements Serializable {
    private String matcatCode;
    private String matDept;
    private Long seqWorkId;

    @Basic
    @Column(name = "MATCAT_CODE")
    public String getMatcatCode() {
        return matcatCode;
    }

    public void setMatcatCode(String matcatCode) {
        this.matcatCode = matcatCode;
    }

    @Basic
    @Column(name = "MAT_DEPT")
    public String getMatDept() {
        return matDept;
    }

    public void setMatDept(String matDept) {
        this.matDept = matDept;
    }

    @Basic
    @Column(name = "SEQ_WORK_ID")
    public Long getSeqWorkId() {
        return seqWorkId;
    }

    public void setSeqWorkId(Long seqWorkId) {
        this.seqWorkId = seqWorkId;
    }
}
