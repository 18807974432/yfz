<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title> 审批信息  </title>   
  </head>
  
  <body>
  	  	 <a href="${pageContext.request.contextPath }/loginAction_myJobList">返回</a>
  	  	 <hr color="red"/>
	  	 <table width="90%" cellspacing="1" bgcolor="#6495ED">
	  	 <tr bgcolor="#2F4F4F">
	  	 		<th colspan="2"><font color="white">审批信息列表</font></th>
	  	 	</tr>
	  	 	<tr bgcolor="#2F4F4F">
	  	 		<th><font color="white">ID</font></th>
	  	 		<th><font color="white">审批时间</font></th>
	  	 		<th><font color="white">审批人</font></th>
	  	 		<th><font color="white">批注内容</font></th>
	  	 	</tr>
	  	 	<s:iterator value="#request.commentList" var="t">
	  	 		<tr bgcolor="white">
	  	 			<td>${t.id }</td>
	  	 			<td><s:date name="time" format="yyyy-MM-dd HH:mm:ss"/> </td>
	  	 			<td>${t.userId }</td>
	  	 			<td>${t.fullMessage }</td>
	  	 		</tr>
	  	 		
	  	 	</s:iterator>
	  	 </table>  	 
  </body>
</html>
