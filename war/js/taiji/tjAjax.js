/*******************************************************************************
 * jQuery tjAjax plugin
 * 
 * 此处因为前台页面需要使用，所有考虑把这块给提出来。以便在没有taiji的情况下也能单独使用。
 * 
 * @requires jquery v1.8.3
 * 			 jquery.form v3.28.0
 * 			 base64  
 * 
 * Copyright © 2010-2014 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 1.0.0 2014.04.01
 * 
 * @since 1.0.0
 * 
 ******************************************************************************/
(function($){
	var tjBase64 = new Base64();
	function showExceptionError(msg){
		var $x = $(window).width() / 2 + $(document). scrollLeft()-150,
		$y = $(window).height() / 2 + + $(document). scrollTop()-20;
		$("<div style='width:auto;position: absolute;z-index: 5;background-color:red;opacity:0.7;position: absolute;height:auto;padding:15px;	display: none;	color: yellow;	font-weight: bold;'></div>").appendTo($("body")).css({"left":$x,"top":$y}).text(msg).fadeIn(500).delay(2000).fadeOut(500);;
	}
	
	
	
	$.extend($,{
		 tjAjaxForm : function(form,opts){
			 $(".taijiCev").remove();
			 var myOpts = opts || {};
			 myOpts.before = myOpts.before || function(){};
			 myOpts.after = myOpts.after || function(){};
			 myOpts.success = myOpts.success || function(){};
			 
			 myOpts.before();
			var options = {
					type:"post",
					headers:{
					        "taiji_ajax":"true"
					    },
					success:function(data,status,xhr){
						if(xhr.getResponseHeader("taiji_note")){
							alert(tjBase64.decode(xhr.getResponseHeader("taiji_note")));
							myOpts.success(data);
						}else if(xhr.getResponseHeader("taiji_jme")){
		            	
							showExceptionError(tjBase64.decode(xhr.getResponseHeader("taiji_jme")));
						}else if(xhr.getResponseHeader("taiji_me")){
							showExceptionError(tjBase64.decode(xhr.getResponseHeader("taiji_me")));
						}else if(xhr.getResponseHeader("taiji_cve")){
							var ejson = $(data).find("#taiji_ejson");
							var	ejsonContent = $.parseJSON($(ejson).html());
							var message = "操作失败，原因如下:\n";
							$.each(ejsonContent,
								function(i, n) {
								var $nextLabel = $("[for=" + i + "]");
								if ($nextLabel
										.hasClass($.Taiji.defaults.errorClass)) {
									$nextLabel.text(n);
									$nextLabel.show();
								} else {
									$nextLabel = $("<label class='taijiCev' style='color:white;background:#FF7676;border-radius:4px;padding:5px;margin-left:10px;' ></label>");
									$nextLabel
											.addClass($.Taiji.defaults.errorClass);
									$nextLabel
											.attr({
												"for" : i,
												generated : true
											});
									$nextLabel.text(n);
									$("[name=" +i + "]",
											$(form))
											.after($nextLabel);
								}
									message += n;
									message += "\n";
							});
							//showExceptionError(message);
						}
						else{
							myOpts.success(data);
						}
						
					},
					error:function(json){
						showExceptionError("网络忙，请稍后重试");
						myOpts.after();
					}
				};
			$(form).ajaxSubmit(options);
		 },
		 tjAjaxHref : function(url,opts){
			 var myOpts = opts || {};
			 myOpts.before = myOpts.before || function(){};
			 myOpts.after = myOpts.after || function(){};
			 myOpts.success = myOpts.success || function(){};
			 
			 myOpts.before();
			 var options = {
					type:"post",
					url:url,
					success:function(data,status,xhr){
						if(xhr.getResponseHeader("taiji_note")){
							showExceptionError(tjBase64.decode(xhr.getResponseHeader("taiji_note")));
							myOpts.success();
						}else if(xhr.getResponseHeader("taiji_jme")){
							showExceptionError(tjBase64.decode(xhr.getResponseHeader("taiji_jme")));
						}else if(xhr.getResponseHeader("taiji_me")){
							showExceptionError(tjBase64.decode(xhr.getResponseHeader("taiji_me")));
						}else if(xhr.getResponseHeader("taiji_cve")){
							var ejson = $(data).find("#taiji_ejson");
							var	ejsonContent = $.parseJSON($(ejson).html());
							var message = "操作失败，原因如下:\n";
							$.each(ejsonContent,
								function(i, n) {
									message += n;
									message += "\n";
							});
							showExceptionError(message);
						}
						myOpts.after();
					},
					error:function(json){
						showExceptionError("网络忙，请稍后重试");
						myOpts.after();
					}
				};
			 $.ajax(options);
		 }
	});
})(jQuery);
