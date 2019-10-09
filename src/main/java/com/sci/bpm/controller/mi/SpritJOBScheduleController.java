package com.sci.bpm.controller.mi;

import com.sci.bpm.command.mi.MatindCommand;
import com.sci.bpm.command.mi.SprintJobCommand;
import com.sci.bpm.controller.base.DatePropertyEditorRegistrar;
import com.sci.bpm.controller.base.SciBaseController;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

@Controller("sprintcont")
public class SpritJOBScheduleController extends SciBaseController {

    @Override
    public Event setupForm(RequestContext context) throws Exception {
        setFormObjectClass(SprintJobCommand.class);
        setFormObjectName("sprintjobbean");
        setPropertyEditorRegistrar(new DatePropertyEditorRegistrar());
        return super.setupForm(context);
    }
}
