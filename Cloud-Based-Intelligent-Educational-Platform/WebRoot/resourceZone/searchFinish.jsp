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
    
    <title>搜索结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/resource/searchfinish.css">
  </head>
  <body>
   <div id="topMenu">
     	<img id="icon" src="image/logo.png" />
     	<label id="webid">智慧教学平台</label>
     	<img id="host_adm" src="image/adm.png" />
     	<label id="current_usr">
     		<a id="usr" class="usr_a" href=""><s:property value="name" /></a> 老师,
     		<a class="usr_a" href="">登出</a>
     	</label>
    </div>
    <img id="menu_bar_first" src="image/menu_bar.png" /> 
    <img id="menu_host" src="image/menu_host.png" 
    		onclick="window.location.href='backhostAction!execute.action'" />
    <img class="menu_element" src="image/menu_group.png" 
    		onclick="window.location.href='group/grouping.jsp'" />
    <img class="menu_element" src="image/menu_resource.png" 
    		onclick="window.location.href='ResourceAction!execute.action'" />
    <img class="menu_element" src="image/menu_debate.png" 
    		onclick="window.location.href='DebateAction!execute.action'" />
    <img class="menu_element" src="image/menu_homework.png" 
    		onclick="window.location.href='HomeworkAction!execute.action'" />
    <img class="menu_element" src="image/menu_test.png" 
    		onclick="window.location.href='TestAction!execute.action'" />
    <img id="menu_bar_last" src="image/menu_bar.png" />
    <div id="current_panel"></div>
    <div id="panel_title">
    	<label id="ziyuanqu">★搜索结果</label>
    	<form id="form1" action="SearchAction!execute.action" method="post">
   			<input id="search_text" type="text" name="title" />
   			<input id="search_button" class="btn" type="submit" name="name" value="搜索" />
    	</form>
     	<input id="upload_resource" class="btn" type="button" value="上传文件" 
     			onclick="window.location.href='resourceZone/upload.jsp'" />
    </div>
     <div id="resultpanel">
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
  </body>
</html>
