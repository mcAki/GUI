<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index2.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
    	<a href="/VolunteerManage/mission/personal!findPersonalByTeam.do?uuid=402880362ac31a57012ac31d8f620030">当前大队</a>
    	<a href="/VolunteerManage/mission/personal!findPersonalByTeam.do?uuid=402880362ac31a57012ac31d8f620030">当前中队</a>
    	<a href="/VolunteerManage/mission/personal!findPersonalByTeam.do?uuid=402880362ac31a57012ac31d8f620030">当前小队</a><br/>
    	<a href="/VolunteerManage/mission/personal!findTeamPersonal.do?missionid=2&teamId=1">大队招人</a><br/>
    	<a href="/VolunteerManage/mission/personal!findTeamPersonal.do?missionid=2&teamId=2">中队招人</a><br/>
    	<a href="/VolunteerManage/mission/personal!findTeamPersonal.do?missionid=2&teamId=4">小队招人</a><br/>
    	<a href="/VolunteerManage/mission/personal!findTeamAllPersonal.do?missionid=2&teamId=4">添加副队</a><br/>
  </body>
</html>
