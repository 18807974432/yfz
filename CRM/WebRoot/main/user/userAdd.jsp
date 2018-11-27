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
    		
    		//验证基本工资是否数字
    		if(isNaN($("#baseSalary").val())){
    			alert("基本工资含有非法字符!");
    			$("#baseSalary").focus();
    			$("#baseSalary").val("0");
    			return false;
    		}
    		//验证部门工资是否数字
    		if(isNaN($("#degreeSalary").val())){
    			alert("部门工资含有非法字符!");
    			$("#degreeSalary").focus();
    			$("#degreeSalary").val("0");
    			return false;
    		}
    		//验证是否输入入职日期
    		if($("#joinDate").datebox("getValue")==""){
    			alert("请输入入职日期!");
    			$("#joinDate").focus();
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
	 <form action="<%=basePath%>usersServlet?action=userAdd" method="post" name="form1" onsubmit="return baocun();">
	 <table align="center" width="80%" align="center">
	 	<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">用户名称:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="username" id="username" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">登录密码:</label>
		   			<div class="layui-input-inline">
		     			<input type="password" name="password" id="password" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">身份证号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="cardno" id="cardno" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">所属部门:</label>
		   			<div class="layui-input-inline">
		     			<select name="depid" id="depid" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${depAll}" var="d">
		     					<option value="${d.depid}">${d.depname}</option>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">职务:</label>
		   			<div class="layui-input-inline">
		     			<select name="jobname" id="jobname" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${degreesAll}" var="d">
		     					<option value="${d.degreeid}">${d.degreename}</option>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">权限类别:</label>
		   			<div class="layui-input-inline">
		     			<select name="managerType" id="managerType" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<option value="0">系统管理员</option>
		   					<option value="1">总裁</option>
		   					<option value="2">部门经理</option>
		   					<option value="3">职员</option>
		     			</select>
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">基本工资:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="baseSalary" id="baseSalary" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">岗位工资:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="degreeSalary" id="degreeSalary" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr align="center" height="50">
 			<td align="center">
 				入职日期：<input type="text" name="joinDate" id="joinDate" style="width:200px;" placeholder="必填" class="easyui-datebox"">
 			</td>
 			<td align="center">
 				转正日期：<input type="text" name="workDate" id="workDate" style="width:200px;" class="easyui-datebox"">
 			</td>
 			<td align="center">
 				离职日期：<input type="text" name="levelDate" id="levelDate" style="width:200px;" class="easyui-datebox"">
 			</td>
 		</tr>
 		<tr height="10"></tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">开户银行:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="bankName" id="bankName" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">银行账号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="bankCardNo" id="bankCardNo" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">联系电话:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="mobile" id="mobile" autocomplete="off" class="layui-input">
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
		  			<label class="layui-form-label">QQ号码:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="qqcode" id="qqcode" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">微信号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="weixin" id="weixin" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">家庭地址:</label>
		   			<div class="layui-input-block">
		     			<input type="text" value=" " name="addr" id="addr" required  lay-verify="required" autocomplete="off" class="layui-input">
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
