<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.pojo.MissionTeam"%>
<%@page import="com.sys.volunteer.common.Const"%>
<%@page import="com.sys.volunteer.mission.ListMissionTeamAction"%>
<%@page import="com.sys.volunteer.mission.missionUtil.MissionUtil"%>

<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
				<script type="text/javascript" src="../js/admin.js"></script>
				<script type="text/javascript">
	function showUserProfile(userId, teamId, missionId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showUserProfile.do?userId='
						+ userId + '&teamId=' + teamId + '&missionId='
						+ missionId, 700, 600);
		//jq检测是否为空
		//if(!$.isEmptyObject(returnVal)){
		//赋值!
		//	$("#userId").val(returnVal.uid);
		//	$("#currentLeaderName").val(returnVal.username);
		//}
	}
	
	function batchAddPersonal(itemName) {
			var datas = "";
			var items = document.getElementsByName(itemName);
			for ( var i = 0; i < items.length; i++) {
				if(items[i].type=="checkbox"){
					var pid = items[i].value;
					if (items[i].checked) {
						datas = pid + ","+datas;
					}
				}
			}
			submitForm.action="${pageContext.request.contextPath}/missionservicelog/batchAddServiceLog.do";
			submitForm.spid.value = datas;
			submitForm.submit();
	}
	
	function check(selectId){
		if(selectId==1){
		
		}else if(selectId==2){
			
		}else{
			
		}
	}
	
	function changeState(rdo){
		if(rdo.value==1){
			document.getElementById('idSelectTime').style.display="block";
			document.getElementById('idSelectMinute').style.display="none";
			display='none';
		}else if(rdo.value==2){
			document.getElementById('idSelectTime').style.display="block";
			document.getElementById('idSelectMinute').style.display="block";
		}else if(rdo.value==3){
			document.getElementById('idSelectTime').style.display="none";
			document.getElementById('idSelectMinute').style.display="none";
		}else if(rdo.value==4){
			document.getElementById('idSelectTime').style.display="none";
			document.getElementById('idSelectMinute').style.display="none";
		}

	}

