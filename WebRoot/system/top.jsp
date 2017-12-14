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
function showServername(elem){
	var optionVal= $(elem).find("option:selected").text();
	document.getElementById("serverid").innerHTML = optionVal;
	var serverId = document.getElementById("server").value;
	
	$.ajax({ 
	   url : "changeServer.do", 
	   type : "post", 
	   data : ({serverId:serverId}),
	    success : function(data){ 
	    if(data.result=="success"){ 
	    // alert("修改密码成功"); 
	    }else if(data.result=="failed"){ 
	     alert("请选择正确的服务器!"); 
	    }
	   } 
   
  }); 
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
        <td width="74%" height="38" class="admin_txt"><span style="color:#F30;">${user.adminGroup.adminGroupName}</span> <b>${user.userName}</b> 您好, 欢迎使用！</td>
        <td width="22%"><a href="javascript:logout();" onmouseout="MM_swapImgRestore()" onmouseover="MM_swapImage('btnLogout','','images/out_o.gif',1)"><img src="images/out.gif" name="btnLogout" width="46" height="21" border="0" id="btnLogout" /></td>
        <td width="4%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table></td>
  </tr>
  <tr>
  	<td valign="top" align="right">选择服务器:当前<span id="serverid">${sessionScope.server.servername }</span>服&nbsp;</td>
    <td valign="top" align="left" class="admin_txt"><s:select cssClass="txtW2" id="server" list="servers" listKey="id" listValue="servername" onclick="showServername(this)" /></td>
  </tr>
</table>
</body>
</html>
