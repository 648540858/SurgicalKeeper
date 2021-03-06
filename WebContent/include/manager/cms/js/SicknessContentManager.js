var editor;
$(function(){
	showTreeArea();
	$(".modal-body").css("height",$(".all_content").height()*0.60);
	editor = new wangEditor('sickAttrcontent');
	editor.config.uploadImgUrl = root + '/resources/wangEditor/jsp/upload_json.jsp';
	editor.config.emotions = {
	        'default': {
	            title: '默认',  // 分组的标题
	            size: 18,  // 表情图标的尺寸
	            imgs: [
	                // 每个表情图标的url地址
	                root+'/resources/wangEditor/static/emotions/default/1.gif',
	                root+'/resources/wangEditor/static/emotions/default/2.gif',
	                root+'/resources/wangEditor/static/emotions/default/3.gif',
	                root+'/resources/wangEditor/static/emotions/default/4.gif',
	                root+'/resources/wangEditor/static/emotions/default/5.gif',
	                root+'/resources/wangEditor/static/emotions/default/6.gif',
	                root+'/resources/wangEditor/static/emotions/default/7.gif',
	                root+'/resources/wangEditor/static/emotions/default/8.gif',
	                root+'/resources/wangEditor/static/emotions/default/9.gif',
	                root+'/resources/wangEditor/static/emotions/default/10.gif',
	                root+'/resources/wangEditor/static/emotions/default/11.gif',
	                root+'/resources/wangEditor/static/emotions/default/12.gif',
	                root+'/resources/wangEditor/static/emotions/default/13.gif',
	                root+'/resources/wangEditor/static/emotions/default/14.gif',
	                root+'/resources/wangEditor/static/emotions/default/15.gif',
	                root+'/resources/wangEditor/static/emotions/default/16.gif',
	                root+'/resources/wangEditor/static/emotions/default/17.gif',
	                root+'/resources/wangEditor/static/emotions/default/18.gif',
	                root+'/resources/wangEditor/static/emotions/default/19.gif',
	                root+'/resources/wangEditor/static/emotions/default/20.gif',
	                root+'/resources/wangEditor/static/emotions/default/21.gif',
	                root+'/resources/wangEditor/static/emotions/default/22.gif',
	                root+'/resources/wangEditor/static/emotions/default/23.gif',
	                root+'/resources/wangEditor/static/emotions/default/24.gif',
	                root+'/resources/wangEditor/static/emotions/default/25.gif',
	                root+'/resources/wangEditor/static/emotions/default/26.gif',
	                root+'/resources/wangEditor/static/emotions/default/27.gif',
	                root+'/resources/wangEditor/static/emotions/default/28.gif',
	                root+'/resources/wangEditor/static/emotions/default/29.gif',
	                root+'/resources/wangEditor/static/emotions/default/30.gif',
	                root+'/resources/wangEditor/static/emotions/default/31.gif',
	                root+'/resources/wangEditor/static/emotions/default/32.gif',
	                root+'/resources/wangEditor/static/emotions/default/33.gif',
	                root+'/resources/wangEditor/static/emotions/default/34.gif',
	                root+'/resources/wangEditor/static/emotions/default/35.gif',
	                root+'/resources/wangEditor/static/emotions/default/36.gif',
	                root+'/resources/wangEditor/static/emotions/default/37.gif',
	                root+'/resources/wangEditor/static/emotions/default/38.gif',
	                root+'/resources/wangEditor/static/emotions/default/39.gif',
	                root+'/resources/wangEditor/static/emotions/default/40.gif',
	                root+'/resources/wangEditor/static/emotions/default/41.gif',
	                root+'/resources/wangEditor/static/emotions/default/42.gif',
	                root+'/resources/wangEditor/static/emotions/default/43.gif',
	                root+'/resources/wangEditor/static/emotions/default/44.gif',
	                root+'/resources/wangEditor/static/emotions/default/45.gif',
	                root+'/resources/wangEditor/static/emotions/default/46.gif',
	                root+'/resources/wangEditor/static/emotions/default/47.gif',
	                root+'/resources/wangEditor/static/emotions/default/48.gif',
	                root+'/resources/wangEditor/static/emotions/default/49.gif',
	                root+'/resources/wangEditor/static/emotions/default/50.gif'
	            ]
	        },
	        'jinxing': {
	            title: '金星',  // 分组的标题
	            size: 50,  // 表情图标的尺寸
	            imgs: [
	                // 每个表情图标的url地址
	                root+'/resources/wangEditor/static/emotions/jinxing/1.gif',
	                root+'/resources/wangEditor/static/emotions/jinxing/2.gif',
	                root+'/resources/wangEditor/static/emotions/jinxing/3.gif'
	            ]
	        }
	    };
    editor.create();
    
	var id_arr=getCodeArray('STATE');
	for(var i=0;i<id_arr.length;i++) {
		$("#sickContentStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	initSickAttrid();
	
	//给添加按钮绑定事件，激活弹窗
	$("#btn_add").click(function(){
		if(sickId==0){
			alert("请选一个疾病");
			return;
		}
		
		$("#submit-mode").val("add");
		$("#modalTitle").text("添加");
		$("#sickAttrcontent").val("");
		editor.$txt.html('<p><br></p>');
		$('#sickContentStatus')[0].selectedIndex = 0;
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
		deleteSicknessContent();
	});
	
	//绑定复选框事件
	$("#topcheck").click(function(){
		checkbox("grup", "topcheck"); 
	});
	
	validateAddEdit();
	
	sickTreeshow();
});

validateAddEdit=function(){
	$("#btn-save-addEdit").click(function(){
		saveAddEdit();
	});
};

//编辑回显
editShow=function(){
	//回显清空内容
	var ids=getCheckId("grup");
	
	if(ids.length==0){
		alert("未选择要编辑的疾病属性！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个疾病属性进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		//显示弹窗
		$("#modal_addEdit").modal("toggle");
		
		sendAjax(root+"/sickAttrContent/getSickContent.do",{
			sickContentId:ids},
			function(data){
				//设置回显的值
				$("#sickAttrcontent").val(data.sickAttrcontent);
				$("#sickAttrid").val(data.sickAttrid);
				$("#sickContentStatus").val(data.sickContentStatus);
				editor.$txt.html(data.sickAttrcontent);
			}
		);
	}
};

//删除确认
deleteSicknessContent=function(){
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

	sendAjax(root+"/sickAttrContent/delSickContent.do",{
		ids:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				querySickContent();
			}else{
				alert(data.msg);
			}
		}
	);
};

//添加与编辑保存按钮事件
saveAddEdit=function(){
	if($("#sickAttrcontent").val().length<1){
		alert("内容不能为空");
		return;
	}
	var type=$("#submit-mode").val();
	if(type=="add"){
			sendAjax(root+"/sickAttrContent/addSickContent.do",{
				sickId:sickId,
				sickAttrcontent:$("#sickAttrcontent").val(),
				sickAttrid:$("#sickAttrid").val(),
				sickContentStatus:$("#sickContentStatus").val()},
				function(data){
					if(data.bflag==1){
						alert(data.msg);
						 //添加成功关闭对话窗口
						 $("#modal_addEdit").modal("toggle");
						 querySickContent();
					}else{
						alert(data.msg);
					}
				}
			);
	}else if(type=="edit"){
		var ids=getCheckId("grup");
		sendAjax(root+"/sickAttrContent/editSickContent.do",{
					sickAttrcontent:$("#sickAttrcontent").val(),
					sickAttrid:$("#sickAttrid").val(),
					sickContentStatus:$("#sickContentStatus").val(),
					sickContentId:ids},
			function(data){
				if(data.bflag==1){
					alert(data.msg);
					 //成功关闭对话窗口
					 $("#modal_addEdit").modal("toggle");
					 querySickContent();
				}else{
					alert(data.msg);
				}
			}
		);
	}
};

var sickId=0;
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
	sendAjax(root+"/commonAdminTree/sickTree.do",{"pId":"c-0","sId":"0"},
		function(data){
			$.fn.zTree.init($("#tree_sick"), setting, [ {
				"id" : "c-0",
				"name" : '疾病总目录',
				"isParent" : true,
				"leaf" : 0,
				"sId" : 0,
				"pId" : "c-0",
				children : data
			} ]);
		}
	);
};
function getUrl(treeId, treeNode) {
	var param = "sId="+treeNode.sId;
	if(treeNode.id.substr(0,1)=="c"){
		param+="&pId=c-0";
	}else{
		param+="&pId=" + treeNode.id;
	}
	return root + "/commonAdminTree/sickTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	if(treeNode.leaf==1){
		sickId=treeNode.id.substr(2);
		
		querySickContent();
	}else{
		sickId=0;
		templateHtml("list",{},"content");
		return;
	}
};
initSickAttrid=function(){
	var url=root+"/commonAdminTree/listSickAttr.do";
	sendAjax(url,{},
			function(data){
				$("#sickAttrid option").remove();
				
				var id_arr=data;
				for(var i=0;i<id_arr.length;i++) {
					$("#sickAttrid").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
				}
			}
		);
};
/**
 * 查询工具方法
 * 查询完成会修改翻页导航的数字
 */
querySickContent=function(){
	var url=root+"/sickAttrContent/listSickContent.do";
	sendAjax(url,{sickId:sickId},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
			}
		);
};