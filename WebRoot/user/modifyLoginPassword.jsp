<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	

  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="loginPassword!modifyLoginPassword" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<!--<s:hidden name="users.userId" />-->
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="450" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>登录修改密码</caption>
  	  <tr>
        <td width="45">&nbsp;</td>
  	    <td width="105" align="right">旧密码：</td>
        <td width="189" align="left"><s:textfield name="userPassword" value=""/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
       <tr>
        <td width="45">&nbsp;</td>
  	    <td width="105" align="right">新密码：</td>
        <td width="189" align="left"><s:textfield name="modifyLoginPassword.userPassword" value=""/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
      <tr>
        <td width="45">&nbsp;</td>
  	    <td width="105" align="right">请再次输入密码：</td>
        <td width="189" align="left"><s:textfield name="retypeNewUserPassword" value=""/></td>
        <td width="82">&nbsp;</td>
      </tr>
      
      <tr>
        <td>&nbsp;</td>
  	    <td width="105" align="right"><s:submit icon="icon-apply" value="修改"/></td>
        <td width="189"><s:reset icon="icon-reset" value="重设"/></td>
        <td>&nbsp;</td>
      </tr>
  	  
  </table>
  
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
