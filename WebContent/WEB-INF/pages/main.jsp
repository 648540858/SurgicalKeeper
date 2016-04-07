<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>医愿-管理后台</title>
<script type="text/javascript">
	var codeJson = ${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
	var root = "<%=request.getContextPath()%>";
	var paramJson = ${applicationScope.APPLICATION_SYSTEM_PARAMS_VAR};
</script>
<link href="<%=request.getContextPath()%>/resources/ext/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/ext/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/ext/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/pro/js/app_define_class.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/pro/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/include/plugins/core/Main.js"></script>
</head>
<body>
	<div id="top">
		<img border="0" width="100%" height="68" src="<%=request.getContextPath() %>/resources/pro/img/main_top.jpg">
		<div id="platTitle" style="position: absolute; left : 5%; bottom: 20%; font-size: 30px; color: white;"></div>
		<div style="position: absolute; right : 15px; bottom: 50%;" id="timeDiv"></div>
		<div style="position: absolute; right : 15px; bottom: 10%;">
			当前用户:<a href="javascript:void(0)" onclick="changePwdWinInit()">${sessionScope.SESSION_VAR_USER.uName}</a>.&nbsp;&nbsp;&nbsp;
			<a href="javascript:void(0)" onclick="changePwdWinInit()">修改密码</a>&nbsp;&nbsp;||&nbsp;
			<a href="javascript:void(0)" onclick="logout()">退出</a></div>
	</div>
	<div id="south" align="right">
	</div>
	<script type="text/javascript">
	var now = new Date();
	function CurentTime(){ 
		var mm = now.getMinutes(); 
		var ss = now.getTime() % 60000;ss = (ss - (ss % 1000)) / 1000; 
		var clock = now.getHours() +':'; 
		if (mm < 10) clock += '0';
		clock += mm+':'; 
		if (ss < 10) clock += '0'; 
		return(clock + ss);
	}
	setInterval(function(){
		document.getElementById("timeDiv").innerHTML = now.getFullYear() + "-"+(now.getMonth()+1)+"-"+now.getDate()+" "+ CurentTime();now.setSeconds(now.getSeconds()+1);
	},1000);
	var getCodeText = function(_var){
		var codeText = "";
		for(var i=0;i<paramJson.length;i++){
			if(paramJson[i].pKey == _var){
				codeText = paramJson[i].pValue;
				break;
			}
		}
		return codeText;
	}
	document.getElementById("platTitle").innerHTML=getCodeText("SYS_TITLE");
	document.getElementById("south").innerHTML=getCodeText("SYS_COMPANY_INFO");
	</script>
</body>
</html>