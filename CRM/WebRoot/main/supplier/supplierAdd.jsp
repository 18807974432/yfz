<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增供应商品信息</title>
    	<script type="text/javascript">
	    	function check(){
	    		if(form1.depName.value==""){
	    			alert("请输入部门名称");
	    			form1.depName.focus();
	    			return false;
	    		}
	    		return true;
	    	}
	    </script>
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
    <h2 align="center">新增供应商品信息</h2>
    <hr/>
    <form action="${pageContext.request.contextPath}/SupplierServlet?action=add" method="post" name="form1" onsubmit="return check();">
    	<table border="0" height="160">
    		<tr>
    			<td align="right" width="200">供应商名称：</td>
    			<td><input type="text" name="supplierName" style="width:300px"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td align="right">银行账号：</td>
    			<td><input type="text" name="bankAccount" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">开户银行：</td>
    			<td><input type="text" name="bankName" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">联系人：</td>
    			<td><input type="text" name="contact" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">电话：</td>
    			<td><input type="text" name="phone" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">地址：</td>
    			<td><input type="text" name="addr" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td align="right">描述：</td>
    			<td><input type="text" name="remark" style="width:300px"></td>
    		</tr>
    		<tr>
    			<td colspan="3" align="center"><input type="submit" value=" 保 存 "></td>
    		</tr>
    		
    	</table>
    
    </form>
  </body>
</html>
