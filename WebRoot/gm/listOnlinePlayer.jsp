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
  <s:property value="#request.message"/>
  <br /><center>
							<s:form id="form1" name="form0"
							action="list!listOnlinePlayer.do" namespace="/gmmanage"
							method="post">
								<table >
									<tr >
									    <td>玩家昵称:<s:textfield name="rolename"/></td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</s:form></center>
		<br />
		<br />
<br/><center>
		<display:table id="idListTb" name="pageView"  style="width:650px;" pagesize="20" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/gmmanage/list!listOnlinePlayer.do">
			<display:caption>在线玩家列表</display:caption>
            <display:column property="roleName" title="角色名称" />
            <display:column property="level" title="等级" />
			<display:column property="vipLv"  title="VIP等级" />
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="staff!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
