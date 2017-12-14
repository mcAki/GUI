<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>管理中心</title>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<style type="text/css">
<!--
BODY {
	LINE-HEIGHT: 24px; 
	MARGIN: 0px; 
	FONT-FAMILY: "宋体", Tahoma, Arial; COLOR: #333; FONT-SIZE: 12px; 
	PADDING-TOP: 0px;
	PADDING-LEFT: 0px; 
	PADDING-RIGHT: 0px; 
	PADDING-BOTTOM: 0px;
	background-color:#F5FFEA;
}
-->
</style>
</head>
<frameset rows="100,*"  frameborder="NO" border="0" framespacing="0" style="background-color:#F5FFEA;">
	<frame src="frame!top.html" noresize="noresize" frameborder="NO" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="main" />
  <frameset cols="200,*" id="frame">
	<frame src="frame!left.html" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
	<frame src="frame!defPage.html" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
  </frameset>
<noframes>
  <body></body>
    </noframes>
</html>
