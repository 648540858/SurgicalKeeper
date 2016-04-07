$(function(){
	//页面初始化
	initPage();
	//validateAddEdit();
	queryVipOrder();
	$(".modal-body").css("height",$(document.body).height()*0.60);
	fanye_fun(function(){
		queryVipOrder();
	});
	//绑定查询按钮事件
	$("#btn_query").click(function(){
		queryVipOrder();
	});
	
	//给查看按钮绑定事件，激活弹窗
	$("#btn_view").click(function(){
		loadOrder();
		docTreeshow();
	});
	
	$("#prevPage").click(function(){
		var curpage=Number($("#pageNum").text());
		if(curpage<2)
			return;
		curpage--;
		$("#pageNum").text(curpage);
		var total=Number($("#pageTotal").text());
		changeTotal(total,curpage);
		queryVipOrder();
	});
	$("#nextPage").click(function(){
		var curpage=Number($("#pageNum").text());
		var total=Number($("#pageTotal").text());
		if(curpage>=total)
			return;
		curpage++;
		$("#pageNum").text(curpage);
		changeTotal(total,curpage);
		queryVipOrder();
	});
	//审核
	$("#btn-save-ordersp").click(function(){
		spOrder();
	});
	//下单
	$("#btn-xd").click(function(){
		xdOrder();
	});
	//下单2
	$("#btn-xd-edit1").click(function(){
		xdOrder2();
	});
	//下单3
	$("#btn-xd-edit2").click(function(){
		xdOrder3();
	});
	//发送支付消息
	$("#btn-save-sendpay").click(function(){
		sendPayMsg();
	});
	//发送确认
	$("#btn-save-sendconfirm").click(function(){
		sendConfirm();
	});
	//取消订单
	$("#btn-save-orderdel").click(function(){
		cancelOrder();
	});
	//退还定金
	$("#btn-save-orderback").click(function(){
		unRefund();
	});
});
var orderStatus=0;//订单状态
var orderId="0";//订单id
var cId=0;
var docId=0;
var docName="";
//页面初始化
initPage=function(){
	$("#orderStatus option").remove();
	var id_arr=getCodeArray('CONS_STATUS');
	for(var i=0;i<id_arr.length;i++) {
		$("#orderStatus").append("<option value='"+id_arr[i].code+"'>"+id_arr[i].name+"</option>");
	}
	var mydate=new Date();
	var initDate=mydate.getFullYear()+"-";
	if((mydate.getMonth()+1)>9){
		initDate+=(mydate.getMonth()+1)
	}else{
		initDate+="0"+(mydate.getMonth()+1)
	}
	initDate+="-";
	if(mydate.getDate()>9){
		initDate+=mydate.getDate();
	}else{
		initDate+="0"+mydate.getDate();
	}
	if((mydate.getHours()+1)>9){
		initDate+=" "+(mydate.getHours()+1);
	}else{
		initDate+=" 0"+(mydate.getHours()+1);
	}
	if(mydate.getMinutes()>9){
		initDate+=":"+mydate.getMinutes();
	}else{
		initDate+=":0"+mydate.getMinutes();
	}
	//添加日期选择
     $("#schTimeForEdit").datetimepicker({
        language:  'zh-CN',
        format: "yyyy-mm-dd hh:ii:ss",
        startDate:new Date().DateAdd('h',1).Format("yyyy-MM-dd hh:mm"),
        minView:0,
        maxView:4,
        showMeridian: true,
       	todayHighlight:true,
        autoclose: true,
        todayBtn: true,
        minuteStep:10
    });
};
//加载订单
loadOrder=function(){
	orderStatus=0;//订单状态
	orderId="0";//订单id
	cId=0;
	docId=0;
	docName="";
	$("#schDocIdForEdit").val("");
	$("#schDocForEdit").val("");
	$("#schAddrForEdit").val("");
	$("#schDescForEdit").val("");
	$("#schTimeForEdit").val("");
	//回显清空内容
	var ids=getCheckId("grup");
	
	if(ids.length==0){
		alert("未选择要编辑的属性！！！");
	}else if(ids.indexOf(",")>0){
		alert("请选择单个进行编辑");
	}else if($.isNumeric(Number(ids)) ){
		sendAjax(root+"/viporder/loadOrder.do",{
			vipId:ids},
			function(data){
				//设置回显的值
				$("#cSickStr").html(data.cSickStr);
				$("#cSickDesc").html(data.cSickDesc);
				
				$('#schDocName').html(data.schDocName);
				$('#schAddr').html(data.schAddr);
				$('#schTime').html(data.schTime);
				$('#schDesc').html(data.schDesc);
				
				$('#oNum').html(data.oNum);
				$('#oIsNeedPay').html(getCodeText('CONS_NEED_PAY',data.oIsNeedPay));
				$('#oNeedPayAmount').html(data.oNeedPayAmount);
				$('#oPayMode').html(getCodeText('CONS_PAY_MODE',data.oPayMode));
				$('#oNeedPayTotal').html(data.oNeedPayTotal);
				$('#oPayTotal').html(data.oPayTotal);
				$('#oStatus').html(getCodeText('CONS_STATUS',data.oStatus));
				orderStatus=data.oStatus;
				orderId=data.oId;
				cId=data.vipId;
				$("#modal_addEdit").modal("toggle");
			}
		);
	}
};

