<%@page import="cn.ogsu.vod.entity.Menu"%>
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
		<style type="text/css">
			#menu_icon>i{
				display:inline-block;
				line-height: 50px;
				width:50px;
			}
			#menu_icon>i:hover{
				cursor: pointer;
				
			}
		</style>
	</head>
	<body>
		<article class="page-container">
			<form action="<%=basePath%>/menu/${requestUrl}.do" method="post" class="form form-horizontal" id="menu_add_form" onsubmit="return menuForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">菜单类型：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" name="menuType" id="menu-type">
							<option value="0" selected>请选择菜单类型</option>
							<option  value="1">顶级菜单</option>
							<option value="2">子菜单</option>
						</select>
						</span> </div>
				</div>
				
				
				<div class="row cl" id="parent-menu" style="display: none">
					<label class="form-label col-xs-4 col-sm-3">顶级菜单：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" name="parentMenuNo" id="menu-select">
							<!-- 通过异步加载子菜单 -->
						</select>
						</span> </div>
				</div>
				
				
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单名：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${editMenu.menuName}" id="menuName" name="menuName">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red"></span>菜单路径：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" value="${editMenu.menuUrl}" placeholder="#" id="menuUrl" name="menuUrl">
					</div>
				</div>
				
				<!-- 定制菜单图标 -->
				<div class="row cl" id="row_menu_icon">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>菜单图标：</label>
					<input type="hidden" name="menuIcon" id="menuIcon"/>
					<div class="formControls col-xs-8 col-sm-9" id="show_menu_icon">
						<a class="btn btn-default round" href="javascript:;" onclick="select_menu_icon()">单击选择菜单图标</a>
						<i class="Hui-iconfont" style="font-size:30px;">${editMenu.menuIcon}</i>
					</div>
				</div>
				
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
	
		function select_menu_icon(){
			var menuIcon='<div id="menu_icon" style="font-size:30px;padding-left:20px;">'+
							'<i class="icon Hui-iconfont" onclick=obtain_menu_icon("xe625");>&#xe625;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe67f");>&#xe67f;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe616");>&#xe616;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe613");>&#xe613;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe60f");>&#xe60f;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe64b");>&#xe64b;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe66f");>&#xe66f;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe62e");>&#xe62e;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe633");>&#xe633;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe634");>&#xe634;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe646");>&#xe646;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe681");>&#xe681;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe636");>&#xe636;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe687");>&#xe687;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe637");>&#xe637;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe691");>&#xe691;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe692");>&#xe692;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe639");>&#xe639;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe623");>&#xe623;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe626");>&#xe626;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe63e");>&#xe63e;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe63c");>&#xe63c;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe627");>&#xe627;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe6a4");>&#xe6a4;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe6a5");>&#xe6a5;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe612");>&#xe612;</i>'+
							'<i class="Hui-iconfont" onclick=obtain_menu_icon("xe685");>&#xe685;</i>'+
						'</div>';
			 //在这里面输入任何合法的js语句
	        layer.open({
	            type: 1,
	            area: ['50%', '70%'],
	            title: '图标选择',
	            shade: 0.5,//遮罩透明度
	            shadeClose:true,//点击遮罩关闭层
	            moveType: 1,//拖拽风格，0是默认，1是传统拖动
	            shift: 1,//0-6的动画形式，-1不开启
	            fix:true,//不随滚动条滚动，一直在可视区域。
	            border:[0],
	            content: menuIcon,
	            btn:['确定','取消']
	        });
		}
		
		//显示所选择的图标
		function obtain_menu_icon(menuIcon){
			var iconHtml='<a class="btn btn-default round" href="javascript:;" onclick="select_menu_icon()">单击选择菜单图标</a>&nbsp;&nbsp;'+
			'<i class="Hui-iconfont" style="font-size:30px;">&#'+menuIcon+';</i>';
			
			$("#show_menu_icon").html(iconHtml);
			$("#menuIcon").val(menuIcon);
		}
		//新增子菜单时验证form表单但是不提交表单
		function validateFormForSub()  
		{  
		    return $('#menu_add_form').validate({  
		        rules:{  menuName:'required',menuUrl:'required', },  
		        
		    });  
		}
	
		function menuForm(){
			var status=false;
			//验证菜单类型
			var menuType=$("#menu-type").val();
			if(menuType==0){
				layer.msg("请选择菜单类型!");
				return false;
			}
			
			//获取访问子菜单的url
			var menuUrl=$("#menuUrl").val();
			//获取子菜单的父菜单编号,若不存在父菜单则该菜单为顶级菜单
			var parentMenuNo=$("#menu-select").val();
			if(parentMenuNo==undefined||parentMenuNo==0) parentMenuNo=0;
			if(menuType==2){
				if(parentMenuNo==undefined||parentMenuNo==0){
					layer.msg("请选择父菜单类型!");
					return false;
				}
				if(menuUrl==''){
					layer.msg("请输入子菜单访问的路径!");
					return false;
				}
			}
			
			//获取菜单名并且验证不能为空
			var menuName=$("#menuName").val();
			if(menuName==''){
				layer.msg("菜单名不能为空!");
				return false;
			}
			
			//当添加父级菜单时应选择图标
			var menuIcon=null;
			//封装数据
			var requestData=null;
			if(menuType==1){
				menuIcon=$("#menuIcon").val();
				requestData={"menuName":menuName,"menuIcon":menuIcon}
			}else{
				requestData={"menuUrl":menuUrl,"parentMenuNo":parentMenuNo,"menuName":menuName,"menuIcon":menuIcon}
			}
			
			//获取请求路径
			var form=$("#menu_add_form");
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
				return false;
			}
			
		}
	
		$(function(){
			var menuUrl='${editMenu.menuUrl}';
			var selectNode=$("#menu-type");
			if(menuUrl==''){
				return;
			}
			if(menuUrl=='#'){
				selectNode.val("1");
				$("#menuUrl").attr("readonly","readonly");
			}else{
				selectNode.val("2");
			}
			//顶级菜单数量
			var parentMenus;
			//当菜单类型发生改变时页面元素一起改变
			$("#menu-type").on("change",function(){
				var menuType=$(this).val();
				//当选择子菜单的时候
				if(menuType==2){
					//当不存在顶级菜单的时候
					if(parentMenus.length==0){
						$(this).val("1");
						layer.msg('当前没有顶级菜单,不能增加子菜单!');
						$("#menuUrl").attr("readonly","true");
					}else{
						//追加子菜单选项卡
						$("#menuUrl").removeAttr("readonly");
						$("#row_menu_icon").hide();
						var selectMenuHtml='<option value="0" selected>请选择顶级菜单</option>';
						for ( var index in parentMenus) {
							selectMenuHtml+='<option value="'+parentMenus[index].menuNo+'">'+parentMenus[index].menuName+'</option>';
						}
						$("#menu-select").html(selectMenuHtml);
						$("#parent-menu").show();
					}
				}else{
					$("#menuUrl").attr("readonly","true");
					$("#parent-menu").hide();
					$("#row_menu_icon").show();
				}
			});
			$.ajax({ 
				url: "<%=basePath%>/menu/parentMenu.do", 
				type:"post",
				async:false,
				success: function(result){
					parentMenus=result;
		      	}
			});
			
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
			
		});
		
		
		
		
		
		
	</script>  
</html>