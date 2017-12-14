<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.vo.PermissionVO"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css" />
		<link href="../css/sys.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<base target="_self">
<script type="text/javascript">
	function showPermission(permissionTypeId,usergroupId) {
		//杨氏简易弹出窗  common.js
		//alert(missionId);
		var returnVal = ezModal('${pageContext.request.contextPath}/permission/list!listPermission.do?permissionTypeId='+permissionTypeId+'&usergroupId='+usergroupId,
								700,600);
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

		<s:form id="form1" name="form1" action="doUpdate"
			namespace="/permission" method="post">
			<center>
			<h4>
				<strong><%=request.getAttribute("usergroupName")%></strong>
			</h4>
			<display:table id="dtable" name="permissionTypes" style="width:450px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/permission/list!listPermissionType.do">
				<display:caption>权限类型</display:caption>
				<display:column title="ID" property="id"></display:column>
				<display:column title="权限类型名称" property="name"></display:column>
				<display:column title="操作">
					<input icon="icon-btncom" type="button" value="选择权限" onclick="showPermission(${dtable.id},${usergroupId })"/>
				</display:column>
			</display:table> </center>&nbsp;&nbsp;&nbsp;&nbsp;
			<s:hidden name="usergroupId"></s:hidden>
			<br />
		</s:form>

		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
