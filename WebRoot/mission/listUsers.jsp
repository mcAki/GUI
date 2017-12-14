<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const" %>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <s:form name="form1" action="list" namespace="/missionTeam" method="post">
  	<tr>
  	  <td align="right">
  	    <tr>
  	      <td width="394" align="left" >
  	        <tr>
  	          <td align="left" >
              <table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					<h4>
						<strong><s:property value="inheritRelation"/></strong>
					</h4>
				</caption>
				<tr>
					<td colspan="5"></td>
				</tr>
				<tr>
					<td align="right">
						本级队长:
					</td>
					<td align="left">
						<s:textfield name="missionTeam.currentLeaderName" readonly="true"/>
					</td>
					<td align="right">
						所属队伍:
					</td>
					<td align="left">
						<s:textfield name="missionTeam.missionTeamname" readonly="true"/>
					</td>
					<td>
						
					</td>
								
				</tr>
				<tr>
				</tr>
				<td colspan="5">
				&nbsp;
				</td>
				<tr>
				<td colspan="5">
				<input icon="icon-example" type="button" value="修改队伍资料" onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/page!update.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />
				<c:choose>
					<c:when test="${missionTeam.teamLevel==1||missionTeam.teamLevel==2}">
						<input icon="icon-add-row" type="button" value="增加子队伍" onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/page!add.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />
					</c:when>
				</c:choose>				
				<c:choose>
					<c:when test="${null!=missionTeam&&missionTeam.teamLevel!=3}">				
						<input icon="icon-offset" type="button" value="设定副队长" onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findTeamAllPersonal.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />&nbsp;
					</c:when>
				</c:choose><input icon="icon-forum" type="button" value="队 员 排 岗" onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findTeamPersonal.do?missionId=${missionTeam.mission.missionId }&teamId=${missionTeam.missionTeamId}'" />
				<c:choose>
					<c:when test="${null!=missionTeam&&missionTeam.mission.missionState.missionStateId==50}">
						<input icon="icon-edit" type="button" value="本队队员考勤" onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionservicelog/list!queryServiceLog.do?missionTeamId=${missionTeam.missionTeamId }'" />						
					</c:when>
				</c:choose>
				</td></tr>
				<tr><td colspan="4" style="border: double 3px;">
		<c:choose>
			<c:when test="${missionTeam.teamLevel==2||missionTeam.teamLevel==3}">
				<input icon="icon-reset" type="button" value="返回父队伍" onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${missionTeam.missionTeam.missionTeamId }'" />&nbsp;
			</c:when>
		</c:choose>
		<input icon="icon-reset" type="button" value="返回任务列表" onclick="window.location.href='${pageContext.request.contextPath}/mission/list!manage.do?missionId=${mission.missionId }'"/>&nbsp;
		</td>
	        </tr>
			</table>
			</s:form>
  <br/><br/>
 
<br/><center>
			
			<display:table id="idListTb" name="pageView"  style="width:650px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/missionTeam/list!listMember.do">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:caption title="本队队员列表">本队队员列表</display:caption>
            <display:column property="userName" title="用户姓名" />
            <display:column property="mobile" title="手机号码" />
            <display:column title="操作">
            	<a href="${pageContext.request.contextPath}/awardPersonal/page!add.do?missionId=${idListTb.mission.missionId}&userId=${list.usersByUserid.userId}&mark=0">颁奖</a>&nbsp;
            	<a href="${pageContext.request.contextPath}/awardPersonal/page!add.do?missionId=${idListTb.mission.missionId}&userId=${list.usersByUserid.userId}&mark=1">惩罚</a>&nbsp;
            </display:column>
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
