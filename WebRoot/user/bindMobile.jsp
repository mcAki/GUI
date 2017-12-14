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
<s:form id="mobileForm" name="mobileForm" action="pageMobile!doUpdateMobile" namespace="/users" method="post">
  <center>
    <s:fielderror></s:fielderror>
  </center>
  <p>&nbsp;</p>
  <p>&nbsp;</p>
  <table width="381" border="0" align="center" cellspacing="3" class="form_tb">
    <caption>
    志愿者绑定手机设置
    </caption>
    <tr>
      <td colspan="2"></td>
    </tr>
    <tr>
      <td width="135" align="right">志愿者姓名:</td>
      <td width="227" align="left"><s:label name="users.userName"/></td>
    </tr>
    <tr>
      <td width="135" align="right">请输入绑定手机号码:</td>
      <td width="227" align="left"><s:textfield name="userUpdateMobile.mobile" /></td>
    </tr>
    <tr>
      <td align="right"><s:submit icon="icon-apply" value="提交" /></td>
      <td align="left">&nbsp;
        <s:reset icon="icon-reset" value="重设" /></td>
    </tr>
  </table>
</s:form>
<%@ include file="../common/incFooter.jsp" %>
</body>
</html>
