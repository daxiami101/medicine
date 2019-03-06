<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html>
	<head>
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript">
		$(function(){
			$.ajaxSetup ({ cache: false}); 
			$("#submit").click(function(){
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
			});
		});
		function getApiNames(){
			var key = $("#myPlatName #key").val();
			var apiNameSelect= $("#apiName");
			apiNameSelect.empty();
			apiNameSelect.append("<option value=''>--请选择--</option>");
	
			$.ajax({
				type:'POST',
				dataType:'json',
				url:'${rootUrl}app/info/infoServiceInfoUser/getApiNames/'+key,
				success:function(data){
					var d = eval(data);//json为接收的后台返回的数据；
					console.log(d);
					$.each(d, function (n, i){
						apiNameSelect.append("<option value='"+i+"'>"+i+ "</option>");
					});
				}
			})
		}
		
		function getNames(){
			var apiName=$("#apiName").val();
			alert(apiName);
		}
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">添加</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" 
	action="${rootUrl}app/tablet/purchase/add" method="post">
	
<!-- 	<div class="form-group" id="myPlatName"> -->
<!-- 	   <label class="col-sm-2 control-label" style="margin-right: 15px">平台名称</label> -->
<%-- 		<form:select path="key"  cssClass="form-control" onchange="getApiNames()"  style="width: 150px"> --%>
<%-- 			<form:option value="">--请选择--</form:option> --%>
<%-- 				<c:forEach items="${infoUsers}" var="infoUser"  varStatus="voStatus"> --%>
<%-- 					<form:option value="${infoUser.key}" >${infoUser.platName}</form:option> --%>
<%-- 				</c:forEach> --%>
<%-- 		</form:select> --%>
<!-- 	</div>	   -->
<!-- 	<div class="form-group"> -->
<!-- 	   <label class="col-sm-2 control-label" style="margin-right: 15px">接口名称</label> -->
<%-- 		<form:select path=""  cssClass="form-control" style="width: 150px"> --%>
<%-- 			<form:option value="">--请选择--</form:option> --%>
<%-- 		</form:select> --%>
<!-- 	</div>	  -->
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">原料名称</label>
		<form:input path="materialKind" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">物料名称</label>
		<form:input path="materialName" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">药材编码</label>
		<form:input path="medicineCode" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">产地</label>
		<form:input path="level" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">等级</label>
		<form:input path="standard" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">规格 </label>
		<form:input path="orderId" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">到货单据号</label>
		<form:input path="companyOrderId" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">计量单位</label>
		<form:input path="unit" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">采购数量</label>
		<form:input path="purchaseNum" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">随货同行单据号</label>
		<form:input path="companyOrderId" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div> 
	<div class="form-group">
		<label  class="col-sm-2 control-label">加工日期   ：</label>
		<div class="input-group" style="width:130px">
	    	<form:input  path="purchaseTime"  cssStyle="width:150px" cssClass="form-control" placeholder="必填"/>
		    <span class="input-group-btn">
				<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('purchaseTime'),dateFmt:'yyyy-MM-dd HH:mm:00'});"><i class="fa fa-calendar"></i></button>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label" style="margin-right: 15px">供应商</label>
		<form:input path="providerName" cssClass="form-control"  style="width: 150px" size="20" maxlength="20" />
	</div>
<!-- 	<div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">详细信息</label> -->
<!-- 	    <div class="col-sm-10"> -->
<!-- 	    	<textarea rows="10" cols="120" name="context" id="context"></textarea> -->
<!-- 	    </div> -->
<!-- 	</div> -->
   </form:form> 
</div>
<div class="modal-footer">
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
	<a href="#" class="btn btn-sm btn-success"  id="submit">保存</a>
</div>

</body>
</html>