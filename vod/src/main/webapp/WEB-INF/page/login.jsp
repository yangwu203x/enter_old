<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link href="<%=basePath%>/static/h-ui/css/H-ui.min.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>/static/h-ui.admin/css/style.css" rel="stylesheet"
	type="text/css" />
<!-- 图标样式的css -->
<link href="<%=basePath%>/static/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
	type="text/css" />
<!-- 滑块解锁的css -->
<link href="<%=basePath%>/static/css/slide-unlock.css" rel="stylesheet"
	type="text/css" />
<title>vod 后台登录</title>
<meta name="keywords"
	content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description"
	content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
	<div class="header"></div>
	<div class="loginWraper">
		<div id="loginform" class="loginBox">
			<form class="form form-horizontal" action="<%=basePath%>/account/login.do" method="post" onsubmit="return validate()">
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
					<div class="formControls col-xs-8">
						<input id="account" name="account" type="text" placeholder="账户"
							class="input-text size-L" value="admin">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
					<div class="formControls col-xs-8">
						<input id="password" name="password" type="password" placeholder="密码"
							class="input-text size-L" value="123456">
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
 						<div id="slider">
   							<div id="slider_bg"></div>
   							<span id="label">>></span> 
   							<span id="labelTip">拖动滑块验证</span>
   						</div>
					</div>
				</div>
				<div class="row cl">
					<div class="formControls col-xs-8 col-xs-offset-3">
						<input  type="submit" class="btn btn-success radius size-L"
							value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;"> 
						<input id="reset" type="button" class="btn btn-default radius size-L"
							value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="footer">Copyright 深圳市音特电子</div>
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.js"></script>
	<!-- 滑块解锁js -->
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery.slideunlock.js"></script>
	<!-- 弹出层js -->
	<script type="text/javascript" src="<%=basePath%>/static/layer/layer.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/login.js"></script>
	
</body>
</html>