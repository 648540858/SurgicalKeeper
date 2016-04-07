$(function(){
	$("#mState option").remove();
	$("#mType option").remove();
	showTreeArea();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#mState").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('MENU_TYPE');
	for(var i=0;i<id_arr.length;i++) {
		$("#mType").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	$(".modal-body").css("height",$(".all_content").height()*0.6);
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加医院信息");
		$("#mName").val("");
		$("#mDesc").val("");
		$("#mRequest").val("");
		$("#mSortNo").val("10");
		
		$('#mState')[0].selectedIndex = 0;
		$('#mType')[0].selectedIndex = 0;
		$("#modal_addEdit").modal("toggle");
	});
	
	//编辑按钮绑定事件
	$("#btn_edit").click(function(){
		$("#submit-mode").val("edit");
		$("#modalTitle").text("编辑医院信息");
		editShow();
	});
	//删除按钮绑定事件
	$("#btn_delete").click(function(){
		deleteMenu();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	validateAddEdit();
	
	menuTreeshow();
});

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	mName: {required:true},
        	mSortNo: {required:true,number:true,range:[0,20]}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
            mName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            mSortNo: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>地址还没填写！</span>",
            	number:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字！</span>",
	            range:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字0-70！</span>"
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
		
		sendAjax(root+"/menuv2/getMenu.do",{
			mId:ids},
			function(data){
				//设置回显的值
				$("#mName").val(data.mName);
				$("#mDesc").val(data.mDesc);
				$("#mRequest").val(data.mRequest);
				$("#mSortNo").val(data.mSortNo);
				$("#mIcon").val(data.mIcon);
				$("#mType").val(data.mType);
				$("#mState").val(data.mState);
			}
		);
	}
};

//删除确认
deleteMenu=function(){
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

	sendAjax(root+"/menuv2/delMenu.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryMenu();
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
			sendAjax(root+"/menuv2/addUser.do",{
				pId:pId,
				mName:$("#mName").val(),
				mDesc:$("#mDesc").val(),
				mRequest:$("#mRequest").val(),
				mSortNo:$("#mSortNo").val(),
				mIcon:$("#mIcon").val(),
				mType:$("#mType").val(),
				mState:$("#mState").val()},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryMenu();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/menuv2/editUser.do",{
				pId:pId,
				mName:$("#mName").val(),
				mDesc:$("#mDesc").val(),
				mRequest:$("#mRequest").val(),
				mSortNo:$("#mSortNo").val(),
				mIcon:$("#mIcon").val(),
				mType:$("#mType").val(),
				mState:$("#mState").val(),
				mId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryMenu();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var pId=0;
menuTreeshow=function(){
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
	
	sendAjax(root+"/commonAdminTree/menuTree.do",{pId:1},
		function(data){
			$.fn.zTree.init($("#tree_menu"), setting, [ {
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
	return root + "/commonAdminTree/menuTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	pId=treeNode.id;
	queryMenu();
};
/**
 * 查询工具方法
 */
queryMenu=function(){
	var url=root+"/menuv2/listMenu.do";
	sendAjax(url,{pId:pId},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};
