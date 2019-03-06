<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<script type="text/javascript">
$(function(){
	$("#userSelectManage").taiji()
	.on("taijiSearchSuccess",function(event,result){
		selectResult.find(">span").each(function(){
			$("#userSelectManage input[name=ids][value="+$(this).data("name")+"]").prop("checked",true);
		});
	});
	$("#submit").click(function(){
		alert(123);
		$("#logManage").triggerHandler("taijiModalPost",[$("#myForm"),{table:"add"}]);
	});
	var selectResult=$("#userSelectResult");
	$("#userSelectBtn").click(function(){
		$("#userSelectManage [name='ids']:checked").each(function(){
			alert(456);
// 			var userId=$(this).val();
// 			alert("userId:"+userId);
// 			var farmerId=$(this).val();
// 			$("#selectFarmerId").val(farmerId);
// 			var farmer=$("#"+ "farmer"+userId).text();
// 			alert("farmerId===:"+farmer);
			if($("#user"+userId).size()>0){
				return true;
			}
			var item=$("<span>",{id:"user"+userId,"class":'select_item'});
			alert(item);
			item.append('<em>'+farmer+'</em>');//显示已选农户
			var rm=$("<i>",{
				"class":"fa fa-times",
				click:function(){
					$(this).parent().remove();
				}
			});
			item.data("id",userId);
			item.append(rm);
			item.appendTo(selectResult);
		});
	});
	$("#selectSubmit").click(function(){
		var farmerIds="";
		$("#userSelectManage [name='ids']:checked").each(function(){
			var taskId=$(this).val();
			$("#taskId").val(taskId);
// 			var taskId="${taskId}";
// 			$("#taskId").val(taskId);
			alert("taskId:"+taskId);
		});
// 		if(farmerIds.length<=0){
// 			$.Taiji.showWarn("请先选择用户！");
// 			return false;
// 		}
		alert(123);
// 		$("#logManage").triggerHandler("taijiModalPost",[$("#listForm"),{table:"add"}]);
		$("#myManage").triggerHandler("taijiModalPost",[$("#randomForm"),{table:"add"}]);
		selectResult.hideModal();
	});
});
</script>
<style type="text/css">
td,th{
text-align: center;
}
</style>
</head>
<body >
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
	<h4 class="modal-title">种植任务信息</h4>
</div>
<div class="modal-body">
	<!-- begin #page-container -->
	<div  class="fade in" >
		<!-- begin #content -->
<!-- 		<div class="container-fluid"> -->
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div id="userSelectManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">选择种植任务批号</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                        	
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/land/growthRegulator/taskSelect/manage" method="post">
<%-- 								<form:input path="taskId" value="${taskId}"/> --%>
								<label class="control-label">种植批号</label>
								<div class="form-group m-5">
									<form:input path="taskNo" size="25" maxlength="50" cssClass="form-control"/>
								</div>
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">状态</label> -->
<%-- 									<form:select path=""  > --%>
<%-- 										<form:option value="">全部</form:option> --%>
<%-- 										<form:options  itemLabel="value"/> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">每页条数1</label> -->
<%-- 									<form:select path="" cssClass="form-control" > --%>
<%-- 										<form:option value="10">10</form:option> --%>
<%-- 										<form:option value="16">16</form:option> --%>
<%-- 										<form:option value="20">20</form:option> --%>
<%-- 										<form:option value="50">50</form:option> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->

								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
								<button class="taiji_search_collapse btn btn-md btn-success m-l-5" data-toggle="collapse" data-target="#collapse-content-inner" type="button" ><i class="fa fa-angle-double-down  m-r-10 "></i>更多条件</button>
					<div  class="collapse" id="collapse-content-inner" >
						<div class="form-group m-t-5">	
							<label class="control-label">农户类型</label>
								<form:select path="" cssClass="form-control" >
								<form:option value="">全部</form:option>
								<form:options items="${farmerTypes}" itemLabel="value"/>
								</form:select>
						</div>		
						
			             <div class="form-group m-t-5">
			             	<%@ include file="/WEB-INF/jsp/pagesize.jsp" %>
						</div>
				  </div>
                        	</form:form>
                        </div>
							<div   class="taiji_search_result  table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
<!-- 											<th style="width:50px">选择</th> -->
											<th >种植批号</th>
											<th>种植药材</th>
											<th >种植方式</th>
											<th>种植起始时间</th>
											<th>种植结束时间</th>
											<th>种植面积(亩)</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager"></div>
             		  </div>
             		  <div style="margin:-20px 20px 5px 20px">
		 					<form:form modelAttribute="queryModel"  id="randomForm" name="randomForm" method="post" action="${rootUrl }app/land/sowTransplant/taskSelect/randomConfig">
<%-- 								<form:hidden path="selectFarmerId" /> --%>
								<form:hidden path="taskId" />
						</form:form>
						</div>
						
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
	<!-- end page container -->
</div>
<div class="modal-footer">
<!-- 	<div style="text-align: left;"> -->
<!-- 		<div id="userSelectResult" class="select_result" style="margin: 10px 0"> -->
<!--    			<label>已选择农户：</label> -->
<!--    		</div> -->
<!--    	</div> -->
<!-- 	<button class="btn btn-sm btn-info" type="button" id="userSelectBtn"><i class="fa fa-user  m-r-10 "></i>选择</button> -->
	<a href="#" class="btn btn-sm btn-white" data-dismiss="modal">关闭</a>
<!-- 	<a href="#" class="btn btn-sm btn-success"  id="selectSubmit">提交</a> -->
</div>	
	

</body>
</html>
