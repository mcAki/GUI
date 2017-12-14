<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const"%>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<%@ include file="../common/incHead.jsp"%>
		 <script type="text/javascript">
         	function addUser(uid,username){
         		$.post("${pageContext.request.contextPath}/mission/personal!addTeamPersonal.action?uuid="+ uid +"&missionId=${param.missionId}&teamId=${param.teamId}",null,function(data)
                 {
         			var sTR;
    				sTR = '<td><input id="uuids" name="uuids" type="checkbox" value="' + uid + '"/></td><td>' + username 
    				+ '</td><td><input name="uuid" type="hidden" value="' + uid + '"/>'
    				+ '<a href="#">查看</a>&nbsp;<a href="javascript:delUser(\''+ uid +'\')">移出队伍</a></td>';
    				sTR = '<tr class="odd">' + sTR + '</tr>';
    				$('#listtable tbody').append(sTR);
                 });
			}
         	function delUser(uid){
         		$.post("${pageContext.request.contextPath}/mission/personal!delTeamPersonal.do?teamId=${param.teamId}&uuids="+ uid +"&missionId=${param.missionId}",null,null);
         		$('#listtable tbody tr td input').each(
        				function(){
        					if($(this).val()==uid){
        						$(this).parent().parent().remove(); 
        					}
        				}
        		);
         		$(window.frames["iframe1"].document).find("input[type='hidden']").each(
       				function(){
       					if($(this).val()==uid){
       						$(this).parent().find("a").eq(1).show();
       					}
        			}
        		);
			}
			function checkUser(uid){
             	var flag = false;
             	$('#listtable tbody tr').each(
        				function(){
            				var nodata =$(this).eq(0).find("td").size(); 
            				if(nodata==1)
            				{
            					$(this).remove();
            				}
        				}
        			);
    			$("#listtable tbody tr td input[type='hidden']").each(
    				function(){
    					if($(this).val()==uid){
    						flag = true;
    					}
    				}
    			);
    			return flag;
    		}
         </script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<s:form name="form1" action="list" namespace="/missionTeam"
			method="post">
			<c:if test="${null!=currentMissionTeam}">
				<table width="800" border="0" align="center" cellspacing="4"
					class="form_tb">
					<tr style="border: double 3px;">
						<td align="right">
							<h2>
								<strong> <%=Const.MISSION_TEAM_LEVEL_NAMES[((MissionTeam) request.getAttribute("currentMissionTeam")).getTeamLevel()]%>: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong>${currentMissionTeam.missionTeamname}</strong>
							</h2>
						</td>
						<td align="right">
							<h2>
								<strong> 队 长: </strong>
							</h2>
						</td>
						<td align="left">
							<h2>
								<strong> ${currentMissionTeam.currentLeaderName} </strong>
							</h2>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center"><input type="button" id="idBackBtn" icon="icon-back" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/missionTeam/list!showSubTeam.do?teamId=${param.teamId}'"/></td>
					</tr>
				</table>
			</c:if>
		</s:form>
		<table width="950" border="0">
			<tr>
				<td align="left" width="700">
					<iframe src="${pageContext.request.contextPath}/mission/personal!findTeamPersonalIframe.do?missionId=${param.missionId}&teamId=${param.teamId}" id="iframe1" name="iframe1" width="750px" 
					height="900" scrolling="No" frameborder="0"></iframe>
				</td>
				<td width="250">
					<center>
					<s:form id="form1" name="form0"
			action="personal!delTeamPersonal" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden>
						<display:table name="list" id="listtable" style="width:250px;"
							sort="external" cellspacing="1" class="list_tb"
							requestURI="${pageContext.request.contextPath}/mission/page!recruitBefore.do">
							<display:caption>
								<c:choose>
									<c:when test="${listtable.missionTeam.teamLevel==1}">当前大队下所有队员</c:when>
									<c:when test="${listtable.missionTeam.teamLevel==2}">当前中队下所有队员</c:when>
									<c:when test="${listtable.missionTeam.teamLevel==3}">当前小队下所有队员</c:when>
								</c:choose>
								(允许${listtable.missionTeam.count}人)
							</display:caption>
							<display:column title="选择">
									<input type="checkbox" id="uuids" name="uuids" value="${listtable.usersByUserid.userId}" />
								</display:column>
							<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
							<display:column title="操作">
								<input type="hidden" id="uuid" name="uuid" value="${listtable.usersByUserid.userId}"/>
								<a href="${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?uuid=${listtable.usersByUserid.userId}">查看</a>
								<a href="javascript:delUser('${listtable.usersByUserid.userId}')">移出队伍</a>
							</display:column>
						</display:table>
						<input type="hidden" name="teamId" value="${param.teamId}"/>
						<input type="checkbox" onclick="checkAll(this, 'uuids','no')" />
						全选&nbsp;
						<input type="submit" value="删除" />&nbsp;
						</s:form>
					</center>
				</td>
			</tr>
		</table>
		</center>
		 <s:fielderror/>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
