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
    <link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.3.0/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="demo.css">
</head>
<body>
<script type="text/javascript">

    function del(){
        var id= $("#depid").val();
        var url = "${pageContext.request.contextPath }/depAction!del.action";
        $.post(
            url,
            {
                "deps.depid":id
            },
            function(data){
                //alert(data);
                location.href="${pageContext.request.contextPath }/depAction!list.action";
            },
            "json"
        );
    }
    function test(obj){
        var id= obj.id;
        var url = "${pageContext.request.contextPath }/depAction!json.action";
        $.post(
            url,
            {
                "deps.depid":id
            },
            updemp,
            "json"
        );
    }
    function updemp(data){
        $("#depid").val(data.deps.depid);
        $("#depname").val(data.deps.depname);
        $("#parentid").val(data.deps.deps.depid);
        $("#chairman").val(data.deps.chairman);
        $("#description").val(data.deps.description);
    }
    function save(){
        document.forms[1].submit();
    }
    function add(){
        $("#depid").val("");
        $("#depname").val("");
        $("#chairman").val("");
        $("#description").val("");
    }

</script>
<DIV id=maintitle>
    <c:form action="depAction!list.action" method="post">
        <DIV class=newtitle><STRONG>部门资料</STRONG>
        </DIV>
    </c:form>
</DIV>
<ul id="tt1" class="easyui-tree" data-options="animate:true,dnd:true" style="height:250px">
    <li data-options="state:'open'">
        <c:iterator value="#request.pager.datas" var="d" status="st">
            <c:if test="%{#request.st.index==0}">
                <span><a href="javascript:void(0)" onclick="test(this)" id="${d.depid}">${d.depname}</a></span>
                <c:iterator value="#request.pager.datas" var="e">
                    <c:if test="%{(#request.d.depid==#request.e.deps.depid) and (#request.d.depid!=#request.e.depid)}">
                        <ul>
                            <li data-options="state:'open'">
                                <span><a href="javascript:void(0)" onclick="test(this)" id="${e.depid}">${e.depname}</a></span>
                                <ul>
                                    <c:iterator value="#request.pager.datas" var="ff">
                                        <c:if test="%{#request.e.depid==#request.ff.deps.depid}">
                                            <li data-options="state:'open'">
                                                <span><a href="javascript:void(0)" onclick="test(this)" id="${ff.depid}">${ff.depname}</a></span>
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

<div id="tt" class="easyui-tabs"  style="height:230px">
    <div title="部门资料" data-options="closable:false,cache:false" style="padding:0px;">
        <div class=newtitle>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="add();">添加</a>
            </th>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-cancel" href="javascript:void(0);" onclick="del();">删除</a>
            </th>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
            </th>
        </div>
        <c:form theme="simple" action="depAction!save.action">
            <c:fielderror></c:fielderror>
            <c:hidden name="deps.depid" id="depid"></c:hidden>
            <table width="100%" align="center">
                <tr>
                    <td align="right">部门名称:</td>
                    <td><c:textfield name="deps.depname" id="depname" size="50"/><font size="2" color="red">*</font></td>
                </tr>
                <tr>
                    <td align="right">上级部门:</td>
                    <td>
                        <c:select list="#request.pager.datas" name="deps.parentid" id="parentid" listKey="depid" listValue="depname"></c:select>
                    </td>
                </tr>
                <tr>
                    <td align="right">部门负责人:</td>
                    <td><c:textfield name="deps.chairman" id="chairman" size="50"/></td>
                </tr>
                <tr>
                    <td align="right">描述:</td>
                    <td><c:textfield name="deps.description" id="description" size="50"/></td>
                </tr>

            </table>
        </c:form>
    </div>
</div>
</body>
</html>