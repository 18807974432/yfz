<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: rainbow
  Date: 2018/11/5
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="IncludeJS.jsp"></jsp:include>
    <script type="text/javascript">

            function mypage(){
            var pager = $(".easyui-datagrid").datagrid('getPager');
            $(pager).pagination({
                total:${pager.totalRows},
                pageSize:${pager.pageRow},
                displayMsg:'{from}-{to}/{total}',
                pageNumber:${pager.cur_page},
                afterPageText:'页，共{pages}页',
                pageList:[50,100],
                loading:false,
                showPageList:true,
                showRefresh:true,
                //刷新方法
                onBeforeRefresh:function(p,s){
                    location.href="${pageContext.request.contextPath }/Eamil!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                },
                onSelectPage:function(p,s){
                    location.href="${pageContext.request.contextPath }/Eamil!list.action?pager.cur_page="+p+"&pager.pageRow="+s;
                }

            });
            $('#changesize').datagrid('resize', {
                height:document.body.clientHeight-74,
                width:document.body.clientWidth-1
            });
            }
            function page() {
                

            var pager1 = $(".easyui-datagrid").datagrid('getPager1');
            $(pager1).pagination({
                total:${pager1.totalRows},
                pageSize:${pager1.pageRow},
                displayMsg:'{from}-{to}/{total}',
                pageNumber:${pager1.cur_page},
                afterPageText:'页，共{pages}页',
                pageList:[50,100],
                loading:false,
                showPageList:true,
                showRefresh:true,
                //刷新方法
                onBeforeRefresh:function(p,s){
                    location.href="${pageContext.request.contextPath }/Eamil!list.action?pager1.cur_page="+p+"&pager1.pageRow="+s;
                },
                onSelectPage:function(p,s){
                    location.href="${pageContext.request.contextPath }/Eamil!list.action?pager1.cur_page="+p+"&pager1.pageRow="+s;
                }

            });
            $('#changesize').datagrid('resize', {
                height:document.body.clientHeight-74,
                width:document.body.clientWidth-1
            });
            }

        function save(){
            document.forms[1].submit();
        }
        function add(){
            var url="${pageContext.request.contextPath }/Eamil!add.action";
            //var title="区域新增";
            //addTab1(title, url);
            window.location.href=url;

        }
        function test(value,rec){
            var id= rec.emailid;
            var url = "${pageContext.request.contextPath }/Eamil!getjson.action";
            $.post(
                url,
                {
                    "email.emailid":id
                },
                updemp,
                "json"
            );
        }
        function updemp(data) {
          $("#topics").text(data.email.topic);
          $("#contents").text(data.email.content);
            
        }

        if("${msg}"!=null&&"${msg}".length>0){
            alert("${msg}");
        }
    </script>
</head>
<body>
<div id="tt" class="easyui-tabs"  style="height:402px"   data-options="tools:'#tab-tools'">
    <div title="收到的邮件" data-options="closable:false,cache:false" style="padding:0px;" onclick="mypage();">
        <table id="recemail" class="easyui-datagrid" pagination="true" style="height:370px;"  singleSelect="true"  data-options="onClickRow:test" >
            <thead>
            <tr>
                <th field="emailid">编号</th>
                <th field="userid">发送人</th>
                <th field="topic">标题</th>
                <th field="sendtime">发送时间</th>
                <th field="isRead">是否已读</th>
                <th field="opt" align="center">操作</th>
            </tr>
            </thead>
            <tbody>
             <s:iterator value="#request.emailList1" var="d">
                 <tr>
                     <td>${d.emailid}</td>
                     <td>${d.userid}</td>
                     <td>${d.topic}</td>
                     <td>${d.sendtime}</td>
                     <td>${d.isRead}</td>
                     <td>
                         <a class="easyui-linkbutton" plain="true" icon="icon-cut"
                            href="${pageContext.request.contextPath }/Eamil!del.action?email.emailid=${d.emailid}" onclick="return confirm('删除确认');">删除</a>
                         <a class="easyui-linkbutton" plain="true" icon="icon-edit"
                            href="${pageContext.request.contextPath }/Eamil!read.action?email.emailid=${d.emailid}">标记已读</a>
                     </td>
                 </tr>

             </s:iterator>

            </tbody>
        </table>
    </div>
    <div title="发送的邮件" data-options="closable:false,cache:false" style="padding:0px;" onclick="page();">
        <table id="sendemail"  class="easyui-datagrid" pagination="true" style="height:370px;"  singleSelect="true"  data-options="onClickRow:test">
            <thead>
               <tr>
                   <th field="emailid">编号</th>
                   <th field="receid">接收人</th>
                   <th field="topic">标题</th>
                   <th field="sendtime">发送时间</th>
                   <th field="isRead">是否已读</th>
                   <th field="opt" align="center">操作</th>
               </tr>
            </thead>
            <tbody>
               <s:iterator value="#request.emailList" var="e">
                   <tr>
                       <td>${e.emailid}</td>
                       <td>${e.receid}</td>
                       <td>${e.topic}</td>
                       <td>${e.sendtime}</td>
                       <td>${e.isRead}</td>
                       <td>
                           <a class="easyui-linkbutton" plain="true" icon="icon-cut"
                              href="${pageContext.request.contextPath }/Eamil!del.action?email.emailid=${e.emailid}" onclick="return confirm('删除确认');">删除</a>
                       </td>
                   </tr>
               </s:iterator>
            </tbody>
        </table>
    </div>

</div>
<div id="tab-tools">
    <span><img src="<%=basePath%>/images/add.gif"></span><a href="#" class="easyui-linkbutton" data-options="plain:true"  onclick="add();">写新邮件</a>
</div>
<table width="100%" align="center" height="150" border="1" cellpadding="0" cellspacing="0">
    <tr height="30">
        <td align="right" width="50"><b><font color="red">标题：</font></b></td>
        <td id="topics"></td>
    </tr>
    <tr>
        <td align="right"><b>内容：</b></td>
        <td id="contents" valign="top">&nbsp;</td>
    </tr>
</table>
</body>
</html>
