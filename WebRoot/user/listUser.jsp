<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list" namespace="/users"
			method="post">
				姓名:
				<input type="text" name="username" size="10" value="${param.username}"/>
				身份证:
				<input type="text" name="idcardCode" size="25" value="${param.idcardCode}"/>
				电话号码:
				<input type="text" name="mobile" size="20" value="${param.mobile}"/>
				<input icon="icon-search"  type="submit" value="搜索"/>
				<br/>
				<center>
					<input type="button" value="按志愿者积分排序" onclick="window.location.href='${pageContext.request.contextPath}/users/list.do?param=1'" />
				</center>
				</s:form>
		</div>
		<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/list.do">

				<display:column title="用户姓名" >
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				</display:column>

				<display:column property="usergroup.groupName" title="所属组名" />
				<display:column title="用户状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">未审核0</c:when>
						<c:when test="${idListTb.state==1}">未审核</c:when>
						<c:when test="${idListTb.state==2}">拟录用</c:when>
						<c:when test="${idListTb.state==3}">待录用</c:when>
						<c:when test="${idListTb.state==4}">已录用</c:when>
					</c:choose>
				</display:column>
				<display:column maxLength="11" property="mobile" title="手机号码" />

				<display:column title="性别">
					<c:choose>
						<c:when test="${idListTb.gender==1}">男</c:when>
						<c:when test="${idListTb.gender==2}">女</c:when>
						<c:otherwise>未知</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column property="districtName" title="来源区"/>
				<display:column property="sourceAreaName" title="来源街道"/>
				<display:column title="操作">
					<c:choose>
						<c:when test="${ sessionScope.user.usergroup.id<idListTb.usergroup.id&&sessionScope.user.usergroup.id<=13}">
							<a href="modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>
						</c:when>
						<c:when test="${sessionScope.user.usergroup.id==1}">
							<a href="list!delUser.do?userId=${idListTb.userId}"
								onclick=javascript: return del();>删除</a>
						</c:when>
					</c:choose>
				</display:column>
					
			</display:table>
		</center>
		<br />
		<br />

		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
