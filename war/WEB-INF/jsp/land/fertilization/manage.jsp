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
							<h4 class="panel-title">施肥管理</h4>
						</div>

						<div class="panel-toolbar">
						<a href="${rootUrl }app/land/fertilization/taskSelect/manage"  class="taiji_modal_lg  btn  btn-success m-r-10">
					添加任务
					</a>
							<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel" 
							id="listForm" name="listForm" 
							action="${rootUrl}app/land/fertilization/manage" method="post">
								<!-- 查询条件 -->
								
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">数据来源</label> -->
<%-- 									<form:select path="key" cssClass="form-control"> --%>
<%-- 										<form:option value="">--全部--</form:option> --%>
<%-- 											<c:forEach items="${infoServiceInfoUsers}" var="infoServiceInfoUser"  varStatus="voStatus"> --%>
<%-- 												<form:option value="${infoServiceInfoUser.key}">${infoServiceInfoUser.platName }</form:option> --%>
<%-- 											</c:forEach> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
								<div class="form-group m-5" id="data-table_length" style="position:relative;">
									<label class="control-label">种植批号</label>
									<form:input path="taskNo" class="form-control" size="10" maxlength="40" />
								</div>
								<div class="form-group m-5" id="data-table_length" style="position:relative;">
									<label class="control-label">药材名称</label>
									<form:input path="medicineName" class="form-control" size="10" maxlength="40" />
								</div>
								
								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" id="queryButton">
									<i class="fa fa-search  m-r-10 "></i>查询
								</button>
									<button class="taiji_search_reset btn btn-md btn-success" type="button"><i class="fa fa-refresh m-r-10"></i>重置</button>
							<button class="taiji_search_collapse btn btn-md btn-success m-l-5" data-toggle="collapse" data-target="#collapse-content" type="button" ><i class="fa fa-angle-double-down  m-r-10 "></i>更多条件</button>
					<div  class="collapse" id="collapse-content" >
						<div class="form-group m-t-5">
						
							<label class="control-label">灌溉方式</label>
							<form:input path="" cssClass="form-control" maxlength="50" />
						</div>	
						
			             <div class="form-group m-t-5">
			             	<%@ include file="/WEB-INF/jsp/pagesize.jsp" %>
						</div>
				  </div>
							</form:form>
						</div>
						
						<div class="panel-body">
							<div class="taiji_search_result table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th style="width:50px;height: 42px;"><input type="checkbox"  name="checkAll" class='taiji_check_all' id="checkAll"/><label for="checkAll">&nbsp;</label></th>
											<th >种植批号</th>
											<th >药材名称</th>
											<th >肥料种类</th>
											<th>施肥开始时间 </th>
											<th >施肥结束时间 </th>
											<th>施肥量/亩</th>
											<th>采购日期</th>
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