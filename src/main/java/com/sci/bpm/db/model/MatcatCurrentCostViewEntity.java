package com.sci.bpm.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "MATCAT_CURRENT_COST_VIEW", schema = "SCIGENICS", catalog = "")
@Immutable

public class MatcatCurrentCostViewEntity {

    private BigDecimal currentCost;

    @EmbeddedId
    private EstCatCostKey estCatCostKey;


    @Basic
    @Column(name = "CURRENT_COST")
    public BigDecimal getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(BigDecimal currentCost) {
        this.currentCost = currentCost;
    }




}
