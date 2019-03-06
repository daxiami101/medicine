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
		  			<th colspan='4' style="text-align:center">产地环境信息</th>
		  		</tr>
				<tr>
					<th>地点</th>
					<td>${fn:escapeXml(model.location) }</td>
					<th>年份</th>
					<td style="word-break: break-all;">${fn:escapeXml(model.year) }</td>
				</tr>
				<tr>
					<th>土壤类型</th>
					<td>${fn:escapeXml(model.soilType) }</td>
					<th>土壤质地</th>
					<td style="word-break: break-all;">${fn:escapeXml(model.soilTexture.value) }</td>
				</tr>
				<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">土壤信息</th>
		  		</tr>
				<tr>
					<th>pH值</th>
					<td>${fn:escapeXml(model.ph) }</td>
					<th></th>
					<td style="word-break: break-all;"></td>
				</tr>
				<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">水源信息</th>
		  		</tr>
		  		<tr>
					<th>水源</th>
					<td>${fn:escapeXml(model.waterType) }</td>
					<th>水质检测报告</th>
					<td>${fn:escapeXml(model.waterReport) }</td>
				</tr>
		  		<tr style="background:#f0f3f5">
		  			<th colspan='4' style="text-align:center">气候信息</th>
		  		</tr>
				<tr>
					<th>年降水量(mm)</th>
					<td>${fn:escapeXml(model.annualPrecipitation) }</td>
					<th>无霜期(天)</th>
					<td>${fn:escapeXml(model.frostlessDay) }</td>
				</tr>
				<tr>
					<th>年平均气温(℃)</th>
					<td>${fn:escapeXml(model.averageTemperature) }</td>
					<th>≧10℃积温(℃)</th>
					<td>${fn:escapeXml(model.accumulatedTemperature) }</td>
				</tr>
				<tr>
					<th>最冷月平均气温(℃)</th>
					<td>${fn:escapeXml(model.monthMinAvgTemperature) }</td>
					<th>最热月平均气温(℃)</th>
					<td>${fn:escapeXml(model.monthMaxAvgTemperature) }</td>
				</tr>
				<tr>
					<th>年绝对最低气温(℃)</th>
					<td>${fn:escapeXml(model.yearAbsMinTemperature) }</td>
					<th>年绝对最高气温(℃)</th>
					<td>${fn:escapeXml(model.yearAbsMaxTemperature) }</td>
				</tr>
				<tr>
					<th>年日照时数(小时)</th>
					<td>${fn:escapeXml(model.yearSunHour) }</td>
					<th>气候报告</th>
					<td>${fn:escapeXml(model.climateReport) }</td>
				</tr>
			</table>
	 	</div>
	  	<div class="modal-footer">
	 		<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	  	</div>
	</body> 
</html>   
		        
