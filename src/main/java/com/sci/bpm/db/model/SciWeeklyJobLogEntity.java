package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "SCI_WEEKLY_JOB_LOG", schema = "SCIGENICS", catalog = "")
public class SciWeeklyJobLogEntity {
    private long seqWkJbLogId;
    private String logDesc;
    private Date insertedDate;
    private String insertedBy;

    @Id
    @Column(name = "SEQ_WK_JB_LOG_ID")
    public long getSeqWkJbLogId() {
        return seqWkJbLogId;
    }

    public void setSeqWkJbLogId(long seqWkJbLogId) {
        this.seqWkJbLogId = seqWkJbLogId;
    }

    @Basic
    @Column(name = "LOG_DESC")
    public String getLogDesc() {
        return logDesc;
    }

    public void setLogDesc(String logDesc) {
        this.logDesc = logDesc;
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
