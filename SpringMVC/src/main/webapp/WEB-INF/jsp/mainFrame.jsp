<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<frameset rows="120,*">
		<frame src="${pageContext.request.contextPath}/user/top">
		<frameset cols="200,*" bordercolor="red">
			<frame src="${pageContext.request.contextPath}/user/menu">
			<frame src="${pageContext.request.contextPath}/users/listByPage?curPage=1" name="mainFrame">
		</frameset>  
	</frameset>
</html>
