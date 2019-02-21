<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/13
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>新增用户</title>
</head>
<body>
    <h2 align="center">新增员工</h2>
    <hr/>
    <form name="form1" action="${pageContext.request.contextPath }/usersServlet?action=add" method="post">
        <table border="1" width="1000" align="center">
            <tr>
                <td align="center">用户名称:</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td align="center">真实姓名:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td align="center">登录密码</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td align="center">确认密码:</td>
                <td><input type="password" name="cfgPwd"></td>
            </tr>
            <tr>
                <td align="center">所属部门:</td>
                <td>

                    <select name="depId" style="width:150px">
                        <c:forEach items="${depList}" var="d">
                            <option value="${d.depId }">${d.depName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td align="center">年龄:</td>
                <td><input type="text" name="age"></td>
            </tr>
            <tr>
                <td align="center">性别:</td>
                <td>
                    <input type="radio" name="sex" value="男" checked="checked">男
                    <input type="radio" name="sex" value="女">女
                </td>
            </tr>
            <tr>
                <td align="center">工作岗位:</td>
                <td><input type="text" name="job"></td>
            </tr>
            <tr>
                <td align="center">备注:</td>
                <td><textarea name="remark" style="width:900px;height:200px"></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="  保 存   ">
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
