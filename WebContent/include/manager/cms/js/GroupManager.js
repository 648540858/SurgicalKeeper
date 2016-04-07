$(function(){
	initPage();
	
	queryGroup();
	fanye_fun(function(){
		queryGroup();
	});
	$("#addPage").css("height",$(document.body).height()*0.55);
	$("#replyPage").css("height",$(document.body).height()*0.55);
	//绑定查询按钮事件
	$("#btn_query_1").click(function(){
		nTypeText='';
		nOptypeText='';
		queryContent=$("#queryId").val();
		queryGroup();
	});
	
	//绑定查询按钮事件
	$("#btn_query_2").click(function(){
		queryContent='';
		nTypeText=$('#queryBYContent').val();
		nOptypeText=$("#queryBYAuthor").val();
		queryGroup();
	});
	
	//添加操作时内容类型发生改变时执行的的方法
	$("#ContentType").change(function() {
		var type=$('#ContentType').val();
		if(type==3){
			 $("#mediaType").show(500);
		}else{
			$("#mediaType").hide(500);
		}
	});
	//绑定添加按钮事件
	$("#btn_add").click(function(){
		
		addBtn_reply();
	});
	
	//图标上传
	$("#uploadBtn").click(function(){
		uploadBtn();
	});
	
	//查看回复
	$("#btn_reply").click(function(){
		replyBtn();
	});
	
	//编辑
	$("#btn_edit").click(function(){
		editBtn();
	});

	//删除提交
	$("#btn_delete").click(function(){
		deleteGroup();
	});
		
	
	
		
		
		
});
	var adId=0;
	var nTypeText='';
	var nOptypeText='';
	var nOptype='';
	var queryContent='';
	var reply_rId='';
initPage=function(){
		$("#adStatus option").remove();
		$("#adStatus option").remove();
		$("#adStatus option").remove();
		
		var id_arr=getCodeArray('GROUP_CONTENT_TYPE');
		for(var i=0;i<id_arr.length;i++) {
			$("#queryBYContent").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
		}
		id_arr=getCodeArray('GROUP_OPTION_TYPE');
		for(var i=0;i<id_arr.length;i++) {
			$("#queryBYAuthor").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
		}
		id_arr=getCodeArray('GROUP_CONTENT_TYPE');
		for(var i=0;i<id_arr.length;i++) {
			$("#ContentType").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
		}
		id_arr=getCodeArray('STATE');
		for(var i=0;i<id_arr.length;i++) {
			$("#groupStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
		}
		
		
	//初始化读文本编辑器
	editor = new wangEditor('groupContent');
	editor.config.uploadImgUrl = root + '/resources/wangEditor/jsp/upload_json.jsp';
	editor.config.emotions = {
	        'default': {
	            title: '默认',  // 分组的标题
	            size: 18,  // 表情图标的尺寸
	            imgs: [
	                // 每个表情图标的url地址
	                root+'/resources/wangEditor/static/emotions/default/1.gif',
	                root+'/resources/wangEditor/static/emotions/default/2.gif',
	                root+'/resources/wangEditor/static/emotions/default/3.gif',
	                root+'/resources/wangEditor/static/emotions/default/4.gif',
	                root+'/resources/wangEditor/static/emotions/default/5.gif',
	                root+'/resources/wangEditor/static/emotions/default/6.gif',
	                root+'/resources/wangEditor/static/emotions/default/7.gif',
	                root+'/resources/wangEditor/static/emotions/default/8.gif',
	                root+'/resources/wangEditor/static/emotions/default/9.gif',
	                root+'/resources/wangEditor/static/emotions/default/10.gif',
	                root+'/resources/wangEditor/static/emotions/default/11.gif',
	                root+'/resources/wangEditor/static/emotions/default/12.gif',
	                root+'/resources/wangEditor/static/emotions/default/13.gif',
	                root+'/resources/wangEditor/static/emotions/default/14.gif',
	                root+'/resources/wangEditor/static/emotions/default/15.gif',
	                root+'/resources/wangEditor/static/emotions/default/16.gif',
	                root+'/resources/wangEditor/static/emotions/default/17.gif',
	                root+'/resources/wangEditor/static/emotions/default/18.gif',
	                root+'/resources/wangEditor/static/emotions/default/19.gif',
	                root+'/resources/wangEditor/static/emotions/default/20.gif',
	                root+'/resources/wangEditor/static/emotions/default/21.gif',
	                root+'/resources/wangEditor/static/emotions/default/22.gif',
	                root+'/resources/wangEditor/static/emotions/default/23.gif',
	                root+'/resources/wangEditor/static/emotions/default/24.gif',
	                root+'/resources/wangEditor/static/emotions/default/25.gif',
	                root+'/resources/wangEditor/static/emotions/default/26.gif',
	                root+'/resources/wangEditor/static/emotions/default/27.gif',
	                root+'/resources/wangEditor/static/emotions/default/28.gif',
	                root+'/resources/wangEditor/static/emotions/default/29.gif',
	                root+'/resources/wangEditor/static/emotions/default/30.gif',
	                root+'/resources/wangEditor/static/emotions/default/31.gif',
	                root+'/resources/wangEditor/static/emotions/default/32.gif',
	                root+'/resources/wangEditor/static/emotions/default/33.gif',
	                root+'/resources/wangEditor/static/emotions/default/34.gif',
	                root+'/resources/wangEditor/static/emotions/default/35.gif',
	                root+'/resources/wangEditor/static/emotions/default/36.gif',
	                root+'/resources/wangEditor/static/emotions/default/37.gif',
	                root+'/resources/wangEditor/static/emotions/default/38.gif',
	                root+'/resources/wangEditor/static/emotions/default/39.gif',
	                root+'/resources/wangEditor/static/emotions/default/40.gif',
	                root+'/resources/wangEditor/static/emotions/default/41.gif',
	                root+'/resources/wangEditor/static/emotions/default/42.gif',
	                root+'/resources/wangEditor/static/emotions/default/43.gif',
	                root+'/resources/wangEditor/static/emotions/default/44.gif',
	                root+'/resources/wangEditor/static/emotions/default/45.gif',
	                root+'/resources/wangEditor/static/emotions/default/46.gif',
	                root+'/resources/wangEditor/static/emotions/default/47.gif',
	                root+'/resources/wangEditor/static/emotions/default/48.gif',
	                root+'/resources/wangEditor/static/emotions/default/49.gif',
	                root+'/resources/wangEditor/static/emotions/default/50.gif'
	            ]
	        }
	    };
    editor.create();
	validateAddEdit();
	
};

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	gTitle: {required:true},
        	groupContent: {required:true},
        	fileIcon: {required:true},
        	mediaUrl:{required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo( element.next() );       
    	},
        messages : {
        	gTitle: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>标题还没有填写</span>"
            },
            groupContent: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>内容还没有填写</span>"
            },
            fileIcon: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>视频图标没有上传</span>"
            },
            mediaUrl: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>视频地址还没有填写</span>"
            }
    	}
    });
	
};


