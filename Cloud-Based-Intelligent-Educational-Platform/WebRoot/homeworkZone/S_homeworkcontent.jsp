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
    
    <title>作业内容</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/homework/S_homeworkcontent.css">

  </head>
  <script language="javascript">
  		function getId(){
  			var id=document.getElementsById("id").InnerHTML;
  			return id;
  		}
  </script>
  <body>
    <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="zuoyequ">作业区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
     <div id="resultpanel">
		<div id="homeworkresult">
			<label id="titlestar">◆</label>
			<label id="titlelabel"><s:property value="title" /></label><br><br>
			<label id="contentlabel"><s:property value="content" /></label>
			<label id="id"><s:property value="id" /></label>
		</div>
		<div id="answerpanel">
			<form id="form2" action="homeworkAnswerAction!execute.action" method="post">
				<input type="hidden" name="id" value=getId() />
				<textarea id="atextarea" name="acontent" cols="100" rows="12"></textarea><br>
				<input class="btn" id="submitanswer" type="submit" value="提交作业"/>
			</form><br>
		</div>
		<div id="answerresult">
			<s:iterator value="holist">
				<label id="ausr"><s:property value="ausr"></s:property> 提交答案:</label><br>
				<label id="acontent"><s:property value="acontent"></s:property></label><br>
				<label id="atime">提交时间:<s:property value="atime"></s:property>&nbsp;&nbsp;</label><br><br>
				<label id="aremark"><s:property value="aremark"></s:property></label><br>
			</s:iterator>
		</div>
		<input class="btn" id="backhomework" type="button" value="返回作业区"
     			onclick="window.location.href='HomeworkAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
