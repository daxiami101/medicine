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
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/land/seedHandle/edit" method="post">
	  <form:hidden path="id" value="${model.id }"/>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">种植批号</label>
	    <div class="col-sm-10">
	    	<form:input path="taskNo"  readonly="readonly"   cssClass="form-control" value="${model.taskNo }" />
	    </div>
	  </div>
	 <div class="form-group">
	    <label  class="col-sm-2 control-label">药材名称</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineName"  readonly="readonly"   cssClass="form-control" value="${model.medicineName }" />
	    </div>
	  </div>
	   <div class="form-group">	
		<label  class="col-sm-2 control-label">繁殖材料</label>
		<div class="col-sm-10">
			<form:select path="reproduceMaterial" cssClass="form-control" >
				<form:option value="">--请选择--</form:option>
				<form:options items="${reproduceMaterials}" itemLabel="value" value="${pageModel.reproduceMaterial }"/>
			</form:select>
	    </div>
	</div>	
	<div class="form-group">
	    <label  class="col-sm-2 control-label">处理方式</label>
	    <div class="col-sm-10">
	    	<form:input path="handleMethod"    cssClass="form-control" value="${model.handleMethod }" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">处理说明</label>
	    <div class="col-sm-10">
	    	<form:input path="handleNote"    cssClass="form-control" value="${model.handleNote }" />
	    </div>
	  </div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">处理时间</label>
	    <div class="col-sm-10">
	    	<form:input path="handleTime"    cssClass="form-control" value="${model.handleTime }" />
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