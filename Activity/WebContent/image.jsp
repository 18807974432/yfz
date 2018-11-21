<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看当前流程图</title>
</head>
<body>
<!-- 1.获取到规则流程图 -->
<img style="position: absolute;top: 0px;left: 0px;" src="${pageContext.request.contextPath }/loginAction_viewImage?id=${pd.deploymentId}&imageName=${pd.diagramResourceName}">

<!-- 2.根据当前活动的坐标，动态绘制DIV -->
<s:iterator value="#request.mapList" var="d">
	<div style="position: absolute;border:2px solid red;top:${d.y}px;left: ${d.x}px;width: ${d.width-2}px;height:${d.height-2}px;"></div>
</s:iterator>
</body>
</html>