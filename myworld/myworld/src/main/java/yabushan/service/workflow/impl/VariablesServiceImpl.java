package yabushan.service.workflow.impl;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.VariablesService;
@Service("variablesServiceImpl")
public class VariablesServiceImpl implements VariablesService {
	@Autowired
	private EngineService engineService;

	@Override
	public void setVariablesByTaskId(Map<String, String> variables, String taskId,
			boolean isBindTask) {
		TaskService taskService	=engineService.getTaskService();
    	boolean isBind=false;//默认不绑定任务,绑定任务后，之后在当前任务才能查看，其它环节将不能查看该变量
    	if(isBindTask){isBind=isBindTask;}
    	if(isBind){
    		for (Map.Entry<String, String> entry : variables.entrySet()) {  
        		taskService.setVariableLocal(taskId, entry.getKey(), entry.getValue());
        	}  
    	}else{
    		for (Map.Entry<String, String> entry : variables.entrySet()) {  
        		taskService.setVariable(taskId, entry.getKey(), entry.getValue());
        	}  
    	}
		
	}

	@Override
	public <T> T setVariablesByTaskId(Class<T> classBean, String taskId, String title,
			boolean isBindTask) {
		TaskService taskService	=engineService.getTaskService();
    	boolean isBind=false;//默认不绑定任务,绑定任务后，之后在当前任务才能查看，其它环节将不能查看该变量
    	if(isBindTask){isBind=isBindTask;}
    	if(isBind){
        		taskService.setVariableLocal(taskId,title,classBean);
    	}else{
        		taskService.setVariable(taskId,title,classBean);
        	}
		return null; 
	}

	@Override
	public void setVariablesByExecutionId(Map<String, String> variables,
			String executionId, boolean isBindTask) {
		RuntimeService runtimeService=engineService.getRuntimeService();
    	boolean isBind=false;//默认不绑定任务,绑定任务后，之后在当前任务才能查看，其它环节将不能查看该变量
    	if(isBindTask){isBind=isBindTask;}
    	if(isBind){
    		runtimeService.setVariablesLocal(executionId, variables);
    	}else{
        		runtimeService.setVariables(executionId, variables);
        	}
	}

	@Override
	public <T> T setVariablesByExcutionId(Class<T> classBean, String taskId,
			String title, boolean isBindTask) {
		RuntimeService runtimeService=engineService.getRuntimeService();
    	boolean isBind=false;//默认不绑定任务,绑定任务后，之后在当前任务才能查看，其它环节将不能查看该变量
    	if(isBindTask){isBind=isBindTask;}
    	if(isBind){
    		runtimeService.setVariableLocal(taskId,title,classBean);
    	}else{
    		runtimeService.setVariable(taskId,title,classBean);
        	}
		return null; 
	}

	@Override
	public Object getVariablesByTaskId(String title, String taskId,boolean isBindTask) {
		TaskService taskService=engineService.getTaskService();
		if(isBindTask){
			return taskService.getVariableLocal(taskId, title);
		}else{
			return taskService.getVariable(taskId, title);
		}
		
	}

	@Override
	public Object getVariablesByExcutionId(String title, String excutionId,boolean isBindTask) {
		RuntimeService runtimeService=engineService.getRuntimeService();
		if(isBindTask){
			return runtimeService.getVariableLocal(excutionId, title);
		}else{
			return runtimeService.getVariable(excutionId, title);
		}
		
	}
	
}
