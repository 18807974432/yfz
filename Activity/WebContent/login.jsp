<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
		<h1 align="center">系统登录</h1>
		<hr color="red">
		<s:form action="loginAction_login" method="post">
			<s:select list="#{'员工':'员工','部门经理':'部门经理','财务经理':'财务经理','总经理':'总经理','董事长':'董事长','张三':'张三','李四':'李四','王五':'王五'}" listKey="key" listValue="value" label="用户名称" name="actorId"></s:select>
			<s:submit value="登录"></s:submit>
		</s:form>    

  </body>
</html>
