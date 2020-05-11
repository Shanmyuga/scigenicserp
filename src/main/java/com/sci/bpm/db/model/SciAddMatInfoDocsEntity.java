package com.sci.bpm.db.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "SCI_ADD_MAT_INFO_DOCS")
public class SciAddMatInfoDocsEntity implements Serializable {
    private long seqMiAddDocsId;
    private String originalDocName;
    private String docType;
    private SciMatindMaster seqMiId;

    private byte[] docData;

    private String addinfoLabel;
    private Time insertedDate;
    private String insertedBy;
    @Id
    @Column(name = "SEQ_MI_ADD_DOCS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MI_ADDINFO_DOC_SEQ")
    @SequenceGenerator(sequenceName = "SEQ_MI_ADDINFO_DOC_SEQ", allocationSize = 1, name = "SEQ_MI_ADDINFO_DOC_SEQ")
    public long getSeqMiAddDocsId() {
        return seqMiAddDocsId;
    }

    public void setSeqMiAddDocsId(Long seqMiAddDocsId) {
        this.seqMiAddDocsId = seqMiAddDocsId;
    }

    public void setSeqMiAddDocsId(long seqMiAddDocsId) {
        this.seqMiAddDocsId = seqMiAddDocsId;
    }

    @Basic
    @Column(name = "ORIGINAL_DOC_NAME")
    public String getOriginalDocName() {
        return originalDocName;
    }

    public void setOriginalDocName(String originalDocName) {
        this.originalDocName = originalDocName;
    }

    @Basic
    @Column(name = "DOC_TYPE")
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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
    @Column(name = "DOC_DATA")
    public byte[] getDocData() {
        return docData;
    }

    public void setDocData(byte[] docData) {
        this.docData = docData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SciAddMatInfoDocsEntity that = (SciAddMatInfoDocsEntity) o;
        return seqMiAddDocsId == that.seqMiAddDocsId &&
                seqMiId == that.seqMiId &&

                Objects.equals(originalDocName, that.originalDocName) &&
                Objects.equals(docType, that.docType) &&
                Arrays.equals(docData, that.docData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(seqMiAddDocsId, originalDocName, docType, seqMiId);
        result = 31 * result + Arrays.hashCode(docData);
        return result;
    }
    @Basic
    @Column(name = "ADDINFO_LABEL")
    public String getAddinfoLabel() {
        return addinfoLabel;
    }

    public void setAddinfoLabel(String addinfoLabel) {
        this.addinfoLabel = addinfoLabel;
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
}
