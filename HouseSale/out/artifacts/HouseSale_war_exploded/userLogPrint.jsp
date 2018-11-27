<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="com.ht.dao.ServiceConstants"%>
<%@ taglib uri="/struts-tags" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统日志</title>
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
            pageList:[5,10,20,30,50,100],
            loading:false,
            showPageList:true,
            showRefresh:true,
            //刷新方法
            onBeforeRefresh:function(p,s){
                location.href="${pageContext.request.contextPath }/usersAction!userlog.action?pager.cur_page="+p+"&pager.pageRow="+s;
            },
            onSelectPage:function(p,s){
                location.href="${pageContext.request.contextPath }/usersAction!userlog.action?pager.cur_page="+p+"&pager.pageRow="+s;
            }
        });
    });
</script>
<DIV id=maintitle>
	<DIV class=newtitle>
		<c:form action="usersAction!userlog.action" method="post">
			开始日期:<c:textfield name="signdate1" id="signdate1" cssClass="easyui-datebox"/>
			截止日期:<c:textfield name="signdate2" id="signdate2" cssClass="easyui-datebox"/>
			<c:submit value="查找"></c:submit>
		</c:form>
	</DIV>
</DIV>
<DIV class=newtitle align="center"><STRONG>用户操作日志</STRONG><br/><br/></DIV>
<table class="easyui-datagrid" pagination="true" style="height:400px"  singleSelect="true">
	<thead>
	<tr>
		<th field="id" width="50px">序号</th>
		<th field="oprtime" width="100px">时间</th>
		<th field="userid" width="60px">用户名称</th>
		<th field="ipaddr" width="100px">IP地址</th>
		<th field="tables" width="150px">操作表格</th>
		<th field="msg" width="660px">更新内容</th>
	</tr>
	</thead>
	<tbody>
	<c:iterator  value="pager.datas" status="i" var="d">
		<tr>
			<td>${id}</td>
			<td>${oprtime}</td>
			<td>${d.user.username}</td>
			<td>${ipaddr}</td>
			<td>${tables}</td>
			<td>${msg}</td>
		</tr>
	</c:iterator>
	</tbody>
</table>
</body>
</html>