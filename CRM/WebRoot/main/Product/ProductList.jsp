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
   
    <title>商品列表</title>
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
					location.href="${pageContext.request.contextPath }/ProductServlet?pager.cur_page="+p+"&pager.pageRow="+s;
				},
				onSelectPage:function(p,s){
					//alert(p+":"+s);
					location.href="${pageContext.request.contextPath }/ProductServlet?pager.cur_page="+p+"&pager.pageRow="+s;
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
			url = "${pageContext.request.contextPath }/ProductServlet?action=update&prodid="+arr;
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
		
		//新增
		function add(){
			url = "${pageContext.request.contextPath }/ProductServlet?action=init";
			location.href=url;
		}
		
		//删除
		function del(){
			var ids = $('input[name="id"]:checked');
			var len = ids.length;
			if(ids.length==0){
				alert("请选择要删除的用户");
				return;
			}
			if(!confirm('删除确认')){
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
			var url = "${pageContext.request.contextPath }/ProductServlet?action=del&prodid="+arr;
			location.href=url;	
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
			    <li><a href="javascript:void(0);" onclick="del();"><span><img src="main/user/images/delete.png"></span>删除</a></li>
			    </ul>
		    </div>
		    </td>
  		</tr>
  	</table>
  	
  	<table class="easyui-datagrid" border="1" pagination="true"  id="changesize" style="width:auto;padding:0px"  singleSelect="false"  datapagesize="onClickRow:getRowData" >
    	<thead>
    		<tr>
    			<th field="id" checkbox="true"  width=80>商品编号</th>
    			<th field="prodname" width=70 align="center">商品名称</th>
    			<th field="prodModel" width=70 align="center">商品型号</th>
    			<th field="prodUnit" width=100 align="center">商品单位</th>
    			<th field="1" width=70 align="center">产品规格</th>
    			<th field="2" width=70 align="center">库存数量</th>
    			<th field="3" width=100 align="center">进货价格</th>
    			<th field="4" width=70 align="center">销售价格</th>
    			<th field="5" width=70 align="center">最低价格</th>
    			<th field="6" width=100 align="center">商品序列号</th>
    			<th field="7" width=70 align="center">商品CDKEY</th>
    			<th field="8" width=70 align="center">已售/未售</th>
    			<th field="9" width=100 align="center">产品供应商</th>
    			<th field="0" width=100 align="center">商品描述</th>
    		</tr>
    	</thead>
    	<tbody>
    	<!--  
    		items:对应的page,request,session,application对象定义的变量名称（集合变量）
    		var:变量名称，对应集合中的当前元素
    	-->
    	 <c:forEach items="${productList}" var="d">
    		<tr>
	  				<td align="center">
						${d.prodid}
					</td>
					<td align="center">
						${d.prodname}
					</td>
					<td align="center">
						${d.prodModel }
					</td>
					<td align="center">
						${d.prodUnit }
					</td>
					<td align="center">
						${d.prodStyle }
					</td>
					<td align="center">
						${d.prodCount }
					</td>
					<td align="center">
						${d.inPrice }
					</td>
					<td align="center">
						${d.salePrice }
					</td>
					<td align="center">
						${d.lowSalePrice }
					</td>
					<td align="center">
						${d.prodSerial }
					</td>
					<td align="center">
						${d.cdKey }
					</td>
					<td align="center">
						${d.saleStatus }
					</td>
					<td align="center">
						${d.supplierId }
					</td>
					<td align="center">
						${d.remark }
					</td>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
  </body>  	
</html>
