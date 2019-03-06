/******************************************************************************
 * jQuery taiji plugin 
 * 
 * @requires    jQuery v1.9.1 
 * @requires    bootstrap v3
 * @requires    form v3.2.8(jQuery plugin) 
 * @requires    validate v1.11.1(jQuery plugin) 
 * @requires    metadata v2.1(jQuery plugin) 
 * @requires    base64
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 3.0(2015-05-29)
 * 
 * @since 1.0.0
 * 
 *****************************************************************************/
/**
 * @param $
 * @param window
 * @param document
 */
(function ($,window,document) {
	//当前版本号
	var version = '3.0';

	/**************************************************************************
	 * 
	 * 扩展jQuery的fn,增加taiji方法，此方法属于语法外衣。
	 * 实际上是调用new $.Taiji
	 * 
	 * @since 2.0.0
	 * 
	 *************************************************************************/ 
	$.extend($.fn, {
		taiji : function(opts) {
			// 如果没有选择任何节点，直接返回
			if (!this.length) {
				if(window.console && window.console.warn){
					window.console.warn("没有选择任何节点，直接返回");
				}
				return;
			}
			// 如果初始化过了，直接返回缓存对象
			var aTaiji = $(this).data('aTaiji');
			if (aTaiji) {
				return this;
			}
			// 初始化
			aTaiji = new $.Taiji(opts, this);

			// 缓存到当前节点中
			$(this).data('aTaiji', aTaiji);
			
			// 如果窗口大小调整，重新计算设定提示、警告、loading的位置
			$(window).resize(function() {
				aTaiji.repostion();
			});
			// 如果拖动了滚动条，也需要重新计算
			$(document).scroll(function() {
				aTaiji.repostion();
			});
			return this;
		}
	});
	
	/**************************************************************************
	 * 
	 * 太极JavaScript库
	 * 
	 * @name Taiji
	 *
	 * @class taiji JavaScript库
	 *
	 * @constructs 构造函数
	 * 
	 * @since 2.0.0
	 * 
	 *************************************************************************/
	$.Taiji = function(opts, targetDiv) {
		// 将默认设置与用户设置进行合并
		this.settings = $.extend(true, {}, $.Taiji.defaults, opts);
		// 缓存当前节点
		this.currentTarget = $(targetDiv);
		// 缓存base64,用于前后台直接传送数据压缩
		this.base64 = new Base64();
		this.isRunning = false;
		// 缓存base64的解码函数
		this.decode = this.base64.decode;
		this.aclCheck($(this.settings.aclClassName,this.currentTarget));
		this.repostion();
		
		// 缓存一些设置中的值，减少获取链
		this.searchFormClassName = this.settings.search.formClassName;
		this.moreCondition = this.settings.search.moreCondition;
		this.searchSubmitClassName = this.settings.search.submitClassName;
		this.searchResetClassName = this.settings.search.resetClassName;
		this.searchAutoRefreshEnable = this.settings.search.autoRefresh.enable;
		this.searchAutoRefreshInterval = this.settings.search.autoRefresh.interval;
		
		// 调用私有的_init(),执行初始化
		this._init();
	};
	
	
	/**************************************************************************
	 * 
	 * 扩展Taiji对象
	 * 
	 *************************************************************************/
	$.extend($.Taiji,{
		/** @lends Taiji# */
		prototype : {
			/** @lends Taiji.prototype */
			/******************************************************************
			 * 
			 * Taiji初始化函数
			 *
			 * 完成所有的初始化操作，包括各种事件的绑定等。
			 * 私有的，请勿在外部直接调用
			 *
			 * @verion 2.2.8
			 *
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			_init : function() {
				// 在当前节点上增加名为_taiji_的class,保留以后使用
				$(this.currentTarget).addClass("_taiji_");

				// 绑定查询按钮的click事件
				this._bindSearchSubmitClick();
				// 绑定重置按钮的click事件
				this._bindSearchResetClick();
				// 绑定查询表单验证,并在验证通过之后，调用_search函数
				this._bindSerachFormValidate();
				// 绑定查询表单中的input text的回车事件
				this._bindSearchFormInputEnter();
				// 绑定更多查询条件的显示隐藏事件
				this._bindSearchMoreCondition();
				// 绑定事件处理函数
				this._bindAll();
				// 主动调用一次查询
				//2.2.8改进为默认执行一次查询；如何设置为false，则不执行。
				if(this.settings.search.autoSearch === true){
					$(this.searchFormClassName,
						this.currentTarget).trigger("submit");
				}else{
						$(this.settings.search.resultClassName,
							this.currentTarget).find("> table > tbody")
							.empty().append("<tr><td colspan='15' ><div class='taiji_not_found'>请先填写查询条件！</div></td></tr>");
				}
			},
			
			/******************************************************************
			 *
			 * 事件绑定函数
			 *
			 * 将所有的事件绑定到target上，减少绑定事件的麻烦
			 * TODO 未来将支持将事件绑定到其他的节点上
			 *
			 * @since 2.0.8
			 * 
			 *****************************************************************/
			_bindAll:function(){
				var $self = (this),
					events = {},
					classNames = {},
					$target = $(this.currentTarget);
				// 将插件需要绑定的事件处理函数提取出来
				$.each($.Taiji.events,function(event,coll){
					if(!events[event]){
						events[event] = {};
					}
					$.each(coll,function(name,other){
						$.each(other,function(cn,handler){
							var className = $self.settings[name][cn];
							events[event][className] = handler;
						});
					});
				});
				// 绑定事件处理函数
				//TODO 需要进行重构，以便兼容可以绑定到其他
				/*
				  events={click:{".taiji_async":"_handleAsyncClick",
								".taiji_collapse","_handleCollapseClick"
								},
						  change:{...}		
						 }
				*/
				$.each(events,function(aEvent,cllol){
					$target.off(aEvent+".taiji").on(aEvent+".taiji",
						function(event){
						var $target = (
								$(event.target).is("a")||
								$(event.target).is(":checkbox")||
								$(event.target).is("th"))? $(event.target):
										($(event.target).parents().is("a") && 
										$(event.target).parents("a"))?
												$(event.target).parents("a") : 
													$self._emptyTarget;
						$.each(cllol,function(className,handler){
							if($target.is(className)){
								$self[handler]($target);
								$.Taiji.preventDefault(event);
							}
						});
					});
				});
				// 为当前实例扩展方法
				$.each($.Taiji.customEvents,function(name,handler){
					$target.off(name).on(name,function(event,form,options){
						$self[handler](form,options);
					});
				});
				
			},
			/******************************************************************
			 * 
			 * 空的target对象，is返回false,
			 * 为bindAll的辅助方法
			 *
			 * @since 2.0.8
			 * 
			 *****************************************************************/
			_emptyTarget:{
				is:function(className){
					return false;
				}
			},
		
			/******************************************************************
			 * 
			 * 绑定查询表单的input:text的回车事件的处理函数，
			 * 会触发查询表单的submit事件
			 * 私有的，请勿在外面直接调用
			 *
			 * @since 2.0.8
			 * 
			 *****************************************************************/
			_bindSearchFormInputEnter:function(){
				var $self = this;
				$(this.searchFormClassName,this.currentTarget)
					.find(":input:text")
					.keydown(function(event){
						var $target = $(event.target);
						if (event.keyCode === 13 ) {
								$($self.searchSubmitClassName,
									$self.currentTarget)
									.trigger("click");
						}
					});
			},
			/******************************************************************
			 * 
			 * 绑定更多查询条件的显示隐藏事件，用来更换点击按钮的内容
			 * 私有的，请勿在外面直接调用
			 *
			 * @since 3.2
			 * 
			 *****************************************************************/
			_bindSearchMoreCondition:function(){
				var $trigger=$(this.moreCondition,this.currentTarget);
				var target=$($trigger.data("target")||$trigger.attr("href"));
				var originalHtml=$trigger.html();
				target.on("hidden.bs.collapse",function(){
						$trigger.html(originalHtml);
					}).on("shown.bs.collapse",function(){
						$trigger.html('<i class="fa fa-angle-double-up  m-r-10 "></i>收起');
					});
			},
			/*****************************************************************
			 * 
			 * 绑定查询表单的验证函数
			 * 私有的，请勿在外面直接调用
			 *
			 * @since 2.0.0
			 * 
			 * @version 2.2.0 去掉前台验证，去掉了jquery.validate.js依赖
			 * 
			 *****************************************************************/
			_bindSerachFormValidate: function(){
				var $this = this;
				//2.2.0的时候，去掉了jquery.validate.js,为了兼容，而留下此代码，下一个版本可能去掉代码
				if($.fn.validate){
					this.searchFormValidate = $(
						this.searchFormClassName,
						this.currentTarget).validate({
							rules : $this.settings.search.rules||{},
							messages : $this.settings.search.messages||{},
							submitHandler : function(form) {
								// 验证通过之后，调用异步查询方法
								$this._search(form);
								
							}
						});
				}else{
					 $(this.searchFormClassName,
							this.currentTarget).on("submit",function(event){
								$this._search($(this));
								$.Taiji.preventDefault(event);
							});
				}
			},
			/******************************************************************
			 * 
			 * 绑定查询按钮click时间的函数。
			 * 私有的，请勿在外部直接调用
			 *
			 * @since 2.0.8
			 * 
			 *****************************************************************/
			_bindSearchSubmitClick : function(){
				var $this = this;
				$(this.searchSubmitClassName,
					this.currentTarget)
					.click(function(event) {
							$($this.searchFormClassName,
								$this.currentTarget).trigger("submit");
							$.Taiji.preventDefault(event);
						});
			},
			_bindSearchResetClick : function(){
				var $this = this;
				$(this.searchResetClassName,this.currentTarget).click(function(event){
					$($this.searchFormClassName,
						$this.currentTarget).trigger("reset");
					$.Taiji.preventDefault(event);
				});
			},
			/******************************************************************
			 * 
			 * 查询函数，
			 * 私有的,请勿在外部直接调用
			 *
			 * @since 2.0.0
			 * 
			 * @version 2.2.0 改为后台验证，前台展示验证结果。
			 * 
			 *****************************************************************/ 
			_search : function(form) {
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}

				var $this = this,
					options = { //查询配置项
					// 查询成功处理函数
					success : function success(responseText,
							status, xhr) {
						
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						// 移出loading
						$.Taiji.hideLoading();
						// 恢复查询按钮
						$($this.searchSubmitClassName,
							$this.currentTarget).attr("disabled",
								false);
						$this.isRunning = false;
						if(responseHeader.note){
							$this.warn("查询成功:"+$this.decode(responseHeader.note));
						}else if(responseHeader.jme){
							$this.warn("查询失败:"+$this.decode(responseHeader.jme));
						}else if(responseHeader.me){
							$this.warn("查询失败:"+$this.decode(responseHeader.me));
						}else if(responseHeader.cve){
							var ejson = $(responseText).find("#taiji_ejson");
							$this.validationTip(ejson,$this.currentTarget);
						}else{
							$this._handleSearchSuccess(responseText);
						}	
						
					},
					// 查询失败处理函数
					error : function error(xhr) {
						$this._handleOperateError(xhr.status,$($this.searchSubmitClassName,$this.currentTarget));
					}
				};
				// 创建loading
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);				
				// 禁用查询按钮，防止客户猛击
				$(this.searchSubmitClassName,
						this.currentTarget).attr("disabled",
						true);
				// 异步查询请求
				$(this.searchFormClassName,
						this.currentTarget).ajaxSubmit(options);

			},
			/******************************************************************
			 * 
			 * 私有的,查询成功处理函数，请勿在外部直接调用
			 * 
			 * @since 2.0.0
			 * 
			 * @version 2.2.9 
			 * 
			 *****************************************************************/ 
			_handleSearchSuccess : function(responseText) {
				var $responseText = $(responseText);
				// 处理分页
				this._bindPager($responseText.find("#taiji_search_pager"));
				var hasText = $responseText.find("#taiji_search_data tbody").children().size() > 0;
				if(hasText === true){
					var $tbody=$responseText.find("#taiji_search_data tbody");
					this.aclCheck($tbody);
					var $trs,trsIndex =0,$myBody;
					$myBody = $(this.settings.search.resultClassName,
						this.currentTarget).find("> table > tbody");
					$myBody.empty().append($tbody.html());
				}else{
					$(this.settings.search.resultClassName,
						this.currentTarget).find("> table > tbody")
						.empty().append("<tr><td colspan='15' ><div class='taiji_not_found'>没有检索到符合条件的数据！</div></td></tr>");
				}
				this._bindDataChange();

				var $$this = this;
				if (this.searchAutoRefreshEnable === true) {
					if (this.taijiSearchTimeoutId){
						window.clearTimeout(this.taijiSearchTimeoutId);
					}
					this.taijiSearchTimeoutId = window.setTimeout(
						function() {
							$($$this.searchSubmitClassName,
								$$this.currentTarget)
								.triggerHandler("click");
						},$$this.searchAutoRefreshInterval);
				}
				
				//显示已选择的查询条件
				var container=$(".taiji_search_condition",$$this.currentTarget);
				if(container.size()>0){
					var conds=$($$this.searchFormClassName,$$this.currentTarget)
					.find(":text:enabled,:radio:checked,:checkbox:checked,select")
					.filter(function(){
					    return $(this).val();
					});
					container.empty().append('<label>已选择查询条件：</label>');
					//<span   class="cond_item"><b >操作用户：</b><em>admin</em><i class="fa fa-times"></i></span>
					conds.each(function(){
						var a=$(this);
						if(a.attr("name")=="pageSize"){
							return true;
						}
						var label;
						if(a.prev("label").size()>0){
							label=a.prev(".control-label");
						}else{
							label=a.parent().siblings(".control-label");
						}
						var item=$("<span>",{"class":'cond_item'});
						if(a.is(":checkbox")){
							item.append('<em>'+a.get(0).nextSibling.nodeValue+'</em>');
						}else if(a.is(":radio")){
							item.append('<b >'+label.text()+'：</b>');
							item.append('<em>'+a.get(0).nextSibling.nodeValue+'</em>');
						}else if(a.is("select")){
							var radioLabel=a.parent().prev("label");
							item.append('<b >'+label.text()+'：</b>');
							item.append('<em>'+a.find("option:selected").text()+'</em>');
						}else{
							//text
							item.append('<b >'+label.text()+'：</b>');
							item.append('<em>'+a.val()+'</em>');
						}
						var rm=$("<i>",{
							class:'fa fa-times',
							click:function(){
								if(a.is(":radio")||a.is(":checkbox")){
									a.prop("checked",false);
								}else{
									a.val("");
								}
								$(this).parent().remove();
							}
						});
						item.append(rm);
						container.append(item);
					});
				}
				//查询成功之后，改为事件传播方式，用于取代callback方式
				$(this.currentTarget).triggerHandler(
					"taijiSearchSuccess",[responseText]);
				
			},
			/******************************************************************
			 * 
			 * 列表中的数据有变化的时候，就调用本函数 
			 * 目前主要提供给 add,edit,search使用成功之后调用 
			 * 此方法为共用，单个方法调用成功之后绑定事件的请在各自的处理函数中单独绑定。
			 * 私有的,请勿在外部直接调用  
			 *
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			_bindDataChange : function(){
				// 行样式切换效果
				$(this.settings.search.resultClassName,
						this.currentTarget)
						.find("> table > tbody > tr")
						.off("click.taiji")
						.on("click.taiji",function(event) {
									$(this).addClass("taiji_clicked")
											.siblings().removeClass("taiji_clicked");
								});
				//双表格同步
				if($("#myTable-header").size()==0&&$(".taiji_table_float",this.currentTarget).size()>0){
					$(".taiji_table_float",this.currentTarget).before($("<div>",{id:"myTable-header"}));
					$("#myTable-header").html($(".taiji_table_float",this.currentTarget).clone().html());
					//.innerWidth($(".taiji_table_float",this.currentTarget).innerWidth());
				}else{
					$("#myTable-header>table>tbody").replaceWith($(".taiji_table_float",this.currentTarget).find("table>tbody").clone());
					//$("#myTable-header").innerWidth($(".taiji_table_float",this.currentTarget).innerWidth());
				}
				$(".taiji_table_float",this.currentTarget).scroll(function () {
					$("#myTable-header").scrollLeft($(this).scrollLeft());
				});
				if($("#myTable-header").size()>0){
					var fixedTables=$("#myTable-header").add($(".taiji_table_float>table",this.currentTarget));
					var fixedTop=$("#myTable-header").offset().top-54;
					$(document).off("scroll.taiji").on("scroll.taiji",function(){
						var e=$(document).scrollTop();
						if(e>fixedTop&&fixedTop>0){
							fixedTables.addClass("fixed");
							$("#myTable-header").innerWidth($(".taiji_table_float",this.currentTarget).innerWidth());
						}else{
							fixedTables.removeClass("fixed");
							$("#myTable-header").innerWidth("");
						}
					});
				}
			},
			
			/******************************************************************
			 * 
			 * 绑定分页处理函数
			 * 私有的,，请勿在外部直接调用
			 *
			 *
			 * @version 2.2.2 增加对非法 总页数、总记录数、当前页数的处理
			 * 
			 * @since 2.0.13
			 *
			 * 分页方面的东东有变化
			 * 
			 * TODO 看能不能将此处代码提取出来
			 * 
			 *****************************************************************/
			_bindPager : function($searchPager) {
				// 添加分页样式
				var $this = this,
					pageNo = window.parseInt(
						$searchPager.find("#taiji_search_pageNo").text(),10),
					pageCount = window.parseInt(
						$searchPager.find("#taiji_search_pagecount").text(),10),
					totalCount = window.parseInt(
						$searchPager.find("#taiji_search_totalcount").text(),10);
				
				//2.2.2 处理各种异常情况 
				//当没有查询到数据或返回数据异常的时候，页面显示0条记录，1页，当前页在第1页。
				if(isNaN(pageCount) || pageCount < 2 ||isNaN(pageNo) || pageNo < 1 ||
						isNaN(totalCount) || totalCount <= 0){
					$(".taiji_pager", this.currentTarget).hide();
					$(".taiji_pager_go",$this.currentTarget).hide();
				}else{
					$(".taiji_pager", this.currentTarget)
						.each(function() {
						var $ul = $("<ul  class='pagination  m-t-0' ></ul>");
						 
						var $li_total_record = $("<li ><span>共<strong class='totalCount'>"+totalCount+"</strong>条/<strong>"+pageCount+"</strong>页</span></li>").appendTo($ul);
						var $li_prev = $("<li ><a href='#' class='taiji_pager_item' value='"+(pageNo-1)+"'>&lt;</a></li>").appendTo($ul);
						if(pageNo === 1){
							$li_prev.addClass("disabled").find("a").removeClass("taiji_pager_item");
						}
						var startPoint = 1,endPoint = 5;
						if(pageNo > 3){
							startPoint = pageNo -2;
							endPoint = pageNo +2;
						}
						if(endPoint > pageCount){
							startPoint = pageCount -4;
							endPoint = pageCount;
						}
						if(startPoint < 1){
							startPoint = 1;
						}
						for(var point = startPoint;point<=endPoint;point++){
							var $li_point = $("<li ><a href='#' class='taiji_pager_item' value='"+point+"'>"+point+"</a></li>").appendTo($ul);
							if(point === pageNo){
								$li_point.addClass("active").find("a").removeClass("taiji_pager_item");
							}
						}
						var $li_next = $("<li ><a href='#' class='taiji_pager_item' value='"+(pageNo + 1)+"'>&gt;</a></li>").appendTo($ul); 
						if(pageNo === pageCount){
							$li_next.addClass("disabled").find("a").removeClass("taiji_pager_item");
						}
						var $pager_input=$('<li><span class="input">到<input type="text" style="width:40px" value="100" class="taiji_pager_input">页</span><a class="taiji_pager_goto" href="#">跳转</a></li>').appendTo($ul); 
						
						$(this).html($ul);
					}).show();
					$(".taiji_pager_input",$this.currentTarget).val(pageNo);
					$(".taiji_pager_go",$this.currentTarget).show();
				}
			},
			
			/******************************************************************
			 * 
			 * 出错处理函数
			 * @param 
			 *		{String} statusCode 出错状态码
			 * @param
			 *		{Object} obj 出错操作是在那个节点被调用的
			 *
			 * @version 2.0.0
			 * 
			 * @since 2.0.0
			 * 
			 * 
			 *****************************************************************/
			_handleOperateError : function(statusCode, obj) {
				var $this = $(obj),
					showMessage = $.Taiji.Messages.ERR_RESPONSE +statusCode;
				$.Taiji.showWarn(showMessage);
				this.warn(showMessage);
				$this.attr("disabled", false);
				$.Taiji.hideLoading();
	
				this.isRunning = false;
			},
			
			/******************************************************************
			 * 
			 * 非菜单权限检查，如果无权限，隐藏链接
			 * 
			 * @param {Object} 
			 *			
			 * @version 2.4
			 * 
			 * @since 2.4
			 * 
			 *****************************************************************/
			aclCheck:function(obj){
				if(!this.settings.enableAclCheck){
					return;
				}
				var $obj=$(obj);
				var uris;
				//如果当前就是taji_acl
				if($obj.is(this.settings.aclClassName)){
					uris=$obj;
				}else{
					uris=$obj.find(this.settings.aclClassName);
				}
				var pathName = window.location.pathname;
				var rootUrl = pathName.substr(0,pathName.substr(1).indexOf("/")+1)+"/";
				var hrefs=uris.map(function(){
							return $(this).attr("href").replace(rootUrl,"");
						}).get().join(",");
				
				//所有uri一次请求
				$.ajax({
					  type: "POST",
					  url: rootUrl+"app/acl/hasbuttons",
					  data:"uris="+hrefs,
					  async: false,
					  dataType:"json",
					  success: function(list){
						  //{"hasButton":true,"msg":""}
						$.each(list,function(i,n){
							if(!n.hasButton){
								uris.filter("[href='"+rootUrl+n.uri+"']").hide();
							}
						});
					  }
					 });
			},
			/******************************************************************
			 * 
			 * 业务成功处理方法。
			 * 
			 * @param responseText  服务器返回的文本
			 * 		  note  成功提示信息
			 * 		  table 表格操作方式
			 * @version 3.0
			 * 
			 * @since 3.0
			 * 
			 *****************************************************************/
			_handleBsSuccess:function(responseText,note,table){
				var $row = $(responseText).find(".taiji_result_data tr");
				// 权限检查
				this.aclCheck($row);
				// 处理row
				if(table=="add"){
					var $tbody = $(this.settings.search.resultClassName,
							this.currentTarget).find("> table > tbody");
					$tbody.find(".taiji_clicked").removeClass("taiji_clicked");
					//如果tbody没有内容，就直接添加在tbody内
					$tbody.prepend($row.addClass("taiji_clicked"));
				}else if(table=="edit"||table=="update"){
					$(this.settings.search.resultClassName,
							this.currentTarget).find(".taiji_clicked").replaceWith(
									$row.addClass("taiji_clicked"));
				}else if(table=="remove"){
					$(this.settings.search.resultClassName,
							this.currentTarget).find(".taiji_clicked").remove();
					
				}
				
				var operateObj={
						add:{event:"taijiAddSuccess",plus:1},
						edit:{event:"taijiEditSuccess",plus:0},
						update:{event:"taijiUpdateSuccess",plus:0},
						remove:{event:"taijiRemoveSuccess",plus:-1},
						none:{event:"taijiOperateSuccess",plus:0}
				};
				// 修改总条数
				$(".taiji_pager .totalCount",this.currentTarget).each(function() {
							$(this).text(parseInt($(this).text(),10) + operateObj[table].plus);
						});
				// 绑定
				this._bindDataChange();
				// 显示提示信息
				$.Taiji.showNote(note);
				$(this.currentTarget).triggerHandler(operateObj[table].event,[responseText]);
			},
			/******************************************************************
			 * 验证提示
			 * 
			 * @param  ejson 服务器端返回的校验json
			 * 		   parent  容器
			 * @version 3.0
			 * 
			 * @since 3.0
			 * 
			 *****************************************************************/	
			validationTip :function(ejson,parent){
				var	ejsonContent = $.parseJSON($(ejson).html());
				$.each(ejsonContent,
						function(i, n) {
							var eles=$("[name='"+i+"']",parent);
							if(eles.length>1){
								$("div[data-for='"+i+"']",parent).showPopover(n,{hideConcern:true});
							}else {
								$("[name='"+i+"']",parent).showPopover(n,{hideConcern:true});
							}
					});
			},
			/******************************************************************
			 * 
			 * taiji公用ajaxHref，对带有href属性的a元素ajax请求。
			 * 支持get/post方式
			 * 支持传参数,a元素通过data-selector增加参数,加参数后强制转为post。
			 * 封装了获取url、confirm,获取参数、加载中、校验、警告框、错误处理。
			 * 业务成功方法bsSuccess交由调用者实现，如弹出成功框，修改表格，触发事件。
			 * 
			 * @param opts: {method:"GET",
			 * 				 bsSuccess:function(responseText,note){},
			 * 				 complete:function(){}
			 * 				 }
			 * @version 3.0
			 * 
			 * @since 3.0
			 * 
			 *****************************************************************/			
			ajaxHref : function(element,opts){
				var $element=$(element);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl(element);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					$.Taiji.showWarn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($element);
				if ($message) {
					if (!window.confirm($message)) {
						return false;
					}
				}
				//如果当前还有未完成的操作，直接返回。
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}
				//禁用当前按钮，防止用户猛击
				$element.attr("disabled",true);
				//加载状态
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				var that=this,dataObj={},defaults={
					method:"GET",
					bsSuccess:function(){},
					complete:function(){}	
				};
				var options = $.extend({}, defaults, opts);
				
				if($element.data("selector")){
					options.method="POST";
					$($element.data("selector")).each(function(){
						var e=$(this);
						if(e.attr("name")&&e.val()){
							var key=e.attr("name"),value=e.val();
							if(dataObj[key]){
								var oldVal=dataObj[key];
								if($.type(oldVal)=="array"){
									oldVal.push(value)
								}else{
									dataObj[key]=[];
									dataObj[key].push(oldVal);
									dataObj[key].push(value);
								}
							}else{
								dataObj[key]=value;
							}
						}
					});
				}
				
				$.ajax({
					   type: options.method,
					   url: $url,
					   data:dataObj,
					   success:function(responseText,status,xhr){
							//完成加载
						   that.isRunning = false;
						   $.Taiji.hideLoading();
						   var responseHeader = {
									note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
									jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
									me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
									cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
							};
							if (responseHeader.jme) {
								// 操作失败,jme返回，显示警告信息
								$.Taiji.showWarn(that.decode(responseHeader.jme));
							} else if (responseHeader.me) {
								// 操作失败,me返回，显示警告信息
								$.Taiji.showWarn(that.decode(responseHeader.me));
							} else if (responseHeader.cve) {
								var ejson = $(responseText).find("#taiji_ejson");
								that.validationTip(ejson,that.currentTarget);
								// 操作失败,cve返回，显示警告信息
								$.Taiji.showWarn($.Taiji.Messages.ERR_CVE);
							}else {
								options.bsSuccess.call(that,responseText,responseHeader.note&&that.decode(responseHeader.note));
							}
							
						
					   },
					   error : function (xhr) {
						   //完成加载
						   that.isRunning = false;
						   $.Taiji.hideLoading();
						   $.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE + xhr.status);
					   },
					   complete: function (xhr) {
						   $element.attr("disabled",false);
						   options.complete.call(that,xhr);
					   }
				});
			 },
			 /******************************************************************
				 * 
				 * taiji的ajaxForm通用方法。
				 * 封装了加载中、警告框、校验提示、错误处理，返回结果正确的话由调用者处理。
				 * 
				 * @param options说明
				 * 	   bsSuccess:function(responseText,note){}:业务成功函数
				 * @version 3.0
				 * 
				 * @since 3.0
				 * 
				 *****************************************************************/
			 ajaxForm : function(form, options) {
				  //如果当前还有未完成的操作，直接返回。
					if (this.isRunning === true) {
						this.warn($.Taiji.Messages.WAR_OPERATE);
						return;
					} else {
						this.isRunning = true;
					}
					//提示操作进行中
					$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
					var that = this,$form = $(form),
					//ajax的配置项目
					settings = {
						success : function(data, status, xhr) {
							var responseHeader = {
									note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
									jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
									me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
									cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
							};
							$.Taiji.hideLoading();
							that.isRunning = false;
							//根据返回的头部信息，获知当前操作状态
							if (responseHeader.note) {
								options.bsSuccess.call(that,data,that.decode(responseHeader.note));
							} else if (responseHeader.jme) {
								// 操作失败,jme返回，显示警告信息
								$.Taiji.showWarn(that.decode(responseHeader.jme));
							} else if (responseHeader.me) {
								// 操作失败,me返回，显示警告信息
								$.Taiji.showWarn(that.decode(responseHeader.me));
							} else if (responseHeader.cve) {
								var ejson = $(data).find("#taiji_ejson");
								that.validationTip(ejson,form);
								// 操作失败,cve返回，显示警告信息
								$.Taiji.showWarn($.Taiji.Messages.ERR_CVE);
							}else {
								// 其他情况，暂时不处理
								$.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE);
							}
							
						},
						error : function(xhr) {
							$.Taiji.hideLoading();
							$.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE + statusCode);
							that.isRunning = false;
						},
						dataType : "html",
						forceSync : true
					};
					//将页面传过来的ajax配置项与当前的配置项进行合并
					//TODO 需要考虑那个在前的问题，免得页面覆盖了此处的success和error处理函数.
					$.extend(settings, options);
					//处理form表单的enctype="multipart/form-data"属性， 20150408
					var $files = $(":file", $form);
					if($files.size() === 0){
						$(form).removeProp("enctype");
					}else{
						var filesSize = $files.size();
						$files.each(function() {
							if (!$(this).val()){
								filesSize--;
								$(this).attr("disabled", true);
							}
						});
						if(filesSize === 0){
							$(form).removeProp("enctype");
						}else{
							$(form).prop("enctype","multipart/form-data");
						}
					}
					$(form).ajaxSubmit(settings);
					$(":file", $form).each(function() {
						$(this).attr("disabled", false);
					});
				},
			/******************************************************************
			 * 
			 * 日志输出函数
			 * 
			 * @param
			 *		{Object} msg 待输出的信息
			 *
			 * @version 2.0.0
			 *
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			log : function(msg) {
				if(this.settings.debug === true && window.console &&
					window.console.warn){
					window.console.warn(msg);
				}
			},
			/******************************************************************
			 * 
			 * 警告日志输出函数
			 * 
			 * @param
			 *		{Object} msg 待输出的警告信息
			 *
			 * @version 2.0.0
			 * 
			 * @since 2.0.0
			 *
			 *****************************************************************/
			warn : function(msg) {
				if(this.settings.debug === true && window.console &&
						window.console.log){
					window.console.log(msg);
				}
			},
			/******************************************************************
			 * 
			 * 重新计算设定提示、警告、loading的位置
			 *
			 * @version 2.0.0
			 *
			 * @since 2.0.0
			 * 
			 *****************************************************************/ 
			repostion : function () {
				this.$windowW = $(window).width();
				this.$windowH = $(window).height();
				this.$sw = $(document).scrollLeft();
				this.$sh = $(document).scrollTop();
			}
		}
	});
	
	/**************************************************************************
	 * 
	 * 扩展Taiji
	 * 
	 * @version 2.0.15
	 * 
	 * @since 2.0.0
	 * 
	 *************************************************************************/
	$.extend($.Taiji,{
		
		/**********************************************************************
		 * 
		 * 新插件扩展方法 
		 * 
		 * @param 
		 *		{Object} aExtendMethod 插件
		 *		
		 *		{
		 *			//插件名字，用于包装config，在插件内部就可以用this.settings.pluginName.className引用了
		 *			name:'pluginName',
		 *			 //是否使用弹出层，默认不使用，目前有add,edit,view,popupRemove使用
		 *			event:{
		 *				//click事件
		 *				"click":{
		 *					//"subject":指定在哪个目标标签上捕获事件(使用标准的jQuery选择器)，如果没有设置或者设为false,将在taiji插件绑定的标签上捕获事件。
		 *					//"handler"：事件处理程序名称
		 *					//"eventSource":事件源(使用标准的jQuery选择器,id请包含"#"前缀;class请包含"."前缀)
		 *					"subject":false,
		 *					"handler":"_handlePluginClick",
		 *					"eventSource":"#id"
		 *				},
		 *				//change事件
		 *				"change":{
		 *					//"subject":指定在哪个目标标签上捕获事件(使用标准的jQuery选择器)，如果没有设置或者设为false,将在taiji插件绑定的标签上捕获事件。
		 *					//"handler"：事件处理程序名称
		 *					//"eventSource":事件源(使用标准的jQuery选择器,id请包含"#"前缀;class请包含"."前缀)
		 *					"subject":"body",
		 *					"handler":"_HandlePluginChange",
		 *					"eventSource":".class"
		 *				}
		 *				//more..
		 *			},
		 *			//自定义事件，目前自定义事件需要在  包装的 div上触发, $(this.currentTarget).trigger("taijiPluginPost");
		 *			customEvent:{
		 *				//"taijiPluginPost",自定义的事件名
		 *				//"_handlePluginPost"，此事件的处理函数名
		 *				"taijiPluginPost":"_handlePluginPost"
		 *			},
		 *			//需要追加到taiji上的方法，主要是各种事件处理函数。
		 *			eventHandler:{
		 *				//处理函数
		 *				_handlePluginClick:function(){},
		 *				//处理函数
		 *				_handlePluginChange:function(){},
		 *				//处理函数
		 *				_handlePluginPost:function(){}
		 *			},
		 *			//可配置项，可以通过this.settings.pluginName.xxxx引用
		 *			config:{
		 *				//可以通过this.settings.pluginName.className引用
		 *				className:".taiji_plugin",
		 *				//可以通过this.settings.pluginName.otherName引用
		 *				otherClassName:'.taiji_pluginOther'
		 *			}	
		 *		} 
		 * 
		 * @version 2.1.1
		 * 
		 * @since  2.0.8
		 * 
		 *********************************************************************/
		extendMethod:function(aExtendMethod){
			//TODO 添加更多的检查和保护，别让插件替换掉已有的设置及功能。
			$.Taiji.defaults[aExtendMethod.name] = aExtendMethod.config;
			$.each(aExtendMethod.event,function(event,coll){
				if(!$.Taiji.events[event]) {
					$.Taiji.events[event] = {};
				}
				if(!$.Taiji.events[event][aExtendMethod.name]){
					$.Taiji.events[event][aExtendMethod.name] = {};
				}
				if(coll){
					$.each(coll,function(className,handler){
						$.Taiji.log("事件："+event+",插件："+
							aExtendMethod.name+"，class:"+
							className+",处理函数:"+handler);
						$.Taiji.events[event][aExtendMethod.name][className] = handler;
					});
				}
			});
			if(aExtendMethod.customEvent){
				$.each(aExtendMethod.customEvent,function(i,event){
					$.Taiji.customEvents[i] = event;
				});
			}
			
			$.each(aExtendMethod.eventHandler,function(i,handler) {
				$.Taiji.prototype[i] = handler;
			});
		},
		/**********************************************************************
		 * 
		 * 插件添加的标准事件处理程序，
		 * 
		 * @version 2.0.8
		 * 
		 * @since 2.0.8
		 * 
		 **********************************************************************/
		events:{},
	
		/**********************************************************************
		 * 
		 * 插件添加的自定义事件处理程序，
		 * 
		 * @version 2.0.8
		 * 
		 * @since 2.0.8
		 * 
		 **********************************************************************/
		customEvents:{},
		
		/**********************************************************************
		 * 
		 * 目前使用的比较多的常量，
		 * 主要是 与服务器交互返回的头部信息
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		Constans:{
			RESPONSE_HEADER_NOTE:"taiji_note",
			RESPONSE_HEADER_JME:"taiji_jme",
			RESPONSE_HEADER_ME:"taiji_me",
			RESPONSE_HEADER_CVE:"taiji_cve"
		},
		/**********************************************************************
		 * 
		 * 取消默认事件
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		preventDefault : function(event){
			if(event.preventDefault){
				event.preventDefault();
			}else{
				event.returnValue=false;
			}
		},
		
		/**********************************************************************
		 * 
		 * 提示信息，以后应该可以被覆盖，以支持国际化
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		Messages : {
			//错误提示
			ERR_CVE : "校验失败，请看相关字段提示!",
			ERR_RESPONSE:"服务器返回了非预期的值，请联系技术人员，代码:",
			//警告提示
			WAR_NOTNODE:"未选中任何节点！",
			WAR_OPERATE:"前一次操作尚未完成，请稍候！",
			//普通提示
			MSG_OPERATE:"操作进行中，请稍候！"
		},
		/**********************************************************************
		 * 
		 * 获取链接的URL
		 * 
		 * @param 
		 *		{Object} obj 待获取链接的节点
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		getUrl : function(obj) {
			var $obj = $(obj);
			return $obj && $obj.is("a") && $obj.attr("href");
		},
		/**********************************************************************
		 * 
		 * 获得配置的 confirm_message
		 * 支持 meta方式 和 属性方式两种。
		 * 
		 * @param {Object}
		 *			obj 节点
		 * @return {String}
		 *			message
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		getConfirmMessage : function(obj) {
			var $obj = $(obj);
			return $.metadata && $obj.metadata().confirm_message ? $obj
					.metadata().confirm_message : $obj.attr("confirm_message");
		},
		
		/**********************************************************************
		 * 
		 * 获得配置的 元数据
		 * 支持 meta方式 和 属性方式两种。
		 * 
		 * @param {Object}
		 *			obj 节点
		 * @param {String}
		 *			key 元数据名称
		 * @return {String}
		 *			value
		 * 
		 * @version 3.0
		 * @since 3.0.0
		 * 
		 *********************************************************************/
		getMetadata:function(obj,key){
			return $(obj).metadata()[key];
		},
		
		/**********************************************************************
		 * 
		 * 在控制台显示调试信息
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		log : function(msg) {
			if(window.console && window.console.log){
				window.console.log(msg);
			}
		},
		
		/******************************************************************
		 *
		 * 显示 操作进行中提示信息 
		 * 
		 * @param 
		 *		{String} message 待显示的信息。
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *****************************************************************/
		showLoading : function(message) {
			// 创建loading
			this.log("[_createLoading]" + message);
			$loadingDiv = $("#alert-loading");
			if(!$loadingDiv.length){
				var html=
					'<div id="alert-loading" class="alert alert-info  ">'+
						'<strong></strong>'+
						'<div  class="fade in"><span class="spinner"></span></div>'+
					'</div>';
				$loadingDiv=$(html).prependTo(document.body);
			}
			
			$loadingDiv.find("strong")
					.text(message).end().fadeIn(10);
		},
		/******************************************************************
		 * 
		 * 隐藏 操作进行中提示信息
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *****************************************************************/
		hideLoading : function() {
			// 移出loading
			$("#alert-loading").fadeOut(10);
		},
		/******************************************************************
		 * 
		 * 显示警告的函数
		 * 
		 * @param 
		 *		{String} message 待显示的信息。 
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 *
		 *****************************************************************/
		showWarn : function(message) {
			if (typeof message !== 'string') {
				this.log("[_showWarn] 非字符串无法显示");
			}
			var $warnDiv = $("#alert-info");
			if(!$warnDiv.length){
				var html='<div id="alert-info" class="alert alert-info  ">'+
							'<i class="fa fa-info-circle  fa-2x"></i>'+
							'<strong></strong>'+
						 '</div>';
				$warnDiv=$(html).prependTo(document.body);
			}
			// 显示警告信息，指定时间之后，消失
			$warnDiv.find("strong")
					.text(message).end().fadeIn(500).delay(2000)
					.fadeOut(500);
		},
		/******************************************************************
		 * 
		 * 显示提示信息的函数
		 * 
		 * @param 
		 *		{String} message 待显示的信息。
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 *
		 *****************************************************************/
		showNote : function(message) {
			if (typeof message !== 'string') {
				this.log("[_showWarn] 非字符串无法显示");
			}
			var $noteDiv = $("#alert-success");
			if(!$noteDiv.length){
				var html=
						'<div id="alert-success" class="alert alert-success  ">'+
							'<i class="fa fa-check-circle fa-2x "></i>'+
							'<strong></strong>'+
						'</div>';
				$noteDiv=$(html).prependTo(document.body);
			}
			$noteDiv.find("strong")
					.text(message).end().fadeIn(500).delay(2000)
					.fadeOut(500);
		},
		/**********************************************************************
		 * 
		 * 默认配置
		 * 可以被覆盖
		 * 
		 * @version 2.0.8
		 * 
		 * @since 2.4.0
		 * 
		 *********************************************************************/
		defaults : {
			search : {
				formClassName : '.taiji_search_form', // 分页查询表单的class
				rules : {}, // 分页查询表单验证的规则
				messages : {}, // 分页查询表单验证失败的提示信息
				submitClassName : '.taiji_search_submit', // 分页查询，查询链接的class
				resetClassName  : '.taiji_search_reset',
				resultClassName : '.taiji_search_result', // 分页查询，结果展示的容器标签的class
				dataClassName	: '.taiji_search_data',   // 分页查询结果，数据
				pagerClassName	: '.taiji_search_pager',	//分页查询结果，分页信息
				moreCondition   : '.taiji_search_collapse', //更多查询条件按钮
				autoRefresh : {
					enable : false,
					interval : 60000
				},
				autoSearch:true
			},
			// 添加或修改页面，后台字段验证出错的时候，显示错误信息的Element类型，此处添加主要是为了与jquery.validate.js保持一致
			errorElement : "label", 
			// 添加或修改页面，后台验证出错的时候，显示错误信息使用的Class类型，此处添加主要是为了与jquery.validate.js保持一致
			errorClass : "taiji_error", 
			//非菜单权限检查
			enableAclCheck : false,
			aclClassName:".taiji_acl",
			//是否通过点击背景关掉
			backdrop:true,
			// 是否启用DEBUG模式，true启用;false不启用
			debug : true              
		}
	});

	$.Taiji.base64 = new Base64();
	
})(jQuery,window,document);




