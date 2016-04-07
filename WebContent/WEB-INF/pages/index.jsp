<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>外科管家(北京瑞吉康星)管理后台</title>
<link href="<%=request.getContextPath()%>/resources/pro/css/ext_icon.css" rel="stylesheet"/>
<link href="<%=request.getContextPath()%>/resources/ext/packages/ext-theme-crisp/build/resources/ext-theme-crisp-all.css" rel="stylesheet" />
<script type="text/javascript">
	var root = "<%=request.getContextPath()%>";
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ext/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/ext/packages/ext-locale/build/ext-locale-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/pro/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/plugins/core/Index.js"></script>
</head>
<body>
	<script type="text/javascript">
	if (top.location != self.location) {
		top.location = self.location;
	}
	</script>
	<div id="hello-win" class="x-hidden">
		<div id="hello-tabs">
			<img border="0" width="450" height="60"
				src="<%=request.getAttribute("bannerPath") == null ? request.getContextPath()
							+ "/resources/pro/img/banner_right.png" : request.getAttribute("bannerPath")%>" />
		</div>
	</div>
	<div id="news-tab" class="x-hidden">
		温馨提示..
	</div>
	<div id="tips-tab" class="x-hidden">
		警告..
	</div>
</body>
</html>