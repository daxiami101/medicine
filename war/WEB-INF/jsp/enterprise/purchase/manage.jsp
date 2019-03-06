<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src='${rootUrl }plugins/moment/moment.min.js'></script>
<script src="${rootUrl }plugins/datepicker/WdatePicker.js" type="text/javascript"></script>
<link href="${rootUrl }plugins/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet" />
<script src="${rootUrl }plugins/bootstrap-editable/js/bootstrap-editable.min.js" type="text/javascript"></script>
<link href="${rootUrl }plugins/jquery-confirm/jquery-confirm.min.css" rel="stylesheet" />
<link href="${rootUrl }plugins/font-awesome-4.1.0/css/font-awesome.css" rel="stylesheet" />
<script src="${rootUrl }plugins/jquery-confirm/jquery-confirm.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	$.ajaxSetup ({ cache: false}); 
	$("#myManage").taiji({
		enableAclCheck:true,
	}).editable({
		selector:".editable",//元素选择器，对目标元素启用编辑
		url:"editGrid",//编辑提交的url
	    success: editGridSuccess//服务器返回成功的回调方法
	}).on("taijiSearchSuccess",function(event,result){
	}).on("taijiEditSuccess",function(event,result){
	});
	
	$("select").change(function(){
		$("#queryButton").click();
	});
	
	$("#importBtn").click(function(){
		if ($("#file").val() == '') {
			alert("请选择文件!");
			return false;
		}
		if (!window.confirm("你确定要导入吗?")) {
			return false;
		}
		$('#listForm').attr("action","${rootUrl}app/ncp/exam/examOrderPlan/importExcel");
		$('#listForm').ajaxSubmit({
			success : function() {
				$("#queryButton").click();//页面自动刷新
			},
			error:function(){
				alert("error occured")
			}
		});
		setTimeri();
		$('#listForm').attr("action","${rootUrl}app/ncp/exam/examOrderPlan/manage");
	});
	$("#downloadTemplate").on("click", function(){
		$("#listForm").attr("action","${rootUrl}app/enterprise/purchase/downloadTemplate");
		listForm.submit();
		$("#listForm").attr("action","${rootUrl}app/enterprise/purchase/manage");
	});
});

function editGridSuccess(response, newValue) {
	if($.isPlainObject(response)&&response.success===true){
		$.Taiji.showNote(response.msg);
	}else{
    	var result=$.parseJSON($(response).find("#taiji_note").text());
    	if(result&&result.success===false){
    		return $.Taiji.base64.decode(result.msg);	        	
    	}
    }
}
function showBtn(e){
	var path=e.value;
	$("#fileName").val(path.substring(path.lastIndexOf('\\')+1, path.length));
}
function setTimeri() {
	setTimeout(count, 1000);
}

	function count() {
		$.ajax({
					url : "${rootUrl}app/ncp/exam/examOrderPlan/getResult",//${rootUrl }app/base/espVeh/getResult
					type : 'post',
					data : {
						type : "import"
					},
					dataType : "json",
					success : function(data) {
						var d = eval(data);//json为接收的后台返回的数据；
						$('#countExcel').text("数据开始上传,已完成"+d.count+"条");
						var state = d.state;
						if(state=='FILEEMPTY'){
							alert("您选择的上传文件内容为空");
							$('#countExcel').text("");
							return;
						}
						if(state=='FILEERROR'){
							alert("请使用系统提供的模板文件上传");
							$('#count').text("");
							return;
						}
						if (state == 'DOING') {
							$('#countExcel').text("数据开始上传,已完成"+d.count+"条");
							setTimeri();
						} else {
							$('#countExcel').text("上传完成共"+d.count+"条");
							if (state == "SUCERROR") {
								
									if (confirm("文件中含有错误记录,需要下载错误情况吗?")) {
										$('#listForm')
												.attr('action',
														'${rootUrl}app/ncp/exam/examOrderPlan/returnDataExport');//${rootUrl }app/base/espVeh/returnDataExport
										listForm.submit();
										$('#listForm')
												.attr('action',
														'${rootUrl}app/ncp/exam/examOrderPlan/manage');//${rootUrl }app/base/espVeh/manage
									}
									alert("上传完成");
							} else {
								alert("上传完成");
							}
							$("#queryButton").click();
						}
					},
					error : function(data) {
						alert("请稍候重试");
					}
				});
		}
	
</script>
<style>
.file {
    position: relative;
    padding: 4px 12px;
    overflow: hidden;
    line-height: 24px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.jconfirm .jconfirm-box div.content-pane .content {
    left: -124px;
    position: absolute;
    right: -24px;
    top: 11px;
    transition: all 0.2s ease-in 0s;
}
</style>
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
							<h4 class="panel-title">考试预约计划管理</h4>
						</div>

						<div class="panel-toolbar">
						<a href="${rootUrl }app/tablet/purchase/add"  class="taiji_modal_lg  btn  btn-success m-r-10">
					添加任务
					</a>
							<form:form cssClass="taiji_search_form form-inline m-t-5" modelAttribute="queryModel" id="listForm" name="listForm" 
							action="${rootUrl}app/enterprise/purchase/manage" method="post">
								<!-- 查询条件 -->
								<div class="form-group m-t-5">	
									<label class="control-label">农户类型</label>
										<form:select path="" cssClass="form-control" >
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
									
								<div class="form-group m-5">
										<button class="btn btn-md btn-success m-r-3"  type="button"   id="downloadTemplate">
												Excel模板下载
										</button>
								</div>
								<div class="input-group" id="importDiv">
										<span class="input-group-addon" id="span1" style="background-color:#00acac;padding:0px">
										<a class="file btn btn-md btn-success m-r-3" >请选择文件
										<input type="file"  id="file" name="file"  accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" 
											onchange="showBtn(this)"/></a></span>
										<input id="fileName" class="form-control" readonly/>
										<span class="input-group-addon" style="background-color:#00acac;padding:0px">
										<a href="#" id="importBtn" class="btn btn-success" type="button">导入</a></span>
									</div>
									
							</form:form>
						</div>
						
						<div class="panel-body">
							<div class="taiji_search_result table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th style="width:50px">序号</th>
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
											<th>供应商</th>
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