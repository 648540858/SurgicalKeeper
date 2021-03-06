<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

<title>医疗科室维护</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/wangEditor/css/wangEditor.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/cms/css/GroupManager.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/include/manager/css/base.css" />
</head>
<body>
<div class="center_query " >	
		<div class="center-block btn_all">
			<div class="center_btn_1 clearfix" >
				 <div class="controls">
				  <label class="control-label">内容类型：&nbsp</label>
		            <select class="input-xlarge valtype myselect" id="queryBYContent">
		            	<option value="">所有</option>
		            </select>
		             <label class="control-label">操作者类型：&nbsp</label>
		            <select class="input-xlarge valtype myselect" id="queryBYAuthor">
		            	<option value="">所有</option>
		            </select>
		           <button class="btn btn-success " type="button" id="btn_query_2" id="btn_query">
			        <span class="glyphicon glyphicon-search" aria-hidden="true"></span>&nbsp按类型查询
			       </button>
		          </div>
			</div>
			<div class="center_btn_2 clearfix" >
			</div>
		</div>		
	</div>
	
 <div class="center_table"id='center_table' >
	<div class="table_btn clearfix" >
		<div class="btn-1">
			<div class="input-group">
			    <input type="text" class="form-control" id="queryId" placeholder="请输入查询内容">
			    <span class="input-group-btn">
		        <button class="btn btn-success" type="button"id="btn_query_1">
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
		   <button type="button" class="btn btn-success btn-sm" id="btn_reply">
		  	<span class="glyphicon zoom-in" aria-hidden="true"></span>&nbsp查看回复
		  </button>
		</div>
		</div>
	</div>
	<div class="table_show center-block">
	<table class="table table-hover">
	  <thead>
	   <tr>
       <th><input type="checkbox" id="topcheck" class="th_checkbox"/></th>
       <th>标题</th>
       <th>内容类型</th>
       <th>操作者类型</th>
       <th>操作者</th>
       <th>阅读量</th>
       <th>收藏量</th>
       <th>回复量</th>
       <th>点赞量</th>
       <th>更新时间</th>
       <th>状态</th>
     </tr>
	   </thead>
	   <tbody id="content" ></tbody>
	     	<script id="list" type="text/html">
 		{{each ROOT as value i}}
     		<tr>
				<td ><input type="checkbox" name="grup" id="{{value.nId}}"  value="{{value.nId}}" class="td_checkbox"/></td>
				<td>{{value.nTitle}}</td>
				<td>{{value.nTypeText}}</td>
				<td>{{value.nOptypeText}}</td>
				<td>{{value.nOpName}}</td>
				<td>{{value.nRead}}</td>
				<td>{{value.favNo}}</td>
				<td>{{value.reply}}</td>
				<td>{{value.zan}}</td>
				<td>{{value.ctime}}</td>
				<td>{{value.nStatusText}}</td>
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
<!-- 添加/编辑圈子 -->
	<div class="hide">
				<div id="addPage"  style="overflow :auto;height:470px;">
					<form id="validate_addEdit">
					 <label >标题</label>
			         	<input type="text" class="form-control" id="gTitle" name="gTitle"/>
			     
					<label >状态</label>
					    <select id="groupStatus" name="groupStatus" class= "form-control"></select>
			     
			         <label >内容类型</label>
						<select id="ContentType" class= "form-control"></select>
					    
					<div  id='mediaType' style="display: none;margin-top: 5px;">  
					<label >视频图标</label>
						<div style="width: 100%;height: 128px;">
						 	<div  style="float: left; "> 
						 		<div>
								<input type="hidden" style="width: 50%;"  id="fileAdres"  name="fileAdres" />
								<span id="upInto" style="font-size: 16px; color: red" ></span> 
					         	<input type="file" class="file"  id="fileIcon" name="fileIcon" />
					         	</div> 
					         	<img  id="imgShow" alt="未加载..." src="#"  style="width: 70px;height: 70px ;border:1px solid #A6A6A6;">
					         	
				        	</div>		
			        		<div style="float: left;"> 
				        		 <input type="button"  id="uploadBtn" class="btn btn-default" value="点击上传"style="padding: 15px;margin-top: 40px;"/>
				        		  
				        	</div>
						</div>
						<div>
						<label >视频地址</label>
							<input type="text"  id="mediaUrl" name="mediaUrl" class= "form-control"/>
						</div>
					</div>
					<label >内容</label>
						<div style="width: 100%;" >
							<textarea rows="5" cols="10" id="groupContent" name="groupContent" style="height:150px;max-height:500px;"></textarea>
						</div>
					</form>
				</div>		
	</div>
	
	<!-- 查看回复 -->
	<div class="hide">
		<div id="replyPage" style="height:450px;">
			<script id="replyList" type="text/html">
				<b>操作者类型</b> 
				<select id="nOptype" >
					<option value="">所有</option>
					<option value="1">后台</option>
					<option value="2">医生</option>
					<option value="3">患者</option>
				</select>
				<input type="button" id="" value="查询" onclick=queryReply() class="btn btn-success btn-sm"/>
			<hr style="height: 3px; margin-top: 15px; margin-bottom: 15px;">
				<input type="checkbox" name="rIdAll" id="rIdAll"  class="td_checkbox " style="margin-left: 2.5rem;"/>全选
				<input style="float: right; margin-top: -0.8rem; margin-right: 1rem;" type="button" class="btn btn-success btn-sm" value="删除" name="deleteReply" onclick=deleteReply() id="deleteReply"> 
				<div  style="overflow:auto;max-height:400px;border:1px solid #A6A6A6;" class="huifu">
				{{each ROOT as value i}}
					<ul >
						<li style="list-style-type:none;">
							<div style="width: 100%;height:155px;border:1px solid #c8c8c8;background: rgb(246, 246, 246);margin-left: -2.5rem;">
								<div style="margin-left: 0.5rem;margin-top: 0.5rem;">
									{{if value.replyUserType==1}}
									<b>回复人类型：</b><span style="font-size: 1.5rem; color: red;"> 后台</span><br>
									{{/if}}

									{{if value.replyUserType==2}}
									<b>回复人类型： </b><span style="font-size: 1.5rem; color: blue;"> 医生</span><br>
									{{/if}}

									{{if value.replyUserType==3}}
									<b>回复人类型：</b> </b><span style="font-size: 1.5rem; color: #F0A53F;">患者</span><br>
									{{/if}}
									<b>回复人姓名:{{value.replyUserName}}</b><br>
									<b>回复时间 : {{value.replyTime}}</b><br>
									<b>回复内容: </b>
										<span>
											<textarea id="text{{value.replyId}}" rows="4" cols="45"style="background: #fff;margin-top: -1.8rem; margin-left: 6.5rem;  width: 40rem; height: 5rem;">{{value.replyContent}}</textarea>
										</span>
								</div>
								<input style="margin-left: 1.5rem;margin-top: 1.3rem;" type="checkbox" name="rId" id="{{value.replyId}}"  value="{{value.replyId}}" class="td_checkbox"/>
								<input style="background:D3DBFC;float: right; margin-top: 0.5rem;"" type="button" class="btn btn-success btn-sm" value="确认更改回复内容" name="submitReply" onclick=replysubmit(this.id) id="{{value.replyId}}"> 
							</div>
						</li>
				</ul>
				{{/each}}
				</div>
			</script>
			
				
		
		</div>
	</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/artTemplate/artTemplate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jqueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/jquery/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/js/commonUtils.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/include/manager/cms/js/GroupManager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/wangEditor/js/wangEditor.js"></script>

<script type="text/javascript">
	var root="<%=request.getContextPath()%>";
	var codeArray=${applicationScope.APPLICATION_SYSTEM_CODE_VAR};
</script>
</body>
</html>