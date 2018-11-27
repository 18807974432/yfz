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
    
    <title>My JSP 'contractAdd.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
        <h2 align="center">新增合同信息</h2>
    <hr/>
    <form action="${pageContext.request.contextPath}/ContractServlet?action=add" method="post" name="form1" onsubmit="return check();">
    	<table border="0" height="160">
    		<tr>
    			<td align="right" width="200">合同编号：</td>
    			<td><input type="text" name="contract_no" style="width:300px"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td align="right" width="200">合同名称：</td>
    			<td><input type="text" name="contractname" style="width:300px"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td align="right">客户编号：</td>
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
    			<td><input type="text" name="contract_time" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同到期时间：</td>
    			<td><input type="text" name="due_time" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同金额：</td>
    			<td><input type="text" name="total_money" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同有效期(年)：</td>
    			<td><input type="text" name="deposit" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">支付类别：</td>
    			<td><input type="text" name="pay_type" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">合同状态：</td>
    			<td><input type="text" name="status" style="width:300px"></td>
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
    			<td><input type="text" name="operator" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">操作时间：</td>
    			<td><input type="text" name="oprtime" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">备注：</td>
    			<td><input type="text" name="remark" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td colspan="3" align="center"><input type="submit" value=" 保 存 "></td>
    		</tr>
    	</table>
    </form>
  </body>
</html>
