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
    
    <title>修改商品信息</title>
		<script type="text/javascript">
	    	function check(){
	    		if(form1.supplierName.value==""){
	    			alert("请输入用户名称");
	    			form1.supplierName.focus();
	    			return false;
	    		}
	    		return true;
	    	}
	    </script>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
    <link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
 </head>
  
  <body>
    

		<h2 align="center">修改商品信息</h2>
		<hr>
	<form action="${pageContext.request.contextPath}/ProductServlet?action=updateSave" method="post" name="form1" onsubmit="return check();">
		 
		 <div class="layui-form-item"> 
    		<label class="layui-form-label">商品编号:</label>
    		<div class="layui-input-inline">
    			<input type="text" name="prodid" value="${dep.prodid}" required lay-verify="required" class="layui-input">
			</div>
    		<label class="layui-form-label">商品名称:</label>
    		<div class="layui-input-inline">	
    			<input type="text" name="prodname" value="${dep.prodname}" required lay-verify="required" class="layui-input">
    		</div>
    		<label class="layui-form-label">商品型号:</label>
    		<div class="layui-input-inline">
    			<input type="text" name="prodModel" value="${dep.prodModel}" class="layui-input">
    		</div>
    	</div>
		<div class="layui-form-item"> 
    		<label class="layui-form-label">单位名称:</label>
    		<div class="layui-input-inline">
    			<select name="prodUnit" required lay-verify="required" class="layui-input">
			    	<option value="">请选择</option>
			    	<c:forEach items="${unitlist}" var="d">
			    		<option value="${d.unitId}">${d.unitName}</option>
			    	</c:forEach>
			    </select>	
   			</div>
    		<label class="layui-form-label">产品规格:</label>
    		<div class="layui-input-inline">
    			<input type="text" name="prodStyle" value="${dep.prodStyle}" class="layui-input">
    		</div>
    		<label class="layui-form-label">库存数量:</label>
    		<div class="layui-input-inline">
    			<input type="text" name="prodCount" value="${dep.prodCount}" class="layui-input">
    		</div>
		</div>
		<div class="layui-form-item"> 
    		<label class="layui-form-label">进货价格：</label>
    		<div class="layui-input-inline">
    			<input type="text" name="inPrice" value="${dep.inPrice}" class="layui-input">
			</div>
    		<label class="layui-form-label">销售价格:</label>
    		<div class="layui-input-inline">	
    			<input type="text" name="salePrice" value="${dep.salePrice}" class="layui-input">
    		</div>
    		<label class="layui-form-label">最低价格:</label>
    		<div class="layui-input-inline">
    			<input type="text" name="lowSalePrice" value="${dep.lowSalePrice}" class="layui-input">
    		</div>
    	</div>
		<div class="layui-form-item"> 
    		<label class="layui-form-label">商品序列号:</label>
    		<div class="layui-input-inline">	
    			<input type="text" name="prodSerial" value="${dep.prodSerial}" class="layui-input">
    		</div>
    		<label class="layui-form-label">商品CDKEY:</label>
    		<div class="layui-input-inline">	
    			<input type="text" name="cdKey" value="${dep.cdKey}" class="layui-input">
    		</div>
    		<label class="layui-form-label">单选框</label>
   			<div class="layui-input-block">
      			<input type="radio" name="saleStatus" value="已售">已售
      			<input type="radio" name="saleStatus" value="未售" checked>未售
   			</div>
   		</div>
   		<div class="layui-form-item">
    		<label class="layui-form-label">商品供应商:</label>
    		<div class="layui-input-inline">
    			<select name="supplierId" required lay-verify="required" class="layui-input">
			    	<option value="">请选择</option>
			    	<c:forEach items="${supplierList}" var="d">
			    		<option value="${d.supplierId}">${d.supplierName}</option>
			    	</c:forEach>
			    </select>
   			</div>
    	<label class="layui-form-label">描述:</label>
    	<div class="layui-input-INLINE">
	      <textarea type="text" id="remark" name="remark" required  lay-verify="required" placeholder="请输入信息" autocomplete="off" class="layui-input">${dep.remark}</textarea>
	    </div>
	  </div>
   	  <div class="layui-input-block">
      	<button type="submit" class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
      	<button type="reset" class="layui-btn layui-btn-primary" id="rename">重置</button>
   	  </div>
    </form>
  </body>
</html>
