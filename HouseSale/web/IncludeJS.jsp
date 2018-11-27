<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/jquery-easyui-1.3.0/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/jquery-easyui-1.3.0/themes/icon.css">
<script type="text/javascript" src="<%=basePath%>/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/opentabs.js"></script>
<link href="<%=basePath%>/style/style.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="<%=basePath%>/js/dh.js"></script>
