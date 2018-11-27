<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>票据管理</title>
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
        票据本号：<input type="text" id="find_ticketNo">
        领用人：<input type="text" id="find_usePerson">
        是否有效：<select id="find_invalid">
                    <option>----</option>
                    <option>有效</option>
                    <option>无效</option>
                </select>
        是否审核：<select id="find_isAudit">
                    <option>----</option>
                    <option>已审核</option>
                    <option>未审核</option>
                </select>
        是否核销：<select id="find_isEnd">
                    <option>----</option>
                    <option>已核销</option>
                    <option>未核销</option>
                </select>
        <a href="javascript:;" id="find" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">搜索</a>
    </div>

    <table id="dg"  style="width:100%;height:100%;" ></table>
    <script type="text/javascript">
        $(function(){
            var ticketid=0;
            $("#dg").datagrid({
                width:'auto',
                height:250,
                border:true,//边框
                url:'TicketAction!list.action',
                singleSelect:true,
                columns:[[
                    {field:'ticketid',title:'序号',width:50},
                    {field:'ticketNo',title:'票据本号',width:70},
                    {field:'ticketType',title:'票据类型',width:65},
                    {field:'usePerson',title:'领用人',width:70},
                    {field:'useTime',title:'领用时间',width:150,
                            formatter :formatDatebox
                    },
                    {field:'startNo',title:'票据号起',width:65},
                    {field:'endNo',title:'票据号止',width:65},
                    {field:'prefix',title:'票据号前缀',width:100},
                    {field:'invalid',title:'是否有效',width:65,
                            formatter:function(val,row,index){
                                if(val=='有效'){
                                    return "<span style=color:red; >有效</span>";
                                }else {
                                    return "<span>无效</span>";
                                }
                            }
                    },
                    {field:'buyTime',title:'购买日期',width:150,
                        formatter :formatDatebox
                    },
                    {field:'isAudit',title:'是否审核',width:65},
                    {field:'isEnd',title:'是否核销',width:65},
                    {field:'opr',title:'操作',width:80,formatter:function (value, row, index) {
                            return "<a href='javascript:void(0);' onclick='del("+row.ticketid+")' name='opera'>删除</a>";
                        }}
                ]],
                pagination:true,//如果表格需要支持分页，必须设置该选项为true
                pageSize:5,   //表格中每页显示的行数
                pageList:[5,10,15],
                // rownumbers:true,   //是否显示行号
                // sortName: 'ticketid',  //按照ID列的值排序
                method:'post',   //表格数据获取方式,请求地址是上面定义的url
                loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
                // frozenColumns: [[  //固定在表格左侧的栏
                //     {field: 'ck', checkbox: true},
                // ]],
                onClickRow:function(rowIndex, rowData){
                    ticketid=rowData.ticketid;
                    $("#ticketNo").val(rowData.ticketNo);
                    $("#username").val(rowData.usePerson);
                    console.log(rowData.useTime);
                    var usertime=formatDatebox(rowData.useTime);
                    $("#useTime").datetimebox('setValue', usertime);
                    $("#ticketType").val(rowData.ticketType);
                    $("#prefix").val(rowData.prefix);
                    $("#startNo").val(rowData.startNo);
                    $("#endNo").val(rowData.endNo);
                    $("#invalid").val(rowData.invalid);
                    var buytime=formatDatebox(rowData.buyTime);
                    $("#buyTime").datetimebox('setValue', buytime);
                    $("#isAudit").val(rowData.isAudit);
                    $("#isEnd").val(rowData.isEnd);
                    $("#demo").val(rowData.demo);
                },
                onLoadSuccess:function(data){
                    $('.datagrid-cell').css('font-size','9px');

                    $('.datagrid-header .datagrid-cell span ').css('font-size','9px');
                    $('.datagrid-row').css('height','10px');

                    $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
                }

            });

            $("#add").click(function(){
                var ticketNo=$("#ticketNo").val();
                var username=$("#username").val();
                var useTime =$("#useTime").val();
                var ticketType=$("#ticketType").val();
                var prefix=$("#prefix").val();
                var startNo=$("#startNo").val();
                var endNo=$("#endNo").val();
                var invalid=$("#invalid").val();
                var buyTime=$("#buyTime").val();
                var isAudit=$("#isAudit").val();
                var isEnd=$("#isEnd").val();
                var demo=$("#demo").val();
                if(ticketNo==null||ticketNo==''){
                    alert("请输入票据本号");
                    return;
                }
                if(username==null||username==''){
                    alert("请选择领用人");
                    return;
                }
                if(useTime==null||useTime==''){alert("请选择日期");return;}
                if(prefix==null||prefix==''){alert("请填写票据前缀");return;}
                if(startNo==null||startNo==''){alert("请填写票据起号");return;}
                if(endNo==null||endNo==''){alert("请填写票据止号");return;}
                if(buyTime==null||buyTime==''){alert("请选择购买日期");return;}
                $.ajax({
                    type:'post',
                    url:'TicketAction!add.action',
                    data:{
                        "ticketNo":ticketNo,
                        "username":username,
                        "useTime":useTime,
                        "ticketType":ticketType,
                        "prefix":prefix,
                        "startNo":startNo,
                        "endNo":endNo,
                        "invalid":invalid,
                        "buyTime":buyTime,
                        "isAudit":isAudit,
                        "isEnd":isEnd,
                        "demo":demo
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#ticketNo").val('');
                        $("#username").val('');
                        $("#useTime").datetimebox('setValue', '');
                        $("#ticketType").val('发票');
                        $("#prefix").val('');
                        $("#startNo").val('');
                        $("#endNo").val('');
                        $("#invalid").val('有效');
                        $("#buyTime").datetimebox('setValue', '');
                        $("#isAudit").val('未审核');
                        $("#isEnd").val('未核销');
                        $("#demo").val('');
                    }
                });


            });

            $("#save").click(function(){
                var ticketNo=$("#ticketNo").val();
                var username=$("#username").val();
                var useTime =$("#useTime").val();
                var ticketType=$("#ticketType").val();
                var prefix=$("#prefix").val();
                var startNo=$("#startNo").val();
                var endNo=$("#endNo").val();
                var invalid=$("#invalid").val();
                var buyTime=$("#buyTime").val();
                var isAudit=$("#isAudit").val();
                var isEnd=$("#isEnd").val();
                var demo=$("#demo").val();
                if(ticketNo==null||ticketNo==''){
                    alert("请输入票据本号");
                    return;
                }
                if(username==null||username==''){
                    alert("请选择领用人");
                    return;
                }
                if(useTime==null||useTime==''){alert("请选择日期");return;}
                if(prefix==null||prefix==''){alert("请填写票据前缀");return;}
                if(startNo==null||startNo==''){alert("请填写票据起号");return;}
                if(endNo==null||endNo==''){alert("请填写票据止号");return;}
                if(buyTime==null||buyTime==''){alert("请选择购买日期");return;}
                $.ajax({
                    type:'post',
                    url:'TicketAction!save.action',
                    data:{
                        "ticketid":ticketid,
                        "ticketNo":ticketNo,
                        "username":username,
                        "useTime":useTime,
                        "ticketType":ticketType,
                        "prefix":prefix,
                        "startNo":startNo,
                        "endNo":endNo,
                        "invalid":invalid,
                        "buyTime":buyTime,
                        "isAudit":isAudit,
                        "isEnd":isEnd,
                        "demo":demo
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                        $("#ticketNo").val('');
                        $("#username").val('');
                        $("#useTime").datetimebox('setValue', '');
                        $("#ticketType").val('发票');
                        $("#prefix").val('');
                        $("#startNo").val('');
                        $("#endNo").val('');
                        $("#invalid").val('有效');
                        $("#buyTime").datetimebox('setValue', '');
                        $("#isAudit").val('未审核');
                        $("#isEnd").val('未核销');
                        $("#demo").val('');
                    }
                });
            });

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
                var find_ticketNo=$("#find_ticketNo").val();
                var find_usePerson=$("#find_usePerson").val();
                var find_invalid=$("#find_invalid").val();
                var find_isAudit=$("#find_isAudit").val();
                var find_isEnd=$("#find_isEnd").val();
                console.log(find_ticketNo);
                console.log(find_usePerson);
                console.log(find_invalid);
                console.log(find_isAudit);
                console.log(find_isEnd);
                $.ajax({
                    type:'post',
                    url:'TicketAction!findbyCondition.action',
                    data:{
                        "find_ticketNo":find_ticketNo,
                        "find_usePerson":find_usePerson,
                        "find_invalid":find_invalid,
                        "find_isAudit":find_isAudit,
                        "find_isEnd":find_isEnd
                    },
                    dataType:'json',
                    success:function(data){
                        $("#dg").datagrid('loadData',data);
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

        function del(ticketid){
            $.messager.confirm("提示","你确定要删除选中数据？",function(){
                $.ajax({
                    type:'post',
                    url:'TicketAction!delete.action',
                    data:{
                        "ticketid":ticketid,
                    },
                    success: function(data){
                        $("#dg").datagrid('reload');
                    }
                });
            });
        }
    </script>

    <div class="easyui-tabs" title="新增票据" style="width:auto;height:auto">
        <div title="新增票据" style="width:auto;height: auto;">
            <div style="background: #99cdff;width:auto;">
                <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            </div>
            <div>
                <table align="center" style="width: auto" >
                    <tr>
                        <td align="right" style="width:105px">票据本号：</td>
                        <td align="left" style="width:80px"><input type="text" id="ticketNo" name="ticket.ticketNo"></td>
                        <td align="right"  style="width:105px">领用人：</td>
                        <td align="left" style="width:70px">
                            <input type="text" disabled="true" id="username" name="ticket.usePerson" style="width:130px">
                            <a href="javascript:;"><img id="tan" src="jquery-easyui-1.6.6/themes/icons/tip.png"></a>
                        </td>
                        <td align="right"  style="width:105px">领用时间：</td>
                        <td align="left" style="width:80px">
                            <input type="text" id="useTime" class="easyui-datetimebox" required="required">
                        </td>
                        <td align="right">票据类型：</td>
                        <td align="left" style="width:90px">
                            <select id="ticketType">
                                <option value="发票">发票</option>
                                <option value="收据">收据</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">票据前缀：</td>
                        <td align="left"><input type="text" id="prefix" name="ticket.prefix" required="true" ></td>
                        <td align="right">票据号起：</td>
                        <td align="left"><input type="text" id="startNo" name="ticket.startNo" required="true"></td>
                        <td align="right">票据号止：</td>
                        <td align="left"><input type="text" id="endNo" name="ticket.endNo" required="true"></td>
                        <td align="right">是否有效：</td>
                        <td align="left">
                            <select id="invalid">
                                <option value="有效">有效</option>
                                <option value="无效">无效</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">购买日期：</td>
                        <td align="left">
                            <input type="text" id="buyTime" class="easyui-datetimebox" required="required">
                        </td>
                        <td align="right">是否审核：</td>
                        <td align="left">
                            <select id="isAudit">
                                <option value="未审核">未审核</option>
                                <option value="已审核">已审核</option>
                            </select>
                        </td>
                        <td align="right">是否核销：</td>
                        <td align="left">
                            <select id="isEnd">
                                <option value="未核销">未核销</option>
                                <option value="已核销">已核销</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td align="right">备注：</td>
                        <td colspan="7" ><input id="demo" type="text" name="ticket.demo" style="width:800px"></td>
                    </tr>
                </table>
            </div>
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