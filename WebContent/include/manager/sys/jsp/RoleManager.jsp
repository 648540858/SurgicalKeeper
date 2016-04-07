<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>角色维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/sys/css/RoleManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/sys/js/RoleManager.js"></script>

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
		  <button type="button" class="btn btn-success btn-sm" id="btn_bindMenu">
		  	<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp绑定菜单
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
       <th>描述</th>
       <th>创建时间</th>
       <th>状态</th>
     </tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td><input type="checkbox" name="grup" id="{{value.roleId}}"  value="{{value.roleId}}"/></td>
				<td>{{value.roleName}}</td>
				<td>{{value.roleDesc}}</td>
				<td>{{value.roleCtime}}</td>
				<td>{{value.roleStatus}}</td>
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
					 <label for="roleName" class="col-sm-2 control-label text-center">名称</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="roleName" name="roleName"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="roleDesc" class="col-sm-2 control-label text-center">描述</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="roleDesc" name="roleDesc"/>
			         </div>
			     </div>
				<div class="form-group">
					<label for="roleStatus" class="col-sm-2 control-label text-center">状态</label>
					 <div class="col-sm-10">
					    <select id="roleStatus" class= "form-control"></select>
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

<!-- 绑定菜单信息页面 -->
<div class="modal fade" id="modal_bindMenu">
	<div class="modal-dialog" >
		<div class="modal-content">
	        	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle">绑定菜单</h4>
				</div>
					<div class="modal-body" >
						<div id="tree_menu" class="ztree" style="width:260px; overflow:auto;">
						</div>
					</div>
				<div class="modal-footer">
					<div class="allChenck">
						<input type="checkbox" id="check_all_bind"/>全选
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_save_bindMenu">菜单绑定保存</button>
				</div>
		</div>
	</div>
</div>
</body>
</html>