<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/6
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>导入客户资料</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/demo/demo.css">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <script language="javascript">
        function check(){
            var filename = form1.upload.value;
            //substring
            var pos = filename.indexOf('.')+1;
            filename = filename.substring(pos,filename.length);
            if(filename !='xls' && filename !='XLS'){
                alert("只支持xls文件上传");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<c:form name="form1" action="UploadAction.action" method="post" enctype="multipart/form-data" theme="simple" onsubmit="return  check();">
    <input type="hidden" name="projectId" value="${param.projectId}"/>
    <table width="100%">
        <tr>
            <td><font color="red"><b><c:fielderror/><c:actionerror/></b></font></td>
        </tr>
        <tr height="40">
            <td><font color="red"><b>只支持扩展名为.xls的Excel文件，只有检查验证合格才能导入数据，导入可能要一些时间，请耐心等待</b></font></td>
        </tr>
        <tr height="40">
            <td><font color="red"><b>只能导入当前项目的数据，导入前请确定！！！</b></font></td>
        </tr>
        <tr height="40">
            <td>请选择文件:<c:file name="upload" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="model/custInfo.xls"><b>下载导入模板</b></a></td>
        </tr>
        <tr height="40">
            <td><c:submit value=" 上 传 "></c:submit></td>
        </tr>
    </table>
</c:form>
<br/>
<font size="3" color="red"><b>
    注意事项：<br/>
    <hr color="red"/>
    1.请先下载导入模板，按导入模板的格式填写数据；<br/>
    2.导入数据时，文件必须关闭，不能在打开状态；<br/>
    3.所有与日期有关的字段必须为日期格式，如(2018-8-8)；<br/>
</b></font>
</body>
</html>
