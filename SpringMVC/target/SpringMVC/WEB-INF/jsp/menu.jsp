<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
    <table height="160" align="center">
    	<tr><td align="center"><a href="${pageContext.request.contextPath }/users/listByPage?curPage=1" target="mainFrame">员工管理</a></td></tr>
    	<tr><td align="center"><a href="${pageContext.request.contextPath }/dep/list" target="mainFrame">部门管理</a></td></tr>
        <tr><td align="center"><a href="${pageContext.request.contextPath }/upload/list" target="mainFrame">文件列表</a></td></tr>
    	<tr><td align="center"><a href="${pageContext.request.contextPath }/pwd.jsp" target="mainFrame">修改密码</a></td></tr>
   		<tr><td align="center"><a href="${pageContext.request.contextPath }/user/exit" target="parent.top">安全退出</a></td></tr>
    </table>
  </body>
</html>
