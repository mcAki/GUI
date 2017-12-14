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
  <br/><br/>
<br/><center><display:table id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/list!manage.do">
			<display:column property="missionId" title="ID"></display:column>
            <display:column property="subject" title="项目主题" style="width:200px;" />
            <display:column property="missionState.stateName" title="项目状态" style="font-weight:bold;"/>
            <display:column  property="missionType.typeName" title="项目类型" />
            <display:column property="usersByManagerId.userName" title="负责人" />
            <display:column property="createUserId.userName" title="发起人" />
            <display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.startDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.endDate}"/>
            </display:column>
            <display:column property="applyPersonInneed" title="规模" />
            <display:column title="查看">				
				<a href="${pageContext.request.contextPath}/mission/viewMission.do?missionId=${idListTb.missionId}" >查看</a>
			</display:column>
		</display:table>
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
