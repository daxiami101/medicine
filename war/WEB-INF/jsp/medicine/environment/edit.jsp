<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$("#submit").click(function(){
				//$("#myForm").trigger("submit");
				$("#logManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
			});

		});
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
	<h4 class="modal-title">农户信息</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/medicine/environment/edit" method="post">
	  <form:hidden path="id"/>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">地点</label>
	    <div class="col-sm-10">
	    	<form:input path="location"  cssClass="form-control" value="${pageModel.location }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年份</label>
	    <div class="col-sm-10">
	    	<form:input path="year"  cssClass="form-control" value="${pageModel.year }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">土壤类型</label>
	    <div class="col-sm-10">
	    	<form:input path="soilType"  cssClass="form-control" value="${pageModel.soilType }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">pH值</label>
	    <div class="col-sm-10">
	    	<form:input path="ph"  cssClass="form-control" value="${pageModel.ph }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">水源</label>
	    <div class="col-sm-10">
	    	<form:input path="waterType"  cssClass="form-control" value="${pageModel.waterType }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年降水量(mm)</label>
	    <div class="col-sm-10">
	    	<form:input path="annualPrecipitation"  cssClass="form-control" value="${pageModel.annualPrecipitation }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">无霜期(天)</label>
	    <div class="col-sm-10">
	    	<form:input path="frostlessDay"  cssClass="form-control" value="${pageModel.frostlessDay }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="averageTemperature"  cssClass="form-control" value="${pageModel.averageTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">≧10℃积温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="accumulatedTemperature"  cssClass="form-control" value="${pageModel.accumulatedTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">最冷月平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="monthMinAvgTemperature"  cssClass="form-control" value="${pageModel.monthMinAvgTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">最热月平均气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="monthMaxAvgTemperature"  cssClass="form-control" value="${pageModel.monthMaxAvgTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年绝对最低气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearAbsMinTemperature"  cssClass="form-control" value="${pageModel.yearAbsMinTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年绝对最高气温(℃)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearAbsMaxTemperature"  cssClass="form-control" value="${pageModel.yearAbsMaxTemperature }"/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">年日照时数(小时)</label>
	    <div class="col-sm-10">
	    	<form:input path="yearSunHour"  cssClass="form-control" value="${pageModel.yearSunHour }"/>
	    </div>
	  </div>
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>