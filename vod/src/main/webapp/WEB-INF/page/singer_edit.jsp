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
			<form action="<%=basePath%>/singer/${requestUrl}.do" method="post" class="form form-horizontal" id="edit_form" onsubmit="return requestForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌星编号：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<c:if test="${singer.singerId!=null and singer.singerId!=''}">
							<input type="text" class="input-text"  value="${singer.singerId}" 
								id="singer_id" name="singer_id" readonly="readonly">
						</c:if>
						<c:if test="${singer.singerId==null or singer.singerId==''}">
							<input type="text" class="input-text"  value="${singer.singerId}" 
								id="singer_id" name="singer_id" placeholder="歌曲编号不允许修改">
						</c:if>
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌星名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${singer.singer_name}" 
							id="singer_name" name="singer_name">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌星拼音：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${singer.singer_pinyin}" 
							id="singer_pinyin" name="singer_pinyin">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">歌星类型：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" name="singer_type" id="singer_type">
							<option value="-1">请选择</option>
							<c:forEach items="${singerTypeList}" var="singerType">
								<option <c:if test="${singer.singer_type==singerType.id}">selected</c:if> 
									value="${singerType.id}">${singerType.singer_type}</option>
							</c:forEach>
						</select>
						</span> </div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>版本：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="singer_version" 
							value="${singer.singer_version}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">歌星头像：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
						<input class="input-text upload-url" type="text"  readonly nullmsg="请添加歌星头像！" style="width:200px">
						<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
						<input type="file"  class="input-file" name="headerFile" id="headerFile">
						</span> </div>
				</div>
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input type="hidden" id="header_path" value="${singer.header_path}">
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
			var formData=new FormData();
			var singerId=$("#singer_id").val();
			if(singerId==null || singerId==""){
				layer.msg("请输入歌星的编号!");
				return false;
			}
			formData.append("singerId",singerId);
			var singerName=$("#singer_name").val();
			if(singerName==null || singerName==""){
				layer.msg("请输入歌星的名字!");
				return false;
			}
			formData.append("singerName",singerName);
			var	singerPinyin=$("#singer_pinyin").val();
			if(singerPinyin==null || singerPinyin==""){
				layer.msg("请输入歌星的拼音!");
				return false;
			}
			formData.append("singerPinyin",singerPinyin);
			var	singerType=$("#singer_type").val();
			if(singerType==-1){
				layer.msg("请选择歌星的类型!");
				return false;
			}
			formData.append("singerType",singerType);
			
			var headerFile=$("#headerFile")[0].files[0];
			if(document.getElementById("header_path").value==""){
				if(headerFile==null){
					layer.msg("请选择歌星的头像!");
					return false;
				}
			}
			formData.append("headerFile",headerFile);
			
			var singerVersion=document.getElementById("singer_version").value;
			if(singerVersion==null||singerVersion==''){
				layer.msg("请输入歌手版本!");
				return false;
			}
			formData.append("singerVersion",singerVersion);
			//获取请求路径
			var form=$("#edit_form");
			var url=form.attr("action");
			if(headerFile!=undefined){
				$.ajax({ 
					url: url, 
					data: formData,
					type:"post",
					async:false,
					processData: false,
				    contentType: false,
				    cache: false,
					success: function(result){
						status=result;
			      	}
				});
			}else{
				var url="<%=basePath%>/singer/modifySinger.do";
				var requestData={"singerId":singerId,"singerName":singerName,
						"singerPinyin":singerPinyin,"singerType":singerType,"singerVersion":singerVersion};
				$.ajax({ 
					url: url, 
					data: requestData,
					type:"post",
					async:false,
					success: function(result){
						status=result;
			      	}
				});
			}
			if(status){
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}else{
				layer.msg("请上传图片类型的文件!");
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