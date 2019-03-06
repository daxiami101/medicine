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
	var	province= $("#farmerInfo #provinceId option:selected").text();
	var city=	$("#farmerInfo #cityId option:selected").text();
	var countyId=	$("#farmerInfo #countyId option:selected").text();
	$("farmerInfo #provinceId").val(province);
	$("#farmerInfo #cityId ").val(city);
	$("#farmerInfo #countyId ").val(countyId);
	
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
	});
	
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
	<h4 class="modal-title">新增农户信息</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/medicine/farmer/add" method="post">
<!-- 	  <div class="form-group" id="farmerInfo"> -->
<!-- 	    <label  class="col-sm-2 control-label">省</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:select path="provinceId" cssClass="form-control" onchange="getCitys()" > --%>
<%-- 				<form:option value="">--全部--</form:option> --%>
<%-- 					<c:forEach items="${provinces}" var="province"  varStatus="voStatus"> --%>
<%-- 						<form:option value="${province.provinceId}">${province.provinceName }</form:option> --%>
<%-- 					</c:forEach> --%>
<%-- 			</form:select> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	  <div class="form-group" id="countyInfo"> -->
<!-- 	    <label  class="col-sm-2 control-label">市</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:select path="cityId"  cssClass="form-control" onchange="getCounty()"> --%>
<%-- 			<form:option value="">--请选择--</form:option> --%>
<%-- 		</form:select> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	  <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">县</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:select path="countyId"  cssClass="form-control"> --%>
<%-- 			<form:option value="">--请选择--</form:option> --%>
<%-- 			</form:select> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">地点</label>
	    <div class="col-sm-10">
	    	<form:input path="location"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">农户代码</label>
	    <div class="col-sm-10">
	    	<form:input path="farmerNo"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">农户类型</label>
		<div class="col-sm-10">
			<form:select path="farmerType" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${statuses}" itemLabel="value"/>
			</form:select>
	    </div>
	</div>	
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">种植药材名称</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineName"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">面积（亩）</label>
	    <div class="col-sm-10">
	    	<form:input path="area"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">种植合同号</label>
	    <div class="col-sm-10">
	    	<form:input path="contractNum"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
		<label class="col-sm-2 control-label">合同文件</label>
		<input type="file" id="" name="file" cssClass="form-control" />
	</div>
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>