package yabushan.service.workflow;

import org.activiti.engine.form.TaskFormData;

public interface FormService {

	/**
	 * 1.获取表单数据对象
	 * @param taskId
	 * @return
	 */
	public TaskFormData queryFormData(String taskId);

	
}
