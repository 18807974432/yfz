<%@ taglib prefix="c" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/19 0019
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>答卷调研</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
</head>
<body topmargin="0" leftmargin="3">
    <form action="researchAction!answer.action?research.reseachid=${research.reseachid }" method="post" name="form1">
        <table width="800" border="0" cellpadding="3" cellspacing="1">
            <tr>
                <td align="center" colspan="2">
                    <h2 align="center"><font color="black">${research.examName }</font></h2>
                    <input type="submit" value="  交卷  ">
                    <input type="button" value="  关闭  " onclick="javascript:$('#editBx4').window('close');">
                    出卷人：${research.userid }&nbsp;&nbsp;&nbsp;&nbsp;
                    共：${research.examCount }道题&nbsp;&nbsp;&nbsp;&nbsp;
                    出题时间：${research.oprtime }
                    <hr color="red" width="100%">
                </td>
            </tr>
            <c:iterator value="#request.rows" var="r" status="st">
                <!-- 查找当前客户是否曾经做过该题目，如果做过把答案保存到dim变量中 -->
                <c:iterator value="#request.answerList" var="answer">
                    <c:if test="%{#r.examid eq #answer.exam.examid}">
                        <c:set  value="%{answer}" var="dim"></c:set>
                    </c:if>
                </c:iterator>
                <tr>
                    <td width="5%" align="right" valign="top">${r.unitNO }.</td>
                    <td width="95%">${r.unitName }<br><br>
                        <input type="hidden" name="examResult[${st.index}].examid" value="${r.examid }">
                        <input type="hidden" name="examResult[${st.index}].orderid" value="${custid}">
                        <input type="hidden" name="examResult[${st.index}].question" value="${research.reseachid }">
                        <c:if test="%{unitType eq '单选题'}">
                            <c:iterator value="#r.item" var="t">
                                <c:if test="%{#t.answer eq #dim.result}">
                                    ${t.orderid }.<input type="radio" name="examResult[${st.index}].result" value="${t.answer}" checked="checked">${t.question}&nbsp;&nbsp;
                                </c:if>
                                <c:else>
                                    ${t.orderid }.<input type="radio" name="examResult[${st.index}].result" value="${t.answer}">${t.question}&nbsp;&nbsp;
                                </c:else>
                            </c:iterator>
                        </c:if>
                        <c:elseif test="%{unitType eq '多选题'}">
                            <c:iterator value="#r.item" var="t">
                                <c:set  value="0" var="ok"></c:set>

                                <c:iterator value="#dim.res" var="mul">
                                    <c:if test="%{#t.answer eq #mul}">
                                        <c:set value="1" var="ok"></c:set>
                                    </c:if>
                                </c:iterator>
                                <c:if test="%{#ok eq 1}">
                                    ${t.orderid }.<input type="checkbox" name="examResult[${st.index}].result" value="${t.answer}" checked="checked">${t.question}&nbsp;&nbsp;
                                </c:if>
                                <c:else>
                                    ${t.orderid }.<input type="checkbox" name="examResult[${st.index}].result" value="${t.answer}">${t.question}&nbsp;&nbsp;
                                </c:else>
                            </c:iterator>
                        </c:elseif>
                        <c:else>
                            <textarea name="examResult[${st.index}].result" cols="105" rows="3">${dim.result }</textarea>
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
    </form>
</body>
</html>
