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
	<link rel="stylesheet" type="text/css" href="css/grouped.css">

  </head>
  
  <body>
  <div id="main">
   	 <div id="topMenu">
     	<div id="icon">
     		<img class="icon" src="image/1.jpg" />
     	</div>
     	<label id="ketangfenzu">课堂分组</label>
     	<div id="mainlink">
     		<input class="btn" type="button" name="host" value="首页"
     				onclick="window.location.href='backhostAction!execute.action'" />
     		<input class="btn" type="button" name="host" value="账户"
     				onclick="window.location.href=''" />
     	</div>
     </div>
     <% int i=1;%>
     <div id="resultpanel">
   		<table id="teamcontent" border="1" cellpadding="1"
                         cellspacing="10">
           <s:iterator value="Users" status="status">
           		<s:if test='#status.index%5==0'>
             		<tr>
             			<td id="teamnum">第<%=i++%>小组</td>
                </s:if>
                        <td id="teammember" valign="middle" align="center">
                            <s:property value="ID"/>
                        </td>
                <s:if test='#status.index%5==4'>
                </s:if>
            </s:iterator>
        </table>
       </div>
     </div>
  </body>
</html>
