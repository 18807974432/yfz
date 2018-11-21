package com.ht.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class AdminListener implements TaskListener{

	@Override
	public void notify(DelegateTask arg0) {
		arg0.setAssignee("总经理");
		
	}

}
