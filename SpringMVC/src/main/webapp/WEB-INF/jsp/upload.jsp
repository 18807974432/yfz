<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/28
  Time: 8:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
    <script type="text/javascript">
        function check(){
            var fileName=form1.file.value;
            var pos=fileName.indexOf(".");
            var ext=fileName.substring(pos+1);
            if(ext!="jpeg"&&ext!="jpg"&&ext!="png"&&ext!="gif"){
                alert("只允许上传jpeg，jpg，png，gif格式的图片");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<form name="form1" method="post" action="${pageContext.request.contextPath}/upload/uploadSingle" enctype="multipart/form-data" onsubmit="return check();">
    请选择要上传的文件：<input type="file" name="file" id="file1">
    <input type="submit" value="提交" >
</form>
</body>
</html>