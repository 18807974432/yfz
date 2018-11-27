<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>付款方式</title>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/demo/demo.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
</head>
<body>
    <table id="dg" title="付款方式" style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function(){
            var paidtypeid=0;
            $("#dg").datagrid({
                title:'付款方式',//表格名称
                width:'auto',
                height:290,
                border:true,//边框
                url:'PaidTypeAction!listPay.action',
                singleSelect:true,
                columns:[[
                    {field:'paidtypeid',title:'编号',width:100},
                    {field:'paidtypename',title:'名称',width:100},
                    {field:'iscash',title:'是否现金',width:100},
                    {field:'invalid',title:'是否有效',width:100},
                    {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                            return "<a href='javascript:void(0);' onclick='del("+row.paidtypeid+")' name='opera'>删除</a>";
                     }}
                ]],
                pagination:true,//如果表格需要支持分页，必须设置该选项为true
                pageSize:5,   //表格中每页显示的行数
                pageList:[5,10,15],
                // rownumbers:true,   //是否显示行号
                sortName: 'paidtypeid',  //按照ID列的值排序
                method:'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                // frozenColumns: [[  //固定在表格左侧的栏
                //     {field: 'ck', checkbox: true},
                // ]],
                onClickRow:function(rowIndex, rowData){
                    paidtypeid=rowData.paidtypeid;
                    $("#paidtypename").val(rowData.paidtypename);
                    $("#iscash").val(rowData.iscash);
                    $("#invalid").val(rowData.invalid);
                },
                onLoadSuccess:function(data){
                    $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
                }
            });

            $("#add").click(function(){
                alert("进来了");
                var paidtypename=$("#paidtypename").val();
                var iscash=$("#iscash").val();
                var invalid=$("#invalid").val();
                alert("进来了"+paidtypename+iscash+invalid);
                if(paidtypename==null||paidtypename==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'PaidTypeAction!add.action',
                    data:{
                        "paidtypename":paidtypename,
                        "iscash":iscash,
                        "invalid":invalid
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidtypename").val('');
                        $("#iscash").val("现金");
                        $("#invalid").val("有效");
                    }
                });
            });

            $("#save").click(function(){
                var paidtypename=$("#paidtypename").val();
                var iscash=$("#iscash").val();
                var invalid=$("#invalid").val();
                if(paidtypename==null||paidtypename==""){
                    alert("请输入付款方式名称！");
                    return null;
                }
                $.ajax({
                    type:'post',
                    url:'PaidTypeAction!save.action',
                    data:{
                        "paidtypeid":paidtypeid,
                        "paidtypename":paidtypename,
                        "iscash":iscash,
                        "invalid":invalid
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidtypename").val('');
                        $("#iscash").val("现金");
                        $("#invalid").val("有效");
                    }
                });
            });

        });

        function del(paidtypeid){
            $.messager.confirm("提示","你确定要删除选中数据？",function(){
                $.ajax({
                    type:'post',
                    url:'PaidTypeAction!delete.action',
                    data:{
                        "paidtypeid":paidtypeid,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#paidtypename").val('');
                        $("#iscash").val("现金");
                        $("#invalid").val("有效");
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
                        <td>付款方式名称：</td>
                        <td><input type="text" id="paidtypename" ></td>
                    </tr>
                    <tr>
                        <td>是否现金：</td>
                        <td>
                            <select id="iscash" style="width:175px;height:20px">
                                <option value="现金">现金</option>
                                <option value="银行">银行</option>
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