queryVipOrder=function(){
	var limit=10;
	var page=$("#page_now").val();
	var start=limit*(page-1);
	var url=root+"/viporder/listVipOrder.do";
	sendAjax(url,{
		limit:limit,
		page:page,
		start:start,
		orderStatus:$("#orderStatus").val()},
			function(data){
				templateHtml("list",data,"content");
				table_checked();
				$("#total").val(data.TOTALCOUNT);
				$("#allContent").html(data.TOTALCOUNT);
				fanye_chageCss();
				
		    }
	);
};

spOrder=function(){
	var ids=getCheckId("grup");
	sendAjax(root+"/viporder/verifyVip.do",{
			cStatus:1,
			cId:ids},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

xdOrder=function(){
	if(orderStatus!=1){
		alert("当前不能下单");
		return;
	}
	if($("#schDocForEdit").val().length<1){
		alert("医生没有选择");
		return;
	}
	if($("#needPayTotal").val().length<1){
		alert("费用没有输入");
		return;
	}
	if($("#schTimeForEdit").val().length<1){
		alert("预约时间没有输入");
		return;
	}
	if($("#schAddrForEdit").val().length<1){
		alert("预约地址没有输入");
		return;
	}
	var ids=getCheckId("grup");
	sendAjax(root+"/viporder/setSch.do",{
			schTypeForEdit:1,
			oId:orderId,
			cId:cId,
			oStatus:1,
			needPayTotal:$("#needPayTotal").val(),
			schDocIdForEdit:$("#schDocIdForEdit").val(),
			schDocForEdit:$("#schDocForEdit").val(),
			schAddrForEdit:$("#schAddrForEdit").val(),
			schDescForEdit:$("#schDescForEdit").val(),
			schTimeForEdit:$("#schTimeForEdit").val()},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

sendPayMsg=function(){
	sendAjax(root+"/viporder/sendPay.do",{
			oId:orderId},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

xdOrder2=function(){
	if(orderStatus!=3){
		alert("当前不能修改");
		return;
	}
	if($("#schDocForEdit").val().length<1){
		alert("医生没有选择");
		return;
	}
	if($("#needPayTotal").val().length<1){
		alert("费用没有输入");
		return;
	}
	if($("#schTimeForEdit").val().length<1){
		alert("预约时间没有输入");
		return;
	}
	if($("#schAddrForEdit").val().length<1){
		alert("预约地址没有输入");
		return;
	}
	var ids=getCheckId("grup");
	sendAjax(root+"/viporder/setSch.do",{
			schTypeForEdit:2,
			oId:orderId,
			cId:cId,
			oStatus:1,
			schDocIdForEdit:$("#schDocIdForEdit").val(),
			schDocForEdit:$("#schDocForEdit").val(),
			schAddrForEdit:$("#schAddrForEdit").val(),
			schDescForEdit:$("#schDescForEdit").val(),
			schTimeForEdit:$("#schTimeForEdit").val()},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

xdOrder3=function(){
	if(orderStatus!=3){
		alert("当前不能修改");
		return;
	}
	if($("#schDocForEdit").val().length<1){
		alert("医生没有选择");
		return;
	}
	if($("#needPayTotal").val().length<1){
		alert("费用没有输入");
		return;
	}
	if($("#schTimeForEdit").val().length<1){
		alert("预约时间没有输入");
		return;
	}
	if($("#schAddrForEdit").val().length<1){
		alert("预约地址没有输入");
		return;
	}
	var ids=getCheckId("grup");
	sendAjax(root+"/viporder/setSch.do",{
			schTypeForEdit:3,
			oId:orderId,
			cId:cId,
			oStatus:1,
			schDocIdForEdit:$("#schDocIdForEdit").val(),
			schDocForEdit:$("#schDocForEdit").val(),
			schAddrForEdit:$("#schAddrForEdit").val(),
			schDescForEdit:$("#schDescForEdit").val(),
			schTimeForEdit:$("#schTimeForEdit").val()},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

sendConfirm=function(){
	if(orderStatus!=3){
		alert("当前不能操作");
		return;
	}
	sendAjax(root+"/viporder/sendConfirm.do",{
			oId:orderId,
			cId:cId,
			oStatus:orderStatus},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

cancelOrder=function(){
	if(orderStatus==5){
		alert("当前不能操作");
		return;
	}
	sendAjax(root+"/viporder/abrogateVipOrder.do",{
			oId:orderId,
			cId:cId,
			oStatus:orderStatus},
		function(data){
			if(data.bflag==1){
				alert(data.msg);
				loadOrder();
			}else{
				alert(data.msg);
			}
		}
	);
};

unRefund=function(){
	if(orderStatus!=3){
		alert("当前不能操作");
		return;
	}
	sendAjax(root+"/viporder/getRefundInfo.do",{
			oId:orderId,
			cId:cId,
			payMode:1,
			oStatus:orderStatus},
		function(data){
			if(data.payType==1){
				sendAjax(root+"/viporder/addRefundToBalance.do",{
					mainUserId:data.mainUserId,
					oPayAmount:data.amount,
					oId:orderId,
					cId:cId,
					oStatus:orderStatus},
					function(data){
						if(data.bflag==1){
							alert(data.msg);
							loadOrder();
						}else{
							alert(data.msg);
						}
					}
				);
			}
			if(data.payType==2){
				sendAjax(root+"/pingpp/refundOrder.do",{
					ch_id:data.outOrderNum,
					oPayAmount:data.amount,
					amount:data.amount,
					description:'因平台原因.退还用户定金(VIP)'},
					function(data){
						sendAjax(root+"/viporder/abrogateVipOrder.do",{
							oId:orderId,
							cId:cId,
							refund:1,
							oStatus:orderStatus},
							function(data){
								if(data.bflag==1){
									alert(data.msg);
									loadOrder();
								}else{
									alert(data.msg);
								}
							}
						);
					}
				);
			}
		}
	);
};

docTreeshow=function(){
	if(orderStatus>3){
		return;
	}
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
	
	sendAjax(root+"/commonAdminTree/doctorTree.do",{pId:'c-1'},
		function(data){
			$.fn.zTree.init($("#tree_doctor"), setting, [ {
				"id" : "1",
				"name" : '北京瑞吉康星',
				"isParent" : true,
				"leaf" : 0,
				children : data
			} ]);
		}
	);
}
function getUrl(treeId, treeNode) {
	var param = "pId=" + treeNode.id;
	return root + "/commonAdminTree/doctorTree.do?" + param;
}

treeClick=function(e,treeId,treeNode,clickFlag){
	if(treeNode.leaf==1){
		docId=treeNode.id.substr(2);
		docName=treeNode.name;
		$("#schDocForEdit").val(docName);
		$("#schDocIdForEdit").val(docId);
	}else{
		docId=0;
		docName="";
	}
}