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
			<display:table id="idListTb" name="pageView" style="width:900px;" sort="external" pagesize="${pageSize}" 
			cellspacing="1" class="list_tb" requestURI="${pageContext.request.contextPath}/usersutils/listAwards.do">

				<display:column maxLength="10" property="awardsName" title="奖项名" />
				<display:column maxLength="10" property="timeAward" title="奖时" />
				<display:column maxLength="10" property="integralAward" title="奖积分" />
				<display:column maxLength="40" property="summary" title="获奖条件简介" />

			</display:table>
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
