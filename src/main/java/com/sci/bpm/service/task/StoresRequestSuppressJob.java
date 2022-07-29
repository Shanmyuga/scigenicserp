package com.sci.bpm.service.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class StoresRequestSuppressJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }


    public TaskService getTservice() {
        return tservice;
    }

    public void setTservice(TaskService tservice) {
        this.tservice = tservice;
    }

    private TaskService tservice;
}
