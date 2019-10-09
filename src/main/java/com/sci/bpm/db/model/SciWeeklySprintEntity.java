package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "SCI_WEEKLY_SPRINT", schema = "SCIGENICS", catalog = "")
public class SciWeeklySprintEntity {
    private long seqSprintId;
    private String sprintNo;
    private Date sprintStartDate;
    private Date sprintEndDate;
    private String sprintStatus;
    private String insertedBy;
    private Date insertedDate;
    private String updatedBy;
    private Date updatedDate;

    @Id
    @Column(name = "SEQ_SPRINT_ID")
    public long getSeqSprintId() {
        return seqSprintId;
    }

    public void setSeqSprintId(long seqSprintId) {
        this.seqSprintId = seqSprintId;
    }

    @Basic
    @Column(name = "SPRINT_NO")
    public String getSprintNo() {
        return sprintNo;
    }

    public void setSprintNo(String sprintNo) {
        this.sprintNo = sprintNo;
    }

    @Basic
    @Column(name = "SPRINT_START_DATE")
    public Date getSprintStartDate() {
        return sprintStartDate;
    }

    public void setSprintStartDate(Date sprintStartDate) {
        this.sprintStartDate = sprintStartDate;
    }

    @Basic
    @Column(name = "SPRINT_END_DATE")
    public Date getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(Date sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    @Basic
    @Column(name = "SPRINT_STATUS")
    public String getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }

    @Basic
    @Column(name = "INSERTED_BY")
    public String getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(String insertedBy) {
        this.insertedBy = insertedBy;
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
    @Column(name = "UPDATED_BY")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Basic
    @Column(name = "UPDATED_DATE")
    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }


}
