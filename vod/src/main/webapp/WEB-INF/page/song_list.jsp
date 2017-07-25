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
		<!-- 自动补全css -->
		<link href="<%=basePath%>/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
		<!-- 上传文件的css -->
		<link href="<%=basePath%>/static/stream/css/stream-v1.css" rel="stylesheet" type="text/css"/>
		<style type="text/css">
			.pagination > li {
			    display: inline;
			}
			
			.pagination > li > a{
			    position: relative;
			    float: left;
			    padding: 6px 12px;
			    line-height: 1.42857143;
			    text-decoration: none;
			    color: #337ab7;
			    background-color: #ffffff;
			    border: 1px solid #dddddd;
			    margin-left: -1px;
			    border-radius:5px;
			}
			
			.pagination > li>input{
				width:100px;
				height:28px;
				margin-left:0.1em;
				margin-right:0.1em;
				border-radius:3px;
				border:1px solid rgba(127,127,127,0.2);
			}
			
			.page-header {
			    margin: 0 0 12px;
			    border-bottom: 1px dotted #e2e2e2;
			    padding-bottom: 16px;
			    padding-top: 7px;
			}
			.btn-success, .btn-success:focus {
			    background-color: #87b87f !important;
			    border-color: #87b87f;
			}
			
			tbody {
			    display: table-row-group;
			    vertical-align: middle;
			    border-color: inherit;
			}		
		</style>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>歌曲管理<span class="c-gray en">&gt;</span>歌曲列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<form action="<%=basePath%>/song/showSongList.do" method="post">
				<div class="text-c">歌曲名：
					<input type="text" class="input-text" style="width:250px" id="song_name" name="song_name" value="${song.song_name}">
					<button type="submit" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜歌曲</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.add==1}">
						<a href="javascript:;" onclick="song_add()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加歌曲
						</a>
						<a href="javascript:;" onclick="backup()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe604;</i> 备份
						</a>
						<a href="javascript:;" onclick="restore()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe68f;</i> 还原
						</a>
						<a href="javascript:;" onclick="templateDown()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe640;</i>下载模板
						</a>
						<input type="file" name="excel_file" id="excel_file" onchange="importFromExcel(this)" style="display: none" />
 						<a href="javascript:;" onclick="document.getElementById('excel_file').click();" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe644;</i> 导入
						</a>
						<a href="javascript:;" onclick="expertToExcel()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe644;</i> 导出
						</a>
						<a href="javascript:;" onclick="uploadSongs()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe644;</i> 上传歌曲
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="100">歌曲格式</th>
						<th width="100">歌曲编号</th>
						<th width="80">歌曲名</th>
						<th width="100">歌曲语种</th>
						<th width="100">原版/翻唱</th>
						<th width="100">背景版本</th>
						<th width="100">背景版本画质</th>
						<th width="100">发行年代</th>
						<th width="100">歌曲主题</th>
						<th width="100">是否经典老歌</th>
						<th width="100">所属娱乐节目</th>
						<th width="100">音量1</th>
						<th width="100">音量2</th>
						<th width="100">原伴唱声轨</th>
						<th width="100">歌星名</th>
						<th width="100">歌星编号</th>
						<th width="100">验收日期</th>
						<th width="90">点击率</th>
						<th width="90">备注</th>
						<th width="60">上传状态</th>
						<th width="60">审核状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${songList}" var="song" varStatus="status">
						<tr class="text-c">
							<td>${song.song_format}</td>
							<td>${song.songId}</td>
							<td>${song.song_name}</td>
							<td>${song.language}</td>
							<td>${song.nature}</td>
							<td>${song.scene}</td>
							<td>${song.pixel}</td>
							<td>${song.song_years}</td>
							<td>${song.theme}</td>
							<td>${song.classic_status==0?"不是":"是"}</td>
							<td>${song.type}</td>
							<td>${song.volume_one}</td>
							<td>${song.volume_two}</td>
							<td>${song.strack=="0"?"":song.strack}</td>
							<td>${song.singer}</td>
							<td>${song.singerNo}</td>
							<td>${song.receiving_date}</td>
							<td>${song.ordertime}</td>
							<td>${song.song_info}</td>
							<td>
								<c:choose>
									<c:when test="${song.song_path!=''}">
										已上传
									</c:when>
									<c:otherwise>
										<span class="btn-upload form-group" id="song-file-span${status.index+1}">
											<a href="javascript:void();" class="btn btn-primary radius upload-btn"><i class="Hui-iconfont">&#xe642;</i> 浏览文件</a>
											<input type="file" name="file-2" class="input-file" name="songFile" id="songFile${status.index+1}" onchange="uploadSong('${song.songId}','${status.index+1}')">
										</span> 
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:if test="${song.status=='0'}">
									<span class="c-red">待审核</span>
								</c:if>
								<c:if test="${song.status=='1'}">
									<span class="c-green">审核通过</span>
								</c:if>
							</td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="song_edit('${song.songId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6df;</i></a> 
								</c:if>
								<c:if test="${sessionScope.role.del==2}">	
									<a title="删除" href="javascript:;" onclick="singleDel(this,'${song.songId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</c:if>
								<c:if test="${song.status=='0'}">
									<c:if test="${sessionScope.role.check=='4'}">
										<a title="审核" href="javascript:;" onclick="passCheck('${song.songId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6e1;</i></a>
									</c:if>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
				<div class="page-header position-relative">
					<table style="width:100%;">
						<tr>
							<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</body>
	<!-- h-ui的js -->
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui/js/H-ui.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/h-ui.admin/js/H-ui.admin.js"></script>
	<!-- 弹出层js -->
	<script type="text/javascript" src="<%=basePath%>/static/layer/layer.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/laypage/1.2/laypage.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<!-- 自动补全js -->
	<script type="text/javascript" src="<%=basePath%>/static/js/jquery-ui.min.js"></script>
	<!-- 上传文件的js -->
	<script type="text/javascript" src="<%=basePath%>/static/stream/js/stream-v1.js"></script>
	<script type="text/javascript">
		var shapeStatus=true;
		/*批量上传歌曲*/
		function uploadSongs(){
			if(!shapeStatus){
				return;
			}
			var url="<%=basePath%>/song/showUpload.do";
			shapeStatus=false;
			layer.open({
				type: 2,
				area: ['630px','500px'],
				fix: false, //不固定
				maxmin: true,
				shade:0,
				title: "上传歌曲",
				offset: ['330px', '20px'],
				content: url,
				cancel:function(){
					location.replace(location.href);
				},
				end: function () {
					if(shapeStatus) location.reload();
		        }
			});
		}
	
		function uploadSong(songId,index){
			var songFile=$("#songFile"+index)[0].files[0];
			$("#song-file-span"+index).html("<progress id='showProgress"+index+"'></progress><br/><span id='song-progress"+index+"'></span>");
			 //创建FormData对象，初始化为form表单中的数据。需要添加其他数据可使用formData.append("property", "value");  
		    var formData = new FormData(); 
		    formData.append("songFile",songFile);
		    formData.append("songId",songId);
		    //ajax异步上传  
		    $.ajax({  
		        url: "<%=basePath%>/song/uploadSongFile.do",  
		        type: "POST",  
		        data: formData,  
		        xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数  
		          
		            myXhr = $.ajaxSettings.xhr();  
		            if(myXhr.upload){ //检查upload属性是否存在  
		                //绑定progress事件的回调函数  
		               /*  myXhr.upload.addEventListener('progress',progressHandlingFunction, false);   */ 
		               myXhr.upload.addEventListener('progress',function(e){
		            	   if (e.lengthComputable) {
			       		        $('#showProgress'+index).attr({value : e.loaded, max : e.total}); //更新数据到进度条  
			       		        var percent = (e.loaded/e.total*100);
			       		        $('#song-progress'+index).html(percent.toFixed(0) + "%");  
		       		       }  
		               },false);
		            }  
		            return myXhr; //xhr对象返回给jQuery使用  
		        },  
		        success: function(result){ 
		            if(result==true)  $("#song-file-span"+index).text("已上传"); 
		        },  
		        contentType: false, //必须false才会自动加上正确的Content-Type  
		        processData: false  //必须false才会避开jQuery对 formdata 的默认处理  
		    });  
		}
		
		$(function(){
			var autoData=null;
			$("#song_name").keyup(function(){
				var songName=$(this).val();
				var requestUrl="<%=basePath%>/song/autoSearch.do";
				var requestData={"songName":songName};
				$.ajax({
					url:requestUrl,
					type:"POST",
					data:requestData,
					dataType:"json",
					async:false,
					success:function(result){
						$("#song_name").autocomplete({
						      source: eval(result)
						});
					}
				});
			});
			
		});
		/*歌曲-增加*/
		function song_add(){
			//请求的路径
			var url="<%=basePath%>/song/showSongEdit.do";
			var title="添加歌曲信息";
			var width="";
			var height="510";
			url+="?viewName=song_edit&operateType=1";
			layer_show(title,url,width,height);
		}
		
		
		/*歌曲-编辑*/
		function song_edit(id){
			//请求的路径
			var url="<%=basePath%>/song/showSongEdit.do";
			var title="编辑歌曲信息";
			var width="";
			var height="510";
			url+="?viewName=song_edit&operateType=2&songId="+id;
			layer_show(title,url,width,height);
		}
		
		/*单个删除*/
		function singleDel(obj,id){
			layer.confirm('确认要删除吗？',function(index){
				/* 后台异步执行的结果 */
				var status=false;
				//访问后台的请求路径
				var requestUrl="<%=basePath%>/song/deleteSong.do";
				//请求后台的数据
				var requestData={"songId":id};
				$.ajax({
					url:requestUrl,
					type:'POST',
					data:requestData,
					async:false,
					success:function(result){
						status=result;
					}
				});
				if(status){
					$(obj).parents("tr").remove();
					layer.msg('已删除!',{icon:1,time:1000});
				}else{
					layer.msg('删除失败!',{icon:2,time:2000});
				}
			});
		}
		/*通过审核*/
		function passCheck(id){
			var status=false;
			var requestUrl="<%=basePath%>/song/passCheck.do";
			var requestData={"songId":id};
			$.ajax({
				url:requestUrl,
				type:'POST',
				data:requestData,
				async:false,
				success:function(result){
					status=result;
				}
			});
			if(status)
				location.replace(location.href);
			else layer.msg("审核失败!");
		}
		
		/*备份歌曲的信息*/
		function backup(){
			var requestUrl="<%=basePath%>/song/backupSong.do";
			$.ajax({
				url:requestUrl,
				type:"POST",
				success:function(result){
					if(result){
						layer.msg("数据备份成功!");
					}else{
						layer.msg("数据备份失败!");
					}
				}
			});
		}
		/*还原歌曲的信息*/
		function restore(){
			layer.confirm('确认要还原备份的信息吗,请谨慎操作!',function(index){
				var requestUrl="<%=basePath%>/song/restoreSong.do";
				var status=false;
				$.ajax({
					url:requestUrl,
					type:"POST",
					async:false,
					success:function(result){
						if(result){
							layer.msg("数据还原成功!");
							status=true;
						}else{
							layer.msg("数据还原失败!");
						}
					}
				});
				if(status) location.replace(location.href);
			});
		}
		/*导出数据到excel中*/
		function expertToExcel(){
			location.href="<%=basePath%>/song/exportToExcel.do";
			//var songFile=$('#songFile')[0].files[0];
		}
		function importFromExcel(){
			var status=null;
			var requestUrl="<%=basePath%>/song/importFromExcel.do";
			//封装file的表单数据
			var formData=new FormData();
			var excelFile=$("#excel_file")[0].files[0];
			formData.append("excelFile",excelFile);
			$.ajax({ 
				url: requestUrl, 
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
			if(status){
				layer.msg("导入成功!");
				location.replace(location.href);
			}
			else layer.msg("导入失败!");
		}
		
		function templateDown(){
			location.href="<%=basePath%>/song/templateDownload.do";
		}
	</script>
	
</html>