<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/24
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>项目资料</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/demo/demo.css">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div style="background: #E0ECFF;padding: 0px;font-size:15px">&nbsp;&nbsp;&nbsp;&nbsp;
    小区名称：<input type="text" id="find_gardenName">
    <a href="javascript:;" id="find" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">搜索</a>
</div>
<table id="dg" title="项目名称" style="width:100%;height:100%;"></table>
<script type="text/javascript">
    $(function(){
        var projectId=0;
        $("#dg").datagrid({
            title:'项目资料',
            width:'auto',
            height:290,
            border:true,//边框
            url:'ProjectInfoAction!list.action',
            singleSelect:true,
            columns:[[
                {field:'projectId',title:'项目编号',width:100},
                {field:'projectName',title:'项目名称',width:100},
                {field:'gardenName',title:'小区名称',width:100},
                {field:'gardenCode',title:'小区代码',width:100},
                {field:'opr',title:'操作',width:200,formatter:function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick='del("+row.projectId+")' name='opera'>删除</a>"+
                        "<a href='upload.jsp' onclick='uploads("+row.projectId+")' name='one'>上传客户资料</a>";
                }}
            ]],
            pagination:true,//如果表格需要支持分页，必须设置该选项为true
            pageSize:5,   //表格中每页显示的行数
            pageList:[5,10,15],
            // rownumbers:true,   //是否显示行号
            sortName: 'projectId',  //按照ID列的值排序
            method:'get',   //表格数据获取方式,请求地址是上面定义的url
            loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
            // frozenColumns: [[  //固定在表格左侧的栏
            //     {field: 'ck', checkbox: true},
            // ]],
            onClickRow:function(rowIndex, rowData){
                projectId=rowData.projectId;
                $("#projectName").val(rowData.projectName);
                $("#gardenName").val(rowData.gardenName);
                $("#gardenCode").val(rowData.gardenCode);
                $("#buildArea").val(rowData.buildArea);
                $("#useArea").val(rowData.useArea);
                $("#viewArea").val(rowData.viewArea);
                $("#startTime").val(rowData.startTime);
                $("#endTime").val(rowData.endTime);
                $("#location").val(rowData.location);
                $("#hourseCount").val(rowData.hourseCount);
                $("#salePrice").val(rowData.salePrice);
                $("#orderid").val(rowData.orderid);
                $("#isPaid").val(rowData.isPaid);
                $("#description").val(rowData.description);
            },
            onLoadSuccess:function(data){
                $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
                $("a[name='one']").linkbutton({text:'上传客户资料',plain:true,iconCls:'icon-edit'})
            }
        });
        $("#add").click(function(){
            var projectName=$("#projectName").val();
            var gardenName=$("#gardenName").val();
            var gardenCode=$("#gardenCode").val();
            var buildArea=$("#buildArea").val();
            var useArea=$("#useArea").val();
            var viewArea=$("#viewArea").val();
            var startTime=$("#startTime").val();
            var endTime=$("#endTime").val() ;
            var location=$("#location").val();
            var hourseCount=$("#hourseCount").val();
            var salePrice=$("#salePrice").val();
            var orderid=$("#orderid").val();
            var isPaid=$("#isPaid").val();
            var description=$("#description").val();
            console.log(projectName);
            console.log(gardenName);
            console.log(gardenCode);
            console.log(buildArea);
            console.log(useArea);
            console.log(viewArea);
            console.log(startTime);
            console.log(endTime);
            console.log(location);
            console.log(hourseCount);
            console.log(salePrice);
            console.log(orderid);
            console.log(isPaid);
            console.log(description);
            if(projectName==null||projectName==""){
                alert("请输入项目名称！");
                return null;
            }
            if(gardenName==null||gardenName==""){
                alert("请输入小区名称！");
                return null;
            }
            if(gardenCode==null||gardenCode==""){
                alert("请输入小区代码");
                return null;
            }
            $.ajax({
                type:'POST',
                url:'ProjectInfoAction!add.action',
                data:{
                    "projectName":projectName,
                    "gardenName":gardenName,
                    "gardenCode":gardenCode,
                    "buildArea":buildArea,
                    "useArea":useArea,
                    "viewArea":viewArea,
                    "startTime":startTime,
                    "endTime":endTime,
                    "location":location,
                    "hourseCount":hourseCount,
                    "salePrice":salePrice,
                    "orderid":orderid,
                    "isPaid":isPaid,
                    "description":description
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#projectName").val('');
                    $("#gardenName").val('');
                    $("#gardenCode").val('');
                    $("#buildArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#location").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#orderid").val('');
                    $("#isPaid").val('');
                    $("#description").val('');
                }
            });
        });
        $("#save").click(function(){
            var projectName=$("#projectName").val();
            var gardenName=$("#gardenName").val();
            var gardenCode=$("#gardenCode").val();
            var buildArea=$("#buildArea").val();
            var useArea=$("#useArea").val();
            var viewArea=$("#viewArea").val();
            var startTime=$("#startTime").val();
            var endTime=$("#endTime").val();
            var location=$("#location").val();
            var hourseCount=$("#hourseCount").val();
            var salePrice=$("#salePrice").val();
            var orderid=$("#orderid").val();
            var isPaid=$("#isPaid").val();
            var description=$("#description").val();

            if(projectName==null||projectName==""){
                alert("请输入项目名称！");
                return null;
            }
            if(gardenName==null||gardenName==""){
                alert("请输入小区名称！");
                return null;
            }
            if(gardenCode==null||gardenCode==""){
                alert("请输入小区代码");
                return null;
            }
            alert("OK");
            $.ajax({
                type:'post',
                url:'ProjectInfoAction!save.action',
                data:{
                    "projectId":projectId,
                    "projectName":projectName,
                    "gardenName":gardenName,
                    "gardenCode":gardenCode,
                    "buildArea":buildArea,
                    "useArea":useArea,
                    "viewArea":viewArea,
                    "startTime":startTime,
                    "endTime":endTime,
                    "location":location,
                    "hourseCount":hourseCount,
                    "salePrice":salePrice,
                    "orderid":orderid,
                    "isPaid":isPaid,
                    "description":description,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#projectName").val('');
                    $("#gardenName").val('');
                    $("#gardenCode").val('');
                    $("#buildArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#location").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#orderid").val('');
                    $("#isPaid").val('');
                    $("#description").val('');

                    console.log(projectName);
                    console.log(gardenName);
                    console.log(gardenCode);
                    console.log(buildArea);
                    console.log(useArea);
                    console.log(viewArea);
                    console.log(startTime);
                    console.log(endTime);
                    console.log(location);
                    console.log(hourseCount);
                    console.log(salePrice);
                    console.log(orderid);
                    console.log(isPaid);
                    console.log(description);
                }
            });
        });
        $("#find").click(function(){
            var find_gardenName=$("#find_gardenName").val();
            $.ajax({
                type:'post',
                url:'ProjectInfoAction!findlist.action',
                data:{
                    "find_gardenName":find_gardenName
                },
                dataType:'json',
                success:function(data){
                    $("#dg").datagrid('loadData',data);
                }
            });
        })
    });
    function del(projectId){
        $.messager.confirm("提示","你确定要删除选中数据？",function(){
            $.ajax({
                type:'post',
                url:'ProjectInfoAction!delete.action',
                data:{
                    "projectId":projectId,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#projectName").val('');
                    $("#gardenName").val('');
                    $("#gardenCode").val('');
                    $("#buildArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#location").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#orderid").val('');
                    $("#isPaid").val('');
                    $("#description").val('');
                }
            });
        });
    }
