package yabushan.common;

import java.util.HashMap;
import java.util.Map;

public  class  Constant {
	/**
	 * 业务数据库
	 */
	public final  static String BINGO_DEV_DATABASE="UIMS_ATD_DEV";
	
	/**
	 * 流程发起人变量设置，流程图中设置
	 */
	public final static String REQUEST_USER="requestUser";
	
	
	/**
	 * 业务主键ID
	 */
	public final static String BUSINESS_ID="BID";
	
	/**
	 * 用户任务节点存储的流程变量（绑定任务）
	 */
	public final static String STEP_INFO ="STEP_INFO";
	
	/**
	 * 分隔符
	 */
	public final static String  SEPARATOR=",";
	/**
	 * 分页开始值
	 */
	public final static Integer firstResult=0;
	/**
	 * 分页结束值
	 */
	public final static Integer maxResults=100000000;
	
	/**
	 * //部署工作流规则，文件存放根目录
	 */
	public final static String PROCESS_FILE_ROOT_PATH="diagrams";
	
	/**
	 * 二级经理正职请假规则文件
	 */
	public final static String VACATION_FLOW_BPMN="VacationProcess.bpmn";
	/**
	 * 二级经理正职请假规则图片
	 */
	public final static String VACATION_FLOW_PNG="VacationProcess.png";
	
	
	/**
	 * 二级经理副职请假规则文件
	 */
	public final static String DEPUTY_FLOW_BPMN="DeputyProcess.bpmn";
	/**
	 * 二级经理副职请假规则图片
	 */
	public final static String DEPUTY_FLOW_PNG="DeputyProcess.png";
	
	/**
	 * 探亲假规则文件
	 */
	public final static String VISIT_FLOW_BPMN="VisitProcess.bpmn";
	/**
	 * 探亲假请假规则图片
	 */
	public final static String VISIT_FLOW_PNG="VisitProcess.png";
	
	/**
	 * 探亲假规则文件
	 */
	public final static String CANCEL_FLOW_BPMN="CancelProcess.bpmn";
	/**
	 * 探亲假请假规则图片
	 */
	public final static String CANCEL_FLOW_PNG="CancelProcess.png";
	
	/**
	 * 请假流程用户角色
	 */
	public final static String APPLY_USER="usertask1";//发起请假人
	
	public final static String FIRST_DEAL_NODE="usertask2";//第一个处理界定啊
	
	/**
	 * 公司分管经理审批
	 */
	public final static String MANAGER_LEADER_ROLE_CODE="charge";
	
	/**
	 * 公司总经理审批
	 */
	public final static String LEADER_ROLE_CODE="manager";
	/**
	 * 人力资源部归档
	 */
	public final static String HR_ROLE_CODE="hr";
	
	/**
	 * 所在公司/部门正总
	 */
	public final static String LEADER_COMPANY="companyLeader";
	
	
	/**
	 * 默认流程步骤
	 */
	public final static String DEFULT_STEP="提交";
	
	
	/**
	 * 流程类型
	 */
	/**
	 * 二级经理正职请假流程KEy值
	 */
	public final static String VACATION_PROCESS="VacationProcess";
	
	/**
	 * 二级经理副职请假流程KEY值
	 */
	public final static String DEPUTY_PROCESS="DeputyProcess";
	
	/**
	 * 探亲假流程KEY
	 */
	public final static String VISIT_PROCESS="VisitProcess";
	
	/**
	 * 销假流程KEY
	 */
	public final static String CANCEL_PROCESS="CancelProcess";
	/**
	 * 地市公司二级经理人员请假
	 */
	public final static String CITY_APPLY="2";
	/**
	 * 省公司二级经理人员请假
	 */
	public final static String PROVINCE_APPLY="1";
	
	
	public final static String BACK_OPERATION_NAME ="退回";
}
