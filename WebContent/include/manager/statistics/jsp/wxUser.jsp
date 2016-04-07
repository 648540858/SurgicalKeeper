<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>微信用户信息统计</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/statistics/css/wxUser.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
</head>
<body>
<div class="center_query " >	
		<div class="center-block btn_all">
			<div class="center_btn_1 clearfix" >
	          <div class="controls">
			 	<label class="control-label">省：&nbsp</label>
	            <select class="input-xlarge valtype myselect sheng" >
	            </select>
	            <label class="control-label">市：&nbsp</label>
	            <select class="input-xlarge valtype myselect shi" >
	            	<option value='NULL'>所有地区</option>
	            </select>
	          </div>
			</div>
			<div class="center_btn_2 clearfix" >
				<div class="input-group">
			      <span class="input-group-btn">
			        <button class="btn btn-success " type="button">
			        <span class="glyphicon glyphicon-time" aria-hidden="true"></span>&nbsp开始时间
			        </button>
			      </span>
			      <input type="text" class="form-control sTime" readonly >
			      <span class="input-group-btn">
			        <button class="btn btn-success " type="button">
			        <span class="glyphicon glyphicon-time" aria-hidden="true"></span>&nbsp结束时间
			        </button>
			      </span>
			      <input type="text" class="form-control eTime" readonly>
		    </div>
			</div>
		</div>		
	</div>
	
<div class="center_table"id='center_table' >
	<div class="table_btn clearfix" >
		<div class="btn-1">
			<div class="input-group">
		      <span class="input-group-btn">
		        <button class="btn btn-success " type="button"id="btn_query">
		        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp用户
		        </button>
		      </span>
		      <input type="text" class="form-control queryContent " >
		    </div>
		</div>
		<div class="btn-2" >
		  <div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-success  btn_query">
		  	<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>&nbsp统计
		  </button>
		</div>
		</div>
	</div>
	
	<div class="table_show center-block ">
	 <table class="table table-hover ">
	   <thead>
     <tr>
       <th>用户名</th>
       <th>性别</th>
       <th>省级地区</th>
       <th>市级地区</th>
       <th>推荐医生</th>
       <th>医生所属</th>
       <th>关注时间</th>
     </tr>
   </thead>
   <tbody id="content" ></tbody>
	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td>{{value.name}}</td>
				<td>{{value.sex}}</td>
				<td>{{value.province}}</td>
				<td>{{value.city}}</td>
				<td>{{value.doc}}</td>
				<td>{{value.hName}}</td>
				<td>{{value.cTime}}</td>
			</tr>
		{{/each}}
   </script>
					
	 </table>
	</div>
	<div class="fanye_show">
		<div class="into_show">
			<b>记录总条数:</b><font id="allContent">0</font>
		</div>
		<ul class="pagination pagination-sm">
		  <li id="firstPag" class=""><a href="#">&laquo;&laquo;</a></li>
		  <li id="lastPag"class=""><a href="#">&laquo;上一页</a></li>
		  <li id="Pag_1" class="" value="1"><a href="#">1</a></li>
		  <li id="Pag_2" class="" value="2"><a href="#" >2</a></li>
		  <li id="Pag_3" class="" value="3"><a href="#" >3</a></li>
		  <li id="nextPag" class=""><a href="#">下一页&raquo;</a></li>
		  <li  id="finalPag" class=""><a href="#">&raquo;&raquo;</a></li>
		</ul>
		<input type="hidden" id="page_now" value="1"/> 
		<input type="hidden" id="total" value="1"/> 
	</div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/statistics/js/wxUser.js"></script>

<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</body>
</html>