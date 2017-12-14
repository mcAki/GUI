<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>

  <head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	
	<script type="text/javascript">
	

	//保存
	function saveState(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		
		switch(operateCode){
			case 25:
				formname.action = '${pageContext.request.contextPath}/missionVerification/doAdd.do?missionId=${mission.missionId}&operateCode='+operateCode;
				break;
			case 26:
				formname.action = '${pageContext.request.contextPath}/missionVerification/doAdd.do?missionId=${mission.missionId}&operateCode='+operateCode;
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}
	
	</script>
  </head>  
  <body>
  <%@ include file="../common/incBanner.jsp" %>
	<s:form name="form1" id="form1" action="doUpdate" namespace="/missionVerification" method="post">
	  	<p>&nbsp;</p>
	  	<p>&nbsp;</p>
		<table width="700" border="0" align="center" cellspacing="3" class="form_tb">
			<caption>
				<strong>项目审核</strong>
			</caption>
				
			<tr>
				<td colspan="5"></td>
			</tr>
				
			<tr>
				<td align="right">项目名称:</td>
				<td align="left" ><s:label name="mission.subject" /></td>
				<td align="right">项目类型:</td>
				<td align="left"><s:label name="mission.missionType.typeName" /></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">建立人:</td>
				<td align="left"><s:label name="mission.createUsername" /></td>
				<td align="right">项目负责人:</td>
				<td align="left"><s:label name="mission.usersByManagerId.userName" />
				
				</td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">所属区域:</td>
				<td align="left"><s:label name="mission.districtName" />
				
				</td>
				<td align="right">报名截止日期:</td>
				<td align="left"><s:label name="mission.applyDeadline" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">报名需求人数上限:</td>
				<td align="left"><s:label name="mission.applyPersonUpperlimit" /></td>
				<td align="right">报名需求人数:</td>
				<td align="left"><s:label name="mission.applyPersonInneed" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">应具备的服务时长:</td>
				<td align="left"><s:label name="mission.requireServiceTime" /></td>
				<td align="right">应具备的培训时长:</td>
				<td align="left"><s:label name="mission.requireTrainingTime" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">可获得的服务时长:</td>
				<td align="left"><s:label name="mission.gainServiceTime" /></td>
				<td align="right">可获得的培训时长:</td>
				<td align="left"><s:label name="mission.gainTrainingTime" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">是否停止在网上招募:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.isAllowJoin==0}">是</c:when>
										<c:when test="${mission.isAllowJoin==1}">否</c:when>
									</c:choose>
								</s:label></td>
				<td align="right">是否发布至前台:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.isPublishWebsite==0}">发布</c:when>
										<c:when test="${mission.isPublishWebsite==1}">不发布</c:when>
									</c:choose>	
								</s:label></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">上次修改人:</td>
				<td align="left"><s:label name="mission.usersByModifyUserId.userName" /></td>
				<td align="right">上次修改时间:</td>
				<td align="left"><s:label name="mission.modifyDatetime" /></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">项目安全级别:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.securityLevel==0}">普通级别</c:when>
										<c:when test="${mission.securityLevel==1}">标准安全</c:when>
										<c:when test="${mission.securityLevel==2}">高度安全</c:when>
									</c:choose>	
								</s:label></td>
				<td align="right">机动任务:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.isUntime==0}">是</c:when>
										<c:when test="${mission.isUntime==1}">否</c:when>
									</c:choose>	
								</s:label></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">最高权限级别:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.highestPosition==1}">大队</c:when>
										<c:when test="${mission.highestPosition==2}">中队</c:when>
										<c:when test="${mission.highestPosition==3}">小队</c:when>
									</c:choose>	
								</s:label></td>
				<td align="right">打卡模式:</td>
				<td align="left"><s:label>
									<c:choose>
										<c:when test="${mission.checkOnMode==1}">短信</c:when>
										<c:when test="${mission.checkOnMode==2}">打卡机</c:when>
									</c:choose>	
								</s:label></td>
                <td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">实施日期:</td>
				<td colspan="3" align="left"><s:label maxlength="5" name="mission.startDate" />
					&nbsp;&nbsp;至&nbsp;&nbsp;<s:label  name="mission.endDate" /></td>
				<td>&nbsp;</td>
                
			</tr>
				
			<tr>
				<td align="right">举办地点:</td>
				<td colspan="3" align="left"><s:label cssClass="txtW5" name="mission.venue" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">详细地址:</td>
				<td colspan="3" align="left"><s:label cssClass="txtW5" name="mission.venueAddress" /></td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">项目内容:</td>
				<td colspan="3" align="left"><s:textarea name="mission.detailInfo" cssClass="txtW5" rows="4" /></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">项目备注:</td>
				<td colspan="3" align="left"><s:textarea name="mission.remark" cssClass="txtW5" rows="4" /></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">任务简介:</td>
				<td colspan="3" align="left"><s:textarea name="mission.summary" cssClass="txtW5" rows="4" /></td>
				<td>&nbsp;</td>
			</tr>
			
			<tr>
				<td align="right">&nbsp;</td>
				<td colspan="3" align="left">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td align="right">&nbsp;</td>
				<td colspan="3" align="left">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
			
		<table width="700px" border="0" align="center" cellspacing="4" class="form_tb">
			<caption>验证情况</caption>
			
		 	<tr>
				<td width="111" align="right"> 审批评语类型:</td>
				<td width="570" align="left"><s:select listKey="key" list="%{#{'1':'志愿者太多人','2':'志愿者太不主动','3':'良好'}}" name="missionVerification.commentType"/></td>
                
		   	</tr>
		    
		   	<tr>
				<td align="right">自定义审批评语:</td>
      			<td align="left"><s:textarea name="missionVerification.commentCustomize" cssClass="txtW5" rows="4"/></td>
		  	</tr>
		  	
		</table>
			
		<table width="700px" border="0" align="center" cellspacing="3" class="form_tb">
			<caption>任务审批记录 </caption>
 			<tr>
				<td><input icon="icon-apply" type ="button" onClick="javascript:saveState(25)" value="审批通过"/>
					<input icon="icon-cancel" type ="button" onClick="javascript:saveState(26)" value="审批不通过"/>
			</tr>
		</table>
  </s:form>
	<%@ include file="../common/incFooter.jsp" %>
  </body>
</html>
