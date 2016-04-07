<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>数据字典维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/dict/css/CodeManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/dict/js/CodeManager.js"></script>
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
			  <button type="button" class="btn btn-success btn-sm" id="btn_sync">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>&nbsp同步到内存		  
			  </button>
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
	       <th>集合名称</th>
	       <th>变量名</th>
	       <th>值名称</th>
	       <th>值</th>
	       <th>描述</th>
	       <th>序号</th>
	       <th>类型</th>
	       <th>状态</th>
    	</tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="grup{{value.cId}}"  value="{{value.cId}}"/></td>
				<td>{{value.cList}}</td>
				<td>{{value.cVar}}</td>
				<td>{{value.cText}}</td>
				<td>{{value.cCode}}</td>
				<td>{{value.cDesc}}</td>
				<td>{{value.cSortNo}}</td>
				<td>{{value.cType}}</td>
				<td>{{value.cState}}</td>
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
				<h4 class="modal-title" id="modalTitle">添加/编辑</h4>
			</div>
			<form id="validate_addEdit" class="form-horizontal">
				<div class="modal-body" >
				<div class="form-group">
					 <label for="cList" class="col-sm-2 control-label text-center">集合名称</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="cList" name="cList"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="cVar" class="col-sm-2 control-label text-center">变量名</label>
			         <div class="col-sm-10">
			       		<input type="text" class="form-control" id="cVar" name="cVar"/>
			         </div>
			     </div>
			    <div class="form-group">
			         <label for="cText" class="col-sm-2 control-label text-center">值名称</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="cText" name="cText"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="cText" class="col-sm-2 control-label text-center">值</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="cCode" name="cCode"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="cDesc" class="col-sm-2 control-label text-center">描述</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="cDesc" name="cDesc"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="cSortNo" class="col-sm-2 control-label text-center">序号</label>
			         <div class="col-sm-10">
			         	<input type="number" class="form-control" id="cSortNo" name="cSortNo"/>
			         </div>
			     </div>
				<div class="form-group">
					<label for="cState" class="col-sm-2 control-label text-center">状态</label>
					<div class="col-sm-10">
					    <select id="cState" class= "form-control"></select>
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