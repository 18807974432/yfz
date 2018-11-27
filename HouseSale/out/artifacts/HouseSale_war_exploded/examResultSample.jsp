<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/23 0023
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="c" %>
<html>
<head>
    <title>答卷调研</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
</head>
<body topmargin="0" leftmargin="3">
    <table width="800" border="0" cellpadding="3" cellspacing="1">
        <tr>
            <td align="center" colspan="2">
                <h2 align="center"><font color="black">${research.examName }</font></h2>
                <input type="button" value="  关闭  " onclick="javascript:$('#editBx1').window('close');">
                出卷人：${research.userid }&nbsp;&nbsp;&nbsp;&nbsp;
                共：${research.examCount }道题&nbsp;&nbsp;&nbsp;&nbsp;
                出题时间：${research.oprtime }
                <hr color="red" width="100%">
            </td>
        </tr>
        <c:iterator value="#request.rows" var="r" status="st">
            <tr>
                <td width="5%" align="right" valign="top">${r.unitNO }.</td>
                <td width="95%">${r.unitName }<br><br>
                    <input type="hidden" name="examResult[${st.index}].examid" value="${r.examid }">
                    <input type="hidden" name="examResult[${st.index}].orderid" value="1">
                    <input type="hidden" name="examResult[${st.index}].question" value="${research.reseachid }">
                    <c:if test="%{unitType eq '单选题'}">
                        <c:iterator value="#r.item" var="t">
                            ${t.orderid }.<input type="radio" name="examResult[${st.index}].result" value="${t.answer}">${t.question}&nbsp;&nbsp;
                        </c:iterator>
                    </c:if>
                    <c:elseif test="%{unitType eq '多选题'}">
                        <c:iterator value="#r.item" var="t">
                            ${t.orderid }.<input type="checkbox" name="examResult[${st.index}].result" value="${t.answer}">${t.question}&nbsp;&nbsp;
                        </c:iterator>
                    </c:elseif>
                    <c:else>
                        <textarea name="examResult[${st.index}].result" cols="105" rows="3"></textarea>
                    </c:else>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <hr >
                </td>
            </tr>
        </c:iterator>
    </table>
</body>
</html>
