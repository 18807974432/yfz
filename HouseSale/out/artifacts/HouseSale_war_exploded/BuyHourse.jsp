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
    <%--搜索条件--%>
    <div style="background: #E0ECFF;padding: 0px;font-size:15px">&nbsp;&nbsp;&nbsp;&nbsp;
        购买类别：<select id="oprType">
                    <option value="----">----</option>
                    <option value="买房">买房</option>
                    <option value="换房">换房</option>
                    <option value="退房">退房</option>
                </select>
        状态：<select id="invalid">
                <option value="----">----</option>
                <option value="有效">有效</option>
                <option value="作废">作废</option>
            </select>
        业务员:<input id="username" type="text" disabled="true" style="width:100px"  >
        <a href="javascript:;"><img id="tan" src="jquery-easyui-1.6.6/themes/icons/tip.png"></a>
        客户名称：<input type="text" id="custname">
        房产名称：<input type="text" id="hoursename" >
        <a href="javascript:;" id="find" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">搜索</a>
        <a href="javascript:;" id="clear" class="easyui-linkbutton" >清空条件</a>
    </div>

    <table id="dg"  style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function() {
            $("#dg").datagrid({
                width: 'auto',
                height: 300,
                border: true,//边框
                url: 'BuyHourseAction!list.action',
                singleSelect: true,
                columns: [[
                    {field:'buyId',title:'编号'},
                    {field:'custname',title:'客户名称'},
                    {field:'hoursename',title:'房产名称'},
                    {field:'userid',title:'业务员'},
                    {field:'psname',title:'付款方案'},
                    {field:'unitPrice',title:'成交单价'},
                    {field:'fitmentunitprice',title:'装修单价'},
                    {field:'signunitprice',title:'签约单价'},
                    {field:'totalMoney',title:'购房总款'},
                    {field:'fitmenttotalprice',title:'装修总款'},
                    {field:'signtotalprice',title:'签约总款'},
                    {field:'bankid',title:'按揭银行'},
                    {field:'firstMoney',title:'首期款'},
                    {field:'bankmoney',title:'按揭金额'},
                    {field:'fitmentFirstPrice',title:'装修首期款'},
                    {field:'fitmentLastPrice',title:'装修尾款'},
                    {field:'buyTime',title:'订/退时间',formatter :formatDatebox},
                    {field:'commisionPercent',title:'佣金比例'},
                    {field:'commisionMoney',title:'应付佣金'},
                    {field:'commisionpaid',title:'已付佣金'},
                    {field:'oprType',title:'购买类别'},
                    {field:'invalid',title:'状态'},
                    {
                        field: 'opr', title: '操作',width:130, formatter: function (value, row, index) {
                            return "<a href='javascript:void(0);' onclick='del(\"" + row.buyId + "\",\""+row.custname+"\",\""+row.hoursename+"\",\""+row.userid+"\")' name='opera'>缴诚意金</a>";
                        }
                    }
                ]],
                pagination: true,//如果表格需要支持分页，必须设置该选项为true
                pageSize: 5,   //表格中每页显示的行数
                pageList: [5, 10, 15],
                method: 'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg: '数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                onClickRow: function (rowIndex, rowData) {
                    console.log(rowData.buyId);
                    $.ajax({
                        type:'post',
                        url:'EarnestMoneyAction!list.action',
                        data:{
                            'buyId':rowData.buyId
                        },
                        dataType:'json',
                        success:function (data) {
                            $("#dg2").datagrid('loadData',data);
                        }
                    });
                },
                onLoadSuccess: function (data) {
                    $('.datagrid-cell').css('font-size', '9px');
                    $('.datagrid-header .datagrid-cell span ').css('font-size', '9px');
                    $('.datagrid-row').css('height', '10px');
                    $("a[name='opera']").linkbutton({text: '缴诚意金', plain: true, iconCls: 'icon-edit'});
                }
            });

            $("#dg2").datagrid({
                width: 'auto',
                height: 300,
                border: true,//边框
                url: 'EarnestMoneyAction!list.action',
                singleSelect: true,
                columns: [[
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

                ]],
                pagination: true,//如果表格需要支持分页，必须设置该选项为true
                pageSize: 5,   //表格中每页显示的行数
                pageList: [5, 10, 15],
                method: 'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg: '数据正在努力加载，请稍后...',   //加载数据时显示提示信息

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

            //弹出操作人员列表
            $("#tan").click(function(){
                $("#dia").window('open');
            });
            //加载操作人员
            $('#tt').tree({
                url: 'TicketAction!listDep.action',
                // url: 'text.json',
                loadFilter: function(rows){
                    return convert(rows);
                }
            });
            //点击树状列表加载人员
            $('#tt').click(function(){
                var id=$("#tt").tree('getSelected').id;
                var text=$("#tt").tree('getSelected').text;
                $.ajax({
                    type:'post',
                    url:'TicketAction!findUsers.action',
                    data:{
                        'depid':id
                    },
                    dataType:'json',
                    success: function(data){
                        var str="<tr><td width='150px'>人员名称</td><td width='120px'>职位</td></tr>";
                        if(data!=null){
                            for(var i=0;i<data.length;i++){
                                //访问每一个的属性，根据属性拿到值
                                console.log("userid:"+data[i].userid+"jobname:"+data[i].jobname);
                                str+="<tr><td onclick='check(\""+data[i].username+"\")'>"+data[i].username+"</td><td>"+data[i].jobname+"</td></tr>";
                                $("#xianshi").html(str);
                            }
                        }
                        if(data==null||data==""){
                            $("#xianshi").html(str);
                            $.messager.show({
                                title:'提示',
                                msg:'该部门没人',
                                timeout:1000,
                                showType:'slide'
                            });
                        }
                    }
                });
            });

            //条件查询
            $("#find").click(function(){
                console.log($("#oprType").val());
                console.log($("#invalid").val());
                console.log($("#username").val());
                console.log($("#custname").val());
                console.log($("#hoursename").val());
                $.ajax({
                    type:'post',
                    url:'BuyHourseAction!findlist.action',
                    data:{
                        'oprType':$("#oprType").val(),
                        'invalid':$("#invalid").val(),
                        'username':$("#username").val(),
                        'custname':$("#custname").val(),
                        'hoursename':$("#hoursename").val(),
                    },
                    dataType:'json',
                    success:function(data){
                        $("#dg").datagrid('loadData',data);
                    }
                });
            });

            //清空查询条件
            $("#clear").click(function(){
                $("#oprType").val("----");
                $("#invalid").val("----");
                $("#username").val("");
                $("#custname").val("");
                $("#hoursename").val("");
            });

        });

        //处理tree json格式
        function convert(rows){
            function exists(rows, parentId){
                for(var i=0; i<rows.length; i++){
                    if (rows[i].id == parentId) return true;
                }
                return false;
            }

            var nodes = [];
            // get the top level nodes
            for(var i=0; i<rows.length; i++){
                var row = rows[i];
                if (!exists(rows, row.parentId)){
                    nodes.push({
                        id:row.id,
                        text:row.name
                    });
                }
            }

            var toDo = [];
            for(var i=0; i<nodes.length; i++){
                toDo.push(nodes[i]);
            }
            while(toDo.length){
                var node = toDo.shift();	// the parent node
                // get the children nodes
                for(var i=0; i<rows.length; i++){
                    var row = rows[i];
                    if (row.parentId == node.id){
                        var child = {id:row.id,text:row.name};
                        if (node.children){
                            node.children.push(child);
                        } else {
                            node.children = [child];
                        }
                        toDo.push(child);
                    }
                }
            }
            return nodes;
        }

        function check(data){
            $("#username").val(data);
            $("#dia").window('close',true);
        }

        //交诚意金
        function del(buyId,custname,hoursename,userid){
            console.log("叫诚意金"+userid);
            location.href="EarnestMoneyAction!set.action?buyId="+buyId+"&custname="+custname+"&hoursename="+hoursename+"&userid="+userid;
        }

    </script>

    <div class="easyui-tabs" title="诚意金明细" style="width:auto;height:auto">
        <div title="诚意金明细" style="width:auto;height: auto;">
            <table id="dg2" style="width:100%;"></table>

        </div>
    </div>

    <div id="dia" class="easyui-window" title="人员选择" data-options="maximizable: false,
    collapsible: false,minimizable:false,closed:true,modal:true,draggable:false" style="width:600px;height:400px;padding:0px;">
        <div class="easyui-layout" data-options="fit:true">
            <div data-options="region:'east',split:true,border:false" style="width:300px">
                <span>温馨提示：请单击人员名称选择</span>
                <table id="xianshi" width="270px" ></table>
            </div>
            <div data-options="region:'center',border:false" style="padding:0px;">
                <div class="easyui-panel" data-options="border:false" style="padding:5px">
                    <ul id="tt" class="easyui-tree"></ul>
                </div>
            </div>
        </div>
    </div>

</body>
</html>
