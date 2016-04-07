<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link type="text/css" rel="stylesheet"  href="<%=request.getContextPath()%>/include/manager/index/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/index/js/menu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/index/js/index.js"></script>
<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet"/>
<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script type="text/javascript">
	if (top.location != self.location) {
		top.location = self.location;
	}
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_PARAMS_VAR};
</script>
<title>管理后台</title>
</head>
<body>
<div class="top"></div>
<div id="header">
	<div id="platTitle" class="logo"></div>
	<div class="navigation">
		<ul>
		 	<li>欢迎您！</li>
			<li><a href="#">${sessionScope.SESSION_VAR_USER.uName }</a></li>
			<li><a href="#">修改密码</a></li>
			<li><a href="<%=request.getContextPath()%>/manager/loginOut.do">退出</a></li>
		</ul>
	</div>
</div>
<div id="content">
	<div class="left_menu">
	<ul id="nav_dot">
		<script id="listmenu" type="text/html">
			{{each ROOT as value i}}
			<li>
				{{if value.leaf==0}}
				<h4 class="M{{i+1}}"><span></span>{{value.menuName}}</h4>
				<div class="list-item none">
					{{each ROOT as mvalue j}}
					{{if mvalue.pId==value.menuId}}
					<a href='javascript:openTab({{mvalue.menuId}},"{{mvalue.menuName}}","{{mvalue.menuUrl}}");'>{{mvalue.menuName}}</a>
					{{/if}}
					{{/each}}
				</div>
				{{/if}}
			</li>
		{{/each}}
		</script>
  	</ul>
	</div>
	<div class="m-right">
		<ul id="myTab" class="nav nav-tabs">
		   <li class="active">
		      <a href="#home" data-toggle="tab">首页</a>
		   </li>
		</ul>
		<div id="myTabContent" class="tab-content">
		   <div class="tab-pane fade in active" id="home">
		      <div class="center_info">欢迎访问医院后台</div>
		   </div>
		</div>
	</div>
</div>

</body>
</html>