<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
          function check(){
              if(form1.userName.value==""){
                  alert("请输入用户名称");
                  form1.userName.focus();
                  return false;
              }
              if(form1.password.value==""){
                  alert("请输入密码");
                  form1.password.focus();
                  return false;
              }
              return true;
          }
    </script>
</head>
<body>
    <br/><br/><br/><br/>
    <h1 align="center">系统登录</h1>
    <table border="0" width="600" align="center">
        <tr><td><hr color="red"/></td></tr>
    </table>
    <form name="form1" action="${pageContext.request.contextPath}/user/login" method="post" onsubmit="return check();">
        <h3 align="center"><font color="red">${msg}</font></h3>
        <table border="1" width="600" align="center" height="90">
            <tr>
                <td>用户名称：</td>
                <td><input type="text" name="userName" id="userName" value="${userName}"></td>
            </tr>
            <tr>
                <td>登录密码：</td>
                <td><input type="password" name="password" id="password" value="${password}"> </td>
            </tr>
            <tr>
                <td align="right">&nbsp;</td>
                <td><input type="checkbox" name="rememberPwd" id="rememberPwd" value="1">记住密码</td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" name="btnLogin" value=" 登 录 "></td>
            </tr>
        </table>
    </form>
</body>
</html>
