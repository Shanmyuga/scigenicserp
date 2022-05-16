package com.sci.bpm.dao.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.db.model.SciProposalItemMaster;
import com.sci.bpm.db.model.SciProposalMasterEntity;
import com.sci.bpm.db.model.SciProposalSelect;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProposalDAOImpl implements ProposalDAO {

    @PersistenceContext
    private EntityManager em;
    @Override
    public void addProposal(SciProposalMasterEntity entity) {

        em.persist(entity);

    }

    @Override
    public List<SciProposalItemMaster> getProposalItems(List<Long> props) {
        Query query = em.createQuery("from SciProposalItemMaster where seqPropItemId in :inclList");
        query.setParameter("inclList",props);
        return query.getResultList();
    }

    @Override
    public List<SciProposalMasterEntity> viewProposal(ProposalCommand command) {


       List<SciProposalMasterEntity> list  = em.createQuery("from SciProposalMasterEntity").getResultList();
        return list;
    }



    @Override
    public void editProposal(SciProposalMasterEntity entity) {

    }

    @Override
    public void clearProposalItems(Long seqPropMasterId) {
        Query qry = em.createQuery("delete from SciProposalSelect where seqPropMasterId= :seqPropMasterId");
        qry.setParameter("seqPropMasterId",seqPropMasterId);
        qry.executeUpdate();
    }

    @Override
    public List<SciProposalItemMaster> getProposalItems() {
        return em.createQuery("from SciProposalItemMaster ").getResultList();
    }

    @Override
    public SciProposalMasterEntity findProposal(Long seqProposalId) {
        SciProposalMasterEntity  master =  em.find(SciProposalMasterEntity.class,seqProposalId);
        return master;
    }

    @Override
    public List<SciProposalSelect> loadProposalSelected(Long seqProposalId) {
        Query query = em.createQuery("from SciProposalSelect  where seqPropMasterId =:seqPropMasterId");
        query.setParameter("seqPropMasterId",seqProposalId);

        return  query.getResultList();
    }

    @Override
    public void addProposalSelect(List<SciProposalSelect> selects) {
        for(SciProposalSelect select : selects) {
            em.persist(select);
        }
    }
}
