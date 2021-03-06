$(function(){
	//页面初始化
	initPage();
	fanye_fun(function(){
		queryWXUser();
	});	
	//绑定查询按钮事件
	$(".btn_query").click(function(){
		$("#pageNum").text('1');
		queryWXUser();
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
			queryWXUser();
		});
		$("#nextPage").click(function(){
			var curpage=Number($("#pageNum").text());
			var total=Number($("#pageTotal").text());
			if(curpage>=total)
				return;
			curpage++;
			$("#pageNum").text(curpage);
			changeTotal(total,curpage);
			queryWXUser();
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
queryWXUser=function(){
	sTime=$(".sTime").val();
	eTime=$(".eTime").val();
	var limit=10;
	var page=$("#page_now").val();
	var start=limit*(page-1);
	var url=root+"/wxUser/listwxUser.do";
	var queryContent=$(".queryContent").val();
	var province=$(".sheng").find("option:selected").text();
	var city=$(".shi").find("option:selected").text();
	 if(sTime>eTime&&eTime!=''&&sTime!=''){
		alert("结束时间需大于开始时间");
	}else{
		sendAjax(url,{
		limit:limit,
		page:page,
		start:start,
		sTime:sTime,
		eTime:eTime,
		province:province,
		city:city,
		name:queryContent},
			function(data){
				templateHtml("list",data,"content");
				$("#allContent").html(data.TOTALCOUNT);
				$("#total").val(data.TOTALCOUNT);
				fanye_chageCss();
		    }
	);
	}
	
};


getcity=function(){	
	sendAjax(root+"/treatment/getCity.do",{
	parentId:parentId,
	status:''},
			function(data){
				if(parentId==null){
					$(".sheng option").remove();
						$(".sheng").append("<option value='NULL'>所有地区</option>");
					for(var i=0;i<data.length;i++) {
					$(".sheng").append("<option value='"+data[i].city_id+"'>"+data[i].text+"</option>");
					}
				}else{
					$(".shi option").remove();
					$(".shi").append("<option value='NULL'>所有地区</option>");
					for(var i=0;i<data.length;i++) {
						$(".shi").append("<option value='"+data[i].city_id+"'>"+data[i].text+"</option>");
					}
				}
		    }
	);
	
};
