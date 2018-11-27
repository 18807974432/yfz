<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/19
  Time: 16:10
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <jsp:include page="IncludeJS.jsp" flush="true"></jsp:include>

    <script type="text/javascript">
        var roweventId;
        var rowplanId;
        $(function(){
            var pager = $(".easyui-datagrid").datagrid('getPager');
            $(pager).pagination({
                total:${pager.totalRows},
                pageSize:${pager.pageRow},
                displayMsg:'{from}-{to}/{total}',
                pageNumber:${pager.cur_page},
                afterPageText:'页，共{pages}页',
                pageList:[2,3,5,50,100],
                loading:false,
                showPageList:true,
                showRefresh:true,
                //刷新方法
                onBeforeRefresh:function(p,s){
                    location.href="${pageContext.request.contextPath }/cusAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                },
                onSelectPage:function(p,s){
                    location.href="${pageContext.request.contextPath }/cusAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                }
            });
            $('#changesize').datagrid('resize', {
                height:document.body.clientHeight-180
            });
        });
        function  selCust() {
            var houseid = $("#houseid").val();
            var custid = $("#custid").val();
            if(houseid=="" || houseid=="0"){
                alert("请进入售楼指引选择您要订购的房产,再选择客户资料进入下一步");
                return;
            }
            if(custid=='')
            {
                alert("请选择客户。");
                return ;
            }
            //if(!confirm("您确认选择客户"+$("#custname").val()+"吗？")){
            //return ;
            //}
            //alert("houseid="+houseid+";custid="+custid);
            var params="custInfo.custId=" + custid +"&houseInfo.hourseId="+houseid;
            location.href="${pageContext.request.contextPath }/buyHourseAction!list.action?"+params;
            
        }
        function edit(){
            var ids = $('input[name="id"]:checked');
            var len = ids.length;
            if(ids.length==0){
                alert("请选择要修改的学生资料");
                return;
            }
            if(ids.length>1){
                alert("只能选择一个学生资料");
                return;
            }
            var arr="";
            ids.each(function(i,d){
                if((i+1)!=len){
                    arr += $(d).val()+",";
                }else{
                    arr += $(d).val();
                }
            })
            url = "${pageContext.request.contextPath }/cusAction!update.action"+arr;
            location.href=url;
        }
        //单击某一行时会触发行的onClickRow,value是行索引，rec代表当前选中的行,包含了所有字段的值
        function getRowData(value,rec){
            var id= rec.custId;


            var url="${pageContext.request.contextPath }/cusAction!getjson.action"
            $.post(
                url,
                {
                    "custom.custId":id
                },
                umpdate,
                "json"
            );



        }
        function umpdate(data) {
            $("#projectId").val(data.custom.pro.projectName);
            $("#custname").val(data.custom.custname);
            $("#custnamecode").val(data.custom.custnamecode);
            $("#custtype").val(data.custom.custtype);
            $("#custid").val(data.custom.custId);
            $("#cardname").val(data.custom.cardname);
            $("#cardno").val(data.custom.cardno);
            $("#degree").val(data.custom.degree);
            $("#custstate").val(data.custom.custstate);
            $("#activeid").val(data.custom.activeid);
            $("#salePercent").val(data.custom.salePercent);
            $("#chance").val(data.custom.chance);
            $("#eventtypeid").val(data.custom.eventtypeid);

            $("#email").val(data.custom.email);
            $("#saleperson").val(data.custom.saleperson);
            $("#birthday").val(data.custom.birthday);
            $("#mobile").val(data.custom.mobile);

            $("#country").val(data.custom.country);
            $("#nation").val(data.custom.nation);
            $("#babyaddr").val(data.custom.babyaddr);
            $("#saleperson").val(data.custom.saleperson);

            $("#sex").val(data.custom.sex);
            $("#addr").val(data.custom.addr);

            //返回事件资料
            url="${pageContext.request.contextPath}/custEvent!getjson.action";
            var custid=data.custom.custId;
            $.post(
                url,
                    {
                    "custEvent.custid":custid
                    },
                listEvent,
                "json"
            );
            //返回客户调研试卷资料列表
            url = "${pageContext.request.contextPath }/researchAction!json3.action";
            $.post(
                url,
                {
                    "custid":custid
                },
                listexam,
                "json"
            );
            //返回客户已订购或者已签约房产列表资料
           url="${pageContext.request.contextPath}/houseInfo!json.action";
           $.post(
               url,
               {
                  "house.custid":custid
               },
                listHourse,
               "json"
           )

            //返回客户意向资料
            url = "${pageContext.request.contextPath }/Cusplan!getjson.action";
            var custid=data.custom.custId;
            $.post(
                url,
                {
                    "custom.custId":custid
                },
                listPlan,
                "json"
            );

        }
        //动态生成意向表格
        function listPlan(data){

            $('#planTable').datagrid({
                height: '190',
                fitColumns: true,
                singleSelect:true,
                columns:[[
                    {field:'termTypeName',title:'意向业态',width:100,align:'center'},
                    {field:'hourseid',title:'意向房产',width:80,align:'center'},
                    {field:'modelId',title:'意向户型',width:50,align:'center'},
                    {field:'paidtypeid',title:'付款方式',width:100,align:'center'},
                    {field:'plantotalPrice',title:'意向总价',width:50,align:'center'},
                    {field:'planunitPrice',title:'意向单价',width:50,align:'center'},
                    {field:'planArea',title:'意向面积',width:50,align:'center'},
                    {field:'priority',title:'优先级',width:50,align:'center'},
                    {field:'demo',title:'备注',width:80,align:'center'}
                ]],
                onHeaderContextMenu: function(e, field){
                    e.preventDefault();
                    if (!$('#tmenu').length){
                        createColumnMenu3();
                    }
                    $('#tmenu').menu('show', {
                        left:e.pageX,
                        top:e.pageY
                    });
                }
            }).datagrid("loadData",data);
        }
        function createColumnMenu3(){
            var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
            var fields = $('#planTable').datagrid('getColumnFields');
            for(var i=0; i<fields.length; i++){
                $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
            }
            tmenu.menu({
                onClick: function(item){
                    if (item.iconCls=='icon-ok'){
                        $('#planTable').datagrid('hideColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-empty'
                        });
                    } else {
                        $('#planTable').datagrid('showColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-ok'
                        });
                    }
                }
            });
        }


        //显示答卷
        function listexam(data){
            $('#examTable').datagrid({
                height: '220',
                fitColumns: true,
                singleSelect:true,
                columns:[[
                    {field:'examName',title:'答卷名称',width:200,align:'center'},
                    {field:'invalid',title:'是否启用',width:50},
                    {field:'examCount',title:'题目数量',width:50},
                    {field:'userid',title:'出卷人',width:50},
                    {field:'description',title:'描述',width:350}
                ]],
                onHeaderContextMenu: function(e, field){
                    e.preventDefault();
                    if (!$('#tmenu').length){
                        createColumnMenu1();
                    }
                    $('#tmenu').menu('show', {
                        left:e.pageX,
                        top:e.pageY
                    });
                }
            }).datagrid("loadData",data);
        }
        function createColumnMenu1(){
            var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
            var fields = $('#examTable').datagrid('getColumnFields');
            for(var i=0; i<fields.length; i++){
                $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
            }
            tmenu.menu({
                onClick: function(item){
                    if (item.iconCls=='icon-ok'){
                        $('#examTable').datagrid('hideColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-empty'
                        });
                    } else {
                        $('#examTable').datagrid('showColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-ok'
                        });
                    }
                }
            });
        }
        //动态生成 房产事件
        function listHourse(data) {
            $('#houseTable').datagrid({
                height: '220',
                fitColumns: true,
                singleSelect:true,
                columns:[[
                    {field:'hourseName',title:'房产名称',width:100,align:'center'},
                    {field:'termType',title:'业态',width:50,align:'center'},
                    {field:'saleArea',title:'使用面积',width:50,align:'center'},
                    {field:'modelname',title:'户型',width:100,align:'center'},
                    {field:'direction',title:'朝向',width:50,align:'center'},
                    {field:'saleState',title:'房间状态',width:50,align:'center'},
                    {field:'saleTime',title:'时间',width:80,align:'center'}
                ]],
                onHeaderContextMenu: function(e, field){
                    e.preventDefault();
                    if (!$('#tmenu').length){
                        createColumnMenu2();
                    }
                    $('#tmenu').menu('show', {
                        left:e.pageX,
                        top:e.pageY
                    });
                }
            }).datagrid("loadData",data);
        }
        function createColumnMenu2(){
            var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
            var fields = $('#houseTable').datagrid('getColumnFields');
            for(var i=0; i<fields.length; i++){
                $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
            }
            tmenu.menu({
                onClick: function(item){
                    if (item.iconCls=='icon-ok'){
                        $('#houseTable').datagrid('hideColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-empty'
                        });
                    } else {
                        $('#houseTable').datagrid('showColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-ok'
                        });
                    }
                }
            });
        }
        //动态生成 客户事件
        function listEvent(data) {


            $('#eventTable').datagrid({
                height: '190',
                fitColumns: true,
                singleSelect:true,
                columns:[[
                    {field:'eventId',title:'事件编号',width:30},
                    {field:'eventtypeid',title:'事件类型',width:50},
                    {field:'eventTime',title:'事件时间',width:50},
                    {field:'description',title:'描述',width:350}
                ]],
                onHeaderContextMenu: function(e, field){
                    e.preventDefault();
                    if (!$('#tmenu').length){
                        createColumnMenu();
                    }
                    $('#tmenu').menu('show', {
                        left:e.pageX,
                        top:e.pageY
                    });
                }
            }).datagrid("loadData",data);




        }

        //新增事件
        function newEvent(){
            var cid=$("#custid").val();
            if($("#custid").val()=='')
            {
                alert("请选择客户");
                return;
            }
            $("#editBx2").window(
                {
                    href:'<%=basePath%>/custEvent!add.action',
                    onClose:function(){

                    }
                });
            $('#editBx2').window('refresh');
            $('#editBx2').window('open');


        }
        //新增事件
       function saveEvent() {

            var url="<%=basePath%>/custEvent!save.action";
            var eventIds=$("#eventIds").val();
            if(eventIds=="")
                eventIds=0;
            var custIds=$("#custid").val();
            var salePercents = $("#salePercents").val();
            var chances = $("#chances").val();
            var eventtypeids = $("#eventtypeids").val();
            var eventTimes = $("#eventTimes").datebox('getValue');
            var descriptions = $("#descriptions").val();
           // alert(eventIds+":"+custIds+":"+salePercents+":"+chances+":"+eventtypeids+":"+eventTimes+":"+descriptions);

            $.post(
                url,
                {
                    "custEvent.eventId":eventIds,
                    "custEvent.custid":custIds,
                    "custEvent.salePercent":salePercents,
                    "custEvent.chance":chances,
                    "custEvent.eventtypeid":eventtypeids,
                    "custEvent.eventTime":eventTimes,
                    "custEvent.description":descriptions
                },
                listEvent,
                    "json"
            );
            $('#editBx2').window('close');
        }

        function createColumnMenu(){
            var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
            var fields = $('#eventTable').datagrid('getColumnFields');
            for(var i=0; i<fields.length; i++){
                $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
            }
            tmenu.menu({
                onClick: function(item){
                    if (item.iconCls=='icon-ok'){
                        $('#eventTable').datagrid('hideColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-empty'
                        });
                    } else {
                        $('#eventTable').datagrid('showColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-ok'
                        });
                    }
                }
            });
        }

        function save(){
            document.forms[1].submit();
        }
        function add(){
            url="${pageContext.request.contextPath }/cusAction!init.action";
            /*	var title="区域新增";
             addTab1(title, url);*/
            location.href=url;
        }
        function test1(value,rec) {
            roweventId=rec.eventId;
        }
        function test2(value,rec){
            rowplanId=rec.planId;
        }
        //删除事件
        function delEvent(){

            if($("#custid").val()==''){
             alert("请选择客户");
             return;
            }
            if(roweventId==null){
             alert("请选择客户事件");
             return;
            }
            if(!confirm('确认删除')){
                return;
            }
         url="<%=basePath%>/custEvent!del.action";
         var rowcustid=$("#custid").val();

         $.post(
             url,
             {
               "custEvent.eventId":roweventId,
               "custEvent.custid":rowcustid
             },
             listEvent,
             "json"
         );
        }
        //修改事件信息
        function getEvent(){
            if($("#custid").val()=='')
            {
                alert("请选择客户");
                return;
            }
            if(roweventId=="" || roweventId==null)
            {
                alert("请选择要修改的事件");
                return;
            }
            $("#editBx2").window(
                {
                    href:'${pageContext.request.contextPath}/custEvent!getdata.action?custEvent.eventId='+roweventId,
                    onClose:function(){

                    }
                }
            )
            $('#editBx2').window('refresh');
            $('#editBx2').window('open');

        }

        function del(){
            var ids = $('input[name="id"]:checked');
            var len = ids.length;
            if(ids.length==0){
                alert("请选择要删除的洽谈资料");
                return;
            }
            if(!confirm('删除确认')){
                return;
            }
            var arr="";
            ids.each(function(i,d){
                if((i+1)!=len){
                    arr += $(d).val()+",";
                }else{
                    arr += $(d).val();
                }
            })
            var url = "${pageContext.request.contextPath }/cusAction!del.action?custom.custId="+arr;
            location.href=url;
        }
        if("${msg}"!=null&&"${msg}".length>0){
            alert("${msg}");
        }
        //打开试卷页面
        function openExam(researchid) {
            var custid = $("#custid").val();
            var url = "${pageContext.request.contextPath }/researchAction!examlist.action?research.reseachid="+researchid+"&custid="+custid;
            $("#editBx4").window(
                {
                    href:url,
                    onClose:function(){

                    }
                });
            $('#editBx4').window('refresh');
            $('#editBx4').window('open');
        }
        //客户意向
        //新增事件
        function newPlan() {
            if($("#custid").val()=='')
            {
                alert("请选择客户");
                return;
            }
            $("#editBx3").window(
                {
                    href:'${pageContext.request.contextPath}/Cusplan!list.action',
                    onClose:function(){

                    }
                });
            $('#editBx3').window('refresh');
            $('#editBx3').window('open');
            
        }
        //修改意向
        function getPlan(){
            if($("#custid").val()=='')
            {
                alert("请选择客户");
                return;
            }
            if(rowplanId=="")
            {
                alert("请选择要修改的数据。");
                return;
            }
            $("#editBx3").window(
                {
                    href:'${pageContext.request.contextPath}/Cusplan!update.action?custPlan.planId='+rowplanId,
                    onClose:function(){

                    }
                });
            $('#editBx3').window('refresh');
            $('#editBx3').window('open');
        }
        //删除意向
        function delPlan(){
            if($("#custid").val()=='')
            {
                alert("请选择客户");
                return;
            }
            if(rowplanId==null)
            {
                alert("请选择要删除的数据");
                return;
            }
            if(!confirm('删除确认'))
            {
                return;
            }
            url = "${pageContext.request.contextPath }/Cusplan!del.action";
            var custid = $("#custid").val();
            $.post(
                url,
                {
                    "custom.custId":custid,
                    "custPlan.planId":rowplanId
                },
                listPlan,
                "json"
            );
        }
        function savePlan(){
            var url = "${pageContext.request.contextPath }/Cusplan!save.action";
            var planId = $("#planId").val();
            if(planId=="")
                planId="0";
            var custIds = $("#custid").val();
            var termTypeName = $("#termTypeName").val();
            var hourseid = $("#hourseid").val();
            var modelId = $("#modelId").val();
            var paidtypename = $("#paidtypename").val();
            var plantotalPrice = $("#plantotalPrice").val();
            var planunitPrice = $("#planunitPrice").val();
            var planArea = $("#planArea").val();
            var priority = $("#priority").val();
            var demo = $("#demo").val();
            if(plantotalPrice==""){
                plantotalPrice="0";
                $("#plantotalPrice").val("0");
            }
            if(isNaN(plantotalPrice)){
                alert("意向总价含有非法字符");
                return;
            }
            if(planunitPrice==""){
                planunitPrice="0";
                $("#planunitPrice").val("0");
            }
            if(isNaN(planunitPrice)){
                alert("意向单价含有非法字符");
                return;
            }
            //alert(custIds+":"+planId+":"+termTypeName+":"+hourseid+":"+modelId+":"+paidtypename+":"+plantotalPrice+":"+planunitPrice+":"+planArea+":"+priority+":"+demo);
            $.post(
                url,
                {
                    "custom.custId":custIds,
                    "custPlan.planId":rowplanId,
                    "custPlan.termTypeName":termTypeName,
                    "custPlan.hourseid":hourseid,
                    "custPlan.modelId":modelId,
                    "custPlan.paidtypeid":paidtypename,
                    "custPlan.plantotalPrice":plantotalPrice,
                    "custPlan.planunitPrice":planunitPrice,
                    "custPlan.planArea":planArea,
                    "custPlan.priority":priority,
                    "custPlan.demo":demo
                },
                listPlan,
                "json"
            );
            $('#editBx3').window('close');
        }
        
    </script>
