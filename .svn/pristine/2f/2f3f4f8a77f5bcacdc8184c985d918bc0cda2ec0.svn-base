(function(){
		var pathName = window.document.location.pathname; 
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/')+1); 
		
		//document.write("<script src='"+projectName+"/resources/jquery-bootstrap/jquery.bootstrap.js' type='text/javascript'></script>");
		$.getScript(projectName+"/resources/jquery-bootstrap/jquery.bootstrap.js");
		
		$("<link rel='stylesheet' type='text/css' href='"+projectName+"/include/manager/css/common.css'/>").appendTo("head");
}());
var getCodeText = function(_list,_code){
	var codeText = "";
	for(var i=0;i<codeArray.length;i++){
		if(codeArray[i].cList == _list && codeArray[i].cCode == _code){
			codeText = codeArray[i].cText;
			break;
		}
	}
	return codeText;
};

var getCodeText2 = function(_var){
	var codeText = "";
	for(var i=0;i<codeArray.length;i++){
		if(codeArray[i].cVar == _var){
			codeText = codeArray[i].cText;
			break;
		}
	}
	return codeText;
};
/**
 * 根据VAR 找Code集合
 * @param _var
 * @return {}
 */
var getCodeArray = function(_var){
	var code = [];
	for(var i=0;i<codeArray.length;i++) {
		if(codeArray[i].cList == _var) {
			code.push({
				name : codeArray[i].cText,
				code : codeArray[i].cCode
			});
		}
	}
	return code;
};
/**
 * 将JSON装入模板并放入指定DOM中
 * @param {} tempId
 * @param {} data
 * @param {} domId
 */
templateHtml=function (tempId , data , domId){
	
	var html = template(tempId, data);
	
	$('#' + domId).html(html);
};
	
appandTemplateHtml=function (tempId , data , domId){
	
	var html = template(tempId, data);
	
	$('#' + domId).html($('#' + domId).html() + html);

};
	
/**
 * LOAD JS
 */
LoadJS = function( fileUrl ){
	var oHead = document.getElementsByTagName('HEAD').item(0);
	
	var oScript= document.createElement("script");
	
	oScript.type = "text/javascript";
	
	oScript.src=fileUrl;
	
	oHead.appendChild( oScript);
//	$(document.createElement("script")).attr("src",fileUrl).appendTo("head");
//	$("<script type='text/javascript' src='"+fileUrl+"'/").appendTo("head");
//	$.getScript(fileUrl);
};

/**
 * 发送AJAX
 */
sendAjax = function(url,params,callback){
	
	var that = this;
	
	var paramsStr = (typeof params) == 'string' ? params : $.param(params);
	
	$.ajax({
		 type : 'POST' ,
		 data:params,
		 url : url,
		 dataType : "json",
		 beforeSend : function(xhr, settings){
		 	that.setProgressbar();
		 },
		 success: function(data){
		 	if(typeof callback == "function"){
		 		callback.call(this , data,'success');
		 	}
		 },
		 error: function(xhr, errorType, error){
			if(xhr && (xhr.status === 200||xhr.status === 0)){
				window.parent.location.href = "/SurgicalKeeper/include/manager/index/jsp/login.jsp";
				return;
			}
			if(typeof callback == "function"){
				callback.call(this , errorType , 'fail');
			}
		 },
		 complete : function(xhr, status){
			 that.removeProgressbar();
		 }
	});
};
	
/**
 * 设置进度条
 */
setProgressbar = function(){
	if($('.myloadbox').length<1){
		var html = "<div class=\"myloadbox\"><div class=\"spinner\">" +
					"<div class=\"spinner-container container1\">" +
						"<div class=\"circle1\"></div>" +
						"<div class=\"circle2\"></div>" +
						"<div class=\"circle3\"></div>" +
						"<div class=\"circle4\"></div>" +
					"</div>" +
					"<div class=\"spinner-container container2\">" +
						"<div class=\"circle1\"></div>" +
						"<div class=\"circle2\"></div>" +
						"<div class=\"circle3\"></div>" +
						"<div class=\"circle4\"></div>" +
					"</div>" +
					"<div class=\"spinner-container container3\">" +
						"<div class=\"circle1\"></div>" +
						"<div class=\"circle2\"></div>" +
						"<div class=\"circle3\"></div>" +
						"<div class=\"circle4\"></div>" +
					"</div>" +
				"</div><span>正在处理中。。。</span</div>";
		$(document.body).append(html);
	}
	var leftw=($(window).width() - $('.spinner').width())/2;
	var leftt=($(window).height() - $('.spinner').height())/2+$(document).scrollTop();
	if(leftw<100){
		leftw='48%';
		leftt='48%';
	}else{
		leftw+='px';
		leftt+='px';
	}
	$('.spinner').css({
		left: leftw, 
		top: leftt 
	});
	$('.myloadbox').show();
};

