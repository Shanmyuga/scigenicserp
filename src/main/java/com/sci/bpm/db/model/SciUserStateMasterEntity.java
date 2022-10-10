package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;
@Entity
@Table(name = "SCI_USER_STATE_MASTER", schema = "SCIGENICS")
public class SciUserStateMasterEntity {
    private long seqUserStateId;
    private long seqUserId;
    private String stateCode;
    private String insertedBy;
    private Date insertedDate;

    @Id
    @Column(name = "SEQ_USER_STATE_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_USER_STATE_MASTER_SEQ")
    @SequenceGenerator(allocationSize=1, sequenceName="SCI_USER_STATE_MASTER_SEQ" ,name="SCI_USER_STATE_MASTER_SEQ" )
    public long getSeqUserStateId() {
        return seqUserStateId;
    }

    public void setSeqUserStateId(long seqUserStateId) {
        this.seqUserStateId = seqUserStateId;
    }

    @Basic
    @Column(name = "SEQ_USER_ID")
    public long getSeqUserId() {
        return seqUserId;
    }

    public void setSeqUserId(long seqUserId) {
        this.seqUserId = seqUserId;
    }

    @Basic
    @Column(name = "STATE_CODE")
    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciUserStateMasterEntity that = (SciUserStateMasterEntity) o;
        return seqUserStateId == that.seqUserStateId &&
                seqUserId == that.seqUserId &&
                Objects.equals(stateCode, that.stateCode) &&
                Objects.equals(insertedBy, that.insertedBy) &&
                Objects.equals(insertedDate, that.insertedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqUserStateId, seqUserId, stateCode, insertedBy, insertedDate);
    }
}
