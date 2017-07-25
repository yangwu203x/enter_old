<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!-- h-ui的css -->
		<link href="<%=basePath%>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
		<link  href="<%=basePath%>/static/h-ui.admin/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
		<!-- 图标样式的css -->
		<link href="<%=basePath%>/static/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
		<!-- 美化表单元素的css -->
		<link  href="<%=basePath%>/static/icheck/icheck.css" rel="stylesheet" type="text/css"/>
	</head>
	<body>
		<header class="navbar-wrapper">
			<div class="navbar navbar-black">
				<div class="container-fluid cl"> <!-- <a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">H-ui.admin</a>  
				<a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">H-ui</a> -->
				<span class="logo navbar-slogan f-l mr-10 hidden-xs"> </span> 
				<!--<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>-->
					<%-- <nav class="nav navbar-nav">
						<ul class="cl">
							<li class="dropDown dropDown_hover"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
								<ul class="dropDown-menu menu radius box-shadow">
									<li><a href="javascript:;" onclick="account_add('添加账户','<%=basePath%>/account/showAccountEdit.do','','510')"><i class="Hui-iconfont">&#xe60d;</i>账户</a></li>
								</ul>
							</li>
						</ul>
					</nav> --%>
					<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
						<ul class="cl">
							<li>${sessionScope.role.role_name}</li>
							<li class="dropDown dropDown_hover"> <a href="#" class="dropDown_A">${sessionUser.account}<i class="Hui-iconfont">&#xe6d5;</i></a>
								<ul class="dropDown-menu menu radius box-shadow">
									<!-- <li><a href="javascript:;" >个人信息</a></li> -->
									<li><a href="<%=basePath%>/account/cancelAccount.do">退出</a></li>
								</ul>
							</li>
							
							<li id="Hui-skin" class="dropDown right dropDown_hover"> <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
								<ul class="dropDown-menu menu radius box-shadow">
									<li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
									<li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
									<li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
									<li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
									<li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
									<li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		<aside class="Hui-aside">
			<input runat="server" id="divScrollValue" type="hidden" value="" />
			<div class="menu_dropdown bk_2">
				<c:forEach items="${menuList}" var="menu">
					<dl>
						<dt>
							<i class="Hui-iconfont" style="font-size:20px;">${menu.menuIcon}</i>&nbsp;&nbsp;${menu.menuName}<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
						</dt>
						<dd>
							<ul>
								<c:forEach items="${menu.subMenuList}" var="subMenu">
									<li><a _href="<%=basePath%>/${subMenu.menuUrl}" data-title="${subMenu.menuName}" href="javascript:void(0)">${subMenu.menuName}</a></li>
								</c:forEach>
							</ul>
						</dd>
					</dl>
				</c:forEach>
			</div>
		</aside>
		<div class="dislpayArrow hidden-xs"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
		<section class="Hui-article-box">
			<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
				<div class="Hui-tabNav-wp">
					<ul id="min_title_list" class="acrossTab cl">
						<li class="active"><span title="我的桌面" data-href="<%=basePath%>/page/right_index.jsp">我的桌面</span><em></em></li>
					</ul>
				</div>
				<div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
			</div>
			<div id="iframe_box" class="Hui-article">
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<iframe scrolling="yes" frameborder="0" src="<%=basePath%>/page/right_index.jsp" id="index_frame"></iframe>
				</div>
			</div>
		</section>
	</body>
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery.min.js"></script>
	<!-- 弹出层js -->
	<script type="text/javascript" src="<%=basePath%>/static/layer/layer.js"></script>
	<!-- h-ui的js -->
	<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	
	
	<script type="text/javascript">
		/*账户-添加*/
		function account_add(title,url,w,h){
			url+="?viewName=account_edit&operateType=1";
			if (w == null || w == '') {
				w=800;
			};
			var executeFlag=true;
			parent.layer.open({
				type: 2,
				area: [w+'px', h +'px'],
				fix: false, //不固定
				maxmin: true,
				shade:0.4,
				title: title,
				content: url,
				cancel:function(){
					executeFlag=false;
				},
				end: function () {
					if(executeFlag) $("#index_frame").attr("src","<%=basePath%>/account/accounts.do")
		        }
			});
		}
	</script>
</html>