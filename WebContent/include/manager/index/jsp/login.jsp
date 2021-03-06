<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>医愿-登录</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/index/css/login.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/index/js/login.js"></script>
<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	if (top.location != self.location) {
		top.location = self.location;
	}
</script>
</head>
<body>
<div class="box">
		<div class="login-box">
			<div class="login-title text-center">
				<h1><small>登录</small></h1>
			</div>
			<div class="login-content ">
			<div class="form">
			<form  id="loginForm">
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
					  		<span class="input-group-addon" style="font-weight: normal;"><span class="glyphicon glyphicon-user"></span></span>
							<input id="account" name="account" class="form-control" placeholder="账户" value="admin" style="width: 300px"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-xs-12  ">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-lock" ></span></span>
							<input type="password" id="pwd" name="pwd" class="form-control" placeholder="密码" value="111111" style="width: 300px"/>
						</div>
					</div>
				</div>
				<div >
					<div class="col-xs-4 col-xs-offset-3 ">
						<span id="login" class="btn btn-info"><span class="glyphicon glyphicon-off"></span> 登录</span>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>

</body>

</html>