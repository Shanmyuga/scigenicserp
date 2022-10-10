package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "SCI_SPRINT_JOB_DETAILS", schema = "SCIGENICS", catalog = "")
public class SciSprintJobDetailsEntity {
    private long seqSprintJobId;
    private long seqSprintNo;
    private long seqJobId;
    private Date insertedDate;
    private String insertedBy;

    @Id
    @Column(name = "SEQ_SPRINT_JOB_ID")
    public long getSeqSprintJobId() {
        return seqSprintJobId;
    }

    public void setSeqSprintJobId(long seqSprintJobId) {
        this.seqSprintJobId = seqSprintJobId;
    }

    @Basic
    @Column(name = "SEQ_SPRINT_NO")
    public long getSeqSprintNo() {
        return seqSprintNo;
    }

    public void setSeqSprintNo(long seqSprintNo) {
        this.seqSprintNo = seqSprintNo;
    }

    @Basic
    @Column(name = "SEQ_JOB_ID")
    public long getSeqJobId() {
        return seqJobId;
    }

    public void setSeqJobId(long seqJobId) {
        this.seqJobId = seqJobId;
    }

    @Basic
    @Column(name = "INSERTED_DATE")
    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }

    @Basic
    @Column(name = "INSERTED_BY")
    public String getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(String insertedBy) {
        this.insertedBy = insertedBy;
    }


}
