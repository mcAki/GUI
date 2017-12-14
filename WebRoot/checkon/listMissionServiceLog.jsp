<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">  
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp" %>
	</head>  
	<body>
		<%@ include file="../common/incBanner.jsp" %>
		<br/><br/><br/>
		<center>
			<display:table id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/checkon/listAttendence!listAttendence.do">
				<display:column property="missionSubject" title="项目主题" />
				<display:column property="userName" title="报道志愿者"/>
				<display:column property="enterUsername" title="录入人" />
				<display:column title="出勤状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">未报道</c:when>
						<c:when test="${idListTb.state==0}">报道(开始)</c:when>
						<c:when test="${idListTb.state==0}">报道(离开)</c:when>
						<c:when test="${idListTb.state==0}">报道超时</c:when>
					</c:choose>
				</display:column>
				<display:column title="请假情况" >
					<c:choose>
						<c:when test="${idListTb.askoff==0}">正常出勤</c:when>
						<c:when test="${idListTb.askoff==1}">已请假</c:when>
						<c:when test="${idListTb.askoff==2}">旷工</c:when>
					</c:choose>
				</display:column>
				<display:column property="startDatetime" title="考勤开始时间" format="{0,date,yy-MM-dd HH:mm}"/>
				<display:column property="endDatetime" title="考勤结束时间" format="{0,date,yy-MM-dd HH:mm}"/>
				<display:column property="servicePerformance" title="服务表现" />
				<display:column property="serviceMinute" title="服务分钟数" />
				<display:column property="trainingMinute" title="培训分钟数" />
				<display:column property="arriveLateMinute" title="迟到分钟数" />
				<display:column property="leaveEarlyMinute" title="早退分钟数" />
				<display:column property="missionPosition.mossionPositionName" title="工作岗位" />
				<display:column title="打卡模式" >
					<c:choose>
						<c:when test="${idListTb.checkOnMode==1}">打卡机</c:when>
						<c:when test="${idListTb.checkOnMode==2}">短信</c:when>
						<c:when test="${idListTb.checkOnMode==3}">电脑录入</c:when>
					</c:choose>
				</display:column>
				<display:column property="memo" title="备注" />
				
			</display:table>
		</center>
		
		
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>
