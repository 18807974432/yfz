<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/19
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@page import= "com.ht.action.ServiceConstants"%>
<%@ page import="com.ht.vo.CustomerInfo" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
</head>
<body>
    <title>Title</title>
    <jsp:include page="IncludeJS.jsp" flush="true"/>
    <script type="text/javascript">
        function save() {
            document.forms[0].submit();

        }
        function addcity(obj) {
            buttonname=obj;
            $("#editBx").window(
                {
                    href:'<%=basePath%>/SelCity.jsp',
                    onClose:function(){

                    }
                });
            $('#editBx').window('refresh');
            $('#editBx').window('open');
            init();
        }
        function init(){
            var url="${pageContext.request.contextPath}/cusAction!prov.action";
            $.post(
                url,
                {
                    province:1,
                    city:2,
                    county:3
                },
                getProv,
                "json"
            );
        }
        //获取省份数据
        function getProv(data){

            var datalist = data.items;
            var td="";
            $.each(datalist,function(i){
                td+="<option value='"+datalist[i].cityid+"'>";
                td+=datalist[i].cityname;
                td+="</option>";
            });
            $("#province").html(td);//显示在province下拉框中
            pchange();
        }
        //省份发生变化时执行
        function pchange(){
            //获取省份下拉框中选中的值
            var pro = $("#province").val();

            var url="${pageContext.request.contextPath}/cusAction!city.action";
            $.post(
                url,
                {
                    province:pro
                },
                getCity,
                "json"
            );
        };
        //获取城市
        function getCity(data){
            var datalist = data.items;
            var td="";
            $.each(datalist,function(i){
                td+="<option value='"+datalist[i].cityid+"'>";
                td+=datalist[i].cityname;
                td+="</option>";
            });
            $("#city").html(td);//显示在city下拉框中
            cchange();
        }
        //城市发生变化时执行
        function cchange(){
            var pro = $("#city").val();
            var url="${pageContext.request.contextPath}/cusAction!county.action";
            $.post(
                url,
                {
                    city:pro
                },
                getCounty,
                "json"
            );
        };
        //获取城市
        function getCounty(data){
            var datalist = data.items;
            var td="";
            $.each(datalist,function(i){
                td+="<option value='"+datalist[i].cityid+"|"+datalist[i].cityname+"'>";
                td+=datalist[i].cityname;
                td+="</option>";
            });
            $("#county").html(td);//显示在county下拉框中
        }
        function close2(){
            var retvalue = $("#county").val();
            var pos = retvalue.indexOf('|');
            var va = retvalue.substring(pos+1);
            if(buttonname=='btnbaby'){
                $("#babyaddr").val(va);
            }
            if(buttonname=='btnnation'){
                $("#nation").val(va);
            }
            $('#editBx').window('close');
        }
    </script>



