<%@ page language="java" pageEncoding="UTF-8"%>

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
			document.searchForm.action='${pageContext.request.contextPath}/statistics/list!listPersonalByMissionIdForStatistics.do?checked=1';
		}else{
			document.searchForm.action='${pageContext.request.contextPath}/statistics/list!listPersonalByMissionIdForStatistics.do';
		}
		document.searchForm.submit();
	}
	
	
	
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		
		<br />
		<br />


		<center>
		<div align="center">
			<s:form id="searchForm" name="searchForm"
			action="list!listPersonalByMissionIdForStatistics" namespace="/statistics"
			method="post"><s:hidden name="missionId"></s:hidden>
				
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
			<display:table id="list" name="pageView" style="width:650px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/statistics/list!listPersonalByMissionIdForStatistics.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="队员列表">队员列表</display:caption>
				<display:column property="missionTeam.missionTeamname" title="所属队伍" />
				<display:column property="missionTeam.teamLevel" title="所属队伍级别" />
				<display:column title="队员姓名">
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
				<display:column property="mobile" title="手机号码" />
				<display:column title="服务时长" >
				<fmt:formatNumber maxFractionDigits="0" value="${list.userServiceTime/60-list.userServiceTime%60/60}"/>:
				<c:choose>
					<c:when test="${list.userServiceTime%60==0 }">${list.userServiceTime%60 }0</c:when>
					<c:otherwise>${list.userServiceTime%60 }</c:otherwise>
				</c:choose>
				</display:column>
				<display:column  title="志愿时积分" >
					<fmt:formatNumber maxFractionDigits="1" value="${list.userIntegral }"/>
				</display:column>
				<display:column title="查看">
					<a href="${pageContext.request.contextPath}/missionservicelog/listPersonalServiceLog.do?missionId=${list.mission.missionId}&userId=${list.usersByUserid.userId}">查看该人员考勤记录</a>
				</display:column>
			</display:table> </center>
		<br />
		<br />

		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
