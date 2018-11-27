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
    
    <title>My JSP 'contractUpdate.jsp' starting page</title>
    
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
		<h2 align="center">修改合同信息</h2>
		<hr>
		 <form action="${pageContext.request.contextPath}/ContractServlet?action=updateSave" method="post" name="form1" onsubmit="return check();">
		 <input type="hidden" name="contract_id" value="${dep.contract_id}">
    	<table border="0" height="160">
    		<tr>
    			<td align="right" width="200">合同编号：</td>
    			<td><input type="text" name="contract_no" value="${dep.contract_no}" style="width:300px"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td align="right">合同姓名：</td>
    			<td><input type="text" name="contractname" value="${dep.contractname}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">客户姓名：</td>
    			<td>
    				<select name="custId"  style="width:100px">
    					<c:forEach items="${cusinfolist}" var="d">
    						<option value="${d.custId}">${d.custname}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td align="right">合同签订时间：</td>
    			<td><input type="text" name="contract_time" value="${dep.contract_time}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同到期时间：</td>
    			<td><input type="text" name="due_time" value="${dep.due_time}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同金额：</td>
    			<td><input type="text" name="total_money" value="${dep.total_money}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同有效期(年)：</td>
    			<td><input type="text" name="deposit" value="${dep.deposit}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">支付类别：</td>
    			<td><input type="text" name="pay_type" value="${dep.pay_type}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同状态：</td>
    			<td><input type="text" name="status" value="${dep.status}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">业务员：</td>
    			<td>
    				<select name="empid"  style="width:100px">
    					<c:forEach items="${userslist}" var="d">
    						<option value="${d.userid}">${d.username}</option>
    					</c:forEach>
    				</select>
    			</td>
    		</tr>
    		<tr>
    			<td align="right">操作员：</td>
    			<td><input type="text" name="operator" value="${dep.operator}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">操作时间：</td>
    			<td>
    				<input type="text" name="oprtime" value="${dep.oprtime }" style="width:300px">
    			</td>
    		</tr>
    		<tr>
    			<td align="right">描述：</td>
    			<td><input type="text" name="remark" value="${dep.remark}" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td colspan="3" align="center"><input type="submit" value=" 保 存 "></td>
    		</tr>		
    	</table>
    </form>
  </body>
</html>
