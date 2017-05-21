package yabushan.service.workflow.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipInputStream;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import yabushan.service.workflow.EngineService;
import yabushan.service.workflow.ProcessDefinitionService;

@Service("processDefinitionServiceImpl")
public class ProcessDefinitionServiceImpl implements ProcessDefinitionService{
	@Autowired
	private EngineService engineService;
	
	Logger log = Logger.getLogger(ProcessDefinitionService.class);
	
	@Override
	public Deployment DeployByInputStream(String deploymentName,
			String processBpmn, String processPng, String rootPath) {
		
		try {
			InputStream inInputStreamBpmn=null;
			InputStream inputStreamPng=null;
				 inInputStreamBpmn = this.getClass().getClassLoader().
						getResourceAsStream(rootPath+"/"+processBpmn);
	         inputStreamPng = this.getClass().getClassLoader().
	        	getResourceAsStream(rootPath+"/"+processPng);
	         org.activiti.engine.repository.Deployment deployment=
	 				engineService.getRepositoryService()	
	 								.createDeployment()
	 								.name(deploymentName)
	 								.addInputStream(processBpmn, inInputStreamBpmn)
	 								.addInputStream(processPng, inputStreamPng)
	 								.deploy();
	         //获取所有步骤信息
	         ProcessDefinition processDefinition= engineService.getRepositoryService().createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
	      /*  List<Map<String, String>> result = workflowUtilService.getJbpmNodeMap(processDefinition.getId());
	        if(result.size()>0){
	     	   serviceStepConf serviceStepConf=null;
	            for (Map<String, String> map : result) {
	         	 serviceStepConf=new serviceStepConf();
	         	 serviceStepConf.setStepId(UUID.randomUUID().toString());
	         	 serviceStepConf.setStepCode(map.get("code"));
	         	 serviceStepConf.setStepName(map.get("name"));
	         	 serviceStepConf.setProcdefId(processDefinition.getId());
	         	 dao.insert(serviceStepConf);
	            }*/
	        //}
	         return deployment;
		} catch (Exception e) {
			log.error("部署失败！",e);
		}
		return null;
	}

	@Override
	public Deployment DeployByZipPath(String zipPath, String deployeName) {
			File zipfile=new File(zipPath);
			ZipInputStream zipInputStream;
			try {
				zipInputStream = new ZipInputStream(new FileInputStream(zipfile));
				Deployment deployment=engineService.getRepositoryService().createDeployment().name(deployeName).addZipInputStream(zipInputStream).deploy();
				return deployment;
			} catch (FileNotFoundException e) {
				log.error("文件没有找到", e);
			}
			return null;
		
	}
	
	@Override
	public Deployment deploymentByWebZipFile(File file,String fileName){
		try {
			ZipInputStream zipInputStream;
			zipInputStream = new ZipInputStream(new FileInputStream(file));
			Deployment deployment=engineService.getRepositoryService().createDeployment().name(fileName).addZipInputStream(zipInputStream).deploy();
			return deployment;
		} catch (Exception e) {
			log.error("文件没有找到", e);
		}
		return null;
	}

	@Override
	public List<ProcessDefinition> queryDefinitionsByDefinitionKey(String definitionKey,
			boolean isNewDefinition) {
		java.util.List<ProcessDefinition> list=null;
		if(StringUtils.isNotEmpty(definitionKey)){//根据流程定义ID获取流程定义
			list=engineService.getRepositoryService()
					.createProcessDefinitionQuery().processDefinitionKey(definitionKey)
					.orderByProcessDefinitionVersion().asc()//默认按升序
					.list();
			if(isNewDefinition){
				//查询最新版本流程定义
				return getNewProcessDefinition(list);
			}else{
				//查询所有版本流程定义
				return list;
			}
		}else{//获取所有的流程定义
			list=engineService.getRepositoryService()
					.createProcessDefinitionQuery()
					.orderByProcessDefinitionVersion().asc()
					.list();
			if(isNewDefinition){
				//查询最新版本流程定义
				return getNewProcessDefinition(list);
			}else{
				//查询所有版本流程定义
				return list;
			}
			
		}
	}
	@Override
	public List<ProcessDefinition> queryDefinitionsByDefinitionId(String definitionId,
			boolean isNewDefinition) {
		java.util.List<ProcessDefinition> list=null;
		if(StringUtils.isNotEmpty(definitionId)){//根据流程定义ID获取流程定义
			list=engineService.getRepositoryService()
					.createProcessDefinitionQuery().processDefinitionId(definitionId)
					.orderByProcessDefinitionVersion().asc()//默认按升序
					.list();
			if(isNewDefinition){
				//查询最新版本流程定义
				return getNewProcessDefinition(list);
			}else{
				//查询所有版本流程定义
				return list;
			}
		}else{//获取所有的流程定义
			list=engineService.getRepositoryService()
					.createProcessDefinitionQuery()
					.orderByProcessDefinitionVersion().asc()
					.list();
			if(isNewDefinition){
				//查询最新版本流程定义
				return getNewProcessDefinition(list);
			}else{
				//查询所有版本流程定义
				return list;
			}
			
		}
	}
	
	//根据传入的流程定义列表筛选出最新版本的流程定义
	public List<ProcessDefinition> getNewProcessDefinition(List<ProcessDefinition> list){
		//查询最新版本流程定义
		Map<String,ProcessDefinition> map = new LinkedHashMap<String, ProcessDefinition>();
		if(list!=null && list.size()>0){
			for (ProcessDefinition processDefinition : list) {
				map.put(processDefinition.getKey(), processDefinition);
			}
		}
		List<ProcessDefinition> pdList=new ArrayList<ProcessDefinition>(map.values());
		return pdList;
	}

	@Override
	public void deleteProcessDefinitonByDeploymentId(String deploymentId,
			boolean cascade) {
		engineService.getRepositoryService().deleteDeployment(deploymentId, cascade);
		
	}

	@Override
	public void deleteProcessDefinitionByKey(String processDefinitionKey) {
		//1.先使用流程定义的key查询流程定义，查询除所有的版本
				List<ProcessDefinition> list = engineService.getRepositoryService()
												.createProcessDefinitionQuery()
												.processDefinitionKey(processDefinitionKey)
												.list();
				//遍历，获取每个流程顶一顶 部署Id
				if(list!=null && list.size()>0){
					for (ProcessDefinition processDefinition : list) {
						//获取部署ID
						String deploymentId = processDefinition.getDeploymentId();
						engineService.getRepositoryService().deleteDeployment(deploymentId,true);
					}
				}
	}

	@Override
	public String queryProcessDefinitionImage(String deploymentId,String outFilePath) {
		try {
			InputStream in=queryProcessDefinitionImageInputStream(deploymentId);
			if(in!=null){
				FileUtils.copyInputStreamToFile(in, new File(outFilePath));
			}
			return outFilePath;
		} catch (IOException e) {
			log.error("路径不存在"+outFilePath, e);
		}
		return null;
	}

	@Override
	public InputStream queryProcessDefinitionImageInputStream(String deploymentId) {
		//1.查询所有资源文件
		List<String> resource = engineService.getRepositoryService().getDeploymentResourceNames(deploymentId);
		String imageName =null;
		for (String string : resource) {
			if(string.indexOf(".png")>=0){
				imageName=string;
			}
		}
		if(imageName!=null){
			InputStream in=engineService.getRepositoryService().getResourceAsStream(deploymentId, imageName);
			return in;
		}
		return null;
	}
	
	
	
	
}