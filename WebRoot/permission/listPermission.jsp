<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="com.sys.volunteer.vo.PermissionVO"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>

	<head>
	<title>请选择权限</title>
		
		<link href="../css/criteria.css" rel="stylesheet" type="text/css" />
		<link href="../css/sys.css" rel="stylesheet" type="text/css" />
		<%@ include file="../common/incHead.jsp"%>
		<script type="text/javascript" src="../js/admin.js"></script>
		<base target="_self"/>
		<script type="text/javascript">
	function batchAddPermission(itemName) {
	

			var datas = "";
			var datas2 = "";
			var items = document.getElementsByName(itemName);
			for ( var i = 0; i < items.length; i++) {
				if(items[i].type=="checkbox"){
					var pid = items[i].value;
					if (items[i].checked) {
						datas = pid + ","+datas;
					}else{
						datas2 = pid + ","+datas2;
					}
				}
			}
			//alert(datas);
			//alert(datas2);
			//document.form1.action="${pageContext.request.contextPath}/permission/doUpdate.do?pid="
			//		+ datas + "&dpid="+ datas2 +"&usergroupId=${usergroupId}";
			submitForm.action="${pageContext.request.contextPath}/permission/doUpdate.do";
			submitForm.spid.value = datas;
			submitForm.sdpid.value = datas2;
			submitForm.susergroupId.value = ${usergroupId};
			submitForm.submit();
		//window.close(); 
	}
	
	
</script>

	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>

		<s:form id="form1" name="form1" action="doUpdate"
			namespace="/permission" method="post" >
			<center>
			<h4>
			 	<strong><%=request.getAttribute("permissionTypeName")%></strong>
			</h4>
			<display:table id="dtable" name="permissionVOs" style="width:450px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/permission/list!listPermission.do">
				<display:caption>所有权限</display:caption>
				<display:column title="选择">
					<input type="checkbox" name="chk" value="${dtable.permissionId }"
						<%=((PermissionVO)pageContext.getAttribute("dtable")).getChecked()%> />
				</display:column>
				<display:column title="ID" property="permissionId"></display:column>
				<display:column title="读写权限">
					<c:choose>
						<c:when test="${dtable.privilegeAccess==-1}">
						无需设置
					</c:when>
						<c:when test="${dtable.privilegeAccess==10}">
						查(读)
					</c:when>
						<c:when test="${dtable.privilegeAccess==20}">
						改(写)
					</c:when>
						<c:when test="${dtable.privilegeAccess==30}">
						增加
					</c:when>
						<c:when test="${dtable.privilegeAccess==40}">
						删除
					</c:when>
						<c:when test="${dtable.privilegeAccess==255}">
						全部
					</c:when>
						<c:otherwise>
						通用
					</c:otherwise>
					</c:choose>
				</display:column>
				<display:column title="权限描述" property="description"></display:column>
			</display:table> </center>&nbsp;&nbsp;&nbsp;&nbsp;
			<s:hidden name="usergroupId"></s:hidden>
			<s:hidden name="permissionTypeId"></s:hidden>
			<input type="checkbox" onclick="checkAll(this, 'chk','no')" />
				全选
				<input icon="icon-save" type="button" value="保存"
				onclick="javascript:batchAddPermission('chk')" />
			<br /></center>
		</s:form>
		
		<form id="submitForm" name="submitForm" action="${pageContext.request.contextPath}/permission/doUpdate.do?pid=datas&dpid=datas2&usergroupId=${usergroupId}";>
			<input type="hidden" id="spid" name="pid"/>
			<input type="hidden" id="sdpid" name="dpid" />
			<input type="hidden" id="susergroupId" name="usergroupId"/>
		</form> 
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
