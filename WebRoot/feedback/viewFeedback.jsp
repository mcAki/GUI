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
		<s:form name="strutsForm" action="page!viewFeedback.do" namespace="/feedback" method="post">
			<center><s:fielderror></s:fielderror></center>
			<s:token/>
			<s:hidden name="missionId" />
			<s:hidden name="teamId"></s:hidden>
		  	<p>&nbsp;</p>
		  	<p>&nbsp;</p>
		  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
		  		
				<caption><H2 style="color:#FF0000">反馈信息</H2></caption>
					
				
				<tr>
					<td colspan="4"></td>
				</tr>
		      
				<tr>
					<td align="right">任务主题:</td>
					<td align="left"><s:label name="feedback.missionSubject"></s:label></td>
					<td align="right">所属队伍:</td>
					<td align="left"><s:label name="feedback.teamName"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">志愿者名:</td>
					<td align="left"><s:label name="feedback.createUserName"></s:label></td>
					<td align="right">反馈类别:</td>
					<td align="left">
						<c:choose>
							<c:when test="${feedback.type==1}">申诉</c:when>
							<c:when test="${feedback.type==2}">建议</c:when>
							<c:when test="${feedback.type==3}">投诉</c:when>
							<c:when test="${feedback.type==4}">汇报</c:when>
							<c:when test="${feedback.type==5}">其他</c:when>
						</c:choose>
					</td>
				</tr>
		      	
		      	<tr>
					<td align="right">发表时间:</td>
					<td align="left"><s:label name="feedback.createTime"></s:label></td>
					<td align="right">已处理:</td>
					<td align="left">
						<c:choose>
							<c:when test="${feedback.isExecuted==1}">已处理</c:when>
							<c:otherwise>未处理</c:otherwise>
						</c:choose>
					</td>
				</tr>
		      	
		      	<tr>
					<td colspan="4"></td>
				</tr>
		      	
				<tr>
					<td align="right">意见或建议:</td>
					<td colspan="3" align="left"><s:label name="feedback.content" cssClass="txtW5" rows="6"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">管理员名称:</td>
					<td align="left"><s:label name="feedback.managerName"></s:label></td>
					 <td align="right">回复时间:</td>
					 <td align="left"><s:label name="feedback.replyTime"></s:label></td>
				</tr>
				
				<tr>
					<td align="right">处理方案:</td>
					<td colspan="3" align="left"><s:label name="feedback.managerReply" cssClass="txtW5" rows="6"></s:label></td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<input type="button" onclick="javascript:window.close();" value="关闭"/>
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>

