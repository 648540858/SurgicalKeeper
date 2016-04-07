<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>全局参数维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/dict/css/ParamManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/dict/js/ParamManager.js"></script>
<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
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
			  <button type="button" class="btn btn-success btn-sm" id="btn_wx">
				<span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>&nbsp创建微信菜单		  
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
	       <th>变量名</th>
	       <th>变量值</th>
	       <th>描述</th>
    	</tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="grup{{value.pId}}"  value="{{value.pId}}"/></td>
				<td>{{value.pKey}}</td>
				<td>{{value.pValue}}</td>
				<td>{{value.pDesc}}</td>
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
				         <label for="pKey" class="col-sm-2 control-label text-center">变量名</label>
				         <div class="col-sm-10">
				       		<input type="text" class="form-control" id="pKey" name="pKey"/>
				         </div>
				     </div>
				    <div class="form-group">
				         <label for="pValue" class="col-sm-2 control-label text-center">变量值</label>
				         <div class="col-sm-10">
				         	<input type="text" class="form-control" id="pValue" name="pValue"/>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="pDesc" class="col-sm-2 control-label text-center">描述</label>
				         <div class="col-sm-10">
				         	<input type="text" class="form-control" id="pDesc" name="pDesc"/>
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