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
		<s:form name="strutsForm" action="doAdd" namespace="/feedback" method="post">
			<center><s:fielderror></s:fielderror></center>
			<s:token/>
			<s:hidden name="missionId"></s:hidden>
			<s:hidden name="teamId"></s:hidden>
			<s:hidden name="feedbackId"></s:hidden>
		  	<p>&nbsp;</p>
		  	<p>&nbsp;</p>
		  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
		  		
				<caption><H2 style="color:#FF0000">反馈信息</H2></caption>
					
				
				<tr>
					<td colspan="4"></td>
				</tr>
		      
				<tr>
					<td align="right">任务主题:</td>
					<td align="left"><s:textfield name="feedback.missionSubject" readonly="true"></s:textfield></td>
					<td align="right">所属队伍:</td>
					<td align="left"><s:textfield name="feedback.teamName" readonly="true"></s:textfield></td>
				</tr>
				
				<tr>
					<td align="right">志愿者名:</td>
					<td align="left"><s:textfield name="feedback.createUserName" readonly="true"></s:textfield></td>
					<td align="right">反馈类别:</td>
					<td align="left"><s:select cssClass="txtW2" name="feedback.type" list="%{#{1:'申诉',2:'建议',3:'投诉',4:'汇报',5:'其他'}}"></s:select></td>
				</tr>
		      	
				<tr>
					<td align="right">意见或建议:</td>
					<td colspan="3" align="left"><s:textarea name="feedback.content" cssClass="txtW5" rows="6"></s:textarea></td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<s:submit icon="icon-apply" value="提交"></s:submit>&nbsp;
						<s:reset icon="icon-reload" value="重设"></s:reset>&nbsp;
						<input type="button" icon="icon-retry" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/mission/list!listMissionForFeedback.do'"/>&nbsp;
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>

