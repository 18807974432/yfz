<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>楼栋资料</title>
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.6.6/demo/demo.css">
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="jquery-easyui-1.6.6/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>

<div style="background: #E0ECFF;padding: 0px;font-size:15px">&nbsp;&nbsp;&nbsp;&nbsp;
    楼栋名称：<input type="text" id="find_termName">
    <a href="javascript:;" id="find" class="easyui-linkbutton" data-options="iconCls:'icon-search'" style="width:80px">搜索</a>
</div>
<table id="dg" title="项目名称" style="width:100%;height:100%;"></table>
<script type="text/javascript">
    $(function(){
        var termId=0;
        $("#dg").datagrid({
            title:'楼栋资料',
            width:'auto',
            height:290,
            border:true,//边框
            url:'TermInfoAction!list.action',
            singleSelect:true,
            columns:[[
                {field:'termId',title:'楼栋编号',width:100},
                {field:'termName',title:'楼栋名称',width:100},
                {field:'termCode',title:'楼座代码',width:100},
                {field:'termType',title:'业态类型',width:100},
                {field:'projectName',title:'项目名称',width:100,},
                {field:'gardenName',title:'小区名称',width:100,},
                {field:'opr',title:'操作',width:100,formatter:function (value, row, index) {
                        return "<a href='javascript:void(0);' onclick='del("+row.termId+")' name='opera'>删除</a>";
                    }}
            ]],
            pagination:true,//如果表格需要支持分页，必须设置该选项为true
            pageSize:5,   //表格中每页显示的行数
            pageList:[5,10,15],
            // rownumbers:true,   //是否显示行号
            sortName: 'termId',  //按照ID列的值排序
            method:'get',   //表格数据获取方式,请求地址是上面定义的url
            loadMsg:'数据正在努力加载，请稍后...',   //加载数据时显示提示信息
            // frozenColumns: [[  //固定在表格左侧的栏
            //     {field: 'ck', checkbox: true},
            // ]],
            onClickRow:function(rowIndex, rowData){
                termId=rowData.termId;
                $("#projectName").val(rowData.projectName);
                $("#gardenName").find("option[text='"+rowData.gardenName+"']").attr('selected',true);
                $("#termName").val(rowData.termName);
                $("#termCode").val(rowData.termCode);
                $("#termType").val(rowData.termType);
                $("#prid").val(rowData.prid);
                $("#prPlanTime").val(rowData.prPlanTime);
                $("#prFactTime").val(rowData.prFactTime);
                $("#auditno").val(rowData.auditno);
                $("#contractno").val(rowData.contractno);
                $("#saleArea").val(rowData.saleArea);
                $("#useArea").val(rowData.useArea);
                $("#viewArea").val(rowData.viewArea);
                $("#startTime").val(rowData.startTime);
                $("#endTime").val(rowData.endTime);
                $("#saleTime").val(rowData.saleTime);
                $("#liveTime").val(rowData.liveTime);
                $("#hourseCount").val(rowData.hourseCount);
                $("#salePrice").val(rowData.salePrice);
                $("#isPaid").val(rowData.isPaid);
                $("#hourseHeight").val(rowData.hourseHeight);
                $("#floorcount").val(rowData.floorcount);
                $("#buildHeight").val(rowData.buildHeight);
                $("#perCount").val(rowData.perCount);
                $("#developer").val(rowData.developer);
                $("#buildadultno").val(rowData.buildadultno);
                $("#buildtype").val(rowData.buildtype);
                $("#description").val(rowData.description);

            },
            onLoadSuccess:function(data){
                $("a[name='opera']").linkbutton({text:'删除',plain:true,iconCls:'icon-remove'});
            }
        });
        $("#add").click(function(){
            var gardenName=$("#gardenName option:selected").val();
            var termName=$("#termName").val();
            var termCode=$("#termCode").val();
            var termType=$("#termType").val();
            var prid=$("#prid").val();
            var prPlanTime=$("#prPlanTime").val();
            var prFactTime=$("#prFactTime").val();
            var auditno=$("#auditno").val();
            var contractno=$("#contractno").val() ;
            var saleArea=$("#saleArea").val();
            var useArea=$("#useArea").val();
            var viewArea=$("#viewArea").val();
            var startTime=$("#startTime").val();
            var endTime=$("#endTime").val();
            var saleTime=$("#saleTime").val();
            var liveTime=$("#liveTime").val();
            var hourseCount=$("#hourseCount").val();
            var salePrice=$("#salePrice").val();
            var isPaid=$("#isPaid").val();
            var hourseHeight=$("#hourseHeight").val();
            var floorcount=$("#floorcount").val();
            var buildHeight=$("#buildHeight").val();
            var perCount=$("#perCount").val() ;
            var developer=$("#developer").val();
            var buildadultno=$("#buildadultno").val();
            var buildtype=$("#buildtype").val();
            var description=$("#description").val();

            console.log(termName);
            console.log(termCode);
            console.log(termType);
            console.log(prid);
            console.log(prPlanTime);
            console.log(prFactTime);
            console.log(auditno);
            console.log(contractno);
            console.log(saleArea);
            console.log(useArea);
            console.log(viewArea);
            console.log(startTime);
            console.log(endTime);
            console.log(saleTime);
            console.log(liveTime);
            console.log(hourseCount);
            console.log(salePrice);
            console.log(isPaid);
            console.log(hourseHeight);
            console.log(floorcount);
            console.log(buildHeight);
            console.log(perCount);
            console.log(developer);
            console.log(buildadultno);
            console.log(buildtype);
            console.log(description);
            if(termName==null||termName==""){
                alert("请输入楼座名称！");
                return null;
            }
            if(termCode==null||termCode==""){
                alert("请输入楼座代码！");
                return null;
            }
            if(termType==null||termType==""){
                alert("业态类型不能为空！");
                return null;
            }
            if(floorcount==null||floorcount==""){
                alert("请输入楼层！");
                return null;
            }
            if(perCount==null||perCount==""){
                alert("每层套数不能为空！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'TermInfoAction!add.action',
                data:{
                    "gardenName":gardenName,
                    "termName":termName,
                    "termCode":termCode,
                    "termType":termType,
                    "prid":prid,
                    "prPlanTime":prPlanTime,
                    "prFactTime":prFactTime,
                    "auditno":auditno,
                    "contractno":contractno,
                    "saleArea":saleArea,
                    "useArea":useArea,
                    "viewArea":viewArea,
                    "startTime":startTime,
                    "endTime":endTime,
                    "saleTime":saleTime,

                    "liveTime":liveTime,
                    "hourseCount":hourseCount,
                    "salePrice":salePrice,
                    "isPaid":isPaid,
                    "hourseHeight":hourseHeight,
                    "floorcount":floorcount,
                    "buildHeight":buildHeight,
                    "perCount":perCount,
                    "developer":developer,
                    "buildadultno":buildadultno,
                    "buildtype":buildtype,
                    "description":description
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#gardenName").val('');
                    $("#termName").val('');
                    $("#termCode").val('');
                    $("#termType").val('');
                    $("#prid").val('');
                    $("#prPlanTime").val('');
                    $("#prFactTime").val('');
                    $("#auditno").val('');
                    $("#contractno").val('');
                    $("#saleArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#saleTime").val('');
                    $("#liveTime").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#isPaid").val('');
                    $("#hourseHeight").val('');
                    $("#floorcount").val('');
                    $("#buildHeight").val('');
                    $("#perCount").val('');
                    $("#developer").val('');
                    $("#buildadultno").val('');
                    $("#buildtype").val('');
                    $("#description").val('');
                }
            });
        });
        $("#save").click(function(){
            var gardenName=$("#gardenName option:selected").val();
            var termName=$("#termName").val();
            var termCode=$("#termCode").val();
            var termType=$("#termType").val();
            var prid=$("#prid").val();
            var prPlanTime=$("#prPlanTime").val();
            var prFactTime=$("#prFactTime").val();
            var auditno=$("#auditno").val();
            var contractno=$("#contractno").val() ;
            var saleArea=$("#saleArea").val();
            var useArea=$("#useArea").val();
            var viewArea=$("#viewArea").val();
            var startTime=$("#startTime").val();
            var endTime=$("#endTime").val();
            var saleTime=$("#saleTime").val();
            var liveTime=$("#liveTime").val();
            var hourseCount=$("#hourseCount").val();
            var salePrice=$("#salePrice").val();
            var isPaid=$("#isPaid").val();
            var hourseHeight=$("#hourseHeight").val();
            var floorcount=$("#floorcount").val();
            var buildHeight=$("#buildHeight").val();
            var perCount=$("#perCount").val() ;
            var developer=$("#developer").val();
            var buildadultno=$("#buildadultno").val();
            var buildtype=$("#buildtype").val();
            var description=$("#description").val();

            console.log(termName);
            console.log(termCode);
            console.log(termType);
            console.log(prid);
            console.log(prPlanTime);
            console.log(prFactTime);
            console.log(auditno);
            console.log(contractno);
            console.log(saleArea);
            console.log(useArea);
            console.log(viewArea);
            console.log(startTime);
            console.log(endTime);
            console.log(saleTime);
            console.log(liveTime);
            console.log(hourseCount);
            console.log(salePrice);
            console.log(isPaid);
            console.log(hourseHeight);
            console.log(floorcount);
            console.log(buildHeight);
            console.log(perCount);
            console.log(developer);
            console.log(buildadultno);
            console.log(buildtype);
            console.log(description);
            if(termName==null||termName==""){
                alert("请输入楼座名称！");
                return null;
            }
            if(termCode==null||termCode==""){
                alert("请输入楼座代码！");
                return null;
            }
            if(termType==null||termType==""){
                alert("业态类型不能为空！");
                return null;
            }
            if(floorcount==null||floorcount==""){
                alert("请输入楼层！");
                return null;
            }
            if(perCount==null||perCount==""){
                alert("每层套数不能为空！");
                return null;
            }
            $.ajax({
                type:'post',
                url:'TermInfoAction!save.action',
                data:{
                    "termId":termId,
                    "gardenName":gardenName,
                    "termName":termName,
                    "termCode":termCode,
                    "termType":termType,
                    "prid":prid,
                    "prPlanTime":prPlanTime,
                    "prFactTime":prFactTime,
                    "auditno":auditno,
                    "contractno":contractno,
                    "saleArea":saleArea,
                    "useArea":useArea,
                    "viewArea":viewArea,
                    "startTime":startTime,
                    "endTime":endTime,
                    "saleTime":saleTime,

                    "liveTime":liveTime,
                    "hourseCount":hourseCount,
                    "salePrice":salePrice,
                    "isPaid":isPaid,
                    "hourseHeight":hourseHeight,
                    "floorcount":floorcount,
                    "buildHeight":buildHeight,
                    "perCount":perCount,
                    "developer":developer,
                    "buildadultno":buildadultno,
                    "buildtype":buildtype,
                    "description":description
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#gardenName").val('');
                    $("#termName").val('');
                    $("#termCode").val('');
                    $("#termType").val('');
                    $("#prid").val('');
                    $("#prPlanTime").val('');
                    $("#prFactTime").val('');
                    $("#auditno").val('');
                    $("#contractno").val('');
                    $("#saleArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#saleTime").val('');
                    $("#liveTime").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#isPaid").val('');
                    $("#hourseHeight").val('');
                    $("#floorcount").val('');
                    $("#buildHeight").val('');
                    $("#perCount").val('');
                    $("#developer").val('');
                    $("#buildadultno").val('');
                    $("#buildtype").val('');
                    $("#description").val('');
                }
            });
        });
    });
    $("#find").click(function(){
        var find_termName=$("#find_termName").val();
        $.ajax({
            type:'post',
            url:'TermInfoAction!findlist.action',
            data:{
                "find_termName":find_termName
            },
            dataType:'json',
            success:function(data){
                $("#dg").datagrid('loadData',data);
            }
        });
    });
    function del(termId){
        $.messager.confirm("提示","你确定要删除选中数据？",function(){
            $.ajax({
                type:'post',
                url:'TermInfoAction!delete.action',
                data:{
                    "termId":termId,
                },
                success: function(data){
                    $("#dg").datagrid('reload');
                    $("#termName").val('');
                    $("#termCode").val('');
                    $("#termType").val('');
                    $("#prid").val('');
                    $("#prPlanTime").val('');
                    $("#prFactTime").val('');
                    $("#auditno").val('');
                    $("#contractno").val('');
                    $("#saleArea").val('');
                    $("#useArea").val('');
                    $("#viewArea").val('');
                    $("#startTime").val('');
                    $("#endTime").val('');
                    $("#saleTime").val('');
                    $("#liveTime").val('');
                    $("#hourseCount").val('');
                    $("#salePrice").val('');
                    $("#isPaid").val('');
                    $("#hourseHeight").val('');
                    $("#floorcount").val('');
                    $("#buildHeight").val('');
                    $("#perCount").val('');
                    $("#developer").val('');
                    $("#buildadultno").val('');
                    $("#buildtype").val('');
                    $("#description").val('');
                }
            });
        });
    }
