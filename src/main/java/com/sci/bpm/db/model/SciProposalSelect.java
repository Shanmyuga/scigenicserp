package com.sci.bpm.db.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SCI_PROPOSAL_SELECTED_ITEMS", schema = "SCIGENICS", catalog = "")
public class SciProposalSelect  implements Serializable  {

    private Long seqPropSelectId;
    private Long seqPropItemId;

    private Long seqPropMasterId;

    @Id
    @Column(name = "SEQ_PROP_SEL_ITEM", nullable = false, precision = 0)
    @SequenceGenerator(allocationSize=1, sequenceName="SCI_PROPOSAL_SELECT_SEQ", name="SCI_PROPOSAL_SELECT_SEQ" )
    @GeneratedValue(generator = "SCI_PROPOSAL_SELECT_SEQ")
    public Long getSeqPropSelectId() {
        return seqPropSelectId;
    }

    public void setSeqPropSelectId(Long seqPropSelectId) {
        this.seqPropSelectId = seqPropSelectId;
    }
    @Column(name = "SEQ_PROP_ITEM_ID", nullable = false, precision = 0)
    public Long getSeqPropItemId() {
        return seqPropItemId;
    }

    public void setSeqPropItemId(Long seqPropItemId) {
        this.seqPropItemId = seqPropItemId;
    }
    @Column(name = "SEQ_PROP_MASTER_ID", nullable = false, precision = 0)
    public Long getSeqPropMasterId() {
        return seqPropMasterId;
    }

    public void setSeqPropMasterId(Long seqPropMasterId) {
        this.seqPropMasterId = seqPropMasterId;
    }
}