/******************************************************************************
 * jQuery taiji pager plugin
 * 
 * @requires  
 *    taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.15 2013.10.25
 *  
 * @since 2.0.8
 * 
 *****************************************************************************/
(function($){
	$.Taiji.extendMethod({
		 name:"pager",
		    event:{
				click:{
					"className":"_handlePagerClick",
					"gotoClassName":"_handleGotoClick"
				}
			},
		    eventHandler:{
				/**
				 * 
				 * 分页上标签click事件处理函数
				 * 
				 * @param 
				 *		{Object} element 当前点击的HTML节点
				 * 
				 */
				_handlePagerClick : function(element) {
					var pageclickednumber = $(element).attr("value");
					this._handlePageClick(pageclickednumber);
				},
				/**
				 * 
				 * 分页上标签click事件处理函数
				 * 
				 * @param 
				 *		{Number} pageclickednumber 当前点击的页数
				 * 
				 */
				_handlePageClick : function(pageclickednumber) {
					// 分页标签点击处理
					var $pageNo = $("input[name='pageNo']",
						this.currentTarget);
					if ($pageNo.attr("name")) {
						// 如果pageNo存在，则直接修改其值
						$pageNo.val(pageclickednumber);
					} else {
						// 否则，创建pageNo，并追加到form标签下。
						$pageNo = $(
						"<input type='hidden' id='pageNo' name='pageNo'/>")
						.val(pageclickednumber)
						.appendTo($(this.searchFormClassName,
							this.currentTarget));
					}
					// 提交表单
					$(this.searchFormClassName,
						this.currentTarget).trigger("submit");
				},
				/**
				 * 
				 * 跳转到标签click事件处理函数
				 * 
				 * @param 
				 *		{Object} element 当前点击的HTML节点
				 * 
				 */
				_handleGotoClick : function(element) {
					var $ele = $(element);
					var $pageNo = $ele.parent().find(".taiji_pager_input");
					//var $pageNo = $("input[name='pageNo']",	this.currentTarget);
					if($pageNo.is(".taiji_pager_input")){
						//---------验证
						var clickValue = $.trim($pageNo.val());
						//空
						if(!clickValue){
							$.Taiji.showWarn("请填写跳转页数");
							return;
						}
						//数字
						if(!/^\d+$/.test(clickValue)){
							$.Taiji.showWarn("请填写数字");
							return;
						}
						var pageclickednumber = parseInt(clickValue,10);
						//大小
						if(pageclickednumber > 100000){
							$.Taiji.showWarn("页数太大，请重新填写");
							return;
						}
						//-----------验证
						
						this._handlePageClick(pageclickednumber);
					}else{
						this.warn("没有name为pageNo的input框");
					}
				}
		    },
		    //可配置项
		   config : {
			    //分页标签上的的class
			    className:'.taiji_pager_item',
			    gotoClassName:'.taiji_pager_goto'
			}
		
	});
})(jQuery);
/******************************************************************************
 * jQuery taiji multiCore plugin
 * 
 * @requires  
 *    taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.15 2013.10.25
 * 
 * @since 2.0.8
 * 
 *****************************************************************************/
