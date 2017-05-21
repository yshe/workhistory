package yabushan.service.workflow;

import java.util.Map;

public interface VariablesService {

	/**
	 * 1.1 通过任务ID设置流程变量
	 * @param variables 
	 * @param taskId
	 * @param isBindTask 是否绑定任务（true：是，false 否）如果绑定任务，则该变量只有通过该任务ID才能查看
	 */
	public void setVariablesByTaskId(Map<String, String> variables,String taskId,boolean isBindTask);
	/**
	 * 1.2通过任务ID设置流程变量
	 * @param classBean 要设置的对象，该对象必须实例化
	 * @param taskId
	 * @param title
	 * @param isBindTask
	 * @return
	 */
	public <T> T setVariablesByTaskId(Class<T> classBean, String taskId,String title,boolean isBindTask);
	
	/**
	 * 1.3通过eexcutionId设置流程变量
	 * @param variables
	 * @param executionId
	 * @param isBindTask
	 */
	public void setVariablesByExecutionId(Map<String, String> variables,String executionId,boolean isBindTask);
	/**
	 * 1.2通过任务ID设置流程变量
	 * @param classBean 要设置的对象，该对象必须实例化
	 * @param taskId
	 * @param title
	 * @param isBindTask
	 * @return
	 */
	public <T> T setVariablesByExcutionId(Class<T> classBean, String taskId,String title,boolean isBindTask);
	
	/**
	 * 2.1.通过任务ID获取流程变量
	 * @param title
	 * @param taskId
	 * @param isBindTask
	 * @return
	 */
	public Object getVariablesByTaskId(String title, String taskId,boolean isBindTask);
	
	/**
	 * 2.2.通过执行ID获取流程变量
	 * @param title
	 * @param excutionId
	 * @param isBindTask
	 * @return
	 */
	public Object getVariablesByExcutionId(String title, String excutionId,boolean isBindTask);
	
}
