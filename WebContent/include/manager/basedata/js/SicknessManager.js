$(function(){
	initPage();
	validateAddEdit();
	querySickness();
	//绑定查询按钮事件
	$("#btn_query").click(function(){
		querySickness();
	});
	$("#btn_queryYyDoc").click(function(){
		queryYyDoc();
	});
	$(".edit_content").css("height",$(document.body).height()*0.60);
	$(".docTable").css("height",$(document.body).height()*0.60);
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#sickName").val("");
		$("#sickDesc").val("");
		
		$('#sickStatus')[0].selectedIndex = 0;
		$("#modal_addEdit").modal("toggle");
	});
	
	//编辑按钮绑定事件
	$("#btn_edit").click(function(){
		$("#submit-mode").val("edit");
		$("#modalTitle").text("编辑");
		editShow();
	});
	
	//给子类维护按钮绑定事件，激活弹窗
	$("#btn_sub").click(function(){
		var ids=getCheckId("grup");
		if(ids.length==0){
			alert("未选择要操作的疾病！！！");
		}else if(ids.indexOf(",")>0){
			alert("请选择单个进行操作");
		}else if($.isNumeric(Number(ids)) ){
			var url=root+"/include/manager/basedata/jsp/SicknessSubManager.jsp?sId="+ids;
			$("#subsickframe").attr("src",url);
			$("#modal_subSick").modal("toggle");
		}
	});
	
	//删除按钮绑定事件
	$("#btn_delete").click(function(){
		deleteSickness();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});

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
});
initPage=function(){
	$("#sickStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#sickStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
};
validateAddEdit=function(){
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	sickName: {required:true},
            sickDesc: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
            sickName: { 
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            sickDesc: {
	            required: "<span style='line-height: 35px;color:red;font-weight: normal;'>描述还没填写！</span>"
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
			sendAjax(root+"/SicknessAndattr/addSickness.do",{
				sickName:$('#sickName').val(),
				sickDesc:$('#sickDesc').val(),
				sickStatus:$('#sickStatus').val(),
				submitMode:""},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 querySickness();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/SicknessAndattr/editSickness.do",{
					sickName:$('#sickName').val(),
					sickDesc:$('#sickDesc').val(),
					sickStatus:$('#sickStatus').val(),
					sickId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 querySickness();
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
		alert("未选择要编辑的疾病！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/SicknessAndattr/getSickness.do",{
			sickId:ids},
			function(data){
				//设置回显的值
				$("#sickName").val(data.sickName);
				$("#sickDesc").val(data.sickDesc);
				$('#sickStatus').val(data.sickStatus);
			}
		);
	}
};

//删除确认
deleteSickness=function(){
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

	sendAjax(root+"/SicknessAndattr/delSickness.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				querySickness();
			}else{
				alert(data.msg);
			}
		}
	);
};

querySickness=function(){
	var url=root+"/SicknessAndattr/listSickness.do";
	sendAjax(url,{
		queryContent:$("#input_query").val()},
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
		type:1},
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
			      docTreeInit();
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
		type:1},
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
docTreeInit=function(){
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
function getUrl(treeId, treeNode) {
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
		type:1},
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
