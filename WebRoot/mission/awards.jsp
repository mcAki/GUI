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
		<s:form name="strutsForm" action="/awardPersonal/page!doAdd.do" namespace="/awardPersonal" method="post">
			<center><s:fielderror></s:fielderror></center>
			<s:token/>
			<s:hidden name="userId" />
			<s:hidden name="teamId"></s:hidden>
		  	<p>&nbsp;</p>
		  	<p>&nbsp;</p>
		  	<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
		  		<c:choose>
		  			<c:when test="${mark==0}">
		  				<div>
							<caption><H2 style="color:#FF0000">颁	奖</H2></caption>
						</div>	
					</c:when>
					
					<c:when test="${mark==1}">
		  				<div>
							<caption><H2>惩	罚</H2></caption>
						</div>	
					</c:when>	
				</c:choose>
				
				<tr>
					<td colspan="4"></td>
				</tr>
		      
				<tr>
					<td align="right">志愿者名:</td>
					<td align="left"><s:label name="awardPersonal.users.userName"></s:label><s:hidden name="mark"></s:hidden></td>
					<td align="right">任务主题:</td>
					<td align="left"><s:label name="awardPersonal.mission.subject"></s:label><s:hidden name="missionId"></s:hidden></td>
					
				</tr>
		      	
				<tr>
					<c:choose>
						<c:when test="${mark==0}">
							<td width="105" align="right">奖项名称:</td>
							<td align="left">
								<s:select id="awardPersonal_awards_id1" cssClass="txtW2" list="awardses" name="awardPersonal.awards.id" listKey="id" listValue="awardsNameShow" ></s:select>
							</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							
						</c:when>
						
						<c:when test="${mark==1}">
							<td width="105" align="right">惩罚名称:</td>
                            <td align="left">
                            <s:select id="awardPersonal_awards_id1" cssClass="txtW2" list="awardses" name="awardPersonal.awards.id" listKey="id" listValue="awardsNameShow" ></s:select>
							</td>
							<td align="right">扣除分数:</td>
						  	<td align="left">
						  	<s:select id="awardPersonal_integralAward" cssClass="txtW1" name="awardPersonal.integralAward" list="%{#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}}"/>
						  	</td>
                           
						</c:when>
					</c:choose>	
				</tr>
		      	
		      	<tr>
					<c:choose>
						<c:when test="${mark==0}">
							<td align="right">获奖时间</td>
		      				<td align="left"><s:textfield name="awardPersonal.gainAwardDate" readonly="true" onclick="WdatePicker()"></s:textfield></td>
							<td colspan="2">(请点击文本框选择获奖的日期)</td>
						</c:when>
						
						<c:when test="${mark==1}">
							<td align="right">扣分时间</td>
		      				<td align="left"><s:textfield name="awardPersonal.gainAwardDate" readonly="true" onclick="WdatePicker()"></s:textfield></td>
							<td colspan="2">(请点击文本框选择扣分的日期)</td>
						</c:when>
					</c:choose>	
				</tr>
				
				<tr>
					<c:choose>
						<c:when test="${mark==0}">
							<div>
								<td align="right">获奖原因:</td>
								<td align="left" colspan="3"><s:textarea cssClass="txtW5" rows="5"  name="awardPersonal.gainAwardReason"/></td>
							</div>	
						</c:when>
						
						<c:when test="${mark==1}">
							<div>
								<td align="right">惩罚原因:</td>
								<td align="left" colspan="3"><s:textarea cssClass="txtW5" rows="5"  name="awardPersonal.gainAwardReason"/></td>
                                
							</div>	
						</c:when>
					</c:choose>	
				</tr>
				
				<tr>
					<td colspan="1" align="center">
						　　　
						<s:submit icon="icon-apply" value="提交"></s:submit>&nbsp;
					</td>
					<td colspan="1" align="center">
					  　　<s:reset icon="icon-reload" value="重设"></s:reset>&nbsp;
					 </td>
					 <td colspan="2" align="center">
					  <input icon="icon-reset" type="button" value="返回父队伍"
										onclick="javascript:window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${teamId }'" />&nbsp;
					 </td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp" %>
	</body>
</html>

