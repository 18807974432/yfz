<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门增加</title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/main/layui/css/layui.css" mobile="all">
	

  </head>
  <body>
   	<style type="text/css">
		table
		  {
		  	border-collapse:collapse;
		  }
	
		table, td, th
		  {
		 	 border:1px solid black;
		  }
	</style>
	<br/><br/>
	<form class="layui-form" action="<%=basePath%>/DepServlet?action=updateSave&depid=${dep.depid}" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">部门名称:</label>
	    <div class="layui-input-inline">
	      <input type="text" id="depname" name="depname" value="${dep.depname}" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
	    <label class="layui-form-label">部门负责人:</label>
	    <div class="layui-input-inline">
	      <input type="text" id="chairman" name="chairman" value="${dep.chairman}" required  lay-verify="required"  class="layui-input">
	    </div>
	  </div>
	  <div class="layui-form-item">
    	<label class="layui-form-label">部门描述:</label>
	    <div class="layui-input-block">
	      <textarea id="description" name="description" placeholder="请输入内容" class="layui-textarea">${dep.description}</textarea>
	    </div>
	   </div>
  	  <div class="layui-form-item">
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div> 	
  	  </div>
	</form>
  </body>
</html>
