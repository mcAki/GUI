/*
在程序启动时候执行按钮事件替换
*/


(function($){
	
	//检测是否太旧版本的ie不支持部分特效
	var IE6 = 'ie6';
	var IE7 = 'ie7';
	var IE8 = 'ie8';	
	var FF = 'ff';		
	var browser="";	
	var bver;
	var selEff = true;
	if($.browser.msie){
		//检查是否ie
		bver = parseInt($.browser.version);
		if(bver<7){
			browser =IE6;
			//selEff = false;
		}else if(bver==7){
			browser =IE7;
		}else{
			browser =IE8;
		}
	}else if($.browser.mozilla){
		//检查是否ff
		browser = FF;
	}else{
		//什么都不是当ie处理
		browser = IE8;
	}
	//alert( browser + " :" + $.browser.version );
	 
	//按钮处理
	$.fn.btn = function(){
		var btn = this.data("_self");;
		if(btn){
			return btn;
		};
		this.init = function(){
			var obj = $(this);
			var id=$(this).attr('id')||"gen"+Math.random();
			var icon = $(this).attr('icon')||'icon-none';			
			//var btnType =  $(this).attr('type');
			var btnType = 'button';
			
			var writeTextCss = (icon=='icon-none')?'z-btn-text':'z-btn-icotext';
			//<span unselectable="on">',
			var bntStr=[
				'<table id="',id,'" class="z-btn" cellSpacing=0 cellPadding=0 border=0><tbody><tr>',
					'<TD class=z-btn-left></TD>',
					'<TD class=z-btn-center >',
						'<BUTTON class="' + writeTextCss + ' ',icon,' ' + browser + '" type="' + btnType + '" >',$(this).attr('value'),'</BUTTON>',
					'</TD>',
					'<TD class=z-btn-right></TD>',
				'</tr></tbody></table>'
			];
			//alert(bntStr);
			var bnt = $(bntStr.join('')).btn();
			bnt._click = eval(obj.attr("onclick"));
			bnt.disable();
			
			if(obj.attr("disabled"))
				bnt.disable();
			else bnt.enable();
			
			$(this).replaceWith(bnt);
			bnt.data("_self", bnt);
			


			return bnt;
		};
		this.enable = function(){
			
			this.removeClass("z-btn-dsb");
			this.click(this._click);
			this.hover(
				  function () {
				    $(this).addClass("z-btn-over");
				  },
				  function () {
				    $(this).removeClass("z-btn-over");
					$(this).removeClass("z-btn-down");
				  }
				)
			//主要是为了点中按钮才能产生效果
			$(this).mousedown(
				  function () {
					$(this).removeClass("z-btn-over");
					$(this).addClass("z-btn-down");
					//$(this).children('button').removeClass(".z-btn-icotext");
					//$(this).children('button').addClass(".z-btn-icotext-down");
				  }
			)
			this.mouseup(				
				  function () {
					$(this).removeClass("z-btn-down");
					//$(this).children('button').addClass(".z-btn-icotext");
					//$(this).children('button').removeClass(".z-btn-icotext-down");
				  }
			)
		};
		this.disable = function(){
			 this.addClass("z-btn-dsb");
			 this.unbind("hover");
			 this.unbind("click");
		};  
		return this;
	};
	
	/*文本*/
	$.fn.txt = function(){
		var txt = this.data("_self");
		if(txt){
			return txt;
		};
		
		this.init = function(){
			
			//如果只读属性则另作处理
			$(this).addClass("z-txt");
			
			if($(this).attr("readOnly")){
				this.readonly(true);
			}else{
				this.readonly(false);				
			}

		}
		
		this.readonly = function(isReadonly){
			if(!isReadonly){
				//不是只读
				this.hover(
					  function () {
						$(this).addClass("z-txt-over");
					  },
					  function () {
						$(this).removeClass("z-txt-over");					
					  }
					);
					
				this.focus(
					function() {
						$(this).addClass("z-txt-focus");
					});
					
				this.blur(
					function() {
						$(this).removeClass("z-txt-focus");
					});	
			}else{
				//只读				
				this.addClass("z-txt-readonly");
				this.unbind("hover");
				this.unbind("click");
			};
		};
		return this;
	}
	
	/*文本区域*/		
	$.fn.textarea = function(){
		var txt = this.data("_self");;
		if(txt){
			return txt;
		};
		
		this.init = function(){
			$(this).addClass("z-textarea");
			
			this.hover(
				  function () {
				    $(this).addClass("z-textarea-over");
				  },
				  function () {
					$(this).removeClass("z-textarea-over");					
				  }
				);
				
			this.focus(
				function() {
				    $(this).addClass("z-textarea-focus");
				});
				
			this.blur(
				function() {
					$(this).removeClass("z-textarea-focus");
				});	
		}
		return this;
	}
	
	/*下拉*/	
	$.fn.dorpdown = function(){
		var txt = this.data("_self");;
		if(txt){
			return txt;
		};
		
		this.init = function(){
			var obj = $(this);
			$(this).addClass("z-select");
			
			var id = $(this).attr('id')||"gen"+Math.random();
			var name = $(this).attr('name')||"";
			var width = $(this).css('width');
			/*$(this).css('border-width','0px');
			if(browser==IE6){
				$(this).css('margin-left','-1px');
				$(this).css('margin-right','-1px');
				$(this).css('margin-top','-2px');
			}else if(browser==IE7){
				$(this).css('margin','-1px');
			}else{
				$(this).css('margin','-1px');
			}
			$(this).css('vertical-align','middle');
			*/
			//alert(width);
			if(width=='auto'){
				width=180;
				$(this).css('width',width+'px');
			}else{
				width = width.replace("px","");
				$(this).css('width', width + 'px');
				//alert(width);
			}
			var height=20;
			$(this).css('height',height+'px');
			//alert(width);

			var ddlStr=[
				'<table id="tb_'+id+'" class="z-select ' + browser + '" style="text-align:left; height:'+ (parseInt(height) + 4) +'px;" cellSpacing=0 cellPadding=0 border=0><tbody><tr>',
					'<TD class=z-select-left></TD>',
					 '<div class="z-sel-div" >',
						'<div id="div_' + id + '" class="z-sel-div-div" style="width:'+ (parseInt(width) - 2 ) +'px; height:' + (parseInt(height) - 2) + 'px;">',
							
						'</div>',
					  '</div>',					
					'<TD class=z-select-center style="vertical-align:top; width:'+ (parseInt(width) - 11) +'px; ">',					 
					'</TD>',
					'<TD class=z-select-right></TD>',
				'</tr></tbody></table>'];
//				'<span id="sp_' + id + '"></span>',
			var ddl = $(ddlStr.join(''));
			$(this).after(ddl);
			$('#div_'+id).prepend($('#'+id));
			
			
			//$('div_' + id).wrap(tbStr);
			//$(this).after(ddl);
			
			//$(this).wrapInner('#sp_'+id);
			//$('#sp_'+id).wrap($('#'+id));
			//$('#sp_' + id).wrapAll($('#' + id));
						
			//$(this).replaceWith(ddl);
			//alert(ddlStr);


						
			//bnt.data("_self", bnt); 
			 
			//this.click(this._click);
			//alert($('tb_'+id).html());
			
			$('#'+ id + ' > option:odd').addClass('odd');
			$('#'+ id + ' > option:even').addClass('even');
			
			//selobj = $('#'+id);

			 
			return $(this);
		}
		return this;
	}
	
	/*radio按钮*/	
	$.fn.prettyCheckboxes = function(settings) {
		settings = jQuery.extend({
					checkboxWidth: 19,
					checkboxHeight: 19,
					className : 'prettyCheckbox',
					display: 'list'
				}, settings);

		
		$(this).each(function(){
	
			// Find the label
			$label = $('label[for="'+$(this).attr('id')+'"]');

			// Add the checkbox holder to the label
			$label.prepend("<span class='holderWrap'><span class='holder'></span></span>");

			// If the checkbox is checked, display it as checked
			if($(this).is(':checked')) { $label.addClass('checked'); };

			// Assign the class on the label
			$label.addClass(settings.className).addClass($(this).attr('type')).addClass(settings.display);

			// Assign the dimensions to the checkbox display
			$label.find('span.holderWrap').width(settings.checkboxWidth).height(settings.checkboxHeight);
			$label.find('span.holder').width(settings.checkboxWidth);

			// Hide the checkbox
			$(this).addClass('hiddenCheckbox');

			// Associate the click event
			$label.bind('click',function(){
				$('input#' + $(this).attr('for')).triggerHandler('click');
				
				if($('input#' + $(this).attr('for')).is(':checkbox')){
					$(this).toggleClass('checked');
					$('input#' + $(this).attr('for')).checked = true;
					
					$(this).find('span.holder').css('top',0);
				}else{
					$toCheck = $('input#' + $(this).attr('for'));

					// Uncheck all radio
					$('input[name="'+$toCheck.attr('name')+'"]').each(function(){
						$('label[for="' + $(this).attr('id')+'"]').removeClass('checked');	
					});

					$(this).addClass('checked');
					$toCheck.checked = true;
				};
			});
			
			$('input#' + $label.attr('for')).bind('keypress',function(e){
				if(e.keyCode == 32){
					if($.browser.msie){
						$('label[for="'+$(this).attr('id')+'"]').toggleClass("checked");
					}else{
						$(this).trigger('click');
					}
					return false;
				};
			});
		});
		
	};
	
	
	
	
	
	$(function(){
		$("input[type='button']").each(function(){
			$(this).btn().init();
		});
		
		
		$("input[type='fileField']").each(function(){
			$(this).txt().init();
		});
		$("input[type='text']").each(function(){
			$(this).txt().init();
		});
		$("input[type='password']").each(function(){
			$(this).txt().init();
		});
		
		$("textarea").each(function(){
			$(this).textarea().init();
		});		
		$("select").each(function(){
			if(selEff){	$(this).dorpdown().init(); }
		});
		$("input[type=checkbox], input[type='radio']").each(function(){
			//$(this).prettyCheckboxes();
		});		
		
		
		
		$("input[type='reset']").each(function(){
			$(this).btn().init().click(function(){
				var form = $(this).parents("form")[0];
				if(form)
					form.reset();
			});
		});
		$("input[type='submit']").each(function(){
			$(this).btn().init().click(function(){
				var form = $(this).parents("form")[0];
				if(form)
					form.submit();
			});
		});
			
	});
})(jQuery);	


