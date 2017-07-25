<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	String picturePath="http://192.168.1.254:8080/vodfile";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<!-- h-ui的css -->
		<%-- <link href="<%=basePath%>/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" /> --%>
		<link href="<%=basePath%>/static/h-ui/css/H-ui.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath%>/static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css"/>
		<link  href="<%=basePath%>/static/h-ui.admin/skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
		<!-- 图标样式的css -->
		<link href="<%=basePath%>/static/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet" type="text/css" />
		<!-- 美化表单元素的css -->
		<link  href="<%=basePath%>/static/icheck/icheck.css" rel="stylesheet" type="text/css"/>
		<!-- 自动补全css -->
		<link href="<%=basePath%>/static/css/jquery-ui.css" rel="stylesheet" type="text/css"/>
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
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>歌星管理 <span class="c-gray en">&gt;</span>歌星列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<form action="<%=basePath%>/singer/showSingerList.do" method="post" onsubmit="return searchForm()">
				<div class="text-c">歌星名：
					<input type="text" class="input-text radius" style="width:250px" id="singer_name" name="singerName" value="${singer.singerName}">
					&nbsp;&nbsp;歌星类型:
					<select name="singer_type" class="radius" id="singerType" style="width:100px;height:30px;">
						<option value="">请选择</option>
						<c:forEach items="${singerTypeList}" var="singerType">
							<option <c:if test="${singer.singer_type==singerType.id}">selected</c:if> 
										value="${singerType.id}">${singerType.singer_type}</option>
						</c:forEach>
					</select>
					<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜歌星</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.add==1}">	
						<a href="javascript:;" onclick="singer_add()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加歌手
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="80">歌星编号</th>
						<th width="80">歌星名字</th>
						<th width="100">歌星拼音</th>
						<th width="200">歌星头像</th>
						<th width="40">歌星类型</th>
						<th width="90">歌星点击率</th>
						<th width="60">审核状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${singerList}" var="singer">
						<tr class="text-c">
							<td>${singer.singerId}</td>
							<td>${singer.singer_name}</td>
							<td>${singer.singer_pinyin}</td>
							<td><img src="<%=picturePath%>/${singer.header_path}" alt="歌星头像" class="thumbnail"></td>
							<td>${singer.singer_type}</td>
							<td>${singer.singer_ordertimes}</td>
							<td>
								<c:if test="${singer.status=='0'}">
									<span class="c-red">待审核</span>
								</c:if>
								<c:if test="${singer.status=='1'}">
									<span class="c-green">审核通过</span>
								</c:if>
							</td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="singer_edit('${singer.singerId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6df;</i></a> 
								</c:if>
								<c:if test="${sessionScope.role.del==2}">
									<a title="删除" href="javascript:;" onclick="singleDel(this,'${singer.singerId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</c:if>
								<c:if test="${singer.status=='0'}">
									<c:if test="${sessionScope.role.check=='4'}">
										<a title="审核" href="javascript:;" onclick="passCheck('${singer.singerId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6e1;</i></a>
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
	<script type="text/javascript">
	
	//搜索表单
//	function searchForm(){
//		var nameNode=document.getElementById("singer_name");
//		var singerName=nameNode.value;
//		if(singerName==null ||singerName==""){
//			return false;
//		}
//		singerName=singerName.replace(/\s/g,"?");
//		nameNode.value=singerName;
//	}
	
	$(function(){
		var autoData=null;
		$("#singer_name").keyup(function(){
			var singerName=$(this).val();
			var requestUrl="<%=basePath%>/singer/autoSearch.do";
			var requestData={"singerName":singerName};
			$.ajax({
				url:requestUrl,
				type:"POST",
				data:requestData,
				dataType:"json",
				async:false,
				success:function(result){
					$("#singer_name").autocomplete({
					      source: eval(result)
					});
				}
			});
		});
		
	});
	
	
	/*歌手-增加*/
	function singer_add(){
		//请求的路径
		var url="<%=basePath%>/singer/showSingerEdit.do";
		var title="添加歌手";
		var width="";
		var height="510";
		url+="?viewName=singer_edit&operateType=1";
		layer_show(title,url,width,height);
	}
	
	
	/*歌手-编辑*/
	function singer_edit(id){
		//请求的路径
		var url="<%=basePath%>/singer/showSingerEdit.do";
		var title="编辑歌手类型";
		var width="";
		var height="510";
		url+="?viewName=singer_edit&operateType=2&singerId="+id;
		layer_show(title,url,width,height);
	}
	
	/*单个删除*/
	function singleDel(obj,id){
		layer.confirm('确认要删除吗？',function(index){
			/* 后台异步执行的结果 */
			var status=false;
			//访问后台的请求路径
			var requestUrl="<%=basePath%>/singer/deleteSinger.do";
			//请求后台的数据
			var requestData={"singerId":id};
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
		var requestUrl="<%=basePath%>/singer/passCheck.do";
		var requestData={"singerId":id};
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
 	</script> 
</html>