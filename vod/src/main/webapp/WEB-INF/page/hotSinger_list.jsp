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
		<link href="<%=basePath%>/static/h-ui/css/H-ui.css" rel="stylesheet" type="text/css" />
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
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>热门歌手管理<span class="c-gray en">&gt;</span>热门歌手列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.del==2}">
						<a href="javascript:;" onclick="batchDel()" class="btn btn-danger radius">
							<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
						</a> 
					</c:if>
					<c:if test="${sessionScope.role.add==1}">
						<a href="javascript:;" onclick="add()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">热门歌手编号</th>
						<th width="80">歌手名称</th>
						<th width="100">歌手头像</th>
						<th width="80">排行</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${hotSingers}" var="hotSinger">
						<tr class="text-c">
							<td><input type="checkbox" value="${hotSinger.hotId}" name="hot_id"></td>
							<td>${hotSinger.hotSingerId}</td>
							<td>${hotSinger.singerName}</td>
							<td><img src="<%=picturePath%>/${hotSinger.headerPath}" alt="歌星头像" class="thumbnail"></td>
							<td>${hotSinger.rank}</td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="edit('${hotSinger.hotId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
								</c:if>
								<c:if test="${sessionScope.role.del==2}">
									<a title="删除" href="javascript:;" onclick="deleteRank(this,'${hotSinger.hotId}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
	<script type="text/javascript">
	/*修改*/
	function edit(id){
		//请求的路径
		var url="<%=basePath%>/hotSinger/hotSingerEdit.do";
		var title="修改热门歌手";
		var width="";
		var height="510";
		url+="?viewName=hotSinger_edit&operateType=2&hotId="+id;
		layer_show(title,url,width,height);
	}
	/*增加*/
	function add(){
		//请求的路径
		var url="<%=basePath%>/hotSinger/hotSingerEdit.do";
		var title="添加热门歌手";
		var width="";
		var height="510";
		url+="?viewName=hotSinger_edit&operateType=1";
		layer_show(title,url,width,height);
	}
	
	/*批量删除*/
	function batchDel(){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		//保存一组编号
		var deleteNos="";
		//获取复选框元素
		var allNode=document.getElementsByName("hot_id");
		//保存待删除的一组复选框元素
		var deleteNodes=new Array();
		
		/* 代表选中被删除元素的索引 */
		var index=0;
		for (i=0;i<allNode.length;i++){
			if(allNode[i].checked == true){
				//保存待删除的节点
				deleteNodes[index]=allNode[i];
				if(i<allNode.length-1){
					deleteNos+=allNode[i].value+",";
				  }else{
					  deleteNos+=allNode[i].value;
				  }
				index++;
			}
		}
		if(deleteNodes.length==0){
			layer.msg("请至少选择一项!");
			return;
		}
		deleteRank(deleteNodes, deleteNos);
	}
	/*单个删除*/
	function deleteRank(obj,id){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		
		layer.confirm('确认要删除吗？',function(index){
			/* 后台异步执行的结果 */
			var status=false;
			//访问后台的请求路径
			var requestUrl="<%=basePath%>/hotSinger/delHotSingers.do";
			//请求后台的数据
			var requestData={"hotId":id};
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
				if(isArray(obj)){
					for (var i = 0; i < obj.length; i++) {
						$(obj[i]).parents("tr").remove();
					}
				}else{
					$(obj).parents("tr").remove();
				 } 
				layer.msg('已删除!',{icon:1,time:1000});
			}else{
				layer.msg('删除失败!',{icon:2,time:2000});
			}
		});
	}
	
	//判断元素是否是数组
	function isArray(object){
	    return object && typeof object==='object' &&
	            Array == object.constructor;
	}
	</script>
</html>