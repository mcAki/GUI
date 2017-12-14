<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<script type="text/javascript">
			function delperson()
			{
				if(checkselect(form1))
				{
					document.form1.action="${pageContext.request.contextPath}/mission/personal!delPersonal.do";
					document.form1.submit();
				}
			}
			function updatestatus(val)
			{
				if(checkselect(form1))
				{
					document.form1.action="${pageContext.request.contextPath}/mission/personal!updatePersonalstatus.do?status="+val+"";
					document.form1.submit();
				}
			}
			function sendMessage()
			{
				document.form1.action="${pageContext.request.contextPath}/mission/personal!smsSendBefore.do";
				document.form1.submit();
			}
			function sendselect()
			{
				if(checkselect(form1))
				{
					document.form1.uStatus.value="1";
					document.form1.submit();
				}
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
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center><br />
		<br />
		
		<div align="center">
			<s:form id="form0" name="form0"
			action="personal!findPersonalSend" namespace="/mission"
			method="post"><s:hidden name="missionId"></s:hidden>
			<table>
				<tr>				
					<td>
						&nbsp;状态:&nbsp;
					</td>					
					<td>
						<s:select name="selection" list="%{#{-1:'显示所有',0:'未确定',1:'已确认',2:'不录用',3:'待确认',4:'本人已确认',5:'拒绝或超时',255:'退出'}}" listKey="key"></s:select>
					</td>
					<td>
						&nbsp;姓名:&nbsp;
					</td>
					<td>
						<input type="text" name="username" size="10" value="${param.username}"/>
					</td>
					<td>
						&nbsp;电话号码:&nbsp;
					</td>
					<td>
						<input type="text" name="mobile" size="15" value="${param.mobile}"/>
					</td>
				</tr>
				<tr>
					<td colspan="6" align="center">
						<input type="submit" value="搜索"/>
					</td>
				</tr>
			</table>
			</s:form>
		</div>
		<s:form id="form1" name="form1"
				action="personal!smsSend" namespace="/mission"
				method="post">
				<display:table id="dtable" name="pageView" style="width:850px;"
					sort="external" pagesize="${pageSize}" cellspacing="1"
					class="list_tb"
					requestURI="${pageContext.request.contextPath}/mission/personal!findPersonalSend.do">
					<display:caption>当前项目所有选定志愿者</display:caption>
					<display:column title="选择">
					<input type="checkbox"
									id="chk" name="uuids"
									value="${dtable.missionPersonlId}" /></display:column>
					<display:column title="真实姓名" property="usersByUserid.userName"></display:column>
					<display:column title="证件号" property="usersByUserid.idcardCode"></display:column>
					<display:column title="性别">
						<c:choose>
							<c:when test="${dtable.usersByUserid.gender==1}">男</c:when>
							<c:when test="${dtable.usersByUserid.gender==2}">女</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</display:column>
					<display:column title="手机号码" property="usersByUserid.mobile"></display:column>
					<display:column title="注册时间" property="usersByUserid.createDate"></display:column>
					<display:column title="加入时间" property="selectedDatetime" format="{0,date,yyyy-MM-dd hh:mm:ss}"></display:column>
					<display:column title="状态">
					<c:choose>
						<c:when test="${dtable.selection==1}">已录用</c:when>
						<c:when test="${dtable.selection==2}">不录用</c:when>
						<c:when test="${dtable.selection==3}">待志愿者确认</c:when>
						<c:when test="${dtable.selection==4}">志愿者已确认</c:when>
						<c:when test="${dtable.selection==5}">志愿者拒绝或超时回应</c:when>
						<c:when test="${dtable.selection==255}">退出</c:when>
						<c:otherwise>
							未确定
						</c:otherwise>
					</c:choose>
					</display:column>
					<display:column title="操作">
						<a href="javascript:void(0)" onclick="showUserProfile('${dtable.usersByUserid.userId}')">查看</a>
					</display:column>
				</display:table>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" name="missionId" value="${param.missionId}" />
			<input type="hidden" name="uStatus" value="0"/>	
			<input type="checkbox" onclick="checkAll(this, 'uuids','no')" />全选
			<input type="checkbox" onclick="checkAll(this, 'uuids','yes')" />反选&nbsp;
			<input type="button" value="删除" onclick="delperson()"/>&nbsp;&nbsp;
			<input type="button" value="正常录用" onclick="updatestatus(1)"/>&nbsp;&nbsp;
			<input type="button" value="强制录用" onclick="updatestatus(0)"/>&nbsp;&nbsp;
			<input type="button" value="发送短信" onclick="sendMessage()"/>&nbsp;&nbsp;
			<input icon="icon-back" type="button" value="返回" onclick="window.location.href='${pageContext.request.contextPath}/mission/list!manage.do'"/>&nbsp;
			<br />
			</s:form>
		</center>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
