<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title> 审核请假申请  </title>
    

  </head>
  
  <body>
  <h2 align="center">审核请假申请</h2>
  <hr/>
  	<c:form action="loginAction_complete" theme="simple" method="post">
  		<c:hidden name="job.jobId"/>
  		<c:hidden name="taskId"/>
  		 <table width="90%" cellspacing="1" bgcolor="#6495ED">
  		 	<tr>
  		 		<td align="right">请假人</td>
  		 		<td>${job.userId}</td>
  		 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">请假天数：</td>
	  	 			<td >${job.day}</td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">填单时间：</td>
	  	 			<td ><c:date name="job.jobDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">请假事由：</td>
	  	 			<td >${job.remark}</td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">是否批准：</td>
	  	 			<td ><c:select name="flow" list="#request.pvmList" listKey="name" listValue="name"/></td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">审批意见：</td>
	  	 			<td ><c:textfield name="comment"/></td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
  	 			<td colspan="2" align="center">
  	 				<input type="submit" value="审批">
  	 			</td>
	  	 	</tr>
	  	 </table>  	 
  	</c:form>
  	
  	  	<hr/>
  	<table width="90%" cellspacing="1" bgcolor="#6495ED">
	  	 <tr bgcolor="#2F4F4F">
	  	 		<th colspan="2"><font color="white">历史审批信息</font></th>
	  	 	</tr>
	  	 	<tr bgcolor="#2F4F4F">
	  	 		<th><font color="white">ID</font></th>
	  	 		<th><font color="white">审批时间</font></th>
	  	 		<th><font color="white">审批人</font></th>
	  	 		<th><font color="white">批注内容</font></th>
	  	 	</tr>
	  	 	<c:iterator value="#request.commentList" var="t">
	  	 		<tr bgcolor="white">
	  	 			<td>${t.id }</td>
	  	 			<td><c:date name="time" format="yyyy-MM-dd HH:mm:ss"/> </td>
	  	 			<td>${t.userId }</td>
	  	 			<td>${t.fullMessage }</td>
	  	 		</tr>
	  	 		
	  	 	</c:iterator>
	  	 </table>  	
  </body>
</html>
