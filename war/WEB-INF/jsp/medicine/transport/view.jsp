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
		  			<th colspan='4' style="text-align:center">运输信息</th>
		  		</tr>
		  		<tr>
					<th>药材名称</th>
					<td>${fn:escapeXml(model.medicineName) }</td>
					<th>运输方式</th>
					<td>${fn:escapeXml(model.transMethod) }</td>
				</tr>
		  		<tr>
					<th>安保措施</th>
					<td>${fn:escapeXml(model.securityMeasure) }</td>
					<th>安保合同号</th>
					<td>${fn:escapeXml(model.securityContractCode) }</td>
				</tr>
		  		<tr>
					<th>饮片编码</th>
					<td>${fn:escapeXml(model.medicineCode) }</td>
					<th>随货单据号</th>
					<td>${fn:escapeXml(model.companyOrderNo) }</td>
				</tr>
		  		<tr>
					<th>销售时间</th>
					<td><fmt:formatDate value="${model.purchaseDate.time}" pattern="yyyy-MM-dd"/></td>
					<th>运输时间</th>
					<td><fmt:formatDate value="${model.transportTime.time}" pattern="yyyy-MM-dd"/></td>
				</tr>
		  	<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">销售信息</th>
		  		</tr>
		  		<tr>
					<th>储存仓库代码</th>
					<td>${fn:escapeXml(storeModel.storeCode) }</td>
					<th>储存条件 </th>
					<td>${fn:escapeXml(storeModel.storeCondition) }</td>
				</tr>
		  		<tr>
					<th>贮藏方式</th>
					<td>${fn:escapeXml(storeModel.storeMethod) }</td>
					<th>存储措施</th>
					<td>${fn:escapeXml(storeModel.measure) }</td>
				</tr>
		  		<tr>
					<th>药材名称</th>
					<td>${fn:escapeXml(storeModel.medicineName) }</td>
					<th></th>
					<td></td>
				</tr>
		  	<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">采收信息</th>
		  		</tr>
		  		<tr>
					<th>采收部位</th>
					<td>${fn:escapeXml(harvestModel.harvestPart) }</td>
					<th>采收药材 </th>
					<td>${fn:escapeXml(harvestModel.harvestMedicine) }</td>
				</tr>
				<tr>
					<th>采收批号</th>
					<td>${fn:escapeXml(harvestModel.harvestNo) }</td>
					<th>采收时间</th>
					<td><fmt:formatDate value="${harvestModel.harvestTime.time}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>采收方式</th>
					<td>${fn:escapeXml(harvestModel.harvestMethod) }</td>
					<th>采收面积(亩)</th>
					<td>${fn:escapeXml(harvestModel.area) }</td>
				</tr>
				<tr>
					<th>采收产量</th>
					<td>${fn:escapeXml(harvestModel.production) }</td>
					<th>药材名称</th>
					<td>${fn:escapeXml(harvestModel.medicineName) }</td>
				</tr>
		  	<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">加工信息</th>
		  		</tr>
		  		<tr>
					<th>加工等级</th>
					<td>${fn:escapeXml(processModel.processLevel) }</td>
					<th>加工方法</th>
					<td>${fn:escapeXml(processModel.processMethod) }</td>
				</tr>
		  		<tr>
					<th>加工前重量(kg)</th>
					<td>${fn:escapeXml(processModel.preProcessQuality) }</td>
					<th>非药用部位重量(kg)</th>
					<td>${fn:escapeXml(processModel.nonMediQuality) }</td>
				</tr>
		  		<tr>
					<th>加工后总重量(kg)</th>
					<td>${fn:escapeXml(processModel.postProcessQuality) }</td>
					<th>采收批号</th>
					<td>${fn:escapeXml(processModel.harvestNo) }</td>
				</tr>
		  		<tr>
					<th>药材名称</th>
					<td>${fn:escapeXml(processModel.medicineName) }</td>
					<th>种植批号</th>
					<td>${fn:escapeXml(processModel.taskNo) }</td>
				</tr>
		  	 <tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">包装信息</th>
		  		</tr>
				<tr>
					<th>包装时间 </th>
					<td><fmt:formatDate value="${processPackageModel.packageDate.time}" pattern="yyyy-MM-dd"/></td>
					<th>包装规格</th>
					<td>${fn:escapeXml(processPackageModel.standard) }</td>
				</tr>
				<tr>
					<th>包装重量</th>
					<td>${fn:escapeXml(processPackageModel.weight) }</td>
					<th>包装材料</th>
					<td>${fn:escapeXml(processPackageModel.packageMaterial) }</td>
				</tr>
				<tr>
					<th>品名</th>
					<td>${fn:escapeXml(processPackageModel.name) }</td>
					<th>等级</th>
					<td>${fn:escapeXml(processPackageModel.level) }</td>
				</tr>
				<tr>
					<th>产地</th>
					<td>${fn:escapeXml(processPackageModel.place) }</td>
					<th>采收日期</th>
					<td><fmt:formatDate value="${processPackageModel.harvestDate.time}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>包装批次号</th>
					<td>${fn:escapeXml(processPackageModel.packageNo) }</td>
					<th>生产单位</th>
					<td>${fn:escapeXml(processPackageModel.produceCom) }</td>
				</tr>
				<tr>
					<th>药材名称</th>
					<td>${fn:escapeXml(processPackageModel.medicineName) }</td>
					<th>采收批号</th>
					<td>${fn:escapeXml(processPackageModel.harvestNo) }</td>
				</tr>
				<tr>
					<th>加工批号</th>
					<td>${fn:escapeXml(processPackageModel.processNo) }</td>
					<th></th>
					<td></td>
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
		        
