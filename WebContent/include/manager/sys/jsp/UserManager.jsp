<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/sys/css/UserManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/sys/js/UserManager.js"></script>
<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</head>
<body>
<div class="all_content">
	<div class="center_query " >	
			<div class="center-block btn_all">
				<div class="center_btn_1 clearfix" >
					 <button type="button" class="btn btn-success btn-sm" id="btn_tree">
						 <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>&nbsp打开关闭树		  
					  </button>
				</div>
				<div class="center_btn_2 clearfix" >
				</div>
			</div>		
		</div>
		<!-- 表格区 -->
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
		  <button type="button" class="btn btn-success btn-sm" id="btn_bindRole">
		  	<span class="glyphicon glyphicon-lock" aria-hidden="true"></span>&nbsp绑定角色
		  </button>
		</div>
		</div>
		
	</div>
	<div class="table_show center-block">
		 <table class="table table-hover ">
		   <thead>
		     <tr>
		       <th><input type="checkbox" id="topcheck" name="topcheck" /></th>
		       <th>姓名</th>
		       <th>性别</th>
		       <th>帐号</th>
		       <th>联系电话</th>
		       <th>锁定</th>
		       <th>描述</th>
		       <th>状态</th>
		     </tr>
	  	 </thead>
	   <tbody id="content" ></tbody>
		<script id="list" type="text/html">
 			{{each ROOT as value i}}
     			<tr>
					<td><input type="checkbox" name="grup" id="grup{{value.uId}}" align="middle" value="{{value.uId}}"/></td>
					<td>{{value.uName}}</td>
					<td>{{value.uSex}}</td>
					<td>{{value.uAccount}}</td>
					<td>{{value.uTel}}</td>
					<td>{{value.uLocked}}</td>
					<td>{{value.uDesc}}</td>
					<td>{{value.uStatus}}</td>
     			</tr>
			{{/each}}
  		 </script>
		</table>
	</div>
		<div class="fanye_show">
			<div class="into_show">
				
			</div>
		</div>
	</div>
</div> 
<div class="mytree">
	<div class="istree">
		<div id="tree_dept" class="ztree" style="width:100%; overflow:auto;"></div>
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
			<div class="modal-body" style="overflow :auto;">
				<div class="form-group">
					 <label for="uName" class="col-sm-2 control-label text-center">姓名</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="uName" name="uName"/>
			         </div>
			     </div>
			     <div class= "form-group ">
					<label for="uSex" class="col-sm-2 control-label text-center">性别</label>
					<div class="col-sm-10">
				    	<select id="uSex" class= "form-control"></select>
				    </div>
				</div>
			     <div class="form-group">
			         <label for="uAccount" class="col-sm-2 control-label text-center">帐号</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="uAccount" name="uAccount"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="uPwd" class="col-sm-2 control-label text-center">密码</label>
			         <div class="col-sm-10">
			         	<input type="password" class="form-control" id="uPwd" name="uPwd"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="uTel" class="col-sm-2 control-label text-center">联系电话</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="uTel" name="uTel"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="uDesc" class="col-sm-2 control-label text-center">描述</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="uDesc" name="uDesc"/>
			         </div>
			     </div>
			     <div class= "form-group ">
					<label for="uLocked" class="col-sm-2 control-label text-center">锁定</label>
					<div class="col-sm-10">
				    	<select id="uLocked" class= "form-control"></select>
				    </div>
				</div>
				<div class= "form-group ">
					<label for="uStatus" class="col-sm-2 control-label text-center">状态</label>
					<div class="col-sm-10">
				    	<select id="uStatus" class= "form-control"></select>
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

<!-- 绑定角色信息页面 -->
<div class="modal fade" id="modal_bindRole">
	<div class="modal-dialog" >
		<div class="modal-content">
	        	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle">绑定角色</h4>
				</div>
					<div class="modal-body" >
						<div id="tree_role" class="ztree" style="width:260px; overflow:auto;">
						</div>
					</div>
				<div class="modal-footer">
					<div class="allChenck">
						<input type="checkbox" id="check_all_bind"/>全选
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_save_bindRole">角色绑定保存</button>
				</div>
		</div>
	</div>
</div>
</body>
</html>