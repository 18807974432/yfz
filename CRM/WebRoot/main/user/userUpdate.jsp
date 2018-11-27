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
    
    <title>修改用户资料</title>
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
    		return true;
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
	 <form action="<%=basePath%>usersServlet?action=UpdateSave&userid=${user.userid}" method="post" name="form1" onsubmit="return baocun();">
	 <table align="center" width="80%" align="center">
	 	<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">用户名称:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" readonly="readonly" name="username" id="username" required  lay-verify="required" value="${user.username}" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">身份证号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" readonly="readonly" name="cardno" id="cardno" required  lay-verify="required" value="${user.cardno}" placeholder="必填" autocomplete="off" class="layui-input">
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
		     					<c:if test="${user.depid==d.depid}">
    								<option value="${d.depid}" selected>${d.depname}</option>
    							</c:if>
    							<c:if test="${user.depid!=d.depid}">
	    							<option value="${d.depid}">${d.depname}</option>
	    						</c:if>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">职务${user.degreeid}:</label>
		   			<div class="layui-input-inline">
		     			<select name="jobname" id="jobname" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${degreesAll}" var="d">
		     					<c:if test="${user.degreeid==d.degreeid}">
    								<option value="${d.degreeid}" selected>${d.degreename}</option>
    							</c:if>
    							<c:if test="${user.degreeid!=d.degreeid}">
	    							<option value="${d.degreeid}">${d.degreename}</option>
	    						</c:if>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">权限类别:</label>
		   			<div class="layui-input-inline">
		     			<select name="managerType" id="managerType" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:if test="${user.managerType==0}">
			     				<option value="0" selected>系统管理员</option>
			   					<option value="1">总裁</option>
			   					<option value="2">部门经理</option>
			   					<option value="3">职员</option>
			   				</c:if>
			   				<c:if test="${user.managerType==1}">
			     				<option value="0">系统管理员</option>
			   					<option value="1" selected>总裁</option>
			   					<option value="2">部门经理</option>
			   					<option value="3">职员</option>
			   				</c:if>
			   				<c:if test="${user.managerType==2}">
			     				<option value="0">系统管理员</option>
			   					<option value="1">总裁</option>
			   					<option value="2" selected>部门经理</option>
			   					<option value="3">职员</option>
			   				</c:if>
			   				<c:if test="${user.managerType==3}">
			     				<option value="0">系统管理员</option>
			   					<option value="1">总裁</option>
			   					<option value="2">部门经理</option>
			   					<option value="3" selected>职员</option>
			   				</c:if>
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
		     			<input type="text" name="baseSalary" id="baseSalary" required  lay-verify="required" value="${user.baseSalary}" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">岗位工资:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="degreeSalary" id="degreeSalary" required  lay-verify="required" value="${user.degreeSalary}" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr align="center" height="50">
 			<td align="center">
 				入职日期：<input type="text" name="joinDate" id="joinDate" style="width:200px;" value="${user.joinDate}" placeholder="必填" class="easyui-datebox"">
 			</td>
 			<td align="center">
 				转正日期：<input type="text" name="workDate" id="workDate" style="width:200px;" value="${user.workDate}" class="easyui-datebox"">
 			</td>
 			<td align="center">
 				离职日期：<input type="text" name="levelDate" id="levelDate" style="width:200px;" value="${user.levelDate}" class="easyui-datebox"">
 			</td>
 		</tr>
 		<tr height="10"></tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">开户银行:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="bankName" value="${user.bankName}" id="bankName" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">银行账号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="bankCardNo" value="${user.bankCardNo}" id="bankCardNo" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">联系电话:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="mobile" value="${user.mobile}" id="mobile" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">邮箱地址:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="email" value="${user.email}" id="email" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">QQ号码:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="qqcode" value="${user.qqcode}" id="qqcode" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">微信号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" name="weixin" value="${user.weixin}" id="weixin" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">家庭地址:</label>
		   			<div class="layui-input-block">
		     			<input type="text" value="${user.addr}" name="addr" id="addr" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		</td>
 		</tr>
 		<tr>
	 		<td colspan="3">
		  		<div class="layui-form-item">
		   			<label class="layui-form-label">备注:</label>
		   			<div class="layui-input-block">
		     			<input type="text" value="${user.addr}" name="remark" id="remark" autocomplete="off" class="layui-input">
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
