<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title> 流程列表  </title>   
	<script type="text/javascript">
		function del(){
			if(confirm('删除确认')){
				return true;
			}else{
				return false;
			}
		}
	</script>
  </head>
  
  <body>
  	  	 	
	  	 <table width="90%" cellspacing="1" bgcolor="#6495ED">
	  	 <tr bgcolor="#2F4F4F">
	  	 		<th colspan="2"><font color="white">流程定义列表</font></th>
	  	 	</tr>
	  	 	<tr bgcolor="#2F4F4F">
	  	 		<th><font color="white">流程ID</font></th>
	  	 		<th><font color="white">流程名称</font></th>
	  	 		<th><font color="white">流程KEY</font></th>
	  	 		<th>删除流程</th>
	  	 		<th>查看流程图</th>
	  	 		<th>下载流程图</th>
	  	 	</tr>
	  	 	<s:iterator value="#request.processList" var="pd">
	  	 		<tr bgcolor="white">
	  	 			<td>${pd.id }</td>
	  	 			<td>${pd.name }</td>
	  	 			<td>${pd.key }</td>
	  	 			<td><a href="loginAction_del?id=${pd.deploymentId}" onclick="return del();">删除流程</a></td>
	  	 			<td><a href="loginAction_viewImage?id=${pd.deploymentId}&imageName=${pd.diagramResourceName}">查看流程图</a>
	  	 			<td><a href="loginAction_toExport?id=${pd.id}">下载流程图</a>
	  	 			</td>
	  	 		</tr>
	  	 		
	  	 	</s:iterator>
	  	 </table>  	 
  </body>
</html>