(function($){
	$.Taiji.extendMethod({
		 name:"multiCore",
		    event:{
				change:{
					"checkAll":"_handleMultiCoreCheckAll"
				}
			},
		    eventHandler:{
		        _handleMultiCoreCheckAll:function(element){
		           if($(element).is(":checked")){
		               $(this.settings.multiCore.checkAll,this.currentTarget).attr("checked",true);
		               $(this.settings.multiCore.checkOne,this.currentTarget).attr("checked",true);
			        } else {
				        $(this.settings.multiCore.checkAll,this.currentTarget).attr("checked",false);
				        $(this.settings.multiCore.checkOne,this.currentTarget).attr("checked",false);
			        }
			    }
		    },
		    //可配置项
		   config : {
			    //批量选择checkbox的class
			    checkAll:'.taiji_check_all',
			    //单项的
		        checkOne:'.taiji_check_one'
			}
		
	});
})(jQuery);
/******************************************************************************
 * jQuery taiji modal plugin
 * 
 * @requires  
 *     taiji v3.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 3.0 2015.05.14
 * 
 * @since 3.0
 * 
 *****************************************************************************/
(function($){
	$.Taiji.extendMethod({
		name:"modal",
		event:{
			click:{
				"modalDefault":"_handleDefaultModal",
				"modalLage":"_handleLageModal",
				"modalSmall":"_handleSmallModal",
				"modalFull":"_handleFullModal"
				
			}
		},
		customEvent:{"taijiModalPost":"_handleModalPost"},
		eventHandler:{
			_handleDefaultModal:function(element){
				this._handleModal(element,"");
			},
			_handleLageModal:function(element){
				this._handleModal(element,"modal-lg");
			},
			_handleSmallModal:function(element){
				this._handleModal(element,"modal-sm");
			},
			_handleFullModal:function(element){
				this._handleModal(element,"modal-message");
			},
			_handleModal:function(element,size){
				//检查是否已有其他操作正在进行中。
				if (this.isRunning === true) {
					return;
				} else {
					this.isRunning = true;
				}
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);	
				var $this=this;
				$(element).showModal({"size":size,backdrop:this.settings.backdrop,complete:function(){
						$this.isRunning = false;
						$.Taiji.hideLoading();
					}
				});
			},
			
			_handleModalPost : function(form, opts) {
				var myOpts={
					table:"add",
					bsSuccess:function(responseText,note){
						this._handleBsSuccess(responseText,note,options.table);
						$().hideModal();
					}
				}
				var options=$.extend(myOpts, opts);
				this.ajaxForm(form,options);
			}
		},
		
		//可配置项
		config:{
			modalDefault : '.taiji_modal',
			modalLage : '.taiji_modal_lg',
			modalSmall : '.taiji_modal_sm',
			modalFull : '.taiji_modal_full'
		}
	});
})(jQuery);	
	
