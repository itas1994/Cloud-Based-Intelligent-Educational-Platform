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
    
    <title>发布新话题</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/debate/debateissue.css">

  </head>
  
  <body>
    <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="taolunqu">讨论区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<input class="btn" type="button" name="name" value="发布新讨论" 
     			onClick="window.location.href='debateZone/debateissue.jsp'"/>
     	</div>
     </div>
     <div id="issue">
    	<form id="form2" action="IssueAction!execute.action" method="post">
     		<label class="issue_info">讨论标题: </label><textarea id="title" name="title" cols="54" rows="1"></textarea><br><br>
     		<label class="issue_info">讨论内容:</label>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
     		<textarea id="content" name="content" cols="64" rows="12"></textarea><br><br>
     		<input class="btn" id="submitissue" type="submit" value="确认发布" />
    	</form><br>
    	<input class="btn" id="backdebate" type="button" value="返回讨论区"
     							onclick="window.location.href='DebateAction!execute.action'"/>
    </div>
    </div>
  </body>
</html>
