
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body style="min-width: 750px;">
		<%@ include file="../common/incBanner.jsp"%>
		<%--
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list" namespace="/users"
			method="post">
				<table>
					<tr>
						<td>员工:&nbsp;</td>
						<td>&nbsp;<input type="text" name="username" size="10" value="${param.username}"/>&nbsp;</td>
						<td>&nbsp;电话号码:&nbsp;</td>
						<td>&nbsp;<input type="text" name="mobile" size="20" value="${param.mobile}"/>&nbsp;</td>
						<td>&nbsp;用户状态:&nbsp;</td>
						<td><s:select name="state" list="%{#{0:'正常',1:'冻结',2:'禁用'}}"/></td>
						<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
					</tr>
				</table>
				</s:form>
		</div>
		<center>
			<display:table id="idListTb" name="pageView" style="width:900px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/list.do">
				<display:caption>员工列表</display:caption>
				<display:column property="userCode" title="编号" />
				<display:column title="用户名" >
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				</display:column>
				<display:column property="usergroup.groupName" title="所属组名" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">正常</c:when>
						<c:when test="${idListTb.state==1}">冻结</c:when>
						<c:when test="${idListTb.state==2}">禁用</c:when>
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
				<display:column property="defaultAlarm" title="警示界限" />
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="账户余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.balance }"/>
				</display:column>
				<display:column title="操作">
					 <c:if test="${sessionScope.user.usergroup.id==1}">
						<a href="modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>&nbsp;
						<c:choose>
							<c:when test="${idListTb.state==0}">
								<a href="list!delUser.do?userId=${idListTb.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${idListTb.state==2}">
								<a href="list!recoverUser.do?userId=${idListTb.userId}">恢复</a>
							</c:when>
						</c:choose>
					</c:if>
				</display:column>
					
			</display:table>
		</center>
		<br />
		<br />
     --%>
     
     <div id="comInfo" class="fixed">
			<div id="contain" class="fixed">
				<s:include value="../system/head_div.jsp"></s:include>
				<div class="tqyj fixed">
					<div class="cxjj fixed">
						<div class="quota cxjjtable" style="width:700px;">
							 <s:form id="form0" name="form0"
								action="list" namespace="/users"
								method="post">
									<table>
										<tr>
											<td>员工:&nbsp;</td>
											<td>&nbsp;<input type="text" name="username" size="10" value="${param.username}"/>&nbsp;</td>
											<td>&nbsp;电话号码:&nbsp;</td>
											<td>&nbsp;<input type="text" name="mobile" size="20" value="${param.mobile}"/>&nbsp;</td>
											<td>&nbsp;用户状态:&nbsp;</td>
											<td><s:select name="state" list="%{#{0:'正常',1:'冻结',2:'禁用'}}"/></td>
											<td>&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
										</tr>
									</table>
									</s:form>
						</div>
						<p class="jjcx">
							员工列表
						</p>

					</div>
			<div class="modify">
			  <center>
				<display:table id="idListTb" name="pageView" style="min-width:650px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002"
				requestURI="${pageContext.request.contextPath}/users/list.do">
				<display:column property="userCode" title="编号" />
				<display:column title="用户名" >
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				</display:column>
				<display:column property="usergroup.groupName" title="所属组名" />
				<display:column title="状态" >
					<c:choose>
						<c:when test="${idListTb.state==0}">正常</c:when>
						<c:when test="${idListTb.state==1}">冻结</c:when>
						<c:when test="${idListTb.state==2}">禁用</c:when>
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
				<display:column property="defaultAlarm" title="警示界限" />
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="账户余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.balance }"/>
				</display:column>
				<display:column title="操作">
					 <c:if test="${sessionScope.user.usergroup.groupType==1}">
						<a href="/MPRS/users/modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>&nbsp;
						<c:choose>
							<c:when test="${idListTb.state==0}">
								<a href="/MPRS/users/list!delUser.do?userId=${idListTb.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${idListTb.state==2}">
								<a href="/MPRS/users/list!recoverUser.do?userId=${idListTb.userId}">恢复</a>
							</c:when>
						</c:choose>
					</c:if>
				</display:column>
					
			</display:table>
			</center>
					</div>
				</div>
			</div>
		</div>	
	
		<a href="user!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
