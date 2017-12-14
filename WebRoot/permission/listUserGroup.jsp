<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
				
<script type="text/javascript">

	
	function listPermission(id){
		var url;
		url='${pageContext.request.contextPath}/permission/list!listPermissionType.do?usergroupId='+id;
		document.getElementById("iframe1").src=url;
	}
	
</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br/><br/>
		<br/><center>
			<table width="1000px" border="2" align="center">
			<tr>
				<td><center>
					<display:table id="idListTb" name="usergroups" style="width:400px;"
						sort="external" pagesize="${pageSize}" cellspacing="1"
						class="list_tb"
						requestURI="${pageContext.request.contextPath}/permission/list!listUserGroup.do">
						<display:column property="groupName" title="用户组名称" />
						<display:column title="操作">
						<input type="button" id="idBtn" value="设置权限" onclick="listPermission(${idListTb.id})" />
					</display:column>
					</display:table>
				</center>
				</td>
				<td align="right" width="500" rowspan=2>
					<iframe name="iframe1" id="iframe1" width="500px" height="500px" scrolling="Auto" frameborder="2"></iframe>
				</td>	
			</tr>
			</table>
			</center>
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
