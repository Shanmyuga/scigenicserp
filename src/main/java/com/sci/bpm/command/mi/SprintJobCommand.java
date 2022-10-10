package com.sci.bpm.command.mi;

import java.io.Serializable;
import java.sql.Date;

public class SprintJobCommand  implements Serializable {

    private String jobDescription;
    private Date creationDate;
    private long jobStatusId;
    private String jobAssignedTo;
    private long jobEstimationHours;
    private String jobPhase;

    private Long seqWorkId;

    private String comments;

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getJobStatusId() {
        return jobStatusId;
    }

    public void setJobStatusId(long jobStatusId) {
        this.jobStatusId = jobStatusId;
    }

    public String getJobAssignedTo() {
        return jobAssignedTo;
    }

    public void setJobAssignedTo(String jobAssignedTo) {
        this.jobAssignedTo = jobAssignedTo;
    }

    public long getJobEstimationHours() {
        return jobEstimationHours;
    }

    public void setJobEstimationHours(long jobEstimationHours) {
        this.jobEstimationHours = jobEstimationHours;
    }

    public String getJobPhase() {
        return jobPhase;
    }

    public void setJobPhase(String jobPhase) {
        this.jobPhase = jobPhase;
    }

    public Long getSeqWorkId() {
        return seqWorkId;
    }

    public void setSeqWorkId(Long seqWorkId) {
        this.seqWorkId = seqWorkId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