</head>
<body>

<s:form name="form1" method="post" action="cusAction!list.action" theme="simple">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" BACKGROUND="images/maintitle.jpg" class="tablelist2">
    <tr  class="tableselect2">
    <td align="right">
    <div class="tools">
        项目名称:<s:select list="#request.prolist" name="custom.projectid" listKey="projectId" listValue="projectName"></s:select>
        客户名称:<s:textfield name="custom.custname" ></s:textfield>
        客户类型:<s:select list="#request.custstateList" name="custom.custstate" listKey="custStateName" listValue="custStateName"></s:select>
             <s:submit value=" 查 询 "></s:submit>
       <%-- <s:hidden name="houseid" id="houseid" value="${houseid}"/>--%>
        <input type="button" name="btnSelCust" id="btnSelCust" value="选择购房客户,进入下一步" onclick="selCust();">
    </div>
    </td>
    </tr>
    </table>
</s:form>
<table class="easyui-datagrid" pagination="true"  id="changesize" style="height:50px"  singleSelect="false"  data-options="onClickRow:getRowData"
title="客户信息">

    <thead>
    <tr><a href="javascript:void(0);" onclick="add();"><span><img src="<%=basePath%>/images/add.gif"></span>添加</a></tr>
    <tr>

        <th field="custId" width=100 align="center">客户编号</th>
        <th field="custname" width=120 align="center">客户姓名</th>
        <th field="mobile" width=150 align="center">手机号码</th>
        <th field="saleperson" width=120 align="center">主管业务员</th>
        <th field="signdate" width=140 align="center">注册时间</th>
        <th field="opr" width=120 align="center">操作</th>
    </tr>
    </thead>
    <tbody>
    <s:iterator value="#request.cusList" var="d">
        <tr>

            <td>${d[1].custId}</td>
            <td>${d[1].custname}</td>
            <td>${d[1].mobile}</td>
            <td>${d[1].saleperson}  </td>
            <td>${d[1].signdate}</td>
            <td>
                <span><img src="<%=basePath%>/images/del.gif"></span>
                <a href="<%=basePath%>/cusAction!del.action?custom.custId=${d[1].custId}" onclick="return confirm('确认删除');">删除</a>
                <span><img src="<%=basePath%>/images/edit.gif"></span>
                <a href="<%=basePath%>/cusAction!update.action?custom.custId=${d[1].custId}">修改</a>
            </td>
        </tr>
    </s:iterator>
    </tbody>
