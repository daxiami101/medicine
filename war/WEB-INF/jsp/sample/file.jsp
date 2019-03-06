<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link rel="stylesheet" type="text/css" href="${rootUrl }plugins/webuploader/webuploader.css">
<script type="text/javascript" src="${rootUrl }plugins/webuploader/webuploader.js"></script>
<script type="text/javascript">
$(function(){
	var uploader = WebUploader.create({
	    // swf文件路径
	    swf: '${rootUrl }plugins/webuploader/Uploader.swf',
	    // 文件接收服务端。
	    server: '${rootUrl }app/sample/file/upload',
	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: {
	    	id:'#picker',
	    	multiple:false //文件多选
	    },
	    fileNumLimit :1,//文件个数
	    formData:{belongId:111},//表单参数
	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false
	});

	// 当有文件被添加进队列的时候
	uploader.on( 'fileQueued', function( file ) {
	    $("#theFile").text(file.name);
	});
	
	// 文件上传过程中创建进度条实时显示。
	uploader.on( 'uploadProgress', function( file, percentage ) {
		$.Taiji.showProcess(percentage * 100,"上传中",0.3);
	});

	uploader.on( 'uploadSuccess', function( file ) {
		$.Taiji.showNote("上传成功！");
		$("#alert-progress").remove();
	});

	uploader.on( 'uploadError', function( file ) {
		$.Taiji.showWarning("上传失败！");
	});

	uploader.on( 'uploadComplete', function( file ) {
	});

	$("#ctlBtn").on('click', function() {
        uploader.upload();
    });
	
	$("#uploadBtn").on('click', function() {
		$("#myManage").taiji("ajaxForm",$("#myForm"),{bsSuccess:function(responseText,note){
			$.Taiji.showNote(note);
	      }
		});
		return false;
    });
	
	$("#myForm :file").change(function(){
		$("#selectedFile").text($(this).val());
	});
});

</script>
<style>
/*demo样式*/
#picker {
	display: inline-block;
	line-height: 1.428571429;
	vertical-align: middle;
	margin: 0 12px 0 0;
	width: 105px;
	zoom:1;
	*display:inline;
}

#picker .webuploader-pick {
	padding: 6px 12px;
	display: block;
}

.u-file-btn {
	position: relative;
	direction: ltr;
	overflow: hidden;
	width: 105px;
	zoom:1;
	*display:inline;
}

.u-file-btn input {
	cursor: pointer;
	z-index: 10;
	font-size: 118px;
	position: absolute;
	top: 0px;
	right: 0px;
	opacity: 0;
	filter: Alpha(opacity : 0);
	zoom: 1;
}
</style>
</head>
<body >
	<!-- begin #page-container -->
	<div id="page-container" class="fade in">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #content -->
		<div class="content" style="margin: 0px 180px">
			<div class="row">
			<div class="col-md-12">
				 <div id="myOpen" class="well">
					<div class="modal-header">
						<h4 class="modal-title">file示例</h4>
					</div>
					<div class="modal-body">
<div id="myManage" >
	<div id="uploader" >
		<h5>webuploader方式</h5>
	    <div class="btns">
	        <div id="picker"  >选择文件</div>
	         <span id="theFile"></span>
	        <button id="ctlBtn" class="btn btn-sm btn-success">开始上传</button>
	    </div>
	</div>
	<div>
		<h5>普通上传方式</h5>
		<form action="${rootUrl }app/sample/file/upload" id="myForm" class="form-inline" method="POST" enctype="multipart/form-data">
			<input type="hidden"  name="belongId"  value="1" />
			<div class="u-file-btn btn  btn-sm btn-success" style="margin-right: 12px;"><input type="file" name="file" />选择文件</div>
	         <span id="selectedFile"></span>
	        <a id="uploadBtn" class="btn btn-sm btn-success">上传</a>
			
		</form>
	</div>
</div>	<!-- end myManage -->	
					</div>
		         </div>  
			</div>
			</div>
			</div>
		<!-- end #content -->
		
					
	</div>
	<!-- end page container -->
	
	

</body>
</html>
