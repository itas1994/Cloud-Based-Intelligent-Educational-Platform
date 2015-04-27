<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>搜索结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/searchnothing.css">
  </head>
  
  <body>
  <div id="main">
     <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ziyuanqu">资源区</label>
     	<div id="mainlink">
     		<input type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />--
     		<input type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     		<form id="form1" action="SearchAction!execute.action" method="post">
     			<input type="text" name="title" />
     			<input type="submit" name="name" value="搜索" />
     		</form>
     		<input id="upload" type="button" value="上传文件" 
     				onclick="window.location.href='resourceZone/upload.jsp'" />
     	</div>
     </div>
     <div id="errornotice">
     	<img id="nothingpic" src="image/searchnothing.jpg" /><br><br>
     	<p id="nothing">未检索到您所要的资源!</p><br>
     	<input id="backresource" type="button" value="返回资源区"
     				onclick="window.location.href='ResourceAction!execute.action'"/>
     </div>
  </div>
</html>
