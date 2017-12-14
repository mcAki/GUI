<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const" %>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
<script language="javascript" type="text/javascript" >
	function showUsers(missionId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		var returnVal = ezModal('${pageContext.request.contextPath}/mission/personal!showPersonal.do?missionId='+missionId,
								700,600);
		//jq检测是否为空
		if(!$.isEmptyObject(returnVal)){
			//赋值!
			$("#userId").val(returnVal.uid);
			$("#currentLeaderName").val(returnVal.username);
		}
	}
	
	
	//保存
	function doUpdate(operateCode) {
		var formname = document.getElementById("form1");
		//var path = ${pageContext.request.contextPath};
		
		switch(operateCode){
			//保存
			case 1:
				formname.action = '${pageContext.request.contextPath}/missionTeam/doUpdate.do?operateCode='+operateCode;
				break;
			//修改
			case 2:
				formname.action = '${pageContext.request.contextPath}/missionTeam/doUpdate.do?operateCode='+operateCode;
				break;
			default:
				alert('提交出错');
				return;
		}
		
		formname.submit();
		
	}
	//保存并发送审核
	function gotoSaveEx() {
		
	}

</script>
<script type="text/javascript">

</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:form id="form1" name="form1" action="doUpdate" namespace="/missionTeam"
			method="post">
			<center><s:fielderror></s:fielderror></center>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<table width="800" border="0" align="center" cellspacing="3"
				class="form_tb">
				<caption>
					<h2>
						<strong>添加队伍</strong>
					</h2>
				</caption>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td align="right" style="width:100px">
					<s:hidden name="teamId"></s:hidden>
						上级队伍:
					</td>
					<td align="left" style="width:300px">
					<c:if test="${null!=missionTeam.missionTeam}">
						<s:label name="missionTeam.missionTeam.missionTeamname"></s:label>
						（<%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam)request.getAttribute("missionTeam")).getMissionTeam().getTeamLevel()] %>）
					</c:if>
					</td>
					<td align="right" style="width:100px">当前队伍级别:</td>
					<td align="left" style="width:300px">
						<%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam)request.getAttribute("missionTeam")).getTeamLevel()] %>
						<s:hidden name="missionTeam.teamLevel"></s:hidden>
					</td>
					
				</tr>
				<tr>
                    <td align="right">
						队伍名称:
						<s:hidden name="missionId"></s:hidden>
					</td>
					<td align="left" >
						<s:textfield name="missionTeam.missionTeamname"></s:textfield>
						<s:hidden name="missionTeam.missionTeamId"></s:hidden>
					</td>
					<td align="right">
						选择队长:
					</td>
					<td align="left">
						<s:hidden id="userId" name="missionTeam.users.userId"></s:hidden>
						<s:textfield name="missionTeam.currentLeaderName"
							id="currentLeaderName" readonly="true" />
						<input icon="icon-prop" type="button" value="选择"
							onClick="showUsers(${missionId});" />&nbsp;
						<input icon="icon-delete" type="button" value="撤下队长" onClick="window.location.href='${pageContext.request.contextPath}/missionTeam/deleteLeader.do?userId=${missionTeam.users.userId}&teamId=${missionTeam.missionTeamId }&missionId=${missionId }'"/>&nbsp;
					</td>
                    </tr>
				
				<tr>
					<td align="right">队伍状态:</td>
					<td align="left">
						<s:select cssClass="txtW2" name="missionTeam.teamType" list="%{#{0:'停用',1:'一般',2:'临时小队'}}"/>
					</td>
					<td align="right">&nbsp;</td>
			    <td align="left"></td>
					
                </tr>
                <tr>
					
					<td colspan="4" align="center">项目职责:</td>
		    	</tr>
                <tr>
                <td colspan="4">
                <s:textarea name="missionTeam.memo" style=" width:700px;height:100px"></s:textarea>
                </td>
                </tr>
				<tr>
					<td colspan="2" align="center">
						<s:submit icon="icon-apply" value="提交"/></td>
					<td colspan="1" align="center">
						<s:reset icon="icon-reload" value="重设"></s:reset>
					</td>
					<td colspan="1" align="center">
						<input type="button" id="idBackBtn" icon="icon-back" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${teamId }'"/>
					</td>
				</tr>

			</table>
			<td>&nbsp;
				

			</td>
		</s:form>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
