<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<script type="text/javascript">
			function batchAddUser(itemName){
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
				form1.action="${pageContext.request.contextPath}/mission/personal!batchRecruit.do"
				form1.userId.value=datas;
				form1.missionId.value=${mission.missionId};
				form1.submit();
			}


			function showUserProfile(userId) {
				//杨氏简易弹出窗  common.js
				//alert(missionId);
				 ezModal(
						'${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?userId='
								+ userId + '&operateCode=1' ,
								 700, 600);
				//jq检测是否为空
				//if(!$.isEmptyObject(returnVal)){
				//赋值!
				//	$("#userId").val(returnVal.uid);
				//	$("#currentLeaderName").val(returnVal.username);
				//}
			}
		</script>
		<!-- 不创建新窗口，在本窗口显示东西 -->
		<base target="_self">
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
		<s:form id="form1" name="form1"
			method="post">
			<s:hidden name="userId"/>
			<input type="hidden" name="missionId" value="${mission.missionId }" />
				<display:table id="dtable" name="users" style="width:600px;"
					sort="external" pagesize="100" cellspacing="1"
					class="list_tb"
					requestURI="${pageContext.request.contextPath}/mission/personal!listUserForRecruit.do">
					<display:caption>所有志愿者</display:caption>
					<display:column title="选择">
						<input type="checkbox" id="chk" name='chk' value="${dtable.userId}" />
					</display:column>
					<display:column title="真实姓名" property="userName"></display:column>
					<display:column title="证件号" property="idcardCode"></display:column>
					<display:column title="性别">
						<c:choose>
							<c:when test="${dtable.gender==1}">男</c:when>
							<c:when test="${dtable.gender==2}">女</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="注册时间" property="createDate" format="{0,date,yyyy-MM-dd hh:mm:ss}"></display:column>
					<display:column title="操作">
						<a href="${pageContext.request.contextPath}/mission/personal!batchRecruit.do?userId=${dtable.userId}&missionId=${mission.missionId}">添加</a>&nbsp;
					</display:column>
				</display:table>&nbsp;
				<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选
				<input icon="icon-add-row" type="button" value="添加" onclick="javascript:batchAddUser('chk')"/>&nbsp;
				<input icon="icon-check" type="button" value="查看已招募人员" onclick="window.location.href='${pageContext.request.contextPath}/mission/personal!listPersonalForLeader.do?missionId=${mission.missionId }'"/>
				<br/>
			</s:form>
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
