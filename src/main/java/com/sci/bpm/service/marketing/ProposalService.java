package com.sci.bpm.service.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.db.model.SciProposalItemMaster;
import com.sci.bpm.db.model.SciProposalMasterEntity;
import com.sci.bpm.db.model.SciProposalSelect;

import java.util.List;

public interface ProposalService {


    public void addProposal(SciProposalMasterEntity entity);

    public List<SciProposalMasterEntity> viewProposal(ProposalCommand command);

    public void editProposal(SciProposalMasterEntity entity);

    public List<SciProposalSelect> loadProposalSelected(Long seqProposalId);

    public List<SciProposalItemMaster> getProposalItems();
    public List<SciProposalItemMaster> getProposalItems(List<Long> props);
    public void clearProposalItems(Long seqPropMasterId);

    public void addProposalSelect(List<SciProposalSelect> selects);

    public SciProposalMasterEntity findProposal(Long seqProposalId);
}
