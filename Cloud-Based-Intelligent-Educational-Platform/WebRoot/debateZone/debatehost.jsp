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
	<link rel="stylesheet" type="text/css" href="css/debatehost.css">

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
     			onClick="window.location.href='debateZone/debateissue.jsp'"/>
     	</div>
     </div>
     <div id="debate">
     	<s:iterator value="delist">
		<div id="debateresult">
			<label id="titlelabel">◆<a id="a_title" href="debateContentAction!execute.action?id=<s:property value="id" />">
				<s:property value='title' /></a></label><br><br>
			<div id="info">
				<label class="info">发布用户:<s:property value="issueusr"></s:property></label>
				<label class="info">回复数量:<s:property value="replynum"></s:property>&nbsp;&nbsp;</label>
				<label class="info">发布时间:<s:property value="issuetime"></s:property>&nbsp;&nbsp;</label>
			</div>
		</div>
		</s:iterator>
     </div>
	 </div>
  </body>
</html>
