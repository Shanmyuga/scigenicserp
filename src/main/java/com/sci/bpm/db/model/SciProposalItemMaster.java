package com.sci.bpm.db.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SCI_PROPOSAL_ITEMS", schema = "SCIGENICS", catalog = "")
public class SciProposalItemMaster implements Serializable {
    private Long seqPropItemId;
    private String schedule;

    private String type;
    private String description;

    private String mocCategory;
    private String insertedBy;

    private Date insertedDate;

    @Id
    @Column(name = "SEQ_PROP_ITEM_ID", nullable = false, precision = 0)
    public Long getSeqPropItemId() {
        return seqPropItemId;
    }

    public void setSeqPropItemId(Long seqPropItemId) {
        this.seqPropItemId = seqPropItemId;
    }

    @Column(name = "SCHEDULE", nullable = false)
    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @Column(name = "TYPE", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "DESCRIPTION", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "MOC_CATEGORY", nullable =true)
    public String getMocCategory() {
        return mocCategory;
    }

    public void setMocCategory(String mocCategory) {
        this.mocCategory = mocCategory;
    }
    @Column(name = "INSERTED_BY")
    public String getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(String insertedBy) {
        this.insertedBy = insertedBy;
    }
    @Column(name = "INSERTED_DATE", nullable = true)
    public Date getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Date insertedDate) {
        this.insertedDate = insertedDate;
    }
}
