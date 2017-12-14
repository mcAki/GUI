<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>
    <%@ include file="../common/incHead.jsp" %>
	<link href="../css/criteria.css" rel="stylesheet" type="text/css">  
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
  </head>  
  <body>
  	<%@ include file="../common/incBanner.jsp" %>
	<br/>
	<br/>
	<br/>
	<center>
		<display:table id="idListTb" name="pageView"  style="width:950px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" >

            <display:column property="subject" title="任务主题" />
            <display:column title="报名截止日期" >
            <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.applyDeadline}"/>
            </display:column>
            <display:column title="实施开始时间" >
            <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.startDate}"/>
            </display:column>
       	    <display:column title="实施结束时间" >
       	    <fmt:formatDate pattern="yy年MM月dd日" value="${idListTb.endDate}"/>
       	    </display:column>
       	    <display:column property="venue" title="举办地址"/>
            <display:column title="操作">
		    	<a href="${pageContext.request.contextPath}/mission/viewMission!viewMission.do?missionId=${idListTb.missionId}">查看</a>
     			<a href="${pageContext.request.contextPath}/usersutils/applyMission!applyMission.do?missionId=${idListTb.missionId}">报名</a>
            </display:column >
		</display:table>
	</center>
	<br/>	
	<br/>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
