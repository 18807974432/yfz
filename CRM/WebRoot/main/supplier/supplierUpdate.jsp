<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>修改供应商信息</title>
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
		<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css"
			media="all">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="<%=basePath%>main/layui/css/layui.css"  media="all">
  
	</head>

	<body>

		<h2 align="center">
			修改供应商信息
		</h2>
		<hr />
		<form class="layui-form"
			action="<%=basePath%>/SupplierServlet?action=updateSave" name="forml"
			method="post" onsubmit="return check();">
			<input type="hidden" name="supplierId" value="${dep.supplierId}">
			<div class="layui-form-item">
				<label class="layui-form-label">
					供应商名称
				</label>
				<div class="layui-input-inline">
					<input type="text" id="supplierName" name="supplierName"
						value="${dep.supplierName}" required lay-verify="required"
						placeholder="请输入名称" autocomplete="off" class="layui-input">
				</div>
				<label class="layui-form-label">
					银行账号
				</label>
				<div class="layui-input-inline">
					<input type="text" id="bankAccount" name="bankAccount"
						value="${dep.bankAccount}" required lay-verify="required"
						class="layui-input">
				</div>
				<div>
					<label class="layui-form-label">
						开户银行
					</label>
					<div class="layui-input-inline">
						<input type="text" id="bankName" name="bankName"
							value="${dep.bankName}" required lay-verify="required"
							class="layui-input">
					</div>
				</div>
			</div>
			<div class="layui-form-item">
					<label class="layui-form-label">
							联系人
						</label>
						<div class="layui-input-inline">
							<input type="text" id="contact" name="contact"
								value="${dep.contact}" required lay-verify="required"
								class="layui-input">
						</div>
						<label class="layui-form-label">
							电话
						</label>
						<div class="layui-input-inline">
							<input type="text" id="phone" name="phone"
								value="${dep.phone}" required lay-verify="required"
								class="layui-input">
						</div>
						<label class="layui-form-label">
							联系地址
						</label>
						<div class="layui-input-inline">
							<input type="text" id="addr" name="addr"
								value="${dep.addr}" required lay-verify="required"
								class="layui-input">
						</div>
				</div>
					<div class="layui-form-item">
							<label class="layui-form-label">
							描述
						</label>
						<div class="layui-input-block">
							<input type="text" id="remark" name="remark"
								value="${dep.remark}" required lay-verify="required"
								class="layui-input">
						</div>
							<div class="layui-form-item">
								<div class="layui-input-block">
									<button type="submit" class="layui-btn" lay-submit
										lay-filter="formDemo" align="center">
										立即保存
									</button>
								</div>
							</div>
		</form>
	</body>
</html>
