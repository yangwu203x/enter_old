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
			<form action="<%=basePath%>/newSong/${requestUrl}.do" method="post" class="form form-horizontal" id="edit_form" onsubmit="return requestForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>新歌名称:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${newSong.songName}" 
							id="song_name" name="song_name">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>对应歌曲编号：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="song_id">
						<option value="-1" selected>请选择</option>
						<c:if test="${newSong.newSongId!=null}">
							<option value="${newSong.newSongId}" selected>${newSong.newSongId}</option>
						</c:if>
						</select>
						</span> </div>
				</div>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input type="hidden" id="new_id" value="${newSong.newId}">
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
			var songId=$("#song_id").val();
			if(songId==null||songId==''){
				layer.msg("请在输入歌曲名后选择歌曲编号!");
				return false;
			}
			var newId=$("#new_id").val();
			//如果不存在编号则是新增,否则的话是修改
			if(newId!=''){
				requestData={"songId":songId,"newId":newId};
			}else{
				requestData={"songId":songId};
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
				return false;
			}
		}
		$(function(){
			//对歌曲的输入加以筛选
			$("#song_name").on("keyup",function(){
				var requestUrl="<%=basePath%>/song/obtainIdByName.do";
				var songName=$(this).val();
				if(songName==""||songName==" "){
					return;
				}
				var requestData={"songName":songName};
				$.ajax({
					url:requestUrl,
					data:requestData,
					type:"POST",
					success:function(result){
						var songIdNode=$("#song_id");
						var songIdHtml="";
						for ( var i in result) {
							songIdHtml+="<option value='"+result[i]+"'>"+result[i]+"</option>";
						}
						songIdNode.html(songIdHtml);
					}
				});
			});
			$('.skin-minimal input').iCheck({
				checkboxClass: 'icheckbox-blue',
				radioClass: 'iradio-blue',
				increaseArea: '20%'
			});
		});
	</script>  
</html>