<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
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
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
  </head>
  
  <body>
  	<div id="topMenu">
     	<img id="icon" src="image/logo.png" />
     	<label id="webid">智慧教学平台</label>
     	<img id="host_adm" src="image/adm.png" />
     	<label id="current_usr">
     		<a id="usr" class="usr_a" href=""></a> ,
     		<a class="usr_a" href="">登出</a>
     	</label>
    </div>
    <img id="menu_bar_first" src="image/menu_bar.png" /> 
    <img id="menu_host" src="image/menu_host.png" 
    		onclick="window.location.href='backhostAction!execute.action'" />
    <img class="menu_element" src="image/menu_resource.png" 
    		onclick="window.location.href='ResourceAction!execute.action'" />
    <img class="menu_element" src="image/menu_debate.png" 
    		onclick="window.location.href='DebateAction!execute.action'" />
    <img class="menu_element" src="image/menu_homework.png" 
    		onclick="window.location.href='HomeworkAction!execute.action'" />
    <img class="menu_element" src="image/menu_test.png" 
    		onclick="window.location.href='TestAction!execute.action'" />
    <img id="menu_bar_last" src="image/menu_bar.png" />
    <div id="current_panel"></div>
    <div id="panel_title">
    	<label id="ziyuanqu">★别忘记输入必要的资源信息</label>
    	<form id="form1" action="SearchAction!execute.action" method="post">
   			<input id="search_text" type="text" name="title" />
   			<input id="search_button" class="btn" type="submit" name="name" value="搜索" />
    	</form>
     	<input id="upload_resource" class="btn" type="button" value="上传文件" 
     			onclick="window.location.href='resourceZone/upload.jsp'" />
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
    </div>
  </body>
</html>
