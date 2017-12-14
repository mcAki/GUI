<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>


		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery.validate.pack.js"></script>
		<script language="javascript" type="text/javascript"
			src="${pageContext.request.contextPath}/js/jquery.validate.lang.cn.js"></script>
		


		<script type="text/javascript">


	//		admin.administrator: "required",
	//			admin.passcode: "required",
	//			admin.passcode2: "required",
	//$.validator.setDefaults({
	//	submitHandler: function() { alert("submitted!"); }
	//});

	/*
	 $().ready(function() {
	 $("#normalForm").validate({
	 rules: {			
	 'admin.administrator': {
	 required: true,
	 minlength: 2
	 },
	 'admin.passcode': {
	 required: true,
	 minlength: 5
	 },
	 'admin.passcode2': {
	 required: true,
	 minlength: 5,
	 equalTo: $("#admin.passcode").value
	 },
	 'admin.realName': {
	 required: true
	 }
	 },
	 messages: {
	 'admin.administrator': "Please enter your firstname",
	 'admin.passcode': {
	 required: "Please provide a password",
	 minlength: "Your password must be at least 5 characters long"
	 },
	 'admin.passcode2': {
	 required: "Please provide a password",
	 minlength: "Your password must be at least 5 characters long",
	 equalTo: "Please enter the same password as above"
	 },
	 'admin.realName': "Please enter your realname"
	 }
	 });
	 });
	 */
</script>
		<style type="text/css">
#commentForm {
	width: 500px;
}

#commentForm label {
	width: 250px;
}

#commentForm label.error,#commentForm input.submit {
	margin-left: 253px;
}

#signupForm {
	width: 670px;
}

#signupForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}

#newsletter_topics label.error {
	display: none;
	margin-left: 103px;
}
</style>
	</head>
	<body>
		<center>
			<%@ include file="../common/incBanner.jsp"%>
				<br>
				<br>
				<br>
				<br>
				<s:property value="#request.message" />
				<table width="600" border="0" class="form_tb">
					<caption>
						玩家信息
					</caption>
					<tr>
						<td colspan="2">&nbsp;
							<s:hidden name="userId"/>
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">
							ID:
						</td>
						<td align="left">
							${userInfo.id }
						</td>
					</tr>
					<tr>
						<td align="right">
							角色名:
						</td>
						<td align="left">
							${userInfo.roleName }
						</td>
					</tr>
					<tr>
						<td align="right">
							状态:
						</td>
						<td align="left">
							<c:choose>
								<c:when test="${userInfo.state==0}">封号</c:when>
								<c:when test="${userInfo.state==1}">正常</c:when>
								<c:when test="${userInfo.state==2}">禁言</c:when>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td align="right">
							等级:
						</td>
						<td align="left">
							${userInfo.level }
						</td>
					</tr>
					<tr>
						<td align="right">
							战斗力:
						</td>
						<td align="left">
							${userInfo.combatPower }
						</td>
					</tr>
					<tr>
						<td align="right">
							充值(消费)数:
						</td>
						<td align="left">
							----
						</td>
					</tr>
					<tr>
						<td align="right">
							VIP等级:
						</td>
						<td align="left">
							${userInfo.vipLv }
						</td>
					</tr>
					<tr>
						<td align="right">
							金币:
						</td>
						<td align="left">
							${userInfo.balanceA }
						</td>
					</tr>
					<tr>
						<td align="right">
							点券:
						</td>
						<td align="left">
							${userInfo.balanceB }
						</td>
					</tr>
					<tr>
						<td align="right">
							绑定点券:
						</td>
						<td align="left">
							${userInfo.balanceC }
						</td>
					</tr>
					<tr>
						<td align="right">
							角色创建时间:
						</td>
						<td align="left">
							----
						</td>
					</tr>
					<tr>
						<td align="right">
							最后登陆时间:
						</td>
						<td align="left">
							${userInfo.lastloginTime }
						</td>
					</tr>
				</table>
			<%@ include file="../common/incFooter.jsp"%></center>
		
	</body>
</html>
