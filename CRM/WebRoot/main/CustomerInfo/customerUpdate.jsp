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
    
    <title>客户资料修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <h1 align="center"><font color="red" style="隶书">客户资料修改</font></h1>
   <hr/>
 <form action="<%=basePath%>/customerServlet?action=updateSave&custId=${c.custId}"  method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">客户名称</label>
	    <div class="layui-input-inline">
	      <input type="text" id="custname" name="custname" value="${c.custname}" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">行业</label>
	    <div class="layui-input-inline">
	      <input type="text" id="custtype" name="custtype" value="${c.custtype}" required  lay-verify="required" placeholder="请输入" class="layui-input">
	    </div>
    	<label class="layui-form-label">银行账号</label>
	    <div class="layui-input-inline">
	      <input type="text" id="bankAccount" name="bankAccount" value="${c.bankAccount}" required  lay-verify="required" placeholder="请输入" class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">开户银行</label>
	    <div class="layui-input-inline">
	      <input type="text" id="bankName" name="bankName" value="${c.bankName}" required  lay-verify="required" placeholder="请输入名称" class="layui-input">
	    </div>
	    <label class="layui-form-label">联系人</label>
	    <div class="layui-input-inline">
	      <input type="text" id="contact" name="contact"  value="${c.contact}" placeholder="请输入" required  lay-verify="required" class="layui-input">
	    </div>
    	<label class="layui-form-label">电话</label>
	    <div class="layui-input-inline">
	      <input type="text" id="phone" name="phone" value="${c.phone}" placeholder="请输入电话号码" required  lay-verify="required" class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">开票时间</label>
	    <div class="layui-input-inline">
	      <input type="text" id="ticketName" name="ticketName" value="${c.ticketName}" required  lay-verify="required" placeholder="请输入名称"  class="layui-input">
	    </div>
	    <label class="layui-form-label">开票地址</label>
	    <div class="layui-input-inline">
	      <input type="text" id="ticketAddr" name="ticketAddr"  value="${c.ticketAddr}"  class="layui-input">
	    </div>
    	<label class="layui-form-label">开票电话</label>
	    <div class="layui-input-inline">
	      <input type="text" id="ticketTel" name="ticketTel" value="${c.ticketTel}" class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">纳税登记号</label>
	    <div class="layui-input-inline">
	      <input type="text" id="taxNo" name="taxNo" value="${c.taxNo}" class="layui-input">
	    </div>
	    <label class="layui-form-label">客户状态</label>
	    <div class="layui-input-inline">
	    <select name="custState" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<c:if test="${c.custState=='新客户'}">
		    	<option value="新客户" selected >新客户</option>
		        <option value="成交客户">成交客户</option>
	        </c:if>
	        <c:if test="${c.custState=='成交客户'}">
		    	<option value="新客户" >新客户</option>
		        <option value="成交客户" selected>成交客户</option>
	        </c:if>
	    </select>
	    </div>
    	<label class="layui-form-label">业务员</label>
	    <div class="layui-input-inline">
	      <input type="text" id="username" name="username" value="${c.username}" class="layui-input">
	    </div>
  	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">信息来源</label>
	    <div class="layui-input-block">
	      <textarea type="text" id="source" name="source"  required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input">${c.source}</textarea>
	    </div>
	  </div>
  	  <div class="layui-form-item">
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div>
  	  </div>	
  </body>
</html>

