<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>用户资料列表</title>
</head>
<body>
    <h2 align="center">用户资料列表</h2>
    <hr/>
    <tr>
        <td>
            <fieldset>
                <legend>查询条件</legend>
                <form:form name="form1" modelAttribute="usersVo" action="/users/listByPage?curPage=${pager.cur_page}" method="post">
                    姓名:<form:input path="userName"/>
                    性别:<form:select path="sex" >
                            <form:option value=""   label="请选择"/>
                            <form:option value="男" label="男"/>
                            <form:option value="女" label="女"/>
                         </form:select>
                    部门:<form:select path="depId">
                            <form:option value="0" label="请选择"/>
                            <form:options items="${depList}" itemValue="depId" itemLabel="depName"/>
                         </form:select>
                        <input type="submit" name="submit" value=" 查 询 "/>
                </form:form>
            </fieldset>
        </td>
    </tr>
    <table align="center" width="1000" border="0">
        <tr>
            <td><a href="/users/addInit">新增</a></td>
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
                    <td>${d.remark}</td>
                    <td><a href="/users/del?userId=${d.userId}">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="/users/find?userId=${d.userId}">修改</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <table width="80%" align="center">
        <tr>
            <td><a href="/users/listByPage?curPage=1">首页</a></td>
            <td>
                <c:if test="${pager.cur_page==1}">
                    <a href="javascript:;">上一页</a>
                </c:if>
                <c:if test="${pager.cur_page>1}">
                    <a href="/users/listByPage?curPage=${pager.cur_page-1}">上一页</a>
                </c:if>
            </td>
            <td>当前页${pager.cur_page}/总页数${pager.totalPages}</td>
            <td>
                <c:if test="${pager.cur_page==pager.totalPages}">
                    <a href="javascript:;">下一页</a>
                </c:if>
                <c:if test="${pager.cur_page<pager.totalPages}">
                    <a href="/users/listByPage?curPage=${pager.cur_page+1}">下一页</a>
                </c:if>
            </td>
            <td><a href="/users/listByPage?curPage=${pager.totalPages}">尾页</a></td>
        </tr>
    </table>
</body>
</html>