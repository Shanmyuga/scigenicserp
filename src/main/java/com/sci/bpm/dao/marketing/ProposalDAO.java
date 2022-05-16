package com.sci.bpm.dao.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.db.model.SciProposalItemMaster;
import com.sci.bpm.db.model.SciProposalMasterEntity;
import com.sci.bpm.db.model.SciProposalSelect;

import java.util.List;

public interface ProposalDAO {

    public void addProposal(SciProposalMasterEntity entity);
    public List<SciProposalItemMaster> getProposalItems(List<Long> props);
    public List<SciProposalMasterEntity> viewProposal(ProposalCommand command);

    public void editProposal(SciProposalMasterEntity entity);
    public void clearProposalItems(Long seqPropMasterId);
    public List<SciProposalItemMaster> getProposalItems();

    public SciProposalMasterEntity findProposal(Long seqProposalId);

    public List<SciProposalSelect> loadProposalSelected(Long seqProposalId);

    public void addProposalSelect(List<SciProposalSelect> selects);
}
