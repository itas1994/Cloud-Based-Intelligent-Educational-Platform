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
    
    <title>随机点名</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/dm.css">

	<script  language="javascript" type="text/javascript">
		function a(){
			var a=document.getElementById("shaizi");
			a.setAttribute("src", "${pageContext.request.contextPath}/image/shaizi.gif");
			setTimeout(function(){window.location.href="DmAction!execute.action";},2000);
		}
	</script>
  </head>

  <body>
  <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="suijidianming">随机点名</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
  	<div id="resultpanel">
  		<div id="resultcontent">
  			<img id="click" src="image/click.png" />
  	 		<input id="shaizi" name="submit" type="image" value="随机点名" src="${pageContext.request.contextPath}/image/shaizi.jpg"  
  	 			onclick=a() />
  	 	</div>
  	</div>
  </div>
  </body>
</html>
