$(function(){
	$("#docDeptid option").remove();
	$("#docSex option").remove();
	$("#docRank option").remove();
	$("#docDeptrank option").remove();
	$("#docStatus option").remove();
	showTreeArea();
	var id_arr=getCodeArray('SEX');
	for(var i=0;i<id_arr.length;i++) {
		$("#docSex").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('DOC_RANK');
	for(var i=0;i<id_arr.length;i++) {
		$("#docRank").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('DOC_DEPT_RANK');
	for(var i=0;i<id_arr.length;i++) {
		$("#docDeptrank").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#docStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	$(".modal-body").css("height",$(".all_content").height()*0.6);
	$("#btn_query").click(function(){
		queryDoctor();
	});
	//异步上传文件
	$("#uploadfile").click(function(){
		$.ajaxFileUpload({
	         url:root+'/doctorv2/uploadPhoto.do',
	         fileElementId:'fileIcon',
	         dataType: 'json',
	         success: function (data, status){
	           if(data.bflag==1){
	        	   $("#imgIcon").attr("src",data.msg);
	        	   $("#docIcon").val(data.msg);
	           }else{
	        	   $("#imgIcon").attr("src","");
	        	   $("#docIcon").val("");
	           }
	         },
	         error: function (data, status){
	         }
	    	}
		);
	});
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		if(hospId==0){
			alert("请选一个医院");
			return;
		}
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#docName").val("");
		$("#docPhone").val("");
		$("#docIcon").val("");
		$("#fileIcon").val("");
		$("#imgIcon").attr("src","");
		$("#docTel").val("");
		$("#docPwd").val("");
		$("#docDeptaddress").val("");
		$("#docOrderaddress").val("");
		$("#docIntro").val("");
		$("#docGoodat").val("");
		
		$('#docStatus')[0].selectedIndex = 0;
		$('#docSex')[0].selectedIndex = 0;
		$('#docRank')[0].selectedIndex = 0;
		$('#docDeptrank')[0].selectedIndex = 0;
		$('#docDeptid')[0].selectedIndex = 0;
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
		deleteDoctor();
	});
	
	//绑定疾病按钮绑定事件
	$("#btn_bindsickness").click(function(){
		bindSicknessShow();
	});
	
	//绑定疾病提交按钮绑定
	$("#btn_save_bindsickness").click(function(){
		bindSicknessSave();
	});
	
	//重新生成二维码按钮绑定
	$("#btn_save_qrcode").click(function(){
		createQrcode();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	validateAddEdit();
	
	hospTreeshow();
});

validateAddEdit=function(){
	
	$('#validate_addEdit').validate({
        //设置离焦即验证，主要针对required:true, 因为它默认提交时验证
		onfocusout: function(element) { $(element).valid(); },
        rules: {
        	docName: {required:true},
        	docAge: {required:true,number:true,range:[0,100]},
        	docWorkyear: {required:true,number:true,range:[0,70]},
        	docPwd: {required:true}
        },
        errorPlacement: function(error, element) {  
		    error.appendTo(element.parent());  
    	},
        messages : { 
            docName: { 
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>名还没填写！</span>"
            },
            docAge: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>年龄还没填写！</span>",
            	number:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字！</span>",
	            range:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字0-100！</span>"
            },
            docWorkyear: {
            	required: "<span style='line-height: 35px;color:red;font-weight: normal;'>工作年限还没填写！</span>",
            	number:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字！</span>",
	            range:"<span style='line-height: 35px;color:red;font-weight: normal;'>必须是数字0-70！</span>"
            },
            docPwd: {
        		required: "<span style='line-height: 35px;color:red;font-weight: normal;'>密码还没填写！</span>"
            }
    	}
    });
	$("#btn-save-addEdit").click(function(){
		saveAddEdit();
	});
};
var qrdocId=0;
loadqrimg=function(v_docId,imgUrl){
	qrdocId=v_docId;
	if(imgUrl.length<1){
		$("#qrimg").attr("src","");
	}else{
		$("#qrimg").attr("src",imgUrl);
	}
	
	$("#modal_qrcode").modal("toggle");
};
//编辑回显
editShow=function(){
	//回显清空内容
	var ids=getCheckId("grup");
	
	if(ids.length==0){
		alert("未选择要编辑的医生！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个医生进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/doctorv2/getDoctor.do",{
			docId:ids},
			function(data){
				//设置回显的值
				$("#docName").val(data.docName);
				$("#docPhone").val(data.docPhone);
				$("#docTel").val(data.docMobile);
				$("#docPwd").val(data.docPwd);
				$("#docIcon").val(data.docIcon);
				$("#fileIcon").val("");
				$("#imgIcon").attr("src",data.docIcon);
				$("#docDeptaddress").val(data.docDeptaddress);
				$("#docOrderaddress").val(data.docOrderaddress);
				$("#docIntro").val(data.docIntro);
				$("#docGoodat").val(data.docGoodat);
				$("#docDeptid").val(data.docDeptid);
				$("#docSex").val(data.docSex);
				$("#docAge").val(data.docAge);
				$("#docWorkyear").val(data.docWorkyear);
				$("#docRank").val(data.docRank);
				$("#docDeptrank").val(data.docDeptrank);
				$("#docStatus").val(data.docStatus);
			}
		);
	}
};

//删除确认
deleteDoctor=function(){
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

	sendAjax(root+"/doctorv2/delDoctor.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				queryDoctor();
			}else{
				alert(data.msg);
			}
		}
	);
};

