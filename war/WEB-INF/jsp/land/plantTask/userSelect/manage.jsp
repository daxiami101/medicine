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
	var selectResult=$("#userSelectResult");
	$("#userSelectBtn").click(function(){
		$("#userSelectManage [name='ids']:checked").each(function(){
			var userId=$(this).val();
			if($("#user"+userId).size()>0){
				return true;
			}
			var item=$("<span>",{id:"user"+userId,"class":'select_item'});
			item.append('<em>'+$(this).parent().text()+'</em>');
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
		if(selectResult.find("span").size()==0){
			$.Taiji.showWarn("请先选择用户！");
			return false;
		}
		var targetId='${param.targetId}'||'userId',
			targetName='${param.targetName}'||'userName',
			multiple=Number('${param.multiple}'||'0');
		var userIdArray=selectResult.find("span").map(function(){
							return $(this).attr("id").replace("user","");
						}).get();
		var	userNameArray=selectResult.find("span").map(function(){
							return $.trim($(this).find("em").text());
						}).get();
		if(multiple){
			$("#page-container #"+targetId).val(userIdArray.join(","));
			$("#page-container #"+targetName).val(userNameArray.join(","));
		}else{
			$("#page-container #"+targetId).val(userIdArray[0]);
			$("#page-container #"+targetName).val(userNameArray[0]);
		}
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
	<h4 class="modal-title">种源信息</h4>
</div>
<div class="modal-body">
	<!-- begin #page-container -->
	<div  class="fade in" style="background: #d9e0e7;padding: 20px 0 0;">
		<!-- begin #content -->
		<div class="container-fluid">
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
                            <h4 class="panel-title">用户</h4>
                        </div>
                        
                        <div class="panel-toolbar ">
                        	
                        	<form:form cssClass="taiji_search_form form-inline m-t-5 " modelAttribute="queryModel"  id="listForm" name="listForm" action="${rootUrl}app/land/plantTask/userSelect/manage" method="post">
								<label class="control-label">药材名称</label>
								<div class="form-group m-5">
									<form:input path="medicineId" size="25" maxlength="50" cssClass="form-control"/>
								</div>
<!-- 								<div class="form-group m-5"> -->
<!-- 									<label class="control-label">状态</label> -->
<%-- 									<form:select path=""  > --%>
<%-- 										<form:option value="">全部</form:option> --%>
<%-- 										<form:options  itemLabel="value"/> --%>
<%-- 									</form:select> --%>
<!-- 								</div> -->
								<div class="form-group m-5">
									<label class="control-label">每页条数</label>
									<form:select path="pageSize" cssClass="form-control" >
										<form:option value="10">10</form:option>
										<form:option value="16">16</form:option>
										<form:option value="20">20</form:option>
										<form:option value="50">50</form:option>
									</form:select>
								</div>

								<button class="taiji_search_submit btn btn-md btn-success m-r-5" type="button" ><i class="fa fa-search  m-r-10 "></i>查询</button>
								<button class="taiji_search_reset btn btn-md btn-default" type="button"><i class="fa  fa-refresh  m-r-10  "></i>重置</button>
                        	</form:form>
                        </div>
							<div   class="taiji_search_result  table-responsive">
								<table class="table table-striped table-bordered  table-hover">
									<thead>
										<tr>
											<th>药材名称</th>
											<th >拉丁名</th>
											<th>繁殖材料</th>
											<th>繁殖方式</th>
											<th >繁殖地点</th>
											<th >购销方式</th>
											<th  style="width: 200px">操作</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							</div>
						<div class="panel-footer text-right">
							<div class="pageturn taiji_pager">
	                        </div>
             		  </div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
	</div>
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
