<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>诚意金管理</title>
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="./jquery-easyui-1.6.6/demo/demo.css">

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
    <table id="dg"  style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function() {
            $("#dg").datagrid({
                title:'诚意金管理',
                width: 'auto',
                height: 300,
                border: true,//边框
                url: 'EarnestMoneyAction!list2.action',
                singleSelect: true,
                columns: [[
                    {field:'custname',title:'客户名称'},
                    {field:'hourseName',title:'房产名称'},
                    {field:'ticketNo',title:'票据本'},
                    {field:'ticketFlow',title:'票据本编号'},
                    {field:'paidtypeid',title:'收款方式'},
                    {field:'paidMoney',title:'诚意金'},
                    {field:'paidTime',title:'付款时间',formatter :formatDatebox},
                    {field:'isTranslate',title:'是否转收付款'},
                    {field:'transMoney',title:'转收付款金额'},
                    {field:'status',title:'状态'},
                    {field:'userid',title:'操作人'},
                    {field:'oprtime',title:'操作时间',formatter :formatDatebox},
                    {field:'invalid',title:'是否有效'},
                    {
                        field: 'opr', title: '操作',width:'190', formatter: function (value, row, index) {
                            var s="";
                            var s2="";
                            if(row.status=='转签约'){
                                s="<a href='javascript:void(0);' name='opera'>已转签约</a>";
                            }else {
                                s="<a href='javascript:void(0);' onclick='turn("+row.earnestId+")' name='opera'>转签约</a>";
                            }
                            if(row.invalid=='有效'){
                                s2="<a href='javascript:void(0);' onclick='del(" + row.earnestId + ")' name='opera'>作废</a>";
                            }else{
                                s2="<a href='javascript:void(0);'  name='opera'>已作废</a>"
                            }
                            return s+s2;
                        }
                    }
                ]],
                pagination: true,//如果表格需要支持分页，必须设置该选项为true
                pageSize: 5,   //表格中每页显示的行数
                pageList: [5, 10, 15],
                method: 'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg: '数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                onClickRow: function (rowIndex, rowData) {

                },
                onLoadSuccess: function (data) {
                    $("a[name='opera']").linkbutton({ plain: true, iconCls: 'icon-edit'});
                }
            });

            $("#save").click(function(){
                var ticket=$("#ticketList").val();
                var ticketNo=$("#ticketNo").val();
                var paidType=$("#paidTypeList").find("option:selected").text();
                var paidMoney=$("#paidMoney").val();
                var paidTime=$("#paidTime").val();
                console.log(ticket);
                console.log(ticketNo);
                if(ticketNo==null||ticketNo==''){
                    alert("请填写票据本编号");
                    return ;
                }
                if(paidMoney==null||paidMoney==''){
                    alert("请填写诚意金");
                    return;
                }
                if(paidTime==null||paidTime==''){
                    alert("请选择付款时间");
                    return;
                }
                $.ajax({
                    type:'post',
                    url:'EarnestMoneyAction!add.action',
                    data:{
                        'ticket':ticket,
                        'ticketNo':ticketNo,
                        'paidType':paidType,
                        'paidMoney':paidMoney,
                        'paidTime':paidTime
                    },
                    success:function(data){
                        $("#dg").datagrid('reload');

                    }
                });
            });

        });

        //处理日期显示问题
        Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1, // month
                "d+": this.getDate(), // day
                "h+": this.getHours(), // hour
                "m+": this.getMinutes(), // minute
                "s+": this.getSeconds(), // second
                "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
                "S": this.getMilliseconds()
                // millisecond
            }
            if (/(y+)/.test(format))
                format = format.replace(RegExp.$1, (this.getFullYear() + "")
                    .substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(format))
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
            return format;
        }
        function formatDatebox(value) {
            if (value == null || value == '') {
                return '';
            }
            var dt;
            if (value instanceof Date) {
                dt = value;
            } else {
                dt = new Date(value);
            }
            return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
        }

        //转签约
        function turn(earnestId){
            $.ajax({
                type:'post',
                url:'EarnestMoneyAction!turn.action',
                data:{
                    'earnestId':earnestId,
                },
                success:function(data){
                    $("#dg").datagrid('reload');
                }
            });
        }
        //作废
        function del(earnestId){
            $.ajax({
                type:'post',
                url:'EarnestMoneyAction!del.action',
                data:{
                    'earnestId':earnestId,
                },
                success:function(data){
                    $("#dg").datagrid('reload');
                }
            });
        }

    </script>
    <div class="easyui-tabs" title="诚意金管理" style="width:auto;height:auto">
        <div title="诚意金管理" style="width:auto;height: auto;">
            <div style="background: #99cdff;width:auto;">
                <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            </div>
            <div>
                <s:form id="form1" action="EarnestMoneyAction!add.action" method="POST">
                    <table align="center" style="width: 100%" >
                        <tr>
                            <td align="right" style="width:105px">意向客户:</td>
                            <td align="left" style="width:50px"><s:property  value="custname"></s:property> </td>
                            <td align="right"  style="width:105px">意向房号:</td>
                            <td align="left" style="width:80px"><font size="2px"><s:property value="housename"></s:property></font></td>
                            <td align="right"  style="width:105px">票据本:</td>
                            <td align="left" style="width:70px">
                                <s:select id="ticketList" list="ticketList" listKey="ticketid" listValue="ticketNo" cssStyle="width: 100px"></s:select>
                            </td>
                            <td align="right" style="width:120px">票据本编号:</td>
                            <td align="left" style="width:90px">
                                <s:textfield id="ticketNo"  ></s:textfield>
                            </td>
                        </tr>
                        <tr>
                            <td align="right">收款方式:</td>
                            <td align="left" style="width:50px">
                                <s:select id="paidTypeList" list="paidTypeList" listKey="paidtypeid" listValue="paidtypename" ></s:select>
                            </td>
                            <td align="right">诚意金:</td>
                            <td align="left"><input type="text" id="paidMoney"  required="true"></td>
                            <td align="right">付款时间:</td>
                            <td align="left"><input type="text" id="paidTime" class="easyui-datetimebox" required="required"></td>
                            <td align="right">转收付金额:</td>
                            <td align="left"><input type="text" disabled="true"></td>
                        </tr>
                    </table>
                </s:form>
            </div>
        </div>
    </div>

</body>
</html>
