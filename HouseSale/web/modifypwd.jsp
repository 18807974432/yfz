<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="com.ht.dao.ContextUtils" %>
<%@ taglib uri="/struts-tags" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改密码</title>
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
    <jsp:include page="IncludeJS.jsp"></jsp:include>
    <link media="screen" href="css/login.css" type="text/css" rel="stylesheet"/>
    <script src="js/jquery-1.4.1.js"></script>
    <script language="javascript">
        function check(){
            if($("#password").val()==""){
                alert("请输入旧密码");
                $("#password").focus();
                return false;
            }
            if($("#password1").val()==""){
                alert("请输入新密码");
                $("#password1").focus();
                return false;
            }
            if ($("#password").val!=""&&$("#password1").val()!=""&&$("#password2").val()==""){
                alert("请输入确认密码");
                return false;

            }

            if($("#password2").val()!=$("#password1").val()&&$("#password").val!=""){
                alert("新密码和确认密码不符");
                $("#password2").focus();
                return false;
            }
            alert("修改密码成功");
            return true;
        }
    </script>
</head>
<body>
<script type="text/javascript">
    function save(){
        document.forms[1].submit();
    }
</script>
<DIV id=maintitle>
    <c:form action="usersAction!list.action" method="post">
        <DIV class=newtitle><STRONG>修改密码:</STRONG>

        </DIV>
    </c:form>
</DIV>
<c:form theme="simple" action="usersAction!pwd" onsubmit="return check();">
    <c:fielderror></c:fielderror>
    <input type="hidden" name="user.userid" id="userid" value="<%=ContextUtils.getUserInfo().getUserid()%>">
    <table width="100%" align="center">
        <tr><td colspan="2"  height="50">&nbsp;</td></tr>
        <tr>
            <td align="right">当前用户:</td>
            <td><%=ContextUtils.getUserInfo().getUsername()%></td>
        </tr>
        <tr>
            <td align="right"   height="50">旧密码:</td>
            <td><c:password name="user.password" id="password"/><font size="2" color="red">*</font></td>
        </tr>
        <tr>
            <td align="right"  height="50">新密码:</td>
            <td><c:password name="user.password1" id="password1"/><font size="2" color="red">*</font></td>
        </tr>
        <tr>
            <td align="right"  height="50">确认密码:</td>
            <td><c:password name="user.password2" id="password2"/><font size="2" color="red">*</font></td>
        </tr>
        <tr height="50">
            <td>&nbsp;</td>
            <td align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <button type="button" onclick="check();">修改密码</button></td>
        </tr>
    </table>
</c:form>
</body>
</html>