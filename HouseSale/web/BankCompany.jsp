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
<table id="dg" title="公司账户" style="width:100%;height:100%;" ></table>
<script type="text/javascript">
    // var bankAccount="";
    $(function(){
        $("#dg").datagrid({
            title:'公司账户',//表格名称
            width:'auto',
            height:290,
            border:true,//边框
            url:'BankCompanyAction!listPay.action',
            singleSelect:true,
            columns:[[
                {field:'bankAccount',title:'公司账户',width:300},
                {field:'bankname',title:'所在银行',width:100},
                {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick='del(\""+row.bankAccount+"\");' name='opera'>删除</a>";
                    }}
            ]],
            pagination:true,//如果表格需要支持分页，必须设置该选项为true
            pageSize:5,   //表格中每页显示的行数
            pageList:[5,10,15],
            // rownumbers:true,   //是否显示行号
            sortName: 'bankAccount',  //按照ID列的值排序
            method:'post',   //表格数据获取方式,请求地址是上面定义的url
            loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
            // frozenColumns: [[  //固定在表格左侧的栏
            //     {field: 'ck', checkbox: true},
            // ]],
            onClickRow:function(rowIndex, rowData){
                $("#bankAccount").val(rowData.bankAccount);
                $("#bankname").combobox('select',rowData.bankname);

            },
            onLoadSuccess:function(data){
                $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            }

        });

        $("#add").click(function(){
            var bankAccount=$("#bankAccount").val();
            var bankname=$("#bankname option:selected").val();
            if(bankAccount==null||bankAccount==""){
                alert("请输入公司账户！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'BankCompanyAction!invadadd.action',
                data:{
                    "bankAccount":bankAccount,
                },
                success: function(data){
                    console.log(data);

                    if(data=="1"){
                        alert("对不起该账户已存在！");
                        return false;
                    }else {
                        $.ajax({
                            type:'post',
                            url:'BankCompanyAction!add.action',
                            data:{
                                "bankAccount":bankAccount,
                                "bankname":bankname,
                            },
                            success: function(data){
                                $("#dg").datagrid('reload');
                                $("#bankAccount").numberbox("setValue","");
                            }
                        });
                    }
                }
            });


        });
        $("#save").click(function(){
            var bankAccount=$("#bankAccount").val();
            var bankname=$("#bankname option:selected").val();
            if(bankAccount==null||bankAccount==""){
                alert("请输入公司账户！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'BankCompanyAction!save.action',
                data:{
                    "bankAccount":bankAccount,
                    "bankname":bankname
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#bankAccount").numberbox("setValue","");
                }
            });
        });


    });
    function del(bankAccount){
        $.messager.confirm("提示","你确定要删除选中数据？",function(){
            $.ajax({
                type:'post',
                url:'BankCompanyAction!delete.action',
                data:{

                    "bankAccount":bankAccount,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#bankAccount").numberbox("setValue","");
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
                    <td>公司账户：</td>
                    <td><input type="text" class="easyui-validatebox" id="bankAccount" data-options="validType:'length[1,19]'"></td>
                </tr>
                <tr>
                    <td>所在银行：</td>
                    <td>
                        <%--<s:select  list="#request.bankList"  listKey="bankid" listValue="bankname" style="width:175px;height:40px"></s:select>--%>
                        <select id="bankname" class="easyui-combobox" style="width:175px">
                            <s:iterator value="#request.bankList" var="b">
                                <option value="<s:property value="#b.bankname"/>"><s:property value="#b.bankname"/></option>
                            </s:iterator>
                        </select>

                    </td>
                </tr>
            </table>

        </div>
    </div>
</div>


</body>
</html>
