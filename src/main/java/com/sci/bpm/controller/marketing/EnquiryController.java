package com.sci.bpm.controller.marketing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sci.bpm.command.LookupValueBean;
import com.sci.bpm.db.model.*;
import com.sci.bpm.service.lookup.LookUpValueService;
import com.sci.bpm.service.task.DiskWriterJob;
import com.sci.bpm.service.user.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import sun.swing.StringUIClientPropertyKey;

import com.sci.bpm.command.marketing.EnqBean;
import com.sci.bpm.command.marketing.JobDescBean;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.service.marketing.EnquiryService;

@Controller("enqcont")
public class EnquiryController extends SciBaseController {

	@Override
	public Event setupForm(RequestContext context) throws Exception {
		setFormObjectClass(EnqBean.class);
		setFormObjectName("enqbean");
		return super.setupForm(context);
	}

	@Autowired
	private EnquiryService service;
	@Autowired
	private UserService userService;
	@Autowired
	private LookUpValueService lservice;
	@Autowired
	private DiskWriterJob diskwriter;
	
	public Event loadEnquiryMaster(RequestContext context) throws Exception {
		EnqBean bean = (EnqBean) getFormObject(context);
		if("marketing".equals(getUserPreferences().getRoleName())) {
		ScigenicsUserMaster userMaster = 	userService.findUser(getUserPreferences().getUserID());
		List<SciUserStateMasterEntity> stateCodes = userService.getUserStates(userMaster.getSeqUserId());
		List<String> stateCodeStrings = new ArrayList<String>();

		for(SciUserStateMasterEntity entity : stateCodes) {
			stateCodeStrings.add(entity.getStateCode());


		}
		if(stateCodeStrings.size() > 0) {

			bean.setStateCodeDelimited(stateCodeStrings);
		}
			//bean.setInsertedBy(getUserPreferences().getUserID());
		}

		List mylist = service.loadOpenEnquiry(bean);
		context.getFlowScope().put("openenqlist", mylist);
	
		return success();
	}
	public Event selectClientOrg(RequestContext context) throws Exception {
		EnqBean value = (EnqBean) getFormObject(context);
		List<SciClientOrgMaster> clientOrgs = (List<SciClientOrgMaster>) context.getFlowScope().get("clientorglist");
		SciClientOrgMaster clientOrgMaster = filterClientOrg(clientOrgs, value.getOrgCode());
		if (clientOrgMaster != null) {
			List<SciCustomerMaster> customers = lservice.loadCustomerforOrg(clientOrgMaster.getSeqClientOrgId());

			context.getFlowScope().put("selectedClientOrg", clientOrgMaster);

		context.getFlowScope().put("selectedCustomers", customers);
		}
		return success();
	}

	private SciClientOrgMaster filterClientOrg(List<SciClientOrgMaster> master,String orgCode) {
		SciClientOrgMaster selected = null;
		for(SciClientOrgMaster m : master) {

			if(m.getOrgCode() != null && orgCode != null && m.getOrgCode().equals(orgCode)) {
				selected = m;
			}
		}

		return selected;
	}

	public Event loadCities(RequestContext context) throws Exception {
		EnqBean value = (EnqBean)getFormObject(context);
		List<SciStateCityMasterEntity> cityList = lservice.loadCities(value.getStateCode());

		context.getFlowScope().put("stateCityList",cityList);
		return success();
	}
	public Event addEnquiryMaster(RequestContext context) throws Exception {
		System.out.println("inside enquiry master");
		EnqBean bean = (EnqBean) getFormObject(context);
		SciEnquiryMaster emaster = new SciEnquiryMaster();
		BeanUtils.copyProperties(emaster, bean);
        SciClientOrgMaster clientOrgMaster = (SciClientOrgMaster) context.getFlowScope().get("selectedClientOrg");
		SciCustomerMaster cmaster = (SciCustomerMaster) context.getFlowScope().get("selectedCustomer");
		emaster.setSciCustomerMaster(cmaster);
		emaster.setEnqStatus("O");
		emaster.setInsertedBy(getUserPreferences().getUserID());
		emaster.setInsertedDate(new Date());
		emaster.setUdpatedBy(getUserPreferences().getUserID());
		emaster.setUpdatedDate(new Date());
		emaster.setEnqCustomerCode(cmaster.getCustomerCode());
		Long enqCode = service.findEnqCode(clientOrgMaster.getOrgCode(),cmaster.getCustomerCityCode(),cmaster.getCustomerCode());
		emaster.setEnquiryCode(enqCode);
		emaster.setEnqStateCityCode(cmaster.getCustomerCityCode());
		emaster.setEnqOrgCode(clientOrgMaster.getOrgCode());
		emaster.setEnqCustomerCode(cmaster.getCustomerCode());
		if(StringUtils.isNotBlank(clientOrgMaster.getOrgCode()) && StringUtils.isNotBlank(cmaster.getCustomerCityCode()) && StringUtils.isNotBlank(cmaster.getCustomerCode()) ) {
			emaster.setEnqFullCode(  cmaster.getCustomerCityCode() +clientOrgMaster.getOrgCode()+ cmaster.getCustomerCode()+String.valueOf(enqCode));
		}
		else {
			throw new Exception("Cannot generate fULL CODE");
		}
		if(StringUtils.isBlank(emaster.getEnqAttendee()) || emaster.getEnqDate() == null ) {
			throw new Exception();
		}
		boolean isTaskRequired = false;
		Long lovID = getLookupservice().loadIDData("ENQ_PRIORITY_HOT");
		if(lovID.longValue() == emaster.getEnqPriority().longValue()) {
			isTaskRequired = true;
		}

		service.addNewEnqMaster(emaster,isTaskRequired);
		
		
		return success();
	}
	
