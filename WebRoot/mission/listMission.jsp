<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <br/><br/><br/>
  <center>
		<div align="center">
			<s:form id="form1" name="form0"
			action="list!manage" namespace="/mission"
			method="post">
			<table>
				<tr>
					<td>项目类型:&nbsp;</td>
					<td>&nbsp;<s:select name="selection" list="%{#{0:'显示所有',1:'新生活·科技',2:'新生活·健康',3:'新生活·学习',4:'新生活·礼仪',5:'新生活·公益',6:'新生活·诚信',7:'新生活·环保',8:'第16届亚运会(赛会)',9:'第16届亚残运会(赛会)',10:'亚运城市文明',11:'亚运城市站点',12:'亚残城市文明',13:'亚残城市站点'}}" listKey="key"></s:select>&nbsp;</td>
					<td>&nbsp;项目主题:&nbsp;</td>
					<td>&nbsp;<input type="text" name="subject" size="10" value="${param.subject}"/>&nbsp;</td>
					<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
				</tr>
			</table>
			</s:form>
		</div>
  <center><display:table export="true" id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/list!manage.do">
			<display:column property="missionId" title="ID"></display:column>
            <display:column property="subject" title="项目主题" style="width:200px;" />
            <display:column property="missionState.stateName" title="项目状态" style="font-weight:bold;"/>
            <display:column  property="missionType.typeName" title="项目类型" />
            <display:column property="usersByManagerId.userName" title="负责人" />
            <display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.startDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.endDate}"/>
            </display:column>
            <display:column property="applyAccepted" title="规模" />
            <display:column title="查看/修改" media="html">				
				<a href="${pageContext.request.contextPath}/mission/viewMission.do?missionId=${idListTb.missionId}">查看</a>
				<c:choose>
					<c:when test="${(idListTb.missionState.missionStateId==1||idListTb.missionState.missionStateId==26
					||idListTb.missionState.missionStateId==20)
					&&(idListTb.createUserId.userId==sessionScope.user.userId
					||idListTb.usersByManagerId.userId==sessionScope.user.userId)}">
						<a href="${pageContext.request.contextPath}/mission/page!update.do?missionId=${idListTb.missionId}">修改</a>
					</c:when>
					<c:otherwise>
						修改
					</c:otherwise>
				</c:choose>
				
			</display:column>
			<display:column title="岗位" media="html" >
				<c:if test="${idListTb.missionState.missionStateId>=30&&(idListTb.createUserId.userId==sessionScope.user.userId
								||idListTb.usersByManagerId.userId==sessionScope.user.userId
								||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/mission/personal!findAllPersonal.action?missionId=${idListTb.missionId}">招募</a>
					<a href="${pageContext.request.contextPath}/mission/personal!findPersonalSend.action?missionId=${idListTb.missionId}">录用</a>
				</c:if>
				<c:if test="${idListTb.missionState.missionStateId>=35&&(idListTb.createUserId.userId==sessionScope.user.userId
								||idListTb.usersByManagerId.userId==sessionScope.user.userId
								||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/missionTeam/page!list.do?missionId=${idListTb.missionId}" >配岗</a>										
				</c:if>
				<c:if test="${(idListTb.missionState.missionStateId>=35) && (sessionScope.user.usergroup.id==1)}">				
					<a href="${pageContext.request.contextPath}/mission/refreshPersonal.do?missionId=${idListTb.missionId}"  onclick="javascript:return conf('点击确认立即从用户表数据中刷新本任务人员信息!')">刷新</a>					
				</c:if>
            </display:column>
            <display:column title="流程处理" media="html" >
            <c:choose>
                <c:when test="${idListTb.missionState.missionStateId==1&&(idListTb.createUserId.userId==sessionScope.user.userId
                				||idListTb.usersByManagerId.userId==sessionScope.user.userId
                				||sessionScope.user.usergroup.id<=13)}">
                	<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">申请审批</a>
                </c:when>
				<c:when test="${idListTb.missionState.missionStateId==20}">
					等待审批中
				</c:when>
				<c:when test="${idListTb.missionState.missionStateId==26&&(idListTb.createUserId.userId==sessionScope.user.userId||idListTb.usersByManagerId.userId==sessionScope.user.userId)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">重新申请审批</a>
				</c:when>
				<c:when test="${idListTb.missionState.missionStateId==25&&(idListTb.createUserId.userId==sessionScope.user.userId||
				idListTb.usersByManagerId.userId==sessionScope.user.userId
				||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">开始招募</a>
				</c:when>				
				<c:when test="${idListTb.missionState.missionStateId==30&&(idListTb.createUserId.userId==sessionScope.user.userId||idListTb.usersByManagerId.userId==sessionScope.user.userId)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">结束招募</a>
				</c:when>
				<c:when test="${idListTb.missionState.missionStateId==35&&(idListTb.createUserId.userId==sessionScope.user.userId
				||idListTb.usersByManagerId.userId==sessionScope.user.userId
				||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">开始执行任务</a>
				</c:when>
				<c:when test="${idListTb.missionState.missionStateId==50&&(idListTb.createUserId.userId==sessionScope.user.userId
				||idListTb.usersByManagerId.userId==sessionScope.user.userId
				||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">结束任务</a>
				</c:when>
				<c:when test="${idListTb.missionState.missionStateId==100&&(idListTb.createUserId.userId==sessionScope.user.userId
				||idListTb.usersByManagerId.userId==sessionScope.user.userId
				||sessionScope.user.usergroup.id<=13)}">
					<a href="${pageContext.request.contextPath}/mission/state.do?missionId=${idListTb.missionId }&transformState=${idListTb.missionState.nextStep}" onclick="javascript:return conf()">执行结项</a>
				</c:when>
			</c:choose>
            </display:column >
            <display:setProperty name="export.excel.filename" value="missionlist.xls"/>
		</display:table><script type="text/javascript">
		displaytagExportLink();
		</script>
	    </center>
	<br/>	
	<br/>
		<script type="text/javascript">
		highlightTableRows("idListTb");
	</script>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
