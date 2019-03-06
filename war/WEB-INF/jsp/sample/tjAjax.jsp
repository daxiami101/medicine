<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script type="text/javascript">
$(function(){
	$.fn.showPopover.defaults.placement="right";
	//后台返回错误，可以添加监听事件处理
	$("#myManage").taiji().on("taijiCVE",function(event,responseText){
		$.Taiji.alert({content:"提交"+event.relatedTarget.attr("id")+"出错了！<br>"+responseText});
	}).on("taijiOperateSuccess",function(event,responseText){
		$("#result").find("p").css("transition","all 1s").css("padding-left","40%");
	}).on("taijiAjaxFormSuccess",function(event,responseText){
		$.Taiji.alert({content:"taijiAjaxFormSuccess!!!"});
	});
	
	//AjaxHref示例
	$("#tjAjaxHrefBtn").click(function(){
		$("#myManage").taiji("ajaxHref",this,
			   {async:false,
				bsSuccess:function(responseText,note){
					$.Taiji.showNote(note);
					alert("bsSuccess");
		      	},
			    complete:function(){
			       alert("complete");
			    },
			    type:'POST',
			    dataType:'text'
		   });
			alert("ok");
		   return false;
		});

	//AjaxForm示例
	$("#tjAjaxFormBtn").click(function(){
		$("#myManage").taiji("ajaxForm",$("#myForm"),{bsSuccess:function(responseText,note){
			$.Taiji.showNote(note);
	      },
	      async:false,
	      complete:function(){
		    }
		});
		return false;
	});
});

</script>
<style>
.required-star{
	color: #f30;
    font-family: simsun;
    margin-left: 1em;
    position: absolute;
}
</style>
</head>
<body >
	<!-- begin #page-container -->
	<div id="page-container" class="fade in">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #content -->
		<div class="content" style="margin: 0px 180px;">
			<div class="row">
			<div class="col-md-12">
				 <div id="myManage" class="well">
					<div class="modal-header">
						<h4 class="modal-title">tjAjax示例</h4>
					</div>
					<div class="modal-body">
<form  id="myForm" name="myForm" class="form-horizontal"  action="${rootUrl }app/sample/tjAjax/add" method="post">
  	<div class="form-group"> 
	    <label  class="col-sm-2 control-label">登录名<span class="required-star">*</span></label>
	    <div class="col-sm-10">
	    	<input type="text" name="loginName"  class="form-control" placeholder="登录名必填" size="50" style="width: 550px"/>
	    </div>
	</div>
  	<div class="form-group"> 
	    <label  class="col-sm-2 control-label">姓名<span class="required-star">*</span></label>
	    <div class="col-sm-10">
	    	<input type="text" name="name"  class="form-control" placeholder="姓名必填"  style="width: 550px"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">性别</label>
	    <div class="col-sm-10">
		  <select name="male" title="请选择性别"  style="width: 150px">
				<option value="true">男</option>
				<option value="false">女</option>
			</select>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">爱好</label>
	    <div class="col-sm-10">
		 	<div class="checkbox-inline ">
				<input type="checkbox" name="checkbox"  class="magic-checkbox" value="check1" id="check1"><label for="check1">篮球</label>
	         </div>	
	         <div class="checkbox-inline">
				<input type="checkbox" name="checkbox" class="magic-checkbox"  value="check2" checked="checked" id="check2"><label for="check2">下棋</label>
	         </div>
	         <div class="checkbox-inline">
				<input type="checkbox" name="checkbox"  class="magic-checkbox"  value="check2" checked="checked" id="check3"><label for="check3">书画</label>
	         </div>
	    </div>
	</div>	  	  
	<div class="form-group">
	    <label  class="col-sm-2 control-label">电子邮箱</label>
	    <div class="col-sm-10">
	    	<input type="text" name="email"  class="form-control" placeholder="XXX@XXX.XXX" style="width: 550px"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">手机<span class="required-star">*</span></label>
	    <div class="col-sm-10">
	    	<input type="text" name="mobile"  class="form-control" placeholder="13XXXXXXXXXXX" style="width: 550px"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">电话</label>
	    <div class="col-sm-10">
	    	<input type="text" name="tel"  class="form-control"  style="width: 550px"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">传真</label>
	    <div class="col-sm-10">
	    	<input type="text" name="fax"  class="form-control" style="width: 550px" />
	    </div>
	</div>
	<div id="result" style="margin:10px;color:green;font-weight: bold;">
	</div>
</form> 
					</div>
					<div class="modal-footer" style="text-align: center;">
						<a href="#" class="btn btn-sm btn-white"   id="closeBtn">关闭</a>
						<a href="${rootUrl }app/sample/tjAjax/add" data-selector=":text,:checkbox:checked,select" class="btn btn-sm btn-success"  id="tjAjaxHrefBtn">ajaxHref</a>
						<a href="#" class="btn btn-sm btn-success"  id="tjAjaxFormBtn">ajaxForm</a>
						<a href="${rootUrl }app/sample/tjAjax/add" data-selector=":text,:checkbox:checked,select" class="taiji_operate {confirm_message:'你确定操作吗？？？',target:'#result'} btn btn-sm btn-success" id="taijiOperateBtn">taiji_operate</a>
						<a href="#" data-form="#myForm" class="taiji_ajaxForm {confirm_message:'你确定提交操作吗?',target:'#result'} btn btn-sm btn-success" id="taijiAjaxformBtn">taiji_ajaxForm</a>
						</div>
		         </div>  
			</div>
			</div>
			</div>
		<!-- end #content -->
		
					
	</div>
	<!-- end page container -->
	
	

</body>
</html>
