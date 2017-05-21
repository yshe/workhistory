package yabushan.service.workflow.impl;

import java.util.ArrayList;
import java.util.List;



import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.service.workflow.CommentService;
import yabushan.service.workflow.EngineService;

@Service("commentServiceImpl")
public class CommentServiceImpl implements CommentService{
	@Autowired
	private EngineService engineService;
	@Override
	public void addCommentInfo(String taskId, String processInstanceId,
			String message) {
		engineService.getTaskService().addComment(taskId, processInstanceId, message);
		
	}

	@Override
	public List<Comment> getCommentsByTaskId(String taskId) {
		List<Comment> list = new ArrayList<Comment>();
		//使用当前的任务id，查询当前流程对应的历史任务ID
		//使用当前任务ID，获取当前任务对象
		Task task = engineService.getTaskService().createTaskQuery()
					.taskId(taskId).singleResult();
		//获取流程实例ID
		String processInstanceId =task.getProcessInstanceId();
		
		/**
		 * 方式1：
		 */
		/*//使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		List<HistoricTaskInstance> hiList = engineService.getHistoryService().createHistoricTaskInstanceQuery()
											.processInstanceId(processInstanceId).list();
		//遍历集合，获取每个任务ID
		if(hiList!=null && hiList.size()>0){
			for (HistoricTaskInstance historicTaskInstance : hiList) {
				//任务ID
				String htaskId =historicTaskInstance.getId();
				//获取批注信息
				List<Comment> taskList = engineService.getTaskService().getTaskComments(htaskId);
				list.addAll(taskList);
			}
		}*/
		
		/**
		 * 方式2：
		 */
		list=engineService.getTaskService().getProcessInstanceComments(processInstanceId);
		return list;
	}

	@Override
	public List<Comment> getCommentByTaskId(String taskId) {
		List<Comment> taskList = engineService.getTaskService().getTaskComments(taskId);
		return taskList;
	}

}
