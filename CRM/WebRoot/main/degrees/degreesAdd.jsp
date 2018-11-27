<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
   <title>新增岗位资料</title>
	    <script type="text/javascript">
	    	function check(){
	    		if(form1.degreename.value==""){
	    			alert("请输入岗位名称！");
	    			form1.degreename.focus();
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
    
    <h2 align="center">新增岗位</h2>
    <hr/>
    <form action="${pageContext.request.contextPath}/degreesServlet?action=add" method="post" name="form1" onsubmit="return check();">
    	<table border="0" height="160">
    		<tr>
    			<td align="right" width="220">岗位名称：</td>
    			<td><input type="text" name="degreename" style="width:180px"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td colspan="3" align="center"><input type="submit" value=" 保 存 "></td>
    		</tr>
    		
    	</table>
    
    </form>
  </body>
</html>
