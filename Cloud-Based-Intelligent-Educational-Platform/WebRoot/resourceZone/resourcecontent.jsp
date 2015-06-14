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
    
    <title>文件详情</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/resource/resourcecontent.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">

  </head>
  
  <body>
   <div id="topMenu">
     	<img id="icon" src="image/logo.png" />
     	<label id="webid">智慧教学平台</label>
     	<img id="host_adm" src="image/adm.png" />
     	<label id="current_usr">
     		<a id="usr" class="usr_a" href=""><s:property value="name" /></a> ,
     		<a class="usr_a" href="">登出</a>
     	</label>
    </div>
    <img id="menu_bar_first" src="image/menu_bar.png" /> 
    <img id="menu_host" src="image/menu_host.png" 
    		onclick="window.location.href='backhostAction!execute.action'" />
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
    	<label id="ziyuanqu">★点击斜体字进行预览或下载</label>
    	<form id="form1" action="SearchAction!execute.action" method="post">
   			<input id="search_text" type="text" name="title" />
   			<input id="search_button" class="btn" type="submit" name="name" value="搜索" />
    	</form>
     	<input id="upload_resource" class="btn" type="button" value="上传文件" 
     			onclick="window.location.href='resourceZone/upload.jsp'" />
    </div>
     <div id="resultpanel">
		<s:iterator value="relist">
			<table class="table"  cellspacing="15">
				<tr>
					<td class="t">标题</td>
					<td class="t1"><s:property value="title"></s:property></td>
				</tr>
				<tr>
					<td class="t">占用</td>
					<td class="t1"><s:property value="size"></s:property></td>
				</tr>
				<tr>
					<td class="t">上传日期</td>
					<td class="t1"><s:property value="date"></s:property></td>
				</tr>
				<tr>
					<td class="t">热度</td>
					<td class="t1"><s:property value="hot"></s:property></td>
				</tr>
				<tr>
					<td class="t">上传用户</td>
					<td class="t1"><s:property value="usr"></s:property></td>
				</tr>
				<tr>
					<td class="t">描述</td>
					<td class="t1"><s:property value="content"></s:property></td>
				</tr>
				<tr>
					<td class="t">文件预览</td>
					<td class="t1"><a id="a_pro_location" href="<s:property value='location' />"><s:property value="name" /></a></td>
				</tr>
				<tr>
					<td class="t">下载路径</td>
					<td class="t1"><a id="a_pro_download" href="DownloadAction!execute.action?fileName=<s:property value="name" />">点我下载</a></td>
				</tr>
			</table>
		</s:iterator>
     </div>
  </body>
</html>
