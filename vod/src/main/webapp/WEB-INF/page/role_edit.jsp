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
			<form action="<%=basePath%>/role/${requestUrl}.do" method="post" class="form form-horizontal" id="edit_form" onsubmit="return requestForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${requestScope.role.role_name}" 
							id="role_name" name="role_name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">角色权限:</label>
					<div class="formControls col-xs-8 col-sm-9">
					  	<input type="checkbox" id="role_add" <c:if test="${requestScope.role.add=='1'}">checked</c:if> value="1">
					    <label for="role_add">增加</label>&nbsp;&nbsp;
					    <input type="checkbox" id="role_del" <c:if test="${requestScope.role.del=='2'}">checked</c:if> value="2">
					    <label for="role_del">删除</label>&nbsp;&nbsp;
					    <input type="checkbox" id="role_update" <c:if test="${requestScope.role.update=='3'}">checked</c:if> value="3">
					    <label for="role_update">修改</label>&nbsp;&nbsp;
					    <input type="checkbox" id="role_check" <c:if test="${requestScope.role.check=='4'}">checked</c:if> value="4">
					    <label for="role_check">审核</label>
					</div>
				</div>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input type="hidden" id="role_id" value="${requestScope.role.role_id}">
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
		function requestForm(){
			var status=false;
			//封装请求数据对象
			var requestData=null;
			var roleName=$("#role_name").val();
			if(role_name==null||role_name==''){
				layer.msg("请输入角色名!");
				return false;
			}
			var rolePrivilege="";
			if($("#role_add")[0].checked==true){
				rolePrivilege+=$("#role_add").val()+",";
			}
			if($("#role_del")[0].checked==true){
				rolePrivilege+=$("#role_del").val()+",";
			}
			if($("#role_update")[0].checked==true){
				rolePrivilege+=$("#role_update").val()+",";
			}
			if($("#role_check")[0].checked==true){
				rolePrivilege+=$("#role_check").val()+",";
			}
			var roleId=$("#role_id").val();
			//如果不存在编号则是新增,否则的话是修改
			if(roleId!=''){
				requestData={"role_id":roleId,"role_name":roleName,"role_privilege":rolePrivilege};
			}else{
				requestData={"role_name":roleName,"role_privilege":rolePrivilege};
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
				if(roleId!=''){
					layer.msg("修改失败!");
				}else{
					layer.msg("增加失败!");
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