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
  <br/>
  <center><display:table id="idListTb" name="statisticsVOs"  style="width:900px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/statistics/list!listStatistics.do">
			<display:column property="missionId" title="ID"></display:column>
            <display:column property="missionSubject" title="项目主题" style="width:250px;" />
          	<display:column property="applyAccepted" title="项目人员数"/>
          	<display:column title="考勤人数">
          		<c:choose>
          			<c:when test="${null==idListTb.logCount}">0</c:when>
          			<c:otherwise>${idListTb.logCount}</c:otherwise>
          		</c:choose>
          	</display:column>
          	<display:column title="出勤人数">
          		<c:choose>
          			<c:when test="${null==idListTb.actuallyLog}">0</c:when>
          			<c:otherwise>${idListTb.actuallyLog}</c:otherwise>
          		</c:choose>
          	</display:column>
          	<display:column title="请假人数" >
          		<c:choose>
          			<c:when test="${null==idListTb.askoff1}">0</c:when>
          			<c:otherwise>${idListTb.askoff1}</c:otherwise>
          		</c:choose>
          	</display:column>
          	<display:column title="旷工人数">
          		<c:choose>
          			<c:when test="${null==idListTb.askoff2}">0</c:when>
          			<c:otherwise>${idListTb.askoff2}</c:otherwise>
          		</c:choose>
          	</display:column>
          	<display:column title="查看">
          		<a href="${pageContext.request.contextPath}/statistics/list!listPersonalByMissionIdForStatistics.do?missionId=${idListTb.missionId}">查看人员考勤情况</a>
          	</display:column>
		</display:table>
	    </center>
	<br/>	
	<br/>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
