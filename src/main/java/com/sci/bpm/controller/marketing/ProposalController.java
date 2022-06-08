package com.sci.bpm.controller.marketing;

import com.sci.bpm.command.marketing.ProposalCommand;
import com.sci.bpm.command.mi.MatCollectionCommand;
import com.sci.bpm.command.mi.MatindCommand;
import com.sci.bpm.controller.base.DatePropertyEditorRegistrar;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.db.model.*;
import com.sci.bpm.service.lookup.LookUpValueService;
import com.sci.bpm.service.marketing.EnquiryService;
import com.sci.bpm.service.marketing.ProposalService;
import com.sci.bpm.service.mi.MaterialIndentService;
import com.sci.bpm.service.product.ProductMasterService;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller("propcont")
public class ProposalController extends SciBaseController {

    @Autowired
    private ProposalService propService;

    @Autowired
    private EnquiryService enquiryService;
    @Autowired
    private MaterialIndentService miservice;
    @Autowired
    private LookUpValueService lookUpValueService;

    @Autowired
    private ProductMasterService productMasterService;

    public Event addProposalItem(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        List<Long> selectIds = (List<Long>) context.getFlowScope().get("selectIds");
        if (selectIds != null) {

            selectIds.add(command.getSelectItem());
        } else {
            selectIds = new ArrayList<Long>();
            selectIds.add(command.getSelectItem());
        }
        context.getFlowScope().put("selectIds", selectIds);
        return success();
    }

    public Event removeProposalItem(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        List<Long> selectIds = (List<Long>) context.getFlowScope().get("selectIds");
        if (selectIds != null) {

            selectIds.remove(command.getSelectItem());
        }

        context.getFlowScope().put("selectIds", selectIds);
        return success();
    }

    public Event searchMIPricing(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
MatindCommand command1 = new MatindCommand();
command1.setMatDept(command.getProductDept());
command1.setProductCat(command.getProductCat());
command1.setMatCodeselected(command.getProductType()+command.getProductSpec());
       List<SciMatindMaster> matindMasterList =  miservice.loadLastPriced(command1);
       context.getFlowScope().put("matindMasterList",matindMasterList);
        return success();
    }

    public Event addProposalSelects(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        Long seqPropMasterId = command.getSeqProposalId();
        //  Long[] seqSelectsId = command.getSeqPropItemId();
        ArrayList<Long> selectedList = (ArrayList<Long>) context.getFlowScope().get("selectIds");
        List<SciProposalItemMaster> materialist = (List<SciProposalItemMaster>) context.getFlowScope().get("proposalItemMasterList");
        List<SciProposalSelect> selectsItems = (List<SciProposalSelect>) context.getFlowScope().get("selectsItems");

        List<Long> selectedProposalItemId = new ArrayList<Long>();
        int idx = 0;

        for (Long selectseqs : selectedList) {
            selectedProposalItemId.add(selectseqs);
        }
        List<SciProposalSelect> newSelect = new ArrayList<SciProposalSelect>();
        for (Long selectItems : selectedProposalItemId) {
            SciProposalSelect proposalSelect = new SciProposalSelect();
            proposalSelect.setSeqPropMasterId(seqPropMasterId);
            proposalSelect.setSeqPropItemId(selectItems);
            newSelect.add(proposalSelect);
        }
        propService.clearProposalItems(seqPropMasterId);
        propService.addProposalSelect(newSelect);
        loadPropItems(context);
        return success();
    }


    public Event addProposalOnly(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);

        SciProposalMasterEntity master = new SciProposalMasterEntity();
        master.setInsertedBy(getUserPreferences().getUserID());
        master.setInsertedDate(new Date());
        //master.setProposalClient(command.getProposalClient());
        master.setProposalRemarks(command.getPropRemarks());
        master.setProposalName(command.getProposalName());
        master.setUpdatedDate(new Date());
        master.setUpdatedBy(getUserPreferences().getUserID());
        master.setEnquiryId(command.getEnquiryID());
        SciEnquiryMaster enquiryMaster = enquiryService.checkEnquiryExists(command.getEnquiryID());
        if (enquiryMaster == null) {
            throw new Exception("Enquiry ID not valid");
        } else {
            master.setProposalOrg(lookUpValueService.findOrgByOrgCode(enquiryMaster.getEnqOrgCode()));
            master.setProposalClientName(enquiryMaster.getSciCustomerMaster().getCustomerContact());
        }
        List<MatCollectionCommand> matcols = command.getMatList();
        int idx = 0;

