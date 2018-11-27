<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="bean.UsersVo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

UsersVo user = (UsersVo)session.getAttribute("userinfo");
if(user==null){
	//未登录，进入登录页面
	//response.sendRedirect("../login/login.jsp");
}

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">	
<head id="Head1">
    <title>主界面</title>
    <link href="<%=basePath %>main/css/default.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath %>main/js/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>main/js/themes/icon.css"/>
    <script type="text/javascript" src="<%=basePath %>main/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>main/js/jQuery.easyui.js"></script>

	<script type="text/javascript" src='<%=basePath %>main/js/outlook2.js'> </script>

    <script type="text/javascript">
	 var _menus = {"menus":[
						{"menuid":"1","icon":"icon-sys","menuname":"系统管理",
							"menus":[{"menuname":"部门管理","icon":"icon-nav","url":"../DepServlet"},
									{"menuname":"岗位管理","icon":"icon-add","url":"../degreesServlet"},
									{"menuname":"单位管理","icon":"icon-users","url":"../UnitServlet"}
								]
						},{"menuid":"8","icon":"icon-sys","menuname":"员工管理",
							"menus":[{"menuname":"员工列表","icon":"icon-users","url":"../usersServlet?action=userlist"}
								]
						},{"menuid":"56","icon":"icon-sys","menuname":"商城管理",
							"menus":[{"menuname":"供应商管理","icon":"icon-nav","url":"../SupplierServlet"},
									{"menuname":"商品列表","icon":"icon-nav","url":"../ProductServlet?action=list"}
								]
						},{"menuid":"28","icon":"icon-sys","menuname":"客户管理",
							"menus":[{"menuname":"客户资料","icon":"icon-nav","url":"../customerServlet?action=list"},
									{"menuname":"客户联系","icon":"icon-nav","url":"../contactServlet"},
									{"menuname":"合同管理","icon":"icon-nav","url":"../ContractServlet"},
									{"menuname":"订单管理","icon":"icon-nav","url":"../OrdersServlet"}
								]
						},{"menuid":"28","icon":"icon-sys","menuname":"销售管理",
							"menus":[{"menuname":"收款管理","icon":"icon-nav","url":"../financeServlet?action=list"},
									{"menuname":"收款方式","icon":"icon-nav","url":"../PaidTypeServlet?action=list"},
									{"menuname":"派工管理","icon":"icon-nav","url":"../jobServlet?action=list"}
								]
						}
				]};
        //设置登录窗口
        function openPwd() {
            $('#w').window({
                title: '修改密码',
                width: 300,
                modal: true,
                shadow: true,
                closed: true,
                height: 160,
                resizable:false
            });
        }
        //关闭登录窗口
        function close() {
            $('#w').window('close');
        }

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
        }

        $(function() {

            openPwd();
            //
            $('#editpass').click(function() {
                $('#w').window('open');
            });

            $('#btnEp').click(function() {
                serverLogin();
            })

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
                    if (r) {
                        location.href = '/CRM/login/login.jsp';
                    }
                });

            })
        });
    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 疯狂秀才 <a href="#" id="editpass">修改密码</a> <a href="#" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="images/blocks.gif" width="20" height="20" align="absmiddle" /> 宏图it站www.hongtu.com</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; "></div>
    <div region="west" split="true" title="导航菜单" style="width:180px;" id="west">
<div class="easyui-accordion" fit="true" border="false">
		<!--  导航内容 -->
				
			</div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" id="home">
				
			<h1>Welcome to jQuery UI!</h1>

			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
        maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
        <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="Password" class="txt01" /></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="Password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >确定</a> 
                <a class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="closeLogin()">取消</a>
            </div>
        </div>
    </div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
</body>
</html>