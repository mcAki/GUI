<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
			<display:table id="idListTb" name="pageView" style="width:600px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/listUserTasked!listUserTasked.do">
				<display:column property="missionSubject" title="任务名称" />
				<display:column title="任务开始时间" >
				<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.missionStartDate}"/>
				</display:column>
				<display:column title="任务结束时间" >
				<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.missionEndDate}"/>
				</display:column>
				<display:column title="用户申请时间" >
				<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.userApplyDate}"/>
				</display:column>
				<display:column title="操作">
					<a href="${pageContext.request.contextPath }/users/listUserTasked!renounceMission.do?missionId=${idListTb.mission.missionId}">放弃任务</a>&nbsp;
					<a href="${pageContext.request.contextPath }/usersutils/applyMission!listApplyMission.do">返回任务列表</a>
				</display:column>
			</display:table>
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
