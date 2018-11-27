<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.ht.dao.ContextUtils"%>
<%@page import="com.ht.vo.AccModule"%>
<%@page import="com.ht.vo.Module"%>
<%@ taglib uri="/struts-tags" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
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
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="demo.css">
    <script language="javascript">
        function seelAll(){
            var len = form3.sysid.length;
            for(var i=0;i<len;i++)
            {
                form3.sysid[i].checked=true;
            }
        }
        function unseelAll(){
            var len = form3.sysid.length;
            for(var i=0;i<len;i++)
            {
                form3.sysid[i].checked=false;
            }
        }
    </script>
</head>

<body>
<div id="tt" class="easyui-tabs" style="height:480px" data-options="tools:'#tab-tools'">
    <div title="用户权限设置" data-options="closable:false,cache:false" style="padding:0px;">

        <c:form action="accAction!list.action" method="post" name="form1" theme="simple">
            <table width="1000" align="center" cellpadding="0" cellspacing="0">
                <tr>
                    <td colspan="2">选择用户<c:select list="#request.userInfo" listKey="userid" listValue="username" name="username"/></td>
                    <td align="right"><c:submit value="查看权限"/></td>
                </tr>
            </table>
        </c:form>
        <hr/>
        <c:form action="accAction!revoke.action?username=%{username}" method="post" name="form2" theme="simple">
            <table width="1000" align="center" cellpadding="0" cellspacing="0">
                <%
                    List<AccModule> list = (List<AccModule>)request.getAttribute("accInfo");
                    for(int i=0;i<list.size();i++){
                        AccModule acc = list.get(i);
                        if(i % 6 ==0){
                            out.print("<tr height='20'>");
                        }
                %>
                <td>
                    <input type="checkbox" name="moduleids" value="<%=acc.getId() %>"/> <%=acc.getModule().getModuleName() %>
                </td>
                <%
                        if(i % 6 ==5){
                            out.print("</tr>");
                        }
                    } %>
                <tr><td align="right" colspan="6"><c:submit value="回收权限"/></td></tr>
            </table>
        </c:form>
        <hr/>
        <c:form action="accAction!grant.action" method="post" name="form3" theme="simple">
            <table width="1000" align="center" cellpadding="0" cellspacing="0">
                <%
                    List<Module> list = (List<Module>)request.getAttribute("moduleInfo");
                    for(int i=0;i<list.size();i++){
                        Module acc = list.get(i);
                        if(i % 6 ==0){
                            out.print("<tr height='20'>");
                        }
                %>
                <td>
                    <input type="checkbox" name=sysid value="<%=acc.getModuleid() %>"/> <%=acc.getModuleName() %>
                </td>
                <%
                        if(i % 6 ==5){
                            out.print("</tr>");
                        }
                    } %>
                <tr>
                    <td colspan="3">
                        选择用户:<c:select list="#request.userInfo" listKey="userid" listValue="username" name="username"/>
                        <input type="button" value=" 取 消 " onclick="unseelAll();"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="button" value=" 全 选 " onclick="seelAll();"/>
                    </td>
                    <td align="right" colspan="3">
                        <c:submit value="授予权限"/>
                    </td>
                </tr>
            </table>
        </c:form>
    </div>
</div>
</body>
</html>
