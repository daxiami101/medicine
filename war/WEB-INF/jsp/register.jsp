<!DOCTYPE html>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<head>
<title><fmt:message key="webapp.name" />--登录</title>
<%@ include file="/WEB-INF/jsp/assets.jsp" %>
<script src="${rootUrl }plugins/encrypt/sha-512.js"></script>
<script src="${rootUrl}js/cookie.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$("#myManage").taiji().on("taijiCVE",function(event,responseText){
		var cve=$.parseJSON($(responseText).find("#taiji_ejson").text());
		if(cve.validCode&&cve.validCode=="验证码错误"||cve.password&&cve.password=="密码错误"){
			$("#getValidCode").click();
		}
	});
	
	$("#login_button").click(function(){
		var hexsha=hex_sha512($("#password").val());
	 	$("#myManage").taiji("ajaxHref",this,
			   {type:"POST",
		   		data:{username:$("#username").val(),password:hexsha,validCode:$("#validCode").val()},
		 });
		return false;
	});
	
	$("input").keydown(function(event){
		if (event.keyCode === 13 ) {
			$("#login_button").click();
		}
	});
	
	i=1;
	$("#getValidCode").click(function(){
		i++;
		if(i>10){
			alert("刷新验证码太频繁了！")
			return;
		}
	    $(this).html("<img   src='${rootUrl }app/common/validCodeImage.html?ee="+i+"'/>");
	});
});
</script>
 <style type="text/css">
	body
		{
			font: normal 14px/1.4 "Helvetica Neue","HelveticaNeue",Helvetica,Arial,sans-serif;
		}
		div
		{
			display:block;
		}
		a
		{
			text-decoration:none;
			opacity: 1;
			color: #fff;
		 }
		input,button{ outline:none; }
		::-moz-focus-inner{border:0px;}   /*去除按钮点击时出现的虚线边框*/
        .login_bg
        {
			position: fixed;
			top: 0;
			right: 0;
			bottom: 0;
			left: 0;
			background-size: cover;
        }
		.login_header {
			position:absolute;
			top:0;
			left:0;
			right:0;
			}
		.container {
			position:relative;
			top:50%;
			margin: 0 auto;
			width: 260px;
			
			}
		#logo
		{
			display: block;
			text-align: center;
			font-weight: bold;
			font-size: 50px;
			color: white;
			height: 100%;
		}
		#subheading 
		{
		  position: relative;
		  width: 517px;
		  left: 50%;
		  margin: 10px 0 16px -258px;
		  font-size: 15px;
		  font-weight: normal;
		  color: #fff;
		  text-align: center;
		}
		.signup_forms
		{
			width:260px;	
		}
		.signup_forms_container
		{
			overflow: hidden;
			background: #fff;
			border-radius:3px;	
		}
		.form_user,.form_password,form_username
		{
			width:260px;
			height:45px;
			margin:0px;
        	padding:0px;
			border:0px;	
		}
		.form_password,.form_username,.form_confirm_password{border-top: 1px solid #e3e3e3;}
		.signup_forms input
		{
			padding: 11px 10px 11px 13px;
			width: 100%;
			margin:0px;
        	background: 0;
			font: 16px/1.4 "Helvetica Neue","HelveticaNeue",Helvetica,Arial,sans-serif;
			border:0px;	
		}
		#signup_forms_submit{
		  margin-top:8px;		
		  width:260px;
		  height:45px;
		  background:#529ECC;
		  border:0px;
		  border-radius:3px;
		  cursor:pointer;              <!--CSS属性设置鼠标为手型-->
		 }
		 #signup_forms_submit span{
			 color: #fff;
			 font: "Helvetica Neue",Arial,Helvetica,sans-serif;
			 font-size: 16px;}
		.myfooter
		 {
			position: fixed;
			top: auto;
			right: 0;
			bottom: 0;
			left: 0;
		 }
		 .myfooter_signup_link
		 {
			 position: absolute;
			 width:141px;
			 height:78px;
			 bottom: 0;
			 padding: 0 20px;
			 margin: 0 0 13px 0;
			 line-height: 25px;
			 text-align: center;
			 opacity: 1;
			 color: #fff;
		 }
		 .signup_link
		 {
			  display: block;
			  height: 45px;
			  padding: 0 10px;
			  margin: 0 0 8px 0;
			  font-size: 16px;
			  font-weight: bold;
			  line-height: 45px;
			  border: 0;
			  border-radius: 2px;
			  color: #fff;
              background: rgba(255,255,255,0.33);
		}
		.link
		{
			font-size: 14px;
			padding-right: 5px;
			margin-right: 12px;
			color: #fff;
		}
		.design_show
		{
			position: fixed;
			bottom: 0;
			right: 0;
			padding: 0 12px;
			margin: 0 0 13px 0;
			line-height: 25px;
		}
		.designer_info
		{
			position: relative;
			color: #FFFFFF;
		}
		#face
		{
			margin: 0 0 0 10px;
			border-radius:20px;
			padding: 0;
			vertical-align: middle;
			height: 24px;
			width: 24px;
		}
    </style>
