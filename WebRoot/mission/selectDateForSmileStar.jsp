<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css"/>
		<link href="../css/sys.css" rel="stylesheet" type="text/css"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin.js"></script>
		<%@ include file="../common/incHead.jsp"%>
		 <script type="text/javascript">
			function showAwardPersonal(isCommit){
				var selectDate=document.getElementById("idSelectDate").value;
				var isCommitVal=isCommit;
				form1.action='${pageContext.request.contextPath}/usersutils/listAwardPersonalBySelectDate.do';
				form1.isCommit.value=isCommitVal;
				form1.selectDate.value=selectDate;
				form1.submit();
			}
         </script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br/><br/><br/>
		<center>
		<s:form id="form1" name="form1" action="listAwardPersonalBySelectDate" namespace="/usersutils"
			method="post" target="iframe1">
		<table width="700px" >
		<tr>
			<td colspan="4"></td>
			<s:hidden name="teamId"></s:hidden>
			<s:hidden name="missionId" />
			<s:hidden name="isCommit" />
		</tr>
		<tr>
			<td align="right">
				选择奖励:
			</td>
			<td align="left">
				<s:select name="selectAwardId" list="%{#{0:'显示所有',1:'每日微笑之星',5:'小队微笑之星',6:'中队微笑之星',7:'大队微笑之星'}}" listKey="key"></s:select>
			</td>
			<td align="right">
				选择开始日期:
			</td>
			<td align="left">
				<s:textfield name="selectDate" id="idSelectDate" onclick="WdatePicker()" readonly="true" />
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<input type="button" value="搜索" onclick="showAwardPersonal(1)"/>
				<input type="button" value="搜索未确认奖励" onclick="showAwardPersonal(0)"/>
			</td>
		</tr>
			<tr>
				<td align="right" colspan="4" width="950px" rowspan=2>
				<iframe name="iframe1" id="iframe1" width="1024px" height="700" scrolling="Auto" frameborder="2"></iframe>
				</td>	
			</tr>
		</table>
		</center>
		
		<br/>
		<br />
		<center>
		 <s:fielderror/>
		 </s:form>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
