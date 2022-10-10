package com.sci.bpm.dao.mi;

import com.sci.bpm.db.model.SciSprintJobDetailsEntity;
import com.sci.bpm.db.model.SciWeeklyJobLogEntity;
import com.sci.bpm.db.model.SciWeeklyJobMasterEntity;
import com.sci.bpm.db.model.SciWeeklySprintEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SciSprintJOBDaoImpl implements SciSprintJOBDAO {

    @PersistenceContext
    private EntityManager entityManager;
    public void addNewJob(SciWeeklyJobMasterEntity jobMasterEntity) {
        entityManager.persist(jobMasterEntity);
    }

    public void addNewJobLog(SciWeeklyJobLogEntity jobLogEntity) {
        entityManager.persist(jobLogEntity);
    }

    public void addNewSprint(SciWeeklySprintEntity sciWeeklySprintEntity) {
        entityManager.persist(sciWeeklySprintEntity);
    }

    public void addNewSprintTasks(List<SciSprintJobDetailsEntity> sprintJobDetails) {
        for(SciSprintJobDetailsEntity sprintJobDetailsEntity :sprintJobDetails) {
            entityManager.persist(sprintJobDetailsEntity);
        }
    }
}