/******************************************************************************
 * jQuery taiji open plugin
 * 
 * @requires  
 *     taiji v3.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 3.0 2015.07.09
 * 
 * @since 3.0
 * 
 *****************************************************************************/
(function($){
	$.Taiji.extendMethod({
		name:"open",
		event:{
		},
		customEvent:{"taijiOpenPost":"_handleOpenPost"},
		eventHandler:{
			
			_handleOpenPost : function(form, opts) {
				var myOpts={
					table:"add",
					bsSuccess:function(responseText,note){
						opener.$(opts.openerTarget).data("aTaiji")._handleBsSuccess(responseText,note,options.table);
						window.close();
					}
				}
				var options=$.extend(myOpts, opts);
				this.ajaxForm(form,options);
			}
		},
		
		//可配置项
		config:{
		}
	});
})(jQuery);		
/**
 * jQuery taiji autocomplate plugin
 * 
 * @requires  
 *     taiji v2.2.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * 自动完成功能改进：
 *  1.抛弃jquery.autocomplete.js插件，理由多多
 *  2.改为使用chosen.jquery.js实现页面自动完成
 *  3.改为使用ajax-chosen.js实现异步自动完成
 *  4.页面标签改为select，无需多一个input来模拟了。
 * 
 * @verion 2.2.5 2014.03.19
 * 
 * @since 2.2.4
 * 
 */
