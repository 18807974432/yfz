<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title> 上传流程定义文件  </title>
    

  </head>
  
  <body>
  	<h2>发布流程</h2>
  	<hr/>
  	<s:form action="loginAction_upload" method="post" enctype="multipart/form-data">
  		选择文件：<input type="file" name="pdFile"> <br>
  		 <input type="submit" value="上传"> 
  	</s:form>
  </body>
</html>
