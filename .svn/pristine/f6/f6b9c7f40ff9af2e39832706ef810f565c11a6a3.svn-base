$(document).ready(function(){
	var attrIdList = attrIdStr.split(",");
	var attrNameList = attrNameStr.split(",");
	var attrIconList = attrIconStr.split(",");

	initAttrHtml(attrIdList,attrNameList,attrIconList);
});

$(document).ready(function(){
	 $.ajax({type:'POST',
        url:ajaxRoot +'/wxItf/getOrderStatusCountByOpenId.freda',
		 data:{"openId":$('#openId').val(),"appToken":'123'},
		 dataType: "json",
		beforeSend:function(){
		  
		},
	   success:function(data){ 
		if(data.success == true){
			var totalCount = 0;
			for (var i = 0; i < data.data.length; i++) { 
				 if(data.data[i].orderStatus == 1){
					 totalCount +=data.data[i].orderCount;
				 }
				 if(data.data[i].orderStatus == 2){
					 totalCount +=data.data[i].orderCount;
				 }
				 if(data.data[i].orderStatus == 3){
					 totalCount +=data.data[i].orderCount;
				 }
	        }
			$('.right_yuan').text(totalCount);
		}else{
			alert("数据有误~"); 
		}
        
    }
});
	
});

function initAttrHtml(attrIds,attrNames,attrIcons){
	var divHtml = "";
	for(var i=0;i<attrIds.length;i++){
		if(i == 0)
		{
			divHtml += "<div class=\"swiper-slide swiper_width  hover_icon\" onclick=\"aClick('"+attrIds[i]+"')\" id='aName_"+attrIds[i]+"'>" +
						"<div class=\"flex_box\">" +
							"<div class=\"iconbox\">" +
								"<img src=\""+ root + attrIcons[i] +"\">" +
							"</div>" +
							"<p class=\"p_text\">" + attrNames[i] +"</p>" +
						"</div>" +
					"</div>";
		}
		else
		{
			divHtml += "<div class=\"swiper-slide swiper_width\" onclick=\"aClick('"+attrIds[i]+"')\" id='aName_"+attrIds[i]+"'>" +
						"<div class=\"flex_box\">" +
							"<div class=\"iconbox\">" +
								"<img src=\""+ root + attrIcons[i] +"\">" +
							"</div>" +
							"<p class=\"p_text\">" + attrNames[i] +"</p>" +
						"</div>" +
					"</div>";
		}
	}
	$('#attDiv').html(divHtml);
}

function sClick(sId){
	
	$.post( ajaxRoot + "/wxItf/getCareInfo1.freda", {sId : sId , appToken : '123'},
	   function(data){
	    var attrIdList = data.data.attrIdStr.split(",");
		var attrNameList = data.data.attrNameStr.split(",");
		var attrIconList = data.data.attrIconStr.split(",");
		ssId = sId;
		initAttrHtml(attrIdList,attrNameList,attrIconList);
		setText(data.data.saText);
		$('.pp').removeClass('libg');
		$('#sName_' + sId).addClass("libg");
	   }, "json");
}

function aClick(aId){
	$.post( ajaxRoot + "/wxItf/getCareInfo2.freda", {aId : aId ,sId : ssId, appToken : '123'},
	   function(data){
	   	$('.swiper-slide').removeClass('hover_icon');
		$('#aName_' + aId).addClass("hover_icon");
		setText(data.data.saText);
	   }, "json");
}

function setText(text){
	$('#textDiv').html(text);
}

function toMyCenter(){
	self.location.href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx21449a3c51607b9f&redirect_uri=http://test.waikegj.com/SurgicalKeeper/wxItf/myCenterPageInit.freda?appToken=123&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
}