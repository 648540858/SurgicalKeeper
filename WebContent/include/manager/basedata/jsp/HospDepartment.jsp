<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>医疗科室维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/basedata/css/HospDepartment.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/basedata/js/HospDepartment.js"></script>

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
			    <input type="text" class="form-control" placeholder="请输入查询内容">
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
		  <button type="button" class="btn btn-success btn-sm" id="btn_bindHosDept">
		  	<span class="glyphicon glyphicon-zoom-in" aria-hidden="true"></span>&nbsp绑定科室信息
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
       <th>类别</th>
       <th>创建时间</th>
       <th>状态</th>
     </tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="{{value.hdId}}"  value="{{value.hdId}}" class="td_checkbox"/></td>
				<td>{{value.hdName}}</td>
				<td>{{value.hdDesc}}</td>
				<td>{{value.hdType}}</td>
				<td>{{value.hdCtime}}</td>
				<td>{{value.hdStatus}}</td>
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
<!-- 添加/编辑页面 -->
<div class="modal fade" id="modal_addEdit">
	<div class="modal-dialog" >
		<div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="modalTitle">添加科室信息</h4>
			</div>
			<form id="validate_addEdit" class="form-horizontal">
			<div class="modal-body" style="height:80%;overflow :auto;">
				<div class="form-group">
					 <label for="hdName" class="col-sm-2 control-label text-center">名称</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="hdName" name="hdName"/>
			         </div>
			     </div>
			     <div class="form-group">
			         <label for="hdDesc" class="col-sm-2 control-label text-center">描述</label>
					 <div class="col-sm-10">
			         	<input type="text" class="form-control" id="hdDesc" name="hdDesc"/>
			         </div>
			     </div>
			    
				<div class= "form-group ">
					<label for="hdType" class="col-sm-2 control-label text-center">类别</label>
					 <div class="col-sm-10">
					    <select id="hdType"  class= "form-control"></select>
				    </div>
				</div>
				<div class="form-group">
					<label for="hdStatus" class="col-sm-2 control-label text-center">状态</label>
					 <div class="col-sm-10">
					    <select id="hdStatus" class= "form-control"></select>
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
<div class="modal fade" id="modal_bindHosDept">
	<div class="modal-dialog" >
		<div class="modal-content">
	        	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
						<h4 class="modal-title" id="modalTitle">绑定科室信息</h4>
				</div>
					<div class="modal-body" style="overflow :auto;">
						<div  id="tree_bindHosDept" class="ztree" style="width:260px; overflow:auto;">
						</div>
					</div>
				<div class="modal-footer">
					<div class="allChenck">
						<input type="checkbox" id="check_all_bind"/>全选
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_save_bindHosDept">疾病绑定保存</button>
				</div>
		</div>
	</div>
</div>
</body>
</html>