$(function(){
	$("#sickSubStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#sickSubStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#sickSubDesc").val("");
		$("#sickSubName").val("");
		$("#sickSubNo").val("10");
		$('#sickSubStatus')[0].selectedIndex = 0;
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
		deleteSicknessSub();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	validateAddEdit();
	
	sickTreeshow();
	//绑定推荐医生按钮
	$("#btn_YyDoc").click(function(){
		yyDocManager();
	});
	
	$("#btn_addYyDoc").click(function(){
		addYyDoc();
	});
	
	$("#btn_deleteYyDoc").click(function(){
		deleteYyDoc();
	});
	$("#btn_queryYyDoc").click(function(){
		queryYyDoc();
	});
});

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	sickSubName: {required:true},
        	sickSubDesc: {required:true},
        	sickSubNo: {required:true,number:true,range:[0,20]}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : { 
            sickSubName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            sickSubDesc: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>描述还没填写！</span>"
            },
            sickSubNo: {
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

//编辑回显
editShow=function(){
	//回显清空内容
	var ids=getCheckId("grup");
	
	if(ids.length==0){
		alert("未选择要编辑的项目！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个项目进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/SicknessAndattr/getSicknessSub.do",{
			sickSubId:ids},
			function(data){
				//设置回显的值
				$("#sickSubDesc").val(data.sickSubDesc);
				$("#sickSubName").val(data.sickSubName);
				$('#sickSubStatus').val(data.sickSubStatus);
				$("#sickSubNo").val(data.sickSubStatus);
			}
		);
	}
};

//删除确认
deleteSicknessSub=function(){
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要删除的项目！！！");
	}else{
		confirmWin("你确认要删除吗？","subDelete");
	}
};

//删除提交
subDelete=function(){
	var ids=getCheckId("grup");

	sendAjax(root+"/SicknessAndattr/delSicknessSub.do",{
		sId:sId,
		pId:pId,
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				query_sickSub();
			}else{
				alert(data.msg);
			}
		}
	);
};

//添加与编辑保存按钮事件
saveAddEdit=function(){
	var type=$("#submit-mode").val();
	if(type=="add"){
			sendAjax(root+"/SicknessAndattr/addSicknessSub.do",{
				sId:sId,
				sickSubName:$('#sickSubName').val(),
				sickSubDesc:$('#sickSubDesc').val(),
				sickSubNo:$('#sickSubNo').val(),
				sickSubStatus:$('#sickSubStatus').val(),
				pId:pId},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 query_sickSub();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/SicknessAndattr/editSicknessSub.do",{
					sId:sId,
					sickSubName:$('#sickSubName').val(),
					sickSubDesc:$('#sickSubDesc').val(),
					sickSubNo:$('#sickSubNo').val(),
					sickSubStatus:$('#sickSubStatus').val(),
					sickSubId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 query_sickSub();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var pId=0;
sickTreeshow=function(){
	var setting = {
		async : {
			enable : true,
			url : getUrl
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			showIcon : true,
			showLine: true
		},
		callback : {
			onClick : treeClick
		// 单机触发的事件
		}
	};
	sendAjax(root+"/commonAdminTree/sickSubTree.do",{sId:sId,pId:0},
		function(data){
			$.fn.zTree.init($("#tree_sick"), setting, [ {
				"id" : "0",
				"name" : '根',
				"isParent" : true,
				"leaf" : 0,
				children : data
			} ]);
		}
	);
};
function getUrl(treeId, treeNode) {
	var param = "sId="+sId+"&pId=" + treeNode.id;
	return root + "/commonAdminTree/sickSubTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	pId=treeNode.id;
	query_sickSub();
};
/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
query_sickSub=function(){
	var url=root+"/SicknessAndattr/listSicknessSub.do";
	sendAjax(url,{sId:sId,pId:pId},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};
yyDocManager=function(){
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要查看的疾病种类。。。");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个疾病种类。。。");
	}else{
		$(".sicknessId").val(ids);
		queryYyDoc();
	}
};
queryYyDoc=function(){
	var url=root+"/SicknessAndattr/listYyDoc.do";
	sendAjax(url,{
		queryContent:$("#quertDocContent").val(),
		sickId:$(".sicknessId").val(),
		type:0},
			function(data){
				templateHtml("docList",data,"docContent");
				//表格行点击选中复选框
				$(".yyDocTable tr").each(function(){  
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
		
				$(".docTable").dialog({
			         title       : "推荐医生管理",
			         backdrop: "static",
			         //dialogClass : "modal-lg",
			         buttons: {
			        	 "关闭": function() {
			                 $(this).dialog("close");}
			          }
			      });
			      yyDocTreeInit();
		    }
	);
};
addYyDoc=function(){
		$("#addYyDoc").dialog({
	         title       : "添加医生",
	         //backdrop: "static",
	         dialogClass : "modal-sm",
	         buttons: {
	        	 "关闭": function(){
	                $(this).dialog("close");},
                "确定": function(){
                	addYyDocSub();
                $(this).dialog("close");
                }
	          }
	      });
	      
};
addYyDocSub=function(){
	var ids=$.fn.zTree.getZTreeObj("addYyDoc").getCheckedNodes(true);
	var idList='';
	for (var index = 0; index < ids.length; index++) {
		if(ids[index].id.substr(0,1)=='d'){
			idList=idList+ids[index].id.substr(2,ids[index].id.length)+",";
		}
	}
	//定义最后一个逗号的前一个索引
	var lastd=idList.lastIndexOf(",");
	idList=idList.slice(0,lastd);
	var url =root+"/SicknessAndattr/addYyDoc.do";
	sendAjax(url,{
		idList:idList,
		sickId:$(".sicknessId").val(),
		type:0},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					queryYyDoc();
				}else{
					alert(data.msg);
				}
		    }
	);
	
};
yyDocTreeInit=function(){
	var setting = {
		async : {
			enable : true,
			url : getYyUrl
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		view : {
			showIcon : true,
			showLine: true
		},
		check: {
			enable: true,
			chkboxType: { "Y": "ps", "N": "ps" },
			chkStyle: "checkbox"
		}
	};
	sendAjax(root+"/commonAdminTree/doctorTree.do",{pId:'c-1'},
		function(data){
			$.fn.zTree.init($("#addYyDoc"), setting, [ {
				"id" : "1",
				"name" : '北京瑞吉康星',
				"isParent" : true,
				"leaf" : 0,
				children : data
			} ]);
		}
	);
};
function getYyUrl(treeId, treeNode) {
	var param = "pId=" + treeNode.id;
	return root + "/commonAdminTree/doctorTree.do?" + param;
};

deleteYyDoc=function(){
	var ids=getCheckId("docGrup");
	if(ids.length==0){
		alert("未选择要删除的项目！！！");
	}else{
		confirmWin("你确认要删除吗？","subDocDelete");
	}
};

//删除提交
subDocDelete=function(){
	var ids=getCheckId("docGrup");
	sendAjax(root+"/SicknessAndattr/deleteYyDoc.do",{
		ids:ids,
		type:0},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryYyDoc();
			}else{
				alert(data.msg);
			}
		}
	);
};