/**
 * 除去进度条
 */
removeProgressbar = function(){
	$('.myloadbox').hide();
};
/**
 * 清理Cookie
 */
clearCookie = function(){
	var keys=document.cookie.match(/[^ =;]+(?=\=)/g); 
	if (keys) {
		for (var i = keys.length; i--;)
		document.cookie=keys[i]+'=0;expires=' + new Date(0).toUTCString();
	}
};

/**
 * 复选框操作（全选按钮
 * checkgName，复选框组的name属性
 * checkId：全选复选框的id
 */
checkbox=function(checkgName , checkId ){
	 $("input[name='"+checkgName+"']").prop("checked",$("#"+checkId+"").is(":checked"));
};
	
	
	
	/**
	 * 获取页面的checkbox组的id值，并拼成（1，2，5，6）的形式
	 */
getCheckId= function(groupName){
	var ids="";
	$("input[name='"+groupName+"']").each(function(){
		 if($(this).prop("checked")==true){
				ids=ids+$(this).val()+",";
			};
	});
	//定义最后一个逗号的前一个索引
	var lastd=ids.lastIndexOf(",");
	ids=ids.slice(0,lastd);
	return ids;
};


/**
 * 确认窗
 */
confirmWin=function (prompt ,callback){
	$("#confirmModel").remove();
		var flsg=false;
		var html = "<div class=\"modal fade\" id=\"confirmModel\">"+  
					  "<div class=\"modal-dialog\">"+   
					    "<div class=\"modal-content \">"+    
					      "<div class=\"modal-header\"> "+   
					        "<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button>"+    
					        "<h4 class=\"modal-title\">提示信息</h4>  "+  
					      "</div>  "+  
					      "<div class=\"modal-body\">  "+  
					        "<p>"+prompt+"</p>"+  
					      "</div>  "+  
					      "<div class=\"modal-footer\">  "+  
					         "<input type=\"hidden\" id=\"url\"/>  "+  
					         "<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">取消</button>  "+  
					         "<button type=\"button\" onclick=\""+callback+"()\" class=\"btn btn-success\" data-dismiss=\"modal\">确定</button>  "+  
					      "</div>  "+  
					    "</div>"+  
					  "</div>"+  
					"</div>";
	$('body').append(html);
	$("#confirmModel").modal("toggle");
};

/**
 * ******************************************  页面分页相关  ***************************************************************************
 */
//向页面添加翻页导航栏
addPaging=function(divId){
		
	var html = "<div id=\"footer\" > "+
					 "<ul class=\"pager\"> "+
					 " <li class=\"disabled\" id=\"prevPage\"><span>&laquo;上一页</span></li> "+
					 " <li >第&nbsp<span id=\"pageNum\" >1</span>&nbsp页</li> "+
					 " <li >共&nbsp<span id=\"pageTotal\" title=0>0</span>&nbsp页</li> "+
					 " <li class=\"\"id=\"nextPage\" ><span>下一页&raquo;</span></li> "+
					"</ul>  "+
				"</div>";
	$('body').append(html);
};

//改变共几页的方法
changeTotal=function(total,curpage){
	$("#pageTotal").text(total);
	$("#pageNum").text(curpage);
	
	if(curpage<2){
		$('#prevPage').html("<span>&laquo;上一页</span>");
		$('#prevPage').prop("class","disabled");
	}else{
		$('#prevPage').html("<span>&laquo;上一页</span>");
		$('#prevPage').prop("class","");
	}
	if(curpage>=total&&total>1){
		$('#nextPage').html("<span>下一页&raquo;</span>");
		$('#nextPage').prop("class","disabled");
	}
	if(total>1||curpage<total){
		$('#nextPage').html("<span>下一页&raquo;</span>");
		$('#nextPage').prop("class","");
	}
};

