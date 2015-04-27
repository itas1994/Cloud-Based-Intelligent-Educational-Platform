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
	<link rel="stylesheet" type="text/css" href="css/resourcecontent.css">
  </head>
  
  <body>
   <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ziyuanqu">资源区</label>
     	<div id="mainlink">
     		<input type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />--
     		<input type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     		<form id="form1" action="SearchAction!execute.action" method="post">
     			<input type="text" name="title" />
     			<input type="submit" name="name" value="搜索" />
     		</form>
     		<input type="button" value="上传文件" 
     				onclick="window.location.href='resourceZone/upload.jsp'" />
     	</div>
     </div>
     <div id="resultpanel">
		<s:iterator value="relist">
			<table class="table"  cellspacing="15">
				<tr>
					<td class="td">文件信息</td><td class="td">详情</td>
				</tr>
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
					<td class="t1"><a href="<s:property value='location' />"><s:property value="name" /></a></td>
				</tr>
				<tr>
					<td class="t">下载路径</td>
					<td class="t1"><a href="DownloadAction!execute.action?fileName=<s:property value="name" />">点我下载</a></td>
				</tr>
			</table>
		</s:iterator>
		<input id="backresource" type="button" value="返回资源区"
     			onclick="window.location.href='ResourceAction!execute.action'"/>
     </div>
   </div>
  </body>
</html>
