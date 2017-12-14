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
			<s:form id="strutsForm" action="doAddItems" namespace="/gmmanage"
				method="post">
				<s:token></s:token>
				<s:fielderror></s:fielderror>
				<br>
				<br>
				<br>
				<br>
				<s:property value="#request.message" />
				<table width="600" >
					<tr>
						<td>
							<a href="page!addItems.do"
								style="color: blue; text-decoration: underline;"><b>指定玩家名</b>
							</a>
						</td>
						<td>
							<a href="page!addItemsByCondition.do"
								style="color: blue; text-decoration: underline;"><b>按条件</b>
							</a>
						</td>
						<td>
							<a href="page!addItemsByAll.do"
								style="color: blue; text-decoration: underline;"><b>全部发送</b>
							</a>
						</td>
					</tr>
				</table>
				<table width="600" border="0" class="form_tb">
					<caption>
						增加道具
					</caption>
					<tr>
						<td colspan="2">&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan=2>
			                <input type="button" id="btnAddPrize" value="增加道具" />&nbsp;&nbsp;<a href="gamer_item_list.php" target="_BLANK" style="border-bottom:1px solid red;"><font  color='red'><b>查看道具列表</b></font></a>
			            </td>
					</tr>
					<tr>
				<td colspan=2>
	                	<table id="tblPrizes" width="100%" border="1"cellspacing="0" cellpadding="0">
	                	<tbody>
	                						</tbody>
					    </table>
					</td>
				</tr>
					
					<tr>
						<td width="30%" align="right">
							玩家昵称,以 ,隔开
						</td>
						<td align="left">
							<s:textarea name="addItemsVo.searchAccount" cssClass="txtW5" rows="4"/>
						</td>
					</tr>
					<tr>
						<td width="30%" align="right">
							内容:
						</td>
						<td>
							<s:textarea name="content" id="content" cssClass="txtW5" rows="4"/>
						</td>
					</tr>
					<tr>
				  	    <td colspan="2" align="center"><s:submit icon="icon-apply" value="提交"></s:submit>
				        </td>
			        </tr>
				</table>
			</s:form>
			<%@ include file="../common/incFooter.jsp"%></center>
		
	</body>
	<textarea style="display:none" id="prizeTpl">
			<tr><td><div id="delPrize"  style="text-align:center;"><a href="javascript:void(0);"><font color='blue'>点我删除↓面道具</font></a></div>
			<table id="table"  width="100%" border="1"cellspacing="0" cellpadding="0">
			<tbody>
				<tr>
					<td>物品ID：<input type="text" name="typeid__index_" value="" /></td>
				</tr>
				<tr class="odd">
					<td>赠送数量：(装备最多1件，其他最多50个)<input type="text" name="number__index_" value="" />
					</td>
				</tr>
			<tbody>
			</table>
			</tr></td>
			</textarea>
			
			<script language="javascript" >
			jQuery(document).ready(function(){
			    window.prizeIndex = $("#tblPrizes>tbody>tr").size();
			    $("div:first-child").bind("click",delPrize);
			    $("#btnAddPrize").click(function(){
			        var strTrPrize =  $("#prizeTpl").text();
			        strTrPrize = strTrPrize.replace(/_index_/g,window.prizeIndex);
			        $("#tblPrizes>tbody").append(strTrPrize);
			        $("div:first-child").bind("click",delPrize);
			        window.prizeIndex +=1;
			        return false;
			    });
			
			    $(".delPrize").click(delPrize);
			});
			
			function delPrize(){
			        $(this).parent().parent().remove();
			        return false;
			}
			</script>
	
</html>
