<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/18
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>房地产后台管理工作平台</title>
</head>
<frameset rows="127,*,11" frameborder="no" border="0" framespacing="0">
    <frame src="top.jsp" name="topFrame" scrolling="No"
           noresize="noresize" id="topFrame" />
    <frame src="center.jsp" name="mainFrame" id="mainFrame" />
    <frame src="down.html" name="bottomFrame" scrolling="No"
           noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
    <body>
    </body>
</noframes>
</html>
