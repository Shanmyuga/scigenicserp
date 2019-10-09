package com.sci.bpm.service.mi;

import com.sci.bpm.dao.mi.SciSprintJOBDAO;
import com.sci.bpm.db.model.SciSprintJobDetailsEntity;
import com.sci.bpm.db.model.SciWeeklyJobLogEntity;
import com.sci.bpm.db.model.SciWeeklyJobMasterEntity;
import com.sci.bpm.db.model.SciWeeklySprintEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SciSprintServiceImpl implements SciSprintService {

    @Autowired
    private SciSprintJOBDAO sciSprintJOBDAO;
    public void addNewJob(SciWeeklyJobMasterEntity jobMasterEntity) {
        sciSprintJOBDAO.addNewJob(jobMasterEntity);
    }

    public void addNewJobLog(SciWeeklyJobLogEntity jobLogEntity) {
        sciSprintJOBDAO.addNewJobLog(jobLogEntity);
    }

    public void addNewSprint(SciWeeklySprintEntity sciWeeklySprintEntity) {
        sciSprintJOBDAO.addNewSprint(
                sciWeeklySprintEntity
        );
    }

    public void addNewSprintTasks(List<SciSprintJobDetailsEntity> sprintJobDetails) {
            sciSprintJOBDAO.addNewSprintTasks(sprintJobDetails);
    }
}
