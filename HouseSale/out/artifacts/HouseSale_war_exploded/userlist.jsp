<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>
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
</head>
<body>
<script type="text/javascript">
    $(function(){
        var pager = $(".easyui-datagrid").datagrid('getPager');
        $(pager).pagination({
            total:${pager.totalRows},
            pageSize:${pager.pageRow},
            displayMsg:'{from}-{to}/{total}',
            pageNumber:${pager.cur_page},
            afterPageText:'页，共{pages}页',
            pageList:[3,5,10,20,30,50,100],
            loading:false,
            showPageList:true,
            showRefresh:true,
            //刷新方法
            onBeforeRefresh:function(p,s){
                location.href="${pageContext.request.contextPath }/usersAction!ulist.action?pager.cur_page="+p+"&pager.pageRow="+s;
            },
            onSelectPage:function(p,s){
                location.href="${pageContext.request.contextPath }/usersAction!ulist.action?pager.cur_page="+p+"&pager.pageRow="+s;
            }
        });
    });


</script>
<DIV id=maintitle>
    <c:form action="usersAction!ulist.action" method="post">
        <DIV class=newtitle align="center"><STRONG>用户信息</STRONG>
        </DIV>
    </c:form>
</DIV>
<table class="easyui-datagrid" pagination="true" style="height:440px"  singleSelect="true">
    <thead>
    <tr>
        <th field="userid">用户编号</th>
        <th field="username">用户名称</th>
        <th field="depid">所在部门</th>
        <th field="jobname">职务名称</th>
        <th field="mobile"width="100px">联系电话</th>
        <th field="email" width="100px">邮箱地址</th>
        <th field="qqcode" width="100px">QQ号码</th>
        <th field="addr"width="150px">联系地址</th>
        <th field="demo" width="150px">说明</th>
    </tr>
    </thead>
    <tbody>
    <c:iterator  value="pager.datas" status="i" var="d">
        <tr>
            <td>${d.userid }</td>
            <td>${d.username }</td>
            <td>${d.deps.depname }</td>
            <td>${d.jobname }</td>
            <td>${d.mobile }</td>
            <td>${d.email }</td>
            <td>${d.qqcode }</td>
            <td>${d.addr }</td>
            <td>${d.demo }</td>

        </tr>
    </c:iterator>
    </tbody>
</table>
</body>
</html>