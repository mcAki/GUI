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
			function apply(){
				
				document.form1.action = '${pageContext.request.contextPath}/usersutils/applyMission!applyMission.do?missionId=${mission.missionId}';
				document.form1.submit();
			}
		</script>
	</head>
	
	<body>
		<%@ include file="../common/incBanner.jsp" %>
		<s:form name="form1" action="viewMission" namespace="/mission" method="post">
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table width="650" border="1" align="center" cellspacing="3" class="form_tb">
				<caption>
					<strong>任务详情</strong>
				</caption>
				
				<tr>
					<td colspan="4"></td>
				</tr>
					
				<tr>
					<td align="right">项目名称:</td>
					<td align="left" ><s:label name="mission.subject" /></td>
					<td align="right">项目类型:</td>
					<td align="left"><s:label name="mission.missionType.typeName" /></td>
				</tr>
				
				<tr>
					<td align="right">建立人:</td>
					<td align="left"><s:label name="mission.createUsername" /></td>
					<td align="right">项目负责人:</td>
					<td align="left"><s:label name="mission.usersByManagerId.userName" /></td>
				</tr>
					
				<tr>
					<td align="right">所属区域:</td>
					<td align="left"><s:label name="mission.districtName" /></td>
					<td align="right">报名截止日期:</td>
					<td align="left"><s:label name="mission.applyDeadline" /></td>
				</tr>
					
				<tr>
					<td align="right">报名需求人数上限:</td>
					<td align="left"><s:label name="mission.applyPersonUpperlimit" /></td>
					<td align="right">报名需求人数:</td>
					<td align="left"><s:label name="mission.applyPersonInneed" /></td>
				</tr>
					
				<tr>
					<td align="right">应具备的服务时长:</td>
					<td align="left"><s:label name="mission.requireServiceTime" /></td>
					<td align="right">应具备的培训时长:</td>
					<td align="left"><s:label name="mission.requireTrainingTime" /></td>
				</tr>
					
				<tr>
					<td align="right">可获得的服务时长:</td>
					<td align="left"><s:label name="mission.gainServiceTime" /></td>
					<td align="right">可获得的培训时长:</td>
					<td align="left"><s:label name="mission.gainTrainingTime" /></td>
				</tr>
					
				<tr>
					<td align="right">是否停止在网上招募:</td>
					<td align="left">
						<s:label>
							<c:choose>
								<c:when test="${mission.isAllowJoin==0}">是</c:when>
								<c:when test="${mission.isAllowJoin==1}">否</c:when>
							</c:choose>
						</s:label>
					</td>
					<td align="right">是否发布至前台:</td>
					<td align="left">
						<s:label>
							<c:choose>
								<c:when test="${mission.isPublishWebsite==0}">发布</c:when>
								<c:when test="${mission.isPublishWebsite==1}">不发布</c:when>
							</c:choose>	
						</s:label>
					</td>
				</tr>
				
				<tr>
					<td align="right">上次修改人:</td>
					<td align="left"><s:label name="mission.usersByModifyUserId.userName" /></td>
					<td align="right">上次修改时间:</td>
					<td align="left"><s:label name="mission.modifyDatetime" /></td>
				</tr>
				
				<tr>
					<td align="right">项目安全级别:</td>
					<td align="left"><s:label>
										<c:choose>
											<c:when test="${mission.securityLevel==0}">普通级别</c:when>
											<c:when test="${mission.securityLevel==1}">标准安全</c:when>
											<c:when test="${mission.securityLevel==2}">高度安全</c:when>
										</c:choose>	
									</s:label></td>
					<td align="right">机动任务:</td>
					<td align="left">
						<s:label>
							<c:choose>
								<c:when test="${mission.isUntime==0}">是</c:when>
								<c:when test="${mission.isUntime==1}">否</c:when>
							</c:choose>	
						</s:label></td>
				</tr>
				
				<tr>
					<td align="right">打卡模式:</td>
					<td align="left"><s:label>
										<c:choose>
											<c:when test="${mission.checkOnMode==1}">短信</c:when>
											<c:when test="${mission.checkOnMode==2}">打卡机</c:when>
										</c:choose>	
									</s:label></td>
					<td align="right">任务最高级别:</td>
					<td align="left">
						<s:label>
							<c:choose>
								<c:when test="${mission.highestPosition==1}">大队长</c:when>
								<c:when test="${mission.highestPosition==2}">中队长</c:when>
								<c:when test="${mission.highestPosition==3}">小队长</c:when>
							</c:choose>
						</s:label>
					</td>
				</tr>
				
				<tr>
					<td align="right">实施日期:</td>
					<td colspan="3" align="left"><s:label maxlength="5" name="mission.startDate" />
						&nbsp;&nbsp;至&nbsp;&nbsp;<s:label  name="mission.endDate" /></td>
				</tr>
					
				<tr>
					<td align="right">举办地点:</td>
					<td colspan="3" align="left"><s:label cssClass="txtW5" name="mission.venue" /></td>
				</tr>
					
				<tr>
					<td align="right">详细地址:</td>
					<td colspan="3" align="left"><s:label cssClass="txtW5" name="mission.venueAddress" /></td>
				</tr>
					
				<tr>
					<td align="right">项目内容:</td>
					<td colspan="3" align="left"><s:textarea name="mission.detailInfo" cssClass="txtW5" rows="4" readonly="true"/></td>
				</tr>
				
				<tr>
					<td align="right">项目备注:</td>
					<td colspan="3" align="left"><s:textarea name="mission.remark" cssClass="txtW5" rows="4" readonly="true"/></td>
				</tr>
				
				<tr>
					<td align="right">任务简介:</td>
					<td colspan="3" align="left"><s:textarea name="mission.summary" cssClass="txtW5" rows="4" readonly="true"/></td>
				</tr>
				
				
				<tr >
					<td colspan="4" align="right">
						<center>
							<input icon="icon-reset" type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/mission/list!manage.do?missionId=${mission.missionId }'"/>
						</center>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
		
		
		<br/>
		<c:if test="${mission.missionState.missionStateId>=25}">
				<center><display:table id="idListTb" name="pageView"  style="width:650px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/page!viewMission.do">

            <display:column property="mission.subject" title="任务名称" />
            <display:column property="verifyDatetime" title="审批日期" format="{0,date,yy-MM-dd HH:mm}"/>
            <display:column title="任务状态">
            	 <%=Const.MISSION_VERIFICATION_PERMISSIBLE_NAMES[((MissionVerification)pageContext.getAttribute("idListTb")).getPermissible()] %>
            </display:column>
            <display:column property="commentCustomize" title="审批评语" />
		</display:table>
	    </center>
		</c:if>
		</s:form>
	<br/>	
	<br/>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>
