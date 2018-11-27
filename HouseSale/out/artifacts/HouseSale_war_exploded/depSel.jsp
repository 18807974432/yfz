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
	<title>部门资料</title>
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
	<link href="css/style.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.0/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="demo.css">
</head>
<body>
<table width="100%">
<tr>
	<td width="50%">
	<ul id="tt1" class="easyui-tree" data-options="animate:true,dnd:true" style="height:250px">
		<li data-options="state:'open'">
			<c:iterator value="#request.pager.datas" var="d" status="st">
				<c:if test="%{#request.st.index==0}">
					<span><a href="javascript:void(0)" onclick="getPerson(this)" id="${d.depid}">${d.depname}</a></span>
				<c:iterator value="#request.pager.datas" var="e">
				<c:if test="%{(#request.d.depid==#request.e.deps.depid) and (#request.d.depid!=#request.e.depid)}">
					<ul>
						<li data-options="state:'open'">
							<span><a href="javascript:void(0)" onclick="getPerson(this)" id="${e.depid}">${e.depname}</a></span>
							<ul>
							<c:iterator value="#request.pager.datas" var="ff">
								<c:if test="%{#request.e.depid==#request.ff.deps.depid}">
									<li data-options="state:'open'">
										<span><a href="javascript:void(0)" onclick="getPerson(this)" id="${ff.depid}">${ff.depname}</a></span>
									</li>
								</c:if>
							</c:iterator>
							</ul>
						</li>
					</ul>
				</c:if>
				</c:iterator>
				</c:if>
			</c:iterator>
		</li>
	</ul>
	</td>
	<td valign="top">
		<table id="usertable" width="100%">
		
		</table>
	</td>
</tr>
</table>
</body>
</html>