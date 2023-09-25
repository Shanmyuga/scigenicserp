package com.sci.bpm.db.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "SCI_MATCAT_WO_EST_COST", schema = "SCIGENICS", catalog = "SCIGENICS")
public class SciMatcatWoEstCostEntity {

    private String shortKey;
    private BigDecimal estimatedCost;
    private Integer count;

    @EmbeddedId
    private SciMatCatCostKey estCostkey;

    @Basic
    @Column(name = "SHORT_KEY")
    public String getShortKey() {
        return shortKey;
    }

    public void setShortKey(String shortKey) {
        this.shortKey = shortKey;
    }

    @Basic
    @Column(name = "ESTIMATED_COST")
    public BigDecimal getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(BigDecimal estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    @Basic
    @Column(name = "COUNT")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public SciMatCatCostKey getEstCostkey() {
        return estCostkey;
    }

    public void setEstCostkey(SciMatCatCostKey estCostkey) {
        this.estCostkey = estCostkey;
    }
}
