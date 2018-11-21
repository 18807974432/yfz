<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title> 客户服务申请  </title>
  </head>
  
  <body>
  	<c:form action="loginAction_add" theme="simple" method="post">
  		<c:hidden name="job.jobId"/>
  		<input type="hidden" name="job.jobType" value="${param.key}"/>
  		<c:hidden name="job.userId" value="%{#session.actorId}"/>
  		 <table width="90%" cellspacing="1" bgcolor="#6495ED">
	  	 	<tr bgcolor="white" align="center">
	  	 			<td colspan="2">客户服务申请</td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">参加客服人员：</td>
	  	 			<td ><c:textfield name="userIds" value="部门经理,总经理,财务经理,董事长" style="width:300px"/></td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
	  	 			<td align="right">评审事由：</td>
	  	 			<td ><c:textarea name="job.remark" style="width:300px;height:200px"></c:textarea></td>
	  	 	</tr>
	  	 	<tr bgcolor="white">
  	 			<td colspan="2" align="center">
  	 				<input type="submit" value="保存">
  	 			</td>
	  	 	</tr>
	  	 </table>  	 
  	</c:form>
  	
  </body>
</html>
