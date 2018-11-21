package com.ht.action;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

public class Deploy {
		// 部署流程定义
	public static void main(String[] args) {
		Deploy deploy = new Deploy();
//		deploy.deploy();
//		deploy.deployList();
//		deploy.startInstance();
//		deploy.searchTask();
		deploy.dealTask();
	}
	public void deploy(){
		// 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		// 获得一个部署对象，用于加载流程定义文件，完成流程定义的部署
		DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
		// 加载流程定义文件
		deploymentBuilder.addClasspathResource("com/ht/flow/qingjia.bpmn");
		// 部署流程定义
		Deployment deploy = deploymentBuilder.deploy();
		System.out.println(deploy.getId() + "---" + deploy.getDeploymentTime());

	}
	// 查询流程定义列表（查询act_re_procdef表）
	public void deployList(){
		// 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		
		// 添加过滤条件
		// query.processDefinitionId("qingjia:1:4");
		
		// 排序
		query.orderByProcessDefinitionVersion().desc();
		
		// 分页
		query.listPage(0, 2);
		
		List<ProcessDefinition> list = query.list();
		
		for (ProcessDefinition pd : list) {
			System.out.println(pd.getName() + "---" + pd.getVersion() + "---" + pd.getKey());
		}
	}
	// 启动流程实例
	public void startInstance() {
		// 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById("qingJia:1:4");
		
		System.out.println(processInstance.getId() + "---" + processInstance.getName());
	}
	// 查询任务列表
	public void searchTask() {
		// 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		
		// 指定办理人查询他的任务
		query.taskAssignee("张三");
		
		List<Task> list = query.list();
		
		for (Task task : list) {
			System.out.println(task.getId() + "---" + task.getName());
		}
	}
	// 办理任务
	public void dealTask() {
		// 创建流程引擎
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		processEngine.getTaskService().complete("2504");
	}
}
