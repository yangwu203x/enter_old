<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<article class="page-container">
			<form action="<%=basePath%>/account/${requestUrl}.do" method="post" class="form form-horizontal" id="edit_form" onsubmit="return accountForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${account.account}" id="account" name="account">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>账户角色：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<span class="select-box">
							<select class="select" size="1" name="user_role" id="user_role">
								<option value="-1">请选择</option>
								<c:forEach items="${roles}" var="role">
									<option <c:if test="${account.user_role==role.role_id}">selected</c:if> value="${role.role_id}">${role.role_name}</option>
								</c:forEach>
							</select>
						</span>
					</div>
				</div>
				
				<c:if test="${account!=null}">
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text" name="oldPassword" id="oldPassword">
						</div>
					</div>
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text" name="password" id="password">
						</div>
					</div>
				</c:if>
				<c:if test="${account==null}">
					<div class="row cl">
						<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>密码：</label>
						<div class="formControls col-xs-8 col-sm-9">
							<input type="password" class="input-text"  id="password" name="password">
						</div>
					</div>
				</c:if>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
					</div>
				</div>
			</form>
		</article>
	</body>
	<!-- h-ui的js -->
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!-- 弹出层js -->
	<script type="text/javascript" src="<%=basePath%>/static/layer/layer.js"></script>
	<!-- 美化表单元素的js -->
	<script type="text/javascript" src="<%=basePath%>/static/icheck/jquery.icheck.min.js"></script>
	<!-- 表单验证的js -->
	<script type="text/javascript" src="<%=basePath%>/static/jquery.validation/1.14.0/jquery.validate.min.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/static/jquery.validation/1.14.0/validate-methods.js"></script> 
	<script type="text/javascript" src="<%=basePath%>/static/jquery.validation/1.14.0/messages_zh.min.js"></script>
	
	<script type="text/javascript">
		
		function accountForm(){
			var status=false;
			var accountNo='${account.accountNo}';
			//保存请求的数据
			var requestData=null;
			//获取账户名
			var account=$("#account").val();
			if(account==null || account==""){
				layer.msg("请输入账号!");
				return false;
			}
			
			var user_role=$("#user_role").val();
			if(user_role==null || user_role==""||user_role==-1){
				layer.msg("请选择用户角色!");
				return false;
			}
			//账户密码
			var password=$("#password").val();
			//原来的密码
			var oldPassword=null;
			//如果不存在账号则是新增,否则的话是修改
			if(accountNo!=''){
				oldPassword=$("#oldPassword").val();
				if(oldPassword==null ||oldPassword==""){
					layer.msg("请输入原密码!");
					return false;
				}
				if(password==null || password==""){
					layer.msg("请输入新密码!");
					return false;
				}
				requestData={'accountNo':accountNo,'account':account,'password':password,'oldPassword':oldPassword,"user_role":user_role};
			}else{
				if(password==null || password==""){
					layer.msg("请输入密码!");
					return false;
				}
				requestData={'account':account,'password':password,"user_role":user_role};
			}
			
			//获取请求路径
			var form=$("#edit_form");
			var url=form.attr("action");
			$.ajax({ 
				url: url, 
				data:requestData,
				type:"post",
				async:false,
				success: function(result){
					status=result;
		      	}
			});
			if(status){
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}else{
				if(accountNo!=''){
					layer.msg("修改失败!,可能是原密码输入不正确!");
				}else{
					layer.msg("新增失败!,请联系管理员");
				}
				return false;
			}
			
		}
		$(function(){
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
		});
	</script>  
</html>