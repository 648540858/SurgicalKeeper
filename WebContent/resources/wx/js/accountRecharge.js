$(document).ready(function() {
	
	$("#rechargeFormId").validate({
		rules : {		
			rechargeFunds : {
				required : true,
				isFloatGtZero : true
			}
		},
		messages : {
			
			rechargeFunds : {
				required : "提现金额不能为空",
				isFloatGtZero : "请正确填写提现金额"
			}
		}
	});

}); 
function wxRecharge(){
	var order_no = '';
	var channel = 'wx_pub';
	if($("#rechargeFormId").valid()){
		$.post(ajaxRoot + "/wxItf/getOrderNo.freda", {
			orderType : 1,
			appToken : '123'
		}, function(odata) {
			if (odata.success == true) {
				order_no = odata.data;
				$.get(ajaxRoot + '/pingpp/getCharge.freda', {
					order_no : order_no,
					amount : $('#rechargeFunds').val(),
					channel : channel,
					subject : subject,
					body : body,
					open_id : open_id,
					appToken : '123'
				}, function(data) {
					//alert(data);
					pingpp.createPayment(data, function(result, error) {
						if (result == "success") {
							$.post(ajaxRoot + "/wxItf/addCapitalLog.freda", {
								opId : opId,
								type : 1,
								amount : $('#rechargeFunds').val(),
								orderNo : order_no,
								appToken : '123'
							}, function(data1) {
								if (data1.success == true) {
									
								} else {
									alert(data1.msg);
								}
							}, "json");
							$.post(ajaxRoot + "/wxItf/editePatient.freda", {
								patientId : opId,
								type : 1,
								availableBalance : $('#rechargeFunds').val(),
								appToken : '123'
							}, function(data2) {
								if (data2.success == true) {
									
								} else {
									alert(data2.msg);
								}
							}, "json");
							alert('充值成功！');
							self.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx21449a3c51607b9f&redirect_uri=http://test.waikegj.com/SurgicalKeeper/wxItf/myCenterPageInit.freda?appToken=123&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
							//$('#rechargeFormId').attr("action","${ctx}/wxItf/myCenterPageInit.freda");
							//$('#rechargeFormId').submit();
						} else if (result == "fail") {
							alert('fail');
						} else if (result == "cancel") {
							alert('cancel');
						}
					});
				}, 'json');
			} else {
				alert(data3.msg);
			}
		}, "json");
		
		
	}else{
		alert('请正确填写提现金额');
	}

	}