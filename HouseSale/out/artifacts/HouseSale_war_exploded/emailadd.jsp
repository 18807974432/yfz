<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/11/5
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
   <STYLE>
    .newtitle {
    BORDER-RIGHT: #c0dcf2 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #c0dcf2 1px solid; PADDING-LEFT: 15px; FONT-SIZE: 14px; BACKGROUND: url(images/maintitle.jpg); PADDING-BOTTOM: 4px; MARGIN: 0px 5px 2px 0px; BORDER-LEFT: #c0dcf2 1px solid; LINE-HEIGHT: 20px; PADDING-TOP: 4px; BORDER-BOTTOM: #c0dcf2 1px solid; HEIGHT: 20px! important
    }
    HTML {
    FONT-SIZE: 100%; MARGIN-BOTTOM: 1px; OVERFLOW: hidden; HEIGHT: 100%
    }
    BODY {
    FONT-SIZE: 10px; COLOR: #555; FONT-FAMILY: "微软雅黑","宋体"
    }
    </STYLE>
    <jsp:include page="IncludeJS.jsp"/>
    <script type="text/javascript">
        function save(){
            document.forms[0].submit();
        }
    </script>
</head>
<body>



<s:form theme="simple" action="Eamil!save.action">
    <table width="80%" align="center">
        <tr height="100">
            <td align="right" valign="bottom">接收人:</td>
            <td valign="bottom">
                <s:select list="#request.usersList" name="email.receid" listKey="username" listValue="username" class="easyui-combobox"/><font size="2" color="red">*</font>
            </td>
        </tr>
        <tr>
            <td align="right" valign="bottom">接收人QQ:</td>
            <td><s:textfield name="send.qq"></s:textfield></td><font size="2" color="red">*</font></td>
        </tr>
        <tr>
            <td align="right">标题:</td>
            <td><s:textfield name="email.topic" id="topic" size="115"/><font size="2" color="red">*</font></td>
        </tr>
        <tr>
            <td align="right">内容:</td>
            <td><s:textarea name="email.content" id="content" cols="100" rows="10"></s:textarea><font size="2" color="red">*</font></td>
        </tr>
    </table>
        <DIV id=maintitle>
        <DIV class=newtitle>
            <th>
                <input type="submit" value="提交" onclick="save();" class="easyui-linkbutton" icon="icon-save">
            </th>
        </DIV>
        </DIV>
</s:form>
</body>
</html>
