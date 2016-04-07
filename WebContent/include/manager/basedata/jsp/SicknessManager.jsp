<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>疾病维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/basedata/css/SicknessManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/basedata/js/SicknessManager.js"></script>
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
		   <button type="button" class="btn btn-success btn-sm" id="btn_sub">
		  	<span class="glyphicon glyphicon-collapse-down" aria-hidden="true"></span>&nbsp子类维护
		  </button>
		  <button type="button" class="btn btn-success btn-sm" id="btn_YyDoc">
		  	<span class="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp推荐医生
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
	       <th>状态</th>
       </tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="grup{{value.sickId}}"  value="{{value.sickId}}"/></td>
				<td>{{value.sickName}}</td>
				<td>{{value.sickDesc}}</td>
				<td>{{value.sickStatus}}</td>
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
					<h4 class="modal-title" id="modalTitle">疾病信息</h4>
			</div>
			<form id="validate_addEdit" class="form-horizontal">
			<div class="modal-body edit_content" style="overflow :auto; max-height: 250px;">
				<div class="form-group">
					 <label for="sickName" class="col-sm-2 control-label text-center">名称</label>
					 <div class="col-sm-10">
			         <input type="text" class="form-control" id="sickName" name="sickName"/>
			         </div>
			     </div>
			     <div class="form-group">
			        <label for="sickDesc" class="col-sm-2 control-label text-center">描述</label>
			        <div class="col-sm-10">
			        	<input type="text" class="form-control" id="sickDesc" name="sickDesc"/>
					</div>
			     </div>
				<div class= "form-group ">
					<label for="sickStatus" class="col-sm-2 control-label text-center">状态</label>
					<div class="col-sm-10">
					    <select id="sickStatus" class="col-sm-10 form-control"> 
					    </select>
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

<!-- 疾病子类页面 -->
<div class="modal fade" id="modal_subSick">
	<div class="modal-dialog" style="width:900px;height: 600px; overflow: auto;">
		<div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="modalTitle1">子类维护</h4>
			</div>
			<div class="modal-body" style="overflow :auto;">
				<iframe src="#" id="subsickframe" name="subsickframe" width="100%" height="100%"></iframe>
			</div>
		</div>
	</div>
</div>
<!-- 推荐医生页面 -->
<div class="hide">
	<div class="docTable" style="overflow :auto;max-height: 320px;">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="请输入查询内容" id="quertDocContent" >
		    <span class="input-group-btn">
		        <button class="btn btn-success" type="button"id="btn_queryYyDoc">
		        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp查询
		        </button>
		    </span>
	      </span>
	    </div>
		<div class="btn-group" role="group" aria-label="...">
		  <button type="button" class="btn btn-success btn-sm" id="btn_addYyDoc">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>&nbsp添加		  
		  </button>
		  <button type="button" class="btn btn-success btn-sm" id="btn_deleteYyDoc">
		  	<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>&nbsp删除
		  </button>
		</div>
		<input class="sicknessId" type="hidden"/>
		<div class="isTable" style="height:250px;">
		 <table class="table table-hover yyDocTable">
		   <thead>
		     <tr>
		       <th><input type="checkbox" id="topcheck" class="th_checkbox"/></th>
		       <th>姓名</th>
		       <th>所属医院</th>
		       <th>联系电话</th>
	       	</tr>
		   </thead>
		   <tbody id="docContent" ></tbody>
	     	<script id="docList" type="text/html">
 			{{each ROOT as value i}}
     			<tr>
					<td ><input type="checkbox" name="docGrup" id="grup{{value.sDocId}}"  value="{{value.sDocId}}"/></td>
					<td>{{value.docName}}</td>
					<td>{{value.docHost}}</td>
					<td>{{value.docPhone}}</td>
				</tr>
			{{/each}}
   		</script>		
	 </table>
	</div>
	</div>
</div> 

<div class="hide">
		<div class="zTree" id="addYyDoc"></div>
</div>
</body>
</html>