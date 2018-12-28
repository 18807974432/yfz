<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改密码</title>
<script type="text/javascript">
	function check(){
		if(form1.oldpwd.value==""){
			alert("请输入旧密码");
			return false;
		}
		if(form1.oldpwd.value!=form1.password.value){
			alert("旧密码与原密码不一致");
			return false;
		}
		if(form1.newpwd.value==""){
			alert("请输入新密码");
			return false;
		}
		if(form1.newpwd.value!= form1.cfgPwd.value){
			alert("新密码与确认密码不一致");
			return false;
		}
		alert("修改密码成功");
		return true;
		
	}
</script>
</head>
<body>
	<h2 align="center">修改密码</h2>
	<hr/>
	<form name="form1" action="${pageContext.request.contextPath }/usersServlet?action=pwd" method="post" onsubmit="return check();">
		<input type="hidden" name="empId" value="${userInfo.userId }">
		<input type="hidden" name="password" value="${userInfo.password }">
		<table border="1" width="1000" align="center" height="400">
			<tr>
				<td align="center" width="180">旧密码:</td>
				<td><input type="password" name="oldpwd" style="width:300px"></td>
			</tr>
			<tr>
				<td align="center">登录密码:</td>
				<td><input type="password" name="newpwd" style="width:300px"></td>
			</tr>
			<tr>
				<td align="center">确认密码:</td>
				<td><input type="password" name="cfgPwd" style="width:300px"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="  保 存   ">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>