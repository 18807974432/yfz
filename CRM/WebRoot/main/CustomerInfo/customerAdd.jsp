<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户增加</title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
  	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">

  <base href="<%=basePath%>">
    
    <title>新增部门资料</title>
	   
  </head>
  
  <body>
    <h1 align="center"><font color="red" style="隶书">增添客户</font></h1>
    <hr/>
    <form action="<%=basePath%>customerServlet?action=add" method="post">
	  <div class="layui-form-item">
	    <label class="layui-form-label">客户名称</label>
	    <div class="layui-input-inline">
	      <input type="text" id="custname" name="custname" required  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
	    </div>
	    <label class="layui-form-label">行业</label>
	    <div class="layui-input-inline">
	      <input type="text" id="custtype" name="custtype" required  lay-verify="required" placeholder="请输入" class="layui-input">
	    </div>
    	<label class="layui-form-label">银行账号</label>
	    <div class="layui-input-inline">
	      <input type="text" id="bankAccount" name="bankAccount" required  lay-verify="required" placeholder="请输入" class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
  	  	<label class="layui-form-label">开户银行</label>
	    <div class="layui-input-inline">
	      <input type="text" id="bankName" name="bankName" required  lay-verify="required" placeholder="请输入名称" class="layui-input">
	    </div>
	    <label class="layui-form-label">联系人</label>
	    <div class="layui-input-inline">
	      <input type="text" id="Contact" name="Contact"  placeholder="请输入" required  lay-verify="required" class="layui-input">
	    </div>
    	<label class="layui-form-label">电话</label>
	    <div class="layui-input-inline">
	      <input type="text" id="Phone" name="Phone" placeholder="请输入电话号码" required  lay-verify="required" class="layui-input">
	    </div>
	    
	  </div>
  	  <div class="layui-form-item">
    	<label class="layui-form-label">开票电话</label>
	    <div class="layui-input-inline">
	      <input type="text" id="TicketTel" name="TicketTel" class="layui-input">
	    </div>
	    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    开票时间：<input type="text" name="TicketName" id="TicketName" style="width:193px;" class="easyui-datebox"">
	    <label class="layui-form-label">开票地址</label>
	    <div class="layui-input-inline">
	      <input type="text" id="TicketAddr" name="TicketAddr"  value=" " class="layui-input">
	    </div>
	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">纳税登记号</label>
	    <div class="layui-input-inline">
	      <input type="text" id="TaxNo" name="TaxNo" class="layui-input">
	    </div>
	    <label class="layui-form-label">客户状态</label>
	    <div class="layui-input-inline">
	    <select name="custState" required lay-verify="required" class="layui-input" autocomplete="off">
	    	<option value="新客户">新客户</option>
	        <option value="成交客户">成交客户</option>
	    </select>
	    </div>
    	<label class="layui-form-label">业务员</label>
	    <div class="layui-input-inline">
	      <input type="text" id="username" name="username" class="layui-input">
	    </div>
  	  </div>
  	  <div class="layui-form-item">
	    <label class="layui-form-label">信息来源</label>
	    <div class="layui-input-block">
	      <textarea type="text" id="source" name="source" required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input"></textarea>
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
