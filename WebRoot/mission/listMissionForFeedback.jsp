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
  <br/><center>
  <display:table id="idListTb" name="missions"  style="width:750px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/list!listMissionForFeedback.do">
			<display:column property="missionId" title="ID"></display:column>
            <display:column property="subject" title="项目主题" style="width:200px;" />
            <display:column title="实施时段">
            	<fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.startDate}"/> 至 <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.endDate}"/>
            </display:column>
			<display:column title="岗位">
				<a href="${pageContext.request.contextPath}/feedback/page!add.do?missionId=${idListTb.missionId}&teamId=${idListTb.teamId}">反馈意见</a>
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
