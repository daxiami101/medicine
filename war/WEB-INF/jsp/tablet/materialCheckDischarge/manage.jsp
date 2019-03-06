<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp"%>
<script src="${rootUrl }plugins/datepicker/WdatePicker.js" type="text/javascript"></script>

<script type="text/javascript">
	$(function(){
		$("#myManage").taiji({
			enableAclCheck:true
		}).on("taijiAsyncSuccess",function(event,result){
						   	alert("执行结果："+result);
					    });
		$("select").change(function(){
			$("#queryButton").click();
		});
		$("#rollUp").click(function(){
			$("#special").hide(500);
			$("#rollUp").hide(500);
		});
// 		$("#special").hide(); 
// 		$("#special").attr("style", "display:none"); 
		$("#rollUp").hide();
		$(document).keydown(function(event){ 
			if(event.ctrlKey && event.which == 13){
				$("#special").show(1000);
				$("#rollUp").show();
			}
		}); 
	});
</script>
<style type="text/css">
#alert-info{
margin-left: -10000px;
}
</style>
</head>
<body>
	<div id="page-loader" class="fade in">
		<span class="spinner"></span>
	</div>

	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp"%>
		<!-- end #header -->

		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp"%>
		<!-- end #sidebar -->
		<!-- 内容页 -->
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right"></ol>
			<!-- begin page-header -->
			<!-- end page-header -->

			<div class="row">
				<!-- begin col-12 -->
				<div class="col-md-12">
					<div id="myManage" class="panel panel-inverse">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a> 
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a> 
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a> 
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger"  data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">物料请验与放行</h4>
						</div>

						<div class="panel-toolbar">
						<a href="${rootUrl }app/tablet/materialCheckDischarge/add"  class="taiji_modal_lg  btn  btn-success m-r-10">
					添加任务
					</a>
							<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel" 
							id="listForm" name="listForm" 
							action="${rootUrl}app/tablet/materialCheckDischarge/manage" method="post">
								<!-- 查询条件 -->
								<div class="form-group m-t-5">	
									<label class="control-label">物料状态</label>
										<form:select path="materialStatus" cssClass="form-control" >
										<form:option value="">全部</form:option>
										<form:options items="${materialStatuses}" itemLabel="value"/>
										</form:select>
								</div>
								<div class="form-group m-5" id="data-table_length" style="position:relative;">
									<label class="control-label">原料名称</label>
									<form:input path="materialKind" class="form-control" size="10" maxlength="40" />
								</div>
								
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize" cssClass="form-control">
										<form:option value="20">20</form:option>
										<form:option value="50">50</form:option>
										<form:option value="100">100</form:option>
									</form:select>
								</div>
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" id="queryButton">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
									<button class="taiji_search_reset btn btn-md btn-success" type="button"><i class="fa fa-refresh m-r-10"></i>重置</button>
									<button class="taiji_search_reset btn btn-md btn-success" style="display:none" type="button" id="rollUp"><i class="fa fa-refresh m-r-10"></i>收起</button>
									
							</form:form>
						</div>
						
						<div class="panel-body">
							<div class="taiji_search_result table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th style="width:50px;height: 42px;"><input type="checkbox"  name="checkAll" class='taiji_check_all' id="checkAll"/><label for="checkAll">&nbsp;</label></th>
											<th >物料名称</th>
											<th>药材编码</th>
											<th >产地</th>
											<th>等级</th>
											<th>规格</th>
											<th>到货单据号</th>
											<th>计量单位</th>
											<th>采购数量</th>
											<th>随货同行单据号</th>
											<th>采收日期</th>
											<th>物料状态</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						</div>

						<div class="panel-footer text-right ">
							<div class="pageturn taiji_pager"></div>
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