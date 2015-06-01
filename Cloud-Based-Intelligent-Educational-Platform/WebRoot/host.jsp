<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/host.css">

  </head>
  
  <body>
  		<div id="topMenu">
     		<div id="icon">
     			<img class="icon" src="image/1.jpg" />
     		</div>
     		<label id="title">智慧教学平台   </label>
     		<label id="subtitle">———— &nbsp;&nbsp;&nbsp;课堂互动系统</label>
     		<div id="mainlink">
     			<input type="button" name="login" value="登陆"
     					onclick="window.location.href='login.jsp'" />
     		</div>
     	</div>
    	<div class="select" onclick="window.location.href='SigninAction!execute.action'"><label class="selectlabel">自主签到</label></div>
    	<div class="select" onclick="window.location.href='dmZone/dm.jsp'"><label class="selectlabel">随机点名</label></div>
    	<div class="select" onclick="window.location.href='group/grouping.jsp'"><label class="selectlabel">课堂分组</label></div>
    	<div class="select" onclick="window.location.href='ResourceAction!execute.action'"><label class="selectlabel">资源区</label></div>
    	<div class="select" onclick="window.location.href='HomeworkAction!execute.action'"><label class="selectlabel">作业区</label></div>
    	<div class="select" ><label class="selectlabel">测试区</label></div>
    	<div class="select" onclick="window.location.href='DebateAction!execute.action'"><label class="selectlabel">讨论区</label></div>
  </body>
</html>
