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
	<h4 class="modal-title">新增饮片生产任务</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl}app/tablet/produce/taskSelect/createTask" method="post">
	  <div class="form-group">
<!-- 	    <label  class="col-sm-2 control-label">采购物料批号</label> -->
<!-- <!-- 	    <div class="col-sm-10"> -->
<%-- <%-- 	    	<form:input path="plantTaskId" type="hidden" cssClass="form-control" placeholder=""  value="${task.id }"/> --%>
<!-- <!-- 	    </div> -->
<!-- 	  </div> -->
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">采购物料批号</label>
	    <div class="col-sm-10">
	    	<form:input path="purchaseNo"  cssClass="form-control" readonly="true" value="${task.purchaseNo }"/>
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">采购原料名称</label>
	    <div class="col-sm-10">
	    	<form:input path="purchaseId"  cssClass="form-control" readonly="true" value="${task.materialKind }"/>
	    </div>
	  </div>
	   <div class="form-group">
	    <label  class="col-sm-2 control-label">执行标准 </label>
	    <div class="col-sm-10">
	    	<form:input path="executeStandard"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">产品名称</label>
	    <div class="col-sm-10">
	    	<form:input path="productionName"  cssClass="form-control" />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">饮片编码 </label>
	    <div class="col-sm-10">
	    	<form:input path="medicineCode"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">炮制方法</label>
	    <div class="col-sm-10">
	    	<form:input path="manufactureMethod"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">饮片加工规格</label>
	    <div class="col-sm-10">
	    	<form:input path="processStandard"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">计量单位</label>
	    <div class="col-sm-10">
	    	<form:input path="unit"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">产品批号</label>
	    <div class="col-sm-10">
	    	<form:input path="produceId"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">领用量(kg)</label>
	    <div class="col-sm-10">
	    	<form:input path="amount"  cssClass="form-control"  />
	    </div>
	  </div>
	  <div class="form-group">
		<label  class="col-sm-2 control-label">生产日期 ：</label>
		<div class="input-group" style="width:130px">
	    	<form:input  path="produceTime"  cssStyle="width:150px" cssClass="form-control" placeholder="必填"/>
		    <span class="input-group-btn">
				<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('produceTime'),dateFmt:'yyyy-MM-dd HH:mm:00'});"><i class="fa fa-calendar"></i></button>
			</span>
		</div>
	</div>
	  <div class="form-group">
	    <label  class="col-sm-2 control-label">生产数量</label>
	    <div class="col-sm-10">
	    	<form:input path="productionNum"  cssClass="form-control" />
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