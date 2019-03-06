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
		  			<th colspan='4' style="text-align:center">种植任务信息</th>
		  		</tr>
				<tr>
					<th>主要虫害种类</th>
					<td>${fn:escapeXml(model.pestKind) }</td>
					<th>种植药材</th>
					<td>${fn:escapeXml(model.medicineName) }</td>
				</tr>
				<tr>
					<th>危害部位 </th>
					<td>${fn:escapeXml(model.pestPlace) }</td>
					<th>防治开始时间</th>
					<td><javatime:format value="${model.preventStartTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
				</tr>
				<tr>
					<th>防治结束时间 </th>
					<td><javatime:format value="${model.preventEndTime}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
					<th>防治方式</th>
					<td>${fn:escapeXml(model.preventMethod) }</td>
				</tr>
				<tr>
					<th>用药量/亩</th>
					<td>${fn:escapeXml(model.num) }</td>
					<th>计量单位</th>
					<td>${fn:escapeXml(model.unit) }</td>
				</tr>
				<tr>
					<th>农药种类</th>
					<td>${fn:escapeXml(model.pesticideKind) }</td>
					<th>农药采购人员</th>
					<td>${fn:escapeXml(model.purchasePersonName) }</td>
				</tr>
				<tr>
					<th>登记证号</th>
					<td>${fn:escapeXml(model.enrollNo) }</td>
					<th>生产厂家</th>
					<td>${fn:escapeXml(model.company) }</td>
				</tr>
				<tr>
					<th>生产日期</th>
					<td><javatime:format value="${model.produceDate}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
					<th>采购日期</th>
					<td><javatime:format value="${model.purchaseDate}" pattern="yyyy-MM-dd HH:mm:ss"  /></td>
				</tr>
				<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">种源信息</th>
		  		</tr>
		  		<tr>
					<th>种子批号</th>
					<td>${fn:escapeXml(seedModel.seedNo) }</td>
					<th>药材名称</th>
					<td>${fn:escapeXml(seedModel.medicineId) }</td>
				</tr>
		  		<tr>
					<th>拉丁名</th>
					<td>${fn:escapeXml(seedModel.latinName) }</td>
					<th>繁殖材料</th>
					<td>${fn:escapeXml(seedModel.reproduceMaterial.value) }</td>
				</tr>
		  		<tr>
					<th>繁殖方式</th>
					<td>${fn:escapeXml(seedModel.reproduceMethod.value) }</td>
					<th>繁殖地点</th>
					<td>${fn:escapeXml(seedModel.reproducePlace.value) }</td>
				</tr>
		  		<tr>
					<th>购销方式</th>
					<td>${fn:escapeXml(seedModel.buySell.value) }</td>
					<th></th>
					<td></td>
				</tr>
				<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">农户信息</th>
		  		</tr>
				<tr>
					<th>农户代码</th>
					<td>${fn:escapeXml(farmerModel.farmerNo) }</td>
					<th>农户类型</th>
					<td>${fn:escapeXml(farmerModel.farmerType.value) }</td>
				</tr>
				<tr>
					<th>种植药材名称</th>
					<td>${fn:escapeXml(farmerModel.medicineName) }</td>
					<th>种植合同号</th>
					<td>${fn:escapeXml(farmerModel.contractNum) }</td>
				</tr>
				<tr>
					<th>面积（亩）</th>
					<td>${fn:escapeXml(farmerModel.area) }</td>
					<th>数据源</th>
					<td>${fn:escapeXml(farmerModel.dataSource) }</td>
				</tr>
			</table>
	 	</div>
	  	<div class="modal-footer">
	 		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	  	</div>
	</body> 
</html>   
		        
