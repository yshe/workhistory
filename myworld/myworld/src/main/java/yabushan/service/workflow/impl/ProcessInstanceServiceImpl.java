package yabushan.service.workflow.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.common.Constant;
import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.ProcessInstanceService;

@Service("processInstanceServiceImpl")
public class ProcessInstanceServiceImpl implements ProcessInstanceService{
	@Autowired
	private EngineService engineService;

	@Override
	public ProcessInstance StartProcess(String userId, String businessId,
			String processDefinitionKey) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put(Constant.REQUEST_USER, userId);//流程规则定义的发起人的ID
		variables.put(Constant.BUSINESS_ID, businessId);//业务主键ID
		//使用流程定义的Key启动流程实例
		engineService.getIdentityService().setAuthenticatedUserId(userId);
		org.activiti.engine.runtime.ProcessInstance pi = engineService.getRuntimeService()
															.startProcessInstanceByKey(processDefinitionKey,businessId,variables);
		return pi;
	}

	@Override
	public List<Task> queryMySingleTasks(String userId) {
		java.util.List<Task> list = engineService.getTaskService()
				.createTaskQuery().taskAssignee(userId)
				.orderByTaskCreateTime().asc()
				.list();
		return list;
	}

	@Override
	public List<Task> queryMyGroupTasks(String userId) {
		List<Task> list = engineService.getTaskService()
				.createTaskQuery()
				.taskCandidateUser(userId)
				.orderByTaskCreateTime().desc()
				.list();
		return list;
	}

	@Override
	public List<Task> getMyTasks(String userId) {
		List<Task> tasks = new ArrayList<Task>();
		tasks.addAll(queryMySingleTasks(userId));
		tasks.addAll(queryMyGroupTasks(userId));
		return tasks;
		
	}

	@Override
	public void claim(String taskId, String dealUser) {
		engineService.getTaskService().claim(taskId, dealUser);
	}

	@Override
	public ProcessInstance processInstanceStep(String processInstanceId) {
		org.activiti.engine.runtime.ProcessInstance pi = engineService.getRuntimeService()
				.createProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.singleResult();
		return pi;
	}

	@Override
	public void completeTask(String taskId,String userId,String nextDealUser,String nextSeqFlow,String stepInfo) {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("nextDealUser",nextDealUser);//流程中设置下一个审批人的占位符为：userName
		variables.put("message", nextSeqFlow);//设置下一步步骤
		//variables.put("opinion", requestMessage.getApproveOpinion());//保存流程实体
		engineService.getTaskService().setVariableLocal(taskId,Constant.STEP_INFO, stepInfo);
		//engineService.getTaskService().setVariable(requestMessage.getTaskId(), "message", requestMessage.getNextSeqFlow());
		claim(taskId,userId);
		engineService.getTaskService().complete(taskId, variables);
		
	}

	@Override
	public void changeAssigneeTask(String taskId, String dealUserId) {
		engineService.getTaskService().setAssignee(taskId, dealUserId);
	}

	@Override
	public List<IdentityLink> getIdentityLinks(String taskId) {
		List<IdentityLink> list = engineService.getTaskService()
				.getIdentityLinksForTask(taskId);
		return list;
	}
	@Override
	public List<String> queryOutComeListByTaskId(String taskId) {
		//返回存放连线的名称集合
		List<String> list = new ArrayList<String>();
		//1.使用任务ID，查询任务对象
		Task task = engineService.getTaskService().createTaskQuery()
								.taskId(taskId).singleResult();
		//2.获取流程定义ID
		String processDefinitionId = task.getProcessDefinitionId();
		//3.查询processDefinitionEntity对象
		ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity) engineService.getRepositoryService()
														.getProcessDefinition(processDefinitionId);
		//使用任务对象task获取流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
		org.activiti.engine.runtime.ProcessInstance pi= engineService.getRuntimeService().createProcessInstanceQuery()
							.processInstanceId(processInstanceId).singleResult();
		//获取当前活动的id
		String activityId = pi.getActivityId();
		//获取当前的活动
		ActivityImpl activityImpl=processDefinitionEntity.findActivity(activityId);
		//获取当前活动完成后的连线的名称
		List<PvmTransition> pvmList =activityImpl.getOutgoingTransitions();
		if(pvmList!=null && pvmList.size()>0){
			for (PvmTransition pvmTransition : pvmList) {
				String name =(String) pvmTransition.getProperty("name");
				if(StringUtils.isNotBlank(name)){
					list.add(name);
				}
			}
		}
		return list;
	}

	@Override
	public Task queryTaskInfo(String taskId) {
		Task task=engineService.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		return task;
	}

	

}
