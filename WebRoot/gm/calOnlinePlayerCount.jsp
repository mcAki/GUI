<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/incPrefix.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<link href="../css/criteria.css" rel="stylesheet" type="text/css">
			<link href="../css/sys.css" rel="stylesheet" type="text/css">
				<%@ include file="../common/incHead.jsp"%>
	</head>
	<body>
		<%@ include file="../common/incBanner.jsp"%>
		<s:property value="#request.message" />
		<br />
							<s:form id="form1" name="form0"
							action="list!calOnlinePlayerCount.do" namespace="/gmmanage"
							method="post">
							<center>
								<table >
									<tr >
									    <td>请选择日期:&nbsp;</td>
										<td colspan="2">&nbsp;<input id="d52211" name="startDate" class="Wdate" type="text" onFocus="var d52222=$dp.$('d52222');WdatePicker({onpicked:function(){d52222.focus();}, minDate:'%y-{%M-1}-%d' ,maxDate:'#F{$dp.$D(\'d52222\')||\'%y-%M-%d\'}'})"/>
												至
											<input id="d52222" name="endDate" class="Wdate" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'d52211\')}'})"/>&nbsp;</td>
										<td>&nbsp;&nbsp;&nbsp;<input icon="icon-search"  type="submit" value="搜索"/>&nbsp;</td>
									</tr>
								</table>
							</center>
							</s:form>
		<br />
		<br />
		<center>
		<display:table id="idListTb" name="pageView" style="width:650px;"
			pagesize="20" cellspacing="1" class="list_tb"
			requestURI="${pageContext.request.contextPath}/gmmanage/list!calOnlinePlayerCount.do">
			<display:caption>最高在线统计</display:caption>
			<display:column title="时间">
			<c:choose>
				<c:when test="${type==1}"><fmt:formatDate value="${idListTb.createTime}" pattern="yyyy-MM-dd HH:mm"/></c:when>
				<c:when test="${type==2}"><fmt:formatDate value="${idListTb.createTime}" pattern="yyyy-MM-dd HH时"/></c:when>
				<c:when test="${type==3}"><fmt:formatDate value="${idListTb.createTime}" pattern="yyyy-MM-dd"/></c:when>
			</c:choose>
				
			</display:column>
			<display:column property="maxCount" title="在线数" />
		</display:table> </center>
		<br />
		<br />

		<a href="staff!add.html"></a>
		<%@ include file="../common/incFooter.jsp"%>
	</body>
</html>
