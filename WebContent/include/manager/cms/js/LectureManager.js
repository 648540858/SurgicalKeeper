$(function(){
	initPage();
	validateAddEdit();
	queryLecture();
	fanye_fun(function(){
		queryLecture();
	});
	//绑定查询按钮事件
	$("#btn_query").click(function(){
		queryLecture();
	});
	$(".modal-body").css("height",$(document.body).height()*0.6);
	//异步上传文件
	$("#uploadfile").click(function(){
		$.ajaxFileUpload({
	         url:root+'/lecturev2/uploadPhoto.do',
	         fileElementId:'fileIcon',
	         dataType: 'json',
	         success: function (data, status){
	           if(data.bflag==1){
	        	   $("#imgIcon").attr("src",data.msg);
	        	   $("#lecIcon").val(data.msg);
	           }else{
	        	   $("#imgIcon").attr("src","");
	        	   $("#lecIcon").val("");
	           }
	         },
	         error: function (data, status){
	         }
	    	}
		);
	});
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#lecTitle").val("");
		$("#lecMediaurl").val("");
		$("#lecIcon").val("");
		$("#fileIcon").val("");
		$("#imgIcon").attr("src","");
		$("#lecSpeaker").val("");
		$("#lecCompany").val("");
		$("#lecContent").val("");
		
		$('#lecStatus')[0].selectedIndex = 0;
		$("#modal_addEdit").modal("toggle");
	});
	
	//编辑按钮绑定事件
	$("#btn_edit").click(function(){
		$("#submit-mode").val("edit");
		$("#modalTitle").text("编辑");
		editShow();
	});
	
	//删除按钮绑定事件
	$("#btn_delete").click(function(){
		deleteLecture();
	});
		
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});

	$("#prevPage").click(function(){
		var curpage=Number($("#pageNum").text());
		if(curpage<2)
			return;
		curpage--;
		$("#pageNum").text(curpage);
		var total=Number($("#pageTotal").text());
		changeTotal(total,curpage);
		queryLecture();
	});
	$("#nextPage").click(function(){
		var curpage=Number($("#pageNum").text());
		var total=Number($("#pageTotal").text());
		if(curpage>=total)
			return;
		curpage++;
		$("#pageNum").text(curpage);
		changeTotal(total,curpage);
		queryLecture();
	});
});

initPage=function(){
	$("#lecStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#lecStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
};
validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	lecTitle: {required:true},
        	lecMediaurl: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
        	lecTitle: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>标题还没填写！</span>"
            },
            lecMediaurl: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>文件地址还没填写！</span>"
            }
    	}
    });
	$("#btn-save-addEdit").click(function(){
		saveAddEdit();
	});
};

//添加与编辑保存按钮事件
saveAddEdit=function(){
	var type=$("#submit-mode").val();
	if(type=="add"){
			sendAjax(root+"/lecturev2/addLecture.do",{
				lecTitle:$("#lecTitle").val(),
				lecMediaurl:$("#lecMediaurl").val(),
				lecIcon:$("#lecIcon").val(),
				lecSpeaker:$("#lecSpeaker").val(),
				lecCompany:$("#lecCompany").val(),
				lecContent:$("#lecContent").val(),
				lecStatus:$("#lecStatus").val(),
				submitMode:"add"},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryLecture();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/lecturev2/editLecture.do",{
					lecTitle:$("#lecTitle").val(),
					lecMediaurl:$("#lecMediaurl").val(),
					lecIcon:$("#lecIcon").val(),
					lecSpeaker:$("#lecSpeaker").val(),
					lecCompany:$("#lecCompany").val(),
					lecContent:$("#lecContent").val(),
					lecStatus:$("#lecStatus").val(),
					lecId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryLecture();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

//编辑回显
editShow=function(){
	//回显清空内容
	var ids=getCheckId("grup");
	
	if(ids.length==0){
		alert("未选择要编辑的项目！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个项目进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/lecturev2/getLecture.do",{
			lecId:ids},
			function(data){
				$("#lecTitle").val(data.lecTitle);
				$("#lecMediaurl").val(data.lecMediaurl);
				$("#lecIcon").val(data.lecIcon);
				$("#fileIcon").val("");
				$("#imgIcon").attr("src",data.lecIcon);
				$("#lecSpeaker").val(data.lecSpeaker);
				$("#lecCompany").val(data.lecCompany);
				$("#lecContent").val(data.lecContent);
				$("#lecContent").val(data.lecStatus);
			}
		);
	}
};

//删除确认
deleteLecture=function(){
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要删除的项目！");
	}else{
		confirmWin("你确认要删除吗？","subDelete");
	}
};

//删除提交
subDelete=function(){
	var ids=getCheckId("grup");

	sendAjax(root+"/lecturev2/delLecture.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryLecture();
			}else{
				alert(data.msg);
			}
		}
	);
};


/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
queryLecture=function(){
	var limit=10;
	var page=$("#page_now").val();
	var queryContent=$("#input_query").val();
	var url=root+"/lecturev2/listLecture.do";
	var start=limit*(page-1);
	sendAjax(url,{
		limit:limit,
		page:page,
		start:start,
		queryContent:queryContent},
			function(data){
				templateHtml("list",data,"content");
				$("#allContent").html(data.TOTALCOUNT);
				table_checked();
				$("#total").val(data.TOTALCOUNT);
				fanye_chageCss();
		    }
	);
};

