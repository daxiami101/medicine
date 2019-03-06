<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
<head>
<meta http-equiv="expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script type="text/javascript">
$(function(){
	$("#submit").click(function(){
		$("#logManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});
});

function setUnitValue(id,name){
	$("#unit\\.id").val(id);
	$("#unit\\.name").val(name);
}
function getCitys(){
	var key = $("#farmerInfo #provinceId").val();
	var apiNameSelect= $("#cityId");
	apiNameSelect.empty();
	apiNameSelect.append("<option value=''>--请选择--</option>");

	$.ajax({
		type:'POST',
		dataType:'json',
		url:'${rootUrl}app/medicine/farmer/getCitys/'+key,
		success:function(data){
			var d = eval(data);//json为接收的后台返回的数据；
			$.each(d, function (n, i){
				apiNameSelect.append("<option value='"+i.cityId+"'>"+i.cityName+ "</option>");
			});
		}
	})
}
function getCounty(){
	var key = $("#countyInfo  #cityId").val();
	var apiNameSelect= $("#countyId");
	apiNameSelect.empty();
	apiNameSelect.append("<option value=''>--请选择--</option>");

	$.ajax({
		type:'POST',
		dataType:'json',
		url:'${rootUrl}app/medicine/farmer/getCounty/'+key,
		success:function(data){
			var d = eval(data);//json为接收的后台返回的数据；
			$.each(d, function (n, i){
				apiNameSelect.append("<option value='"+i.countyId+"'>"+i.countyName+ "</option>");
			});
		}
	})
}
</script>
</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">新增环境信息</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/medicine/environment/add" method="post">
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">地点</label>
	    <div class="col-sm-10">
	    	<form:input path="location"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">年份</label>
	    <div class="col-sm-10">
	    	<form:input path="year"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">土壤类型</label>
	    <div class="col-sm-10">
	    	<form:input path="soilType"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">土壤质地</label>
		<div class="col-sm-10">
			<form:select path="soilTexture" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${soilTextures}" itemLabel="value"/>
			</form:select>
	    </div>
	</div>	
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">pH值</label>
	    <div class="col-sm-10">
	    	<form:input path="ph"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
<!-- 	 <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">土壤检测报告</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:input path="soilReport"  cssClass="form-control" placeholder=""/> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">水源</label>
	    <div class="col-sm-10">
	    	<form:input path="waterType"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
<!-- 	 <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">水质检测报告</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:input path="waterReport"  cssClass="form-control" placeholder=""/> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年降水量(mm)</label>
	    <div class="col-sm-10">
	    	<form:input path="annualPrecipitation"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">无霜期(天)</label>
	    <div class="col-sm-10">
	    	<form:input path="frostlessDay"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="averageTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">≧10℃积温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="accumulatedTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">最冷月平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="monthMinAvgTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">最热月平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="monthMaxAvgTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年绝对最低气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearAbsMinTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年绝对最高气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearAbsMaxTemperature"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年日照时数(小时)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearSunHour"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
<!-- 	 <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">气候报告</label> -->
<!-- 	    <div class="col-sm-10"> -->
<!-- 	    	<input type="file" id="" name="file" cssClass="form-control" /> -->
<!-- 	    </div> -->
<!-- 	  </div> -->
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>