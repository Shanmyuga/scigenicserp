package com.sci.bpm.dao.proj;

import java.util.Date;
import java.util.List;

import com.sci.bpm.command.proj.ProjectReportView;
import com.sci.bpm.command.proj.ProjectTrackCommand;
import com.sci.bpm.db.model.SciProjectCostMaster;
import com.sci.bpm.db.model.SciWoTrackMaster;
import com.sci.bpm.db.model.SciWorkorderMaster;

public interface ProjTrackDao {

	
	public void addTaskPhase(SciWoTrackMaster master);
	
	public List loadWoPhases(SciWorkorderMaster master);
	public List loadWoPhasesByShortKey(String shortKey,String phaseName);
	public List loadSubPhase(String phaseMaster);
	public boolean checkPhaseExist(String phaseName,String subphase,Long seqworkid);
	public SciWoTrackMaster loadWoMaster(String phasename,Long seqWorkId);
	public List<ProjectReportView> reportTasks(ProjectTrackCommand command) ;
	public void updateDates(String phasename,Long seqWorkID);
	
	public void updateActualDates(String phasename,String subphase,Long seqWorkId,Date actualStart,Date actualend);
public List loadProjectCost(SciWorkorderMaster wmaster);
	
	public void addProjectCost(SciProjectCostMaster costmaster);
}
