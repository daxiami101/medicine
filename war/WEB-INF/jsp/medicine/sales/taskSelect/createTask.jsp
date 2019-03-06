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
		$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
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
	<h4 class="modal-title">新增销售任务</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl}app/medicine/sales/taskSelect/createTask" method="post">
	  <div class="form-group">
	    <div class="col-sm-10">
	    	<form:input path="storeId" type="hidden" cssClass="form-control" placeholder=""  value="${task.id }"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">药材名称</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineName"  cssClass="form-control" readonly="true" value="${task.medicineName }"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">客户名称</label>
	    <div class="col-sm-10">
	    	<form:input path="clientName"  cssClass="form-control" />
	    </div>
	  </div>
	  <div class="form-group">
		<label  class="col-sm-2 control-label">销售日期</label>
		<div class="input-group" style="width:130px">
	    	<form:input  path="soldTime"  cssStyle="width:150px" cssClass="form-control" placeholder="必填"/>
		    <span class="input-group-btn">
				<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('soldTime'),dateFmt:'yyyy-MM-dd HH:mm:00'});"><i class="fa fa-calendar"></i></button>
			</span>
		</div>
	</div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">药材编码</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineCode"  cssClass="form-control" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">商务部品种代码</label>
	    <div class="col-sm-10">
	    	<form:input path="commerceDepartCode"  cssClass="form-control"/>
	    </div>
	  </div>
	  
	<div class="form-group">
	    <label  class="col-sm-2 control-label">销售数量</label>
	    <div class="col-sm-10">
	    	<form:input path="soldNum"  cssClass="form-control" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">企业条形码</label>
	    <div class="col-sm-10">
	    	<form:input path="barcode"  cssClass="form-control" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">台湾地区代码</label>
	    <div class="col-sm-10">
	    	<form:input path="taiwanCode"  cssClass="form-control" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">海关码</label>
	    <div class="col-sm-10">
	    	<form:input path="customCode"  cssClass="form-control" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">同行随货单号</label>
	    <div class="col-sm-10">
	    	<form:input path="companyOrderId"  cssClass="form-control" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">退货状态</label>
	    <div class="col-sm-10">
	    	<form:input path="returnStatus"  cssClass="form-control" />
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