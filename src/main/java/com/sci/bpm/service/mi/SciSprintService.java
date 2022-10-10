package com.sci.bpm.service.mi;

import com.sci.bpm.db.model.SciSprintJobDetailsEntity;
import com.sci.bpm.db.model.SciWeeklyJobLogEntity;
import com.sci.bpm.db.model.SciWeeklyJobMasterEntity;
import com.sci.bpm.db.model.SciWeeklySprintEntity;

import java.util.List;

public interface SciSprintService {

    public void addNewJob(SciWeeklyJobMasterEntity jobMasterEntity);

    public void addNewJobLog(SciWeeklyJobLogEntity jobLogEntity);

    public void addNewSprint(SciWeeklySprintEntity sciWeeklySprintEntity);



    public void addNewSprintTasks(List<SciSprintJobDetailsEntity> sprintJobDetails);
}
