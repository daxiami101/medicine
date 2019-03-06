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
			<h4 class="modal-title">明细--查看</h4>
		</div>
	 	<div class="modal-body">
		  	<table class="table table-bordered">
		  	 <tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">种源管理</th>
		  		</tr>
		  		<tr>
					<th>药材名称</th>
					<td>${fn:escapeXml(model.medicineId ) }</td>
					<th>拉丁名</th>
					<td>${fn:escapeXml(model.latinName) }</td>
				</tr>
				<tr>
					<th>繁殖材料</th>
					<td>${fn:escapeXml(model.reproduceMaterial.value) }</td>
					<th>繁殖方式</th>
					<td style="word-break: break-all;">${fn:escapeXml(model.reproduceMethod.value) }</td>
				</tr>
				<tr>
					<th>繁殖地点</th>
					<td>${fn:escapeXml(model.reproducePlace.value) }</td>
					<th>购销方式</th>
					<td style="word-break: break-all;">${fn:escapeXml(model.buySell.value) }</td>
				</tr>
				<tr>
					
					<th>入库时间</th>
					<td><javatime:format value="${model.insertTime}" pattern="yyyy-MM-dd HH:mm:ss"  />	
					</td>
					<th></th>
					<td></td>
				</tr>
			</table>
	 	</div>
	  	<div class="modal-footer">
	 		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	  	</div>
	</body> 
</html>   
		        
