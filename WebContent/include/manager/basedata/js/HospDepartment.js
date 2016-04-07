$(function(){
	initPage();
	validateAddEdit();
	queryHosp();
	fanye_fun(function(){
		queryHosp();
	});
	//绑定查询按钮事件
	$("#btn_query").click(function(){
		queryHosp();
	});
	$(".modal-body").css("height",$(document.body).height()*0.60);
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加科室信息");
		$("#hdDesc").val("");
		$("#hdName").val("");
		
		$('#hdStatus')[0].selectedIndex = 0;
		$('#hdType')[0].selectedIndex = 0;
		$("#modal_addEdit").modal("toggle");
	});
	
	//编辑按钮绑定事件
	$("#btn_edit").click(function(){
		$("#submit-mode").val("edit");
		$("#modalTitle").text("编辑科室信息");
		editShow();
	});
	
	
	//删除按钮绑定事件
	$("#btn_delete").click(function(){
		deleteHosp();
	});
	
	//绑定科室信息按钮绑定事件
	$("#btn_bindHosDept").click(function(){
		bindHosDeptShow();
	});
	
	//绑定科室信息提交按钮绑定
	$("#btn_save_bindHosDept").click(function(){
		bindHospDeptSave();
	});
		
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
});
initPage=function(){
	
	$("#hdStatus option").remove();
	$("#hdType option").remove();
	var id_arr=getCodeArray('HOS_DEPT_TYPE');
	for(var i=0;i<id_arr.length;i++) {
		$("#hdType").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#hdStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
};
validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	hdName: {required:true},
        	hdDesc: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : {
        	hdName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            hdDesc: {
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
			sendAjax(root+"/hospDept/addHosDept.do",{
				hdName:$('#hdName').val(),
				hdType:$('#hdType').val(),
				hdDesc:$('#hdDesc').val(),
				hdStatus:$('#hdStatus').val(),
				hdId:"",
				submitMode:""},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 window.location.reload();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
	
		var ids=getCheckId("grup");
		sendAjax(root+"/hospDept/editHosDept.do",{
					hdName:$('#hdName').val(),
					hdType:$('#hdType').val(),
					hdDesc:$('#hdDesc').val(),
					hdStatus:$('#hdStatus').val(),
					hdId:ids,
					submitMode:"edit"},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 window.location.reload();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};


//绑定疾病信息树形结构显示
bindHosDeptShow=function(){
	
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要绑定疾病信息的科室！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个科室进行绑定");
	}else if($.isNumeric(Number(ids)) ){
		$("#modal_bindHosDept").modal("toggle");
		var setting={
			async: {
				enable: true,
				type:"post",
				contentType: "application/x-www-form-urlencoded",
				url: root+"/interrelation/sicknessTreeWithHosDeptForComb.do",
				autoParam: ['id=node'],
				otherParam: {"hdId" : ids}
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
				chkboxType: { "Y": "p", "N": "s" }
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
				$.fn.zTree.getZTreeObj("tree_bindHosDept").checkAllNodes(true);
			}else if(checkstatu==false){
				$.fn.zTree.getZTreeObj("tree_bindHosDept").checkAllNodes(false);
			}
		});
		
		var t = $("#tree_bindHosDept");
		t = $.fn.zTree.init(t, setting);
	}
	
};


//绑定科室信息提交
bindHospDeptSave=function() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_bindHosDept");
    var nodes = treeObj.getCheckedNodes(true);
    var hdId=getCheckId("grup");
    var ids="";
   
    for (var i = 0; i < nodes.length; i++) {
    	var id=nodes[i].id,
    	ids = ids+id+",";
    }
	//定义最后一个逗号的前一个索引
	var lastd=ids.lastIndexOf(",");
	ids=ids.slice(0,lastd);

	sendAjax(root+"/interrelation/sicknessToHosDept.do",{
				ids:ids,
				hdId:hdId},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				 //添加成功关闭对话窗口
				 $("#modal_bindHosDept").modal("toggle");
				 window.location.reload();
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
		alert("未选择要编辑的科室！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个科室进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/hospDept/hosDept.do",{
			hdId:ids},
			function(data){
				//设置回显的值
				$("#hdDesc").val(data.hdDesc);
				$("#hdName").val(data.hdName);	
				$('#hdType').val(data.hdType);
				$('#hdStatus').val(data.hdStatus);
			}
		);
	}
};

//删除确认
deleteHosp=function(){
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要删除的科室！！！");
	}else{
		confirmWin("你确认要删除吗？","subDelete");
	}
};

//删除提交
subDelete=function(){
	var ids=getCheckId("grup");

	sendAjax(root+"/hospDept/delHosDept.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				window.location.reload();
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
queryHosp=function(){
	var limit=10;
	var page=$("#page_now").val();
	var queryContent=$("#HospDepartmentqueryId").val();
	var url=root+"/hospDept/listHosDept.do";
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

