$(function(){
	initPage();
	validateAddEdit();
	queryCode();
	//绑定查询按钮事件
	$("#btn_sync").click(function(){
		syncMemory();
	});
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加属性信息");
		$("#cList").val("");
		$("#cVar").val("");
		$("#cText").val("");
		$("#cCode").val("");
		$("#cDesc").val("");
		$("#cSortNo").val("10");
		
		$('#cState')[0].selectedIndex = 0;
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
initPage=function(){
	$("#cState option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#cState").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
}
validateAddEdit=function(){
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	cList: {required:true},
        	cVar: {required:true},
        	cText: {required:true},
        	cCode: {required:true},
        	cSortNo: {required:true,number:true,range:[0,20]}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
        	cList: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>集合名称还没填写！</span>"
            },
            cVar: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>变量名称还没填写！</span>"
            },
            cText: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>值名称还没填写！</span>"
            },
            cCode: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>值还没填写！</span>"
            },
            cSortNo: {
	            required: "<span style='line-height: 35px;color:red;font-weight: normal;'>序号还没填写！</span>",
	            number:"<span style='line-height: 35px;color:red;font-weight: normal;'>序号必须是数字！</span>",
	            range:"<span style='line-height: 35px;color:red;font-weight: normal;'>序号必须是数字0-20！</span>"
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
			sendAjax(root+"/codev2/addCode.do",{
				cList:$("#cList").val(),
				cVar:$("#cVar").val(),
				cText:$("#cText").val(),
				cCode:$("#cCode").val(),
				cDesc:$("#cDesc").val(),
				cSortNo:$("#cSortNo").val(),
				cState:$("#cState").val(),
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
		sendAjax(root+"/codev2/editCode.do",{
					cList:$("#cList").val(),
					cVar:$("#cVar").val(),
					cText:$("#cText").val(),
					cCode:$("#cCode").val(),
					cDesc:$("#cDesc").val(),
					cSortNo:$("#cSortNo").val(),
					cState:$("#cState").val(),
					cId:ids,
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
		
		sendAjax(root+"/codev2/getCode.do",{
			cId:ids},
			function(data){
				//设置回显的值
				$("#cList").val(data.cList);
				$("#cVar").val(data.cVar);
				$("#cText").val(data.cText);
				$("#cCode").val(data.cCode);
				$("#cDesc").val(data.cDesc);
				$("#cSortNo").val(data.cSortNo);
				$("#cState").val(data.cState);
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

	sendAjax(root+"/codev2/delCode.do",{
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
	var url=root+"/codev2/listCode.do";
	sendAjax(url,{},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
		    }
	);
};

syncMemory=function(){
	var url=root+"/codev2/syncMemory.do";
	sendAjax(url,{},
			function(data){
				alert(data.msg);
		    }
	);
};
