<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const" %>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil" %>

<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<base target="_self">
	<script type="text/javascript">
		function selectLeader(id,username){
			//alert(id);
			//alert(username);
			var userpack=new Object();
			userpack.uid=id;
			userpack.username=username;
			
			window.returnValue = userpack;
			//alert("----------------");
			window.close(); 
			//alert("closed");
		}
	</script>
  </head>  
  <body>
  
  <%@ include file="../common/incBanner.jsp" %>
   
<br/><br/><center>
		<div align="center">
			<s:form id="form1" name="form0"
			action="personal!showPersonal" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden><input type="hidden" name="teamId" value="${param.teamId }"/>
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				电话号码:
				<input type="text" name="mobile" size="20" value="${param.mobile}"/>
				<input icon="icon-search"  type="submit" value="搜索"/>
			</s:form>
		</div>
<br/><center><display:table id="idListTb" name="pageView"  style="width:400px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/personal!showPersonal.do">
            <display:column property="userName" title="志愿者名" />
            <display:column property="mobile" title="手机号" />
            <display:column title="选择">
            	<a href="javacript:void(0)" onclick="selectLeader('${idListTb.usersByUserid.userId}','${idListTb.userName }')">选择</a>
            </display:column>
		</display:table>
		
		
		
	    </center>
	<br/>	
	<br/>
	
		<script type="text/javascript">
		highlightTableRows("idListTb");
		</script>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
