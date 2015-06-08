<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/resource/upload.css">
  </head>

  <body>
  	<div id="main">
     <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ziyuanqu">资源区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<form id="form1" action="SearchAction!execute.action" method="post">
     			<input id="search_text" type="text" name="title" />
     			<input class="btn" type="submit" name="name" value="搜索" /> 	|
     		</form>
     		<input class="btn" type="button" value="上传文件" 
     				onclick="window.location.href='resourceZone/upload.jsp'" />
     	</div>
     </div>
     <div id="upload">
    	<form id="form2" action="UploadAction!execute.action"
     				enctype="multipart/form-data" method="post">
     		<label class="upload_info">资源标题: </label><textarea id="title" name="title" cols="54" rows="1"></textarea><br><br>
     		<label class="upload_info">内容描述:</label>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
     		<textarea id="content" name="content" cols="64" rows="12"></textarea><br><br>
			<label class="upload_info">选择文件: </label><input type="file" name="upload" />
     			  <input class="btn" type="submit" value="确认上传" />
    	</form><br>
    	<input class="btn" id="backresource" type="button" value="返回资源区"
     							onclick="window.location.href='ResourceAction!execute.action'"/>
    </div>
     </div>
    
  </body>
</html>
