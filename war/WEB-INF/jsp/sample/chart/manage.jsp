<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/WEB-INF/jsp/head.jsp" %>

<link rel="stylesheet" type="text/css" href="${rootUrl }css/jquery.jqplot.css" />
<style type="text/css">
#chartbox pre{
	width:1500px;background-color: white;margin: 10px;padding: 20px
	font-family: 黑体;font-size: 12pt;
}
#chart1,#chart2,#chart3{
	width:1018px;border: 1px gray solid;margin: 10px;
}
</style>

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
<script type="text/javascript" >
$(function(){
	$("#code1").html($("#script1").html());
	$("#code2").html($("#script2").html());
	$("#code3").html($("#script3").html());
});
</script>

</head>
<body>

	<!-- LOGO -->
	<%@ include file="/WEB-INF/jsp/top.jsp" %>
	<!-- LOGO -->

	<div id="boxmain">
		<!-- 左侧菜单 -->
		<%@ include file="/WEB-INF/jsp/left.jsp" %>
		<!-- 左侧菜单 -->
	
		<!-- 内容页 -->
	<div id="boxright">
		<%@ include file="/WEB-INF/jsp/place.jsp" %>
		<form:form modelAttribute="queryModel" id="listForm" action="${rootUrl}sample/proxy/test.do" name="listForm" method="post">
		</form:form>
	<!-- 查询结果 -->
	<div   id="chartbox" style="height: 2300px">
		<div  id="chart1" ></div>
		<pre id="code1" ></pre>
		<div  id="chart2" ></div>
		<pre  id="code2"></pre>
		<div >
			<span>Clicked: </span>
			<span id="info2b">......</span>
		</div>
		<div  id="chart3"></div>
		<pre  id="code3"></pre>
	</div>
	</div>
		<!-- 内容页 -->
		</div>
	<!-- 版权页 -->
	<%@ include file="/WEB-INF/jsp/bottom.jsp" %>
	<!-- 版权页 -->
</body>
</html>