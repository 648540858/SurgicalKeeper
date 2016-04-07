<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>医生管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/zTree/css/zTreeStyle/zTreeStyle.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/basedata/css/DoctorManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/zTree/js/jquery.ztree.all-3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/basedata/js/DoctorManager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/ajaxfileupload.js"></script>
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
		  <button type="button" class="btn btn-success btn-sm" id="btn_bindsickness">
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
		       <th>ID</th>
		       <th>姓名</th>
		       <th>科室</th>
		       <th>职称</th>
		       <th>职务</th>
		       <th>手机号</th>
		       <th>二维码</th>
		       <th>状态</th>
		     </tr>
	  	 </thead>
	   <tbody id="content" ></tbody>
		<script id="list" type="text/html">
 			{{each ROOT as value i}}
     			<tr>
					<td><input type="checkbox" name="grup" id="grup{{value.docId}}" align="middle" value="{{value.docId}}"/></td>
					<td>{{value.docId}}</td>						
					<td>{{value.docName}}</td>
					<td>{{value.docDeptname}}</td>
					<td>{{value.docRank}}</td>
					<td>{{value.docDeptrank}}</td>
					<td>{{value.docMobile}}</td>
					<td><a href="javascript:loadqrimg({{value.docId}},'{{value.docQrcode}}');">查看</a>
					</td>
					<td>{{value.docStatus}}</td>
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
				<div class="modal-body" style="height:80%;overflow :auto;">
					<div class="form-group">
						 <label for="docName" class="col-sm-2 control-label text-center">姓名</label>
						 <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docName" name="docName"/>
				         </div>
				     </div>
				     <div class= "form-group ">
						<label for="docDeptid" class="col-sm-2 control-label text-center">科室</label>
					    <div class="col-sm-10">
						    <select id="docDeptid" class="form-control"></select>
					    </div>
					</div>
				     <div class= "form-group ">
						<label for="docSex" class="col-sm-2 control-label text-center">性别</label>
					    <div class="col-sm-10">
						    <select id="docSex" class="form-control"></select>
					    </div>
					</div>
				     <div class="form-group">
				         <label for="docAge" class="col-sm-2 control-label text-center">年龄</label>
				         <div class="col-sm-10">
				         	<input type="number" class="form-control" id="docAge" name="docAge" value="0"/>
				         </div>
				     </div>
				     <div class="form-group">
						 <label for="fileIcon" class="col-sm-2 control-label text-center">头像</label>
						 <div class="col-sm-10">
				         	<input type="hidden" class="form-control" id="docIcon" name="docIcon"/>
				         	<input type="file" class="form-control" id="fileIcon" name="fileIcon"/>
				         	<button type="button" class="btn btn-default" id="uploadfile">上传</button>
				         	<img id="imgIcon" alt="" src="" width="60px" height="60px"/>
				         </div>
				     </div>
				      <div class="form-group">
				         <label for="docWorkyear" class="col-sm-2 control-label text-center">工作年限</label>
				         <div class="col-sm-10">
				         	<input type="number" class="form-control" id="docWorkyear" name="docWorkyear" value="0"/>
				         </div>
				     </div>
				      <div class= "form-group ">
						<label for="docRank" class="col-sm-2 control-label text-center">职称</label>
					    <div class="col-sm-10">
						    <select id="docRank" class="form-control"></select>
					    </div>
					</div>
					 <div class= "form-group ">
						<label for="docDeptrank" class="col-sm-2 control-label text-center">职务</label>
					    <div class="col-sm-10">
						    <select id="docDeptrank" class="form-control"></select>
					    </div>
					</div>
				     <div class="form-group">
				         <label for="docPhone" class="col-sm-2 control-label text-center">办公电话</label>
				         <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docPhone" name="docPhone"/>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="docTel" class="col-sm-2 control-label text-center">手机号</label>
				         <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docTel" name="docTel"/>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="docPwd" class="col-sm-2 control-label text-center">密码</label>
				         <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docPwd" name="docPwd"/>
				         </div>
				     </div>
				     <div class="form-group">
						 <label for="docDeptaddress" class="col-sm-2 control-label text-center">科室地址</label>
						 <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docDeptaddress" name="docDeptaddress"/>
				         </div>
				     </div>
				     <div class="form-group">
						 <label for="docOrderaddress" class="col-sm-2 control-label text-center">预约地址</label>
						 <div class="col-sm-10">
				         	<input type="text" class="form-control" id="docOrderaddress" name="docOrderaddress"/>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="docIntro" class="col-sm-2 control-label text-center">简介</label>
				         <div class="col-sm-10">
				         	<textarea class="form-control" id="docIntro" name="docIntro" rows="3" cols="50"></textarea>
				         </div>
				     </div>
				     <div class="form-group">
				         <label for="docGoodat" class="col-sm-2 control-label text-center">擅长</label>
				         <div class="col-sm-10">
				         	<textarea class="form-control" id="docGoodat" name="docGoodat" rows="3" cols="50"></textarea>
				         </div>
				     </div>
				    <div class= "form-group ">
						<label for="docStatus" class="col-sm-2 control-label text-center">状态</label>
						<div class="col-sm-10">
					    	<select id="docStatus" class= "form-control"></select>
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
	
<!-- 绑定疾病信息页面 -->
<div class="modal fade" id="modal_bindsickness">
	<div class="modal-dialog" >
		<div class="modal-content">
	        	<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle">绑定疾病信息</h4>
				</div>
					<div class="modal-body" >
						<div  id="tree_bindsickness" class="ztree" style="width:260px; overflow:auto;">
						</div>
					</div>
				<div class="modal-footer">
					<div class="allChenck">
						<input type="checkbox" id="check_all_bind"/>全选
					</div>
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-default" id="btn_save_bindsickness">疾病绑定保存</button>
				</div>
		</div>
	</div>
</div>

<!-- 绑定疾病信息页面 -->
<div class="modal fade" id="modal_qrcode">
	<div class="modal-dialog" >
		<div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="modalTitle2">二维码</h4>
			</div> 
			<div class="modal-body" style="text-align:center;vertical-align: middle;">
				<img id="qrimg" alt="" src="" border="0" style="width:200px;height:200px;"/>
				<button type="button" class="btn btn-default" id="btn_save_qrcode">重新生成</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>