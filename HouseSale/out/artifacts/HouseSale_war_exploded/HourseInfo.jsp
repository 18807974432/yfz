<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>房产管理</title>
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
    楼座名称：<select id="find_termName" style="width:175px">
                <s:iterator value="#request.termInfoList" var="t">
                    <s:iterator value="#request.projectInfoList" var="p">
                        <s:if test="%{#t.projectId==#p.projectId}" >
                            <option value="<s:property value="#p.projectName"/>-<s:property value="#t.termName"/>"><s:property value="#p.projectName"/>-<s:property value="#t.termName"/></option>
                        </s:if>
                    </s:iterator>
                </s:iterator>
            </select>
    销售状态：<select id="find_saleState">
                <s:iterator value="#request.saleStateList" var="s">
                    <option <s:property value="#s.saleName"/>><s:property value="#s.saleName"/></option>
                </s:iterator>
            </select>
    业态类型：<select  id="find_termType">
                <option value="普通住宅">普通住宅</option>
                <option value="商铺">商铺</option>
                <option value="车库">车库</option>
                <option value="别墅">别墅</option>
                <option value="商品房">商品房</option>
            </select>
    单元：<input type="text" id="find_unit" style="width:60px">
    楼层：<input type="text" id="find_floor" style="width:60px">
    <a href="javascript:;" id="find" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">搜索</a>
    <a href="javascript:;" id="clearfind" class="easyui-linkbutton" >清空条件</a>
</div>

