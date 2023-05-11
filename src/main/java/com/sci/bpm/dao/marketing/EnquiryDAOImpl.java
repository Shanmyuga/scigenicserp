package com.sci.bpm.dao.marketing;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sci.bpm.db.model.SciEnquiryDocs;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sci.bpm.command.marketing.EnqBean;
import com.sci.bpm.db.model.SciEnquiryDetails;
import com.sci.bpm.db.model.SciEnquiryMaster;

@Repository
public class EnquiryDAOImpl implements EnquiryDAO {

    @PersistenceContext
    private EntityManager em;

    public void addNewEnqMaster(SciEnquiryMaster master) {
        // TODO Auto-generated method stub
        em.persist(master);
    }

    @Override
    public void addNewEnquiryDocs(List<SciEnquiryDocs> docs) {
        for(SciEnquiryDocs doc:docs) {
            em.persist(doc);
        }
    }

    @Override
    public SciEnquiryDetails addNewEnquiryActionDetail(SciEnquiryDetails details) {
        em.persist(details);
        System.out.println(details.getSeqEnqDetId());
        return  details;
    }

    public void addNewEnquiryDetail(SciEnquiryDetails details,SciEnquiryMaster master
                                     ) {
        em.persist(details);

    }

    public List loadEnquiryDetails(SciEnquiryMaster master) {

        Query qry = em.createQuery("from SciEnquiryDetails where sciEnquiryMaster =:emaster");
        qry.setParameter("emaster", master);
        return qry.getResultList();
    }

    public List loadOpenEnquiry(EnqBean command) {


        String query = "select  m from SciEnquiryMaster m  ";
        // Query query = em.createQuery("Select * from SciMatindMaster m ");
        Map parameters = new HashMap();
        String whereClause = "where";

        if (command.getEnqStatus() != null && !"".equals(command.getEnqStatus())) {
            whereClause = whereClause + " and m.enqStatus = :enqStatus ";
            parameters.put("enqStatus", command.getEnqStatus());
        }

        if (command.getInsertedBy() != null && !"".equals(command.getInsertedBy())) {
            whereClause = whereClause + " and m.insertedBy = :insertedBy ";
            parameters.put("insertedBy", command.getInsertedBy());
        }

        if (command.getEnqOrVisit() != null && !"".equals(command.getEnqOrVisit())) {
            whereClause = whereClause + " and m.enqOrVisit = :enqOrVisit ";
            parameters.put("enqOrVisit", command.getEnqOrVisit());
        }
        if (command.getSeqCustomerId() != null) {
            whereClause = whereClause + " and m.sciCustomerMaster.seqCustId = :seqCustomerId ";
            parameters.put("seqCustomerId", command.getSeqCustomerId());
        }

        if (command.getSeqEnqMasterId() != null) {
            whereClause = whereClause + " and m.seqEnqryId = :seqEnqryId ";
            parameters.put("seqEnqryId", command.getSeqEnqMasterId());
        }
        if (command.getEnqCreateFromDate() != null) {
            whereClause = whereClause + " and m.insertedDate > :fromDate ";
            parameters.put("fromDate", command.getEnqCreateFromDate());
        }
        if (command.getEnqCreateToDate() != null) {
            whereClause = whereClause + " and m.insertedDate < :toDate ";
            parameters.put("toDate", command.getEnqCreateToDate());
        }
        if (command.getEnqPriority() != null) {
            whereClause = whereClause + " and m.enqPriority = :enqPriority ";
            parameters.put("enqPriority", command.getEnqPriority());
        }
        if (command.getEnqCategory() != null) {
            whereClause = whereClause + " and m.enqCategory = :enqCategory ";
            parameters.put("enqCategory", command.getEnqCategory());
        }
        if (command.getOrgCode() != null && !StringUtils.isEmpty(command.getOrgCode())) {
            whereClause = whereClause + " and m.enqOrgCode = :enqOrgCode ";
            parameters.put("enqOrgCode", command.getOrgCode());
        }
        if (command.getStateCode() != null && !StringUtils.isEmpty(command.getStateCode())) {
            whereClause = whereClause + " and SUBSTRING(m.enqStateCityCode,1,2) = :enqStateCityCode ";
            parameters.put("enqStateCityCode", command.getStateCode());
        }

        if (command.getEnqFullCode() != null && !StringUtils.isEmpty(command.getEnqFullCode())) {
            whereClause = whereClause + " and enqFullCode = :enqFullCode ";
            parameters.put("enqFullCode", command.getEnqFullCode());
        }
        if (command.getCustomerCityCode() != null && !StringUtils.isEmpty(command.getCustomerCityCode())) {
            whereClause = whereClause + " and m.enqStateCityCode = :enqCityCode ";
            parameters.put("enqCityCode", command.getCustomerCityCode());
        }
        if (command.getStateCodeDelimited() != null ) {
            whereClause = whereClause + " and SUBSTRING(m.enqStateCityCode,1,2) IN  :enqStateCodeDelimited ";
            parameters.put("enqStateCodeDelimited", command.getStateCodeDelimited());
        }
        Query wquery = null;
        if (parameters.size() > 0) {
            wquery = em.createQuery(query
                    + whereClause.replaceAll("where and", "where"));
        } else {
            System.out.println("myquery " + query);
            wquery = em.createQuery(query);
        }
        Iterator keyset = parameters.keySet().iterator();

        while (keyset.hasNext()) {
            String key = (String) keyset.next();
            wquery.setParameter(key, parameters.get(key));
        }
wquery.setMaxResults(1000);
        List<SciEnquiryMaster> enqList = wquery.getResultList();
        for(SciEnquiryMaster sciEnquiryMaster:enqList) {

        Set<SciEnquiryDetails> treeSet = new TreeSet<SciEnquiryDetails>(new DateSortComparator());
        treeSet.addAll(sciEnquiryMaster.getSciEnquiryDetailses());
        if(!treeSet.isEmpty()) {
            SciEnquiryDetails details = treeSet.stream().findFirst().get();
            sciEnquiryMaster.setActionDate(details.getActionDate());
            sciEnquiryMaster.setBringForwardDate(details.getBringForwardDate());
            sciEnquiryMaster.setLastUpdatedBy(details.getUpdatedBy());
            sciEnquiryMaster.setActionTaken(details.getActionTaken());
        }
        }
        return enqList;
    }

