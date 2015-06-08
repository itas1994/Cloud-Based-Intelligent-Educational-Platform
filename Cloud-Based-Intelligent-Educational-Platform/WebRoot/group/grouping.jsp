<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>课堂分组</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/grouping.css">

  </head>
  
  <body>
  <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ketangfenzu">课堂分组</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
  	<div id="resultpanel">
  		<div id="resultcontent">
  			<label id="notice">输入分组数量</label>
    		<input id="num" name="num" type="text" />
    		<input id="submit" name="submit" class="btn" type="submit" value="输入组数"
  			  	 onclick="window.location.href='GroupedAction!execute.action'"/>
  		</div>
  	</div>
  </div>
  </body>
</html>
