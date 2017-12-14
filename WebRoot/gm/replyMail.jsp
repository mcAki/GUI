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
			<s:form id="strutsForm" action="doReplyMail" namespace="/gmmanage"
				method="post">
				<s:token></s:token>
				<s:fielderror></s:fielderror>
				<br>
				<br>
				<br>
				<br>
				<s:property value="#request.message" />
				<table width="600" border="0" class="form_tb">
					<caption>
						邮件信息
					</caption>
					<tr>
						<td colspan="2">&nbsp;
							<input type="hidden" value="${gmMail.id }" name="mailId" />
						</td>
					</tr>
					<tr>
						<td align="right">
							发送玩家:
						</td>
						<td align="left">
							${gmMail.sendUserName }
						</td>
					</tr>
					<tr>
						<td align="right">
							发送时间:
						</td>
						<td align="left">
							<fmt:formatDate value="${gmMail.sendDatetime }" pattern="yyyy-MM-dd HH:mm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							标题:
						</td>
						<td align="left">
							${gmMail.topic }
						</td>
					</tr>
					<tr>
						<td align="right">
							发送内容:
						</td>
						<td align="left">
							${gmMail.content }
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">
							回复内容:
						</td>
						<td>
							<s:textarea name="content" id="content" cssClass="txtW5" rows="4"/>
						</td>
					</tr>
					<tr><td colspan="2">&nbsp;</td></tr>
					<tr>
				  	    <td colspan="2" align="center" valign="middle"><s:submit icon="icon-apply" value="回复"></s:submit>
				        </td>
			        </tr>
				</table>
				</br>
			</s:form>
			<%@ include file="../common/incFooter.jsp"%></center>
		
	</body>
</html>