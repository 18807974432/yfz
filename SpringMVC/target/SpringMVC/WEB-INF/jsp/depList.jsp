<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/24
  Time: 20:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>部门资料列表</title>
</head>
<body>
    <h2 align="center">部门资料列表</h2>
    <hr/>
    <table align="center" width="1000" border="0">
        <tr>
            <td><a href="${pageContext.request.contextPath}/dep/init">新增</a></td>
        </tr>
    </table>
    <table align="center" width="1000" border="1">
        <thead>
        <tr height="30">
            <th>编号</th>
            <th>部门</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${depList}" var="d">
            <tr height="30">
                <td>${d.depId}</td>
                <td>${d.depName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/dep/update?depId=${d.depId}">修改</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/dep/del?depId=${d.depId}" onclick="return confirm('删除确定');">删除</a>&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/dep/users/${d.depId}" >部门员工</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
