<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css" />
		<link href="../css/sys.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<script type="text/javascript" >
		function batchServiceLog(itemName,operateCode) {
	

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
			if(operateCode==1){
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/doDelete.do?missionServiceLogIds="
					+ datas +"&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}";
			}
			if(operateCode==2){
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/doCommit.do?missionServiceLogIds="
					+ datas +"&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}&isCancel=0";
			}
			if(operateCode==3){
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/doCommit.do?missionServiceLogIds="
					+ datas +"&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}&isCancel=1";
			}
			
			document.form1.submit();
		}
		</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<s:hidden></s:hidden>
		<br />
		<br />
		<s:form id="form1" name="form1"
			 namespace="/missionservicelog"
			method="post">
		<center><s:hidden name="checkOnDate"/>
		<display:table id="dtable" name="missionServiceLogVOs"
			style="width:750px;" sort="external" pagesize="${pageSize}"
			cellspacing="1" class="list_tb"
			requestURI="${pageContext.request.contextPath}/missionservicelog/list!listMissionServiceLog.do">
			<s:hidden name="misssionTeamId"/>
			<display:column title="选择">
				<input type="checkbox" id="chk" name="chk" value="${dtable.missionServiceLogId}" />
			</display:column>
			<display:column property="userName" title="队员名称" />
			<display:column property="checkOnDate" title="考勤日"
				format="{0,date,yyyy年MM月dd日}" />
			<display:column title="工作时间">
				<fmt:formatDate pattern="dd日hh时mm分"
					value="${dtable.startTime}" /> 至 <fmt:formatDate
					pattern="dd日hh时mm分" value="${dtable.endTime}" />
			</display:column>
			<display:column title="请假">
				<c:choose>
					<c:when test="${dtable.askoff==1}">
						请假
					</c:when>
					<c:when test="${dtable.askoff==2}">
						旷工
					</c:when>
					<c:otherwise>
						无
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="服务时长" >
				<fmt:formatNumber maxFractionDigits="0" value="${dtable.serviceMinute/60-dtable.serviceMinute%60/60}"/>:
				<c:choose>
					<c:when test="${dtable.serviceMinute%60==0 }">00</c:when>
					<c:otherwise>${dtable.serviceMinute%60 }</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="迟到" >
				<c:choose>
					<c:when test="${null==dtable.arriveLateMinute||dtable.arriveLateMinute==0}">
						无
					</c:when>
					<c:otherwise>
						是
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column  title="早退" >
				<c:choose>
					<c:when test="${null==dtable.leaveEarlyMinute||dtable.leaveEarlyMinute==0}">
						无
					</c:when>
					<c:otherwise>
						是
					</c:otherwise>
				</c:choose>
			</display:column>
			<display:column title="确认">
				<c:choose>
					<c:when test="${null==dtable.isUpdated||dtable.isUpdated==0}">
           				待定
           			</c:when>
					<c:otherwise>
           				已确认
           		</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="enterUsername" title="提交人" />
			<display:column property="commitUsername" title="确认人" />
			<display:column title="操作">
           		<c:choose>
					<c:when test="${null==dtable.isUpdated||dtable.isUpdated==0}">
           				<a href="${pageContext.request.contextPath}/missionservicelog/doDelete.do?missionServiceLogIds=${dtable.missionServiceLogId}&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}" onclick="javascript:return del()">删除</a>&nbsp;
           				<a href="${pageContext.request.contextPath}/missionservicelog/page!update.do?missionServiceLogId=${dtable.missionServiceLogId}&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}">修改</a>&nbsp;
           				<a href="${pageContext.request.contextPath}/missionservicelog/doCommit.do?missionServiceLogIds=${dtable.missionServiceLogId}&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}&isCancel=0" onclick="javascript:return conf()">确认</a>&nbsp;
           			</c:when>
           			<c:otherwise>
           				<a href="${pageContext.request.contextPath}/missionservicelog/doCommit.do?missionServiceLogIds=${dtable.missionServiceLogId}&missionTeamId=${missionTeamId}&checkOnDate=${checkOnDate}&isCancel=1" onclick="javascript:return conf()">取消确认</a>&nbsp;
           			</c:otherwise>
           		</c:choose>
           	</display:column>
		</display:table> 
				<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选&nbsp;
				<input icon="icon-delete" type="button" value="删除"
				onclick="javascript:batchServiceLog('chk',1)" />&nbsp;
				<input icon="icon-save" type="button" value="确认"
				onclick="javascript:batchServiceLog('chk',2)" />&nbsp;
				<input icon="icon-save" type="button" value="取消确认"
				onclick="javascript:batchServiceLog('chk',3)" />&nbsp;
		</center>
		
			
		
		<br />
		<br />
		</s:form>
		<a href="user!add.html"></a>
		<script type="text/javascript">
		highlightTableRows("dTable");
		</script>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
