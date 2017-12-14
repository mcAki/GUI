<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const"%>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil"%>

<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
				<script type="text/javascript">
	function showUserProfile(userId, teamId, missionId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showUserProfile.do?userId='
						+ userId + '&teamId=' + teamId + '&missionId='
						+ missionId, 700, 600);
		//jq检测是否为空
		//if(!$.isEmptyObject(returnVal)){
		//赋值!
		//	$("#userId").val(returnVal.uid);
		//	$("#currentLeaderName").val(returnVal.username);
		//}
	}
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:form name="form1" action="list" namespace="/missionTeam"
			method="post">
			<c:if test="${null!=missionTeam}">
				<table width="800" border="0" align="center" cellspacing="4"
					class="form_tb">
					<tr>
						<td colspan="4" align="center">
							<h4>
								<strong>当前任务:ID ${missionTeam.mission.missionId} 名称 ${missionTeam.mission.subject }</strong>
							</h4>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<h4>
								<strong>当前层级:<%=request.getAttribute("inheritRelation")%></strong>
							</h4>
						</td>
					</tr>
					<tr style="border: double 3px;">
						<td align="right">
							<h2>
								<strong> <%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam) request.getAttribute("missionTeam")).getTeamLevel()]%>: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong>${missionTeam.missionTeamname}</strong>
							</h2>
						</td>
						<td align="right">
							<h2>
								<strong> 队 长: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong> ${missionTeam.currentLeaderName} </strong>
							</h2>
						</td>
					</tr>


					<tr>
						<td colspan="4" align="center">
							<s:hidden name="teamId"></s:hidden>
							<input icon="icon-example" type="button" value="修改队伍资料"
								onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/page!update.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />
							<c:choose>
								<c:when
									test="${missionTeam.teamLevel==1||missionTeam.teamLevel==2}">
									<input icon="icon-add-row" type="button" value="增加子队伍"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/page!add.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />&nbsp;
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${null!=missionTeam&&missionTeam.teamLevel!=3}">
									<input icon="icon-offset" type="button" value="设定副队长"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findTeamAllPersonal.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />&nbsp;
								</c:when>
								<c:otherwise>
									<input icon="icon-offset" type="button" value="设定监督员"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findTeamSupervisor.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId}'" />&nbsp;
								</c:otherwise>
							</c:choose>
							<input icon="icon-forum" type="button" value="队 员 排 岗"
								onclick="javascript:window.location.href='${pageContext.request.contextPath}/mission/personal!findTeamPersonal.do?missionId=${missionTeam.mission.missionId }&teamId=${missionTeam.missionTeamId}'" />

							<input icon="icon-print" type="button" value="下载打印考勤表"
								onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionservicelog/exportExcel.do?missionTeamId=${missionTeam.missionTeamId}'" />

						</td>
					</tr>
					<tr>
						<td colspan="4" style="border: double 3px;">
							<c:choose>
								<c:when
									test="${missionTeam.teamLevel!=missionTeam.mission.highestPosition}">
									<input icon="icon-reset" type="button" value="返回父队伍"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${missionTeam.missionTeam.missionTeamId }'" />&nbsp;
								</c:when>
								<c:otherwise>
									<input icon="icon-reset" type="button" value="返回任务列表"
										onclick="window.location.href='${pageContext.request.contextPath}/mission/list!manage.do?missionId=${mission.missionId }'" />
									&nbsp;
									<input icon="icon-reset" type="button" value="查看奖励列表"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/usersutils/selectDateForSmileStar.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId }'" />&nbsp;
										<input icon="icon-reset" type="button" value="查看未确认考勤列表"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionservicelog/listPersonalServiceLog.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId }&isUpdated=0'" />&nbsp;
								</c:otherwise>
							</c:choose>
							<input icon="icon-forum" type="button" value="查看所有队员"
										onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?missionId=${mission.missionId }&teamId=${missionTeam.missionTeamId }'" />
						</td>
					</tr>
				</table>
			</c:if>
		</s:form>
		<br />
		<br />

		<c:if test="${null!=missionTeam}">
			<br />
			<center>
			<display:table id="list" name="list" style="width:650px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="队员列表">队员列表</display:caption>
				<display:column title="队员姓名">
					<a
						onclick="showUserProfile('${list.usersByUserid.userId}','${list.missionTeam.missionTeamId }','${list.mission.missionId }')">
						<c:choose>
							<c:when
								test="${list.missionPosition.missionPositionId==15
					||list.missionPosition.missionPositionId==13
					||list.missionPosition.missionPositionId==11}">
								<b class="leader">${list.userName}(队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==14
					||list.missionPosition.missionPositionId==12}">
								<b class="subleader">${list.userName}(副队)</b>
							</c:when>
							<c:when test="${list.missionPosition.missionPositionId==3}">
								<b class="assleader">${list.userName}(监督)</b>
							</c:when>
							<c:otherwise>
						${list.userName}					
					</c:otherwise>
						</c:choose>
					</a>
				</display:column>
				<display:column property="mobile" title="手机号码" />
				<display:column title="操作">
					<a href="${pageContext.request.contextPath}/awardPersonal/page!add.do?missionId=${list.mission.missionId}&userId=${list.usersByUserid.userId}&teamId=${list.missionTeam.missionTeamId }&&mark=0">奖励</a>&nbsp;
            		<a href="${pageContext.request.contextPath}/awardPersonal/page!add.do?missionId=${list.mission.missionId}&userId=${list.usersByUserid.userId}&teamId=${list.missionTeam.missionTeamId }&mark=1">扣分</a>&nbsp;
            		<a href="${pageContext.request.contextPath}/missionservicelog/listPersonalServiceLog.do?missionId=${list.mission.missionId}&userId=${list.usersByUserid.userId}&teamId=${list.missionTeam.missionTeamId }">考勤列表</a>&nbsp;
            </display:column>
			</display:table> </center>
		</c:if>


		<br />
		<center> <c:if test="${missionTeam.teamLevel!=3}">
			<display:table id="idListTb" name="pageView" style="width:650px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="本队子队">
					子队列表
				</display:caption>
				<display:column property="missionTeamname" title="队伍名称" />
				<display:column property="currentLeaderName" title="队长" />
				<display:column title="队伍类型">
					<%=Const.MISSION_TEAM_TYPE_NAMES[((MissionTeam) pageContext.getAttribute("idListTb")).getTeamType()]%>
				</display:column>
				<display:column title="队伍级别">
					<%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam) pageContext.getAttribute("idListTb")).getTeamLevel()]%>
				</display:column>
				<display:column property="count" title="队伍人数" />
				<display:column title="操作">
					<c:choose>
						<c:when test="${null!=idListTb.teamLevel}">
							<a
								href="${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${idListTb.missionTeamId}">查看支队</a>&nbsp;
							<a
								href="${pageContext.request.contextPath}/missionservicelog/exportExcel.do?missionTeamId=${idListTb.missionTeamId}">下载打印考勤表</a>&nbsp;
					</c:when>
					</c:choose>

					<!-- missionId在session里获得 -->
					<!--
                <a href="${pageContext.request.contextPath}/missionTeam/page!update.do?missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeamId}">修改队伍</a>&nbsp;
				-->
				</display:column>
			</display:table>
		</c:if> </center>


		<br />
		<br />

		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
