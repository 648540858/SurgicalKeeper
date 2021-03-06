$(function(){
	initPage();
	validateAddEdit();
	querySicknessAttr();
	//绑定查询按钮事件
	$("#btn_query").click(function(){
		querySicknessAttr();
	});
	$(".modal-body").css("height",$(document.body).height()*0.60);
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加属性信息");
		$("#attrName").val("");
		$("#attrIcon").val("");
		$("#attrNo").val("10");
		
		$('#attrStatus')[0].selectedIndex = 0;
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
		deleteSicknessAttr();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});

});
initPage=function(){
	$("#attrStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#attrStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
}
validateAddEdit=function(){
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	attrName: {required:true},
            attrNo: {required:true,number:true,range:[0,20]},
            attrIcon: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
            attrName: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            attrNo: {
	            required: "<span style='line-height: 35px;color:red;font-weight: normal;'>序号还没填写！</span>",
	            number:"<span style='line-height: 35px;color:red;font-weight: normal;'>序号必须是数字！</span>",
	            range:"<span style='line-height: 35px;color:red;font-weight: normal;'>序号必须是数字0-20！</span>"
	        },
            attrIcon: {
	            required: "<span style='line-height: 35px;color:red;font-weight: normal;'>图标还没填写！</span>"
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
			sendAjax(root+"/SicknessAndattr/addSicknessAttr.do",{
				attrName:$('#attrName').val(),
				attrIcon:$('#attrIcon').val(),
				attrNo:$('#attrNo').val(),
				attrStatus:$('#attrStatus').val(),
				submitMode:""},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 querySicknessAttr();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/SicknessAndattr/editSicknessAttr.do",{
					attrName:$('#attrName').val(),
					attrIcon:$('#attrIcon').val(),
					attrNo:$('#attrNo').val(),
					attrStatus:$('#attrStatus').val(),
					attrId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 querySicknessAttr();
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
		alert("未选择要编辑的属性！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/SicknessAndattr/getSicknessAttr.do",{
			attrId:ids},
			function(data){
				//设置回显的值
				$("#attrName").val(data.attrName);
				$("#attrIcon").val(data.attrIcon);
				$('#attrNo').val(data.attrNo);
				$('#attrStatus').val(data.attrStatus);
			}
		);
	}
};

//删除确认
deleteSicknessAttr=function(){
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

	sendAjax(root+"/SicknessAndattr/delSicknessAttr.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				querySicknessAttr();
			}else{
				alert(data.msg);
			}
		}
	);
};

querySicknessAttr=function(){
	var url=root+"/SicknessAndattr/listSicknessAttr.do";
	sendAjax(url,{
		queryContent:$("#input_query").val()},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
		    }
	);
};

