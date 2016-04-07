var mainHeight
$(function(){
	$("#platTitle").html(getCodeText("SYS_TITLE"));
	//getMenuItem
	getMenuItem();
	mainHeight = ($(window).height()-60);
	$(document.body).height(mainHeight);
	document.body.scrollTop;
	$(document).scrollTop();
	
	$('.main-left,.main-right').height(mainHeight);
	$(".nav-tabs").on("click", "[tabclose]", function(e) {
	  id = $(this).attr("tabclose");
	  closeTab(id);
	});
	$('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
	  console.log($(e.target));
	});
	var tabHeight=$("#myTab").height();
	$("#myTabContent").height(mainHeight-tabHeight);
});

var addTab = function(obj) {
    var id = "tab_" + obj.id;
    $(".active").removeClass("active");
    //如果TAB不存在，创建一个新的TAB
    if (!$("#" + id)[0]) {
        //固定TAB中IFRAME高度
        mainHeight=mainHeight - 5;
        //创建新TAB的title
        title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + obj.title;
        //是否允许关闭
        if (obj.close) {
            title += ' <i class="tabclose" tabclose="' + id + '"><span class="glyphicon glyphicon-remove"></span></i>';
        }
        title += '</a></li>';
        //是否指定TAB内容
        if (obj.content) {
            content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + obj.content + '</div>';
        } else { //没有内容，使用IFRAME打开链接
            content = '<div role="tabpanel" height="100%" width="100%" class="tab-pane" id="' + id + '"><iframe id="iframe" src="' + obj.url + '" width="100%" height="' + mainHeight +
                '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="auto" allowtransparency="yes"></iframe></div>';
        }
        //加入TABS
        $(".nav-tabs").append(title);
        $(".tab-content").append(content);
    }
    //激活TAB
    $("#tab_" + id).addClass('active');
    $("#" + id).addClass("active");
};

var closeTab = function(id) {
    //如果关闭的是当前激活的TAB，激活他的前一个TAB
    if ($("li.active").attr('id') == "tab_" + id) {
        $("#tab_" + id).prev().addClass('active');
        $("#" + id).prev().addClass('active');
    }
    //关闭TAB
    $("#tab_" + id).remove();
    $("#" + id).remove();
};

var openTab=function(tabindex,title,url) {
	if(url.length>10){
		if(url.substr(0,4).toLowerCase()!='http'){
			url=root+url;
		}
		addTab({
			id: 'alex'+tabindex,
			title: title,
			close: true,
			url: url
		});
	}else{
		addTab({
			id: 'alex'+tabindex,
			title: title,
			close: true,
			content: '正在开发维护中...'
		});
	}
};

var getCodeText = function(_var){
	var codeText = "";
	for(var i=0;i<codeArray.length;i++){
		if(codeArray[i].pKey == _var){
			codeText = codeArray[i].pValue;
			break;
		}
	}
	return codeText;
}

getMenuItem=function(){
	var url=root+"/manager/getMenuItem.do";
	sendAjax(url,{},
		function(data){
			templateHtml("listmenu",data,"nav_dot");
			navList(5);
	    }
	);
}