//改变共几页的方法
/**
 * 
 * @param {} total :查询的总条数
 * @param {} curpage 当前页
 */
changeTotalV2=function(total,curpage,limit){
	var allPage=0;
				if(total%limit==0){
					allPage=Math.floor(total/limit);
				}else{
					allPage=Math.floor(total/limit)+1;
				}
	$("#pageTotal").text(allPage);
	$("#pageNum").text(curpage);
	
	if(curpage<2){
		$('#prevPage').html("<span>&laquo;上一页</span>");
		$('#prevPage').prop("class","disabled");
	}else{
		$('#prevPage').html("<span>&laquo;上一页</span>");
		$('#prevPage').prop("class","");
	}
	if(curpage>=allPage&&allPage>1){
		$('#nextPage').html("<span>下一页&raquo;</span>");
		$('#nextPage').prop("class","disabled");
	}
	if(allPage>1||curpage<allPage){
		$('#nextPage').html("<span>下一页&raquo;</span>");
		$('#nextPage').prop("class","");
	}
};
/*alert=function(){
	if(arguments.length==0){
		$.messager.alert("提示","");
		return;
	}
	if(arguments.length==1){
		$.messager.alert(arguments[0]);
		return;
	}
	if(arguments.length==2){
		$.messager.alert(arguments[0],arguments[1]);
		return;
	}
};*/

//翻页V2
flipPage=function(){
	function addPaging(){
				var html = "<div id=\"footer\" > "+
								 "<ul class=\"pager\"> "+
								 " <li class=\"disabled prevPage\" ><span>&laquo;上一页</span></li> "+
								 " <li >第&nbsp<span id=\"pageNum\" >1</span>&nbsp页</li> "+
								 " <li >共&nbsp<span id=\"pageTotal\" title=0>0</span>&nbsp页</li> "+
								 " <li class=\"nextPage\" ><span>下一页&raquo;</span></li> "+
								"</ul>  "+
							"</div>";
				$('body').append(html);
	};
};

table_checked=function(){
	//表格行点击选中复选框
	$(".table-hover tr").each(function(){  
	    var p = this;  
	    $(this).on("click","td",function(){
	       $($(p).children()[0]).children().each(function(){  
	            if(this.type=="checkbox"){  
	                if(!this.checked){  
	                    this.checked = true;  
	                }else{  
	                    this.checked = false;  
	                }  
	            }  
	        }); 
	    });  
	});
		
	};

var lock=false;
showTreeArea=function(){
	//tree区域的隐藏显示
		$("#btn_tree").click(function(){
			if(lock){
			$(".mytree").animate({width:"30%"},500);
			$(".mytree").animate({height:"100%"},500);
			$(".all_content").animate({width:"70%"},500);
			$(".istree").animate({height:"100%"},500);
			lock=!lock;
		}else{
			$(".mytree").animate({width:"0"},500);
			$(".mytree").animate({height:"0"},500);
			$(".all_content").animate({width:"100%"},500);
			$(".istree").animate({height:"0"},500);
			lock=!lock;
		} 
	});
	
};


