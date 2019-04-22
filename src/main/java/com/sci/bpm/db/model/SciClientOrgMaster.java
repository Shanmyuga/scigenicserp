package com.sci.bpm.db.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * SciCustomerMaster entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SCI_CLIENT_ORG_MASTER", schema = "SCIGENICS")
public class SciClientOrgMaster implements java.io.Serializable {

	// Fields

	private Long seqClientOrgId;
	private String orgName;
	private String orgAddress;

	private String insertedBy;

	private Date insertedDate;
	private String updatedBy;
	private Date updatedDate;


	private Set<SciCustomerMaster> sciCustomerMasters = new HashSet<SciCustomerMaster>(
			0);

	private String orgCity;
	@Id
	@Column(name = "SEQ_CLIENT_ORG_ID", unique = true, nullable = false, insertable = true, updatable = true, precision = 22, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "SCI_CLIENT_ORG_MASTER_SEQ")
	@SequenceGenerator(allocationSize=1, sequenceName="SCI_CLIENT_ORG_MASTER_SEQ" ,name="SCI_CLIENT_ORG_MASTER_SEQ" )
	public Long getSeqClientOrgId() {
		return seqClientOrgId;
	}

	public void setSeqClientOrgId(Long seqClientOrgId) {
		this.seqClientOrgId = seqClientOrgId;
	}

	@Column(name = "ORG_NAME", length = 500)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Column(name = "ORG_ADDRESS", length = 2000)
	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	@Column(name = "ORG_CITY", length = 500)
	public String getOrgCity() {
		return orgCity;
	}

	public void setOrgCity(String orgCity) {
		this.orgCity = orgCity;
	}

	@Column(name = "UPDATED_BY", unique = false, nullable = false, insertable = true, updatable = true, length = 20)
	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE", unique = false, nullable = false, insertable = true, updatable = true, length = 7)
	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name = "INSERTED_BY", length = 20)
	public String getInsertedBy() {
		return insertedBy;
	}

	public void setInsertedBy(String insertedBy) {
		this.insertedBy = insertedBy;
	}
	@Temporal(TemporalType.DATE)
	@Column(name = "INSERTED_DATE", nullable = true, length = 7)
	public Date getInsertedDate() {
		return insertedDate;
	}

	public void setInsertedDate(Date insertedDate) {
		this.insertedDate = insertedDate;
	}

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER, mappedBy = "sciClientOrgMaster")
	public Set<SciCustomerMaster> getSciCustomerMasters() {
		return sciCustomerMasters;
	}

	public void setSciCustomerMasters(Set<SciCustomerMaster> sciCustomerMasters) {
		this.sciCustomerMasters = sciCustomerMasters;
	}

}