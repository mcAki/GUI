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
					document.form1.action="${pageContext.request.contextPath}/mission/personal!delsmsSendPersonal.do";
					document.form1.submit();
				}
			}
		</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<center>
		<s:form id="form1" name="form1"
				action="personal!smsSend" namespace="/mission"
				method="post">
				<display:table id="dtable" name="pageView" style="width:850px;"
					sort="external" pagesize="20" cellspacing="1"
					class="list_tb"
					requestURI="${pageContext.request.contextPath}/mission/personal!findsmsSendPersonal.do">
					<display:caption>当前项目所有准备发短信志愿者</display:caption>
					<display:column title="选择">
					<input type="checkbox"
									id="chk" name="uuids"
									value="${dtable.id}" />
					</display:column>
					<display:column title="真实姓名" property="realname"></display:column>
					<display:column property="idcardCode" title="证件号"></display:column>
					<display:column title="性别">
					<c:choose>
							<c:when test="${dtable.gender==1}">男</c:when>
							<c:when test="${dtable.gender==2}">女</c:when>
							<c:otherwise>未知</c:otherwise>
						</c:choose>
					</display:column>
					<display:column property="mobile" title="手机号码"></display:column>
					<display:column property="createDate" title="注册时间"></display:column>
					<display:column property="selectedDatetime" title="加入时间"></display:column>
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
						<a href="${pageContext.request.contextPath}/usersutils/viewUserProfile!viewUserProfile.do?uuid=">查看</a>
					</display:column>
				</display:table>&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="hidden" name="missionId" value="${param.missionId}" />	
			<input type="checkbox" onclick="checkAll(this, 'uuids','no')" />全选
			<input type="checkbox" onclick="checkAll(this, 'uuids','yes')" />反选&nbsp;
			<input type="button" value="删除" onclick="delperson()"/>&nbsp;&nbsp;
			<input icon="icon-back" type="button" value="返回" onclick="javascript:self.location='${pageContext.request.contextPath}/mission/personal!findPersonalSend.do?missionId=${param.missionId}'"/>&nbsp;&nbsp;
			<br />
			<p>
				<table>
					<caption>
						发送信息内容
					</caption>
					<tr>
						<td>
							<textarea rows="6" cols="70" name="smscontent"></textarea>
						</td>
					</tr>
					<tr>
						<td align="center">
							<input type="submit" value="确定发送短信" />&nbsp;&nbsp;
							<input icon="icon-back" type="button" value="返回" onclick="javascript:self.location='${pageContext.request.contextPath}/mission/personal!findPersonalSend.do?missionId=${param.missionId}'"/>&nbsp;
						</td>
					</tr>
				</table>
			</p>
			</s:form>
		</center>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