(function($){
	$.extend({
		autocompleteAll:function(){
			//找到所有class含有taiji_autocomplete的,并且可见的，并且没有绑定过的
			var alls = $(".taiji_autocomplete:visible:not(._taiji_autocomplete)"),
				remotes = [],locals = [];
			$(alls).each(function(){
				if($(this).is("[data-url]")){
					remotes.push($(this));
				}else{
					locals.push($(this));
				}
			});
			$.autocompleteRemote(remotes);
			$.autocompleteLocal(locals);
		},
		autocompleteRemote:function(elems){
			$(elems).each(function(){
				var url = $(this).attr("data-url");
				var that = $(this);
				var key = that.attr("name");
				that.ajaxChosen({
					type: 'POST',
					url: url,
					dataType: 'json',
					minTermLength:1,
					afterTypeDelay: 1000,
					jsonTermKey:key
				}, function (data) {
					var terms = {};
					
					$.each(data, function (i, val) {
						terms[val.id] = val.label;
					});
					
					return terms;
				}).addClass("_taiji_autocomplete");
			});
		},
		autocompleteLocal:function(elems){
			$(elems).each(function(){
				$(this).chosen({no_results_text:'没有匹配的选项!',search_contains:true}).addClass("_taiji_autocomplete");
			});
		}
	});
})(jQuery);

jQuery(function(){
	jQuery.autocompleteAll();
});
/**
 * jQuery taiji download plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.8 2013.06.14
 * 
 * @since 2.0.0
 *  
 */

(function($){
	$.Taiji.extendMethod({
		name:"download",
		event:{
			click:{
				"className":"_handleDownloadClick"
			}
		},
		eventHandler:{
			/**
			 * 
			 * downlaod的click事件处理函数
			 * 利用ajax请求，获取可能出错的信息
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleDownloadClick:function(elment){
				var $this = $(elment);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//如果当前还有未完成的操作，直接返回。
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}
				// 禁用当前下载链接，防止客户猛击
				$this.attr("disabled",true);
				$.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
				//ajax调用参数
				var $$this = this;
				var options = {
					url : $url,
					type : "POST",
					dataType : "html",
					success : function(responseText,
							status,
							xhr) {
						$$this.isRunning = false;
						if (xhr.getResponseHeader(
								$.Taiji.Constans.RESPONSE_HEADER_NOTE)) {
							//如果调用没问题，在新窗口中下载！
							window.open($url);
						} else if (xhr.getResponseHeader(
								$.Taiji.Constans.RESPONSE_HEADER_JME)) {
							//此处应该不会被调用到
							$.Taiji.showWarn(
								$$this.decode(
									xhr.getResponseHeader(
										$.Taiji.Constans.RESPONSE_HEADER_JME)));
						} else if (xhr.getResponseHeader(
									$.Taiji.Constans.RESPONSE_HEADER_ME)) {
							//此处应该不会被调用到
							$.Taiji.showWarn(
								$$this.decode(
											xhr.getResponseHeader(
												$.Taiji.Constans.RESPONSE_HEADER_ME)));
						} else if (
								xhr.getResponseHeader(
									$.Taiji.Constans.RESPONSE_HEADER_CVE)) {
							//此处应该不会被调用到
							$.Taiji.showWarn(
								$$this.decode(
									xhr.getResponseHeader(
										$.Taiji.Constans.RESPONSE_HEADER_CVE)));
						} else {
							//ajax返回非预期的值
							$.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE,
								xhr.status);
						}
						$.Taiji.hideLoading();
						$this.attr("disabled",
								false);
					},
					error : function(xhr) {
						//ajax调用出错
						$$this._handleOperateError(
										xhr.status,
										$this);
					}
				};
				//ajax调用
				$.ajax(options);
			},
			/**
			 * 
			 * 绑定download事件的函数，
			 * 私有的,请勿在外部直接调用
			 * 
			 */ 
			_bindDownload : function() {
				var $$this = this;
				//绑定click事件处理函数之前，先取消已绑定的click事件处理函数
				$(this.settings.download.className,
						this.currentTarget)
						.off("click.taiji")
						.on("click.taiji",
								function() {
									$$this._handleDownloadClick(this);
								});
			}
		},
		//可配置项
		config:{
			// download链接的class.
			className : '.taiji_download',
			// download成功之后，页面后续处理函数
			callback : function() {
			} 
		}
	});
})(jQuery);

	
/**
 * jQuery taiji exported plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.8 2013.06.14
 * 
 * @since 2.0.0
 *  
 */
