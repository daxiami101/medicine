<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h4 class="modal-title">二维码</h4>
		</div>
	 	<div class="modal-body">
		  	<table class="table table-bordered">
		  	<tr style="background:#f0f3f5">
		  			<th style="text-align:center">二维码信息</th>
		  		</tr>
				<tr>
				<td style="align:center"><img style="align:center" width="400" height="400" src='data:image/jpg;base64,${photoSrc }'/></td>
				</tr>
			</table>
	 	</div>
	  	<div class="modal-footer">
	 		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	  	</div>
	</body> 
</html>   
		        
