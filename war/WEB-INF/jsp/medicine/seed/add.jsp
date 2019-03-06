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
	<h4 class="modal-title">新增信息</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/medicine/seed/add" method="post">
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
	    <label  class="col-sm-2 control-label">药材名称</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineId"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">种子批号</label>
	    <div class="col-sm-10">
	    	<form:input path="seedNo"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">拉丁名</label>
	    <div class="col-sm-10">
	    	<form:input path="latinName"  cssClass="form-control" placeholder=""/>
	    </div>
	  </div>
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖材料</label>
		<div class="col-sm-10">
			<form:select path="reproduceMaterial" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproduceMaterials}" itemLabel="value"/>
			</form:select>
	    </div>
	</div>	
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖方式</label>
		<div class="col-sm-10">
			<form:select path="reproduceMethod" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproduceMethods}" itemLabel="value"/>
			</form:select>
	    </div>
	</div>	
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖地点</label>
		<div class="col-sm-10">
			<form:select path="reproducePlace" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproducePlaces}" itemLabel="value"/>
			</form:select>
	    </div>
	</div>	
	  <div class="form-group">	
		<label  class="col-sm-2 control-label">购销方式</label>
		<div class="col-sm-10">
			<form:select path="buySell" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${buySells}" itemLabel="value"/>
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