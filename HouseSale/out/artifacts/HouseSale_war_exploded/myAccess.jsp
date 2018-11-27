<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.ht.dao.ContextUtils"%>
<%@page import="com.ht.vo.AccModule"%>
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
</head>

<body>
<DIV id=maintitle>
    <DIV class=newtitle><STRONG>我的权限</STRONG>
        (<%=ContextUtils.getUserInfo().getUsername() %>)：
    </DIV>
</DIV>
<table width="1000" align="center" cellpadding="0" cellspacing="0" border="0">
    <%
        List<AccModule> list = ContextUtils.getUserInfo().getRight();
        for(int i=0;i<list.size();i++){
            AccModule acc = list.get(i);
            if(i % 7 ==0){
                out.print("<tr height='30'>");
            }
    %>
    <td>
        <b><%=acc.getModule().getModuleName() %></b>
    </td>
    <%
            if(i % 7 ==6){
                out.print("</tr>");
            }
        } %>
</table>
</body>
</html>
