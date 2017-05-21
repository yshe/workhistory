package yabushan.controller.system;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource
	private TaskService taskService;
	
	@Resource
	private HistoryService historyService;
	
	@Resource
	private ManagementService managementService;
	
	@Resource
	private IdentityService identityService;
	
	@RequestMapping(value="/testService.do", method = RequestMethod.POST)
	public void testService(){
		System.out.println(repositoryService.toString());
		System.out.println(runtimeService.toString());
		System.out.println(taskService.toString());
		System.out.println(historyService.toString());
		System.out.println(managementService.toString());
		System.out.println(identityService.toString());
	}
	

}
