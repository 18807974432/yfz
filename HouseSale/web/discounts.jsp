<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>优惠方案资料</title>

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
    <s:include value="IncludeJS.jsp"></s:include>
</head>
<body leftmargin="0" topmargin="0" bgcolor="#f0ffff">
<script type="text/javascript">
    $(function(){
        var pager = $(".easyui-datagrid").datagrid('getPager');
        $(pager).pagination({
            total:${pager.totalRows},
            pageSize:${pager.pageRow},
            displayMsg:'{from}-{to}/{total}',
            pageNumber:${pager.cur_page},
            afterPageText:'页，共{pages}页',
            pageList:[2,3,5,10,20,50],
            loading:false,
            showPageList:true,
            showRefresh:true,
            //刷新方法
            onBeforeRefresh:function(p,s){
                location.href="${pageContext.request.contextPath }/discountsAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
            },
            onSelectPage:function(p,s){
                //alert(p+":"+s);
                location.href="${pageContext.request.contextPath }/discountsAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
            }
        });
    });

    function test(value,rec){
        var id= rec.discountId;
        var url = "${pageContext.request.contextPath }/discountsAction!json.action";
        $.post(
            url,
            {
                "discount.discountId":id
            },
            updemp,
            "json"
        );
    }
    function updemp(data){
        $("#discountId").val(data.discount.discountId);
        $("#disCountName").val(data.discount.disCountName);
        $("#unitPrice").val(data.discount.unitPrice);
        $("#totalPrice").val(data.discount.totalPrice);
        $("#discountPercent").val(data.discount.discountPercent);
        $("#invalid").val(data.discount.invalid);
        $("#description").val(data.discount.description);
    }
    function save(){
        var error = "<ul>";
        if($("#disCountName").val()==""){
            error += "<li><font color='red'>方案名称不能为空！</font></li>";
        }
        if($("#description").val()==""){
            error += "<li><font color='red'>方案说明不能为空！</font></li>";
        }
        error += "</ul>";
        if(error=="<ul></ul>"){
            if($("#unitPrice").val()==""){
                $("#unitPrice").val("0.0");
            }
            if($("#totalPrice").val()==""){
                $("#totalPrice").val("0.0");
            }
            if($("#discountPercent").val()==""){
                $("#discountPercent").val("1");
            }
            document.forms[0].submit();
        }else{
            $("#fielderror").html(error);
        }
    }
    function add(){
        $("#discountId").val("");
        $("#disCountName").val("");
        $("#unitPrice").val("");
        $("#totalPrice").val("");
        $("#discountPercent").val("");
        $("#invalid").val("");
        $("#description").val("");
    }

</script>
<DIV id=maintitle>
        <DIV class=newtitle><STRONG>付款类别资料</STRONG>

        </DIV>
</DIV>
<table class="easyui-datagrid" pagination="true" style="height: 260px;"  singleSelect="true"  data-options="onClickRow:test" >
    <thead>
    <tr>
        <th field="discountId">编号</th>
        <th field="disCountName" width="300px">优惠方案名称</th>
        <th field="unitPrice">单价优惠</th>
        <th field="totalPrice">总价优惠</th>
        <th field="discountPercent">折扣优惠</th>
        <th field="invalid">是否有效</th>
        <th field="description" width="300px">优惠说明</th>
        <th field="opt">操作</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator  value="#request.list" status="i" var="d">
        <tr>
            <td>${d.discountId }</td>
            <td>${d.disCountName }</td>
            <td>${d.unitPrice }</td>
            <td>${d.totalPrice }</td>
            <td>${d.discountPercent }</td>
            <td>${d.invalid }</td>
            <td>${d.description }</td>
            <td>
                <a class="easyui-linkbutton" plain="true" icon="icon-cut"
                   href="${pageContext.request.contextPath }/discountsAction!del.action?discount.discountId=${d.discountId}" onclick="return confirm('删除确认');">删除</a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>
<div id="tt" class="easyui-tabs"  style="height:230px">
    <div title="优惠方案资料" data-options="closable:false,cache:false" style="padding:0px;">
        <div class=newtitle>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="add();">添加</a>
            </th>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
            </th>
        </div>
        <s:form theme="simple" action="discountsAction!save">
            <h7 id="fielderror"></h7>
            <s:hidden name="discount.discountId" id="discountId"></s:hidden>
            <table width="100%" align="center">
                <tr>
                    <td align="right">优惠方案名称:</td>
                    <td colspan="3"><s:textfield name="discount.disCountName" id="disCountName"  cssStyle="width:600px"/><font size="2" color="red">*</font></td>
                    <td align="right">是否有效:</td>
                    <td><s:select list="{'有效','无效'}" name="discount.invalid" id="invalid" cssStyle="width:100px"></s:select></td>
                </tr>
                <tr>
                    <td align="right">单价优惠:</td>
                    <td><s:textfield name="discount.unitPrice" id="unitPrice"  cssStyle="width:100px"/><font size="2" color="red">每平方米优惠金额</font></td>
                    <td align="right">总价优惠:</td>
                    <td><s:textfield name="discount.totalPrice" id="totalPrice"  cssStyle="width:100px"/><font size="2" color="red">总价优惠金额</font></td>
                    <td align="right">折扣优惠:</td>
                    <td><s:textfield name="discount.discountPercent" id="discountPercent"  cssStyle="width:100px"/><font size="2" color="red">如0.98为98折</font></td>
                </tr>
                <tr>
                    <td align="right">优惠方案说明:</td>
                    <td colspan="5"><s:textfield name="discount.description" id="description"  cssStyle="width:600px"/></td>
                </tr>
                <tr>
                    <td colspan="6"><font size=2" color="red"><b>
                        说明：优惠单价，优惠总价，优惠折扣可以只使用一个，也可以组合使用;如果3个都使用的意思是在折扣优惠后，再按单价优惠，再减去总价优惠</b></font></td>
                </tr>
            </table>
        </s:form>
    </div>
</div>
</body>
</html>