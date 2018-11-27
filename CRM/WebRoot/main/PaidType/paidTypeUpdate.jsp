<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>修改记录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
	

  </head>
  <script type="text/javascript">
  	$(function(){
  		$("#rename").click(function(){
   			$("#unitName").val("");
    	});
  	});
  </script>
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
	<form action="<%=basePath%>/PaidTypeServlet?action=updateSave&paidtypeid=${paidtype.paidtypeid}" method="post">
  	  <div class="layui-form-item">
	    <label class="layui-form-label">支付方式${paidtype.paidtypeid}</label>
	 	 <div class="layui-input-inline">
	      <select name="paidtypename" required lay-verify="required" class="layui-input">
	        <c:if test="${paidtype.paidtypename eq '银行转账'}">
		        <option value="银行转账" selected>银行转账</option>
		        <option value="支付宝">支付宝</option>
		        <option value="微信">微信</option>
		        <option value="现金">现金</option>
		        <option value="刷卡">刷卡</option>
		        <option value="其他">其他</option>
	        </c:if>
	        <c:if test="${paidtype.paidtypename eq '支付宝'}">
		        <option value="银行转账">银行转账</option>
		        <option value="支付宝" selected>支付宝</option>
		        <option value="微信">微信</option>
		        <option value="现金">现金</option>
		        <option value="刷卡">刷卡</option>
		        <option value="其他">其他</option>
	        </c:if>
	        <c:if test="${paidtype.paidtypename eq '微信'}">
		        <option value="银行转账">银行转账</option>
		        <option value="支付宝">支付宝</option>
		        <option value="微信" selected>微信</option>
		        <option value="现金">现金</option>
		        <option value="4">刷卡</option>
		        <option value="5">其他</option>
	        </c:if>
	        <c:if test="${paidtype.paidtypename eq '现金'}">
		        <option value="银行转账">银行转账</option>
		        <option value="支付宝">支付宝</option>
		        <option value="微信">微信</option>
		        <option value="现金" selected>现金</option>
		        <option value="刷卡">刷卡</option>
		        <option value="其他">其他</option>
	        </c:if>
	        <c:if test="${paidtype.paidtypename eq '刷卡'}">
		        <option value="银行转账">银行转账</option>
		        <option value="支付宝">支付宝</option>
		        <option value="微信">微信</option>
		        <option value="现金">现金</option>
		        <option value="刷卡" selected>刷卡</option>
		        <option value="其他">其他</option>
	        </c:if>
	        <c:if test="${paidtype.paidtypename eq '其他'}">
		        <option value="银行转账">银行转账</option>
		        <option value="支付宝">支付宝</option>
		        <option value="微信">微信</option>
		        <option value="现金">现金</option>
		        <option value="刷卡">刷卡</option>
		        <option value="其他" selected>其他</option>
	        </c:if>
	        
	      </select>
    	</div>
     </div>
  	  <div class="layui-form-item">
    	<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div> 	
  	  </div>
	</form>
  </body>
</html>
