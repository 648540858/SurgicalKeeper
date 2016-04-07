$(function(){
	validateAddEdit();
	queryCode();
	//绑定同步内存按钮事件
	$("#btn_sync").click(function(){
		syncMemory();
	});
	//绑定创建微信菜单按钮事件
	$("#btn_wx").click(function(){
		createWxMenu();
	});
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加属性信息");
		$("#pKey").val("");
		$("#pValue").val("");
		$("#pDesc").val("");
		
		$("#modal_addEdit").modal("toggle");
	});
	
	//编辑按钮绑定事件
	$("#btn_edit").click(function(){
		$("#submit-mode").val("edit");
		$("#modalTitle").text("编辑信息");
		editShow();
	});
	
	//删除按钮绑定事件
	$("#btn_delete").click(function(){
		deleteCode();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});

});

validateAddEdit=function(){
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	pKey: {required:true},
        	pValue: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
        	pKey: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            pValue: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>值还没填写！</span>"
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
			sendAjax(root+"/paramsv2/addParams.do",{
				pKey:$("#pKey").val(),
				pValue:$("#pValue").val(),
				pDesc:$("#pDesc").val(),
				submitMode:"add"},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryCode();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/paramsv2/editParams.do",{
					pKey:$("#pKey").val(),
					pValue:$("#pValue").val(),
					pDesc:$("#pDesc").val(),
					pId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryCode();
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
		alert("未选择要编辑的项目！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/paramsv2/getParams.do",{
			pId:ids},
			function(data){
				//设置回显的值
				$("#pKey").val(data.pKey);
				$("#pValue").val(data.pValue);
				$("#pDesc").val(data.pDesc);
			}
		);
	}
};

//删除确认
deleteCode=function(){
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要删除的项目！！！")
	}else{
		confirmWin("你确认要删除吗？","subDelete");
	}
};

//删除提交
subDelete=function(){
	var ids=getCheckId("grup");

	sendAjax(root+"/paramsv2/delParams.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryCode();
			}else{
				alert(data.msg);
			}
		}
	);
};


queryCode=function(){
	var url=root+"/paramsv2/listParams.do";
	sendAjax(url,{},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
		    }
	);
};

syncMemory=function(){
	var url=root+"/paramsv2/syncMemory.do";
	sendAjax(url,{},
			function(data){
				alert(data.msg);
		    }
	);
};

createWxMenu=function(){
	var url=root+"/paramsv2/createWxMenu.do";
	sendAjax(url,{},
			function(data){
				alert(data.msg);
		    }
	);
};
