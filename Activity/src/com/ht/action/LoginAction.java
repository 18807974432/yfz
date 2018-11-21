package com.ht.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormData;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.VariableInstance;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.rest.service.api.runtime.process.ProcessInstanceDiagramResource;
import org.activiti.workflow.simple.definition.WorkflowDefinition;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.ht.base.IBaseDAO;
import com.ht.base.WebApplicationContextUtil;
import com.ht.vo.JobsVo;
import com.ht.vo.MapBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{

//	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	//获取流程引擎
	private ProcessEngine processEngine = (ProcessEngine)WebApplicationContextUtil.createService("processEngine");
	private IBaseDAO base = (IBaseDAO)WebApplicationContextUtil.createService("baseDAO");
	
	private ProcessDefinition pd;

	private List<ProcessDefinition> processList;
	private JobsVo job;
	private String id;
	private String imageName;
	private String url;
	//并行网关指定评审人员
	private String userIds;
	
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	//审批意见
	private String comment;
	//批注信息列表
	private List<Comment> commentList;
	
	//查看节点流程图
	private List mapList;
	//连线集合
	private List<MapBean> pvmList;
	//流程名称
	private String flow;
	//我的申请单
	public List<JobsVo> jobList;
	//我的任务
	private List<Task> taskList;
	
	//查看任务明细
	private String taskId; //任务id
	private String instId; //实例id

	
	public ProcessDefinition getPd() {
		return pd;
	}

	public void setPd(ProcessDefinition pd) {
		this.pd = pd;
	}

	public List getMapList() {
		return mapList;
	}

	public void setMapList(List mapList) {
		this.mapList = mapList;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<JobsVo> getJobList() {
		return jobList;
	}

	public void setJobList(List<JobsVo> jobList) {
		this.jobList = jobList;
	}

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	public List<MapBean> getPvmList() {
		return pvmList;
	}

	public void setPvmList(List<MapBean> pvmList) {
		this.pvmList = pvmList;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getInstId() {
		return instId;
	}

	public void setInstId(String instId) {
		this.instId = instId;
	}

	
	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobsVo getJob() {
		return job;
	}

	public void setJob(JobsVo job) {
		this.job = job;
	}

	public List<ProcessDefinition> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessDefinition> processList) {
		this.processList = processList;
	}

	private String actorId;
	private File pdFile;//上传流程文件
	public File getPdFile() {
		return pdFile;
	}

	public void setPdFile(File pdFile) {
		this.pdFile = pdFile;
	}

	public String getPdname() {
		return pdname;
	}

	public void setPdname(String pdname) {
		this.pdname = pdname;
	}

	private String pdname;//上传流程的名称

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String login() throws Exception{
		
		//设置session
		ActionContext.getContext().getSession().put("actorId", actorId);
//		ServletActionContext.getRequest().getSession().setAttribute("actorId", actorId);
		return SUCCESS;
	}
    /**
     * 2、创建流程定义，画流程图
     * 3、流程部署，告诉activiti哪里有流程图和xml文件
     *  act_ge_bytearray
     *  act_re_deployment   对应于Deployment对象
     *  act_re_procdef    流程定义表，保存有流程的名字，key,流程描述信息
     */

	public String upload() throws Exception{
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // Deployment deployment = repositoryService.createDeployment().addClasspathResource("com/ht.flow/qingjia.bpmn.xml").deploy(); // 去读取src根目录下的流程定义文件并部署到activiti中
        try {
            // 通过zip文件来部署
            Deployment deployment = repositoryService.createDeployment().
                    addZipInputStream(new ZipInputStream(new FileInputStream(pdFile))).deploy();
            System.out.println(deployment.getId() + deployment.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //查询发布成功的流程列表
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        //查看所有流程的最新版本
        query.latestVersion();
        // 按照版本升序排序
		query.orderByProcessDefinitionVersion().desc();
		processList = query.list();
		return "upload";
	}
	//查看流程图
	public String viewImage(){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		//读取流程图的流程图片
		InputStream  in = repositoryService.getResourceAsStream(id,imageName);
		//把图片打印到网页
		//获取response对象
        HttpServletResponse resp = ServletActionContext.getResponse();
        try {
            OutputStream out = resp.getOutputStream();
            // 把图片的输入流程写入resp的输出流中
            byte[] b = new byte[1024];
            for (int len = -1; (len= in.read(b))!=-1; ) {
                out.write(b, 0, len);
            }
            // 关闭流
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
	 /** 
     * 获取流程图像，已执行节点和流程线高亮显示
     */
    public void currentTaskImg() throws Exception {
    	 HttpServletResponse response = ServletActionContext.getResponse();
        // 设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
        	HistoryService historyService = processEngine.getHistoryService();

        	//通过单据id查找实例对象
        	HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("jobId", job.getJobId()).singleResult();

            //  获取历史流程实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(hvi.getProcessInstanceId()).singleResult();

            if (historicProcessInstance == null) {
                throw new Exception();
            } else {
        		RepositoryService repositoryService = processEngine.getRepositoryService();
        		// 获取流程定义
//        		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
//                        .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());
//        		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
                // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
                String processInstanceId = historicProcessInstance.getId();
        		List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
                // 已执行的节点ID集合
                List<String> executedActivityIdList = new ArrayList<String>();
                int index = 1;
                //获取已经执行的节点ID
                for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                	System.out.println("id="+activityInstance.getId()+",id2="+activityInstance.getTaskId()+",name="+activityInstance.getActivityId());
                    executedActivityIdList.add(activityInstance.getActivityId());
                    index++;
                }
                ProcessDiagramGenerator diagramGenerator = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();        
                //获取流程图        
                BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());        
                
                // 获取流程图图像字符流
                InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", executedActivityIdList);

                response.setContentType("image/png");
                OutputStream os = response.getOutputStream();
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = imageStream.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                imageStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	//删除流程
	//id是act_re_procdef表中DEPLOYMENT_ID_字段的值，也就是act_re_deployment表中对应的主键值（ID_）
	public String del() throws Exception{
		RepositoryService repositoryService = processEngine.getRepositoryService();
//		repositoryService.deleteDeployment(id);
		//级联删除相关的所有信息，包括正在执行的流程，及已经执行结束的历史流程
		repositoryService.deleteDeployment(id,true);
		
		return deployList();
	}
	//退出系统
	public String exit() throws Exception{
		
		ServletActionContext.getRequest().getSession().invalidate();
		return "exit";
	}
	//查看流程列表
	public String deployList() throws Exception{
		
		//查看所有流程的所有版本
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();

		//指定流程的key进行查询
//		query.processDefinitionKey("myProcess");
		//查询所有流程最新版本 
//		query.latestVersion();
		// 按照版本升序排序
		query.orderByProcessDefinitionVersion().desc();
		processList = query.list();
		return "upload";
	}
	/**
	 * 根据key删除流程定义
	 */
	public void deleteByKey(String key) {
		// 根据key查询流程定义列表
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		query.processDefinitionKey(key);
//		query.processDefinitionId("");
		List<ProcessDefinition> list = query.list();
		for (ProcessDefinition pd : list) {
			String deploymentId = pd.getDeploymentId();
			processEngine.getRepositoryService().deleteDeployment(deploymentId,
					true);
		}
	}
	/**
	 * 根据Id删除流程定义,不用
	 */
	public void deleteById(String id) {
		// 根据key查询流程定义列表
		ProcessDefinitionQuery query = processEngine.getRepositoryService()
				.createProcessDefinitionQuery();
		query.processDefinitionId(id);
		List<ProcessDefinition> list = query.list();
		for (ProcessDefinition pd : list) {
			String deploymentId = pd.getDeploymentId();
			processEngine.getRepositoryService().deleteDeployment(deploymentId,
					true);
		}
	}
    /**
     * 4、启动流程
     *
     * act_hi_actinst   activity实例记录   activity  :事件，任务，边界，网关
     * act_hi_porcinst  流程实例记录
     * act_hi_taskinst  任务实例记录
     *
     * act_ru_execution  流程实例中已经做过的和未做的Activity
     * act_ru_task     当前即将要执行的任务
     * act_ru_variable  当前即将要执行的任务关联的变量数据
     */

    
    public String add() throws Exception{
    	
//    	HttpServletRequest request = ServletActionContext.getRequest();
//    	request.setCharacterEncoding("utf-8");
    	
    	job.setMoney(0);
    	if(job.getJobType().equals("qingJia")){
    		job.setJobName("请假单");
    	}else if(job.getJobType().equals("meeting")){
    		job.setJobName("评审单");
    	}else if(job.getJobType().equals("tel")){
    		job.setJobName("客户服务");
    	}
    	job.setJobDate(new Date());
    	job.setProcessFlag(1);//审核中
    	base.saveOrUpdate(job);
    	
    	//设置流程变量
    	Map<String, Object> variables = new HashMap<String, Object>();
    	variables.put("userId", job.getUserId());
    	//设置并行网关任务节点的执行人（多人）
//    	variables.put("assignee", job.getUserId());
    	//专家评审流程需要使用的参数必须是集合（指定参与人员列表），不能用字符串数组,一般用List比较多
    	/*
    	 * 1.Collection:      变量名称必须与流程图中的Collection的变量名一致assigneeList
    	 * 2.Element variable:流程中要定义一个集合变量，这个变量直接在流程图中定义assignee
    	 * 3.Assignee：                   任务执行者用Element variable变量${assignee}
    	 * 4.sequential:      false表示并行，true表示串行
    	 * 5.completion condition:设置完成当前任务的百分比nrOfCompletedInstances/nrOfInstances>0.6
    	 * 		nrOfInstances：			实例的数量即参与投票的人数
    	 * 		nrOfCompletedInstances: 已完成的数量即已投票的人数
    	 * 		nrOfActiveInstances:    未完成的数量即未投票的人数
    	 * 		loopCounter：			循环次数
    	 * */
    	variables.put("assigneeList", Arrays.asList(userIds.split(",")));
    	/*
    	 * Tel客户服务流程,动态生成多个任务，但是只允许其中一个参与执行（抢任务）
    	 * 必须设置一个用户集合（多个用户的字符串，用户之间用逗号分隔）
    	 * */
    	variables.put("userIds", userIds);
    	//设置请假天数变量，<=3天经理审批，>3天总经理审批
    	variables.put("day", job.getDay());
    	variables.put("jobId", job.getJobId());
    	//创建运行服务
    	RuntimeService runtimeService = processEngine.getRuntimeService();
    	//启动流程
    	ProcessInstance ins =  runtimeService.startProcessInstanceByKey(job.getJobType(),variables);
    	//把业务id设置到流程的BUSINESS_KEY中
//    	ProcessInstance ins =  runtimeService.startProcessInstanceByKey(job.getJobType(),job.getJobId()+"",variables);
    	
    	
    	//获取启动流程中当前需要办理的任务
    	Task task = processEngine.getTaskService().createTaskQuery().processInstanceId(ins.getProcessInstanceId()).orderByProcessInstanceId().desc().singleResult();
    	
    	//获取FormKey的值
    	url = task.getFormKey();
    	//结束当前任务
    	processEngine.getTaskService().complete(task.getId(),variables);
    	
//        for (Task task : taskList) {
//            System.out.println(task.getId() + ", " + task.getName());
//            taskService.complete(task.getId());
//        }
    	
    	
    	System.out.println("申请成功......");
    	
    	return myTask();
    }
    //获取当需要前用户办理的任务列表
    public String myTask() throws Exception{
    	//获取当前登录的用户id
    	String userid = ActionContext.getContext().getSession().get("actorId").toString();
    	TaskService taskService = processEngine.getTaskService();
    	//查看当前所以需要办理的任务列表
//    	taskList = taskService.createTaskQuery().list();
    	//获取当需要前用户办理的任务列表
    	taskList = taskService.createTaskQuery().taskAssignee(userid).list();
    	//认领任务（抢任务）
    	/*
    	 * 当使用客户服务流程时，因接电话的节点存在多个任务，但是只能由速度最快的其中一个用户认领任务
    	 * 
    	 * */
    	//查询当前登录的用户是否有执行该任务权限，如果有就查询出来
    	List<Task> tList = taskService.createTaskQuery().taskCandidateUser(userid).list();
    	for (Task task : tList) {
    		//认领任务
    		taskService.claim(task.getId(),userid );
    		taskList.add(task);
		}
    	
    	//获取当前用户办理任务列表，按创建时间升序显示数据
//    	taskList = taskService.createTaskQuery().taskAssignee(userid).orderByTaskCreateTime().asc().list();
		
		return "myTask";
	}
    //查看任务明细
    public String taskDetail(){
    	//创建运行服务
    	RuntimeService runtimeService = processEngine.getRuntimeService();
    	
    	TaskService taskService = processEngine.getTaskService();

    	ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(instId).singleResult();
    	
    	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    	//获取历史审批批注信息
    	commentList = taskService.getProcessInstanceComments(task.getProcessInstanceId());
    	
    	//获取流程定义id
    	String processDefineId = task.getProcessDefinitionId();
    	//查询流程定义实体对象
    	ProcessDefinitionEntity pdentity = (ProcessDefinitionEntity)processEngine.getRepositoryService().getProcessDefinition(processDefineId);
    	//获取当前活动id
    	String activeId = pi.getActivityId();
    	//获取当前活动
    	ActivityImpl impl = pdentity.findActivity(activeId);
    	//获取当前活动结束之后连线的名称
    	pvmList = new ArrayList<MapBean>();
    	//如果是专家评审等需要用系统自动添加Task的流程，当前活动节点有可能为空
    	if(impl==null){
    		MapBean map = new MapBean();
    		map.setId("0");
			map.setName("审批");
			pvmList.add(map);
    	}else{
        	List<PvmTransition> plist = impl.getOutgoingTransitions();
	    	for (PvmTransition pvm : plist) {
	//			System.out.println("id="+pvm.getId()+";name="+pvm.getProperty("name"));
	    		MapBean map = new MapBean();
	    		if(pvm.getProperty("name")==null){
	        		map.setId("0");
	    			map.setName("审批");
	    		}else{
	        		map.setId(pvm.getId());
	    			map.setName(pvm.getProperty("name")+"");
	    		}
	    		pvmList.add(map);
	//			pvmList.add(new MapBean(pvm.getId(),pvm.getProperty("name").toString()));
			}
    	}
    	int jobId =  Integer.parseInt(taskService.getVariable(taskId, "jobId").toString());
    	System.out.println("userid="+taskService.getVariable(taskId, "userId"));
    	
    	System.out.println("jobid="+jobId);
    	
    	//取出请假单数据
    	job = (JobsVo)base.findObjById(JobsVo.class, jobId);
    
    	return "adult";
    } 
    //审批
    public String complete() throws Exception{
    	TaskService taskService = processEngine.getTaskService();
    	//获取当前Task对象
    	Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
    	//获取当前实例对象Id
    	String processId = task.getProcessInstanceId();
		//获取jobId变量
		 int jobId = (Integer)taskService.getVariable(task.getId(),"jobId");
		 JobsVo job = (JobsVo)base.findObjById(JobsVo.class, jobId);
    	//获取当前登录的用户id
    	String userid = ActionContext.getContext().getSession().get("actorId").toString();
		 //添加当前任务的办理人
		 Authentication.setAuthenticatedUserId(userid);
    	//添加审批意见
    	taskService.addComment(taskId,processId,comment);
    	//添加任务变量
    	Map<String, Object> var = new HashMap<String, Object>();
    	var.put("flow", flow);
//    	var.put("assignee", userid);
    	//完成当前任务
    	taskService.complete(taskId,var);
    	//判断当前流程是否已经审批结束
    	ProcessInstance pi = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processId).singleResult();
    	
    	
    	//如果pi==null表示流程审批结束
    	if(pi==null){
    		//更新job表的状态为2
    		 job.setProcessFlag(2);
    		 base.saveOrUpdate(job);
    	}
    	return myTask();
    }
    //我的申请单
    public String myJobList() throws Exception{
    	//获取当前登录的用户id
    	String userid = ActionContext.getContext().getSession().get("actorId").toString();
    	
    	DetachedCriteria dc = DetachedCriteria.forClass(JobsVo.class);
    	dc.add(Restrictions.eq("userId", userid));
    	jobList = base.findByDetach(dc);
    	return "myJob";
    	
    }
    //在我的申请单中查看批注
    public String lookComment() throws Exception{
    	//获取历史服务对象
    	HistoryService historyService= processEngine.getHistoryService();
    	//通过单据id查找实例对象
    	HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("jobId", job.getJobId()).singleResult();
    	//获取流程实例id
    	String piId = hvi.getProcessInstanceId();
    	//通过实例id查找批注信息列表
    	commentList = processEngine.getTaskService().getProcessInstanceComments(piId);
//    	for (Comment comment : commentList) {
//			System.out.println(comment.getId()+","+comment.getTime()+","+comment.getUserId()+","+comment.getFullMessage());
//		}
    	
    	return "comment";
    }
	 /** 
     * 获取流程图像，已执行节点和流程线高亮显示
     */
    public String lookTaskImg() throws Exception {
        try {
        	HistoryService historyService = processEngine.getHistoryService();
        	/**
        	 * 1.在我的任务中传递的参数是instId（实例id）
        	 * 2.在我的申请单中传递的参数是job.jobId(单据id)
        	 * */
        	//获取流程id
        	String processInstanceId = instId;
        	if(processInstanceId==null){
	        	//通过单据id查找实例对象
	        	HistoricVariableInstance hvi = historyService.createHistoricVariableInstanceQuery().variableValueEquals("jobId", job.getJobId()).singleResult();
	        	processInstanceId = hvi.getProcessInstanceId();
        	}
            //  获取历史流程实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId).singleResult();

            if (historicProcessInstance == null) {
                throw new Exception();
            } else {
        		RepositoryService repositoryService = processEngine.getRepositoryService();
        		//获取流程定义信息
        		pd = repositoryService.getProcessDefinition(historicProcessInstance.getProcessDefinitionId());
        		// 获取流程定义的实体（包含了流程中的任务节点信息，连线信息）
        		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity)pd;
                // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
        		List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                        .processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
                // 已经激活的节点ID集合
        		//激活的节点（1.任务已经完成；2.任务已经开始，但还未结束）
                mapList = new ArrayList();
                //获取已经激活的节点ID
                for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                	//getActivityId方法获取已经激活的节点id
                	ActivityImpl activityImpl = processDefinition.findActivity(activityInstance.getActivityId());
            		//获取当前节点在图片中的坐标位置，左上角坐标及长宽
                	int x = activityImpl.getX();
            		int y = activityImpl.getY();
            		int height = activityImpl.getHeight();
            		int width = activityImpl.getWidth();
            		Map<String, Object> map = new HashMap<String, Object>();
            		map.put("x", x);
            		map.put("y", y);
            		map.put("height", height);
            		map.put("width", width);
            		mapList.add(map);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "image";
    }
	/**
	 * 导出流程定义文件ZIP,必须使用ZIP文件上传的才能用ZIP下载，否则会报错
	 * @return
	 */
	public String toExport() throws Exception{
		try {
			//获取response对象
			HttpServletResponse resp =  ServletActionContext.getResponse();
			//设置response对象的头参数，attachment就是附件，filename=文件名称
			resp.setHeader("Content-disposition","attachment;filename=" +id+".zip" );
			//下载的文件类型是zip文件
			resp.setContentType("application/x-zip-compressed");

			//----------------------------------------------------------------------------
			//得到repositoryService
			RepositoryService repositoryService = processEngine.getRepositoryService();
			
			ProcessDefinition processDefinition = repositoryService
					.createProcessDefinitionQuery()
					.processDefinitionId(id).singleResult();
			//部署id
			String deploymentId = processDefinition.getDeploymentId();
			
			//bpmn资源文件名称
			String resourceName_bpmn = processDefinition.getResourceName();
			//bpmn资源文件输入流
			InputStream inputStream_bpmn = repositoryService.getResourceAsStream(deploymentId, resourceName_bpmn);
			//png文件名称
			String resourceName_png = processDefinition.getDiagramResourceName();
			//png资源文件输入流
			InputStream inputStream_png = repositoryService.getResourceAsStream(deploymentId, resourceName_png);

			//------创建输出流，绑定到response对象-------------------------------------------------------
			OutputStream out = resp.getOutputStream();
			//创建ZIP输出对象，绑定到输出流
			ZipOutputStream zipo = new ZipOutputStream(out);
			
			//流复制
			byte[] b = new byte[1024];
			int len = -1;
			
			//定义zip压缩包中的文件对象（zip实体）
			ZipEntry ze = new ZipEntry(resourceName_bpmn);
			//把创建的实体对象放到压缩包中
			zipo.putNextEntry(ze);
			//文件内容拷贝
			while((len = inputStream_bpmn.read(b,0,1024)) != -1){
				zipo.write(b,0,b.length);
			}
			zipo.closeEntry();
			//---------------
			ZipEntry ze1 = new ZipEntry(resourceName_png);
			zipo.putNextEntry(ze1);
			while((len = inputStream_png.read(b,0,1024)) != -1){
				zipo.write(b,0,b.length);
			}
			//关闭流
			inputStream_bpmn.close();
			inputStream_png.close();
			zipo.flush();
			zipo.close();
			out.flush();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null; 
		
	}
}
