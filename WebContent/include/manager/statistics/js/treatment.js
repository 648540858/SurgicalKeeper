$(function(){
	//页面初始化
	initPage();
	fanye_fun(function(){
		queryTreatment();
	});
	//绑定查询按钮事件
	$(".btn_query").click(function(){
		queryTreatment();
	});
	
	//省市二级联动
	$(".sheng").change(function(){
		parentId=$(".sheng").val();
		getcity();
	});
	//翻页
	$("#prevPage").click(function(){
			var curpage=Number($("#pageNum").text());
			if(curpage<2)
				return;
			curpage--;
			$("#pageNum").text(curpage);
			var total=Number($("#pageTotal").text());
			changeTotal(total,curpage);
			queryTreatment();
		});
		$("#nextPage").click(function(){
			var curpage=Number($("#pageNum").text());
			var total=Number($("#pageTotal").text());
			if(curpage>=total)
				return;
			curpage++;
			$("#pageNum").text(curpage);
			changeTotal(total,curpage);
			queryTreatment();
		});
	
});
var parentId=null;//父节点

var sTime=null;
var eTime=null;
//页面初始化
initPage=function(){
	//添加状态下来列表
	getcity();
	
    //给开始时间添加日期选择
    $(".sTime").datetimepicker({
       language:  'zh-CN',
       format: "yyyy-mm-dd",
       startDate:'2016-01-01',
       startView:2,//默认打开显示月
       minView:2,//默认显示到天
       todayBtn: true,
       showMeridian: true,
       todayHighlight:true,
       autoclose: true
   });
   
    //给结束时间添加日期选择
    $(".eTime").datetimepicker({
       language:  'zh-CN',
       format: "yyyy-mm-dd",
       startDate:'2016-01-01',
       startView:2,//默认打开显示月
       minView:2,//默认显示到天
       todayBtn: true,
       showMeridian: true,
       todayHighlight:true,
       autoclose: true
   });
};


//列表查询
queryTreatment=function(){
	sTime=$(".sTime").val();
	eTime=$(".eTime").val();
	var limit=10;
	var page=$("#page_now").val();
	var start=limit*(page-1);
	var url=root+"/treatment/listTreatment.do";
	var queryContent=$(".queryContent").val();
	if($(".shi").val()==null){
		var aId=$(".sheng").val();
	}else{
		var aId=$(".shi").val();
	}
	if(aId==null){
		alert("请选择查询区域");
	}else if(sTime>eTime&&eTime!=''&&sTime!=''){
		alert("结束时间需大于开始时间");
	}else{
		sendAjax(url,{
		limit:limit,
		page:page,
		start:start,
		sTime:sTime,
		eTime:eTime,
		aId:aId,
		queryContent:queryContent},
			function(data){
				templateHtml("list",data,"content");
				$("#allContent").html(data.TOTALCOUNT);
				table_checked();
				$("#total").val(data.TOTALCOUNT);
				fanye_chageCss();
		    }
	);
	}
	
	
	
};




//详情弹窗
getdetail=function(dId,type,count){
		var limit=4;
		var page=$("#page_now").val();
		var start=limit*(page-1);
		if(count!=0){
			sendAjax(root+"/treatment/listTreatmentDetail.do",{
				limit:limit,
				page:page,
				start:start,
				count:count,
				sTime:sTime,
				eTime:eTime,
				dId:dId,
				type:type},
					function(data){
						templateHtml("detailList",data,"detailContent");
						$("#detail").dialog({
					         title       : "预约明细",
					         backdrop: "static",
					         buttons: {
					        	 "关闭": function() {
					                 $(this).dialog("close");}
					          }
		      			});
				    }
			);
			
		}
	
};

getcity=function(){	
	sendAjax(root+"/treatment/getCity.do",{
	parentId:parentId,
	status:1},
			function(data){
				if(parentId==null){
					$(".sheng option").remove();
					for(var i=0;i<data.length;i++) {
					$(".sheng").append("<option value='"+data[i].city_id+"'>"+data[i].text+"</option>");
					}
				}else{
					$(".shi option").remove();
					for(var i=0;i<data.length;i++) {
						$(".shi").append("<option value='"+data[i].city_id+"'>"+data[i].text+"</option>");
					}
				}
		    }
	);
	
};