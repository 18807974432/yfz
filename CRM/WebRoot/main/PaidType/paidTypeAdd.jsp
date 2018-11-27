<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>付款方式</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">

  </head>
  	
  <body>
  <body>
   	<style type="text/css">
		table
		  {
		  	border-collapse:collapse;
		  }
	
		table, td, th
		  {
		 	 border:1px solid black;
		  }
	</style>
	<script type="text/javascript">
		function formDemo(){
			alert($("#paidtypename").val());
			location.href="${pageContext.request.contextPath}/PaidTypeServlet?action=add&paidtypename="
			+$("#paidtypename").val();
		}
	</script>
	
	<form action="<%=basePath%>/PaidTypeServlet?action=add" method="post">
	<div class="layui-form-item">
	    <label class="layui-form-label">支付方式</label>
	 	 <div class="layui-input-inline">
	      <select name="paidtypename" required lay-verify="required" class="layui-input" autocomplete="off">
	        <option value="">请选择付款方式</option>
	        <option value="银行转账">银行转账</option>
	        <option value="支付宝">支付宝</option>
	        <option value="微信">微信</option>
	        <option value="现金">现金</option>
	        <option value="刷卡">刷卡</option>
	        <option value="其他">其他</option>
	      </select>
    	</div>
     </div>
  	  <div class="layui-form-item">
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn"  onclick="formDemo();">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div>
  	  </div>	
	</form>
  </body>
</html>
