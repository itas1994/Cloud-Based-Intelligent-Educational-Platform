<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>分组结果</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/group/groupSucceed.css">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script>
		function logout(){
			if(confirm("要走了么？╮(╯▽╰)╭")){
				window.location.href = "LogoutAction!execute.action";
			}
		}
	</script>

  </head>
  
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
    	<label id="fenzu">★分组结果</label>
    </div>	 
     <% int i=1;%>
     <div id="resultpanel">
   		<table id="teamcontent" border="1" cellpadding="1"
                         cellspacing="10">
           <s:iterator value="list" status="status">
           		<s:if test='#status.index%<s:property value="memNum" />==0'>
             		<tr>
             			<td id="teamnum">第<%=i++%>小组</td>
                </s:if>
                <td id="teammember" valign="middle" align="center">
                    <s:property value="name" />
                </td>
            </s:iterator>
        </table>
       </div>
  </body>
</html>
