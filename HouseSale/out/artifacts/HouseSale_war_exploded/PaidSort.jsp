<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>收款类别</title>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/demo/demo.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>
<body>
    <table id="dg" title="收款类别" style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function(){
            var paidsortid=0;

            $("#dg").datagrid({
                title:'收款类别',//表格名称
                width:'auto',
                height:290,
                border:true,//边框
                url:'PaidSortAction!listPay.action',
                singleSelect:true,
                columns:[[
                    {field:'paidsortid',title:'编号',width:100},
                    {field:'paidsortname',title:'名称',width:100},
                    {field:'paidtype',title:'收付款类别',width:100},
                    {field:'invalid',title:'是否有效',width:100},
                    {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                            return "<a href='javascript:void(0);' onclick='del("+row.paidsortid+")' name='opera'>删除</a>";
                        }}
                ]],
                pagination:true,//如果表格需要支持分页，必须设置该选项为true
                pageSize:5,   //表格中每页显示的行数
                pageList:[5,10,15],
                // rownumbers:true,   //是否显示行号
                sortName: 'paidsortid',  //按照ID列的值排序
                method:'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                // frozenColumns: [[  //固定在表格左侧的栏
                //     {field: 'ck', checkbox: true},
                // ]],
                onClickRow:function(rowIndex, rowData){
                    paidsortid=rowData.paidsortid;
                    alert(paidsortid);
                    $("#paidsortname").val(rowData.paidsortname);
                    $("#paidtype").val(rowData.paidtype);
                    $("#invalid").val(rowData.invalid);
                },
                onLoadSuccess:function(data){
                    $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
                }

            });

            $("#add").click(function(){
                var paidsortname=$("#paidsortname").val();
                var paidtype=$("#paidtype").val();
                var invalid=$("#invalid").val();
                if(paidsortname==null||paidsortname==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'PaidSortAction!add.action',
                    data:{
                        "paidsortname":paidsortname,
                        "paidtype":paidtype,
                        "invalid":invalid
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidsortname").val('');
                        $("#paidtype").val("收款");
                        $("#invalid").val("有效");
                    }
                });
            });

            $("#save").click(function(){
                alert(paidsortid);
                var paidsortname=$("#paidsortname").val();
                var paidtype=$("#paidtype").val();
                var invalid=$("#invalid").val();
                if(paidsortname==null||paidsortname==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'PaidSortAction!save.action',
                    data:{
                        "paidsortid":paidsortid,
                        "paidsortname":paidsortname,
                        "paidtype":paidtype,
                        "invalid":invalid
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidsortname").val('');
                        $("#paidtype").val("收款");
                        $("#invalid").val("有效");
                    }
                });
            });
        });
        function del(paidsortid){
            $.messager.confirm("提示","你确定要删除选中数据？",function(){
                $.ajax({
                    type:'post',
                    url:'PaidSortAction!delete.action',
                    data:{
                        "paidsortid":paidsortid,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidsortname").val('');
                        $("#paidtype").val("收款");
                        $("#invalid").val("有效");
                    }
                });
            });
        }
    </script>

    <div class="easyui-tabs" title="付款类别资料" style="width:auto;height:auto">
        <div title="付款类别资料" style="width:auto;height: auto;">
            <div style="background: #99cdff;width:auto;">
                <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            </div>
            <div>
                <table align="center">
                    <tr>
                        <td>收付款类别名称：</td>
                        <td><input type="text" id="paidsortname" ></td>
                    </tr>
                    <tr>
                        <td>收付款名称：</td>
                        <td>
                            <select id="paidtype" style="width:175px;height:20px">
                                <option value="收款">收款</option>
                                <option value="付款">付款</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>是否有效：</td>
                        <td>
                            <select id="invalid" style="width:175px">
                                <option value="有效">有效</option>
                                <option value="无效">无效</option>
                            </select>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </div>

</body>
</html>
