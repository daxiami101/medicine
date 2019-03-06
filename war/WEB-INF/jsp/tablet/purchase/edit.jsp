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
				$("#myManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"edit"}]);
			});

			//$('.selectpicker').selectpicker('render');
		});
		</script>
	</head>
<body>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">采购管理--修改</h4>
</div>
<div class="modal-body">
	<form:form modelAttribute="pageModel" id="myForm" name="myForm" cssClass="form-horizontal" action="${rootUrl }app/tablet/purchase/edit" method="post">
	 <form:hidden path="id" />
<%-- 	 <form:hidden path="loginName" /> --%>
<!-- 	  <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">登录名</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<p class="form-control-static">${pageModel.loginName }</p> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
<!-- 	  <div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">角色</label> -->
<!-- 	    <div class="col-sm-10"> -->
<%-- 	    	<form:select path="roleId" class="selectpicker" title="请选择。。。"  data-style="btn-warning" data-live-search="true" data-size="10"> --%>
<%-- 				<form:option value=""></form:option> --%>
<%-- 				<form:options items="${roles}" itemLabel="name"  itemValue="id"/> --%>
<%-- 			</form:select> --%>
<!-- 	    </div> -->
<!-- 	  </div> -->
	
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">原料名称</label>
	    <div class="col-sm-10">
	    	<form:input path="materialKind" cssClass="form-control" value="${model.materialKind}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">物料名称</label>
	    <div class="col-sm-10">
	    	<form:input path="materialName" cssClass="form-control" value="${model.materialName}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">药材编码</label>
	    <div class="col-sm-10">
	    	<form:input path="medicineCode" cssClass="form-control" value="${model.medicineCode}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">产地</label>
	    <div class="col-sm-10">
	    	<form:input path="province" cssClass="form-control" value="${model.province}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">等级</label>
	    <div class="col-sm-10">
	    	<form:input path="level" cssClass="form-control" value="${model.level}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">规格</label>
	    <div class="col-sm-10">
	    	<form:input path="standard" cssClass="form-control" value="${model.standard}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">到货单据号</label>
	    <div class="col-sm-10">
	    	<form:input path="orderId" cssClass="form-control" value="${model.orderId}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">随货同行单据号</label>
	    <div class="col-sm-10">
	    	<form:input path="companyOrderId" cssClass="form-control" value="${model.companyOrderId}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">计量单位</label>
	    <div class="col-sm-10">
	    	<form:input path="unit" cssClass="form-control" value="${model.unit}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">采购数量(个)</label>
	    <div class="col-sm-10">
	    	<form:input path="purchaseNum" cssClass="form-control" value="${model.purchaseNum}"/>
	    </div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">采收日期</label>
		<div class="input-group" style="width:130px">
	    	<form:input  path="purchaseTime"  cssStyle="width:150px" cssClass="form-control" placeholder="必填"/>
		    <span class="input-group-btn">
				<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('purchaseTime'),dateFmt:'yyyy-MM-dd HH:mm:00'});"><i class="fa fa-calendar"></i></button>
			</span>
		</div>
	</div>
  	<div class="form-group">
	    <label  class="col-sm-2 control-label">供货商</label>
	    <div class="col-sm-10">
	    	<form:input path="providerName" cssClass="form-control" value="${model.providerName}"/>
	    </div>
	</div>
<!-- 	<div class="form-group"> -->
<!-- 	    <label  class="col-sm-2 control-label">性别</label> -->
<!-- 	    <div class="col-sm-10"> -->
<!-- 	    	<label class="radio-inline"> -->
<%-- 		    	<form:radiobutton path="male" value="true"  label="男" /> --%>
<!--             </label> -->
<!-- 	    	<label class="radio-inline"> -->
<%-- 				<form:radiobutton path="male" value="false" label="女"/> --%>
<!--             </label> -->
<!-- 	    </div> -->
<!-- 	</div>	   -->
	<div class="form-group">
	    <label  class="col-sm-2 control-label">电子邮箱</label>
	    <div class="col-sm-10">
	    	<form:input path=""  cssClass="form-control"  maxlength="50" placeholder="XXX@XXX.XXX"/>
	    </div>
	</div>
	<div class="form-group">
	    <label  class="col-sm-2 control-label">传真</label>
	    <div class="col-sm-10">
	    	<form:input path=""  cssClass="form-control" maxlength="50"/>
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