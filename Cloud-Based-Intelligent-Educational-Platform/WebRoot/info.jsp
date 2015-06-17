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
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css">
	<link rel="stylesheet" type="text/css" href="css/info.css">
	
  </head>
  	<script type="text/javascript" src="./js/jquery-1.11.1.min.js">
  	</script>
    <script>
		function logout(){
			if(confirm("要走了么？╮(╯▽╰)╭")){
				window.location.href = "LogoutAction!execute.action";
			}
		}
		
		$(function(){
 			$("#modify_info").click(function(){
				$("#modify_div").slideToggle("middle");
   			});
		});
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
    	<label id="info">★个人资料</label>
    	<input id="modify_info" class="btn" type="button" value="修改密码" />
    </div>
    <div id="modify_div">
    	<div id="modify_content_div">
    		<form id="modify_form" action="ModifyPsdAction!execute.action" method="post">
    			<label id="new_psd_label">输入新密码:</label>
    			<input id="new_psd_input" type="password" name="new_psd" />
    			<input class="btn" id="new_psd_submit" type="submit" value="确认修改" />
    		</form>
    	</div>
    </div>
    <div id="resultpanel">
    	<div id="presentation">
			<s:if test='authority=="t"'>
				<s:iterator value="inlist">
					&nbsp;&nbsp;&nbsp;&nbsp;您迄今为止上传了
					<label class="count">
						<s:property value="count_re" />
					</label>个大家喜爱的资源,发布了
					<label class="count">
						<s:property value="count_de" />
					</label>个讨论,为同学们布置了
					<label class="count">
						<s:property value="count_ho" />
					</label>篇作业，丰富了同学们的课余生活=_=!,
					但是仍不忘用
					<label class="count">
						<s:property value="count_te" />
					</label>张测验卷检验同学们的真才实学，
					不愧为“中国好老师” 
				</s:iterator>
			</s:if>
			<s:elseif test='authority=="s"'>
				<s:iterator value="inlist">
					&nbsp;&nbsp;&nbsp;&nbsp;你迄今为止上传了
					<label class="count">
						<s:property value="count_re" />
					</label>个大家喜爱的资源,发布了
					<label class="count">
						<s:property value="count_de" />
					</label>个关于节操的讨论,完成了老师布置的
					<label class="count">
						<s:property value="count_ho" />
					</label>篇作业,还顺便填写了
					<label class="count">
						<s:property value="count_te" />
					</label>张测验卷=_=!
					你表示:虽然成为学霸的路还很长，但你会坚定地走下去 
				</s:iterator>
			</s:elseif>
		</div>
		<div id="infopanel">
			<s:iterator value="ulist">
				<table class="table"  cellspacing="15">
					<tr>
						<td class="t">用户名</td>
						<td class="t1"><s:property value="ID" /></td>
					</tr>
					<tr>
						<td class="t">姓名</td>
						<td class="t1"><s:property value="name" /></td>
					</tr>
					<tr>
						<td class="t">年龄</td>
						<td class="t1"><s:property value="age" /></td>
					</tr>
					<tr>
						<td class="t">性别</td>
						<td class="t1"><s:property value="sex" /></td>
					</tr>
					<tr>
						<td class="t">国籍</td>
						<td class="t1"><s:property value="nation" /></td>
					</tr>
					<tr>
						<td class="t">联系方式</td>
						<td class="t1"><s:property value="tel" /></td>
					</tr>
					<tr>
						<td class="t">邮箱地址</td>
						<td class="t1"><s:property value='email' /></td>
					</tr>
				</table>
			</s:iterator>
		</div>
	</div>
  </body>
</html>
