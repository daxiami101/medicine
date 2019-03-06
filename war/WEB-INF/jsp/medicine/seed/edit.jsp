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
	<h4 class="modal-title">种源信息</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/medicine/seed/edit" method="post">
	  <form:hidden path="id"/>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">药材名称</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineId"  cssClass="form-control" value="${pageModel.medicineId }"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">种子批号</label>
	    <div class="col-sm-10">
	    	<form:input path="seedNo"  cssClass="form-control" readonly="true" value="${pageModel.seedNo }"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">拉丁名</label>
	    <div class="col-sm-10">
	    	<form:input path="latinName"  cssClass="form-control" placeholder="" value="${pageModel.latinName }"/>
	    </div>
	  </div>	
	   <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖材料</label>
		<div class="col-sm-10">
			<form:select path="reproduceMaterial" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproduceMaterials}" itemLabel="value" />${pageModel.reproduceMaterial}
			</form:select>
	    </div>
	</div>	
	   <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖方式</label>
		<div class="col-sm-10">
			<form:select path="reproduceMethod" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproduceMethods}" itemLabel="value" />${pageModel.reproduceMethod }
			</form:select>
	    </div>
	</div>	
	   <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖地点</label>
		<div class="col-sm-10">
			<form:select path="reproducePlace" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproducePlaces}" itemLabel="value" />${pageModel.reproducePlace }
			</form:select>
	    </div>
	</div>	
	   <div class="form-group">	
		<label  class="col-sm-2 control-label">购销方式</label>
		<div class="col-sm-10">
			<form:select path="buySell" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${buySells}" itemLabel="value" />${pageModel.buySell }
			</form:select>
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