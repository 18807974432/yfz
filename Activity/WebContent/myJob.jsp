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
	  	 		<th colspan="2"><font color="white">我的申请单</font></th>
	  	 	</tr>
	  	 	<tr bgcolor="#2F4F4F">
	  	 		<th><font color="white">单据编号</font></th>
	  	 		<th><font color="white">单据名称</font></th>
	  	 		<th><font color="white">请假天数</font></th>
	  	 		<th><font color="white">金额</font></th>
	  	 		<th><font color="white">申请人</font></th>
	  	 		<th><font color="white">申请时间</font></th>
	  	 		<th><font color="white">审批状态</font></th>
	  	 		<th><font color="white">申请说明</font></th>
	  	 		<th><font color="white">查看批注</font></th>
	  	 		<th>办理进度</th>
	  	 	</tr>
	  	 	<s:iterator value="#request.jobList" var="t">
	  	 		<tr bgcolor="white">
	  	 			<td>${t.jobId }</td>
	  	 			<td>${t.jobName }</td>
	  	 			<td>${t.day }</td>
	  	 			<td>${t.money }</td>
	  	 			<td>${t.userId }</td>
	  	 			<td>
	  	 				<s:date name="jobDate" format="yyyy-MM-dd HH:mm:ss"/>
	  	 			</td>
	  	 			<td>
	  	 				<s:if test="%{#t.processFlag==0}">申请</s:if>
	  	 				<s:elseif test="%{#t.processFlag==1}">审批中</s:elseif>
	  	 				<s:elseif test="%{#t.processFlag==2}">审批通过</s:elseif>
	  	 			</td>
	  	 			<td>${t.remark }</td>
	  	 			<td><a href="${pageContext.request.contextPath }/loginAction_lookComment?job.jobId=${t.jobId}">查看批注</a></td>
	  	 			<td><a href="loginAction_lookTaskImg?job.jobId=${t.jobId}" target="_blank">办理进度</a></td>
	  	 			
	  	 		</tr>
	  	 	</s:iterator>
	  	 </table>  	 
  </body>
</html>
