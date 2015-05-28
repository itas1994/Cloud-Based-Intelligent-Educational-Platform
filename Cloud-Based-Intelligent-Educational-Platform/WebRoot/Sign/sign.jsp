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
    
    <title>自主签到</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/sign.css">

  </head>
  
  <body>
  <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="zizhuqiandao">自主签到</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='host.jsp'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
     <div id="resultpanel">
     	<div id="resultcontent">
    		<s:iterator value="classes1">
				<table id="table"  cellspacing="15" border = "2">
					<tr>
						<td class="t1">
							<s:property value="s_class1" ></s:property>
							<input class="btn" type="button" value="签到" 
     							onclick="window.location.href='Sign/success.jsp'" />
						</td>
					</tr>
					<tr>
						<td class="t1">
							<s:property value="s_class2" ></s:property>
							<input class="btn" type="button" value="签到" 
     							onclick="window.location.href='Sign/success.jsp'" />
						</td>
					</tr>
					<tr>
						<td class="t1">
							<s:property value="s_class3" ></s:property>
							<input class="btn" type="button" value="签到" 
     							onclick="window.location.href='Sign/success.jsp'" />
						</td>
					</tr>
			</table>
	</s:iterator>
	</div>
	</div>
	</div>	
  </body>
</html>
