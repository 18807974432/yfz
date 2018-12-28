<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/24
  Time: 20:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>部门管理</title>
</head>
<body>
<h2 align="center">部门管理</h2>
<hr/>
<!--

    Spring MVC的标签库的使用
    modelAttribute:是后台对应的request.setAttribute定义的javabean的变量名称

 -->
<form:form name="form1" modelAttribute="depVo" action="${pageContext.request.contextPath }/dep/add" method="post">
    <form:hidden path="depId"/>
    <table border="1" width="1000" align="center" height="400">
        <tr>
            <td align="center" width="180">部门名称:</td>
            <td><form:input path="depName" style="width:300px"/></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="  保 存   ">
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>