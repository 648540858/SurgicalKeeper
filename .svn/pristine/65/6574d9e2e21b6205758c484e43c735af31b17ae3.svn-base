<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>医疗科室维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/statistics/css/treatment.css" />
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
	            <select class="input-xlarge valtype myselect shi" style="width:69px;">
	            </select>
	          </div>
			</div>
			<div class="center_btn_2 clearfix">
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
		        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp医生
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
       <th>省</th>
       <th>市</th>
       <th>医院</th>
       <th>医生</th>
       <th>精准预约数</th>
       <th>VIP会诊数</th>
       <th>关注引入量</th>
     </tr>
   </thead>
   <tbody id="content" ></tbody>
	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td>{{value.shengName}}</td>
				<td>{{value.shiName}}</td>
				<td>{{value.hName}}</td>
				<td>{{value.dName}}</td>
				<td><a href="#" onclick="getdetail({{value.dId}},0,{{value.patientNum}})">{{value.patientNum}}</a></td>
				<td><a href="#" onclick="getdetail({{value.dId}},1,{{value.vipNum}})">{{value.vipNum}}</a></td>
				<td>{{value.attention}}</td>
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
<!-- 就诊详情页 -->
	<div class="hide">  
		<div id="detail">
			 <table class="table table-hover">
			 	 <thead>     
				 <tr>
			       <th>就诊人</th>
			       <th>时间</th>
			       <th>金额</th>
			     </tr>
			   </thead>
			   <tbody id="detailContent" ></tbody>
				<script id="detailList" type="text/html">
 					{{each ROOT as value i}}
     					<tr>
						<td>{{value.pName}}</td>
						<td>{{value.sTime}}</td>
						<td>{{value.amount}}</td>
					{{/each}}	
   				</script>
			 </table>
		</div>	
	</div>
	
	

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap-datetimepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/statistics/js/treatment.js"></script>

<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</body>
</html>