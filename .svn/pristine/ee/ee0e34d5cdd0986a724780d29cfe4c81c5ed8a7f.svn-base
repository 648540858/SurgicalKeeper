<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>医疗机构注册</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/basedata/css/MedicalInstitution.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/basedata/js/MedicalInstitution.js"></script>
<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</head>
<body>
<div class="all_content">
	<div class="center_query ">	
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
		  <button type="button" class="btn btn-success btn-sm" id="btn_bindDept">
		  	<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>&nbsp绑定科室信息
		  </button>
		</div>
		</div>
	</div>
	<div class="table_show center-block">
		 <table class="table table-hover ">
		   <thead>
		     <tr>
		       <th><input type="checkbox" id="topcheck" name="topcheck" /></th>
		       <th>名称</th>
		       <th>类别</th>
		       <th>级别</th>
		       <th>电话</th>
		       <th>邮箱</th>
		       <th>创建时间</th>
		       <th>最后修改时间</th>
		       <th>状态</th>
	    	 </tr>
	   	</thead>
	   <tbody id="content" ></tbody>
		<script id="list" type="text/html">
 			{{each ROOT as value i}}
     			<tr>
					<td><input type="checkbox" name="grup" id="grup{{value.hospId}}" align="middle" value="{{value.hospId}}"/></td>
					<td>{{value.hospName}}</td>
					<td>{{value.hospTypeName}}</td>
					<td>{{value.hospLevelName}}</td>
					<td>{{value.hospTel}}</td>
					<td>{{value.hospEmail}}</td>
					<td>{{value.hospCtime}}</td>
					<td>{{value.hospUtime}}</td>
					<td>{{value.hospStatus}}</td>
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
		<div id="tree_area" class="ztree" style="width:100%; overflow:auto;"></div>
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
					 <label for="hospName" class="col-sm-2 control-label text-center">名称</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="hospName" name="hospName"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="hospAddr" class="col-sm-2 control-label text-center">地址</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="hospAddr" name="hospAddr"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="hospTel" class="col-sm-2 control-label text-center">电话</label>
			         <div class="col-sm-10">
			         	<input type="text" class="form-control" id="hospTel" name="hospTel"/>
			         </div>
			     </div>
			     <div class="form-group">
					 <label for="fileIcon" class="col-sm-2 control-label text-center">头像</label>
					 <div class="col-sm-10">
			         	<input type="hidden" class="form-control" id="hospLogo" name="hospLogo"/>
			         	<input type="file" class="form-control" id="fileIcon" name="fileIcon"/>
			         	<button type="button" class="btn btn-default" id="uploadfile">上传</button>
			         	<img id="imgIcon" alt="" src="" width="60px" height="60px"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="hospDesc" class="col-sm-2 control-label text-center">描述</label>
			         <div class="col-sm-10">
			         	<textarea class="form-control" id="hospDesc" name="hospDesc" rows="3" cols="50"></textarea>
			         </div>
			     </div>
			    <div class= "form-group ">
					<label for="hospType" class="col-sm-2 control-label text-center">医院类别</label>
				    <div class="col-sm-10">
					    <select id="hospType" class="form-control"></select>
				    </div>
				</div>
				<div class= "form-group ">
					<label for="hospLevel" class="col-sm-2 control-label text-center">医院级别</label>
				    <div class="col-sm-10">
				    	<select id="hospLevel" class= "form-control"></select>
				    </div>
				</div>
				<div class= "form-group ">
					<label for="hospStatus" class="col-sm-2 control-label text-center">状态</label>
					<div class="col-sm-10">
				    	<select id="hospStatus" class= "form-control"></select>
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

<!-- 绑定科室信息页面 -->
<div class="modal fade" id="modal_bindHospDept">
	<div class="modal-dialog">
		<div class="modal-content">
	        	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title" id="modalTitle">绑定科室信息</h4>
				</div>
					<div class="modal-body" style="overflow:auto;">
						<div id="tree_bindHospDept" class="ztree" style="width:95%;">
						</div>
					</div>
				<div class="modal-footer">
					<div class="allChenck">
						<input type="checkbox" id="check_all_bind"/>全选
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_save_bindHospDept">科室绑定保存</button>
				</div>
		</div>
	</div>
</div>
</body>
</html>