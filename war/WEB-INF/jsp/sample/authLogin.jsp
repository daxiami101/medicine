<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({ cache: false}); 
			$("#submit").click(function(){
				$("#myForm").ajaxSubmit({
					success : function(data, status, xhr) {
						$.Taiji.showNote("模拟登录成功后跳转");
						$(".taiji_auth.authing").click();
						$.hideModal();
					}
				});
			});

		});
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">模拟登录</h4>
</div>
<div class="modal-body">
	<form   id="myForm" name="myForm" class="form-horizontal" action="${rootUrl }app/sample/auth/updateAuth" method="post">
	  <div class="form-group">
	    <label  class="col-sm-3 control-label">登录名</label>
	    <div class="col-sm-9">
	    	<input type="text"  name="loginName" class="form-control"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-3 control-label">密码</label>
	    <div class="col-sm-9">
	    	<input type="password"  name="password"  class="form-control"/>
	    </div>
	  </div>
	
   </form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>