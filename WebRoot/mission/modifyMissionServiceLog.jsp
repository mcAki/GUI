<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.pojo.MissionVerification"%>
<%@page import="com.sys.volunteer.common.Const" %>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil" %>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>

	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		
		<script type="text/javascript">

		</script>
	</head>
	
	<body>
		<%@ include file="../common/incBanner.jsp" %>
		<s:form name="form1" action="doUpdate" namespace="/missionservicelog" method="post">
		<center><s:fielderror></s:fielderror></center>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table width="650" border="1" align="center" cellspacing="3" class="form_tb">
				<caption>
					<strong>修改考勤记录</strong>
				</caption>
				
				<tr>
					<td colspan="4"><s:hidden name="missionServiceLog.missionServiceLogId"/><s:hidden name="missionTeamId"/></td>
				</tr>
					
				<tr>
					<td align="right">项目名称:</td>
					<td align="left" ><s:label name="missionServiceLog.missionSubject" /></td>
					<td align="right">用户名称:</td>
					<td align="left"><s:label name="missionServiceLog.userName" /></td>
				</tr>
				
				<tr>
					<td align="right">考勤日期:</td>
					<td align="left"><s:label name="missionServiceLog.checkOnDate" /></td>
					<td align="right">请假旷工情况:</td>
					<td align="left"><s:radio name="missionServiceLog.askoff" list="%{#{0:'无',1:'请假',2:'旷工'}}"></s:radio></td>
				</tr>
					
				<tr>
					<td align="right">服务时长:</td>
					<td align="left"><s:textfield name="missionServiceLog.serviceMinute"/></td>
					<td align="right">培训时长:</td>
					<td align="left"><s:textfield name="missionServiceLog.trainingMinute"/></td>
				</tr>
					
				<tr>
					<td align="right">迟到分钟:</td>
					<td align="left"><s:textfield name="missionServiceLog.arriveLateMinute" /></td>
					<td align="right">早退分钟:</td>
					<td align="left"><s:textfield name="missionServiceLog.leaveEarlyMinute" /></td>
				</tr>
					
				<tr >
					<td colspan="4" align="right">
						<center>
							<s:submit icon="icon-save" value="提交"/>&nbsp;
							<input icon="icon-reset" type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/missionservicelog/list!listMissionServiceLog.do?checkOnDate=${missionServiceLog.checkOnDate }&missionTeamId=${missionTeamId }'"/>
						</center>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
		
		
		<br/>
		
		</s:form>
	<br/>	
	<br/>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>
