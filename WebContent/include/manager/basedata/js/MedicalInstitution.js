$(function(){
	$("#hospType option").remove();
	$("#hospLevel option").remove();
	$("#hospStatus option").remove();
	showTreeArea();
	var id_arr=getCodeArray('HOS_TYPE');
	for(var i=0;i<id_arr.length;i++) {
		$("#hospType").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('HOS_LEVEL');
	for(var i=0;i<id_arr.length;i++) {
		$("#hospLevel").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#hospStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	
	$("#btn_query").click(function(){
		query_Hosp();
	});
	
	//异步上传文件
	$("#uploadfile").click(function(){
		$.ajaxFileUpload({
	         url:root+'/hospDept/uploadPhoto.do',
	         fileElementId:'fileIcon',
	         dataType: 'json',
	         success: function (data, status){
	           if(data.bflag==1){
	        	   $("#imgIcon").attr("src",data.msg);
	        	   $("#hospLogo").val(data.msg);
	           }else{
	        	   $("#imgIcon").attr("src","");
	        	   $("#hospLogo").val("");
	           }
	         },
	         error: function (data, status){
	         }
	    	}
		);
	});
	$(".modal-body").css("height",$(".all_content").height()*0.6);
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		if(cityId==0)
			return;
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加医院信息");
		$("#hospDesc").val("");
		$("#hospName").val("");
		$("#hospAddr").val("");
		$("#hospTel").val("");
		$("#hospLogo").val("");
		$("#fileIcon").val("");
		$("#imgIcon").attr("src","");
		
		$('#hospStatus')[0].selectedIndex = 0;
		$('#hospType')[0].selectedIndex = 0;
		$('#hospLevel')[0].selectedIndex = 0;
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
		deleteHosp();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	//绑定科室信息按钮绑定事件
	$("#btn_bindDept").click(function(){
		bindHospDeptShow();
	});
	
	//绑定科室信息提交按钮绑定
	$("#btn_save_bindHospDept").click(function(){
		bindHospDeptSave();
	});
	
	validateAddEdit();
	
	cityTreeshow();
});

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	hospName: {required:true},
        	hospDesc: {required:true},
        	hospAddr: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : { 
            hospName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名称还没填写！</span>"
            },
            hospDesc: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>描述还没填写！</span>"
            },
            hospAddr: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>地址还没填写！</span>"
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
		alert("未选择要编辑的医院！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个医院进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/hospDept/getHosp.do",{
			hospId:ids},
			function(data){
				//设置回显的值
				$("#hospDesc").val(data.hospDesc);
				$("#hospName").val(data.hospName);
				$('#hospLevel').val(data.hospLevel);
				$('#hospType').val(data.hospType);
				$('#hospStatus').val(data.hospStatus);
				$("#hospAddr").val(data.hospAddr);
				$("#hospTel").val(data.hospTel);
				$("#hospLogo").val(data.hospLogo);
				$("#fileIcon").val("");
				$("#imgIcon").attr("src",data.hospLogo);
			}
		);
	}
};

//删除确认
deleteHosp=function(){
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

	sendAjax(root+"/hospDept/delHospital.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				query_Hosp();
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
			sendAjax(root+"/hospDept/addHospital.do",{
				cityId:cityId,
				hospName:$('#hospName').val(),
				hospAddr:$('#hospAddr').val(),
				hospTel:$('#hospTel').val(),
				hospLogo:$('#hospLogo').val(),
				hospType:$('#hospType').val(),
				hospDesc:$('#hospDesc').val(),
				hospEmail:'',
				hospStatus:$('#hospStatus').val(),
				hospLevel:$('#hospLevel').val()},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 query_Hosp();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/hospDept/editHospital.do",{
					cityId:cityId,
					hospName:$('#hospName').val(),
					hospAddr:$('#hospAddr').val(),
					hospTel:$('#hospTel').val(),
					hospLogo:$('#hospLogo').val(),
					hospType:$('#hospType').val(),
					hospDesc:$('#hospDesc').val(),
					hospEmail:'',
					hospStatus:$('#hospStatus').val(),
					hospLevel:$('#hospLevel').val(),
					hospId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 query_Hosp();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var cityId=0;
cityTreeshow=function(){
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
	sendAjax(root+"/commonAdminTree/cityTree.do",{pId:1},
		function(data){
			$.fn.zTree.init($("#tree_area"), setting, [ {
				"id" : "1",
				"name" : '中华人民共和国',
				"isParent" : true,
				"leaf" : 0,
				children : data
			} ]);
		}
	);
}
function getUrl(treeId, treeNode) {
	var param = "pId=" + treeNode.id;
	return root + "/commonAdminTree/cityTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	if(treeNode.leaf==1){
		cityId=treeNode.id;
		query_Hosp();
	}else{
		cityId=0;
		templateHtml("list",{},"content");
		return;
	}
}
/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
query_Hosp=function(){
	var url=root+"/hospDept/listHospital.do";
	sendAjax(url,{
		cityId:cityId,
		queryContent:$("#input_query").val()},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};

//绑定疾病信息树形结构显示
bindHospDeptShow=function(){
	
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要绑定医院！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个医院进行绑定");
	}else if($.isNumeric(Number(ids)) ){
		$("#modal_bindHospDept").modal("toggle");
		var setting={
			async: {
				enable: true,
				type:"post",
				contentType: "application/x-www-form-urlencoded",
				url: root+"/interrelation/hosDeptTreeWithHosForComb.do",
				autoParam: ['id=node'],
				otherParam: {"hospId" : ids}
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
				$.fn.zTree.getZTreeObj("tree_bindHospDept").checkAllNodes(true);
			}else if(checkstatu==false){
				$.fn.zTree.getZTreeObj("tree_bindHospDept").checkAllNodes(false);
			}
		});
		
		var t = $("#tree_bindHospDept");
		t = $.fn.zTree.init(t, setting);
	}
	
};

//绑定科室信息提交
bindHospDeptSave=function() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_bindHospDept");
    var nodes = treeObj.getCheckedNodes(true);
    var hospId=getCheckId("grup");
    var ids="";
   
    for (var i = 0; i < nodes.length; i++) {
    	var id=nodes[i].id,
    	ids = ids+id+",";
    }
	//定义最后一个逗号的前一个索引
	var lastd=ids.lastIndexOf(",");
	ids=ids.slice(0,lastd);

	sendAjax(root+"/interrelation/hosDeptToHos.do",{
				ids:ids,
				hospId:hospId},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				 //添加成功关闭对话窗口
				 $("#modal_bindHospDept").modal("toggle");
				 query_Hosp();
			}else{
				alert(data.msg);
			}
		}
	);
};