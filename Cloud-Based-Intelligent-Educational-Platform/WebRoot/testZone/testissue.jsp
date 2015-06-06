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
    
    <title>发布新作业</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

  </head>
  
  <body>
    <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ceshiqu">测试区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<input class="btn" type="button" name="name" value="发布新作业" 
     			onClick="window.location.href='testZone/testissue.jsp'"/>
     	</div>
     </div>
     <div id="issue">
    	<form id="form2" action="testIssueAction!execute.action" method="post">
     		<label class="issue_info">作业标题: </label><textarea id="title" name="title" cols="54" rows="1"></textarea><br><br>
     		<label class="issue_info">作业内容:</label>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
     		<textarea id="content" name="content" cols="64" rows="12"></textarea><br><br>
     		<label class="issue_info">截止日期:</label>
     		<!-- limittime 的输入方式还没定 -->
     		<input class="btn" id="submitissue" type="submit" value="确认发布" />
    	</form><br>
    	<input class="btn" id="backtest" type="button" value="返回作业区"
     							onclick="window.location.href='TestAction!execute.action'"/>
    </div>
    </div>
  </body>
</html>
