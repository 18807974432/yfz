<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改岗位</title>
    
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

  </head>
  
  <body>
    <h2 align="center">修改岗位</h2>
    <hr/>
    <form action="${pageContext.request.contextPath}/degreesServlet?action=updateSave" method="post" name="form1" onsubmit="return check();">
    	<input type="hidden" name="degreeid" value="${dep.degreeid}">
    	<table border="0" height="160">
    		<tr>
    			<td align="right" width="200">岗位名称：</td>
    			<td><input type="text" name="degreename" style="width:300px" value="${dep.degreename}"><font size="2" color="red">*必填</font></td>
    		</tr>
    		<tr>
    			<td colspan="3" align="center"><input type="submit" value=" 保 存 "></td>
    		</tr>		
    	</table>
    </form>
  </body>
</html>
