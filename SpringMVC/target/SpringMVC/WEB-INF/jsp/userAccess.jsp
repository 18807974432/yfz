<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/29
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>权限管理</title>
</head>
<body>
    <h3 align="center">系统权限</h3>
    <form action="/acc/grant" method="post">
        <table align="center">
            <tr>
                <c:forEach items="${sysAccessList}" var="s" varStatus="ss">
                    <c:if test="${ss.index==0}">
                        <td><input type="checkbox" name="sysId" value="${s.sysId}"/>${ss.index}</td>
                        <td>${s.pathName}</td>
                    </c:if>
                    <c:if test="${ss.index>0&&ss.index%5!=0}">
                        <td><input type="checkbox" name="sysId" value="${s.sysId}"/>${ss.index}</td>
                        <td>${s.pathName}</td>
                    </c:if>
                    <c:if test="${ss.index>0&&ss.index%5==0}" >
                        </tr><tr>
                        <td><input type="checkbox" name="sysId" value="${s.sysId}"/>${ss.index}</td>
                        <td>${s.pathName}</td>
                        <tr></tr>
                    </c:if>
                </c:forEach>
            </tr>
            <tr>
                <td align="right" colspan="9">
                    <input type="button" value="全选" id="selectAll">
                    <input type="button" value="取消全选" id="cancel">
                    选择用户:
                    <select name="userId">
                        <c:forEach items="${usersList}" var="u">
                            <option value="${u.userId}">${u.userName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td align="right" ><input type="submit" value="授予权限" ></td>
            </tr>
        </table>
    </form>
    <br/>
    <hr/>
    <br/>
    <h3 align="center">${sessionScope.users.userName}的用户权限：</h3>
    <table align="center" border="1">
        <tr>
            <c:forEach items="${userAccessList}" var="u" varStatus="uu">
                <c:if test="${uu.index==0}">
                    <td><input type="checkbox" value="${u.accId}"/>${uu.index}</td>
                    <td>${u.pathName}</td>
                </c:if>
                <c:if test="${uu.index>0&&uu.index%5!=0}">
                    <td><input type="checkbox" value="${u.accId}"/>${uu.index}</td>
                    <td>${u.pathName}</td>
                </c:if>
                <c:if test="${uu.index>0&&uu.index%5==0}" >
                    </tr><tr>
                    <td><input type="checkbox" value="${u.accId}"/>${uu.index}</td>
                    <td>${u.pathName}</td>
                    <tr></tr>
                </c:if>
            </c:forEach>
        </tr>
    </table>
</body>
</html>