        propService.addProposal(master);
        return success();
    }

    public Event searchProp(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        SciProposalMasterEntity master = propService.findProposal(command.getSeqProposalId());
        context.getFlowScope().put("propsDetails", master.getSciProposalDetailsBySeqPropId());
        context.getFlowScope().put("propsMaster", master);

        return success();
    }

    public SciProposalItemMaster selectItemMaster(List<SciProposalItemMaster> datalist, Long seqPropItemId) {

        SciProposalItemMaster selectItem = null;
        for (SciProposalItemMaster data : datalist) {
            if (data.getSeqPropItemId().equals(seqPropItemId)) {

                selectItem = data;
                break;
            }
        }
        return selectItem;
    }

    public Event selectForPricing(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        List<SciProposalItemMaster> itemMasters = (List<SciProposalItemMaster>) context.getFlowScope().get("proposalItemMasterListSelected");

        SciProposalItemMaster master = selectItemMaster(itemMasters, command.getSelectItem());
        if (master == null) {
            throw new Exception("Select a ProposalItem");
        }
        String moc = master.getMocCategory();
        if (moc != null && moc.length() == 4) {
            String mocType = moc.substring(0, 2);
            List<SciMattypeMaster> matTypes = productMasterService.getMatType(mocType);
            SciMattypeMaster mattypeMaster = matTypes.get(0);
            command.setProductType(mattypeMaster.getMatCode());
            context.getFlowScope().put("matTypes", matTypes);
            SciMatcatMaster matcatMasters = productMasterService.getMatTypeCat(moc.substring(2, 4), mattypeMaster.getMatDept());
            command.setProductCat(matcatMasters.getMatcatCode());
            command.setProductDept(mattypeMaster.getMatDept());
            List<SciMatcatMaster> matcatMasterList = new ArrayList<SciMatcatMaster>();
            matcatMasterList.add(matcatMasters);
            context.getFlowScope().put("matCategorys", matcatMasterList);
            List<SciMatspecMaster> sciMatspecMasters = productMasterService.selectProducts(moc.substring(2, 4), mattypeMaster.getMatDept());
            context.getFlowScope().put("matspecs", sciMatspecMasters);

        } else {
            List<SciMattypeMaster> matTypes = productMasterService.selectProdType();
            context.getFlowScope().put("matTypes", matTypes);
        }

        return success();
    }


    public Event searchPricedMI(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        MatindCommand matindCommand = new MatindCommand();
        matindCommand.setMatDept(command.getProductDept());
        matindCommand.setMatCodeselected(command.getProductType()+command.getProductSpec());
        List<SciMatindMaster> matindMasterList = miservice.loadLastPriced(matindCommand);
        System.out.println(matindMasterList.size());
        context.getFlowScope().put("matindMasterList",matindMasterList);
        return success();
    }
    public Event loadPropItems(RequestContext context) throws Exception {

        ProposalCommand command = (ProposalCommand) getFormObject(context);
        Long seqProposalId = command.getSeqProposalId();
        List<SciProposalSelect> selects = propService.loadProposalSelected(seqProposalId);
        Long[] selectCheck = null;

        if (selects != null) {
            selectCheck = new Long[selects.size()];

            int idx = 0;
            for (SciProposalSelect select : selects) {
                selectCheck[idx] = select.getSeqPropItemId();
                idx++;
            }
            command.setSeqPropItemId(selectCheck);
        }

        List<SciProposalItemMaster> proposalItemMasterList = propService.getProposalItems();
        List<Long> selectIds = new ArrayList<Long>();
        List<SciProposalItemMaster> testList = new ArrayList<SciProposalItemMaster>();
        for (Long check : selectCheck) {
            selectIds.add(check);
        }

        List<SciProposalItemMaster> proposalItemMasterList1 = propService.getProposalItems(selectIds);

        context.getFlowScope().put("proposalItemMasterListSelected", proposalItemMasterList1);
        context.getFlowScope().put("selectsItems", selects);
        context.getFlowScope().put("proposalItemMasterList", proposalItemMasterList);
        if (selectCheck != null) {
            context.getFlowScope().put("selectIds", selectIds);
        }
        return success();
    }

    public Event editProps(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        SciProposalMasterEntity master = propService.findProposal(command.getSeqProposalId());


        context.getFlowScope().put("propsDetails", master.getSciProposalDetailsBySeqPropId());
        context.getFlowScope().put("propsMaster", master);

        return success();
    }

    public Event viewProps(RequestContext context) throws Exception {
        ProposalCommand command = (ProposalCommand) getFormObject(context);
        List<SciProposalMasterEntity> masters = propService.viewProposal(command);
        context.getFlowScope().put("proposals", masters);
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
