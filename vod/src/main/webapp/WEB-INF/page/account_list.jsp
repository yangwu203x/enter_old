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
			
		</style>
	</head>
	<body>
		<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>账户管理<span class="c-gray en">&gt;</span> 账户列表<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		<div class="page-container">
			<form action="<%=basePath%>/account/accounts.do" method="post">
				<div class="text-c"> 账户名：
					<input type="text" class="input-text" style="width:250px"  id="account_name" name="account_name"  value="${account.account_name}">
					<button type="submit" class="btn btn-success radius"><i class="Hui-iconfont">&#xe665;</i> 搜账户</button>
				</div>
			</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20"> 
				<span class="l">
					<c:if test="${sessionScope.role.del==2}">
						<a href="javascript:;" onclick="accounts_del()" class="btn btn-danger radius">
							<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
						</a> 
					</c:if>
					<c:if test="${sessionScope.role.add==1}">
						<a href="javascript:;" onclick="account_add()" class="btn btn-primary radius">
							<i class="Hui-iconfont">&#xe600;</i> 添加账户
						</a>
					</c:if>
				</span> 
			</div>
			<div class="mt-20">
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="25"><input type="checkbox" name="" value=""></th>
						<th width="80">账户编号</th>
						<th width="100">账户名</th>
						<th width="40">账户角色</th>
						<th width="90">上次登陆时间</th>
						<th width="90">上次登陆ip</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${accountList}" var="account">
						<tr class="text-c">
							<td><input type="checkbox" value="${account.accountNo}" name="accountNo"></td>
							<td>${account.accountNo}</td>
							<td>${account.account}</td>
							<td>${account.user_role}</td>
							<td>${account.lastLoginTime}</td>
							<td>${account.lastLoginIp}</td>
							<td class="td-manage">
								<c:if test="${sessionScope.role.update==3}">
									<a title="编辑" href="javascript:;" onclick="account_edit('${account.accountNo}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6df;</i></a> 
									<a title="权限分配" href="javascript:;" onclick="allocatePrivilege('${account.accountNo}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe62e;</i></a>
								</c:if>
								<c:if test="${sessionScope.role.del==2}">
									<a title="删除" href="javascript:;" onclick="account_del(this,'${account.accountNo}')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont" style="font-size:20px;">&#xe6e2;</i></a>
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
	
	var menuList=null;
	$(function(){
		$.ajax({
			url:"<%=basePath%>/menu/obtainMenuList.do",
			type:"POST",
			success:function(result){
				menuList=result;
			}
		});
		
		var autoData=null;
		$("#account_name").keyup(function(){
			var account_name=$(this).val();
			var requestUrl="<%=basePath%>/account/autoSearch.do";
			var requestData={"account":account_name};
			$.ajax({
				url:requestUrl,
				type:"POST",
				data:requestData,
				dataType:"json",
				async:false,
				success:function(result){
					$("#account_name").autocomplete({
					      source: eval(result)
					});
				}
			});
		});
		
	});
	
	/*显示权限分配的弹窗*/
	function allocatePrivilege(accountNo){
		var userRole='${sessionUser.userRole}';
		if(userRole!='1'){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		
		//保存账户拥有权限的菜单
		var privilegeMenu=null;
		$.ajax({
			url:"<%=basePath%>/account/privilegeMenu.do",
			type:"POST",
			data:{"account":accountNo},
			async:false,
			success:function(result){
				privilegeMenu=result;
			}
		});
		var contentData='<div style="padding-left:50px;font-size:18px;padding-top:15px;">';
		for (var i = 0; i <menuList.length; i++) {
			var menuNo=menuList[i].menuNo;
			var menuName=menuList[i].menuName;
			if(checkExist(privilegeMenu,menuNo)){
				contentData+='<span class="privilege_menu_span"><input type="checkbox" value='+menuNo+' checked=true  onclick=selectChildren('+accountNo+',this) />'+menuName+"<br/>";
			}else{
				contentData+='<span class="privilege_menu_span"><input type="checkbox" value='+menuNo+'  onclick=selectChildren('+accountNo+',this) />'+menuName+"<br/>";
			}
			if(menuList[i].subMenuList.length!=0){
				var subMenuList=menuList[i].subMenuList;
				for (var j = 0; j < subMenuList.length; j++) {
					var subMenuNo=subMenuList[j].menuNo;
					var subMenuName=subMenuList[j].menuName;
					if(checkExist(privilegeMenu,subMenuNo)){
						contentData+='&nbsp;&nbsp;&nbsp;<input type="checkbox" checked=true value='+subMenuNo+' onclick=selectPrevious('+accountNo+',this) />'+subMenuName+"<br/>";
					}else{
						contentData+='&nbsp;&nbsp;&nbsp;<input type="checkbox" value='+subMenuNo+' onclick=selectPrevious('+accountNo+',this) />'+subMenuName+"<br/>";
					}
				}
			}
			contentData+="</span>";
		}
		contentData+='</div>';
		 //在这里面输入任何合法的js语句
        layer.open({
            type: 1,
            area: ['15%', '40%'],
            title: '权限管理',
            shade: 0.5,//遮罩透明度
            shadeClose:true,//点击遮罩关闭层
            moveType: 1,//拖拽风格，0是默认，1是传统拖动
            shift: 0,//0-6的动画形式，-1不开启
            fix:true,//不随滚动条滚动，一直在可视区域。
            border:[0],
            content: contentData,
            btn:['确定    ']
        });
	}
	
	//在一个数组中检查是否存在某一个值
	function checkExist(privilegeMenuList,menuNo){
		var status=false;
		for (var i = 0; i < privilegeMenuList.length; i++) {
			if(privilegeMenuList[i]==menuNo)
				status=true;
		}
		return status;
	}
	
	//同步父级菜单的选项
	function selectPrevious(account,current){
		var checkNode=$(current).parent(".privilege_menu_span").children(":first")[0];
		if(current.checked==true){
			var requestData=null;
			if(checkNode.checked==true){
				requestData={"accountNo":account,"menuNo":current.value};
			}else{
				requestData={"accountNo":account,"menuNo":current.value+","+checkNode.value};
			}
			checkNode.checked=true;
			
			//增加菜单权限
			$.ajax({
				url:"<%=basePath%>/account/givePrivilege.do",
				type:"POST",
				data:requestData,
				success:function(result){
					//result true:false
				}
			});
		}else{
			var requestData={"accountNo":account,"menuNo":current.value};
			//取消选中子菜单权限
			$.ajax({
				url:"<%=basePath%>/account/cancelPrivilege.do",
				type:"POST",
				data:requestData,
				success:function(result){
					//result true:false
				}
			});
		}
	}
	
	//同步子菜单选项
	function selectChildren(account,current){
		var inputNode=$(current).parent(".privilege_menu_span").children("input");
		var menuNo="";
		if(current.checked==false){
			for (var i = 1; i < inputNode.length; i++) {
				inputNode[i].checked=false;
				menuNo+=inputNode[i].value+",";
			}
			//要取消权限的菜单编号
			menuNo+=current.value;
			var requestData={"accountNo":account,"menuNo":menuNo};
			//取消选中子菜单权限
			$.ajax({
				url:"<%=basePath%>/account/cancelPrivilege.do",
				type:"POST",
				data:requestData,
				success:function(result){
					//alert(result);
				}
			});
		}else{
			var requestData={"accountNo":account,"menuNo":current.value};
			//增加菜单权限
			$.ajax({
				url:"<%=basePath%>/account/givePrivilege.do",
				type:"POST",
				data:requestData,
				success:function(result){
					//alert(result);
				}
			});
		}
		
	}
	
	/*账户-添加*/
	function account_add(){
		var url='<%=basePath%>/account/showAccountEdit.do';
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你的权限不够!");
			return;
		}
		url+="?viewName=account_edit&operateType=1";
		layer_show('添加账户',url,'','350');
	}
	
	
	/*账户-编辑*/
	function account_edit(id){
		var url="<%=basePath%>/account/showAccountEdit.do";
		var currentAccount='${sessionUser.accountNo}';
		if(currentAccount!=1&&currentAccount!=id){
			layer.msg("很抱歉,你没有权限修改该账户信息!");
			return;
		}
		url+="?viewName=account_edit&operateType=2&account="+id;
		layer_show('编辑账户',url,'','350');
	}
	
	/*账户-批量删除*/
	function accounts_del(){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你没有权限删除账户信息!!");
			return;
		}
		//保存一组菜单编号
		var accountNos="";
		//获取复选框元素
		var accountNosNode=document.getElementsByName("accountNo");
		//保存已删除的一组复选框元素
		var deletedAccountNode=new Array();
		
		/* 代表选中被删除元素的索引 */
		var index=0;
		for (i=0;i<accountNosNode.length;i++){
			if(accountNosNode[i].checked == true){
				//保存待删除的节点
				deletedAccountNode[index]=accountNosNode[i];
				if(i<accountNosNode.length-1){
					if(('${sessionUser.accountNo}')==accountNosNode[i].value){
						layer.msg("很抱歉,暂时不能删除管理员账户!");
						return;
					}
					accountNos+=accountNosNode[i].value+",";
				  }else{
					  accountNos+=accountNosNode[i].value;
				  }
				index++;
			}
		  
		}
		account_del(deletedAccountNode, accountNos);
	}
	/*账户-删除*/
	function account_del(obj,accountNo){
		var userRole='${sessionUser.userRole}';
		if(userRole!=1){
			layer.msg("很抱歉,你没有权限删除账户信息!");
			return;
		}
		
		if(('${sessionUser.accountNo}')==accountNo){
			layer.msg("很抱歉,暂时不能删除管理员账户!");
			return;
		}
		layer.confirm('确认要删除吗？',function(index){
			/* 后台异步执行的结果 */
			var status=false;
			//访问后台的请求路径
			var requestUrl="<%=basePath%>/account/delAccount.do";
			//请求后台的数据
			var requestData={"accountNos":accountNo};
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