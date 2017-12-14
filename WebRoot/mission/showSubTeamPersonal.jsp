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
	
	function searchUser(){
		var checked=document.getElementById("chk").checked;
		if(checked){
			document.searchForm.action='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?checked=1';
		}else{
			document.searchForm.action='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?';
		}
		document.searchForm.submit();
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
						<input icon="icon-example" type="button" value="按志愿时排序" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId }&param=1'"/>&nbsp;
						<input icon="icon-example" type="button" value="按本任务志愿时积分排序" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId }&param=2'"/>&nbsp;
						<input icon="icon-example" type="button" value="按个人志愿时积分排序" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do?missionId=${missionTeam.mission.missionId}&teamId=${missionTeam.missionTeamId }&param=3'"/>&nbsp;
							<input icon="icon-reset" type="button" value="返回父队伍"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${missionTeam.missionTeamId }'" />&nbsp;
						</td>
					</tr>

				</table>
			</c:if>
		</s:form>
		<br />
		<br />

		<c:if test="${null!=missionTeam}">
		<center>
		<div align="center">
			<s:form id="searchForm" name="searchForm"
			action="list!showSubTeamPersonal" namespace="/missionTeam"
			method="post"><s:hidden name="teamId"></s:hidden>
				
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				身份证:
				<input type="text" name="idcardCode" size="25" value="${param.idcardCode}"/>
				电话号码:
				<input type="text" name="mobile" size="20" value="${param.mobile}"/>
				<input icon="icon-search"  type="button" value="搜索" onclick="searchUser()"/>
				<br/>
				<input type="checkbox" name="chk" id="chk" />(姓名模糊查询)
			</s:form>
		</div>
		</center>
				<br />
				
			<br />
			<center>
			<display:table export="true" id="list" name="list" style="width:750px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/missionTeam/list!showSubTeamPersonal.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="队员列表">队员列表</display:caption>
				<display:column property="missionTeam.missionTeamname" title="所属队伍" />
				<display:column property="missionTeam.teamLevel" title="队伍级别" media="html" />
				<display:column property="userName" title="队员姓名" media="excel" />	
				<display:column title="队员姓名" media="html">
					<a onclick="showUserProfile('${list.usersByUserid.userId}','${list.missionTeam.missionTeamId }','${list.mission.missionId }')">
						<c:choose>
							<c:when
								test="${list.missionPosition.missionPositionId==15}">
								<b class="leader">${list.userName}(大队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==13}">
								<b class="leader">${list.userName}(中队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==11}">
								<b class="leader">${list.userName}(小队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==14}">
								<b class="subleader">${list.userName}(副大队)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==12}">
								<b class="subleader">${list.userName}(副中队)</b>
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
				<display:column property="usersByUserid.idcardCode" title="身份证号码" media="excel"/>
				<display:column property="mobile" title="手机号码" />
				<display:column title="服务时长" >
					<fmt:formatNumber maxFractionDigits="1" value="${list.userServiceTime/60}"/>
				</display:column>
				<display:column  title="当前任务总积分" >
					<fmt:formatNumber maxFractionDigits="1" value="${list.userIntegral }"/>
				</display:column>
				<display:column  title="个人总积分" >
					<fmt:formatNumber maxFractionDigits="1" value="${list.usersByUserid.integral }"/>
				</display:column>
				<display:column title="可获徽章">
					<c:choose>
						<c:when test="${list.usersByUserid.integral>100&&list.usersByUserid.integral<200 }">铜</c:when>
						<c:when test="${list.usersByUserid.integral>=200&&list.usersByUserid.integral<300 }">银</c:when>
						<c:when test="${list.usersByUserid.integral>=300 }">金</c:when>
					</c:choose>
				</display:column>
				<display:column title="考勤" media="html">
					<a href="${pageContext.request.contextPath}/missionservicelog/listPersonalServiceLog.do?missionId=${list.mission.missionId}&userId=${list.usersByUserid.userId}&teamId=${list.missionTeam.missionTeamId }">考勤列表</a>&nbsp;
				</display:column>
				<display:setProperty name="export.excel.filename" value="times.xls"/>     				
			</display:table></center>
		</c:if>


		


		<br />
		<br />
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
