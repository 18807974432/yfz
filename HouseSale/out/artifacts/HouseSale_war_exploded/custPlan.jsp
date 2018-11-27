<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/11/6
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page import= "com.ht.action.ServiceConstants"%>
<%@ page import="com.ht.vo.CustPlan" %>
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

    <title>客户意向</title>
    <jsp:include page="IncludeJS.jsp" flush="true"/>
</head>
<body>
<DIV id=maintitle>
        <DIV class=newtitle><STRONG>客户意向</STRONG>

        </DIV>
</DIV>
<form action="<%=basePath%>/Cusplan!save.action" method="POST">
    <s:fielderror></s:fielderror>
    <s:hidden name="custPlan.planId" id="planId"></s:hidden>
    <table width="100%"  align="center"  border="0" cellpadding="0" cellspacing="0"  class="tablelist3">
        <tr>
            <td align="right">意向业态:</td>
            <td>
                <s:select list="#request.termTypeInfoList"  name="custPlan.termTypeName" id="termTypeName" listKey="termTypeName" listValue="termTypeName"  cssStyle="width:123px"></s:select>
            </td>
            <td align="right">意向房产:</td>
            <td><s:textfield name="custPlan.hourseid" id="hourseid"  size="15"/></td>
        </tr>
        <tr>
            <td align="right">意向户型:</td>
            <td>
                <s:select list="#request.housemodelList"  name="custPlan.modelId" id="modelId" listKey="modelName" listValue="modelName"  cssStyle="width:123px"></s:select>
            </td>
            <td align="right">付款方式:</td>
            <td>
                <s:select list="#request.paidTypeList"  name="custPlan.paidtypeid" id="paidtypename" listKey="paidtypename" listValue="paidtypename"  cssStyle="width:123px">
                </s:select>
            </td>
        </tr>
        <tr>
            <td align="right">意向总价:</td>
            <td><s:textfield name="custPlan.plantotalPrice" id="plantotalPrice"  size="15"/></td>
            <td align="right">意向单价:</td>
            <td><s:textfield name="custPlan.planunitPrice" id="planunitPrice"  size="15"/></td>
        </tr>
        <tr>
            <td align="right">意向面积:</td>
            <td>
                <select name="custPlan.planArea" id="planArea"  style="width:130px">

                <%
                    CustPlan cust= (CustPlan) request.getAttribute("custPlan");
                    for(int i=0;i<ServiceConstants.PLANAREA.length;i++){
                        if(cust!=null&&cust.getPlanArea().equals(ServiceConstants.PLANAREA[i])){
                %>
                <option value="<%=ServiceConstants.PLANAREA[i] %>" selected><%=ServiceConstants.PLANAREA[i] %></option>
                <%
                }else {
                %>
                <option value="<%=ServiceConstants.PLANAREA[i] %>"><%=ServiceConstants.PLANAREA[i] %></option>
                <%
                }
                    }
                %>
            </select></td>
            <td align="right">优先级:</td>
            <td>
                <s:select list="{'高','中','低'}"  name="custPlan.priority" id="priority"></s:select>
            </td>
        </tr>
        <tr>
            <td align="right">说明:</td>
            <td colspan="3"><s:textfield name="custPlan.demo" id="demo" size="61"/></td>
        </tr>
    </table>
</form>
<div class=newtitle>
    <th>
        <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="savePlan();">保存</a>
    </th>
</div>
</body>
</html>
