<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title> 我的任务  </title>   
  </head>
  
  <body>
  	  	 	
	  	 <table width="90%" cellspacing="1" bgcolor="#6495ED">
	  	 <tr bgcolor="#2F4F4F">
	  	 		<th colspan="2"><font color="white">我的任务列表</font></th>
	  	 	</tr>
	  	 	<tr bgcolor="#2F4F4F">
	  	 		<th><font color="white">任务ID</font></th>
	  	 		<th><font color="white">实例ID</font></th>
	  	 		<th><font color="white">流程ID</font></th>
	  	 		<th><font color="white">任务名称</font></th>
	  	 		<th><font color="white">任务执行人</font></th>
	  	 		<th><font color="white">任务创建时间</font></th>
	  	 		<th>查看详情</th>
	  	 		<th>办理进度</th>
	  	 		
	  	 	</tr>
	  	 	<s:iterator value="#request.taskList" var="t">
	  	 		<tr bgcolor="white">
	  	 			<td>${t.id }</td>
	  	 			<td>${t.processInstanceId }</td>
	  	 			<td>${t.processDefinitionId }</td>
	  	 			<td>${t.name }</td>
	  	 			<td>${t.assignee }</td>
	  	 			<td>
	  	 				<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>
	  	 			</td>
	  	 			<td><a href="loginAction_taskDetail?taskId=${t.id}&instId=${t.processInstanceId}">查看详情</a></td>
	  	 			<td><a href="loginAction_lookTaskImg?instId=${t.processInstanceId}" target="_blank">办理进度</a></td>
	  	 		</tr>
	  	 		
	  	 	</s:iterator>
	  	 </table>  	 
  </body>
</html>