//二维码生成
createQrcode=function(){
	var ids=getCheckId("grup");

	sendAjax(root+"/doctorv2/updateDoctorQrcode.do",{
		docId:qrdocId},
		function(data){
			if(data.bflag==1){
				$("#qrimg").attr("src",data.msg);
				queryDoctor();
//				$("#modal_qrcode").modal("toggle");
			}else{
				alert(data.msg);
				$("#modal_qrcode").modal("toggle");
			}
		}
	);
};

//添加与编辑保存按钮事件
saveAddEdit=function(){
	var type=$("#submit-mode").val();
	if(type=="add"){
			sendAjax(root+"/doctorv2/addDoctor.do",{
				hospId:hospId,
				docName:$("#docName").val(),
				docPhone:$("#docPhone").val(),
				docMobile:$("#docTel").val(),
				docPwd:$("#docPwd").val(),
				docIcon:$("#docIcon").val(),
				docDeptaddress:$("#docDeptaddress").val(),
				docOrderaddress:$("#docOrderaddress").val(),
				docIntro:$("#docIntro").val(),
				docGoodat:$("#docGoodat").val(),
				docDeptid:$("#docDeptid").val(),
				docSex:$("#docSex").val(),
				docAge:$("#docAge").val(),
				docWorkyear:$("#docWorkyear").val(),
				docRank:$("#docRank").val(),
				docDeptrank:$("#docDeptrank").val(),
				docStatus:$("#docStatus").val()},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 queryDoctor();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/doctorv2/editDoctor.do",{
					docName:$("#docName").val(),
					docPhone:$("#docPhone").val(),
					docMobile:$("#docTel").val(),
					docPwd:$("#docPwd").val(),
					docIcon:$("#docIcon").val(),
					docDeptaddress:$("#docDeptaddress").val(),
					docOrderaddress:$("#docOrderaddress").val(),
					docIntro:$("#docIntro").val(),
					docGoodat:$("#docGoodat").val(),
					docDeptid:$("#docDeptid").val(),
					docSex:$("#docSex").val(),
					docAge:$("#docAge").val(),
					docWorkyear:$("#docWorkyear").val(),
					docRank:$("#docRank").val(),
					docDeptrank:$("#docDeptrank").val(),
					docStatus:$("#docStatus").val(),
					docId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 queryDoctor();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var hospId=0;
hospTreeshow=function(){
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
	sendAjax(root+"/commonAdminTree/cityHospTree.do",{"pId":"c-1"},
		function(data){
			$.fn.zTree.init($("#tree_area"), setting, [ {
				"id" : "c-1",
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
	return root + "/commonAdminTree/cityHospTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	if(treeNode.leaf==1){
		hospId=treeNode.id.substr(2);
		
		initDocDeptid();
		
		queryDoctor();
	}else{
		hospId=0;
		$("#docDeptid option").remove();
		templateHtml("list",{},"content");
		return;
	}
}
initDocDeptid=function(){
	var url=root+"/commonAdminTree/listHospDept.do";
	sendAjax(url,{hospId:hospId},
			function(data){
				$("#docDeptid option").remove();
				
				var id_arr=data;
				for(var i=0;i<id_arr.length;i++) {
					$("#docDeptid").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
				}
			}
		);
};
/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
queryDoctor=function(){
	var url=root+"/doctorv2/listDoctor.do";
	sendAjax(url,{
		hospId:hospId,
		queryContent:$("#input_query").val()},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};

//绑定疾病信息树形结构显示
bindSicknessShow=function(){
	
	var ids=getCheckId("grup");
	if(ids.length==0){
		alert("未选择要绑定的医生！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个医生进行绑定");
	}else if($.isNumeric(Number(ids)) ){
		$("#modal_bindsickness").modal("toggle");
		var setting={
			async: {
				enable: true,
				type:"post",
				contentType: "application/x-www-form-urlencoded",
				url: root+"/interrelation/sicknessTreeWithDocForComb.do",
				autoParam: ['id=node'],
				otherParam: {"docId" : ids}
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
				$.fn.zTree.getZTreeObj("tree_bindsickness").checkAllNodes(true);
			}else if(checkstatu==false){
				$.fn.zTree.getZTreeObj("tree_bindsickness").checkAllNodes(false);
			}
		});
		
		var t = $("#tree_bindsickness");
		t = $.fn.zTree.init(t, setting);
	}
	
};


//绑定疾病信息提交
bindSicknessSave=function() {
	var treeObj = $.fn.zTree.getZTreeObj("tree_bindsickness");
    var nodes = treeObj.getCheckedNodes(true);
    var docId=getCheckId("grup");
    var ids="";
   
    for (var i = 0; i < nodes.length; i++) {
    	var id=nodes[i].id,
    	ids = ids+id+",";
    }
	//定义最后一个逗号的前一个索引
	var lastd=ids.lastIndexOf(",");
	ids=ids.slice(0,lastd);

	sendAjax(root+"/interrelation/sicknessToDoctor.do",{
				ids:ids,
				docId:docId},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				 //添加成功关闭对话窗口
				 $("#tree_bindsickness").modal("toggle");
			}else{
				alert(data.msg);
			}
		}
	);
};