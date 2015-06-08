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
    
    <title>资源圈</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/resource/resourcehost.css">
  </head>

  <body>
  <div id="main">
     <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ziyuanqu">资源区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<form id="form1" action="SearchAction!execute.action" method="post">
     			<input id="search_text" type="text" name="title" />
     			<input class="btn" type="submit" name="name" value="搜索" /> 	|
     		</form>
     		<input class="btn" type="button" value="上传文件" 
     				onclick="window.location.href='resourceZone/upload.jsp'" />
     	</div>
     </div>
     <div id="resource">
     	<s:iterator value="relist">
		<div id="searchresult">
			<label id="titlelabel">◆<a id="a_title" href="ContentAction!execute.action?id=<s:property value="id" />">
				<s:if test='<s:property value="title" />==NULL'>未添加标题</s:if>
				<s:else><s:property value='title' /></s:else></a></label><br><br>
			<label id="contentlabel">
				<s:if test='<s:property value="content" />==NULL'>未添加描述</s:if>
				<s:else><s:property value='content' /></s:else></label><br><br>
			<div id="info">
				<label class="info">文件大小:<s:property value="size"></s:property></label>
				<label class="info">上传日期:<s:property value="date"></s:property>&nbsp;&nbsp;</label>
			</div>
		</div>
		</s:iterator>
     </div>
  </div>
  </body>
</html>
