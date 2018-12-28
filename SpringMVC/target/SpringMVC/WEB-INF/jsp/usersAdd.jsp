<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/13
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>新增用户</title>
</head>
<body>
    <h2 align="center">新增员工</h2>
    <hr/>
    <form:form name="form1" modelAttribute="usersVo" action="/users/add" method="post">
        <form:hidden path="userId" />
        <table border="1" width="1000" align="center">
            <tr>
                <td align="center">用户名称:</td>
                <td><form:input path="userName"/></td>
            </tr>
            <tr>
                <td align="center">真实姓名:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td align="center">登录密码</td>
                <td><form:password path="password"/></td>
            </tr>
            <tr>
                <td align="center">确认密码:</td>
                <td><input type="password" name="cfgPwd"></td>
            </tr>
            <tr>
                <td align="center">所属部门:</td>
                <td>
                    <form:select path="depId">
                        <form:options items="${depList}" itemValue="depId" itemLabel="depName"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td align="center">年龄:</td>
                <td><form:input path="age"/></td>
            </tr>
            <tr>
                <td align="center">性别:</td>
                <td>
                    <form:radiobutton path="sex" value="男" label="男"/>
                    <form:radiobutton path="sex" value="女" label="女"/>
                </td>
            </tr>
            <tr>
                <td align="center">工作岗位:</td>
                <td><form:input path="job"/></td>
            </tr>
            <tr>
                <td align="center">备注:</td>
                <td><form:textarea path="remark" style="width:900px;height:200px"></form:textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="  保 存  ">
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
