<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/login.css">

  </head>
  
  <body>
  		<div id="login">
  			<label id="idlabel">用户名:</label><input id="id" name="usr" type="text" /><br><br>
  			<label id="psdlabel">密&nbsp;码:</label><input id="psd" name="psd" type="password"/><br><br>
  			<input id="submit" name="submit" type="submit" value="登陆"
  				   onclick="window.location.href='LoginAction!execute.action'"/>
  		</div>
  </body>
</html>
