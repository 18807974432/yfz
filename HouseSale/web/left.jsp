<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/chili-1.7.pack.js"></script>
<script type="text/javascript" src="js/jquery.easing.js"></script>
<script type="text/javascript" src="js/jquery.dimensions.js"></script>
<script type="text/javascript" src="js/jquery.accordion.js"></script>
<script language="javascript">
	jQuery().ready(function(){
		jQuery('#navigation').accordion({
			header: '.head',
			navigation1: true, 
			event: 'click',
			fillSpace: true,
			animated: 'bounceslide'
		});
	});
</script>
<style type="text/css">
<!--
body {
	margin:0px;
	padding:0px;
	font-size: 12px;
}
#navigation {
	margin:0px;
	padding:0px;
	width:147px;
}
#navigation a.head {
	cursor:pointer;
	background:url(images/main_34.gif) no-repeat scroll;
	display:block;
	font-weight:bold;
	margin:0px;
	padding:5px 0 5px;
	text-align:center;
	font-size:12px;
	text-decoration:none;
}
#navigation ul {
	border-width:0px;
	margin:0px;
	padding:0px;
	text-indent:0px;
}
#navigation li {
	list-style:none; display:inline;
}
#navigation li li a {
	display:block;
	font-size:12px;
	text-decoration: none;
	text-align:center;
	padding:3px;
}
#navigation li li a:hover {
	background:url(images/tab_bg.gif) repeat-x;
		border:solid 1px #adb9c2;
}
-->
</style>
</head>
<body>
<div  style="height:100%;">
  <ul id="navigation">
    <li> <a class="head">我的主页</a>
      <ul>
        <li><a href="Eamil!list.action" target="rightFrame">我的邮件</a></li>
        <li><a href="myAccess.jsp" target="rightFrame">我的权限</a></li>
      </ul>
    </li>
    <li> <a class="head">客户管理</a>
      <ul>
        <li><a href="cusAction!list.action" target="rightFrame">客户台账</a></li>
        <li><a href="Eventtype!list.action" target="rightFrame">事件类型</a></li>
      </ul>
    </li>
    <li> <a class="head">销售管理</a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/guideAction!guide.action" target="rightFrame">售楼指引</a></li>
        <li><a href="comments.php" target="rightFrame">查看/删除评论</a></li>
      </ul>
    </li>
    <li> <a class="head">财务管理</a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/discountsAction!list.action" target="rightFrame">优惠管理</a></li>
        <li><a href="BuyHourse.jsp" target="rightFrame">诚意金管理</a></li>
        <li><a href="" target="rightFrame">收款管理</a></li>
        <li><a href="" target="rightFrame">佣金管理</a></li>
        <li><a href="PaidType.jsp" target="rightFrame">付款方式</a></li>
        <li><a href="PaidScheme.jsp" target="rightFrame">收款方案</a></li>
        <li><a href="PaidSort.jsp" target="rightFrame">收款类别</a></li>
        <li><a href="Bank.jsp" target="rightFrame">银行资料</a></li>
        <li><a href="BankCompanyAction!addinit.action" target="rightFrame">公司账户</a></li>
        <li><a href="Ticket.jsp" target="rightFrame">票据管理</a></li>

      </ul>
    </li>
    <li> <a class="head">营销管理</a>
      <ul>
        <li><a href="researchAction!list.action" target="rightFrame">调研试卷</a></li>
      </ul>
    </li>
	 <li> <a class="head">房产管理</a>
      <ul>
        <li><a href="projectInfo.jsp" target="rightFrame">项目资料</a></li>
        <li><a href="houseModel.jsp" target="rightFrame">户型资料</a></li>
        <li><a href="TermInfoAction!listProject.action" target="rightFrame">楼栋资料</a></li>
        <li><a href="HourseInfoAction!list.action" target="rightFrame">房产管理</a></li>
      </ul>
    </li>
	 <li> <a class="head">报表管理</a>
      <ul>
        <li><a href="AddLink.php" target="rightFrame">添加友情链接</a></li>
        <li><a href="Links.php" target="rightFrame">查看/修改友情链接</a></li>
      </ul>
    </li>
		 <li> <a class="head">用户管理</a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/jobsAction!list.action"target="rightFrame">职务管理</a></li>
        <li><a href="${pageContext.request.contextPath}/depAction!list.action"target="rightFrame">部门管理</a></li>
        <li><a href="${pageContext.request.contextPath}/usersAction!list.action"target="rightFrame">用户管理</a></li>

      </ul>
    </li>
		 <li> <a class="head">安全管理</a>
      <ul>
        <li><a href="${pageContext.request.contextPath}/accAction!list.action" target="rightFrame">权限设置</a></li>
        <li><a href="${pageContext.request.contextPath}/usersAction!userlog.action" target="rightFrame">查看日志</a></li>
      </ul>
    </li>
  </ul>
</div>
</body>
</html>
