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
			<form action="<%=basePath%>/song/${requestUrl}.do" method="post" enctype="multipart/from-data" class="form form-horizontal" id="edit_form" onsubmit="return editForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲格式：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" name="song_format" id="song_format">
							<option value="-1" selected>请选择</option>
							<option <c:if test="${song.song_format==0}">selected</c:if> value="0">VOD</option>
							<option <c:if test="${song.song_format==1}">selected</c:if> value="1">MRS</option>
							<option <c:if test="${song.song_format==2}">selected</c:if> value="2">MIDI</option>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲编号(文件名)：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<c:if test="${song.id!=null}">
							<input type="text"  class="input-text"  readonly="readonly"  id="song_id" value="${song.id}">
						</c:if>
						<c:if test="${song.id==null}">
							<input type="text"  class="input-text" placeholder="歌曲编号不允许修改"  id="song_id" value="${song.id}">
						</c:if>
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲名称：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="song_name" value="${song.song_name}">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>拼写缩写：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="spell" 
							name="spell" value="${song.spell}">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲语种：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="song_lang">
							<option value="-1" selected>请选择</option>
							<c:forEach items="${songLangs}" var="songLang">
								<option <c:if test="${song.lang_id==songLang.id}">selected</c:if> value="${songLang.id}">${songLang.language}</option>
							</c:forEach>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原版/翻唱：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="nature">
							<option  value="-1" selected>请选择</option>
							<option <c:if test="${song.nature==0}">selected</c:if> value="0">原版</option>
							<option <c:if test="${song.nature==1}">selected</c:if> value="1">翻唱</option>
							<option <c:if test="${song.nature==2}">selected</c:if> value="2">原版LIVE</option>
							<option <c:if test="${song.nature==3}">selected</c:if> value="3">翻唱LIVE</option>
						</select>
						</span> </div>
				</div>
				
				
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>发行年代：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="song_years">
							<option value="-1" selected>请选择</option>
							<option <c:if test="${song.song_years==5}">selected</c:if> value="0">70's以前</option>
							<option <c:if test="${song.song_years==0}">selected</c:if> value="0">70's</option>
							<option <c:if test="${song.song_years==1}">selected</c:if> value="1">80's</option>
							<option <c:if test="${song.song_years==2}">selected</c:if> value="2">90's</option>
							<option <c:if test="${song.song_years==3}">selected</c:if> value="3">00's</option>
							<option <c:if test="${song.song_years==4}">selected</c:if> value="4">00's以后</option>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲主题：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="theme">
							<option value="-1" selected>请选择</option>
							<c:forEach items="${songThemes}" var="theme">
								<option <c:if test="${song.theme_id==theme.id}">selected</c:if> value="${theme.id}">${theme.theme}</option>
							</c:forEach>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">经典老歌：</label>
					<div class="formControls col-xs-8 col-sm-9">
						  <div class="radio-box">
						    <input type="radio"  name="classic_status" checked="checked" value="1" >
						    <label for="radio-1">是</label>
						  </div>
						   <div class="radio-box">
						    <input type="radio"  name="classic_status" <c:if test="${song.classic_status==0}">checked</c:if>  value="0">
						    <label for="radio-1">否</label>
						  </div>
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌曲类型：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="song_type">
							<option value="-1" selected>请选择</option>
							<c:forEach items="${songTypes}" var="type">
								<option <c:if test="${song.type_id==type.id}">selected</c:if> value="${type.id}">${type.type}</option>
							</c:forEach>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>歌星名1：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text" id="singer_name_one" 
							 value="${song.singerOneName}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>对应歌星编号：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="singer_id_one">
							<option value="-1" selected>请选择</option>
							<c:if test="${song.singer_id_one!=null}">
								<option value="${song.singer_id_one}" selected>${song.singer_id_one}</option>
							</c:if>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">歌星名2：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="singer_name_two" 
							value="${song.singerTwoName}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">对应歌星编号：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="singer_id_two">
							<option value="-1" selected>请选择</option>
							<c:if test="${song.singer_id_two!=null and song.singer_id_three!='-1'}">
								<option value="${song.singer_id_two}" selected>${song.singer_id_two}</option>
							</c:if>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">歌星名3：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="singer_name_three" 
							value="${song.singerThreeName}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">对应歌星编号：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="singer_id_three">
							<option value="-1" selected>请选择</option>
							<c:if test="${song.singer_id_three!=null and song.singer_id_three!='-1'}">
								<option value="${song.singer_id_three}" selected>${song.singer_id_three}</option>
							</c:if>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">歌曲文件：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="btn-upload form-group">
						<input class="input-text upload-url" type="text"  readonly nullmsg="请添加歌曲文件！" style="width:200px">
						<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
						<input type="file" name="file-2" class="input-file" id="song_file">
						</span> 
					</div>
				</div>
				
				<div class="row cl show_by_vod" style="display: none">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>背景版本：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="scene">
							<option value="-1" selected>请选择</option>
							<option <c:if test="${song.nature==0}">selected</c:if> value="0">LIVE</option>
							<option <c:if test="${song.nature==1}">selected</c:if> value="1">MTV</option>
							<option <c:if test="${song.nature==2}">selected</c:if> value="2">非原画</option>
						</select>
						</span> </div>
				</div>
				<div class="row cl show_by_vod" style="display: none">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>背景版本画质：</label>
					<div class="formControls col-xs-8 col-sm-9"> <span class="select-box">
						<select class="select" size="1" id="pixel">
							<option value="-1" selected>请选择</option>
							<option <c:if test="${song.pixel==0}">selected</c:if> value="0">480p</option>
							<option <c:if test="${song.pixel==1}">selected</c:if> value="1">720p</option>
							<option <c:if test="${song.pixel==2}">selected</c:if> value="2">1080p</option>
							<option <c:if test="${song.pixel==3}">selected</c:if> value="3">1080p以上</option>
						</select>
						</span> </div>
				</div>
				
				<div class="row cl show_by_vod" style="display: none">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>音量1：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="volume_one" value="${song.volume_one}">
					</div>
				</div>
				
				<div class="row cl show_by_vod" style="display: none">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>音量2：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="volume_two"  value="${song.volume_two}">
					</div>
				</div>
				<div class="row cl show_by_vod" style="display: none">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>原伴唱声轨：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="strack" value="${song.strack}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>验收日期:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text Wdate"  id="receiving_date" value="${song.receiving_date}"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width:100%"/>  
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>版本：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  id="song_version" 
							value="${song.song_version}">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3">备注：</label>
					<div class="formControls col-xs-8 col-sm-9">
						<textarea name="song_info" cols="" rows="" class="textarea" 
							id="song_info">${song.song_info}</textarea>
					</div>
				</div>
				
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input type="hidden" id="song_path" value="${song.song_path}"/>
						<input type="hidden" id="old_song_id" value="${song.id}"/>
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
	<script type="text/javascript" src="<%=basePath%>/static/My97DatePicker/WdatePicker.js"></script>
	
	<script type="text/javascript">
		function editForm(){
			var formData=new FormData();
			var songFormat=document.getElementById("song_format").value;
			if(songFormat==-1){
				layer.msg("请选择歌曲格式!");
				return false;
			}
			formData.append("songFormat",songFormat);
			var songId=document.getElementById("song_id").value;
			if(songId==""){
				layer.msg("请输入歌曲编号(文件名)!");
				return false;
			}
			formData.append("songId",songId);
			var songName=document.getElementById("song_name").value;
			if(songName==""){
				layer.msg("请输入歌曲名!");
				return false;
			}
			formData.append("songName",songName);
			var spell=document.getElementById("spell").value;
			if(spell==""){
				layer.msg("请输入歌曲的拼音缩写!");
				return false;
			}
			formData.append("spell",spell);
			var songLang=document.getElementById("song_lang").value;
			if(songLang==-1){
				layer.msg("请选择歌曲语种!");
				return false;
			}
			formData.append("langId",songLang);
			var nature=document.getElementById("nature").value;
			if(nature==-1){
				layer.msg("请选择歌曲原唱和翻唱的种类!");
				return false;
			}
			formData.append("nature",nature);
			var songYears=document.getElementById("song_years").value;
			if(songYears==-1){
				layer.msg("请选择歌曲的发行年代!");
				return false;
			}
			formData.append("songYears",songYears);
			var theme=document.getElementById("theme").value;
			if(theme==""){
				layer.msg("请选择歌曲的主题!");
				return false;
			}
			formData.append("themeId",theme);
			var classicNode=document.getElementsByName("classic_status");
			var classicStatus=classicNode[0].checked==true?classicNode[0].value:classicNode[1].value;
			formData.append("classicStatus",classicStatus);
			var song_type=document.getElementById("song_type").value;
			if(song_type==-1){
				layer.msg("请选择歌曲的类型!");
				return false;
			}
			formData.append("typeId",song_type);
			var singerIdOne=document.getElementById("singer_id_one").value;
			if(singerIdOne==-1){
				layer.msg("请至少输入一位歌手的名字,并且选择对应的歌星编号!");
				return false;
			}
			formData.append("singerIdOne",singerIdOne);
			var singerNameTwo=document.getElementById("singer_name_two").value;
			if(singerNameTwo!=""){
				var singerIdTwo=document.getElementById("singer_id_two").value;
				if(singerIdTwo==-1){
					layer.msg("请选择第二位歌手对应的歌手编号!");
					return false;
				}
				formData.append("singerIdTwo",singerIdTwo);
			}
			var singerNameThree=document.getElementById("singer_name_three").value;
			if(singerNameThree!=""){
				var singerIdThree=document.getElementById("singer_id_three").value;
				if(singerIdThree==-1){
					if(singerIdTwo==-1){
						layer.msg("请选择第三位歌手对应的歌手编号!");
						return false;
					}
				}
				formData.append("singerIdThree",singerIdThree);
			}
			var songFile=$("#song_file")[0].files[0];
			var songPath=document.getElementById("song_path").value;
			if(songPath==""){
				if(songFile==null){
					layer.msg("请选择歌曲文件!");
					return false;
				}
			}
			formData.append("songFile",songFile);
			//vod格式的其它数据空校验
			if(songFormat==0){
				var scene=document.getElementById("scene").value;
				if(scene==-1){
					layer.msg("请选择背景版本!");
					return false;
				}
				formData.append("scene",scene);
				var pixel=document.getElementById("pixel").value;
				if(pixel==-1){
					layer.msg("请选择背景版本画质!");
					return false;
				}
				formData.append("pixel",pixel);
				var volumeOne=document.getElementById("volume_one").value;
				if(volumeOne==""){
					layer.msg("请输入音量1!");
					return false;
				}
				formData.append("volumeOne",volumeOne);
				var volumeTwo=document.getElementById("volume_two").value;
				if(volumeTwo==""){
					layer.msg("请输入音量2!");
					return false;
				}
				formData.append("volumeTwo",volumeTwo);
				var strack=document.getElementById("strack").value;
				if(strack==""){
					layer.msg("请输入原伴唱声轨!");
					return false;
				}
				formData.append("strack",strack);
			}
			var  receivingDate=document.getElementById("receiving_date").value;
			if(receivingDate==""){
				layer.msg("请选择验收日期!");
				return false;
			}
			formData.append("receivingDate",receivingDate);
			var songVersion=document.getElementById("song_version").value;
			if(songVersion==""){
				layer.msg("请输入歌曲版本!");
				return false;
			}
			formData.append("songVersion",songVersion);
			var songInfo=document.getElementById("song_info").value;
			formData.append("songInfo",songInfo);
			var form=$("#edit_form");
			var requestUrl=form.attr("action");
			//修改结果
			var flag=false;
			var oldSongId=document.getElementById("old_song_id").value;
			formData.append("oldSongId",oldSongId);
			//如果songPath为空或者songPath不为空但songFile为空则是修改，修改需要原来的歌曲编号
			if(songFile==null){
				var requestData;
				if(songFormat==0){
					requestData={"oldSongId":oldSongId,"songFormat":songFormat,"songId":songId,"songName":songName,
							"spell":spell,"langId":songLang,"nature":nature,"songYears":songYears,"themeId":theme,
							"classicStatus":classicStatus,"singerIdOne":singerIdOne,"singerIdTwo":singerIdTwo,
							"singerIdThree":singerIdThree,"scene":scene,"pixel":pixel,"volumeOne":volumeOne,
							"volumeTwo":volumeTwo,"strack":strack,"receivingDate":receivingDate,"songVersion":songVersion,
							"songInfo":songInfo};
				}else{
					requestData={"oldSongId":oldSongId,"songFormat":songFormat,"songId":songId,"songName":songName,
							"spell":spell,"langId":songLang,"nature":nature,"songYears":songYears,"themeId":theme,
							"classicStatus":classicStatus,"singerIdOne":singerIdOne,"singerIdTwo":singerIdTwo,
							"singerIdThree":singerIdThree,"receivingDate":receivingDate,"songVersion":songVersion,
							"songInfo":songInfo};
				}
				$.ajax({
					url:"<%=basePath%>/song/updateSongOther.do",
					data:requestData,
					async:false,
					type:"POST",
					success:function(result){
						flag=result;
					}
				});
			}else{
				$.ajax({ 
					url:requestUrl, 
					data: formData,
					type:"POST",
					async:false,
					processData: false,
				    contentType: false,
				    cache: false,
					success: function(result){
						flag=result;
			      	}
				});
			}
			
			if(flag){
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			}else{
				layer.msg("编辑歌曲信息失败,请确保文件名和文件编号相同!");
				return false;
			}
		}
		$(function(){
			//针对vod格式的数据添加进行添加数据调整
			$("#song_format").on("change",function(){
				var songFormat=$(this).val();
				if(songFormat==0){
					$(".show_by_vod").show();
				}else{
					$(".show_by_vod").hide();
				}
			});
			//对歌星的输入加以筛选
			$("#singer_name_one").on("keyup",function(){
				var requestUrl="<%=basePath%>/singer/obtainIdByName.do";
				var singerName=$(this).val();
				if(singerName==""||singerName==" "){
					return;
				}
				var requestData={"singerName":singerName};
				$.ajax({
					url:requestUrl,
					data:requestData,
					type:"POST",
					success:function(result){
						var singerIdOneNode=$("#singer_id_one");
						var singerIdOneHtml="";
						for ( var i in result) {
							singerIdOneHtml+="<option value='"+result[i]+"'>"+result[i]+"</option>";
						}
						singerIdOneNode.html(singerIdOneHtml);
					}
				});
			});
			$("#singer_name_two").on("change",function(){
				var requestUrl="<%=basePath%>/singer/obtainIdByName.do";
				var singerName=$(this).val();
				if(singerName==""||singerName==" "){
					return;
				}
				var requestData={"singerName":singerName};
				$.ajax({
					url:requestUrl,
					data:requestData,
					type:"POST",
					success:function(result){
						var singerIdOneNode=$("#singer_id_two");
						var singerIdOneHtml="";
						for ( var singerId in eval(result)) {
							singerIdOneHtml+="<option value="+result[i]+">"+result[i]+"</option>";
						}
						singerIdOneNode.html(singerIdOneHtml);
					}
				});
			});
			$("#singer_name_three").on("change",function(){
				var requestUrl="<%=basePath%>/singer/obtainIdByName.do";
				var singerName=$(this).val();
				if(singerName==""||singerName==" "){
					return;
				}
				var requestData={"singerName":singerName};
				$.ajax({
					url:requestUrl,
					data:requestData,
					type:"POST",
					success:function(result){
						var singerIdOneNode=$("#singer_id_three");
						var singerIdOneHtml="";
						for ( var singerId in eval(result)) {
							singerIdOneHtml+="<option value="+result[i]+">"+result[i]+"</option>";
						}
						singerIdOneNode.html(singerIdOneHtml);
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