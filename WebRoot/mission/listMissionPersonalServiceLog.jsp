<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
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
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/deleteServiceLog.do?missionServiceLogIds="
					+ datas +"&missionId=${mission.missionId}&teamId=${teamId}";
			}
			if(operateCode==2){
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/commitServiceLog.do?missionServiceLogIds="
					+ datas +"&missionId=${mission.missionId}&teamId=${teamId}&isCancel=0";
			}
			if(operateCode==3){
				document.form1.action="${pageContext.request.contextPath}/missionservicelog/commitServiceLog.do?missionServiceLogIds="
					+ datas +"&missionId=${mission.missionId}&teamId=${teamId}&isCancel=1";
			}
			document.form1.submit();
		}
		</script>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <br/><s:hidden></s:hidden><br/>
  <s:form name="form1" id="form1" action="listPersonalServiceLog" namespace="/missionservicelog"
			method="post">
<br/><center><display:table id="idListTb" name="pageView" export="true" style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/missionservicelog/listPersonalServiceLog.do">
			<display:setProperty name="paging.banner.placement" value="bottom" />
			<display:caption title="队员个人考勤列表">队员个人考勤列表</display:caption>
			<display:column title="选择"  media="html">
				<input type="checkbox" id="chk" name="chk" value="${idListTb.missionServiceLogId}"/>
			</display:column>
            <display:column property="missionSubject" title="项目主题" />
            <display:column property="userName" title="队员名称"/>
            <display:column  property="checkOnDate" title="考勤日" format="{0,date,yy-MM-dd}"/>
       	   	<display:column title="请假情况" >
       	   		<c:choose>
       	   			<c:when test="${idListTb.askoff==0}">正常出勤</c:when>
       	   			<c:when test="${idListTb.askoff==1}">请假</c:when>
       	   			<c:when test="${idListTb.askoff==2}">旷工</c:when>
       	   		</c:choose>
       	   	</display:column>
            <display:column  title="服务时长" media="html">
            	<fmt:formatNumber maxFractionDigits="0" value="${idListTb.serviceMinute/60-idListTb.serviceMinute%60/60}"/>:
				<c:choose>
					<c:when test="${idListTb.serviceMinute%60==0 }">${idListTb.serviceMinute%60 }0</c:when>
					<c:otherwise>${idListTb.serviceMinute%60 }</c:otherwise>
				</c:choose>
            </display:column>
            <display:column title="服务时长" media="excel">
            	<fmt:formatNumber maxFractionDigits="2" value="${idListTb.serviceMinute/60}"/>
            </display:column>
            <display:column title="迟到分钟">
            	<fmt:formatNumber maxFractionDigits="0" value="${idListTb.arriveLateMinute/60-idListTb.arriveLateMinute%60/60}"/>:
				<c:choose>
					<c:when test="${idListTb.arriveLateMinute%60==0 }">${idListTb.arriveLateMinute%60 }0</c:when>
					<c:otherwise>${idListTb.arriveLateMinute%60 }</c:otherwise>
				</c:choose>
            </display:column>
           	<display:column title="早退分钟">
           		<fmt:formatNumber maxFractionDigits="0" value="${idListTb.leaveEarlyMinute/60-idListTb.leaveEarlyMinute%60/60}"/>:
				<c:choose>
					<c:when test="${idListTb.leaveEarlyMinute%60==0 }">${idListTb.leaveEarlyMinute%60 }0</c:when>
					<c:otherwise>${idListTb.leaveEarlyMinute%60 }</c:otherwise>
				</c:choose>
           	</display:column>
           	<display:column  title="志愿时积分">
           		<fmt:formatNumber maxFractionDigits="1" value="${idListTb.totalIntegral }"/>
           	</display:column>
           	<display:column title="已确认">
           		<c:choose>
           			<c:when test="${idListTb.isUpdated==1}">已确认</c:when>
           			<c:otherwise>未确认</c:otherwise>
           		</c:choose>
           	</display:column>
           	<display:column property="enterUsername" title="提交人" />
           	 <display:column property="commitUsername" title="确认人"/>
           	<display:column title="操作" media="html">
           		<c:choose>
           			<c:when test="${null==idListTb.isUpdated||idListTb.isUpdated==0}">
           				<a href="${pageContext.request.contextPath}/missionservicelog/deleteServiceLog.do?missionServiceLogIds=${idListTb.missionServiceLogId}&missionId=${idListTb.mission.missionId }&teamId=${teamId}" onclick="javascript:return del()">删除</a>&nbsp;
           				<a href="${pageContext.request.contextPath}/missionservicelog/commitServiceLog.do?missionServiceLogIds=${idListTb.missionServiceLogId}&missionId=${idListTb.mission.missionId }&teamId=${teamId}&isCancel=0" onclick="javascript:return conf()">确认</a>&nbsp;
           			</c:when>
           			<c:otherwise>
           				<a href="${pageContext.request.contextPath}/missionservicelog/commitServiceLog.do?missionServiceLogIds=${idListTb.missionServiceLogId}&missionId=${idListTb.mission.missionId }&teamId=${teamId}&isCancel=1" onclick="javascript:return conf()">取消确认</a>&nbsp;
           			</c:otherwise>
           		</c:choose>
           	</display:column>
           	<display:setProperty name="export.excel.filename" value="servicelog.xls"/>
		</display:table>
		<script type="text/javascript">
			displaytagExportLink();
	    </script>
		<center>
			<table>
			<tr>
				<td>
				<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选&nbsp;
				<input icon="icon-delete" type="button" value="删除"
				onclick="javascript:batchServiceLog('chk',1)" />&nbsp;
				<input icon="icon-save" type="button" value="确认"
				onclick="javascript:batchServiceLog('chk',2)" />&nbsp;
				<input icon="icon-save" type="button" value="取消确认"
				onclick="javascript:batchServiceLog('chk',3)" />&nbsp;
				<input type="button" id="idBackBtn" icon="icon-back" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${missionTeam.missionTeamId }'"/>
				</td>
			</tr>
		</table>
		</center>
		
		
	    </center>
	    </s:form>
	<br/>	
	<br/>
		<script type="text/javascript">
		highlightTableRows("idListTb");
		</script>
	<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
