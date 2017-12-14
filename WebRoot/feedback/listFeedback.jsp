<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
  <head>		
  	<link href="../css/criteria.css" rel="stylesheet" type="text/css">
	<link href="../css/sys.css" rel="stylesheet" type="text/css">
	<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
	function showFeedback(feedbackId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		ezModal(
				'${pageContext.request.contextPath}/feedback/page!viewFeedback.do?feedbackId='+feedbackId,
				 700, 600);
		//jq检测是否为空
		//if(!$.isEmptyObject(returnVal)){
		//赋值!
		//	$("#userId").val(returnVal.uid);
		//	$("#currentLeaderName").val(returnVal.username);
		//}
	}
</script>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
  <br/><br/><s:form name="strutsForm" action="list" namespace="/feedback" method="post"><s:hidden name="isAdmin"></s:hidden>
<br/><center><display:table id="idListTb" name="pageView"  style="width:1024px;" sort="external" pagesize="${pageSize}" cellspacing="1"  class="list_tb" requestURI="${pageContext.request.contextPath}/feedback/list.do">
			<display:column property="missionSubject" title="项目主题"></display:column>
			<display:column title="反馈类型">
				<c:choose>
					<c:when test="${idListTb.type==1}">申诉</c:when>
					<c:when test="${idListTb.type==2}">建议</c:when>
					<c:when test="${idListTb.type==3}">投诉</c:when>
					<c:when test="${idListTb.type==4}">汇报</c:when>
					<c:when test="${idListTb.type==5}">其他</c:when>
				</c:choose>
			</display:column>
			<display:column property="teamName" title="所属队伍"></display:column>
			<display:column property="createUserName" title="发表人名称"></display:column>
			<display:column property="createTime" title="发布时间" format="{0,date,yy-MM-dd HH:mm}"></display:column>
			<display:column title="处理">
				<c:choose>
					<c:when test="${idListTb.isExecuted==1}">已处理</c:when>
					<c:otherwise>未处理</c:otherwise>
				</c:choose>
			</display:column>
			<display:column property="managerName" title="处理人名称"></display:column>
			<display:column property="replyTime" title="回复时间" format="{0,date,yy-MM-dd HH:mm}"></display:column>
            <display:column title="查看/处理">				
				<a onclick="showFeedback(${idListTb.id})" href="javascript:void(0)">查看</a>&nbsp;
				<c:choose>
					<c:when test="${idListTb.isExecuted==0}">
						<a href="${pageContext.request.contextPath}/feedback/page!verifyFeedback.do?feedbackId=${idListTb.id}" >处理</a>&nbsp;
					</c:when>
				</c:choose>
			</display:column>
			
		</display:table>
	    </center>
	<br/>	
	<br/>
</s:form>
<a href="user!add.html"></a>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
