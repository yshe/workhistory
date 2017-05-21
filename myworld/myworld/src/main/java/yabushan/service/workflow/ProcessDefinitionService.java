package yabushan.service.workflow;

import java.io.File;
import java.io.InputStream;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;


public interface ProcessDefinitionService  {

	/**
	 * 1.1通过输入流部署流程
	 * @param deploymentName 部署流程定义明
	 * @param processBpmn bpmn文件
	 * @param processPng png文件
	 * @param rootPath 该文件所在路径文件夹默认：（src/main/resources/）下
	 * @return
	 */
	public Deployment DeployByInputStream(String deploymentName,String processBpmn,String processPng,String rootPath);
	
	/**
	 * 1.2通过zip文件部署流程
	 * @param zipPath zip文件路径
	 * @param deployeName 部署名称
	 * @return
	 */
	public Deployment DeployByZipPath(String zipPath,String deployeName);
	
	public Deployment deploymentByWebZipFile(File file,String fileName);
	
	/**
	 * 2.查询流程定义
	 * @param definitionId 流程定义ID(如果为空，则查询所有版本)
	 * @param isNewDefinition 是否只查询最新版本 true：查询最新版本，false：查询所有版本
	 * @return
	 */
	public java.util.List<ProcessDefinition> queryDefinitionsByDefinitionId(String definitionId,boolean isNewDefinition);
	
	/**
	 * 2.1查询流程定义
	 * @param definitionKey 流程定义ID(如果为空，则查询所有版本)
	 * @param isNewDefinition 是否只查询最新版本 true：查询最新版本，false：查询所有版本
	 * @return
	 */
	public java.util.List<ProcessDefinition> queryDefinitionsByDefinitionKey(String definitionKey,boolean isNewDefinition);
	/**
	 * 3.1删除流程定义
	 * @param deploymentId 流程部署ID
	 * @param cascade 是否级联删除（如果流程定义已经有了流程实例，则必须使用级联删除否则会报错）
	 */
	public void deleteProcessDefinitonByDeploymentId(String deploymentId,boolean cascade);
	
	/**
	 * 3.2删除流程定义
	 * 根据Key值删除该key对应的所有不同版本的流程定义
	 * @param processDefinitionKey
	 */
	public void deleteProcessDefinitionByKey(String processDefinitionKey);
	
	
	/**
	 * 4.1查看流程图
	 * @param deploymentId 流程部署ID
	 * @param outFilePath 保存流程图文件路径
	 * @return
	 */
	public String queryProcessDefinitionImage(String deploymentId,String outFilePath);
	/**
	 * 4.2查看流程图输出流
	 * @param deploymentId
	 * @return 
	 */
	public InputStream queryProcessDefinitionImageInputStream(String deploymentId);
	
}
