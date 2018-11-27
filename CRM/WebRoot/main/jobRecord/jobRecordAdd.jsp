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
    
    <title>增加派工单
    </title>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">

  </head>
  	<script type="text/javascript">
  		function count(){
  			
  			
  			//验证是否输入日期
    		if($("#jobDate").datebox("getValue")==""){
    			alert("请输入日期!");
    			$("#jobDate").focus();
    			return false;
    		}
    		//验证是否输入开工时间
    		if($("#startTime").datebox("getValue")==""){
    			alert("请输入开工时间!");
    			$("#startTime").focus();
    			return false;
    		}
    		if($("#busMoney").val()==""){
  				$("#busMoney").focus();
  				$("#busMoney").val("0");
  			}
  			
  			if($("#attachMoney").val()==""){
  				$("#attachMoney").focus();
  				$("#attachMoney").val("0");
  			}
  		}
  	</script>
  <body>
    <h1 align="center"><font color="red" face="隶书">增加派工单</font></h1>
   	<hr/>
   	<form action="<%=basePath%>jobServlet?action=add" method="post" onsubmit="return count();">
	<div class="layui-form-item">
	    <label class="layui-form-label">订单编号</label>
	    <div class="layui-input-inline">
		    <select name="orderId" required lay-verify="required" class="layui-input" autocomplete="off">
		    	<c:forEach items="${ordersList}" var="o">
		    		<option value="${o.orderId}">${o.orderId}</option>
		    	</c:forEach>
		    </select>
	    </div>
	    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	    	日期：<input type="text" name="jobDate" id="jobDate" style="width:200px;" class="easyui-datebox"">
   		
   		<label class="layui-form-label">商品信息</label>
   		<div class="layui-input-inline">
	   		<select name="prodName" required lay-verify="required" class="layui-input">
		    	<c:forEach items="${productList}" var="p"> 
		    		<option value="${p.prodid}">${p.prodname}</option>
		    	</c:forEach>
	   		</select>
   		</div>
   	</div>
   	<div class="layui-form-item">
   		<label class="layui-form-label">客户名称</label>
   		<div class="layui-input-inline">
   			<select name="custid" required lay-verify="required" class="layui-input">
		    	<c:forEach items="${customerList}" var="c">
		    		<option value="${c.custId}">${c.custname}</option>
		    	</c:forEach>
   			</select>
   		</div>
   		<label class="layui-form-label">员工信息</label>
	    <div class="layui-input-inline">
	    	<select name="userid" required lay-verify="required" class="layui-input">
		    	<c:forEach items="${userList}" var="u">
		    		<option value="${u.userid}">${u.username}</option>
		    	</c:forEach>
   			</select>
	    </div>
   		<label class="layui-form-label">完成情况</label>
   		<div class="layui-input-inline">
	    <select name="callback" required lay-verify="required" class="layui-input" >
	    	<option value="已完成">已完成</option>
	        <option value="未完成">未完成</option>
	    </select>
	    </div>
	</div>
   	<div class="layui-form-item">
	    <label class="layui-form-label">派工内容</label>	
   		<div class="layui-input-block">
   			 <textarea type="text" id="jobContent" name="jobContent" required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input"></textarea>
	    </div>
	</div>
   	<div class="layui-form-item"> 
	    <label class="layui-form-label">客户评价</label>
	    <div class="layui-input-block">
	    	<textarea type="text" id="custEval" name="custEval"  required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input"></textarea>
	    </div>
  	</div>
   	<div class="layui-form-item">
   		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		 开工时间：<input type="text" name="startTime" id="startTime" style="width:200px;" class="easyui-datebox"">
	  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  		结束时间：<input type="text" name="endTime" id="endTime" style="width:200px;" class="easyui-datebox"">
	  
		<label class="layui-form-label">客户签名</label>
   		<div class="layui-input-inline">
    	<select name="custSign" required lay-verify="required" class="layui-input">
    		<option value="已签">已签</option>
        	<option value="未签">未签</option>
  		</select>
  		</div>
	</div>
	<div class="layui-form-item">
		<label class="layui-form-label">人工天数</label>
		<div class="layui-input-inline">
  			 <input type="text" id="workDay" name="workDay"  class="layui-input">
	    </div>
	    <label class="layui-form-label">交通费</label>
	    <div class="layui-input-inline">
  			 <input type="text" id="busMoney" name="busMoney"  class="layui-input">
	    </div>
	    <label class="layui-form-label">补助费</label>
	    <div class="layui-input-inline">
  			 <input type="text" id="attachMoney" name="attachMoney" class="layui-input">
	    </div>
	</div>
		<div class="layui-input-block">
	      <button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
	      <button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
    	</div>
	</form>
  </body>
</html>