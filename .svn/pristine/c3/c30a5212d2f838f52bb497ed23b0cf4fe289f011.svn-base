<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript">
$(function(){
	$.fn.showPopover.defaults.placement="right";
	$("#rootwizard").taiji();
	$('#rootwizard').bootstrapWizard({
			'nextSelector': '.btn-next',
			'previousSelector': '.btn-previous', 
			'finishSelector': '.btn-finish',
	  		'tabClass': 'nav nav-pills',
	  		'onNext': function(tab, navigation, index) {
	  			if(index==1) {
	  				$("#rootwizard").taiji("ajaxHref",$("#step1"),
	  					   {async:false,
	  						bsSuccess:function(responseText,note){
	  							$('#rootwizard').bootstrapWizard("show",1);
	  				      }
	  				   });
	  				return false;
	  			}
	  		}
		});
	
	$('#button-finish').click(function() {
		alert('Finished!, Starting over!');
	});
});

</script>
<style>
	#rootwizard ul{
		display: table;
		width: 100%;
	}
	#rootwizard ul>li {
	    background: #efefef none repeat scroll 0 0;
	    display: table-cell;
	    line-height: 18px;
	    list-style: outside none none;
	    margin-right: 5px;
	    padding: 12px 17px 10px 30px;
	    position: relative;
	    float: none;
	}
	#rootwizard li.active, #rootwizard li.active:focus, #rootwizard li.active:hover {
   		 background: #00acac none repeat scroll 0 0 !important;
	}
	
	#rootwizard li a {
		background-color: transparent;
	    display: block !important;
	    text-decoration: none !important;
	    color: #333;
	}
	#rootwizard li.active a {
	    color: #fff;
	    cursor: default;
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
		<div class="content" style="margin: 0px 180px">
			<div class="row">
			<div class="col-md-12">
				 <div id="myOpen" class="well">
					<div class="modal-header">
						<h4 class="modal-title">wizard示例</h4>
					</div>
					<div class="modal-body">
<div id="rootwizard" >			
	<ul class="nav nav-pills">
	  	<li class="active">
	  		<a href="#tab1" data-toggle="tab">第一步</a>
	  	</li>
		<li><a href="#tab2" data-toggle="tab">第二步</a></li>
		<li><a href="#tab3" data-toggle="tab">第三步</a></li>
		<li><a href="#tab4" data-toggle="tab">第四步</a></li>
	</ul>
<form  id="myForm" name="myForm" class="form-horizontal"  action="${rootUrl }app/sample/tjAjax/add" method="post">
	<div class="tab-content">
	    <div class="tab-pane active" id="tab1">
		    <div class="form-group"> 
			    <label  class="col-sm-2 control-label">登录名</label>
			    <div class="col-sm-10">
			    	<input type="text" id="loginName" name="loginName"  class="form-control" placeholder="登录名必填" size="50" style="width: 550px;display: inline-block;"/>
			    	<a href="${rootUrl }app/sample/wizard/checkStep1"  class="taiji_modal  btn  btn-success m-r-10" data-selector="#tab1 :text,#tab1 :password">Modal校验</a>
			    </div>
			</div>
		    <div class="form-group"> 
			    <label  class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-10">
			    	<input type="text" name="password"  class="form-control"  size="50" style="width: 550px"/>
			    </div>
			</div>
			<a id="step1" href="${rootUrl }app/sample/wizard/checkStep1" data-selector="#tab1 :text,#tab1 :password"></a>
	    </div>
	    <div class="tab-pane" id="tab2">
	      <div class="form-group"> 
			    <label  class="col-sm-2 control-label">姓名</label>
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
	    </div>
		<div class="tab-pane" id="tab3">
			<div class="form-group">
			    <label  class="col-sm-2 control-label">爱好</label>
			    <div class="col-sm-10">
				 	<div class="checkbox-inline ">
						<input type="checkbox" name="checkbox"  value="check1">篮球
			         </div>	
			         <div class="checkbox-inline">
						<input type="checkbox" name="checkbox"  value="check2" checked="checked">下棋
			         </div>
			         <div class="checkbox-inline">
						<input type="checkbox" name="checkbox"   value="check2" checked="checked">书画
			         </div>
			    </div>
			</div>	  	
	    </div>
		<div class="tab-pane" id="tab4">
			<div class="form-group">
			    <label  class="col-sm-2 control-label">电子邮箱</label>
			    <div class="col-sm-10">
			    	<input type="text" name="email"  class="form-control" placeholder="XXX@XXX.XXX" style="width: 550px"/>
			    </div>
			</div>
			<div class="form-group">
			    <label  class="col-sm-2 control-label">手机</label>
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
	    </div>
	    <div style="text-align: center;">
	    	<a href="#" class='btn btn-white btn-previous'>上一步</a>
	    	<a href="#" class='btn btn-success btn-next'>下一步</a>
	    	<a href="#" class='btn btn-success btn-finish' id="button-finish">提交</a>
		</div>
	</div>
</form>  
</div>		<!-- end #rootwizard -->
					</div>  <!--  end modal-body -->
		         </div>  
			</div>
			</div>
			</div>
		<!-- end #content -->
		
					
	</div>
	<!-- end page container -->
	
	

</body>
</html>
