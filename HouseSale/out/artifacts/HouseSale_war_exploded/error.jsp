<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出错了</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<jsp:include page="/IncludeJS.jsp"></jsp:include>

  </head>


  <body onload="error();">
		
  </body>
<script type="text/javascript">
function error(){
	var win = $.messager.progress({
				title:'单位食堂订餐管理系统',
				msg:'正在连接服务器.....'
			});
			setTimeout(function(){
				$.messager.progress('close');
				$.messager.alert('单位食堂订餐管理系统','服务器繁忙!连接失败!请稍后再试!!!!!','error');
			},5000)
}

</script>
</html>
