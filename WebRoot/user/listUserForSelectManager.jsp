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
		<div align="center">
			<s:form id="form1" name="form0"
			action="list!searchUserForManager" namespace="/users"
			method="post">
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				身份证:
				<input type="text" name="idcardCode" size="25" value="${param.idcardCode}"/>
				电话号码:
				<input type="text" name="mobile" size="20" value="${param.mobile}"/>
				<input icon="icon-search"  type="submit" value="搜索"/>
			</s:form>
		</div>
				<br />
			<display:table id="idListTb" name="pageView" style="width:500px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/list!listUserForSelectManager.do">
			<s:hidden name="userId" />
				<display:column maxLength="5" property="userName" title="用户姓名" />
				<display:column title="用户状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">志愿者</c:when>
						<c:when test="${idListTb.state==1}">拟录用</c:when>
						<c:when test="${idListTb.state==2}">待录用</c:when>
						<c:when test="${idListTb.state==3}">已录用</c:when>
					</c:choose>
				</display:column>
	
				<display:column title="性别">
					<c:choose>
						<c:when test="${idListTb.gender==1}">男</c:when>
						<c:when test="${idListTb.gender==2}">女</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="idcardCode" title="证件号码" />
				<display:column property="serviceTime" title="服务时长" />
				<display:column property="mobile" title="手机号码" />
				<display:column title="操作" ><a href="javacript:void(0)" onclick="selectUser('${idListTb.userId}','${idListTb.userName }')">选择</a></display:column>
			</display:table>
			
		</center>
		<br />
		<br />
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
