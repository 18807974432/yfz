<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/10/24
  Time: 20:19
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
   <jsp:include page="IncludeJS.jsp" flush="true"/>
    <title>事件类型资料</title>
    <script type="text/javascript">
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
                    location.href="${pageContext.request.contextPath }/Eventtype!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                },
                onSelectPage:function(p,s){
                    location.href="${pageContext.request.contextPath }/Eventtype!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                }
            });
            $('#changesize').datagrid('resize', {
                height:document.body.clientHeight-100
            });
        });

        //单击某一行时会触发行的onClickRow,value是行索引，rec代表当前选中的行,包含了所有字段的值
        function getRowData(value,rec){
            var id= rec.eventtypeId;
            //alert(rec.depname);
            //alert(id);
            var url="<%=basePath%>/Eventtype!getjson.action"
            $.post(
                url,
                    {
                     "eventType.eventtypeId":id
                    },
                umpdate,
                "json"
            );

        }
        function umpdate(data) {
            /*alert("成功");*/
            $("#eventtypename").val(data.eventlist.eventtypename);


        }
          function add(){
            $("#eventtypeId").val("0");
            $("#eventtypename").val("");

          }
          function save() {
              document.forms[0].submit();


          }


        if("${msg}"!=null&&"${msg}".length>0){
            alert("${msg}");
        }
    </script>
</head>
<body>

        <table class="easyui-datagrid" pagination="true"  id="changesize" style="height:50px"  singleSelect="false" data-options="onClickRow:getRowData">
            <thead>
            <tr>

                <th field="eventtypeId" align="center">事件类型编号</th>
                <th field="eventtypename" align="center">事件类型名称</th>
                <th field="opr" align="center">操作</th>

            </tr>
            </thead>
            <tbody>
            <s:iterator value="#request.eventTypeList" var="d">
               <tr>
                <td>${d.eventtypeId}</td>
                <td>${d.eventtypename}</td>
                <td><span><img src="<%=basePath%>/images/del.gif"></span><a href="<%=basePath%>Eventtype!del.action?eventType.eventtypeId=${d.eventtypeId}" onclick="return confirm('确认删除');"><font color="red">删除</font></a></td>
               </tr>
            </s:iterator>
            </tbody>

        </table>
        <div id="tt" class="easyui-tabs"  style="height:230px">
            <div title="事件类型资料" data-options="closable:false,cache:false" style="padding:0px;">
                <div id="newclass">
                    <th>
                        <a class="easyui-linkbutton" icon="icon-add" href="javascript:void(0);" onclick="add();">增加</a>
                    </th>
                    <th>
                        <a class="easyui-linkbutton" icon="icon-save" href="javascript:void(0);" onclick="save();">保存</a>
                    </th>

                </div>
               <form id="eventAction" name="eventaction" action="Eventtype!save.action" method="post">
                   <input type="hidden" name="eventType.eventtypeId" id="eventtypeId"/>
                   <table>

                       <tr>
                           <td align="center">事件类型名称:</td>
                           <td><input type="text" name="eventType.eventtypename" id="eventtypename"/></td>
                       </tr>
                   </table>
               </form>
            </div>
        </div>

</body>
</html>
