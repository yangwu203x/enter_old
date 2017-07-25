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
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>排行管理 <span class="c-gray en">&gt;</span>排行列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<%-- <form action="<%=basePath%>/song/showRank.do" method="post">
				<div class="text-c">歌曲名：
					<input type="text" class="input-text radius" style="width:250px" name="song_name"  value="${rank.song_name}">
					&nbsp;&nbsp;歌星名:
					<input type="text" class="input-text radius" style="width:250px" name="singer_name" value="${rank.singer_name}">
					<button type="submit" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜歌星类型</button>
				</div>
			</form> --%>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<!-- <a href="javascript:;" onclick="user_add()" class="btn btn-primary radius">
						<i class="Hui-iconfont">&#xe600;</i> 添加用户
					</a> -->
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="80">排行</th>
						<th width="80">歌曲名</th>
						<th width="100">歌曲编号</th>
						<th width="40">歌星名</th>
						<th width="90">歌星编号</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${rankList}" var="rank" varStatus="status">
						<tr class="text-c">
							<td>${rank.rank}</td>
							<td>${rank.song_name}</td>
							<td>${rank.song_id}</td>
							<td>${rank.singer_name}</td>
							<td>${rank.singer_id}</td>
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
	<script type="text/javascript">
	
	/*用户-增加*/
	function user_add(){
		//请求的路径
		var url="<%=basePath%>/userInfo/showUserEdit.do";
		var title="添加用户";
		var width="";
		var height="510";
		url+="?viewName=user_edit&operateType=1";
		layer_show(title,url,width,height);
	}
	
	
	/*用户-编辑*/
	function user_edit(id){
		//请求的路径
		var url="<%=basePath%>/userInfo/showUserEdit.do";
		var title="编辑用户";
		var width="";
		var height="510";
		url+="?viewName=user_edit&operateType=2&user_id="+id;
		layer_show(title,url,width,height);
	}
 	</script> 
</html>