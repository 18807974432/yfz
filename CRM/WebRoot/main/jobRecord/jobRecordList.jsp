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
    <link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>main/user/js/jquery-easyui-1.3.0/themes/icon.css">
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/jquery-easyui-1.3.0/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=basePath%>main/user/js/opentabs.js"></script>
	<link href="<%=basePath%>main/user/style/style.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="<%=basePath%>main/user/js/dh.js"></script>
   
    <title>部门列表</title>
    <script type="text/javascript">
    	$(function(){
			var pager = $(".easyui-datagrid").datagrid('getPager');
			$(pager).pagination({
				total:${pager.totalRows},
				pageSize:${pager.pageRow},
				displayMsg:'{from}-{to}/{total}',
				pageNumber:${pager.cur_page},
				afterPageText:'页，共{pages}页',
				pageList:[3,4,5,6,7],
				loading:false,
				showPageList:true,
				showRefresh:true,
				//刷新方法
				onBeforeRefresh:function(p,s){
					location.href="${pageContext.request.contextPath }/jobServlet?pager.cur_page="+p+"&pager.pageRow="+s;
				},
				onSelectPage:function(p,s){
					//alert(p+":"+s);
					location.href="${pageContext.request.contextPath }/jobServlet?pager.cur_page="+p+"&pager.pageRow="+s;
				}
			});
			$('#changesize').datagrid('resize', {
				height:document.body.clientHeight-45
			});
		});	
    	$(function(){
			$('#changesize').datagrid('resize', {
				height:document.body.clientHeight-45
			});
		});	
    	//修改
    	function edit(){
    		alert("修改");
			var ids = $('input[name="id"]:checked');
			var len = ids.length;
			if(ids.length==0){
				alert("请选择要修改的用户");
				return;
			}
			if(ids.length>1){
				alert("只能选择一个用户");
				return;
			}			
			var arr="";
			ids.each(function(i,d){
				if((i+1)!=len){
					arr += $(d).val()+",";
				}else{
					arr += $(d).val();
				}
			})
			url = "${pageContext.request.contextPath }/jobServlet?action=update&jobId="+arr;
			location.href=url;	
		}
		
		//新增
		function add(){
			url = "${pageContext.request.contextPath }/jobServlet?action=init";
			location.href=url;
		}
		
		//单击某一行时会触发行的onClickRow,value是行索引，rec代表当前选中的行,包含了所有字段的值
		function getRowData(value,rec){
			var id= rec.id;
			//alert(rec.depname);
		}
		function save(){
			document.forms[1].submit();
		}
		
		if("${msg}"!=null&&"${msg}".length>0){
				alert("${msg}");
		}
    </script>

  </head>
  
  <body>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" BACKGROUND="images/maintitle.jpg" class="tablelist2">
  		<tr  class="tableselect2">
  			<td>
			<div class="tools">
				<ul class="toolbar">
				<li class="click"><a href="javascript:void(0);" onclick="add();"><span><img src="main/user/images/add.png"></span>添加</a></li>
			    <li class="click"><a href="javascript:void(0);" onclick="edit();"><span><img src="main/user/images/Mod.png"></span>修改</a></li>
			    </ul>
		    </div>
		    </td>
  		</tr>
  	</table>
  	
  	<table class="easyui-datagrid" border="1" pagination="true"  id="changesize" style="width:auto;padding:0px"  singleSelect="false"  datapagesize="onClickRow:getRowData" >
    	<thead>
    		<tr>
    			<th field="id" checkbox="true"  width=50>派工编号</th>
    			<th field="username" width=70 align="center">订单号</th>
    			<th field="depname" width=150 align="center">日期</th>
    			<th field="jobname" width=100 align="center">商品信息</th>
    			<th field="phone" width=100 align="center">客户名称</th>
    			<th field="cardno" width=100 align="center">派工内容</th>
    			<th field="qqcode" width=100 align="center">派工完成内容</th>
    			<th field="joindate" width=100 align="center">员工信息</th>
    			<th field="basesalary" width=80 align="center">客户评价</th>
    			<th field="degsalary" width=80 align="center">客户签名</th>
    			<th field="ssss" width=150 align="center">开始时间</th>
    			<th field="managerType" width=150 align="center">结束时间</th>
    			<th field="status" width=70 align="center">人工天数</th>
    			<th field="234234" width=80 align="center">交通费</th>
    			<th field="23452323" width=80 align="center">补助费</th>
    		</tr>
    	</thead>
    	<tbody>
    	<!--  
    		items:对应的page,request,session,application对象定义的变量名称（集合变量）
    		var:变量名称，对应集合中的当前元素
    	-->
    	 <c:forEach items="${jobRecordList}" var="j">
    		<tr>
    			<td align="center">${j.jobId }</td>
    			<td align="center">${j.orderId}<br></td>
				<td align="center">${j.jobDate}<br></td>
				<td align="center">${j.prodName}<br></td>
				<td align="center">${j.custid}<br></td>
				<td align="center">${j.jobContent}<br></td>
				<td align="center">${j.callback}<br></td>
				<td align="center">${j.userid}<br></td>
				<td align="center">${j.custEval}<br></td>
				<td align="center">${j.custSign}<br></td>
				<td align="center">${j.startTime}<br></td>
				<td align="center">${j.endTime}<br></td>
				<td align="center">${j.workDay}<br></td>
				<td align="center">${j.busMoney}<br></td>
				<td align="center">${j.attachMoney}<br></td>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
  </body>
  	
</html>
