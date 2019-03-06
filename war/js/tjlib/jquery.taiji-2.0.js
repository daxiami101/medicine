/******************************************************************************
 * jQuery taiji plugin 
 * 
 * @requires    jQuery v1.7.2 
 * @requires    pager v1.0 (jQuery plugin) 改进版 
 * @requires    form v3.2.8(jQuery plugin) 
 * @requires    validate v1.11.1(jQuery plugin) 
 * @requires    nyroModal V2.0.0(jQuery plugin) 
 * @requires    metadata v2.1(jQuery plugin) 
 * @requires    base64
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.5.2(2015-05-18)
 * 
 * @since 1.0.0
 * 
 *****************************************************************************/
(function ($,window,document) {
	//当前版本号
	var version = '2.5.2';

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
		// 预先初始化提示、警告、loading的div
		this.$warnDiv = $("<div class='taiji_warn'></div>").appendTo(
			this.currentTarget);
		this.$noteDiv = $("<div class='taiji_note'></div>").appendTo(
			this.currentTarget);
		this.$loadingDiv = $("<div class='taiji_loading'></div>").appendTo(
			this.currentTarget);
		this.aclCheck($(this.settings.aclClassName,this.currentTarget));
		// 计算警告div的大小及文字大小
		this.$pl = this.$warnDiv.css("padding-left");
		this.$fs = this.$warnDiv.css("font-size");
		this.$pl = this.$pl ? parseInt(this.$pl, 10) ? 
				parseInt(this.$pl, 10) : 10 : 10;
		this.$fs = this.$fs ? parseInt(this.$fs, 10) ? 
				parseInt(this.$fs, 10) : 12 : 12;
		this.repostion();
		
		// 缓存一些设置中的值，减少获取链
		this.searchFormClassName = this.settings.search.formClassName;
		this.searchSubmitClassName = this.settings.search.submitClassName;
		this.searchResetClassName = this.settings.search.resetClassName;
		this.searchAutoRefreshEnable = this.settings.search.autoRefresh.enable;
		this.searchAutoRefreshInterval = this.settings.search.autoRefresh.interval;
		// 弹出层的默认配置
		this.nyroModalConf = {
				defaults:{
					stack : this.settings.stack,
					closeOnClick: this.settings.closeOnClick,	
					closeButton : null,
					sizes : {
						minW : this.settings.width,
						minH : this.settings.height
					}
				}
		};
		// 从插件中获取弹出层大小设置
		var $self = this;
		$.each($.Taiji.useNyroModals,function(i,name){
			$self.nyroModalConf[name] = $.extend(true,{},$self.nyroModalConf.defaults,
				{
				sizes:{
					minW : $self.settings[name].width,
					minH : $self.settings[name].height
				}
			});
		});
		
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
			 * 完成所有的初始化操作，包括各种事件的绑定、弹出层大小设定等。
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

				// 这样在add,edit,popupRemove的时候，就无需单独处理了
				this._configRunning();
				// 绑定查询按钮的click事件
				this._bindSearchSubmitClick();
				// 绑定重置按钮的click事件
				this._bindSearchResetClick();
				// 绑定查询表单验证,并在验证通过之后，调用_search函数
				this._bindSerachFormValidate();
				// 绑定查询表单中的input text的回车事件
				this._bindSearchFormInputEnter();
				// 通过设置弹出层，来禁用或启用 isRunning参数，
				// 绑定事件处理函数
				this._bindAll();
				// 主动调用一次查询
				//2.2.8改进为默认执行一次查询；如何设置为false，则不执行。
				if(this.settings.search.autoSearch === true){
					$(this.searchFormClassName,
						this.currentTarget).trigger("submit");
				}else{
					//2.2.9	表头放入了thead中，为了支持多行表头
					if(this.settings.search.thInThead === true){
						$(this.settings.search.resultClassName,
							this.currentTarget).find("> table > tbody")
							.empty().append("<tr><td colspan='15' ><div class='taiji_not_found'>请先填写查询条件！</div></td></tr>");
					}else{
						//2.2.9	为了兼容以前的
						$(this.settings.search.resultClassName,
							this.currentTarget).find("> table > tbody")
							.find("tr:gt(0)").remove().end().append("<tr><td colspan='15' ><div class='taiji_not_found'>请先填写查询条件！</div></td></tr>");
					}
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
			 * 通过设置弹出层，来禁用或启用 isRunning参数，
			 * 这样在add,edit,popupRemove的时候，就无需单独处理了
			 * 
			 * @since 2.0.8
			 * 
			 * @version 2.2.15 解决一个页面多个taiji的时候，添加、修改按钮点击冲突的问题。
			 * 
			 *****************************************************************/
			_configRunning:function(){
				var $self = this,
					nmTop = $.nmTop();
				var	options = {};
					options["myinit"+this.currentTarget.attr("id")] = {
						is:function(nm){
							return true;
						},
						initElts:function(nm){
							$self.isRunning = true;
							$self.log("禁用--init");
						},
						afterShowCont:function(nm){
							//TODO 以后这个需要写在其他的地方
							$(".taiji_validator").remove();
							$.placeholderAll();
							$.autocompleteAll();
						},
						beforeClose:function(nm){
							if(nm.getInternal().stack.length>0) {
								return;
							}
							$self.isRunning = false;
							$self.log("启用");
							//TODO 以后这个需要写在其他的地方
							$(".taiji_validator").remove();
							$(".taiji_placeholder").remove();
						},
						afterClose:function(nm){
							$.placeholderAll();
						}
						
					};
				
				if(!(nmTop && nmTop.getInternal)){
					$.nmFilters(options); 
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
							if($target.is(".taiji_pager_input")){
								$self._handleGotoClick(
									$target.parent().find(".taiji_pager_goto"));
							}else{
								$($self.searchSubmitClassName,
									$self.currentTarget)
									.trigger("click");
							}
						}
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
						$this._removeLoading();
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
							var	ejsonContent = $.parseJSON($(ejson).html());
							$.each(ejsonContent,
								function(i, n) {
									// 2.4.4 20150508
									//如果按name没有找到的话，就按Id找，为了解决input的name一样，id不一样的问题
									if($("[name='"+i+"']",$this.currentTarget).size() ===0){
										$("#"+i,$this.currentTarget).remind(n,{prefix:"id"});
									}else{
										$("[name='"+i+"']",$this.currentTarget).remind(n);
									}
								
							});
							
						}else{
							$this._handleSearchSuccess(responseText);
						}	
						
					},
					// 查询失败处理函数
					error : function error(xhr) {
						$this._handleSearchError(xhr.status);
					}
				};
				// 创建loading
				this._createLoading($.Taiji.Messages.MSG_OPERATE);				
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
			 * 列表中的数据有变化的时候，就调用本函数，绑定行样式切换事件处理程序 
			 * 目前主要提供给 add,edit,search使用成功之后调用 
			 * 此方法为共用，单个方法调用成功之后绑定事件的请在各自的处理函数中单独绑定。
			 * 私有的,请勿在外部直接调用  
			 *
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			_bindDataChange : function(){
				// 绑定行样式切换
				this._bindCssToggle();
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
				//2.0.8以前的返回是按此方法进行处理
				if($responseText.is("table")){
					// 将服务器端的数据追加到 this.settings.search.result
					$(this.settings.search.resultClassName,
						this.currentTarget).html(
							$(responseText));
					// 处理分页
					this._bindPager_8();
				}else{
					// 处理分页
					if(this.version < '2.0.13'){
						//2.0.9--2.0.12
						this._bindPager_9($responseText.find("#taiji_search_pager"));
					}else{
						//>2.0.13,新的页面样式所需
						this._bindPager($responseText.find("#taiji_search_pager"));
					}
					
					var hasText = $responseText.find("#taiji_search_data tbody").children().size() > 0;
					//2.0.9及以后的使用此方法
					if(hasText === true){
						var $tbody=$responseText.find("#taiji_search_data tbody");
						
						this.aclCheck($tbody);
						var $trs,trsIndex =0,$myBody;
						//2.2.9	表头放入了thead中，为了支持多行表头
						if(this.settings.search.thInThead === true){
							$myBody = $(this.settings.search.resultClassName,
								this.currentTarget).find("> table > tbody");
							$myBody.empty().append($tbody.html());
							if(this.settings.search.autoAddOrder === true){
								var $headTr =  $(this.settings.search.resultClassName,
									this.currentTarget).find("> table > thead").find("tr:eq(0)");
								if($headTr.find("th:eq(0)").text() !== '序号'){
									$headTr.prepend("<th>序号</th>");
								}
								
								$trs = $myBody.children();
								$.each($trs,function(){
									++trsIndex;
									$(this).prepend("<td>"+trsIndex+"</td>");
								});
							}
								
						}else{
						//2.2.9	为了兼容以前的
							$myBody = $(this.settings.search.resultClassName,
								this.currentTarget).find("> table > tbody");
								$myBody.find("tr:gt(0)").remove().end().append($tbody.html());
							
							if(this.settings.search.autoAddOrder === true){
								var $bodyTr =  $myBody.find("tr:eq(0)");
								if($bodyTr.find("th:eq(0)").text() !== '序号'){
									$bodyTr.prepend("<th>序号</th>");
								}
								
								$trs = $myBody.find("tr:gt(0)");
								$.each($trs,function(){
									++trsIndex;
									$(this).prepend("<td>"+trsIndex+"</td>");
								});
							}
						}
					}else{
						//2.2.9	表头放入了thead中，为了支持多行表头
						if(this.settings.search.thInThead === true){
							$(this.settings.search.resultClassName,
								this.currentTarget).find("> table > tbody")
								.empty().append("<tr><td colspan='15' ><div class='taiji_not_found'>没有检索到符合条件的数据！</div></td></tr>");
						}else{
						//2.2.9	为了兼容以前的
							$(this.settings.search.resultClassName,
								this.currentTarget).find("> table > tbody")
								.find("tr:gt(0)").remove().end().append("<tr><td colspan='15' ><div class='taiji_not_found'>没有检索到符合条件的数据！</div></td></tr>");
						}
					}
				}
				
				//绑定事件
				this._bindDataChange();
				// 移出loading
//				this._removeLoading();
				// 恢复查询按钮
//				$(this.searchSubmitClassName,
//					this.currentTarget).attr("disabled",
//						false);
				// 如果设置了callback，调用
				if (this.settings.search.callback &&
						typeof this.settings.search.callback === 'function') {
					this.settings.search.callback(responseText);
				}
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
				//查询成功之后，改为事件传播方式，用于取代callback方式
				$(this.currentTarget).triggerHandler(
					"taijiSearchSuccess",[responseText]);
				
//				this.isRunning = false;

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
			 * TODO 看能不能支持所有的出错处理
			 * 
			 *****************************************************************/
			_handleOperateError : function(statusCode, obj) {
				var $this = $(obj),
					showMessage = $.Taiji.Messages.ERR_RESPONSE +statusCode;
				this._showWarn(showMessage);
				this.warn(showMessage);
				$this.attr("disabled", false);
				this._removeLoading();
	
				this.isRunning = false;
			},
			/******************************************************************
			 * 
			 * 绑定样式切换函数(mouseover,mouseout,click)
			 * 私有的,，请勿在外部直接调用
			 *
			 * @version 2.0.0
			 * 
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			_bindCssToggle : function() {
				// 行样式切换效果
				$(this.settings.search.resultClassName,
						this.currentTarget)
						.find("> table > tbody > tr")
						.off("mouseover.taiji")
						.on(
								"mouseover.taiji",
								function(e) {
									$(this).addClass(
											"taiji_mouseover");
								})
						.off("mouseout.taiji")
						.on(
								"mouseout.taiji",
								function(e) {
									$(this).removeClass(
											"taiji_mouseover");
									// 行选中效果
								})
						.off("click.taiji")
						.on(
								"click.taiji",
								function(event) {
									$(this)
											.addClass(
													"taiji_clicked")
											.siblings()
											.removeClass(
													"taiji_clicked");
								});
			},
			/******************************************************************
			 * 
			 * 绑定分页处理函数
			 * 私有的,，请勿在外部直接调用
			 *
			 * @version 2.0.1
			 * 
			 * @since 2.0.1
			 *
			 * @deprecated 2.0.9之后不再使用，但为兼容还保留
			 *
			 *****************************************************************/
			_bindPager_8 : function() {
				// 添加分页样式
				var $this = this;
				$(".taiji_pager", this.currentTarget)
					.each(function() {
						$(this).pager({
							pagenumber : $(this).attr("pageNo"),
							pagecount : $(this).attr("pagecount"),
							totalcount : $(this).attr("totalcount"),
							buttonClickCallback : function(
								pageclickednumber) {
									$this._handlePageClick(pageclickednumber);
								}
							});
						});
			},
			/******************************************************************
			 * 
			 * 绑定分页处理函数
			 * 私有的,，请勿在外部直接调用
			 *
			 * @since 2.0.9
			 *
			 * @since 2.0.9
			 *
			 * 分页方面的东东有变化
			 *
			 * @deprecated 2.0.13之后，弃用此方法
			 *****************************************************************/
			_bindPager_9 : function($searchPager) {
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
						$(this).pager({
							pagenumber : pageNo,
							pagecount : pageCount,
							totalcount : totalCount,
							buttonClickCallback : function(
								pageClickedNumber) {
								$this._handlePageClick(pageClickedNumber);
							}
						});
					}).show();
					$(".taiji_pager_go",$this.currentTarget).show();
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
						var $ul = $("<ul></ul>");
						var $li_total_record = $("<li  class='prev disabled'><a >共<label class='totalcount'>"+totalCount+"</label>条/"+pageCount+"页</a></li>").appendTo($ul);
						var $li_prev = $("<li ><a href='#' class='taiji_pager_item' value='"+(pageNo-1)+"'>&lt;</a></li>").appendTo($ul);
						if(pageNo === 1){
							$li_prev.addClass("disabled prev").find("a").removeClass("taiji_pager_item");
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
						
						$(this).html($ul);
					}).show();
					$(".taiji_pager_input",$this.currentTarget).val(pageNo);
					$(".taiji_pager_go",$this.currentTarget).show();
				}
			},
			
			/******************************************************************
			 * 
			 * 查询出错的处理函数
			 * 
			 * @param 
			 *		{Object} statusCode 出错状态码 
			 *
			 * @version 2.0.0
			 * 
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			_handleSearchError : function(statusCode) {
				// 查询失败处理程序
				this._showWarn($.Taiji.Messages.ERR_RESPONSE + statusCode);
				this._removeLoading();
				$(this.searchSubmitClassName,
						this.currentTarget).attr("disabled",
						false);
				this.isRunning = false;
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
			_createLoading : function(message) {
				// 创建loading
				this.log("[_createLoading]" + message);
				var offset = $(this.currentTarget).offset();
				var len = message.length,
					width = len * this.$fs + this.$pl * 2,
					x = (this.$windowW - width- offset.left) / 2 + this.$sw,
					y = (this.$windowH - offset.top) / 3 + this.$sh;
				
				this.$loadingDiv.css({"width":width,"left": x,"top":y})
						.text(message).fadeIn(10);
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
			_removeLoading : function() {
				// 移出loading
				this.$loadingDiv.fadeOut(10);
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
			_showWarn : function(message) {
				if (typeof message !== 'string') {
					this.warn("[_showWarn] 非字符串无法显示");
				}
				this.log("[_showWarn]" + message);
				var offset = $(this.currentTarget).offset();
				var len = message.length,
					width = len * this.$fs + this.$pl * 2,
					x = (this.$windowW - width- offset.left) / 2 + this.$sw,
					y = (this.$windowH - offset.top)/ 3 + this.$sh;
				
				// 显示警告信息，指定时间之后，消失
				this.$warnDiv.css({"width":width,"left":x,"top":y})
						.text(message).fadeIn(500).delay(
								this.settings.warnShowTime)
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
			_showNote : function(message) {
				if (typeof message !== 'string') {
					this.warn("[_showWarn] 非字符串无法显示");
				}
				// 显示提示信息,指定时间之后，消失
				this.log("[_showNote]" + message);
				var offset = $(this.currentTarget).offset();
				var len = message.length,
					width = len * this.$fs + this.$pl * 2,
					x = (this.$windowW - width- offset.left) / 2 + this.$sw,
					y = (this.$windowH- offset.top) / 3 + this.$sh;
				
				this.$noteDiv.css({"width":width,"left":x,"top":y})
						.text(message).fadeIn(500).delay(
								this.settings.noteShowTime)
						.fadeOut(500);
			},
			/******************************************************************
			 * 
			 * 获取meta方式配置的弹出层宽和高，并且与传进来的参数进行合并。
			 * 
			 * 提供给插件使用的方法
			 * 
			 * @param {Object} 
			 *			element class中可能还有{width:10px,height:10px}的节点
			 * @param {String} 
			 *			pluginName 插件的名字，用于在缓存中获取 nyromodal配置
			 *
			 * @version 2.0.8
			 * 
			 * @since 2.0.0
			 * 
			 *****************************************************************/
			getMetaWH:function(element,pluginName){
				var currentWH = this.nyroModalConf[pluginName],
					$myMetadata;
				if ($.metadata) {
					$myMetadata = $(element).metadata();
					$myMetadata.sizes = {};
					if ($myMetadata.width) {
						$myMetadata.sizes.minW = $myMetadata.width;
					}
					if ($myMetadata.height) {
						$myMetadata.sizes.minH = $myMetadata.height;
					}
					currentWH = $.extend(true, {},
						currentWH,
						$myMetadata);
				}
				return currentWH;
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
				
				//console.log("hrefs:"+hrefs);
				//所有uri一次请求
				$.ajax({
					  type: "POST",
					  url: rootUrl+"app/acl/hasbuttons",
					  data:"uris="+hrefs,
					  async: false,
					  dataType:"json",
					  success: function(list){
						  //{"hasButton":true,"msg":""}
						  //console.log(list);
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
		/** @lends Taiji# */
		/**********************************************************************
		 * 支持插件的方法
		 * 
		 * @param 
		 *		{Object} acustomMethod 插件
		 *		格式为: {name:'',initMethod:function(){},afterSearchMethod:function{}}
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 *
		 * @deprecated 2.0.8版本之后，插件方法有改进，此方法已经弃用 参见 @link extendMethod 
		 * 
		 *********************************************************************/
		addCustomMethod : function(acustomMethod) {
			$.Taiji.initMethods[acustomMethod.name] = acustomMethod.initMethod;
			$.Taiji.afterSearchMethods[acustomMethod.name] = acustomMethod.afterSearchMethod;
			$.Taiji.defaults[acustomMethod.name] = acustomMethod.defaults;
		},
		/**********************************************************************
		 * 
		 * 目前想想法是，将插件的方法放在此处，后面调用
		 * 插件目前想法是：通过将在指定的class上绑定事件处理函数，来达到增强的目的。
		 * initMethods,将只在初始化的时候调用一次
		 * 希望能减少调用次数
		 *
		 * @version 2.0.0
		 *
		 * @since 2.0.0
		 * 
		 * @deprecated 
		 *		2.0.8版本之后，插件方法有改进，此方法已经弃用 参见 
		 *				@link events,customEvents,useNyroModals 
		 * 
		 * 
		 *********************************************************************/
		initMethods : {},
		/**********************************************************************
		 * 
		 * 目前想想法是，将插件的方法放在此处，后面调用
		 * 插件目前想法是：通过将在指定的class上绑定事件处理函数，来达到增强的目的。
		 * beforeSearchMethods,将在查询之前调用，与查询结果相关
		 * 每次查询结果有变化的时候，都将调用。包括添加新数据、修改旧数据等。
		 *
		 * @version 2.0.0
		 *
		 * @since 2.0.0
		 * 
		 * @deprecated 
		 *		2.0.8版本之后，插件方法有改进，此方法已经弃用 参见 
		 *				@link events,customEvents,useNyroModals 
		 * 
		 *  
		 *********************************************************************/
		afterSearchMethods : {},
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
		 *			userNyroModal:true,
		 *			//通过响应哪些事件(标准事件click,change等)来实现插件功能
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
			if(aExtendMethod.userNyroModal === true){
				$.Taiji.useNyroModals.push(aExtendMethod.name);
			}
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
		 * 插件添加的标准事件处理程序，
		 * 
		 * @version 2.1.1
		 * 
		 * @since 2.1.1
		 * 
		 **********************************************************************/
		eventsSubject:{},
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
		 * 插件添加的需要弹出层设置，
		 * 
		 * @version 2.0.8
		 * 
		 * @since 2.0.8
		 * 
		 **********************************************************************/
		useNyroModals:[],
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
		 * 获取meta方式配置的弹出层宽和高，并且与传进来的参数进行合并。
		 * 
		 * @param {Object} 
		 *			element class中可能还有{width:10px,height:10px}的节点
		 * @param {Object} 
		 *			currentWH 已有的弹出层宽和高 格式{sizes:{width:10px,height:10px}}
		 * 
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 *********************************************************************/
		getMetaWH:function(element,currentWH){
			if ($.metadata) {
				var $metadata = $(element).metadata();
				$metadata.sizes = {};
				if ($metadata.width) {
					$metadata.sizes.minW = $metadata.width;
				}
				if ($metadata.height) {
					$metadata.sizes.minH = $metadata.height;
				}
				currentWH = $.extend(true, {},
					currentWH,
					$metadata);
			}
			return currentWH;
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
		 * 元素的class属性是否有customForm
		 * 
		 * @param {Object}
		 *			obj 节点
		 * @return {Boolean}
		 *			是否有customForm
		 * 
		 * @version 2.4
		 * 
		 * @since 2.4
		 * 
		 *********************************************************************/
		hasCustomForm : function(obj){
			var $obj = $(obj);
			return $.metadata && $obj.metadata().custom_form;
		},
		
		/**********************************************************************
		 * 
		 * 创建一个name为myCustomForm的form,将指定的input复制过来,追加到body后面
		 * 
		 * @param {Object}
		 *			obj 节点
		 * @return {jQuery Object}
		 *			customForm
		 * 
		 * @version 2.4
		 * 
		 * @since 2.4
		 * 
		 *********************************************************************/
		createCustomForm : function(obj){
			var $obj = $(obj);
			var url=this.getUrl(obj);
			var ids =$obj.metadata().custom_form;
			var createForm=$("<form>",{
						name:"myCustomForm",
						action:url,
						method:"post"
					})
					.append($(ids).clone()
							.removeAttr("id"))
					.appendTo(document.body)
					.hide();
			return createForm;
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
				callback : function() {
				}, // 分页查询成功之后，调用
				autoRefresh : {
					enable : false,
					interval : 60000
				},
				autoSearch:true,
				thInThead:false,
				autoAddOrder:false
			},

			width : 640,
			height : 480,
			stack : true,
			//点击暗色背景的时候，是否关闭弹出层
			closeOnClick : true,

			// 提示信息显示时间
			noteShowTime : 2000, 
			// 出错信息显示时间
			warnShowTime : 2000, 


			// 添加或修改页面，后台字段验证出错的时候，显示错误信息的Element类型，此处添加主要是为了与jquery.validate.js保持一致
			errorElement : "label", 
			// 添加或修改页面，后台验证出错的时候，显示错误信息使用的Class类型，此处添加主要是为了与jquery.validate.js保持一致
			errorClass : "taiji_error", 
			
			//非菜单是否启用权限检查
			aclClassName:".taiji_acl",
			enableAclCheck : false,

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
			userNyroModal:false,
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
							this._showWarn("请填写跳转页数");
							return;
						}
						//数字
						if(!/^\d+$/.test(clickValue)){
							this._showWarn("请填写数字");
							return;
						}
						var pageclickednumber = parseInt(clickValue,10);
						//大小
						if(pageclickednumber > 100000){
							this._showWarn("页数太大，请重新填写");
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
			userNyroModal:false,
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
 * jQuery taiji add plugin
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
 *****************************************************************************/
(function($){
	$.Taiji.extendMethod({
		name:"add",
		event:{
			click:{
				"className":"_handleAddClick"
			}
		},
		userNyroModal:true,
		customEvent:{"taijiAddPost":"_handleAddPost"},
		eventHandler:{
			/******************************************************************
			 * 
			 * add的click事件处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 *
			 *****************************************************************/
			_handleAddClick:function(element){
				//检查是否已有其他操作正在进行中。
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}
				var $this = $(element),
				// 获取添加弹出层的高度和宽度配置
					$metacurrent = this.getMetaWH($this,"add");
				//如果自定义form，只能post提交
				if($.Taiji.hasCustomForm(element)){
					var customForm=$.Taiji.createCustomForm(element);
					customForm.nm($metacurrent);
					customForm.submit();
					customForm.remove();
				}else{
					//触发nyroModal事件，弹出添加层
					$.nmManual($this.attr("href"),$metacurrent);
					//$this.nyroModal($metacurrent).trigger('nyroModal');
				}
		
			},
			/******************************************************************
			 * 
			 * 绑定add事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 *****************************************************************/
			_bindAdd : function() {
				var $$this = this;
				//绑定click事件处理函数之前，先取消已绑定的click事件处理函数
				$(this.settings.add.className,
						this.currentTarget)
						.off("click.taiji")
						.on("click.taiji",
								function(event) {
									$$this._handleAddClick(this);
									//取消浏览器默认click事件处理函数
									$.Taiji.preventDefault(event);
								});
			},
			/******************************************************************
			 * 
			 * 在当前元素上绑定taijiAddPost事件。
			 * 替代 添加页面直接调用 $.taijiAddSubmit ，
			 * 添加页面替代为  $("myManage").triggerHandler("taijiAddPost",[form,options]);
			 * 
			 ******************************************************************/
			_bindAddPost:function(){
				var $self = this;
				$(this.currentTarget).off("taijiAddPost").on("taijiAddPost",
						function(event,form,options){
							$self._handleAddPost(form, options);
				});
			},
			/******************************************************************
			 * 
			 * add处理函数
			 * 以ajax方式向服务器提交表单，并显示相应信息。
			 * 
			 * @param
			 *		{Object} form 待提交的表单对象
			 * @param
			 *		{Object} options 表单提交的追加配置
			 * 
			 *****************************************************************/
			_handleAddPost : function(form, options) {
				//提示操作进行中
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				var $$this = this,
					$form = $(form),
				//ajax的配置项目
					settings = {
					success : function(data, status, xhr) {
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._handleAddSuccess(xhr.status,responseHeader,
								data, $form);
						$(":file", $form).each(function() {
							$(this).attr("disabled", false);
						});
					},
					error : function(xhr) {
						$$this._handleAddError(xhr.status);
						$(":file", $form).each(function() {
							$(this).attr("disabled", false);
						});
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
			 * add失败的处理函数
			 * 
			 * @param
			 *		String statusCode 出错码
			 * 
			 *****************************************************************/
			_handleAddError : function(statusCode) {
				this._removeLoading();
				this._showWarn($.Taiji.Messages.ERR_RESPONSE + statusCode);
				this.isRunnig = false;
			},
			/**
			 * add出现ME，JME的时候的处理函数，主要是触发 taijiAddMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleAddMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiAddMe",[responseText]);
			},
			/******************************************************************
			 * 
			 * add成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部信息
			 *		格式 {note:''，jme:'',me:'',cve:''}
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} $form	当前操作的表单对象
			 * 
			 *****************************************************************/
			_handleAddSuccess : function(statusCode, 
					responseHeader,
					responseText, 
					$form) {
				this._removeLoading();
				//根据返回的头部信息，获知当前操作状态
				if (responseHeader.note) {
					this._handleAddSuccessOK(responseText,this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleAddMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleAddMe(responseText);
				} else if (responseHeader.cve) {
					var ejson = $(responseText).find("#taiji_ejson"),
						$$this = this;
					var	ejsonContent = $.parseJSON($(ejson).html());
					$.each(ejsonContent,
						function(i, n) {
						// 2.4.4 20150508
						//如果按name没有找到的话，就按Id找，为了解决input的name一样，id不一样的问题
						if($("[name='"+i+"']",$form).size() ===0){
							$("#"+i,$form).remind(n,{prefix:"id"});
						}else{
							$("[name='"+i+"']",$form).remind(n);
						}
					});
					// 操作失败,cve返回，显示警告信息
					this._showWarn($.Taiji.Messages.ERR_CVE);
				}else if($(responseText).is("div")){
					var $taijiNote = $(responseText).find("#taiji_note");
					if($taijiNote.is("#taiji_note")){
						this._handleAddSuccessOK(responseText, $taijiNote.text());
					}else{
						// 其他情况，暂时不处理
						this.warn($.Taiji.Messages.ERR_RESPONSE,
								statusCode);
						this._showWarn(
								$.Taiji.Messages.ERR_RESPONSE,
								statusCode);
					}
				} else {
					// 其他情况，暂时不处理
					this.warn($.Taiji.Messages.ERR_RESPONSE,
							statusCode);
					this._showWarn(
							$.Taiji.Messages.ERR_RESPONSE,
							statusCode);
				}
				this.isRunning = false;
			},
			/******************************************************************
			 * 
			 * add成功的处理函数,附加数据到页面
			 * 
			 * @param
			 *		{Object} statusCode 状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部信息
			 *		格式 {note:''，jme:'',me:'',cve:''}
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} $form	当前操作的表单对象
			 *
			 * @since 2.0.0
			 *
			 * @version 2.4
			 * 
			 *****************************************************************/
			_handleAddSuccessOK:function(responseText,note){
				var $row1 = $(responseText).find(".taiji_result_data tr").addClass("taiji_clicked");
				//version 2.4
				this.aclCheck($row1);
				var $tbody = $(this.settings.search.resultClassName,
					this.currentTarget).find("> table > tbody");
				// 操作成功
//				$(this.settings.search.resultClassName,
//						this.currentTarget).find(
//						"table > tbody").find(
				$tbody.find(".taiji_clicked").removeClass(
						"taiji_clicked");
				//如果tbody没有内容，就直接添加在tbody内
				if($tbody.children().size() === 0){
					$tbody.append($row1);
				}else{
					var $firstTr = $tbody.find(">tr:first"); 
					//判断第一行是 th（表明第一行表头） 还是td(表明第一行是数据) ,
					if($firstTr.children().is("th")){
						//th 追加在th后面
						$firstTr.after($row1);
					}else if($firstTr.children().is("td")){
						//td 追加在td前面
						$firstTr.before($row1);
					}
				}
//				$(this.settings.search.resultClassName,
//						this.currentTarget).find(
//						"table > tbody > tr:first").after(
//							$row1.addClass("taiji_clicked"));
	
				this._bindDataChange();
				// 修改总条数
				$(".taiji_pager .totalcount",
						this.currentTarget)
						.each(function() {
									$(this).text(parseInt(
										$(this).text(),10) + 1);
								});
	
				// 显示提示信息
				this._showNote(note);
				// 关闭弹出层
				if($.nmTop && $.nmTop()){
					$.nmTop().close();
				}
				// 回调
				if (this.settings.add.callback &&
					typeof this.settings.add.callback === 'function') {
					this.settings.add.callback(responseText);
				}
				
				//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
				$(this.currentTarget).triggerHandler("taijiAddSuccess",[responseText]);
			}
		},
		
		//可配置项
		config:{
			// add链接的class.
			className : '.taiji_add',
			// add成功之后，页面后续处理函数(不推荐)
			callback : function() {
			} 
		}
	});
	var http = /^http:\/\//;
	$.extend({
		/** @lends jQuery# */
		/******************************************************************
		 * 
		 * 添加函数
		 * 
		 * @param
		 *		{Object} form 表单
		 * @param
		 *		{Object} option 附加的可选项
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 * @deprecated 2.0.8 已不建议使用的方式
		 * 
		 *****************************************************************/
		taijiAddSubmit : function(form, option) {
			var nmT = $.nmTop().opener[0].toString();
			if (http.test(nmT)) {
				nmT = nmT.substr(7);
				var index = nmT.indexOf("/");
				nmT = nmT.substr(index);
			}
			$("a[href$='" + nmT + "']").parents("._taiji_").data("aTaiji")
					._handleAddPost(form, option);
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
/* jQuery taiji collapse plugin
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
		userNyroModal:false,
		eventHandler:{
			_handleCollapseClick : function(element) {
				var a = $(element);
				var sibling=a.closest("tr").next();
				if(sibling.hasClass("collapse")){
					sibling.remove();
					return;
				}
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}

				var $this = this;
				// 创建loading
				this._createLoading($.Taiji.Messages.MSG_OPERATE);				
				$.ajax({
					   type: "POST",
					   url: a.attr("href"),
					   success: function(data){
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
					   },
					   error : function (xhr) {
						   $this._showWarn($.Taiji.Messages.ERR_RESPONSE + xhr.status);
					   },
					   complete: function (xhr) {
							 $this.isRunning = false;
							 $this._removeLoading();
						}
					   
					});

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
		userNyroModal:false,
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
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
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
							$$this._showWarn(
								$$this.decode(
									xhr.getResponseHeader(
										$.Taiji.Constans.RESPONSE_HEADER_JME)));
						} else if (xhr.getResponseHeader(
									$.Taiji.Constans.RESPONSE_HEADER_ME)) {
							//此处应该不会被调用到
							$$this._showWarn(
								$$this.decode(
											xhr.getResponseHeader(
												$.Taiji.Constans.RESPONSE_HEADER_ME)));
						} else if (
								xhr.getResponseHeader(
									$.Taiji.Constans.RESPONSE_HEADER_CVE)) {
							//此处应该不会被调用到
							$$this._showWarn(
								$$this.decode(
									xhr.getResponseHeader(
										$.Taiji.Constans.RESPONSE_HEADER_CVE)));
						} else {
							//ajax返回非预期的值
							$$this.warn($.Taiji.Messages.ERR_RESPONSE,
								xhr.status);
							$$this._showWarn($.Taiji.Messages.ERR_RESPONSE,
								xhr.status);
						}
						$$this._removeLoading();
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
 * jQuery taiji edit plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * 
 * @version 2.0.8 2013.06.14
 * 
 * @since 2.0.0
 *  
 */

(function($){
	$.Taiji.extendMethod({
		name:"edit",
		event:{
			click:{
				"className":"_handleEditClick"
			}
		},
		userNyroModal:true,
		customEvent:{"taijiEditPost":"_handleEditPost"},
		eventHandler:{
			_handleEditClick:function(element){
				//检查是否已有其他操作正在进行中。
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}
				var $this = $(element);
				// 如果有metadata插件，循环里处理
				// metadata
				var $metacurrent = this.getMetaWH(
					$this,"edit");
				// 链接点击之后，绑定nyroModal，并直接调用
				$.nmManual($this.attr("href"),$metacurrent);
				//$this.nyroModal($metacurrent).trigger('nyroModal');
			},
			/**
			 * 
			 * 私有的,绑定edit事件的函数，请勿在外部直接调用
			 * 
			 */ 
			_bindEdit : function() {
				var $$this = this;
				//绑定click事件处理函数之前，先取消已绑定的click事件处理函数
				$(this.settings.edit.className,
						this.currentTarget)
						.off("click.taiji")
						.on("click.taiji",
							function() {
								$$this._handleEditClick(this);
							});
			},
			/**
			 * 
			 * 在当前元素上绑定taijiEditPost事件。
			 * 替代 添加页面直接调用 $.taijiEditSubmit ，
			 * 添加页面替代为  $("myManage").triggerHandler("taijiEditPost",[form,options]);
			 * 
			 */
			_bindEditPost:function(){
				var $self = this;
				$(this.currentTarget).off("taijiEditPost").on("taijiEditPost",
						function(event,form,options){
							$self._handleEditPost(form, options);
				});
			},
			/**
			 * 
			 * edit处理函数
			 * 以ajax方式向服务器提交表单，并显示相应信息。
			 * 
			 * @param
			 *		{Object}  form 待提交的表单对象
			 * @param
			 *		{Object}  options 表单提交的追加配置
			 * 
			 */
			_handleEditPost : function(form, options) {
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				var $$this = this;
				var $form = $(form);
				var settings = {
					success : function(data, status, xhr) {
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._handleEditSuccess(xhr.status, 
								responseHeader,data, $form);
					},
					error : function(xhr) {
						$$this._handleEditError(xhr.status);
	
					}
				};
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
			/**
			 * 
			 * edit失败的处理函数
			 * 
			 * @param
			 *		{String} statusCode 出错码
			 * 
			 */
			_handleEditError : function(statusCode) {
				this._removeLoading();
				this._showWarn($.Taiji.Messages.ERR_RESPONSE + statusCode);
				this.isRunning = false;
			},
			/**
			 * edit出现ME，JME的时候的处理函数，主要是触发 taijiEditMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleEditMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiEditMe",[responseText]);
			},
			/**
			 * 
			 * edit成功的处理函数
			 * 
			 * @param
			 *		{Object}  statusCode 状态码
			 * @param
			 *		{Object}  responseHeader 服务器返回的头部信息
			 *		格式 {note:''，jme:'',me:'',cve:''}
			 * @param
			 *		{Object}  responseText 服务器返回的正文
			 * @param
			 *		{Object}  $form	当前操作的表单对象
			 * 
			 */
			_handleEditSuccess : function(statusCode,
					responseHeader,
					responseText, 
					$form) {
				this._removeLoading();
				if (responseHeader.note) {
					this._handleEditSuccessOK(responseText, this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleEditMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleEditMe(responseText);
				} else if (responseHeader.cve) {
					var ejson = $(responseText).find("#taiji_ejson");
					var	ejsonContent = $.parseJSON($(ejson).html());
					var $$this = this;
					$.each(ejsonContent,
						function(i, n) {
						// 2.4.4 20150508
						//如果按name没有找到的话，就按Id找，为了解决input的name一样，id不一样的问题
						if($("[name='"+i+"']",$form).size() ===0){
							$("#"+i,$form).remind(n,{prefix:"id"});
						}else{
							$("[name='"+i+"']",$form).remind(n);
						}
					});
					// 操作失败,cve返回，显示警告信息
					this._showWarn($.Taiji.Messages.ERR_CVE);
				}else if($(responseText).is("div")){
					var $taijiNote = $(responseText).find("#taiji_note");
					if($taijiNote.is("#taiji_note")){
						this._handleEditSuccessOK(responseText, $taijiNote.text());
					}else{
						// 其他情况，暂时不处理
						this.warn($.Taiji.Messages.ERR_RESPONSE,
								statusCode);
						this._showWarn(
								$.Taiji.Messages.ERR_RESPONSE,
								statusCode);
					}
				}else {
					// 其他情况，暂时不处理
					this.warn($.Taiji.Messages.ERR_RESPONSE,
							statusCode);
					this._showWarn(
							$.Taiji.Messages.ERR_RESPONSE,
							statusCode);
				}
				this.isRunning = false;
			},
			/**
			 * 
			 * edit成功的处理函数
			 * 
			 * @param
			 *		{Object}  statusCode 状态码
			 * @param
			 *		{Object}  responseHeader 服务器返回的头部信息
			 *		格式 {note:''，jme:'',me:'',cve:''}
			 * @param
			 *		{Object}  responseText 服务器返回的正文
			 * @param
			 *		{Object}  $form	当前操作的表单对象
			 *
			 * @Version 2.4.0
			 * 
			 * @since 2.0.0
			 * 
			 * 
			 */
			_handleEditSuccessOK:function(responseText,note){
				// 操作成功
				var $row = $(responseText).find(
						".taiji_result_data tr");
				//version 2.4
				this.aclCheck($row);
				$(this.settings.search.resultClassName,
						this.currentTarget).find(
						".taiji_clicked").replaceWith(
						$row.addClass("taiji_clicked"));

				this._bindDataChange();

				// 显示提示信息
				this._showNote(note);
				// 关闭弹出层
				if($.nmTop && $.nmTop()){
					$.nmTop().close();
				}
				// 回调
				if (this.settings.edit.callback &&
					typeof this.settings.edit.callback){
					this.settings.edit.callback(responseText);
				}
				
				//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
				$(this.currentTarget).triggerHandler("taijiEditSuccess",[responseText]);

			}
		},
		//可配置项
		config:{
			// edit链接的class.
			className : '.taiji_edit', 
			// edit成功之后，页面后续处理函数,(不推荐)
			callback : function() {
			} 
		}
	});
	var http = /^http:\/\//;

	$.extend({
		/** @lends jQuery# */
		/******************************************************************
		 * 
		 * 修改函数
		 * 
		 * @param
		 *		{Object} form 表单
		 * @param
		 *		{Object} option 附加的可选项
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 * @deprecated 2.0.8 已不建议使用的方式
		 * 
		 *****************************************************************/
		taijiEditSubmit : function(form, option) {
			var nmT = $.nmTop().opener[0].toString();
			if (http.test(nmT)) {
				nmT = nmT.substr(7);
				var index = nmT.indexOf("/");
				nmT = nmT.substr(index);
			}
			$("a[href$='" + nmT + "']").parents("._taiji_").data("aTaiji")
					._handleEditPost(form, option);
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
	userNyroModal:false,
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
                this._createLoading($.Taiji.Messages.MSG_OPERATE);
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
            this._removeLoading();
            //根据服务器返回的header头进行处理
            //操作成功
            if (responseHeader.note) {
                window.open(url,"_self");
            } else if (responseHeader.jme) {
                //操作失败,jme返回，显示警告信息
                this._showWarn(this.decode(responseHeader.jme));
            } else if (responseHeader.me) {
                //操作失败,me返回，显示警告信息
                this._showWarn(this.decode(responseHeader.me));
            } else if (responseHeader.cve) {
                //操作失败,cve返回，显示警告信息
                this._showWarn(this.decode(responseHeader.cve));
            } else {
                //神马情况，咋个处理啊
                this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
                this._showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
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
					$.Taiji.removeLoading();
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
					$.Taiji.removeLoading();
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
		},
		createLoading:function(){
			//window.console.log($.Taiji.Messages.MSG_OPERATE);
		},
		showNote:function(message){
			//window.console.log(message);
		},
		showWarn:function(message){
			//window.console.log(message);
		},
		removeLoading:function(){
			//window.console.log("removeLoading");
		}
	});
	
})(jQuery);
/*******************************************************************************
 * Taiji menu
 * 
 * 页面一级菜单超长处理
 * 
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * 
 * @author: Tom (tomesc@msn.com)
 * 
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.2.12 2014.04.20
 * 
 * @since 2.2.12
 * 
 ******************************************************************************/
(function ($) {
	$.fn.menu = function(){
		// 容器宽就是菜单可以放置的宽度
		var windowWidth = $(".nav_mid").width() - 10;
		// 默认没有隐藏
		var hidable = false;
		// 缓存所有的li一级菜单项
		var $lis = $(".menu > li");
		// 找到当前选中的一级菜单项
		// TODO 下一步应该能处理一个也没有选中的情况
		var $now = $lis.find("> a.now").parent();
		var totalCount = $lis.size();
		// 计算所有菜单项的宽度
		var totalWidth = 0;
		var allWidth = [];
		$lis.each(function (i, n) {
			totalWidth += $(this).width();
			allWidth[i] = $(this).width();
		});
		var firstShowIndex = 0, lastShowIndex = totalCount, theWidth = 0;
		function nextAll () {
			for ( var i = 1; i < totalCount; i++) {
				var temp = theWidth + allWidth[i];
				if (temp < windowWidth) {
					theWidth += allWidth[i];
					lastShowIndex = i;
					$lis.eq(i).show();
				} else {
					break;
				}
			}
			$(".nav_right").addClass("nav_more");
		}
		function prevAll () {
			for ( var j = totalCount - 2; j >= 0; j--) {
				var temp = theWidth + allWidth[j];
				if (temp < windowWidth) {
					theWidth += allWidth[j];
					firstShowIndex = j;
					$lis.eq(j).show();
				} else {
					break;
				}
			}
			$(".nav_left").addClass("nav_more");
		}
		function siblingAll (index) {
			var next = false, nextIndex = index, prevIndex = index;
			for ( var z = 0; z < totalCount*2; z++) {
				if (next === true) {
					++nextIndex;
					if (nextIndex < totalCount) {
						var temp = theWidth +
							allWidth[nextIndex];
						if (temp < windowWidth) {
							theWidth += allWidth[nextIndex];
							lastShowIndex = nextIndex;
							$lis.eq(nextIndex).show();
						} else {
							break;
						}
					}
					next = false;
				} else if (next === false) {
					--prevIndex;
					if (prevIndex >= 0) {
						var temp1 = theWidth +
							allWidth[prevIndex];
						if (temp1 < windowWidth) {
							theWidth += allWidth[prevIndex];
							firstShowIndex = prevIndex;
							$lis.eq(prevIndex).show();
						} else {
							break;
						}
					}
					next = true;
				}
			}
			if (lastShowIndex < totalCount) {
				$(".nav_right").addClass("nav_more");
			}
			if (firstShowIndex > 0) {
				$(".nav_left").addClass("nav_more");
			}
		}
		
		function init () {
			windowWidth = $(".nav_mid").width() - 10;
			$now = $lis.find("> a.now").parent();
			// 如果默认能放下所有菜单，则结束
			if (totalWidth < windowWidth) {
				$lis.show();
				return;
			}
			$lis.hide();
			$now.show();
			// 计算当前选中的菜单项的宽度，以此为基础
			theWidth = $now.width();
			hidable = true;
			var index = $lis.index($now), firstShowIndex = index, lastShowIndex = index;
			if (index === 0) {
				nextAll();
			} else if (index === totalCount - 1) {
				prevAll();
			} else {
				siblingAll(index);
			}
		}
		$(window).on("resize", init);
		$(".nav_left").on(
			"click",
			function () {
				if (hidable === false) {
					return false;
				} else if (firstShowIndex === 0) {
					return false;
				}
				theWidth += allWidth[--firstShowIndex];
				if (theWidth > windowWidth) {
					theWidth -= $lis.eq(lastShowIndex--).hide()
							.width();
					if (theWidth > windowWidth) {
						theWidth -= $lis.eq(lastShowIndex--)
								.hide().width();
					}
				}
				$lis.eq(firstShowIndex).fadeIn(1000);

				if (lastShowIndex < totalCount - 1) {
					$(".nav_right").addClass("nav_more");
				} else {
					$(".nav_right").removeClass("nav_more");
				}
				if (firstShowIndex > 0) {
					$(".nav_left").addClass("nav_more");
				} else {
					$(".nav_left").removeClass("nav_more");
				}

				return false;
			});
		$(".nav_right").on(
			"click",
			function () {
				if (hidable === false) {
					return false;
				} else if (lastShowIndex === totalCount - 1) {
					return false;
				}
				theWidth += allWidth[++lastShowIndex];
				if (theWidth > windowWidth) {
					theWidth -= $lis.eq(firstShowIndex++)
							.hide().width();
					if (theWidth > windowWidth) {
						theWidth -= $lis.eq(firstShowIndex++)
								.hide().width();
					}
				}
				$lis.eq(lastShowIndex).fadeIn(1000);
				if (lastShowIndex < totalCount - 1) {
					$(".nav_right").addClass("nav_more");
				} else {
					$(".nav_right").removeClass("nav_more");
				}
				if (firstShowIndex > 0) {
					$(".nav_left").addClass("nav_more");
				} else {
					$(".nav_left").removeClass("nav_more");
				}
				return false;
			});
		
		return init();
	};
	
	
})(jQuery);

jQuery(function(){
	jQuery(".nav").menu();
});

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
	userNyroModal:false,
	eventHandler:{
		/**
		 * multiOperate出现ME，JME的时候的处理函数，主要是触发 taijiMultiOperateMe事件，供页面使用
		 * 
		 * @param
		 *	{Object}  responseText,后台返回的内容
		 */
		_handleMultiOperateMe : function(responseText){
			//ME，JME的时候触发此事件
			$(this.currentTarget).triggerHandler("taijiMultiOperateMe",[responseText]);
		},
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
                $$this._showWarn("请至少选中一个操作项");
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
            $$this._createLoading("批量操作操作进行中，请稍等");
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
            this._removeLoading();
            this.isRunning = false;
            //根据服务器返回的header头进行处理
            //操作成功
            if(responseHeader.note) {

                this._showNote(this.decode(responseHeader.note));
                 if (this.settings.multiOperate.callback && typeof this.settings.multiOperate.callback === 'function') {
                    this.settings.multiOperate.callback(responseText);
               }
               //TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
                 $(this.currentTarget).trigger("taijiMultiOperateSuccess",[responseText]);
                 
            } else if (responseHeader.jme) {
                //操作失败,jme返回，显示警告信息
                this._showWarn(this.decode(responseHeader.jme));
                this._handleMultiOperateMe(responseText);
            } else if (responseHeader.me) {
                //操作失败,me返回，显示警告信息
                this._showWarn(this.decode(responseHeader.me));
                this._handleMultiOperateMe(responseText);
            } else if (responseHeader.cve) {
                //操作失败,cve返回，显示警告信息
                this._showWarn(this.decode(responseHeader.cve));
            } else {
                //神马情况，咋个处理啊
                this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
                this._showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
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
		userNyroModal : false,
		eventHandler : {
			/**
			 * multiRemove出现ME，JME的时候的处理函数，主要是触发 taijMultiRemoveMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleMultiRemoveMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiMultiRemoveMe",[responseText]);
			},
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
					$$this.warn("未选中任何项");
					$$this._showWarn("请至少选中一个操作项");
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
				this._createLoading("批量删除操作进行中，请稍等");
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
				this._removeLoading();
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

					this._showNote(this.decode(responseHeader.note));
					if (this.settings.multiRemove.callback &&
						typeof this.settings.multiRemove.callback === 'function') {
						this.settings.multiRemove
								.callback(responseText);
					}
					// TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).trigger(
						"taijiMultiRemoveSuccess", [ responseText ]);
					this._handleMultiRemoveMe(responseText);

				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleMultiRemoveMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					this._showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:", status);
					this._showWarn("服务器返回了非预期的值，请联系开发人员进行处理！,代码:",
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
		userNyroModal:false,
		eventHandler:{
			/**
			 * operate出现ME，JME的时候的处理函数，主要是触发 taijiOperateMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleOperateMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiOperateMe",[responseText]);
			},
			
			/**
			 * 
			 * operate成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 服务器返回的状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} obj 当前链接
			 * 
			 */
			_handleOperateSuccess : function(statusCode,
					responseHeader,
					responseText,
					obj
					) {
				var $this = $(obj);
				// 解除禁用
				$this.attr("disabled", false);
				// 移出loading
				this._removeLoading();
				this.isRunning = false;
				// 根据服务器返回的header头进行处理
				if (responseHeader.note) {
					// 操作成功
					if (this.settings.operate.callback &&
						typeof this.settings.operate.callback === 'function') {
						this.settings.operate.callback(responseText);
					}
	
					//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).triggerHandler("taijiOperateSuccess",[responseText]);
					
					this._showNote(this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleOperateMe(responseText);
					
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleOperateMe(responseText);
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					this._showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn($.Taiji.Messages.ERR_RESPONSE+statusCode);
					this._showWarn($.Taiji.Messages.ERR_RESPONSE+statusCode);
				}
	
			},
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
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window
							.confirm($message)) {
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
				$this.attr("disabled",true);
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				//ajax参数
				var $$this = this;
				var options = {
						url : $url,
						type : "POST",
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
							$$this._handleOperateSuccess(
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
					};
				$.ajax(options);
			},
			/**
			 * 
			 * 绑定operate事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 */ 
			_bindOperate : function() {
				var $$this = this;
				$(this.settings.operate.className,
						this.currentTarget)
						.off("click.taiji")
						.on(
								"click.taiji",
								function(event) {
									$$this._handleOperateClick(this);
								});
			}
		},
		//可配置项
		config:{
			// operate链接的class.
			className : '.taiji_operate',
			// operate成功之后，页面后续处理函数（不推荐使用）
			callback : function() {
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
 * jQuery taiji add plugin
 * 
 * @requires  
 *     taiji v2.0.8 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @verion 2.0.8 2013.06.14
 * 
 * @since 2.0.8
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"popupRemove",
		event:{
			click:{
				"className":"_handlePopupRemoveClick"
			}
		},
		userNyroModal:true,
		customEvent:{"taijiPopupRemovePost":"_handlePopupRemovePost"},
		eventHandler:{
			/**
			 * popupRemove出现ME，JME的时候的处理函数，主要是触发 taijjPopupRemoveMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handlePopupRemoveMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiPopupRemoveMe",[responseText]);
			},
			/**
			 * 
			 * popupRemove的click事件的处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handlePopupRemoveClick:function(element){
				//检查是否已有其他操作正在进行中。
				if (this.isRunning === true) {
					this.warn($.Taiji.Messages.WAR_OPERATE);
					return;
				} else {
					this.isRunning = true;
				}
				var $this = $(element);
				// 获取弹出层的高度和宽度设置。
				var $metacurrent = this.getMetaWH($this,
						"popupRemove");
				$.nmManual($this.attr("href"),$metacurrent);
				//bv$this.nyroModal($metacurrent).trigger('nyroModal');
			},
			/**
			 * 
			 * 在当前元素上绑定taijiPopupRemovePost事件。
			 * 替代 添加页面直接调用 $.taijiPopupRemoveSubmit ，
			 * 添加页面替代为  $("myManage").triggerHandler("taijiPopupRemovePost",[form,options]);
			 * 
			 */
			_bindPopupRemovePost:function(){
				var $self = this;
				$(this.currentTarget).off("taijiPopupRemovePost").on("taijiPopupRemovePost",
						function(event,form,options){
							$self._handlePopupRemovePost(form, options);
				});
			},
			/**
			 * 
			 * 绑定popupRemove事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 */
			_bindPopupRemove : function() {
				var $$this = this;
				//绑定click事件处理函数之前，先取消已绑定的click事件处理函数
				$(this.settings.popupRemove.className,
						this.currentTarget)
						.off("click.taiji")
						.on("click.taiji",
								function() {
									
									$$this._handlePopupRemoveClick(this);
								});
			},
			/**
			 * 
			 * popupRemove处理函数
			 * 以ajax方式向服务器提交表单，并显示相应信息。
			 * 
			 * @param
			 *		{Object} form 待提交的表单对象
			 * @param
			 *		{Object} options 表单提交的追加配置
			 * 
			 */
			_handlePopupRemovePost : function(form, options) {
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				var $$this = this;
				var $form = $(form);
				var settings = {
					success : function(data, status, xhr) {
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._handlePopupRemoveSuccess(xhr.status,
								responseHeader,data, $form);
					},
					error : function(xhr) {
						$$this._hanlePopupRemoveError(xhr.status);
	
					},
					dataType : "html"
				};
				$.extend(settings, options);
				$(":file", $form).each(function() {
					if (!$(this).val()){
						$(this).attr("disabled", true);
					}
				});
				$(form).ajaxSubmit(settings);
				$(":file", $form).each(function() {
					$(this).attr("disabled", false);
				});
			},
			/**
			 * 
			 * popupRemove失败的处理函数
			 * 
			 * @param
			 *		{String} statusCode 出错码
			 * 
			 */
			_hanlePopupRemoveError : function(statusCode) {
				this._removeLoading();
				this._showWarn($.Taiji.Messages.ERR_RESPONSE + statusCode);
				this.isRunning = false;
			},
			/**
			 * 
			 * popupRemove成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部信息
			 *		格式 {note:''，jme:'',me:'',cve:''}
			 * @param
			 *		{Object}  responseText 服务器返回的正文
			 * @param
			 *		{Object} $form	当前操作的表单对象
			 * 
			 */
			_handlePopupRemoveSuccess : function(statusCode,
					responseHeader,
					responseText,
					$form) {
				this._removeLoading();
				this.isRunning = false;
				if (responseHeader.note) {
					// 操作成功
					$(this.settings.search.resultClassName,
							this.currentTarget).find(
							".taiji_clicked").remove();
					// 修改总条数
					$(".taiji_pager .totalcount",
							this.currentTarget)
							.each(
									function() {
										$(this)
												.text(
														parseInt($(
																this)
																.text(),10) - 1);
									});
					// 显示提示信息
					this._showNote(this.decode(responseHeader.note));
					// 关闭弹出层
					$.nmTop().close();
					// 回调
					if (this.settings.popupRemove.callback &&
						typeof this.settings.popupRemove.callback){
						this.settings.popupRemove
								.callback(responseText);
					}
					
					//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).triggerHandler("taijiPopupRemoveSuccess",[responseText]);
	
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handlePopupRemoveMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handlePopupRemoveMe(responseText);
				} else if (responseHeader.cve) {
					var jsonData = $.parseJSON(responseText);
					var $$this = this;
					$("[generated=true]", $form).each(
							function() {
								$(this).html("");
							});
					$.each(jsonData,
							function(i, n) {
								var $nextLabel = $("[for=" +
									i + "]");
								if ($nextLabel
										.hasClass($$this.settings.errorClass)) {
									$nextLabel.text(n);
									$nextLabel.show();
								} else {
									$nextLabel = $("<label></label>");
									$nextLabel
											.addClass($$this.settings.errorClass);
									$nextLabel
											.attr({
												"for" : i,
												generated : true
											});
									$nextLabel.text(n);
									$(
											"input[name=" +
												i + "]",
											$form)
											.after(
													$nextLabel);
								}
							});
					// 操作失败,cve返回，显示警告信息
					this._showWarn($.Taiji.Messages.ERR_CVE);
				} else {
					// 其他情况，暂时不处理
					this.warn($.Taiji.Messages.ERR_RESPONSE,
							statusCode);
					this._showWarn(
							$.Taiji.Messages.ERR_RESPONSE,
							statusCode);
				}
			}
		},
		//可配置想
		config:{
			// popupRemove 链接的class.
			className : '.taiji_popupRemove', 
			// popupRemove成功之后，页面后续处理函数（不推荐使用）
			callback : function() {
			} 
		}
	});
	var http = /^http:\/\//;

	$.extend({
		/** @lends jQuery# */
		/******************************************************************
		 * 
		 * 弹出层，操作删除函数
		 * 
		 * @param
		 *		{Object} form 表单
		 * @param
		 *		{Object} option 附加的可选项
		 *
		 * @version 2.0.0
		 * 
		 * @since 2.0.0
		 * 
		 * @deprecated 2.0.8 已不建议使用的方式
		 * 
		 *****************************************************************/
		taijiPopupRemoveSubmit : function(form, option) {
			var nmT = $.nmTop().opener[0].toString();
			if (http.test(nmT)) {
				nmT = nmT.substr(7);
				var index = nmT.indexOf("/");
				nmT = nmT.substr(index);
			}
			$("a[href$='" + nmT + "']").parents("._taiji_").data("aTaiji")
					._handlePopupRemovePost(form, option);
		}
	});
})(jQuery);
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
		userNyroModal:false,
		eventHandler:{
			_handleAsyncClick:function(element){
				var $this = $(element);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window.confirm($message)) {
						return false;
					}
				}
				var $asyncConfig=$.extend({},{"interval":4000,"process":$url+"/process"},$this.metadata());
				var $$this = this;
				var options = { 
					// 成功处理函数
					success : function success(responseText,
							status, xhr) {
						var responseHeader = {
								note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
								jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
								me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
								cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
						};
						$$this._removeLoading();

						if (responseHeader.jme) {
							// 操作失败,jme返回，显示警告信息
							$$this._showWarn($$this.decode(responseHeader.jme));
						} else if (responseHeader.me) {
							// 操作失败,me返回，显示警告信息
							$$this._showWarn($$this.decode(responseHeader.me));
						}else if (responseHeader.cve) {
							var ejson = $(responseText).find("#taiji_ejson");
							var $form=$($$this.searchFormClassName,$$this.currentTarget);
							var	ejsonContent = $.parseJSON($(ejson).html());
							$.each(ejsonContent,
								function(i, n) {
								// 2.4.4 20150508
								//如果按name没有找到的话，就按Id找，为了解决input的name一样，id不一样的问题
								if($("[name='"+i+"']",$form).size() ===0){
									$("#"+i,$form).remind(n);
								}else{
									$("[name='"+i+"']",$form).remind(n);
								}
							});
							// 操作失败,cve返回，显示警告信息
							$$this._showWarn($.Taiji.Messages.ERR_CVE);
						}else{
							$$this._showNote(responseText.msg);
							if ($("#process_bar").size() === 0) {
								window.setTimeout(function(){
									$$this._handleAsyncProcess($asyncConfig.process,$asyncConfig.interval);
								},1000);
							}
						}
						
					},
					// 失败处理函数
					error : function error(xhr) {
						$$this._handleOperateError(xhr.status, $$this);
					}
				};
				// 创建loading
				this._createLoading($.Taiji.Messages.MSG_OPERATE);	
				
				if($.Taiji.hasCustomForm(element)){
					var customForm=$.Taiji.createCustomForm(element);
					customForm.ajaxSubmit(options);	
					customForm.remove();
				}else{
					var searchForm=$(this.searchFormClassName,this.currentTarget);
					var formAction=searchForm.attr("action");
					searchForm.attr("action",$url);
					searchForm.ajaxSubmit(options);	
					searchForm.attr("action",formAction);
				}
		   },
		   
		   _handleAsyncProcess : function($url,interval) {
				var $this = this;
				$.ajax({
					url : $url,
					type : "POST",
					data : {},
					dataType : "json",
					success : function(responseText,
							status, xhr) {
						var $process_bar = $("#process_bar");
						if(responseText.processType ==='SUCCESS'){
							// 任务完成
							$($this.currentTarget).triggerHandler("taijiAsyncSuccess",[responseText.result]);
							$process_bar.remove();
							return;
						}
						
						var len = responseText.msg.length;
						var width = len * $this.$fs + 120;
						var offset = $($this.currentTarget).offset();
						var x = ($this.$windowW - width- offset.left) / 2 + $this.$sw;
						var percent=Math.round(responseText.percent*100);
						if ($process_bar && $process_bar.size() === 1) {
							$process_bar.css("width",width)
										.css("left", x);
							$(".taiji_bar_text",$process_bar).text(responseText.msg+"("+percent+"%)");
							$(".taiji_bar",$process_bar).animate({"width":percent +"%"},interval);
						} else {
							$process_bar = $(
									"<div id='process_bar' class='taiji_processing'></div>")
									.css("top",$this.$windowH /5 + $this.$sh)
									.css("width",width)
									.css("left", x);
							var $swap = $(
									"<div class='taiji_bar_swap'></div>");
							var $bar = $(
									"<div class='taiji_bar'></div>")
									.animate({"width":percent +"%"},interval);
							var $bar_text = $(
									"<span class='taiji_bar_text'></span>")
									.text(responseText.msg+"("+percent+"%)");

							$bar.appendTo($swap);
							$bar_text.appendTo($swap);
							$swap.appendTo($process_bar);
							$process_bar.appendTo("body");
							
							$bar_text.css("width",width)
									 .css("top",($process_bar.height()-$bar_text.height())/2);
						}
						window.setTimeout(function(){
							  $this._handleAsyncProcess($url,interval);
							},interval);
					},
					error : function(xhr) {
						$this._handleOperateError(xhr.status, $this);
						$this.isRunning = false;
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
		userNyroModal:false,
		eventHandler:{
			/**
			 * remove出现ME，JME的时候的处理函数，主要是触发 taijiRemoveMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleRemoveMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiRemoveMe",[responseText]);
			},
			/**
			 * 
			 * remove成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 服务器返回的状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} obj 当前链接
			 * 
			 */
			_handleRemoveSuccess : function(
					statusCode, 
					responseHeader,
					responseText,
					obj) {
				var $this = $(obj);
				// 解除禁用
				$this.attr("disabled", false);
				// 移出loading
				this._removeLoading();
				// 根据服务器返回的header头进行处理
				if (responseHeader.note) {
					// 操作成功
						$this.parents("tr").remove();
						$(".taiji_pager .totalcount",
								this.currentTarget)
								.each(
										function() {
											$(this)
													.text(
															parseInt($(
																	this)
																	.text(),10) - 1);
										});
	
					if (this.settings.remove.callback &&
						typeof this.settings.remove.callback === 'function') {
						this.settings.remove.callback(responseText);
					}
	
					//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).triggerHandler("taijiRemoveSuccess",[responseText]);
					
					this._showNote(this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleRemoveMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleRemoveMe(responseText);
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					// 删除不应该有此中情况发生
					this._showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn($.Taiji.Messages.ERR_RESPONSE+statusCode);
					this._showWarn($.Taiji.Messages.ERR_RESPONSE+statusCode);
				}
	
				this.isRunning = false;
			},
			/**
			 * 
			 * remove的click事件处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleRemoveClick:function(element){
				var $this = $(element);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window
							.confirm($message)) {
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
				$this.attr("disabled",true);
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				//ajax参数
				var $$this = this;
				var options = {
						url : $url,
						type : "POST",
						data : {},
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
							$$this._handleRemoveSuccess(
								xhr.status,
								responseHeader,
								responseText,
								$this);
						},
						error : function(
								xhr) {
							$$this._handleOperateError(
											xhr.status,
											$this);
						}
					};
				$.ajax(options);
			},
			/**
			 * 
			 * 绑定remove事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 */
			_bindRemove : function() {
				var $$this = this;
				$(this.settings.remove.className,
						this.currentTarget)
						.off("click.taiji")
						.on("click.taiji",
								function(event) {
									$$this._handleRemoveClick(this);
								});
	
			}
		},
		//可配置项
		config:{
			// remove链接的class.
			className : '.taiji_remove',
			// remove成功之后，页面后续处理函数
			callback : function() {
			} 
		}
	});
})(jQuery);
/**
 * jQuery taiji search plugin
 * 
 * @requires  
 *     taiji v2.2.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * @version 2.2.0
 * 
 * @since 2.2.0
 * 拟将查询功能也从base.js中提取出来
 * 
 */

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
		userNyroModal:false,
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
/**
 * jQuery taiji taiji_topOperate plugin
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
 */

(function($){
	$.Taiji.extendMethod({
		name:"topOperate",
		event:{
			click:{
				"className":"_handleTopOperateClick"
			}
		},
		userNyroModal:false,
		eventHandler:{
			/**
			 * topOperate出现ME，JME的时候的处理函数，主要是触发 taijiTopOperateMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleTopOperateMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiTopOperateMe",[responseText]);
			},
			/**
			 * 
			 * topOperate成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 服务器返回的状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} obj 当前链接
			 * 
			 */
			_handleTopOperateSuccess : function(statusCode,
					responseHeader,
					responseText,
					obj
					) {
				var $this = $(obj);
				// 解除禁用
				$this.attr("disabled", false);
				// 移出loading
				this._removeLoading();
				this.isRunning = false;
				// 根据服务器返回的header头进行处理
				if (responseHeader.note) {
					// 操作成功
					if (this.settings.topOperate.callback &&
						typeof this.settings.topOperate.callback === 'function') {
						this.settings.topOperate.callback(responseText);
					}
					
					//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).triggerHandler("taijiTopOperateSuccess",[responseText]);
	
					this._showNote(this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleTopOperateMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleTopOperateMe(responseText);
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					this._showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn($.Taiji.Messages.ERR_RESPONSE+statusCode);
					this._showWarn($.Taiji.Messages.ERR_RESPONSE+statusCode);
				}
	
			},
			/**
			 * 
			 * topOperate的click事件处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleTopOperateClick:function(element){
				var $this = $(element);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window
							.confirm($message)) {
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
				$this.attr("disabled",true);
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				//ajax参数
				var $$this = this;
				
				var options = {
						url : $url,
						type : "POST",
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
							$$this._handleTopOperateSuccess(
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
					};
				
				$.ajax(options);
			},
			/**
			 * 
			 * 绑定topOperate事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 */ 
			_bindTopOperate : function() {
				var $$this = this;
				$(this.settings.topOperate.className,
						this.currentTarget)
						.off("click.taiji")
						.on(
								"click.taiji",
								function(event) {
									$$this._handleTopOperateClick(this);
									//取消浏览器默认行为
									$.Taiji.preventDefault(event);
								});
			}
		},
		//可配置项
		config:{
			// topOperate链接的class.
			className : '.taiji_topOperate', 
			// topOperate成功之后，页面后续处理函数
			callback : function() {
			} 
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
		userNyroModal:false,
		eventHandler:{
			/**
			 * update出现ME，JME的时候的处理函数，主要是触发 taijiUpdateMe事件，供页面使用
			 * 
			 * @param
			 *	{Object}  responseText,后台返回的内容
			 */
			_handleUpdateMe : function(responseText){
				//ME，JME的时候触发此事件
				$(this.currentTarget).triggerHandler("taijiUpdateMe",[responseText]);
			},
			/**
			 * 
			 * update成功的处理函数
			 * 
			 * @param
			 *		{Object} statusCode 服务器返回的状态码
			 * @param
			 *		{Object} responseHeader 服务器返回的头部
			 * @param
			 *		{Object} responseText 服务器返回的正文
			 * @param
			 *		{Object} obj 当前链接
			 * 
			 */
			_handleUpdateSuccess : function(statusCode, responseHeader,
				 responseText, obj) {
				var $this = $(obj);
				// 解除禁用
				$this.attr("disabled", false);
				// 移出loading
				this._removeLoading();
				// 根据服务器返回的header头进行处理
				if (responseHeader.note){
					// 操作成功
					$this.parents("tr")
							.replaceWith(
									$(responseText)
											.find("tr")
											.addClass("taiji_clicked"));
					this._bindDataChange();
	
					if (this.settings.update.callback &&
						typeof this.settings.update.callback === 'function') {
						this.settings.update.callback(responseText);
					}
	
					//TODO 为未来做准备，以后取消callback方式，改为事件传播方式。
					$(this.currentTarget).triggerHandler("taijiUpdateSuccess",[responseText]);
					
					this._showNote(this.decode(responseHeader.note));
				} else if (responseHeader.jme) {
					// 操作失败,jme返回，显示警告信息
					this._showWarn(this.decode(responseHeader.jme));
					this._handleUpdateMe(responseText);
				} else if (responseHeader.me) {
					// 操作失败,me返回，显示警告信息
					this._showWarn(this.decode(responseHeader.me));
					this._handleUpdateMe(responseText);
				} else if (responseHeader.cve) {
					// 操作失败,cve返回，显示警告信息
					// 不应该出现的情况
					this._showWarn(this.decode(responseHeader.cve));
				} else {
					// 神马情况，咋个处理啊
					this.warn($.Taiji.Messages.ERR_RESPONSE+statusCode);
					this._showWarn($.Taiji.Messages.ERR_RESPONSE+statusCode);
				}
	
				this.isRunning = false;
			},
			/**
			 * 
			 * update的click事件处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleUpdateClick:function(element){
				var $this = $(element);
				// 获取当前被点击下载链接的URL,
				var $url = $.Taiji.getUrl($this);
				//如果没有获取到URL,直接返回。
				if (!$url) {
					this.warn($.Taiji.Messages.ERR_URL);
					return;
				}
				//获取确认信息
				var $message = $.Taiji.getConfirmMessage($this);
				if ($message) {
					if (!window
							.confirm($message)) {
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
				$this.attr("disabled",true);
				this._createLoading($.Taiji.Messages.MSG_OPERATE);
				//ajax参数
				var $$this = this;
				var options = {
						url : $url,
						type : "POST",
						dataType : "html",
						success : function(responseText,
								status,
								xhr) {
							var responseHeader = {
									note:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_NOTE),
									jme:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_JME),
									me:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_ME),
									cve:xhr.getResponseHeader($.Taiji.Constans.RESPONSE_HEADER_CVE)
							};
							$$this._handleUpdateSuccess(
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
					};

				$.ajax(options);
			},
			/**
			 * 
			 * 绑定update事件的函数
			 * 私有的,，请勿在外部直接调用
			 * 
			 */ 
			_bindUpdate : function() {
				var $$this = this;
				//循环绑定click事件，绑定click事件之前，先取消已有的click事件绑定。
				$(this.settings.update.className,
						this.currentTarget)
						.off("click.taiji")
						.on(
								"click.taiji",
								function() {
									$$this._handleUpdateClick(this);
								});
			}
		},
		// 可配置项
		config:{
			// update 链接的class.
			className : '.taiji_update',
			// update成功之后，页面后续处理函数
			callback : function() {
			} 
		}
	});
})(jQuery);
/******************************************************************************
 * jQuery taiji validator plugin
 * 
 * @requires  
 *     taiji v2.2.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * 用html5的方式显示验证失败结果
 * 
 * @version 2.2.10 2014.04.08
 * 
 * @since 2.2.0
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
		remind: function(content, options) {
			var defaults = {
				size: 6,	// 三角的尺寸
				align: "center",	//三角的位置，默认居中
				css: {
					maxWidth: 280,
					backgroundColor: "#FFFFE0",
					borderColor: "#F7CE39",
					color: "#333",
					fontSize: "12px",
					padding: "5px 10px",
					zIndex:"5"
				},
				prefix:"name"
			};
			options = options || {};
			options.css = $.extend({}, defaults.css, options.css);
			
			var params = $.extend({}, defaults, options || {});
			var $remindId = $(this).attr(params.prefix) + "_remind";
			
			// 如果元素不可见，不处理
			if (!content || !$(this).isVisible()) {
				return;
			}
			
			var objAlign = {
				"center": "50%",
				"left": "15%",
				"right": "85%"	
			}, align = objAlign[params.align] || "50%";
			
			params.css.position = "absolute";
			params.css.top = "-99px";
			params.css.border = "1px solid " + params.css.borderColor;
			
			if ($("#" + $remindId).size()) {
				$("#" + $remindId).hide();
			}
			
			this.remind = $('<div id="'+ $remindId +'" class="taiji_validator">'+ content +'</div>').css(params.css);
			//补丁-20140222-相对父元素，不在相对body了
			//$(document.body).append(this.remind);
			$(this).parent().append(this.remind);
			// IE6 max-width的处理
			var maxWidth;
			if (!window.XMLHttpRequest && (maxWidth = parseInt(params.css.maxWidth,10)) && this.remind.width() > maxWidth) {
				 this.remind.width(maxWidth);	
			}
			//补丁-20140222-相对父元素，不在相对body
			// 当前元素的位置，提示框的方向
			//var offset = $(this).offset, direction = "top";
			var offset = $(this).position(), direction = "top";
			//补丁-20140222-placeholder
			if($(this).isChosenSelect()){
				offset = $(this).next("div").offset();
			}
			if (!offset) {
				return $(this);
			}
			var remindTop = offset.top - this.remind.outerHeight() - params.size + 10;
			if (remindTop < $(document).scrollTop()) {
				direction = "bottom";
				remindTop = offset.top + $(this).outerHeight() + params.size - 10;
			}	
			
			// 创建三角
			var fnCreateCorner = function(beforeOrAfter) {
				// CSS名称值与变量，主要用来mini后节约文件大小
				var transparent = "transparent", dashed = "dashed", solid = "solid";
				
				// CSS样式对象们
				var cssWithDirection = {}, cssWithoutDirection = {
					// 与方向无关的CSS
					//left: align,
					width: 0,
					height: 0,
					overflow: "hidden",
					//marginLeft: (-1 * params.size) + "px",
					borderWidth: params.size + "px",
					position: "absolute"
				}, cssFinalUsed = {};
				
				// before颜色为边框色
				// after为背景色
				// 方向由direction决定
				if (beforeOrAfter === "before") {
					cssWithDirection = {
						"top": {
							borderColor: [params.css.borderColor, transparent, transparent, transparent].join(" "),
							borderStyle: [solid, dashed, dashed, dashed].join(" "),
							top: 0
						},
						"bottom": {
							borderColor: [transparent, transparent, params.css.borderColor, ""].join(" "),
							borderStyle: [dashed, dashed, solid, dashed].join(" "),
							bottom: 0
						}	
					};	
				} else if (beforeOrAfter === "after") {
					cssWithDirection = {
						"top": {
							borderColor: params.css.backgroundColor + ["", transparent, transparent, transparent].join(" "),
							borderStyle: [solid, dashed, dashed, dashed].join(" "),
							top: -1
						},
						"bottom": {
							borderColor: [transparent, transparent, params.css.backgroundColor, ""].join(" "),
							borderStyle: [dashed, dashed, solid, dashed].join(" "),
							bottom: -1
						}	
					};	
				} else {
					cssWithDirection = null;
					cssWithoutDirection = null;
					cssFinalUsed = null;
					return null;	
				}
				
				cssFinalUsed = $.extend({}, cssWithDirection[direction], cssWithoutDirection);
				
				return $('<'+ beforeOrAfter +'></'+ beforeOrAfter +'>').css(cssFinalUsed);
			};
			
			// 限高
			var cssOuterLimit = {
				width: 2 * params.size,
				left: align,
				marginLeft: (-1 * params.size) + "px",
				height: params.size,
				textIndent: 0,
				overflow: "hidden",
				position: "absolute"
			};
			if (direction === "top") {
				cssOuterLimit.bottom = -1 * params.size;
			} else {
				cssOuterLimit.top = -1 * params.size;
			}
			
			this.remind.css({
				left: offset.left,
				top: remindTop, 
				// marginLeft: ($(this).outerWidth() - this.remind.outerWidth()) * 0.5 + /*因为三角位置造成的偏移*/ this.remind.outerWidth() * (50 - parseInt(align)) / 100		
				// 等于下面这个：
				marginLeft: $(this).outerWidth() * 0.5 - this.remind.outerWidth() * parseInt(align,10) / 100 
			}).prepend($('<div></div>').css(cssOuterLimit).append(fnCreateCorner("before")).append(fnCreateCorner("after")));
			
			$("#" + $remindId).show();
			
			// 绑定消除事件
			$(this).on("focus keydown",function(){
				$("#"+$remindId).remove();
			});
			if($(this).isChosenSelect()){
				$(this).next("div").on("click",function(){
					$("#"+$remindId).remove();
				});
			}
			
			var $$$this = $(this);
			$(document).on("taijiFixableSuccess",function(){
				var myP = $$$this.position();
				remindTop = myP - $("#"+$remindId).outerHeight() - params.size + 10;
				if (direction === "bottom") {
					remindTop = myP.top + $($$$this).outerHeight() + params.size - 10;
				}
				$("#"+$remindId).css({top:remindTop});
			}).on("taijiResizeSuccess",function(){
				var myP = $$$this.position();
				remindTop = myP - $("#"+$remindId).outerHeight() - params.size + 10;
				if (direction === "bottom") {
					remindTop = myP.top + $($$$this).outerHeight() + params.size - 10;
				}
				$("#"+$remindId).css({top:remindTop});
			});
			
			return $(this);
		}
	});
})(jQuery);
/**
 * jQuery taiji view plugin
 * 
 * @requires  
 *     taiji v2.2.0 or later
 *
 * Copyright © 2010-2015 TaiJi Computer Corporation Limited
 * @author: Tom (tomesc@msn.com)
 *
 * Examples and documentation at: http://js.taiji.com.cn/
 * 
 * 查看的弹出层，需要支持在页面任何地方单击，并且弹出层还能点击再次弹出。
 * 
 * @version 2.2.0 2014.02.27
 * 
 * @since 2.0.8
 * 
 */

(function($){
	$.Taiji.extendMethod({
		name:"view",
		event:{
			click:{
				"className":"_handleViewClick"
			}
		},
		userNyroModal:true,
		eventHandler:{
			/**
			 * 
			 * view的click事件处理函数
			 * 会触发nyroModal事件
			 * 私有的,请勿在外部直接调用
			 * 
			 * @param 
			 *		{Object} element 被点击的HTML节点
			 * 
			 */
			_handleViewClick:function(element){
				var $this = $(element);
				// 获取添加弹出层的高度和宽度配置
				var $metacurrent = this.getMetaWH($this,
						"view");
				//触发nyroModal事件，弹出添加层
				$.nmManual($this.attr("href"),$metacurrent);
				//$this.nyroModal($metacurrent).trigger('nyroModal');
		
			},
			/**
			 * 
			 * 绑定view事件的函数
			 * 私有的,请勿在外部直接调用
			 * 
			 */
			_bindView : function() {
				var $$this = this;
				// 循环处理settings width,height
				$(this.settings.view.className,
						this.currentTarget)
						.off("click.taiji")
						.on(
								"click.taiji",
								function(event) {
									$$this._handleViewClick(this);
								});
	
			}
		},
		//可配置项
		config:{
			// view链接的class.
			className : '.taiji_view'
		}
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

