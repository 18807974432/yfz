<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件列表</title>
</head>
<body>
<h2 align="center">上传文件列表</h2>
<hr/>
<table align="center" width="1000" border="0">
    <tr>
        <td><a href="${pageContext.request.contextPath}/upload/uploadSingle">上传</a></td>
    </tr>
</table>
<table align="center" width="1000" border="1">
    <thead>
    <tr height="30">
        <th>图片</th>
        <th>名称</th>
        <th>上传时间</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${uploadList}" var="d">
        <tr height="30">
            <td><img src="${pageContext.request.contextPath}/upload20181225/${d.descFileName}" height="100"/></td>
            <td>${d.srcFileName}</td>
            <td>${d.sTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>