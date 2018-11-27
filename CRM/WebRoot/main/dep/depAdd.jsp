<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加部门</title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/main/layui/css/layui.css" mobile="all">

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
  	//返回
   	function back(){
   		location.href="${pageContext.request.contextPath }/DepServlet";
   	}
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
	<c:if test="${a eq 1}">
		<script type="text/javascript">
			alert("新增成功!");
		</script>
	</c:if>
	<c:if test="${a eq 2}">
		<script type="text/javascript">
			alert("新增失败，部门名称已存在!");
		</script>
	</c:if>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
	<link href="<%=basePath%>main/user/style/style.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="<%=basePath%>main/user/js/dh.js"></script>
	<div class="tools">
		<ul class="toolbar">
		<li class="click"><a href="javascript:void(0);" onclick="back();"><span><img src="main/images/fh.png"></span>返回</a></li>
	    </ul>
    </div>
	<br/><br/><br/>
	<form class="layui-form" action="<%=basePath%>/DepServlet?action=add" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">部门名称</label>
	    <div class="layui-input-inline">
	      <input type="text" id="depname" name="depname" required  lay-verify="required" placeholder="请输入部门名称" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">部门负责人</label>
	    <div class="layui-input-inline">
	      <input type="text" id="chairman" name="chairman" required  lay-verify="required" placeholder="请输入部门负责人"  class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item layui-form-text">
    	<label class="layui-form-label">部门描述</label>
	    <div class="layui-input-block">
	      <textarea id="description" name="description" placeholder="请输入内容" class="layui-textarea"></textarea>
	    </div>
  	  </div>
  	  <div class="layui-form-item">
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo" onclick="return check();">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div>
  	  </div>
	</form>
  </body>
</html>
