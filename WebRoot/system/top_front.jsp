<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="../common/incHead.jsp" %>

<title>管理页面</title>
<meta http-equiv=Content-Type content=text/html;charset=utf-8>
<script type="text/javascript" language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
function logout(){
	if (confirm("您确定要退出控制面板吗？")){
		parent.location = "${pageContext.request.contextPath}/logout.do";
		return false;
	}else{
		return;
	}
}
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>

<link href="images/skin.css" rel="stylesheet" type="text/css">
</head>
<body leftmargin="0" topmargin="0">
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="49%" height="64">&nbsp;</td>
    <td width="51%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="74%" height="38" class="admin_txt"><span style="color:#F30;">${user.usergroup.groupName}</span> <b>${user.userName}</b> 您好, 欢迎使用！</td>
        <td width="22%"><a href="javascript:logout();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('btnLogout','','images/out_o.gif',1)"><img src="images/out.gif" name="btnLogout" width="46" height="21" border="0" id="btnLogout" /></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
<div style="position:absolute;right:120px;">
 <!-- WPA Button Begin -->
<script type="text/javascript" src="http://static.b.qq.com/account/bizqq/js/wpa.js?wty=1&type=4&kfuin=4000307517&ws=http%3A%2F%2F4000307517.114.qq.com%2F&btn1=%E4%BC%81%E4%B8%9AQQ%E4%BA%A4%E8%B0%88&aty=0&a=&key=%0Ff%054Qd%050VaT2W%60%05i%03b%012PiT%3A%056%07i%5C%3AU3%0FeQfWkVe"></script>
<!-- WPA Button END -->
</div>
<div style="position:absolute;left:10px;width:10px;"> 
 <img src="images/11qqkefu.jpg"></img> 
</div>
<div style="position:absolute;left:320px;height:50px;">
 <img src="images/time11.jpg"></img>
</div>
</table> 
</body>
</html>
