package yabushan.controller.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import yabushan.service.workflow.CommentService;
import yabushan.service.workflow.FormService;
import yabushan.service.workflow.HistoryQueryService;
import yabushan.service.workflow.ProcessDefinitionService;
import yabushan.service.workflow.ProcessInstanceService;
import yabushan.service.workflow.TaskService;
import yabushan.service.workflow.VariablesService;

@Controller("/workTest")
public class TestController {
	@Resource(name="commentServiceImpl")
	private CommentService commentService;
	@Resource(name="formServiceImpl")
	private FormService formService;
	@Resource(name="historyQueryServiceImpl")
	private HistoryQueryService historyService;
	@Resource(name="processDefinitionServiceImpl")
	private ProcessDefinitionService processDefinitionService;
	@Resource(name="processInstanceServiceImpl")
	private ProcessInstanceService processInstanceService;
	@Resource(name="taskServiceImpl")
	private TaskService taskService;
	@Resource(name="variablesServiceImpl")
	private VariablesService variablesService;
	
	@RequestMapping(value="/testFlowService.do", method = RequestMethod.GET)
	public void testService(){
		System.out.println(commentService);
		System.out.println(formService);
		System.out.println(historyService);
		System.out.println(processDefinitionService);
		System.out.println(processInstanceService);
		System.out.println(taskService);
		System.out.println(variablesService);
	}
	
	

}
