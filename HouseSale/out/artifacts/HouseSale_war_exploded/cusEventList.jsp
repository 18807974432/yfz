<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import= "com.ht.action.ServiceConstants"%>
<%@ page import="com.ht.vo.CustEvent" %>
<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/30
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>客户事件</title>
    <jsp:include page="IncludeJS.jsp"/>
</head>
<body>
  <script type="text/javascript">
    function save() {
        document.forms[0].submit();
    }
  </script>
  <DIV id=maintitle>

          <DIV class=newtitle><STRONG>客户事件</STRONG>

          </DIV>

  </DIV>
   <s:form action="custEvent!save.action" name="form1" method="post">


        <s:hidden name="custEvent.eventId" id="eventIds"/>
        <s:hidden name="custEvent.custid" id="custids"/>
       <table width="100%" align="center"  border="0" cellpadding="0" cellspacing="0"  class="tablelist3">
           <tr>
               <td>成交几率</td>
                <td>
                   <select name="custEvent.salePercent" id="salePercents">
               <%
                   CustEvent cust= (CustEvent) request.getAttribute("custEvent");
                   for(int i=0;i<ServiceConstants.PERCENT.length;i++){
                       if(cust!=null&&cust.getSalePercent().equals(ServiceConstants.PERCENT[i])){


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
               <td>机会阶段</td>
               <td>
               <select name="custEvent.chance" id="chances">
                   <%

                       for(int i=0;i<ServiceConstants.CHANCE.length;i++){
                           if(cust!=null&&cust.getChance().equals(ServiceConstants.CHANCE[i])){


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
           </tr>
           <tr>
               <td>事件类型</td>
               <td><s:select list="#request.eventTypeList" name="custEvent.eventtypeid" listKey="eventtypename" listValue="eventtypename" id="eventtypeids"></s:select></td>
               <td>事件时间</td>
               <td><s:textfield cssClass="easyui-datebox" id="eventTimes" name="custEvent.eventTime"/></td>
           </tr>
           <tr>
               <td>说明</td>
               <td colspan="3"><s:textfield name="custEvent.description" id="descriptions"/></td>
           </tr>

       </table>
   </s:form>
   <div class=newtitle>
       <th>
           <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="saveEvent();">保存</a>
       </th>
   </div>
</body>
</html>
