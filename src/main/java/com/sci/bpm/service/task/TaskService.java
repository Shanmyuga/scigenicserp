package com.sci.bpm.service.task;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sci.bpm.command.LookupValueBean;
import com.sci.bpm.command.user.TableDynaBean;
import com.sci.bpm.db.model.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface TaskService {

	
	public boolean addNewTask(SciIssueMaster master);
	public void addNewEnquiryDocs(List<SciEnquiryDocs> docs);
	public SciEnquiryDetails addNewEnquiryActionDetail(SciEnquiryDetails details);
	public SciEnquiryMaster loadEnquiryMaster(String enqFullId);
	public boolean forwardTasks(SciIssueDetails issueDetails);
	
	public boolean closeTasks(SciIssueDetails issueDetails);
	
	public boolean esclateTasks(SciIssueDetails  details);
	public boolean rejectDelayedRequests();

	public List loadForEscalation(int numberOfdays);
	
	public List searchTasks(String status ,int startpage,String userid);
	public SciIssueDetails loadDetails(String seqid);
	
	public String getNextSeqID();
	public Map<String,Map<Integer,String>> getReportCount();
	
	public List<Map<Integer,String>>  getWOTotalCount();
	public void generateReports();
	public void generateWeeklyReports();
	
	public void runSelectedReport(SciReportConfiguration configuration);


	public List<LinkedHashMap<String, Object>> downloadSelectedReport(SciReportConfiguration configuration);
	public List<LinkedHashMap<String, Object>> downloadSelectedReportWithFilter(SciReportConfiguration configuration,String shortKey);
	public List<TableDynaBean> viewSelectedReport(SciReportConfiguration configuration);

	public List<TableDynaBean> viewSelectedReportWithFilter(SciReportConfiguration configuration,String shortKey);
}
