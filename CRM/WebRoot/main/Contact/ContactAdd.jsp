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
    
    <title>新增用户</title>
    <script type="text/javascript">
    	function baocun(){
    		//获取时间
    		//alert($("#joinDate").datebox("getValue"));
    		//获取下拉框内容,获取到的是索引值
    		//alert($("#managerType").val());
    		//获取文本框内容
    		//alert($("#username").val());
    		
    		//验证QQ是否数字
    		if(isNaN($("#qqCode").val())){
    			alert("QQ含有非法字符!");
    			$("#qqCode").focus();
    			return false;
    		}
    		
    		//验证是否输入日期
    		if($("#talkDate").datebox("getValue")==""){
    			alert("请输入日期!");
    			$("#talkDate").focus();
    			return false;
    		}
    	}
    </script>
  </head>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
  <link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
  <body>
	 <br/><br/>
	 <form action="<%=basePath%>contactServlet?action=add" method="post" name="form1" onsubmit="return baocun();">
	 <table align="center" width="80%" align="center">
	 	<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			日期：<input type="text" name="talkDate" id="talkDate" style="width:200px;" placeholder="必填" class="easyui-datebox"">
 			
		   			<label class="layui-form-label">联系人:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="custContact" id="custContact" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">手机1:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="phone1" id="phone1" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">手机2:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="phone2" id="phone2" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">客户名称:</label>
		   			<div class="layui-input-inline">
		     			<select name="custid" id="custid" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${custAll}" var="d">
		     					<option value="${d.custId}">${d.custname}</option>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">QQ:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="qqCode" id="qqCode" id="custContact" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">邮箱地址:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="email" id="email" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">微信号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="weixin" id="weixin" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">员工信息:</label>
		   			<div class="layui-input-inline">
		     			<select name="userid" id="userid" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${userAll}" var="d">
		     					<option value="${d.userid}">${d.username}</option>
		     				</c:forEach>
		     			</select>
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">生日:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="birthday" id="birthday" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">喜爱:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="hobbit" id="hobbit" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">岗位:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="jobName" id="jobName" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		   			<label class="layui-form-label">备注:</label>
		   			<div class="layui-input-block">
		     			<input type="text" name="remark" id="remark" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
 			<td align="center">
 				<div class="layui-form-item">
				   	<div class="layui-input-block">
				   	  <input type="submit" value="保存" class="layui-btn layui-btn-primary"/>
				      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
				   	</div>
  				</div>
 			</td>
 		</tr>
 	</table>
 	</form>
  </body>
</html>
