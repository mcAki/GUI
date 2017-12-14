<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <br/><br/>
  
  <!-- 分页路径 -->

	<br/>
		<center><display:table id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/usersutils/listUserServiceLog!listUserServiceLog.do">

            <display:column property="missionSubject" title="任务标题" />
            <display:column property="userName" title="报道志愿者" />
            <display:column  property="state" title="状态" />
            <display:column property="askoff" title="请假情况" />     	
       	    <display:column property="serviceMinute"  title="服务分钟数" />
       	    <display:column property="trainingMinute" title="培训分钟数"/>
       	    <display:column property="arriveLateMinute" title="迟到时间" />
            <display:column property="leaveEarlyMinute" title="早退时间" />
          
            <display:column title="操作">
		    	<a href="${pageContext.request.contextPath}/usersutils/listUserServiceLog!listUserServiceLog.do">查询</a>
     	
            </display:column >
		</display:table>
	    </center>
	<br/>	
	<br/>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
