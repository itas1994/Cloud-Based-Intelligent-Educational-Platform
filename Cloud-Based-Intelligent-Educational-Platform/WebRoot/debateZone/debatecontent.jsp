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
	<link rel="stylesheet" type="text/css" href="css/debate/debatecontent.css">

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
     			onclick="window.location.href='debateZone/debateissue.jsp'"/>
     	</div>
     </div>
     <div id="resultpanel">
		<div id="debateresult">
			<label id="titlestar">◆</label>
			<label id="titlelabel"><s:property value='title' /></label><br>
			<label id="contentlabel"><s:property value='content' /></label><br><br>
		</div>
		<div id="form2div">
			<form id="form2" action="debateReplyAction!execute.action" method="post">
				<input type="hidden" name="title" value="<s:property value="title" />" />
				<input type="hidden" name="id" value="<s:property value="id" />" />
				<textarea id="replytextarea" name="replycontent" cols="100" rows="15"></textarea><br>
				<input class="btn" id="submitreply" type="submit" value="提交回复"/>
			</form><br>
		</div>
		<s:iterator value="delist">
			<div id="replypanel">
				<label id="replyusr"><s:property value="replyusr" /> 在  <s:property value="replytime" /> 回复:</label><br>
				<label id="replycontent"><s:property value="replycontent"></s:property></label><br>
			</div>
		</s:iterator>
		<input class="btn" id="backdebate" type="button" value="返回讨论区"
     			onclick="window.location.href='DebateAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
