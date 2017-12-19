<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
     <script type="text/javascript">
        //document.all("lab_1").contentEditable = "true";
      </script>
  </head>  
  <body contenteditable="contenteditable">
  
  <%@ include file="../common/incBanner.jsp" %>
  	<s:form name="strutsForm" action="showUserProfile" namespace="/usersutils" method="post">
  	<center><s:fielderror></s:fielderror></center>
  	<s:token/>
  	<s:hidden name="users.userId" />
  	<p>&nbsp;</p>
  	<p>&nbsp;</p>
  	<table width="600" border="0" align="center" cellspacing="3" class="form_tb" >
    <caption>个人基本信息</caption>
  	  <tr>
  	    <td colspan="4"></td>
      </tr>
     
  	  <tr>
      	<td width="121" align="right">用户名称:</td>
  	    <td align="left"><s:label name="users.userName"/></td>
  	    <td width="134" align="right">登录名称:</td>
  	    <td width="134" align="left"><s:label name="users.loginName"/></td>
  	  </tr>
      
  	  <tr>
	   <td align="right" >性&nbsp;别:</td>
	   <td align="left"><s:label>
	    					<c:choose>
	    						<c:when test="${users.gender==1}">男</c:when>
	    						<c:when test="${users.gender==2}">女</c:when>
	    					</c:choose>
		  	    		</s:label>
						</td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
      </tr>
      
      <tr>
  	   <td align="right">email:</td>
  	   <td align="left" ><s:label name="users.email"/></td>
  	   <td>&nbsp;</td>
  	   <td>&nbsp;</td>
  	  </tr>
      
      <tr>
  	    <td align="right">手机号码:</td>
  	    <td align="left" ><s:label name="users.mobile"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
      
      <tr>
  	    <td align="right">账户余额:</td>
  	    <td align="left" ><s:label name="useraccount.balance"/></td>
  	    <td>&nbsp;</td>
  	    <td>&nbsp;</td>
      </tr>
       
       <tr>
        <td align="right" >备&nbsp;注:</td>
  	    <td align="left" colspan="3"><s:label name="users.remark" rows="3"/></td> 	  
       </tr>
		
		<tr>
		   <td align="right">操&nbsp;作:</td>
		   <td colspan="3">
		     <c:if test="${(sessionScope.user.usergroup.groupType<=3) || (sessionScope.user.userId==users.parentUsers.userId)}">
						<a href="/MPRS/users/modifyUser!viewUser.do?userId=${users.userId}">修改</a>&nbsp;
						<a href="/MPRS/users/page!recharge.do?userId=${users.userId}">设置额度</a>&nbsp;
						<c:choose>
							<c:when test="${users.state==0}">
								<a href="/MPRS/users/list!delUser.do?userId=${users.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${users.state==2}">
								<a href="/MPRS/users/list!recoverUser.do?userId=${users.userId}">恢复</a>
							</c:when>
						</c:choose>
						&nbsp;<a href="/MPRS/users/commitAccount.do?userId=${users.userId}">更新账户余额</a>&nbsp;
					</c:if>
					<c:if test="${users.usergroup.groupType<5}">
						<a href="/MPRS/users/page!addExEx.do?userId=${users.userId}">增加二级商户</a>
					</c:if>
					
					<c:if test="${sessionScope.user.usergroup.groupType<=2}">
						<a href="/MPRS/users/page!addKey.do?userId=${users.userId}">设置加密key</a>
						<a href="${pageContext.request.contextPath}/otp/page!testPage.do?userId=${users.userId }">测试密保</a>
					</c:if>
					<%--
					 --%>
					<c:if test="${(sessionScope.user.usergroup.groupType<=4||sessionScope.user.userId==users.userId)&&users.usergroup.groupType==4}">
						<a href="/MPRS/users/list!manageUserEx.do?userId=${users.userId}">查看</a>
					</c:if>
		   </td>
		</tr>
  </table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