(function($){
$.Taiji.extendMethod({
    name:"exported",
    event:{
		click:{
			"className":"_handleExportedClick"
		}
	},
	eventHandler:{
		/**
		 * 
		 * exported的click事件处理函数
		 * 私有的,请勿在外部直接调用
		 * 
		 * @param 
		 *		{Object} element 被点击的HTML节点
		 * 
		 */
		_handleExportedClick: function(element){
        //此处假设 弹出层内的查询页面不含有导出
			var $$this = this;
            var $this = $(element);
            var $url = $.Taiji.getUrl($this);
            if (!$url) {
                this.warn("没有找到导出的URL..");
                return;
            }
            
            // 将查询表单中多余的字段进行disabled
            // 使用search进行验证
            // AJAX执行导出
            // WARN:记得导出之后，将 action的url设置回来，并且取消disbaled。
            var query = "";
            $(this.settings.search.formClassName,$$this.currentTarget).find(":input").each(function(){
                // if(!$.inArray($(this).attr("name"),$$this.settings.exported.includeFields)){
                     query += $(this).attr("name");
                     query += "=";
                     query += $(this).val();
                     query += "&";
                 //}
              });
			$(this.settings.search.formClassName,$$this.currentTarget).find(":selected").each(function(){
				// if(!$.inArray($(this).attr("name"),$$this.settings.exported.includeFields)){
				query += $(this).attr("name");
                query += "=";
                query += $(this).val();
                query += "&";     
				//}
			});
			if($url.indexOf("?") > -1){
				query = "&" + query;
			}else{
				query = "?" + query;
			}
            $url += encodeURI(query);
            //利用查询表单的验证
                if (this.isRunning === true) {
                    this.warn($.Taiji.Messages.WAR_OPERATE);
                    return false;
                } else {
                    $$this.isRunning = true;
                }
        
                $this.attr("disabled", true);
                $.Taiji.showLoading($.Taiji.Messages.MSG_OPERATE);
                $.ajax({
                        url : $url,
                        type : "GET",
                        dataType : "html",
                        success : function(
						responseText,
						status,
						xhr) {
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._handleExportedSuccess(
							xhr.status,
							responseHeader,
							responseText,
							$this,
							$url);
					},
					error : function(xhr) {
						$$this._handleOperateError(
										xhr.status,
										$this);
					}
				});  
        },
        _handleExportedSuccess : function(status,responseHeader,responseText,obj,url)  {
            var $this = $(obj);
            $this.attr("disabled", false);
            //移出loading
            $.Taiji.hideLoading();
            //根据服务器返回的header头进行处理
            //操作成功
            if (responseHeader.note) {
                window.open(url,"_self");
            } else if (responseHeader.jme) {
                //操作失败,jme返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.jme));
            } else if (responseHeader.me) {
                //操作失败,me返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.me));
            } else if (responseHeader.cve) {
                //操作失败,cve返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.cve));
            } else {
                //神马情况，咋个处理啊
                this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
                $.Taiji.showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
            }

            this.isRunning = false;
        }
	},
	//配置项
	config:{
        className:".taiji_exported",
        includeAll:false,
        includeFields:[],
        rules:{}
    }

});
})(jQuery);

/**
 * jQuery taiji jump plugin
 * 
 * @requires  
 *     taiji v2.0.11 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.4.1 2015.03.01
 * 
 * @since 2.0.11
 *  
 */
(function($){
	$.extend($.Taiji,{
		/** @lends Taiji# */
		/**
		 * 
		 * 异步提交表单，操作成功之后，跳转到指定的URL上，失败则停留在当前页面
		 * 
		 * @param
		 *		{Object} form	待提交的表单。
		 *		{Object} options	表单提交的追加配置.
		 *		{String} button	响应点击的按钮，用于禁用。
		 *
		 */
		taijiJump:function(form,button,options){
			var $button = $(button);
			$.holdReady(true);
			$button.attr("disabled",true);
			//操作进行中。。与实例方法不一样
			$.Taiji.createLoading($.Taiji.Messages.MSG_OPERATE);
			//表单设置
			var $form = $(form),
				settings = {
				//成功
				success:function(data, status, xhr){
					var responseHeader = {
							note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
							jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
							me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
							cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
					};
					$.Taiji.hideLoading();
					if (responseHeader.note) {
						$.Taiji.showNote($.Taiji.base64.decode(responseHeader.note));
						
						var noteHead =$("head")[0];
						var si = data.indexOf("<script");
						var sdd = data.substr(si);
						var siii = data.indexOf(">",si);
						var ei = data.indexOf("</",siii);
						var sd = data.substr(siii+1,ei-siii-1);
						var noteScript = document.createElement("script");
						noteHead.appendChild(noteScript);
						noteScript.innerHTML = sd;
					} else if (responseHeader.jme) {
						// 操作失败,jme返回，显示警告信息
						$.Taiji.showWarn($.Taiji.base64.decode(responseHeader.jme));
					} else if (responseHeader.me) {
						// 操作失败,me返回，显示警告信息
						$.Taiji.showWarn($.Taiji.base64.decode(responseHeader.me));
					} else if (responseHeader.cve) {
						var ejson = $(data).find("#taiji_ejson");
						var	ejsonContent = $.parseJSON($(ejson).html());
						$("[generated=true]", $form).each(
								function() {
									$(this).html("");
								});
						$.each(ejsonContent,
								function(i, n) {
									var $nextLabel = $("[for=" + i + "]");
									if ($nextLabel
											.hasClass($.Taiji.defaults.errorClass)) {
										$nextLabel.text(n);
										$nextLabel.show();
									} else {
										$nextLabel = $("<label></label>");
										$nextLabel
												.addClass($.Taiji.defaults.errorClass);
										$nextLabel
												.attr({
													"for" : i,
													generated : true
												});
										$nextLabel.text(n);
										$("[name=" +i + "]",
												$form)
												.after($nextLabel);
									}
								});
						// 操作失败,cve返回，显示警告信息
						$.Taiji.showWarn($.Taiji.Messages.ERR_CVE);
					}else if(xhr.status === 200){
						var headStart=-1,headEnd=-1,
							head =$("head").html("")[0],
							tagList = ["src","defer","language","type"],
							scriptStart=-1,tagIndex=-1,scriptEnd=-1,
							tagListLength = tagList.length,
							scriptText=[],
							scriptSrcText=[],
							aText,aAtt;
						while(data.indexOf("<script") > -1){
							scriptStart = data.indexOf("<script");
							tagIndex = data.indexOf(">");
							scriptEnd = data.indexOf("</script");
							var temp = data.substr(scriptStart,tagIndex);
							if(temp.indexOf("src") > 0){
								scriptSrcText.push(data.substr(scriptStart,scriptEnd-scriptStart+9));
							}else{
								scriptText.push(data.substr(scriptStart,scriptEnd-scriptStart+9));
							}
							data = data.substr(0,scriptStart) + data.substr(scriptEnd+9);
						}
						var tagLength,start,end,text;
//						for(var c=0;c<scriptSrcText.length;c++){
//							aText = scriptSrcText[c];
//							tagIndex = aText.indexOf(">");
//							aAtt = aText.substr(0,tagIndex);
//							var aScript = document.createElement("script");
//							aScript.type="text/javascript";
//							head.appendChild(aScript);
//							for (var d=0;d<tagListLength;d++) {
//								tagLength = tagList[d].length+1;
//								if(aAtt.indexOf(tagList[d]+"=") > -1) {
//									start = aAtt.indexOf(tagList[d]+"=");
//									end = aAtt.indexOf(aAtt.charAt(start+tagLength),start+tagLength+2);
//									text = aAtt.substr(start+tagLength+1,end-start-tagLength-1);
//									
//									aScript[tagList[d]] = text;
//								}
//							}
//							if(aText.length > tagIndex+1){
//								var content = aText.substr(tagIndex+1,aText.length- tagIndex-10);
//								aScript.text=content;	
//							}
//							
//						}
						while(data.indexOf("<head") > 0){
							headStart = data.indexOf("<head");
							headEnd = data.indexOf("</head");
							var headHtml = data.substr(headStart,headEnd - headStart+7);
							$("head").append($(headHtml));
							data = data.substr(0,headStart) + data.substr(headEnd+7);
						}
						
						$("body").html(data);
						
						window.setTimeout(function(){
							for(var a=0;a<scriptText.length;a++){
								aText = scriptText[a];
								tagIndex = aText.indexOf(">");
								aAtt = aText.substr(0,tagIndex);
								var aScript = document.createElement("script");
								aScript.type="text/javascript";
								head.appendChild(aScript);
								for (var i=0;i<tagListLength;i++) {
									tagLength = tagList[i].length+1;
									if(aAtt.indexOf(tagList[i]+"=") > -1) {
										start = aAtt.indexOf(tagList[i]+"=");
										end = aAtt.indexOf(aAtt.charAt(start+tagLength),start+tagLength+2);
										text = aAtt.substr(start+tagLength+1,end-start-tagLength-1);
										
										aScript[tagList[i]] = text;
									}
								}
								if(aText.length > tagIndex+1){
									aScript.text = aText.substr(tagIndex+1,aText.length- tagIndex-10);
								}
							}
						},100);
						
					} else {
						// 其他情况，暂时不处理
						$.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE+xhr.status);
					}
					$button.attr("disabled",false);
				},
				//失败
				error: function(xhr){
					$.Taiji.hideLoading();
					$.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE + xhr.status);
					$button.attr("disabled",false);
				},
				dataType : "html",
				forceSync : true
			};
			//TODO 需要考虑那个在前的问题，免得页面覆盖了此处的success和error处理函数.
			$.extend(settings, options);
			//提交表单
			$(form).ajaxSubmit(settings);
		}
	});
	
})(jQuery);


/**
 * jQuery taiji multiOperate plugin
 * 
 * @requires  
 *    taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.8 2013.06.14
 * 
 * @since 2.0.8
 * 
 */

(function($){
	$.Taiji.extendMethod({
    name:"multiOperate",
    event:{
		click:{
			"className":"_handleMultiOperateClick"
		}
	},
	eventHandler:{
        /**
		 * 
		 * multiOperate的click事件处理函数
		 * 私有的,请勿在外部直接调用
		 * 
		 * @param 
		 *		{Object} element 被点击的HTML节点
		 * 
		 */
        _handleMultiOperateClick:function(element){
            var $$this = this;
            var $this = $(element);
            var $url = $.Taiji.getUrl($this);
            if (!$url) {
                $$this.warn("没有找到批量删除的URL..");
                return false;
            }
            
            if(!this.settings.multiCore.checkOne){
                $$this.warn("未设定checkbox的class");
                return false;
            }
            var $checkedList = $(this.settings.multiCore.checkOne+":checked");
            if($checkedList.size() === 0){
                $$this.warn("未选中任何项");
                $.Taiji.showWarn("请至少选中一个操作项");
                return false;
            }
            var $message = $.Taiji.getConfirmMessage($this);
            if ($message) {
                if (!window.confirm($message)) {
                    return false;
                }
            }	
            if ($$this.isRunning === true) {
                $$this.warn("前一次<" + $$this.running + ">操作尚未结束，当前multiOperate操作被禁止！");
                return false;
            } else {
                $$this.isRunning = true;
            }
    
            $this.attr("disabled", true);
            $.Taiji.showLoading("批量操作操作进行中，请稍等");
            var dataA = "";
			var theCheckboxName = $checkedList.attr("name");
			$checkedList.each(function () {
				dataA += theCheckboxName;
				dataA += "=";
				dataA += $(this).val();
				dataA += "&";
			});
			if (dataA.length > 1) {
				dataA = dataA.substr(0, dataA.length - 1);
			}
            $.ajax({
                url : $url,
                type : "POST",
                data : dataA,
                dataType : "html",
                success : function(
					responseText,
					status,
					xhr) {
					var responseHeader = {
							note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
							jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
							me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
							cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
					};
					$$this._handleMultiOperateSuccess(
						xhr.status,
						responseHeader,
						responseText,
						$this);
				},
				error : function(xhr) {
					$$this._handleOperateError(
									xhr.status,
									$this);
				}
            });
        },
        _handleMultiOperateSuccess : function(status,responseHeader,responseText,obj) {
            var $this = $(obj);
            $this.attr("disabled", false);
            //移出loading
            $.Taiji.hideLoading();
            this.isRunning = false;
            //根据服务器返回的header头进行处理
            //操作成功
            if(responseHeader.note) {

            	$.Taiji.showNote(this.decode(responseHeader.note));
                 if (this.settings.multiOperate.callback && typeof this.settings.multiOperate.callback === 'function') {
                    this.settings.multiOperate.callback(responseText);
               }
               //TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
                 $(this.currentTarget).trigger("taijiMultiOperateSuccess",[responseText]);
                 
            } else if (responseHeader.jme) {
                //操作失败,jme返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.jme));
            } else if (responseHeader.me) {
                //操作失败,me返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.me));
            } else if (responseHeader.cve) {
                //操作失败,cve返回，显示警告信息
                $.Taiji.showWarn(this.decode(responseHeader.cve));
            } else {
                //神马情况，咋个处理啊
                this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
                $.Taiji.showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
            }

        }
    },
    afterSearchMethod:function(){
        var $$this = this;
        $(this.settings.multiOperate.checkAllClassName,this.currentTarget).off("change.taiji").on("change.taiji",function(event){
           if($(this).is(":checked")){
               $(":input[name="+$$this.settings.multiOperate.checkboxName+"]",$$this.currentTarget).attr("checked",true);
               $($$this.settings.multiOperate.checkAllClassName,$$this.currentTarget).attr("checked",true);
           } else{
               $(":input[name="+$$this.settings.multiOperate.checkboxName+"]",$$this.currentTarget).attr("checked",false);
               $($$this.settings.multiOperate.checkAllClassName,$$this.currentTarget).attr("checked",false);
           }
           $.Taiji.preventDefault(event);
        });
        
        
    },
    //配置项
    config:{
    //multiOperate链接的class
        className:'.taiji_multiOperate',
      //multiOperate成功后的，页面回调函数（不推荐使用）
        callback:function(){}
    }
});
})(jQuery);
/**
 * jQuery taiji multiRemove plugin 
 * 
 * @requires taiji v2.0.8 or later
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.8 (2013.07.12)
 * 
 * @since 2.0.8
 * 
 */

