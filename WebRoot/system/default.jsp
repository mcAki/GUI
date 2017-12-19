<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<%@ include file="../common/incHead.jsp" %>
<title>管理页面</title>
<style>
body {
	font:12px Arial, Helvetica, sans-serif;
	color: #000;
	background-color: #F5FFEA;
	margin: 0px;
}
</style>
</head>
<body leftmargin="0" topmargin="0" align="center"  >
<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>用户信息</caption>
  	  <tr>
  	    <td colspan="5"></td>
      </tr>
      
  	  <tr>
  	    <td align="right">用 户:</td> 
  	    <td align="left">${user.userName }</td>
  	    <td align="right">余 额:</td>
        <td align="left"><c:choose>
        	<c:when test="${useraccount.balance<=user.defaultAlarm}"><font color="red">${useraccount.balance }</font>,你的余额已低于警示,请尽快充值!</c:when>
        	<c:otherwise>${useraccount.balance}</c:otherwise>
        </c:choose></td>
        <td>&nbsp;</td>
      </tr>
  </table>

</body>
</html>