<table id="dg"  style="width:100%;height:100%;" ></table>
<script type="text/javascript">
    $(function(){
        var ticketid=0;
        $("#dg").datagrid({
            width:'auto',
            height:250,
            border:true,//边框
            url:'HourseInfoAction!listjson.action',
            singleSelect:true,
            columns:[[
                {field:'hourseId',title:'房产编号'},
                {field:'hourseName',title:'房产名称'},
                {field:'unitid',title:'单元'},
                {field:'floor',title:'楼层'},
                {field:'hourseno',title:'房号'},
                {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick='del("+row.hourseId+")' name='opera'>删除</a>";
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
                $("#hourseId").val(rowData.hourseId);
                $("#termName").val(rowData.termId);
                $("#hourseName").val(rowData.hourseName);
                $("#hourseType").val(rowData.hourseType);
                $("#termType").val(rowData.termType);
                $("#saleState").val(rowData.saleState);
                $("#unitid").val(rowData.unitid);
                $("#floor").val(rowData.floor);
                $("#hourseno").val(rowData.hourseno);
                $("#contractno").val(rowData.contractno);
                $("#fitment").val(rowData.fitment);
                $("#direction").val(rowData.direction);
                $("#struts").val(rowData.struts);
                $("#buildstyle").val(rowData.buildstyle);
                $("#modelName").val(rowData.modelname);
                $("#isbalance").val(rowData.isbalance);
                $("#lift").val(rowData.lift);
                $("#saleArea").val(rowData.saleArea);
                $("#inArea").val(rowData.inArea);
                $("#buildArea").val(rowData.buildArea);
                $("#viewArea").val(rowData.viewArea);
                $("#publicArea").val(rowData.publicArea);
                $("#tableArea").val(rowData.tableArea);
                $("#usePercent").val(rowData.usePercent);
                $("#facttableArea").val(rowData.facttableArea);
                $("#factinArea").val(rowData.factinArea);
                $("#factbuildArea").val(rowData.factbuildArea);
                $("#factviewArea").val(rowData.factviewArea);
                $("#factpublicArea").val(rowData.factpublicArea);
                $("#factdownArea").val(rowData.factdownArea);
                $("#totallowPrice").val(rowData.totallowPrice);
                $("#unitlowPrice").val(rowData.unitlowPrice);
                $("#unitPrice").val(rowData.unitPrice);
                $("#totalPrice").val(rowData.totalPrice);
                $("#inunitPrice").val(rowData.inunitPrice);
                $("#buildunitPrice").val(rowData.buildunitPrice);
                $("#saleTime").datetimebox('setValue', formatDatebox(rowData.saleTime));
                $("#oldtotalPrice").val(rowData.oldtotalPrice);
                $("#oldunitPrice").val(rowData.oldunitPrice);
                $("#downArea").val(rowData.downArea);
                $("#doorno").val(rowData.doorno);
                $("#outArea").val(rowData.outArea);
                $("#floor").val(rowData.floor);
                $("#hourseno").val(rowData.hourseno);
                $("#discount").val(rowData.discount);
                $("#discountPrice").val(rowData.discountPrice);
                $("#commisionPercent").val(rowData.commisionPercent);
                $("#commisionMoney").val(rowData.commisionMoney);
                $("#commisionpaid").val(rowData.commisionpaid);
                $("#description").val(rowData.description);
            },
            onLoadSuccess:function(data){
                $('.datagrid-cell').css('font-size','9px');
                $('.datagrid-header .datagrid-cell span ').css('font-size','9px');
                $('.datagrid-row').css('height','10px');
                $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            }

        });

        $("#add").click(function(){
            if($("#unitid").val()==null||$("#unitid").val()==''){
                alert("请输入单元号");
                return ;
            }
            if($("#floor").val()==null||$("#floor").val()==''){
                alert("请输入楼层");
                return ;
            }
            if($("#hourseno").val()==null||$("#hourseno").val()==''){
                alert("请输入房号");
                return;
            }
            $("#form1").submit();

        });

        $("#save").click(function(){
            if($("#unitid").val()==null||$("#unitid").val()==''){
                alert("请输入单元号");
                return ;
            }
            if($("#floor").val()==null||$("#floor").val()==''){
                alert("请输入楼层");
                return ;
            }
            if($("#hourseno").val()==null||$("#hourseno").val()==''){
                alert("请输入房号");
                return;
            }
            $("#form1").attr('action','HourseInfoAction!save.action');
            $("#form1").submit();
        });


        //条件查询
        $("#find").click(function(){
            var find_termName=$("#find_termName").val();
            var find_saleState=$("#find_saleState").val();
            var find_termType=$("#find_termType").val();
            var find_unit=$("#find_unit").val();
            var find_floor=$("#find_floor").val();
            $.ajax({
                type:'post',
                url:'HourseInfoAction!findbyCondition.action',
                data:{
                    "find_termName":find_termName,
                    "find_saleState":find_saleState,
                    "find_termType":find_termType,
                    "find_unit":find_unit,
                    "find_floor":find_floor
                },
                dataType:'json',
                success:function(data){
                    $("#dg").datagrid('loadData',data);
                }
            });
        });
        $("#clearfind").click(function(){
            var find_termName="";
            var find_saleState="";
            var find_termType="";
            var find_unit="";
            var find_floor="";
            $.ajax({
                type:'post',
                url:'HourseInfoAction!findbyCondition.action',
                data:{
                    "find_termName":find_termName,
                    "find_saleState":find_saleState,
                    "find_termType":find_termType,
                    "find_unit":find_unit,
                    "find_floor":find_floor
                },
                dataType:'json',
                success:function(data){
                    $("#dg").datagrid('loadData',data);
                }
            });

        });


        $("#termName").change(function(){
            $("#hourseName").val($("#termName  option:selected").text()+"-"+$("#unitid").val()+"-"+$("#floor").val()+$("#hourseno").val());
        });
        $("#unitid").blur(function(){
            $("#hourseName").val($("#termName  option:selected").text()+"-"+$("#unitid").val()+"-"+$("#floor").val()+$("#hourseno").val());
        });
        $("#floor").blur(function(){
            $("#hourseName").val($("#termName  option:selected").text()+"-"+$("#unitid").val()+"-"+$("#floor").val()+$("#hourseno").val());
        });
        $("#hourseno").blur(function(){
            $("#hourseName").val($("#termName  option:selected").text()+"-"+$("#unitid").val()+"-"+$("#floor").val()+$("#hourseno").val());
        });
        //初始化值
        $("#hourseName").val($("#termName  option:selected").text());


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

    function del(hourseId){
        $.messager.confirm("提示","你确定要删除选中数据？",function(){
            $.ajax({
                type:'post',
                url:'HourseInfoAction!delete.action',
                data:{
                    "hourseId":hourseId,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                }
            });
        });
    }
</script>

<div class="easyui-tabs" title="房产资料" style="width:auto;height:auto">
    <div title="户型资料" style="width:auto;height: auto;">
        <div style="background: #99cdff;width:auto;">
            <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        </div>
        <div>
            <s:form id="form1" method="POST" action="HourseInfoAction!add.action">
                <s:hidden id="hourseId" name="hourseInfo.hourseId" ></s:hidden>
            <table align="center" style="font-size: 17px">
                <tr>
                    <td>楼座名称:</td>
                    <td>
                        <select id="termName" name="hourseInfo.termId"  style="width:175px">
                            <s:iterator value="#request.termInfoList" var="t">
                                <s:iterator value="#request.projectInfoList" var="p">
                                    <s:if test="%{#t.projectId==#p.projectId}" >
                                        <option value="<s:property value='#t.termId'/>"><s:property value="#p.projectName"/>-<s:property value="#t.termName"/></option>
                                    </s:if>
                                </s:iterator>
                            </s:iterator>
                        </select>
                    </td>
                    <td align="right">房产名称:</td>
                    <td>
                        <input type="text" id="hourseName" readonly="readonly" name="hourseInfo.hourseName">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>房产类型:</td>
                    <td>
                        <select id="hourseType" name="hourseInfo.hourseType">
                            <option value="现房">现房</option>
                            <option value="期房">期房</option>
                        </select>
                        <font size="2" color="red">*</font>
                    </td>
                    <td>业态类型:</td>
                    <td>
                        <select id="termType" name="hourseInfo.termType">
                            <option value="普通住宅">普通住宅</option>
                            <option value="商铺">商铺</option>
                            <option value="车库">车库</option>
                            <option value="别墅">别墅</option>
                            <option value="商品房">商品房</option>
                        </select>
                        <font size="2" color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td>销售状态:</td>
                    <td>
                        <select id="saleState" disabled="disabled" name="hourseInfo.saleState">
                            <s:iterator value="#request.saleStateList" var="s">
                                <option <s:property value="#s.saleName"/>><s:property value="#s.saleName"/></option>
                            </s:iterator>
                        </select>
                    </td>
                    <td>单元:</td>
                    <td>
                        <input type="text" id="unitid" name="hourseInfo.unitid">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>楼层:</td>
                    <td>
                        <input type="text" id="floor" name="hourseInfo.floor">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>房号:</td>
                    <td>
                        <input type="text" id="hourseno" name="hourseInfo.hourseno">
                        <font size="2" color="red">*</font>
                    </td>
                </tr>
                <tr>
                    <td>合同房产号:</td>
                    <td>
                        <input type="text" id="contractno" name="hourseInfo.contractno">
                    </td>
                    <td>装修标准:</td>
                    <td>
                        <input type="text" id="fitment" name="hourseInfo.fitment">
                    </td>
                    <td>朝向:</td>
                    <td>
                        <select id="direction" name="hourseInfo.direction">
                            <option value="坐东朝西">坐东朝西</option>
                            <option value="坐西朝东">坐西朝东</option>
                            <option value="坐南朝北">坐南朝北</option>
                            <option value="坐北朝南">坐北朝南</option>
                            <option value="坐东北朝西南">坐东北朝西南</option>
                            <option value="坐东南朝西北">坐东南朝西北</option>
                            <option value="坐西北朝东南">坐西北朝东南</option>
                            <option value="坐西南朝东北">坐西南朝东北</option>
                        </select>
                    </td>
                    <td>结构</td>
                    <td>
                        <select id="struts" name="hourseInfo.struts">
                            <option value="小高层">小高层</option>
                            <option value="半高层">半高层</option>
                            <option value="普通商品房">普通商品房</option>
                            <option value="楼栋结构测试">楼栋结构测试</option>
                            <option value="现浇剪力墙">现浇剪力墙</option>
                            <option value="框架剪力墙">框架剪力墙</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>建筑形式:</td>
                    <td>
                        <select id="buildstyle" name="hourseInfo.buildstyle">
                            <option value="复古">复古</option>
                            <option value="现代">现代</option>
                            <option value="欧美">欧美</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                    <td>户型名称:</td>
                    <td>
                        <select id="modelName" name="hourseInfo.modelname"  style="width:175px">
                            <s:iterator value="#request.housemodelList" var="h">
                                <option value="<s:property value="#h.modelName"/>"><s:property value="#h.modelName"/></option>
                            </s:iterator>
                        </select>
                    </td>
                    <td>是否已结算:</td>
                    <td>
                        <select id="isbalance" name="hourseInfo.isbalance">
                            <option value="是">是</option>
                            <option value="否">否</option>
                        </select>
                    </td>
                    <td>几梯几户:</td>
                    <td>
                        <input type="text" id="lift" name="hourseInfo.lift">
                    </td>
                </tr>
                <tr>
                    <td>销售面积:</td>
                    <td>
                        <input type="text" id="saleArea" name="hourseInfo.saleArea">
                    </td>
                    <td>套内面积:</td>
                    <td>
                        <input type="text" id="inArea" name="hourseInfo.inArea">
                    </td>
                    <td>建筑面积:</td>
                    <td>
                        <input type="text" id="buildArea" name="hourseInfo.buildArea">
                    </td>
                    <td>花园面积:</td>
                    <td>
                        <input type="text" id="viewArea" name="hourseInfo.viewArea">
                    </td>
                </tr>
                <tr>
                    <td>公摊面积:</td>
                    <td>
                        <input type="text" id="publicArea" name="hourseInfo.publicArea">
                    </td>
                    <td>阳台面积:</td>
                    <td>
                        <input type="text" id="tableArea" name="hourseInfo.tableArea">
                    </td>
                    <td>使用率(%):</td>
                    <td>
                        <input type="text" id="usePercent" name="hourseInfo.usePercent">
                    </td>
                    <td>实测平台:</td>
                    <td>
                        <input type="text" id="facttableArea" name="hourseInfo.facttableArea">
                    </td>
                </tr>
                <tr>
                    <td>实测套内:</td>
                    <td>
                        <input type="text" id="factinArea" name="hourseInfo.factinArea">
                    </td>
                    <td>实测建筑:</td>
                    <td>
                        <input type="text" id="factbuildArea" name="hourseInfo.factbuildArea">
                    </td>
                    <td>实测花园:</td>
                    <td>
                        <input type="text" id="factviewArea" name="hourseInfo.factviewArea">
                    </td>
                    <td>实测公摊:</td>
                    <td>
                        <input type="text" id="factpublicArea" name="hourseInfo.factpublicArea">
                    </td>
                </tr>
                <tr>
                    <td>装修单价:</td>
                    <td>
                        <input type="text" id="totallowPrice" name="hourseInfo.totallowPrice">
                    </td>
                    <td>签约单价:</td>
                    <td>
                        <input type="text" id="unitlowPrice" name="hourseInfo.unitlowPrice">
                    </td>
                    <td>销售单价:</td>
                    <td>
                        <input type="text" id="unitPrice" name="hourseInfo.unitPrice">
                    </td>
                    <td>销售总价:</td>
                    <td>
                        <input type="text" id="totalPrice" name="hourseInfo.totalPrice">
                    </td>
                </tr>
                <tr>
                    <td>套内单价:</td>
                    <td>
                        <input type="text" id="inunitPrice" name="hourseInfo.inunitPrice">
                    </td>
                    <td>建筑单价:</td>
                    <td>
                        <input type="text" id="buildunitPrice" name="hourseInfo.buildunitPrice">
                    </td>
                    <td>推出时间:</td>
                    <td><input  type="text" id="saleTime" name="hourseInfo.saleTime" class="easyui-datetimebox" required="required"></td>
                    <td>原始单价:</td>
                    <td>
                        <input type="text" id="oldtotalPrice" name="hourseInfo.oldtotalPrice">
                    </td>
                </tr>
                <tr>
                    <td>原始总价:</td>
                    <td>
                        <input type="text" id="oldunitPrice" name="hourseInfo.oldunitPrice">
                    </td>
                    <td>优惠单价:</td>
                    <td>
                        <input type="text" id="downArea" name="hourseInfo.downArea" >
                    </td>
                    <td>优惠总价:</td>
                    <td>
                        <input type="text" id="factdownArea" name="hourseInfo.factdownArea">
                    </td>
                    <td>渠道费:</td>
                    <td>
                        <input type="text" id="outArea" name="hourseInfo.outArea">
                    </td>
                </tr>
                <tr>
                    <td>门牌号:</td>
                    <td>
                        <input type="text" id="doorno" name="hourseInfo.doorno">
                    </td>
                    <td>折扣(%):</td>
                    <td>
                        <input type="text" id="discount" name="hourseInfo.discount">
                    </td>
                    <td>优惠金额:</td>
                    <td>
                        <input type="text" id="discountPrice" name="hourseInfo.discountPrice">
                    </td>
                    <td>佣金比例:</td>
                    <td>
                        <input type="text" id="commisionPercent" name="hourseInfo.commisionPercent">
                    </td>
                </tr>
                <tr>
                    <td>应付佣金:</td>
                    <td>
                        <input type="text" id="commisionMoney" name="hourseInfo.commisionMoney">
                    </td>
                    <td>已付佣金:</td>
                    <td>
                        <input type="text" id="commisionpaid" name="hourseInfo.commisionpaid">
                    </td>
                    <td>描述:</td>
                    <td>
                        <textarea id="description" name="hourseInfo.description"></textarea>
                    </td>
                </tr>
            </table>
            </s:form>
        </div>
    </div>
</div>

</body>
</html>