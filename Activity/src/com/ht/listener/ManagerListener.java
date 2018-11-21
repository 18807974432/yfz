package com.ht.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.struts2.ServletActionContext;

//经理审批监听器
public class ManagerListener implements TaskListener{

	
	
	//	'员工':'员工','部门经理':'部门经理','财务经理':'财务经理','总经理':'总经理','董事长':'董事长','张三':'张三','李四':'李四','王五':'王五'
	//实现notify方法
	@Override
	public void notify(DelegateTask delegateTask) {
		//获取当前用户，根据业务规则动态设置当前节点的执行人
		String userid = ServletActionContext.getRequest().getSession().getAttribute("actorId").toString();
		if(userid.equals("员工") || userid.equals("张三") ){
			delegateTask.setAssignee("部门经理");
		}else if(userid.equals("李四") || userid.equals("王五")){
			delegateTask.setAssignee("财务经理");
		}else if(userid.equals("部门经理") || userid.equals("财务经理")){
			delegateTask.setAssignee("总经理");
		}else if(userid.equals("总经理")){
			delegateTask.setAssignee("董事长");
		}
	}

}
