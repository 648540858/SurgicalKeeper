<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="com.alibaba.fastjson.JSONObject"%>
<%@ page import="com.rjkx.sk.system.properties.PropertiesFile,com.rjkx.sk.system.properties.PropertiesHelper,com.rjkx.sk.system.properties.PropertiesFactory"%>
<%
PropertiesHelper systemProperties=PropertiesFactory.getPropertiesHelper(PropertiesFile.APP_PROPERTIES);
String fileDir=systemProperties.getValue("sys.var.kingeditor.fileDir");

String saveDir=systemProperties.getValue("sys.var.kingeditor.saveDir");
//文件保存目录路径
//String savePath = pageContext.getServletContext().getRealPath("/") + "filedir/dataupload/kindattached/";
//String savePath = fileDir + "/dataupload/kindattached/";
//文件保存目录URL
//String saveUrl  = saveDir + "/dataupload/kindattached/";

//文件保存目录路径
String savePath = fileDir;
//文件保存目录URL
String saveUrl  = saveDir;

//定义允许上传的文件扩展名
HashMap<String, String> extMap = new HashMap<String, String>();
extMap.put("image", "gif,jpg,jpeg,png,bmp");
extMap.put("flash", "swf,flv");
extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

//最大文件大小
long maxSize = 10240000;

response.setContentType("text/html; charset=UTF-8");

if(!ServletFileUpload.isMultipartContent(request)){
	out.print(getError("请选择文件。"));
	out.flush();
    //out.close();
	return;
}
//检查目录
File uploadDir = new File(savePath);
if(!uploadDir.isDirectory()){
	out.print(getError("上传目录不存在。"));
	out.flush();
    //out.close();
	return;
}
//检查目录写权限
if(!uploadDir.canWrite()){
	out.print(getError("上传目录没有写权限。"));
	out.flush();
    //out.close();
	return;
}

String dirName = request.getParameter("dir");
if (dirName == null) {
	dirName = "image";
}
if(!extMap.containsKey(dirName)){
	out.print(getError("目录名不正确。"));
	out.flush();
    //out.close();
	return;
}
//创建文件夹
savePath += dirName + "/";
saveUrl += dirName + "/";
File saveDirFile = new File(savePath);
if (!saveDirFile.exists()) {
	saveDirFile.mkdirs();
}
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
String ymd = sdf.format(new Date());
savePath += ymd + "/";
saveUrl += ymd + "/";
File dirFile = new File(savePath);
if (!dirFile.exists()) {
	dirFile.mkdirs();
}

FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);
upload.setHeaderEncoding("UTF-8");
List<?> items = upload.parseRequest(request);
Iterator<?> itr = items.iterator();
while (itr.hasNext()) {
	FileItem item = (FileItem) itr.next();
	String fileName = item.getName()+".jpg";
	long fileSize = item.getSize();
	if (!item.isFormField()) {
		//检查文件大小
		if(item.getSize() > maxSize){
			out.print(getError("上传文件大小超过限制。"));
			out.flush();
	        //out.close();
			return;
		}
		fileName="ceshi.jpg";
		//检查扩展名
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
			out.print(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
			out.flush();
	        //out.close();
			return;
		}

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		try{
			File uploadedFile = new File(savePath, newFileName);
			item.write(uploadedFile);
		}catch(Exception e){
			out.print(getError("上传文件失败。"));
			out.flush();
	        //out.close();
			return;
		}

		out.print(saveUrl + newFileName);
		out.flush();
        //out.close();
	}
}
%>
<%!
private String getError(String message) {
	return "error|"+message;
}
%>