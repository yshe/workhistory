package yabushan.service.workflow.impl;

import java.util.List;

import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.common.Constant;
import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.HistoryQueryService;

@Service("historyQueryServiceImpl")
public class HistoryQueryServiceImpl implements HistoryQueryService{
	@Autowired
	private EngineService engineService;

	@Override
	public List<HistoricProcessInstance> getHistoricProcessInstanceByKey(
			String ID, String flag, boolean isFinish,Integer firstResult ,Integer maxResults) {
		List<HistoricProcessInstance> list=null;
		if(firstResult==null){
			firstResult=Constant.firstResult;
		}
		if(maxResults==null){
			maxResults=Constant.maxResults;
		}
		if("key".equals(flag)){//按流程KEY查询
			if(isFinish){
				list = engineService.getHistoryService()
						.createHistoricProcessInstanceQuery().finished()
						.processDefinitionKey(ID).orderByProcessInstanceStartTime().desc()
						.listPage(firstResult, maxResults);
			}else{
				list = engineService.getHistoryService()
						.createHistoricProcessInstanceQuery()
						.processDefinitionKey(ID).orderByProcessInstanceStartTime().desc()
						.listPage(firstResult, maxResults);
			}
			
		}else if("pid".equals(flag)){
				list = engineService.getHistoryService()
						.createHistoricProcessInstanceQuery()
						.processInstanceId(ID).orderByProcessInstanceStartTime()
						.list();
		}else if("bid".equals(flag)){
			list = engineService.getHistoryService()
					.createHistoricProcessInstanceQuery().processInstanceBusinessKey(ID)
					.orderByProcessInstanceStartTime().desc()
					.list();
		}
		return list;
		
	}

	@Override
	public List<HistoricActivityInstance> getHistoricActivityInstances(
			String processInstanceId, boolean isFinish,Integer firstResult ,Integer maxResults) {
		if(firstResult==null){
			firstResult=Constant.firstResult;
		}
		if(maxResults==null){
			maxResults=Constant.maxResults;
		}
		List<HistoricActivityInstance> list = engineService.getHistoryService()
				.createHistoricActivityInstanceQuery()
				.processInstanceId(processInstanceId)
				.orderByHistoricActivityInstanceEndTime().asc()
				.listPage(firstResult, maxResults);
		return list;
	}

	@Override
	public List<HistoricTaskInstance> getHistoricTaskInstances(
			String processInstanceId,boolean isFinish,Integer firstResult ,Integer maxResults) {
		List<HistoricTaskInstance> list=null;
		if(firstResult==null){
			firstResult=Constant.firstResult;
		}
		if(maxResults==null){
			maxResults=Constant.maxResults;
		}
		if(isFinish){
			list = engineService.getHistoryService()
					.createHistoricTaskInstanceQuery()
					.processInstanceId(processInstanceId).finished()
					.orderByTaskId().asc()
					.listPage(firstResult, maxResults);
		}else{
			 list = engineService.getHistoryService()
					.createHistoricTaskInstanceQuery()
					.processInstanceId(processInstanceId)
					.orderByTaskId().asc()
					.listPage(firstResult, maxResults);
		}
		
		return list;
	}
	
	/**
	 * 查询历史流程变量（在流程结束后）
	 * @param variableName 变量名
	 * @return List<HistoricVariableInstance> 
	 */
	@Override
	public List<HistoricVariableInstance> getHistoricVariableInstancesByVariableName(String variableName){
		List<HistoricVariableInstance> list = engineService.getHistoryService()
												.createHistoricVariableInstanceQuery()
												.variableName(variableName)
												.list();
		return list;
	}
	/**
	 * 查询历史流程变量（在流程结束后）
	 * @param variableName 变量名
	 * @return List<HistoricVariableInstance> 
	 */
	@Override
	public List<HistoricVariableInstance> getHistoricVariableInstancesByTaskId(String taskId){
		List<HistoricVariableInstance> list = engineService.getHistoryService()
												.createHistoricVariableInstanceQuery()
												.taskId(taskId)
												.list();
		return list;
	}
	
	/**
	 * 查询历史流程变量
	 */
	@Override
	public List<HistoricVariableInstance> getHistoricVariableInstancesByProcessInstanceId(String processInstanceId){
		List<HistoricVariableInstance> list = engineService.getHistoryService()
												.createHistoricVariableInstanceQuery()
												.processInstanceId(processInstanceId)
												.list();
		return list;
		
	}
	/**
	 * 获取流程变量
	 * @param title 变量名
	 * @param taskId 任务名
	 * @return
	 */
	@Override
	public Object getVariableLocal(String title, String taskId) {
		TaskService taskService=engineService.getTaskService();
		return  taskService.getVariableLocal(taskId, title);
	}

	@Override
	public Object getVariable(String title, String taskId) {
		TaskService taskService=engineService.getTaskService();
		return  taskService.getVariable(taskId, title);
	}
	
}
