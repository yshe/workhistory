package yabushan.service.workflow.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.TaskService;

@Service("taskServiceImpl")
public class TaskServiceImpl implements TaskService{
	@Autowired
	private EngineService engineService;

	@Override
	public Map<String, Object> findCoordingByTask(String taskId) {
		//存放坐标
		Map<String, Object> map = new HashMap<String, Object>();
		//使用任务ID，查询任务对象
		Task task = engineService.getTaskService().createTaskQuery().taskId(taskId).singleResult();
		//获取流程定义的ID
		String processDefinitionId = task.getProcessDefinitionId();
		//获取流程dinginess的实体对象（对应.bpmn文件中的数据）
		ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity) engineService.getRepositoryService().getProcessDefinition(processDefinitionId);
		//流程实例ID
		String processInstanceId = task.getProcessInstanceId();
		//使用流程实例ID，查询正在执行的执行对象表，获取当前活动对应的流程实例对象
		ProcessInstance pi = engineService.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		//获取当前活动的ID
		String activityId = pi.getActivityId();
		//获取当前活动对象
		ActivityImpl activityImpl = processDefinitionEntity.findActivity(activityId);//活动ID
		//获取坐标
		map.put("x", activityImpl.getX());
		map.put("y", activityImpl.getY());
		map.put("width", activityImpl.getWidth());
		map.put("height", activityImpl.getHeight());
		map.put("deploymentId", pi.getDeploymentId());
		return map;
	}

	
	

}
