package com.sci.bpm.controller.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.command.mi.MatCollectionCommand;
import com.sci.bpm.command.mi.MatindCommand;
import com.sci.bpm.controller.base.DatePropertyEditorRegistrar;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.db.model.SciMatindMaster;
import com.sci.bpm.db.model.SciProposalDetailsEntity;
import com.sci.bpm.db.model.SciProposalMasterEntity;
import com.sci.bpm.service.marketing.ProposalService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller("propcont")
public class ProposalController extends SciBaseController {

    @Autowired
    private ProposalService propService;

    public Event addProposal(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        List<SciMatindMaster> mis = (List) context.getFlowScope().get("propMIList");
        SciProposalMasterEntity master = new SciProposalMasterEntity();
        master.setInsertedBy(getUserPreferences().getUserID());
        master.setInsertedDate(new Date());
        master.setProposalClient(command.getProposalClient());
        master.setProposalRemarks(command.getPropRemarks());
        master.setProposalName(command.getProposalName());
        master.setUpdatedDate(new Date());
        master.setUpdatedBy(getUserPreferences().getUserID());
       List<MatCollectionCommand> matcols =  command.getMatList();
       int idx = 0;
        for(SciMatindMaster mi:mis) {
            SciProposalDetailsEntity detail = new SciProposalDetailsEntity();
            MatCollectionCommand matcol =  matcols.get(idx);
            detail.setUpdatedDate(new Date());
            if(StringUtils.isNotBlank(matcol.getMatEstUnitCost())) {
                detail.setMatCost(new Float(matcol.getMatEstUnitCost()));
            }
            else {
                detail.setMatCost(mi.getTempunitCost());
            }
            if(matcol.getMatQty() == null || matcol.getMatQty().floatValue() ==0) {
                detail.setQuantity(new Float(1));
            }
            else {
                detail.setMatCost(matcol.getMatQty().floatValue());
            }
            detail.setUpdatedBy(getUserPreferences().getUserID());
            detail.setInsertedDate(new Date());
            detail.setInsertedBy(getUserPreferences().getUserID());
            detail.setMatCode(mi.getMatcode());
            detail.setMatSpec(mi.getMatSpec());

            detail.setSeqMiId(mi.getSeqMiId());
            detail.setMasterEntity(master);
            master.getSciProposalDetailsBySeqPropId().add(detail);

        }
        propService.addProposal(master);
        return success();
    }

    public Event searchProp(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        SciProposalMasterEntity master = propService.findProposal(command.getSeqProposalId());
        context.getFlowScope().put("propsDetails",master.getSciProposalDetailsBySeqPropId());
        context.getFlowScope().put("propsMaster",master);

        return success();
    }


    public Event editProps(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        SciProposalMasterEntity master = propService.findProposal(command.getSeqProposalId());


        context.getFlowScope().put("propsDetails",master.getSciProposalDetailsBySeqPropId());
        context.getFlowScope().put("propsMaster",master);

        return success();
    }
    @Override
    public Event setupForm(RequestContext context) throws Exception {
        setFormObjectClass(ProposalCommand.class);
        setFormObjectName("proposalbean");
        setPropertyEditorRegistrar(new DatePropertyEditorRegistrar());

        return super.setupForm(context);
    }

    @Override
    public Event bind(RequestContext context) throws Exception {
        // TODO Auto-generated method stub
        super.bind(context);
        return success();
    }

}
