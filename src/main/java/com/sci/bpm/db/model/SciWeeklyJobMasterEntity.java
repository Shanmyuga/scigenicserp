package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "SCI_WEEKLY_JOB_MASTER", schema = "SCIGENICS", catalog = "")
public class SciWeeklyJobMasterEntity {
    private long seqWeekScId;
    private String jobId;
    private String jobDescription;
    private Date creationDate;
    private long jobStatusId;
    private String jobAssignedTo;
    private long jobEstimationHours;
    private Date insertedDate;
    private String insertedBy;
    private String updatedBy;
    private Date updatedDate;
    private String jobPhase;

    @Id
    @Column(name = "SEQ_WEEK_SC_ID")
    public long getSeqWeekScId() {
        return seqWeekScId;
    }

    public void setSeqWeekScId(long seqWeekScId) {
        this.seqWeekScId = seqWeekScId;
    }

    @Basic
    @Column(name = "JOB_ID")
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Basic
    @Column(name = "JOB_DESCRIPTION")
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "CREATION_DATE")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "JOB_STATUS_ID")
    public long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(long jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    @Basic
    @Column(name = "JOB_ASSIGNED_TO")
    public String getJobAssignedTo() {
        return jobAssignedTo;
    }

    public void setJobAssignedTo(String jobAssignedTo) {
        this.jobAssignedTo = jobAssignedTo;
    }

    @Basic
    @Column(name = "JOB_ESTIMATION_HOURS")
    public long getJobEstimationHours() {
        return jobEstimationHours;
    }

    public void setJobEstimationHours(long jobEstimationHours) {
        this.jobEstimationHours = jobEstimationHours;
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

    @Basic
    @Column(name = "JOB_PHASE")
    public String getJobPhase() {
        return jobPhase;
    }

    public void setJobPhase(String jobPhase) {
        this.jobPhase = jobPhase;
    }


}
