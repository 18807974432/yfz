<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 8:07
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传示例</title>
</head>
<body>
<form:form method="POST" action="/fileUploadPage" enctype="multipart/form-data">
    请选择一个文件上传：
    <input type="file" name="file"/>
    <input type="submit" value="提交上传"/>
</form:form>

</body>
</html>