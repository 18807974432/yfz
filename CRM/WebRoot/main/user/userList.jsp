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
					location.href="${pageContext.request.contextPath }/usersServlet?pager.cur_page="+p+"&pager.pageRow="+s;
				},
				onSelectPage:function(p,s){
					//alert(p+":"+s);
					location.href="${pageContext.request.contextPath }/usersServlet?pager.cur_page="+p+"&pager.pageRow="+s;
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
			url = "${pageContext.request.contextPath }/usersServlet?action=update&userid="+arr;
			location.href=url;	
		}
		//禁用
		function enable(){
			var ids = $('input[name="id"]:checked');
			var len = ids.length;
			if(ids.length==0){
				alert("请选择要禁用的用户");
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
			url = "${pageContext.request.contextPath }/usersServlet?action=status&status=0&userid="+arr;
			location.href=url;	
		}
		//启用
		function disable(){
			var ids = $('input[name="id"]:checked');
			var len = ids.length;
			if(ids.length==0){
				alert("请选择要启用的用户");
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
			url = "${pageContext.request.contextPath }/usersServlet?action=status&status=1&userid="+arr;
			location.href=url;	
		}
		
		//新增
		function add(){
			url = "${pageContext.request.contextPath }/usersServlet?action=init";
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
			    <li><a href="javascript:void(0);" onclick="enable();"><span><img src="main/user/images/login_icon01.png"></span>禁用</a></li>
			    <li><a href="javascript:void(0);" onclick="disable();"><span><img src="main/user/images/evaluate.png"></span>启用</a></li>
			    </ul>
		    </div>
		    </td>
  		</tr>
  	</table>
  	
  	<table class="easyui-datagrid" border="1" pagination="true"  id="changesize" style="width:auto;padding:0px"  singleSelect="false"  datapagesize="onClickRow:getRowData" >
    	<thead>
    		<tr>
    			<th field="id" checkbox="true"  width=50>用户编号</th>
    			<th field="username" width=70 align="center">用户名称</th>
    			<th field="depname" width=70 align="center">所属部门</th>
    			<th field="jobname" width=100 align="center">职务名称</th>
    			<th field="phone" width=100 align="center">联系电话</th>
    			<th field="cardno" width=150 align="center">身份证号</th>
    			<th field="qqcode" width=100 align="center">QQ号码</th>
    			<th field="joindate" width=100 align="center">入职日期</th>
    			<th field="basesalary" width=80 align="center">基本工资</th>
    			<th field="degsalary" width=80 align="center">岗位工资</th>
    			<th field="managerType" width=80 align="center">管理员类别</th>
    			<th field="status" width=70 align="center">状态</th>
    		</tr>
    	</thead>
    	<tbody>
    	<!--  
    		items:对应的page,request,session,application对象定义的变量名称（集合变量）
    		var:变量名称，对应集合中的当前元素
    	-->
    	 <c:forEach items="${userList}" var="d">
    		<tr>
    			<td align="center">${d.userid }</td>
    			<td align="center">${d.username }</td>
    			<td align="center">${d.depName }</td>
    			<td align="center">${d.jobname }</td>
    			<td align="center">${d.mobile }</td>
    			<td align="center">${d.cardno }</td>
    			<td align="center">${d.qqcode }</td>
    			<td align="center">${d.joinDate }</td>
    			<td align="center">${d.baseSalary }</td>
    			<td align="center">${d.degreeSalary }</td>
    			<td align="center">
    			<c:if test="${d.managerType==0}">系统管理员</c:if>  			
    			<c:if test="${d.managerType==1}">部门经理</c:if>  			
    			<c:if test="${d.managerType==2}">财务</c:if>  			
    			<c:if test="${d.managerType==3}">职员</c:if>  			
    			</td>
    			<td align="center">
    			<c:if test="${d.status==1}">启用</c:if>  			
    			<c:if test="${d.status==0}">禁用</c:if>  			
    			</td>
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
  </body>
  	
</html>
