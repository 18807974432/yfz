<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增用户</title>
    <script type="text/javascript">
    	function baocun(){
    		//验证应收金额是否数字
    		if(isNaN($("#remainMoney").val())){
    			alert("应收金额含有非法字符!");
    			$("#remainMoney").focus();
    			$("#remainMoney").val("0");
    			return false;
    		}
    		//验证收款金额是否数字
    		if(isNaN($("#paidMoney").val())){
    			alert("收款金额含有非法字符!");
    			$("#paidMoney").focus();
    			$("#paidMoney").val("0");
    			return false;
    		}
    		//验证订单金额是否数字
    		if(isNaN($("#orderMoney").val())){
    			alert("订单金额含有非法字符!");
    			$("#orderMoney").focus();
    			$("#orderMoney").val("0");
    			return false;
    		}
    		//验证是否输入交款时间
    		if($("#paidTime").datebox("getValue")==""){
    			alert("请输入交款时间!");
    			$("#paidTime").focus();
    			return false;
    		}
    	}
    </script>
  </head>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
  <link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
  <body>
	 <br/><br/>
	 <form action="<%=basePath%>financeServlet?action=updateSave&financeid=${financeList.financeId}" method="post" name="form1" onsubmit="return baocun();">
		 		
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">订单号:</label>
		   			<div class="layui-input-inline">
		     			<select name="orderId" id="orderId" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${orderlist}" var="d">
			     				<c:if test="${financeList.orderId eq d.orderId}">
			     					<option value="${d.orderId}" selected>${d.orderId}</option>
			     				</c:if>
			     				<c:if test="${financeList.orderId ne d.orderId}">
			     					<option value="${d.orderId}">${d.orderId}</option>
			     				</c:if>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">产品名称:</label>
		   			<div class="layui-input-inline">
		     			<select name="prodid" id="prodid" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${productlist}" var="d">
		     					<c:if test="${financeList.prodid eq d.prodid}">
			     					<option value="${d.prodid}" selected>${d.prodname}</option>
			     				</c:if>
			     				<c:if test="${financeList.prodid ne d.prodid}">
			     					<option value="${d.prodid}">${d.prodname}</option>
			     				</c:if>
		     				</c:forEach>
		     			</select>
		   			</div>
		   			<label class="layui-form-label">收款方式:</label>
		   			<div class="layui-input-inline">
		     			<select name="paidtypeid" id="paidtypeid" required  lay-verify="required" class="layui-input" autocomplete="off">
		     				<c:forEach items="${paidTypelist}" var="d">
		     					<c:if test="${financeList.paidtypeid eq d.paidtypeid}">
			     					<option value="${d.paidtypeid}" selected>${d.paidtypename}</option>
			     				</c:if>
			     				<c:if test="${financeList.paidtypeid ne d.paidtypeid}">
			     					<option value="${d.paidtypeid}">${d.paidtypename}</option>
			     				</c:if>
		     				</c:forEach>
		     			</select>
		   			</div>
		 		</div>
	 		
		  		<div class="layui-form-item">
		  			<label class="layui-form-label">应收金额:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.remainMoney }" name="remainMoney" id="remainMoney" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">收款金额:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.paidMoney }" name="paidMoney" id="paidMoney" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">订单金额:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.orderMoney }" name="orderMoney" id="orderMoney" required  lay-verify="required" placeholder="必填" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
		 		
		 		<div class="layui-form-item">
		  			<label class="layui-form-label">交款人:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.paidPerson }" name="paidPerson" id="paidPerson" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">入账银行:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.inbank }" name="inbank" id="inbank" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">入账账号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.bankAccount }" name="bankAccount" id="bankAccount" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
		 		
		 		<div class="layui-form-item">
		  			<label class="layui-form-label">出账银行:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.outbank }" name="outbank" id="outbank" autocomplete="off" class="layui-input">
		   			</div>
		  			<label class="layui-form-label">相关凭证号:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.warrant }" name="warrant" id="warrant" autocomplete="off" class="layui-input">
		   			</div>
		   			交款时间：<input type="text" value="${financeList.paidTime }" name="paidTime" id="paidTime" style="width:200px;" placeholder="必填" class="easyui-datebox"">
		 		</div>	
	 			
 			
		  		<div class="layui-form-item">
		  			到账日期：<input type="text" value="${financeList.paidinTime }" name="paidinTime" id="paidinTime" style="width:200px;" class="easyui-datebox"">
		 		
		  			<label class="layui-form-label">是否有效:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.invalid }" name="invalid" id="invalid" autocomplete="off" class="layui-input">
		   			</div>
		   			<label class="layui-form-label">操作类别:</label>
		   			<div class="layui-input-inline">
		     			<input type="text" value="${financeList.oprType }" name="oprType" id="oprType" autocomplete="off" class="layui-input">
		   			</div>
		 		</div>
	 		
		  		
 				<div class="layui-form-item">
				   	<div class="layui-input-block">
				   	  <input type="submit" value="保存" class="layui-btn layui-btn-primary"/>
				      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
				   	</div>
  				</div>
 			
 	</form>
  </body>
</html>
