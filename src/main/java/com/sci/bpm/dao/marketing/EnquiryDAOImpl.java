package com.sci.bpm.dao.marketing;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.sci.bpm.db.model.SciEnquiryDocs;
import com.sci.bpm.util.QueryBuilderUtil;
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
        QueryBuilderUtil.DynamicQueryBuilder builder = QueryBuilderUtil.createBuilder("select m from SciEnquiryMaster m where 1=1");

        builder.addStringCondition(command.getEnqStatus(), " and m.enqStatus = :enqStatus", "enqStatus")
               .addStringCondition(command.getInsertedBy(), " and m.insertedBy = :insertedBy", "insertedBy")
               .addCondition(" and m.sciCustomerMaster.seqCustId = :seqCustomerId", "seqCustomerId", command.getSeqCustomerId())
               .addCondition(" and m.seqEnqryId = :seqEnqryId", "seqEnqryId", command.getSeqEnqMasterId())
               .addCondition(" and m.insertedDate > :fromDate", "fromDate", command.getEnqCreateFromDate())
               .addCondition(" and m.insertedDate < :toDate", "toDate", command.getEnqCreateToDate())
               .addCondition(" and m.enqPriority = :enqPriority", "enqPriority", command.getEnqPriority())
               .addCondition(" and m.enqCategory = :enqCategory", "enqCategory", command.getEnqCategory())
               .addStringCondition(command.getOrgCode(), " and m.enqOrgCode = :enqOrgCode", "enqOrgCode")
               .addStringCondition(command.getStateCode(), " and SUBSTRING(m.enqStateCityCode,1,2) = :enqStateCityCode", "enqStateCityCode")
               .addStringCondition(command.getEnqFullCode(), " and enqFullCode = :enqFullCode", "enqFullCode")
               .addStringCondition(command.getCustomerCityCode(), " and m.enqStateCityCode = :enqCityCode", "enqCityCode")
               .addCondition(" and SUBSTRING(m.enqStateCityCode,1,2) IN :enqStateCodeDelimited", "enqStateCodeDelimited", command.getStateCodeDelimited());

        Query wquery = builder.buildQuery(em);
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
