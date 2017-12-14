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
<br/><center><display:table id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/mission/list!waitList.do">
			
            <display:column property="subject" title="项目主题" />
            <display:column property="missionState.stateName" title="项目状态"/>
            <display:column  property="missionType.typeName" title="项目类型" />
            <display:column property="usersByManagerId.userName" title="项目负责人" />     	
       	   <display:column property="createDatetime"  title="建立时间" format="{0,date,yy-MM-dd HH:mm}" />
       	   <display:column property="finishDatetime" title="结项时间" format="{0,date,yy-MM-dd HH:mm}"/>
       	   <display:column property="createUsername" title="建立人" />
            <display:column property="startDate" title="实施开始时间" format="{0,date,yy-MM-dd HH:mm}"/>
            <display:column property="endDate" title="实施结束时间" format="{0,date,yy-MM-dd HH:mm}"/>
           <display:column property="applyPersonInneed" title="需求人数" />
           	<display:column property="applyDeadline" title="报名截止日期" format="{0,date,yy-MM-dd}"/>
            <display:column title="操作">
            <c:choose>
				<c:when test="${idListTb.missionState.missionStateId==20}">
					<a href="${pageContext.request.contextPath}/missionVerification/page!add.do?missionId=${idListTb.missionId}">处理</a>
				</c:when>
			</c:choose>
            	
            </display:column >
		</display:table>
	    </center>
	<br/>	
	<br/>

<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