fanye_chageCss=function(){
	var limit=10;
	var total=$("#total").val();
	var allPage=getAllPage(total,limit);
	var page_now=Number($("#page_now").val());
	if(allPage==1||allPage==0){
		$('#Pag_1').addClass('active');
		
		$('#Pag_2').removeClass('active');
		$('#Pag_2').addClass("disabled");
		
		$('#Pag_3').removeClass('active');
		$('#Pag_3').addClass("disabled");
		
		$('#nextPag').addClass('disabled');
		$('#lastPag').addClass('disabled');
		$('#firstPag').addClass('disabled');
		$('#finalPag').addClass('disabled');
	}else if(allPage==2){
		if(page_now==1){
			$('#Pag_1').addClass('active');
			
			$('#Pag_2').removeClass('active');
			$('#Pag_2').removeClass("disabled");
			
			$('#Pag_3').removeClass('active');
			$('#Pag_3').addClass("disabled");
			
			$('#nextPag').removeClass('disabled');
			$('#finalPag').removeClass('disabled');
			$('#lastPag').addClass('disabled');
			$('#firstPag').addClass('disabled');
		}else if(page_now==2){
			$('#Pag_1').removeClass('active');
			
			$('#Pag_2').addClass('active');
			$('#Pag_2').removeClass('disabled');
			
			$('#Pag_3').removeClass('active');
			$('#Pag_3').addClass("disabled");
			
			$('#nextPag').addClass('disabled');
			$('#finalPag').addClass('disabled');
			$('#lastPag').removeClass('disabled');
			$('#firstPag').removeClass('disabled');
		}
	}else if(allPage>=3){
		if(page_now==1){
			$('#Pag_1').addClass('active');
			
			$('#Pag_2').removeClass('active');
			$('#Pag_2').removeClass("disabled");
			
			$('#Pag_3').removeClass('active');
			$('#Pag_3').removeClass("disabled");
			
			$('#nextPag').removeClass('disabled');
			$('#finalPag').removeClass('disabled');
			$('#lastPag').addClass('disabled');
			$('#firstPag').addClass('disabled');
		}else if(page_now>1&&page_now<allPage){
			$('#nextPag').removeClass('disabled');
			$('#finalPag').removeClass('disabled');
			$('#lastPag').removeClass('disabled');
			$('#firstPag').removeClass('disabled');
		}else if(page_now==allPage){
			$('#Pag_1').removeClass('active');
			
			$('#Pag_2').removeClass('active');
			$('#Pag_2').removeClass('disabled');
			
			$('#Pag_3').addClass('active');
			$('#Pag_3').removeClass("disabled");
			
			$('#nextPag').addClass('disabled');
			$('#finalPag').addClass('disabled');
			$('#lastPag').removeClass('disabled');
			$('#firstPag').removeClass('disabled');
		}
	
};
};
fanye_fun=function(callback){
	var limit=10;
	$('.pagination li ').on("click","a",function(){
		var p=$(this).parent().attr("id");
		if(!$(this).parent().hasClass("disabled")){
		if(p.substring(0,3)=="Pag"){
	        $('.pagination li').removeClass('active');
	        $(this).parent().addClass('active');
	        var page_now=$(this).html();
	        $("#page_now").val(page_now);
	        callback();
		}else if(p=="firstPag"){
			$('#Pag_3').children().html(3);
			$('#Pag_2').children().html(2);
			$('#Pag_1').children().html(1);
			$("#page_now").val(1);
			callback();
		}else if(p=="finalPag"){
			var total=Number($("#total").val());
			var allPage=getAllPage(total,limit);
			if(allPage>3){
				$('#Pag_3').children().html(allPage);
				$('#Pag_2').children().html(allPage-1);
				$('#Pag_1').children().html(allPage-2);
			}else if(allPage<=3){
				$('#Pag_3').children().html(3);
				$('#Pag_2').children().html(2);
				$('#Pag_1').children().html(1);
			}
			$("#page_now").val(allPage);
			callback();
		}else if(p=="lastPag"){
			var id="";
			$('.pagination li ').each(function(){
				if($(this).attr("class")=="active"){
					id=$(this).attr("id");
				}
			});
			var NO=Number(id.substring(id.length-1,id.length));
			if(NO==2||NO==3){
				$("#"+id).removeClass('active');
				$("#Pag_"+(NO-1)).addClass('active');
			}else if(NO==1){
				$('.pagination li ').each(function(){
					var a_html=Number($(this).children().html());
					if(!isNaN(a_html)){
						$(this).children().html(a_html-1);
					}
				});
			};
			var page_now = Number($("#page_now").val());
			$("#page_now").val( page_now-1);
			callback();
		}else if(p=="nextPag"){
			var id="";
			$('.pagination li ').each(function(){
				if($(this).attr("class")=="active"){
					id=$(this).attr("id");
				}
			});
			var NO=Number(id.substring(id.length-1,id.length));
			if(NO==1||NO==2){
				$("#"+id).removeClass('active');
				$("#Pag_"+(NO+1)).addClass('active');
			}else if(NO==3){
				$('.pagination li ').each(function(){
					var a_html=Number($(this).children().html());
					if(!isNaN(a_html)){
						$(this).children().html(a_html+1);
					}
				});
			};
			var page_now = Number($("#page_now").val());
			$("#page_now").val(page_now+1);
			callback();
		}
	};
   });
};
getAllPage=function(total, limit){
	var allPage=0;
	if(total%limit==0){
		allPage=Math.floor(total/limit);
	}else{
		allPage=Math.floor(total/limit)+1;
	};
	return allPage;	
};
