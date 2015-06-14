<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用智慧教学平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/index.css">

  </head>
  
  <body>
  	<div id="welcome">
  		<label id="wel1">欢迎使用</label>
  		<label id="wel2">智慧教学平台</label>
  		<div id="loginpanel"></div>
  		<img id="adm" src="image/adm.png" />
  		<img id="pass" src="image/pass.png" />
  		<form action="LoginAction!execute.action" method="post">
  			<input id="adminput" type="text" name="usr" />
  			<input id="passinput" type="password" name="psd" />
  			<input class="btn" id="submit" 
  					name="submit" type="submit" value="登陆" />
  		</form>
  	</div>
  </body>
</html>
