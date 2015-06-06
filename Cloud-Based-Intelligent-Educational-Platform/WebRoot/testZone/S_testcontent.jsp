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
	<link rel="stylesheet" type="text/css" href="css/S_testcontent.css">

  </head>
  <script language="javascript">
  		function getId(){
  			var id=document.getElementsByTagName("id").value();
  			return id;
  		}
  </script>
  <body>
    <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ceshiqu">测试区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
     <div id="resultpanel">
		<div id="testresult">
			<label id="titlestar">◆</label>
			<label id="titlelabel"><s:property value="title" /></label><br><br>
			<label id="contentlabel"><s:property value="content" /></label>
			<label id="id"><s:property value="id" /></label>
			<s:if test='<s:property value="hasMine" />== false'>
				<form id="form2" action="testAnswerAction!execute.action" method="post">
					<input type="hidden" name="id" value=getId() />
					<textarea id="atextarea" name="acontent" cols="100" rows="12"></textarea><br>
					<input class="btn" id="submitanswer" type="submit" value="提交测验"/>
				</form><br>
			</s:if>
			<s:iterator value="telist">
				<label id="ausr"><s:property value="ausr"></s:property> 提交答案:</label><br>
				<label id="acontent"><s:property value="acontent"></s:property></label><br>
				<label id="atime">提交时间:<s:property value="atime"></s:property>&nbsp;&nbsp;</label><br><br>
				<label id="ascore"><s:property value="ascore"></s:property></label><br>
			</s:iterator>
		</div>
		<input class="btn" id="backtest" type="button" value="返回作业区"
     			onclick="window.location.href='TestAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
