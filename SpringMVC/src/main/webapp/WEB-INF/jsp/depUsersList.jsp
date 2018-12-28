<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/24
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>员工资料列表</title>
</head>
<body>
<h2 align="center">员工资料列表</h2>
<hr/>
<table align="center" width="1000" border="0">
    <tr>
        <td align="right">部门编号:</td>
        <td bgcolor="blue">${dep.depId}</td>
        <td align="right">部门名称:</td>
        <td bgcolor="blue">${dep.depName}</td>
    </tr>
</table>
<table align="center" width="1000" border="1">
    <thead>
    <tr height="30">
        <th>编号</th>
        <th>姓名</th>
        <th>部门</th>
        <th>年龄</th>
        <th>性别</th>
        <th>工作岗位</th>
        <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${dep.usersList}" var="d">
        <tr height="30">
            <td>${d.userId}</td>
            <td>${d.userName}</td>
            <td>${d.depName}</td>
            <td>${d.age}</td>
            <td>${d.sex}</td>
            <td>${d.job}</td>
            <td>${d.remark}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>