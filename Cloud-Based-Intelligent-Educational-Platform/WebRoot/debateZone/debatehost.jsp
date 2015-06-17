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
	<link rel="stylesheet" type="text/css" href="css/debate/debatehost.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">

  </head>
  
  <script>
		function logout(){
			if(confirm("要走了么？╮(╯▽╰)╭")){
				window.location.href = "LogoutAction!execute.action";
			}
		}
</script>
  
  <body>
    <div id="topMenu">
     	<img id="icon" src="image/logo.png" />
     	<label id="webid">智慧教学平台</label>
     	<img id="host_adm" src="image/adm.png" />
     	<label id="current_usr">
     		<a id="usr" class="usr_a" href="PersonalInfoAction!execute.action"><s:property value="name" /></a> ,
     		<a class="usr_a" onclick="logout()">登出</a>
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
    	<label id="taolunqu">★大家都在讨论什么？</label>
    	<input id="issue_debate" class="btn" type="button" value="发布新讨论" 
     			onclick="window.location.href='conveyName4DebateAction!execute.action'" />
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
  </body>
</html>
