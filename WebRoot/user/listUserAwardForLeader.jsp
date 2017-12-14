<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<script type="text/javascript" >
		function batchAddPersonal(itemName) {
			var datas = "";
			var items = document.getElementsByName(itemName);
			for ( var i = 0; i < items.length; i++) {
				if(items[i].type=="checkbox"){
					var pid = items[i].value;
					if (items[i].checked) {
						datas = pid + ","+datas;
					}
				}
			}
			document.form1.action="${pageContext.request.contextPath}/missionservicelog/doDelete.do?missionServiceLogIds="
					+ datas +"&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}";
			
			document.form1.submit();
		}
		</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:form id="form1" name="form1"
			 namespace="/usersutils"
			method="post">
		<center><s:hidden name="selectDate"/>
			<display:table id="idListTb" name="pageView" style="width:800px;" sort="external" pagesize="${pageSize}" 
			cellspacing="1" class="list_tb" requestURI="${pageContext.request.contextPath}/usersutils/listAwardPersonalBySelectDate.do">
				<display:column property="missionTeam.missionTeamname" title="所属队伍" />
				<display:column title="队伍级别">
					<c:choose>
						<c:when test="${idListTb.teamLevel==1}">大队</c:when>
						<c:when test="${idListTb.teamLevel==2}">中队</c:when>
						<c:when test="${idListTb.teamLevel==3}">小队</c:when>
					</c:choose>
				</display:column>
				<display:column maxLength="10" property="users.userName" sortName="id" title="获奖者" />
				<display:column property="awardName" title="奖项名" />
				<display:column property="integralAward" title="奖积分" />
				<display:column property="gainAwardDate" title="获奖时间" format="{0,date,yyyy年MM月dd日}" />
				<display:column title="获奖状态" >
					<c:choose>
						<c:when test="${idListTb.awardState==0}">取消</c:when>
						<c:when test="${idListTb.awardState==1}">正常发放</c:when>
					</c:choose>
				</display:column>
				<display:column title="已确认" >
					<c:choose>
						<c:when test="${idListTb.isCommitted==0}">未确认</c:when>
						<c:when test="${idListTb.isCommitted==1}">已确认</c:when>
					</c:choose>
				</display:column>
				<display:column title="操作">
					<c:choose>
						<c:when test="${idListTb.isCommitted==0}">
							<c:choose>
						<c:when test="${idListTb.awardState==1}">
							<a href="${pageContext.request.contextPath}/usersutils/delete.do?awardId=${idListTb.id}&missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeam.missionTeamId}&selectDate=${idListTb.gainAwardDate}" onclick="return conf();">取消奖励</a>&nbsp;
						</c:when>
						<c:otherwise>
							<a href="${pageContext.request.contextPath}/usersutils/regain.do?awardId=${idListTb.id}&missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeam.missionTeamId}&selectDate=${idListTb.gainAwardDate}" onclick="return conf();">恢复奖励</a>&nbsp;
						</c:otherwise>
					</c:choose>
							<a href="${pageContext.request.contextPath}/usersutils/doCommit.do?awardId=${idListTb.id}&missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeam.missionTeamId}&selectDate=${idListTb.gainAwardDate}" onclick="return conf();">确认</a>&nbsp;
						</c:when>
						<c:when test="${idListTb.isCommitted==1}">
							<a href="${pageContext.request.contextPath}/usersutils/cancelCommit.do?awardId=${idListTb.id}&missionId=${idListTb.mission.missionId}&teamId=${idListTb.missionTeam.missionTeamId}&selectDate=${idListTb.gainAwardDate}" onclick="return conf();">取消确认</a>&nbsp;
						</c:when>
					</c:choose>
				</display:column>
			</display:table>
		</center>
		<br /><br />
		<br />
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
