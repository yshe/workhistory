package yabushan.service.workflow;

import java.util.List;

import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;

public interface HistoryQueryService {
	
	/**
	 * 1.查看历史流程实例
	 * @param ID 查询ID
	 * @param flag(key获取pid) 查询ID表示的查询字段（key:processDefinitionKey,pid:processInstanceId,bid:businessId）
	 * @param isFinish
	 * @return
	 */
	
	public List<HistoricProcessInstance> getHistoricProcessInstanceByKey(
			String ID, String flag, boolean isFinish,Integer firstResult ,Integer maxResults);
	
	/**
	 * 2.查看历史流程活动
	 * @param processInstanceId 历史流程实例ID
	 * @return
	 */
	public List<HistoricActivityInstance> getHistoricActivityInstances(String processInstanceId, boolean isFinish,Integer firstResult ,Integer maxResults);
	

	/**
	 *  3.查询历史任务
	 * @param processInstanceId 历史流程实例ID
	 * @param isFinish 是否已完成
	 * @param firstResult 分页限制
	 * @param maxResults 分页限制
	 * @return
	 */
	public List<HistoricTaskInstance> getHistoricTaskInstances(String processInstanceId, boolean isFinish,Integer firstResult ,Integer maxResults);
	
	
	/**
	 * 4.1根据流程变量名查询历史流程变量
	 * @param variableName
	 * @return
	 */
	public List<HistoricVariableInstance> getHistoricVariableInstancesByVariableName(String variableName);
	/**
	 * 4.2 根据流程实例ID查询历史流程变量
	 * @param processInstanceId
	 * @return
	 */
	public List<HistoricVariableInstance> getHistoricVariableInstancesByProcessInstanceId(String processInstanceId);
	/**
	 * 5.1根据任务ID查询历史流程变量
	 * @param taskId
	 * @return
	 */
	public List<HistoricVariableInstance> getHistoricVariableInstancesByTaskId(String taskId);
	/**
	 * 5.2查询绑定任务的流程变量
	 * @param title
	 * @param taskId
	 * @return
	 */
	public Object getVariableLocal(String title, String taskId);
	
	public Object getVariable(String title, String taskId);
}
