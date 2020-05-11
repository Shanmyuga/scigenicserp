package com.sci.bpm.db.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "SCI_MI_MATERIAL_ADDINFO")
public class SciMiMaterialAddinfoEntity implements java.io.Serializable {
    private long seqMatAddinfoId;
    private Time insertedDate;
    private String insertedBy;
    private SciMatindMaster seqMiId;
    private String addInfoLabel;
    private String addInfoValue;

    @Id
    @Column(name = "SEQ_MAT_ADDINFO_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCI_ADD_MAT_INFO_ENTITY_SEQ")
    @SequenceGenerator(sequenceName = "SCI_ADD_MAT_INFO_ENTITY_SEQ", allocationSize = 1, name = "SCI_ADD_MAT_INFO_ENTITY_SEQ")
    public long getSeqMatAddinfoId() {
        return seqMatAddinfoId;
    }

    public void setSeqMatAddinfoId(long seqMatAddinfoId) {
        this.seqMatAddinfoId = seqMatAddinfoId;
    }

    @Basic
    @Column(name = "INSERTED_DATE")
    public Time getInsertedDate() {
        return insertedDate;
    }

    public void setInsertedDate(Time insertedDate) {
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seq_mi_id")
    public SciMatindMaster getSeqMiId() {
        return seqMiId;
    }

    public void setSeqMiId(SciMatindMaster seqMiId) {
        this.seqMiId = seqMiId;
    }

    @Basic
    @Column(name = "ADD_INFO_LABEL")
    public String getAddInfoLabel() {
        return addInfoLabel;
    }

    public void setAddInfoLabel(String addInfoLabel) {
        this.addInfoLabel = addInfoLabel;
    }

    @Basic
    @Column(name = "ADD_INFO_VALUE")
    public String getAddInfoValue() {
        return addInfoValue;
    }

    public void setAddInfoValue(String addInfoValue) {
        this.addInfoValue = addInfoValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciMiMaterialAddinfoEntity that = (SciMiMaterialAddinfoEntity) o;
        return seqMatAddinfoId == that.seqMatAddinfoId &&
                seqMiId == that.seqMiId &&
                Objects.equals(insertedDate, that.insertedDate) &&
                Objects.equals(insertedBy, that.insertedBy) &&
                Objects.equals(addInfoLabel, that.addInfoLabel) &&
                Objects.equals(addInfoValue, that.addInfoValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seqMatAddinfoId, insertedDate, insertedBy, seqMiId, addInfoLabel, addInfoValue);
    }
}
