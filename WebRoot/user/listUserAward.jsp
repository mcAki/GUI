<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
			<display:table id="idListTb" name="pageView" style="width:600px;" sort="external" pagesize="${pageSize}" 
			cellspacing="1" class="list_tb" requestURI="${pageContext.request.contextPath}/usersutils/list.do">

				<display:column maxLength="10" property="users.userName" sortName="id" title="获奖者" />
				<display:column property="awardName" title="奖项名" />
				<display:column property="integralAward" title="奖积分" />
				<display:column property="gainAwardDate" title="获奖时间" format="{0,date,yyyy年MM月dd日}" />
				<display:column maxLength="20" property="gainAwardReason" title="获奖原因" />
				<display:column title="获奖状态" >
					<c:choose>
						<c:when test="${idListTb.awardState==0}">取消</c:when>
						<c:when test="${idListTb.awardState==1}">正常发放</c:when>
					</c:choose>
				</display:column>

			</display:table>
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
