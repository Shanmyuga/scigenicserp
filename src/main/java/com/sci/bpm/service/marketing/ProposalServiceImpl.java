package com.sci.bpm.service.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.dao.marketing.ProposalDAO;
import com.sci.bpm.db.model.SciProposalItemMaster;
import com.sci.bpm.db.model.SciProposalMasterEntity;
import com.sci.bpm.db.model.SciProposalSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
@Transactional
public class ProposalServiceImpl implements ProposalService {

    @Autowired
    private ProposalDAO propDao;
    @Override
    public void addProposal(SciProposalMasterEntity entity) {
        propDao.addProposal(entity);
    }

    @Override
    public List<SciProposalMasterEntity> viewProposal(ProposalCommand command) {

        return propDao.viewProposal(command);
    }

    @Override
    public void editProposal(SciProposalMasterEntity entity) {

    }

    @Override
    public List<SciProposalSelect> loadProposalSelected(Long seqProposalId) {
        return propDao.loadProposalSelected(seqProposalId);
    }

    @Override
    public List<SciProposalItemMaster> getProposalItems() {
        return propDao.getProposalItems();
    }

    @Override
    public List<SciProposalItemMaster> getProposalItems(List<Long> props) {
        return propDao.getProposalItems(props);
    }

    @Override
    public void clearProposalItems(Long seqPropMasterId) {
        propDao.clearProposalItems(seqPropMasterId);
    }

    @Override
    public void addProposalSelect(List<SciProposalSelect> selects) {
        propDao.addProposalSelect(selects);
    }

    @Override
    public SciProposalMasterEntity findProposal(Long seqProposalId) {
        return propDao.findProposal(seqProposalId);
    }
}
