package com.sci.bpm.dao.lookup;

import java.io.IOException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sci.bpm.db.model.*;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LookupValueDAOImpl implements LookupValueDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	private Properties lookupQueries;
	public LookupValueDAOImpl() {
		super();
		lookupQueries = new Properties();
		try {
			lookupQueries.load(this.getClass().getResourceAsStream("lookupvalueQueries.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	public List<SciStateCityMasterEntity> loadCities(String stateCode) {

		Query qry  = em.createQuery("Select cm from SciStateCityMasterEntity cm where cm.stateCode = :stateCode");
		qry.setParameter("stateCode",stateCode);
		return  qry.getResultList();

	}

	public List getDropDownList(String querykey) {
		
		String query = lookupQueries.getProperty(querykey);
	
		return em.createQuery(query).getResultList();
	}

	public List<SciCustomerMaster> loadCustomerforOrg(Long seqClientOrgId) {
		Query qry = em.createQuery("from SciCustomerMaster cm where cm.sciClientOrgMaster.seqClientOrgId=:seqClientOrgId");
		qry.setParameter("seqClientOrgId",seqClientOrgId);

		return qry.getResultList();
	}

	@Override
	public List<SciCustomerMaster> loadCustomerforOrgandState(Long seqClientOrgId, Long stateCode) {
		String query = "Select cm from SciCustomerMaster cm  where ";
		System.out.println(stateCode + " - " + seqClientOrgId);
		Map parameters = new HashMap();
		String whereClause = "";
		if(seqClientOrgId != null) {
			whereClause = whereClause + "and cm.sciClientOrgMaster.seqClientOrgId=:seqClientOrgId ";
			parameters.put("seqClientOrgId",seqClientOrgId);
		}
		if(stateCode != null) {
			whereClause = whereClause + "and cm.customerState=:stateCode ";
			parameters.put("stateCode",stateCode);
		}
		if (!"".equals(whereClause)) {
			whereClause = whereClause.substring(3,whereClause.length());
		}
		Query wquery = em.createQuery(query+whereClause);

		Iterator keyset = parameters.keySet().iterator();
		while (keyset.hasNext()) {
			String key = (String) keyset.next();
			wquery.setParameter(key, parameters.get(key));
		}
		List<SciCustomerMaster> customerMasterList = wquery.getResultList();


		return customerMasterList;
	}


	public boolean addNewValue(SciLookupMaster master) {
		em.persist(master);
		return true;
	}

	public List<SciClientOrgMaster> loadOrgNames() {
		return em.createQuery("from SciClientOrgMaster em").getResultList();
	}


	public Long lookupId(String lovname) {
		Query q = em.createQuery("select seqLovId from SciLookupMaster where lovName =:lovn");
		q.setParameter("lovn",lovname);
		
		return (Long)q.getSingleResult();
	}



	public Map loadDesc() {
		List<SciLookupMaster> querylist = em.createQuery("from SciLookupMaster").getResultList();
		Map lookupmap = new HashMap<Long, String>();
		for(SciLookupMaster lk:querylist) {
			lookupmap.put(lk.getSeqLovId(),lk.getLovDescription());
		}
		return lookupmap;
	}



	public boolean addNewItemValue(SciMasterItem master) {
		em.persist(master);
		return true;
	}



	public void addNewCustomer(SciCustomerMaster custmaster) {
		em.persist(custmaster);
		
	}

	public void addNewClientOrg(SciClientOrgMaster orgMaster) {
		em.persist(orgMaster);
	}


	public void addNewMatSpec(SciMatspecMaster specmaster) {
		// TODO Auto-generated method stub
		em.persist(specmaster);
	}



	public void addNewVendor(SciVendorMaster vendorMaster) {
		// TODO Auto-generated method stub
		em.persist(vendorMaster);
	}



	public void addNewReport(SciReportConfiguration reportConfig) {
		// TODO Auto-generated method stub
		em.persist(reportConfig);
	}



	public List<SciReportConfiguration> loadReports() {
		// TODO Auto-generated method stub
		Query qry = em.createQuery("from SciReportConfiguration");
		List<SciReportConfiguration> myresults = qry.getResultList();
		return myresults;
	}



	public void updateStatus(SciReportConfiguration config) {
		// TODO Auto-generated method stub
		em.merge(config);
	}

	@Override
	public void updateVendor(SciVendorMaster vendorMaster) {
		em.merge(vendorMaster);
	}

	@Override
	public SciVendorMaster loadVendor(Long seqVendorId) {
		return em.find(SciVendorMaster.class,seqVendorId);
	}

	@Override
	public void updateCustomer(SciCustomerMaster customerMaster) {
		em.merge(customerMaster);
	}

	public void updateCLientOrg(SciClientOrgMaster clientOrg) {
		em.merge(clientOrg);
	}

	public String selectOrgCode() {
		return (String) em.createNativeQuery("select to_char(seq_org_code.nextval) from dual").getSingleResult();
	}

	public String selectCustomerCode(String seq_client_org_id) {
		Query qry = em.createNativeQuery("select to_char(nvl(max(CUSTOMER_CODE),0) +1,'00') from sci_customer_master where seq_client_org_id =:seq_client_org_id ");
		qry.setParameter("seq_client_org_id",seq_client_org_id);

		return (String) qry.getSingleResult();

	}

	@Override
	public String findOrgByOrgCode(String orgCode) {
		Query qry = em.createQuery("from SciClientOrgMaster where orgCode =:orgCode");
		qry.setParameter("orgCode",orgCode);
		SciClientOrgMaster clientOrgMaster = (SciClientOrgMaster) qry.getSingleResult();
		return clientOrgMaster.getOrgName();

	}

	@Override
	public SciCustomerMaster findCustomer(Long seqClientId) {
		Query qry = em.createQuery("from SciCustomerMaster where seqCustId =:seqCustId");
		qry.setParameter("seqCustId",seqClientId);
		return (SciCustomerMaster) qry.getSingleResult();
	}

}
