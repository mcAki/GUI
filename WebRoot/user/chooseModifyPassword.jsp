<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
		function gotoModifyPassword(operateCode){
			var formname = document.getElementById("form1");
			
			switch(operateCode){
				case 1:
					formname.action='${pageContext.request.contextPath}/usersutils/loginPassword!loginPassword.do';
					break;
					
				case 2:
					formname.action ='${pageContext.request.contextPath}/usersutils/phonePassword!phonePassword.do';
					break;
				
				default:
					alert('操作出错');
					return;
			}
			formname.submit();
		}
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form id="form1" name="form1">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="500" border="0" align="center" cellpadding="10" cellspacing="10" class="form_tb">
    <caption>修改密码</caption>
  	  <tr>
  	    <td width="240"><img src="../images/ico/modifyPassword.gif" alt="" width="80" height="91" /></td>
  	    <td width="20">&nbsp;</td>
  	    <td width="240"><img src="../images/ico/modifyPhonePassword.gif" alt="" width="80" height="91" /></td>
      </tr>
  	  <tr>
  	    <td><p>点击这里修改本人系统登陆密码,密码直接涉及个人资料安全,请慎重保管。</p>
<p>首次注册请立刻进行修改。</p></td>
  	    <td>&nbsp;</td>
  	    <td><p>点击这里修改本人电话操作密码。</p>
        <p>在必要时候使用电话客服中心协助必须在电话中确认此密码。</p></td>
      </tr>
  	  <tr>
  	     <td><input icon="icon-edit" type="button" value="登录密码修改"   onclick="javascript:gotoModifyPassword(1);"/></td>
  	     <td>&nbsp;</td>
  	     <td><input icon="icon-edit" type="button" value="电话操作码修改 " onclick="javascript:gotoModifyPassword(2);"/></td>
      </tr>

  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
