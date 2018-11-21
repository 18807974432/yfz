<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <ul>
    	<li><a href="uploadProcessDefinition.jsp" target="mainFrame">发布流程</a></li>
    	<li><a href="loginAction_deployList" target="mainFrame">查看流程</a></li>
    	<li><a href="${pageContext.request.contextPath }/loginAction_myJobList" target="mainFrame">我的申请单</a></li>
    	<li><a href="apply.jsp?key=qingJia" target="mainFrame">填请假单</a></li>
    	<li><a href="applyMeeting.jsp?key=meeting" target="mainFrame">填评审单</a></li>
    	<li><a href="applyTel.jsp?key=tel" target="mainFrame">填客服单</a></li>
    	<li><a href="loginAction_myTask" target="mainFrame">我的任务</a></li>
   		<li><a href="loginAction_exit" target="parent.top">注销</a></li>
    </ul>
  </body>
</html>
