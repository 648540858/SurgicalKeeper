$(document).ready(function() {
	
	$("#withdrawFormId").validate({
		rules : {		
			amount : {
				required : true,
				isFloatGtZero : true
			}
		},
		messages : {
			
			amount : {
				required : "提现金额不能为空",
				isFloatGtZero : "请正确填写提现金额"
			}
		}
	});

}); 

function withdraw() {
	if(parseFloat(availableBalance) >= parseFloat($('#amount').val()) && $("#withdrawFormId").valid()){
		var orderNo = '';
		$.post(ajaxRoot + "/wxItf/getOrderNo.freda", {
			orderType : 6,
			appToken : '123'
		}, function(odata) {
			if (odata.success == true) {
				orderNo = odata.data;
				$.post(ajaxRoot + "/wxItf/accountWithdraw.freda", {
					openId : openId,
					amount: $('#amount').val(),
					order_no: orderNo,
					description: '提现申请！',
					appToken : '123'
				}, function(result) {
					if(result.success == true){
						$.post(ajaxRoot + "/wxItf/addCapitalLog.freda", {
							opId : opId,
							type : 6,
							amount : -$('#amount').val(),
							orderNo : orderNo,
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
							availableBalance : -$('#amount').val(),
							appToken : '123'
						}, function(data2) {
							if (data2.success == true) {
								
							} else {
								alert(data2.msg);
							}
						}, "json");
						
						$('#withdrawFormId').attr("action",ajaxRoot + "/wxItf/toAccountWithdrawResult.freda");
						$('#withdrawFormId').submit();
						
					}
				}, "json");
			} else {
				alert(odata.msg);
			}
		}, "json");

	}else{
		if(parseFloat(availableBalance) < parseFloat($('#amount').val())){
			alert('提现金额不能大于账户余额！');
		}
		if(parseFloat($('#amount').val())<1){
			alert('提现金额不能小于1元！');
		}
		if(!$("#withdrawFormId").valid()){
			alert('请正确填写提现金额！');
		}
	}
	
}