<form action="<%=basePath%>/cusAction!save.action" method="post">
 <div title="资料"  data-options="closable:false,cache:false" style="padding:0px;">
    <s:hidden name="custom.custId" id="custid"/>
    <table width="100%" align="center"  border="0" cellpadding="0" cellspacing="0"  class="tablelist3">
        <tr>
            <td align="right">项目名称:</td>
            <td>
            <s:select list="#request.prolist" name="custom.projectid" listKey="projectId" listValue="projectName" class="easyui-combobox"/>
            </td>
            <td align="right">客户名称:</td>
            <td>
                <s:textfield name="custom.custname" id="custname"/>
            </td>
            <td align="right">拼音代码</td>
            <td>
                <s:textfield name="custom.custnamecode" id="custnamecode"/>
            </td>
            <td align="right">客户性质:</td>
            <td>
                <select name="custom.custtype" class="easyui-combobox">
                    <option value="个人客户" selected>个人客户</option>
                    <option value="公司客户">公司客户</option>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right">证件名称:</td>
            <td>
                <s:textfield name="custom.cardname" id="cardname"/>
            </td>
            <td align="right">证件号码:</td>
            <td>
                <s:textfield name="custom.cardno" id="cardno"/>
            </td>
            <td align="right">文化程度:</td>

            <td>
            <select name="custom.degree"  class="easyui-combobox" style="width:175px">
                <%
                    CustomerInfo cust = (CustomerInfo)request.getAttribute("custom");
                    for(int i=0;i<ServiceConstants.DEGREE.length;i++){
                        if(cust !=null && cust.getDegree().equals(ServiceConstants.DEGREE[i])){
                %>
                <option value="<%=ServiceConstants.DEGREE[i] %>" selected><%=ServiceConstants.DEGREE[i] %></option>
                <%
                }else{
                %>
                <option value="<%=ServiceConstants.DEGREE[i] %>"><%=ServiceConstants.DEGREE[i] %></option>
                <%

                        }
                    }
                %>
            </select>
            </td>
            <td align="right">客户类型:</td>
            <td>
                <s:select list="#request.custstateList" name="custom.custstate" listKey="custStateName" listValue="custStateName" class="easyui-combobox"/>
            </td>
        </tr>
        <tr>
            <td align="right">信息来源:</td>
            <td>
                <s:textfield name="custom.activeid" id="activeid"/>
            </td>
            <td align="right">成交几率:</td>
            <td>

                    <select name="custom.salePercent" id="salePercent"  style="width:130px" class="easyui-combobox">
                        <%

                            for(int i=0;i<ServiceConstants.PERCENT.length;i++){
                                if(cust!=null && cust.getSalePercent().equals(ServiceConstants.PERCENT[i])){
                        %>
                        <option value="<%=ServiceConstants.PERCENT[i] %>" selected><%=ServiceConstants.PERCENT[i] %></option>
                        <%
                        }else{
                        %>
                        <option value="<%=ServiceConstants.PERCENT[i] %>"><%=ServiceConstants.PERCENT[i] %></option>
                        <%

                                }
                            }
                        %>
                    </select>
            </td>
            <td align="right">机会阶段:</td>
            <td>
                <select name="custom.chance"  style="width:130px" class="easyui-combobox">
                    <%

                        for(int i=0;i<ServiceConstants.CHANCE.length;i++){
                            if(cust !=null && cust.getChance().equals(ServiceConstants.CHANCE[i])){
                    %>
                    <option value="<%=ServiceConstants.CHANCE[i] %>" selected><%=ServiceConstants.CHANCE[i] %></option>
                    <%
                    }else{
                    %>
                    <option value="<%=ServiceConstants.CHANCE[i] %>"><%=ServiceConstants.CHANCE[i] %></option>
                    <%

                            }
                        }
                    %>
                </select>
            </td>
            <td align="right">事件类型:</td>
            <td>

                    <s:select list="#request.eventTypeList" name="custom.eventtypeid" listKey="eventtypename" listValue="eventtypename" class="easyui-combobox"/>
            </td>
        </tr>
        <tr>
            <td align="right">电子邮件:</td>
            <td>
                <s:textfield name="custom.email" id="email"/>
            </td>
            <td align="right">业务员:</td>
            <td>
            <s:select list="#request.usersList" name="custom.saleperson" listKey="username" listValue="username" class="easyui-combobox"/>
            </td>
            <td align="right">出生日期:</td>
            <td>
                <s:textfield name="custom.birthday" id="birthday" cssClass="easyui-datebox"/>
            </td>
            <td align="right">手机号码:</td>
            <td>
                <s:textfield name="custom.mobile" id="mobile"/>
            </td>


        </tr>
        <tr>
            <td align="right">国籍:</td>
            <td><input type="text" name="custom.country" id="country" value="中国"></td>
            <td align="right">户籍:</td>
            <td><s:textfield name="custom.nation" id="nation" size="15"/><img src="images/sound.gif" id="btnnation" valign="middle" onclick="addcity('btnnation');"/></td>
            <td align="right">籍贯:</td>
            <td><s:textfield name="custom.babyaddr" id="babyaddr" size="15"/><img src="images/sound.gif" id="btnbaby" valign="middle" onclick="addcity('btnbaby');"/></td>
            <td align="right">主管业务员:</td>
            <td>
            <s:select list="#request.usersList" name="custom.chargeperson" listKey="username" listValue="username" class="easyui-combobox"/>
            </td>
        </tr>
        <tr>
            <td align="right">性别:</td>
            <td>

                    <select name="custom.sex" class="easyui-combobox">
                      <option value="男" selected>男</option>
                      <option value="女" >女</option>
                    </select>
            </td>
            <td align="right">联系地址:</td>
            <td colspan="5">
                <s:textfield name="custom.addr" id="addr"/>
            </td>
        </tr>
        <tr>
            <td align="right">办公电话:</td>
            <td>
                <s:textfield name="custom.officeTel" id="officeTel"/>
            </td>
            <td align="right">邮政编码:</td>
            <td>
                <s:textfield name="custom.postcode" id="postcode"/>
            </td>
            <td align="right">客户身份类别:</td>
            <td>
                <s:textfield name="custom.custVip" id="custVip"/>
            </td>
            <td align="right">年龄:</td>
            <td>
                <select name="custom.age"  class="easyui-combobox" style="width:175px">
                    <%
                        for(int i=0;i<ServiceConstants.AGE.length;i++){
                            if(cust !=null && cust.getDegree().equals(ServiceConstants.AGE[i])){
                    %>
                    <option value="<%=ServiceConstants.AGE[i] %>" selected><%=ServiceConstants.AGE[i] %></option>
                    <%
                    }else{
                    %>
                    <option value="<%=ServiceConstants.AGE[i] %>"><%=ServiceConstants.AGE[i] %></option>
                    <%

                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right">登记地点:</td>
            <td>
                <s:textfield name="custom.signAddr" id="signAddr"/>
            </td>
            <td align="right">家庭电话:</td>
            <td>
                <s:textfield name="custom.familyTel" id="familyTel"/>
            </td>
            <td align="right">首选联系电话:</td>
            <td>
                <s:textfield name="custom.primaryTel" id="primaryTel"/>
            </td>
        </tr>
        <tr>
            <td align="center" colspan="8">
                <div>
                <input type="submit" value="提交" onclick="save();" align="center" class="easyui-linkbutton" width="160px;" height="150px;">
                </div>
            </td>
        </tr>
    </table>
 </div>
</form>
</div>

<div class="easyui-window" title="选择籍贯" id="editBx" style="width: 300px; height: 200px;" mode="true" closed="true" href="${pageContext.request.contextPath}/SelCity.jsp">
</div>

</body>
</html>
