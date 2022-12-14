package com.sci.bpm.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "SCI_ACTIVE_WORKORDERS_REPORT", schema = "SCIGENICS")
public class SciActiveWorkordersReportEntity implements Serializable {
    private Long seqWorkId;
    private String jdrDesc;
    private String clientDesc;
    private String shortKey;

    @Id
    @Column(name = "SEQ_WORK_ID")
    public Long getSeqWorkId() {
        return seqWorkId;
    }

    public void setSeqWorkId(Long seqWorkId) {
        this.seqWorkId = seqWorkId;
    }

    @Basic
    @Column(name = "JDR_DESC")
    public String getJdrDesc() {
        return jdrDesc;
    }

    public void setJdrDesc(String jdrDesc) {
        this.jdrDesc = jdrDesc;
    }

    @Basic
    @Column(name = "CLIENT_DESC")
    public String getClientDesc() {
        return clientDesc;
    }

    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }

    @Basic
    @Column(name = "SHORT_KEY")
    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciActiveWorkordersReportEntity that = (SciActiveWorkordersReportEntity) o;
        return Objects.equals(seqWorkId, that.seqWorkId) &&
                Objects.equals(jdrDesc, that.jdrDesc) &&
                Objects.equals(clientDesc, that.clientDesc) &&
                Objects.equals(shortKey, that.shortKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqWorkId, jdrDesc, clientDesc, shortKey);
    }
}
