$(function(){
	$("#roleStatus option").remove();
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#roleStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	
	validateAddEdit();
	
	queryRole();
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#roleDesc").val("");
		$("#roleName").val("");
		
		$('#roleStatus')[0].selectedIndex = 0;
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
		deleteRole();
	});
	
	//绑定菜单按钮绑定事件
	$("#btn_bindMenu").click(function(){
		bindMenuShow();
	});
	
	//绑定菜单提交按钮绑定
	$("#btn_save_bindMenu").click(function(){
		bindMenuSave();
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
        	roleName: {required:true},
        	roleDesc: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
        	roleName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            roleDesc: {
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
			sendAjax(root+"/organizationv2/addRole.do",{
				roleName:$('#roleName').val(),
				roleDesc:$('#roleDesc').val(),
				roleStatus:$('#roleStatus').val(),
				submitMode:"add"},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryRole();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/organizationv2/editRole.do",{
					roleName:$('#roleName').val(),
					roleDesc:$('#roleDesc').val(),
					roleStatus:$('#roleStatus').val(),
					roleId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryRole();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};


//绑定信息树形结构显示
bindMenuShow=function(){
	
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要绑定的项目！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个项目进行绑定");
	}else if($.isNumeric(Number(ids)) ){
		$("#modal_bindMenu").modal("toggle");
		var setting={
			async: {
				enable: true,
				type:"post",
				contentType: "application/x-www-form-urlencoded",
				url: root+"/authorityv2/menuTreeWithUserForCombo.do",
				autoParam: ['id=node'],
				otherParam: {"roleId" : ids}
			},
			data: {
				key: {
					name: "text",
					checked: "checked",
					children: "nodes"
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			view:{
				expandSpeed: "slow",
				selectedMulti: true,
				showIcon: true,
				showLine: true,
				showTitle: false
			}
		};
		
		//给全选按钮添加事件
		$("#check_all_bind").click(function name() {
			var checkstatu=$("#check_all_bind").is(":checked");
			if(checkstatu==true){
				$.fn.zTree.getZTreeObj("tree_menu").checkAllNodes(true);
			}else if(checkstatu==false){
				$.fn.zTree.getZTreeObj("tree_menu").checkAllNodes(false);
			}
		});
		
		var t = $("#tree_menu");
		t = $.fn.zTree.init(t, setting);
	}
	
};

//绑定菜单信息提交
bindMenuSave=function() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_menu");
    var nodes = treeObj.getCheckedNodes(true);
    var roleId=getCheckId("grup");
    var ids="";
   
    for (var i = 0; i < nodes.length; i++) {
    	var id=nodes[i].id,
    	ids = ids+id+",";
    }
	//定义最后一个逗号的前一个索引
	var lastd=ids.lastIndexOf(",");
	ids=ids.slice(0,lastd);
	sendAjax(root+"/authorityv2/setMenuForRole.do",{
				ids:ids,
				roleId:roleId},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				 //添加成功关闭对话窗口
				 $("#modal_bindMenu").modal("toggle");
				 queryRole();
			}else{
				alert(data.msg);
			}
		}
	);
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
		
		sendAjax(root+"/organizationv2/getRole.do",{
			roleId:ids},
			function(data){
				//设置回显的值
				$("#roleDesc").val(data.roleDesc);
				$("#roleName").val(data.roleName);	
				$('#roleStatus').val(data.roleStatus);
			}
		);
	}
};

//删除确认
deleteRole=function(){
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

	sendAjax(root+"/organizationv2/delRole.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryRole();
			}else{
				alert(data.msg);
			}
		}
	);
};


/**
 * 查询工具方法
 */
queryRole=function(){
	var url=root+"/organizationv2/listRole.do";
	sendAjax(url,{},
		function(data){
			templateHtml("list",data,"content");
			table_checked();
	    }
	);
};
