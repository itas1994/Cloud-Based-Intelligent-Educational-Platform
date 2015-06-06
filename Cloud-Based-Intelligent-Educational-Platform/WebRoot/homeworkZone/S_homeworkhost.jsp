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
    
    <title>作业区</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/homeworkhost.css">

  </head>
  
  <body>
  	<div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="zuoyequ">作业区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
     <div id="homework">
     	<s:iterator value="sholist">
		<div id="searchresult">
			<label id="titlelabel">◆<a id="a_title" href="homeworkContentAction!execute.action?id=<s:property value="id" />">
				<s:property value='title' /></a></label><br><br>
			<div id="info">
				<label class="info">发布教师:<s:property value="issueteacher"></s:property></label>
				<label class="info">发布日期:<s:property value="issuetime"></s:property>&nbsp;&nbsp;</label>
				<label class="info">截至日期:<s:property value="deadline"></s:property>&nbsp;&nbsp;</label>
			</div>
		</div>
		</s:iterator>
     </div>
	 </div>
  </body>
</html>
