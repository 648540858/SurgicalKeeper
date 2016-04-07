$(function(){
	$("#deptStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#deptStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}

	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加医院信息");
		$("#deptName").val("");
		$("#deptSortNo").val("10");
		$("#deptDesc").val("");
		
		$('#deptStatus')[0].selectedIndex = 0;
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
		deleteDept();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	validateAddEdit();
	showTreeArea();
	deptTreeshow();
	
});

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	deptName: {required:true},
        	deptDesc: {required:true},
        	deptSortNo: {required:true,number:true,range:[0,20]}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
            deptName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            deptDesc: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>描述还没填写！</span>"
            },
            deptSortNo: {
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
		alert("未选择要编辑的项目！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个项目进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/organizationv2/getDept.do",{
			deptId:ids},
			function(data){
				//设置回显的值
				$("#deptDesc").val(data.deptDesc);
				$("#deptName").val(data.deptName);
				$('#deptSortNo').val(data.deptSortNo);
				$('#deptStatus').val(data.deptStatus);
			}
		);
	}
};

//删除确认
deleteDept=function(){
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

	sendAjax(root+"/organizationv2/delDept.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryDept();
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
			sendAjax(root+"/organizationv2/addDept.do",{
				pId:pId,
				deptName:$('#deptName').val(),
				deptSortNo:$('#deptSortNo').val(),
				deptDesc:$('#deptDesc').val(),
				deptStatus:$('#deptStatus').val()},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryDept();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/organizationv2/editDept.do",{
					pId:pId,
					deptName:$('#deptName').val(),
					deptSortNo:$('#deptSortNo').val(),
					deptDesc:$('#deptDesc').val(),
					deptStatus:$('#deptStatus').val(),
					deptId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryDept();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var pId=0;
deptTreeshow=function(){
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
	
	sendAjax(root+"/commonAdminTree/deptTree.do",{pId:1},
		function(data){
			$.fn.zTree.init($("#tree_dept"), setting, [ {
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
	return root + "/commonAdminTree/deptTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	pId=treeNode.id;
	queryDept();
};
/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
queryDept=function(){
	var url=root+"/organizationv2/listDept.do";
	sendAjax(url,{pId:pId},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};
