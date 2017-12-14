<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript">
	//保存
	/*function gotoSave(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		
		switch(operateCode){
			case 1:
				formname.action = '${pageContext.request.contextPath}/missionPersonal/doAdd.do?operateCode='+operateCode;
				break;
			case 20:
				formname.action = '${pageContext.request.contextPath}/missionPersonal/doAdd.do?operateCode='+operateCode;
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}*/
	//跳转考勤--小队页面
	function gotoCheckOn() {
		var formname = document.getElementById("form1");
		formname.action = '${pageContext.request.contextPath}/missionPersonal/doAdd.do
	}
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:form id="form1" name="form1" action="doUpdate" namespace="/missionPersonal"
			method="post">
			<p>&nbsp;
				
			</p>
			<p>&nbsp;
				
			</p>
			<table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					<h2>
						<strong>个人项目记录</strong>
					</h2>
				</caption>
				<tr>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td align="right">
						项目名称:
					</td>
					<td width="228" align="left" style="white-space: pre-line">
						<s:textfield name="missionPersonal.mission.subject" readonly="true"></s:textfield>
					</td>
					<td align="right">所属小队ID:</td>
					<td align="left" style="">
						<s:textfield name="missionPersonal.missionTeam.missionTeamId" readonly="true"></s:textfield>
					</td>
					<td width="47"></td>
				</tr>
				<tr>
					<td width="108" align="right">志愿者名:</td>
					<td align="left"><s:textfield name="missionPersonal.userName" readonly="true"></s:textfield></td>
					<td width="154" align="right">志愿者手机号码</td>
					<td align="left"><s:textfield name="missionPersonal.mobile" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td align="right">
						报名时间:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.signDatetime" readonly="true"></s:textfield>
					</td>
					<td align="right">
						录取时间:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.selectedDatetime" readonly="true"></s:textfield>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">
						招募志愿者方式:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.recruitType" readonly="true"></s:textfield>
					</td>
					<td align="right">
						是否为正式人员:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.selection" readonly="true"></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						是否允许考勤:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.isCheckOnPerson" readonly="true"></s:textfield>
					</td>
					<td align="right">
						所属队长名:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.leaderName" readonly="true"></s:textfield>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">
						所在职务:
					</td>
					<td align="left">
						<s:textfield name="missionPersonal.missionPosition.missionPositionName" readonly="true"></s:textfield>
					</td>
					<td align="right">&nbsp;</td>
					<td>&nbsp;
						
					</td>
				</tr>
				
				
				<tr>
					<td align="right">&nbsp;
						
					</td>
					<td colspan="3" align="left">&nbsp;
						
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">&nbsp;
						
					</td>
					<td colspan="3" align="left">&nbsp;
						
					</td>
					<td></td>
				</tr>
			</table>
			<td>&nbsp;
				
</td>
			</tr>
			</table>
			<table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					任 务 功 能
				</caption>
				<tr>
					<td>
						<c:choose>
							<c:when test="${missionPersonal.missionPosition.missionPositionId==0||missionPersonal.missionPosition.missionPositionId==1}">
							</c:when>
							<c:otherwise>
								<div>
									考勤:&nbsp;&nbsp;
									<input type="button" value="考勤" onClick="javascript: gotoCheckOn();"/>
									&nbsp;&nbsp;&nbsp;
									
									&nbsp;&nbsp;&nbsp;
									<input type="button" value="返回" />
								</div>
								
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
