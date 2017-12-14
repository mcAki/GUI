<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const"%>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<s:form name="form1" action="list" namespace="/missionTeam"
			method="post">
			<c:if test="${null!=currentMissionTeam}">
				<table width="800" border="0" align="center" cellspacing="4"
					class="form_tb">
					<tr style="border: double 3px;">
						<td align="right">
							<h2>
								<strong> <%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam) request.getAttribute("currentMissionTeam")).getTeamLevel()]%>: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong>${currentMissionTeam.missionTeamname}</strong>
							</h2>
						</td>
						<td align="right">
							<h2>
								<strong> 队 长: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong> ${currentMissionTeam.currentLeaderName} </strong>
							</h2>
						</td>
					</tr>

				</table>
			</c:if>
		</s:form>
		<div align="center">
			<s:form id="form1" name="form0"
			action="personal!findTeamSupervisor" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden><input type="hidden" name="teamId" value="${param.teamId }"/>
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				电话号码:
				<input type="text" name="mobile" size="20" value="${param.mobile}"/>
				<input icon="icon-search"  type="submit" value="搜索"/>
			</s:form>
		</div>
		<table width="800" border="0" align="center">
			<tr>
				<td>
					<center><s:hidden name="missionId"></s:hidden>
						<display:table id="dtable" name="pageView" style="width:650px;"
							sort="external" pagesize="${pageSize}" cellspacing="1"
							class="list_tb"
							requestURI="${pageContext.request.contextPath}/mission/personal!findTeamSupervisor.do">
							<display:caption>当前队伍中志愿者</display:caption>
							<display:column title="登录名" property="usersByUserid.loginName"></display:column>
							<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
							<display:column title="性别">
								<c:choose>
									<c:when test="${dtable.usersByUserid.gender==1}">男</c:when>
									<c:when test="${dtable.usersByUserid.gender==2}">女</c:when>
									<c:otherwise>未知</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="手机号码" property="usersByUserid.mobile"></display:column>
							<display:column title="注册时间" property="usersByUserid.createDate"></display:column>
							<display:column title="加入时间" property="selectedDatetime"></display:column>
							<display:column title="操作">
							<a href="${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?uuid=${listtable.usersByUserid.userId}">查看</a>&nbsp;
								<a href="${pageContext.request.contextPath}/mission/personal!addTeamSupervisor.do?uuid=${dtable.usersByUserid.userId}&teamId=${param.teamId}&missionId=${missionId}" onclick="return conf();">添加监督员</a>
							</display:column>
						</display:table>
					</center>
				</td>
				<td>
					<center>
						<display:table name="list" id="listtable" style="width:250px;"
							sort="external" cellspacing="1" class="list_tb"
							requestURI="${pageContext.request.contextPath}/mission/personal!findTeamSupervisor.do">
							<display:caption>
								<c:choose>
									<c:when test="${listtable.missionTeam.teamLevel==3}">当前小队下所有监督员</c:when>
								</c:choose>
							</display:caption>
							<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
							<display:column title="手机号码" property="usersByUserid.mobile"></display:column>
							<display:column title="操作">
								<a href="#">查看</a>
										<a href="${pageContext.request.contextPath}/mission/personal!delTeamSupervisor.do?uuid=${listtable.usersByUserid.userId}&teamId=${param.teamId}" onclick="return conf();">撤下监督员</a>&nbsp;
							</display:column>
						</display:table>
						<input type="button" id="idBackBtn" icon="icon-back" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${param.teamId}'"/>
					</center>
				</td>
			</tr>
		</table>
		</center>
		 <s:fielderror/>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
