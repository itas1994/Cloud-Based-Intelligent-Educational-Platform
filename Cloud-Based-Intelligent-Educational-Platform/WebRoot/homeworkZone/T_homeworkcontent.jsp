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
	<link rel="stylesheet" type="text/css" href="css/homework/T_homeworkcontent.css">

  </head>
  
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
     				onclick="window.location.href=''" /> | 
     		<input class="btn" type="button" name="name" value="发布新作业" 
     			onclick="window.location.href='homeworkZone/homeworkissue.jsp'"/>
     	</div>
     </div>
     <div id="resultpanel">
		<div id="homeworkresult">
			<label id="titlestar">◆</label>
			<label id="titlelabel"><s:property value="title" /></label><br><br>
			<label id="contentlabel"><s:property value="content" /></label>
		</div>
		<s:iterator value="holist" var="ho">
			<div id="answerresult">
				<label id="ausr">学生  <s:property value="ausr" /> 在 <s:property value="atime" /> 提交答案:</label><br><br>
				<label id="acontent"><s:property value="acontent"></s:property></label><br><br>
				<s:if test="#ho.aremark=='暂未评价'">
					<form id="form2" action="homeworkRemarkAction!execute.action" method="post">
						<input type="hidden" name="id" value="<s:property value='id' />" />
						<input type="hidden" name="ausr" value="<s:property value='ausr' />" />
						<input type="hidden" name="atime" value="<s:property value='atime' />" />
						<textarea id="atextarea" name="aremark" cols="100" rows="2"></textarea><br>
						<input class="btn" id="submitanswer" type="submit" value="提交作业评价"/>
					</form>
				</s:if>
				<s:else>
					<label id="aremark">教师评语:<s:property value="aremark"></s:property></label><br>
				</s:else>
			</div>
		</s:iterator>
		<input class="btn" id="backhomework" type="button" value="返回作业区"
     			onclick="window.location.href='HomeworkAction!execute.action'"/>
     </div>
    </div>
  </body>
</html>
