package yabushan.service.workflow.impl;

import javax.annotation.Resource;

import org.activiti.engine.form.TaskFormData;
import org.springframework.stereotype.Service;

import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.FormService;
@Service("formServiceImpl")
public class FormServiceImpl implements FormService{
	@Resource
	private EngineService engineService;
	@Override
	public TaskFormData queryFormData(String taskId) {
		TaskFormData taskFormData=engineService.getFormService().getTaskFormData(taskId);
		return taskFormData;
		
	}

	
	

}