//添加
addBtn_reply=function(){
	$('#gTitle').val('');
	$('#ContentType').val(1);

	editor.$txt.html('<p><br></p>');
	$('#mediaUrl').val('');
	$('#fileAdres').val('');
	$('#fileIcon').val('');
	$('#groupStatus').val(1);
	$("#upInto").html("");
	$("#mediaType").hide();
	$("#addPage").dialog({ 
		backdrop: "static",
        title       : "添加",
        //dialogClass : "modal-lg",
       buttons: {
    	   "取消": function() {
               $(this).dialog("close");},
            "提交": function() {
            		var gTitle=$('#gTitle').val();
            		var gType=$('#ContentType').val();
            		var gContent=editor.$txt.text();
            		var gMediaUrl=$('#mediaUrl').val();
            		var gImg=$('#fileAdres').val();
            		if(gType!=3){
            			var gMediaUrl=null;
            			var gImg=null;
            		}
            		if((gType==3&&gTitle!=""&&gType!=""&&gContent!=""&&gMediaUrl!=""&&gImg!="")||(gType!=3&&gTitle!=""&&gType!=""&&gContent!="")){
			        	
			        	sendAjax(root+"/groupController/addGroup.do",{
			        		gTitle:gTitle,
			        		msgtype:gType,
			        		gContent:editor.$txt.html(),
			        		gMediaUrl:gMediaUrl,
			        		gImg:gImg,
			        		gStatus:$('#groupStatus').val()},
							function(data){
								if(data.bflag==1){
									alert(data.msg);
									$("#addPage").dialog("close");
									queryGroup();
								}else{
									alert(data.msg);
									$("#addPage").dialog("close");
								}
							}
						);
            		}else{
            			alert("内容填写不完整，请检查后提交...");
            		}
           
    		
            }
        }
    });
};

uploadBtn=function(){
	if($('#fileIcon').val()!=''){
		$.ajaxFileUpload({
	        url:root+'/groupController/uploadAdImg.do',
	        fileElementId:'fileIcon',
	        dataType: 'json',
	        success: function (data, status){
	          if(data.bflag==1){
	        	  $("#fileAdres").val(data.msg);
	        	  $("#upInto").html("上传成功...");
	          }else{
	        	  $("#fileAdres").val();
	        	  $("#upInto").html("上传失败...请重试...");
	          }
	        },
	        error: function (data, status){
	        }
	   	});
	};
};

//编辑
editBtn=function(){
	
	$('#gTitle').val('');
	$('#ContentType').val(1);
	editor.$txt.html('<p><br></p>');
	$('#mediaUrl').val('');
	$('#fileAdres').val('');
	$('#fileIcon').val('');
	$('#imgShow').attr("src","");
	$('#groupStatus').val(1);
	$("#upInto").html("");
	$("#mediaType").hide();
	
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择任何内容");
	}else if(ids.indexOf(",")>0){
		alert("请选择单一内容进行编辑");
	}else if($.isNumeric(Number(ids))){
		sendAjax(root+"/groupController/queryGroup.do",{
			nId:ids},
			function(data){
				
				$('#gTitle').val(data.nTitle);
				$('#groupStatus').val(data.nStatus);
				$('#ContentType').val(data.nType);
				if(data.nType==3){
					 $("#mediaType").show();
					 $('#mediaUrl').val(data.nMovieUrl);
					 $('#imgShow').attr("src",data.nImg);
					 $('#fileAdres').val(data.nImg);
				}
				editor.$txt.append('<p>'+data.nContent+'</p>');
			}
		);
		editSubmit(ids);
		
	}
};

