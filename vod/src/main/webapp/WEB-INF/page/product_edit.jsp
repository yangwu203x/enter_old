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
			<form action="<%=basePath%>/product/${requestUrl}.do" method="post" class="form form-horizontal" id="edit_form" onsubmit="return requestForm()">
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>mac地址:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${product.mac_address}" 
							id="mac_address" name="mac_address">
					</div>
				</div>
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>序列号:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text"  value="${product.serial_number}" 
							id="serial_number" name="serial_number">
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>生产时间:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text Wdate radius"  id="delivery_time" value="${product.delivery_time}"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:100%"/>   
					</div>
				</div>
				
				<div class="row cl">
					<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>销售时间:</label>
					<div class="formControls col-xs-8 col-sm-9">
						<input type="text" class="input-text Wdate radius"  id="sale_time" value="${product.sale_time}"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:100%"/>   
					</div>
				</div>
				
				<div class="row cl">
					<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
						<input type="hidden" id="product_id" value="${product.product_id}">
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
	
		function requestForm(){
			var status=false;
			//封装请求数据对象
			var requestData=null;
			
			var macAddress=$("#mac_address").val();
			if(macAddress==null||macAddress==''){
				layer.msg("请输入mac地址!");
				return false;
			}
			
			var serialNum=$("#serial_number").val();
			if(serialNum==null||serialNum==''){
				layer.msg("请输入序列号!");
				return false;
			}
			
			var deliveryTime=$("#delivery_time").val();
			if(deliveryTime==null||deliveryTime==''){
				layer.msg("请选择生产时间!");
				return false;
			}
			deliveryTime=deliveryTime.replace(/\s/g,"|");
			var saleTime=$("#sale_time").val();
			if(saleTime==null||saleTime==''){
				layer.msg("请选择销售时间!");
				return false;
			}
			saleTime=saleTime.replace(/\s/g,"|");
			var productId=$("#product_id").val();
			//如果不存在编号则是新增,否则的话是修改
			if(productId!=''){
				requestData={"mac_address":macAddress,"serial_number":serialNum,"delivery_time":deliveryTime,"sale_time":saleTime,"product_id":productId};
			}else{
				requestData={"mac_address":macAddress,"serial_number":serialNum,"delivery_time":deliveryTime,"sale_time":saleTime};
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
				layer.msg("请确认输入的mac地址和序列号已经在用户信息中存在!");
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