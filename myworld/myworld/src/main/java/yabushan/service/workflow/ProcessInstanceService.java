package yabushan.service.workflow;

import java.util.List;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;



public interface ProcessInstanceService {
	
	/**
	 * 1.启动流实例
	 * @param userId 发起人ID
	 * @param businessId 业务ID
	 * @param processDefinitionKey 流程定义的KEY值
	 * @return
	 */
	public ProcessInstance StartProcess(String userId,String businessId,String processDefinitionKey);

	/**
	 * 2.查看我的所有待办任务 (组待办任务和个人待办任务)
	 * @param userId 任务办理人
	 * @return
	 */
	public List<Task> getMyTasks(String userId);
	
	/**
	 * 2.1查看个人待办理任务
	 * @param userId 任务办理人
	 * @return
	 */
	public java.util.List<Task> queryMySingleTasks(String userId);
	
	/**
	 * 2.2 查看个人待办理的组任务
	 * @param userId
	 * @return
	 */
	public List<Task> queryMyGroupTasks(String userId);
	
	/**
	 * 3.认领任务
	 * @param taskId 任务ID
	 * @param dealUser 认领人
	 */
	public void claim(String taskId,String dealUser);
	
	/**
	 * 4.获取流程实例当前所处活动环节
	 * @param processInstanceId
	 * @return
	 */
	public ProcessInstance processInstanceStep(String processInstanceId);
	
	/**
	 * 5.完成任务 
	 * @param taskId 任务ID
	 * @param userId 处理人ID
	 * @param nextDealUser 下一步处理人ID(多个人之间用,分隔)
	 * @param nextSeqFlow 下一步流向线条
	 * @param stepInfo 需要保存的当前任务的变量信息
	 */
	 public void completeTask(String taskId,String userId,String nextDealUser,String nextSeqFlow,String stepInfo);
	
	
 	/**
	 * 6.调换任务的处理人
	 * @param taskId
	 * @param dealUserId
	 */
	public void changeAssigneeTask(String taskId,String dealUserId);
	
	/**
	 * 7.查询任务的办理人列表(如果任务是由多个人办理)
	 * @param taskId
	 * @return List<IdentityLink>
	 */
	public List<IdentityLink> getIdentityLinks(String taskId);
	
	/**
	 * 8.查询当前任务的所有输出线
	 * @param taskId
	 * @return
	 */
	public List<String> queryOutComeListByTaskId(String taskId);
	
	/**
	 * 9.根据taskId获取任务
	 * @param taskId
	 * @return
	 */
	public Task queryTaskInfo(String taskId);
	
	
	
}
