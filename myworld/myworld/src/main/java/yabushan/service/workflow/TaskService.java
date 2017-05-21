package yabushan.service.workflow;

import java.util.Map;

public interface TaskService {
	
	public Map<String, Object> findCoordingByTask(String taskId);
}