</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<table>
			<tr>
				<td>
						<display:table id="list" name="missionPersonals" style="width:650px;"
				sort="external" pagesize="999" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/missionservicelog/list!batchServiceLogByPersonal.do">
				<display:setProperty name="paging.banner.placement" value="bottom" />
				<display:caption title="队员列表">队员列表</display:caption>
				<display:column title="选择">
					<input type="checkbox" name="chk" value="${list.usersByUserid.userId }"/>
				</display:column>
				<display:column property="missionTeam.missionTeamname" title="队伍名称"></display:column>
				<display:column title="队员姓名">
					<a onclick="showUserProfile('${list.usersByUserid.userId}','${list.missionTeam.missionTeamId }','${list.mission.missionId }')">
						<c:choose>
							<c:when
								test="${list.missionPosition.missionPositionId==15}">
								<b class="leader">${list.userName}(大队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==13}">
								<b class="leader">${list.userName}(中队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==11}">
								<b class="leader">${list.userName}(小队长)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==14}">
								<b class="subleader">${list.userName}(副大队)</b>
							</c:when>
							<c:when
								test="${list.missionPosition.missionPositionId==12}">
								<b class="subleader">${list.userName}(副中队)</b>
							</c:when>
							<c:when test="${list.missionPosition.missionPositionId==3}">
								<b class="assleader">${list.userName}(监督)</b>
							</c:when>
							<c:otherwise>
						${list.userName}
					</c:otherwise>
						</c:choose>
					</a>
				</display:column>
				<display:column property="mobile" title="手机号码" />
			</display:table> 
			<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选
				<input icon="icon-save" type="button" value="保存"
				onclick="javascript:batchAddPersonal('chk')" />
				</td>
			</tr>
		</table>
		</center>
		
		<form id="submitForm" name="submitForm" >
			<input type="hidden" id="spid" name="userId"/>
			<s:hidden name="missionId"></s:hidden>
			<s:hidden name="checkOnDate"/>
			<s:hidden name="item"/>
			<table width="670" border="0" align="center" cellspacing="3" class="form_tb">
				<tr>
					<td colspan="4"  style="line-height:5px;"></td>
				</tr>
					<tr id="idRadio">
						<td colspan="4" align="center">选择考勤情况:
	                    <s:radio listKey="key" list="%{#{1:'正常考勤',2:'迟到/早退',3:'旷工',4:'请假'}}" name="status" value="1" onclick="changeState(this);" />
						</td>
					</tr>
				<tr>
					<td colspan="4"  style="line-height:5px;"></td>
				</tr>
					<tr id="idSelectTime">
						<td width="119">出勤小时:</td>
						<td width="201"><s:select cssClass="txtW2" name="hour" list="%{#{0:'0小时',1:'1小时',2:'2小时',3:'3小时',4:'4小时',5:'5小时',6:'6小时',7:'7小时',8:'8小时',9:'9小时',10:'10小时',
																						11:'11小时',12:'12小时',13:'13小时',14:'14小时',15:'15小时',16:'16小时',17:'17小时',18:'18小时',19:'19小时',20:'20小时',
																						21:'21小时',22:'22小时',23:'23小时'}}"></s:select></td>
						<td width="125">出勤分钟:</td>
						<td width="196"><s:select cssClass="txtW2" name="minute" list="%{#{0:'0分钟',1:'1分钟',2:'2分钟',3:'3分钟',4:'4分钟',5:'5分钟',6:'6分钟',7:'7分钟',8:'8分钟',9:'9分钟',10:'10分钟',
																			11:'11分钟',12:'12分钟',13:'13分钟',14:'14分钟',15:'15分钟',16:'16分钟',17:'17分钟',18:'18分钟',19:'19分钟',20:'20分钟',
																			21:'21分钟',22:'22分钟',23:'23分钟',24:'24分钟',25:'25分钟',26:'26分钟',27:'27分钟',28:'28分钟',29:'29分钟',30:'30分钟',
																			31:'21分钟',32:'32分钟',33:'33分钟',34:'34分钟',35:'35分钟',36:'36分钟',37:'37分钟',38:'38分钟',39:'39分钟',40:'40分钟',
																			41:'41分钟',42:'42分钟',43:'43分钟',44:'44分钟',45:'45分钟',46:'46分钟',47:'47分钟',48:'48分钟',49:'49分钟',50:'50分钟',
																			51:'51分钟',52:'52分钟',53:'53分钟',54:'54分钟',55:'55分钟',56:'16分钟',57:'57分钟',58:'58分钟',59:'59分钟'}}"></s:select>
						</td>
					</tr>
					<tr id="idSelectMinute" style="display:none;">
						<td>早退分钟:</td>
						<td><s:select cssClass="txtW2" name="earlyMinute" list="%{#{0:'0分钟',1:'1分钟',2:'2分钟',3:'3分钟',4:'4分钟',5:'5分钟',6:'6分钟',7:'7分钟',8:'8分钟',9:'9分钟',10:'10分钟',
																			11:'11分钟',12:'12分钟',13:'13分钟',14:'14分钟',15:'15分钟',16:'16分钟',17:'17分钟',18:'18分钟',19:'19分钟',20:'20分钟',
																			21:'21分钟',22:'22分钟',23:'23分钟',24:'24分钟',25:'25分钟',26:'26分钟',27:'27分钟',28:'28分钟',29:'29分钟',30:'30分钟'}}"></s:select>
						</td>
						<td>迟到分钟:</td>
						<td><s:select cssClass="txtW2" name="lateMinute" list="%{#{0:'0分钟',1:'1分钟',2:'2分钟',3:'3分钟',4:'4分钟',5:'5分钟',6:'6分钟',7:'7分钟',8:'8分钟',9:'9分钟',10:'10分钟',
																			11:'11分钟',12:'12分钟',13:'13分钟',14:'14分钟',15:'15分钟',16:'16分钟',17:'17分钟',18:'18分钟',19:'19分钟',20:'20分钟',
																			21:'21分钟',22:'22分钟',23:'23分钟',24:'24分钟',25:'25分钟',26:'26分钟',27:'27分钟',28:'28分钟',29:'29分钟',30:'30分钟'}}"></s:select>
						</td>
					</tr>
					
			</table>
		</form> 


		


		<br />
		<br />

		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
