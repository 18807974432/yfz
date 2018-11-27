<%--
  Created by IntelliJ IDEA.
  User: LW
  Date: 2018/10/19
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page import="com.ht.vo.HourseInfo"%>
<%@page import="com.ht.vo.SaleState"%>
<%@ page import="com.ht.vo.WorkProcess" %>
<%@ page import="java.util.List" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>售楼指引</title>
    <STYLE>
        .newtitle {
            BORDER-RIGHT: #c0dcf2 1px solid; PADDING-RIGHT: 0px; BORDER-TOP: #c0dcf2 1px solid; PADDING-LEFT: 15px; FONT-SIZE: 14px; BACKGROUND: url(images/maintitle.jpg); PADDING-BOTTOM: 4px; MARGIN: 0px 5px 2px 0px; BORDER-LEFT: #c0dcf2 1px solid; LINE-HEIGHT: 20px; PADDING-TOP: 4px; BORDER-BOTTOM: #c0dcf2 1px solid; HEIGHT: 20px! important
        }
        HTML {
            FONT-SIZE: 100%; MARGIN-BOTTOM: 1px; OVERFLOW: hidden; HEIGHT: 100%
        }
        BODY {
            FONT-SIZE: 9px; COLOR: #555; FONT-FAMILY: "微软雅黑","宋体"
        }
        td{
            border-right-width: 1px;
            border-top-style: solid;
            border-right-style: solid;
            border-top-color: #ffffff;
            border-right-color: #ffffff;
            FONT-SIZE: 12px;
            COLOR: #000000;
            font-family:serif

        }
    </STYLE>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
</head>
<body leftmargin="0" topmargin="0" bgcolor="#f0ffff">

<script type="text/javascript">
    var house_id="0";
    function findid(){
        //取出id=tb1的所有的列,第一单元所有的房产id值
        var t = $("#tb1 td");
        var id="";
        var txt="";
        $(t).each(function(){
            txt = $(this).text();
            if(txt=="101"){
                house_id=$(this).attr("id");
            }
        });
        var url = "${pageContext.request.contextPath }/guideAction!json.action";
        $.post(
            url,
            {
                "houseInfo.hourseId":house_id
            },
            updemp,
            "json"
        );
    }
    function getHouseInfo(obj){
        house_id= $(obj).attr("id");
        var url = "${pageContext.request.contextPath }/guideAction!json.action";
        $.post(
            url,
            {
                "houseInfo.hourseId":house_id
            },
            updemp,
            "json"
        );
    }
    function updemp(data){
        $("#saleArea").val(data.houseInfo.saleArea);
        $("#unitPrice").val(data.houseInfo.unitPrice);
        $("#totalPrice").val(data.houseInfo.totalPrice);
        var tds="";
        tds +="<tr height='30'><td align='right'>房产:</td><td>"+data.houseInfo.hourseName+"</td></tr>";
        tds +="<tr height='30'><td align='right'>业态:</td><td>"+data.houseInfo.termType+"</td></tr>";
        tds +="<tr height='30'><td align='right'>户型:</td><td>"+data.houseInfo.modelname+"</td></tr>";
        tds +="<tr height='30'><td align='right'>建筑面积:</td><td>"+data.houseInfo.buildArea+"</td></tr>";
        tds +="<tr height='30'><td align='right'>销售面积:</td><td>"+data.houseInfo.saleArea+"</td></tr>";
        tds +="<tr height='30'><td align='right'>套内面积:</td><td>"+data.houseInfo.inArea+"</td></tr>";
        tds +="<tr height='30'><td align='right'>建筑面积单价:</td><td>"+data.houseInfo.buildunitPrice+"</td></tr>";
        tds +="<tr height='30'><td align='right'>套内面积单价:</td><td>"+data.houseInfo.inunitPrice+"</td></tr>";
        tds +="<tr height='30'><td align='right'>销售单价:</td><td>"+data.houseInfo.unitPrice+"</td></tr>";
        tds +="<tr height='30'><td align='right'>销售总价:</td><td>"+data.houseInfo.totalPrice+"</td></tr>";
        var stat = data.houseInfo.saleState;
        if(stat=='认购' || stat=='签约'){
            tds +="<tr height='30'><td align='right'>客户:</td><td>"+data.custInfo.custname+"</td></tr>";
            tds +="<tr height='30'><td align='right'>业务员:</td><td>"+data.custInfo.saleperson+"</td></tr>";
        }else{
            tds +="<tr height='30'><td align='right'>客户:</td><td>&nbsp;</td></tr>";
            tds +="<tr height='30'><td align='right'>业务员:</td><td>&nbsp;</td></tr>";
        }
        tds +="<tr height='30'><td align='right'>当前状态:</td><td>"+stat+"</td></tr>";
        tds +="<tr height='30'><td align='right' width='80px'>备注:</td><td>"+data.houseInfo.description+"</td></tr>";

        $("#houseinfoTable").html(tds);
        if(stat=='可售'){
            tds="<tr><td align='center'><img src='<%=basePath%>images/flowstar_formal.gif' onclick='buyhouse();'></td></tr>";
        }else{
            tds=data.tds;
        }
        $("#saleStateTable").html(tds);
    }
    function buyhouse(){
        if(house_id==""){
            alert("请选择您要订购的房号");
            return;
        }

        var saleArea = $("#saleArea").val();
        var unitPrice = $("#unitPrice").val();
        var totalPrice= $("#totalPrice").val();
        if(saleArea=="" || parseFloat(saleArea)<1){
            alert("销售面积大于0才能订购房产");
            return;
        }
        if(unitPrice=="" || parseFloat(unitPrice)<1){
            alert("销售单价大于0才能订购房产");
            return;
        }
        if(totalPrice=="" || parseFloat(totalPrice)<1){
            alert("销售总价大于0才能订购房产");
            return;
        }

        var url = "${pageContext.request.contextPath }/custInfoAction!list.action?houseid="+house_id;
        //alert(url);
        location.href=url;
    }
    function changeTerm(){
        document.forms[0].submit();
    }
