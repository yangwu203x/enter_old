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
			.btn-point{
				cursor: default;
				border: none;
			}
		</style>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>权限管理<span class="c-gray en">&gt;</span>权限列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<form action="<%=basePath%>/role/roleList.do" method="post">
				<div class="text-c">角色名:
					<input type="text" class="input-text radius" style="width:250px" id="role_name" name="role_name" value="${role.role_name}">
					<button type="submit" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i>搜索角色</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.del==2}">
						<a href="javascript:;" onclick="roles_del()" class="btn btn-danger radius">
							<i class="Hui-iconfont">&#xe6e2;</i>批量删除
						</a> 
					</c:if>
					<c:if test="${sessionScope.role.add==1}">
						<a href="javascript:;" onclick="role_add()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加角色
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">角色编号</th>
						<th width="100">角色名</th>
						<th width="40">角色权限</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${roles}" var="role">
						<tr class="text-c">
							<td><input type="checkbox" value="${role.role_id}" name="role_id"></td>
							<td>${role.role_id}</td>
							<td>${role.role_name}</td>
							<td>
						    	<c:if test="${role.add=='1'}">
									<input class="btn btn-success radius btn-point" type="button" value="增加">
								</c:if>
								<c:if test="${role.del=='2'}">
									<input class="btn btn-danger radius btn-point" type="button" value="删除">
								</c:if>
								<c:if test="${role.update=='3'}">
									 <input class="btn btn-warning radius btn-point" type="button" value="修改">
								</c:if>
								<c:if test="${role.check=='4'}">
									<input class="btn btn-secondary radius btn-point" type="button" value="审核">
								</c:if>
							</td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="role_edit('${role.role_id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6df;</i></a> 
								</c:if>
								<c:if test="${sessionScope.role.del==2}">
									<a title="删除" href="javascript:;" onclick="role_del(this,'${role.role_id}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a>
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
	
	$(function(){
		var autoData=null;
		$("#role_name").keyup(function(){
			var role_name=$(this).val();
			var requestUrl="<%=basePath%>/role/autoSearch.do";
			var requestData={"role_name":role_name};
			$.ajax({
				url:requestUrl,
				type:"POST",
				data:requestData,
				dataType:"json",
				async:false,
				success:function(result){
					$("#role_name").autocomplete({
					      source: eval(result)
					});
				}
			});
		});
	});
	
	/*角色-增加*/
	function role_add(){
		//请求的路径
		var url="<%=basePath%>/role/showRoleEdit.do";
		var title="添加角色";
		var width="";
		var height="370";
		url+="?viewName=role_edit&operateType=1";
		layer_show(title,url,width,height);
	}
	
	
	/*用户-编辑*/
	function role_edit(id){
		//请求的路径
		var url="<%=basePath%>/role/showRoleEdit.do";
		var title="编辑角色";
		var width="";
		var height="370";
		url+="?viewName=role_edit&operateType=2&roleNo="+id;
		layer_show(title,url,width,height);
	}
	/*账户-批量删除*/
	function roles_del(){
		//保存一组角色编号
		var roleNos="";
		//获取复选框元素
		var rolesNode=document.getElementsByName("role_id");
		//保存已删除的一组复选框元素
		var deletedRoleNode=new Array();
		/* 代表选中被删除元素的索引 */
		var index=0;
		for (i=0;i<rolesNode.length;i++){
			if(rolesNode[i].checked == true){
				//保存待删除的节点
				deletedRoleNode[index]=rolesNode[i];
				if(i<rolesNode.length-1){
					roleNos+=rolesNode[i].value+",";
				  }else{
					  roleNos+=rolesNode[i].value;
				  }
				index++;
			}
		  
		}
		role_del(deletedRoleNode, roleNos);
	}
	/*账户-删除*/
	function role_del(obj,roleNo){
		layer.confirm('确认要删除吗？',function(index){
			/* 后台异步执行的结果 */
			var status=false;
			//访问后台的请求路径
			var requestUrl="<%=basePath%>/role/deleteRole.do";
			//请求后台的数据
			var requestData={"roleNo":roleNo};
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