</script>
<div class="easyui-tabs" title="项目资料" style="width:auto;height:auto ">
    <div title="项目资料" style="width:auto;height:auto">
        <div style="background: #99cdff;width:auto;">
            <a id="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a id="save" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        </div>
        <div>
            <table>
                <tr>
                    <td>项目名称:</td>
                    <td>
                        <input type="text" id="projectName" >
                        <font size="2" color="red">*</font>
                    </td>
                    <td>小区名称:</td>
                    <td>
                        <input type="text" id="gardenName">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>小区代码:</td>
                    <td>
                        <input type="text" id="gardenCode">
                        <font size="2" color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td>建筑面积:</td>
                    <td><input type="text" id="buildArea"></td>
                    <td>使用面积:</td>
                    <td><input type="text" id="useArea"></td>
                    <td>绿化面积:</td>
                    <td><input type="text" id="viewArea"></td>
                </tr>
                <tr>
                    <td>开工时间:</td>
                    <td><input type="text" id="startTime" class="easyui-datetimebox" required="required"></td>
                    <td>竣工时间:</td>
                    <td><input type="text" id="endTime" class="easyui-datetimebox" required="required"></td>
                    <td>是否可售:</td>
                    <td>
                        <select id="isPaid">
                            <option value="是">是</option>
                            <option value="否">否</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>房产总数:</td>
                    <td><input  type="text" id="hourseCount"></td>
                    <td>期望售价:</td>
                    <td><input  type="text" id="salePrice"></td>
                    <td>排序序号:</td>
                    <td><input  type="text" id="orderid"></td>
                </tr>
                <tr>
                    <td align="right">位置:</td>
                    <td><input type="text" id="location"></td>
                    <td align="right">描述:</td>
                    <td><input type="text" id="description" size="50"></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
