<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>疾病属性维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/basedata/css/SicknessAttr.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/basedata/js/SicknessAttr.js"></script>
<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</head>
<body>
 <div class="center_query " >	
		<div class="center-block btn_all">
			<div class="center_btn_1 clearfix" >
			</div>
			<div class="center_btn_2 clearfix" >
			</div>
		</div>		
	</div>
	
 <div class="center_table"id='center_table' >
	<div class="table_btn clearfix" >
		<div class="btn-1">
			<div class="input-group">
			    <input type="text" class="form-control" placeholder="请输入查询内容" id="input_query">
			    <span class="input-group-btn">
		        <button class="btn btn-success" type="button"id="btn_query">
		        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp查询
		        </button>
		      </span>
		    </div>
		</div>
		<div class="btn-2" >
		   <div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-success btn-sm" id="btn_add">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp添加		  
		  </button>
		  <button type="button" class="btn btn-success btn-sm" id="btn_edit">
		  	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>&nbsp编辑
		  </button>
		  <button type="button" class="btn btn-success btn-sm" id="btn_delete">
		  	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp删除
		  </button>
		</div>
		</div>
	</div>
	<div class="table_show center-block">
	 <table class="table table-hover">
	   <thead>
	     <tr>
	       <th><input type="checkbox" id="topcheck" class="th_checkbox"/></th>
	       <th>名称</th>
	       <th>图标</th>
	       <th>序号</th>
	       <th>创建时间</th>
	       <th>修改时间</th>
	       <th>状态</th>
       </tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="grup{{value.attrId}}"  value="{{value.attrId}}"/></td>
				<td>{{value.attrName}}</td>
				<td>{{value.attrIcon}}</td>
				<td>{{value.attrNo}}</td>
				<td>{{value.cTime}}</td>
				<td>{{value.uTime}}</td>
				<td>{{value.attrStatus}}</td>
			</tr>
		{{/each}}
   </script>		
	 </table>
	</div>
	<div class="fanye_show">
	</div>
 </div>
<!-- 添加/编辑页面 -->
<div class="modal fade" id="modal_addEdit">
	<div class="modal-dialog" >
		<div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
					<h4 class="modal-title" id="modalTitle">疾病属性信息</h4>
			</div>
			<form id="validate_addEdit" class="form-horizontal">
				<div class="modal-body" style="overflow :auto;">
				<div class="form-group">
					 <label for="attrName" class="col-sm-2 control-label text-center">名称</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="attrName" name="attrName"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="attrIcon" class="col-sm-2 control-label text-center">图标</label>
			         <div class="col-sm-10">
			       		<input type="text" class="form-control" id="attrIcon" name="attrIcon"/>
			         </div>
			     </div>
			    <div class="form-group">
			         <label for="attrNo" class="col-sm-2 control-label text-center">序号</label>
			         <div class="col-sm-10">
			         	<input type="number" class="form-control" id="attrNo" name="attrNo"/>
			         </div>
			     </div>
				<div class="form-group">
					<label for="attrStatus" class="col-sm-2 control-label text-center">状态</label>
					<div class="col-sm-10">
					    <select id="attrStatus" class= "form-control"></select>
				    </div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn-save-addEdit">保存</button>
				</div>
			</form>
		</div>
	</div>
	<input type="hidden" value="" id="submit-mode"/>
</div>
</body>
</html>