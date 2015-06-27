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
    
    <title>发布新作业</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/homework/homeworkissue.css">
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
    	<label id="zuoyequ">★老师,作业不要太难啊o(╯□╰)o</label>
    </div>
     <div id="issue">
    	<form id="form2" action="homeworkIssueAction!execute.action" method="post">
     		<label class="issue_info">作业标题: </label><textarea id="title" name="title" cols="34" rows="1"></textarea><br><br>
     		<label class="issue_info">作业内容:</label>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; 
     		<textarea id="content" name="content" cols="34" rows="6"></textarea><br><br>
     		
     		<label class="issue_info">截止日期</label>
     		<label class="issue_info">(年年年年-月月-日日):</label><br>
     		<textarea id="deadline_year" name="year" cols="7" rows="1"></textarea>
     		<label class="issue_info">年</label>
     		<textarea id="deadline_month" name="month" cols="7" rows="1"></textarea>
     		<label class="issue_info">月</label>
     		<textarea id="deadline_day" name="day" cols="7" rows="1"></textarea>
     		<label class="issue_info">日</label><br><br>
     		
     		<input class="btn" id="submitissue" type="submit" value="确认发布" />
    	</form><br>
    </div>
  </body>
</html>