editSubmit=function(ids){
	$("#addPage").dialog({ 
		backdrop: "static",
        title       : "编辑",
       buttons: {
    	   "取消": function() {
               $(this).dialog("close");},
            "提交": function() {
            		var gTitle=$('#gTitle').val();
            		var gType=$('#ContentType').val();
            		var gContent=editor.$txt.text();;
            		var gMediaUrl=$('#mediaUrl').val();
            		var gImg=$('#fileAdres').val();
            		if(gType!=3){
            			var gMediaUrl=null;
            			var gImg=null;
            		}
            		if((gType==3&&gTitle!=""&&gType!=""&&gContent!=""&&gMediaUrl!=""&&gImg!="")||(gType!=2&&gTitle!=""&&gType!=""&&gContent!="")){
		            	sendAjax(root+"/groupController/editGroup.do",{
		            		gTitle:gTitle,
		            		gType:gType,
		            		gContent:editor.$txt.html(),
		            		gMediaUrl:gMediaUrl,
		            		gImg:gImg,
		            		gStatus:$('#groupStatus').val(),
		            		nId:ids},
		    				function(data){
		    					if(data.bflag==1){
		    						alert(data.msg);
		    						$("#addPage").dialog("close");
		    						queryGroup();
		    					}else{
		    						alert(data.msg);
		    						$("#addPage").dialog("close");
		    					}
		    				}
		    			);
		            	$(this).dialog("close");
            	}else{
            			alert("内容填写不完整，请检查后提交...");
            		}
            }
        }
    });
	
};

//查看回复
replyBtn=function(){
	
	reply_rId=getCheckId("grup");
	if(reply_rId.length==0){
		alert("未选择任何内容");
	}else if(reply_rId.indexOf(",")>0){
		alert("请选择单一内容进行编辑");
	}else if($.isNumeric(Number(reply_rId))){
		
		sendAjax(root+"/groupController/queryReply.do",{
			queryReplyContent:"",
			nOptype:"",
			nId:reply_rId},
			function(data){
				templateHtml("replyList",data,"replyPage");
				//绑定复选框事件
				$("#rIdAll").click(function(){
					checkbox("rId","rIdAll");
				});
				$(".huifu").css("height",$(document.body).height()*0.45);
			}
		);
		
		$("#replyPage").dialog({ 
		backdrop: "static",
        title       : "查看回复",
       	buttons: {
    	   "关闭": function() {
               $(this).dialog("close");}
        }
    });
		
	}
};

//查询回复
queryReply=function(){
	
	sendAjax(root+"/groupController/queryReply.do",{
			queryReplyContent:"",
			nOptype:$("#nOptype").val(),
			nId:reply_rId},
			function(data){
				templateHtml("replyList",data,"replyPage");
			}
		);
};
//修改回复内容
replysubmit=function(id){
	
	var content=$("#text"+id+"").val();
		sendAjax(root+"/groupController/editReply.do",{
				content:content,
				rId:id},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
					}else{
						alert(data.msg);
					}
				}
			);
		
};

//删除
deleteGroup=function(){
	
	 $.messager.model = { 
        cancel: { text: "取消", classed: 'btn-error' },
        ok:{ text: "确认", classed: 'btn-default' }
      };
      $.messager.confirm("删除提示", "确认删除吗", function() { 
        var ids=getCheckId("grup");
		  sendAjax(root+"/groupController/deleteGroup.do",{
				ids:ids},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						queryGroup();
					}else{
						alert(data.msg);
					}
				}
			);
      });
};


/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
queryGroup=function(){
	
	var limit=10;
	var page=$("#page_now").val();
	var url=root+"/groupController/listGroup.do";
	var start=limit*(page-1);
	
	sendAjax(url,{
		limit:limit,
		page:page,
		start:start,
		queryContent:queryContent,
		nType:nTypeText,
		nOptype:nOptypeText},
			function(data){
				templateHtml("list",data,"content");
				$("#allContent").html(data.TOTALCOUNT);
				table_checked();
				$("#total").val(data.TOTALCOUNT);
				fanye_chageCss();
				
		    }
	);
};

//删除回复
deleteReply=function(){
	var ids_reply=getCheckId("rId");
	if(reply_rId.length==0){
		alert("未选择任何内容");
	}else{
		$.messager.model = { 
        cancel: { text: "取消", classed: 'btn-error' },
        ok:{ text: "确认", classed: 'btn-default' }
      };
      $.messager.confirm("删除提示", "确认删除吗", function() { 
        
		  sendAjax(root+"/groupController/deleteReplyById.do",{
				ids_reply:ids_reply},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						queryReply();
					}else{
						alert(data.msg);
					}
				}
			);
      });
	}
};