</script>
<div class="easyui-tabs" title="项目资料" style="width:auto;height:auto ">
    <div title="项目资料" style="width:auto;height:auto">
        <div style="background: #99cdff;width:auto;">
            <a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a id="save" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
        </div>
        <div>
            <table>
                <tr>
                    <td>小区名称:</td>
                    <td>
                        <select id="gardenName"  style="width:175px">
                            <s:iterator value="#request.projectList" var="p">
                                <option value="<s:property value='#p.projectId'></s:property>"><s:property value="#p.gardenName"></s:property> </option>
                            </s:iterator>
                        </select>

                    </td>
                    <td align="right">楼座名称:</td>
                    <td>
                        <input type="text" id="termName">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>楼座代码:</td>
                    <td>
                        <input type="text" id="termCode">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>业态类型:</td>
                    <td>
                        <select id="termType">
                            <option value="写字楼">写字楼</option>
                            <option value="商业住房">商业住房</option>
                            <option value="车库">车库</option>
                            <option value="医院">医院</option>
                        </select>
                        <font size="2" color="red">*</font>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td>是否可售:</td>
                    <td>
                        <select id="isPaid">
                            <option value="是">是</option>
                            <option value="否">否</option>
                        </select>
                    </td>
                    <td align="right">层数:</td>
                    <td>
                        <input type="text" id="floorcount">
                        <font size="2" color="red">*</font>
                    </td>
                    <td>楼高(m):</td>
                    <td><input type="text" id="buildHeight" ></td>
                    <td>每层套数:</td>
                    <td>
                        <input type="text" id="perCount">
                        <font size="2" color="red">*</font>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td align="right">层高:</td>
                    <td><input type="text" id="hourseHeight"></td>
                    <td align="right">建筑规划区号:</td>
                    <td><input type="text" id="buildadultno"></td>
                    <td>大产权号:</td>
                    <td><input  type="text" id="prid"></td>
                    <td>计划时间:</td>
                    <td><input  type="text" id="prPlanTime" class="easyui-datetimebox" required="required"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>完成时间:</td>
                    <td><input  type="text" id="prFactTime" class="easyui-datetimebox" required="required"></td>
                    <td align="right">合同栋号:</td>
                    <td><input id="contractno" type="text"></td>
                    <td>报批楼号:</td>
                    <td><input id="auditno" type="text"></td>
                    <td>开工时间:</td>
                    <td><input type="text" id="startTime" class="easyui-datetimebox" required="required"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>竣工时间:</td>
                    <td><input type="text" id="endTime" class="easyui-datetimebox" required="required"></td>
                    <td align="right">销售面积:</td>
                    <td><input type="text" id="saleArea"></td>
                    <td>使用面积:</td>
                    <td><input type="text" id="useArea"></td>
                    <td>绿化面积:</td>
                    <td><input type="text" id="viewArea"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td>开盘时间:</td>
                    <td><input type="text" id="saleTime" class="easyui-datetimebox" required="required"></td>
                    <td align="right">入住时间:</td>
                    <td><input type="text" id="liveTime" class="easyui-datetimebox" required="required"></td>
                    <td>房产总数:</td>
                    <td><input type="text" id="hourseCount"></td>
                    <td>期望售价:</td>
                    <td><input type="text" id="salePrice"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td align="right">开发商:	</td>
                    <td><input type="text" id="developer"></td>
                    <td align="right">建筑类型 :</td>
                    <td><input type="text" id="buildtype"></td>
                </tr>
                <tr></tr>
                <tr>
                    <td align="right">描述:</td>
                    <td><input type="text" id="description" ></td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
