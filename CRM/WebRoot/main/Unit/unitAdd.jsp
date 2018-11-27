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
    
    <title>单位增加</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
	

  </head>
  <script type="text/javascript">
  	$(function(){
  		$("#rename").click(function(){
   			$("#name").val("");
   			$("#title").val("");
   			$("#desc").val("");
   			$("#description").val("");
    	});
  	});
  	
  </script>
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
	<form class="layui-form" action="<%=basePath%>/UnitServlet?action=add" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">单位名称</label>
	    <div class="layui-input-inline">
	      <input type="text" id="unitName" name="unitName" required lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
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