</table>

<div id="tt" class="easyui-tabs" style="height:260px">

<div title="资料"  data-options="closable:false,cache:false" style="padding:0px;">

    <input type="hidden" name="custom.custId" id="custid">
    <table width="100%" align="center"  border="0" cellpadding="0" cellspacing="0" class="easyui-tabs">
       <tr>
              <td align="right">项目名称:</td>
              <td><input type="text" name="proj.projectName" id="projectId"></td>
              <td align="right">客户名称:</td>
              <td><input type="text" name="custom.custname" id="custname"></td>
              <td align="right">拼音代码</td>
              <td><input type="text" name="custom.custnamecode" id="custnamecode"></td>
              <td align="right">客户性质:</td>
              <td><input type="text" name="custom.custtype" id="custtype"></td>
       </tr>
        <tr>
             <td align="right">证件名称:</td>
             <td><input type="text" name="custom.cardname" id="cardname"></td>
             <td align="right">证件号码:</td>
             <td><input type="text" name="custom.cardno" id="cardno"></td>
             <td align="right">文化程度:</td>
             <td><input type="text" name="custom.degree" id="degree"></td>
             <td align="right">客户类型:</td>
             <td><input type="text" name="custom.custstate" id="custstate"></td>
        </tr>
        <tr>
            <td align="right">信息来源:</td>
            <td><input type="text" name="custom.activeid" id="activeid"></td>
            <td align="right">成交几率:</td>
            <td><input type="text" name="custom.salePercent" id="salePercent"></td>
            <td align="right">机会阶段:</td>
            <td><input type="text" name="custom.chance" id="chance"></td>
            <td align="right">事件类型:</td>
            <td><input type="text" name="custom.eventtypeid" id="eventtypeid"></td>
        </tr>
        <tr>
            <td align="right">电子邮件:</td>
            <td><input type="text" name="custom.email" id="email"></td>
            <td align="right">业务员:</td>
            <td><input type="text" name="custom.saleperson" id="saleperson"></td>
            <td align="right">出生日期:</td>
            <td><input type="text" name="custom.birthday" id="birthday"></td>
            <td align="right">手机号码:</td>
            <td><input type="text" name="custom.mobile" id="mobile"></td>
        </tr>
        <tr>
            <td align="right">国籍:</td>
            <td><input type="text" name="custom.country" id="country"></td>
            <td align="right">户籍:</td>
            <td><input type="text" name="custom.nation" id="nation"></td>
            <td align="right">籍贯:</td>
            <td><input type="text" name="custom.babyaddr" id="babyaddr"></td>
            <td align="right">主管业务员:</td>
            <td><input type="text" name="custom.chargeperson" id="chargeperson"></td>
        </tr>
        <tr>
            <td align="right">性别:</td>
            <td><input type="text" name="custom.sex" id="sex"></td>
            <td align="right">联系地址:</td>
            <td colspan="6"><input type="text" name="custom.addr" id="addr"></td>
        </tr>

    </table>


