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
	<title>职务资料</title>
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
                location.href="${pageContext.request.contextPath }/jobsAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
            },
            onSelectPage:function(p,s){
                location.href="${pageContext.request.contextPath }/jobsAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
            }
        });
    });

    function test(value,rec){
        var id= rec.jobid;
        var url = "${pageContext.request.contextPath }/jobsAction!json.action";
        $.post(
            url,
            {
                "jobs.jobid":id
            },
            updemp,
            "json"
        );
    }
    function updemp(data){
        $("#jobid").val(data.jobs.jobid);
        $("#jobname").val(data.jobs.jobname);
    }
    function save(){
        document.forms[1].submit();
    }
    function add(){
        $("#jobid").val("");
        $("#jobname").val("");
    }

</script>
<DIV id=maintitle>
	<c:form action="jobsAction!list.action" method="post">
		<DIV class=newtitle><STRONG>职务资料</STRONG>

		</DIV>
	</c:form>
</DIV>
<table class="easyui-datagrid" pagination="true" style="height:260px"  singleSelect="true"  data-options="onClickRow:test" >
	<thead>
	<tr>
		<th field="jobid">职务编号</th>
		<th field="jobname">职务名称</th>
		<th field="opt">操作</th>
	</tr>
	</thead>
	<tbody>
	<c:iterator  value="pager.datas" status="i" var="d">
		<tr>
			<td>${d.jobid }</td>
			<td>${d.jobname }</td>
			<td>
				<a class="easyui-linkbutton" plain="true" icon="icon-cut"
				   href="${pageContext.request.contextPath }/jobsAction!del.action?jobs.jobid=${d.jobid}" onclick="return confirm('删除确认');">删除</a>
			</td>
		</tr>
	</c:iterator>
	</tbody>
</table>
<div id="tt" class="easyui-tabs"  style="height:230px">
	<div title="职务资料" data-options="closable:false,cache:false" style="padding:0px;">
		<div class=newtitle>
			<th>
				<a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="add();">添加</a>
			</th>
			<th>
				<a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
			</th>
		</div>
		<c:form theme="simple" action="jobsAction!save.action">
			<c:fielderror></c:fielderror>
			<c:hidden name="jobs.jobid" id="jobid"></c:hidden>
			<table width="100%" align="center">
				<tr>
					<td align="right">职务名称:</td>
					<td><c:textfield name="jobs.jobname" id="jobname" size="50"/><font size="2" color="red">*</font></td>
				</tr>

			</table>
		</c:form>
	</div>
</div>
</body>
</html>