	private SciCustomerMaster selectCustom(List<SciCustomerMaster> master,Long seqcustomerID) {
		SciCustomerMaster selected = null;
		for(SciCustomerMaster m : master) {
			if(m.getSeqCustId().intValue() == seqcustomerID.intValue()) {
				selected = m;
			}
		}
		
		return selected;
	}
	private SciEnquiryMaster selectEnqmaster(List<SciEnquiryMaster> master,Long seqEnqID) {
		SciEnquiryMaster selected = null;
		for(SciEnquiryMaster m : master) {
			if(m.getSeqEnqryId().intValue() == seqEnqID.intValue()) {
				selected = m;
			}
		}
		
		return selected;
	}
	
	public Event loadEnquiryDetails(RequestContext context) throws Exception {
		EnqBean bean = (EnqBean) getFormObject(context);
		List enqmasterlist = (List) context.getFlowScope().get("openenqlist");
		SciEnquiryMaster emaster = selectEnqmaster(enqmasterlist, bean.getSeqenqmasterid());
		List detaillist = service.loadEnquiryDetails(emaster);

		List enqdoclist = service.loadEnquiryDocs(emaster);
		context.getFlowScope().put("openenqdetails", detaillist);
		context.getExternalContext().getSessionMap().put("enqdoclistSessiom", enqdoclist);
		context.getFlowScope().put("enqdoclist", enqdoclist);
		context.getFlowScope().put("selectEnq", emaster);
		return success();
	}
	
	public Event addEnquiryDetails(RequestContext context) throws Exception {
		EnqBean bean = (EnqBean) getFormObject(context);
		SciEnquiryMaster emaster  = (SciEnquiryMaster) context.getFlowScope().get("selectEnq");
		SciEnquiryDetails details = new SciEnquiryDetails();
		details.setActionDate(bean.getActionDate());
		details.setActionTaken(bean.getActionTaken());
		details.setSciEnquiryMaster(emaster);
		details.setUpdatedBy(getUserPreferences().getUserID());
		details.setUpdatedDate(new java.util.Date());
		details.setInsertedBy(getUserPreferences().getUserID());
		details.setCompetitors(bean.getCompetitors());
		details.setFollowupMethod(bean.getFollowupMethod());
		details.setInsertedDate(new java.util.Date());
		if(bean.getBringForwardDate() == null || StringUtils.isEmpty(bean.getPurpose())) {
			throw new Exception();
		}
		details.setBringForwardDate(bean.getBringForwardDate());
		details.setPurpose(bean.getPurpose());
		//emaster.setCloseDate(new Date());
		//emaster.setEnqStatus("C");
		emaster.setEnqType(bean.getEnqmasterType());
		emaster.setEnqPriority(bean.getEnqPriorityLov());
		emaster.setUdpatedBy(getUserPreferences().getUserID());
		emaster.setUpdatedDate(new Date());
		service.updateEnquiryMaster(emaster);
		service.addNewEnquiryDetail(details, null);
		loadEnquiryDetails(context);
		resetForm(context);
	  return success();	
		
	}
	
	public Event closeEnquiry(RequestContext context) throws Exception {
		EnqBean bean = (EnqBean) getFormObject(context);
		SciEnquiryMaster emaster  = (SciEnquiryMaster) context.getFlowScope().get("selectEnq");
		emaster.setCloseDate(new Date());
		emaster.setEnqStatus("C");
		emaster.setUdpatedBy(getUserPreferences().getUserID());
		emaster.setUpdatedDate(new Date());
		service.closeEnquury(emaster);
		
	  return success();	
		
	}


	public Event addEnquiryDoc(RequestContext context) throws Exception{

		EnqBean bean = (EnqBean) getFormObject(context);


		MultipartFile file = bean.getFileDoc();

		SciEnquiryDocs master = new SciEnquiryDocs();
		List enqmasterlist = (List) context.getFlowScope().get("openenqlist");
		SciEnquiryMaster emaster = selectEnqmaster(enqmasterlist, bean.getSeqenqmasterid());
		BeanUtils.copyProperties(master, bean);
		master.setSeqEnqId(emaster.getSeqEnqryId());
		master.setEnqDocName(file.getOriginalFilename());

		master.setUpdatedBy(getUserPreferences().getUserID());
		master.setUpdatedDate(new java.util.Date());

		master.setEnqDocData(bean.getFileDoc().getBytes());


		master.setOriginalDoc(file.getOriginalFilename());
		master.setDocCnttype(file.getContentType());

		service.addEnquiryDocMaster(master);
		loadEnquiryDetails(context);
		diskwriter.writeEnquiryDoc();
		return success();
	}
}
