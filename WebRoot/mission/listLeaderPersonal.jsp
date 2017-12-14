<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
   <br/><br/><br/>
  <center>	<display:table id="idListTb" name="pageView"  style="width:750px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/personal!listLeaderPersonal.do">
			<display:column property="mission.missionId" title="ID"></display:column>
            <display:column property="missionSubject" title="项目主题" style="width:200px;" />
            <display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.missionStartDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.missionEndDate}"/>
            </display:column>
            <display:column property="missionState.stateName" title="项目状态" style="font-weight:bold;"/>
            <display:column title="配岗">				
				<a href="${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeam.missionTeamId}" >人岗配置与综合管理</a>
			</display:column>
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
