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
    
    <title>签到成功</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/signin/signin.css">

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
    	<label id="qiandao">★签到成功</label>
    </div>
    <div id="resultpanel">
  		<div id="resultcontent">
  			<s:if test='in_class_now=="-1"'>
  				<label id="not_in_class">现在不是上课时间,不用签到啊</label>
  			</s:if>
  			<s:elseif test='in_class_now=="1"'>
  				<label id="course_name_label">当前课程:</label>
 				<label id="course_name">
 					<s:property value="course_name" />
 				</label><br><br>
 				<label id="start_label">上课时段:</label>
				<label id="start">
					<s:property value="start" />
				</label>
				<label id="to_label">至</label>
				<label id="end">
					<s:property value="end" />
				</label><br>
			</s:elseif>
  		</div>
  	</div>
  </body>
</html>