    @Override
    public SciEnquiryMaster loadEnquiryMaster(String enqFullId) {
        Query query = em.createQuery("from SciEnquiryMaster  em where em.enqFullCode=:enqFullCode");
        query.setParameter("enqFullCode",enqFullId);
       List<SciEnquiryMaster> masters =  query.getResultList();
       if(masters.size() > 0) {
           return masters.get(0);
       }
        return null;
    }

    public void closeEnquury(SciEnquiryMaster master) {
        em.merge(master);

    }

    public void updateEnquiryMaster(SciEnquiryMaster master) {
        // TODO Auto-generated method stub
        em.merge(master);
    }

    @Override
    public void addEnquiryDocMaster(SciEnquiryDocs enquiryDocs) {
        em.persist(enquiryDocs);
    }

    @Override
    public List loadEnquiryDocs(SciEnquiryMaster enquiryMaster) {
        Query qry = em.createQuery("from SciEnquiryDocs where seqEnqId =:emaster");
        qry.setParameter("emaster", enquiryMaster.getSeqEnqryId());
        return qry.getResultList();
    }

    @Override
    public List loadCustomerEnquiries(Long seqCustomerId) {
        Query qry = em.createQuery("from SciEnquiryMaster em where em.sciCustomerMaster.seqCustId =:seqCustId");
        qry.setParameter("seqCustId",seqCustomerId);
        return qry.getResultList();
    }

    @Override
    public Boolean checkEnquiryCodeExists(String enqCode) {
          Query qry = em.createQuery("from SciEnquiryMaster em where em.enqFullCode =:enqCode");
        qry.setParameter("enqCode",enqCode);;

        List<SciEnquiryMaster> enquiryMasters =  qry.getResultList();
        if(enquiryMasters.size() >0) {
            return true;
        }
        else  {
            return false;
        }
    }


    @Override
    public Long findEnqCode(String orgCode, String stateCode, String customerCode) {
        Long result = new Long(0);
        Query qry =  em.createNativeQuery("select nvl(max(enq_code),0)+1 from sci_enquiry_master where enq_customer_code =:customer_code and enq_statecity_code =:statecode and enq_org_code=:orgCode" );
        qry.setParameter("customer_code",customerCode);
        qry.setParameter("statecode",stateCode);
        qry.setParameter("orgCode",orgCode);

        List results = qry.getResultList();
        if(results.size() ==0 ) {
            return result;
        }
        else {
            result = ((BigDecimal) results.get(0)).longValue();

        }
        return result;
    }

}
