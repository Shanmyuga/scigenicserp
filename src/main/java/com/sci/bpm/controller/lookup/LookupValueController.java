package com.sci.bpm.controller.lookup;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.sci.bpm.command.marketing.WorkOrderCommand;
import com.sci.bpm.db.model.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.sci.bpm.command.LookupValueBean;
import com.sci.bpm.controller.base.SciBaseController;
import com.sci.bpm.service.lookup.LookUpValueService;
import com.sci.bpm.service.task.TaskService;

@Controller("lookupcontbean")
public class LookupValueController extends SciBaseController {


	@Autowired
	private LookUpValueService service;

	@Autowired
	private TaskService taskService;

	public Event addNewValue(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciLookupMaster master = new SciLookupMaster();
		try {
			BeanUtils.copyProperties(master, value);
			master.setUpdatedDt(new java.util.Date());
			master.setUpdatedBy(getUserPreferences().getUserID());
			service.addNewValue(master);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success();
	}

	public Event addNewItemValue(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciMasterItem master = new SciMasterItem();

		master.setItemType(value.getLovType());
		master.setItemDescription(value.getLovDescription());
		master.setUpdatedDt(new java.util.Date());
		master.setUpdatedBy(getUserPreferences().getUserID());
		service.addNewItemValue(master);

		return success();
	}
	public Event addNewCustomer(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciCustomerMaster master = new SciCustomerMaster();
SciClientOrgMaster clientOrgMaster = (SciClientOrgMaster) context.getFlowScope().get("selectedClientOrg");
		BeanUtils.copyProperties(master, value);
		if(StringUtils.isBlank(master.getCustomerPhone()) || StringUtils.isBlank(master.getCustomerEmail() )|| StringUtils.isBlank(master.getCusomterCity())) {
			throw new Exception("Customer phone ,email and city codes are mandatory");
		}
		String customerCode = service.selectCustomerCode(String.valueOf(clientOrgMaster.getSeqClientOrgId()));
		master.setCustomerCode(StringUtils.trim(customerCode));
		master.setUpdatedDate(new java.util.Date());
		master.setUpdatedBy(getUserPreferences().getUserID());
		master.setSciClientOrgMaster(clientOrgMaster);
		service.addNewCustomer(master);
		List<SciCustomerMaster> customers = service.loadCustomerforOrg(clientOrgMaster.getSeqClientOrgId());
		context.getFlowScope().put("selectedCustomers",customers);
		resetForm(context);
		return success();
	}
	public Event addNewClientOrg(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciClientOrgMaster master = new SciClientOrgMaster();

		BeanUtils.copyProperties(master, value);
		master.setUpdatedDate(new java.util.Date());
		String orgCode = service.selectOrgCode();
		master.setOrgCode(orgCode);
		master.setUpdatedBy(getUserPreferences().getUserID());
		master.setInsertedBy(getUserPreferences().getUserID());
		master.setInsertedDate(new java.util.Date());
		if(StringUtils.isBlank(master.getOrgName())) {
			throw new Exception("Org name cannot be blank");
		}
		service.addNewClientOrg(master);

		return success();
	}
	public Event addNewVendor(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciVendorMaster master = new SciVendorMaster();

		BeanUtils.copyProperties(master, value);
		master.setUpdatedDate(new java.util.Date());
		master.setUpdatedBy(getUserPreferences().getUserID());
		service.addNewVendor(master);

		return success();
	}

	public Event loadCities(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciStateCityMasterEntity> cityList = service.loadCities(value.getStateCode());

		context.getFlowScope().put("stateCityList",cityList);
		return success();
	}
	public Event editVendor(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciVendorMaster vendorMaster = (SciVendorMaster) context.getFlowScope().get("selectedVendor");
		BeanUtils.copyProperties(vendorMaster,value);
		service.updateVendor(vendorMaster);
		return success();
	}

	public Event editCustomer(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciCustomerMaster customerMaster = (SciCustomerMaster) context.getFlowScope().get("selectedCustomer");
		BeanUtils.copyProperties(customerMaster,value);
		  customerMaster.setCustomerState(Long.parseLong(value.getStateCode()));
		service.updateCustomer(customerMaster);
		return success();
	}
	public Event editClientOrg(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciClientOrgMaster clientOrgMaster = (SciClientOrgMaster) context.getFlowScope().get("selectedClientOrg");
		BeanUtils.copyProperties(clientOrgMaster,value);
		service.updateCLientOrg(clientOrgMaster);
		return success();
	}
	public Event selectVendor(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciVendorMaster> vendors = (List<SciVendorMaster>) context.getFlowScope().get("vendordroplist");
		SciVendorMaster vendorMaster = filterVendor(vendors,value.getSeqVendorId());
		BeanUtils.copyProperties(value,vendorMaster);
		context.getFlowScope().put("selectedVendor",vendorMaster);
		return success();
	}

	public Event selectCustomer(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciCustomerMaster> customers = (List<SciCustomerMaster>) context.getFlowScope().get("selectedCustomers");
		SciCustomerMaster customerMaster = filterCustomer(customers,value.getSeqCustId());
		BeanUtils.copyProperties(value,customerMaster);
		value.setCustomerCityCode(customerMaster.getCustomerCityCode());
		value.setStateCode(String.valueOf(customerMaster.getCustomerState()));

		List<SciStateCityMasterEntity> cityList = service.loadCities(value.getStateCode());

		context.getFlowScope().put("stateCityList",cityList);
		context.getFlowScope().put("selectedCustomer",customerMaster);
		return success();
	}

	public Event selectClientOrg(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciClientOrgMaster> clientOrgs = (List<SciClientOrgMaster>) context.getFlowScope().get("clientorglist");
		SciClientOrgMaster clientOrgMaster = filterClientOrg(clientOrgs,value.getSeqClientOrgId());
		List<SciCustomerMaster> customers = service.loadCustomerforOrg(clientOrgMaster.getSeqClientOrgId());
		 value.setOrgAddress(clientOrgMaster.getOrgAddress());
		 value.setOrgName(clientOrgMaster.getOrgName());
		context.getFlowScope().put("selectedClientOrg",clientOrgMaster);
		context.getFlowScope().put("selectedCustomers",customers);
		return success();
	}
	public Event addNewMatSpec(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciMatspecMaster master = new SciMatspecMaster();

		BeanUtils.copyProperties(master, value);
		master.setMaterialSpec(value.getMatSpecDesc());
		master.setUpdatedDate(new java.util.Date());
		master.setMatcatCode(value.getSpecCode().substring(0,2));
		master.setUpdatedBy(getUserPreferences().getUserID());
		service.addNewMatSpec(master);

		return success();
	}
	public Event addNewReport(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		SciReportConfiguration master = new SciReportConfiguration();

		BeanUtils.copyProperties(master, value);
		master.setReportStatus("A");
		master.setInsertedDate(new java.util.Date());
		master.setUpdatedDate(new java.util.Date());
		master.setInsertedBy(getUserPreferences().getUserID());
		master.setUpdatedBy(getUserPreferences().getUserID());
		service.addNewReport(master);
		resetForm(context);
		List<SciReportConfiguration> reports = loadReports(context);
		context.getFlowScope().put("reportsdata", reports);

		return success();
	}

	public Event deactivateStatus(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciReportConfiguration> reports = (List<SciReportConfiguration>) context.getFlowScope().get("reportsdata");

		SciReportConfiguration config=   selectReport(reports, new Long(value.getSeqReportID()));
		config.setReportStatus("D");
		config.setUpdatedBy(getUserPreferences().getUserID());
		config.setUpdatedDate(new java.util.Date());
		service.updateStatus(config);
		List<SciReportConfiguration> reportsdata = loadReports(context);
		context.getFlowScope().put("reportsdata", reportsdata);

		return success();
	}

	public Event filterReport(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciReportConfiguration> reports = loadReports(context);
		List<SciReportConfiguration> reportsdata = null;
		if(!StringUtils.isEmpty(value.getReportFilter())) {
			reportsdata = selectReportBysubject(reports, value.getReportFilter());
		}
		else {
			reportsdata = loadReports(context);
		}
		context.getFlowScope().put("reportsdata", reportsdata);

		return success();
	}



	public Event runSelectedReport(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciReportConfiguration> reports = loadReports(context);

		SciReportConfiguration config=   selectReport(reports, new Long(value.getSeqReportID()));
		taskService.runSelectedReport(config);

		return success();
	}

	private SciReportConfiguration selectReport(List<SciReportConfiguration> master,Long seqReportId) {
		SciReportConfiguration selected = null;
		for(SciReportConfiguration m : master) {
			if(m.getSeqRepConfigId().intValue() == seqReportId.intValue()) {
				selected = m;
			}
		}

		return selected;
	}


	private SciVendorMaster filterVendor(List<SciVendorMaster> master,Long seqVendorId) {
		SciVendorMaster selected = null;
		for(SciVendorMaster m : master) {
			if(m.getSeqVendorId().intValue() == seqVendorId.intValue()) {
				selected = m;
			}
		}

		return selected;
	}

	private SciCustomerMaster filterCustomer(List<SciCustomerMaster> master,Long seqCustomerId) {
		SciCustomerMaster selected = null;
		for(SciCustomerMaster m : master) {
			if(m.getSeqCustId().intValue() == seqCustomerId.intValue()) {
				selected = m;
			}
		}

		return selected;
	}
	private SciClientOrgMaster filterClientOrg(List<SciClientOrgMaster> master,Long seqClientOrgId) {
		SciClientOrgMaster selected = null;
		for(SciClientOrgMaster m : master) {
			if(m.getSeqClientOrgId().intValue() == seqClientOrgId.intValue()) {
				selected = m;
			}
		}

		return selected;
	}

	public Event filterReportByClientOrgName(RequestContext context) throws Exception {
		LookupValueBean value = (LookupValueBean)getFormObject(context);
		List<SciClientOrgMaster> clientOrgMasters = service.loadOrgNames();
		List<SciClientOrgMaster> clientOrgMastersList = null;
		if(!StringUtils.isEmpty(value.getReportFilter())) {
			clientOrgMastersList = selectClientOrgName(clientOrgMasters, value.getReportFilter());
		}
		else {
			clientOrgMastersList = service.loadOrgNames();
		}
		context.getFlowScope().put("clientorglist", clientOrgMastersList);

		return success();
	}
	private List<SciReportConfiguration> selectReportBysubject(List<SciReportConfiguration> master,String reportSubject) {
		System.out.println("filter " + reportSubject);
		List<SciReportConfiguration> configurationList = new ArrayList<SciReportConfiguration>();
		for(SciReportConfiguration m : master) {
			if(m.getReportSubject().matches(".*"+reportSubject+".*")) {
				configurationList.add(m);
			}
		}

		return configurationList;
	}
	public List loadReports(RequestContext context) throws Exception {
		List<SciReportConfiguration> reports =service.loadReports();



		return reports;
	}

	public Event loadOrgNames(RequestContext context) throws Exception {
		List<SciClientOrgMaster> reports =service.loadOrgNames();

		StringBuilder builder = new StringBuilder();
		for(SciClientOrgMaster wm:reports) {
			builder.append(wm.getOrgName()+"|");
		}
		context.getFlowScope().put("clientOrgNames", builder.toString());
		context.getFlowScope().put("clientorglist", reports);
		return success();
	}

	@Override
	public Event setupForm(RequestContext context) throws Exception {
		setFormObjectClass(LookupValueBean.class);

		setFormObjectName("lookupvalbean");
		return super.setupForm(context);
	}

	private List<SciClientOrgMaster> selectClientOrgName(List<SciClientOrgMaster> master,String filter) {
		System.out.println("filter " + filter);
		List<SciClientOrgMaster> configurationList = new ArrayList<SciClientOrgMaster>();
		for(SciClientOrgMaster m : master) {
			if(m.getOrgName().toLowerCase().contains(filter.toLowerCase())) {
				configurationList.add(m);
			}
		}

		return configurationList;
	}
}
