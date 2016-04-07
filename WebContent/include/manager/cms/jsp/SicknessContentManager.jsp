<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>疾病科普内容管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/cms/css/SicknessContentManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/cms/js/SicknessContentManager.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/wangEditor/css/wangEditor.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/wangEditor/js/wangEditor.js"></script>
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
		</div>
		</div>
		
	</div>
	<div class="table_show center-block">
		 <table class="table table-hover ">
		   <thead>
		     <tr>
		       <th><input type="checkbox" id="topcheck" name="topcheck" /></th>
		       <th>属性名称</th>
		       <th>录入时间</th>
		       <th>状态</th>
		     </tr>
	  	 </thead>
	   <tbody id="content" ></tbody>
		<script id="list" type="text/html">
 			{{each ROOT as value i}}
     			<tr>
					<td><input type="checkbox" name="grup" id="grup{{value.sickContentId}}" align="middle" value="{{value.sickContentId}}"/></td>
					<td>{{value.sickAttrName}}</td>
					<td>{{value.sickContentCtime}}</td>
					<td>{{value.sickContentStatus}}</td>
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
<div id="tree_sick" class="ztree" style="width:100%; overflow:auto;"></div>
	</div>
</div> 
	<!-- 添加/编辑页面 -->
	<div class="modal fade" id="modal_addEdit" style="width:auto;">
		<div class="modal-dialog" style="width:850px;">
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
						 <label for="sickAttrid" class="col-sm-2 control-label text-center">属性名称</label>
						 <div class="col-sm-10">
				         	<select id="sickAttrid" class="form-control"></select>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="sickAttrcontent" class="col-sm-2 control-label text-center">内容</label>
				         <div class="col-sm-10">
				         	<textarea class="form-control" id="sickAttrcontent" name="sickAttrcontent" style="width:600px;height:270px;" rows="5" cols="80"></textarea>
				         </div>
				     </div>
				    <div class= "form-group ">
						<label for="sickContentStatus" class="col-sm-2 control-label text-center">状态</label>
						<div class="col-sm-10">
					    	<select id="sickContentStatus" class= "form-control"></select>
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