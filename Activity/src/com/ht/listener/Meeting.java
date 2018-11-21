package com.ht.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class Meeting implements TaskListener{

	@Override
	public void notify(DelegateTask task) {
		//正常情况人员列表是从界面传递过来，或者通过业务逻辑计算出来
		task.addCandidateUser("部门经理");
		task.addCandidateUser("总经理");
		task.addCandidateUser("财务经理");
		task.addCandidateUser("董事长");
	}

}
