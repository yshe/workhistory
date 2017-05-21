package yabushan.service.workflow;

import java.util.List;

import org.activiti.engine.task.Comment;

public interface CommentService {
	
	/**
	 * 1.添加批注
	 * @param taskId 要添加批注的任务ID
	 * @param processInstanceId 流程实例ID（可以为null）
	 * @param message 要添加的信息
	 */
	public void addCommentInfo(String taskId,String processInstanceId,String message);
	
	/**
	 * 2.获取流程实例所有批注
	 * @param taskId
	 * @return
	 */
	public List<Comment> getCommentsByTaskId(String taskId);
	
	
	/**
	 * 3.获取单条任务的批注
	 * @param taskId
	 * @return
	 */
	public List<Comment>  getCommentByTaskId(String taskId);

}
