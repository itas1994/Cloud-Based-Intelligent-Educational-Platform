<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>欢迎使用智慧教学平台</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/index.css">
	
	<script src="script/js/jquery-1.11.1.min.js"></script>
	<script>
		/* begin of ajax  */
		function login(){
      		var data={usr:$("#adminput").val(),psd:$("#passinput").val()};
     		console.log(data);
      		$.post("checkLoginAction!execute.action",data,function(result){
     			console.log(result);
     			if(result=="-2"){//psd
     				alert("您的账号或密码有误,请重新输入");
     			}else if(result=="-1"){//usr
     				alert("您的账号或密码有误,请重新输入");
     			}else if(result=="1"){//success
     				$("#login_form").submit();
     			}
     		});
     	}
		/* end of ajax */
	</script>	
  </head>
  
  <body>
  	<div id="welcome">
  		<label id="wel1">欢迎使用</label>
  		<label id="wel2">智慧教学平台</label>
  		<div id="loginpanel"></div>
  		<img id="adm" src="image/adm.png" />
  		<img id="pass" src="image/pass.png" />
  		<form id="login_form" action="LoginAction!execute.action" method="post">
  			<input id="adminput" type="text" name="usr" />
  			<input id="passinput" type="password" name="psd" />
  		</form>
  		<input class="btn" id="submit" type="button"
  					 onclick="login();" value="登陆" />
  	</div>
  </body>
</html>