</div>


<div title="事件" data-options="closable:false,cache:false" style="padding:0px;">
    <div class=newtitle>
        <th>
            <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="newEvent();">新增</a>
        </th>
        <th>
            <a class="easyui-linkbutton" plain="true" icon="icon-edit" href="javascript:void(0);" onclick="getEvent();">修改</a>
        </th>
        <th>
            <a class="easyui-linkbutton" plain="true" icon="icon-cut" href="javascript:void(0);" onclick="delEvent();">删除</a>
        </th>
    </div>
    <table id="eventTable" data-options="onClickRow:test1">

    </table>
</div>
<div title="答卷" data-options="closable:false,cache:false" style="padding:0px;">
    <table id="examTable">

    </table>
</div>
<div title="意向" data-options="closable:false,cache:false" style="padding:0px;">
        <div class=newtitle>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="newPlan();">新增</a>
            </th>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-edit" href="javascript:void(0);" onclick="getPlan();">修改</a>
            </th>
            <th>
                <a class="easyui-linkbutton" plain="true" icon="icon-cut" href="javascript:void(0);" onclick="delPlan();">删除</a>
            </th>
        </div>
        <table id="planTable" data-options="onClickRow:test2">

        </table>
</div>

<div title="房产" data-options="closable:false,cache:false" style="padding:0px;">
    <table id="houseTable">

    </table>
</div>
</div>
<div class="easyui-window" title="客户事件" id="editBx2"
     style="width: 550px; height: 250px;" mode="true" closed="true"
     href="<%=basePath%>/custEvent!add.action">
</div>
<div class="easyui-window" title="调研试卷" id="editBx4"
     style="width: 840px; height:530px;" mode="true" closed="true"
     href="${pageContext.request.contextPath}/selUser.jsp">
</div>
<div class="easyui-window" title="客户意向" id="editBx3"
     style="width: 550px; height: 300px;" mode="true" closed="true"
     href="${pageContext.request.contextPath}/custPlanAction!list.action">
</div>


</body>
</html>
