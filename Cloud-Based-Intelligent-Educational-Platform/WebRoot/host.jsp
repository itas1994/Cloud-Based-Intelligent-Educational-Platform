<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/host.css">
	
  </head>
  
  <script>
  		function getAuthority(){
  			var authority=document.getElementById("authority").value;
  			if(authority=="s"){
  				document.getElementById("call_over").href="javascript:volid(0);";
  				document.getElementById("call_over").style.color="#EEEEE0";
  				document.getElementById("call_over").style.borderColor="#EEEEE0";
  				
  				document.getElementById("grouping").href="javascript:volid(0);";
  				document.getElementById("grouping").style.color="#EEEEE0";
  				document.getElementById("grouping").style.borderColor="#EEEEE0";
  			}else{
  				document.getElementById("sign_in").href="javascript:volid(0);";
  				document.getElementById("sign_in").style.color="#EEEEE0";
  				document.getElementById("sign_in").style.borderColor="#EEEEE0";
  			}
  		};
  		
		function logout(){
			if(confirm("要走了么？╮(╯▽╰)╭")){
				window.location.href = "LogoutAction!execute.action";
			}
		}
	</script>
  
  <body  onLoad="getAuthority();">
  	<input type="hidden" id="authority" value="<s:property value='authority' />" />
  	<div id="topMenu">
     	<img id="icon" src="image/logo.png" />
     	<label id="webid">智慧教学平台</label>
     	<a href="SigninAction!execute.action" id="sign_in" class="host_3_function">签到</a>
     	<a href="conveyName4GroupAction!execute.action" id="grouping" class="host_3_function">分组</a>
     	<a href="CallOverAction!execute.action" id="call_over" class="host_3_function">抽点</a>
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
    
    <img id="host1" class="host_pic" src="image/host1.JPG" />
   	<img id="host2" class="host_pic" src="image/host2.JPG" />
    <img id="host3" class="host_pic" src="image/host3.JPG" />
    <img id="host4" class="host_pic" src="image/host4.JPG" />
    <img id="host5" class="host_pic" src="image/host5.JPG" />
    <img id="host6" class="host_pic" src="image/host6.JPG" />
    	
    <div id="resource" class="describe">
    	★资源区:大家的资源都在这里
    </div>
    <div id="debate" class="describe">
    	★讨论区:只要和谐,什么都能说
    </div>
    <div id="homework" class="describe">
    	★作业区:记得定期看看老师留的作业
    </div>
    <div id="test" class="describe">
    	★测试区:测试是要打分的,要认真对待
    </div>
    <div id="signin" class="describe">
    	★自主签到:来上课要让老师知道
    </div>
    <div id="callover" class="describe">
    	★随机点名:请被点到的同学起立
    </div>
    <div id="group" class="describe">
    	★课堂分组:分到一组就是缘分
    </div>
  </body>
</html>
