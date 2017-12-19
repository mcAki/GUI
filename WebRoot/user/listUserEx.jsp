
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
		<link href="../css/sys.css" rel="stylesheet" type="text/css">
		<link href="../css/main_new.css" rel="stylesheet" type="text/css">
		<%@ include file="../common/incHead.jsp"%>
	<script type="text/javascript">
		/**
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId.do?userId='
						+ userId , 700, 600);
		}**/
		function showPersonalByUserId(userId){
			var returnVal = ezModal(
				'${pageContext.request.contextPath}/usersutils/showPersonalByUserId4.do?userId='
						+ userId , 700, 600);
		}
	</script>
	</head>
	<body style="min-width: 1100px;">
		<%@ include file="../common/incBanner.jsp"%>
		<%--
		3
		<br />
		<br />
		<br />
		<center>
		<div align="center">
			<s:form id="form0" name="form0"
			action="list!listUserEx" namespace="/users"
			method="post">
				<table>
					<tr>
						<td>&nbsp;用户级别:&nbsp;</td>
						<td><s:select name="userLevel" list="%{#{0:'不限制等级',1:'A级',2:'B级',3:'C级',4:'D级'}}"/></td>
						<td>&nbsp;经销商:&nbsp;</td>
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
			<display:table id="idListTb" name="pageView" style="width:1200px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb"
				requestURI="${pageContext.request.contextPath}/users/list!listUserEx.do">
				<display:caption>一级商户列表</display:caption>
				<display:column property="userCode" title="编号" />
				<display:column title="用户名" >
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				</display:column>
				<c:choose>
					<c:when test="${idListTb.parentUsers!=null}">
						<display:column property="parentUsers.userName" title="上级商户名" />
					</c:when>
				</c:choose>
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
				<display:column title="商户类别">
					<c:choose>
						<c:when test="${idListTb.userLevel.userLevelId==1}">A级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==2}">B级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==3}">C级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==4}">D级</c:when>
						<c:otherwise>无级别</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="defaultAlarm" title="警示界限" />
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="账户余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.balance }"/>
				</display:column>
				<display:column title="佣金余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.commission }"/>
				</display:column>
				<display:column property="terminalCount" title="终端数量" />
				<display:column property="reversalCount" title="可冲正次数" />
				<display:column property="maxReversalCount" title="最大可冲正次数" />
				<display:column property="otp.keyId" title="加密key" />
				<display:column property="recodeOperator" title="操作员" />
				<display:column title="操作">
					 <c:if test="${sessionScope.user.usergroup.id<=3
					 || (sessionScope.user.userId==idListTb.parentUserId)}">
						<a href="modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>&nbsp;
						<a href="page!recharge.do?userId=${idListTb.userId}">设置额度</a>&nbsp;
						<c:choose>
							<c:when test="${idListTb.state==0}">
								<a href="list!delUser.do?userId=${idListTb.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${idListTb.state==2}">
								<a href="list!recoverUser.do?userId=${idListTb.userId}">恢复</a>
							</c:when>
						</c:choose>
						&nbsp;<a href="commitAccount.do?userId=${idListTb.userId}">更新账户余额</a>&nbsp;
					</c:if>
					<c:if test="${sessionScope.user.usergroup.id<=2}">
						<a href="page!addExEx.do?userId=${idListTb.userId}">增加二级商户</a>
						<a href="page!addKey.do?userId=${idListTb.userId}">设置加密key</a>
						<a href="${pageContext.request.contextPath}/otp/page!testPage.do?userId=${idListTb.userId }">测试密保</a>
					</c:if>
					<c:if test="${(sessionScope.user.usergroup.id<=4||sessionScope.user.userId==idListTb.userId)&&idListTb.usergroup.id==4}">
						<a href="list!manageUserEx.do?userId=${idListTb.userId}">查看</a>
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
						<div class="quota cxjjtable" style="width: 80%">
							<s:form id="form0" name="form0"
			action="list!listUserEx" namespace="/users"
			method="post">
				<table>
					<tr>
						<td>&nbsp;用户级别:&nbsp;</td>
						<td><s:select name="userLevel" list="%{#{0:'不限制等级',1:'A级',2:'B级',3:'C级',4:'D级'}}"/></td>
						<td>&nbsp;经销商:&nbsp;</td>
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
							一级商户列表
						</p>

					</div>
					<div class="modify">
						<center>
						<display:table id="idListTb" name="pageView" style="min-width:1000px;"
				sort="external" pagesize="${pageSize}" cellspacing="1"
				class="list_tb_002"
				requestURI="${pageContext.request.contextPath}/users/list!listUserEx.do">
				<display:column property="userCode" title="编号" />
				<display:column title="用户名" >
				  <%--
					<a href="javascript:void(0);" onclick="showPersonalByUserId('${idListTb.userId}')">${idListTb.userName }</a>
				   --%>
					<a href="/MPRS/users/list!manageUserEx.do?userId=${idListTb.userId}" >${idListTb.userName }</a>
				</display:column>
				<c:choose>
					<c:when test="${idListTb.parentUsers!=null}">
						<display:column property="parentUsers.userName" title="上级商户名" />
					</c:when>
				</c:choose>
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
				<display:column title="商户类别">
					<c:choose>
						<c:when test="${idListTb.userLevel.userLevelId==1}">A级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==2}">B级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==3}">C级</c:when>
						<c:when test="${idListTb.userLevel.userLevelId==4}">D级</c:when>
						<c:otherwise>无级别</c:otherwise>
					</c:choose>
				</display:column>
				<display:column property="defaultAlarm" title="警示界限" />
				<display:column property="createDate" title="建立时间"
					format="{0,date,yy-MM-dd HH:mm}" />
				<display:column title="账户余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.balance }"/>
				</display:column>
				<display:column title="佣金余额">
					<fmt:formatNumber maxFractionDigits="2" value="${idListTb.useraccount.commission }"/>
				</display:column>
				<display:column property="terminalCount" title="终端数量" />
				<display:column property="reversalCount" title="可冲正次数" />
				<display:column property="maxReversalCount" title="最大可冲正次数" />
				<display:column property="otp.keyId" title="加密key" />
				<display:column property="recodeOperator" title="操作员" />
				<%-- 
				<display:column title="操作">
					 <c:if test="${sessionScope.user.usergroup.id<=3
					 || (sessionScope.user.userId==idListTb.parentUserId)}">
						<a href="/MPRS/users/modifyUser!viewUser.do?userId=${idListTb.userId}">修改</a>&nbsp;
						<a href="/MPRS/users/page!recharge.do?userId=${idListTb.userId}">设置额度</a>&nbsp;
						<c:choose>
							<c:when test="${idListTb.state==0}">
								<a href="/MPRS/users/list!delUser.do?userId=${idListTb.userId}"
									onclick=javascript: return del();>禁用</a>
							</c:when>
							<c:when test="${idListTb.state==2}">
								<a href="/MPRS/users/list!recoverUser.do?userId=${idListTb.userId}">恢复</a>
							</c:when>
						</c:choose>
						&nbsp;<a href="/MPRS/users/commitAccount.do?userId=${idListTb.userId}">更新账户余额</a>&nbsp;
					</c:if>
					<c:if test="${sessionScope.user.usergroup.id<=2}">
						<a href="/MPRS/users/page!addExEx.do?userId=${idListTb.userId}">增加二级商户</a>
						<a href="/MPRS/users/page!addKey.do?userId=${idListTb.userId}">设置加密key</a>
						<a href="${pageContext.request.contextPath}/otp/page!testPage.do?userId=${idListTb.userId }">测试密保</a>
					</c:if>
					<c:if test="${(sessionScope.user.usergroup.id<=4||sessionScope.user.userId==idListTb.userId)&&idListTb.usergroup.id==4}">
						<a href="/MPRS/users/list!manageUserEx.do?userId=${idListTb.userId}">查看</a>
					</c:if>
				</display:column>
					 --%>
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
