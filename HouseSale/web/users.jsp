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
	<title>用户资料</title>
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
					location.href="${pageContext.request.contextPath }/usersAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
				},
				onSelectPage:function(p,s){
					location.href="${pageContext.request.contextPath }/usersAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
				}
			});
		});	
		
		function test(value,rec){
			var id= rec.userid;
			var url = "${pageContext.request.contextPath }/usersAction!json.action";
			$.post(
				url,
				{
					"user.userid":id
				},
				updemp,
				"json"
			);			
		}
		function init(){
		    alert("11");
			var id= $("#userid").val();
			if(id ==""){
				alert("请选择要修改的用户");
				return;
			}

			var url = "${pageContext.request.contextPath }/usersAction!init.action";
			$.post(
				url,
				{
					"user.userid":id
				},
				function(){
					alert("初始密码成功，初始密码为888888，请注意修改密码");
				},
                alert("22"),
				"json"
			);			
		}
		
		function updemp(data){
			$("#userid").val(data.user.userid);
			$("#username").val(data.user.username);
			$("#password").val(data.user.password);
			$("#password1").val(data.user.password);
			$("#depid").val(data.user.deps.depid);
			$("#jobname").val(data.user.jobname);
			$("#mobile").val(data.user.mobile);
			$("#email").val(data.user.email);
			$("#qqcode").val(data.user.qqcode);
			$("#addr").val(data.user.addr);
			$("#status").val(data.user.status);
			$("#demo").val(data.user.demo);
			$("#username").attr("readonly",true);
			$("#password").attr("readonly",true);
			$("#password1").attr("readonly",true);
			
		}
		function save(){
			document.forms[1].submit();
		}
		function add(){
			$("#userid").val("");
			$("#username").val("");
			$("#password").val("");
			$("#password1").val("");
			//$("#depid").val(data.user.depid);
			//$("#jobname").val(data.user.jobname);
			$("#mobile").val("");
			$("#email").val("");
			$("#qqcode").val("");
			$("#addr").val("");
			//$("#status").val(data.user.status);
			$("#demo").val("");	
			$("#username").attr("readonly",false);	
			$("#password").attr("readonly",false);	
			$("#password1").attr("readonly",false);	
		}
		
	</script>
<DIV id=maintitle>
<c:form action="usersAction!list.action" method="post">
<DIV class=newtitle><STRONG>用户资料</STRONG>
		
</DIV>
</c:form>
</DIV>
	<table class="easyui-datagrid" pagination="true" style="height:260px"  singleSelect="true"  data-options="onClickRow:test" >
			<thead>
			<tr>
				<th field="userid">用户编号</th>
				<th field="username">用户名称</th>
				<th field="depid">所在部门</th>
				<th field="jobname">职务名称</th>
				<th field="opt">操作</th>
			</tr>
			</thead>
			<tbody>
				<c:iterator  value="pager.datas" status="i" var="d">
					<tr>
						<td>${d.userid }</td>
						<td>${d.username }</td>
						<td>${d.deps.depname }</td>
						<td>${d.jobname }</td>
						<td>
							<a class="easyui-linkbutton" plain="true" icon="icon-cut"
								href="${pageContext.request.contextPath }/usersAction!del.action?user.userid=${d.userid}" onclick="return confirm('删除确认');">删除</a>
						</td>
					</tr>
				</c:iterator>
			</tbody>
		</table>
	<div id="tt" class="easyui-tabs"  style="height:230px">
		<div title="用户资料" data-options="closable:false,cache:false" style="padding:0px;">
			<div class=newtitle>
				<th>
					<a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="add();">添加</a>
				</th>
				<%--<th>--%>
					<%--<a class="easyui-linkbutton" plain="true" icon="icon-edit" href="javascript:void(0);" onclick="init(); onclick=check()">初始密码</a>--%>
				<%--</th>--%>
				<th>
					<a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="return save();">保存</a>
				</th>
			</div>
				<c:form theme="simple" action="usersAction!save.action">
				<c:fielderror></c:fielderror>
				<c:hidden name="user.userid" id="userid"></c:hidden>
	 			<table width="100%" align="center">
	 				<tr>
	 					<td align="right">用户名称:</td>
	 					<td><c:textfield name="user.username" id="username" readonly="false"/><font size="2" color="red">*</font></td>
	 					<td align="right">所在部门:</td>
	 					<td>
	 						<c:select list="#request.depdata" name="dep.depid" id="depid" listKey="depid" listValue="depname" cssStyle="width:152px"></c:select>
	 					</td>
	 					<td align="right">职务:</td>
	 					<td>
	 						<c:select list="#request.jobdata" name="user.jobname" id="jobname" listKey="jobname" listValue="jobname" cssStyle="width:152px"></c:select>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td align="right">登录密码:</td>
	 					<td><c:password name="user.password" id="password"/><font size="2" color="red">*</font></td>
	 					<td align="right">确认密码:</td>
	 					<td><c:password name="user.password1" id="password1"/><font size="2" color="red">*</font></td>
	 					<td align="right">用户状态:</td>
	 					<td>
	 						<c:select list="#{'1':'启用','0':'禁用'}" name="user.status" id="status" listKey="key" listValue="value" cssStyle="width:152px"></c:select>
	 					</td>
	 				</tr>
	 				<tr>
	 					<td align="right">联系电话:</td>
	 					<td><c:textfield name="user.mobile" id="mobile"/></td>
	 					<td align="right">邮箱地址:</td>
	 					<td><c:textfield name="user.email" id="email"/></td>
	 					<td align="right">QQ号码:</td>
	 					<td><c:textfield name="user.qqcode" id="qqcode"/></td>
	 				</tr>
	 				<tr>
	 					<td align="right">联系地址:</td>
	 					<td><c:textfield name="user.addr" id="addr" size="40"/></td>
	 					<td align="right">说明:</td>
	 					<td colspan="3"><c:textfield name="user.demo" id="demo" size="78"/></td>
	 				</tr>
	 				
	 			</table>
	 			</c:form>
		</div>
	</div>
</body>
</html>