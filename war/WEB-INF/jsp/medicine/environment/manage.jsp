<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/datepicker/4.8/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#logManage").taiji({
		enableAclCheck:true,
		search:{
			 autoSearch:false,
			 autoRefresh : {
				    enable : false,
				    interval : 10000
				   }
		}
	}).on("taijiAsyncSuccess",function(event,result){
		if(result!="error"){
			window.location="${rootUrl }app/system/oplog/asyncExport/download?fileName="+result;
		}
	}).on("taijiSearchSuccess",function(event,result){
	});
});
</script>

</head>
<body>
<div id="form-target"></div>
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>

<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		<!-- 内容页 -->
				<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			
		<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			
			<div id="logManage" class="panel panel-inverse">
				<div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">产地环境</h4>
                        </div>
                        
                  <div class="panel-toolbar">
                  <a href="${rootUrl }app/medicine/environment/add"  class="taiji_modal	btn  btn-success m-5"><i class="fa fa-plus m-r-5"></i>添加</a>
<%--                         <a href="${rootUrl }app/system/oplog/multiRemove" data-selector="#myTable-column [name='ids']:checked" class="btn  btn-success m-r-10 taiji_operate {confirm_message:'您确认要删除选中记录吗?',refresh:true}"> --%>
<!-- 							<i class="fa fa-times m-r-10"></i>批量删除 -->
<!-- 						</a> -->
<%-- 						<a href="${rootUrl }app/system/oplog/export" data-selector="#startTime,#endTime" class="taiji_export {fileUrl:'${rootUrl }app/system/oplog/export/file',confirm_message:'您确认要操作吗?'} btn  btn-success m-r-10 "> --%>
<!-- 							<i class="fa fa-file-excel-o m-r-10"></i>导出Excel -->
<!-- 						</a> -->
<%-- 						<a href="${rootUrl }app/system/oplog/word" class="btn  btn-success  m-r-10 "> --%>
<!-- 							<i class="fa fa-file-word-o m-r-10"></i>导出Word -->
<!-- 						</a> -->
				</div>	
				<div class="panel-body">		
				<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/medicine/environment/manage" method="post" enctype="multipart/form-data">
				<!-- 查询条件 -->
					<div class="taiji_search_condition  m-t-5">
					</div>
					<div class="form-group m-t-5">
							<label class="control-label">年份</label>
							<form:input path="year" cssClass="form-control" maxlength="50" />
						</div>
						<div class="form-group m-5">
							<label class="control-label">省份</label>
							<form:select path="provinceId" cssClass="form-control">
								<form:option value="">--全部--</form:option>
									<c:forEach items="${provinces}" var="province"  varStatus="voStatus">
										<form:option value="${province.provinceId}">${province.provinceName }</form:option>
									</c:forEach>
							</form:select>
						</div>	
						<div class="form-group m-5">
							<label class="control-label">土壤质地</label>
							<form:select path="soilTexture" cssClass="form-control">
								<form:option value="">--全部--</form:option>
									<c:forEach items="${soilTextures}" var="soilTexture"  varStatus="voStatus">
										<form:option value="${soilTexture}">${soilTexture.value }</form:option>
									</c:forEach>
							</form:select>
						</div>	
<!-- 					<div class="form-group m-t-5"> -->
<!-- 						<label class="control-label">日志日期</label> -->
<!-- 						<div  class="input-group"> -->
<%-- 	 						<form:input cssStyle="width:150px"   path="startTime" readonly="true"  cssClass="form-control" /> --%>
<!-- 						    <span class="input-group-btn"> -->
<!-- 								<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('startTime'),dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endTime\')}'});"><i class="fa fa-calendar"></i></button> -->
<!-- 							</span> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="form-group m-t-5">	 -->
<!-- 						<label class="control-label">至</label> -->
<!-- 						<div  class="input-group"> -->
<%-- 		  					<form:input cssStyle="width:150px" path="endTime"  readonly="true" cssClass="form-control"   /> --%>
<!-- 							<span class="input-group-btn"> -->
<!-- 								<button type="button" class="btn btn-default" onclick="WdatePicker({el:$dp.$('endTime'),dateFmt:'yyyy-MM-dd 23:59:59',minDate:'#F{$dp.$D(\'startTime\')}'});"><i class="fa fa-calendar"></i></button> -->
<!-- 							</span> -->
<!-- 						</div> -->
<!-- 	 				</div>	 -->
	 				
						
					<button class="taiji_search_submit btn btn-md btn-success m-l-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
					<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
				    <button class="taiji_search_collapse btn btn-md btn-success m-l-5" data-toggle="collapse" data-target="#collapse-content" type="button" ><i class="fa fa-angle-double-down  m-r-10 "></i>更多条件</button>
					<div  class="collapse" id="collapse-content" >
			             <div class="form-group m-t-5">
			             	<%@ include file="/WEB-INF/jsp/pagesize.jsp" %>
						</div>
				  </div>
				</form:form>	
			</div>
			<div id="myTable-body"  class="taiji_search_result taiji_table_float taiji_column_float table-responsive" data-column-width="60">
				<table class="table table-striped table-bordered  table-hover" style="width:1200px">
					<thead>
						<tr>
							<th style="width:50px;height: 42px;"><input type="checkbox"  name="checkAll" class='taiji_check_all' id="checkAll"/><label for="checkAll">&nbsp;</label></th>
							<th >年份</th>
							<th >地点</th>
							<th>土壤类型</th>
							<th>土壤质地</th>
							<th>水源</th>
							<th>ph值</th>
							<th>年降水量(mm)</th>
							<th>无霜期(天)</th>
							<th>年平均气温(℃)</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
		
			<div class="panel-footer text-right ">
							<div class="pageturn taiji_pager">
	                        </div>
	                       
             		  </div>
		</div>
		</div>
		</div>
		</div>
	
	<!-- 内容页 -->

<!-- 版权页 -->
<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
<!-- 版权页 -->
</div>
</body>
</html>