(function ($) {
	$.Taiji.extendMethod({
		name : "multiRemove",
		event : {
			click : {
				"className" : "_handleMultiRemoveClick"
			}
		},
		eventHandler : {
			/**
			 * 
			 * multiRemove的click事件处理函数 私有的,请勿在外部直接调用
			 * 
			 * @param {Object}
			 *            element 被点击的HTML节点
			 * 
			 */
			_handleMultiRemoveClick : function (element) {

				var $$this = this;
				var $this = $(element);
				var $url = $.Taiji.getUrl($this);
				if (!$url) {
					$$this.warn("没有找到批量删除的URL..");
					return false;
				}

				if (!this.settings.multiCore.checkOne) {
					$$this.warn("未设定checkbox的class");
					return false;
				}
				var $checkedList = $(this.settings.multiCore.checkOne +
					":checked");
				if ($checkedList.size() === 0) {
					$.Taiji.showWarn("请至少选中一个操作项");
					return false;
				}
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window.confirm($message)) {
						return false;
					}
				}
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return false;
				} else {
					this.isRunning = true;
				}

				$this.attr("disabled", true);
				$.Taiji.showLoading("批量删除操作进行中，请稍等");
				var dataA = "";
				var theCheckboxName = $checkedList.attr("name");
				$checkedList.each(function () {
					dataA += theCheckboxName;
					dataA += "=";
					dataA += $(this).val();
					dataA += "&";
				});
				if (dataA.length > 1) {
					dataA = dataA.substr(0, dataA.length - 1);
				}
				$.ajax({
					url : $url,
					type : "POST",
					data : dataA,
					dataType : "html",
					success : function (responseText, status,
							xhr) {
						var responseHeader = {
							note : xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
							jme : xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
							me : xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
							cve : xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._handleMultiRemoveSuccess(
							xhr.status, responseHeader,
							responseText, $this);
					},
					error : function (xhr) {
						$$this._handleOperateError(xhr.status,
							$this);
					}
				});
			},
			_handleMultiRemoveSuccess : function (status,
					responseHeader, responseText, obj) {
				var $this = $(obj);
				$this.attr("disabled", false);
				// 移出loading
				$.Taiji.hideLoading();
				this.isRunning = false;
				// 根据服务器返回的header头进行处理
				// 操作成功
				if (responseHeader.note) {

					var $checkedList = $(this.settings.multiCore.checkOne +
						":checked");
					var $size = $checkedList.size();
					$checkedList.each(function (i, v) {
						$(this).parents("tr").remove();
					});

					$(".taiji_pager .totalcount", this.currentTarget)
							.each(
								function () {
									$(this).text(
										parseInt($(this).text(), 10) -
											$size);

								});

					$.Taiji.showNote(this.decode(responseHeader.note));
					if (this.settings.multiRemove.callback &&
						typeof this.settings.multiRemove.callback === 'function') {
						this.settings.multiRemove
								.callback(responseText);
					}
					// TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).trigger(
						"taijiMultiRemoveSuccess", [ responseText ]);

				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					$.Taiji.showWarn(this.decode(responseHeader.jme));
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					$.Taiji.showWarn(this.decode(responseHeader.me));
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					$.Taiji.showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
					$.Taiji.showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:",
						status);
				}

			}
		},
		// 可配置项
		config : {
			// 批量删除链接的class
			className : '.taiji_multiRemove',
			// 批量删除成功后的，页面回调函数（不推荐使用）
			callback : function () {
			}
		}

	});
})(jQuery);

/******************************************************************************
 * Taiji placeholder
 * 
 * placeholder模拟实现，
 * 在不支持placeholder的浏览器上进行模拟，支持的浏览器上还是使用原生的实现。
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.2.3
 * 
 * @since 2.2.3
 * 
 *****************************************************************************/
(function($){
	$.extend({
		//检查浏览器是否支持placeholder
		isSupportPlaceholder:(function(){
			return 'placeholder' in document.createElement('input'); 
		})(),
		//全部触发
		placeholderAll:function(){
			var inputs = $("input[placeholder]:visible");
			$.placeholder(inputs);
			var areas = $("textarea[placeholder]:visible");
			$.placeholder(areas);
		},
		//单个触发
		placeholder:function(elems){
			if($.isSupportPlaceholder) {
				return;
			}
			$(elems).each(function(){
				if($(this).is("[placeholder]")){
					 var $offset = $(this).offset(),
		                paddingLeft = $.getInt($(this).css("paddingLeft")) + $.getInt($offset.left) + 2 + "px",
		                paddingTop = $.getInt($(this).css("paddingTop")) + $.getInt($offset.top) + 2 + "px",
		                placeValue =  $(this).attr("placeholder"),
		                zindex = $(this).css("zIndex");
		            
						var $span = $("<span class='taiji_placeholder'></span>")
							.text(placeValue).css({left:paddingLeft,top: paddingTop});
		            
		            $("body").after($span);
		            
		            var that = $(this);
					$span.on("click",function(){
						that.focus();
					});
					$(this).on("propertychange",function(){
						if ($.trim($(that).val()) === '') {
							$span.show();
						} else {
							$span.hide();
						}
					}).addClass("_taiji_placeholder");
		            
				}
			});
		},
		getInt:function(str){
			return window.parseInt(str,10);
		}
	});
})(jQuery);

jQuery(function(){
	jQuery.placeholderAll();
});


/**
 * jQuery taiji async plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2014 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.8 2014.10.08
 * 
 * @since 2.0.8
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"async",
		event:{
			click:{
				"className":"_handleAsyncClick"
			}
		},
		eventHandler:{
			_handleAsyncClick:function(element){
				var that=this;
				var $asyncConfig=$.extend({},{"interval":4000},$(element).metadata());
				var options = { 
					bsSuccess: function success(responseText) {
							$.Taiji.showNote(responseText.msg);
							if ($("#alert-progress").size() === 0) {
								window.setTimeout(function(){
									that._handleAsyncProcess($asyncConfig.process,$asyncConfig.interval);
								},1000);
							}
					}
				};
				this.ajaxHref(element,options);
		   },
		   
		   _handleAsyncProcess : function($url,interval) {
				var $this = this;
				$.ajax({
					url : $url,
					type : "POST",
					dataType : "json",
					success : function(responseText,
							status, xhr) {
						var $alertProgress = $("#alert-progress");
						if(responseText.processType ==='SUCCESS'){
							// 任务完成
							$($this.currentTarget).triggerHandler("taijiAsyncSuccess",[responseText.result]);
							$alertProgress.remove();
							return;
						}
						
						var percent=Math.round(responseText.percent*100);
						if ($alertProgress && $alertProgress.size() === 1) {
							$(".progress-bar",$alertProgress).text(percent+"%").css({"width":percent +"%"});
							$("strong",$alertProgress).text(responseText.msg);
						} else {
							var html=
							'<div id="alert-progress" class="alert alert-success  "  style="width:500px;display: block;">'+
								'<div class="progress progress-striped progress-lg active  m-t-5">'+
						            '<div   class="progress-bar progress-bar-success"></div>'+
						        '</div>'+
								'<strong></strong>'+
							'</div>';
							$alertProgress = $(html);
							$(".progress-bar",$alertProgress).text(percent+"%").css({"width":percent +"%","transition":"width "+interval/1000+"s linear 0s"});
							$("strong",$alertProgress).text(responseText.msg);
							$alertProgress.appendTo("body");
						}
						window.setTimeout(function(){
							  $this._handleAsyncProcess($url,interval);
							},interval);
					}
				});
			}
		},
		//可配置项
		config:{
			// async链接的class.
			className : '.taiji_async'
		}
	});
})(jQuery);

/**
 * jQuery taiji collapse plugin
 * 
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: lijun
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.5 2015.04.27
 * 
 * @since 2.5
 * 
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"collapse",
		event:{
			click:{
				"className":"_handleCollapseClick"
			}
		},
		eventHandler:{
			_handleCollapseClick : function(element) {
				var a=$(element);
				var sibling=a.closest("tr").next();
				if(sibling.hasClass("collapse")){
					sibling.remove();
					return;
				}
				var options={
						method:"POST",
						bsSuccess: function(data){
							   var tr,td,container;
							   tr=$("<tr>").addClass("collapse in");
							   td=$("<td>",{
								   "colspan":a.closest("tr").children().length
								   }).appendTo(tr);
							   container=$("<div>",{
								   "class":"well",
								   "html":$(data)
							   }).appendTo(td);
							   a.closest("tr").after(tr);
						   }
				};
				this.ajaxHref(element,options);
			}
			
		},
		//可配置项
		config:{
			// async链接的class.
			className : '.taiji_collapse'
		}
	});
})(jQuery);

/**
 * jQuery taiji update plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.8 2013.07.09
 * 
 * @since 2.0.8
 */

