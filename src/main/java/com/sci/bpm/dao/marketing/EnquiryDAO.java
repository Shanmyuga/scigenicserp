package com.sci.bpm.dao.marketing;

import java.util.List;

import com.sci.bpm.command.marketing.EnqBean;
import com.sci.bpm.db.model.SciEnquiryDetails;
import com.sci.bpm.db.model.SciEnquiryDocs;
import com.sci.bpm.db.model.SciEnquiryMaster;

public interface EnquiryDAO {
	public List loadOpenEnquiry(EnqBean bean);
	public SciEnquiryMaster loadEnquiryMaster(String enqFullId);

	public void addNewEnqMaster(SciEnquiryMaster master);
	public void addNewEnquiryDocs(List<SciEnquiryDocs> docs);
	public SciEnquiryDetails addNewEnquiryActionDetail(SciEnquiryDetails details);
	public void addNewEnquiryDetail(SciEnquiryDetails detail,SciEnquiryMaster master);
	
	public List loadEnquiryDetails(SciEnquiryMaster master);
	public void closeEnquury(SciEnquiryMaster master);
	
	public void updateEnquiryMaster(SciEnquiryMaster master);

	public void addEnquiryDocMaster(SciEnquiryDocs enquiryDocs);

	public List loadEnquiryDocs(SciEnquiryMaster enquiryMaster);

	public List loadCustomerEnquiries(Long seqCustomerId);
	public Boolean checkEnquiryCodeExists(String enqCode);
	public Long findEnqCode(String orgCode,String stateCode,String customerCode);



}
