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
				<s:form id="form1" name="form0"
							action="list!calLoginByEverydayBar.do" namespace="/gmmanage"
							method="post">
							<center>
								<table >
									<tr >
									    <td>请选择日期:&nbsp;</td>
										<td colspan="2">&nbsp;<input id="d52211" name="startDate" class="Wdate" type="text" onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();},maxDate:'#F{$dp.$D(\'d52222\')}'})"/>
												至
											<input id="d52222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})"/>&nbsp;</td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</center>
							</s:form>
				<br>
				<br>
				<s:property value="#request.message" />
				<table width="750" border="0" class="form_tb">
					<caption>
						玩家每日登陆信息
					</caption>
					<tr>
						<td colspan="2">&nbsp;
							<s:hidden name="userId"/>
						</td>
					</tr>
					<tr>
						<td align="center">
							<img src="${graphURL}" width="750" height="450" border="0" usemap="#${filename}" >
						</td>
					</tr>
				</table>
			<%@ include file="../common/incFooter.jsp"%></center>
		
	</body>
</html>