(function($){
	$.Taiji.extendMethod({
		name:"update",
		event:{
			click:{
				"className":"_handleUpdateClick"
			}
		},
		eventHandler:{
			/**
			 * 
			 * update的click事件处理函数.私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleUpdateClick:function(element){
				var $this = $(element);
				var options={
						method:"POST",
						bsSuccess:function(responseText,note){
							this._handleBsSuccess(responseText,note,"update");
						}
				};

				this.ajaxHref(element,options);
			}
		},
		config:{
			className : '.taiji_update'
		}
	});
})(jQuery);

/**
 * jQuery taiji operate plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.0.8 2013.06.14
 * 
 * @since 2.0.8
 *  
 */

(function($){
	$.Taiji.extendMethod({
		name:"operate",
		event:{
			click:{
				"className":"_handleOperateClick"
			}
		},
		eventHandler:{
			/**
			 * 
			 * operate的click事件处理函数
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleOperateClick:function(element){
				var $this = $(element);
				var options={
						method:"POST",
						bsSuccess:function(responseText,note){
							this._handleBsSuccess(responseText,note,"none");
							if($.Taiji.getMetadata($this,'refresh')){
								$(this.searchFormClassName,
										this.currentTarget).submit();
							}
						}
				};

				this.ajaxHref(element,options);
			}
		},
		//可配置项
		config:{
			className : '.taiji_operate'
		}
	});
})(jQuery);

/**
 * jQuery taiji remove plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.8 2013.07.09
 * 
 * @since 2.0.8
 * 
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"remove",
		event:{
			click:{
				"className":"_handleRemoveClick"
			}
		},
		eventHandler:{
			_handleRemoveClick:function(element){
				var $this = $(element);
				var options={
						method:"POST",
						bsSuccess:function(responseText,note){
							this._handleBsSuccess(responseText,note,"remove");
						}
				};

				this.ajaxHref(element,options);
			}
		},
		//可配置项
		config:{
			className : '.taiji_remove'
		}
	});
})(jQuery);


/**
 * jQuery taiji sortable plugin
 * 
 * @requires  
 *     taiji v2.0.9 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.9 2013.07.17
 * 
 * @since 2.0.9
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"sortable",
		event:{
			click:{
				"className":"_handleSortableClick"
			}
		},
		eventHandler:{
			/**
			 * 
			 * sortable的click事件处理函数
			 * 私有的,请勿在外部直接调用
			 * 
			 * @since 2.0.9
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleSortableClick:function(element){
				var base = this;
				var $this = $(element);
				//如果当前还有未完成的操作，直接返回。
				if (base.isRunning === true) {
					base.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} 
				
				var sortName = "",sortType='false';
				if($.metadata){
					var md = $this.metadata();
					sortName = md.orderBy;
					if(!sortName) {
						return;
					}
					sortType = md.desc;
					if($this.hasClass("taiji_asc")){
						$this.removeClass("taiji_asc").addClass("taiji_desc");
						sortType = "true";
					}else if($this.hasClass("taiji_desc")){
						$this.removeClass("taiji_desc").addClass("taiji_asc");
						sortType = "false";
					}else if(sortType === true){
						$this.addClass("taiji_desc");
					}else{
						$this.addClass("taiji_asc");
					}
				}else{
					return;
				}
				var sortable = base.settings.sortable;
				var search = base.settings.search;
				var $searchForm = $(search.formClassName,base.currentTarget);
				var $sortName = $(base.currentTarget)
					.find("input[name='orderBy']");
				if(!$sortName.val()){
					$sortName = $("<input type='hidden' name='orderBy'/>")
					.appendTo($searchForm);
				}
				$sortName.val(sortName);
				var $sortType = $(base.currentTarget)
				.find("input[name='desc']");
				if(!$sortType.val()){
					$sortType = $("<input type='hidden' name='desc'/>")
						.appendTo($searchForm);
				}
				$sortType.val(sortType);
				
				$(base.searchSubmitClassName,
					base.currentTarget).trigger("click");
			}
		},
		// 可配置项
		config:{
			// sortable 链接的class.
			className : '.taiji_sortable'
		}
	});
})(jQuery);

/******************************************************************************
 * jQuery taiji validator plugin
 * 
 * @requires  
 *     taiji v3.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * 借助bootstrap的popover实现提示
 * 
 * @version 3.0 2015-5-22
 * 
 * @since 3.0
 * 
 *****************************************************************************/
(function($){
	$.fn.extend({
		isVisible: function() {
			if($(this).isChosenSelect()) {return true;}
			return $(this).attr("type") !== "hidden" && $(this).css("display") !== "none" && $(this).css("visibility") !== "hidden";
		},
		//补丁20140422-placeholder
		isChosenSelect:function(){
			return $(this).is("select") && $(this).is(":hidden") && $(this).next().is("div.chosen-container");
		},
		showPopover:function(content, options) {
			// 如果元素不可见，不处理
			if (!content || !$(this).isVisible()) {
				return;
			}
			var control=this;
			if(this.isChosenSelect()){
				control=this.next();
			}
			var errorEle=control.parent();
			errorEle.addClass("has-error");
			
			control.removeAttr("title").attr("data-content",content);
			control.popover({ 
							placement: 'auto',
							//container:'#modal-dialog .modal-content',
							//viewport: {selector: '#modal-dialog .modal-content', padding: 2},
							trigger:"manual"
							})
					.popover("show");
			if(options&&options.hideConcern){
				control.off("click.taiji focus.taiji keydown.taiji")
				control.on("click.taiji focus.taiji keydown.taiji",function(){$(this).hidePopover()});
			}
		},
		hidePopover:function(){
			this.popover("hide");
			var errorEle=this.parent();
			errorEle.removeClass("has-error");
		}
	});
	
	
	if($.validator){
		$.validator.prototype.prepareElement=function( element ) {
			this.reset();
			var $ele=$(element);
			if($ele.is(":radio")||$ele.is(":checkbox")){
				this.toHide = $ele.closest("div[data-for='"+$ele.attr("name")+"']");
			}else{
				this.toHide =$ele;
			}
		};
		jQuery.validator.setDefaults({
			showErrors: function(errorMap, errorList) {
				if(this.toHide.jquery&&errorList.length==0){
					this.toHide.hidePopover();
				}
				$.each(errorList,function(i,obj){
					var $ele=$(obj.element);
					var msg=obj.message;
					var pop=$ele;
					if($ele.is(":radio")||$ele.is(":checkbox")){
						pop=$ele.closest("div[data-for='"+$ele.attr("name")+"']");
					}
					pop.showPopover(msg);
				});
			  },
			  debug: false
		});
	}
})(jQuery);



/******************************************************************************
 * jQuery taiji modal plugin
 * 
 * @requires  
 *     taiji v3.0 or later
 *     
 * 借助bootstrap的modal组件实现弹出层
 * 
 * @version 3.0 2015-5-27
 * 
 * @since 3.0
 * 
 *****************************************************************************/
(function($){
	$.fn.extend({
		showModal:function(opts) {
			var defaults={
				size:"",
				backdrop:true,
				complete:function(){}	
			};
			var options = $.extend({}, defaults, opts);
			if(this.length==0){
				return;
			}
			var $element=this.eq(0);
			var href=$element.attr("href");
			var $modalDialog =$("#modal-dialog");
			if($modalDialog.length==0){
				var templateModal='<div class="modal fade" id="modal-dialog">'+
									'<div class="modal-dialog">'+
										'<div class="modal-content">'+
										'</div>'+
									'</div>'+
								'</div>';
				$modalDialog=$(templateModal).prependTo(document.body);
				
				$modalDialog.on("shown.bs.modal",function(){
				   $.autocompleteAll();
			    }).on("hidden.bs.modal",function(){
			       $(this).removeClass("modal-message");
				   $(this).find(".modal-dialog").removeClass("modal-lg").removeClass("modal-sm");
				   $(this).find(".modal-content").empty();
			    });
			}
			var dataObj={},method="GET";
			if($element.data("selector")){
				method="POST";
				$($element.data("selector")).each(function(){
					var e=$(this);
					if(e.attr("name")&&e.val()){
						var key=e.attr("name"),value=e.val();
						if(dataObj[key]){
							var oldVal=dataObj[key];
							if($.type(oldVal)=="array"){
								oldVal.push(value)
							}else{
								dataObj[key]=[];
								dataObj[key].push(oldVal);
								dataObj[key].push(value);
							}
						}else{
							dataObj[key]=value;
						}
					}
				});
			}
			if(options.data){
				method="POST";
				$.extend(dataObj, options.data);
			}
			//$.Taiji.log(dataObj);
			$.ajax({
				   type: method,
				   url: href,
				   data:dataObj,
				   traditional:true,
				   success: function(data){
					   if(options.size=="modal-message"){
						   $modalDialog.addClass(options.size);
					   }else{
						   $modalDialog.find(".modal-dialog").addClass(options.size);
					   }
					   $modalDialog.find(".modal-content").html(data);
					   $modalDialog.modal({backdrop:options.backdrop,show:true});
				   },
				   error : function (xhr) {
					   $.Taiji.showWarn($.Taiji.Messages.ERR_RESPONSE + xhr.status);
				   },
				   complete: function (xhr) {
						options.complete.call($element);
					}
				   
				});
		},
		hideModal:function(opts) {
			var $modalDialog =$("#modal-dialog");
			if($modalDialog.hasClass("in")){
				$modalDialog.modal("hide");
			}
		},
		
	});
		
})(jQuery);
/*******************************************************************************
 * Taiji inputCase
 * 
 * toUpperCase 转换为大写 toLowerCase 转换为小写 scb2dbc 全角到半角 toDbcUpperCase
 * 全角到半角，并且转换为大小 toDbcLowerCase 全角到半角，并且转换为小写
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * 
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.2.11 2014.04.15
 * 
 * @since 2.2.11
 * 
 ******************************************************************************/
var tjUtils = tjUtils || {};

(function ($) {
	$.extend(tjUtils, {
		toUpperCase : function () {
			$(this).val($(this).val().toUpperCase());
		},
		toLowerCase : function () {
			$(this).val($(this).val().toLowerCase());
		},
		scb2dbc : function () {
			var str = $(this).val(), result = "";
			for ( var i = 0; i < str.length; i++) {
				var c = str.charCodeAt(i);
				if (c === 12888) {
					result += String.fromCharCode(32);
				} else if (c > 65280 && c < 65375) {
					result += String.fromCharCode(c - 65248);
				} else {
					result += String.fromCharCode(c);
				}
			}
			$(this).val(result);
		},
		toDbcUpperCase : function () {
			var str = $(this).val(), result = "";
			for ( var i = 0; i < str.length; i++) {
				var c = str.charCodeAt(i);
				if (c === 12888) {
					result += String.fromCharCode(32);
				} else if (c > 65280 && c < 65375) {
					result += String.fromCharCode(c - 65248);
				} else {
					result += String.fromCharCode(c);
				}
			}
			$(this).val(result.toUpperCase());
		},
		toDbcLowerCase : function () {
			var str = $(this).val(), result = "";
			for ( var i = 0; i < str.length; i++) {
				var c = str.charCodeAt(i);
				if (c === 12888) {
					result += String.fromCharCode(32);
				} else if (c > 65280 && c < 65375) {
					result += String.fromCharCode(c - 65248);
				} else {
					result += String.fromCharCode(c);
				}
			}
			$(this).val(result.toLowerCase());
		}
	});
})(jQuery);

