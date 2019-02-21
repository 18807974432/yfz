<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>用户资料列表</title>
</head>
<body>
    <h2 align="center">用户资料列表</h2>
    <hr/>
    <fieldset>
        <legend>查询条件</legend>
        <form name="form1" action="${pageContext.request.contextPath}/usersServlet?action=list" method="post">
            姓名:<input type="text" name="userName">
            性别:<select name="sex">
                    <option value="">---不选择---</option>
                    <option value="男">男</option>
                    <option value="女">女</option>
                 </select>
            状态:<select name="status">
                    <option value="-1">---不选择---</option>
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                 </select>
            部门:<select name="depId" style="width:150px">
                    <option value="0">---不选择---</option>
                    <c:forEach items="${depList}" var="d">
                        <option value="${d.depId }">${d.depName}</option>
                    </c:forEach>
                 </select>
                 <input type="submit" name="submit" value="  查 询  ">
        </form>
    </fieldset>
    <table align="center" width="1000" border="0">
        <tr>
            <td><a href="${pageContext.request.contextPath}/usersServlet?action=init">新增</a></td>
        </tr>
    </table>
    <table align="center" width="1000" border="1">
        <thead>
            <tr>
                <td>用户编号</td>
                <td>用户名称</td>
                <td>真实名字</td>
                <td>部门</td>
                <td>职位</td>
                <td>年龄</td>
                <td>性别</td>
                <td>状态</td>
                <td>备注</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${usersList}" var="d">
                <tr>
                    <td>${d.userId}</td>
                    <td>${d.userName}</td>
                    <td>${d.name}</td>
                    <td>${d.depName}</td>
                    <td>${d.job}</td>
                    <td>${d.age}</td>
                    <td>${d.sex}</td>
                    <td>
                        <c:if test="${d.status==0}">禁用</c:if>
                        <c:if test="${d.status==1}">启用</c:if>
                    </td>
                    <td>${d.remark}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/usersServlet?userId=${d.userId}&action=update">修改</a>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/usersServlet?userId=${d.userId}&action=del">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table align="center" width="1000" border="1">
        <tr>
            <td><a href="${pageContext.request.contextPath}/usersServlet?action=list&curPage=1">首页</a></td>
            <td><a href="${pageContext.request.contextPath}/usersServlet?action=list&curPage=${pager.cur_page-1}">上一页</a></td>
            <td><a href="${pageContext.request.contextPath}/usersServlet?action=list&curPage=${pager.cur_page+1}">下一页</a></td>
            <td><a href="${pageContext.request.contextPath}/usersServlet?action=list&curPage=${pageCount}">末页</a></td>
            <td>当前页/总页数</td>
            <td>总记录数</td>
        </tr>
    </table>
</body>
</html>