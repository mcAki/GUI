<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		
		<script type="text/javascript">
			function selectUser(id,username){
				var userpack=new Object();
				userpack.uid=id;				
				userpack.username=username;				
				window.returnValue = userpack;
				window.close(); 
			}

		</script>
		<!-- 不创建新窗口，在本窗口显示东西 -->
		<base target="_self">
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
				<br />
			<display:table id="idListTb" name="pageView" style="width:500px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/list!listGroupForLog.do">
			<s:hidden name="userId" />
				<display:column title="用户组名称" property="groupName"/>
				<display:column title="用户组类型" >
					<c:choose>
						<c:when test="${idListTb.groupType==1}">超级管理员</c:when>
						<c:when test="${idListTb.groupType==2}">员工</c:when>
						<c:when test="${idListTb.groupType==3}">查询</c:when>
						<c:when test="${idListTb.groupType==4}">一级商户</c:when>
						<c:when test="${idListTb.groupType==5}">二级商户</c:when>
					</c:choose>
				</display:column>
				<display:column title="操作" ><a href="javacript:void(0)" onclick="selectUser('${idListTb.id}','${idListTb.groupName }')">选择</a></display:column>
			</display:table>
			
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
