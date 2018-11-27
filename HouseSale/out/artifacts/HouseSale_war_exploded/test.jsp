<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/22
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
   <c:forEach items="cusList" var="d">
       <tr>
           <td>${d.custname}</td>
           <td>${d.custId}</td>
           <td>${d.mobile}</td>
           <td>${d.saleperson}</td>
           <td>${d.signdate}</td>
       </tr>
   </c:forEach>
</table>
</body>
</html>
