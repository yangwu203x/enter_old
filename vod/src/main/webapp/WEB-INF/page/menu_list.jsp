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
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>菜单管理<span class="c-gray en">&gt;</span>菜单列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<%-- <form action="<%=basePath%>/menu/allMenu.do">
				<div class="text-c"> 菜单名：
					<input type="text" class="input-text" style="width:250px" name="menu_name" value="${menu.menu_name}">
					<button type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
				</div>
			</form> --%>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.del==2}">
						<a href="javascript:;" onclick="menus_del()" class="btn btn-danger radius">
							<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
						</a> 
					</c:if>
					<c:if test="${sessionScope.role.add==1}">
						<a href="javascript:;" onclick="menu_add('添加菜单','<%=basePath%>/menu/showAddMenu.do','','510')" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加菜单
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">菜单编号</th>
						<th width="100">菜单名</th>
						<th width="40">菜单路径</th>
						<th width="90">菜单图标</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${menuList}" var="menu">
						<tr class="text-c">
							<td><input type="checkbox" value="${menu.menuNo}" name="menuNo"></td>
							<td>${menu.menuNo}</td>
							<td>${menu.menuName}</td>
							<td>${menu.menuUrl}</td>
							<td><i class="Hui-iconfont" style="font-size:25px;">${menu.menuIcon}</i></td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="menu_edit('编辑','<%=basePath%>/menu/showAddMenu.do','${menu.menuNo}','','510')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a>
								</c:if>
								<c:if test="${sessionScope.role.del==2}">
									<a title="删除" href="javascript:;" onclick="menu_del(this,'${menu.menuNo}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
	
	
	/*菜单-添加*/
	function menu_add(title,url,w,h){
		url+="?viewName=menu_edit&operateType=1";
		layer_show(title,url,w,h);
	}
	
	
	/*菜单-编辑*/
	function menu_edit(title,url,id,w,h){
		url+="?viewName=menu_edit&operateType=2&menuNo="+id;
		layer_show(title,url,w,h);
	}
	
	/*菜单-批量删除*/
	function menus_del(){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		//保存一组菜单编号
		var menuNos="";
		//获取复选框元素
		var menuNosNode=document.getElementsByName("menuNo");
		//保存已删除的一组复选框元素
		var deletedMenuNode=new Array();
		
		/* 代表选中被删除元素的索引 */
		var index=0;
		for (i=0;i<menuNosNode.length;i++){
			if(menuNosNode[i].checked == true){
				//保存待删除的节点
				deletedMenuNode[index]=menuNosNode[i];
				if(i<menuNosNode.length-1){
					  menuNos+=menuNosNode[i].value+",";
				  }else{
					  menuNos+=menuNosNode[i].value;
				  }
				index++;
			}
		  
		}
		menu_del(deletedMenuNode, menuNos);
	}
	/*菜单-删除*/
	function menu_del(obj,menuNo){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		
		layer.confirm('确认要删除吗？',function(index){
			/* 后台异步执行的结果 */
			var status=false;
			//访问后台的请求路径
			var requestUrl="<%=basePath%>/menu/delMenu.do";
			//请求后台的数据
			var requestData={"menuNos":menuNo};
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
				layer.msg('删除失败,可能待删除的菜单中存在子菜单!',{icon:2,time:2000});
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