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
  <br/><br/>
<br/><center><display:table id="idListTb" name="pageView"  style="width:650px;" pagesize="20" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/gmmanage/list!listUserInfo.do">
			<display:caption>玩家管理列表</display:caption>
			<display:column property="id" title="ID" />
			<display:column property="name"  title="登陆名" />
            <display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">封号</c:when>
						<c:when test="${idListTb.state==1}">正常</c:when>
						<c:when test="${idListTb.state==2}">禁言</c:when>
					</c:choose>
				</display:column>
            <display:column property="roleName" title="角色名称" />
            <display:column ><a href="page!updateUserState.do?userId=${idListTb.id}" >修改状态</a></display:column >
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="staff!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
