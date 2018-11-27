<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>银行资料</title>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/demo/demo.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>
<body>
    <table id="dg" title="银行资料" style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function(){
            var bankid=0;
            $("#dg").datagrid({
                title:'银行资料',//表格名称
                width:'auto',
                height:290,
                border:true,//边框
                url:'BankAction!listPay.action',
                singleSelect:true,
                columns:[[
                    {field:'bankid',title:'银行编号',width:100},
                    {field:'bankname',title:'银行名称',width:100},
                    {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                            return "<a href='javascript:void(0);' onclick='del("+row.bankid+")' name='opera'>删除</a>";
                     }}
                ]],
                pagination:true,//如果表格需要支持分页，必须设置该选项为true
                pageSize:5,   //表格中每页显示的行数
                pageList:[5,10,15],
                // rownumbers:true,   //是否显示行号
                sortName: 'bankid',  //按照ID列的值排序
                method:'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                // frozenColumns: [[  //固定在表格左侧的栏
                //     {field: 'ck', checkbox: true},
                // ]],
                onClickRow:function(rowIndex, rowData){
                    bankid=rowData.bankid;
                    $("#bankname").val(rowData.bankname);
                },
                onLoadSuccess:function(data){
                    $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
                }
            });

            $("#add").click(function(){
                var bankname=$("#bankname").val();
                if(bankname==null||bankname==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'BankAction!add.action',
                    data:{
                        "bankname":bankname,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#bankname").val('');
                    }
                });
            });

            $("#save").click(function(){
                var bankname=$("#bankname").val();
                if(bankname==null||bankname==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'BankAction!save.action',
                    data:{
                        "bankid":bankid,
                        "bankname":bankname,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#bankname").val('');
                    }
                });
            });

        });

        function del(bankid){
            $.messager.confirm("提示","你确定要删除选中数据？",function(){
                $.ajax({
                    type:'post',
                    url:'BankAction!delete.action',
                    data:{
                        "bankid":bankid,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#bankname").val('');
                    }
                });
            });
        }
    </script>

    <div class="easyui-tabs" title="付款方式快捷操作" style="width:auto;height:auto">
        <div title="付款方式快捷操作" style="width:auto;height: auto;">
            <div style="background: #99cdff;width:auto;">
                <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            </div>
            <div>
                <table align="center">
                    <tr>
                        <td>银行名称：</td>
                        <td><input type="text" id="bankname" ></td>
                    </tr>
                </table>

            </div>
        </div>
    </div>


</body>
</html>
