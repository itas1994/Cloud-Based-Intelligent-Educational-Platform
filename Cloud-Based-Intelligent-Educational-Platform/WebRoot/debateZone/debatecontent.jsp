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
    
    <title>讨论区</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/debatecontent.css">

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
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<input class="btn" type="button" name="name" value="发布新讨论" 
     			onclick="windows.location.href='IssueAction!execute.action'"/>
     	</div>
     </div>
     <div id="resultpanel">
		<div id="debateresult"	>
			<label id="titlelabel">◆<s:property value='title' /></label><br><br>
			<form id="form2" action="debateReplyAction!execute.action" method="post">
				<textarea id="replytextarea" cols="100" rows="15"></textarea><br>
				<input class="btn" id="submitreply" type="submit" value="提交回复"/>
			</form><br>
			<s:iterator value="delist">
				<label id="replyusr"><s:property value="issueusr"></s:property> 回复:</label><br>
				<label id="replycontent"><s:property value="replycontent"></s:property></label><br>
				<label id="replytime">回复时间:<s:property value="replytime"></s:property>&nbsp;&nbsp;</label>
			</s:iterator>
		</div>
		<input class="btn" id="backresource" type="button" value="返回讨论区"
     			onclick="window.location.href='DebateAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
