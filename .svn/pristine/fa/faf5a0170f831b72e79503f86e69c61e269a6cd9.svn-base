<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<link rel="stylesheet" type="text/css" href="${rootUrl }plugins/jqplot/jquery.jqplot.css" />
<script src="${rootUrl }js/tjlib/jquery.jqplot.all.js" type="text/javascript"></script>
<script type="text/javascript" id="script1">
$(function(){
	//使用默认图表参数
	var mychart1=$("#chart1").mychart({url:"${rootUrl}app/sample/chart/data"});
});
</script>
<script type="text/javascript" id="script2">
$(function(){
	//设置图表参数
	var mychart2=$("#chart2").mychart({
		pagerForm:"noform",				//pagerForm找不到时不再提交form，采用get请求。
		url:"${rootUrl}app/sample/chart/data",
		optionsCallback:function(chartModel,options){	//回调方法，chartModel为后台返回的数据，options为图表参数。
			options.canvasOverlay.show=true;
		},
		chartOptions:{ 
			title:"调度中心调派能力",		//标题
			legend: {
				show: false,			//不显示图例
		    	placement: 'none'
			},
			seriesDefaults:{
		            renderer:$.jqplot.BarRenderer
		    },
			canvasOverlay: {			//在图表上画线
		      	  show: false,
		      	  objects: [
	      	      	{
	      	    	  	dashedHorizontalLine: {
	      	    		  name: 'wilma',
	      	    		  y: "{other[0]}",//水平线的高度
	      	    		  lineWidth: 2,
						xOffset: '0',
	 	    		  	color: 'rgb(255,0,0)',
	  	    		  	  shadow: true
	      	    	  }  	      	    	  
	      	      }      
	      	  ]
	        }
		}
	});
});
</script>
<script type="text/javascript" id="script3">
$(function(){	
	var mychart3=$("#chart3").mychart({
		url:"${rootUrl}app/sample/chart/data",
		chartOptions:{ 
			seriesDefaults:{
		            renderer:$.jqplot.BarRenderer
		    }
		}
	});
	//图表绑定事件
	 $('#chart3').bind('jqplotDataHighlight',
	            function (ev, seriesIndex, pointIndex, data) {
	                $('#info2b').html('series: '+seriesIndex+', point: '+pointIndex+', data: '+data+ ', pageX: '+ev.pageX+', pageY: '+ev.pageY);
	            }
	        );
});

</script>

</head>
<body >
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
		<!-- begin #header -->
		<%@ include file="/WEB-INF/jsp/header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="/WEB-INF/jsp/sidebar.jsp" %>
		<!-- end #sidebar -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			<ol class="breadcrumb pull-right">
			</ol>
			<!-- begin page-header -->
			<h1 class="page-header"></h1>
			<!-- end page-header -->
			
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-8">
			        <!-- begin panel -->
                    <div id="myManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">jqplot chart1</h4>
                        </div>
                        <div class="panel-body" >
                        	<p id="chart1" ></p>
                        </div>
					</div>
                    <!-- end panel -->
			    </div>
			     <div class="col-md-4">
			        <!-- begin panel -->
                    <div id="myManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">jqplot chart2</h4>
                        </div>
                         <div class="panel-body">
                        	<p id="chart2"></p>
                        </div>
					</div>
                    <!-- end panel -->
			    </div>
			    <!-- end col-12 -->
			</div>
				<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div id="myManage" class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">jqplot chart3</h4>
                        </div>
                        <div class="panel-body" >
                        	<p id="chart3"></p>
                        </div>
					</div>
                    <!-- end panel -->
			    </div>
			    </div>
			<!-- end row -->
		</div>
		<!-- end #content -->
		
					
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	
	

</body>
</html>
