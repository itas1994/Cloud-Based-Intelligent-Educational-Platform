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

  </head>
  
  <body>
    <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ceshiqu">测试区</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" /> | 
     		<input class="btn" type="button" name="name" value="发布新测试" 
     			onclick="window.location.href='testZone/testissue.jsp'"/>
     	</div>
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
		<input class="btn" id="backtest" type="button" value="返回测试区"
     			onclick="window.location.href='TestAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
