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
    
    <title>订单增加</title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
    <link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1 align="center"><font color="red" face="隶书">订单增加</font></h1>
   	<hr/>
   		 <form action="<%=basePath%>OrdersServlet?action=add" method="post" name="form1" onsubmit="return baocun();">
	  <div class="layui-form-item">
	    <label class="layui-form-label">订单编号</label>
	    <div class="layui-input-inline">
	    	 <input type="text" id="orderId" name="orderId" required  lay-verify="required" placeholder="请输入"  class="layui-input">
	    </div>
	    <label class="layui-form-label">客户编号</label>
	    <div class="layui-input-inline">
	    <select name="custid" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<c:forEach items="${customerList}" var="c">
	    		<option value="${c.custId}">${c.custname}</option>
	    	</c:forEach>
	    	</select>
	    </div>
    	<label class="layui-form-label">业务员</label>
	    <div class="layui-input-inline">
	      <select name="userid" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<c:forEach items="${userList}" var="u">
	    		<option value="${u.userid}">${u.username}</option>
	    	</c:forEach>
	    	</select></div>
	   </div>
	   <div class="layui-form-item">
	    <label class="layui-form-label">订单类别</label>
	    <div class="layui-input-inline">
	    <select name="orderType" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<option value="采购入库">采购入库</option>
	        <option value="销售出库">销售出库</option>
	    </select>
	    </div>
  	    <label class="layui-form-label">订单状态</label>
	    <div class="layui-input-inline">
	    <select name="orderStatus" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<option value="已发货">已发货</option>
	        <option value="已收款">已收款</option>
	        <option value="实施中">实施中</option>
	    </select>
	    </div>
    	<label class="layui-form-label">进度</label>
	    <div class="layui-input-inline">
	      <input type="text" id="process" name="process"   class="layui-input">
	    </div>
	   </div>
	    <div class="layui-form-item">
	    <label class="layui-form-label">订单金额</label>
	    <div class="layui-input-inline">
	      <input type="text" id="totalMoney" name="totalMoney" required  lay-verify="required" placeholder="请输入名称"  class="layui-input">
	    </div>
	    
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    开票时间：<input type="text" name="oprtime" id="oprtime" style="width:200px;" class="easyui-datebox"">
    	
    	<label class="layui-form-label">开票人</label>
	    <div class="layui-input-inline">
	      <input type="text" id="operator" name="operator" placeholder="请输入" required lay-verify="required" class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
	   <div class="layui-form-item">
	    <label class="layui-form-label">描述</label>
	    <div class="layui-input-block">
	      <textarea type="text" id="remark" name="remark" required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input"></textarea>
	    </div>
	  </div>
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div>
  	  </div>
  	  </form>	
  </body>
</html>
