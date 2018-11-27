<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/19 0019
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="/struts-tags" prefix="c" %>
<html>
<head>
    <title>调研答卷信息</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
</head>
<body>
    <script type="text/javascript">
        var j_examid;
        var j_researchid;
        $(function(){
            var pager = $(".easyui-datagrid").datagrid('getPager');
            $(pager).pagination({
                total:${pager.totalRows},
                pageSize:${pager.pageRow},
                displayMsg:'{from}-{to}/{total}',
                pageNumber:${pager.cur_page},
                afterPageText:'页，共{pages}页',
                pageList:[3,5,10,20,50,100],
                loading:false,
                showPageList:true,
                showRefresh:true,
                //刷新方法
                onBeforeRefresh:function(p,s){
                    location.href="${pageContext.request.contextPath }/researchAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                },
                onSelectPage:function(p,s){
                    location.href="${pageContext.request.contextPath }/researchAction!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                }
            });
        });
        //调研答卷资料显示在下面进行修改
        function test(value,rec){
            var id= rec.reseachid;
            var url = "${pageContext.request.contextPath }/researchAction!json.action";
            $.post(
                url,
                {
                    "research.reseachid":id
                },
                updemp,
                "json"
            );
        }
        function test1(value,rec){
            j_examid=rec.examid;
            j_researchid=rec.reseachid;
        }
        //调研答卷信息显示  在下面进行修改
        function updemp(data){

            $("#reseachid").val(data.research.reseachid);
            $("#projectid").val(data.research.proj.projectId);
            $("#examName").val(data.research.examName);
            $("#invalid").val(data.research.invalid);
            $("#description").val(data.research.description);
            $("#orderid").val(data.research.orderid);
            url = "${pageContext.request.contextPath }/researchAction!json2.action";
            var projectid = data.research.reseachid;
            $.post(
                url,
                {
                    "research.reseachid":projectid
                },
                listExam,
                "json"
            );
        }
        //试题明细
        function listExam(data){
            $('#examtable').datagrid({
                height: '160',
                fitColumns: true,
                singleSelect:true,
                columns:[[
                    {field:'examid',title:'编号',width:30},
                    {field:'unitNO',title:'试题序号',width:50},
                    {field:'unitName',title:'题目名称',width:220},
                    {field:'unitType',title:'题目类型',width:50,align:'right'},
                    {field:'optCnt',title:'选项个数',width:50,align:'right'},
                    {field:'question1',title:'选项1',width:70},
                    {field:'question2',title:'选项2',width:70},
                    {field:'question3',title:'选项3',width:70},
                    {field:'question4',title:'选项4',width:70},
                    {field:'question5',title:'选项5',width:70},
                    {field:'question6',title:'选项6',width:70},
                    {field:'question7',title:'选项7',width:70},
                    {field:'question8',title:'选项8',width:70}
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
        function createColumnMenu(){
            var tmenu = $('<div id="tmenu" style="width:100px;"></div>').appendTo('body');
            var fields = $('#examtable').datagrid('getColumnFields');
            for(var i=0; i<fields.length; i++){
                $('<div iconCls="icon-ok"/>').html(fields[i]).appendTo(tmenu);
            }
            tmenu.menu({
                onClick: function(item){
                    if (item.iconCls=='icon-ok'){
                        $('#examtable').datagrid('hideColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-empty'
                        });
                    } else {
                        $('#examtable').datagrid('showColumn', item.text);
                        tmenu.menu('setIcon', {
                            target: item.target,
                            iconCls: 'icon-ok'
                        });
                    }
                }
            });
        }
        //保存
        function save(){
            document.forms[1].submit();
        }
        //新增题目
        function openexam(){
            if($("#reseachid").val()=='') {
                alert("请选择一份答卷！");
                return;
            }
            location.href="${pageContext.request.contextPath }/ExamMasterAdd.jsp?reseachid="+$("#reseachid").val();
        }
        //修改题目
        function openexam1(){
            if($("#reseachid").val()=='') {
                alert("请选择一份答卷！");
                return;
            }
            if(j_examid==null) {
                alert("请选择要修改的试题");
                return;
            }
            location.href="${pageContext.request.contextPath }/researchAction!update.action?exam.examid="+j_examid;
        }
        //删除题目
        function openexam2(){
            if($("#reseachid").val()=='') {
                alert("请选择一份答卷!");
                return;
            }
            if(j_examid==null) {
                alert("请选择要删除的试题!");
                return;
            }
            if(!confirm('删除确认')) {
                return;
            }
            location.href="${pageContext.request.contextPath }/researchAction!delexam.action?exam.examid="+j_examid;

        }
        //新增调研试卷资料
        function add(){
            $("#reseachid").val("");
            $("#examName").val("");
            $("#description").val("");
            $("#orderid").val("1");
        }

        //阅览试卷
        function openExam() {
            var researchid=$("#reseachid").val();
            if(researchid==""){
                alert("请选择一份试卷");
                return;
            }
            var url = "${pageContext.request.contextPath }/researchAction!examlistSample.action?research.reseachid="+researchid;
            $("#editBx1").window(
                {
                    href:url,
                    onClose:function(){
                    }
                });
            $('#editBx1').window('refresh');
            $('#editBx1').window('open');
        }

    </script>

    <DIV id=maintitle>
        <c:form action="researchAction!list" method="post">
            <DIV class=newtitle><STRONG>调研答卷资料</STRONG></DIV>
        </c:form>
    </DIV>
    <table class="easyui-datagrid" pagination="true" style="height:290px"  singleSelect="true"  data-options="onClickRow:test" >
        <thead>
        <tr>
            <th field="reseachid">答卷编号</th>
            <th field="examName">答卷名称</th>
            <th field="projectid">项目名称</th>
            <th field="invalid">是否启用</th>
            <th field="examCount">题目数量</th>
            <th field="userid">出题人</th>
            <th field="oprtime">时间</th>
            <th field="opt">操作</th>
        </tr>
        </thead>
        <tbody>
        <c:iterator  value="pager.datas" status="i" var="d">
            <tr>
                <td>${d.reseachid }</td>
                <%--阅览试卷--%>
                <td><a href="javascript:void(0)" onclick="openExam();">${d.examName}</a></td>

                <td>${d.proj.projectName }</td>
                <td>${d.invalid }</td>
                <td>${d.examCount }</td>
                <td>${d.userid }</td>
                <td>${d.oprtime }</td>
                <td>
                    <a class="easyui-linkbutton" plain="true" icon="icon-cut"
                       href="${pageContext.request.contextPath }/researchAction!del.action?research.reseachid=${d.reseachid}" onclick="return confirm('删除确认');">删除</a>
                </td>
            </tr>
        </c:iterator>
        </tbody>
    </table>
    <div id="tt" class="easyui-tabs"  style="height:230px">
        <div title="调研答卷资料" data-options="closable:false,cache:false" style="padding:0px;">
            <div class=newtitle>
                <th>
                    <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="add();">添加</a>
                </th>
                <th>
                    <a class="easyui-linkbutton" plain="true" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
                </th>
            </div>
            <c:form theme="simple" action="researchAction!save">
                <c:fielderror></c:fielderror>
                <c:hidden name="research.reseachid" id="reseachid"></c:hidden>
                <table width="100%" align="center">
                    <tr>
                        <td align="right">答卷名称:</td>
                        <td colspan="5"><c:textfield name="research.examName" id="examName" size="144"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">项目名称:</td>
                        <td>
                            <c:select list="#request.projdata" name="proj.projectId" listKey="projectId" listValue="projectName" cssStyle="width:152px"></c:select>
                        </td>
                        <td align="right">是否启用:</td>
                        <td>
                            <c:select list="{'启用','禁用'}" name="research.invalid" id="invalid" cssStyle="width:152px"></c:select>
                        </td>
                        <td align="right">序号:</td>
                        <td><c:textfield name="research.orderid" id="orderid" value="1"/><font size="2" color="red">*</font></td>
                    </tr>
                    <tr>
                        <td align="right">说明:</td>
                        <td colspan="5"><c:textfield name="research.description" id="description" size="144"/></td>
                    </tr>

                </table>
            </c:form>
        </div>
        <div title="答卷明细" data-options="closable:false,cache:false" style="padding:0px;">
            <div class=newtitle>
                <th>
                    <a class="easyui-linkbutton" plain="true" icon="icon-add" href="javascript:void(0);" onclick="openexam();">添加</a>
                    <a class="easyui-linkbutton" plain="true" icon="icon-edit" href="javascript:void(0);" onclick="openexam1();">修改</a>
                    <a class="easyui-linkbutton" plain="true" icon="icon-cut" href="javascript:void(0);" onclick="openexam2();">删除</a>
                </th>
                <table id="examtable"   data-options="onClickRow:test1" >
                </table>
            </div>
        </div>
    </div>
    <div class="easyui-window" title="调研试卷" id="editBx1"
         style="width: 840px; height:530px;" mode="true" closed="true"
         href="${pageContext.request.contextPath}examResultSample.jsp">
    </div>

</body>
</html>
