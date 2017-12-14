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
<br/><center><display:table id="idListTb" name="pageView"  style="width:650px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/admin/list.do">
			<display:caption>系统用户管理</display:caption>
			<display:column property="id" sortName="id" sortable="true" title="ID" />
			<display:column property="userName" sortName="id"  title="管理员名" />
            <display:column property="adminGroup.adminGroupName" title="用户组别" />
            <display:column property="realName" title="真名" />
            <display:column property="status" title="状态" />
            <display:column property="loginCounts" title="登陆次数" />
            <display:column property="lastLoginIp" title="上次登陆IP" />
            <display:column ><a href="page!update.do?id=${idListTb.id}" >修改</a></display:column >
            <display:column ><a href="list!del.do?id=${idListTb.id}&p=${p}" onclick="javascript:return del()">删除</a></display:column >		
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="staff!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
