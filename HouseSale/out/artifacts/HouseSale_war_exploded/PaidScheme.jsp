<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收款方式</title>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/demo/demo.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>
<body>
<table id="dg" title="收款方式" style="width:100%;height:100%;" ></table>
<script type="text/javascript">
    $(function(){
        var psid=0;

        $("#dg").datagrid({
            title:'收款方式',//表格名称
            width:'auto',
            height:290,
            border:true,//边框
            url:'PaidSchemeAction!listPay.action',
            singleSelect:true,
            columns:[[
                {field:'psid',title:'编号',width:100},
                {field:'psname',title:'付款方案名称',width:100},
                {field:'projectid',title:'项目名称',width:100},
                {field:'isBank',title:'付款方式',width:100},
                {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick='del("+row.psid+")' name='opera'>删除</a>";
                    }}
            ]],
            pagination:true,//如果表格需要支持分页，必须设置该选项为true
            pageSize:5,   //表格中每页显示的行数
            pageList:[5,10,15],
            // rownumbers:true,   //是否显示行号
            sortName: 'psid',  //按照ID列的值排序
            method:'post',   //表格数据获取方式,请求地址是上面定义的url
            loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
            // frozenColumns: [[  //固定在表格左侧的栏
            //     {field: 'ck', checkbox: true},
            // ]],
            onClickRow:function(rowIndex, rowData){
                psid=rowData.psid;
                alert(psid);
                $("#psname").val(rowData.psname);
                $("#projectid").val(rowData.projectid);
                $("#isBank").val(rowData.isBank);
                $("#pstime").val(rowData.pstime);
                $("#userid").val(rowData.userid);
            },
            onLoadSuccess:function(data){
                $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            }

        });

        $("#add").click(function(){
            var psname=$("#psname").val();
            var projectid=$("#projectid").val();
            var isBank=$("#isBank").val();
            if(psname==null||psname==""){
                alert("请输入付款方式名称！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'PaidSchemeAction!add.action',
                data:{
                    "psname":psname,
                    "projectid":projectid,
                    "isBank":isBank
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#psname").val('');
                    $("#projectid").val(5);
                    $("#isBank").val("现金");
                }
            });
        });

        $("#save").click(function(){
            alert(psid);
            var psname=$("#psname").val();
            var projectid=$("#projectid").val();
            var isBank=$("#isBank").val();
            if(psname==null||psname==""){
                alert("请输入付款方式名称！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'PaidSchemeAction!save.action',
                data:{
                    "psid":psid,
                    "psname":psname,
                    "projectid":projectid,
                    "isBank":isBank,
                    "pstime":$("#pstime").val(),
                    "userid":$("#userid").val(),
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#psname").val('');
                    $("#projectid").val(5);
                    $("#isBank").val("现金");
                }
            });
        });
    });
    function del(psid){
        $.messager.confirm("提示","你确定要删除选中数据？",function(){
            $.ajax({
                type:'post',
                url:'PaidSchemeAction!delete.action',
                data:{
                    "psid":psid,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#psname").val('');
                    $("#projectid").val(5);
                    $("#isBank").val("现金");
                }
            });
        });
    }
</script>

<div class="easyui-tabs" title="收款方式快捷操作" style="width:auto;height:auto">
    <div title="收款方式快捷操作" style="width:auto;height: auto;">
        <div style="background: #99cdff;width:auto;">
            <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        </div>
        <div>
            <table align="center">
                <tr>
                    <td>付款方案名称：</td>
                    <td><input type="text" id="psname" ></td>
                </tr>
                <tr>
                    <td>项目名称：</td>
                    <td>
                        <select id="projectid" style="width:175px;height:20px">
                            <s:iterator value="#request.projectInfoList" var="p">
                                <option value="<s:property value="#p.projectName"/>"><s:property value="#p.projectName"/></option>
                            </s:iterator>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>付款方式：</td>
                    <td>
                        <select id="isBank" style="width:175px">
                            <option value="现金">现金</option>
                            <option value="银行">银行</option>
                        </select>
                    </td>
                </tr>
                <s:hidden value="" id="pstime" />
                <s:hidden value="" id="userid"/>
            </table>

        </div>
    </div>
</div>


</body>
</html>
