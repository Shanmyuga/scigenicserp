package com.sci.bpm.command.marketing;

import com.sci.bpm.command.mi.MatCollectionCommand;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProposalCommand implements Serializable{

    private String proposalName;

    private Long seqProposalId;

    private Long[] seqPropItemId;
    private String propRemarks;

    private String enquiryID;
    private String proposalClient;

    private Long selectItem;
    private Long unSelectItem;

    private String  productCat ;
    private String  productType ;
    private String productSpec;
    private String productDept;

    private List<MatCollectionCommand> matList = new ArrayList();

    public Long[] getSeqPropItemId() {
        return seqPropItemId;
    }

    public void setSeqPropItemId(Long[] seqPropItemId) {
        this.seqPropItemId = seqPropItemId;
    }

    public String getEnquiryID() {
        return enquiryID;
    }

    public void setEnquiryID(String enquiryID) {
        this.enquiryID = enquiryID;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public String getPropRemarks() {
        return propRemarks;
    }

    public void setPropRemarks(String propRemarks) {
        this.propRemarks = propRemarks;
    }

    public String getProposalClient() {
        return proposalClient;
    }

    public void setProposalClient(String proposalClient) {
        this.proposalClient = proposalClient;
    }

    public Long getSeqProposalId() {
        return seqProposalId;
    }

    public void setSeqProposalId(Long seqProposalId) {
        this.seqProposalId = seqProposalId;
    }



    public List<MatCollectionCommand> getMatList() {
        return matList;
    }

    public void setMatList(List<MatCollectionCommand> matList) {
        this.matList = matList;
    }

    public Long getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(Long selectItem) {
        this.selectItem = selectItem;
    }

    public Long getUnSelectItem() {
        return unSelectItem;
    }

    public void setUnSelectItem(Long unSelectItem) {
        this.unSelectItem = unSelectItem;
    }

    public String getProductDept() {
        return productDept;
    }

    public void setProductDept(String productDept) {
        this.productDept = productDept;
    }

    public String getProductCat() {
        return productCat;
    }

    public void setProductCat(String productCat) {
        this.productCat = productCat;
    }

    public String getProductType() {
        return productType;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public ProposalCommand() {
        super();
        for (int idx = 0; idx < 11; idx++) {
            this.matList.add(new MatCollectionCommand());
        }
    }
}
