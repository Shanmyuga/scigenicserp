package com.sci.bpm.db.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Table(name = "SCI_SHORT_WORKORDERS", schema = "SCIGENICS", uniqueConstraints = {})
@Immutable

public class ActiveWorkOrderView {


	private String shortKey;

	private Long seqWorkId;


	@Column(name="SHORT_KEY")
	public String getShortKey() {
		return shortKey;
	}

	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}
	@Id
	@Column(name="SEQ_WORK_ID")
	public Long getSeqWorkId() {
		return seqWorkId;
	}

	public void setSeqWorkId(Long seqWorkId) {
		this.seqWorkId = seqWorkId;
	}
}