</head>

<body>
 <div id="login_bg" class ="login_bg" style="background-image:url(${rootUrl }images/blog_bg.jpg);"></div>
<!-- begin #page-loader -->
<!-- 	<div id="page-loader" class="fade in"><span class="spinner"></span></div> -->
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	<div id="page-container" class="login-container">
	    <!-- begin login -->
        <div class="login  animated">
            <!-- begin brand -->
            <div class="form_header">
        	<h1 id="logo" style="color:black">本草真源</h1>
			<h2 id="subheading" style="color:black">河南辅仁堂制药有限公司</h2>
        </div>
            <!-- end brand -->
            <div id="myManage" class="login-content">
            	<form  id="loginForm" name="loginForm" class="margin-bottom-0" action="${rootUrl }app/common/login" method="post">
                    <div class="form-group m-b-20">
                    	<input name="username" id="username" class="form-control input-lg"  placeholder="请输入用户名" /> 
                    </div>
                    <div class="form-group m-b-20">
                        <input type="password"  id="password" name="password" class="form-control input-lg" placeholder="请输入密码" />
                    </div>
                    <div class="form-group m-b-20">
                        <input type="password"  id="password" name="passwordConfirm" class="form-control input-lg" placeholder="请再次输入密码" />
                    </div>
                   <div class="input-group m-b-20" style="width:100%">
                        <input id="validCode"  name="validCode" class="form-control input-lg"  placeholder="请输入验证码" autocomplete="off"/>
                        <label id="getValidCode" class="input-group-addon" title="单击刷新验证码" style="width:96px;padding: 0px;"><img src="${rootUrl }app/common/validCodeImage.html?ee=1"></label>
                    </div>
                    <div class="login-buttons">
                        <a id="login_button"   href="${rootUrl }app/common/login" class="btn btn-success btn-block btn-lg" >注册</a>
                    </div>
                </form>
                <div style="padding-top:20px;">
<%--                 	<a id="tplogin"  href="${oauthUrl}" class="btn btn-link btn-sm ">第三方登录</a> --%>
                </div>
            </div>
        </div>
        <!-- end login -->
        <div class="myfooter">
    	<div class="myfooter_signup_link">
        	<a class="signup_link" href="${rootUrl }app/common/welcome">登录</a>
<!--             <a href="https://www.baidu.com" target="_blank" class="link unnamed_1">联系我们</a> -->
<!--             <a href="#" target="_blank" class="link unnamed_2">关于</a> -->
        </div>
        <div class="design_show">
        	<div class="designer_info">
            	<a href="#">河南辅仁堂制药有限公司--<strong>制</strong></a>
               
            </div>
        </div>
    </div>
	</div>
	<!-- end page container -->
	
</body>
</html>