</script>

    <input type="hidden" name="saleArea" id="saleArea">
    <input type="hidden" name="unitPrice" id="unitPrice">
    <input type="hidden" name="totalPrice" id="totalPrice">
<table width="100%" border="0" height="420" cellpadding="0" cellspacing="0">
    <tr>
        <td width="40%">
            <table width="100%" height="100%" border="0"  cellpadding="0" cellspacing="0">
                <s:form action="guideAction!guide" method="post">
                    <tr height="20">
                        <td>项目：<s:select name="proj.projectId" list="#request.projInfo" listKey="projectId" listValue="projectName" style="width:420px" onchange="changeTerm();"></s:select></td>
                    </tr>
                    <s:if test="#request.termList.size>0">
                        <tr height="20">
                            <td>楼栋：<s:select name="termInfo.termId"  list="#request.termList" listKey="termId" listValue="termName"  style="width:420px"  onchange="changeTerm();"></s:select></td>
                        </tr>
                    </s:if>
                </s:form>
                <tr>
                    <td valign="bottom">
                        <div id="tt" class="easyui-tabs" style="height:400px;width:450px">
                            <div title="楼盘信息" data-options="closable:false,cache:false" style="padding:0px;">
                                <table width="100%" height="100%"  border="0" cellpadding="5" cellspacing="0">
                                    <tr>
                                        <%
                                            int map[][] = (int[][])request.getAttribute("maps");
                                            int mapkey=0,mapvalue=0,index=0,listsize=0;
                                            List houseList = (List)request.getAttribute("houseData");
                                            List saleList = (List)request.getAttribute("saleState");
                                            HourseInfo info = null;
                                            if(map==null){
                                                listsize=0;
                                            }else{
                                                listsize=houseList.size();
                                            }
                                            if(listsize>0){
                                                for(int k=0;k<map.length;k++){
                                                    mapkey = map[k][0];//单元
                                                    mapvalue =map[k][1]; //楼层对应的房子套数
                                        %>
                                        <td valign="bottom">
                                            <table border="1" cellpadding="0" cellspacing="0" id="tb<%=mapkey%>">
                                                <%
                                                    while((index+1)<listsize){
                                                        info = (HourseInfo)houseList.get(index);
                                                        if(info.getUnitid()!=mapkey){
                                                            break;
                                                        }
                                                %>
                                                <tr>
                                                    <%
                                                        for(int i=0;i<mapvalue;i++){
                                                            info = (HourseInfo)houseList.get(index);
                                                            index++;
                                                            if(index>listsize){
                                                                break;
                                                            }
                                                            if(mapkey!=info.getUnitid()){
                                                                break;
                                                            }
                                                            String bgcolor="";
                                                            //取出房子状态对应的颜色
                                                            for(int j=0;j<saleList.size();j++){
                                                                SaleState sale = (SaleState)saleList.get(j);
                                                                if(sale.getSaleName().equals(info.getSaleState())){
                                                                    bgcolor=sale.getBgcolor();
                                                                    break;
                                                                }
                                                            }
                                                    %>
                                                    <td align="center" width="35" bgcolor="<%=bgcolor %>" id=<%=info.getHourseId() %> onclick="getHouseInfo(this);"><%=info.getFloor() %><%=info.getHourseno() %></td>
                                                    <%
                                                        }%></tr><%
                                                }
                                            %>
                                                <tr><td align="center" colspan="<%=mapvalue %>"><%=mapkey %>单元</td></tr>
                                            </table>
                                        </td>
                                        <%
                                                }
                                            }
                                        %>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table width="100%" border="1"  cellpadding="0" cellspacing="0">
                            <tr>
                                <s:iterator value="#request.saleState" var="sale">
                                    <td  align="center" bgcolor="${sale.bgcolor}">${sale.saleName }</td>
                                </s:iterator>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
        <td width="25%" valign="top">
            <table border="1" cellpadding="0" cellspacing="0" id="houseinfoTable" width="100%">

            </table>
        </td>
        <td width="35%" valign="top">
            <table border="1" cellpadding="0" cellspacing="0" id="saleStateTable" width="100%" height="420">

            </table>
        </td>
    </tr>
</table>
<script type="text/javascript">
    findid();
</script>
</body>
</html>

