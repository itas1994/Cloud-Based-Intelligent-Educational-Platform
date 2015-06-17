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
    
    <title>测试内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/test/T_testcontent.css">
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
    	<label id="ceshiqu">★可以给交卷的同学打分</label>
    	<input id="issue_test" class="btn" type="button" value="发布新测试" 
     			onclick="window.location.href='conveyName4TestAction!execute.action'" />
    </div>
     <div id="resultpanel">
		<div id="testresult">
			<label id="titlestar">◆</label>
			<label id="titlelabel"><s:property value="title" /></label><br><br>
			<label id="contentlabel"><s:property value="content" /></label>
		</div>
		<s:iterator value="ttelist" var="te">
			<div id="answerresult">
				<label id="ausr"><s:property value="ausr" /> 在  <s:property value="atime" /> 提交测试答案:</label><br><br>
				<label id="acontent"><s:property value="acontent" /></label><br><br>
				<s:if test="#te.ascore=='暂未打分'">
					<form id="form2" action="testScoreAction!execute.action" method="post">
						<input type="hidden" name="id" value="<s:property value='id' />" />
						<input type="hidden" name="ausr" value="<s:property value='ausr' />" />
						<textarea id="atextarea" name="ascore" cols="100" rows="2"></textarea><br>
						<input class="btn" id="submitanswer" type="submit" value="提交测试打分"/>
					</form><br>
				</s:if>
				<s:else>
					<label id="ascore">教师打分:<s:property value="ascore" /></label><br>
				</s:else>
			</div>
		</s:iterator>
     </div>
  </body>
</html>
